<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共机构管理管理</title>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
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

		function ThisLayerDialog(src, title, height, width){
			parent.drawForm=parent.layer.open({
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
						$("#areaPoint")[0].value = parent.areaPoint;
						parent.areaPoint = null;
						$("#areaMap")[0].value = parent.areaMap;
						parent.areaMap = null;
					}
					window.isCancel = null;
				}
			});
		}
	</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/org/ccmOrgCommonality/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/org/ccmOrgCommonality/form?id=${ccmOrgCommonality.id}">数据<shiro:hasPermission name="org:ccmOrgCommonality:edit">${not empty ccmOrgCommonality.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgCommonality:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmOrgCommonality" action="${ctx}/org/ccmOrgCommonality/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;border-top-color: white">
		
			<tr>
				<td style="width: 33%;">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>机构名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td style="width: 33%;">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>机构编号：</label>
						<div class="controls">
							<form:input path="orgCode" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td style="width: 33%;">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>机构类型：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_org_commonality_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>

						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td >
					<div>
						<label class="control-label">公共机构图片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" uploadPath="/photo/GongGongJiGou" selectMultiple="false"  maxWidth="240" maxHeight="360"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>

				<td>
					<div>
						<label class="control-label">地址：</label>
						<div class="controls">
							<form:input path="adds" htmlEscape="false" maxlength="200" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font></span>所属区域：</label>
						<div class="controls">
							<sys:treeselect id="area" name="area.id" value="${ccmOrgCommonality.area.id}" labelName="area.name" labelValue="${ccmOrgCommonality.area.name}"
											title="区域" url="/sys/area/treeData" allowClear="true" notAllowSelectParent="false" cssClass=""/>
							<span class="help-inline"><font color="red" id="show1"></font></span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">机构联系电话：</label>
						<div class="controls">
							<form:input path="orgTel" htmlEscape="false" maxlength="18" class="input-xlarge phone"/>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人姓名：</label>
						<div class="controls">
							<form:input path="principalName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						</div>
							
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人电话：</label>
						<div class="controls">
							<form:input path="principalTel" htmlEscape="false" maxlength="18" class="input-xlarge required phone"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">机构人数：</label>
						<div class="controls">
							<form:input path="institutionPeopleNum" htmlEscape="false" maxlength="6" class="input-xlarge  digits" type= "number"/>
						</div>

					</div>
				</td>
			</tr>
			<tr>

				<td>
					<div>
						<label class="control-label">重点资源数：</label>
						<div class="controls">
							<form:input path="keyResourceNum" htmlEscape="false" maxlength="6" class="input-xlarge  digits" type= "number"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">图标：</label>
						<div class="controls">
							<sys:iconselect id="image" name="image" value="${ccmOrgCommonality.image}"/>
						</div>
					</div>
				</td>
				<td>

					<div>
						<label class="control-label">附件：</label>
						<div class="controls">
							<form:hidden id="files" path="files" htmlEscape="false" class="input-xlarge"/>
							<sys:ckfinder input="files" type="files" uploadPath="/org/ccmOrgCommonality" selectMultiple="true"/>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false" maxlength="2000" class="input-xlarge " disabled="true"/>
							<a onclick="ThisLayerDialog('${ctx}/event/ccmEventIncident/drawForm?areaMap='+$('#areaMap').val()+'&areaPoint='+$('#areaPoint').val(), '标注', '1100px', '700px');"
							   class="btn btn-primary">标 注</a>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false" maxlength="40" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>

			</tr>

			<tr>
				<td colspan="3">
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
		<tr>
		 
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="org:ccmOrgCommonality:edit">
			<input id="btnSubmit" class="btn btn-primary"  onclick="saveForm()" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn"  type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>