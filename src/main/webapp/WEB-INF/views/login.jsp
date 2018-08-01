<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Login page</title>
</head>
<body>
   <h1>Login</h1>

   <c:if test="${not empty error}">
   			<div class="error">${error}</div>
   		</c:if>
   		<c:if test="${not empty msg}">
   			<div class="msg">${msg}</div>
   		</c:if>

 <form:form action="/login" modelAttribute="user" method="post">
       <form:label path="login">Login</form:label>
       <form:input type="text" path="login"/><br>

       <form:label path="password">Password</form:label>
       <form:input type="password" path="password"/><br>

       <input type="submit" value="Sign in"/><br>
    </form:form>

    <form:form action="/registration" method="post">
        <input type="submit" value="Sign up" /><br>
    </form:form>

    <p><a href="/registration">Link to reg</a></p>
</body>
</html>