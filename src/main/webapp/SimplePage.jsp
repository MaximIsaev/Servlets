<%@ page import="com.jsp_handler.JspLogic" %>
<%@ page import="com.news_session.NewsSession" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    JspLogic jspLogic = new JspLogic();
    NewsSession newsSession = new NewsSession();
    newsSession.checkSession(request);
    if (newsSession.getSessionFlag()) {
%>

<h1><%=jspLogic.getWelcomeTitle()%>
    <%
        } else {
            out.println(jspLogic.getServerTimeTitle());
        }
        jspLogic.serverTime(out);
    %>
    <br>

</h1>
<br>

<%
    if (!jspLogic.getParameters(request)) { // Нет полей
%>

<form method="POST" action="SimplePage.jsp">
    <h2>Please input folder name with images</h2>

    Folder name: <input type="text" size="20" name="Folder_name">

    <INPUT TYPE=submit name=submit value="Check folder">
</form>


<%
} else {
    jspLogic.checkFolder(request, out);
%>
<%=jspLogic.getExistExpression()%>

<br>

<p1><b>Content of folder:<%="\"" + jspLogic.getImgFolderHomePath() + "\""%>:</b><br><br>
</p1>

<%
    jspLogic.displayImgFolderContent(out);
%>
<br>
<%
    }
%>
</body>
</html>