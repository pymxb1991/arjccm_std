<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>请求登记管理</title>
<meta name="decorator" content="default" />
	<script charset="utf-8" type="text/javascript"
    src="${ctxStatic}/ccm/validator/validatorBaseList.js"></script> 
    <script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
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
<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/event/ccmEventRequest/">请求列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmEventRequest"
		action="${ctx}/event/ccmEventRequest/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>请求名称：</label> <form:input path="caseName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>发生日期：</label> <input name="beginHappenDate" id="beginHappenDate"
				type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmEventRequest.beginHappenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endHappenDate\')||\'%y-%M-%d\'}'});" />
				- <input name="endHappenDate" type="text" readonly="readonly"  id="endHappenDate"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmEventRequest.endHappenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'beginHappenDate\')}' ,maxDate:'%y-%M-%d'});" />
			</li>
			<li><label>处理状态：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>请求地点：</label> <sys:treeselect id="area"
					name="area.id" value="${ccmEventRequest.area.id}"
					labelName="area.name" labelValue="${ccmEventRequest.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li> -->
				<shiro:hasPermission name="event:ccmEventRequest:edit">
				<li class="btns"><a
					onclick="parent.LayerDialog('${ctx}/event/ccmEventRequest/form', '添加', '1100px', '700px')"
					class="btn btn-success"><i class="icon-plus"></i> 添加</a></li>
			</shiro:hasPermission>
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>请求名称</th>
				<th>发生日期</th>
				<th>请求地点</th>
				<th>处理状态</th>
				<th>请求人员</th>
				<shiro:hasPermission name="event:ccmEventRequest:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmEventRequest">
				<tr>
						
					<td><a onclick="parent.LayerDialog('${ctx}/event/ccmEventRequest/form?id=${ccmEventRequest.id}','编辑', '1100px', '700px')">
							${ccmEventRequest.caseName} </a></td>
					<td><fmt:formatDate value="${ccmEventRequest.happenDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${ccmEventRequest.area.name}</td>
					<td>
					<span class="eventScaleCss statusCss-${ccmEventRequest.type}">${fns:getDictLabel(ccmEventRequest.type, 'ccm_event_status', '')}
					</span>
					</td>
					<td>${ccmEventRequest.createName}</td>
					<td><shiro:hasPermission name="event:ccmEventRequest:edit">
							<a	class="btnList"
								onclick="parent.LayerDialog('${ctx}/event/ccmEventRequest/form?id=${ccmEventRequest.id}','编辑', '1100px', '700px')"><i class="icon-pencil"></i></a>
							<a	class="btnList"
								href="${ctx}/event/ccmEventRequest/delete?id=${ccmEventRequest.id}"
								onclick="return confirmx('确认要删除该请求登记吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
										<a class="btnList" href="javascript:;" onclick="LocationOpen('${ccmEventRequest.id}')"  title="位置信息"><i class="icon-map-marker "></i></a>
					
						</shiro:hasPermission> 
						<!-- 事件处理 编辑权限  --> 
						<shiro:hasPermission name="event:ccmEventCasedeal:edit">
							<%-- <a	class="btnList"
								href="${ctx}/event/ccmEventRequestdeal/dealform?eventRequestId=${ccmEventRequest.id}" title="添加处理信息"><i class="icon-plus"></i></a>
							 --%><a class="btnList" onclick="LayerDialogWithReload('${ctx}/event/ccmEventCasedeal/dealformCommon?objType=ccm_event_request&objId=${ccmEventRequest.id}', '处理', '700px', '500px')" title="添加处理"><i class="icon-plus"></i></a>
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>