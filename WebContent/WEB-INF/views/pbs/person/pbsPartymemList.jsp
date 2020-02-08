<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(function() {

  });
  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }
  function change(){
	  window.location.href = ctx+"/person/pbsPartymem/image";
  }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/person/pbsPartymem/">学员信息列表</a></li>
		<shiro:hasPermission name="person:pbsPartymem:edit">
			<li><a href="${ctx}/person/pbsPartymem/form">学员信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsPartymem"
		action="${ctx}/person/pbsPartymem/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>证件号码：</label> <form:input path="SIdnum"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>证件类别：</label> <form:select path="sIdtype"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('sys_idtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>学员类型：</label> <form:select path="iType"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('sys_mebcategory')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="clearfix"></li>
			<li><label>姓名：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>性别：</label> <form:select path="iSex"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('sex')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>民族信息：</label> <form:select path="iNation"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('sys_volk')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>出生日期：</label> <input name="beginDtBirth" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsPartymem.beginDtBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
				- <input name="endDtBirth" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsPartymem.endDtBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns">
				<input onclick="change()" class="btn btn-primary" type="button" value="切换图片模式"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center">学员头像</th>
				<th>学员姓名</th>
				<th>证件类别</th>
				<th>身份证号码</th>
				<th>人物标签</th>
				<th>人物标签描述</th>
				<th>入党日期</th>
				<th>更新时间</th>
				<shiro:hasPermission name="person:pbsPartymem:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:set var="dataPbsPartymem" value="${page.list}"></c:set>
			<c:forEach items="${page.list}" var="pbsPartymem">
				<tr>
					<td style="width:60px;text-align:center">
						<img onerror="this.src='${ctxStatic}/images/headPC.png'" src="${pbsPartymem.getSPhoto()}" style="width:40px;height:40px"/>
					</td>
					<td><a
						href="${ctx}/person/pbsPartymem/form?id=${pbsPartymem.id}">${pbsPartymem.SName}</a></td>
					<td>${fns:getDictLabel(pbsPartymem.SIdtype,'sys_idtype',"")}</td>
					<td>${pbsPartymem.SIdnum}</td>
					<td>
						<c:if test="${not empty pbsPartymem.pbsMemlabel.SLabelids}">
							<c:set value="${fn:split(pbsPartymem.pbsMemlabel.SLabelids,',')}" var="pbsTypeList"/>
							<c:forEach items="${pbsTypeList}" var="pbsType" varStatus="index">
								<c:forEach items="${pbsLabelinfos}" var="pbsLabelList">
									<c:if test="${pbsType eq pbsLabelList.SType}">
										<c:if test ="${index.index eq 0}">
											${pbsLabelList.SName}
										</c:if>
										<c:if test ="${index.index ne 0}">
											，${pbsLabelList.SName}
										</c:if>
									</c:if>
								</c:forEach>
							</c:forEach>
						 </c:if>
					</td>
					<td>${pbsPartymem.pbsMemlabel.SDescription}</td>
					<td><fmt:formatDate value="${pbsPartymem.dtAdmission}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${pbsPartymem.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="person:pbsPartymem:edit">
						<td>
							<form id="subform${pbsPartymem.id}" action="${ctx}/person/pbsPartymem/checkDetail"
								method="post" style="display: none">
								<input type="hidden" name="id" value="${pbsPartymem.id}" />
								<c:forEach items="${dataPbsPartymem}" var="getPbsPartymem">
									<input type="hidden" name="obj" value="${getPbsPartymem.id}" />
								</c:forEach>
							</form> <a href="#"
							onclick="document.getElementById('subform${pbsPartymem.id}').submit();return false"><i
							title="人物信息图"	class="icon icon-group"></i></a> <a
							href="${ctx}/person/pbsPartymem/form?id=${pbsPartymem.id}"
							title="修改"><i class="icon icon-pencil"></i></a> <a
							href="${ctx}/person/pbsPartymem/delete?id=${pbsPartymem.id}"
							onclick="return confirmx('确认要删除该学员信息吗？', this.href)" title="删除"><i
								class="icon icon-trash"></i></a> <a
							href="${ctx}/person/pbsPartymembind/adduser?partymemid=${pbsPartymem.id}&sSource=sys_user"><i
								class="icon icon-plus" title="添加学员用户关系"></i></a> <a
							href="${ctx}/person/pbsDepartmentbind/addoffice?partymemid=${pbsPartymem.id}&sSource=sys_office&pageTurn=add"><i
								class="icon icon-plus-sign" title="添加学员部门关系"></i></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>