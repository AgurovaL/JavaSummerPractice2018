<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
 <h2>All users</h2>
<table border="1px black">
    <thead>
    <th width="50px"><h2>ID</h2></th>
    <th width="150px"><h2>Name</h2></th>
    <th width="150px"><h2>Login</h2></th>
    <th width="150px"><h2>Password</h2></th>
    </thead>
    <tbody>
    <c:forEach var="item" items="${users}">
             <tr>
                 <td>${item.getId()}</td>
                 <td>${item.getName()}</td>
                    <td>${item.getLogin()}</td>
                 <td>${item.getPassword()}</td>
             </tr>
         </c:forEach>
    </tbody>
</table>
</body>
</html>