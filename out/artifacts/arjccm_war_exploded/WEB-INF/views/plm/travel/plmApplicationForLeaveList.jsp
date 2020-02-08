<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假申请管理</title>
	<meta name="decorator" content="default"/>
	<link
		href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
		rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
	<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSubmit").on("click", function() {
				var begin = new Date(Date.parse($("[name='beginFromDate']").val()));
			    var end = new Date(Date.parse($("[name='endFromDate']").val()));
			    var begin2 = new Date(Date.parse($("[name='beginToDate']").val()));
			    var end2 = new Date(Date.parse($("[name='endToDate']").val()));
			    if(begin.getTime() > end.getTime()){
			    	messageAlert("开始时间填写错误！", "error");
			    	return false;
			    }
			    if(begin2.getTime() > end2.getTime()){
			    	messageAlert("结束时间填写错误！", "error");
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
	<form:form id="searchForm" modelAttribute="plmApplicationForLeave" action="${ctx}/travel/plmApplicationForLeave/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>申请人：</label>
				<sys:treeselect id="applyer" name="applyer.id" value="${plmApplicationForLeave.applyer.id}" labelName="applyer.name" labelValue="${plmApplicationForLeave.applyer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>开始时间：</label>
				<input name="beginFromDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmApplicationForLeave.beginFromDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endFromDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmApplicationForLeave.endFromDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="beginToDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmApplicationForLeave.beginToDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endToDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmApplicationForLeave.endToDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>请假类型：</label>
				<form:select path="applyType" class="input-medium">
					<form:option value="" label="未选择"/>
					<form:options items="${fns:getDictList('leave_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>标题</th>
				<th>编号</th>
				<th>申请人</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>请假时长</th>
				<th>请假类型</th>
				<th>请假原因</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmApplicationForLeave">
			<tr>
				<td><a href="${ctx}/travel/plmApplicationForLeave/form?id=${plmApplicationForLeave.id}">
					${plmApplicationForLeave.title}
				</a></td>
				<td>
					${plmApplicationForLeave.code}
				</td>
				<td>
					${plmApplicationForLeave.applyer.name}
				</td>
				<td>
					<fmt:formatDate value="${plmApplicationForLeave.fromDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${plmApplicationForLeave.toDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmApplicationForLeave.days}
				</td>
				<td>
					${fns:getDictLabel(plmApplicationForLeave.applyType, 'leave_type', '')}
				</td>
				<td>
					${plmApplicationForLeave.cause}
				</td>
				<td>
					${plmApplicationForLeave.remarks}
				</td>
			<td>
    				<c:if test="${not empty plmApplicationForLeave.procInsId}" var="e">
						
							<a href="${ctx}/travel/plmApplicationForLeave/form?id=${plmApplicationForLeave.id}"
								title="显示详情"><i class="icon-file"></i></a>
						
					</c:if>
					<c:if test="${!e}">
						
							<a href="${ctx}/travel/plmApplicationForLeave/form?id=${plmApplicationForLeave.id}"
								title="提交申请"><i class="icon-pencil"></i></a>
							<a class="btnList"
								href="${ctx}/travel/plmApplicationForLeave/delete?id=${plmApplicationForLeave.id}"
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
			<c:forEach items="${page.list}" var="plmApplicationForLeave">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/travel/plmApplicationForLeave/form?id=${plmApplicationForLeave.id}">
							<h4 title="${plmApplicationForLeave.title}">主题：${plmApplicationForLeave.title}</h4>
						</a>
						<div class="caption row-fluid">

							<%-- <div class=" spanimg" style="width: 30%"> <img src="${ctxStatic}/common/index/images/index-bg.gif"   alt="通用的占位符缩略图">  </div> --%>

							<div class="spantext  " style="width: 88%; margin-left: 6%">
								<p title="${plmApplicationForLeave.code}">编号：${plmApplicationForLeave.code}</p>
								<p title="${plmApplicationForLeave.applyer.name}">申请人：${plmApplicationForLeave.applyer.name}</p>
								<p title="${plmApplicationForLeave.days}">请假时长：${plmApplicationForLeave.days}</p>
							</div>

						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmApplicationForLeave.procInsId}" var="e">
								
									<a href="${ctx}/travel/plmApplicationForLeave/form?id=${plmApplicationForLeave.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!e}">
																<a href="${ctx}/travel/plmApplicationForLeave/form?id=${plmApplicationForLeave.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/travel/plmApplicationForLeave/delete?id=${plmApplicationForLeave.id}"
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