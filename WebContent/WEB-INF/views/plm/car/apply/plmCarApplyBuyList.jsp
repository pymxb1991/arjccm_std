<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购车申请管理</title>
	<meta name="decorator" content="default"/>
	<!-- 列表缩略图切换 -->
	<!--自适应  -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
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
	<form:form id="searchForm" modelAttribute="plmCarApplyBuy" action="${ctx}/car/apply/plmCarApplyBuy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>是否结束：</label>
				<form:select path="isEnd" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>			
			<%-- <li><label>申请人ID：</label>
				<sys:treeselect id="user" name="user.id" value="${plmCarApplyBuy.user.id}" labelName="user.name" labelValue="${plmCarApplyBuy.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>购车数量：</label>
				<form:input path="num" htmlEscape="false" maxlength="4" class="input-medium"/>
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
					<th>是否结束</th>
					<th>申请人ID</th>
					<th>购车原因</th>
					<th>购车数量</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="plmCarApplyBuy">
				<tr>
					<td><a href="${ctx}/car/apply/plmCarApplyBuy/form?id=${plmCarApplyBuy.id}">
						${plmCarApplyBuy.title}
					</a></td>
					<td>
						${fns:getDictLabel(plmCarApplyBuy.isEnd, 'yes_no', '')}
					</td>				
					<td>
						${plmCarApplyBuy.user.name}
					</td>
					<td>
						${plmCarApplyBuy.reason}
					</td>
					<td>
						${plmCarApplyBuy.num}
					</td>
					<td>
						<fmt:formatDate value="${plmCarApplyBuy.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
	    				<c:if test="${not empty plmCarApplyBuy.procInsId}" var="condition">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyBuy/form?id=${plmCarApplyBuy.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!condition}">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyBuy/form?id=${plmCarApplyBuy.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyBuy/delete?id=${plmCarApplyBuy.id}"
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
			<c:forEach items="${page.list}" var="plmCarApplyBuy">
				<div class="span4 spandiv">
					<div class="thumbnail">


						<a href="${ctx}/car/apply/plmCarApplyBuy/form?id=${plmCarApplyBuy.id}">
							<h4 title="${plmCarApplyBuy.title}">申请编号：${plmCarApplyBuy.title}</h4>
						</a>
						<div class="caption row-fluid">

						    
							<div class="spantext  " style="width: 84%; margin-left: 7%">
							
							    <p title="${fns:getDictLabel(plmCarApplyBuy.plmAct.status, 'plm_act_status', '')}">流程状态：${fns:getDictLabel(plmCarApplyBuy.plmAct.status, 'plm_act_status', '')}</p>
								<p title="${plmCarApplyBuy.num}">购车数量：${plmCarApplyBuy.num}台</p>
								<p
									title="<fmt:formatDate value="${plmCarApplyBuy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
									申请时间：
									<fmt:formatDate value="${plmCarApplyBuy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</p>																								
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmCarApplyBuy.procInsId}" var="condition">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyBuy/form?id=${plmCarApplyBuy.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!condition}">
								
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyBuy/form?id=${plmCarApplyBuy.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/car/apply/plmCarApplyBuy/delete?id=${plmCarApplyBuy.id}"
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