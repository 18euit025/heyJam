<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 28-01-2021
  Time: 08:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/css/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<nav>
    <a href="#aboutus">About Us</a>
    <a href="index.jsp">Login</a>
    <a href="#createinstitution">Create Institution</a>
    <a href="#contactus">Contact Us</a>
</nav>
<h2>Institution Registration </h2>
<form>
    <div class="instireg">
        <table  cellpadding="10">
            <tr>
                <td>SIGN UP!!!</td>
            </tr>
            <tr>
                <td><input id="institutionName" oninput="institutionProfileName(this.value)" type="text" class="name form-control" name="instiname" placeholder="Institution Name" autocomplete="off" required></td>
            </tr>
            <tr>
                <td><input id="institutionUniqueName" oninput="institutionProfileUniqueName(this.value)" type="text" class="name form-control" name="email" placeholder="Create Unique Name" autocomplete="off" required></td>
            </tr>
            <tr>
                <td id="uniqueNameWarning"></td>
            </tr>
            <tr>
                <td><input id="institutionEmail" oninput="institutionProfileEmail(this.value)" type="email" class="name form-control" name="email" placeholder="Institution email ID" autocomplete="off" required></td>
            </tr>
            <tr>
                <td><textarea id="institutionDescription" oninput="institutionProfileDescription(this.value)" class="name form-control" name="description" placeholder="Description..!"></textarea></td>
            </tr>
            <tr>
                <td><input id="institutionPhone" oninput="institutionProfilePhone(this.value)" type="number" class="name form-control" name="mobnum" placeholder="Enter mobile number" autocomplete="off" required></td>
            </tr>
            <tr>
                <td><input id="institutionPassword" oninput="institutionProfilePassword(this.value)" type="password" class="name form-control" name="password" placeholder="Enter password" autocomplete="off" required></td>
            </tr>
            <tr>
                <td><input id="institutionRePassword" oninput="institutionProfileRePassword(this.value)" type="password" class="name form-control" name="password" placeholder="Re Enter password" autocomplete="off" required></td>
            </tr>
            <tr>
                <td><button id="createInstitution" type="button" style="align-items: center" class="btn btn-dark">Create Institution!</button></td>
            </tr>
            <tr>
                <td id="warning"></td>
            </tr>
        </table>
    </div>
    <div id="loadingBar" class="terms"></div>
    <div id="activationKey" class="terms" style="visibility: hidden;">
        <br>
        Your activation key will be sent to your registered Email id<br><br>
        <input type="text" id="actikey" name="aactikey" placeholder="Enter your Activation key" autocomplete="off" required><br><br>
        <button id="verifyActivation" type="button" class="btn btn-dark">Verify</button>
    </div>
    <p id="activationWarning" style="color: black;"></p>
</form>
</body>
<script src="static/js/institutionProfileRegistration.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</html>
