<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点地区标准管理</title>
	<meta name="decorator" content="default"/>
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
	</script>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">工作助手</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/know/ccmKnowKeyRegion/">数据列表</a></li>
		<shiro:hasPermission name="know:ccmKnowKeyRegion:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/know/ccmKnowKeyRegion/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmKnowKeyRegion" action="${ctx}/know/ccmKnowKeyRegion/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>类别：</label>
				<form:select path="type" class="input-small">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_know_key_region_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>发布日期：</label>
				<input name="beginPublishDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmKnowKeyRegion.beginPublishDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> </li>
			<li class="first-line"><label>结束日期：</label>	<input name="endPublishDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmKnowKeyRegion.endPublishDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;
    /*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>标题</th>
				<th>类别</th>
				<th>发布日期</th>
				<th>备注信息</th>
				<shiro:hasPermission name="know:ccmKnowKeyRegion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmKnowKeyRegion">
			<tr>
				<td style="height: 50px"><a href="${ctx}/know/ccmKnowKeyRegion/form?id=${ccmKnowKeyRegion.id}">
					${ccmKnowKeyRegion.title}
				</a></td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmKnowKeyRegion.type, 'ccm_know_key_region_type', '')}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmKnowKeyRegion.publishDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="tp" style="height: 50px">
					${ccmKnowKeyRegion.remarks}
				</td>
				<shiro:hasPermission name="know:ccmKnowKeyRegion:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/know/ccmKnowKeyRegion/form?id=${ccmKnowKeyRegion.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/know/ccmKnowKeyRegion/delete?id=${ccmKnowKeyRegion.id}" onclick="return confirmx('确认要删除该重点地区标准吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>