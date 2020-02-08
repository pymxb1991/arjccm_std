<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>资源共享管理</title>
<meta name="decorator" content="default" />
<!-- 列表缩略图切换 -->
<!--自适应  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
	rel="stylesheet">
<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
<!-- /列表缩略图切换 -->
	<script type="text/javascript" src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(){
			$('#searchForm').submit();
		});
		$("#btnSubmit1, #userName").click(function(){
			// 是否限制选择，如果限制，设置为disabled
			if ($("#userButton").hasClass("disabled")){
				return true;
			}
			var rid=[];
			rid=$(this).attr("rid")
			//alert(rid);
			// 针对于巡逻点位 修改 传出 相关类型方法
			// TODO
			var patorlTimeType=$("#timeType").val()||"";
			var UrlType = "";
			if(patorlTimeType !=""){
				UrlType ="iframe:/arjplm/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=3")+"&module="+patorlTimeType+"&checked=&extId=&isAll=true";
			}else{
				UrlType= "iframe:/arjplm/a/tag/treeselect?url="+encodeURIComponent("/sys/office/treeData?type=3")+"&module=&checked=&extId=&isAll=true";
			}
			// 正常打开	
			top.$.jBox.open( UrlType, "选择用户", 300, 420, {
				ajaxData:{selectIds: $("#userId").val()},buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
					if (v=="ok"){
						var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
						var ids = [], names = [], nodes = [] ;
						if ("" == "true"){
							nodes = tree.getCheckedNodes(true);
						}else{
							nodes = tree.getSelectedNodes();
						}
						for(var i=0; i<nodes.length; i++) {//
							if (nodes[i].isParent){
								top.$.jBox.tip("不能选择父节点（"+nodes[i].name+"）请重新选择。");
								return false;
							}//
							ids.push(nodes[i].id);
							//alert(ids);
							names.push(nodes[i].name);//
							$.ajax({
										url : "${ctx}/resource/plmPersonalResource/saveId?type=03",
										type : "post",
										data : {"ids" : ids,"rid":rid },
										dataType:"json",
										traditional: true, 
										async : true,
										cache : false,
										timeout : 300000,
										success : function(data) {
											if (data === undefined || data == null
													|| data == "") {
												 messageAlert("分享失败！", "error"); 
												return;
											}
											if (data.ret != 0) {
												messageAlert(data.message, "error");
												return;
											}
											 messageAlert("成功分享给"+names, "success");
										},
										 error : function(jqXHR, textStatus, errorThrown) {
											messageAlert("分享失败！", "error");
											console.error("jqXHR:", jqXHR);
											console.error("textStatus:", textStatus);
											console.error("errorThrown:", errorThrown);
											top.$.jBox.closeTip();
									}							
							});
							break; // 如果为非复选框选择，则返回第一个选择  
						}
						$("#userId").val(ids.join(",").replace(/u_/ig,""));
						$("#userName").val(names.join(","));
					}//
					else if (v=="clear"){
						$("#userId").val("");
						$("#userName").val("");
	                }//
					if(typeof userTreeselectCallBack == 'function'){
						userTreeselectCallBack(v, h, f);
					}
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
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
		<li class="active"><a
			href="${ctx}/resource/plmPersonalResource/?types=01">我的资源列表</a></li>
		<shiro:hasPermission name="resource:plmPersonalResource:edit">
			<li><a href="${ctx}/resource/plmPersonalResource/form">我的资源添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmResource"
		action="${ctx}/resource/plmPersonalResource/?type=02&types=03" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>标题：</label> <form:input path="name"
					htmlEscape="false" maxlength="128" class="input-medium" /></li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<!--/列表缩略图切换按钮 -->
	<div id="prodInfo_List">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>标题</th>
					<th>更新时间</th>
					<th>备注信息</th>
					<shiro:hasPermission name="resource:plmPersonalResource:edit">
						<th>操作</th>
					</shiro:hasPermission>
					<th>资源分享</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmResource">
					<tr>
						<td><a
							href="${ctx}/resource/plmPersonalResource/form?id=${plmResource.id}">
								${plmResource.name} </a></td>
						<td><fmt:formatDate value="${plmResource.updateDate}"
								pattern="yyyy-MM-dd " /></td>
						<td>${plmResource.remarks}</td>
						<shiro:hasPermission name="resource:plmPersonalResource:edit">
							<td> 
							<c:if test="${plmResource.type!='03'}">
							<a id="hide" class="btnList"
								href="${ctx}/resource/plmPersonalResource/form?id=${plmResource.id}"><i title="修改" class="icon-pencil"></i></a>
							</c:if>
								<a class="btnList"
								href="${ctx}/resource/plmPersonalResource/delete?id=${plmResource.id}"
								onclick="return confirmx('确认要删除吗？', this.href)"><i title="删除" class="icon-trash"></i></a></td>
						</shiro:hasPermission>
						<td>
							<a id="btnSubmit1"  class="fenxiang"  rid='${plmResource.id}' ><i class="icon-bookmark-empty" title="分享"></i></a>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmResource">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a
							href="${ctx}/resource/plmPersonalResource/form?id=${plmResource.id}">
							<h4 title="${plmResource.name}">标题：${plmResource.name}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${plmResource.img}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图">
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmResource.user.name }">提交人：${plmResource.user.name }</p>
								<p
									title="<fmt:formatDate value="${plmResource.updateDate}"
								pattern="yyyy-MM-dd " />">
									提交时间：
									<fmt:formatDate value="${plmResource.updateDate}"
										pattern="yyyy-MM-dd " />
								</p>
								<p title="${plmResource.remarks}">备注：${plmResource.remarks}</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="resource:plmPersonalResource:edit">
								<c:if test="${plmResource.type!='03'}">
								<a class="btnList"
									href="${ctx}/resource/plmPersonalResource/form?id=${plmResource.id}"
									title="修改"><i class="icon-pencil"></i></a>
								</c:if>
								<a class="btnList"
									href="${ctx}/resource/plmPersonalResource/delete?id=${plmResource.id}"
									onclick="return confirmx('确认要删除该资源共享吗？', this.href)" title="删除"><i
									class="icon-trash"></i></a>
							</shiro:hasPermission>
							<a id="btnSubmit1"  class="fenxiang"  rid='${plmResource.id}' ><i class="icon-bookmark-empty" title="分享"></i></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- /缩略图 -->
	<div class="pagination">${page}</div>
</body>
</html>