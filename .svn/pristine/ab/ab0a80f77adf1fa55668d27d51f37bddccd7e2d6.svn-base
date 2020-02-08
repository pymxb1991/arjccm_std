<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>地图信息表管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(
      function() {
        //$("#name").focus();
        $("#inputForm").validate(
            {
              submitHandler : function(form) {
                loading('正在提交，请稍等...');
                form.submit();
              },
              errorContainer : "#messageBox",
              errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio")
                    || element.parent().is(".input-append")) {
                  error.appendTo(element.parent().parent());
                } else {
                  error.insertAfter(element);
                }
              }
            });
        var  sSuperiorid = $(".sSuperiorid").attr("topid");
        // 选择主题
        $("#sSuperiorid").load(ctx + "/task/pbsTaskrec/namelists",function(){
        	$("#sSuperiorid").val(sSuperiorid).select2();
        });
     	// 主题id
        var topid = $(".sActivityid").attr("topid");
        // 选择主题
        $("#sActivityid").load(ctx + "/activity/pbsActivityrec/namelist",
        function() {
            $("#sActivityid").val(topid).select2();
        });
		var type = $("#type").val();
       	if( type == 1){
           	$("#mem")[0].style.display ="none";
       		$("#depart")[0].style.display ="block";
       		$("#task")[0].style.display ="none";
       		$("#activity")[0].style.display ="none";
        } else if (type == 2){
           	$("#mem")[0].style.display ="block";
       		$("#depart")[0].style.display ="none";
       		$("#task")[0].style.display ="none";
       		$("#activity")[0].style.display ="none";
        } else if(type == 3){
           	$("#mem")[0].style.display ="none";
       		$("#depart")[0].style.display ="none";
       		$("#task")[0].style.display ="none";
       		$("#activity")[0].style.display ="block";
        } else if(type == 4){
           	$("#mem")[0].style.display ="none";
       		$("#depart")[0].style.display ="none";
       		$("#task")[0].style.display ="block";
       		$("#activity")[0].style.display ="none";
        } 
        $("#type").change(function(){
        	if($("#type").val() == 1){
        		$("#mem")[0].style.display ="none";
        		$("#depart")[0].style.display ="block";
        		$("#task")[0].style.display ="none";
        		$("#activity")[0].style.display ="none";
        		//$("#sPrimarykey").val($("#sDepartmentid").val());
        	} else if ($("#type").val() == 2){
        		$("#mem")[0].style.display ="block";
        		$("#depart")[0].style.display ="none";
        		$("#task")[0].style.display ="none";
        		$("#activity")[0].style.display ="none";
        	} else if ($("#type").val() == 3){
        		$("#mem")[0].style.display ="none";
        		$("#depart")[0].style.display ="none";
        		$("#task")[0].style.display ="none";
        		$("#activity")[0].style.display ="block";
        	} else if($("#type").val() == 4){
        		$("#mem")[0].style.display ="none";
        		$("#depart")[0].style.display ="none";
        		$("#task")[0].style.display ="block";
        		$("#activity")[0].style.display ="none";
        	}
        });
   });
</script>
	<script type="text/javascript" src="${ctxStatic}/statis/js/ol.js"></script>
	<script src="${ctxStatic}/pbs/maps/pbsGeographicalForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/map/pbsGeographical/">地图信息表列表</a></li>
		<li class="active"><a
			href="${ctx}/map/pbsGeographical/form?id=${pbsGeographical.id}">地图信息表<shiro:hasPermission
					name="map:pbsGeographical:edit">${not empty pbsGeographical.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="map:pbsGeographical:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsGeographical"
		action="${ctx}/map/pbsGeographical/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<form:hidden path="sSource" value="sys_office" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>标注类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required" id="type">
					<form:options items="${fns:getDictList('geotabletype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地图类型：</label>
			<div class="controls">
				<form:select path="sMaptype" class="input-xlarge">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('maptype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>地理经度：</label>
			<div class="controls">
				<form:input path="sLongitude" htmlEscape="false" maxlength="60"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>地理纬度：</label>
			<div class="controls">
				<form:input path="sLatitude" htmlEscape="false" maxlength="60"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>地点名称：</label>
			<div class="controls">
				<form:input path="sDepartname" htmlEscape="false" maxlength="60"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group" id="depart">
			<label class="control-label"><font color="red">*&nbsp;</font>选择部门：</label>
			<div class="controls">
				<sys:treeselect id="sDepartmentid" name="sDepartmentid"
					value="${pbsGeographical.sDepartmentid.id}" labelName="sDepartmentid"
					labelValue="${pbsGeographical.sDepartmentid.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="required" allowClear="true"
					notAllowSelectParent="false" />
			</div>
		</div>
		<div class="control-group" id="mem" style="display:none">
			<label class="control-label"><font color="red">*&nbsp;</font>选择学员：</label>
			<div class="controls">
				<sys:treeselect id="pbsPartymem" name="pbsPartymem"
					value="${pbsGeographical.pbsPartymem.id}" labelName="pbsPartymem"
					labelValue="${pbsGeographical.pbsPartymem.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group" id="task" style="display:none">
			<label class="control-label"><font color="red">*&nbsp;</font>选择任务：</label>
			<div class="controls">
				<form:select path="sSuperiorid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group" id="activity" style="display:none">
			<label class="control-label"><font color="red">*&nbsp;</font>选择活动：</label>
			<div class="controls">
				<form:select path="sActivityid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div id="map" style="width: 100%; height: 400px;cursor:pointer"></div>
		<div class="form-actions">
			<shiro:hasPermission name="map:pbsGeographical:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<!-- <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
	<script src="http://open.map.qq.com/apifiles/2/4/79/main.js"
		type="text/javascript"></script> -->
</body>
</html>