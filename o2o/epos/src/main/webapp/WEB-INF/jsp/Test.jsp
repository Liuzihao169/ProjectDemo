<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>测试</title>
</head>
<body>
	${msg } 
	
	<c:forEach items="${userList }" var="user">
		<p>${user.user_id }---${user.user_name }---${user.user_password }---${user.user_level }</p>
	</c:forEach>
	
	<c:forEach items="${greensList }" var="greens">
		<p>${greens.greens_id }---${greens.greens_type }---${greens.greens_name }---${greens.greens_price }---${greens.greens_count }---${greens.greens_picture }---${greens.greens_surplus }---${greens.greens_status }</p>
	</c:forEach>
	
	<p>${greens.greens_id }---${greens.greens_type }---${greens.greens_name }---${greens.greens_price }---${greens.greens_count }---${greens.greens_picture }---${greens.greens_surplus }---${greens.greens_status }</p>
	<img alt="" src="${pageContext.request.contextPath }/images/${greens.greens_picture}">
	<select>
		<c:forEach items="${greensLevels }" var="level">
			<c:if test="${level.dict_id==greens.greens_type }">
				<option value="${level.dict_id }" selected="selected">${level.dict_type_value }</option>
			</c:if>
			<c:if test="${level.dict_id!=greens.greens_type }">
				<option value="${level.dict_id }">${level.dict_type_value }</option>
			</c:if>
		</c:forEach>
	</select>
	
	<c:forEach items="${orders }" var="order">
		${order.order_id }---${order.user.user_name }----s
		<c:forEach items="${order.orderItems }" var="item">
			${item.orderitem_id }---${item.greens.greens_name }
		</c:forEach>
	</c:forEach>
</body>
</html>