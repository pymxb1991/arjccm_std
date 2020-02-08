<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>值班表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<br>
	<form:form id="inputForm" modelAttribute="ccmWorkBeonduty" action="${ctx}/work/ccmWorkBeonduty/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="padding-top: 8px">
			<label class="control-label">年月：</label>
			<div class="controls">
				<input name="months" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${ccmWorkBeonduty.clickDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间段：</label>
			<div class="controls">
			<input name="beginDatas" id="beginDatas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${beginDatas}" pattern="HH:mm"/>"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/> 
					<span>-</span>
			<input name="endDatas" id="endDatas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${endDatas}" pattern="HH:mm"/>"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
					</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门:</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id"
					value="${ccmWorkBeonduty.office.id}" labelName="office.name"
					labelValue="${ccmWorkBeonduty.office.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="required"
					notAllowSelectParent="false" />
			</div>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">值班负责人：</label>
			<div class="controls">
				<sys:treeselect id="principal" name="principal" value="${ccmWorkBeonduty.principal.id}" labelName="" labelValue="${ccmWorkBeonduty.principal.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">值班队伍：</label>
			<div class="controls">
				<form:textarea path="principalMans" htmlEscape="false" rows="2" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">值班地点：</label>
			<div class="controls">
				<form:textarea path="adds" htmlEscape="false" rows="2" maxlength="100" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作重点：</label>
			<div class="controls">
				<form:textarea path="details" htmlEscape="false" rows="12" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
	</form:form>
</body>
</html>