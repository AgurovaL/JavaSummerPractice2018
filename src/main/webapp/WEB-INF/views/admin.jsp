<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
	<h1>Hello admin!</h1>

    <form:form action="@{/users/all}" method="get">
        <button type="submit">All users</button>
    </form:form>

    <form:form action="@{/logout}" method="post">
        <input type="submit" value="Sign Out"/>
    </form:form>
</body>
</html>