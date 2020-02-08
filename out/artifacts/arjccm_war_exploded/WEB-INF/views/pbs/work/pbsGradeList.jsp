<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评分详细列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
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
		<li><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li class="active"><a href="">评分详细列表</a></li>
		</shiro:hasPermission>
	</ul>
	
	<div>
		<div style="color:black">
			评价人员：<span id="pbsActivityrecId">${pbsActivityrec.SMastermem.SName}</span>
		</div>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>人员姓名</th>
					<th>评价等级</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${valueList}" var="values">
					<tr>
						<td>${values.sPartmember.SName}</td>
						<td>${fns:getDictLabel(values.SValue, 'taskvaluetype', '')}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>