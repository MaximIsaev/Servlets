package com.main_servlet;

import com.json_news_item.JSONContainer;
import com.xml_parser.XMLParser;
import org.json.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


public class NewsServlet extends HttpServlet {
//    File file = new File("D:/Tomcat/rss.builder.feedrss.builder.xml");
    ServletConfig conf;


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        Enumeration fields = req.getParameterNames();



        XMLParser.parser();

        PrintWriter out = resp.getWriter();
        out.println("Deploy is working");

        out.println();
        //JSONContainer.displayJsonObjects(out);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }
}
