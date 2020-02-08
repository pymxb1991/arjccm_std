<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>民政工作管理管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">社区事务</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/service/ccmServiceCivil/">数据列表</a></li>
		<shiro:hasPermission name="service:ccmServiceCivil:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/service/ccmServiceCivil/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmServiceCivil" action="${ctx}/service/ccmServiceCivil/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>工作类型：</label>
				<form:select path="type" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_service_civil_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>受理人：</label>
				<form:input path="receiver" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>被受理人：</label>
				<form:input path="gods" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>开始日期：</label>
				<input name="beginTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmServiceCivil.beginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> </li>
			<li class="first-line"><label>结束日期：</label>	<input name="endTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmServiceCivil.endTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;/*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>工作类型</th>
				<th>受理人</th>
				<th>被受理人</th>
				<th>时间</th>
				<shiro:hasPermission name="service:ccmServiceCivil:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmServiceCivil">
			<tr>
				<td style="height: 50px"><a href="${ctx}/service/ccmServiceCivil/form?id=${ccmServiceCivil.id}">
					${fns:getDictLabel(ccmServiceCivil.type, 'ccm_service_civil_type', '')}
				</a></td>
				<td style="height: 50px">
					${ccmServiceCivil.receiver}
				</td>
				<td style="height: 50px">
					${ccmServiceCivil.gods}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmServiceCivil.times}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="service:ccmServiceCivil:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/service/ccmServiceCivil/form?id=${ccmServiceCivil.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/service/ccmServiceCivil/delete?id=${ccmServiceCivil.id}" onclick="return confirmx('确认要删除该民政工作管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>