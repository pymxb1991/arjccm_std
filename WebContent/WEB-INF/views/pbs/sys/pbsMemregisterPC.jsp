<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学员信息登记</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(function() {
    // 表单对象
    var $copy = $("#memregister");
    // 点击进行提交
    $copy.find("#registerButton").click(function() {
      // 错误提示消失
      $("#messageError").hide();
      // 显示 form 表单信息
      var sIdtype = $.trim($copy.find("#sIdtype").val());
      var sIdnum = $.trim($copy.find("#sIdnum").val());
      var sName = $.trim($copy.find("#sName").val());
      var iSex = $.trim($copy.find("#iSex").val());
      var iNation = $.trim($copy.find("#iNation").val());
      var dtBirth = $.trim($copy.find("#dtBirth").val());
      var sEducation = $.trim($copy.find("#sEducation").val());
      var iType = $.trim($copy.find("#iType").val());
      var sPartybranch = $.trim($copy.find("#sPartybranch").val());
      var dtAdmission = $.trim($copy.find("#dtAdmission").val());
      var dtCorrection = $.trim($copy.find("#dtCorrection").val());
      var sPost = $.trim($copy.find("#sPost").val());
      var sMobilephone = $.trim($copy.find("#sMobilephone").val());
      var sFixedphone = $.trim($copy.find("#sFixedphone").val());
      var sHomeaddr = $.trim($copy.find("#sHomeaddr").val());
      var iStat = $.trim($copy.find("#iStat").val());
      var iOutcontact = $.trim($copy.find("#iOutcontact").val());
      var dtOutcontact = $.trim($copy.find("#dtOutcontact").val());
      var iFloat = $.trim($copy.find("#iFloat").val());
      var sFloattopro = $.trim($copy.find("#sFloattopro").val());
      var sFloattocity = $.trim($copy.find("#sFloattocity").val());
      var sFloattocounty = $.trim($copy.find("#sFloattocounty").val());
      // post请求
      $.post(ctx + "/sys/PbsCertification/register", {
        "sIdtype" : sIdtype,
        "sIdnum" : sIdnum,
        "sName" : sName,
        "iSex" : iSex,
        "iNation" : iNation,
        "dtBirth" : dtBirth,
        "sEducation" : sEducation,
        "iType" : iType,
        "sPartybranch" : sPartybranch,
        "dtAdmission" : dtAdmission,
        "dtCorrection" : dtCorrection,
        "sPost" : sPost,
        "sMobilephone" : sMobilephone,
        "sFixedphone" : sFixedphone,
        "sHomeaddr" : sHomeaddr,
        "iStat" : iStat,
        "iOutcontact" : iOutcontact,
        "dtOutcontact" : dtOutcontact,
        "iFloat" : iFloat,
        "sFloattopro" : sFloattopro,
        "sFloattocity" : sFloattocity,
        "sFloattocounty" : sFloattocounty,
        "sRegstat" : 0,
      }, function(data) {
        // 成功
        if (data == "sucess") {
          window.location = ctx + "/sys/user/info/";
        } else {
          // 错误展示
          $("#messageError").show();
          $("#messageError").html(data)
        }
      })

    });
  });
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
		<li class="active"><a>学员认证</a></li>
	</ul>
	<br />
	<sys:message content="${message}" />
	<c:if
		test="${empty  pbsPartymemreg.SRegstat  or pbsPartymemreg.SRegstat eq '2'}">
		<form class="form-horizontal" id="memregister">
			<div class="control-group">
				<label class="control-label">证件类别：</label>
				<div class="controls">
					<!-- sys_idtype  -->
					<select name="select" id="sIdtype" placeholder="请选择证件类型"
						class="input-xlarge">
						<c:forEach items="${fns:getDictList('sys_idtype')}" var="idtype">
							<option value="${idtype.value}" label="${idtype.label}">${idtype.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">身份号码：</label>
				<div class="controls">
					<input id="sIdnum" type="text" class="input-xlarge"
						placeholder="请输入身份号码">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">姓名：</label>
				<div class="controls">
					<input id="sName" type="text" class="input-xlarge"
						placeholder="请输入姓名">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">性别：</label>
				<div class="controls">
					<select name="select" id="iSex" placeholder="请选择性别信息"
						class="input-xlarge">
						<c:forEach items="${fns:getDictList('sex')}" var="type">
							<option value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">民族信息：</label>
				<div class="controls">
					<select name="select" id="iNation" placeholder="请选择民族信息"
						class="input-xlarge">
						<c:forEach items="${fns:getDictList('sys_volk')}" var="type">
							<option class="color" value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出生日期：</label>
				<div class="controls">
					<input id="dtBirth" type="text" readonly="readonly" maxlength="20"
						class="input-medium Wdate" style="width: 268px"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学历信息：</label>
				<div class="controls">
					<select name="select" id="sEducation" placeholder="请选择学历信息"
						class="input-xlarge">
						<c:forEach items="${fns:getDictList('sys_degree')}" var="type">
							<option class="color" value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学员类别：</label>
				<div class="controls">
					<select name="select" id="iType" placeholder="请选择学员类别"
						class="input-xlarge">
						<c:forEach items="${fns:getDictList('sys_mebcategory')}"
							var="type">
							<option class="color" value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">所在党支部全称：</label>
				<div class="controls">
					<input id="sPartybranch" type="text" class="input-xlarge"
						placeholder="请输入所在党支部全称">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入党时间：</label>
				<div class="controls">
					<input id="dtAdmission" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate " style="width: 268px"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">转正时间：</label>
				<div class="controls">
					<input id="dtCorrection" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate " style="width: 268px"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">工作岗位：</label>
				<div class="controls">
					<input id="sPost" type="text" class="input-xlarge"
						placeholder="请输入工作岗位">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">移动电话：</label>
				<div class="controls">
					<input id="sMobilephone" type="text" class="input-xlarge"
						placeholder="请输入移动电话">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">固定电话：</label>
				<div class="controls">
					<input id="sFixedphone" type="text" class="input-xlarge"
						placeholder="请输入固定电话">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">家庭住址：</label>
				<div class="controls">
					<input id="sHomeaddr" type="text" class="input-xlarge"
						placeholder="请输入家庭住址">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">党籍状态：</label>
				<div class="controls">
					<select name="select" id="iStat" class="input-xlarge">
						<c:forEach items="${fns:getDictList('sys_mebtype')}" var="type">
							<option class="color" value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否失联：</label>
				<div class="controls">
					<select name="select" id="iOutcontact" class="input-xlarge">
						<c:forEach items="${fns:getDictList('yes_no')}" var="type">
							<option class="color" value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">失联日期：</label>
				<div class="controls">
					<input id="dtOutcontact" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate" style="width: 268px"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否流动：</label>
				<div class="controls">
					<select name="select" id="iFloat" class="input-xlarge">
						<c:forEach items="${fns:getDictList('yes_no')}" var="type">
							<option class="color" value="${type.value}" label="${type.label}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">外出流向省：</label>
				<div class="controls">
					<input id="sFloattopro" type="text" class="input-xlarge"
						placeholder="外出流向省">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">外出流向市：</label>
				<div class="controls">
					<input id="sFloattocity" type="text" class="input-xlarge"
						placeholder="外出流向市">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">外出流向县：</label>
				<div class="controls">
					<input id="sFloattocounty" type="text" class="input-xlarge"
						placeholder="外出流向县">
				</div>
			</div>
			<div class="form-actions">
				<c:if test="${flagMem ne true}">
					<button type="button" id="registerButton" class="btn btn-primary">提交认证</button>
				</c:if>
				<c:if test="${flagMem eq true}">
				您已经是学员，无需再提交申请。
				</c:if>
			</div>
		</form>
	</c:if>
	<c:if
		test="${pbsPartymemreg.SRegstat eq '0' or pbsPartymemreg.SRegstat eq '1'}">
		<form:form id="inputForm" modelAttribute="pbsPartymemreg" action=""
			method="post" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">证件类别：</label>
				<div class="controls">
					<!-- sys_idtype  -->
					<form:select path="sIdtype" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sys_idtype')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">身份号码：</label>
				<div class="controls">
					<form:input path="sIdnum" htmlEscape="false" maxlength="30"
						readonly="true" class="input-xlarge required" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">姓名：</label>
				<div class="controls">
					<form:input path="sName" htmlEscape="false" maxlength="200"
						readonly="true" class="input-xlarge " />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">性别：</label>
				<div class="controls">
					<form:select path="iSex" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sex')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">民族信息：</label>
				<div class="controls">
					<form:select path="iNation" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sys_volk')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出生日期：</label>
				<div class="controls">
					<input name="dtBirth" type="text" maxlength="20"
						class="input-medium Wdate " readonly="readonly" style="width: 18%"
						value="<fmt:formatDate value="${pbsPartymemreg.dtBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学历信息：</label>
				<div class="controls">
					<form:select path="sEducation" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sys_degree')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">学员类别：</label>
				<div class="controls">
					<form:select path="iType" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sys_mebcategory')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入党日期：</label>
				<div class="controls">
					<input name="dtAdmission" type="text" maxlength="20"
						class="input-medium Wdate " readonly="readonly" style="width: 18%"
						value="<fmt:formatDate value="${pbsPartymemreg.dtAdmission}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">转正时间：</label>
				<div class="controls">
					<input id="dtCorrection" type="text" readonly="readonly"
						style="width: 18%" maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${pbsPartymemreg.dtCorrection}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">工作岗位：</label>
				<div class="controls">
					<form:input path="sPost" htmlEscape="false" maxlength="64"
						readonly="true" class="input-xlarge " />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">移动电话：</label>
				<div class="controls">
					<form:input path="sMobilephone" htmlEscape="false" maxlength="64"
						class="input-xlarge" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">固定电话：</label>
				<div class="controls">
					<form:input path="sFixedphone" htmlEscape="false" maxlength="64"
						class="input-xlarge" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">家庭住址：</label>
				<div class="controls">
					<form:input path="sHomeaddr" htmlEscape="false" maxlength="200"
						class="input-xlarge" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">党籍状态：</label>
				<div class="controls">
					<form:select path="iStat" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('sys_mebtype')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否失联：</label>
				<div class="controls">
					<form:select path="iOutcontact" class="input-xlarge"
						disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('yes_no')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">失联日期：</label>
				<div class="controls">
					<input name="dtOutcontact" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate " style="width: 18%"
						value="<fmt:formatDate value="${pbsPartymemreg.dtOutcontact}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否流动：</label>
				<div class="controls">
					<form:select path="iFloat" class="input-xlarge" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('yes_no')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">外出流向省：</label>
				<div class="controls">
					<form:input path="sFloattopro" htmlEscape="false" maxlength="64"
						class="input-xlarge" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">外出流向市：</label>
				<div class="controls">
					<form:input path="sFloattocity" htmlEscape="false" maxlength="64"
						class="input-xlarge" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">外出流向县：</label>
				<div class="controls">
					<form:input path="sFloattocounty" htmlEscape="false" maxlength="64"
						class="input-xlarge" readonly="true" />
				</div>
			</div>
			<div class="form-actions">
				<c:if test="${pbsPartymemreg.SRegstat eq '0' }">
					<a class="mui-btn" id="regcancel" regid="${pbsPartymemreg.id}"
						href="${ctx}/sys/PbsCertification/regcancel?id=${pbsPartymemreg.id}">
						<button type="button" class="btn btn-primary">取消认证</button>
					</a>
				</c:if>
				<c:if test="${pbsPartymemreg.SRegstat eq '1'}">
					<button type="button" class="btn" disabled="disabled">认证通过</button>
				</c:if>
			</div>
		</form:form>
	</c:if>
</body>
</html>