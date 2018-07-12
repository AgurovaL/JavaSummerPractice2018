<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<html>
<head>
    <title>Find on categories</title>
</head>
<body>
    <h2>${message}</h2>
    <form>
       <p><input type="search" name="q" placeholder="search a category">
       <input type="submit" value="Search"></p>
      </form>
      <h2>Results</h2>
          <c:forEach var="item" items="${images}" >
                 <img src=${item.getAddress()} alt="image"/>
          </c:forEach>
</body>
</html>