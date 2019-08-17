<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width,
    initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
<title>选菜</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	   $(function () {
           $(".button1").click(function () {
        	     var _thisInput=$(this).siblings("input[name='num']");
               var e=_thisInput.val();
               if(eval(_thisInput.val()) == 1){
   				return ;
   			}
               var _thisInputId=$(this).siblings("input[name='greenid']");
               var _thisInputPrice=$(this).siblings("input[name='price']");
               _thisInput.val(eval(_thisInput.val())-1)
               	$.post("${pageContext.request.contextPath}/cart/update/num/"+_thisInputId.val()+"/"+_thisInput.val()+".action",function(data){
               		//更新总价格
               		var oldTotal = $("#total").text();
               		console.log(oldTotal);
               		console.log(_thisInputPrice.val());
               		var newTotal = (eval(oldTotal)-eval(_thisInputPrice.val()))*100/100;
               		console.log(newTotal);
               		$("#total").text(newTotal+".0");
               	});
           })

           $(".button2").click(function () {
               var _thisInput=$(this).siblings("input[name='num']");
               var _thisInputId=$(this).siblings("input[name='greenid']");
               var _thisInputPrice=$(this).siblings("input[name='price']");
               _thisInput.val(eval(_thisInput.val())+1)
               	$.post("${pageContext.request.contextPath}/cart/update/num/"+_thisInputId.val()+"/"+_thisInput.val()+".action",function(data){
               		//更新总价格
               		var oldTotal = $("#total").text();
               		var newTotal = eval(oldTotal)+eval(_thisInputPrice.val());
               		$("#total").text(newTotal+".0");
               	});
              /*$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				CART.refreshTotalPrice();
			});*/
           })
			//跳转到主页
			$(".btn.btn-success.btn-xs").click(function(){
				location.href="${pageContext.request.contextPath}";
			})
			
			//跳转到填写收货详细信息的页面
			$(".btn.btn-success.btn-sm.pull-right").click(function(){
				location.reload();
				//触发提价表单
				$("#Order").submit();
			});
           // 清空购物车
           $(".btn.btn-default.btn-xs").click(function(){
        	   //弹出确认会话框
        	   if(confirm("确认要清空购物车吗")){
        		   $.post("${pageContext.request.contextPath}/cart/clean.action",function(data){
        			   if(data.status==200){
        				   alert("成功清空购物车");
        				   location.reload();
        			   }
        		   },"json")
        	   }
           });
       })
	</script>
<style>
body {
	background: #eaeaea;
}

.scroll {
	/*padding:0 50px;*/
	
}

.row {
	background: white;
	border: 0.1px solid rgba(100, 100, 100, 0.16);
}

.row p {
	height: 40px;
	margin: 0;
	line-height: 40px;
}

.head span {
	font-size: 21px;
	color: red;
}

.col-xs-4 p {
	font-size: 20px;
	font-weight: bold;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.col-xs-3 button {
	width: 75px;
	margin-top: 10px;
}

.div-2 i {
	display: inline-block;
	width: 22px;
	height: 13px;
	text-align: center;
}

.div-2 button {
	width: 16px;
	height: 16px;
	border-radius: 50%;
	line-height: 1.5px;
	padding-left: 2.2px;
	background: #f1f1f1;
}

.div-3 {
	height: 40px;
	border: none;
	background: #eaeaea;
}

.div-3 button {
	margin-top: 8px;
	width: 65px;
}
</style>
</head>
<body>
	<div class="scroll container">
		<div class="head text-right"></div>
		<div class="row">
			<form
				action="${pageContext.request.contextPath}/order/order-cart.action"
				id="Order" method="post">
				<c:forEach items="${cartList }" var="greensItem" varStatus="status">
					<c:set var="totalPrice"
						value="${ totalPrice + (greensItem.greens_price * greensItem.greens_count)}" />
					<input type="hidden"
						name="orderItems[${status.index}].orderitem_count"
						value="${greensItem.greens_count}">
					<input type="hidden"
						name="orderItems[${status.index}].orderitem_total"
						value="${greensItem.greens_price * greensItem.greens_count}">
					<input type="hidden"
						name="orderItems[${status.index}].greens.greens_id"
						value="${greensItem.greens_id}">
				</c:forEach>
				<input type="hidden" name="order_total" value="${totalPrice}">
			</form>
			<div class="col-xs-4">
				<p>菜篮子</p>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-3">
				<button class="btn btn-success btn-xs">选菜</button>
			</div>
			<c:if test="${!empty cartList }">
				<div class="col-xs-3">
					<button class="btn btn-default btn-xs">清空</button>
				</div>
			</c:if>
		</div>
		<c:if test="${!empty cartList }">
			<c:forEach items="${cartList}" var="greensItem">
				<c:set var="totalPriceIn"
					value="${ totalPriceIn + (greensItem.greens_price * greensItem.greens_count)}" />
				<div class="row">
					<div class="col-xs-3">
						<p>
							<strong>${ greensItem.greens_name}</strong>
						</p>
					</div>
					<div class="col-xs-6">
						<p style="color: red;">${greensItem.greens_price}元一例</p>
					</div>
					<div class="col-xs-3 div-2">
						<button type="button" style="margin-top: 3px;"
							class="button1 btn btn-default ">--</button>
						<input type="hidden" value="${greensItem.greens_id}"
							name="greenid"> <input type="hidden"
							value="${greensItem.greens_price}" name="price"> <input
							type="button"
							style="border: none; background: none; padding: 9px;"
							class="button0 btn" name="num"
							value="${ greensItem.greens_count}">
						<button type="button" style="margin-top: 3px;"
							class="button2 btn btn-default">+</button>
					</div>
				</div>
			</c:forEach>
			<div class="row div-3">
				<div class="col-xs-9"></div>
				<div class="col-xs-2" style="margin-top: 15px">
					<span style="padding-left: 80px">共计<strong id="total">${totalPriceIn}</strong>￥
					</span>
				</div>
				<div class="col-xs-1">
					<button class="btn btn-success btn-sm pull-right">下单</button>
				</div>
			</div>
		</c:if>
		<c:if test="${empty cartList}">
			<img alt="" src="/imagesUrl/cart-empty.png">
		</c:if>
	</div>
</body>
</html>