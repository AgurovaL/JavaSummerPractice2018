<%@page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
	<h2>Registration page</h2>
    <form:form action="/registration" modelAttribute="user" method="post">
       <form:label path="login">Login</form:label>
       <form:input type="text" path="login"/><br>

       <form:label path="password">Password</form:label>
       <form:input type="password" path="password"/><br>

       <form:label path="name">Name</form:label>
       <form:input type="text" path="name"/><br>

        <input type="submit" value="Sign up"/><br>
    </form:form>
</body>
</html>