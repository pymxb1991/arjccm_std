<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>老年人口管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/ccm/pop/ccmPeopleForm.js"></script>
	<script type="text/javascript" src="${ctxStatic}/ccm/validator/validator.js"></script>
	<script type="text/javascript" src="${ctxStatic}/ccm/validator.js"></script>
<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
	<style type="text/css">
		.pad{padding: 5px; padding-left: 10px}
		#person{display: none;}
		#float{display: none;}
		#oversea{display: none;}
		#unsettle{display: none;}
		.input-xlarge{width: 200px;}
		.select2-container.input-xlarge{width:215px;}
		.input-medium.Wdate {width: 200px;}
		
.nav  li .znul {
	background-color: #e9493b;
	color: white;
	box-shadow: 0px 1px 3px rgba(34, 25, 25, 0.2);
	margin-right: 10px;
	float: left;
	border-radius: 2px;
}

.nav  li .znul:HOVER {
	background-color: white;
	color: #6d6666;
}

.nav  li .zzul {
	background-color: #01ad5c;
	color: white;
	box-shadow: 0px 1px 3px rgba(34, 25, 25, 0.2);
	margin-right: 10px;
	float: left;
	border-radius: 2px;
}

.nav  li .zzul:HOVER {
	background-color: white;
	color: #6d6666;
}

.nav  li .gzul {
	background-color: #3c9fd6;
	color: white;
	box-shadow: 0px 1px 3px rgba(34, 25, 25, 0.2);
	margin-right: 10px;
	float: left;
	border-radius: 2px;
}

.nav  li .gzul:HOVER {
	background-color: white;
	color: #6d6666;
}

#zd1 {
	color: #e9493b;
	line-height: 30px;
	font-size: 130%
}

#zd2 {
	color: #01ad5c;
	line-height: 30px;
	font-size: 130%
}

#zd3 {
	color: #3c9fd6;
	line-height: 30px;
	font-size: 130%
}

.ulz {
	margin-left: 50px;
}
	</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("td").css({"padding":"8px"});
				$("td").css({"border":"0px dashed #CCCCCC"});
				//跟踪信息
				var jsonString = '${documentList}';
                data = JSON.parse(jsonString);  
				$(".fishBone1").fishBone(data, '${ctx}','deal');
				$(".fishBone2").fishBone(data, '${ctx}','read');
			});
	
	function saveForm(){
		var areaComIdId = $("#areaComIdId").val();
		var areaGridIdId = $("#areaGridIdId").val();
		var html1 = '<label for="" class="error">必选字段 *<label>';
		if (areaComIdId.length != 0) {
			$("#showCom").html("*");
		} else {
			$("#showCom").html(html1);
		}
		if (areaGridIdId.length != 0) {
			$("#showGrid").html("*");
		} else {
			$("#showGrid").html(html1);
		}
		
	} 

</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">


</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatOlder">数据统计</a></li>
		<li><a style="width: 140px;text-align:center" href="${ctx}/pop/ccmPeople/listOlder">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/pop/ccmPeople/formOlder?id=${ccmPeople.id}">老年人<shiro:hasPermission name="pop:ccmPeople:edit">${not empty ccmPeople.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pop:ccmPeople:edit">查看</shiro:lacksPermission></a></li>
		<%-- <c:if test="${not empty ccmPeople.id}">
			<li><a href="${ctx}/log/ccmLogTail/formProOlder?relevance_id=${ccmPeople.id}&relevance_table=ccm_people">跟踪信息<shiro:hasPermission name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a></li>
		</c:if> --%>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmPeople" action="${ctx}/pop/ccmPeople/saveOlder" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<table border="0px"
			style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%">
			<h4 class="color-bg6">基本信息：</h4>
			<tr>
				<td>
					<div>
						<label class="control-label">人口类型：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge " id="sel"
								onchange="sels()">
								<form:options items="${fns:getDictList('sys_ccm_people')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td style="width: 35%">
					<div>
						<label class="control-label">性别：</label>
						<div class="controls">
							<form:radiobuttons path="sex" items="${fns:getDictList('sex')}"
								itemLabel="label" itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red" id="show1">*</font></span>姓名：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="50"
								class="input-xlarge required" />

							</span>
						</div>
					</div>
				</td>

				<td>
					<div>
						<label class="control-label">曾用名：</label>
						<div class="controls">
							<form:input path="usedname" htmlEscape="false" maxlength="50"
								class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">出生日期：</label>
						<div class="controls">
							<input name="birthday" type="text" readonly="readonly"
								   maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${ccmPeople.birthday}" pattern="yyyy-MM-dd"/>"
								   onclick="WdatePicker({minDate:'{%y-150}-%M-%d',maxDate: '%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td >
					<div>
						<label class="control-label">照片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false"
										 maxlength="255" class="input-xlarge" />
							<sys:ckfinder input="images" type="images"
										  uploadPath="/photo/ShiYouRenKou" selectMultiple="false" maxWidth="240"
										  maxHeight="360" />
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
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('sys_volk')}"
											  itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>

				<td>
					<div>
						<label class="control-label">公民身份号码：</label>
						<div class="controls">
							<form:input path="ident" htmlEscape="false" maxlength="18"
								class="input-xlarge ident0 card" />
							<span id="ident0"></span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font
								color="red" id="show1">*</font> </span>联系方式：</label>
						<div class="controls">
							<form:input path="telephone" htmlEscape="false" maxlength="18"
										class="input-xlarge telephone0 phone" />
							<span id="telephone0"></span>
						</div>
					</div>
				</td>
			</tr>

			<tr>

				<td>
					<div>
						<label class="control-label">籍贯：</label>
						<div class="controls">
							<form:input path="censu" htmlEscape="false" maxlength="6"
								class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">婚姻状况：</label>
						<div class="controls">
							<form:select path="marriage" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('sys_ccm_mari_stat')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">政治面貌：</label>
						<div class="controls">
							<form:select path="politics" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('sys_ccm_poli_stat')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
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
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('sys_ccm_degree')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">宗教信仰：</label>
						<div class="controls">
							<form:select path="belief" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('sys_ccm_belief')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">职业类别：</label>
						<div class="controls">
							<form:input path="profType" htmlEscape="false" maxlength="5"
								class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>

				<td>
					<div>
						<label class="control-label">职业：</label>
						<div class="controls">
							<form:input path="profession" htmlEscape="false" maxlength="30"
								class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否公职人员：</label>
						<div class="controls">
							<form:select path="isPublicServants" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('yes_no')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">所在单位/学校：</label>
						<div class="controls">
							<form:input path="officeName" htmlEscape="false" maxlength="30"
								class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>

			<tr>

				<td>
					<div>
						<label class="control-label">是否常住：</label>
						<div class="controls">
							<form:radiobuttons path="isPermanent"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否重点关注人员：</label>
						<div class="controls">
							<form:radiobuttons path="focuPers"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">户籍地：</label>
						<div class="controls">
							<form:input path="domicile" htmlEscape="false" maxlength="6"
								class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>


				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red" id="showCom">*</font></span>所属社区：</label>
						<div class="controls" onmouseout="onclickNet()">
							<!--  
							<sys:treeselect id="areaComId" name="areaComId.id" value="${ccmPeople.areaComId.id}" labelName="areaComId.name" labelValue="${ccmPeople.areaComId.name}"
							title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="false" cssStyle=""/>
							-->
							<sys:treeselect id="areaComId" name="areaComId.id"
								value="${ccmPeople.areaComId.id}" labelName="areaComId.name"
								labelValue="${ccmPeople.areaComId.name}" title="区域"
								url="/tree/ccmTree/treeDataArea?type=6" cssClass=""
								allowClear="true" notAllowSelectParent="true"
								cssStyle="" />

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red" id="showGrid">*</font></span>所属网格：</label>
						<div class="controls">
						<div class="help-inline" id="newNet" onmouseout="onclickHouse()"
							onmousemove="onclickNet()">
							<sys:treeselect id="areaGridId" name="areaGridId.id"
								value="${ccmPeople.areaGridId.id}" labelName="areaGridId.name"
								labelValue="${ccmPeople.areaGridId.name}" title="区域"
								url="/tree/ccmTree/treeDataArea?type=7&areaid=" cssClass=""
								allowClear="true" notAllowSelectParent="true"
								cssStyle="" />
						</div>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">所属房屋：</label>
						<div class="controls" id="newHouse" onmousemove="onclickHouse()">
							<!--  
							<sys:treeselect id="roomId" name="roomId.id" value="${ccmPeople.roomId.id}" labelName="roomId.houseBuild" labelValue="${ccmPeople.roomId.houseBuild}"
							title="房屋" url="/pop/ccmPopTenant/treeData?type=7" cssClass="" allowClear="true" notAllowSelectParent="true" cssStyle=""/>
							-->
							<sys:treeselect id="roomId" name="roomId.id"
								value="${ccmPeople.roomId.id}" labelName="roomId.houseBuild"
								labelValue="${ccmPeople.roomId.houseBuild}" title="房屋"
								url="/tree/ccmTree/treeDataArea?type=9&areaid=" cssClass=""
								allowClear="true" notAllowSelectParent="true"
								cssStyle="" />

						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">户籍门（楼）详址：</label>
						<div class="controls">
							<form:input path="domiciledetail" htmlEscape="false"
								maxlength="80" class="input-xlarge " />
						</div>
					</div>
				</td>

				<td>
					<div>
						<label class="control-label">现住地：</label>
						<div class="controls">
							<form:input path="residence" htmlEscape="false" maxlength="6"
								class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">服务处所：</label>
						<div class="controls">
							<form:input path="servPlace" htmlEscape="false" maxlength="100"
								class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<label class="control-label">特殊关注类型：</label>
						<div class="controls">
							<form:checkboxes path="specialCareTypeList"
											 items="${fns:getDictList('pop_special_care_type')}"
											 itemLabel="label" itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label">现住门（楼）详址：</label>
						<div class="controls">
							<form:input path="residencedetail" htmlEscape="false"
								maxlength="80" class="input-xlarge " />
						</div>
					</div>
				</td>


			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="2"
										   maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
		</table>
		<br />
		<shiro:hasPermission name="pop:ccmPeople:edit">
			<ul class="nav nav-pills ulz">
				<li><h4 id="zd1">
						<i class="icon-tags"></i>特殊人群标记：
					</h4>
				<li>
				<c:if test="${ccmPeople.isRelease==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseRelease/specialform?id=${ccmPeople.id}', '安置帮教人员标记', '1200px', '900px')">安置帮教人员标记</a></li>
					</c:if> <c:if test="${ccmPeople.isRectification==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseRectification/specialform?id=${ccmPeople.id}', '社区矫正人员标记', '1200px', '900px')">社区矫正人员标记</a></li>
					</c:if> <c:if test="${ccmPeople.isPsychogeny==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHousePsychogeny/specialform?id=${ccmPeople.id}', '肇事肇祸等严重精神障碍患者', '1200px', '900px')">肇事肇祸等严重精神障碍患者</a></li>
					</c:if> <c:if test="${ccmPeople.isAids==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseAids/specialform?id=${ccmPeople.id}', '艾滋病危险人员标记', '1200px', '900px')">艾滋病危险人员标记</a></li>
					</c:if> <c:if test="${ccmPeople.isDangerous==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseDangerous/specialform?id=${ccmPeople.id}', '危险品从业人员标记', '1200px', '900px')">危险品从业人员标记</a></li>
					</c:if> 
				<c:if test="${ccmPeople.isDrugs==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/pop/ccmPeople/specialform?id=${ccmPeople.id}', '吸毒人员标记', '1200px', '900px')">吸毒人员标记</a></li>
					</c:if> <c:if test="${ccmPeople.isVisit==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHousePetition/specialform?id=${ccmPeople.id}', '重点上访人员标记', '1200px', '900px')">重点上访人员标记</a></li>
					</c:if> <c:if test="${ccmPeople.isHeresy==1}">
						<li><a class="znul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseHeresy/specialform?id=${ccmPeople.id}', '涉教人员标记', '1200px', '900px')">涉教人员标记</a></li>
					</c:if>
					<c:if
						test="${ccmPeople.isRelease==0||ccmPeople.isRelease==null||ccmPeople.isRelease==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseRelease/specialform?id=${ccmPeople.id}', '安置帮教人员标记', '1200px', '900px')">安置帮教人员标记</a></li>
					</c:if> <c:if
						test="${ccmPeople.isRectification==0||ccmPeople.isRectification==null||ccmPeople.isRectification==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseRectification/specialform?id=${ccmPeople.id}', '社区矫正人员标记', '1200px', '900px')">社区矫正人员标记</a></li>
					</c:if> <c:if
						test="${ccmPeople.isPsychogeny==0||ccmPeople.isPsychogeny==null||ccmPeople.isPsychogeny==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHousePsychogeny/specialform?id=${ccmPeople.id}', '肇事肇祸等严重精神障碍患', '1200px', '900px')">肇事肇祸等严重精神障碍患者</a></li>
					</c:if> <c:if
						test="${ccmPeople.isAids==0||ccmPeople.isAids==null||ccmPeople.isAids==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseAids/specialform?id=${ccmPeople.id}', '艾滋病危险人员标记', '1200px', '900px')">艾滋病危险人员标记</a></li>
					</c:if> <c:if
						test="${ccmPeople.isDangerous==0||ccmPeople.isDangerous==null||ccmPeople.isDangerous==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseDangerous/specialform?id=${ccmPeople.id}', '危险品从业人员标记', '1200px', '900px')">危险品从业人员标记</a></li>
					</c:if>
					<c:if
						test="${ccmPeople.isDrugs==0||ccmPeople.isDrugs==null||ccmPeople.isDrugs==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/pop/ccmPeople/specialform?id=${ccmPeople.id}', '吸毒人员标记', '1200px', '900px')">吸毒人员标记</a></li>
					</c:if> <c:if
						test="${ccmPeople.isVisit==0||ccmPeople.isVisit==null||ccmPeople.isVisit==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHousePetition/specialform?id=${ccmPeople.id}', '重点上访人员标记', '1200px', '900px')">重点上访人员标记</a></li>
					</c:if> <c:if
						test="${ccmPeople.isHeresy==0||ccmPeople.isHeresy==null||ccmPeople.isHeresy==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseHeresy/specialform?id=${ccmPeople.id}', '涉教人员标记', '1200px', '900px')">涉教人员标记</a></li>
					</c:if> 
			</ul>

			

			<ul class="nav nav-pills ulz">
				<li><h4 id="zd3">
						<i class="icon-tags"></i>重点人群标记：
					</h4>
				<li> <c:if test="${ccmPeople.isKym==1}">
						<li><a class="gzul"
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseKym/specialform?id=${ccmPeople.id}', '重点青少年标记', '1200px', '900px')">重点青少年标记</a></li>
					</c:if><c:if
						test="${ccmPeople.isKym==0||ccmPeople.isKym==null||ccmPeople.isKym==''}">
						<li><a
							onclick="parent.LayerDialog('${ctx}/house/ccmHouseKym/specialform?id=${ccmPeople.id}', '重点青少年标记', '1200px', '900px')">重点青少年标记</a></li>
					</c:if>
			</ul>
		</shiro:hasPermission>
		
		
		
		<br/>
		
		<h4 class="color-bg6">其他信息：</h4>
		<table id="person" border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; width: 100%" >
			<tr><td colspan="6">户籍信息：</td></tr>
			<tr>
				<td>
					<div>
						<label class="control-label">人户一致标识：</label>
						<div class="controls">
							<form:select path="uniformlogo" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_huh_cst')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">户号：</label>
						<div class="controls">
							<form:input path="account" htmlEscape="false" maxlength="9" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">户主公民身份号码：</label>
						<div class="controls">
							<form:input path="accountidentity" htmlEscape="false" maxlength="18" class="input-xlarge ident1 card"/>
							<span id="ident1"></span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">户主姓名：</label>
						<div class="controls">
							<form:input path="accountname" htmlEscape="false" maxlength="10" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">与户主关系：</label>
						<div class="controls">
							<form:select path="accountrelation" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_fami_ties')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">户主联系方式：</label>
						<div class="controls">
							<form:input path="accounttelephone" htmlEscape="false" maxlength="50" class="input-xlarge telephone1 phone"/>
							<span id="telephone1"></span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">户籍状态：</label>
						<div class="controls">
							<form:select path="personType" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_people_person_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">户籍变动时间：</label>
						<div class="controls">
							<input name="personTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.personTime}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">迁入、迁出原因：</label>
						<div class="controls">
							<form:textarea path="personReason" htmlEscape="false" rows="3" maxlength="1000" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			
		</table>
		
		
		
		
		
		<table id="float" border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; width: 100%" >
			
			<tr><td colspan="6">流动信息：</td></tr>
			
			<tr>
				<td>
					<div>
						<label class="control-label">流入原因：</label>
						<div class="controls">
							<form:select path="flowRea" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_flow_res')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">办证类型：</label>
						<div class="controls">
							<form:select path="accrType" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_acc_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">证件号码（流入）：</label>
						<div class="controls">
							<form:input path="certNum" htmlEscape="false" maxlength="22" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">登记日期：</label>
						<div class="controls">
							<input name="recoDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.recoDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">证件到期日期：</label>
						<div class="controls">
							<input name="dueDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.dueDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">住所类型：</label>
						<div class="controls">
							<form:select path="domiType" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_dom_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">流出时间：</label>
						<div class="controls">
							<input name="time" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.time}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">流出原因：</label>
						<div class="controls">
							<form:input path="cause" htmlEscape="false" maxlength="18" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">说明：</label>
						<div class="controls">
							<form:input path="explainelse" htmlEscape="false" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			
		</table>
		
		
		
		
		
		<table id="oversea" border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC;  width: 100%" >	
			
			<tr><td colspan="6">境外信息：</td></tr>
			<tr>
				<td>
					<div>
						<label class="control-label">外文姓：</label>
						<div class="controls">
							<form:input path="esurname" htmlEscape="false" maxlength="40" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">外文名：</label>
						<div class="controls">
							<form:input path="ename" htmlEscape="false" maxlength="40" class="input-xlarge "/>
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
						<label class="control-label">证件类型：</label>
						<div class="controls">
							<form:select path="idenCode" class="input-xlarge " items="${fns:getDictList('sys_ccm_org_papers')}" itemLabel="label" itemValue="value" htmlEscape="false">
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">证件号码（境外）：</label>
						<div class="controls">
							<form:input path="idenNum" htmlEscape="false" maxlength="30" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">证件有效期：</label>
						<div class="controls">
							<input name="idenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.idenDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">来华目的：</label>
						<div class="controls">
							<form:select path="purpose" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_cn_aim')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">抵达日期：</label>
						<div class="controls">
							<input name="arriDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.arriDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">预计离开日期：</label>
						<div class="controls">
							<input name="departDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.departDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		
		<table id="unsettle" border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC;  width: 100%" >	
			
			<tr><td colspan="6">未落户信息：</td></tr>
			<tr>
				<td>
					<div>
						<label class="control-label">未落户时间：</label>
						<div class="controls">
							<input name="unsettleDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPeople.unsettleDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">持证种类名称：</label>
						<div class="controls">
							<form:input path="unsettleCardType" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">持证编号：</label>
						<div class="controls">
							<form:input path="unsettleCardNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">未落户原因：</label>
						<div class="controls">
							<form:textarea path="unsettleReason" htmlEscape="false" rows="3" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
		</table>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="pop:ccmPeople:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="saveForm()" value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
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