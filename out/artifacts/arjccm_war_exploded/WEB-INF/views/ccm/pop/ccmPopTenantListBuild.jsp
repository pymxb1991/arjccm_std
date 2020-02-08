<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋管理</title>
	<meta name="decorator" content="default"/>
	
<script type="text/javascript"
	src="${ctxStatic}/ccm/pop/js/ccmTenantInfo.js"></script>
</head>
<body>
<div class="back-list">
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/pop/ccmPopTenant/import"
			method="post" enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br /> <br /> <input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="导  入 " />
		</form>
	</div>
	
	<ul class="nav nav-tabs">		 
		<li><a style="width: 140px;text-align:center" href="${ctx}/house/ccmHouseBuildmanage/">楼栋列表</a></li>
		<li><a style="width: 140px;text-align:center" href="${ctx}/house/ccmHouseBuildmanage/form">楼栋添加</a></li>
		
        <li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/pop/ccmPopTenant/listBuild?buildingId.id=${buildingId}&area.id=${buildingAreaId}">房屋列表</a></li>
 		<shiro:hasPermission name="pop:ccmPopTenant:edit">
 			<li><a style="width: 140px;text-align:center" href="${ctx}/pop/ccmPopTenant/formBuild?buildingIdId=${buildingId}&area.id=${buildingAreaId}">房屋新增</a></li>
 		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPopTenant" action="${ctx}/pop/ccmPopTenant/listBuild?buildingId.id=${buildingId}&area.id=${buildingAreaId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns">
				<a class="btn btn-primary" onclick="parent.LayerDialog1('','${ctx}/pop/ccmPopTenant/listBuildAdd?bId=${buildingId}&aId=${buildingAreaId}', '绑定房屋', '1200px', '700px')" title="绑定房屋">
					绑定房屋</a>
			</li>
			<li class="clearfix"></li>
		</ul>
		
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>楼门号</th>
				<th>门牌号</th>
				<th>房屋编号</th>
				<th>所属网格</th>
				<th>所属楼栋</th>
				<th>房主姓名</th>
				<th>房屋地址</th>
				<th>建筑面积(平方米）</th>
				<shiro:hasPermission name="pop:ccmPopTenant:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPopTenant">
			<tr>
				<td>${ccmPopTenant.buildDoorNum}</td>
				<td><a href="${ctx}/pop/ccmPopTenant/formBuild?buildingIdId=${ccmPopTenant.buildingId.id}&id=${ccmPopTenant.id}">
					${ccmPopTenant.doorNum}
				</a></td>
				<td>${ccmPopTenant.houseBuild}</td>
				<td>
					${ccmPopTenant.area.name}
				</td>
				<td>
					${ccmPopTenant.buildingId.buildname}
				</td>
				<td>
					${ccmPopTenant.houseName}
				</td>
				<td>
					${ccmPopTenant.housePlace}
				</td>
				<td>
					${ccmPopTenant.houseArea}
				</td>
				<td><shiro:hasPermission name="pop:ccmPopTenant:edit">
    				<a class="btnList"  
    				href="${ctx}/pop/ccmPopTenant/formBuild?buildingIdId=${ccmPopTenant.buildingId.id}&id=${ccmPopTenant.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" 
					href="${ctx}/pop/ccmPopTenant/deleteHouse?id=${ccmPopTenant.id}&buildingId=${buildingId}&buildingAreaId=${buildingAreaId}" onclick="return confirmx('确认要解除该房屋的绑定吗？', this.href)" title="解除绑定"><i class="icon-remove-sign"></i></a>
					<a class="btnList"  
                     href="${ctx}/pop/ccmPeople/getPeoListByHouse?houseId=${ccmPopTenant.id}&buildId=${buildingId}&type=houseBuild&netId=${buildingAreaId}" title="住户管理"><i class="icon-group"></i></a>
				 </shiro:hasPermission> <shiro:hasPermission name="log:ccmLogTail:edit">
				  			<%-- <a	class="btn btn-success"
								href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmPopTenant.id}&relevance_table=ccm_pop_tenant">添加记录</a> --%>
						</shiro:hasPermission></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</div>
</body>
</html>