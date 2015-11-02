package com.logging;


import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LoggerListner implements HttpSessionListener, ServletContextListener, ServletContextAttributeListener {

    private static int totalActiveSessions;

    public static int getTotalActiveSession() {
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        totalActiveSessions++;
        System.out.println("sessionCreated - add one session into counter");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        totalActiveSessions--;
        System.out.println("sessionDestroyed - deduct one session from counter");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener destroyed");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener started");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("An attribute was added to the ServletContext object");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("An attribute was removed from the ServletContext object");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("An attribute was replaced in the ServletContext object");
    }
}
