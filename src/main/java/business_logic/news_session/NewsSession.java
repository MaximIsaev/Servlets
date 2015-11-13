package business_logic.news_session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NewsSession {

    final String SESSION_ID_KEY = "sessionPeekCount";
    private boolean sessionFlag = false;
    private long userCount = 0;

    public void checkSession(HttpServletRequest request) {


        HttpSession httpSession = request.getSession(true);

        Integer ival = (Integer) httpSession.getAttribute(SESSION_ID_KEY);
        if (ival == null) {
            setSessionAttribute(httpSession, 1);
        } else {
            setSessionAttribute(httpSession, ival.intValue() + 1);
        }
    }

    public void setSessionAttribute(HttpSession httpSession, int value) {

        Integer ival = new Integer(value);
        httpSession.setAttribute(SESSION_ID_KEY, ival);
        userCount++;
        sessionFlag = true;

    }

    public boolean getSessionFlag() {
        return sessionFlag;
    }

    public long getUserCount() {
        return userCount;
    }

}

