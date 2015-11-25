package news_treatment.jsp_handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JspView {

    static final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\";

    public void setWelcomeTitle(String welcomeTitle) {
        this.welcomeTitle = welcomeTitle;
    }

    private String welcomeTitle = "Welcome! The server time is now";
    private String serverTimeTitle = "Server time: ";

    private int countImagesFolderContent;

    private List<String> filesNamesInImgFolder = new ArrayList<>();

    public List<String> getFilesNamesInImgFolder() {
        return filesNamesInImgFolder;
    }

    String field;
    String imgFolderIsEmpty;
//    List<String> imgFolderContent = new ArrayList<>();

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


//    public boolean getParameters(HttpServletRequest request) {
//        fields = request.getParameterNames();
//        return fields.hasMoreElements();
//    }

    public String checkFields(HttpServletRequest request) throws IOException {
//        while (fields.hasMoreElements()) {
        field = request.getParameter("Folder_name");
//        String field = (String) fields.nextElement();
        return checkFolderByFullPath(field, request);
//        }
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


    public void getImgFolderContent() throws IOException {
        if (!rootImgFolder.exists()) {
            rootImgFolder.mkdir();
        }
        File folderExport[] = rootImgFolder.listFiles();
        String[] foldersNames = new String[folderExport.length];
        if (foldersNames.length == 0) {
            imgFolderIsEmpty = "Folder \"images\" is empty";
        } else {
            countImagesFolderContent = foldersNames.length;
            getImgFolderContent(folderExport, foldersNames);
        }
    }

    private void getImgFolderContent(File[] folderExport, String[] foldersNames) throws IOException {
        for (int i = 0; i < foldersNames.length; i++) {
            filesNamesInImgFolder.add(folderExport[i].getName());
        }
//        return filesNamesInImgFolder;
    }


    private String checkFolderByFullPath(String field, HttpServletRequest request) {

        String folderFullPath = IMG_FOLDER_HOME_PATH + field;
        File folder = new File(folderFullPath);
        return checkFolderExist(folder, request, field, folderFullPath);
    }

    private String checkFolderExist(File folder, HttpServletRequest request, String field, String folderFullPath) {

        if (folder.exists() && folder.isDirectory()) {
            return existExpression = "Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is EXIST!";
        } else {
            return existExpression = "Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is NOT EXIST!";
        }
    }

}
