<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29-01-2021
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/css/requestInstitutionFollow.css">
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
    <h1><strong>Request Form</strong></h1><br>
</div>
<div style="margin-left: 390px" class="titlearea">
    <input type="text" class="form-control" id="title" placeholder="Institution Name" name=title  disabled>
</div>
<div style="margin-left: 390px" class="textarea">
    <textarea id="description" class="form-control" name="description" rows="11" cols="50" placeholder="Description" disabled></textarea>
</div>
<div class="datearea">
    <input placeholder="official institution Email.. if exists.." class="form-control" type="email" id="email"  name="email"autocomplete="off" required disabled>
    <input type="text" id="mobnumber" class="form-control" placeholder="Phone" name="Phone"autocomplete="off" required disabled>
    <input type="text" id="joinyear" class="form-control" placeholder="Year Joining in Institution" name="joinyear"autocomplete="off" required>
    <input type="text" id="depart" class="form-control" placeholder="Department" name="Department"autocomplete="off" required>
</div>
<div class="btn">
    <button id="sendRequest" style="background-color: #184770; border-color:wheat; color: white; width: 110px; height: 40px;" type="button">SEND</button>
</div>
</body>
<script src="static/js/requestForm.js"></script>
</html>
