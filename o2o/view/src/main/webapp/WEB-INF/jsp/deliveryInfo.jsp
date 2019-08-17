<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
</head>
	<script type="text/javascript">
		$(function(){
			$(".btn.btn-info.pull-right").click(function(){
				$("#info").submit();
				/* location.href="${pageContext.request.contextPath}/" */
			});
			
			$(".btn.btn-default.pull-right").click(function(){
				//跳转到购物车页面
				location.href="${pageContext.request.contextPath}/cart/cart.action
			})
			
		})
	</script>
<body>
   <div class="scroll container">
       <div class="page-header">
           <span >配送信息</span>
       </div>
       <form role="form" id="info" action="${pageContext.request.contextPath}/order/orderinfo.action" method="post">
           <div class="form-group">
               <label class="control-label">手机号码</label>
               <input type="text" class="form-control" placeholder="Enter telephoneNumber" name="order_phone">
           </div>
           <div class="form-group">
               <label class="control-label">姓名</label>
               <input type="text" class="form-control" placeholder="Enter name" name="order_realname">
           </div>
           <div class="form-group">
               <label class="control-label">地址</label>
               <input type="text" class="form-control" placeholder="Enter address" name="order_address">
           </div>
           <div class="form-group" style="height: 50px; margin-top: 10px;margin-bottom: 0;">
               <button class="btn btn-info pull-right" >确认</button>
               <button class="btn btn-default pull-right" style="margin-right: 5px;">取消</button>
           </div>
       </form>
   </div>
</body>
</html>