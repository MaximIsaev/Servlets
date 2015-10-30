package com.news_session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewsSession {

    private static boolean sessionFlag = false;
    private static long userCount = 0;

    public static void checkSession(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        final String SESSION_ID_KEY = "sessionPeekCount";

        HttpSession httpSession = request.getSession(true);

        Integer ival = (Integer) httpSession.getAttribute(SESSION_ID_KEY);
        if (ival == null) {
            ival = new Integer(1);
            httpSession.setAttribute(SESSION_ID_KEY, ival);
            userCount++;
            sessionFlag = true;

        } else {
            ival = new Integer(ival.intValue() + 1);
            httpSession.setAttribute(SESSION_ID_KEY, ival);
            userCount++;
            sessionFlag = false;
        }
    }

    public static boolean getSessionFlag() {
        return sessionFlag;
    }

    public long getUserCount() {
        return userCount;
    }

}

