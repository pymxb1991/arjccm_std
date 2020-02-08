<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>驾驶员管理</title>
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
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#btnSubmit').click(function(){
					$('#searchForm').submit();
				});
				$("#btnExport").click(
						function() {
							top.$.jBox.confirm("确认要导出司机数据吗？", "系统提示", function(
									v, h, f) {
								if (v == "ok") {
									$("#searchForm").attr("action",
											"${ctx}/car/plmCarDriver/export");
									$("#searchForm").submit();
								}
							}, {
								buttonsFocus : 1
							});
							top.$('.jbox-body .jbox-icon').css('top', '55px');
						});
				$("#btnImport").click(function() {
					$.jBox($("#importBox").html(), {
						title : "导入数据",
						buttons : {
							"关闭" : true
						},
						bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
					});
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/car/plmCarDriver/import"
			method="post" enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/car/plmCarDriver/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/car/plmCarDriver/">司机列表</a></li>
		<shiro:hasPermission name="car:plmCarDriver:edit">
			<li><a href="${ctx}/car/plmCarDriver/form">司机添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarDriver"
		action="${ctx}/car/plmCarDriver/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>姓名：</label> <form:input path="name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>状态：</label> <form:select path="status"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plm_car_driver_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>准驾车型：</label> <form:select path="didType"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plm_car_driver_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>手机：</label> <form:input path="phone"
					htmlEscape="false" maxlength="11" class="input-medium" /></li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary"><i class="icon-search"></i>查询</a><shiro:hasPermission
					name="car:plmCarDriver:edit">
					<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
					<input id="btnImport" class="btn btn-primary" type="button" value="导入"/> -->
				</shiro:hasPermission></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<!--/列表缩略图切换按钮 -->
	<div id="prodInfo_List">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>姓名</th>
					<th>状态</th>
					<th>性别</th>
					<th>准驾车型</th>
					<th>出生日期</th>
					<th>驾驶证有效期</th>
					<th>手机</th>
					<th>更新时间</th>
					<shiro:hasPermission name="car:plmCarDriver:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmCarDriver">
					<tr>
						<td><a
							href="${ctx}/car/plmCarDriver/form?id=${plmCarDriver.id}">
								${plmCarDriver.name} </a></td>
						<td>${fns:getDictLabel(plmCarDriver.status, 'plm_car_driver_status', '')}
						</td>
						<td>${fns:getDictLabel(plmCarDriver.sex, 'sex', '')}</td>
						<td>${fns:getDictLabel(plmCarDriver.didType, 'plm_car_driver_type', '')}
						</td>
						<td><fmt:formatDate value="${plmCarDriver.birthday}"
								pattern="yyyy-MM-dd " /></td>
						<td><fmt:formatDate value="${plmCarDriver.didAvldate}"
								pattern="yyyy-MM-dd " /></td>
						<td>${plmCarDriver.phone}</td>
						<td><fmt:formatDate value="${plmCarDriver.updateDate}"
								pattern="yyyy-MM-dd " /></td>
						<td><shiro:hasPermission name="car:plmCarViolation:view">
								<a class="btnList"
									onclick="parent.LayerDialog('${ctx}/car/plmCarViolation/addFromUseList?driver.id=${plmCarDriver.id}', '违章记录', '1000px', '700px')"
									title="违章记录"><i class="icon-file"></i></a>
							</shiro:hasPermission> <shiro:hasPermission name="car:plmCarDriver:edit">
								<a class="btnList"
									href="${ctx}/car/plmCarDriver/form?id=${plmCarDriver.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/car/plmCarDriver/delete?id=${plmCarDriver.id}"
									onclick="return confirmx('确认要删除该驾驶员吗？', this.href)" title="删除"><i
									class="icon-trash"></i></a>
							</shiro:hasPermission></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmCarDriver">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/car/plmCarDriver/form?id=${plmCarDriver.id}">
							<h4 title="${plmCarDriver.name}">姓名：${plmCarDriver.name}</h4>
						</a>
						<div class="caption row-fluid">

							<div class=" spanimg" style="width: 30%">
								<img src="${plmCarDriver.photo}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图" />
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">

								<p
									title="${fns:getDictLabel(plmCarDriver.status, 'plm_car_driver_status', '')}">状态：${fns:getDictLabel(plmCarDriver.status, 'plm_car_driver_status', '')}</p>
								<p
									title="${fns:getDictLabel(plmCarDriver.didType, 'plm_car_driver_type', '')}">准驾车型：${fns:getDictLabel(plmCarDriver.didType, 'plm_car_driver_type', '')}</p>
								<p title="${plmCarDriver.phone}" />
								手机：${plmCarDriver.phone}
								</p>
							</div>
						</div>

						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="car:plmCarViolation:view">
								<a class="btnList"
									onclick="parent.LayerDialog('${ctx}/car/plmCarViolation/addFromUseList?driver.id=${plmCarDriver.id}', '违章记录', '1000px', '700px')"
									title="违章记录"><i class="icon-file"></i></a>
							</shiro:hasPermission>
							<shiro:hasPermission name="car:plmCarDriver:edit">
								<a class="btnList"
									href="${ctx}/car/plmCarDriver/form?id=${plmCarDriver.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/car/plmCarDriver/delete?id=${plmCarDriver.id}"
									onclick="return confirmx('确认要删除该驾驶员吗？', this.href)" title="删除"><i
									class="icon-trash"></i></a>
							</shiro:hasPermission>
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