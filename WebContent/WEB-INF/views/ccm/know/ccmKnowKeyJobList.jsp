<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专项工作管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">个人办公</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/know/ccmKnowKeyJob/">数据列表</a></li>
		<shiro:hasPermission name="know:ccmKnowKeyJob:edit"><li style="width: 112px"><a style="text-align: center" href="${ctx}/know/ccmKnowKeyJob/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmKnowKeyJob" action="${ctx}/know/ccmKnowKeyJob/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>专项名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>开始日期：</label>
				<input name="beginTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmKnowKeyJob.beginTimeStart}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="first-line"><label>结束日期：</label>
				<input name="endTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmKnowKeyJob.endTimeStart}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>地点：</label>
				<form:input path="address" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li class="first-line"><label>专项工作简介：</label>
				<form:input path="abstracts" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

		</ul>
		<div class="clearfix pull-right btn-box">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;margin-left: 20px;margin-right: 14px;margin-bottom: 20px">
				<i></i><span style="font-size: 12px">查询</span>  </a>
		</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>专项名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>地点</th>
				<th>专项工作简介</th>
				<shiro:hasPermission name="know:ccmKnowKeyJob:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmKnowKeyJob">
			<tr>
				<td style="height: 50px"><a href="${ctx}/know/ccmKnowKeyJob/form?id=${ccmKnowKeyJob.id}">
					${ccmKnowKeyJob.name}
				</a></td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmKnowKeyJob.timeStart}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmKnowKeyJob.timeEnd}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="height: 50px">
					${ccmKnowKeyJob.address}
				</td>
				<td style="height: 50px">
					${ccmKnowKeyJob.abstracts}
				</td>
				<shiro:hasPermission name="know:ccmKnowKeyJob:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/know/ccmKnowKeyJob/form?id=${ccmKnowKeyJob.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/know/ccmKnowKeyJob/delete?id=${ccmKnowKeyJob.id}" onclick="return confirmx('确认要删除该专项工作吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</ul>
</body>
</html>