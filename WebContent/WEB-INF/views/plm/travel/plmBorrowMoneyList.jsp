<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款申请管理</title>
	<meta name="decorator" content="default"/>
	<!-- 列表缩略图切换 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link
		href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
		rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
	<!-- /列表缩略图切换 -->
	<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSubmit").on("click", function() {
				var begin = new Date(Date.parse($("[name='beginBorrowDate']").val()));
			    var end = new Date(Date.parse($("[name='endBorrowDate']").val()));
			    if(begin.getTime() > end.getTime()){
			    	messageAlert("开始时间大于结束时间！", "error");
			    	return false;
			    }
			    $("#searchForm").submit();
			});
						$("#searchForm").validate({	
						 
						   submitHandler: function(form){
							   var beginBorrowDate = $("#beginBorrowDate").val();
								var endBorrowDate = $("#endBorrowDate").val();							
								if(dateSearchForm(beginBorrowDate,endBorrowDate)){
									alert("借款日期范围错误");
									return false;
								}else{
								$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
								form.submit();
								}
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function dateSearchForm(beginBorrowDate,endBorrowDate){
			if(beginBorrowDate == null || endBorrowDate == null){
				return true;
			}
			var startTime = new Date(Date.parse(beginBorrowDate));
			var endTime = new Date(Date.parse(endBorrowDate));
			if(startTime > endTime){
				return true;
			}else{
				return false;
			}
        }
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="plmBorrowMoney" action="${ctx}/travel/plmBorrowMoney/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium "/>
			</li>
			
			<li><label>借款日期：</label>
				<input name="beginBorrowDate" id="beginBorrowDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmBorrowMoney.beginBorrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBorrowDate" id="endBorrowDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmBorrowMoney.endBorrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>借款总额：</label>
				<form:input path="beginSum" htmlEscape="false" class="input-medium"/> - 
				<form:input path="endSum" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<div id="prodInfo_List">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>编号</th>
				<th>申请人</th>
				<th>借款日期</th>
				<th>借款总额</th>
				<th>借款原因</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmBorrowMoney">
			<tr>
				<td><a href="${ctx}/travel/plmBorrowMoney/form?id=${plmBorrowMoney.id}">
					${plmBorrowMoney.title}
				</a></td>
				<td>
					${plmBorrowMoney.code}
				</td>
				<td>
					${plmBorrowMoney.applyer.name}
				</td>
				<td>
					<fmt:formatDate value="${plmBorrowMoney.borrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmBorrowMoney.sum}
				</td>
				<td>
					${plmBorrowMoney.cause}
				</td>
				<td class="tp">
					${plmBorrowMoney.remarks}
				</td>
				<td>
    				<c:if test="${not empty plmBorrowMoney.procInsId}" var="e">
						
							<a href="${ctx}/travel/plmBorrowMoney/form?id=${plmBorrowMoney.id}"
								title="显示详情"><i class="icon-file"></i></a>
				
					</c:if>
					<c:if test="${!e}">
						
							<a href="${ctx}/travel/plmBorrowMoney/form?id=${plmBorrowMoney.id}"
								title="提交申请"><i class="icon-pencil"></i></a>
							<a class="btnList"
								href="${ctx}/travel/plmBorrowMoney/delete?id=${plmBorrowMoney.id}"
								onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a>
						
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmBorrowMoney">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/travel/plmBorrowMoney/form?id=${plmBorrowMoney.id}">
							<h4 title="${plmBorrowMoney.title}">标题：${plmBorrowMoney.title}</h4>
						</a>
						<div class="caption row-fluid">


							<div class="spantext  " style="width: 88%; margin-left: 6%">
								<p title="${plmBorrowMoney.code}">编号：${plmBorrowMoney.code}</p>
								<p title="${plmBorrowMoney.applyer.name}">申请人：${plmBorrowMoney.applyer.name}</p>
								<p title="${plmBorrowMoney.sum}">借款总额：${plmBorrowMoney.sum}</p>
							</div>

						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmBorrowMoney.procInsId}" var="e">
								
									<a href="${ctx}/travel/plmBorrowMoney/form?id=${plmBorrowMoney.id}"
										title="显示详情"><i class="icon-file"></i></a>
							
							</c:if>
							<c:if test="${!e}">
								
									<a href="${ctx}/travel/plmBorrowMoney/form?id=${plmBorrowMoney.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/travel/plmBorrowMoney/delete?id=${plmBorrowMoney.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>