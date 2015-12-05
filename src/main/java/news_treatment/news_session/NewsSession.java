package news_treatment.news_session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NewsSession {

    final String SESSION_ID_KEY = "sessionPeekCount";
    private boolean sessionFlag = false;
    private long userCount = 0;

    public boolean isFirstTime(HttpServletRequest request) {

        HttpSession httpSession = request.getSession(true);

        Integer ival = (Integer) httpSession.getAttribute(SESSION_ID_KEY);
        if (ival == null) {
            setSessionAttribute(httpSession, 1, true);
            return true;
        } else {
            setSessionAttribute(httpSession, ival + 1, false);
            return false;
        }
    }

    public void setSessionAttribute(HttpSession httpSession, int value, boolean flagValue) {

        Integer ival = value;
        httpSession.setAttribute(SESSION_ID_KEY, ival);
        userCount++;
        sessionFlag = flagValue;

    }

    public boolean getSessionFlag() {
        return sessionFlag;
    }

    public long getUserCount() {
        return userCount;
    }

}

