<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>装备物资管理</title>
<meta name="decorator" content="default" />
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
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	//编辑内容
	function bjnr() {
		if (bjckid == "-1") {
			bjalert("请先选中编辑窗口!")
		} else {
			jBox('iframe:${ctx}/home/plmHome/form?id=' + bjckid, {
				title : "编辑门户内容",
				width : 600,
				height : 380,
				buttons : {}, //为了不出现底部的按钮这里特别要这样填写
				closed : function() { //关闭时发生，为了刷新父级页面
					search();
				},
				loaded : function(h) { //隐藏滚动条
					$(".jbox-content").css("overflow", "inherit");
				}
			});
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equipment/plmEquipmentAssets/">装备资产信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="plmEquipment"
		action="${ctx}/equipment/plmEquipmentAssets/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>物资名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>物资状态：</label> <form:select path="type"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plm_equipment_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
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
				<th>物资名称</th>
				<th>物资编号</th>
				<th>数量</th>
				<th>装备类别</th>
				<th>物资状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="plmEquipmentAssets">
				<tr>
					<td><a
						href="${ctx}/equipment/plmEquipmentAssets/form?id=${plmEquipmentAssets.id}">
							${plmEquipmentAssets.name} </a></td>
					<td>${plmEquipmentAssets.code}</td>
					<td>${plmEquipmentAssets.erialNumber}</td>
					<td>${fns:getDictLabel(plmEquipmentAssets.typeChild, 'plm_equipment_type_child', '')}
					</td>
					<td>${fns:getDictLabel(plmEquipmentAssets.type, 'plm_equipment_status', '')}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmEquipmentAssets">
				<div class="span4 spandiv">
					<div class="thumbnail"><a
						href="${ctx}/equipment/plmEquipmentAssets/form?id=${plmEquipmentAssets.id}">
							 
							<h4 title="${plmEquipmentAssets.name}">物资名称:${plmEquipmentAssets.name}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${ctxStatic}/common/index/images/index-bg.gif"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图" />
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmEquipmentAssets.code}">物资编号:${plmEquipmentAssets.code}</p>
								<p title="${fns:getDictLabel(plmEquipmentAssets.typeChild, 'plm_equipment_type_child', '')}">装备类别:${fns:getDictLabel(plmEquipmentAssets.typeChild, 'plm_equipment_type_child', '')}</p>
								<p title="${fns:getDictLabel(plmEquipmentAssets.type, 'plm_equipment_status', '')}">物资状态:${fns:getDictLabel(plmEquipmentAssets.type, 'plm_equipment_status', '')}</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
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