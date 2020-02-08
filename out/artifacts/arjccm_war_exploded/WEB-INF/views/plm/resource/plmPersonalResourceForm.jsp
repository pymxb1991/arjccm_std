<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>资源共享管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$('#btnSubmit').click(function(){
					$('#inputForm').submit();
				});
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
				$(".trtop").css({
					"width" : "12.5%"
				});
				$("td[colspan='2']").css({
					"width" : "22.5%"
				});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/resource/plmPersonalResource/?types=01">我的资源列表</a></li>
		<li class="active"><a
			href="${ctx}/resource/plmPersonalResource/form?id=${plmResource.id}&type=02">我的资源<shiro:hasPermission
					name="resource:plmPersonalResource:edit">${not empty plmResource.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="resource:plmPersonalResource:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="plmResource" style="margin: 0px 260px;"
		action="${ctx}/resource/plmPersonalResource/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h2>我的资源信息</h2>

		<table id="table" class="table   table-condensed" style="table-layout: fixed;">
			<tr>
				<td class="trtop">标题</td>
				<td colspan="2"><form:input path="name" htmlEscape="false"
						maxlength="128" class="input-xlarge required" /></td>
				<td style="text-align: center; font-weight: 800" colspan="2">附件</td>
				<td style="text-align: center; font-weight: 800" colspan="2">图片</td>
			</tr>
			<tr>
				<td class="trtop">提交人</td>
				<td colspan="2"><c:if test="${empty plmResource.user.id}">
						<sys:treeselect id="user" name="user.id"
							value="${fns:getUser().id}" labelName="user.name"
							labelValue="${fns:getUser().name}" title="用户"
							url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
							notAllowSelectParent="true" />
					</c:if> <c:if test="${not empty plmResource.user.id}">
					${plmResource.user.name }
				</c:if></td>
				<td colspan="2" style="text-align: center; height:200px; font-weight: 800"><form:hidden
						id="file" path="file" htmlEscape="false" maxlength="256"
						class="input-xlarge" /> <sys:ckfinder input="file" type="files"
						uploadPath="/resource/plmResource" selectMultiple="true" /></td>
				<td colspan="2" style="text-align: center; font-weight: 800"><form:hidden
						id="img" path="img" htmlEscape="false" maxlength="2048" value="${plmResource.img }"
						class="input-xlarge" /> <sys:ckfinder input="img" type="thumb"
						uploadPath="/resource/plmResource" selectMultiple="false" /></td>
			</tr>
			<tr>
				<td class="trtop">内容</td>
				<td colspan="6"><form:textarea path="body" htmlEscape="false"
						rows="4" maxlength="200" class="input-xxlarge " /> <sys:ckeditor
						replace="body" uploadPath="/resource/plmPersonalResource" /></td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="6"><form:textarea path="remarks"
						htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " /></td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="resource:plmPersonalResource:edit">
				<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>