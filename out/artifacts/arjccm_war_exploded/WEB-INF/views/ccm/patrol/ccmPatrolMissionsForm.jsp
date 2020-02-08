<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡逻任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var error="<label for=\"\" class=\"error\" style=\"display: inline-block;\">必选字段</label>";
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					//判断必填项
					var people=$(".people-num")
					for (var i = 0; i <people.length ; i++) {
						if(!people[i].value){
							$(people[i].parentNode).append(error)
							return "";
						}

					}

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
			
			$("#patrolTime").click(function () {
			    WdatePicker({
			        el: "patrolTime", //点击对象id，一般可省略el
			        lang: 'auto', //语言选择，一般用auto
			        dateFmt: 'yyyy-MM-dd HH:mm:ss', //时间显示格式，年月日 时分秒，年月日就是yyyy-MM-dd
			        minDate: '%y-%M-%d', //最小日期
			        readOnly: true, //是否只读
			        isShowClear: true, //是否显示“清空”按钮
			        isShowOK: true, //是否显示“确定”按钮
			        isShowToday: true, //是否显示“今天”按钮
			        autoPickDate: true //为false时 点日期的时候不自动输入,而是要通过确定才能输入，为true时 即点击日期即可返回日期值，为null时(推荐使用) 如果有时间置为false 否则置为true
			    })
			});
			$("#endTime").click(function () {
			    WdatePicker({
			        el: "endTime", //点击对象id，一般可省略el
			        lang: 'auto', //语言选择，一般用auto
			        dateFmt: 'yyyy-MM-dd HH:mm:ss', //时间显示格式，年月日 时分秒，年月日就是yyyy-MM-dd
			        minDate: '#F{$dp.$D(\'patrolTime\')}',
			        readOnly: true, //是否只读
			        isShowClear: true, //是否显示“清空”按钮
			        isShowOK: true, //是否显示“确定”按钮
			        isShowToday: true, //是否显示“今天”按钮
			        autoPickDate: true //为false时 点日期的时候不自动输入,而是要通过确定才能输入，为true时 即点击日期即可返回日期值，为null时(推荐使用) 如果有时间置为false 否则置为true
			    })
			});
			//初始化值
			officeName=$('#officeName').val();
			var num=$('#number').val();
			if((num||"").indexOf(",")!=-1){
				addPeopleNum(num.split(","));
			}else{
				addPeopleNum([num])
			}





		});

		var officeName="";

		//判断是否改变值
		function changeName() {
			var temp=$('#officeName').val();
			if(temp!=officeName){
				officeName=temp;
				addPeopleNum();
			}else{
				officeName=temp;

			}

		}

		function addPeopleNum(str){
			// var tempStr="";

			//判断有没有传参

			if(!str){
				var people=$(".people-num");
				 str=[];
				for (var i = 0; i <people.length ; i++) {
					str[i]=people[i].value
				}
			}

			//添加人数
			var temp="";
			if(officeName.indexOf(",")!=-1){
				var split=officeName.split(",");
				for (var i = 0; i < split.length; i++) {
					 temp+="<div class=\"input-prepend input-append\">\n" +
							"<span class=\"add-on\">"+split[i]+":</span>\n" +
							"<input onchange='changeNumber()' class=\"span2 people-num \" value='"+(str[i]||'')+"' type=\"text\">\n" +
							"<span class=\"add-on\">人数</span>\n" +
							"</div>" +
							 "<span class=\"help-inline\"><font color=\"red\">*</font> </span><br/>"
				}

			}else if (officeName) {
				temp+="<div class=\"input-prepend input-append\">\n" +
						"<span class=\"add-on\">"+officeName+":</span>\n" +
						"<input onchange='changeNumber()' class=\"span2 people-num \" value='"+(str[0]||"")+"' type=\"text\">\n" +
						"<span class=\"add-on\">人数</span>\n" +
						"</div><span class=\"help-inline\"><font color=\"red\">*</font> </span>"
			}
			$("#peopleNum").html(temp);


		}

		function changeNumber(){
			//设置提交input的值
		 	var people=$(".people-num");
			var str=[];

			for (var i = 0; i <people.length ; i++) {
				str[i]=people[i].value
			}
			$("#number").attr("value",str.join(","));
		}

	</script>
	<style>
		#peopleNum{
			margin-left: 5px;
		}
	</style>
</head>
<body >
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolMissions/summaryGraph">统计数据</a></li>
		<li><a href="${ctx}/patrol/ccmPatrolMissions/">数据列表</a></li>
		<li class="active"><a href="${ctx}/patrol/ccmPatrolMissions/form?id=${ccmPatrolMissions.id}">巡逻任务<shiro:hasPermission name="patrol:ccmPatrolMissions:edit">${not empty ccmPatrolMissions.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="patrol:ccmPatrolMissions:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form  id="inputForm"  onmouseover="changeName()" modelAttribute="ccmPatrolMissions" action="${ctx}/patrol/ccmPatrolMissions/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">巡逻任务：</label>
			<div class="controls">
				<form:textarea path="patrolContent" htmlEscape="false"  class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡逻时间：</label>
			<div class="controls">
				<input name="patrolTime" id="patrolTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmPatrolMissions.patrolTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text"  id="endTime"readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmPatrolMissions.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参与单位：</label>
			<div class="controls">
				<sys:treeselect id="office"  name="office" value="${ccmPatrolMissions.office}" labelName="officeName" labelValue="${ccmPatrolMissions.officeName}"
					title="部门" url="/sys/office/treeData?type=2" checked="true" cssClass="input-xxlarge required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div style="line-height: 40px;"  class="control-group">
			<label class="control-label">每个单位人数：</label>
			<div class="controls">
				<form:input cssStyle="display: none"  value="${ccmPatrolMissions.number}" path="number"  onclick="" htmlEscape="false" maxlength="1024" class="input-xlarge "/>
				<div id="peopleNum">

				</div>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡逻路线：</label>
			<div class="controls">
				<form:input path="patrolRoutes" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡逻辖区：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${ccmPatrolMissions.area.id}" labelName="area.name" labelValue="${ccmPatrolMissions.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		 <%-- <div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" disabled="true" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%> 

		<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
				<form:select path="auditingStatus" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auditing_status')}" itemLabel="label"
								  itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="patrol:ccmPatrolMissions:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>