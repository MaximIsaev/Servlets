<%@ page contentType="text/html; charset=UTF-8" language="java" %>

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
    Folder name:
    <input type="url">
</center>
</body>
</html>