<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用车申请管理</title>
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
</head>
<body>
	<form:form id="searchForm" modelAttribute="plmCarApplyUse" action="${ctx}/car/apply/plmCarApplyUse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>申请人：</label>
				<sys:treeselect id="user" name="user.id" value="${plmCarApplyUse.user.id}" labelName="user.name" labelValue="${plmCarApplyUse.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarApplyUse.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarApplyUse.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
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
					<th>标题</th>
					<th>申请人</th>
					<th>出车时间</th>
					<th>归还时间</th>
					<th>申请事由</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="plmCarApplyUse">
				<tr>
					<td><a href="${ctx}/car/apply/plmCarApplyUse/form?id=${plmCarApplyUse.id}">
						${plmCarApplyUse.title}
					</a></td>
					<td>
						${plmCarApplyUse.user.name}
					</td>				
					<td>
						<fmt:formatDate value="${plmCarApplyUse.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${plmCarApplyUse.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						${plmCarApplyUse.remarks}
					</td>
					<td>
						<fmt:formatDate value="${plmCarApplyUse.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
	    				<c:if test="${not empty plmCarApplyUse.procInsId}" var="condition">
							
									<a  class="btnList"
										href="${ctx}/car/apply/plmCarApplyUse/form?id=${plmCarApplyUse.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!condition}">
								
									<a  class="btnList"
										href="${ctx}/car/apply/plmCarApplyUse/form?id=${plmCarApplyUse.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyUse/delete?id=${plmCarApplyUse.id}"
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
			<c:forEach items="${page.list}" var="plmCarApplyUse">
				<div class="span4 spandiv">
					<div class="thumbnail">


						<a href="${ctx}/car/apply/plmCarApplyUse/form?id=${plmCarApplyUse.id}">
							<h4 title="${plmCarApplyUse.title}">申请编号：${plmCarApplyUse.title}</h4>
						</a>
						<div class="caption row-fluid">

						     
							<div class="spantext  " style="width:84%; margin-left: 7%">
							    <p title="${fns:getDictLabel(plmCarApplyUse.plmAct.status, 'plm_act_status', '')}">流程状态：${fns:getDictLabel(plmCarApplyUse.plmAct.status, 'plm_act_status', '')}</p>
								<p title="${plmCarApplyUse.car.vehicle}">预选车辆：${plmCarApplyUse.car.vehicle}</p>
								<p title="${plmCarApplyUse.num}">乘车人数：${plmCarApplyUse.num}</p>
								<p
									title="<fmt:formatDate value="${plmCarApplyUse.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
									申请时间：
									<fmt:formatDate value="${plmCarApplyUse.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</p>																								
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmCarApplyUse.procInsId}" var="condition">
							
									<a  class="btnList"
										href="${ctx}/car/apply/plmCarApplyUse/form?id=${plmCarApplyUse.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!condition}">
								
									<a  class="btnList"
										href="${ctx}/car/apply/plmCarApplyUse/form?id=${plmCarApplyUse.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyUse/delete?id=${plmCarApplyUse.id}"
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