<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差申请表单管理</title>
	<meta name="decorator" content="default"/>
	<!-- 列表缩略图切换 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link
		href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
		rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
	<!-- /列表缩略图切换 -->
	<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSubmit").on("click", function() {
				var begin = new Date(Date.parse($("[name='beginFldDt']").val()));
			    var end = new Date(Date.parse($("[name='endFldDt']").val()));
			    var begin2 = new Date(Date.parse($("[name='beginFldBdt']").val()));
			    var end2 = new Date(Date.parse($("[name='endFldBdt']").val()));
			    var begin3 = new Date(Date.parse($("[name='beginFldEdt']").val()));
			    var end3 = new Date(Date.parse($("[name='endFldEdt']").val()));
			    if(begin.getTime() > end.getTime()){
			    	messageAlert("申请日期填写错误！", "error");
			    	return false;
			    }
			    if(begin2.getTime() > end2.getTime()){
			    	messageAlert("开始时间填写错误！", "error");
			    	return false;
			    }
			    if(begin3.getTime() > end3.getTime()){
			    	messageAlert("返回时间填写错误！", "error");
			    	return false;
			    }
			    $("#searchForm").submit();
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
	<form:form id="searchForm" modelAttribute="plmTravelManage" action="${ctx}/travel/plmTravelManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>目的地：</label>
				<form:input path="fldDest" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>			
			
			<li><label>开始时间：</label>
				<input name="beginFldBdt" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmTravelManage.beginFldBdt}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endFldBdt" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmTravelManage.endFldBdt}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			
			<li><label>返回时间：</label>
				<input name="beginFldEdt" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmTravelManage.beginFldEdt}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endFldEdt" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmTravelManage.endFldEdt}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<div id="prodInfo_List">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主题</th>
				<th>目的地</th>
				<th>申请人</th>
				<th>申请日期</th>
				<th>部门</th>
				<th>出发时间</th>
				<th>部门负责人</th>
				<th>返回时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmTravelManage">
			<tr>
				<td><a href="${ctx}/travel/plmTravelManage/form?id=${plmTravelManage.id}">
					${plmTravelManage.fldSubject}
				</a></td>
				<td>
					${plmTravelManage.fldDest}
				</td>
				<td>
					${plmTravelManage.fldApplicant.name}
				</td>
				<td>
					<fmt:formatDate value="${plmTravelManage.fldDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmTravelManage.fldDept.name}
				</td>
				<td>
					<fmt:formatDate value="${plmTravelManage.fldBdt}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmTravelManage.departmentHead.name}
				</td>
				<td>
					<fmt:formatDate value="${plmTravelManage.fldEdt}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${not empty plmTravelManage.procInsId}" var="e">
								
									<a class="btnList" href="${ctx}/travel/plmTravelManage/form?id=${plmTravelManage.id}"
										title="显示详情"><i class="icon-file"></i></a>
							
							</c:if>
							<c:if test="${!e}">
								
									<a  class="btnList" href="${ctx}/travel/plmTravelManage/form?id=${plmTravelManage.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/travel/plmTravelManage/delete?id=${plmTravelManage.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
							
							</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmTravelManage">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/travel/plmTravelManage/form?id=${plmTravelManage.id}">
							<h4 title="${plmTravelManage.fldSubject}">主题：${plmTravelManage.fldSubject}</h4>
						</a>
						<div class="caption row-fluid">


							<div class="spantext  " style="width: 86%; margin-left: 7%">
								<p title="${plmTravelManage.fldDest}">目的地：${plmTravelManage.fldDest}</p>
								<p title="${plmTravelManage.fldApplicant.name}">申请人：${plmTravelManage.fldApplicant.name}</p>
								<%-- <p title="<fmt:formatDate value="${plmTravelManage.fldBdt}" pattern='yyyy-MM-dd  HH:mm:ss'/>">
									出发时间：
									<fmt:formatDate value="${plmTravelManage.fldBdt}"
										pattern="yyyy-MM-dd  HH:mm:ss" />
								</p>
								<p title="<fmt:formatDate value="${plmTravelManage.fldEdt}" pattern='yyyy-MM-dd  HH:mm:ss'/>">
									返回时间：
									<fmt:formatDate value="${plmTravelManage.fldEdt}"
										pattern="yyyy-MM-dd  HH:mm:ss" />
								</p> --%>
								<p title="${plmTravelManage.departmentHead.name}">部门负责人：${plmTravelManage.departmentHead.name}</p>
							</div>

						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmTravelManage.procInsId}" var="e">
								
									<a class="btnList" href="${ctx}/travel/plmTravelManage/form?id=${plmTravelManage.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!e}">
															<a  class="btnList" href="${ctx}/travel/plmTravelManage/form?id=${plmTravelManage.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/travel/plmTravelManage/delete?id=${plmTravelManage.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
								
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>