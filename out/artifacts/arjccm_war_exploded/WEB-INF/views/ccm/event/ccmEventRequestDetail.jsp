<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>请求登记管理</title>
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<meta name="decorator" content="default" />

</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="ccmEventRequest"
		action="${ctx}/event/ccmEventRequest/save" method="post"
		class="form-horizontal">	
		<table class="table table-bordered table-hover">
			
			<tr>
				<td>
					<div>
						<label class="control-label">请求名称：</label>
						<div class="controls">
							<form:input path="caseName" htmlEscape="false"
							maxlength="100" class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td rowspan="5">
					<div>
						<label class="control-label">图片：</label>
						<div class="controls">
							<form:hidden id="icon" path="icon" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="icon" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="120" maxHeight="180"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">发生日期：</label>
						<div class="controls">
							<input name="happenDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmEventRequest.happenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">请求种类：</label>
						<div class="controls">
							<form:select path="eventKind" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_event_inci_kind')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">请求地点：</label>
						<div class="controls">
							<sys:treeselect id="area" name="area.id"
							value="${ccmEventRequest.area.id}" labelName="area.name"
							labelValue="${ccmEventRequest.area.name}" title="区域"
							url="/sys/area/treeData" cssClass="" allowClear="true"
							notAllowSelectParent="false" cssStyle="width: 150px" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">详址：</label>
						<div class="controls">
							<form:input path="happenPlace"
							htmlEscape="false" maxlength="200" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false"
							maxlength="2000" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false"
						maxlength="40" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">请求内容：</label>
						<div class="controls">
							<form:input path="caseCondition" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">处理：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge ">
								<c:if test="${empty ccmEventRequest.id}"><form:option value="01" label="未处理"/></c:if>
								<c:if test="${not empty ccmEventRequest.id}">
								<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</c:if>
							</form:select></td>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks"
							htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">附件：</label>
						<div class="controls">
							<form:hidden id="file" path="file"  htmlEscape="false" maxlength="255" class="input-xxlarge"/>
				    		<sys:ckfinder input="file" type="files" uploadPath="/event/ccmEventRequest" selectMultiple="false"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
			
			
		
	</form:form>


</body>
</html>