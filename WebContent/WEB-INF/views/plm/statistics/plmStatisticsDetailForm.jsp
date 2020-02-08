<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门户明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//$("#name").focus();
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
			$("#inputForm").validate({
				
				submitHandler: function(form){
					//判断 该位置是否有窗口存在 longItude(输入的行位置)latItude(输入的列位置)ylongItude(编辑时原本的行位置)ylatItude(编辑时原本的列位置)
					 $.ajax({
						  url:'${ctx}/statistics/plmStatisticsDetail/itudeValidate',
						  data:{"longItude":$("#longItude").val() ,"latItude":$("#latItude").val() ,"ylongItude":"${plmStatisticsDetail.longItude}","ylatItude":"${plmStatisticsDetail.latItude}"},
						  type:'post',
						  dataType:'text',
						  error: function(){ alert("错误")},
						  success:function(data){
							  if(data=="1"){
								  form.submit();
							  }else {
								$(".itudeError").show();
							}
							
						   }
						  });	  
					
					
					
					
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		
		//下拉事件
		function  selectsubmit(){	
			
			var content= $("#content").val();
				//alert(content)
				//内容变动时  回自动匹配 相对应的标题和更多链接
			 $.ajax({
				  url:'${ctx}/statistics/plmStatistics/selectContent',
				  data:{"content":content },
				  type:'post',
				  dataType:'json',
				  error: function(){ alert("错误")},
				  success:function(data){
					  
					$("input[name='connect']").attr("value",data.connect);
					$("input[name='title']").attr("value",data.title);
				   }
				  });	  
			}
	</script>
</head>
<body>
	<br/>
	<form:form target="_parent" id="inputForm" modelAttribute="plmStatisticsDetail" action="${ctx}/statistics/plmStatisticsDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="parent.id"/>
		<form:hidden path="type"/>
				
		<sys:message content="${message}"/>		
		<div class="control-group">
		<label class="control-label">内容：</label>
			<div class="controls">
				<form:select path="content" class="input-xlarge required" onchange="selectsubmit()" >	
				<form:option value="" label=""/>			
					<form:options items="${plmStatisticsDictList}" itemLabel="titleAndTypeName" itemValue="content" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布局标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"> <font color="red">*</font></span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">行位置：</label>
			<div class="controls">
			    		
			    <form:select path="longItude" class="input-xlarge required"  >	
				<form:option value="" label=""/>			
					<form:options items="${fns:getDictList('home_longItude')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="longItude" htmlEscape="false" maxlength="4" class="input-xlarge required number"/> --%>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列位置：</label>
			<div class="controls">
				 <form:select path="latItude" class="input-xlarge required"  >	
				<form:option value="" label=""/>			
					<form:options items="${statistics_latItudelist}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="latItude" htmlEscape="false" maxlength="4" class="input-xlarge required"/> --%>
				<span class="help-inline"><font color="red">*</font> </span>
				<br><label style="display: none;" class="error itudeError">该位置已存在其他窗口</label>
			</div>
			
		</div>
		
		
		<div class="form-actions">
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;
		</div>
	</form:form>
</body>
</html>