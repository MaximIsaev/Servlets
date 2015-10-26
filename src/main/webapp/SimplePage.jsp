<%@ page import="com.jsp_handler.JspLogic" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    if (JspLogic.checkSession(request, response, session, out)) {
%>

<h1><%=JspLogic.welcomeTitle%>
    <%
        } else {
            out.println(JspLogic.serverTime);
        }
        JspLogic.serverTime(out);
    %>

</h1>
<br>

<%
    if (!JspLogic.getParameters(request)) { // Нет полей
%>

<form method="POST" action="SimplePage.jsp">
    <h2>Please input folder name with images</h2>

    Folder name: <input type="text" size="20" name="Folder_name">

    <INPUT TYPE=submit name=submit value="Check folder">
</form>


<%
} else {
    JspLogic.checkFolder(request, out);
%>

<br>

<p1><b>Content of folder:<%="\"" + JspLogic.getImgFolderHomePath() + "\""%>:</b><br><br>
</p1>

<%
        JspLogic.displayImgFolderContent(out);
    }
%>
</body>
</html>