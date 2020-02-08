<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>双报道情况管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            if('${type}' == '1'){
                document.getElementById("contentTablePer").style.display = "none";
            }else{
                document.getElementById("contentTableOrg").style.display = "none";
                $("#orgParty").text("党    员：")
            }
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

        function partyOrg() {
			$("#type").val("1");
			$('#type', parent.document).val("1");
			$("#searchForm").submit();
		}
		function partyPerson() {
			$("#orgParty").text("党    员：")
			// $("#contentTablePer").css("display", "block")
			// $("#contentTableOrg").css("display","none")
			// document.getElementById("contentTablePer").style.display = "table";
			// document.getElementById("contentTableOrg").style.display = "none";
			$("#add").attr("onclick","parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?type=2', '添加','1120px', '350px')");
			$("#orgParty").text("党  员：")
			// $("#contentTablePer").css("display", "block")
			// $("#contentTableOrg").css("display","none")
			// document.getElementById("contentTablePer").style.display = "table";
			// document.getElementById("contentTableOrg").style.display = "none";
			$("#add").attr("onclick","parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?type=2', '添加','1120px', '350px')");
			$("#type").val("2");
			$('#type', parent.document).val("2");
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>双报道情况管理列表</a></li>
		<%--<shiro:hasPermission name="partyreport:ccmPartyReport:edit"><li><a href="${ctx}/partyreport/ccmPartyReport/form">双报道情况管理添加</a></li></shiro:hasPermission>--%>
		<li style="float:right"><a class="btn btn-danger" onclick="partyPerson()">党    员</a></li>
		<li style="float:right"><a class="btn btn-primary" onclick="partyOrg()">党组织</a></li>
	</ul>
	<!-- <div class="site-demo-button" style="margin-bottom: 0;    padding-bottom: 5px;">
		<button type="button" class="btn btn-primary" onclick="partyOrg()">党组织</button>
		<button type="button" class="btn btn-danger" onclick="partyPerson()">党员</button>
	</div>
	<div> -->
	<%--<shiro:hasPermission name="partyreport:ccmPartyReport:edit"><li><a href="${ctx}/partyreport/ccmPartyReport/form">双报道情况管理添加</a></li></shiro:hasPermission>--%>

	<form:form id="searchForm" modelAttribute="ccmPartyReport" action="${ctx}/partyreport/ccmPartyReport/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="type" name="type" type="hidden" value="${type}"/>
		<ul class="ul-form" >
			<li>
				<label id="orgParty">
				<c:if test="${type == 1}">
				党组织：
				</c:if>
				<c:if test="${type == 2}">
				党  员：
				</c:if>
				</label>
				<form:input path="orgParty" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><a id="add"
					onclick="parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?type=${type}', '添加', '1120px', '350px')"
					class="btn btn-success"><i class="icon-plus"></i> 添加</a></li>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary"> 
					<i class="icon-search"></i> 查询
				</a>
			</li>
			<li class="clearfix"></li>
		</ul>

	</form:form>

	</div>
	<sys:message content="${message}"/>
	<table id="contentTableOrg" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>组织名称</th>
			<th>所属社区</th>
			<th>报道时间</th>
			<th>通讯地址</th>
			<th>组织属性</th>
			<th>联系电话</th>
			<shiro:hasPermission name="partyreport:ccmPartyReport:edit"><th>操作</th></shiro:hasPermission>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPartyReport">
			<tr>
				<td><a onclick="parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?id=${ccmPartyReport.id}', '修改', '1100px', '700px')">
						${ccmPartyReport.orgPartyEntity.name}
				</a></td>
				<td>
						${ccmPartyReport.community.name}
				</td>
				<td>
					<fmt:formatDate value="${ccmPartyReport.reportingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                        ${ccmPartyReport.orgPartyEntity.address}
				</td>
				<td>
                        ${ccmPartyReport.orgPartyEntity.orgAttr}
				</td>
				<td>
                        ${ccmPartyReport.orgPartyEntity.telphone}
				</td>
				<shiro:hasPermission name="partyreport:ccmPartyReport:edit"><td>
					<%--<a href="${ctx}/partyreport/ccmPartyReport/form?id=${ccmPartyReport.id}">修改</a>
					<a href="${ctx}/partyreport/ccmPartyReport/delete?id=${ccmPartyReport.id}" onclick="return confirmx('确认要删除该双报道情况管理吗？', this.href)">删除</a>--%>
						<a  class="btnList"
							onclick="parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?id=${ccmPartyReport.id}', '修改', '1100px', '700px')"><i class="icon-pencil"></i></a>
						<a  class="btnList"
							href="${ctx}/partyreport/ccmPartyReport/delete?id=${ccmPartyReport.id}"
							onclick="return confirmx('确认要删除该双报道情况管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table id="contentTablePer" class="table table-striped table-bordered table-condensed" >
		<thead>
		<tr>
			<th>姓名</th>
			<th>所属社区</th>
			<th>报道时间</th>
			<th>专业</th>
			<th>教育类别</th>
			<shiro:hasPermission name="partyreport:ccmPartyReport:edit"><th>操作</th></shiro:hasPermission>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPartyReport">
			<tr>
				<td><a onclick="parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?id=${ccmPartyReport.id}', '修改', '1100px', '700px')">
						${ccmPartyReport.perPartyEntity.name}
				</a></td>
				<td>
						${ccmPartyReport.community.name}
				</td>
				<td>
					<fmt:formatDate value="${ccmPartyReport.reportingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                        ${ccmPartyReport.perPartyEntity.profession}
				</td>
				<td>
                        ${ccmPartyReport.perPartyEntity.educationCategory}
				</td>
				<shiro:hasPermission name="partyreport:ccmPartyReport:edit"><td>
					<%--<a href="${ctx}/partyreport/ccmPartyReport/form?id=${ccmPartyReport.id}">修改</a>
					<a href="${ctx}/partyreport/ccmPartyReport/delete?id=${ccmPartyReport.id}" onclick="return confirmx('确认要删除该双报道情况管理吗？', this.href)">删除</a>--%>
						<a  class="btnList"
							onclick="parent.parent.parent.LayerDialog('${ctx}/partyreport/ccmPartyReport/form?id=${ccmPartyReport.id}', '修改', '1100px', '700px')"><i class="icon-pencil"></i></a>
						<a  class="btnList"
							href="${ctx}/partyreport/ccmPartyReport/delete?id=${ccmPartyReport.id}"
							onclick="return confirmx('确认要删除该双报道情况管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>