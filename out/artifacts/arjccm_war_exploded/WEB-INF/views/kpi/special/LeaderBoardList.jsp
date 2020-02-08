<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专项考核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var contexturl = $(".context").attr("content");
            $("#btnSubmit").on("click" ,function(){
                $("#searchForm").submit();
            })
			$("#btn").click(function(){

			});
            //初始化时判断隐藏条件
            var val = $('#type option:selected') .val();
			if(val == '02'){
				$("#showYear").hide();
				$("#showQuarter").hide();
				$("#showMonth").show();
			}else if(val == '03'){
				$("#showMonth").hide();
				$("#showYear").show();
				$("#showQuarter").show();
			}else{
				$("#showQuarter").hide();
				$("#showMonth").hide();
				$("#showYear").show();
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//排名类型变化时，后面的条件隐藏修改
		function change(){
			debugger;
			var val = $('#type option:selected') .val();
			//每次修改都将季度恢复为第一季度选项
			$('#quarter').select2("val","01");
			//先清空值
			document.getElementById('year').value = "";
			document.getElementById('month').value = "";
			if(val == '02'){
				//选择显示还是隐藏
				$("#showYear").hide();
				$("#showQuarter").hide();
				$("#showMonth").show();
			}else if(val == '03'){
				//选择显示还是隐藏
				$("#showMonth").hide();
				$("#showYear").show();
				$("#showQuarter").show();
			}else{
				//选择显示还是隐藏
				$("#showQuarter").hide();
				$("#showMonth").hide();
				$("#showYear").show();
			}
		}
		
		$(function()
				  {
						
						$("#showAllwg").click(function()
							{
							if($(this).val()=="展开更多")
								{
							      $(".wgTr").removeAttr("style");
							      $("#showAllwg").attr("value","收起");
								}
				 			else
								{
								  $(".wgTr").css("display","none");
				 			      $("#showAllwg").attr("value","展开更多");
				 				}
							});	
						
						$("#showAllsq").click(function()
								{
								if($(this).val()=="展开更多")
									{
								      $(".sqTr").removeAttr("style");
								      $("#showAllsq").attr("value","收起");
									}
					 			else
									{
									  $(".sqTr").css("display","none");
					 			      $("#showAllsq").attr("value","展开更多");
					 				}
								});
						
						$("#showAllqx").click(function()
								{
								if($(this).val()=="展开更多")
									{
								      $(".qxTr").removeAttr("style");
								      $("#showAllqx").attr("value","收起");
									}
					 			else
									{
									  $(".qxTr").css("display","none");
					 			      $("#showAllqx").attr("value","展开更多");
					 				}
								});
				  });
		
	</script>
</head>
<body>
<div class="back-list" style="height: 970px;">
	<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/special/kpiSpecialScore/getLeaderBoard">排行榜</a></li>
	</ul>
		<sys:message content="${message}"/>
		<form:form id="searchForm"  modelAttribute="kpiSpecialScore" action="${ctx}/special/kpiSpecialScore/getLeaderBoard" method="post" class="breadcrumb form-search clearfix">
			<ul class="ul-form pull-left">
				<li class="first-line"><label>请选择排名：</label>
					<form:select id="type" path="type" class="input-medium" onchange="change()">
						<form:options items="${fns:getDictList('ccm_check_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li id="showYear" class="first-line">
					<label>选择年份：</label> <input
					name="year" type="text" readonly="readonly" id="year" path="year" 
					maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${year}" pattern="yyyy"/>"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true});" />
				</li>
				<li id="showMonth" class="first-line">
					<label>选择月份：</label><input 
					name="month" type="text" readonly="readonly" id="month" path="month" 
					maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${kpiSpecialScore.month}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});" />
				</li>
				<li id="showQuarter" class="first-line"><label>选择季度：</label>
					<form:select id="quarter" path="quarter" class="input-medium">
						<form:options items="${fns:getDictList('kpi_score_quarter')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
			</ul>	

		<div class="clearfix pull-right btn-box">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right">
				<i></i><span style="font-size: 12px">查询</span>  </a>
		</div>
	</form:form>
	<div class="span6 column ui-sortable" style="margin-top: 20px">
		<span>网格排行榜</span>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>名次</th>
					<th>网格</th>
					<th>分值</th>
				</tr>
			</thead>
			<tbody id="WG">
				<c:set var="i" value="0"></c:set>
				<c:forEach var="wg" items="${listWG}">
					<c:set var="i" value="${i+1}"/>
					<c:if test="${i<11}">
						<tr >
							<td>${i}</td>
							<td>${wg.name}</td>
							<td>${wg.score}</td>
						</tr>
					</c:if>
					<c:if test="${i>=11}">
						<tr style="display: none;" class="wgTr">
							<td>${i}</td>
							<td>${wg.name}</td>
							<td>${wg.score}</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<c:set var="wgsize" value="${wgsize}"/>
					<c:if test="${wgsize>10}">
						<td colspan="3"><input type="button" value="展开更多" id="showAllwg"></td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<div class="span6 column ui-sortable" style="margin-top: 20px">
		<span>社区排行榜</span>
		<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名次</th>
				<th>社区</th>
				<th>分值</th>
			</tr>
		</thead>
			<tbody id="SQ">
				<c:set var="j" value="0"></c:set>
				<c:forEach var="sq" items="${listSQ}">
					<c:set var="j" value="${j+1}"/>
					<c:if test="${j<11}">
						<tr>
							<td>${j}</td>
							<td>${sq.name}</td>
							<td>${sq.score}</td>
						</tr>
					</c:if>
					<c:if test="${j>=11}">
						<tr style="display: none;" class="sqTr">
							<td>${j}</td>
							<td>${sq.name}</td>
							<td>${sq.score}</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<c:set var="sqsize" value="${sqsize}"/>
					<c:if test="${sqsize>10}">
						<td colspan="3"><input type="button" value="展开更多" id="showAllsq"></td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<div class="span6 column ui-sortable" style="margin-top: 20px">
		<span>区县排行榜</span>
		<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名次</th>
				<th>区县</th>
				<th>分值</th>
			</tr>
		</thead>
			<tbody id="qx">
				<c:set var="k" value="0"></c:set>
				<c:forEach var="qx" items="${listQX}">
					<c:set var="k" value="${k+1}"/>
					<c:if test="${k<11}">
						<tr>
							<td>${k}</td>
							<td>${qx.name}</td>
							<td>${qx.score}</td>
						</tr>
					</c:if>
					<c:if test="${k>=11}">
						<tr style="display: none;" class="qxTr">
							<td>${k}</td>
							<td>${qx.name}</td>
							<td>${qx.score}</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<c:set var="qxsize" value="${qxsize}"/>
					<c:if test="${qxsize>10}">
						<td colspan="3"><input type="button" value="展开更多" id="showAllqx"></td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>