<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作日志管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").on("click" ,function(){
			$("#searchForm").submit();
		})
		$("#btnExport").click(
				function() {
					top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
						if (v == "ok") {
							// 借用导出action
							$("#searchForm").attr("action", "${ctx}/sys/ccmWorkReport/exportJob");
							$("#searchForm").submit();
							// 还原查询action 
							$("#searchForm").attr("action", "${ctx}/sys/ccmWorkReport/Job");
						}
					}, {
						buttonsFocus : 1
					});
					top.$('.jbox-body .jbox-icon').css('top', '55px');
				});
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">个人办公</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/sys/ccmWorkReport/Job/">数据列表</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkReport"
		action="${ctx}/sys/ccmWorkReport/Job/" method="post"
		class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form pull-left">
			<%-- <li><label>类型：</label> <form:select path="type"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_work_report_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li> --%>
			<li class="first-line"><label>社工姓名：</label> <form:input path="createName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>		
			<li class="first-line"><label>提交开始日期：</label> <input name="beginDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmWorkReport.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></li>
			<li class="first-line"><label>提交结束日期：</label>
				<input name="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmWorkReport.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</li>
			<li class="first-line"><label>标题：</label> <form:input path="title"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
					
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li> -->

<%--			<li class="clearfix"></li>--%>
		</ul>
		<div class="clearfix pull-right btn-box">
			<!-- 导出 -->
			<a href="javascript:;" id="btnExport" class="btn btn-export" style="width: 49px;display:inline-block;float: right">
				<i></i> <span style="font-size: 12px">导出</span>
			</a>
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right">
				<i></i><span style="font-size: 12px">查询</span>  </a>
		</div>
	</form:form>
	<sys:message content="${message}" />

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>行政区划</th>
				<th>社工姓名</th>
				<th>标题</th>
				<th>日志时间范围</th>
				<th>提交时间</th>

				<shiro:hasPermission name="sys:ccmWorkReport:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmWorkReport">
				<tr>
					<td style="height: 50px">${ccmWorkReport.name}</td>
					
					<td style="height: 50px">${ccmWorkReport.createName}</td>
					
					<td style="height: 50px">${ccmWorkReport.title}</td>
							
					<td style="height: 50px"><fmt:formatDate value="${ccmWorkReport.beginDate}"
							pattern="yyyy-MM-dd" /> - <fmt:formatDate
							value="${ccmWorkReport.endDate}" pattern="yyyy-MM-dd" /></td>

					<td style="height: 50px"><fmt:formatDate value="${ccmWorkReport.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					
					<shiro:hasPermission name="sys:ccmWorkReport:edit">
						<td style="height: 50px">
							<a class="btnList" 
							onclick="parent.LayerDialog('${ctx}/sys/ccmWorkReport/Jobform?id=${ccmWorkReport.id}', '日常工作详情', '900px', '700px')"
							title="查看"><i class="icon-file"></i></a>
							<a class="btnList"
							onclick="parent.LayerDialog('${ctx}/sys/ccmWorkReport/SaveJobform?id=${ccmWorkReport.id}', '日常工作编辑', '900px', '700px')"
							title="编辑"><i class="icon-pencil"></i></a>
						</td>	
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</ul>
</body>
</html>