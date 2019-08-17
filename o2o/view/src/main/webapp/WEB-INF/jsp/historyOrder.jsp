<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>  
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>历史订单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <style>
        body{
            background: #eaeaea;
        }
        .container{
            width: auto;
        }
        [class*="col-"]{
            /*border:0.3px solid rgba(189, 189, 189, 0.5);*/
            height: 40px;
            background: white;
            border-bottom:0.5px solid rgba(189, 189, 189, 0.5);
        }
        .head{
            background: #eaeaea;
        }
        .MyPay p{
            font-size: 18px;
            line-height: 40px;
            margin:0;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }
        .type p{
            font-size: 13px;
            text-align: center;
            line-height: 40px;
            margin:0;
        }
        .first{
            margin-bottom:50px;
        }
    </style>
</head>
<body>
<div class="scroll container">
    <h4 style="margin-left: 62.4px;"><strong>历史订单</strong></h4>
   <!-- <div class="head row ">
        <div class="col-xs-8 col-xs-offset-1" style="background: #eaeaea;"></div>
        <div class="col-xs-2 pull-right" style="background: #eaeaea;border-style:none;"><span>共计</span><span style="font-size:20px;color:red;">46元</span></div>
    </div>-->
 	<c:forEach items="${orderList}" var="order">
 		 <div class="first">
	        <div class="MyPay row">
	            <div class="col-xs-3 col-xs-offset-1">
	                <p>订单编号:${order.order_code}</p>
	            </div>
	            <div class="col-xs-7" >
	                <span style="padding-top:7px;display:inline-block;font-size:20px;"></span>
	            </div>
	        </div>
	        <c:forEach items="${order.orderItems}" var="OrderItem">
	        	<div class="type row">
		            <div class="col-xs-2 col-xs-offset-1">
		                <p>${OrderItem.greens.greens_name }</p>
		            </div>
		            <div class="col-xs-1"></div>
		            <div class="col-xs-2">
		                <p style="color: red;">${OrderItem.greens.greens_price}元一份</p>
		            </div>
		            <div class="col-xs-4"></div>
		            <div class="col-xs-1">
		                <p>${OrderItem.orderitem_count}</p>
	            	</div>
		        </div>
	        </c:forEach>
	        <div class="type row">
	            <div class="col-xs-3 col-xs-offset-1">
	            <fmt:formatDate value="${order.order_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
	            </div>
	            <div class="col-xs-2">
	
	            </div>
	            <div class="col-xs-3"></div>
	            <div class="col-xs-2">
	            	${order.order_total}￥<c:if test="${order.order_status==1}">
	            		<button type="button" class="btn btn-warning btn-sm">支付</button>
	            	</c:if>
	            	<c:if test="${order.order_status==0}">
	            		<button type="button" class="btn btn-success btn-sm" style="margin-top:6px;">已支付</button>
	            	</c:if>
	            </div>
	        </div>
	    </div>
 	</c:forEach>
   

</div>
</body>
</html>