<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知信息设置</title>
	<meta name="decorator" content="default" />
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			var paramStr = ${ccmHouseSetting.paramStr};
			radioChange();
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
			$("input:radio[name='sendInfo']").change(function (){
				radioChange();
	        });
			$("#timeInterval").change(function (){
				var timeInterval = $('#timeInterval').val();
				var number = /^([1-9]|[1-9][0-9]*)$/;
				if(number.test(timeInterval)){
					$("#timeIntervalHid").val(timeInterval);
				}
	        });
			$('#btnSubmit').click(function() {
				var timeInterval = $('#timeInterval').val();
				var sendInfo = $('input:radio[name="sendInfo"]:checked').val();
				var number = /^([1-9]|[1-9][0-9]*)$/;
				if(sendInfo == "1"){
					if(!number.test(timeInterval)){
						$.jBox.tip('间隔时间应为非零正整数');
						return;
					}
				}else if(sendInfo == "2"){
					timeInterval = $('#timeIntervalHid').val();
				}else{
					return;
				}
				var type = $('#type').val();
				paramStr[type] = {
					timeInterval : timeInterval,
					sendInfo : sendInfo
				};
				$.ajax({
			        type: "POST",
			        url: ctx + "/sys/sysConfig/save",
			        data: {
			        	id:$('#id').val(),
			        	remarks:$('#remarks').val(),
			        	paramStr:JSON.stringify(paramStr)
			        },
			        dataType: "json",
			        cache: false,
			        async: false,
			        success: function (data) {
			        	console.log("data",data);
			        }, error: function (e,data) {
			        	console.error("e",e);
			        }
			    });
	        	parent.layer.close(parent.layerIndex);
			});
		});
		function radioChange(){
			var sendInfo = $("input:radio[name='sendInfo']:checked").val();
			if(sendInfo == "1"){
				$("#timeIntervalTr").show();
			}else if(sendInfo == "2"){
				$("#timeIntervalTr").hide();
			}else{
				$("#timeIntervalTr").hide();
			}
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="ccmHouseSetting" action="" method="post" class="form-horizontal" onsubmit="return false;">
		<form:hidden path="id" />
		<form:hidden path="remarks" />
		<form:hidden path="type" />
		<input type="hidden" value="${ccmHouseSetting != null && ccmHouseSetting.timeInterval != null && ccmHouseSetting.timeInterval != '' ? ccmHouseSetting.timeInterval:'1'}" id="timeIntervalHid">
		<table>
			<tr>
				<td>
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否发送消息：</label>
					<div class="controls">
						<form:radiobuttons path="sendInfo" items="${fns:getDictList('preview_system_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required" />

					</div>
				</td>
			</tr>
			<tr id="timeIntervalTr">
				<td>
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>间隔时间：</label>
					<div class="controls">
						<form:input path="timeInterval" htmlEscape="false" min="1" maxlength="128" class="input-xlarge required" type="number"/>
						<label class="lableStyle">天</label>

					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions"> 
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭" />
		</div>
	</form:form>
</body>
</html>