<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息管理管理</title>
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
		<%-- <li class="active"><a href="${ctx}/message/ccmMessageManage/">消息管理列表</a></li> --%>
		<%-- <shiro:hasPermission name="message:ccmMessageManage:edit"><li><a href="${ctx}/message/ccmMessageManage/form">消息管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmMessageManage" action="${ctx}/message/ccmMessageManage/getlist?id=${id}&num=${num}" method="post" class="breadcrumb form-search" style="margin-top:10px;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息：</label>
				<form:input path="message" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>发送时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmMessageManage.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmMessageManage.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th style="text-align:center;vertical-align:middle;">消息</th>
				<th style="text-align:center;vertical-align:middle;">发送状态</th>
				<th style="text-align:center;vertical-align:middle;">发送时间</th>
				<shiro:hasPermission name="message:ccmMessageManage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmMessageManage">
			<tr>
				<td style="text-align:center;vertical-align:middle;">
					${ccmMessageManage.message}
				</a></td>
				<td style="text-align:center;vertical-align:middle;">
					${fns:getDictLabel(ccmMessageManage.sendState, 'ccm_message_send_state', '')}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					<fmt:formatDate value="${ccmMessageManage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="message:ccmMessageManage:edit"><td>
    				<a href="${ctx}/message/ccmMessageManage/send?id=${ccmMessageManage.id}&remoteid=${id}&num=${num}" onclick="return confirmx('确认要发送该消息吗？', this.href)" title="发送消息"><i class="icon-upload"></i></a>
					<a href="${ctx}/message/ccmMessageManage/delete?id=${ccmMessageManage.id}&remoteid=${id}&num=${num}" onclick="return confirmx('确认要删除该消息吗？', this.href)" title="删除消息"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>