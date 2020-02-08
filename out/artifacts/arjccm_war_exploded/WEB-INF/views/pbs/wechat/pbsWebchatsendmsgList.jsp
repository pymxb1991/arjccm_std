<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>微信消息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(function() {

  });
  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/wechat/pbsWebchatsendmsg/">微信消息列表</a></li>
		<shiro:hasPermission name="wechat:pbsWebchatsendmsg:edit">
			<li><a href="${ctx}/wechat/pbsWebchatsendmsg/form">微信消息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsWebchatsendmsg"
		action="${ctx}/wechat/pbsWebchatsendmsg/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>消息发送内容：</label> <form:input path="sSenddata"
					htmlEscape="false" class="input-medium" /></li>
			<li><label>学员：</label> <sys:treeselect id="sCreatemem"
					name="sCreatemem" value="${pbsWebchatsendmsg.sCreatemem.id}"
					labelName="" labelValue="${pbsWebchatsendmsg.sCreatemem.SName}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>消息发送内容</th>
				<th>发送消息类型</th>
				<th>学员姓名</th>
				<th>发送状态</th>
				<th>发送返回信息</th>
				<th>发送时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="wechat:pbsWebchatsendmsg:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsWebchatsendmsg">
				<tr>
					<td><a href="${ctx}/wechat/pbsWebchatsendmsg/form?id=${pbsWebchatsendmsg.id}">
						${pbsWebchatsendmsg.SSenddata}</a></td>
					<td>${fns:getDictLabel(pbsWebchatsendmsg.SMsgtype, 'wxfiletype', '')}</td>
					<td>${pbsWebchatsendmsg.sCreatemem.SName}</td>
					<td>${fns:getDictLabel(pbsWebchatsendmsg.sStat, 'wxsendtype', '')}</td>
					<td>${fns:getDictLabel(pbsWebchatsendmsg.SRetcode, 'wxerrorcode', '')}</td>
					<td><fmt:formatDate value="${pbsWebchatsendmsg.dtSendtime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsWebchatsendmsg.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="wechat:pbsWebchatsendmsg:edit">
						<td><a
							href="${ctx}/wechat/pbsWebchatsendmsg/form?id=${pbsWebchatsendmsg.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/wechat/pbsWebchatsendmsg/delete?id=${pbsWebchatsendmsg.id}"
							onclick="return confirmx('确认要删除该微信消息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a> <a
							href="${ctx}/wechat/sendmessage?id=${pbsWebchatsendmsg.id}"
							onclick="return confirmx('一个月只能发送4次群发消息,确认要发布该微信消息吗？', this.href)" title = "发布"><i class="icon-fankui"></i></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>

