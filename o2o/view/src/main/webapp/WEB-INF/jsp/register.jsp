<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,
    initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no"/>
    <title>表单登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js" ></script>
    <style>
        body{
            background: #eaeaea;
        }
        .scroll{
            width: auto;
            padding: 0 80px;
        }
        .page-header{
            height: 40px;
            margin:0;
            padding:0;
            background: #e2e2e2;
        }
        .page-header span{
            display:inline-block;
            margin-top: 9px;
            padding-left: 16px;
        }
        form{
            /*background: white;*/
            height: auto;
            background: white;
            padding:4px 30px 20px 20px;
        }
        form .form-control{
            background: rgba(185, 185, 185, 0.12);
        }
        form button{
            width: 65px;
        }
    </style>
    <script type="text/javascript">
    	$(function(){
    		//页面加载完全 点击一次图片
    		/*无效果*/
    		$("#vCode").click();
    		
    		$("#username").blur(function(){
    			//发送ajax请求，校验用户名是否存在
    			var name=$("#username").val();
    			$.post("${pageContext.request.contextPath}/user/checkUserName.action",
    					{"user_name":name},function(data){
    						if(data.status==500){
    							$("#msg").text(data.msg);
    						}else{
    							$("#msg").text("")
    						}
    					},"json");
    		});
    	})
    </script>
</head>
<body>
<div class="scroll container">
    <div class="page-header">
        <span class="text-center">用户注册</span><span id="msg" style="margin-left: 10px;color: red"> ${msg}</span>
    </div>
      <form role="form" class="" action="${pageContext.request.contextPath}/user/register.action">
        <div class="form-group">
            <label class="control-label" style="padding-right:7px;">用户名</label>
            <input type="text" id="username" class="form-control" name="user_name" placeholder="输入用户名" style="width: 80%;display: inline-block;">
        </div>
        <div class="form-group">
            <label class="control-label" style="padding-right:7px;">验证码</label>
             <input type="text" class="form-control" name="identifyingCode" placeholder="请输入验证码" style="width: 60%;display: inline-block;">
             <img id="vCode" src="${pageContext.request.contextPath }/validatecode.jsp"
								onclick="javascript:document.getElementById('vCode').src='${pageContext.request.contextPath }/page/validatecode.action?'+Math.random();"/>
        </div>
        <div class="form-group">
            <label class="control-label"  style="padding-right:20px;">密码</label>
            <input type="text" class="form-control" name="user_password" placeholder="请输入密码" style="width: 80%;display: inline-block;">
        </div>

        <div class="form-group" style="height: 50px; margin-top: 10px;margin-bottom: 0;">
            <a class="btn btn-info " href="${pageContext.request.contextPath }/page/login.action" style="width: 100px;margin-right:20%;">立即登录</a>
            <button class="btn btn-success " style="display:inline-block;width: 100px; float:right">立即注册</button>
        </div>
    </form>
</div>
</body>
</html>