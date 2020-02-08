<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综治中心管理</title>
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
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/org/ccmOrgSyncentre/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/org/ccmOrgSyncentre/form?id=${ccmOrgSyncentre.id}">数据<shiro:hasPermission name="org:ccmOrgSyncentre:edit">${not empty ccmOrgSyncentre.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgSyncentre:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmOrgSyncentre" action="${ctx}/org/ccmOrgSyncentre/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<table border="0px"
			style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;border-top-color: white">
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>综治中心名称：</label>
						<div class="controls">
							<form:input path="centreName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>综治中心联系方式：</label>
						<div class="controls">
							<form:input path="centreTel" htmlEscape="false" maxlength="50" class="input-xlarge required phone"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td >
					<div>
						<label class="control-label">综治中心图片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" uploadPath="/photo/ZongZhiZhongXin" selectMultiple="false" maxWidth="240" maxHeight="360"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>综治中心层级：</label>
						<div class="controls">
							<form:select path="centreScale" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_ply_rat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人姓名：</label>
						<div class="controls">
							<form:input path="respName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人联系方式：</label>
						<div class="controls">
							<form:input path="respTel" htmlEscape="false" maxlength="50" class="input-xlarge required phone"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>组成单位：</label>
						<div class="controls">
							<form:input path="constituteCom" htmlEscape="false" maxlength="400" class="input-xlarge required"  title="可根据工作需要由各相关综治成员单位组成"/>
							<span class="help-inline"><font color="red">（可根据工作需要由各相关综治成员单位组成）</font> </span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>所在地行政区划代码：</label>
						<div class="controls">
							<form:input path="address" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
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
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
							<a onclick="ThisLayerDialog('${ctx}/event/ccmEventIncident/drawForm?areaMap='+ encodeURIComponent($('#areaMap').val())+'&areaPoint='+ encodeURIComponent($('#areaPoint').val()) , '标注', '1100px', '700px');"
							   class="btn btn-primary">标 注</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">图标：</label>
						<div class="controls">
							<sys:iconselect id="icon" name="icon" value="${ccmOrgSyncentre.icon}"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>

				<td colspan="2" >
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
			<shiro:hasPermission name="org:ccmOrgSyncentre:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>