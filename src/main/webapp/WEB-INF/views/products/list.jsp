<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
</head>
<body>
	<security:authentication property="principal" var="user"/>
	Hello ${user.name}
	<table>
		<tr>
			<td>Titles</td>
			<td>Values</td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>
					<a href="${spring:mvcUrl('PC#show').arg(0,product.id).build()}">${product.title}</a>
				</td>
				<td>
					<c:forEach items="${product.prices}" var="price">
						[${price.value}-${price.bookType}]
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

