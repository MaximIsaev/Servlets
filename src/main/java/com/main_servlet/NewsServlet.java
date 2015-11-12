package com.main_servlet;

import com.json_news_item.JSONStorage;
import com.xml_parser.XMLParserToJson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    JSONStorage jsonStorage;
    XMLParserToJson xmlParserToJson = new XMLParserToJson();


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        jsonStorage.displayContent(out);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
       jsonStorage =  xmlParserToJson.parseToJson();
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
