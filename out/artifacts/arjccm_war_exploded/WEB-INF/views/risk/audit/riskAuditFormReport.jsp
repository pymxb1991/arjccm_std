<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事项上报管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		});
		function saveForm(){
			//var s = confirmx('确认要将该重大事项报告上报吗？');
			var msg = "确认要将该重大事项报告上报吗？"; 
			if (confirm(msg)==true){ 
				var riskAuditId = $("#riskAuditId").val();
				var html1 = '<label for="" class="error">必填信息 *<label>';
				//alert(riskAuditId.length);
				if(riskAuditId.length!=0){
					$("#show1").html("*");
				}else{
					$("#show1").html(html1);
				}
				if(riskAuditId.length!=0){
					$("#inputForm2").submit();
				}
			}
			
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/audit/riskAudit/listReport">重大事项上报列表</a></li>
		<li class="active"><a href="${ctx}/audit/riskAudit/formReport?id=${riskReport.id}&readNum=${riskReport.readNum}">重大事项上报查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="riskReport" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="fileName" htmlEscape="false" maxlength="256" class="input-xlarge" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">所属重大事项：</label>
			<div class="controls">${riskReport.riskEventGreat.name}
				<span class="help-inline"><font color="red" >*</font> </span>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报告名称：</label>
			<div class="controls">${riskReport.fileName}
				<span class="help-inline"><font color="red" >*</font> </span>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">事项评估报告：</label>
			<div class="controls">
				<form:hidden id="file" path="file" htmlEscape="false" maxlength="256" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/report/riskReport" selectMultiple="true" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">${riskReport.remarks}
			</div>
		</div>
		
	</form:form>
	<form:form id="inputForm2" modelAttribute="riskReport" action="${ctx}/audit/riskAudit/formSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="fileName" htmlEscape="false" maxlength="256" class="input-xlarge" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">审核人：</label>
			<div class="controls">
				<sys:treeselect id="riskAudit" name="riskAuditIds" value="${riskReport.riskAuditIds}" isAll="true"
					labelName="riskAuditNames" labelValue="${riskReport.riskAuditNames}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true" />
				<span class="help-inline"><font color="red" id="show1">*</font> </span>
			</div>
		</div>
		<div class="panel-group control-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<label class="control-label"> <a data-toggle="collapse"
						data-parent="#accordion" href="#collapseOne"
						style="text-decoration: none;"> 审核人列表 ： </a>
					</label>
				</div>
				<div class="controls" style="padding-top: 3px;">未查阅：${riskReport.unReadNum}
					&nbsp; 已查阅：${riskReport.readNum} &nbsp;
					总共：${riskReport.unReadNum + riskReport.readNum}</div>
				<div class="controls" style="padding-top: 3px;">未通过：${riskReport.noauditNum}
					&nbsp; 已通过：${riskReport.auditNum} &nbsp;
					总共：${riskReport.unauditNum + riskReport.noauditNum + riskReport.auditNum}</div>
				<div id="collapseOne" class="panel-collapse collapse">
					<c:if test="${ not empty riskReport.id}">
						<div class="control-group">
							<table id="contentTable"
								class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th width="12%">接收人</th>
										<th width="12%">接受部门</th>
										<th width="12%">阅读状态</th>
										<th width="12%">阅读时间</th>
										<th width="12%">审核结果</th>
										<th width="40%">审核意见</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${riskReport.riskAuditList}"
										var="riskAudit">
										<tr>
											<td>${riskAudit.user.name}</td>
											<td>${riskAudit.user.office.name}</td>
											<c:if test="${riskAudit.readFlag eq '0'}">
												<td>${fns:getDictLabel(riskAudit.readFlag, 'oa_notify_read', '')}
											</c:if>
											<c:if test="${riskAudit.readFlag eq '1'}">
												<td style="color:green;">${fns:getDictLabel(riskAudit.readFlag, 'oa_notify_read', '')}
											</c:if>
											</td>
											<td><fmt:formatDate
													value="${riskAudit.readTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<c:if test="${riskAudit.result eq '01'}">
												<td>${fns:getDictLabel(riskAudit.result, 'ccm_service_online_handle', '')}
											</c:if>		
											<c:if test="${riskAudit.result eq '02'}">
												<td style="color:red;">${fns:getDictLabel(riskAudit.result, 'ccm_service_online_handle', '')}
											</c:if>	
											<c:if test="${riskAudit.result eq '03'}">
												<td style="color:green;">${fns:getDictLabel(riskAudit.result, 'ccm_service_online_handle', '')}
											</c:if>	
											</td>
											<td>${riskAudit.opinion}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
				</c:if>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${empty riskReport.readNum}">
				<shiro:hasPermission name="audit:riskAudit:edit"><input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button"  value="上报"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>