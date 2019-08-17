<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>o2o后台管理系统</title>
    <script src="${pageContext.request.contextPath }/js/jQ.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" >
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<style>
    html{
        height: 100%;
    }
    body{
        height: 100%;
        background: #eaeaea;
        background: url("${pageContext.request.contextPath }/images/1.jpg") no-repeat;
    }
    /*test*/
    [class*="col-"]{
        height: 100%;
        /*border:1px solid rgba(204, 204, 204, 0.49);*/
    }
    .scroll{
        height: 100%;
    }
    img{
        margin:0;
        padding:0;
        border:none;
        width: 100%;
        display:block;
    }
    .first{
        position: relative;
        height: 15.5%;
        background: url(${pageContext.request.contextPath }/images/2.jpg) no-repeat;
    }
    .first h1{
        margin:0;
        padding:0;
        line-height: 65px;
    }
    .second{
        height: 84.5%;
        width:100%;
    }
    /*左边的nav*/
    .second .left-box{
        width:16%;
        padding:0;
        background: url(${pageContext.request.contextPath }/images/3.jpg) no-repeat;
        overflow: hidden;
    }
    .second .left-box dd{
        cursor: pointer;
        font-size:15px;
        padding-left: 15px;
        padding-right:15px;
    }
    .second .left-box dd.current{
        background: rgba(145, 177, 252, 0.84);
    }
    .second .left-box dd span{
        padding-left:20px;
    }
    .second .left-box dd a{
        color: rgba(0, 0, 0, 0.82);
        text-decoration: none;
    }
    /*右边的界面*/
    .second .right-box{
        padding:0;
        height: 100%;
    }
    .second .right-box div{
        height: auto;
    }
    .second .right-box  li th{
        text-align:center;
    }
     .modal-content{
         background: url(${pageContext.request.contextPath }/images/02.jpg) no-repeat;
    }
     /*订单列表中的样式*/
    .pay .spans span{
        display:inline-block;
        width: 76.75px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        border:0.1px solid rgba(138, 43, 226, 0.32);
    }
    #outdoor{
        display:none;
        position: relative;
        height: 60px;
        width: 614px;
        border:0.1px solid rgba(138, 43, 226, 0.32);
        margin-left: 2.6%;
    }
     .indoor{
         position: absolute;
         border:0.1px solid rgba(138, 43, 226, 0.32);
         height: 40px;
         width: 580px;
         margin:10px auto;
   }
    .indoor span{
        display:inline-block;
        width: 193px;
        height:40px ;
        border-right:0.1px solid rgba(138, 43, 226, 0.32);
        text-align:center;
        line-height: 40px;
    }
    #out{
        display:none;
        position: relative;
        height: 60px;
        width: 614px;
        border:0.1px solid rgba(204, 204, 204, 0.76);
        margin-left: 2.6%;
    }
    /*用户列表 和 字典列表部分*/
     .table thead tr th{
        text-align:center;
    }
    .table tbody tr:hover{
        background: rgba(145, 177, 252, 0.84);
    }
</style>
<body>

<div class="scroll container-fluid">
    <div class="row first">
        <div class="header col-sm-12">
            <h1 class="text-center">O2O后台管理系统</h1>
            <p>
                <button type="button" class="btn btn-xs btn-info pull-right"><a href="${pageContext.request.contextPath }/admin/exit.action" target="_blank" style="text-decoration: none;color:white;">退出登录</a></button>
        </div>
        <!--修改密码的模态框-->
        <div class="modal fade" id="Modal1" tabindex="-1" role="dialog" aria-labelledby="Modal1Label" >
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="Modal1Label">菜品信息修改</h4>
                    </div>
                    <div class="modal-body ">
                        <div class="container" style="height: auto;width: 90%;">
                            <form class="form-horizontal" role="form">

                                <div class="form-group">
                                    <label class="col-xs-2 control-label text-right" style="padding-top:7px;">旧密码</label>
                                    <div class="col-xs-10" >
                                        <input type="password" class="form-control" name="user_password" placeholder="旧密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 control-label text-right " style="padding-top:7px;">新密码</label>
                                    <div class="col-sm-10" >
                                        <input type="password" class="form-control" placeholder="新密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 control-label text-right" style="padding-left: 2px;">确定密码</label>
                                    <div class="col-xs-10" >
                                        <input type="password" class="form-control" placeholder="确定密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12" >
                                        <button type="button" class="btn btn-sm btn-success center-block">确认修改</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--第二部分-->
    <div class="row second">
        <!--左边的nav-->
        <div class="left-box col-sm-2" id="leftBox">
            <dl style="margin-top:12px;">
                <dd class="current"><span style="padding-left: 6px;">+</span><strong>菜品管理</strong></dd>
                <dd class="" id="greensList" style="display:none;"><span>-</span><a href="${pageContext.request.contextPath }/greens/list.action">菜品列表</a></dd>
                <dd class="" id="addGreens" style="display:none;"><span>-</span><a href="#">添加菜品</a></dd>
            </dl>
            <dl>
                <dd class=""><span  style=";padding-left: 6px;">+</span><strong>订单管理</strong></dd>
                <dd id="orderList" class=""  style="display:none;"><span>-</span><a href="${pageContext.request.contextPath }/order/list.action">订单列表</a></dd>
            </dl>
            <dl>
                <dd class=""><span  style=";padding-left: 6px;">+</span><strong>用户管理</strong></dd>
                <dd class="" id="userList"  style="display:none;"><span>-</span><a href="${pageContext.request.contextPath }/admin/list.action">用户列表</a></dd>
            </dl>
            <dl>
                <dd class=""><span  style=";padding-left: 6px;">+</span><strong>字典管理</strong></dd>
                <dd class="" id="dictList" style="display:none;"><span>-</span><a href="${pageContext.request.contextPath }/dict/list.action">字典列表</a></dd>
                <dd class="" id="addDict" style="display:none;"><span>-</span><a href="#">字典扩充</a></dd>
            </dl>
        </div>
        <!--右边的界面-->
        <div class="right-box col-sm-10" id="rightBox">

            <div id="default" class="show"  style="font-weight: bold;font-size:30px;text-align: center;line-height:450px;">
                欢迎来到O2O-管理系统
            </div>
<!--菜品列表对应的界面            -->
            <div id="showGreens" class="hidden">
                      <ul style="list-style: none;margin-top:30px;width: 100%;height: auto;" class="container">
                      	<form action="${pageContext.request.contextPath }/greens/list.action" method="post">
                          <li style="display:inline-block;font-size:18px;margin-left:210px;"><strong>搜索</strong></li>
                          <li style="display:inline-block;">
                              <select name="dict_type_code" class="form-control" >
                                  <option>菜品类型</option>
                                  	  <c:forEach items="${greensLevels }" var="level">
                                  	  	<c:if test="${level.dict_type_code==dict_type_code }">
                                  	  		<option value="${level.dict_type_code }" selected="selected">${level.dict_type_value }</option>
                                  	  	</c:if>
                                  	  	<option value="${level.dict_type_code }">${level.dict_type_value }</option>
                                  	  </c:forEach>
                              </select>
                          </li>
                          <li style="display:inline-block;"><button type="submit" class="btn btn-info">GO</button></li>
                         </form>
                          <li style="width: 85%;height: auto;margin-top:40px;" class="table-responsive container">
                                  <table class="table table-bordered">
                                      <thead>
                                      <tr style="font-size:14px;">
                                          <th>id</th>
                                          <th>菜品类型</th>
                                          <th>菜名</th>
                                          <th>单价</th>
                                          <th>下单次数</th>
                                          <th>图片</th>
                                          <th>库存</th>
                                          <th>上下架</th>
                                          <th>操作</th>
                                      </tr>
                                      </thead>
                                      <tbody>
	                                      <c:forEach items="${greensList }" var="greens">
	                                      	<tr style="font-size:14px;">
	                                          <td>${greens.greens_id }</td>
	                                          <td>${greens.greens_type }</td>
	                                          <td>${greens.greens_name }</td>
	                                          <td>${greens.greens_price }</td>
	                                          <td>${greens.greens_count }</td>
	                                          <td><img src="/imagesUrl/${greens.greens_picture }" style="width: 21px;height: 21px;"></td>
	                                          <td>${greens.greens_surplus }</td>
	                                          <td>${greens.greens_status }</td>
	                                          <td><a href="javascript:void(0);" type="button" class="btn btn-info btn-xs" id="btn1" onclick="ensureDel(${greens.greens_id})">删除</a>
	                                              <a type="button" class="btn btn-danger btn-xs"  data-toggle="modal" data-target="#greens_${greens.greens_id}">修改</a>
	                                          </td>
	                                      	</tr>
		                                      	<!--删除的JavaScript部分-->
	                                          <!-- <script>
	                                              //菜品列表中的删除按钮的设置
	                                              var btn1 = document.getElementById("btn1");
	                                              var btn2 = document.getElementById("btn2");
	                                              btn1.onclick = function () {
	                                                  alert("确认删除");
	                                              }
	                                              btn2.onclick = function () {
	                                                  alert("确认删除");
	                                              }
	                                          </script> -->
	
	                                          <!--修改的模态框-->
	                                          <div class="modal fade" id="greens_${greens.greens_id }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
	                                              <div class="modal-dialog" role="document">
	                                                  <div class="modal-content" style="display:block;height: auto;">
	                                                      <div class="modal-header">
	                                                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                                                          <h4 class="modal-title" id="myModalLabel">菜品信息修改</h4>
	                                                      </div>
	                                                      <div class="modal-body ">
	                                                          <div class="container" style="height: auto;width: 100%;">
	                                                              <form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/greens/update.action?greens_id=${greens.greens_id}" method="post"  enctype="multipart/form-data" >
	                                                                  <div class="form-group" style="height:auto;">
	                                                                      <label class="col-xs-2 control-label text-right" style="padding-top:7px;">菜品类型</label>
	                                                                      <div class="col-xs-10" style="margin-bottom: 10px;">
	                                                                          <select name="greens_type" class="form-control">
	                                                                              <c:forEach items="${greensLevels }" var="level">
																						<c:if test="${level.dict_id!=green.greens_type }">
																							<option value="${level.dict_id }" <c:if test="${level.dict_type_value==greens.greens_type }">selected="selected" </c:if>>${level.dict_type_value }</option>
																						</c:if>
																				   </c:forEach>
	                                                                          </select>
	                                                                      </div>
	                                                                  </div>
	                                                                  <div class="form-group">
	                                                                      <label class="col-xs-2 control-label text-right" style="padding-top:7px;">图片</label>
	                                                                      <div class="col-xs-10"  style="margin-bottom: 10px;">
	                                                                          <input type="file" name="pictureFile" id="files">
	                                                                      </div>
	                                                                  </div>
	                                                                  <div class="form-group">
	                                                                      <label class="col-xs-2 control-label text-right" style="padding-top:7px;">菜名</label>
	                                                                      <div class="col-xs-10"  style="margin-bottom: 10px;">
	                                                                          <input type="text" name="greens_name" value="${greens.greens_name }" class="form-control" placeholder="菜名">
	                                                                      </div>
	                                                                  </div>
	                                                                  <div class="form-group">
	                                                                      <label class="col-xs-2 control-label text-right " style="padding-top:7px;">单价</label>
	                                                                      <div class="col-sm-10"  style="margin-bottom: 10px;">
	                                                                          <input type="text" name="greens_price" value="${greens.greens_price }"  class="form-control" placeholder="单价">
	                                                                      </div>
	                                                                  </div>
	                                                                  <div class="form-group">
	                                                                      <label class="col-xs-2 control-label text-right" style="padding-top:7px;">下单次数</label>
	                                                                      <div class="col-xs-10"  style="margin-bottom: 10px;">
	                                                                          <input type="text" name="greens_count" value="${greens.greens_count }"  class="form-control" placeholder="下单次数">
	                                                                      </div>
	                                                                  </div>
	                                                                  <div class="form-group">
	                                                                      <label class="col-xs-2 control-label text-right" style="padding-top:7px;">库存</label>
	                                                                      <div class="col-xs-10"  style="margin-bottom: 10px;">
	                                                                          <input type="text" name="greens_surplus" value="${greens.greens_surplus }"  class="form-control" placeholder="库存">
	                                                                      </div>
	                                                                  </div>
	                                                                  <div class="form-group">
	                                                                      <label class="col-xs-2 control-label text-right " style="padding-top:7px;">是否上架</label>
	                                                                      <div class="col-xs-10"  style="margin-bottom: 10px;">
	                                                                          <select name="greens_status" value="${greens.greens_status }"  class="form-control">
	                                                                              <option value="1">是</option>
	                                                                              <option value="0">否</option>
	                                                                          </select>
	                                                                      </div>
	                                                                  </div>
				                                                      <div class="pull-right">
				                                                          <button type="submit" class="btn btn-primary pull-right">保存</button>
				                                                          <button type="button" class="btn btn-default pull-right" data-dismiss="modal">取消</button>
				                                                      </div>
	                                                              </form>
	                                                          </div>
	                                                      </div>
	                                                  </div>
	                                              </div>
	                                          </div>
	                                          <script>
	                                                  $('#greens_${greens.greens_id}').on('shown.bs.modal', function () {
	                                                      $('#myInput').focus()
	                                                  })
	                                                  console.log(3);
	                                          </script>
	                                      </c:forEach>
                                      </tbody>
                                  </table>
                          </li>
                      </ul>
            </div>
            
            
<!--添加菜品对应的界面-->
            <div class="hidden">
                 <div class="container" style="padding:0 100px;">
                     <div class="page-header col-xs-12" style="margin-top: 10px;margin-bottom:3px;">
                         <h4 style="margin:0;"><b>添加菜品</b></h4>
                     </div>
                     <form role="form" class="form-horizontal" action="${pageContext.request.contextPath }/greens/save.action" method="post" enctype="multipart/form-data" >
                         <div class="form-group" style="height:auto;">
                             <label class="col-xs-2 control-label text-right" style="padding-top:7px;">菜品类型</label>
                             <div class="col-xs-10" style="margin-bottom: 5px;">
                                 <select id="ddd" name="greens_type" class="form-control">
                                     <option>菜品类型</option>
                                 </select>
                             </div>
                         </div>
                         <div class="form-group" style="margin-bottom:10px;">
                             <label class="col-xs-2 control-label text-right" style="padding-top:3px;">图片</label>
                             <div class="col-xs-10"  style="margin-bottom: 5px;">
                                 <input name="pictureFile" type="file" id="file">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-xs-2 control-label text-right" style="padding-top:7px;">菜名</label>
                             <div class="col-xs-10"  style="margin-bottom: 5px;">
                                 <input type="text" name="greens_name" class="form-control" placeholder="菜名">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-xs-2 control-label text-right " style="padding-top:7px;">单价</label>
                             <div class="col-sm-10"  style="margin-bottom: 5px;">
                                 <input type="text" name="greens_price" class="form-control" placeholder="单价">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-xs-2 control-label text-right" style="padding-top:7px;">下单次数</label>
                             <div class="col-xs-10"  style="margin-bottom: 5px;">
                                 <input type="text" name="greens_count" class="form-control" placeholder="下单次数">
                             </div>
                         </div>
                         <div class="form-group">
                             <label class="col-xs-2 control-label text-right" style="padding-top:7px;">库存</label>
                             <div class="col-xs-10"  style="margin-bottom: 5px;">
                                 <input type="text" name="greens_surplus" class="form-control" placeholder="库存">
                             </div>
                         </div>
                         <div class="form-group" style="margin-bottom: 5px;">
                             <label class="col-xs-2 control-label text-right " style="padding-top:7px;">是否上架</label>
                             <div class="col-xs-10"  style="margin-bottom: 3px;">
                                 <select name="greens_status" class="form-control">
                                     <option value="1">是</option>
                                     <option value="0">否</option>
                                 </select>
                             </div>
                         </div>
	                     <div class="modal-footer" style="padding-top:5px;">
	                         <button type="reset" class="btn btn-default btn-sm" data-dismiss="modal">重置</button>
	                         <button type="submit" class="btn btn-primary btn-sm">保存</button>
	                     </div>
                     </form>
                 </div>
            </div>

            <div class="hidden"  style="font-weight: bold;font-size:30px;text-align: center;line-height:450px;">
                欢迎来到O2O-管理系统
            </div>
<!--订单列表对应的界面     -->
            <div id="showOrder" class="hidden">
                <div class="container pay" style="width: 80%;margin:80px 60px; ">
                    <div class="spans container">
                        <span style="width: 46.75px;"><strong>id</strong></span><span><strong>订单编号</strong></span><span><strong>用户名</strong></span><span><strong>真实姓名</strong></span><span><strong>配送地址</strong></span><span style="width:91.75px;"><strong>用户手机</strong></span><span><strong>时间</strong></span><span><strong>交易金额</strong></span><span style="width: 91.75px;"><strong>操作</strong></span>
                    </div>
                    <c:forEach items="${orders }" var="order">
                    	<div class="spans container">
	                        <span style="width: 46.75px;">${order.order_id }</span><span>${order.order_code }</span><span>${order.user.user_name }</span><span>${order.order_realname }</span><span>${order.order_address }</span><span style="width:91.75px;">${order.order_phone }</span><span></span><span>${order.order_total }</span><span style="width: 91.75px;"><button type="button" class="btn btn-info btn-xs" id="order_${order.order_id }">详情</button></span>
	                    </div>
	                    <c:forEach items="${order.orderItems }" var="orderItem">
		                    <div class="outdoor container " id="outdoor">
		                    		<div class="indoor" id="indoor">
			                            <span>${orderItem.greens.greens_name }</span><span>${orderItem.orderitem_count }</span><span>${orderItem.orderitem_total }</span>
			                        </div>
		                    </div>
	                    </c:forEach>
	                    <!--修改和删除的js部分-->
	                    <script>
	                        // 修改(按钮)部分
	                        var button1= document.getElementById("order_${order.order_id}");
	                        var outdoor = document.getElementById("outdoor");
	                        var out = document.getElementById("out");
	                        button1.onclick = function () {
	                            if (outdoor.style.display === "none") {
	                                outdoor.style.display = "block";
	                            } else {
	                                outdoor.style.display = "none";
	                            }
	                        }
	                    </script>
                    </c:forEach>
                    
                </div>
            </div>
            <div class="hidden"  style="font-weight: bold;font-size:30px;text-align: center;line-height:450px;">
                欢迎来到O2O-管理系统
            </div>
<!--用户列表对应的界面      -->
            <div id="showUser" class="hidden">
                <div class="container" style="padding:100px 200px;">
                    <table class="table table-bordered table-responsive ">
                          <thead>
                             <tr >
                                 <th>id</th>
                                 <th>账号</th>
                                 <th>密码</th>
                                 <th>用户级别</th>
                                 <th>操作</th>
                             </tr>
                          </thead>
                          <tbody>
	                          <c:forEach items="${userList }" var="user">
	                          	<tr>
	                                 <td>${user.user_id }</td>
	                                 <td>${user.user_name }</td>
	                                 <td>${user.user_password }</td>
	                                 <td>${user.user_level }</td>
	                                 <td>
	                                     <a type="button" class="btn btn-primary btn-xs btn-block" href="javascript:void(0);" onclick="delUser(${user.user_id})">删除</a>
	                                 </td>
	                             </tr>
	                          </c:forEach>
                          </tbody>
                    </table>
                </div>
            </div>

            <div class="hidden"  style="font-weight: bold;font-size:30px;text-align: center;line-height:450px;">
                欢迎来到O2O-管理系统
            </div>
 <!--字典列表                -->
            <div id="showDict" class="hidden">
                <div class="container" style="padding:100px 120px;">
                    <table class="table table-bordered table-responsive ">
                        <thead>
                        <tr>
                            <th>字典ID</th>
                            <th>字典编号</th>
                            <th>字典类型</th>
                            <th>字典类型值</th>
                            <th>字典类型编号</th>
                            <th>字典状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
	                        <c:forEach items="${dicts }" var="dict">
	                        	<tr>
		                            <td>${dict.dict_id }</td>
		                            <td>${dict.dict_code }</td>
		                            <td>${dict.dict_type }</td>
		                            <td>${dict.dict_type_value }</td>
		                            <td>${dict.dict_type_code }</td>
		                            <td>${dict.dict_status }</td>
		                            <td>
		                                <a type="button" class="btn btn-primary btn-xs btn-block" href="javascript:void(0);" onclick="delDict(${dict.dict_id })">删除</a>
		                            </td>
		                        </tr>
	                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
  <!--字典扩充-->
            <div class="hidden">
                <div class="container"  style="padding:0 100px;">
                   <div class="page-header" style="margin-bottom: 0;margin-top:30px;">
                       <h4 style="margin-top:0;">字典扩充</h4>
                   </div>
                    <div class="modal-body">
                        <form role="form" class="form-horizontal" action="${pageContext.request.contextPath }/dict/save.action" method="post">
                            <div class="form-group">
                                <div class="col-xs-2">
                                    <label class="control-label pull-right">字典编号</label>
                                </div>
                                <div class="col-xs-10">
                                    <input type="text" name="dict_code" class="form-control" placeholder="字典编号">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-2">
                                    <label class="control-label pull-right">字典类型</label>
                                </div>
                                <div class="col-xs-10">
                                    <input type="text" name="dict_type" class="form-control" placeholder="字典类型">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-2" style="padding-left: 0;">
                                    <label class="control-label pull-right">字典类型值</label>
                                </div>
                                <div class="col-xs-10">
                                    <input type="text" name="dict_type_value" class="form-control" placeholder="字典类型值">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-2" style="position: relative;padding:0;">
                                    <label class="control-label pull-left" style="position: absolute;">字典类型编号</label>
                                </div>
                                <div class="col-xs-10">
                                    <input type="text" name="dict_type_code" class="form-control" placeholder="字典类型编号">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-2">
                                    <label class="control-label pull-right">字典状态</label>
                                </div>
                                <div class="col-xs-10">
                                    <input type="text" name="dict_status" class="form-control" placeholder="字典状态">
                                </div>
                            </div>
		                    <div class="modal-footer">
		                        <button type="submit" class="btn btn-primary btn-sm">保存</button>
		                    </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script>
    window.onload = function () {
        function identify(id) {
            return document.getElementById(id);
        }

        //添加菜品--模态框中的图片选择的格式化
        identify("file").onchange = function () {
            var path=this.value;//得到文件路径
            var Upper=path.substr(path.lastIndexOf(".")).toUpperCase();//后缀名变成大写
            if (".JPG" === Upper || ".PNG" === Upper  || ".BMP" === Upper  || ".JPEG" === Upper  || ".GIF"=== Upper
                || ".PSD" === Upper  || ".SVG" === Upper  || ".EMF" === Upper) {

            }else{
                this.value = "";
                alert("图片格式不正确，请重输");
            }
        }

        //tab选项卡部分
        var dls = identify("leftBox").children;//获取left-box所有的孩子dl
        var dds = document.getElementsByTagName("dd");//获取所有的dd 元素;
        var boxes = identify("rightBox").children;
        for(var i=0;i<dls.length;i++){
            dls[i].children[0].children[0].onclick = function () {//当前对象为第一个span
                if(this.parentNode.parentNode.children[1].style.display === "block") {
                    this.parentNode.parentNode.children[1].style.display = "none";
                    this.parentNode.parentNode.children[2].style.display = "none";
                }else{
                    this.parentNode.parentNode.children[1].style.display = "block";
                    this.parentNode.parentNode.children[2].style.display = "block";
                }

            }
            for(var j=0;j<dds.length;j++){
                dds[j].index = j;
                dds[j].onclick = function(){
                    //清除其他所有的元素的类
                    for(var k=0;k<dds.length;k++){
                        boxes[k].className = "hidden";
                        dds[k].className = "";
                    }
                    //保留本元素的类
                    boxes[this.index].className="show";
                    this.className = "current";
                }
            }
        }
        
        
   		//派发一个失去焦点的事件
   		$("#addGreens").click(function(){
   			var $greensType=$("#ddd");
   			$greensType.html("<option>---菜品类型---</option>");
   			//设置请求路径
   			var $url="${pageContext.request.contextPath }/greens/add.action";
   			//获取请求参数
   			
   			//异步操作
   			$.post($url,function(data){
   				if(data){
   					$(data).each(function(){
   						$greensType.append("<option value='"+this.dict_id+"'>"+this.dict_type_value+"</option>");
   					});
   				}
   			},"json");
   		});
   		
   		var tabFlag="${tabFlag}";
        if(tabFlag=="1"){
        	$("#default").attr("class", "hidden");
        	$("#greensList").css("display","block");
        	$("#addGreens").css("display","block");
        	$("#showGreens").attr("class", "show");
        }else if(tabFlag=="2"){
        	$("#default").attr("class", "hidden");
        	$("#orderList").css("display","block");
        	$("#showOrder").attr("class", "show");
        }else if(tabFlag=="3"){
        	$("#default").attr("class", "hidden");
        	$("#userList").css("display","block");
        	$("#showUser").attr("class", "show");
        }else if(tabFlag=="4"){
        	$("#default").attr("class", "hidden");
        	$("#dictList").css("display","block");
        	$("#addDict").css("display","block");
        	$("#showDict").attr("class", "show");
        }
        
        
    }
        function ensureDel(greens_id){
			if(confirm("确定删除该菜品？")){
				location.href="${pageContext.request.contextPath }/greens/delete.action?greens_id="+greens_id;
			}
		}
        
        function delUser(user_id){
			if(confirm("确定删除该菜品？")){
				location.href="${pageContext.request.contextPath }/admin/delete.action?user_id="+user_id;
			}
		}
        
        function delDict(dict_id){
			if(confirm("确定删除该菜品？")){
				location.href="${pageContext.request.contextPath }/dict/delete.action?dict_id="+dict_id;
			}
		}
</script>
</body>
</html>