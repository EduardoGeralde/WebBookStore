<%@attribute name="title" required="true" %>
<%@attribute name="bodyClass" required="true" %>
<%@attribute name="extraScripts" fragment="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@tag language="java" pageEncoding="UTF-8"%>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<title>${title}</title>
</head>
<body class="${bodyClass}">

	<%@include file="/WEB-INF/header.jsp" %>
	
	<jsp:doBody/>

	<%@include file="/WEB-INF/footer.jsp" %>
	
	<jsp:invoke fragment="extraScripts"/>
	
</body>
</html>
