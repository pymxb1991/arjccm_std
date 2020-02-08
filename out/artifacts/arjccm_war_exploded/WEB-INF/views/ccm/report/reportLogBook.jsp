<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日常工作数据统计分析</title>
	<meta name="decorator" content="default"/>
	<style>
	.common-pading{
	  width:100%;
	  height:200px;
	  padding:5px;
	}
	.echarts{
	  width:100%;
	  height:100%;
	}
	
	
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
			$("#btnExport").click(
				function() {
					top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
						if (v == "ok") {
							// 借用导出action
							$("#searchForm").attr("action", "${ctx}/report/ccmReportOthers/export");
							$("#searchForm").submit();
							// 还原查询action 
							$("#searchForm").attr("action", "${ctx}/report/ccmReportOthers/logBook");
						}
					}, {
						buttonsFocus : 1
					});
					top.$('.jbox-body .jbox-icon').css('top', '55px');
				});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/ccmReportOthers/logBook">日常工作数据报表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkReportCount" action="${ctx}/report/ccmReportOthers/logBook" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${office.id}" labelName="office.name" labelValue="${office.name}"
					title="部门" url="/view/vCcmTeam/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"  cssStyle="width: 150px"/>
			</li>
			<li><label>用户类型：</label> <form:select path="user.userType" class="input-medium">
				<form:option value="" label="全部" />
				<form:options items="${fns:getDictList('sys_user_type')}"
					itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select></li>
			<li><label>时间段：</label> <input name="beginDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmWorkReportCount.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				- <input name="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmWorkReportCount.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</li>
			<li class="btns">
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出" />  -->
					<a href="javascript:;" id="btnExport" class="btn btn-export"> 
						<i class=" icon-reply"></i> 导出
					</a>
				<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /> -->
					<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	     <div class="row-fluid">
		      <div class="span12" >
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>姓名</th>
							<th>人员类型</th>
							<th>所属部门</th>
							<th>日报数</th>
							<th>上报案事件数</th>
							<th>上报矛盾纠纷数</th>
							<th>上报请求数</th>
							<th>事件处理数</th>
							<th>事件考核得分</th>
							<th>人口更新数</th>
							<th>特殊人员跟进次数</th>
							<th>房屋楼栋跟进次数</th>
							<th>重点场所跟进次数</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${logBookList}" var="ccmWorkReportCount">
							<tr>
								<td>${ccmWorkReportCount.user.name}</td>
								<td>${fns:getDictLabel(ccmWorkReportCount.user.userType, 'sys_user_type', '')}</td>
								<td>${ccmWorkReportCount.office.name}</td>
								<td>${ccmWorkReportCount.reportCount}</td>
								<td>${ccmWorkReportCount.eventIncidentCount}</td>
								<td>${ccmWorkReportCount.eventAmbiCount}</td>
								<td>${ccmWorkReportCount.eventRequestCount}</td>
								<td>${ccmWorkReportCount.eventDealCount}</td>
								<td>${ccmWorkReportCount.eventDealScore}</td>
								<td>${ccmWorkReportCount.popUpdateCount}</td>
								<td>${ccmWorkReportCount.popSpecialTailCount}</td>
								<td>${ccmWorkReportCount.houseTailCount}</td>
								<td>${ccmWorkReportCount.orgTailCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	    </div>

</body>
</html>