<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Logout</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'/>"  rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/resources/css/common.css'/>" rel="stylesheet" type="text/css"/>
</head>
<body>

<div id="container">
    <h1></h1>
    <h4 class="text-center"> You have now been logged out</h4>
    <form  method="GET" action="<c:url value="/login"/>"  class="form-signin">
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        </div>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"/>
<script src=<c:url value='/resources/js/bootstrap.min.js'/>/>
</body>
</html>
