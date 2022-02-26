<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/2/25
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/LoginServlet">
   用户名 <input type="text" name="loginname"></br>
密码<input type="text" name="password"></br>
<input type="submit" value="登陆">
</form>
</body>
</html>
