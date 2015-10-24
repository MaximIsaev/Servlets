<%@ page import="java.util.Enumeration" %>
<%@ page import="java.io.File" %>
<%@ page import="java.nio.file.attribute.BasicFileAttributes" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Path" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    String welcomeTitle = "Welcome! The server time is now";
    String serverTime = "Server time: ";
    request.getSession(true);
    if (session.getAttribute("ID") == null) {
        session.setAttribute("ID", "1");
%>
<h1><%=welcomeTitle%>

    <%
        } else if (session.getAttribute("ID") == "1") {
            out.println(serverTime);
        }
    %>
    <%
        java.util.Calendar now = java.util.Calendar.getInstance();
        int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = now.get(java.util.Calendar.MINUTE);
        if (hour < 10)
            out.println("0" + hour);
        else
            out.println(hour);
        out.println(":");
        if (minute < 10)
            out.println("0" + minute);
        else
            out.println(minute);
    %></h1><br>


<%
    final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\images\\";
    final String JSP_URL = "http://localhost:8080/SimplePage.jsp";

    File rootImgFolder = new File(IMG_FOLDER_HOME_PATH);
    Enumeration fields = request.getParameterNames();
    Cookie cookie = new Cookie("greeting", "Welcome!");
    response.addCookie(cookie);


    if (!fields.hasMoreElements()) { // Нет полей
%>

<form method="POST" action="SimplePage.jsp">
    <h2>Please input folder name with images</h2>

    Folder name: <input type="text" size="20" name="Folder_name">

    <INPUT TYPE=submit name=submit value="Check folder">
</form>
<%
} else {
    while (fields.hasMoreElements()) {
        String field = (String) fields.nextElement();
        if (!field.equals("submit")) {
            String folderFullPath = IMG_FOLDER_HOME_PATH + request.getParameter(field);
            fullPath = IMG_FOLDER_HOME_PATH;
%>
<br>
<%
            File folder = new File(folderFullPath);
            if (folder.exists() && folder.isDirectory()) {
                out.println("Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is EXIST!<br>");
            } else {
                out.println("Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is NOT EXIST!<br>");
            }
        }
    } %><br>
<p1><b>Content of folder:<%="\"" + IMG_FOLDER_HOME_PATH + "\""%>:</b><br><br>
</p1>
<%
    File folderExport[] = rootImgFolder.listFiles();
    BasicFileAttributes attr;
    Path filePath;
    String[] foldersNames = new String[folderExport.length];
    if (foldersNames.length == 0) {
        out.println("Folder \"img\" is empty");
    } else {
        for (int i = 0; i < foldersNames.length; i++) {
            filePath = folderExport[i].toPath();
            attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            out.println("<li>" + folderExport[i].getName());
            if (attr.creationTime() != null) {
                out.println("(Дата создания: " + attr.creationTime() + ")</li><br>");
            }

        }
    }

%>


<%
    }
%>
</body>
</html>