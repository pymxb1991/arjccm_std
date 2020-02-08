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
  
  function changeList(){
	  window.location.href = ctx+"/person/pbsPartymem/";
  }
</script>
<style type="text/css">
	.pbsImg:hover .pbsInfo{
		display:block;
		
	}

</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/person/pbsPartymem/image">学员信息列表</a></li>
		<shiro:hasPermission name="person:pbsPartymem:edit">
			<li><a href="${ctx}/person/pbsPartymem/form">学员信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsPartymem"
		action="${ctx}/person/pbsPartymem/image" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>证件号码：</label> <form:input path="SIdnum"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>证件类别：</label> <form:select path="sIdtype"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('sys_idtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>学员类型：</label> <form:select path="iType"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('sys_mebcategory')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="clearfix"></li>
			<li><label>姓名：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>性别：</label> <form:select path="iSex"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('sex')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>民族信息：</label> <form:select path="iNation"
					class="input-medium">
					<form:option value="" label="" />
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
				<input onclick="changeList()" class="btn btn-primary" type="button" value="切换列表模式"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<c:set var="dataPbsPartymem" value="${page.list}"></c:set>
	<div>
		<c:forEach items="#{page.list}" var="pbsPartymem">
			<div style="margin-left:30px;width:23%;height:200px;float:left;margin-top:30px;">
				<div style="height:100%;width:21%; background: -webkit-gradient(linear, left top, left bottom, from(#E8E8E8), to(#B2B4B4));float:left">
					<div style="height:55%">
						<img onerror="this.src='${ctxStatic}/images/headPC.png'" src="${pbsPartymem.getSPhoto()}"  style="height:60px;width:60px;margin-top:20px;margin-left:10px"/>
					</div>
					<div style="padding-left:20%">
						<form id="subform" action="${ctx}/person/pbsPartymem/checkDetail"
							method="post" style="display: none">
							<input type="hidden" name="id" value="${pbsPartymem.id}" />
							<c:forEach items="${dataPbsPartymem}" var="getPbsPartymem">
								<input type="hidden" name="obj" value="${getPbsPartymem.id}" />
							</c:forEach>
						</form> <a href="#"
						onclick="document.getElementById('subform').submit();return false"><i
						title="人物信息图"	class="icon icon-group"></i></a> <a
						href="${ctx}/person/pbsPartymem/form?id=${pbsPartymem.id}"
						title="修改"><i class="icon icon-pencil"></i></a> <a
						href="${ctx}/person/pbsPartymem/delete?id=${pbsPartymem.id}"
						onclick="return confirmx('确认要删除该学员信息吗？', this.href)" title="删除"><i
							class="icon icon-trash"></i></a>
						</div>	
						<div style="padding-left:20%;margin-top:10px">	
							 <a
						href="${ctx}/person/pbsPartymembind/adduser?partymemid=${pbsPartymem.id}&sSource=sys_user"><i
							class="icon icon-plus" title="添加学员用户关系"></i></a> <a
						href="${ctx}/person/pbsDepartmentbind/addoffice?partymemid=${pbsPartymem.id}&sSource=sys_office"><i
						class="icon icon-plus-sign" title="添加学员部门关系"></i></a>
						</div>
				</div>
				<div style="height:100%;margin-left:5px;width:70%; background: -webkit-gradient(linear, left top, left bottom, from(#E8E8E8), to(#B2B4B4)); float:left;">
					<p style="font-size:15px;maring-left:-5px;line-height:20px;padding-top:30px;padding-left:5%">学员姓名：${pbsPartymem.SName}</p>
					<p style="font-size:15px;maring-left:-5px;line-height:20px;padding-left:5%">证件类别：${fns:getDictLabel(pbsPartymem.SIdtype,'sys_idtype',"")}</p>
					<p style="font-size:15px;maring-left:-5px;line-height:20px;padding-left:5%">入党时间：<fmt:formatDate value="${pbsPartymem.dtAdmission}"
							pattern="yyyy-MM-dd" /></p>
					<p style="font-size:15px;maring-left:-5px;line-height:20px;padding-left:5%">更新时间：<fmt:formatDate value="${pbsPartymem.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></p>
					<p style="font-size:15px;maring-left:-5px;line-height:20px;padding-left:5%">身份证号：${pbsPartymem.SIdnum}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="pagination" style="clear:both;margin-left:30px;padding-top:10px">${page}</div>
</body>
</html>