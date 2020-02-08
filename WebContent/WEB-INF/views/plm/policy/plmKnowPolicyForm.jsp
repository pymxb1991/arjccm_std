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
				$('#btnSubmit').click(function(){
					$('#inputForm').submit();
				});
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										var ratify = $("#ratifyDate").val();//批准日期
										var imp = $("#impDate").val();//实施日期
										var rel = $("#relDate").val();//发布日期
										if (ratify > imp) {
											alert("实施日期不能小于批准日期");
											return false;
										} else if (ratify > rel) {
											alert("发布日期不能小于批准日期");
											return false;
										} else if (imp < rel) {
											alert("实施日期不能小于发布日期");
											return false;
										} else {
											$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
											form.submit();
										}
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/policy/plmKnowPolicy/?types=1">规章制度管理列表</a></li>
		<li class="active"><a
			href="${ctx}/policy/plmKnowPolicy/form?id=${plmKnowPolicy.id}">规章制度<shiro:hasPermission
					name="policy:plmKnowPolicy:edit">${not empty plmKnowPolicy.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="policy:plmKnowPolicy:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="plmKnowPolicy"
		action="${ctx}/policy/plmKnowPolicy/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h2>规章制度表单</h2>
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop">标题<span class="help-inline"><font
						color="red">*</font></td>
				<td colspan="2"><input id="name" name="name"
					class="input-xlarge required" type="text"
					value="${plmKnowPolicy.name} " maxlength="512" /></td>
				<td class="trtop">效力级别<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="3"><form:select path="level"
						class="input-xlarge required">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('plm_effe_level')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></td>
				
			</tr>
			<tr>
				<td class="trtop">类别<span class="help-inline"><font
						color="red">*</font></td>
				<td colspan="2"><form:select path="type"
						class="input-xlarge required">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sys_effe_level')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></td>
				<td class="trtop">批准部门</td>
				<td colspan="3"><input id="ratifyDept" name="ratifyDept"
					class="input-xlarge " type="text"
					value="${plmKnowPolicy.ratifyDept}" maxlength="64" /></td>
				
			</tr>
			<tr>
				<td class="trtop">发文字号</td>
				<td colspan="2"><input id="lssNo" name="lssNo"
					class="input-xlarge " type="text" value="${plmKnowPolicy.lssNo }"
					maxlength="64" /></td>
				<td class="trtop">批准日期<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="3"><input id="ratifyDate" name="ratifyDate"
					type="text" readonly="readonly" maxlength="20"
					class="input-medium required Wdate "
					value="<fmt:formatDate  value="${plmKnowPolicy.ratifyDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" /></td>
			</tr>
			<tr>
				<td class="trtop">发布日期<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="2"><input id="relDate" name="relDate" type="text"
					readonly="readonly" maxlength="20"
					class="input-medium required Wdate "
					value="<fmt:formatDate value="${plmKnowPolicy.relDate }" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" /></td>
				<td class="trtop">发布部门</td>
				<td colspan="3"><input id="relDept" name="relDept"
					class="input-xlarge " type="text" value="${plmKnowPolicy.relDept}"
					maxlength="64" /></td>
			</tr>
			<tr>
				<td class="trtop">时效性</td>
				<td colspan="2"><input id="prescription" name="prescription"
					class="input-xlarge " type="text"
					value="${plmKnowPolicy.prescription}" maxlength="64" /></td>
				<td class="trtop">实施日期<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="3"><input id="impDate" name="impDate" type="text"
					readonly="readonly" maxlength="20"
					class="input-medium required Wdate "
					value="<fmt:formatDate value="${plmKnowPolicy.impDate }" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" /></td>
			</tr>
			<tr>
			<td class="trtop">附件</td>
				
				<td   colspan="6" ><form:hidden id="file1" path="file1"
						htmlEscape="false" /> <sys:ckfinder input="file1" type="files"
						uploadPath="/policy/plmKnowPolicy" selectMultiple="true" /></td>		
			
			</tr>
			<tr>
				<td class="trtop">内容</td>
				<td colspan="6"><form:textarea id="content" path="content"
						htmlEscape="false" rows="4" class="input-xxlarge "
						cssStyle="width:76.296%" /> <sys:ckeditor replace="content" /></td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="6"><form:textarea path="remarks"
						htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " /></td>
			</tr>
			<input name="types" type="text" readonly="readonly" value="1"
				style="display: none" pattern="" />
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="policy:plmKnowPolicy:edit">
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>