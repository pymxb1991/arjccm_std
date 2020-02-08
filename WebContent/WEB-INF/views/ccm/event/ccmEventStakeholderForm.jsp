<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案（事）件干系人管理</title>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		
		
	
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    initAge( $("input[name='birthday']").val());
                    console.log(form.isNonage.value)
                    console.log(form.isKym.value)
                    debugger
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
		function initAge(data) {
			//判断是否为18岁
			var date=new Date(data);
			var time=new Date().getTime()-date.getTime();
			var age=time/1000/60/60/24/365;
			age=parseInt(age);
			if(time<0){
				//时间不合法
				$("input[name='birthday']").val("")
				$("#tshi").show()
			}else{
				$("#tshi").hide()
				if(age<=18){
					$("#isNonage1").attr("checked","checked");
					$("#isNonage1").val("1");
					$("#isNonage2").removeAttr("checked");
				}else{
					$("#isNonage1").removeAttr("checked");
					$("#isNonage1").val("0");
					$("#isNonage2").attr("checked","checked");
				}
				if(age>=6 && age <=25){
					$("#isKym1").attr("checked","checked");
					$("#isKym1").val("1");
					$("#isKym2").removeAttr("checked");
				}else{
					$("#isKym2").attr("checked","checked");
					$("#isKym1").val("0");
					$("#isKym1").removeAttr("checked");
				}
			}
			$("#isNonage").val($("#isNonage1").val());
			$("#isKym").val($("#isKym1").val());
		}
	</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
	
		<%-- <li><a href="${ctx}/event/ccmEventIncident/list">案（事）件登记列表</a></li>
	    <!-- 案（事）件登记编辑权限  -->
		<shiro:hasPermission name="event:ccmEventIncident:edit">
			<li><a href="${ctx}/event/ccmEventIncident/form">案（事）件登记添加</a></li>
		</shiro:hasPermission> --%>
		<!-- 事件干系人 -->
		<c:if test="${not empty ccmEventStakeholder.incidentId}">
		<li><a style="width: 140px;text-align:center" href="${ctx}/event/ccmEventStakeholder/list?incidentId=${ccmEventStakeholder.incidentId}">数据列表</a></li>
		</c:if>
		<c:if test="${empty ccmEventStakeholder.incidentId}">
		<li><a href="${ctx}/event/ccmEventStakeholder/list?preventType=${ccmEventStakeholder.preventType}">数据列表</a></li>
		</c:if>
		<li class="active" style="width: 200px"><a class="nav-head" href="${ctx}/event/ccmEventStakeholder/form?id=${ccmEventStakeholder.id}&incidentId=${ccmEventStakeholder.incidentId}">案（事）件干系人<shiro:hasPermission name="event:ccmEventStakeholder:edit">${not empty ccmEventStakeholder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="event:ccmEventStakeholder:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmEventStakeholder" action="${ctx}/event/ccmEventStakeholder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="incidentId" />
		<sys:message content="${message}"/>		
		
		<table border="0px"
			style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;border-top-color: white">
			
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>干系人类型：</label>
						<div class="controls">
							<form:select path="preventType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_event_stakeholder_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>姓名：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="80" class="input-xlarge required"/>

						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">曾用名：</label>
						<div class="controls">
							<form:input path="usedname" htmlEscape="false" maxlength="50" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>证件类型：</label>
						<div class="controls">
							<form:select path="idenCode" class="input-xlarge required" items="${fns:getDictList('sys_ccm_org_papers')}" 
								itemLabel="label" itemValue="value" htmlEscape="false">
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>证件号码：</label>
						<div class="controls">
							<form:input path="idenNum" htmlEscape="false" maxlength="30" class="input-xlarge ident1 card required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>性别：</label>
						<div class="controls">
							<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" 
								itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>出生日期：</label>
						<div class="controls">
							<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								onchange="initAge(this.value)"
								   value="<fmt:formatDate value="${ccmEventStakeholder.birthday}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({minDate:'{%y-150}-%M-%d',maxDate: '%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">国籍（地区）：</label>
						<div class="controls">
							<form:input path="nationality" htmlEscape="false" maxlength="3" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">民族：</label>
						<div class="controls">
							<form:select path="nation" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_volk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">籍贯：</label>
						<div class="controls">
							<form:input path="censu" htmlEscape="false" maxlength="6" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">婚姻状况：</label>
						<div class="controls">
							<form:select path="marriage" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_mari_stat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">政治面貌：</label>
						<div class="controls">
							<form:select path="politics" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_poli_stat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">学历：</label>
						<div class="controls">
							<form:select path="education" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">宗教信仰：</label>
						<div class="controls">
							<form:select path="belief" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_belief')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					     	</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">职业类别：</label>
						<div class="controls">
							<form:input path="profType" htmlEscape="false" maxlength="5" class="input-xlarge "/> 
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">职业：</label>
						<div class="controls">
							<form:input path="profession" htmlEscape="false" maxlength="30" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">服务处所：</label>
						<div class="controls">
							<form:input path="servPlace" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">户籍地：</label>
						<div class="controls">
							<form:input path="domicile" htmlEscape="false" maxlength="6" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">户籍门（楼）详址：</label>
						<div class="controls">
							<form:input path="domiciledetail" htmlEscape="false" maxlength="80" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">现居住地：</label>
						<div class="controls">
							<form:input path="residence" htmlEscape="false" maxlength="6" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">现住门（楼）详址：</label>
						<div class="controls">
							<form:input path="residencedetail" htmlEscape="false" maxlength="80" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否为严重精神障碍患者：</label>
						<div class="controls">
							<form:radiobuttons path="isPsychogeny" items="${fns:getDictList('yes_no')}" 
								itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否为未成年人：</label>
						<div class="controls">
                            <input name="isNonage" id="isNonage" style="display: none"/>
                            <form:radiobuttons path="isNonage" disabled="true"   items="${fns:getDictList('yes_no')}"
				            	itemLabel="label" itemValue="value"  htmlEscape="false" class="required"/>
			      			<span class="help-inline"><font color="red">未成年人为不满18周岁</font> </span>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否为青少年：</label>
						<div class="controls">
                            <input name="isKym" id="isKym" style="display: none" />
							<form:radiobuttons path="isKym" disabled="true" items="${fns:getDictList('yes_no')}"
			        			itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
                            <span class="help-inline"><font color="red">青少年为已满6周岁不满25周岁</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"></label>
						<div class="controls">
							
						</div>
					</div>
				</td>
			</tr>
	    </table>
		<div class="form-actions">
			<shiro:hasPermission name="event:ccmEventStakeholder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>