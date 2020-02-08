<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>政策法规管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	include('ckeditor_lib', '/arjplm/static/ckeditor/', [ 'ckeditor.js' ]);
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
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
			});
</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="plmKnowPolicy"
		action="${ctx}/policy/plmKnowPolicyAct/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h2>政策法规信息</h2>
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop">标题：</td>
				<td colspan="2">${plmKnowPolicy.name}</td>
				<td class="trtop">效力级别：</td>
				<td colspan="3">${fns:getDictLabel(plmKnowPolicy.level, 'plm_effe_level', '')}</td>
				
			</tr>
			<tr>
				<td class="trtop">类别</td>
				<td colspan="2">${fns:getDictLabel(plmKnowPolicy.type, 'sys_effe_level', '')}</td>
				<td class="trtop">批准部门：</td>
				<td colspan="3">${plmKnowPolicy.ratifyDept}</td>
				
			</tr>
			<tr>
				<td class="trtop">发文字号：</td>
				<td colspan="2">${plmKnowPolicy.lssNo }</td>
				<td class="trtop">批准日期：</td>
				<td colspan="3"><fmt:formatDate value="${plmKnowPolicy.ratifyDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="trtop">实施日期：</td>
				<td colspan="2"><fmt:formatDate value="${plmKnowPolicy.impDate }" pattern="yyyy-MM-dd"/></td>
				<td class="trtop">发布部门：</td>
				<td colspan="3">${plmKnowPolicy.relDept}</td>
			</tr>
			<tr>
				<td class="trtop">时效性：</td>
				<td colspan="2">${plmKnowPolicy.prescription}</td>

				<td class="trtop">发布日期：</td>
				<td colspan="3"><fmt:formatDate value="${plmKnowPolicy.relDate }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
			<td class="trtop">附件</td>
				
				<td   colspan="6" ><form:hidden id="file1" path="file1"
						htmlEscape="false" /> <sys:ckfinder input="file1" type="files"
						uploadPath="/policy/plmKnowPolicy" selectMultiple="true" readonly="true" /></td>		
			
			</tr>
			<input name="types" type="text" style="display: none"
				readonly="readonly" value="2" pattern="" />
			<tr>
				<td class="trtop">内容：</td>
				<td id="bodyHtml" colspan="6">  <%-- <form:textarea name="content" htmlEscape="false"
						path="content" rows="4" maxlength="200"
						class="input-xxlarge" /> <sys:ckeditor replace="content"
						uploadPath="/policy/plmKnowPolicyAct" />  --%>
						${plmKnowPolicy.content}
				</td>
			<script>
			$(document).ready(function() {
			   $("#bodyHtml").html( $("#bodyHtml").text())
			});</script>
						</td>
			</tr>
			<tr>
				<td class="trtop">备注：</td>
				<td colspan="6">${plmKnowPolicy.remarks }</td>
			</tr>
		</table>
		<div class="form-actions">
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>