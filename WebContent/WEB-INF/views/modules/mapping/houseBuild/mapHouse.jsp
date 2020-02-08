<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<ul id="myTab" class="nav nav-tabs" style="display:block;position:unset;">
	<li class="active"><a href="#houseTab" data-toggle="tab">房屋基础信息</a></li>
	<li><a href="#pop" data-toggle="tab">人员信息</a></li>
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="houseTab">
		<table
			class="houseMap table table-striped table-bordered table-condensed">
			<tr>
				<td colspan="1">房主姓名：</td>
				<td colspan="3">${ccmPopTenant.houseName}</td>
			</tr>
			<tr>
				<td colspan="1">房主联系方式：</td>
				<td colspan="3">${ccmPopTenant.houseTl}</td>
			</tr>
			<tr>
				<td>状态：</td>
				<td>
					${fns:getDictLabel(ccmPopTenant.houseType,'ccm_pop_tenant_type',"")}
				</td>
			</tr>
			
<tr>
				<td colspan="1">房屋编号：</td>
				<td colspan="2">${ccmPopTenant.houseBuild}
				<td rowspan="5" width="30%" align="center"><img alt=""
					src="${ccmHouseBuildmanage.images}"></td>
			</tr>
			<tr>
				<td>房屋地址：</td>
				<td>${ccmPopTenant.housePlace}</td>
			</tr>
			<tr>
				<td>建筑用途：</td>
				<td>
					${fns:getDictLabel(ccmPopTenant.housePrup,'ccm_str_use',"")}</td>
			</tr>
			<tr>
				<td>建筑面积：</td>
				<td>${ccmPopTenant.houseArea}</td>
			</tr>
			<tr>
				<td colspan="1">证件类型：</td>
				<td colspan="3">
					${fns:getDictLabel(ccmPopTenant.idenCode,'sys_ccm_org_papers',"")}</td>
			</tr>
			<tr>
				<td colspan="1">证件号码：</td>
				<td colspan="3">${ccmPopTenant.idenNum}</td>

			</tr>
			<tr>
				<td colspan="1">房主现居住详址：</td>
				<td colspan="3">${ccmPopTenant.houseCur}</td>
			</tr>

			<tr>
				<td colspan="1">出租用途：</td>
				<td colspan="3">${ccmPopTenant.housePlace}
					${fns:getDictLabel(ccmPopTenant.housePlace,'ccm_let_use',"")}</td>
			</tr>
			<tr>
				<td colspan="1">隐患类型：</td>
				<td colspan="3">
					${fns:getDictLabel(ccmPopTenant.hazard,'ccm_hidd_type',"")}</td>
			</tr>
			<tr>
				<td colspan="1">承租人身份号：</td>
				<td colspan="3">${ccmPopTenant.tenantId}</td>
			</tr>
			<tr>
				<td colspan="1">承租人姓名：</td>
				<td colspan="3">${ccmPopTenant.tenantName}</td>
			</tr>
		</table>
	</div>

	<!-- 人员列表信息 -->
	<div class="tab-pane fade" id="pop">
		<div>楼栋名称:${buildName}, 第${ccmPopTenant.floorNum}层</div>
		<table class="table table-striped table-bordered table-condensed">
			<tr>
				<td>姓名</td>
				<td>重点人员类型</td>
				<td>公民身份号码</td>
				<td>联系方式</td>
				<td>与户主关系</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list}" var="ccmHouse">
				<tr>
					<td>${ccmHouse.name}</td>
					<td style="color: #e63500">${ccmHouse.more1}</td>
					<td>${ccmHouse.ident}</td>
					<td>${ccmHouse.telephone}</td>
					<td>
						${fns:getDictLabel(ccmHouse.accountrelation,'sys_ccm_fami_ties',"")}</td>
					<td><a class="popclick btn btn-success" href="###"
						popId="${ccmHouse.id}" buildName="${buildName}"
						elemNum="${elemNum}" pilesNum="${pilesNum}">查看</a></td>
				</tr>
			</c:forEach>
			<c:if test="${  empty list}">
				<tr>
					<td>暂无数据</td>
					<td style="color: #e63500"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
		</table>
	</div>

</div>







