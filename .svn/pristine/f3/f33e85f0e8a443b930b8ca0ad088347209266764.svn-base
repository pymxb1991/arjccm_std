<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		// $("#name").focus();
		//var parentid =$(".sParentid").attr("parentid");
		$("#sParentid").load(ctx + "/question/pbsQuestionProject/listPageAddAll", {
		}, function() {
			// 选择类别
			$("#sParentid").val("${pbsCourseinfoEx.SParentid}").select2();
		});
	});
	function page(n, s) {
	    $("#pageNo").val(n);
	    $("#pageSize").val(s);
	    $("#searchForm").submit();
	    return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>党建热门视频列表</a></li>
	</ul>
	<form:form modelAttribute="pbsCourseinfoEx"
		action="${ctx}/question/pbsTestOnlinePC/coursePage" method="post"
		class="breadcrumb form-search">
		<ul class="ul-form">
			<li>
				<label>课程类型：</label> 
				<form:select path="sParentid" class="input-xlarge ">
                </form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="clearfix"></li> 
		</ul>
	</form:form>
	<div>
		<c:forEach items="${page.list}" var="study">
			<div style="width:20%; float:left;margin-left:4%;margin-top:2%;height:310px" >
				<a href="${ctx}/question/pbsTestOnlinePC/courseInfo?id=${study.id}" title = "播放">
					<img onerror='this.src="${ctxStatic}/statis/img/vedio.jpg"' src="${study.SIconurl}" style="width:100%;height:75%">
				</a>
				<p style="font-size:16px;margin-top:5px;">${study.SName}</p>
				<label style="color:grey">创建时间：<fmt:formatDate value="${study.createDate}" pattern="yyyy-MM-dd" /></label>
				
			</div>
		</c:forEach>
	</div>
	<div class="pagination" style="clear:both;margin-left:4%;padding-top:10px">${page}</div>
</body>
</html>