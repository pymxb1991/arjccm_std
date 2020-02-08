<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作日志管理</title>
<meta name="decorator" content="default" />
<style>
.logWorkRep li {
	margin-left: 0px;
}
.logWorkRep li p{
	margin-left: 15px;
}
.logWorkRep li hr{
	margin: 5px 0px;
}

#content{
	height: 130px;
}
</style>
<script type="text/javascript"
	src="${ctxStatic}/ccm/sys/js/ccmWorkReportinfo.js"></script>
<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<!--  收件箱  /arjccm/a/sys/ccmWorkReport/self-->
		<c:if test="${viewRep  eq 02 }">
			<li><a style="width: 140px;text-align:center" href="${ctx}/sys/ccmWorkReport/self">数据列表</a></li>
			<li class="active" style="width: 140px"><a class="nav-head"
				href="${ctx}/sys/ccmWorkReport/view?id=${ccmWorkReport.id}">数据<shiro:hasPermission
						name="sys:ccmWorkReport:edit">${not empty ccmWorkReport.id?'查看':'添加'}</shiro:hasPermission></a></li>
			<li><a style="width: 140px;text-align:center"
				href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmWorkReport.id}&relevance_table=ccm_sys_workreport">数据回复信息<shiro:hasPermission
						name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission>
					<shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a>
			</li>
		</c:if>
		<!-- 发件箱 -->
		<c:if test="${viewRep eq 01 }">
			<li><a style="width: 140px;text-align:center" href="${ctx}/sys/ccmWorkReport/">数据列表</a></li>
			<li class="active" style="width: 140px"><a class="nav-head"
				href="${ctx}/sys/ccmWorkReport/form?id=${ccmWorkReport.id}">数据<shiro:hasPermission
						name="sys:ccmWorkReport:edit">${not empty ccmWorkReport.id?'查看':'添加'}</shiro:hasPermission>
					<shiro:lacksPermission name="sys:ccmWorkReport:edit">查看</shiro:lacksPermission></a></li>
		</c:if>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmWorkReport"
		action="${ctx}/sys/ccmWorkReport/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<c:if test="${ empty ccmWorkReport.id}">
			<div class="control-group head_Space">
				<label class="control-label" ><span class="help-inline"><font color="red">*</font> </span>接收人：</label>
				<div class="controls">
					<sys:treeselect id="ccmWorkReportRead" name="ccmWorkReportReadIds"
						value="${ccmWorkReport.ccmWorkReportReadIds}" isAll="true"
						labelName="oaNotifyRecordNames"
						labelValue="${ccmWorkReport.ccmWorkReportReadNames}" title="用户"
						url="/sys/office/treeData?type=3"
						cssClass="input-xxlarge required" notAllowSelectParent="true"
						checked="true" />

				</div>
			</div>
		</c:if>
		<div class="control-group head_Space"  >
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:options items="${fns:getDictList('ccm_work_report_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>开始日期：</label>
			<div class="controls">
				<input name="beginDate" id="beginDate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmWorkReport.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>结束日期：</label>
			<div class="controls">
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmWorkReport.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}',dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="15"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="files" path="files" htmlEscape="false"
					maxlength="1000" class="input-xlarge" />
				<sys:ckfinder input="files" type="files"
					uploadPath="/sys/ccmWorkReport" selectMultiple="true" />
			</div>
		</div>


		<div class="panel-group control-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<label class="control-label"> <a data-toggle="collapse"
						data-parent="#accordion" href="#collapseOne"
						style="text-decoration: none;"> 接收人列表 ： </a>
					</label>
				</div>
				<div class="controls" style="padding-top: 3px;">已查阅：${ccmWorkReport.readNum}
					&nbsp; 未查阅：${ccmWorkReport.unReadNum} &nbsp;
					总共：${ccmWorkReport.readNum + ccmWorkReport.unReadNum}</div>
				<div id="collapseOne" class="panel-collapse collapse">
					<c:if test="${ not empty ccmWorkReport.id}">
						<div class="control-group">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>接收人</th>
										<th>接受部门</th>
										<th>阅读状态</th>
										<th>阅读时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ccmWorkReport.ccmWorkReportReadList}"
										var="ccmWorkReportRead">
										<tr>
											<td>${ccmWorkReportRead.user.name}</td>
											<td>${ccmWorkReportRead.user.office.name}</td>
											<td>${fns:getDictLabel(ccmWorkReportRead.readFlag, 'oa_notify_read', '')}
											</td>
											<td><fmt:formatDate
													value="${ccmWorkReportRead.readTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
				</c:if>
			</div>
		</div>
		</div>

		<div class="control-group logWorkRep">
			<label class="control-label">回复记录信息：</label>
			<div class="controls">
				<c:forEach items="${ccmLogTailList}" var="logList">
					<li style="list-style-type: none;">
						<a href="${ctx}/log/ccmLogTail/formPro?id=${logList.id}">
							${logList.createBy.name}&nbsp; 于&nbsp; <fmt:formatDate
								value="${logList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;
							回复记录信息</a>
						<br/>
						<p>${logList.remarks}</p>
						<hr/>
					</li>
				</c:forEach>
			</div>
		</div>

		<div class="form-actions">
			<c:if test="${ empty ccmWorkReport.id}">
				<shiro:hasPermission name="sys:ccmWorkReport:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>