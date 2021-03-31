<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 31-01-2021
  Time: 00:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Event</title>
    <link rel="stylesheet" href="static/css/institutionCreateEvent.css">
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
    <a  style="font-size: 12px" href="institutionSurvey.jsp"><b>Survey</b></a>
    <a href="institutionNotification.jsp">Notifications</a>
    <a style="font-size: 12px" href="institutionDetails.jsp"><b>Participants</b></a>
    <form method="get" action="UserInstitutionProfileLogout">
        <input type="submit" value="logout">
    </form>
</nav>
<div style="margin-left: 395px" class="create">
    <a style="color: #184770;">Create your Event</a>
</div>
<div class="titlearea">
    <input type="text" id="title" placeholder="TITLE" name=title >
</div>
<div class="textarea">
    <textarea id="createvent" name="createevent" rows="11" cols="50" placeholder="Event Content...!"></textarea>
</div>
<div class="datearea">
    <input type="text" id="To" placeholder="Creation Data DD-MM-YYYY" name="To">
    <input placeholder="Expirydate DD-MM-YYY" type="text" id="date"  name="date">
    <input type="text" id="from" placeholder="Send To Batch-From: all or YYYY" name="from">
    <input type="text" id="linktitle" placeholder="link Title" name="linktitle">
    <input type="url" id="link" placeholder="Link" name="link">
</div>
<div class="btn">
    <button id="addEvent" style="background-color: #184770; border-color:wheat; color: white; width: 150px; height: 50px;" type="button">Create Event</button>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="static/js/institutionProfileCreateEvent.js"></script>
</html>
