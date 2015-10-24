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

<%--<%--%>
<%--request.getSession(true);--%>
<%--session.setAttribute("ID","11");--%>

<%--%>--%>
<center><h1>Welcome! The server time is now

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
    %></h1>

    <h2>Please input folder name with images</h2>

    <%
        final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\images\\";
        final String JSP_URL = "http://localhost:8080/SimplePage.jsp";

        File rootImgFolder = new File(IMG_FOLDER_HOME_PATH);
        String fullPath = "";
        Enumeration fields = request.getParameterNames();
        Cookie cookie = new Cookie("greeting", "Welcome!");
        response.addCookie(cookie);


        if (!fields.hasMoreElements()) { // Нет полей
    %>

    <form method="POST" action="SimplePage.jsp">

        Folder name: <input type="text" size="20" name="Folder_name">

        <INPUT TYPE=submit name=submit value="Check folder">
    </form>
    <%
    } else {
        while (fields.hasMoreElements()) {
            String field = (String) fields.nextElement();
            if (!field.equals("submit")) {
                String folderFullPath = IMG_FOLDER_HOME_PATH + request.getParameter(field);
                out.println("<b>" + folderFullPath + "</b>");
                fullPath = folderFullPath;
    %>
    <br>
    <%
                File folder = new File(folderFullPath);
                if (folder.exists() && folder.isDirectory()) {
                    out.println("Folder " + "\"" + request.getParameter(field) + "\"" + " is Exist!" + "<br>");
                } else {
                    out.println("Folder's not exist!");
                }
            }
        } %><br>
    <p1><b>Content of folder:<%="\"" + fullPath + "\""%>:</b><br><br>
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
                    out.println("(Дата создания: " + attr.creationTime(). + ")</li><br>");
                }

            }
        }

    %>


    <%
        }
    %>
</center>
</body>
</html>