<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 07-02-2021
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <h4 style="color: white; font-weight: bold">Hey! <%=username%></h4>
    <a style="font-size: 12px" href="userProfileEdit.jsp">Edit Profile</a>
    <a  style="font-size: 12px" href="userProfileHome.jsp">your <b>Institutions</b></a>
    <a style="font-size: 12px" href="userFollowing.jsp">following</a>
    <a style="font-size: 12px" href="userProfileFollowers.jsp">followers</a>
    <a style="font-size: 12px" href="userProfileNotifications.jsp">Notifications</a>
    <form method="get" action="UserInstitutionProfileLogout">
        <input type="submit" value="logout">
    </form>
</nav>
<div class="topnav">
    <h1><strong>Following</strong></h1><br>
</div>
<div class="top">
    <h2 id="detailsHead" style="align-content: center;">People you Follow</h2>
</div>
<div class="viewinstitution">
    <table id="participants" cellpadding="10" >


    </table>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="static/js/userProfileFollowing.js"></script>
</html>
