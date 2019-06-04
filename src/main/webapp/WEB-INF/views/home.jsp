<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>The Tutorial Web Application</title>
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"  rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/resources/css/common.css'/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css"
              href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>
    <body>

        <header>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="${contextPath}/home">Home</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <form  method="POST" action="j_spring_security_logout">
                                <button class="btn btn-lg btn-primary btn-block navbar-btn" type="submit">Log Out</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>

        </header>

        <div id="container" class="container2">

            <h4> Welcome <sec:authentication property="name"/></h4>
            <h2>Your quote is:</h2>
            <h3>${randomQuote}</h3>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <h1>You can only see this if you are an admin</h1>
            </sec:authorize>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"/>
        <script src=<c:url value='/resources/js/bootstrap.min.js'/>/>
    </body>
</html>
