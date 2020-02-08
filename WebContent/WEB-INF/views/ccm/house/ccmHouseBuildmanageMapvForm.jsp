<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>建筑物管理</title>
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />	
<meta name="decorator" content="technology"/>
<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//$("#name").focus();
		$("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
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
		
		//创建案事件历史
		var jsonString = '${documentList}';
           data = JSON.parse(jsonString);  
		$(".fishBone1").fishBone(data, '${ctx}','deal');
		$(".fishBone2").fishBone(data, '${ctx}','read');
	});
	
	function saveForm(){
		var areaId = $("#areaId").val();
		var html1 = '<label for="" class="error">必填信息 *<label>';
		if(areaId.length!=0){
			$("#show1").html("*");
		}else{
			$("#show1").html(html1);
			$("#areaName").focus();
		}
		
		if(areaId.length!=0){
			$("#inputForm").submit();
		}
	}

	function getccmHouseBuildmanage(id,buildname) {
		parent.layer.open({
			type: 2,
			title: '楼栋结构',
			area: ['1100px', '750px'],
			fixed: true, //固定
			maxmin: true,
			content: ctx + '/house/ccmHouseBuildmanage/Maplist?type=1&id='+ id +'&buildName='+ buildname,
			zIndex: '1992',
			skin:"mySkin"
		})
	}
</script>
</head>
<body>
	<br/>
	    <form:form id="inputForm" modelAttribute="ccmHouseBuildmanage" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<h4 class="tableNamefirst">建筑物信息</h4>
		<table border="1px"
			style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div>
						<label class="control-label">小区（单位）名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="512" readonly="true" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">所属网格：</label>
						<div class="controls">
							<sys:treeselect disabled="disabled" id="area" name="area.id" value="${ccmHouseBuildmanage.area.id}" labelName="area.name" labelValue="${ccmHouseBuildmanage.area.name}" title="网格" url="/tree/ccmTree/treeDataArea?type=7&areaid=" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">建筑物名称：</label>
						<div class="controls">
							<form:input path="buildname" htmlEscape="false" maxlength="512" readonly="true" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td rowspan="7">
					<div>
						<label class="control-label">建筑物图片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" readonly="true" uploadPath="/photo/LouDong" selectMultiple="false" maxWidth="240" maxHeight="360"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">建筑面积（平方米）：</label>
						<div class="controls">
							<form:input path="floorArea" htmlEscape="false" readonly="true" maxlength="10" class="input-xlarge number required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">层数：</label>
						<div class="controls">
							<form:input path="pilesNum" htmlEscape="false" maxlength="3" readonly="true" class="input-xlarge required digits" min="0" type= "number"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">单元数：</label>
						<div class="controls">
							<form:input path="elemNum" htmlEscape="false" maxlength="2" readonly="true" class="input-xlarge required digits"  min="0"  type= "number" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">建筑物户数：</label>
						<div class="controls">
							<form:input path="buildNum" htmlEscape="false" maxlength="4"  min="0" readonly="true"  class="input-xlarge digits" type= "number" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">建筑物人数：</label>
						<div class="controls">
							<form:input path="buildPeo" htmlEscape="false" maxlength="6" readonly="true" class="input-xlarge digits"  min="0"  type= "number" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">所在地行政区划代码：</label>
						<div class="controls">
							<form:input path="residence" readonly="true" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
			<td>
				<div>
					<label class="control-label">所在地详细地址：</label>
					<div class="controls">
						<form:input path="residencedetail" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
					</div>
				</div>
			</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">建筑物编号：</label>
						<div class="controls">
							<form:input path="code" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<input type="button" class="btn btn-primary" value="楼栋结构信息" onclick="getccmHouseBuildmanage('${ccmHouseBuildmanage.id}','${ccmHouseBuildmanage.buildname}')">
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">楼栋长姓名：</label>
						<div class="controls">
							<form:input path="buildPname" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">性别：</label>
						<div class="controls">
							<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label"
											   itemValue="value" htmlEscape="false" class="required" disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">手机号码：</label>
						<div class="controls">
							<form:input path="tel" readonly="true" htmlEscape="false" maxlength="11" class="input-xlarge required mobile"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">固定电话：</label>
						<div class="controls">
							<form:input path="phone" readonly="true" htmlEscape="false" maxlength="12" class="input-xlarge simplePhone"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">民族：</label>
						<div class="controls">
							<form:select path="nation" class="input-xlarge required" disabled="true">
								<form:options items="${fns:getDictList('sys_volk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">政治面貌：</label>
						<div class="controls">
							<form:select path="content" class="input-xlarge required" disabled="true">
								<form:options items="${fns:getDictList('sys_ccm_poli_stat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">出生日期：</label>
						<div class="controls">
							<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value="<fmt:formatDate value="${ccmHouseBuildmanage.birthday}" pattern="yyyy-MM-dd"/>"  disabled="true"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">学历：</label>
						<div class="controls">
							<form:select path="education" class="input-xlarge required" disabled="true">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
	</form:form>

</body>
</html>