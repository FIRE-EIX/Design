<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/20
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>成绩管理系统登录</title>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
</head>
<body>
<div align="center">
    <h1 style="color: #2b65b6">登&nbsp&nbsp&nbsp&nbsp录</h1>

<%--    表单提交框--%>
    <form action="userLogin" method="post">
        <input name="id" placeholder="请输入id" class="id" autocomplete="off" required />
        <input name="password" placeholder="请输入密码" class="password" type="password" required />
        <input name="submit" class="btn" type="submit" style="margin-top: 50px;"/>
    </form>
</div>
</body>
</html>
