<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综治视联网信息中心管理</title>
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
			$("td").css({"border":"1px dashed #CCCCCC"});
		});
	</script>
	<style type="text/css">
		.pad{padding: 5px; padding-left: 10px}
		#person{display: none;}
		#float{display: none;}
		#oversea{display: none;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/org/ccmOrgInfovideo/">数据列表</a></li>
		<li class="active"><a href="${ctx}/org/ccmOrgInfovideo/form?id=${ccmOrgInfovideo.id}">数据<shiro:hasPermission name="org:ccmOrgInfovideo:edit">${not empty ccmOrgInfovideo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgInfovideo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmOrgInfovideo" action="${ctx}/org/ccmOrgInfovideo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%" >	
			<tr>
				<td>
					<div>
						<label class="control-label">中心名称：</label>
						<div class="controls">
							<form:input path="centreName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td rowspan="8">
					<div>
						<label class="control-label">综治视联网信息中心图片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" uploadPath="/photo/ZongZhiShiLianWang" selectMultiple="false" maxWidth="240" maxHeight="360"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">中心联系方式：</label>
						<div class="controls">
							<form:input path="centreTel" htmlEscape="false" maxlength="50" class="input-xlarge required phone"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">中心层级：</label>
						<div class="controls">
							<form:select path="centreScale" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_ply_rat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">中心负责人姓名：</label>
						<div class="controls">
							<form:input path="centreRespName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">专职工作人员数量：</label>
						<div class="controls">
							<form:input path="specialtyNum" htmlEscape="false" maxlength="6" class="input-xlarge digits required" type="number"/>
					<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">办公用房面积（平方米）：</label>
						<div class="controls">
							<form:input path="workArea" htmlEscape="false" class="input-xlarge number"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">年度运行经费（元）：</label>
						<div class="controls">
							<form:input path="expenditure" htmlEscape="false" maxlength="10" class="input-xlarge digits required" type="number"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">联网公共安全视频监控摄像机数量：</label>
						<div class="controls">
							<form:input path="videoSecuNum" htmlEscape="false" maxlength="6" class="input-xlarge digits required" type="number"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">是否24小时有人值守：</label>
						<div class="controls">
							<form:radiobuttons path="officeAllday" items="${fns:getDictList('yes_no')}" 
								itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
							<span class="help-inline"><font color="red">*</font> </span>
							<span class="help-inline"><font color="red">此项由省（区、市）、地（市、州、盟）、县（市、区、旗）、乡镇（街道）、村（社区）级填报</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">图标：</label>
						<div class="controls">
							<sys:iconselect id="icon" name="icon" value="${ccmOrgInfovideo.icon}"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">所在地行政区划代码：</label>
						<div class="controls">
							<form:input path="address" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">所在地详址：</label>
						<div class="controls">
							<form:input path="addDetail" htmlEscape="false" maxlength="80" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false" maxlength="40" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
						</div>
					</div>
				</td>
			</tr>
		</table>	

		<div class="form-actions">
			<shiro:hasPermission name="org:ccmOrgInfovideo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>