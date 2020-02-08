<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>入库单管理</title>
<meta name="decorator" content="default" />
<!-- 列表缩略图切换 -->
<!--自适应  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
	rel="stylesheet">
<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
<!-- /列表缩略图切换 -->
<link rel="stylesheet"
	href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
<script type="text/javascript">
	$(function() {
		$("#addDetailInfo").dialog({

			autoOpen : false,
			closeOnEscape : false,
			height : 500,
			width : 1100,
			modal : true,
			close : function() {
				$(this).dialog("close");
			}
		});
		$("a[title='addDetail']").on("click", function() {
			$("#addDetailInfo").attr("src", this);
			$("#addDetailInfo").dialog("open");
			$("#addDetailInfo").css({
				"width" : "98%"
			});
			return false;
		});
		$("#btnSubmit").on("click", function() {
			var begin = new Date($("[name='beginIncomingDate']").val());
		    var end = new Date($("[name='endIncomingDate']").val());
		    if(begin.getTime() > end.getTime()){
		    	messageAlert("开始时间大于结束时间！", "error");
		    	return false;
		    }
		    $("#searchForm").submit();
		});
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">应急物资保障</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/storage/plmIncomingEntry/">入库单列表</a></li>
		<shiro:hasPermission name="storage:plmIncomingEntry:edit">
			<li><a style="width: 140px;text-align:center" href="${ctx}/storage/plmIncomingEntry/form">入库单添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmIncomingEntry"
		action="${ctx}/storage/plmIncomingEntry/" method="post"
		class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form pull-left">
			<li class="first-line"><label>供货单位：</label> <form:select path="provide"
					class="input-medium">
					<form:option value="" label="未选择" />
					<form:options items="${provideList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select></li>
			<li class="first-line"><label>入库开始日期：</label>
				<input name="beginIncomingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmIncomingEntry.beginIncomingDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> </li>
			<li class="first-line"><label>入库结束日期:</label>	<input name="endIncomingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmIncomingEntry.endIncomingDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="first-line"><label>入库单号：</label> <form:input path="incomingCode"
					htmlEscape="false" maxlength="64" class="input-medium"/></li>
			<li class="first-line"><label>入库类别：</label> <form:select path="incomingType"
					class="input-medium">
					<form:option value="" label="未选择" />
					<form:options items="${fns:getDictList('plm_incoming_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="second-line"><label>入库状态：</label> <form:select path="type"
					class="input-medium">
					<form:option value="" label="未选择" />
					<form:options items="${fns:getDictList('plm_incoming_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

<%--			<li class="clearfix"></li>--%>

		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;
    /*margin-top: 25px;*/display:inline-block;float: right" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}" />

	<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn" style="margin-top: 35px;margin-right: 100px">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn" style="margin-right: 20px"> <i class="icon-th-list2 "></i></a>
	</div>
	<!--/列表缩略图切换按钮 -->
	<div id="prodInfo_List">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed table-gradient">
			<thead>
				<tr>
					<th>入库单号</th>
					<th>入库日期</th>
					<th>供货单位</th>
					<th>入库类别</th>
					<th>入库状态</th>
					<th>备注信息</th>
					<shiro:hasPermission name="storage:plmIncomingEntry:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmIncomingEntry">
					<tr>
						<td style="height: 50px"><a
							href="${ctx}/storage/plmIncomingEntry/form?id=${plmIncomingEntry.id}">
								${plmIncomingEntry.incomingCode} </a></td>
						<td style="height: 50px"><fmt:formatDate value="${plmIncomingEntry.incomingDate}"
								pattern="yyyy-MM-dd" /></td>
						<td style="height: 50px">${plmIncomingEntry.provide.name}</td>
						<td style="height: 50px">${fns:getDictLabel(plmIncomingEntry.incomingType, 'plm_incoming_type', '未知')}
						</td>
						<td style="height: 50px">${fns:getDictLabel(plmIncomingEntry.type, 'plm_incoming_status', '未知')}
						</td>
						<td class="tp" style="height: 50px">${plmIncomingEntry.remarks}</td>
						<shiro:hasPermission name="storage:plmIncomingEntry:edit">
							<td style="height: 50px">
								<c:if test="${plmIncomingEntry.type == '0'}">
									<a class="btnList"
										href="${ctx}/storage/plmIncomingEntry/form?id=${plmIncomingEntry.id}"><i title="修改" class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/storage/plmIncomingEntry/delete?id=${plmIncomingEntry.id}"
										onclick="return confirmx('确认要删除该入库单吗？', this.href)"><i title="删除" class="icon-trash"></i></a> 
									<a class="btnList" title="addDetail"
										href="${ctx}/storage/plmMinusandAddDetail/countEquipmentByType?incomingId=${plmIncomingEntry.id}"><i title="添加物资" class="icon-plus"></i></a>
								</c:if>
								<c:if test="${plmIncomingEntry.type != '0'}">
									<a class="btnList"
										href="${ctx}/storage/plmIncomingEntry/form?id=${plmIncomingEntry.id}"><i title="查看" class="icon-file"></i></a>
								</c:if>
							</td>
						</shiro:hasPermission>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<ul style="padding-top: 50px">
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmIncomingEntry">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a
							href="${ctx}/storage/plmIncomingEntry/form?id=${plmIncomingEntry.id}">
							<h4 title="${plmIncomingEntry.incomingCode}">入库单号:${plmIncomingEntry.incomingCode}</h4></a>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${ctxStatic}/common/index/images/index-bg.gif"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图" />
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${fns:getDictLabel(plmIncomingEntry.incomingType, 'plm_incoming_type', '')}">入库类别:${fns:getDictLabel(plmIncomingEntry.incomingType, 'plm_incoming_type', '')}</p>
								<p title="${fns:getDictLabel(plmIncomingEntry.type, 'plm_incoming_status', '')}">入库状态:${fns:getDictLabel(plmIncomingEntry.type, 'plm_incoming_status', '')}</p>
								<p title="<fmt:formatDate value="${plmIncomingEntry.incomingDate}" pattern="yyyy-MM-dd"/>">
									入库日期:<fmt:formatDate value="${plmIncomingEntry.incomingDate}"  pattern="yyyy-MM-dd" /></p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="storage:plmIncomingEntry:edit">
								<c:if test="${plmIncomingEntry.type == '0'}">
									<a class="btnList"
										href="${ctx}/storage/plmIncomingEntry/form?id=${plmIncomingEntry.id}"><i title="修改" class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/storage/plmIncomingEntry/delete?id=${plmIncomingEntry.id}"
										onclick="return confirmx('确认要删除该入库单吗？', this.href)"><i title="删除" class="icon-trash"></i></a> 
									<a class="btnList" title="addDetail"
										href="${ctx}/storage/plmMinusandAddDetail/countEquipmentByType?incomingId=${plmIncomingEntry.id}"><i title="添加物资" class="icon-plus"></i></a>
								</c:if>
								<c:if test="${plmIncomingEntry.type != '0'}">
									<a class="btnList"
										href="${ctx}/storage/plmIncomingEntry/form?id=${plmIncomingEntry.id}"><i title="查看" class="icon-file"></i></a>
								</c:if>
							</shiro:hasPermission>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	</ul>
	<!-- /缩略图 -->
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
	<iframe id="addDetailInfo" src=""></iframe>
</body>
</html>