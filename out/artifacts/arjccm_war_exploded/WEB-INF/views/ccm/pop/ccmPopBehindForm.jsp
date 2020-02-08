<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>留守人员管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//关闭弹框事件
				$('#btnCancel').click(function() {
					parent.layer.close(parent.layerIndex);
				})
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#btnSubmit").removeAttr('disabled');
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
				$("td").css({"padding":"8px"});
				$("td").css({"border":"0px dashed #CCCCCC"});
				
				//创建案事件历史
				var jsonString = '${documentList}';
                data = JSON.parse(jsonString);  
				$(".fishBone1").fishBone(data, '${ctx}','deal');
				$(".fishBone2").fishBone(data, '${ctx}','read');
			});
</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">

</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatBehind">留守人口统计</a></li>
		<li><a href="${ctx}/pop/ccmPopBehind/">留守人员列表</a></li>
		<li class="active"><a
			href="${ctx}/pop/ccmPopBehind/form?id=${ccmPopBehind.id}">留守人员<shiro:hasPermission
					name="pop:ccmPopBehind:edit">${not empty ccmPopBehind.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="pop:ccmPopBehind:edit">查看</shiro:lacksPermission></a></li> --%>
	<%-- 	<li><a
			href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmPopBehind.id}&relevance_table=ccm_pop_behind">跟踪信息<shiro:hasPermission
					name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a>
	</li> --%>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmPopBehind"
		action="${ctx}/pop/ccmPopBehind/save" method="post"
		class="form-horizontal">
		<h4 class="tableNamefirst color-bg6">人员基本信息</h4>
		<form:hidden path="id" />
		<form:hidden path="peopleId" value="${ccmPeople.id}" />
		<table class="first_table" border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div>
						<label class="control-label">人口类型：</label>
						<div class="controls">
							${fns:getDictLabel(ccmPopBehind.type, 'sys_ccm_people', "")}
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">姓名：</label>
						<div class="controls">
							${ccmPopBehind.name}
						</div>
					</div>
				</td>
				<td rowspan="4" width="30%" align=left >
					<div>
						<label class="control-label">照片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge" /> 
							<sys:ckfinder input="images" readonly="true" type="images" uploadPath="/photo/ShiYouRenKou"
							selectMultiple="false" maxWidth="120" maxHeight="180" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">公民身份号码：</label>
						<div class="controls">
							${ccmPopBehind.ident}
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">籍贯：</label>
						<div class="controls">
							${ccmPopBehind.censu}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">性别：</label>
						<div class="controls">
							${fns:getDictLabel(ccmPopBehind.sex, 'sex', '')}
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">联系方式：</label>
						<div class="controls">
							${ccmPopBehind.telephone}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">户籍详细地址：</label>
						<div class="controls">
							${ccmPopBehind.domiciledetail}
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">现住详细地址：</label>
						<div class="controls">
							${ccmPopBehind.residencedetail}
						</div>
					</div>
				</td>
			</tr>
		</table>
		<h4 class="tableName color-bg6">留守人员信息</h4>
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>留守人员类型：</label>
						<div class="controls">
							<form:select path="staytype" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_rear_type')}"
									itemTd="td" itemValue="value" htmlEscape="false" />
							</form:select>
							<br><span class="help-inline"><font color="red">留守老人是有子女到本县以外务工达6个月以上；</font> </span>
							<br><span class="help-inline"><font color="red">留守妇女是丈夫到本县以外务工达6个月以上；</font> </span>
							<br><span class="help-inline"><font color="red">留守儿童是父母一方或者双方到本县以外务工达6个月以上；</font> </span>
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>关注程度：</label>
						<div class="controls">
							<form:select path="atteType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_conc_exte')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">个人年收入：</label>
						<div class="controls">
							<form:input path="annualincome" htmlEscape="false" maxlength="8" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>健康状况：</label>
						<div class="controls">
							<form:select path="health" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_phy_cdt')}"
									itemTd="td" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">家庭主要成员身份号码：</label>
						<div class="controls">
							<form:input path="crucialcondition" htmlEscape="false" maxlength="18" class="input-xlarge card" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">家庭主要成员姓名：</label>
						<div class="controls">
							<form:input path="crucialname" htmlEscape="false" maxlength="50" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">家庭主要成员健康状况：</label>
						<div class="controls">
							<form:select path="crucialhealth" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_phy_cdt')}"
									itemTd="td" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">与留守人员关系：</label>
						<div class="controls">
							<form:select path="crucialrelation" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('sys_ccm_fami_ties')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">家庭主要成员联系方式：</label>
						<div class="controls">
							<form:input path="crucialtelephone" htmlEscape="false" maxlength="50" class="input-xlarge phone" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">家庭主要成员工作详细地址：</label>
						<div class="controls">
							<form:input path="crucialwork" htmlEscape="false" maxlength="200" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">家庭年收入：</label>
						<div class="controls">
							<form:input path="crucialmoney"
						htmlEscape="false" maxlength="8" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">困难及诉求：</label>
						<div class="controls">
							<form:textarea path="difficult" htmlEscape="false" rows="4" maxlength="1024" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">帮扶情况：</label>
						<div class="controls">
							<form:textarea path="helpcase" htmlEscape="false" rows="4" maxlength="1024" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " />
						</div>
					</div>
				</td>
			</tr>

		</table>
		
	<%-- 	<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			  <%@include file="/WEB-INF/views/include/ccmlog.jsp" %>

		</table> --%>

		<div class="form-actions">
			<shiro:hasPermission name="pop:ccmPopBehind:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关闭"
				 />
		</div>
	</form:form><br>
	<c:if test="${documentNumber > 0}">
		<shiro:hasPermission name="log:ccmLogTail:edit">
			<h4>&nbsp;跟踪信息：</h4>
			<br>
			<div class="fishBone1" ></div>
		</shiro:hasPermission>
		<shiro:lacksPermission name="log:ccmLogTail:edit">
			<h4>&nbsp;查看跟踪信息：</h4>
			<br>
			<div class="fishBone2" ></div>
		</shiro:lacksPermission> 
	</c:if>
</body>
</html>