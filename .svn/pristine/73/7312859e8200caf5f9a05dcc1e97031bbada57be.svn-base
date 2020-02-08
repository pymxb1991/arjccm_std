<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报废申请管理</title>
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
	<form:form id="searchForm" modelAttribute="plmCarApplyScrap" action="${ctx}/car/apply/plmCarApplyScrap/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>申请人：</label>
				<sys:treeselect id="user" name="user.id" value="${plmCarApplyScrap.user.id}" labelName="user.name" labelValue="${plmCarApplyScrap.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>报废车辆：</label>
				<form:input type="hide" id="carIdsAll" path="car.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报废类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_apply_scrap_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
					<th>申请标题</th>
					<th>申请人</th>
					<th>报废车辆</th>
					<th>报废类型</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="plmCarApplyScrap">
				<tr>
					<td><a href="${ctx}/car/apply/plmCarApplyScrap/form?id=${plmCarApplyScrap.id}">
						${plmCarApplyScrap.title}
					</a></td>
					<td>
						${plmCarApplyScrap.user.name}
					</td>
					<td>
						${plmCarApplyScrap.car.vehicle}
					</td>
					<td>
						${fns:getDictLabel(plmCarApplyScrap.type, 'plm_car_apply_scrap_type', '')}
					</td>
					<td>
						<fmt:formatDate value="${plmCarApplyScrap.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
	    				<c:if test="${not empty plmCarApplyScrap.procInsId}" var="condition">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyScrap/form?id=${plmCarApplyScrap.id}"
										title="显示详情"><i class="icon-file"></i></a>
							
							</c:if>
							<c:if test="${!condition}">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyScrap/form?id=${plmCarApplyScrap.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyScrap/delete?id=${plmCarApplyScrap.id}"
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
			<c:forEach items="${page.list}" var="plmCarApplyScrap">
				<div class="span4 spandiv">
					<div class="thumbnail">


						<a href="${ctx}/car/apply/plmCarApplyScrap/form?id=${plmCarApplyScrap.id}">
							<h4 title="${plmCarApplyScrap.title}">申请编号：${plmCarApplyScrap.title}</h4>
						</a>
						<div class="caption row-fluid">

						   
							<div class="spantext  " style="width: 84%; margin-left: 7%">
							    <p title="${fns:getDictLabel(plmCarApplyScrap.plmAct.status, 'plm_act_status', '')}">流程状态：${fns:getDictLabel(plmCarApplyScrap.plmAct.status, 'plm_act_status', '')}</p>
								<p title="${plmCarApplyScrap.car.vehicle}">报废车辆：${plmCarApplyScrap.car.vehicle}</p>
								<p title="${fns:getDictLabel(plmCarApplyScrap.type, 'plm_car_apply_scrap_type', '')}">报废类型：${fns:getDictLabel(plmCarApplyScrap.type, 'plm_car_apply_scrap_type', '')}</p>
								<p
									title="<fmt:formatDate value="${plmCarApplyScrap.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
									申请时间：
									<fmt:formatDate value="${plmCarApplyScrap.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</p>																								
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmCarApplyScrap.procInsId}" var="condition">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyScrap/form?id=${plmCarApplyScrap.id}"
										title="显示详情"><i class="icon-file"></i></a>
							
							</c:if>
							<c:if test="${!condition}">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyScrap/form?id=${plmCarApplyScrap.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyScrap/delete?id=${plmCarApplyScrap.id}"
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