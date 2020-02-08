<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>群防群治组织管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
		<%-- <li><a href="${ctx}/org/ccmOrgOrgprevent/">数据列表</a></li>
		<li class="active"><a href="${ctx}/org/ccmOrgOrgprevent/form?id=${ccmOrgOrgprevent.id}">数据<shiro:hasPermission name="org:ccmOrgOrgprevent:edit">${not empty ccmOrgOrgprevent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgOrgprevent:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmOrgOrgprevent" action="${ctx}/org/ccmOrgOrgprevent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
		<!-- 	
			<label class="control-label">父级编号：</label>
			<div class="controls">
				<form:input path="more2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
			<label class="control-label">所有父级编号：</label>
			<div class="controls">
				<form:input path="more3" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="more4" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
			<label class="control-label">区域编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		 -->
		<tr>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font></span>归属区域：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${ccmOrgOrgprevent.area.id}" labelName="area.name" labelValue="${ccmOrgOrgprevent.area.name}"
					title="区域" url="/sys/area/treeData" allowClear="true" notAllowSelectParent="false" cssClass=""/>
				<span class="help-inline"><font color="red" id="show1"></font></span>
			</div>
			</td>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>组织名称：</label>
			<div class="controls">
				<form:input path="comName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>

			</div>
			</td>
		</tr>
		<tr>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>组织类型：</label>
			<div class="controls">
				<form:select path="comType" class="input-xlarge ">
					<form:options items="${fns:getDictList('ccm_org_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
			</td>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>组织层级：</label>
			<div class="controls">
				<form:select path="comScale" class="input-xlarge ">
					<form:options items="${fns:getDictList('ccm_ply_rat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
			</td>
		</tr>
		<tr>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>业务指导部门：</label>
			<div class="controls">
				<form:input path="guidePart" htmlEscape="false" maxlength="200" class="input-xlarge required"/>

			</div>
			</td>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>人员数量：</label>
			<div class="controls">
				<form:input path="manNum" htmlEscape="false" maxlength="6" class="input-xlarge number required digits " />

			</div>
			</td>
		</tr>
		<tr>
			<td>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>主要职能：</label>
			<div class="controls">
				<form:input path="mainFunc" htmlEscape="false" maxlength="1024" class="input-xlarge required"/>

			</div>
			</td>
			<td>
			<label class="control-label">组织性质：</label>
			<div class="controls">
				<form:input path="orgnature" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:input path="principal" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
			</td>
			<td>
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="255" class="input-xlarge  phone"/>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<label class="control-label">通讯地址：</label>
			<div class="controls">
				<form:input path="teladdress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
			</td>
		</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="org:ccmOrgOrgprevent:edit">
			<input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>