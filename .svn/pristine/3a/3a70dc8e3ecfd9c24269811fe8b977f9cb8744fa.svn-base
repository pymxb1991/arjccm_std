<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>车辆管理</title>
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
	//详情弹框--刷新父页面
	function LayerDialog(src, title, height, width) {
		parent.layer.open({
			type : 2,
			title : title,
			area : [ height, width ],
			fixed : true, //固定
			maxmin : true,
			//btn: ['确定', '关闭'], //可以无限个按钮
			content : src,
			end : function() {
				location.reload();
			}
		});
	}
</script>
<style type="text/css">
img {
	width: 150px;
	max-width: 150px;
}

p {
	margin: 0 55px 10px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/car/plmCar/">车辆列表</a></li>
		<shiro:hasPermission name="car:plmCar:edit">
			<li><a href="${ctx}/car/plmCar/form">车辆添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCar"
		action="${ctx}/car/plmCar/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>车牌号：</label> <form:input path="vehicle"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>状态：</label> <form:select path="vehicleStatus"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plm_car_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>车型：</label> <form:select path="vtype"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plm_car_vtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>核载人数：</label> <form:input path="loadNum"
					htmlEscape="false" maxlength="3" class="input-medium" /></li>
			
			
			<li class="btns"><a id="btnSubmit" class="btn btn-primary"><i class="icon-search"></i>查询</a></li>
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
					<th>车牌号</th>
					<th>状态</th>
					<th>品牌</th>
					<th>型号</th>
					<th>车型</th>
					<th>核载人数</th>
					
					<shiro:hasPermission name="car:plmCar:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmCar">
					<tr>
						<td><a href="${ctx}/car/plmCar/form?id=${plmCar.id}">
								${plmCar.vehicle} </a></td>
						<td>${fns:getDictLabel(plmCar.vehicleStatus, 'plm_car_status', '')}
						</td>
						<td>${plmCar.brand}</td>
						<td>${plmCar.vmodel}</td>
						<td>${fns:getDictLabel(plmCar.vtype, 'plm_car_vtype', '')}</td>
						<td>${plmCar.loadNum}</td>
						
						</td>
						<td width="20%"><shiro:hasPermission
								name="car:plmCarUse:edit">
								<a class="btnList"
									onclick="LayerDialog('${ctx}/car/plmCarUse/carList?car.id=${plmCar.id}', '【${plmCar.vehicle}】领用记录', '1000px', '700px')"
									title="领用记录"><i class="icon-fast-forward"></i></a>
							</shiro:hasPermission> <shiro:hasPermission name="car:plmCarViolation:view">
								<a class="btnList"
									onclick="parent.LayerDialog('${ctx}/car/plmCarViolation/addFromUseList?car.id=${plmCar.id}', '【${plmCar.vehicle}】违章记录', '1000px', '700px')"
									title="违章记录"><i class="icon-file"></i></a>
							</shiro:hasPermission> <shiro:hasPermission name="car:plmCar:edit">
								<a class="btnList" href="${ctx}/car/plmCar/form?id=${plmCar.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/car/plmCar/delete?id=${plmCar.id}"
									onclick="return confirmx('确认要删除该车辆吗？', this.href)" title="删除"><i
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
			<c:forEach items="${page.list}" var="plmCar">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/car/plmCar/form?id=${plmCar.id}">
							<h4 title="${plmCar.vehicle}">车牌号：${plmCar.vehicle}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${plmCar.photo}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图" />
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p
									title="${fns:getDictLabel(plmCar.vehicleStatus, 'plm_car_status', '')}">状态:${fns:getDictLabel(plmCar.vehicleStatus, 'plm_car_status', '')}</p>
								<p title="${plmCar.vmodel}">型号:${plmCar.vmodel}</p>
								<p title="${plmCar.loadNum}">核载人数:${plmCar.loadNum}</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="car:plmCarUse:edit">
								<a class="btnList"
									onclick="LayerDialog('${ctx}/car/plmCarUse/carList?car.id=${plmCar.id}', '【${plmCar.vehicle}】领用记录', '1000px', '700px')"
									title="领用记录"><i class="icon-fast-forward"></i></a>
							</shiro:hasPermission>
							<shiro:hasPermission name="car:plmCarViolation:view">
								<a class="btnList"
									onclick="parent.LayerDialog('${ctx}/car/plmCarViolation/addFromUseList?car.id=${plmCar.id}', '【${plmCar.vehicle}】违章记录', '1000px', '700px')"
									title="违章记录"><i class="icon-file"></i></a>
							</shiro:hasPermission>
							<shiro:hasPermission name="car:plmCar:edit">
								<a class="btnList" href="${ctx}/car/plmCar/form?id=${plmCar.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/car/plmCar/delete?id=${plmCar.id}"
									onclick="return confirmx('确认要删除该车辆吗？', this.href)" title="删除"><i
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