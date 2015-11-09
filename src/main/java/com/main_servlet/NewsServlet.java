package com.main_servlet;

import com.xml_parser.XMLParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NewsServlet extends HttpServlet {

    XMLParser xmlParser;

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        xmlParser = (XMLParser) context.getBean("xmlParser");

        xmlParser.parse();

        PrintWriter out = resp.getWriter();
        xmlParser.getJsonStorage().displayContent(out);
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
