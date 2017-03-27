<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Registration</title>
</head>
<body>
	<spring:hasBindErrors name="product">
		<ul>
		<c:forEach var="error" items="${errors.allErrors}">	
			<li>${error.code}-${error.field}</li>
		</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<form:form action="${spring:mvcUrl('saveProduct').build()}" method="post" commandName="product">
		<div>
			<label for="title">Title</label> 
			<input type="text" name="title" id="title" />
			<form:errors path="title"/>
		</div>
		<div>
			<label for="description">Description</label>
			<textarea rows="10" cols="20" name="description" id="description"></textarea>
			<form:errors path="description"/>
		</div>
		<div>
			<label for="pages">Number of Pages</label> 
			<input type="text" name="pages" id="pages"/>
			<form:errors path="pages"/>
		</div>
		<div>
			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label> 
					<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/> 
					<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}" />
				</div>
			</c:forEach>
		</div>
		<div>
			<input type="submit" value="Save">
		</div>
	</form:form>
</body>
</html>


