<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作日历管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
			$( "#updateDetailInfo" ).dialog({
			    autoOpen: false,
			    closeOnEscape: false,
			    height: 500,
			    width: 1100,
			    modal: true,
			    close: function() {
			    	$( this ).dialog( "close" );
			    	//$("#incomingEntry")[0].click();
			    }
			  });
			$("a[title='updateDetail']").on("click",function(){
				$("#updateDetailInfo").attr("src",this);
				$("#updateDetailInfo").dialog( "open" );
				$("#updateDetailInfo").css({"width":"98%"});
				return false;
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
	
	<form:form id="searchForm" modelAttribute="plmCalendar" action="${ctx}/calendar/plmCalendar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日程标题：</label>
				<form:input path="subject" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			
			<li class="btns"><a id="btnSubmit" class="btn btn-primary"><i class="icon-search"></i>查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日程标题</th>
				<th>开始日期</th>
				<th>开始时间</th>
				<th>结束日期</th>
				<th>结束时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="calendar:plmCalendar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmCalendar">
			<tr>
				<td>	<a title='updateDetail' href="${ctx}/calendar/plmCalendar/form?id=${plmCalendar.id}">
					${plmCalendar.subject}
				</a></td>
				<td>
					<fmt:formatDate value="${plmCalendar.beginDate}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${plmCalendar.beginTime}" pattern=" HH:mm"/>
				</td>
				<td>
					<fmt:formatDate value="${plmCalendar.endDate}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${plmCalendar.endTime}" pattern=" HH:mm"/>
				</td>
				<td>
					<fmt:formatDate value="${plmCalendar.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${plmCalendar.remarks}
				</td>
				<shiro:hasPermission name="calendar:plmCalendar:edit"><td>
    				<a title='updateDetail' href="${ctx}/calendar/plmCalendar/form?id=${plmCalendar.id}"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/calendar/plmCalendar/delete?id=${plmCalendar.id}" onclick="return confirmx('确认要删除该工作日历吗？', this.href)"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<iframe id="updateDetailInfo" src=""></iframe>
</body>
</html>