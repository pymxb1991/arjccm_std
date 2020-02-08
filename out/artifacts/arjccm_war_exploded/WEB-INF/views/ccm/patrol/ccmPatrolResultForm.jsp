<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>巡逻结果管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/ccm/patrol/js/ccmPatrolResultForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolResult/">数据列表</a></li>
		<li class="active"><a
			href="${ctx}/patrol/ccmPatrolResult/form?id=${ccmPatrolResult.id}">数据<shiro:hasPermission
					name="patrol:ccmPatrolResult:edit">${not empty ccmPatrolResult.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="patrol:ccmPatrolResult:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<div id="ccmPatrolResultInformation" class="ccmPatrolPlanID"
		style="display: none;" planid="${ccmPatrolResult.plan.id}"
		attrbegindate="<fmt:formatDate value="${ccmPatrolResult.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
		attrenddate="<fmt:formatDate value="${ccmPatrolResult.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></div>
	<form:form id="inputForm" modelAttribute="ccmPatrolResult"
		action="${ctx}/patrol/ccmPatrolResult/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table table-bordered table-hover">
			<tr>
				<td class="control-group"><label class="control-label">名称：</label>
					<div class="controls">
						<input htmlEscape="false" maxlength="64" readonly="readonly"
							value="${ccmPatrolPlan.name}" class="input-xlarge  required"
							type="text" /> <span class="help-inline"><font
							color="red">*</font>
					</div></td>
				<td class="control-group"><label class="control-label">职责：</label>
					<div class="controls">
						<input htmlEscape="false" maxlength="64" type="text"
							readonly="readonly" class="input-xlarge "
							value="${ccmPatrolPlan.responsibility}" />
					</div></td>
			</tr>
			<tr>
				<td class="control-group"><label class="control-label">开始日期：</label>
					<div class="controls">
						<input type="text" readonly="readonly" maxlength="20"
							class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPatrolPlan.beginDate}" pattern="yyyy-MM-dd"/>" />
					</div></td>
				<td class="control-group"><label class="control-label">结束日期：</label>
					<div class="controls">
						<input  type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPatrolPlan.endDate}" pattern="yyyy-MM-dd"/>" />
					</div>
			</tr>
			<tr>
				<td class="control-group"><label class="control-label">开始时间：</label>
					<div class="controls">
						<input   type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPatrolPlan.beginTime}" pattern="HH:mm:ss"/>" />
					</div></td>
				<td class="control-group"><label class="control-label">结束时间：</label>
					<div class="controls">
						<input   type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPatrolPlan.endTime}" pattern="HH:mm:ss"/>" />
					</div>
			</tr>
			<tr>
				<td class="control-group"><label class="control-label">时间类型：</label>
					<div class="controls">
						<input htmlEscape="false" maxlength="64" type="text"
							class="input-xlarge" readonly="readonly"
							value=" ${fns:getDictLabel(ccmPatrolPlan.timeType,'ccm_patrol_time_type','')}" />
					</div></td>
				<td class="control-group"><label class="control-label">时间规则：</label>
					<div class="controls">
						<input htmlEscape="false" maxlength="64" type="text"
							readonly="readonly" class="input-xlarge "
							value="${ccmPatrolPlan.timeRuleName}" />
					</div>
			</tr>
			<tr>
				<td class="control-group"><label class="control-label">巡逻结果编号：</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div></td>
				<td class="control-group"><label class="control-label">计划名称：</label>
					<div class="controls">
						<form:select id="planIds" path="plan.id" class="input-xlarge ">
							<form:option value="" label="加载中..." />
						</form:select>
					</div>
			</tr>
			<tr>
				<td class="control-group"><label class="control-label">开始时间：</label>
					<div class="controls">
						<input name="beginDate" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPatrolResult.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div></td>
				<td class="control-group"><label class="control-label">结束时间：</label>
					<div class="controls">
						<input name="endDate" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmPatrolResult.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div>
			</tr>
			<tr>
				<td class="control-group" colspan="2"><label
					class="control-label">本次巡逻结果：</label>
					<div class="controls">
						<form:select path="status" class="input-xlarge ">
							<form:option value="" label="" />
							<form:options items="${fns:getDictList('ccm_patrol_resttype')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div></td>
			</tr>

		</table>
		<div class="panel-group control-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<label class="control-label"> <a data-toggle="collapse"
						data-parent="#accordion" href="#collapseOne"
						style="text-decoration: none;"> 巡检点情况 ： </a>
					</label>
				</div>
				<div class="controls" style="padding-top: 3px;">点位数：${fn:length(pointlist)}</div>
				<div id="collapseOne" class="panel-collapse collapse">
					<c:if test="${ not empty pointlist}">
						<table id="contentTable"
							class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>点位名称</th>
									<th>点位情况</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pointlist}" var="point">
									<tr>
										<td>${point.pointId.name}</td>
										<td>${point.result}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
				</c:if>
			</div>
		</div>
		<div class="control-group hide">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group checkPoint"></div>
		<div class="form-actions">
			<shiro:hasPermission name="patrol:ccmPatrolResult:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>