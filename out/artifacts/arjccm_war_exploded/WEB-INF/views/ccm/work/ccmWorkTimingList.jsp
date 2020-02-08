<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时提醒管理</title>
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
		<li class="active"><a href="${ctx}/work/ccmWorkTiming/">数据列表</a></li>
		<%-- <shiro:hasPermission name="work:ccmWorkTiming:edit"><li><a href="${ctx}/work/ccmWorkTiming/form">定时提醒添加</a></li></shiro:hasPermission> --%>
		<li><a href="${ctx}/work/ccmWorkTiming/form">数据添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkTiming" action="${ctx}/work/ccmWorkTiming/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>提醒时间：</label>
				<input name="beginTiming" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkTiming.beginTiming}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endTiming" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkTiming.endTiming}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>提醒人员：</label>
				<sys:treeselect id="user" name="user.id" value="${ccmWorkTiming.user.id}" labelName="user.name" labelValue="${ccmWorkTiming.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>提醒时间</th>
				<th>提醒人员</th>
				<%-- <shiro:hasPermission name="work:ccmWorkTiming:edit"><th>操作</th></shiro:hasPermission> --%>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkTiming">
			<tr>
				<td><a href="${ctx}/work/ccmWorkTiming/form?id=${ccmWorkTiming.id}">
					<fmt:formatDate value="${ccmWorkTiming.timing}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ccmWorkTiming.user.name}
				</td>
				<%-- <shiro:hasPermission name="work:ccmWorkTiming:edit"><td>
    				<a href="${ctx}/work/ccmWorkTiming/form?id=${ccmWorkTiming.id}">修改</a>
					<a href="${ctx}/work/ccmWorkTiming/delete?id=${ccmWorkTiming.id}" onclick="return confirmx('确认要删除该定时提醒吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
				<td>
    				<a class="btnList" href="${ctx}/work/ccmWorkTiming/form?id=${ccmWorkTiming.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/work/ccmWorkTiming/delete?id=${ccmWorkTiming.id}" onclick="return confirmx('确认要删除该定时提醒吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>