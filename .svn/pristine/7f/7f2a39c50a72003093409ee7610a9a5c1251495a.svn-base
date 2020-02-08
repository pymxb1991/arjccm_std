<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人际关系管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnCancel').click(function() {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			console.log(parent.window.frames['mainFrame'])
			parent.window.frames['mainFrame'].popPopList();
	
		})
		//$("#name").focus();
		$("#inputFormAdd").validate({
			submitHandler : function(form) {
				if($("#addName").html()!=null&&$("#addName").html()!=""){
				var otherName="";
				if($("#otherName")[0]){
					otherName=$("#otherName").val();
				}
				if($("#idCard1").val() == $("#idCard2").val()){
					alert('关系人不能为自己本人！请重新填写身份证号！');
					return;
				}
				$.post("${ctx}/cpp/cppPopPop/ajaxsave", {
					id:$("#id").val(),
					idCard1:$("#idCard1").val(),
					type:$("#type").val(),
					idCard2:$("#idCard2").val(),
					otherName:otherName
				}, function(data) {	
					if(data == 'fail'){
						alert('保存信息重复，请重新输入');
						return;
					}else{
						cppPopPopApp()
						parent.window.frames['mainFrame'].popPopList();
					}
					
				}); 
				}
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
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
		
		function getUrlParam(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
		    if (r != null) return unescape(r[2]); return null; //返回参数值
		}
		var hide1=getUrlParam('hide1');
		var hide2=getUrlParam('hide2');
		if(hide1!=null&&hide2!=null){
			if(hide1=="true"){
				$('.hide1').show();
				$('.form-actions').hide();
			}
			if(hide2=="true"){
				$('.form-actions').hide();
				$('.hide2').show();
				
			}
		}else{
			$('.hide1').show();
			$('.hide2').show();
		}
		//$("#name").focus();
		$("#inputForm").validate({
			submitHandler : function(form) {
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
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
	});

	function deletex(id) {
		confirmx('确认要删除该条信息吗？', function() {
			$.post("${ctx}/cpp/cppPopPop/ajaxdelete", {
				"id" : id
			}, function(data) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				/* parent.alertInfo(data.message); */
				parent.window.frames['mainFrame'].popPopList();
			});
		})
	}
	
	function deleteAdd(id) {
		confirmx('确认要删除该条信息吗？', function() {
			$.post("${ctx}/cpp/cppPopPop/ajaxdelete", {
				"id" : id
			}, function(data) {
				parent.alertInfo(data.message);
				cppPopPopApp();
				parent.window.frames['mainFrame'].popPopList();
			});
		})
	}
	//新添加列表刷新事件
	function cppPopPopApp(){
		$.get("${ctx}/cpp/cppPopPop/saveAddList",function(data) {
			var addContent=$("#addList");
			addContent.empty();
			  for ( var i in data) {
				var name=data[i].name2;
				if( name==""||name==null||name=='undefined' ){
					name=data[i].otherName;
				}
				  addContent.append('<tr>'+
                         ' <td>'+name+'</td>'+
                          '<td><a onclick="deleteAdd(\''+data[i].id+'\')">删除</a></td>'+
						'</tr>');  
			  }
		});
	}
	
	
	//关闭事件
	function close() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		parent.window.frames['mainFrame'].popPopList();
	}
	
	var idCard = "";
	function onblurs() {

		idCard2 = $("#idCard2").val();
      
		if (idCard2 != null && idCard2 != "" && idCard2 != idCard) {
			$.get("${ctx}/cpp/cppPopPop/getIdCard?idCard=" + idCard2,function(data) {
								if (data != "") {
									$("#addName").html('<input id="otherName" name="otherName" class="input required" style="width:180px;" type="text" value="'+data+'" maxlength="25" readonly="readonly">' );
								} else {
									$("#addName")
											.html(
													'<input id="otherName" name="otherName" class="input required" style="width:180px;" type="text" value="" maxlength="25">'
															+ '<span class="help-inline"><font color="red">*</font></span>'
															+ '<div><font color="red">数据库中暂无该人口信息，请自行填写姓名</font> </div>');
								}
							});
			idCard = idCard2;
		}
	}
</script>
<style type="text/css">
.form-horizontal .control-label {
	width: 95px;
}

.form-horizontal .controls {
	margin-left: 100px;
}
#addList td{
text-align: center;}
</style>
</head>
<body>
	<!-- 更新 -->
	<c:if test="${not empty cppPopPop.id}" var="e">
		<form:form id="inputForm" modelAttribute="cppPopPop"
			action="${ctx}/cpp/cppPopPop/save" method="post"
			class="form-horizontal hide1">
			<form:hidden path="id" />
			<sys:message content="${message}" />
			<div class="control-group">
				<label class="control-label">关系类型：</label>
				<div class="controls">
					<form:select id="cpptype" path="type" class="input-xlarge required">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('cpp_pop_pop_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<c:if test="${cppPopPop.idCard2 == idCard}">
				<div class="control-group">
					<label class="control-label">人员身份证号：</label>
					<div class="controls">
						<form:input path="idCard1" htmlEscape="false" maxlength="18"
							class="input-xlarge card" />
					</div>
				</div>
			</c:if>
			<c:if test="${cppPopPop.idCard1 == idCard}">
				<div class="control-group">
					<label class="control-label">人员身份证号：</label>
					<div class="controls">
						<form:input path="idCard2" htmlEscape="false" maxlength="18"
							class="input-xlarge required card" disabled="true"/>
						<span class="help-inline"><font color="red">*</font></span>
					</div>
				</div>
			</c:if>
			<c:if test="${cppPopPop.idCard2 == idCard}">
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">${cppPopPop.name1}
						<c:if test="${ empty cppPopPop.name1}">
							<form:input path="otherName" htmlEscape="false" maxlength="25"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font></span>
							<div>
								<font color="red">数据库中暂无该人口信息，请自行填写姓名</font>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
			<c:if test="${cppPopPop.idCard1 == idCard}">
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">${cppPopPop.name2}
						<c:if test="${ empty cppPopPop.name2}">
							<form:input path="otherName" htmlEscape="false" maxlength="25"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font></span>
							<div>
								<font color="red">数据库中暂无该人口信息，请自行填写姓名</font>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>


			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp; <input id="btnDelete" class="btn btn-danger"
					type="button" onclick="deletex('${cppPopPop.id}')" value="删除" />
			</div>
		</form:form>
	</c:if>

	<!-- 添加 -->
	<c:if test="${!e}">
		<div class="row-fluid">
			<div class="span8" style="width: 70%">
				<form:form id="inputFormAdd" modelAttribute="cppPopPop"
					action="" method="post"
					class="form-horizontal">
					<form:hidden path="id" />
					<form:hidden path="idCard1" var="${idCard}" />
					<sys:message content="${message}" />
					<div class="control-group">
						<label class="control-label">关系类型：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('cpp_pop_pop_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label"><span class="help-inline"><font color="red">*</font></span>人员身份证号：</label>
						<div class="controls">
							<form:input path="idCard2" htmlEscape="false" maxlength="18"
								class="input-xlarge required card" onblur="onblurs()" />


						</div>
					</div>
					<div class="control-group">
						<label class="control-label">姓名：</label>
						<div class="controls" id="addName"></div>


					</div>
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						style="float: right; margin-right: 30px;" value="添加" />
					<input id="btnCancel" class="btn  btn-danger" type="button"
					 style="float: right; margin-right: 30px;" value="关闭"/>




				</form:form>
			</div>
			<div class="span3" style="position: relative; top:-18px ;padding:3 20px ;height: 275px;width: 25%;overflow-y: auto;">
				<span style="margin: 10px 0 0 8px; font-weight: 900;font-size: 15px;">新添加列表</span>
				<table id="contentTable" 
					class="table table-striped table-bordered table-condensed" >
					
					<tbody id="addList" align="center">

						
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</body>
</html>