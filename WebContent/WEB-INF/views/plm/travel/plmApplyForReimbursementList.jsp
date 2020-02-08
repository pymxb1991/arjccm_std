<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销申请管理</title>
	<meta name="decorator" content="default"/>
	<link
		href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
		rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
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
	<form:form id="searchForm" modelAttribute="plmApplyForReimbursement" action="${ctx}/travel/plmApplyForReimbursement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			
			<li><label>报销类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="16" class="input-medium"/>
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
				<th>报销总额</th>
				<th>报销类型</th>
				<th>报销原因</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmApplyForReimbursement">
			<tr>
				<td><a href="${ctx}/travel/plmApplyForReimbursement/form?id=${plmApplyForReimbursement.id}">
					${plmApplyForReimbursement.title}
				</a></td>
				<td>
					${plmApplyForReimbursement.code}
				</td>
				<td>
					${plmApplyForReimbursement.applyer.name}
				</td>
				<td>
					${plmApplyForReimbursement.sum}
				</td>
				<td>
					${plmApplyForReimbursement.type}
				</td>
				<td>
					${plmApplyForReimbursement.cause}
				</td>
				<td>
					<fmt:formatDate value="${plmApplyForReimbursement.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmApplyForReimbursement.remarks}
				</td>
				<td>
    				<c:if test="${not empty plmApplyForReimbursement.procInsId}" var="e">
						
							<a href="${ctx}/travel/plmApplyForReimbursement/form?id=${plmApplyForReimbursement.id}"
								title="显示详情"><i class="icon-file"></i></a>
					
					</c:if>
					<c:if test="${!e}">
						
							<a href="${ctx}/travel/plmApplyForReimbursement/form?id=${plmApplyForReimbursement.id}"
								title="提交申请"><i class="icon-pencil"></i></a>
							<a class="btnList"
								href="${ctx}/travel/plmApplyForReimbursement/delete?id=${plmApplyForReimbursement.id}"
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
			<c:forEach items="${page.list}" var="plmApplyForReimbursement">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/travel/plmApplyForReimbursement/form?id=${plmApplyForReimbursement.id}">
							<h4 title="${plmApplyForReimbursement.title}">标题：${plmApplyForReimbursement.title}</h4>
						</a>
						<div class="caption row-fluid">


							<div class="spantext  " style="width: 88%; margin-left: 6%">
								<p title="${plmApplyForReimbursement.code}">编号：${plmApplyForReimbursement.code}</p>
								<p title="${plmApplyForReimbursement.applyer.name}">申请人：${plmApplyForReimbursement.applyer.name}</p>
								<p title="${plmApplyForReimbursement.sum}">报销总额：${plmApplyForReimbursement.sum}</p>
							</div>

						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmApplyForReimbursement.procInsId}" var="e">
								
									<a href="${ctx}/travel/plmApplyForReimbursement/form?id=${plmApplyForReimbursement.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!e}">
								
									<a href="${ctx}/travel/plmApplyForReimbursement/form?id=${plmApplyForReimbursement.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/travel/plmApplyForReimbursement/delete?id=${plmApplyForReimbursement.id}"
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