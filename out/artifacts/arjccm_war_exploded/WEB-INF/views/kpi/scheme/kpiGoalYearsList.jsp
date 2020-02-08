<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门年度目标管理</title>
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
<%--<span class="nav-position" >当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">业务考核</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/scheme/kpiGoalYears/">数据列表</a></li>
		<shiro:hasPermission name="scheme:kpiGoalYears:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/scheme/kpiGoalYears/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="kpiGoalYears" action="${ctx}/scheme/kpiGoalYears/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>部门：</label>
				<sys:treeselect id="office" name="office.id" value="${kpiGoalYears.office.id}" labelName="office.name" labelValue="${kpiGoalYears.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-medium" allowClear="true" notAllowSelectParent="fasle"/>
			</li>
			<li class="first-line"><label>年度日期：</label>
				<%--<form:input path="years" htmlEscape="false" maxlength="32" class="input-medium"/>--%>
				<input name="years" type="text"
					   readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${kpiGoalYears.years}"
					   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true});" />
			</li>
			<li class="first-line"><label>是否完成：</label>
				<form:select path="finished" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

<%--			<li class="clearfix"></li>--%>
		</ul>


	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;">
			<i></i><span style="font-size: 12px">查询</span>  </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>部门</th>
				<th>年度</th>
				<th>是否完成</th>
				<th>更新时间</th>
				<shiro:hasPermission name="scheme:kpiGoalYears:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="kpiGoalYears">
			<tr>
				<td style="height: 50px"><a href="${ctx}/scheme/kpiGoalYears/form?id=${kpiGoalYears.id}">
					${kpiGoalYears.office.name}
				</a></td>
				<td style="height: 50px">
					${kpiGoalYears.years}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(kpiGoalYears.finished, 'yes_no', '')}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${kpiGoalYears.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="scheme:kpiGoalYears:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/scheme/kpiGoalYears/form?id=${kpiGoalYears.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/scheme/kpiGoalYears/delete?id=${kpiGoalYears.id}" onclick="return confirmx('确认要删除该部门年度目标吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>