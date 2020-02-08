<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息列表</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/statis/css/grid.css" rel="stylesheet"/>
<script type="text/javascript">
  $(document).ready(function() {
	  $("#btnSubmit").on("click" ,function(){
			$("#searchForm").submit();
		})
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
		<li class="active"><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li><a href="${ctx}/work/pbsMeeting/form">活动信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivityrec"
		action="${ctx}/work/pbsMeeting/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>活动标题：</label> <form:input path="sTitle"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li>
				<label>活动类别：</label> 
				<form:select path="sGroupType" class="input-xlarge" style="width:200px">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('actdefinitiontype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>开始时间：</label> <input name="dtStarttime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsActivityrec.getDtStarttime()}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
			</li>
			<li><label>结束时间：</label> <input name="dtEndtime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsActivityrec.getDtEndtime()}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
			</li>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary"> 
					<i class="icon-search"></i> 查询
				</a>
			</li>
			<li class="clearfix"></li> 
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<ul class="zbdt">
		<c:forEach items="${page.list}" var="pbsActivityrec">
			<li>
				 <div class="list0">
				  	<h5>活动标题</h5>
				  	<h6><a
						href="${ctx}/work/pbsComment/ActivityNewsInfo?id=${pbsActivityrec.getId()}">${pbsActivityrec.getSTitle()}</a></h6>
				 </div>
				 <div class="list1">
				  	<h5>活动地点</h5>
				  	<h6>
				  		${pbsActivityrec.getSPlace()}
				  	</h6>
				  </div>
				  <div class="list2">
				  	<h5>开始时间</h5>
				  	<h6>
						<fmt:formatDate value="${pbsActivityrec.getDtStarttime()}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</h6>
				  </div>
				  <div class="list3">
				  	<h5>截止时间</h5>
				  	<h6>
						<fmt:formatDate value="${pbsActivityrec.getDtEndtime()}" pattern="yyyy-MM-dd HH:mm:ss" />
					</h6>
				  </div>
				  <div  class="list4">
				  	<h5>操作</h5>
				  	<h6><a href="${ctx}/work/pbsComment/ActivityNewsInfo?id=${pbsActivityrec.getId()}" title = "详细"><i class="icon-user"></i></a>
						<a href="${ctx}/work/pbsMeeting/checkActivityValue?id=${pbsActivityrec.getId()}" title = "评分详情"><i class="icon-paste"></i></a>
						<a href="${ctx}/work/pbsMeeting/SendActivitySign?id=${pbsActivityrec.id}" title = "查看参与人"><i class="icon-bar-chart"></i></a>
					</h6>
				  </div>
				</li>
			</c:forEach>
		</ul>
	<div class="pagination">${page}</div>
</body>
</html>