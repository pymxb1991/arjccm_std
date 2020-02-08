<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>房屋管理</title>
<meta name="decorator" content="default" />

<script type="text/javascript"
	src="${ctxStatic}/ccm/pop/js/ccmTenantInfo.js"></script>
</head>
<body>
<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pop/ccmPopTenant/listBuildAdd?bId=${bId}&aId=${aId}">数据列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPopTenant" action="${ctx}/pop/ccmPopTenant/listBuildAdd" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<input id="bId" name="bId" type="hidden" value="${bId}" />
		<input id="aId" name="aId" type="hidden" value="${aId}" />
		<ul class="ul-form">
			<li><label>房屋编号：</label> <form:input path="houseBuild"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li><label>房主姓名：</label> <form:input path="houseName"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li><label>房屋地址：</label> <form:input path="housePlace"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<%-- <li><label>所属网格：</label> <sys:treeselect id="area"
					name="area.id" value="${ccmPopTenant.area.id}"
					labelName="area.name" labelValue="${ccmPopTenant.area.name}"
					title="网格" url="/tree/ccmTree/treeDataArea?type=7&areaid=" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<li><label>状态：</label> 
				<form:select path="houseType" class="input-small">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_pop_tenant_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li class="btns">
				<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();" /> -->
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>楼门号</th>
				<th>门牌号</th>
				<th>房屋编号</th>
				<th>房主姓名</th>
				<th>房屋地址</th>
				<th>状态</th>
				<th>所属网格</th>
				<th>所属楼栋</th>
				<shiro:hasPermission name="pop:ccmPopTenant:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmPopTenant">
				<tr>
					<td>${ccmPopTenant.buildDoorNum}</td>
					<td>${ccmPopTenant.doorNum}</td>
					<td>${ccmPopTenant.houseBuild}</td>
					<td>${ccmPopTenant.houseName}</td>
					<td>${ccmPopTenant.housePlace}</td>
					<td>${fns:getDictLabel(ccmPopTenant.houseType, 'ccm_pop_tenant_type', '')}</td>
					<td>${ccmPopTenant.area.name}</td>
					<td>${ccmPopTenant.buildingId.buildname}</td>
					<td><shiro:hasPermission name="pop:ccmPopTenant:edit">
						<a class="btnList"  href="javascript:;"  onclick="PopAdd(this,'${ccmPopTenant.id}','${bId}','${aId}')"   title="绑定"><i class="icon-plus"></i></a>
					</shiro:hasPermission></td>
						
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<script>
	function PopAdd(_this,id,bId,aId){
		var context = $(".context").attr("content");
		$.get(context+"/pop/ccmPopTenant/saveBuildAdd?id="+id+"&bId="+bId+"&aId="+aId+"",function(data){
			
		var len=data.length;
			if(len>0){
				var id=data[0]
				top.$.jBox.tip(data[1])
				$(_this).unbind('click');
				$(_this).children('i').css('color',"#ccc");
				$(_this).removeAttr("onclick")
				$(_this).css('cursor','not-allowed')
			}
		})
	}
	</script>
</body>
</html>