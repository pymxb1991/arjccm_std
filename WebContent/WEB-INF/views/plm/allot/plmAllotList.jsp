<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调拨单管理</title>
	<meta name="decorator" content="default"/>
	<link
		href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
		rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
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
	<form:form id="searchForm" modelAttribute="plmAllot" action="${ctx}/allot/plmAllot/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>填写日期：</label>
				<input name="beginAddDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmAllot.beginAddDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endAddDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmAllot.endAddDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>调出部门：</label>
				<sys:treeselect id="outDep" name="outDep.id" value="${plmAllot.outDep.id}" labelName="outDep.name" labelValue="${plmAllot.outDep.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>调入部门：</label>
				<sys:treeselect id="inDep" name="inDep.id" value="${plmAllot.inDep.id}" labelName="inDep.name" labelValue="${plmAllot.inDep.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
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
					<th>编号</th>
					<th>标题</th>
					<th>申请人</th>
					<th>填写日期</th>
					<th>资产总额小写</th>
					<th>调出部门</th>
					<th>调入部门</th>
					<th>更新时间</th>
					<th>备注信息</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="plmAllot">
				<tr>
					<td><a href="${ctx}/allot/plmAllot/form?id=${plmAllot.id}">
						${plmAllot.code}
					</a></td>
					<td>
						${plmAllot.title}
					</td>
					<td>
						${plmAllot.applyer.name}
					</td>
					<td>
						<fmt:formatDate value="${plmAllot.addDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${plmAllot.sumLow}
					</td>
					<td>
						${plmAllot.outDep.name}
					</td>
					<td>
						${plmAllot.inDep.name}
					</td>
					<td>
						<fmt:formatDate value="${plmAllot.updateDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${plmAllot.remarks}
					</td>
					<td>
		   				<c:if test="${not empty plmAllot.procInsId}" var="e">
							<a href="${ctx}/allot/plmAllot/form?id=${plmAllot.id}"
									title="显示详情"><i class="icon-file"></i></a>
							</c:if>
							<c:if test="${!e}">
								<a href="${ctx}/allot/plmAllot/form?id=${plmAllot.id}"
									title="提交申请"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/allot/plmAllot/delete?id=${plmAllot.id}"
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
			<c:forEach items="${page.list}" var="plmAllot">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/allot/plmAllot/form?id=${plmAllot.id}">
							<h4 title="${plmAllot.title}">主题：${plmAllot.title}</h4>
						</a>
						<div class="caption row-fluid">

							<div class=" spanimg" style="width: 30%"> <img src="${ctxStatic}/common/index/images/index-bg.gif"   alt="通用的占位符缩略图">  </div>

							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmAllot.code}">编号：${plmAllot.code}</p>
								<p title="${plmAllot.applyer.name}">申请人：${plmAllot.applyer.name}</p>
								<p title="${plmAllot.addDate}">填写日期：<fmt:formatDate value="${plmAllot.addDate}" pattern="yyyy-MM-dd"/></p>
							</div>

						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmAllot.procInsId}" var="e">
								<a href="${ctx}/allot/plmAllot/form?id=${plmAllot.id}"
									title="显示详情"><i class="icon-file"></i></a>
							</c:if>
							<c:if test="${!e}">
								<a href="${ctx}/allot/plmAllot/form?id=${plmAllot.id}"
									title="提交申请"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/allot/plmAllot/delete?id=${plmAllot.id}"
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