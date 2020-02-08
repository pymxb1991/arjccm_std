<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>巡逻计划管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/ccm/patrol/js/ccmPatrolPlanForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolPlan/">数据列表</a></li>
		<li class="active"><a
			href="${ctx}/patrol/ccmPatrolPlan/form?id=${ccmPatrolPlan.id}">数据<shiro:hasPermission
					name="patrol:ccmPatrolPlan:edit">${not empty ccmPatrolPlan.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="patrol:ccmPatrolPlan:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<div id="ccmPatrolPlanID" attrId= "${ccmPatrolPlan.id}" attrPoint="${ccmPatrolPlan.pointSort }" style="display:none;"></div>
	<form:form id="inputForm " modelAttribute="ccmPatrolPlan" 
		action="${ctx}/patrol/ccmPatrolPlan/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">接受人：</label>
			<div class="controls">
				<sys:treeselect id="users" name="ccmPatrolUserIds"
					value="${ccmPatrolPlan.ccmPatrolUserIds}" isAll="true"
					labelName="ccmPatrolUserNames"  
					labelValue="${ccmPatrolPlan.ccmPatrolUserNames}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-xlarge required"
					notAllowSelectParent="true" checked="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64"
					class="input-xlarge  required" />
					<span class="help-inline"><font color="red">*</font> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职责：</label>
			<div class="controls">
				<form:input path="responsibility" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始日期：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20"
				 class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmPatrolPlan.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmPatrolPlan.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<!-- H:mm:ss  -->
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="beginTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmPatrolPlan.beginTime}" pattern="HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmPatrolPlan.endTime}" pattern="HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间类型：</label>
			<div class="controls">
				<form:select path="timeType" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_patrol_time_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间规则：</label>
			<div class="controls">
			<sys:treeselect
			 id="timeRule" name="timeRule"
                    value="${ccmPatrolPlan.timeRule}" isAll="true"
                    labelName="timeRuleName"  
                    labelValue="${ccmPatrolPlan.timeRuleName}" title="时间"
                    url="/patrol/ccmPatrolPlan/treeData" 
                    cssClass="input-xlarge required"
                    notAllowSelectParent="true" checked="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡检内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4"
					maxlength="64" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启动状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_patrol_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xlarge  " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">点位类型：</label>
			<div class="controls">
				<form:select path="pointType" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_patrol_point_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		  <div class="control-group hide">
            <label class="control-label">点位ID顺序(测试)：</label>
            <div class="controls">
               <form:input path="pointSort" htmlEscape="false"  class="input-xlarge " />
            </div>
        </div>
		<div class="control-group checkPoint">
		</div>
		<!--   -->
		<div class="control-group pointadd"></div>
		<div class="form-actions">
			<shiro:hasPermission name="patrol:ccmPatrolPlan:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>