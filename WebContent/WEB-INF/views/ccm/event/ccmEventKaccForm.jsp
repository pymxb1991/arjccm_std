<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点地区排查整治管理</title>
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
			$("td").css({"padding":"8px"});
			$("td").css({"border":"0px dashed #CCCCCC"});
		});
		
		function saveForm() {
			var area = $("#areaId").val();
			var html1 = '<label for="" class="error">必选字段 <label>';
			//alert(officeId.length);
			if (area.length != 0) {
				$("#show1").html("");
			} else {
				$("#show1").html(html1);
			}

			if (area.length != 0) {
				$("#inputForm").submit();
			}

		}
	</script>

	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/event/ccmEventKacc/map">排查整治分析</a></li>
		<li><a style="width: 140px;text-align:center" href="${ctx}/event/ccmEventKacc/">排查整治列表</a></li>
		<li class="active" style="width: 160px"><a class="nav-head" href="${ctx}/event/ccmEventKacc/form?id=${ccmEventKacc.id}">排查整治<shiro:hasPermission name="event:ccmEventKacc:edit">${not empty ccmEventKacc.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="event:ccmEventKacc:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmEventKacc" action="${ctx}/event/ccmEventKacc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%" >
			<h4 class="color-bg6">排查问题：</h4>
			<tr>
				<td style="width:50%;">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>治安重点地区：</label>
						<div class="controls">
							<form:input path="secuPlace" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td style="width:50%;">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span> 所属社区：</label>
						<div class="controls">
							<sys:treeselect id="area" name="area.id" value="${ccmEventKacc.area.id}" labelName="area.name" labelValue="${ccmEventKacc.area.name}"
					title="区域" url="/tree/ccmTree/treeDataArea?type=6" allowClear="true" notAllowSelectParent="true" cssClass=""/>
							<span class="help-inline"><font color="red" id="show1"></font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>治安突出问题：</label>
						<div class="controls">
							<form:select path="secuProb" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_sese_prob')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>涉及区域类型：</label>
						<div class="controls">
							<form:select path="distType" class="input-xlarge required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ccm_touc_regi')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>涉及区域范围：</label>
						<div class="controls">
							<form:input path="distScope" htmlEscape="false" maxlength="400" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>整治牵头单位：</label>
						<div class="controls">
							<form:input path="compLead" htmlEscape="false" maxlength="100" class="input-xlarge required"/>

						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">整治参与单位：</label>
						<div class="controls">
							<form:input path="compPart" htmlEscape="false" maxlength="400" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>整治牵头单位负责人姓名：</label>
						<div class="controls">
							<form:input path="compPrinName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>整治牵头单位负责人联系方式：</label>
						<div class="controls">
							<form:input path="compPrinPhone" htmlEscape="false" maxlength="50" class="input-xlarge required phone"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>整改时限：</label>
						<div class="controls">
							<input name="abarDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							style="width: 270px"
							value="<fmt:formatDate value="${ccmEventKacc.abarDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%" >
			<h4 class="color-bg6">整治结果：</h4>
			<tr>
				<td>
					<div>
						<label class="control-label">整治期间破获刑事案事件数：</label>
						<div class="controls">
							<form:input path="abarSolvNum" htmlEscape="false" maxlength="6" class="input-xlarge number digits"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">整治期间查处治安案事件数：</label>
						<div class="controls">
							<form:input path="abarInveNum" htmlEscape="false" maxlength="6" class="input-xlarge number digits"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>整治情况：</label>
						<div class="controls">
							<form:input path="abarCase" htmlEscape="false" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>效果评估：</label>
						<div class="controls">
							<form:select path="resuAsse" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_impa_eval')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
			
		</table>
		
		<div class="form-actions">
			<shiro:hasPermission name="event:ccmEventKacc:edit">
			<input id="btnSubmit" class="btn btn-primary"  onclick="saveForm()" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn"  type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>