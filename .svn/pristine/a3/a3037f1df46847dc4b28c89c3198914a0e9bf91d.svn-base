<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>建言献策</title>
<meta name="decorator" content="default" />

<script>
  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }

  $(function() {
    
    $("#sPublish").val("${pbsProposal.SPublish}").select2();
  });
  // 

  function btnSubmit() {
    var flag = document.getElementById("selectFlag").value;
    if (flag == 1) {
      window.location.href = ctx + "/proposal/pbsProposalPC/proposalList";
    } else {
      window.location.href = ctx
          + "/proposal/pbsProposalPC/proposalList?isNotOpenFlag=true";
    }
  }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>建言信息列表</a></li>
		<li><a href="${ctx}/proposal/pbsProposalPC/form">提交建言</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="pbsProposal"
		action="${ctx}/proposal/pbsProposalPC/proposalList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>建言标题：</label> <form:input path="sTitle"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>展示方式：</label> <form:select path="sPublish"
					class="input-medium ">
					<option value="0">未通过建议</option>
					<option value="1">已通过建议</option>
				</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>

	<!-- <div class="breadcrumb form-search">
        <ul class="ul-form">
            <li><label>建议类型：</label> <select id="selectFlag"
                class="input-xlarge">
                    <option value="">全部建议</option>
                    <option value="1">公开建议</option>
                    <option value="2">我的建议</option>
            </select></li>
            <li class="btns">
                <button onclick="btnSubmit()" class="btn btn-primary">查询</button>
            </li>
            <li class="clearfix"></li>
        </ul>
    </div> -->

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>建言类型</th>
				<th>建言标题</th>
				<th>发起时间</th>
				<th>审批状态</th>
				<th>回复个数</th>
				<th>详情</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="proposal">
				<tr>
					<td><a
						href="${ctx}/proposal/pbsProposalPC/proposalInfo?id=${proposal.id}"
						title="详细">${proposal.sAreaid.SName}</a></td>
					<td>${proposal.STitle}</td>
					<td><fmt:formatDate value="${proposal.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${fns:getDictLabel(proposal.SStat, 'proposalstattype', '')}</td>
					<td>${proposal.ICnt}<c:if
							test="${ (proposal.ICnt ne 0) and (empty proposal.ICnt) }">0</c:if></td>
					<td><a
						href="${ctx}/proposal/pbsProposalPC/proposalInfo?id=${proposal.id}"
						title="详细"><i class="icon-user"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>