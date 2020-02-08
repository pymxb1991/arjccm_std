<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>指派给我的工作列表</title>
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
		<li class="active"><a>指派给我的工作列表</a></li>
		<li><a href="${ctx}/work/pbsWork/distributedList">我派发的工作列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li><a href="${ctx}/work/pbsWork/form">任务指派</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsTaskrec"
		action="${ctx}/work/pbsWork/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>任务标题：</label> <form:input path="sResume"
					htmlEscape="false" maxlength="200" class="input-medium" />
			</li>
			<li><label>发起时间：</label> <input name="createDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsTaskrec.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
			</li>
			<li><label>截止时间：</label> <input name="sDeadline" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsTaskrec.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
			</li>
			<li><label>任务状态：</label> 
				<form:select path="sOptstatus" class="input-large" >
					<form:option value="" label="全部"></form:option>
					<form:options items="${fns:getDictList('taskoptstatus')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary"> 
					<i class="icon-search"></i> 查询
				</a>
			</li>
			<li class="clearfix"></li> 
		</ul>
	</form:form>
	<ul class="zbdt">
		<c:forEach items="${page.list}" var="pbsTaskrec">
			<li>
				 <div class="list0">
				  	<h5>任务标题</h5>
				  	<h6><a
						href="${ctx}/work/pbsWork/givemeInfo?id=${pbsTaskrec.getId()}">${pbsTaskrec.getSResume()}</a></h6>
				 </div>
				 <div class="list1">
				  	<h5>发起时间</h5>
				  	<h6>
				  		<fmt:formatDate value="${pbsTaskrec.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				  	</h6>
				  </div>
				  <div class="list2">
				  	<h5>截止时间</h5>
				  	<h6>
						<fmt:formatDate value="${pbsTaskrec.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss" />
					</h6>
				  </div>
				  <div class="list3">
				  	<h5>任务状态</h5>
				  	<h6>
						${fns:getDictLabel(pbsTaskrec.sOptstatus, 'taskoptstatus', '')}
					</h6>
				  </div>
				  <div  class="list4">
				  	<h5>操作</h5>
				  	<h6>
				  		<a href="${ctx}/work/pbsWork/givemeInfo?id=${pbsTaskrec.getId()}" title = "详细"><i class="icon-user"></i></a>
					</h6>
				  </div>
				</li>
			</c:forEach>
		</ul>
	<div class="pagination">${page}</div>
</body>
</html>