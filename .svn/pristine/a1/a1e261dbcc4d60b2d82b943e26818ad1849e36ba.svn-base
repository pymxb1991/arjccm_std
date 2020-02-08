<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>党组织管理管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css" />
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
        		$("#searchForm").submit();
        	})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        // function btnSubmit() {
        //     $("#searchForm").submit();
        // }
		//详情弹框--不刷新父页面
		function LayerDialog(src, title, height, width) {
			layer.open({
				type : 2,
				title : title,
				area : [ height, width ],
				fixed : true, //固定
				maxmin : true,
				//btn: ['确定', '关闭'], //可以无限个按钮
				content : src,
			});
		}
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/partybuild/ccmPartyOrganiz/">党组织管理列表</a></li>
<%--		<shiro:hasPermission name="partybuild:ccmPartyOrganiz:edit">
            <li><a href="${ctx}/partybuild/ccmPartyOrganiz/form">党组织管理添加</a></li>
        </shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPartyOrganiz" action="${ctx}/partybuild/ccmPartyOrganiz/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <input id="type" name="type" type="hidden" value="1"/>
		<ul class="ul-form">
			<li><label>组织名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>选择社区：</label>
				<sys:treeselect id="community" name="community.id" value="${ccmPartyOrganiz.community.id}"
								labelName="community.name" labelValue="${ccmPartyOrganiz.community.name}"
								title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>党组织编号：</label>
				<form:input path="orgCode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><a
					onclick="parent.parent.parent.LayerDialog('${ctx}/partybuild/ccmPartyOrganiz/form?type=1', '添加', '1330px', '800px')"
					class="btn btn-success"><i class="icon-plus"></i> 添加</a></li>
			<%--<li class="btns"><input id="btnSubmit" onclick="btnSubmit()" class="btn btn-primary" type="button" value="查询"/></li>--%>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary"> 
					<i class="icon-search"></i> 查询
				</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>组织名称</th>
				<th>选择社区</th>
				<th>组织属性</th>
				<th>党组织编号</th>
				<th>创建时间</th>
				<shiro:hasPermission name="partybuild:ccmPartyOrganiz:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPartyOrganiz">
			<tr>
				<td><a onclick="parent.parent.parent.LayerDialog('${ctx}/partybuild/ccmPartyOrganiz/form?id=${ccmPartyOrganiz.id}', '修改', '1330px', '800px')">
					${ccmPartyOrganiz.name}
				</a></td>
				<td>
					${ccmPartyOrganiz.community.name}
				</td>
				<td>
					${fns:getDictLabel(ccmPartyOrganiz.orgAttr, 'ccm_party_attr', '')}
				</td>
				<td>
					${ccmPartyOrganiz.orgCode}
				</td>
				<td>
					<fmt:formatDate value="${ccmPartyOrganiz.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="partybuild:ccmPartyOrganiz:edit"><td>
					<a  class="btnList"
						onclick="parent.parent.parent.LayerDialog('${ctx}/partybuild/ccmPartyOrganiz/form?id=${ccmPartyOrganiz.id}', '修改', '1330px', '800px')"><i class="icon-pencil"></i></a>
					<a  class="btnList"
						href="${ctx}/partybuild/ccmPartyOrganiz/delete?id=${ccmPartyOrganiz.id}"
						onclick="return confirmx('确认要删除该党组织管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>