<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>试卷信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/exam/js/pbsExampaperForm.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/pbsExampaper/?sWay=2">试卷信息列表</a></li>
		<li class="active"><a
			href="">试卷信息<shiro:hasPermission
					name="exam:pbsExampaper:edit">${not empty pbsExampaper.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="exam:pbsExampaper:edit">查看</shiro:lacksPermission></a></li>
		<c:if test="${! empty pbsExampaper.id}">
		<li><a
            href="${ctx}/exam/pbsExampaperitem/addform?sExampaper=${pbsExampaper.id}">题目信息添加
                <shiro:lacksPermission name="exam:pbsExampaper:edit">查看</shiro:lacksPermission></a></li>
        </c:if>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsExampaper"
		action="${ctx}/exam/pbsExampaper/save" method="post" onsubmit="return checkParam();"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sMajor"  value="000001"/>
		<div class="hide sProject"
			topid="${pbsExampaper.sProject.id}"></div>
		<div class="hide sLevel"
			topid="${pbsExampaper.sLevel.id}"></div>	
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试卷名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>展示标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label">专业编号：</label>
			<div class="controls">
				<form:input path="sMajor" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>科目编号：</label>
			<div class="controls">
			<form:select path="sProject" class="input-xlarge required">
					<form:option value="" label=""/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>难度等级编号：</label>
			<div class="controls">
			<form:select path="sLevel" class="input-xlarge required">
					<form:option value="" label=""/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>考试开始时间：</label>
			<div class="controls">
				<input name="dtStart" type="text" readonly="readonly" maxlength="20" id="startTime"
					class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsExampaper.dtStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>考试结束时间：</label>
			<div class="controls">
				<input name="dtEnd" type="text" readonly="readonly" maxlength="20" id="endTime"
					class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsExampaper.dtEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>考试时间(分)：</label>
			<div class="controls">
				<form:input path="iExamtime" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits required" />
			</div>
		</div>
		<div class="control-group">
			<form:hidden path="sWay"  value="2"/>
		</div>
		 <c:if test="${fn:length(exampaperitemList)> 0}">
            <div class="panel-group control-group" id="accordion">
                <table id="contentTable"
                    class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>选项</th>
                            <th>题目</th>
                            <th>答案</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${exampaperitemList}" var="paperitem">
                            <tr>
                                <td><a href="${ctx}/exam/pbsExampaperitem/addform?id=${paperitem.id}">
                                        ${paperitem.ISort}</a></td>
                                <td><a href="${ctx}/exam/pbsExampaperitem/addform?id=${paperitem.id}">${paperitem.sItem.SBody}</a></td>
                                <td><a href="${ctx}/exam/pbsExampaperitem/addform?id=${paperitem.id}">${paperitem.sItem.SAnswer}</a></td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
		 
		 
		<div class="form-actions">
			<shiro:hasPermission name="exam:pbsExampaper:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>