<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待上传上级平台记录管理</title>
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
		<li class="active"><a href="${ctx}/ccmsys/ccmUploadLog/">数据列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmUploadLog" action="${ctx}/ccmsys/ccmUploadLog/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>所在表：</label>
				<form:input path="tableName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>操作类型：</label>
				<form:select path="operateType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_upload_log_operate_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>上传状态：</label>
				<form:select path="uploadStatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_upload_log_upload_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>更新开始时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmUploadLog.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></li>
			<li class="first-line"><label>更新结束时间：</label>
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmUploadLog.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="second-line"><label>备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

<%--			<li class="clearfix"></li>--%>
		</ul>
		<div class="clearfix pull-right btn-box">
			<!-- 	<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> -->
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
				<i class="icon-search"></i> 查询 </a>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所在表</th>
				<th>记录id</th>
				<th>操作类型</th>
				<th>上传状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="ccmsys:ccmUploadLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmUploadLog">
			<tr>
				<td><a href="${ctx}/ccmsys/ccmUploadLog/form?id=${ccmUploadLog.id}">
					${ccmUploadLog.tableName}
				</a></td>
				<td>
					${ccmUploadLog.dataId}
				</td>
				<td>
					${fns:getDictLabel(ccmUploadLog.operateType, 'ccm_upload_log_operate_type', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmUploadLog.uploadStatus, 'ccm_upload_log_upload_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmUploadLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmUploadLog.remarks}
				</td>
				<shiro:hasPermission name="ccmsys:ccmUploadLog:edit"><td>
					<c:if test="${ccmUploadLog.uploadStatus eq '3' }">
    					<a class="btnList" href="${ctx}/ccmsys/ccmUploadLog/form?id=${ccmUploadLog.id}" title="修改"><i class="icon-pencil"></i></a>
					</c:if>
					<a class="btnList" href="${ctx}/ccmsys/ccmUploadLog/delete?id=${ccmUploadLog.id}" onclick="return confirmx('确认要删除该待上传上级平台记录吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination"style="float: right; margin-top: 12px">${page}</div>
</body>
</html>