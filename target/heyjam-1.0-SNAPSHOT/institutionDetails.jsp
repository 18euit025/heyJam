<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="static/css/userInstitutionDetails.css">
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
<div class="topnav">
    <h1><strong>Participants</strong></h1><br>
</div>
<div class="top">
    <h2 id="detailsHead" style="align-content: center;"></h2>
</div>
<div class="viewinstitution">
    <table id="participants" cellpadding="10" >


    </table>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="static/js/institutionDetails.js"></script>
</html>