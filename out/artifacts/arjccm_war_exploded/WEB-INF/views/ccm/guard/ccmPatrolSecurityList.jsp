<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>警卫管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active" ><a href="${ctx}/guard/ccmGuardUnit/securityList/">警卫任务安排</a></li>
		<li ><a href="${ctx}/guard/ccmGuardUnit/">警卫单位列表</a></li>
		<%--<shiro:hasPermission name="guard:ccmGuardUnit:edit"><li><a href="${ctx}/guard/ccmGuardUnit/form">警卫单位添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPatrolSecurity" action="${ctx}/security/ccmPatrolSecurity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>巡逻民警：</label>
				<sys:treeselect id="user" name="user.id" value="${ccmPatrolSecurity.user.id}" labelName="user.name" labelValue="${ccmPatrolSecurity.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>警卫时间：</label>
				<input name="securityTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmPatrolSecurity.securityTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>参与单位：</label>
				<sys:treeselect id="office" name="office" value="${ccmPatrolSecurity.office}" labelName="office.name" labelValue="${ccmPatrolSecurity.office}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<%--<th>巡逻民警</th>--%>
				<th>任务标题</th>
				<th>警卫时间</th>
				<th>结束时间</th>
				<th>参与单位</th>
				<th>单位人数</th>
				<th>警卫线路</th>
				<th>集合时间</th>
				<th>集合地点</th>
				<th>状态</th>
				<th>审核状态</th>
				<th>更新时间</th>
				<th>描述信息</th>
				<shiro:hasPermission name="security:ccmPatrolSecurity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPatrolSecurity">
			<tr>
				<%--<td><a href="${ctx}/security/ccmPatrolSecurity/form?id=${ccmPatrolSecurity.id}">--%>
					<%--${ccmPatrolSecurity.user.name}--%>
				<%--</a></td>--%>
				<td><a href="${ctx}/security/ccmPatrolSecurity/form?id=${ccmPatrolSecurity.id}">
					${ccmPatrolSecurity.title}
				</a></td>
				<td>
					<fmt:formatDate value="${ccmPatrolSecurity.securityTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolSecurity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmPatrolSecurity.officeName}
				</td>
				<td>
					${ccmPatrolSecurity.numberUnits}
				</td>
				<td>
					${ccmPatrolSecurity.guardLine}
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolSecurity.collectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmPatrolSecurity.collectionPlace}
				</td>
				<td>
					${fns:getDictLabel(ccmPatrolSecurity.status, 'ccm_patrol_missions_status', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmPatrolSecurity.auditingStatus, 'auditing_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolSecurity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmPatrolSecurity.remarks}
				</td>
				<shiro:hasPermission name="security:ccmPatrolSecurity:edit"><td>

						<a onclick="parent.LayerDialog('${ctx}/guard/ccmGuardUnit/securityFormSee?id=${ccmPatrolSecurity.id}', '查看', '1000px', '700px')">查看</a>
						<a onclick="parent.LayerDialog('${ctx}/guard/ccmGuardUnit/securityForm?id=${ccmPatrolSecurity.id}', '安排警卫', '1200px', '700px')">安排警卫</a>
						<a href="${ctx}/guard/ccmGuardUnit?sid=${ccmPatrolSecurity.id}" >查看安排</a>

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>