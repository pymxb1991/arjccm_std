<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
	<title>综合智慧指挥系统</title>
	<script src="${ctxStatic}/jquery/jquery-2.2.4.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			window.location.href="${ctx}/sys/map/statJump";				//滨海	
			//window.location.href="${ctx}/sys/map/statJumpXinmi";		//新密	
		});
	</script>
</head>
<body>
</body>

