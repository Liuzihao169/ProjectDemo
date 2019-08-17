<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <style>
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
    	<h3 style="text-align:center;"><strong>用户登录</strong></h3>
    	<p style="color:red">${msg}</p>
        <form role="form" action="${pageContext.request.contextPath}/user/login.action" method="post">
            <div class="form-group">
                <label class="sr-ohgnly">用户名</label>
                 <span class="pull-right"><a href="#" style="text-decoration: none;">立即注册</a></span>
                <input type="text" name="user_name" class="form-control" placeholder="用户名">
            </div>
            <div class="form-group">
                <label class="sr-gyonly">密码</label>
                <input type="password" name="user_password" class="form-control" placeholder="密码">
            </div>
            <!-- <div class="form-group">
                <label class="sr-ohgnly">验证码</label>
                <input type="text" class="form-control" placeholder="验证码">
            </div> -->
            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        </form>
    </div>
</body>
</html>