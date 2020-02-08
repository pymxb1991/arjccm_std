<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节假日管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/holiday/ccmWorkerHoliday/">数据列表</a></li>
		<%-- <shiro:hasPermission name="holiday:ccmWorkerHoliday:edit"><li><a href="${ctx}/holiday/ccmWorkerHoliday/form">节假日管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkerHoliday" action="${ctx}/holiday/ccmWorkerHoliday/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>假日名称：</label>
				<form:input path="holidayName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>开始日期：</label>
				<input name="holidayBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerHoliday.holidayBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>结束日期：</label>
				<input name="holidayEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerHoliday.holidayEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>创建者：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%--<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerHoliday.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerHoliday.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>--%>

<%--			<li class="clearfix"></li>--%>
		</ul>
	<sys:message content="${message}"/>
	<div class="clearfix pull-right btn-box">
		<shiro:hasPermission name="holiday:ccmWorkerHoliday:edit">
			<input id="btnSubmit" class="btn btn-export" style="width: 75px;display:inline-block;float: right;margin-left: 20px;margin-right: 14px;margin-bottom: 20px" type="button" value="添加" onclick="parent.LayerDialog('${ctx}/holiday/ccmWorkerHoliday/form','添加','1100px','700px')"/>
		</shiro:hasPermission>
		<input id="btnSubmit" class="btn btn-primary" style="width: 75px;display:inline-block;float: right;margin-left: 20px;margin-bottom: 20px" type="submit" value="查询"/>
	</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>假日名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>创建者</th>
				<th>备注信息</th>
				<shiro:hasPermission name="holiday:ccmWorkerHoliday:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkerHoliday">
			<tr>
				<td style="height: 50px"><a href="${ctx}/holiday/ccmWorkerHoliday/form?id=${ccmWorkerHoliday.id}">
					${ccmWorkerHoliday.holidayName}
				</a></td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkerHoliday.holidayBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkerHoliday.holidayEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					${ccmWorkerHoliday.name}
				</td>
				<td class="" style="height: 50px">
					${ccmWorkerHoliday.remarks}
				</td>
				<shiro:hasPermission name="holiday:ccmWorkerHoliday:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/holiday/ccmWorkerHoliday/form?id=${ccmWorkerHoliday.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/holiday/ccmWorkerHoliday/delete?id=${ccmWorkerHoliday.id}" onclick="return confirmx('确认要删除该假日信息吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</ul>
</body>
</html>