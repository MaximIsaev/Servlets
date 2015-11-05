package com.main_servlet;

import com.json_news_item.JSONContainer;
import com.news_filters.FilteringByAuthor;
import com.news_filters.NewsFilter;
import com.xml_parser.XMLParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    JSONContainer jsonContainer = new JSONContainer();
    XMLParser xmlParser = new XMLParser(jsonContainer);
    FilteringByAuthor filteringByAuthor = new FilteringByAuthor("", jsonContainer);


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        xmlParser.parse();

        PrintWriter out = resp.getWriter();
        jsonContainer.displayJsonObjects(out);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);

    }
}
