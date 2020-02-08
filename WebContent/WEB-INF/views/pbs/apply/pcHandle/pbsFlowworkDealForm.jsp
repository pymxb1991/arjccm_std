<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>运行工作流管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/apply/js/pbsApplyDealForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/dealPc">我提交的申请</a></li>
		<li><a href="${ctx}/flow/dealPc/applyNew">新的申请</a></li>
		<li><a href="${ctx}/flow/dealPc/dealtList">待我处理</a></li>
		<li class="active"><a href="">正在处理</a></li>
		<li><a href="${ctx}/flow/dealPc/dealtFinishList">我已处理</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsApplyrec"
		action="${ctx}/apply/pbsApplyrec/save" method="post"
		class="form-horizontal" onsubmit="javascript:return false;">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sType" topid="${pbsApplyrec.sType.id}"></div>
		<div class="hide worknodeid" worknodeid="${worknodeid}"></div>
		<div class="control-group">
			<label class="control-label">申请类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge " disabled="true">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请简述信息：</label>
			<div class="controls">
				<form:input path="sResume" htmlEscape="false" maxlength="500"
					readonly="true" class="input-xlarge " />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">申请的部门编号：</label>
			<div class="controls">
				<sys:treeselect id="sPartment" name="sPartment" disabled="disabled"
					value="${pbsApplyrec.sPartment.id}" labelName=""
					labelValue="${pbsApplyrec.sPartment.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请绑定人员：</label>
			<div class="controls">
				<form:input path="sBindmember.sName" htmlEscape="false"
					readonly="true" maxlength="64" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记录状态：</label>
			<div class="controls">
				<form:select path="sStatus" class="input-xlarge required"
					disabled="true">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('flowresult')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden path="sFileurl" id="sFileurl"/>
				<sys:ckfinder input="sFileurl" type="files" uploadPath="/apply/pbsApplyrec" readonly="true" selectMultiple="true"></sys:ckfinder>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请内容：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="2000" class="input-xxlarge " disabled="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已通过流程：</label>
			<div class="controls">
				<div class="panel-group " id="accordion" style="width: 80%;">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>审批环节</th>
								<th>审批部门</th>
								<th>审批人员</th>
								<th>审批时间</th>
								<th>审批状态</th>
								<th>批注内容</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(list)> 0}">
								<c:forEach items="${list}" var="worknode" varStatus="nodestat">
									<c:if test="${worknode.sSort ne 0  and worknode.sSort ne 99 }">
										<tr>
											<td>${worknode.sNodeid.SName}</td>
											<td>${pbsApplyrec.sPartment.name}</td>
											<td>${worknode.sBindmember.SName}</td>
											<td><fmt:formatDate value="${worknode.createDate}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><c:if test="${!nodestat.last}">
                                                                                                已通过</c:if>
												<c:if test="${nodestat.last}">
                                             ${fns:getDictLabel(pbsApplyrec.SStatus, 'flowresult', '')}
                                            </c:if></td>
											<td>${worknode.SActionremark}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(list)== 0}">
								<tr>
									<td>开始环节</td>
									<td>${pbsApplyrec.sPartment.name}</td>
									<td>-</td>
									<td><fmt:formatDate value="${worknode.createDate}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>等待审核</td>
									<td></td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批操作：</label>
			<div class="controls">
				<select id="handletype" class="input-xlarge required">
					<option value="100">同意</option>
					<option value="-99">退回</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>审核意见：</label>
			<div class="controls">
				<textarea id="sactionremark" rows="4" maxlength="2000"
					class="input-xxlarge "></textarea>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button"
				value="提交" /> <input id="btnCancel" class="btn" type="button"
				value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>