<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30-01-2021
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Institution Notifications</title>
    <link rel="stylesheet" href="static/css/notifications.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    String username = (String) session.getAttribute("username");
    if(session.getAttribute("username")==null){
        response.sendRedirect("index.jsp");
    }
%>
<nav>
    <h4 style="color: white; font-weight: bold">Welcome, <%=username%> </h4>
    <a  style="font-size: 12px" href="institutionProfileHome.jsp"><b>Events</b></a>
    <a  style="font-size: 12px" href="institutionProfileHome.jsp"><b>Survey</b></a>
    <a href="institutionNotification.jsp">Notifications</a>
    <a style="font-size: 12px" href="institutionDetails.jsp"><b>Participants</b></a>
    <form method="get" action="UserInstitutionProfileLogout">
        <input type="submit" value="logout">
    </form>
</nav>
<div class="topnav">
    <h1 style="align-content: center;"><strong>NOTIFICATIONS</strong></h1><br>
</div>
<div class="top">
    <h2 style="margin-left: 400px;">follow requests</h2>
</div>
<div class="viewinstitution">
    <table  id="notifications" cellpadding="10" >
        <tr>
            <th>Name</th>
            <th>Year joined</th>
            <th>Department</th>
            <th>Email ID</th>
            <th></th>
        </tr>
    </table>
</div>
<div id="snackbar"></div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="static/js/institutionProfileNotifications.js"></script>
</html>
