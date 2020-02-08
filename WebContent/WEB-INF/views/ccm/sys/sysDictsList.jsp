<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典树管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysDicts/">数据列表</a></li>
		<shiro:hasPermission name="sys:sysDicts:edit"><li><a href="${ctx}/sys/sysDicts/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysDicts" action="${ctx}/sys/sysDicts/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<%-- <li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>父级编号：</label>
			</li>
			<li><label>所有父级编号：</label>
				<form:input path="parentIds" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>数据值：</label>
				<form:input path="value" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>标签名：</label>
				<form:input path="label" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<li><label>类型：</label>
				<form:select id="type" path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${typeList}" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="type" htmlEscape="false" maxlength="100" class="input-medium"/> --%>
			</li>
			<li><label>描述：</label>
				<form:input path="description" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li style="color: red"><label></label>（请选择“类型”或“描述”进行查询！！！）</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>数据值</th>
				<th>标签名</th>
				<th>类型</th>
				<th>描述</th>
				<th>排序（升序）</th>
				<shiro:hasPermission name="sys:sysDicts:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td>
				{{row.value}}
			</td>
			<td><a href="${ctx}/sys/sysDicts/form?id={{row.id}}">
				{{row.label}}
			</a></td>
			<td>
				{{row.type}}
			</td>
			<td>
				{{row.description}}
			</td>
			<td>
				{{row.sort}}
			</td>
			<shiro:hasPermission name="sys:sysDicts:edit"><td>
   				<a href="${ctx}/sys/sysDicts/form?id={{row.id}}">修改</a>
				<a href="${ctx}/sys/sysDicts/delete?id={{row.id}}" onclick="return confirmx('确认要删除该字典树及所有子字典树吗？', this.href)">删除</a>
				<a href="${ctx}/sys/sysDicts/form?parent.id={{row.id}}">添加下级字典树</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>