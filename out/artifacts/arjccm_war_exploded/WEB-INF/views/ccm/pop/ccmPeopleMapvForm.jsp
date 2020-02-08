<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>重点人员信息</title>
<meta name="decorator" content="technology" />
<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("td").css({"padding":"8px"});
				$("td").css({"border":"1px dashed #CCCCCC"});
			});

	<%--			//获取url中的参数--%>
	<%--			function getUrlParam(name) {--%>
	<%--			    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象--%>
	<%--			    var r = window.location.search.substr(1).match(reg);  //匹配目标参数--%>
	<%--			    if (r != null) return unescape(r[2]); return null; //返回参数值--%>
	<%--			}--%>
	<%--			var hide1=getUrlParam('hide1');--%>
	<%--			var hide2=getUrlParam('hide2');--%>
	<%--			if(hide1!=null&&hide2!=null){--%>
	<%--				if(hide1=="true"){--%>
	<%--					$('.hide1').show();--%>
	<%--					$('.form-actions').hide();--%>
	<%--					$('.help-inline').hide();--%>
	<%--					$('.input-xlarge').attr("readonly","readonly");--%>
	<%--					$('.input-medium').attr("disabled","disabled");--%>
	<%--					$('.displayedbuttons').attr("disabled","disabled");--%>
	<%--				}--%>
	<%--				if(hide2=="true"){--%>
	<%--					$('.form-actions').hide();--%>
	<%--					$('.hide2').show();--%>
	<%--					--%>
	<%--				}--%>
	<%--			}else{--%>
	<%--				$('.hide1').show();--%>
	<%--				$('.hide2').show();--%>
	<%--			}--%>
	<%--			//$("#name").focus();--%>
	<%--			$("#inputForm")--%>
	<%--					.validate(--%>
	<%--							{--%>
	<%--								submitHandler : function(form) {--%>
	<%--									$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');--%>
	<%--									form.submit();--%>
	<%--								},--%>
	<%--								errorContainer : "#messageBox",--%>
	<%--								errorPlacement : function(error, element) {--%>
	<%--									$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");--%>
	<%--									if (element.is(":checkbox")--%>
	<%--											|| element.is(":radio")--%>
	<%--											|| element.parent().is(--%>
	<%--													".input-append")) {--%>
	<%--										error.appendTo(element.parent()--%>
	<%--												.parent());--%>
	<%--									} else {--%>
	<%--										error.insertAfter(element);--%>
	<%--									}--%>
	<%--								}--%>
	<%--							});--%>
	<%--			$("td").css({"padding":"8px"});--%>
	<%--			$("td").css({"border":"1px dashed #CCCCCC"});--%>
	<%--			//跟踪信息--%>
	<%--			var jsonString = '${documentList}';--%>
    <%--            data = JSON.parse(jsonString);  --%>
	<%--			$(".fishBone1").fishBone(data, '${ctx}','deal');--%>
	<%--			$(".fishBone2").fishBone(data, '${ctx}','read');--%>
	<%--		});--%>
</script>
</head>
<div>
<%-- 	<ul class="nav nav-tabs">
		<li><a href="${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatPsychogeny">肇事肇祸等严重精神障碍患者口统计</a></li>
		<li><a  href="javascript:;" data-href="${ctx}/house/ccmHousePsychogeny" onclick="HasSecret(this)">肇事肇祸等严重精神障碍患者列表</a></li>
		<li class="active">
			<a href="${ctx}/house/ccmHousePsychogeny/form?id=${ccmHousePsychogeny.id}">肇事肇祸等严重精神障碍患者
				<shiro:hasPermission name="house:ccmHousePsychogeny:edit">${not empty ccmHousePsychogeny.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="house:ccmHousePsychogeny:edit">查看</shiro:lacksPermission>
			</a>
		</li>
			<li><a
			href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmHousePsychogeny.id}&relevance_table=ccm_house_psychogeny">跟踪信息<shiro:hasPermission
					name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a></li>
	</ul> --%>
	<br />
	<form:form id="inputForm" modelAttribute="ccmPeople" action="" method="post" class="form-horizontal">
<%--		<form:hidden path="peopleId" />--%>
<%--		<sys:message content="${message}" />--%>
<%--		<h4 class="tableNamefirst">新增重点人员信息</h4>--%>
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div>
						<label class="control-label">人口类型：</label>
						<label class="control-label" style="text-align: center">
							${fns:getDictLabel(ccmPeople.type, 'sys_ccm_people', "")}
						</label>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">姓名：</label>
						<label class="control-label" style="text-align: center">
							${ccmPeople.name}
						</label>
					</div>
				</td>
				<td rowspan="4" width="30%" align="left" >
					<div>
						<label class="control-label">照片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge" readonly="true" />
							<sys:ckfinder input="images" readonly="true" type="images" uploadPath="/photo/ShiYouRenKou" selectMultiple="false" maxWidth="120" maxHeight="180" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">公民身份号码：</label>
						<label class="control-label" style="text-align: center">
							${ccmPeople.ident}
						</label>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">籍贯：</label>
						<label class="control-label" style="text-align: center">
							${ccmPeople.censu}
						</label>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">性别：</label>
						<label class="control-label" style="text-align: center">
							${fns:getDictLabel(ccmPeople.sex, 'sex', '')}
						</label>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">联系方式：</label>
						<label class="control-label" style="text-align: center">
							${ccmPeople.telephone}
						</label>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">户籍详细地址：</label>
						<label class="control-label" style="text-align: center">
							${ccmPeople.domiciledetail}
						</label>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">现住详细地址：</label>
						<label class="control-label" style="text-align: center">
							${ccmPeople.residencedetail}
						</label>
					</div>
				</td>
			</tr>
		</table>
	</form:form>

<%--		<h4 class="tableName">肇事肇祸等严重精神障碍患者信息</h4>--%>
<%--		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">家庭经济状况：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:select path="economic" class="input-xlarge required">--%>
<%--								<form:option value="" label="" />--%>
<%--								<form:options items="${fns:getDictList('ccm_famy_codt')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
<%--							</form:select>--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">是否纳入低保：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:radiobuttons path="allowance" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="displayedbuttons required" />--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">监护人公民身份证号码：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:input path="guarIden" htmlEscape="false" maxlength="18" class="input-xlarge card" />--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">监护人姓名：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:input path="guarName" htmlEscape="false" maxlength="50" class="input-xlarge required" />--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">监护人联系方式：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:input path="guarTel" htmlEscape="false" maxlength="50" class="input-xlarge required phone" />--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">初次发病日期：</label>--%>
<%--						<div class="controls">--%>
<%--							<input name="dateonset" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${ccmHousePsychogeny.dateonset}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">目前诊断类型：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:select path="diagType" class="input-xlarge required">--%>
<%--								<form:option value="" label="" />--%>
<%--								<form:options items="${fns:getDictList('ccm_now_diag')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
<%--							</form:select>--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">有无肇事肇祸史：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:radiobuttons path="acciHist" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="displayedbuttons required" />--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">肇事肇祸次数：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:input path="acciCount" htmlEscape="false" maxlength="3" class="input-xlarge  digits" type="number"/>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">上次肇亊肇祸日期：</label>--%>
<%--						<div class="controls">--%>
<%--							<input name="acciLast" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="<fmt:formatDate value="${ccmHousePsychogeny.acciLast}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">目前危险性评估等级：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:select path="dangAsse" class="input-xlarge required">--%>
<%--								<form:option value="" label="" />--%>
<%--								<form:options items="${fns:getDictList('ccm_nors_asle')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
<%--							</form:select>--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">治疗情况：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:select path="treaCond" class="input-xlarge required">--%>
<%--								<form:option value="" label="" />--%>
<%--								<form:options items="${fns:getDictList('ccm_treat_cond')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
<%--							</form:select><span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">治疗医院名称：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:input path="hospital" htmlEscape="false" maxlength="100" class="input-xlarge " />--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">实施住院治疗原因：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:checkboxes path="hospReasonList" items="${fns:getDictList('ccm_hosl_cause')}" itemLabel="label" --%>
<%--							itemValue="value" htmlEscape="false" class="displayedbuttons"/>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">接收康复训练机构名称：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:input path="traiInst" htmlEscape="false" maxlength="100" class="input-xlarge " />--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">参与管理人员：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:checkboxes path="managementList" items="${fns:getDictList('ccm_parp_mang')}" itemLabel="label" itemValue="value" htmlEscape="false" class="displayedbuttons"/>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">帮扶情况：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:checkboxes path="helpcaseList" items="${fns:getDictList('ccm_supp_case')}" itemLabel="label" itemValue="value" htmlEscape="false" class="displayedbuttons"/>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">关注程度：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:select path="atteType" class="input-xlarge required">--%>
<%--								<form:option value="" label="" />--%>
<%--								<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
<%--							</form:select>--%>
<%--							<span class="help-inline"><font color="red">*</font> </span>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td>--%>
<%--					<div>--%>
<%--						<label class="control-label">备注信息：</label>--%>
<%--						<div class="controls">--%>
<%--							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--		</table>--%>
<%--		--%>
<%--		&lt;%&ndash; <table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">--%>
<%--			<%@include file="/WEB-INF/views/include/ccmlog.jsp" %>--%>
<%--		</table> &ndash;%&gt;--%>

<%--		<div class="form-actions">--%>
<%--			<shiro:hasPermission name="house:ccmHousePsychogeny:edit">--%>
<%--				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>--%>
<%--			<!-- <input id="btnCancel" class="btn" type="button" value="返 回"--%>
<%--				onclick="history.go(-1)" /> -->--%>
<%--		</div>--%>
<%--	</form:form><br>--%>
<%--	<c:if test="${documentNumber > 0}">--%>
<%--		<shiro:hasPermission name="log:ccmLogTail:edit">--%>
<%--			<h4 class="hide2">&nbsp;跟踪信息：</h4>--%>
<%--			<br>--%>
<%--			<div class="fishBone1 hide2" ></div>--%>
<%--		</shiro:hasPermission>--%>
<%--		<shiro:lacksPermission name="log:ccmLogTail:edit">--%>
<%--			<h4 class="hide2">&nbsp;查看跟踪信息：</h4>--%>
<%--			<br>--%>
<%--			<div class="fishBone2 hide2" ></div>--%>
<%--		</shiro:lacksPermission> --%>
<%--	</c:if>--%>
</div>
</html>