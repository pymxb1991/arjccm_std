<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>黑名单和静态库人员管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
		function initAge(data) {
			// console.log("1111")
			var date=new Date(data);
			var time=new Date().getTime()-date.getTime();
			var age=time/1000/60/60/24/365;
			age=parseInt(age);
			if(time<0 || age>150 ){
				//时间不合法
				$("#tshi").show()
			}else{
				$("#tshi").hide()
			}
		}

	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="ccmListPeople" action="${ctx}/list/ccmListPeople/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table>
			<tr>
				<td>	
					<label class="control-label">姓名：</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</td>
				<td>
					<label class="control-label">所属库：</label>
					<div class="controls">
						<form:input path="listId" htmlEscape="false" maxlength="64" class="input-xlarge" style="display:none"/>
						<select id="listIdSelect" class="input-xlarge required" name="listIdSelect"></select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">证件类型：</label>
					<div class="controls">
						<form:select path="papersType" class="input-xlarge required">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getDictList('ccm_list_papers_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false"
								class="required" />
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</td>
				<%-- <td>
					<label class="control-label">证件号码：</label>
					<div class="controls">
						<form:input path="papersNumber" htmlEscape="false" maxlength="30" class="input-xlarge "/>
					</div>
				</td> --%>
				<td>
					<div>
						<label class="control-label">公民身份号码：</label>
						<div class="controls">
							<form:input path="papersNumber" htmlEscape="false" maxlength="18"
								class="input-xlarge required ident0 card" />
							<span id="ident0"></span><span class="help-inline"><font color="red" >*</font></span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">性别：</label>
					<div class="controls">
						<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" 
							itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</td>
				<td>
					<label class="control-label">出生日期：</label>
					<div class="controls">
						<input name="birthday" type="text" readonly="readonly" maxlength="20"  onchange="initAge(this.value)" class="input-medium Wdate required"
							value="<fmt:formatDate value="${ccmListPeople.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						<span class="help-inline"><font color="red">*</font> </span>
						<span id="tshi" class="help-inline" style="display: none"><font color="red">日期不能大于当前日期或大于150岁</font> </span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label">上传图片：</label>
					<div class="controls">
						<form:hidden id="images" path="img" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="images" type="images" uploadPath="/photo/peoplePhoto" selectMultiple="false" maxWidth="160" maxHeight="240"/>
					</div>
				</td>
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
			<shiro:hasPermission name="list:ccmListPeople:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭" />
		</div>
	</form:form>
	<script type="text/javascript">
		$(function(){
			var listIdVal=$('#listId').val();
			$.getJSON("${ctx}/list/ccmList/getList?type=${type}",function(data){
				var html='<option value="0" class="required">请选择</option>';
				var list = data.list;
				for(var i in list){
					html+='<option value="'+list[i].id+'" class="required">'+list[i].name+'</option>'
				}
				$('#listIdSelect').html(html);
				if(listIdVal != ''){
					$("#listIdSelect").val(listIdVal).select2()
				}else{
					$("#listIdSelect").val('0').select2()
				}
			})
			$('#listIdSelect').change(function(){
				var listIdSelecVal= $('#listIdSelect').val();
				$('#listId').val(listIdSelecVal);
			})
		})
	</script>
</body>
</html>