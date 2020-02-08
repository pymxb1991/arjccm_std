<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<html>
<head>
	<title>居民用户管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/Ex/">居民用户管理列表</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute="ccmFontUser" action="${ctx}/Ex/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录名</th>
				<th>姓名</th>
				<th>身份证号</th>
			<!-- 	<th>审核状态</th>
				<th>更新时间</th>
				<th>备注信息</th> -->
				<th>邮箱</th>
				<th>联系方式</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmFontUser">
			<tr>
				<td>
					${ccmFontUser.loginName}
				</a></td>
		<td>
		<c:choose>
    	 <c:when test="${ccmFontUser.isNameVisable eq '0'}">
          ${ccmFontUser.name}
     	 </c:when>
     	 <c:otherwise>
          <span>******</span>
    	 </c:otherwise>
		 </c:choose>
		 
		 <td>
		<c:choose>
    	 <c:when test="${ccmFontUser.isNoVisable eq '0'}">
         ${ccmFontUser.no}
     	 </c:when>
     	 <c:otherwise>
          <span>******</span>
    	 </c:otherwise>
		 </c:choose>
		 
		 <td>
		<c:choose>
    	 <c:when test="${ccmFontUser.isEmailVisable eq '0'}">
          ${ccmFontUser.email}
     	 </c:when>
     	 <c:otherwise>
          <span>******</span>
    	 </c:otherwise>
		 </c:choose>
		 
		 <td>
		<c:choose>
    	 <c:when test="${ccmFontUser.isMobileVisable eq '0'}">
         ${ccmFontUser.mobile}
     	 </c:when>
     	 <c:otherwise>
          <span>******</span>
    	 </c:otherwise>
		 </c:choose>
			<%-- 	<td>
					${fns:getDictLabel(ccmFontUser.loginFlag,'font_user_loginFlag','')}	
				</td>
				<td>
					<fmt:formatDate value="${ccmFontUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmFontUser.remarks}
				</td> --%>
<%-- 				<shiro:hasPermission name="cms:ccmFontUser:edit"><td>
    				<a href="${ctx}/cms/ccmFontUser/form?id=${ccmFontUser.id}">修改</a>
					<a href="${ctx}/cms/ccmFontUser/delete?id=${ccmFontUser.id}" onclick="return confirmx('确认要删除该居民用户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>