<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Find on categories</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/search" modelAttribute="tag" method="post">
       <p><form:input type="search" placeholder="search a category" name="tag">
       <input type="submit" value="Search"></p>
    </form:form>
</body>
</html>