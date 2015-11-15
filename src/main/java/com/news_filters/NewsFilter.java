package com.news_filters;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


@WebFilter(filterName = "Filter", urlPatterns = "/news")
public class NewsFilter implements Filter {

    FilteringByAuthor filteringByAuthor = new FilteringByAuthor("");

    protected FilterConfig config;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = new JSONArray();
    private static Logger log = Logger.getLogger(NewsFilter.class.getName());

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        ServletResponse newResponse;

        newResponse = new CharResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, newResponse);

        String text = newResponse.toString();
        try {
            checkText(response, text);
        } catch (ParseException e) {
            log.info("Execute ParseException in NewsFilter.java");
            e.printStackTrace();
        }
    }

    private void checkText(ServletResponse response, String text) throws IOException, ParseException {
        if (text != null) {
            filteringJsonArray(response, text);
        } else {
            response.getWriter().write("No text in response!");
        }
    }

    private void filteringJsonArray(ServletResponse response, String text) throws IOException, ParseException {
        
        Object object = parser.parse(text);
        jsonArray = (JSONArray) object;
        filteringByAuthor.filter(jsonArray, response.getWriter());

    }
}