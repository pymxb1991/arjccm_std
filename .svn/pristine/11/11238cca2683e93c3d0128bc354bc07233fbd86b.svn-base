<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作日志管理</title>
<meta name="decorator" content="default" />
<style>
.logWorkRep li {
	margin-left: 160px;
}
</style>
<script type="text/javascript" src="${ctxStatic}/ccm/sys/js/ccmWorkReportinfo.js">
		$(document).ready(function() {
			
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("td").css({"padding":"8px"});
			$("td").css({"border":"0px dashed #CCCCCC"});
		});
</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>


	<form:form id="inputForm" modelAttribute="ccmWorkReport"
		action="${ctx}/sys/ccmWorkReport/saveJob" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<c:if test="${ empty ccmWorkReport.id}">
			<div class="control-group">
				<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>接收人：</label>
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
		<div class="control-group" style="padding-top: 7px">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>行政区划：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"
					class="input-xlarge required" readonly="true"/>

			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>社工姓名：</label>
			<div class="controls">
				<form:input path="createName" htmlEscape="false" maxlength="100"
					class="input-xlarge required" readonly="true"/>

			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>电话：</label>
			<div class="controls">
				<form:input path="telephone" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />

			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>日志类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:options items="${fns:getDictList('ccm_work_report_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>

			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>事件类型：</label>
			<div class="controls">
				<form:select path="eventtypeid" class="input-xlarge ">
					<form:options items="${fns:getDictList('ccm_event_sort')}"
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
		<c:if test="${ccmWorkReport.type eq 02 || ccmWorkReport.type eq 03}">
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>地点：</label>
			<div class="controls">
				<form:input path="place" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />

			</div>
		</div>
		</c:if>
		
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

		<c:if test="${viewRep  eq 01 }">
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
			<label class="control-label"></label>
			<tr>
				<td class="pad">回复记录信息：</td>
				<td class="pad" colspan="3">
					<table>
						<tr>
							<c:forEach items="${ccmLogTailList}" var="logList">
								<li style="list-style-type: none;"><a
									href="${ctx}/log/ccmLogTail/formPro?id=${logList.id}">
										${logList.createBy.name}&nbsp; 于&nbsp; <fmt:formatDate
											value="${logList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;
										回复记录信息
								</a></li>
							</c:forEach>
						</tr>
					</table>
				</td>
			</tr>
		</div>
		</c:if>	

		<div class="form-actions">
			<c:if test="${viewRep  eq 02 }">
				<shiro:hasPermission name="sys:ccmWorkReport:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" 
			onclick="parent.layer.close(parent.layerIndex)" value="关 闭" />
		</div>
	</form:form>
</body>
</html>