package news_treatment.jsp_handler;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JspView {

    static final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\";


    private String welcomeTitle = "Welcome! The server time is now";
    private String serverTimeTitle = "Server time: ";

    private int countImagesFolderContent;

    private List<String> filesNamesInImgFolder = new ArrayList<>();

    public List<String> getFilesNamesInImgFolder() {
        return filesNamesInImgFolder;
    }

    String imgFolderIsEmpty;

    File rootImgFolder = new File(System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\");
    private String existExpression;


    public String checkFields(String field) throws IOException {
        if (field == null) {
            return null;
        } else {
            System.out.println(checkFolderByFullPath(field));
            return checkFolderByFullPath(field);
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


    public List<String> getImgFolderContent() throws IOException {
        filesNamesInImgFolder.clear();
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
        return filesNamesInImgFolder;
    }

    private void getImgFolderContent(File[] folderExport, String[] foldersNames) throws IOException {
        for (int i = 0; i < foldersNames.length; i++) {
            filesNamesInImgFolder.add(folderExport[i].getName());
        }
    }


    private String checkFolderByFullPath(String field) {

        String folderFullPath = IMG_FOLDER_HOME_PATH + field;
        File folder = new File(folderFullPath);
        return checkFolderExist(folder, field, folderFullPath);
    }

    private String checkFolderExist(File folder, String field, String folderFullPath) {

        if (folder.exists() && folder.isDirectory()) {
            return existExpression = "Folder " + "\"" + field + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is EXIST!";
        } else {
            return existExpression = "Folder " + "\"" + field + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is NOT EXIST!";
        }
    }

}
