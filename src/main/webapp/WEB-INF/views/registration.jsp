<%@page session="false"%>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
	<h2>Registration page</h2>
    <form action="#" th:action="@{/registration}" th:object="${user}" method="post">
       <label>Login</label><input type="text" th:field="*{login}"/><br>
       <label>Password</label><input type="password" th:field="*{password}"/><br>
       <label>Name</label><input type="text" th:field="*{name}"/><br>
       <input type="checkbox" th:field="*{admin}"/><label>Admin</label><br>
       <button type="submit" class="button">Sign up</button>
    </form>
</body>
</html>