<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重要活动管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">事件管理</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/know/ccmKnowKeyActivity/">数据列表</a></li>
		<shiro:hasPermission name="know:ccmKnowKeyActivity:edit"><li style="width: 112px" ><a style="text-align: center" href="${ctx}/know/ccmKnowKeyActivity/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmKnowKeyActivity" action="${ctx}/know/ccmKnowKeyActivity/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>活动名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>活动类别：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_know_key_activity_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
            <li class="first-line"><label>开始日期：</label>
                <input name="beginTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                       value="<fmt:formatDate value="${ccmKnowKeyActivity.beginTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></li>
            <li class="first-line"><label>结束日期：</label>
                <input name="endTimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                       value="<fmt:formatDate value="${ccmKnowKeyActivity.endTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </li>
			<li class="first-line"><label>活动地点：</label>
				<form:input path="address" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="second-line"><label>参加人员：</label>
				<form:input path="attendee" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

		</ul>
		<div class="clearfix pull-right btn-box">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;margin-left: 20px;margin-right: 14px;margin-bottom: 20px">
				<i></i> <span style="font-size: 12px">查询</span> </a>
		</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>活动名称</th>
				<th>活动类别</th>
				<th>开始时间</th>
				<th>活动时长(分钟)</th>
				<th>活动地点</th>
				<th>参加人员</th>
				<shiro:hasPermission name="know:ccmKnowKeyActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmKnowKeyActivity">
			<tr>
				<td style="height: 50px"><a href="${ctx}/know/ccmKnowKeyActivity/form?id=${ccmKnowKeyActivity.id}">
					${ccmKnowKeyActivity.name}
				</a></td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmKnowKeyActivity.type, 'ccm_know_key_activity_type', '')}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmKnowKeyActivity.timeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					${ccmKnowKeyActivity.timeLong}
				</td>
				<td style="height: 50px">
					${ccmKnowKeyActivity.address}
				</td>
				<td style="height: 50px">
					${ccmKnowKeyActivity.attendee}
				</td>
				<shiro:hasPermission name="know:ccmKnowKeyActivity:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/know/ccmKnowKeyActivity/form?id=${ccmKnowKeyActivity.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/know/ccmKnowKeyActivity/delete?id=${ccmKnowKeyActivity.id}" onclick="return confirmx('确认要删除该重要活动吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</ul>
</body>
</html>