<%@page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User page</title>
</head>
<body>
    <h1>Hello %User name%!</h1>

     <form:form action="@{|/user/${id}/images/favorite|}" method="get">
            <button type="submit" >Favorite images</button>
     </form:form>

      <form:form action="@{|/user/${id}/images/all|}" method="get">
            <button type="submit" >All images</button>
      </form:form>

      <form:form action="@{|/user/${id}/images/search|}" method="get">
           <button type="submit" >Search categories</button>
      </form:form>

       <form:form action="@{/logout}" method="post">
             <input type="submit" value="Sign Out"/>
       </form:form>
</body>
</html>