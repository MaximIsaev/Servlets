<%@ page import="com.jsp_handler.DynamicJspContent" %>
<%@ page import="com.news_session.NewsSession" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    DynamicJspContent dynamicJspContent = (DynamicJspContent) context.getBean("dynamicJspContent");
    NewsSession newsSession = (NewsSession) context.getBean("newsSession");
    newsSession.checkSession(request);
    if (newsSession.getSessionFlag()) {
%>

<h1><%=dynamicJspContent.getWelcomeTitle()%>
    <%
        } else {
            out.println(dynamicJspContent.getServerTimeTitle());
        }
        dynamicJspContent.serverTime(out);
    %>
    <br>

</h1>
<br>

<%
    if (!dynamicJspContent.getParameters(request)) { // Нет полей
%>

<form method="POST" action="SimplePage.jsp">
    <h2>Please input folder name with images</h2>

    Folder name: <input type="text" size="20" name="Folder_name">

    <INPUT TYPE=submit name=submit value="Check folder">
</form>


<%
} else {
    dynamicJspContent.checkFolder(request, out);
%>
<%=dynamicJspContent.getExistExpression()%>

<br>

<p1><b>Content of folder:<%="\"" + dynamicJspContent.getImgFolderHomePath() + "\""%>:</b><br><br>
</p1>

<%
    dynamicJspContent.displayImgFolderContent(out);
%>
<br>
<%
    }
%>
</body>
</html>