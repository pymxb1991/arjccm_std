<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>投票主题信息管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/statis/css/grid.css" rel="stylesheet"/>
<script type="text/javascript">
  $(document).ready(function() {
    // 导出
    $(".btnExport").click(function() {
      var topicid = $(this).attr("topid");
      console.log(topicid);
      $("#attrid").val(topicid);
      top.$.jBox.confirm("确认要导出投票数据吗？", "系统提示", function(v, h, f) {
        if (v == "ok") {
          $("#searchForm").attr("action", "${ctx}/vote/pbsVoteTopic/export");
          $("#searchForm").submit();
          $("#attrid").val("");
          $("#searchForm").attr("action", "${ctx}/vote/pbsVoteTopic/");
        }
      }, {
        buttonsFocus : 1
      });
      top.$('.jbox-body .jbox-icon').css('top', '55px');
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
		<li class="active"><a>投票列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsVoteTopic"
		action="${ctx}/vote/pbsVotePc" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="attrid" name="id" type="hidden" value="" />
		<input id="sBelongfunc" name="sBelongfunc" type="hidden" value="0" />
		<ul class="ul-form">
			<li><label>标题名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<ul class="zbdt">
		<c:forEach items="${page.list}" var="pbsVoteTopic" >
			<li>
				 <div class="list0">
				  	<h5>标题名称</h5>
				  	<h6><a href="${ctx}/vote/pbsVotePc/voteaction?id=${pbsVoteTopic.id}">${pbsVoteTopic.SName}</a></h6>
				 </div>
				 <div class="list1">
				  	<h5>是否完成</h5>
				  	<h6>
				  		<c:if test="${pbsVoteTopic.voteflag eq true}">是</c:if>
				  		<c:if test="${pbsVoteTopic.voteflag ne true}">否</c:if>
				  	</h6>
				  </div>
				  <div class="list2">
				  	<h5>投票开始时间</h5>
				  	<h6>
					<fmt:formatDate value="${pbsVoteTopic.dtStart}" pattern="yyyy-MM-dd HH:mm:ss" />
					</h6>
				  </div>
				  <div class="list3">
				  	<h5>投票结束时间</h5>
				  	<h6>
						<fmt:formatDate value="${pbsVoteTopic.dtEnd}" pattern="yyyy-MM-dd HH:mm:ss" />
					</h6>
				  </div>
				  <div  class="list4">
				  	<h5>操作</h5>
				  	<h6><a href="${ctx}/vote/pbsVotePc/voteaction?id=${pbsVoteTopic.id}" title="进入"><i class="icon-circle-arrow-right"></i></a></h6>
				  </div>
				</li>
			</c:forEach>
		</ul>
	<div class="pagination">${page}</div>
</body>

</html>