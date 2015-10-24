<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
</head>
<body>
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
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) { // Нет полей
    %>

    <form method="POST" action="SimplePage.jsp">
        <%--<%--%>
        <%--for (int i = 0; i < 10; i++) {--%>
        <%--%>--%>

        Field<%=1%>: <input type="text" size="20" name="Field<%=1%>" value="Value<%=1%>"><br>

        <%--<%--%>
        <%--}--%>
        <%--%>--%>
        <INPUT TYPE=submit name=submit value="Submit"></form>
    <%
    } else {
        while (flds.hasMoreElements()) {
            String field = (String) flds.nextElement();
            if (!field.equals("submit")) {
                String value = request.getParameter(field);

    %>

    <li><%=field%> = <%=value%>
    </li>

    <%
                }
            }
        }
    %>
</center>
</body>
</html>