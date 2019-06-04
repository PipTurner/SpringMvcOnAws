<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1"/>
    <style>
        .error{
            color:red;
            font-size: 15px;
        }
    </style>
    <title>Create an Account</title>
</head>
<body>
<h1>Enter Account Details</h1>
<form:form commandName="aNewAccount" method="post" action="doCreate">
    <table>
        <tr><td>
            First Name: <form:input path="firstName" type="text" name="firstName"/>
            <form:errors path="firstName" cssClass="error"/>
        </td></tr>

        <tr><td>
            Last Name: <form:input path="lastName" type="text" name="lastName"/>
            <form:errors path="lastName" cssClass="error"/>
        </td></tr>

        <tr><td>
            Age: <form:input path="age" type="text" name="age"/>
            <form:errors path="age" cssClass="error"/>
        </td></tr>

        <tr><td>
            Address: <form:input path="address" type="text" name="address"/>
            <form:errors path="address" cssClass="error"/>
        </td></tr>

        <tr><td>
            Email: <form:input path="email" type="text" name="email"/>
            <form:errors path="email" cssClass="error"/>
        </td></tr>

        <tr><td><input type="submit" value="Create   "/></td></tr>
    </table>
</form:form>

</body>
</html>
