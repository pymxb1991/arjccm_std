<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修(保养)申请管理</title>
	<meta name="decorator" content="default"/>
	<!-- 列表缩略图切换 -->
	<!--自适应  -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<style type="text/css">
        img {
		    height: 109px;
		}
    </style>
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
	<!-- /列表缩略图切换 -->	
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
	<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarUseForm.js"></script> 
</head>
<body>
	<form:form id="searchForm" modelAttribute="plmCarApplyRepair" action="${ctx}/car/apply/plmCarApplyRepair/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请编号：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>申请人：</label>
				<sys:treeselect id="user" name="user.id" value="${plmCarApplyRepair.user.id}" labelName="user.name" labelValue="${plmCarApplyRepair.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>维修(保养)车辆：</label>
				<form:input type="hide" id="carIdsAll" path="car.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>维保单位：</label>
				<form:input type="hide" id="repairComIds" path="repairCom.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<!--/列表缩略图切换按钮 -->
	<div id="prodInfo_List">	
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>申请编号</th>
					<th>申请人</th>
					<th>维修(保养)车辆</th>
					<th>维保单位</th>
					<th>计划保养日期</th>
					<th>申请时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="plmCarApplyRepair">
				<tr>
					<td><a href="${ctx}/car/apply/plmCarApplyRepair/form?id=${plmCarApplyRepair.id}">
						${plmCarApplyRepair.title}
					</a></td>
					<td>
						${plmCarApplyRepair.user.name}
					</td>
					<td>
						${plmCarApplyRepair.car.vehicle}
					</td>
					<td>
						${plmCarApplyRepair.repairCom.name}
					</td>
					<td>
						<fmt:formatDate value="${plmCarApplyRepair.repairDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<fmt:formatDate value="${plmCarApplyRepair.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<c:if test="${not empty plmCarApplyRepair.procInsId}" var="condition">
							
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyRepair/form?id=${plmCarApplyRepair.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!condition}">
								 
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyRepair/form?id=${plmCarApplyRepair.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyRepair/delete?id=${plmCarApplyRepair.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
								
							</c:if>		
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmCarApplyRepair">
				<div class="span4 spandiv">
					<div class="thumbnail">


						<a href="${ctx}/car/apply/plmCarApplyRepair/form?id=${plmCarApplyRepair.id}">
							<h4 title="${plmCarApplyRepair.title}">申请编号：${plmCarApplyRepair.title}</h4>
						</a>
						<div class="caption row-fluid">

						   
							<div class="spantext  " style="width: 84%; margin-left: 7%">
							    <p title="${fns:getDictLabel(plmCarApplyRepair.plmAct.status, 'plm_act_status', '')}">流程状态：${fns:getDictLabel(plmCarApplyRepair.plmAct.status, 'plm_act_status', '')}</p>
								<p title="${plmCarApplyRepair.car.vehicle}">维修(保养)车辆：${plmCarApplyRepair.car.vehicle}</p>
								<p title="${plmCarApplyRepair.repairCom.name}">维保单位：${plmCarApplyRepair.repairCom.name}</p>
								<p
									title="<fmt:formatDate value="${plmCarApplyRepair.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
									申请时间：
									<fmt:formatDate value="${plmCarApplyRepair.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</p>																								
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmCarApplyRepair.procInsId}" var="condition">
							
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyRepair/form?id=${plmCarApplyRepair.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!condition}">
								 
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyRepair/form?id=${plmCarApplyRepair.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyRepair/delete?id=${plmCarApplyRepair.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
								
							</c:if>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<!-- /缩略图 -->	
	<div class="pagination">${page}</div>
</body>
</html>