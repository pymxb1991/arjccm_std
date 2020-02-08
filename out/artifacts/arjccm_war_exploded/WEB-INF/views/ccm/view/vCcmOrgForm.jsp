<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>综治机构管理</title>
<meta name="decorator" content="default" />
<!--  
<script src="${ctxstatic}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js" type="text/javascript"></script>
<link href="${ctxstatic}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet" />
-->
<script type="text/javascript">
	$(document).ready(
			function(){
				$("#name").focus();
				$("#inputForm").validate(
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
				$("td").css({"padding":"10px"});
				$("td").css({"border":"1px dashed #CCCCCC"});
				
		    }
			
	
			
	);
	
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
	function ThisLayerDialog(src, title, height, width){
		parent.parent.drawForm=parent.parent.layer.open({
			type: 2,
			title: title,
			area: [height, width],
			fixed: true, //固定
			maxmin: false,
			/*   btn: ['关闭'], //可以无限个按钮 */
			content: src,
			zIndex:'1992',
			cancel:function(){
				//防止取消和删除效果一样
				window.isCancel = true;
			},
			end:function(){
				if(!window.isCancel){
					$("#areaPoint")[0].value = parent.parent.areaPoint;
					parent.areaPoint = null;
					$("#areaMap")[0].value = parent.parent.areaMap;
					parent.areaMap = null;
				}
				window.isCancel = null;
			}
		});
	}
</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/view/vCcmOrg/list">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head"
			href="">数据<shiro:hasPermission
					name="view:vCcmOrg:edit">${not empty vCcmOrg.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="view:vCcmOrg:edit">查看</shiro:lacksPermission></a></li>
	</ul>

	<form:form id="inputForm" modelAttribute="vCcmOrg"
		action="${ctx}/org/ccmOrgComprehensive/save" method="post"
		class="form-horizontal">
	 	 <form:hidden path="id" />  
		<sys:message content="${message}" />
		
		<table class="first_table" border="1px" style="border-color: #CCCCCC; border: 1px dashed #CCCCCC; padding: 10px; width: 100%" >
			<tr>
				<td style="width:50%;">
					<div>
						<label class="control-label">上级机构：</label>
						<div class="controls">${vCcmOrg.parent.name}
							<!--  --><!-- 
							<sys:treeselect id="parent" name="parent.id"
								value="${vCcmOrg.parent.id}" labelName="parent.name"
								labelValue="${vCcmOrg.parent.name}" title="父级编号"
								url="/view/vCcmOrg/treeData" extId="${vCcmOrg.id}" cssClass=""
								allowClear="true" /> 
							<form:input path="parent.id" htmlEscape="false" maxlength="255"
								class="input-xlarge " readonly="true"/>
							
							
							<sys:treeselect id="parent" name="parent.id" value="${vCcmOrg.parent.id}" labelName="parent.name" labelValue="${vCcmOrg.parent.name}"
								title="机构" url="/view/vCcmOrg/treeData" extId="${vCcmOrg.id}" cssClass="" allowClear="${vCcmOrg.currentUser.admin}"/>-->
							<!--<input type="text" readonly="readonly" value="${vCcmOrg.parent.name}"/>  -->
					
						</div>
					</div>
				</td>
				<td>
				</td>
				
			</tr>
			
		
		
		<tr>
			<td>
		<div>
			<label class="control-label"><span class="help-inline"><font color="red" >*</font></span>归属区域：</label>
			<div class="controls">${vCcmOrg.area.name}
				<!--
				<sys:treeselect id="area" name="area.id" value="${vCcmOrg.area.id}"
					labelName="area.name" labelValue="${vCcmOrg.area.name}" title="区域"
					url="/sys/area/treeData" cssClass="" allowClear="true"
					notAllowSelectParent="true" /><!--  -->
				<!--<form:input path="area.name" htmlEscape="false" maxlength="255"
					class="input-xlarge " readonly="true"/><!--  -->
				<span class="help-inline"><font color="red" id="show1"></font></span>
			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">主负责人：</label>
			<div class="controls">${vCcmOrg.primaryPerson.name}
				<!--
				<sys:treeselect id="primaryPerson" name="primaryPerson.id" value="${vCcmOrg.primaryPerson.id}" labelName="vCcmOrg.primaryPerson.name" labelValue="${vCcmOrg.primaryPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>  -->
				<!--<form:input path="primaryPerson.name" htmlEscape="false" maxlength="255"
					class="input-xlarge " readonly="true"/>  -->
			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td>
		<div>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>机构名称：</label>
			<div class="controls">${vCcmOrg.name}
				<!--<form:input path="name" htmlEscape="false" maxlength="100"
					class="input-xlarge required" readonly="true"/> -->

			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">副负责人：</label>
			<div class="controls">${vCcmOrg.deputyPerson.name}
				
				<!-- <sys:treeselect id="deputyPerson" name="deputyPerson.id" value="${vCcmOrg.deputyPerson.id}" labelName="vCcmOrg.deputyPerson.name" labelValue="${vCcmOrg.deputyPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/> -->
				<!--<form:input path="deputyPerson.name" htmlEscape="false" maxlength="255"
					class="input-xlarge " readonly="true"/>  -->
			</div>
		</div>
			</td>
		</tr>
		<tr style="display:none">
			<td>
		<div >
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false"
					class="input-xlarge required" readonly="true"/>

			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td>
		
		<div>
			<label class="control-label">机构编码：</label>
			<div class="controls">${vCcmOrg.code}
				<!--<form:input path="code" htmlEscape="false" maxlength="100"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">负责人：</label>
			<div class="controls">${vCcmOrg.master}
				<!--<form:input path="master" htmlEscape="false" maxlength="100"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td>
		<div>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>机构类型：</label>
			<div class="controls">${fns:getDictLabel(vCcmOrg.type, 'sys_office_type', '')}
				<!--<form:select path="type" class="input-xlarge required" disabled="true">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('sys_office_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>  -->

			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">邮政编码：</label>
			<div class="controls">${vCcmOrg.zipCode}
				<!--<form:input path="zipCode" htmlEscape="false" maxlength="100"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td>
		<div>
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>机构层级：</label>
			<div class="controls">${fns:getDictLabel(vCcmOrg.grade, 'ccm_ply_rat', '')}
				<!--<form:select path="grade" class="input-xlarge required" disabled="true">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_ply_rat')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> -->

			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">传真：</label>
			<div class="controls">${vCcmOrg.fax}
				<!--<form:input path="fax" htmlEscape="false" maxlength="200"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td>
		<div>
			<label class="control-label">联系地址：</label>
			<div class="controls">${vCcmOrg.address}
				<!--<form:input path="address" htmlEscape="false" maxlength="255"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">是否启用：</label>
			<div class="controls" style="">${fns:getDictLabel(vCcmOrg.useable, 'yes_no', '')}
				<!--<form:select path="useable" class="input-xlarge " disabled="true" >
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> -->
			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td>
		<div>
			<label class="control-label">电话：</label>
			<div class="controls">${vCcmOrg.phone}
				<!--<form:input path="phone" htmlEscape="false" maxlength="200"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
			<td>
		<div>
			<label class="control-label">邮箱：</label>
			<div class="controls">${vCcmOrg.email}
				<!--<form:input path="email" htmlEscape="false" maxlength="200"
					class="input-xlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
		<div>
			<label class="control-label">备注信息：</label>
			<div class="controls">${vCcmOrg.remarks}
				<!--<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge "  readonly="true"/> -->
			</div>
		</div>
			</td>
		</tr>
		</table>

<br/>
			<table>
		<tr>
			
			<td>
				<div>
					<label class="control-label">机构说明：</label>
					<div class="controls">
						<form:input path="description" htmlEscape="false" maxlength="1000"
							class="input-xlarge " />
					</div>
				</div>
			</td>
			<td>
				<div>
					<label class="control-label">主要职能：</label>
					<div class="controls">
						<form:input path="mainFunc" htmlEscape="false" maxlength="1000"
									class="input-xlarge " />
					</div>
				</div>
			</td>

		</tr>
				<tr>
					<td >
						<div>
							<label class="control-label">机构照片：</label>
							<div class="controls">
								<!--
							<form:input path="picPath" htmlEscape="false" maxlength="255"
								class="input-xlarge " />
							  -->
								<form:hidden id="images" path="picPath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
								<sys:ckfinder input="images" type="images" uploadPath="/photo/综治机构" selectMultiple="false" maxWidth="160" maxHeight="240"/>
							</div>
						</div>
					</td>
				</tr>

		<tr>
			<td>
				<div>
					<label class="control-label">中心点：</label>
					<div class="controls">
						<form:input path="areaPoint" htmlEscape="false" maxlength="40"
							class="input-xlarge"/>
						
					</div>
				</div>
			</td>
			<td>
				<div>
					<label class="control-label">区域图：</label>
					<div class="controls">
						<form:input path="areaMap" htmlEscape="false" maxlength="2000"
									class="input-xlarge " />
						<a onclick="ThisLayerDialog('${ctx}/event/ccmEventIncident/drawForm?areaMap='+ encodeURIComponent($('#areaMap').val())+'&areaPoint='+ encodeURIComponent($('#areaPoint').val()) , '标注', '1100px', '700px');"
							class="btn btn-primary">标 注</a>
					</div>
				</div>
			</td>
		</tr>

		<tr>
			<td>
				<div>
					<label class="control-label">最大签收时间：</label>
					<div class="controls">
						<form:input path="maxDispatchTime" htmlEscape="false" maxlength="2000"
									class="input-xlarge number" />
					</div>
				</div>
			</td>
		</tr>
				<tr>
					<td>
						<div>
							<label class="control-label">最大到达时间：</label>
							<div class="controls">
								<form:input path="maxArriveTime" htmlEscape="false" maxlength="2000"
											class="input-xlarge number" />
							</div>
						</div>
					</td>
		</tr>
		<tr>
			<td rowspan="2">
				<div>
					<label class="control-label">图标：</label>
					<div class="controls">
						<!--
						<form:input path="icon" htmlEscape="false" maxlength="255"
							class="input-xlarge " />  -->
						<!--<form:hidden id="icon" path="icon" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="icon" type="files" uploadPath="/view/vCcmOrg" selectMultiple="true"/>-->
						<sys:iconselect id="icon" name="icon" value="${vCcmOrg.icon}"/>	
					</div>
				</div>
			</td>
		</tr>
		
		
		</table>
			 
			<div style="display:none">
				<label class="control-label">机构：</label>
				<div class="controls">
					<sys:treeselect id="office" name="office.id"
						value="${vCcmOrg.office.id}" labelName="office.name"
						labelValue="${vCcmOrg.office.name}" title="部门"
						url="/sys/office/treeData?type=2" cssClass="" allowClear="true"
						notAllowSelectParent="true" />
				</div>
			</div>
			
			
			
			
			
			
			
			<!--
			<div>
				<label class="control-label">冗余字段1：</label>
				<div class="controls">
					<form:input path="more1" htmlEscape="false" maxlength="100"
						class="input-xlarge " />
				</div>
			</div>
			  -->
			<div class="form-actions">
				<shiro:hasPermission name="org:ccmOrgComprehensive:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit" name="submit"
						value="保 存"/>&nbsp;</shiro:hasPermission>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
		
	</form:form>
</body>
</html>