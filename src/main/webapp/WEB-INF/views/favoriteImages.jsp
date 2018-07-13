<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your favorite images</title>
</head>
<body>
    <h2>Your favorite images</h2>
     <c:forEach var="counter" begin="1" end="${images.size()}">
         <img src=${images.iterator().next().getAddress()} alt="image"/>
     </c:forEach>
</body>
</html>