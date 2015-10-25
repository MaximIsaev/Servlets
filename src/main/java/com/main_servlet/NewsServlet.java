package com.main_servlet;

import com.json_news_item.JSONContainer;
import com.xml_parser.XMLParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    private final static Logger newsServletLog = Logger.getLogger(com.main_servlet.NewsServlet.class.getName());


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setStatus(200);
        newsServletLog.info("processRequest invoked successfully");
        resp.setContentType("text/html; charset=utf-8");

        XMLParser.parser();

        PrintWriter out = resp.getWriter();
        out.println("Deploy is working <br>");

        out.println();
        JSONContainer.displayJsonObjects(out);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        newsServletLog.info("doGet invoked successfully");
        this.processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        newsServletLog.info("doGet invoked successfully");
        this.processRequest(req, resp);

    }
}
