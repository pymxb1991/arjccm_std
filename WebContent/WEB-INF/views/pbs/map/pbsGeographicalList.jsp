<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地图信息表管理</title>
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
		<li class="active"><a href="${ctx}/map/pbsGeographical/">地图信息表列表</a></li>
		<shiro:hasPermission name="map:pbsGeographical:edit"><li><a href="${ctx}/map/pbsGeographical/form">地图信息表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsGeographical" action="${ctx}/map/pbsGeographical/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标注类型：</label>
				<form:select path="sType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('geotabletype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>地图类型：</label>
				<form:select path="sMaptype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('maptype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地点名称</th>
				<th>标注名称</th>
				<th>标注类型</th>
				<th>地图类型</th>
				<th>地理经度</th>
				<th>地理纬度</th>
				<th>更新时间</th>
				
				<shiro:hasPermission name="map:pbsGeographical:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsGeographical">
			<tr>
				<td><a href="${ctx}/map/pbsGeographical/form?id=${pbsGeographical.id}">
					${pbsGeographical.SDepartname}
				</a></td>
				<td>
					<c:if test="${pbsGeographical.SType eq '1'}">
						${pbsGeographical.officeName}
					</c:if>
                    <c:if test="${pbsGeographical.SType eq '2'}">
						${pbsGeographical.pbsPartymem.SName}
					</c:if>
					<c:if test="${pbsGeographical.SType eq '3'}">
						${pbsGeographical.sActivityid.STitle}
					</c:if>
					<c:if test="${pbsGeographical.SType eq '4'}">
						${pbsGeographical.sSuperiorid.SResume}
					</c:if>
                </td>
                <td>
                	${fns:getDictLabel(pbsGeographical.SType, 'geotabletype', '')}
                </td>
				<td>
					${fns:getDictLabel(pbsGeographical.SMaptype, 'maptype', '')}
				</td>
				<td>
					${pbsGeographical.SLongitude}
				</td>
				<td>
					${pbsGeographical.SLatitude}
				</td>
				<td>
					<fmt:formatDate value="${pbsGeographical.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				 
				<shiro:hasPermission name="map:pbsGeographical:edit"><td>
    				<a href="${ctx}/map/pbsGeographical/form?id=${pbsGeographical.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/map/pbsGeographical/delete?id=${pbsGeographical.id}" onclick="return confirmx('确认要删除该地图信息表吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>