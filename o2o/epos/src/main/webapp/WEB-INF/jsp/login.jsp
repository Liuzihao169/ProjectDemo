<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>o2o-登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" >
    <style>
        body{
            background: url("${pageContext.request.contextPath }/images/bg.jpg") repeat;
        }
        .container{
            margin-top: 100px;
            width: 400px;
        }
        .form-group .form-control{
            height: 40px;
        }
    </style>
</head>
<body>
<div class="container" >
    <form role="form" action="${pageContext.request.contextPath }/admin/login.action" method="post">
        <div class="form-group">
            <label class="sr-ohgnly">用户名</label>
            <input type="text" class="form-control" name="user_name" placeholder="用户名">
        </div>
        <div class="form-group">
            <label class="sr-gyonly">密码</label>
            <input type="password" class="form-control" name="user_password" placeholder="密码">
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>
</div>
</body>
</html>