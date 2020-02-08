<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信回复管理</title>
	<meta name="decorator" content="default"/>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/event/wechat/ccmWechatEventReply/">微信回复列表</a></li>
		<%-- <shiro:hasPermission name="event:wechat:ccmWechatEventReply:edit"><li><a href="${ctx}/event/wechat/ccmWechatEventReply/form">微信回复添加</a></li></shiro:hasPermission>
	 --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWechatEventReply" action="${ctx}/event/wechat/ccmWechatEventReply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>上报详情：</label>
				<form:input path="event.reportInfo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>回复人员：</label>
				<form:input path="createBy.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
			<li><label>回复时间：</label>
				<input name="beginReplyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWechatEventReply.beginReplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endReplyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWechatEventReply.endReplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>消息回复状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_wechat_event_reply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>上报详情</th>
				<th>回复信息</th>
				<th>回复人员</th>
				<th>回复时间</th>
				<th>消息回复状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="event:wechat:ccmWechatEventReply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWechatEventReply">
			<tr>
				<td>${fns:abbr(ccmWechatEventReply.event.reportInfo,30)}
				</td>
				<td><a href="${ctx}/event/wechat/ccmWechatEventReply/form?id=${ccmWechatEventReply.id}">
					${fns:abbr(ccmWechatEventReply.replyInfo,30)}
				</a></td>
				<td>
					${ccmWechatEventReply.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${ccmWechatEventReply.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(ccmWechatEventReply.status, 'ccm_wechat_event_reply_status', '')}
				</td>
				
				<td>
					<fmt:formatDate value="${ccmWechatEventReply.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="event:wechat:ccmWechatEventReply:edit"><td>
    				<a class="btnList" href="${ctx}/event/wechat/ccmWechatEventReply/form?id=${ccmWechatEventReply.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/event/wechat/ccmWechatEventReply/delete?id=${ccmWechatEventReply.id}" onclick="return confirmx('确认要删除该微信信息回复吗？', this.href)"title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>