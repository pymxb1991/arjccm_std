<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分规则定义管理</title>
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
		<li class="active"><a href="${ctx}/question/pbsQuestionRuleitem/">评分规则定义列表</a></li>
		<shiro:hasPermission name="question:pbsQuestionRuleitem:edit"><li><a href="${ctx}/question/pbsQuestionRuleitem/form">评分规则定义添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsQuestionRuleitem" action="${ctx}/question/pbsQuestionRuleitem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评分规则名称：</label>
				<form:input path="sparentid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>试题类型：</label>
				<form:input path="stype" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>对比方式：</label>
				<form:input path="scompare" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>判断代码语言：</label>
				<form:input path="scodetype" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>评分规则名称</th>
				<th>试题类型</th>
				<th>对比方式</th>
				<th>判断代码语言</th>
				<th>脚本内容</th>
				<th>判断值</th>
				<th>赋分比例</th>
				<th>展示的图片</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="question:pbsQuestionRuleitem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsQuestionRuleitem">
			<tr>
				<td><a href="${ctx}/question/pbsQuestionRuleitem/form?id=${pbsQuestionRuleitem.id}">
					${pbsQuestionRuleitem.sparentid}
				</a></td>
				<td>
					${pbsQuestionRuleitem.stype}
				</td>
				<td>
					${pbsQuestionRuleitem.scompare}
				</td>
				<td>
					${pbsQuestionRuleitem.scodetype}
				</td>
				<td>
					${pbsQuestionRuleitem.scodecontent}
				</td>
				<td>
					${pbsQuestionRuleitem.sjudgeval}
				</td>
				<td>
					${pbsQuestionRuleitem.ival}
				</td>
				<td width="100px">
					<img src="${pbsQuestionRuleitem.surl}" style="height:50px;"/>
				</td>
				<td>
					<fmt:formatDate value="${pbsQuestionRuleitem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pbsQuestionRuleitem.remarks}
				</td>
				<shiro:hasPermission name="question:pbsQuestionRuleitem:edit"><td>
    				<a href="${ctx}/question/pbsQuestionRuleitem/form?id=${pbsQuestionRuleitem.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/question/pbsQuestionRuleitem/delete?id=${pbsQuestionRuleitem.id}" onclick="return confirmx('确认要删除该评分规则定义吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>