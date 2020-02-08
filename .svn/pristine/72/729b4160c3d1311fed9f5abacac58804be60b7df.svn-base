<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>我提交的</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(function() {
	  $("#flowType").val("${pbsFlowwork.flowType}").select2();
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/flow/dealPc">我提交的申请</a></li>
		<li><a href="${ctx}/flow/dealPc/applyNew">新的申请</a></li>
		<li><a href="${ctx}/flow/dealPc/dealtList">待我处理</a></li>
		<li><a href="${ctx}/flow/dealPc/dealtFinishList">我已处理</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="pbsFlowwork"
		action="${ctx}/flow/dealPc/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>申请简述信息：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>申请类型：</label> 
				<form:select path="flowType" class="input-medium ">
					<option value="">全部</option>
					<option value="JoinOfc">转入</option>
					<option value="DragOfc">转出</option>
				</form:select>
<%-- 			<form:input path="sFlowid.sName" --%>
<%-- 					htmlEscape="false" maxlength="64" class="input-medium" /> --%>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请简述信息</th>
				<th>申请类型</th>
				<th>审核结果</th>
				<th>开始日期</th>
				<!-- <th>结束日期</th> -->
				<th>绑定的数据类型</th>
				<th>操作人员</th>
				<th>学员信息</th>
				<shiro:hasPermission name="flow:pbsFlowwork:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsFlowwork">
				<tr>
					<td><a
						href="${ctx}/flow/dealPc/dealtMyInfo?id=${pbsFlowwork.SBindkey}">
							${pbsFlowwork.SName} </a></td>
					<td>${pbsFlowwork.sFlowid.SName}</td>
					<td>${fns:getDictLabel(pbsFlowwork.sBindstat, 'flowresult', '')}
					</td>
					<td><fmt:formatDate value="${pbsFlowwork.dtStartdate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td><fmt:formatDate value="${pbsFlowwork.dtEnddate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
					<td>${fns:getDictLabel(pbsFlowwork.SBinddata, 'flowbinddata', '')}
					</td>
					<td>${pbsFlowwork.sOperator.name}</td>
					<td>${pbsFlowwork.sBindmember.SName}</td>
					<td><a
						href="${ctx}/flow/dealPc/dealtMyInfo?id=${pbsFlowwork.SBindkey}" title = "查看"><i class="icon-eye-open"></i></a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>