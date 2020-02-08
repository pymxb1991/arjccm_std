<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>远程控制管理</title>
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
<script src="${ctxStatic}/ccm/event/js/ccmEventIncident.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/remote/ccmRemoteControl/">远程控制</a></li>
		<%--注释掉原有添加跳转方式,改为按钮弹出模态框--%>	
		<%-- <shiro:hasPermission name="remote:ccmRemoteControl:edit"><li><a href="${ctx}/remote/ccmRemoteControl/form">远程控制添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmRemoteControl" action="${ctx}/remote/ccmRemoteControl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="equipmentName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>编号：</label>
				<form:input path="equipmentNum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>定位频率：</label>
				<form:input path="equipmentFrequency" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%--注释掉生成的设备状态查询,改为下拉列表样式 --%>	
			<%-- <li><label>设备状态：</label>
				<form:radiobuttons path="equipmentState" items="${fns:getDictList('ccm_remote_equipment_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li><label>状态：</label>
				<form:select path="equipmentState" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_remote_equipment_state')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>登记时间：</label>
				<input
				name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmRemoteControl.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endCreateDate\')||\'%y-%M-%d\'}'});" />
				- <input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmRemoteControl.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'beginCreateDate\')}' ,maxDate:'%y-%M-%d'});" />	
			</li>
			<shiro:hasPermission name="remote:ccmRemoteControl:edit">
				<li class="btns">
					<a onclick="parent.LayerDialog('${ctx}/remote/ccmRemoteControl/form', '添加重点人员设备管理信息', '1100px', '370px')" class="btn btn-success">
						<i class="icon-plus"></i>添加
					</a>
				</li>
			</shiro:hasPermission>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
					<i class="icon-search"></i>查询
				</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;vertical-align:middle;">名称</th>
				<th style="text-align:center;vertical-align:middle;">编号</th>
				<th style="text-align:center;vertical-align:middle;">定位频率</th>
				<th style="text-align:center;vertical-align:middle;">状态</th>
				<th style="text-align:center;vertical-align:middle;">登记时间</th>
				<shiro:hasPermission name="remote:ccmRemoteControl:edit"><th style="text-align:center;vertical-align:middle;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmRemoteControl">
			<tr>
				<td style="text-align:center;vertical-align:middle;">
					${ccmRemoteControl.equipmentName}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${ccmRemoteControl.equipmentNum}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${ccmRemoteControl.equipmentFrequency}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${fns:getDictLabel(ccmRemoteControl.equipmentState, 'ccm_remote_equipment_state', '')}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					<fmt:formatDate value="${ccmRemoteControl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="remote:ccmRemoteControl:edit"><td style="text-align:center;vertical-align:middle;">
					<a class="btnList" title="详情" onclick="parent.LayerDialog('${ctx}/remote/ccmRemoteControl/detail?id=${ccmRemoteControl.id}', '重点人员设备管理信息', '1100px', '370px')"><i class="icon-eye-open"></i></a>
    				<a class="btnList" title="编辑" onclick="parent.LayerDialog('${ctx}/remote/ccmRemoteControl/form?id=${ccmRemoteControl.id}', '编辑重点人员设备管理信息', '1100px', '370px')"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/remote/ccmRemoteControl/close?id=${ccmRemoteControl.id}" onclick="return confirmx('确认要关闭该远程机器吗？', this.href)" title="关机"><i class="icon-off"></i></a>
					<a class="btnList" href="${ctx}/remote/ccmRemoteControl/init?id=${ccmRemoteControl.id}" onclick="return confirmx('确认要开启该远程机器吗？', this.href)" title="初始化"><i class="icon-refresh"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>