<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>脱贫攻坚管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shake/ccmShakePovertyPeople/">数据列表</a></li>
		<shiro:hasPermission name="shake:ccmShakePovertyPeople:edit"><li><a href="${ctx}/shake/ccmShakePovertyPeople/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmShakePovertyPeople" action="${ctx}/shake/ccmShakePovertyPeople/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属网格：</label>
				<sys:treeselect id="areaGridId" name="areaGridId.id" value="${ccmShakePovertyPeople.areaGridId.id}" labelName="areaGridId.name" labelValue="${ccmShakePovertyPeople.areaGridId.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>乡镇：</label>
				<sys:treeselect id="areaTownId" name="areaTownId.id" value="${ccmShakePovertyPeople.areaTownId.id}" labelName="areaTownId.name" labelValue="${ccmShakePovertyPeople.areaTownId.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>社区：</label>
				<sys:treeselect id="areaCommunityId" name="areaCommunityId.id" value="${ccmShakePovertyPeople.areaCommunityId.id}" labelName="areaCommunityId.name" labelValue="${ccmShakePovertyPeople.areaCommunityId.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>脱贫属性：</label>
				<form:select path="spAlleviation" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_shake_poverty_people_sp_alleviation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>所属网格</th>
				<th>公民身份号码</th>
				<th>出生日期</th>
				<th>建档时间</th>
				<th>乡镇</th>
				<th>社区</th>
				<th>脱贫属性</th>
				<th>贫困户属性</th>
				<th>致贫原因</th>
				<shiro:hasPermission name="shake:ccmShakePovertyPeople:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmShakePovertyPeople">
			<tr>
				<td><a href="${ctx}/shake/ccmShakePovertyPeople/form?id=${ccmShakePovertyPeople.id}">
					${ccmShakePovertyPeople.name}
				</a></td>
				<td>
					${fns:getDictLabel(ccmShakePovertyPeople.sex, 'sex', '')}
				</td>
				<td>
					${ccmShakePovertyPeople.areaGridId.name}
				</td>
				<td>
					${ccmShakePovertyPeople.ident}
				</td>
				<td>
					<fmt:formatDate value="${ccmShakePovertyPeople.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ccmShakePovertyPeople.filingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmShakePovertyPeople.areaTownId.name}
				</td>
				<td>
					${ccmShakePovertyPeople.areaCommunityId.name}
				</td>
				<td>
					${fns:getDictLabel(ccmShakePovertyPeople.spAlleviation, 'ccm_shake_poverty_people_sp_alleviation', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmShakePovertyPeople.spPeopleAlleviation, 'ccm_shake_poverty_people_sp_people_alleviation', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmShakePovertyPeople.spReason, 'ccm_shake_poverty_people_sp_reason', '')}
				</td>
				<shiro:hasPermission name="shake:ccmShakePovertyPeople:edit"><td>
    				<a class="btnList" href="${ctx}/shake/ccmShakePovertyPeople/form?id=${ccmShakePovertyPeople.id}" title="编辑"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/shake/ccmShakePovertyPeople/delete?id=${ccmShakePovertyPeople.id}" onclick="return confirmx('确认要删除该脱贫攻坚吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>