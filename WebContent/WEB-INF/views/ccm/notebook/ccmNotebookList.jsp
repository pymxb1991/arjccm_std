<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记事本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $('#btnSubmit').click(function(){
                $('#searchForm').submit();
            });
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
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/notebook/ccmNotebook/">数据列表</a></li>
		<shiro:hasPermission name="notebook:ccmNotebook:edit"><li style="width: 140px;text-align:center"><a  href="${ctx}/notebook/ccmNotebook/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmNotebook" action="${ctx}/notebook/ccmNotebook/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>笔记标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium" />
			</li>
			<li class="first-line"><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;margin-right: 14px;
    margin-bottom: 20px;/*margin-top: 25px;*/display:inline-block;float: right;margin-left: 20px" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient" style="table-layout: fixed">
		<thead>
			<tr>
				<th>笔记标题</th>
				<th>内容</th>
				<th>更新时间</th>
				<shiro:hasPermission name="notebook:ccmNotebook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmNotebook">
			<tr>
				<td style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;height: 50px"><a href="${ctx}/notebook/ccmNotebook/form?id=${ccmNotebook.id}">
					${ccmNotebook.title}
				</a></td>
				<td style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;height: 50px">
					${ccmNotebook.content}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmNotebook.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="notebook:ccmNotebook:edit"><td style="height: 50px">
					<a class="btnList" href="${ctx}/notebook/ccmNotebook/form?id=${ccmNotebook.id}"><i title="修改" class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/notebook/ccmNotebook/delete?id=${ccmNotebook.id}" onclick="return confirmx('确认要删除该记事本吗？', this.href)"><i title="删除" class="icon-trash"></i> </a>
    				<!-- <a href="${ctx}/notebook/ccmNotebook/form?id=${ccmNotebook.id}">修改</a>
					<a href="${ctx}/notebook/ccmNotebook/delete?id=${ccmNotebook.id}" onclick="return confirmx('确认要删除该记事本吗？', this.href)">删除</a> -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>