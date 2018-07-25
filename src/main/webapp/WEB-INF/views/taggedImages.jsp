<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Found on categories</title>
</head>
<body>
      <h2>Results</h2>
          <c:forEach var="item" items="${images}" >
                 <img src=${item.getAddress()} alt="image"/>
          </c:forEach>
</body>
</html>