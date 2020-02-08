<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>反馈信息管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/alarm.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
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
		<li class="active"><a href="${ctx}/feedback/bphFeedbackInfo/">数据列表</a></li>
		<shiro:hasPermission name="feedback:bphFeedbackInfo:edit"><li><a href="${ctx}/feedback/bphFeedbackInfo/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bphFeedbackInfo" action="${ctx}/feedback/bphFeedbackInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>反馈内容标题：</label>
				<form:input path="feedbackTitle" htmlEscape="false" maxlength="80" class="input-medium"/>
			</li>
			<li><label>反馈内容类型 0 文字 1 图片 2 音频 3 视频：</label>
				<form:checkboxes path="feedbackType" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns">
		<!-- 	<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> -->
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>反馈内容标题</th>
				<th>反馈内容类型 0 文字 1 图片 2 音频 3 视频</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="feedback:bphFeedbackInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bphFeedbackInfo">
			<tr>
				<td><a href="${ctx}/feedback/bphFeedbackInfo/form?id=${bphFeedbackInfo.id}">
					${bphFeedbackInfo.feedbackTitle}
				</a></td>
				<td>
					${fns:getDictLabel(bphFeedbackInfo.feedbackType, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${bphFeedbackInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bphFeedbackInfo.remarks}
				</td>
				<shiro:hasPermission name="feedback:bphFeedbackInfo:edit"><td>
    				<a href="${ctx}/feedback/bphFeedbackInfo/form?id=${bphFeedbackInfo.id}">修改</a>
					<a href="${ctx}/feedback/bphFeedbackInfo/delete?id=${bphFeedbackInfo.id}" onclick="return confirmx('确认要删除该反馈信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>