<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>布控管理</title>
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
		<li class="active"><a href="${ctx}/device/ccmDeviceControl?controlBy=${controlBy}">
			<c:choose>
				<c:when test="${controlBy==1}">
					探针布控列表
				</c:when>
				<c:when test="${controlBy==2}">
					RFID布控列表
				</c:when>
				<c:when test="${controlBy==3}">
					人员预警列表
				</c:when>
				<c:when test="${controlBy==4}">
					手机布控列表
				</c:when>
				<c:otherwise>
					布控列表
				</c:otherwise>
			</c:choose>
			</a></li>
<%--		<shiro:hasPermission name="device:ccmDeviceControl:edit"><li><a href="${ctx}/device/ccmDeviceControl/form">探针布控添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmDeviceControl" action="${ctx}/device/ccmDeviceControl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="controlBy" name="controlBy" type="hidden" value="${ccmDeviceControl.controlBy}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
<%--			<li><label>人员类型：</label>--%>
<%--				<form:input path="peopleType" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
<%--			</li>--%>
			<li><label>布控类型：</label>
				<form:select path="controlType" class="input-large">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('ccm_people_controller')}"
								  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
<%--			<li><label>布控开始时间：</label>--%>
<%--				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
<%--					value="<fmt:formatDate value="${ccmDeviceControl.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--			</li>--%>
<%--			<li><label>布控结束时间：</label>--%>
<%--				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
<%--					value="<fmt:formatDate value="${ccmDeviceControl.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--			</li>--%>
			<li><label>布控等级：</label>
<%--				<form:input path="grade" htmlEscape="false" maxlength="2" class="input-medium"/>--%>
				<form:select path="grade" class="input-large">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('ccm_control_level')}"
								  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><a
					onclick="parent.LayerDialog('${ctx}/device/ccmDeviceControl/form?controlBy=${controlBy}', '添加', '1100px', '700px')"
					class="btn btn-success"><i class="icon-plus"></i> 添加</a></li>
			<li class="btns"><a href="javascript:;" id="btnSubmit"
								class="btn btn-primary"> <i class="icon-search"></i> 查询
			</a></li>
<%--			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>身份证</th>
				<th>人员类型</th>
<%--				<th>布控开始时间</th>--%>
<%--				<th>布控结束时间</th>--%>
				<th>布控原因</th>
				<th>创建时间</th>
<%--				<th>备注信息</th>--%>
				<shiro:hasPermission name="device:ccmDeviceControl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmDeviceControl">
			<tr>
				<td>
<%--					<a href="${ctx}/device/ccmDeviceControl/form?id=${ccmDeviceControl.id}">--%>
					${ccmDeviceControl.name}
<%--				</a>--%>
				</td>
				<td>
					${fns:getDictLabel(ccmDeviceControl.sex, 'sex', '')}
				</td>
				<td>
					${ccmDeviceControl.idCard}
				</td>
				<td>
					${ccmDeviceControl.peopleType}
				</td>
<%--				<td>--%>
<%--					<fmt:formatDate value="${ccmDeviceControl.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<fmt:formatDate value="${ccmDeviceControl.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--				</td>--%>
				<td>
					${ccmDeviceControl.reason}
				</td>
				<td>
					<fmt:formatDate value="${ccmDeviceControl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
<%--				<td>--%>
<%--					${ccmDeviceControl.remarks}--%>
<%--				</td>--%>
				<shiro:hasPermission name="device:ccmDeviceControl:edit"><td>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/device/ccmDeviceControl/form?id=${ccmDeviceControl.id}&hide1=true', '详情', '1200px', '800px')" title="详情"><i class="icon-list-alt"></i></a>
<%--    				<a href="${ctx}/device/ccmDeviceControl/form?id=${ccmDeviceControl.id}">修改</a>--%>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/device/ccmDeviceControl/earlyWarningList?idCard=${ccmDeviceControl.idCard}&controlBy=${ccmDeviceControl.controlBy}', '预警列表', '1200px', '500px')" title="预警列表"><i class="icon-list-ul"></i></a>
<%--					<a href="${ctx}/device/ccmDeviceControl/delete?id=${ccmDeviceControl.id}" onclick="return confirmx('确认要删除该探针布控吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>