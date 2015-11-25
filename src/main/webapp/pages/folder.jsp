<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Choose Folder</title>
</head>
<body>
<h1>${greeting}</h1>

<form method="get">
    <h2>Please input folder name with images</h2>
    Folder name: <input type="text" size="20" name="Folder_name">
    <INPUT TYPE=submit value="Check folder">
</form>

<br>
${existExpression}
<br>

<p1><b>Content of folder:${imgFolderHomePath}</b><br></p1>

<c:forEach var="item" items="${folders}">
    <li><c:out value="${item}"/></li>
    <br>
</c:forEach>
</body>
</html>



















