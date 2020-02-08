<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${title}人员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function onAddClick(){
			var listId = $("#listId").val();
			var type = $("#type").val();
			parent.parent.LayerDialog("${ctx}/list/ccmListPeople/form?type=" + type + "&listId=" + listId, "添加", "1100px", "500px");
        }
		function onModifyClick(id){
			var type = $("#type").val();
			parent.parent.LayerDialog("${ctx}/list/ccmListPeople/form?type=" + type + "&id=" + id, "编辑", "1100px", "500px")
        }
	</script>
	<script src="${ctxStatic}/ccm/event/js/ccmEventIncident.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/list/ccmListPeople?type=${type}">${title}人员列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmListPeople" action="${ctx}/list/ccmListPeople/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="type" name="type" type="hidden" value="${type}"/>
		<input id="listId" name="listId" type="hidden" value="${listId}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>证件类型：</label>
				<form:select path="papersType" class="input-medium required">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_list_papers_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false"
						class="required" />
				</form:select>
			</li>
			<li><label>证件号码：</label>
				<form:input path="papersNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">
				<a onclick="onAddClick()" class="btn btn-success">
					<i class="icon-plus"></i> 添加
				</a>
			</li>
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
				<th>姓名</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>所属库</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="list:ccmListPeople:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmListPeople">
			<tr>
				<td>
					<a class="btnList" id="${ccmListPeople.id}" onclick="onModifyClick(this.id)" title="修改">
						${ccmListPeople.name}
					</a>
				</td>
				<td>
					${fns:getDictLabel(ccmListPeople.papersType, 'ccm_list_papers_type', '无')}
				</td>
				<td>
					${ccmListPeople.papersNumber}
				</td>
				<td>
					${ccmListPeople.listName}
				</td>
				<td>
					<fmt:formatDate value="${ccmListPeople.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="tp">
					${ccmListPeople.remarks}
				</td>
				<shiro:hasPermission name="list:ccmListPeople:edit">
				<td>
					<a class="btnList" id="${ccmListPeople.id}" onclick="onModifyClick(this.id)" title="修改">
						<i class="icon-pencil"></i>
					</a>
					<a class="btnList" href="${ctx}/list/ccmListPeople/delete?id=${ccmListPeople.id}&type=${type}"
							onclick="return confirmx('确认要删除该${title}人员吗？', this.href)" title="删除">
						<i class="icon-remove-sign"></i>
					</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>