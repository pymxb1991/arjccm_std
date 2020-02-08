<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>投票主题信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(function() {
    // 导出
    $(".btnExport").click(function() {
      var topicid = $(this).attr("topid");
      console.log(topicid);
      $("#attrid").val(topicid);
      top.$.jBox.confirm("确认要导出投票数据吗？", "系统提示", function(v, h, f) {
        if (v == "ok") {
          $("#searchForm").attr("action", "${ctx}/vote/pbsVoteTopic/export");
          $("#searchForm").submit();
          $("#attrid").val("");
          $("#searchForm").attr("action", "${ctx}/vote/pbsVoteTopic/");
        }
      }, {
        buttonsFocus : 1
      });
      top.$('.jbox-body .jbox-icon').css('top', '55px');

    });
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
		<li class="active"><a href="${ctx}/vote/pbsVoteTopic/">投票主题信息列表</a></li>
		<shiro:hasPermission name="vote:pbsVoteTopic:edit">
			<li><a href="${ctx}/vote/pbsVoteTopic/form">投票主题信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsVoteTopic"
		action="${ctx}/vote/pbsVoteTopic/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="attrid" name="id" type="hidden" value="" />
		<input id="sBelongfunc" name="sBelongfunc" type="hidden" value="0" />
		<ul class="ul-form">
			<li><label>标题名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>是否关闭：</label> <form:select path="sClose"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
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
				<th>标题名称</th>
				<th>是否发布</th>
				<th>是否关闭</th>
				<th>投票开始时间</th>
				<th>投票结束时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="vote:pbsVoteTopic:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsVoteTopic">
				<tr>
					<td><a
						href="${ctx}/vote/pbsVoteTopic/form?id=${pbsVoteTopic.id}">
							${pbsVoteTopic.SName} </a></td>
					<td>${fns:getDictLabel(pbsVoteTopic.sStat, 'yes_no', '否')}</td>
					<td>${fns:getDictLabel(pbsVoteTopic.SClose, 'yes_no', '否')}</td>
					<td><fmt:formatDate value="${pbsVoteTopic.dtStart}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsVoteTopic.dtEnd}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsVoteTopic.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="vote:pbsVoteTopic:edit">
						<td><a
							href="${ctx}/vote/pbsVoteTopic/handleform?id=${pbsVoteTopic.id}&sStat=1&sBelongfunc=0" title = "发布"><i class="icon-fankui"></i></a>
							<a
							href="${ctx}/vote/pbsVoteTopic/handleform?id=${pbsVoteTopic.id}&sStat=0&sBelongfunc=0" title = "撤销"><i class="icon-reply"></i></a>
							<a href="${ctx}/vote/pbsVoteTopic/form?id=${pbsVoteTopic.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a href="${ctx}/vote/pbsVoteTopic/delete?id=${pbsVoteTopic.id}"
							onclick="return confirmx('确认要删除该投票主题信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a> <a
							class="btnExport" topid="${pbsVoteTopic.id}" sBelongfunc="1" title = "导出"><i class="icon-download-alt"></i></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>