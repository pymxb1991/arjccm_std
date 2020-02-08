<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>联系人管理</title>
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
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">工作助手</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/work/ccmWorkRelation/">数据列表</a></li>
		<shiro:hasPermission name="work:ccmWorkRelation:edit"><li style="width: 140px;text-align:center"><a href="${ctx}/work/ccmWorkRelation/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkRelation" action="${ctx}/work/ccmWorkRelation/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="first-line"><label>单位：</label>
				<form:input path="department" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="first-line"><label>电话：</label>
				<form:input path="tel" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>家庭住址：</label>
				<form:input path="adds" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;
   /*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>姓名</th>
				<th>单位</th>
				<th>电话</th>
				<th>邮件</th>
				<th>家庭住址</th>
				<th>更新时间</th>
				<shiro:hasPermission name="work:ccmWorkRelation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkRelation">
			<tr>
				<td style="height: 50px"><a href="${ctx}/work/ccmWorkRelation/form?id=${ccmWorkRelation.id}">
					${ccmWorkRelation.name}
				</a></td>
				<td style="height: 50px">
					${ccmWorkRelation.department}
				</td>
				<td style="height: 50px">
					${ccmWorkRelation.tel}
				</td>
				<td style="height: 50px">
					${ccmWorkRelation.mail}
				</td>
				<td style="height: 50px">
					${ccmWorkRelation.adds}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkRelation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="work:ccmWorkRelation:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/work/ccmWorkRelation/form?id=${ccmWorkRelation.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/work/ccmWorkRelation/delete?id=${ccmWorkRelation.id}" onclick="return confirmx('确认要删除该联系人吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>