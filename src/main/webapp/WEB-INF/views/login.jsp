<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html; charset = UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
   <h1>Login</h1>

   <c:if test="${not empty error}">
   			<div class="error">${error}</div>
   		</c:if>
   		<c:if test="${not empty msg}">
   			<div class="msg">${msg}</div>
   		</c:if>

   <form name='f' action="@{/login}" method='POST'>
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' name='login' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="Login" /></td>
         </tr>
      </table>
  </form>

  <form action="@{/registration}" method='GET'>
     <button type="submit" class="button">Sign up</button>
  </form>
</body>
</html>