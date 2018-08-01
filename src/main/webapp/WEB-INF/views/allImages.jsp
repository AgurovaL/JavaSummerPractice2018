<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>All images</title>
</head>
<body>
    <h2>All images</h2>
    <c:forEach var="item" items="${images}" >
           <img src=${item.getAddress()} alt="image"/>
            <form:form action="/images/all" method="post">
                   <input type="submit" value="Add to favorite" />
            </form:form>
    </c:forEach>
</body>
</html>