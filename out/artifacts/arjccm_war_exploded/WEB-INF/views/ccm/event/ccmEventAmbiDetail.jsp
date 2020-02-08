<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>矛盾纠纷排查化解管理</title>
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<meta name="decorator" content="default" />

</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="ccmEventAmbi" action="${ctx}/event/ccmEventAmbi/save" method="post" class="form-horizontal">
		
		<table class="table table-bordered table-hover">
			
			<tr>
				<td>
					<div>
						<label class="control-label">矛盾纠纷名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				  			
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">发生时间：</label>
						<div class="controls">
							<input name="sendDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmEventAmbi.sendDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">所属社区：</label>
						<div class="controls">
							<sys:treeselect id="area" name="area.id" value="${ccmEventAmbi.area.id}" labelName="area.name" labelValue="${ccmEventAmbi.area.name}"
							title="区域" url="/tree/ccmTree/treeDataArea?type=6" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">发生地点详址：</label>
						<div class="controls">
							<form:input path="sendAdd" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">矛盾纠纷规模：</label>
						<div class="controls">
							<form:select path="eventScale" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_event_scale')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						 	</form:select>
							
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">矛盾纠纷类别：</label>
						<div class="controls">
							<form:select path="eventType" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_event_sort')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">涉及人数：</label>
						<div class="controls">
							<form:input path="invoNum" htmlEscape="false" maxlength="6" class="input-xlarge required digits"  type = "number"/>
							
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">矛盾纠纷简述：</label>
						<div class="controls">
							<form:input path="eventSket" htmlEscape="false" maxlength="100" class="input-xlarge "/> 
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">涉及单位：</label>
						<div class="controls">
							<form:input path="involveCom" htmlEscape="false" maxlength="100" class="input-xlarge "/>  
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">主要当事人证件类型：</label>
						<div class="controls">
							<form:select path="partCode" class="input-xlarge " items="${fns:getDictList('sys_ccm_org_papers')}" itemLabel="label" itemValue="value" htmlEscape="false">
								</form:select> 
							
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">主要当事人证件号码：</label>
						<div class="controls">
							<form:input path="partNum" htmlEscape="false" maxlength="50" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">主要当事人姓名：</label>
						<div class="controls">
							<form:input path="partName" htmlEscape="false" maxlength="80" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">主要当事人性别：</label>
						<div class="controls">
							<form:radiobuttons path="partSex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">主要当事人民族：</label>
						<div class="controls">
							<form:select path="partNat" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_volk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">主要当事人学历：</label>
						<div class="controls">
							<form:select path="partEduBg" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_ccm_degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">主要当事人人员类别：</label>
						<div class="controls">
							<form:select path="partType" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_zydsr_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">主要当事人居住详址：</label>
						<div class="controls">
							<form:input path="partAdd" htmlEscape="false" maxlength="200" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			
			
			
			
			<tr>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false" maxlength="40" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">图片：</label>
						<div class="controls">
							<form:hidden id="icon" path="icon" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="icon" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="120" maxHeight="180"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">附件：</label>
						<div class="controls">
							<form:hidden id="file" path="file"  htmlEscape="false" maxlength="255" class="input-xxlarge"/>
				    		<sys:ckfinder input="file" type="files" uploadPath="/event/ccmEventAmbi" selectMultiple="false"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
			
			
		</table>
		
	</form:form>


</body>
</html>