package com.news_session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewsSession {

    public static boolean checkSession(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        long sessionID = 0;
        final String SESSION_ID_KEY = "ID";
        int userCount = 0;

        HttpSession httpSession = request.getSession(true);

        if (httpSession.isNew()) {
            httpSession.setAttribute(SESSION_ID_KEY, sessionID);
            sessionID++;
            userCount++;
            return true;
        } else {
            sessionID = (Integer) httpSession.getAttribute(SESSION_ID_KEY);
            return false;
        }
    }
}
