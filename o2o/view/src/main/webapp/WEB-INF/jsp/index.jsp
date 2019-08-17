<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,
    initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no"/>
    <title>主界面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js" ></script>
    <link rel="stylesheet" href="1肉品.css">
    <style>
        *{margin:0;padding:0;}
        html{
            height: 100%;
        }
        body{
            height: 100%;
        }
        .scroll{
            height: 100%;
        }
        .header{
            height: 10%;
            background: rgba(204, 204, 204, 0.67);
        }
        /*左侧的导航栏部分*/
        .left-box{
            height: 90%;
            padding:0;
            background-color: #e2e2e2;
        }
        .left-box li{
            padding-right:15px;
        }
        .left-box li.show{
           background:#cccccc;
        }
      ul{
          list-style: none;
          width: 100%;
          height: 100%;
      }
        ul li{
            width: 100%;
            height: 32px;
            text-align: right;
            line-height: 32px;
            /*padding-right: 1px;*/
            font-size: 13px;
            cursor: pointer;
        }
        ul li a{
            color: #000;
            /*text-decoration: none;*/
        }
        .right-box{
            height: 90%;
            padding-top: 4px;
        }
    /*    .left-tr1{
            width: 71.466px;
            height: 47.938px;
            background: url("imgs/1.jpg") no-repeat left top;
        }*/
        .row dd{
            font-size:12px;
            color:red;
            margin-top: 1px;
        }
        .row p{
            font-size:13px;
            color: #cbcbcb;
            margin-top: 1px;
        }
        img{
            width: 71.766px;
            height: 47.938px;
        }
        .right-box .btn{
            width: 16px;
            height: 16px;
            border-radius:50%;
            line-height: 1.5px;
            padding-left: 2.2px;
            background: #f1f1f1;
        }
        .header .btn{
            margin-top:30px;
            margin-right:4px;
        }
        .col-xs-2{
            margin-top:17px;
        }
        .right-btn{
            position: absolute;
            right: 20px;
            bottom: 30px;
            border-style: none;
            padding: 1px 3px;
        }

      /*右侧物品展示部分*/
    </style>
    <script type="text/javascript">
    	$(function(){
    		//页面加载完全之后，发送ajax请求加载左边的菜品分类目录
    		$.post("${pageContext.request.contextPath}/greens/categorylist.action",
    				function(data){
    				for(var i = 0;i<data.length;i++){
    					//循环追加
    					$("#UL").append("<li  value='"+data[i].dict_id+"'><a href='${pageContext.request.contextPath}/greens/list.action?typeCode="+data[i].dict_id+"'>"+data[i].dict_type_value+"</a></li>");
    				}
    				 // 回显选中的行
    				 $("#UL li[value='${typeCode}']").attr("class","show");
    		},"json");
	
    		//点击之后触发事件，添加此商品到购物车
    		$(".btn-default").click(function(){
    			var idInput=$(this).siblings("input")
    			 var idInput=$(this).siblings("input[name='green_id']").val(); 
    			 var numInput=$(this).siblings("input[name='num']").val(); 
    			//发送ajax请求跳转到后台
    			var url="${pageContext.request.contextPath}/cart/"+idInput+".action"
    			$.post(url,{"num":numInput},
    					function(data){
    						if(data.status==200){
    							alert(data.msg);
    						}
    			},"json")
    		})
    		
    		//跳转到菜篮子
    		$(".btn-success").click(function(){
    			location.href="${pageContext.request.contextPath}/cart/cart.action";
    		})
    		// 确认退出对话框
    		$(".btn.btn-primary.btn-xs.pull-right").click(function(){
    			if(confirm("你确认要退出登入吗")){
    				location.href="${pageContext.request.contextPath}/user/outLogin.action";
    			}
    		})
    	})
    </script>
   
</head>
<body>
  <div class="scroll">
      <div class="header row">
          <div class="col-sm-4">

          </div>
          <div class="col-sm-4">
              <h3 style="margin-top:10px;">O2O-epos管理系统</h3>
          </div>
          <div class="col-sm-2 text-left">
          </div>
          	<c:if test="${ empty user }">
	          <div class="col-sm-2" id="test">
	              <a href="${pageContext.request.contextPath}/page/register.action" type="button" class="btn btn-primary btn-xs pull-right">注册</a>
	              <a href="${pageContext.request.contextPath}/page/login.action" type="button" class="btn btn-info btn-xs pull-right">登录</a>
	          </div>
          	</c:if>
          	<c:if test="${! empty user }">
	          	<div class="col-sm-2" >
					<h5 style="margin-top:25px;">欢迎：<span>${user.user_name}</span></h5>
	             	<a href="#" type="button" class="btn btn-primary btn-xs pull-right" >退出登录</a>
	          </div>
          	</c:if>
       <!--     -->
      </div>
      <!--左侧导航栏部分-->
       <div class="left-box col-sm-2" >
           <ul id="UL">
             <!--   <li class="show" value=""><a href="#">肉品</a></li>
               <li class=""><a href="#">水产</a></li>
               <li class=""><a href="#">蔬菜</a></li>
               <li class=""><a href="#">干货杂粮</a></li>
               <li class=""><a href="#">糕点</a></li> -->
           </ul>
       </div>
     <!--右侧物品展示部分-->
        <div class="right-box col-sm-10 container" >
            <div class="row img-responsive">
          	  <c:forEach items="${list}" var="greens">
          	  	 <div class="col-sm-4">
                    <div class="col-sm-4"><img src="/imagesUrl/${greens.greens_picture}" alt=""></div>
                    <div class="col-sm-6 text-right">
                            <dt>${greens.greens_name }</dt>
                           <dd>${greens.greens_price }</dd>
                          <p class="help-block"><span>${greens.greens_count}人买过</p>
                    </div>
                    <input type="hidden" value="${greens.greens_id}" name="green_id">
                    <!-- 默认的购买数量是1 方便以后扩展 -->
                 	 <input type="hidden" value="1" name="num"> 
                    <button type="button" class="btn btn-default">+</button>
                </div>
          	  </c:forEach>
            </div>
        </div>
        <div class="right-btn">
            <button class="btn-success" href="">菜篮子</button>
        </div>
  </div>
  <script>
      window.onload = function () {
          var ul = document.getElementById("UL");
          var lis = ul.children;//获取ul中的所有的孩子li
           var as = ul.getElementsByTagName("a");
          for(var i=0;i<lis.length;i++){
             as[i].index=i;
              as[i].onclick = function () {
                  //清除所有li的类
                  for(var k=0;k<lis.length;k++){
                      lis[k].className="";
                  }
                  //执行并留下当前li的类名current
                 lis[this.index].className="show";
              }
          }
      }
  </script>
</body>
</html>