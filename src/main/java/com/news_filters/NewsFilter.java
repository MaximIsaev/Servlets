package com.news_filters;


import com.xml_parser.XMLParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


@WebFilter(filterName = "Filter", urlPatterns = "/news")
public class NewsFilter implements Filter {

    FilteringByAuthor filteringByAuthor = new FilteringByAuthor("",XMLParser.getJsonContainer());

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

        ServletResponse newResponse = response;

        if (request instanceof HttpServletRequest) {
            newResponse = new CharResponseWrapper((HttpServletResponse) response);
        }

        chain.doFilter(request, newResponse);

        if (newResponse instanceof CharResponseWrapper) {
            String text = newResponse.toString();
            checkText(text, response);
        }
    }

    public void checkText(String text, ServletResponse response) throws IOException {
        if (text != null) {
            filteringJsonArray(text, response);
        } else {
            response.getWriter().write("No text in response!");
        }
    }

    public void filteringJsonArray(String text, ServletResponse response) {

        try {
            Object object = parser.parse(text);
            jsonArray = (JSONArray) object;
            filteringByAuthor.filter(jsonArray, response.getWriter());
        } catch (Exception e) {
            log.info("Execute ParseException or IOException in NewsFilter.java");
            e.printStackTrace();
        }

    }
}