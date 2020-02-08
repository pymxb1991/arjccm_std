<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案（事）件干系人管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		// $(document).ready(function() {
		//
		// });

		var preventType = ${ccmEventStakeholder.preventType};
		$('#preventType').val(preventType);
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
		<%-- <li><a href="${ctx}/event/ccmEventIncident/list">案（事）件登记列表</a></li>
	    <!-- 案（事）件登记编辑权限  -->
		<shiro:hasPermission name="event:ccmEventIncident:edit">
			<li><a href="${ctx}/event/ccmEventIncident/form">案（事）件登记添加</a></li>
		</shiro:hasPermission> --%>
		<!-- 事件干系人 -->
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/event/ccmEventStakeholder/list?incidentId=${incidentId}">数据列表</a></li>
		<c:if test="${not empty incidentId}">
			<shiro:hasPermission name="event:ccmEventStakeholder:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/event/ccmEventStakeholder/form?incidentId=${incidentId}">案（事）件干系人添加</a></li></shiro:hasPermission>
		</c:if>
		
	</ul>
	<form:form id="searchForm" modelAttribute="ccmEventStakeholder" action="${ctx}/event/ccmEventStakeholder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="incidentId" />
		<ul class="ul-form">
			<li class="first-line"><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="80" class="input-medium"/>
			</li>
			<%-- <li><label>干系人类型：</label>
				<form:select path="preventType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_event_stakeholder_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li class="first-line"><label>证件号码：</label>
				<form:input path="idenNum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li>
				<form:input path="preventType" style="display:none;" id="preventType" />
			</li>
			<li class="btns">
			<a href="javascript:void(0);" onclick="$('#searchForm').submit();" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" >
		<thead>
			<tr>
				<th>姓名</th>
				<th>所属案（事）件</th>
				<th>干系人类型</th>
				<th>证件号码</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>现住门（楼）详址</th>
				<%--<th>是否为严重精神障碍患者</th>
				<th>是否为未成年人</th>
				<th>是否为青少年</th>--%>
				<shiro:hasPermission name="event:ccmEventStakeholder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmEventStakeholder">
			<tr>
				<td style="height: 50px"><a href="${ctx}/event/ccmEventStakeholder/form?id=${ccmEventStakeholder.id}">
					${ccmEventStakeholder.name}
				</td>
				<td style="height: 50px">
					${ccmEventStakeholder.incidentName}
				</a></td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmEventStakeholder.preventType, 'ccm_event_stakeholder_type', '')}
				</td>
				<td style="height: 50px">
					${ccmEventStakeholder.idenNum}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmEventStakeholder.sex, 'sex', '')}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmEventStakeholder.birthday}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="tp" style="height: 50px">
					${ccmEventStakeholder.residencedetail}
				</td>
			<%--	<td>
					${fns:getDictLabel(ccmEventStakeholder.isPsychogeny, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmEventStakeholder.isNonage, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmEventStakeholder.isKym, 'yes_no', '')}
				</td>--%>
				<shiro:hasPermission name="event:ccmEventStakeholder:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/event/ccmEventStakeholder/form?id=${ccmEventStakeholder.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList"  href="${ctx}/event/ccmEventStakeholder/delete?id=${ccmEventStakeholder.id}" onclick="return confirmx('确认要删除该案（事）件干系人吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>