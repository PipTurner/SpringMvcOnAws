<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Log in with your account</title>
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"  rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/resources/css/common.css'/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css"
              href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>
    <body>
        <div id="container">
            <form  method="POST" action="j_spring_security_check"  class="form-signin">
                <h2 class="form-heading">Log in</h2>

                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Email" id="username" autofocus="true" required>
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                    <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
                 </div>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"/>
        <script src=<c:url value='/resources/js/bootstrap.min.js'/>/>
    </body>
</html>
