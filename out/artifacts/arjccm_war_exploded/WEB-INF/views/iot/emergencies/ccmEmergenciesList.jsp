<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>突发事件管理</title>
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
		<li class="active"><a href="${ctx}/emergencies/ccmEarlyWarning/">突发事件列表</a></li>
<%--		<shiro:hasPermission name="emergencies:ccmEmergencies:edit"><li><a href="${ctx}/emergencies/ccmEmergencies/form">突发事件添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmEarlyWarning" action="${ctx}/emergencies/ccmEmergencies/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
<%--			<li><label>性别：</label>--%>
<%--				<form:input path="sex" htmlEscape="false" maxlength="2" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>年龄：</label>--%>
<%--				<form:input path="age" htmlEscape="false" maxlength="3" class="input-medium"/>--%>
<%--			</li>--%>
			<li><label>身份证：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
<%--			<li><label>人员类型：</label>--%>
<%--				<form:input path="peopleType" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>预警设备：</label>--%>
<%--				<form:input path="alarmFacility" htmlEscape="false" maxlength="20" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>地址：</label>--%>
<%--				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>预警时间：</label>--%>
<%--				<input name="alarmDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
<%--					value="<fmt:formatDate value="${ccmEarlyWarning.alarmDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--			</li>--%>
<%--			<li><label>是否核实：</label>--%>
<%--				<form:input path="isCheck" htmlEscape="false" maxlength="2" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>是否为重点人员：</label>--%>
<%--				<form:input path="peopleEmphasis" htmlEscape="false" maxlength="2" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>MAC地址：</label>--%>
<%--				<form:input path="macAddress" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>RFID：</label>--%>
<%--				<form:input path="rfid" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>IMEI：</label>--%>
<%--				<form:input path="imei" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>手机：</label>--%>
<%--				<form:input path="phone" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>创建时间：</label>--%>
<%--				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
<%--					value="<fmt:formatDate value="${ccmEarlyWarning.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--			</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
<%--				<th>性别</th>--%>
<%--				<th>年龄</th>--%>
				<th>身份证</th>
				<th>特殊类型</th>
<%--				<th>人员类型</th>--%>
				<th>预警设备</th>
				<th>位置</th>
				<th>状态</th>
				<th>预警时间</th>
<%--				<th>是否核实</th>--%>
				<th>图片</th>
<%--				<th>是否为重点人员</th>--%>
<%--				<th>MAC地址</th>--%>
<%--				<th>RFID</th>--%>
<%--				<th>IMEI</th>--%>
<%--				<th>手机</th>--%>
<%--				<th>创建时间</th>--%>
<%--				<th>更新时间</th>--%>
<%--				<th>备注信息</th>--%>
				<shiro:hasPermission name="emergencies:ccmEmergencies:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmEarlyWarning">
			<tr>
				<td>
<%--					<a href="${ctx}/emergencies/ccmEmergencies/form?id=${ccmEarlyWarning.id}">--%>
					${ccmEarlyWarning.name}
<%--				</a>--%>
				</td>
<%--				<td>--%>
<%--					${ccmEarlyWarning.sex}--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					${ccmEarlyWarning.age}--%>
<%--				</td>--%>
				<td>
					${ccmEarlyWarning.idCard}
				</td>
				<td>
					${ccmEarlyWarning.specialType}
				</td>
<%--				<td>--%>
<%--					${ccmEarlyWarning.peopleType}--%>
<%--				</td>--%>
				<td>
					${ccmEarlyWarning.alarmFacility}
				</td>
				<td>
					${ccmEarlyWarning.address}
				</td>
				<td>
					${ccmEarlyWarning.type}
				</td>
				<td>
					<fmt:formatDate value="${ccmEarlyWarning.alarmDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
<%--				<td>--%>
<%--					${ccmEarlyWarning.isCheck}--%>
<%--				</td>--%>
				<td>
					${ccmEarlyWarning.img}
				</td>
<%--				<td>--%>
<%--					${ccmEarlyWarning.peopleEmphasis}--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					${ccmEarlyWarning.macAddress}--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					${ccmEarlyWarning.rfid}--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					${ccmEarlyWarning.imei}--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					${ccmEarlyWarning.phone}--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<fmt:formatDate value="${ccmEarlyWarning.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<fmt:formatDate value="${ccmEarlyWarning.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					${ccmEarlyWarning.remarks}--%>
<%--				</td>--%>
				<shiro:hasPermission name="emergencies:ccmEmergencies:edit"><td>
<%--    				<a href="${ctx}/emergencies/ccmEmergencies/form?id=${ccmEarlyWarning.id}">修改</a>--%>
					<a class="btnList" href="${ctx}/emergencies/ccmEmergencies/delete?id=${ccmEarlyWarning.id}" onclick="return confirmx('确认要删除该突发事件吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>