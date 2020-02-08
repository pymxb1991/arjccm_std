<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部门积分榜</title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a  href="${ctx}/exam/pbsFractionPCController/integralSelfList">个人积分榜列表</a></li>
		<li class="active"><a>部门积分榜列表</a></li>
	</ul>
	<form:form method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>当前学员：</label> 
				<input class="input-medium" value="${user.SName}" readonly="readonly" type="text"/></li>
			<li>
			<li><label>分数：</label> 
				<input class="input-medium" value="<c:if test='${! empty usertime}'>${usertime} </c:if>
					<c:if test='${ empty usertime}'>您未参加此次考试。 </c:if>" readonly="readonly" type="text"/>
				</li>
			<li>
		</ul>
	</form:form>
	<table class="table table-striped table-bordered table-condensed" style="margin-top:40px">
		<thead>
			<tr>
				<th>序号</th>
				<th>头像</th>
				<th>组织机构</th>
				<th>分数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${examOfcList}" var="examaction" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>
						<img onerror='this.src="${ctxStatic}/wechat/img/exam.png"'  src="${examaction.ofcphoto}" style="width:40px;height:40px"> 
					</td>
					<td>${examaction.sDepartment.name}</td>
					<td>${examaction.IReport}</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>
</body>
</html>