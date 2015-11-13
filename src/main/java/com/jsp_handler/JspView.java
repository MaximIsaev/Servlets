package com.jsp_handler;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.jsp.*;

public class JspView {

    static final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\";
    private static final String welcomeTitle = "Welcome! The server time is now";
    static final String serverTimeTitle = "Server time: ";

    private int countImagesFolderContent;

    private List<String> filesNamesInImgFolder = new ArrayList<>();

    public List<String> getFilesNamesInImgFolder() {
        return filesNamesInImgFolder;
    }

    Enumeration fields;
    File rootImgFolder = new File(System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\");
    private String existExpression;

    public int getCountImagesFolderContent() {
        return countImagesFolderContent;
    }

    public void serverTime(JspWriter out) throws IOException {

        java.util.Calendar now = java.util.Calendar.getInstance();
        int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = now.get(java.util.Calendar.MINUTE);
        if (hour < 10) {
            out.println("0" + hour);
        } else {
            out.println(hour);
        }
        out.println(":");
        if (minute < 10) {
            out.println("0" + minute);
        } else {
            out.println(minute);
        }
    }


    public boolean getParameters(HttpServletRequest request) {
        fields = request.getParameterNames();
        return fields.hasMoreElements();
    }

    public void checkFields(HttpServletRequest request) throws IOException {
        while (fields.hasMoreElements()) {
            String field = (String) fields.nextElement();
            checkFolderByFullPath(field, request);
        }
    }

    public String getExistExpression() {
        return existExpression;
    }

    public String getImgFolderHomePath() {
        return IMG_FOLDER_HOME_PATH;
    }

    public String getWelcomeTitle() {
        return welcomeTitle;
    }

    public String getServerTimeTitle() {
        return serverTimeTitle;
    }


    public void getImgFolderContent(JspWriter out) throws IOException {
        if (!rootImgFolder.exists()) {
            rootImgFolder.mkdir();
        }
        File folderExport[] = rootImgFolder.listFiles();
        String[] foldersNames = new String[folderExport.length];
        if (foldersNames.length == 0) {
            out.println("Folder \"images\" is empty");
        } else {
            countImagesFolderContent = foldersNames.length;
            getImgFolderContent(folderExport, foldersNames);
        }
    }

    private void getImgFolderContent(File[] folderExport, String[] foldersNames) throws IOException {
        for (int i = 0; i < foldersNames.length; i++) {
            filesNamesInImgFolder.add(folderExport[i].getName());
        }
    }


    private void checkFolderByFullPath(String field, HttpServletRequest request) {//rename method;

        String folderFullPath = IMG_FOLDER_HOME_PATH + request.getParameter(field);
        File folder = new File(folderFullPath);
        checkFolderExist(folder, request, field, folderFullPath);
    }

    private void checkFolderExist(File folder, HttpServletRequest request, String field, String folderFullPath) {

        if (folder.exists() && folder.isDirectory() && folderFullPath.equals("")) {
            existExpression = "Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is EXIST!";
        } else {
            existExpression = "Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is NOT EXIST!";
        }

    }

}
