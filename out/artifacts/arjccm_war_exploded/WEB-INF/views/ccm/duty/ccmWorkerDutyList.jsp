<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社工职责管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click", function () {
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">网格员管理</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/duty/ccmWorkerDuty/">数据列表</a></li>
		<%-- <shiro:hasPermission name="duty:ccmWorkerDuty:edit"><li><a href="${ctx}/duty/ccmWorkerDuty/form">社工职责添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkerDuty" action="${ctx}/duty/ccmWorkerDuty/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li style="display:none;"><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_worker_duty_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

				<%-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="添加" onclick="parent.LayerDialog('${ctx}/duty/ccmWorkerDuty/form','添加','1100px','700px')"/>
				</li> --%>

<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<shiro:hasPermission name="duty:ccmWorkerDuty:edit"><li class="btns">
		<a onclick="parent.LayerDialog('${ctx}/duty/ccmWorkerDuty/form','添加','1100px','700px')" class="btn btn-export" style="width: 49px;display:inline-block;float: right;">
			<i></i> <span style="font-size: 12px">添加</span>
		</a>
		</shiro:hasPermission>
		<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;">
			<i></i><span style="font-size: 12px">查询</span>
		</a>
			<%--<li class="btns" style="display:none;"><input id="btnSubmit" class="btn btn-primary" style="width: 75px;display:inline-block;float: right;margin-left: 20px;margin-bottom: 20px" type="submit" value="查询"/></li>--%>
	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>标题</th>
				<th style="display:none;">状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="duty:ccmWorkerDuty:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkerDuty">
			<tr >
				<td id="td_class" style="height: 50px"><a onclick="parent.LayerDialog('${ctx}/duty/ccmWorkerDuty/form?id=${ccmWorkerDuty.id}', '编辑', '1100px','700px')">
					${ccmWorkerDuty.title}
				</a></td>
				<td style="display:none;height: 50px">
					${fns:getDictLabel(ccmWorkerDuty.status, 'ccm_worker_duty_status', '')}
				</td>
				<td id="td_class" style="height: 50px">
					<fmt:formatDate value="${ccmWorkerDuty.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<shiro:hasPermission name="duty:ccmWorkerDuty:edit"><td id="td_class" style="height: 50px">
					<%-- <c:if test="${ccmWorkerDuty.status eq '10'}">
						<a class="btnList" href="${ctx}/duty/ccmWorkerDuty/updatestatus?id=${ccmWorkerDuty.id}" title="发布"><i class="icon-plane"></i></a>
					</c:if>
					<c:if test="${ccmWorkerDuty.status eq '20'}">
						<a class="btnList" onclick="alertx('此信息已发布')" title="发布"><i class="icon-plane"></i></a>
					</c:if> --%>
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/duty/ccmWorkerDuty/form?id=${ccmWorkerDuty.id}', '编辑', '1100px','700px')" title="修改">
						<i class="icon-pencil"></i>
					</a> 
					<%-- <a class="btnList" href="${ctx}/duty/ccmWorkerDuty/form?id=${ccmWorkerDuty.id}" title="修改"><i class="icon-pencil"></i></a> --%>
					<a class="btnList" href="${ctx}/duty/ccmWorkerDuty/delete?id=${ccmWorkerDuty.id}" onclick="return confirmx('确认要删除该社工职责吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>