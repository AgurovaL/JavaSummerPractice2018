<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Find on categories</title>
</head>
<body>
    <h2>${message}</h2>
    <form action="#" th:object="${tag}" method="post">
       <p><input type="search" placeholder="search a category">
       <input type="submit" value="Search"></p>
      </form>

      <h2>Results</h2>
          <c:forEach var="item" items="${images}" >
                 <img src=${item.getAddress()} alt="image"/>
          </c:forEach>
</body>
</html>