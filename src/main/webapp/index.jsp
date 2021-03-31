<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
<nav>
<%--    <img src="HTMLIMAGES/heyjam.JPG" alt="logo">--%>
    <a href="">About Us</a>
    <a href="">Login</a>
    <a href="institutionProfileRegistration.jsp">Create Institution</a>
    <a href="">Contact Us</a>
</nav>
<div class="topnav">
    <div class="search-container">
        <select id="profileOption" class="name" style="margin-left:20px;width: 300px;" aria-label="Default select example">
            <option value="1" selected>Personal Profile</option>
            <option value="2">Institution Profile</option>
        </select>
        <input id="loginUsername" class="name" type="text" style="margin-left: 10px;margin-right: 5px" placeholder="Username" name="search" autocomplete="off" required>
        <input id="loginPassword" class="name" type="password" style="margin-left: 0" placeholder="Password" name="psw" autocomplete="off" required>
        <button id="login" type="button" class="btn btn-dark">Login</button>
        <p style="text-align: end; margin-right: 70px;font-size: 15px; color: white" id="loginWarning"></p>
    </div>
</div>
<table  cellpadding="10">
    <tr>
        <td>SIGN UP!!!</td>
    </tr>
    <tr>
        <td><input  class="name" id="signName" oninput="verifyName(this.value)" type="text" name="name" placeholder="Enter Name" autocomplete="off" required></td>
    </tr>
    <tr id="nameWarning">
    </tr>
    <tr>
        <td><input class="name" id="signUsername" oninput="verifyUsername(this.value)" type="text" name="Username" placeholder="Enter Username" autocomplete="off" required></td>
    </tr>
    <tr id="usernameWarning">
    </tr>
    <tr>
        <td><input class="name" id="signEmail" oninput="verifyEmail(this.value)" type="email" name="email" placeholder="Enter email id" autocomplete="off" required></td>
    </tr>
    <tr id="emailWarning">
    </tr>
    <tr>
        <td><input class="name" id="signMobile" oninput="verifyPhone(this.value)" type="number" name="mobnum" placeholder="Enter mobile number" autocomplete="off" required></td>
    </tr>
    <tr id="phoneWarning">
    </tr>
    <tr>
        <td><input class="name" id="signDate" oninput="verifyDate(this.value)" type="date" name="dob"  autocomplete="off" required></td>
    </tr>
    <tr id="dateWarning">
    </tr>
    <tr>
        <td>
            <input type="radio" name="gender" id="male" value="M" ><label for="male">Male</label>
            <input type="radio" name="gender" id="female" value="F" ><label for="female">Female</label>
            <input type="radio" name="gender" id="other" value="O"><label for="other">Other</label>
        </td>
    </tr>
    <tr>
        <td><input class="name" id="signPassword" oninput="verifyPassword(this.value)" type="password" name="password" placeholder="Enter password" autocomplete="off" required></td>
    </tr>
    <tr id="passwordWarning">
    </tr>
    <tr>
        <td><input class="name" id="signRePassword" oninput="verifyRePassword(this.value)" type="password" name="password" placeholder="Re Enter password" autocomplete="off" required></td>
    </tr>
    <tr id="rePasswordWarning">
    </tr>
    <tr>
        <td><button type="button" id="registerUser" class="btn btn-dark">Register</button></td>
    </tr>
    <tr id="warning">
    </tr>
</table>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="static/js/index.js"></script>
</html>