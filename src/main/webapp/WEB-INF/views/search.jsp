<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Find on categories</title>
</head>
<body>
    <form action="/search" modelAttribute="tag" method="post">
       <p><input type="search" placeholder="search a category">
       <input type="submit" value="Search"></p>
      </form>
</body>
</html>