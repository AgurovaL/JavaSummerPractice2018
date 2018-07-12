<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<html>
<head>
    <title>Your favorite images</title>
</head>
<body>
    <h2>Your favorite images</h2>
     <c:forEach var="item" items="${images}" >
               <img src=${item.getAddress()} alt="image"/>
        </c:forEach>
</body>
</html>