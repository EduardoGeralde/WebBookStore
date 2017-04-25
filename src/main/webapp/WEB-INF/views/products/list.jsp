<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags" %>

<customTags:page bodyClass="list" title="${title}">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user"/>
		<div>
			Hello ${user.name}
		</div>
	</sec:authorize>
	<ul class="menu">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li>
				<a href="${spring:mvcUrl('PC#form').build()}">Register New Product</a>
			</li>
		</sec:authorize>
	</ul>
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
</customTags:page>

