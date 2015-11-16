<%@ page import="news_treatment.jsp_handler.JspView" %>
<%@ page import="news_treatment.news_session.NewsSession" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    JspView jspView = new JspView();
    NewsSession newsSession = new NewsSession();
    newsSession.checkSession(request);
    if (newsSession.getSessionFlag()) {
%>

<h1><%=jspView.getWelcomeTitle()%>
    <%
        } else {
            out.println(jspView.getServerTimeTitle());
        }
        jspView.serverTime(out);
    %>
    <br>

</h1>
<br>

<%
    if (!jspView.getParameters(request)) { // Нет полей
%>

<form method="POST" action="SimplePage.jsp">
    <h2>Please input folder name with images</h2>

    Folder name: <input type="text" size="20" name="Folder_name">

    <INPUT TYPE=submit value="Check folder">
</form>


<%
} else {
    jspView.checkFields(request);
%>
<br>
<%=jspView.getExistExpression()%>

<br>

<p1><b>Content of folder:<%="\"" + jspView.getImgFolderHomePath() + "\""%>:</b><br><br>
</p1>

<%
    jspView.getImgFolderContent(out);
    for (int i = 0; i < jspView.getCountImagesFolderContent(); i++) {
%>
<li><%=jspView.getFilesNamesInImgFolder().get(i)%>
</li>
<%
    }
%>
<br>
<%
    }
%>
</body>
</html>



















