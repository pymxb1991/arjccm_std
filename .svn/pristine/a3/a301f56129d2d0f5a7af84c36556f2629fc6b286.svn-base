<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息管理</title>
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
		<li class="active"><a href="${ctx}/message/ccmMessageManage/tolist">消息管理</a></li>
		<%--注释掉原有添加跳转方式,改为按钮弹出模态框--%>	
		<%-- <shiro:hasPermission name="remote:ccmRemoteControl:edit"><li><a href="${ctx}/remote/ccmRemoteControl/form">远程控制添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmRemoteControl" action="${ctx}/message/ccmMessageManage/tolist" method="post" class="breadcrumb form-search">
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
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmRemoteControl.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endCreateDate\')||\'%y-%M-%d\'}'});"/> - 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmRemoteControl.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'beginCreateDate\')}' ,maxDate:'%y-%M-%d'});" />	
			</li>
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
				<shiro:hasPermission name="message:ccmMessageManage:edit"><th style="text-align:center;vertical-align:middle;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmRemoteControl">
			<tr>
				<td style="text-align:center;vertical-align:middle;">
					${ccmRemoteControl.equipmentName}
				</a></td>
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
				<shiro:hasPermission name="message:ccmMessageManage:edit">
					<td style="text-align:center;vertical-align:middle;">
						<a class="btnList" title="添加消息"  onclick="parent.LayerDialog('${ctx}/message/ccmMessageManage/form?equipmentId=${ccmRemoteControl.id}&equipmentNum=${ccmRemoteControl.equipmentNum}', '${ccmRemoteControl.equipmentName}添加发送消息', '1100px', '700px')"><i class="icon-plus"></i></a>
						<a class="btnList" title="消息记录"  onclick="parent.LayerDialog('${ctx}/message/ccmMessageManage/getlist?id=${ccmRemoteControl.id}&num=${ccmRemoteControl.equipmentNum}', '${ccmRemoteControl.equipmentName}发送消息列表', '1100px', '700px')"><i class="icon-file"></i></a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>