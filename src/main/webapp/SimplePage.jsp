<%@ page import="com.jsp_handler.JspLogic" %>
<%@ page import="com.news_session.NewsSession" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    NewsSession.checkSession(request, response, session);
    if (NewsSession.getSessionFlag()) {
%>

<h1><%=JspLogic.welcomeTitle%>
    <%
        } else {
            out.println(JspLogic.serverTime);
        }
        JspLogic.serverTime(out);
    %>
    <br>

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
<%=JspLogic.getExistExpression()%>

<br>

<p1><b>Content of folder:<%="\"" + JspLogic.getImgFolderHomePath() + "\""%>:</b><br><br>
</p1>

<%
    JspLogic.displayImgFolderContent(out);
%>
<br>
<%
    }
%>
</body>
</html>