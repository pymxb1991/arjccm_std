<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>通讯录管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready({
			});
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="plmEmployee"
		action="${ctx}/addressbook/plmEmployee/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h2>人员信息</h2>
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop">姓名：</td>
				<td colspan="2">${plmEmployee.name }</td>
				<td class="trtop">英文姓名：</td>
				<td colspan="2">${plmEmployee.ename}</td>
				<td style="text-align: center; font-weight: 800" class="trtop">照片：</td>
			</tr>
			<tr>
				<td class="trtop">性别：</td>
				<td colspan="2">${fns:getDictLabel(plmEmployee.sex,'sex','')}</td>
				<td class="trtop">所在部门： </td>
				<td colspan="2">${plmEmployee.dePid.name }</td>
				<td class="trtop" rowspan="6"><input id="imgul" name="imgul"
					type="hidden" value="${plmEmployee.imgul}" /> <sys:ckfinder
						input="imgul" type="thumb" uploadPath="/addressbook/plmEmployee"
						selectMultiple="false" /></td>
			</tr>
			<tr>
				<td class="trtop">职务 ： </td>
				<td colspan="2"><form:input path="duty" htmlEscape="false"
						maxlength="64" class="input-xlarge " /></td>
				<td class="trtop">就职状态： </td>
				<td colspan="2">${fns:getDictLabel(plmEmployee.state,'plm_state','')}</td>
			</tr>
			<tr>
				<td class="trtop">联系电话：</td>
				<td colspan="2">${plmEmployee.phoneone}</td>
				<td class="trtop">备用联系电话：</td>
				<td colspan="2">${plmEmployee.phonetwo}</td>
			</tr>
			<tr>
				<td class="trtop">工作人员编号 ：</td>
				<td colspan="2">${plmEmployee.accounts}</td>
				<td class="trtop">电子邮箱：</td>
				<td colspan="2">${plmEmployee.email}</td>
			</tr>
			<tr>
				<td class="trtop">现住址 ：</td>
				<td colspan="2">${plmEmployee.nowPlace}</td>
				<td class="trtop">家庭住址：</td>
				<td colspan="2">${plmEmployee.familyPlace}</td>
			</tr>
			<tr>
				<td class="trtop">紧急联系人：：</td>
				<td colspan="2">${plmEmployee.exigenceMan}</td>
				<td class="trtop">紧急联系人电话：</td>
				<td colspan="2">${plmEmployee.exigencePhone}</td>
			</tr>
			<tr>
				<td class="trtop">备注：</td>
				<td colspan="6"><form:textarea path="remarks"
						htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " /></td>
			</tr>
		</table>
		<div class="form-actions">
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>