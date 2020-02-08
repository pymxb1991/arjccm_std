<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>聊天室管理</title>
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
		<li class="active"><a href="${ctx}/chat/Pc">聊天室列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsChatroom"
		action="${ctx}/chat/Pc" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>聊天室：</label> <form:input path="sTitle"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<%-- <li><label>聊天室创建时间：</label> <input name="dtCreatetime"
				type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsChatroom.dtCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</li> --%>
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
				<th>聊天室</th>
				<th>聊天室创建时间</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsChatroom">
				<tr>
					<td><a
						href="${ctx}/chat/Pc/getIntoRoom?roomid=${pbsChatroom.id}">
							${pbsChatroom.STitle} </a></td>
					<td><fmt:formatDate value="${pbsChatroom.dtCreatetime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsChatroom.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a
						href="${ctx}/chat/Pc/getIntoRoom?roomid=${pbsChatroom.id}"
						title="进入房间"><i class="icon-circle-arrow-right"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>