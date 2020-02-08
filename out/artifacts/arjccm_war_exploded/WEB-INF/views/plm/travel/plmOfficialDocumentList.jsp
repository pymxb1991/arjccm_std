<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公文管理</title>
	<meta name="decorator" content="default"/>
	<!-- 列表缩略图切换 -->
	<!--自适应  -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
	<!-- /列表缩略图切换 -->
	<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSubmit").on("click", function() {
				var begin = new Date(Date.parse($("[name='beginDate']").val()));
			    var end = new Date(Date.parse($("[name='endDate']").val()));
			    if(begin.getTime() > end.getTime()){
			    	messageAlert("开始时间大于结束时间！", "error");
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
	<form:form id="searchForm" modelAttribute="plmOfficialDocument" action="${ctx}/travel/plmOfficialDocument/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>紧急程度：</label>
				<form:select path="urgent" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('urgent_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>密级：</label>
				<form:select path="confidentiality" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('confident_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>日期：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmOfficialDocument.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmOfficialDocument.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>发送单位：</label>
				<sys:treeselect id="fromDept" name="fromDept.id" value="${plmOfficialDocument.fromDept.id}" labelName="fromDept.name" labelValue="${plmOfficialDocument.fromDept.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true" isAll="true"/>
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
				<th>编号</th>
				<th>紧急程度</th>
				<th>密级</th>
				<th>日期</th>
				<th>发送单位</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmOfficialDocument">
			<tr>
				<td><a href="${ctx}/travel/plmOfficialDocument/form?id=${plmOfficialDocument.id}">
					${plmOfficialDocument.title}
				</a></td>
				<td>
					${plmOfficialDocument.code}
				</td>
				<td>
					${fns:getDictLabel(plmOfficialDocument.urgent, 'urgent_type', '')}
				</td>
				<td>
					${fns:getDictLabel(plmOfficialDocument.confidentiality, 'confident_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${plmOfficialDocument.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmOfficialDocument.fromDept.name}
				</td>
				<td>
					${plmOfficialDocument.remarks}
				</td>
				<td>
					<c:if test="${not empty plmOfficialDocument.procInsId}" var="e">
						<a class="btnList" href="${ctx}/travel/plmOfficialDocument/form?id=${plmOfficialDocument.id}" title="显示详情"><i class="icon-file"></i></a>
					</c:if>
				<c:if test="${!e}">
				
    				<a class="btnList" href="${ctx}/travel/plmOfficialDocument/form?id=${plmOfficialDocument.id}" title="提交申请"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/travel/plmOfficialDocument/delete?id=${plmOfficialDocument.id}" onclick="return confirmx('确认要删除该公文吗？', this.href)" title="删除"><i
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
			<c:forEach items="${page.list}" var="plmOfficialDocument">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a
							href="${ctx}/travel/plmOfficialDocument/form?id=${plmOfficialDocument.id}">
							<h4 title="${plmOfficialDocument.title}">标题：${plmOfficialDocument.title}</h4>
						</a>
						<div class="caption row-fluid">

						   

							<div class="spantext  " style="width: 88%; margin-left: 6%">
							
							    <p title="${plmOfficialDocument.code}">编号：${plmOfficialDocument.code}</p>
								<p title="${plmOfficialDocument.fromDept.name}">发送单位：${plmOfficialDocument.fromDept.name}</p>
								<p title="<fmt:formatDate value="${plmOfficialDocument.date}" pattern='yyyy-MM-dd'/>">
									日期：
									<fmt:formatDate value="${plmOfficialDocument.date}"
										pattern="yyyy-MM-dd " />
								</p>
																								
							</div>

						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmOfficialDocument.procInsId}" var="e">
								
									<a class="btnList"
										href="${ctx}/travel/plmOfficialDocument/form?id=${plmOfficialDocument.id}"
										title="显示详情"><i class="icon-file"></i></a>
								
							</c:if>
							<c:if test="${!e}">
								
									<a class="btnList"
										href="${ctx}/travel/plmOfficialDocument/form?id=${plmOfficialDocument.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/travel/plmOfficialDocument/delete?id=${plmOfficialDocument.id}"
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