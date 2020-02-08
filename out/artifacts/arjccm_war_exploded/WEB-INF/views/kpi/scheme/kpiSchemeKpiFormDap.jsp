<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绩效考评KPI管理</title>
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
			var state = ${kpiScheme.state};
			//alert(state);
			if(state=="0"||state==""){
				var id = $('#id').val();
				if(id==""){
					top.$.jBox.confirm("是否提交？提交后考核类别不可修改。", "系统提示", function(v, h, f) {
						if (v == "ok") {
							$("#inputForm").submit();
						} else {
							 
						}
					}, {
						buttonsFocus : 1
					});
					
				}else{
					$("#inputForm").submit();
				}
				
			}else if(state=="1"){
				top.$.jBox.tip('它的方案状态为发布状态，不可编辑！ ');
			}else if(state=="2"){
				top.$.jBox.tip('它的方案状态为冻结状态，不可编辑！ ');
			}
			
		}
		function deleteForm(){
			var state = ${kpiScheme.state};
			if(state=="0"||state==""){
				var id = $('#id').val();
				if(id==""){
					top.$.jBox.tip('新建的未保存KPI，不可编辑！ ');
				}else{
					top.$.jBox.confirm("确认要删除该KPI吗？", "系统提示", function(v, h, f) {
						if (v == "ok") {
							window.location.href="${ctx}/scheme/kpiSchemeKpi/delete?id=${kpiSchemeKpi.id}";
						} else {
							 
						}
					}, {
						buttonsFocus : 1
					});
				}
				
			}else if(state=="1"){
				top.$.jBox.tip('它的方案状态为发布状态，不可编辑！ ');
			}else if(state=="2"){
				top.$.jBox.tip('它的方案状态为冻结状态，不可编辑！ ');
			}
			
		}
		function relationship(){
			var type = $('#type option:selected').val();
			var id = $('#id').val();
			//alert(type+"-"+id);
			if(id==""){
				top.$.jBox.tip('新建的未保存KPI，不可编辑！ ');
			}else{
				if(type=="02"){
					top.$.jBox.tip('正在打开考评关系！ ');
					top.LayerDialog1('relationshipId','${ctx}/scheme/kpiSchemeSubjectivity/relationship?kpiId='+id, '考评关系', '1000px', '650px');
				}else{
					top.$.jBox.tip('非主观KPI，不可编辑！ ');
				}
			}
		}
	</script>
	<%--引入文本框外部样式--%>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<h5 class="nav nav-tabs">
		 ${kpiScheme.name}方案-绩效考评KPI${not empty kpiSchemeKpi.id?'修改':'添加'}
	</h5>
	<form:form id="inputForm" cssStyle="padding-top: 20px" modelAttribute="kpiSchemeKpi" action="${ctx}/scheme/kpiSchemeKpi/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="schemeId"/>
		<sys:message content="${message}"/>		
		<!--  
		<div class="control-group">
			<label class="control-label">上级id：</label>
			<div class="controls">
				<form:input path="parentId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		-->
		<div class="control-group head_Space">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>KPI名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>KPI分数：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" class="input-xlarge required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>考核类别：</label>
			<div class="controls">
				<c:if test="${empty kpiSchemeKpi.id }">
				<form:select path="type" class="input-xlarge required">
					<form:options items="${fns:getDictList('kpi_scheme_kpi_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</c:if>
				<c:if test="${not empty kpiSchemeKpi.id }">
				<form:select path="type" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getDictList('kpi_scheme_kpi_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;
			<input id="btnRelationship" style="background:#D69601" class="btn btn-primary" onclick="relationship()" type="button" value="考评关系"/>&nbsp;
			<input id="btnDelete" class="btn btn-danger" onclick="deleteForm()" type="button" value="删 除"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
</body>
</html>