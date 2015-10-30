package com.news_filters;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "Filter", urlPatterns = "/news")
public class NewsFilter implements Filter {

    protected FilterConfig config;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = new JSONArray();

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
            if (text != null) {
                try {
                    Object object = parser.parse(text);
                    jsonArray = (JSONArray) object;
                    FilteringByAuthor.filter(jsonArray, response.getWriter());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}