<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>布控管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<style>
		/*.hide1{*/
		/*	display: none*/
		/*}*/
		.tableNamefirst{
			padding-top: 20px;
		}
		#mark {
			/* display: none; */
		}
		.markli {
			background-color: #ece9e9c9;
			padding: 2px 10px;
			border-radius: 15px;
			margin-left: 10px;
		}

		.mark-add {
			padding: 2px 0px;
			margin-left: 3px;
		}

		li .mark-icon {
			cursor: pointer;
			font-size: 16px;
			position: relative;
			top: 1.5px;
			left: 2px;
		}

		li .mark-icon:HOVER {
			font-size: 18px;
		}

		.mark td ul {
			margin: 0px;
		}

		.mark td .mark-add {
			position: relative;
		}
        .ul-form>li{
            display: inline-block;
            padding-left: 20px;
            white-space:nowrap;
        }
	</style>
	<script type="text/javascript">
		$(document).ready(function() {

			$('.hide1').attr("readonly","readonly");
			$('.hide1').attr("disabled","disabled");
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})

			// //获取url中的参数
			// function getUrlParam(name) {
			// 	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			// 	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			// 	if (r != null) return unescape(r[2]); return null; //返回参数值
			// }
			// var hide1=getUrlParam('hide1');
			//
			// if(hide1!=null){
			// 	if(hide1=="true"){
			// 		$('.hide1').show();
			// 		$('.form-actions').hide();
			// 		$('.help-inline').hide();
			// 		$('.input-xlarge').attr("readonly","readonly");
			// 		$('.displayedbuttons').attr("disabled","disabled");
			// 		$('.input-medium').attr("disabled","disabled");
			// 	}
			// }else{
			// 	$('.hide1').show();
			// }

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
		// 开启关闭布控区域
		function showFace(){
			var s = $("#face").prop('checked');
			if(s){
				$(".selectFace").show();
			}else{
				$(".selectFace").hide();
			}
		}
		function showDevice(){
			var s = $("#device").prop('checked');
			if(s){
				$(".selectDevice").show();
			}else{
				$(".selectDevice").hide();
			}
		}
        var carNumber="";
        function showCar(){
            var s = $("#car").prop('checked');
            if(s){
                $(".selectCar").show();
                //获取车牌信息
                carNumber=$("#carNumber").val();
            }else{
                $(".selectCar").hide();
            }
        }

        function saveForm() {
            if ($("#face").is(':checked')|| $("#device").is(':checked')|| $("#car").is(':checked')) {
                if($("#car").is(':checked')){
                    if(!carNumber){
                        if($("#car").is(':checked')&&!carNumber){
                            parent.layer.msg('请完善车辆信息', {
                                icon: 5
                            });
                        }
                    }else {
                        $("#inputForm").submit();
					}
                }else {
                    $("#inputForm").submit();
                }
            }else{
                parent.layer.msg('请至少选中一个布控内容', {
                    icon: 5
                });
            }
        }
	</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
<%--	<ul class="nav nav-tabs">--%>
<%--		<li><a href="${ctx}/device/ccmDeviceControl/">探针布控列表</a></li>--%>
<%--		<li class="active"><a href="${ctx}/device/ccmDeviceControl/form?id=${ccmDeviceControl.id}">探针布控<shiro:hasPermission name="device:ccmDeviceControl:edit">${not empty ccmDeviceControl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="device:ccmDeviceControl:edit">查看</shiro:lacksPermission></a></li>--%>
<%--	</ul><br/>--%>
	<form:form id="inputForm" modelAttribute="ccmPeople" cssStyle="padding: 10px;" action="${ctx}/overallControl/ccmOverallControl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>


		<ul class="ul-form">
            <span style="font-family:'Telex',sans-serif;line-height:20px;color:#317eac;text-rendering:optimizelegibility;margin: -10px 10px 10px 10px;">布控内容</span>
			<li style="padding-left: 30px;"><label>人脸布控</label>
				<input type="checkbox" id="face" name="face" onclick="showFace()">
			<li/>
			<li><label>手机布控</label>
				<input type="checkbox" id="device" name="device" onclick="showDevice()">
			<li/>
			<li><label>车辆布控</label>
				<input type="checkbox" id="car" name="car" onclick="showCar()">
			<li/>
		</ul>
		<h4 class="tableNamefirst">人员基本信息</h4>
	<table>
		<tr>
		<td><div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<input type="hidden" name="name" value="${ccmPeople.name}"/>
				<form:input path="name" htmlEscape="false" disabled="disabled" maxlength="64" class="input-xlarge hide1 required"/>
<%--				<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div></td>
		<td><div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<input type="hidden" name="sex" value="${ccmPeople.sex}"/>
				<form:radiobuttons path="sex" items="${fns:getDictList('sex')}"
								   itemLabel="label" disabled="disabled" itemValue="value" htmlEscape="false" class="input-xlarge hide1 required"/>
<%--				<form:input path="sex" htmlEscape="false" maxlength="2" class="input-xlarge required"/>--%>
<%--				<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div></td>
		</tr>
		<tr>
		<td><div class="control-group">
			<label class="control-label">证件号码：</label>
			<div class="controls">
				<input type="hidden" name="ident" value="${ccmPeople.ident}"/>
				<form:input path="ident" htmlEscape="false" disabled="disabled" maxlength="18" class="input-xlarge hide1 required"/>
<%--				<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div></td>
		<td><div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
<%--				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
<%--					value="<fmt:formatDate value="${ccmDeviceControl.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<input type="hidden" name="age" value="${ccmPeople.age}"/>
			<form:input path="age"  htmlEscape="false" disabled="disabled" maxlength="3" class="input-xlarge hide1 required"/>
<%--				<input name="birthday" type="text" readonly="readonly" maxlength="18" class="input-xlarge required"--%>
<%--					   value="${age}">--%>
<%--					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--				<form:input path="birthday"  htmlEscape="false" maxlength="18" class="input-xlarge required"value="<fmt:formatDate value="${ccmPeople.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
			</div>
		</div></td>
		</tr>
	</table>

		<h4 class="tableNamefirst">布控基本信息</h4>
		<table>
			<tr>
				<td><div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>布控名称：</label>
					<div class="controls">
						<input name="controlName" type="text" maxlength="64" class="input-xlarge required"/>

					</div>
				</div></td>
				<td><div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>布控等级：</label>
					<div class="controls">
						<form:select path="" name="controlGrade" class="input-xlarge required">
							<form:options value="" label="请选择" />
							<form:options items="${fns:getDictList('ccm_control_level')}"
										  itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>

					</div>
				</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
					<label class="control-label">布控开始时间：</label>
					<div class="controls">
						<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${ccmDeviceControl.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</div>
				</div></td>
				<td><div class="control-group">
					<label class="control-label">布控结束时间：</label>
					<div class="controls">
						<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${ccmDeviceControl.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td colspan="2"><div class="control-group">
					<label class="control-label">布控原因：</label>
					<div class="controls">
						<form:textarea path="" name="reason" htmlEscape="false" rows="4"
									   maxlength="255" class="input-xlarge " />
					</div>
				</div></td>
			</tr>
		</table>

		<div class="selectFace hide">
			<h4 class="tableNamefirst">人脸布控信息</h4>
			<table>
				<tr>
					<td colspan="2"><div class="control-group">
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>布控名单库：</label>
							<div class="controls">
								<select data-placeholder="选择一个或多个名单库" style="width: 90%;" name="librarySelect" class="chosen-select required" multiple tabindex="4">
									<option value=""></option>
									<c:forEach items="${libraryList}" var="people">
										<option value="${people.id}">${people.name}</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</td>
                </tr>
                <tr>
					<td><div class="control-group">
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>布控抓拍机：</label>
							<div class="controls">
								<select data-placeholder="选择一个或多个名单库" style="width: 90%;" name="captureMachine" class="chosen-select required" multiple tabindex="4">
									<option value=""></option>
									<c:forEach items="${grabberList}" var="grabber">
										<option value="${grabber.id}">${grabber.grabberName}</option>
									</c:forEach>
								</select>

							</div>
					</div></td>
				</tr>
				<tr>
					<td colspan="2" rowspan="2">
						<div>
							<label class="control-label">照片：</label>
							<div class="controls">
								<form:hidden id="images" path="images" htmlEscape="false"
											 maxlength="255" class="input-xlarge" />
								<sys:ckfinder input="images" type="images" readonly="true"
											  uploadPath="/photo/ShiYouRenKou" selectMultiple="false"
											  maxWidth="240" maxHeight="360" />
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="selectDevice hide">
		<h4 class="tableNamefirst">手机布控信息</h4>
			<table>
				<tr>
					<td colspan="2"><div class="control-group">
						<label class="control-label">布控手机号：</label>
						<div class="controls">
							<ul class="nav nav-pills">
								<c:if test="${not empty ccmPeople.telephone}">
									<input type="hidden" name="telephone" value="${ccmPeople.telephone}"/>
									<li class="markli" style="background-color:#6495ED;color:#FFF;">${ccmPeople.telephone}</li>
								</c:if>
								<c:forEach items="${phoneList}" var="phone">
									<input type="hidden" name="telephone" value="${phone.textName}"/>
									<li class="markli" style="background-color:#6495ED;color:#FFF;">${phone.textName}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					</td>
				</tr>
				<tr>
					<td><div class="control-group">
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>手机布控范围：</label>
						<div class="controls">
							<input name="phoneRange" type="text" class="required" value=""/>

						</div>
					</div></td>
					<td>
						<div class="control-group">
							<label class="control-label">手机布控类型：</label>
							<div class="controls">
								<form:select path="" name="phoneType" class="input-xlarge">
									<form:option value="" label="请选择" />
									<form:options items="${fns:getDictList('ccm_people_controller')}"
												  itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="selectCar hide">
			<h4 class="tableNamefirst">车辆布控信息</h4>
			<table>
				<tr>
					<td colspan="2"><div class="control-group">
						<label class="control-label">布控车辆：</label>
						<div class="controls">
							<ul class="nav nav-pills">
								<c:forEach items="${vehicleList}" var="vehicle">
									<input type="hidden" id="carNumber" name="vehicleNumber" value="${vehicle.textName}"/>
									<li class="markli" style="background-color:#6495ED;color:#FFF;" name="vehicleNumber">${vehicle.textName}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					</td>
				</tr>
				<tr>
<%--					<td><div class="control-group">--%>
<%--						<label class="control-label">车辆布控范围：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:select path="" name="vehicleRange" class="input-xlarge">--%>
<%--								<form:options value="" label="请选择" />--%>
<%--								&lt;%&ndash;								<form:options items="${fns:getDictList('ccm_control_level')}"&ndash;%&gt;--%>
<%--								&lt;%&ndash;											  itemLabel="label" itemValue="value" htmlEscape="false"/>&ndash;%&gt;--%>
<%--							</form:select>--%>
<%--						</div>--%>
<%--					</div></td>--%>
					<td>
						<div class="control-group">
							<label class="control-label">车辆布控类型：</label>
							<div class="controls">
								<form:select path="" name="vehicleType" class="input-xlarge">
									<form:option value="" label="请选择" />
									<form:options items="${fns:getDictList('ccm_people_controller')}"
												  itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="form-actions">
<%--			<shiro:hasPermission name="overallControl:ccmOverallControl:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<shiro:hasPermission name="overallControl:ccmOverallControl:edit"><input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button"
				   value="关闭" />
		</div>
	</form:form>
</body>
</html>