<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时提醒管理</title>
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
			if(userId.length!=0){
				$("#show1").html("*");
			}else{
				$("#show1").html(html1);
			}

			if(userId.length!=0){
				$("#inputForm").submit();
			}
			
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/work/ccmWorkTiming/">定时提醒列表</a></li> --%>
		<%-- <li class="active"><a href="${ctx}/work/ccmWorkTiming/form?id=${ccmWorkTiming.id}">定时提醒<shiro:hasPermission name="work:ccmWorkTiming:edit">${not empty ccmWorkTiming.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:ccmWorkTiming:edit">查看</shiro:lacksPermission></a></li> --%>
		<li class="active"><a href="${ctx}/work/ccmWorkTiming/form?id=${ccmWorkTiming.id}">定时提醒${not empty ccmWorkTiming.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmWorkTiming" action="${ctx}/work/ccmWorkTiming/saveTiming" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">提醒时间：</label>
			<div class="controls">
				<input name="timing" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmWorkTiming.timing}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提醒人员：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${ccmWorkTiming.user.id}" labelName="user.name" labelValue="${ccmWorkTiming.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red" id="show1">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提醒内容：</label>
			<div class="controls">
				<form:textarea path="details" htmlEscape="false" rows="6" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="work:ccmWorkTiming:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;
<!-- 			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
 -->		<input id="btnCancel" class="btn btn-primary" type="button" value="关 闭" onclick="parent.layer.closeAll()" /></div>
	</form:form>
</body>
</html>