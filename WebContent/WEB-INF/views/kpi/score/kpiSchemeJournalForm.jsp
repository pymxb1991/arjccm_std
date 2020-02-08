<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特殊考核项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function saveForm(){
			var userId = $("#userId").val();
			var html1 = '<label for="" class="error">必填信息 *<label>';
			//alert(userId.length);
			if(userId.length!=0){
				$("#show1").html("*");
			}else{
				$("#show1").html(html1);
			}

			if($("#score").val()<0 || $("#score").val()>100 ){
				parent.$.jBox.tip('得分百分制！ ');
				return;
			}
			
			if(userId.length!=0){
				$("#inputForm").submit();
			}
		}
	</script>
	<%--引入文本框外部样式--%>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/score/kpiSchemeJournal/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/score/kpiSchemeJournal/form?id=${kpiSchemeJournal.id}">数据<shiro:hasPermission name="score:kpiSchemeJournal:edit">${not empty kpiSchemeJournal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="score:kpiSchemeJournal:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="kpiSchemeJournal" action="${ctx}/score/kpiSchemeJournal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group head_Space" >
			<label class="control-label"><span class="help-inline"><font color="red" id="show1">*</font> </span>被考核人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${kpiSchemeJournal.user.id}" labelName="user.name" labelValue="${kpiSchemeJournal.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>分数：</label>
			<div class="controls">
				<form:input path="score" id="score" htmlEscape="false" class="input-xlarge required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>事由：</label>
			<div class="controls">
				<form:textarea path="reson" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>通报日期：</label>
			<div class="controls">
				<input name="notifyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${kpiSchemeJournal.notifyDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>通报形式：</label>
			<div class="controls">
				<form:input path="notifyType" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>通报人姓名：</label>
			<div class="controls">
				<form:input path="notifyStaffName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="score:kpiSchemeJournal:edit"><input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>