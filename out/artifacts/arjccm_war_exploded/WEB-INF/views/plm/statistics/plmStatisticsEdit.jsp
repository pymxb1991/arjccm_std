<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/2.3.1/css_default/bootstrap.min.css">
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
<!--[if lte IE 7]>
    <link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
    <![endif]-->
<!--[if lte IE 6]>
    <link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
    <script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="${ctxStatic}/asidenav/asidenav.css">
<script src="${ctxStatic}/asidenav/asidenav.js"></script>
<script src="${ctxStatic}/asidenav/jquery.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/indexCommon.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/index.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexStatis.css">
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/shine.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/indexCommon.js"></script>

<style type="text/css">
#themeSwitch{
   position: absolute;
   top:16px;
   right: 50px;
}
li> .dropdown-menu {
    right: 0;
    left: auto;
}
li>.dropdown-menu:before {
    right: 10px;
    left: auto;
}
li>.dropdown-menu:before {
    position: absolute;
    top: -7px;
    left: auto;
    display: inline-block;
    border-right: 7px solid transparent;
    border-bottom: 7px solid #ccc;
    border-left: 7px solid transparent;
    border-bottom-color: rgba(0,0,0,0.2);
    content: '';
}
li>.dropdown-menu:after {
    right: 11px;
    left: auto;
}
li>.dropdown-menu:after {
    position: absolute;
    top: -6px;
   left: auto;
    display: inline-block;
    border-right: 6px solid transparent;
    border-bottom: 6px solid #fff;
    border-left: 6px solid transparent;
    content: '';
}
</style>
<script type="text/javascript">
	$(function() {
		$("#bodydiv").height($("#mainstatis").height() - 140);
         
		
		
	})
</script>
</head>
<body>

	<div class="container-fluid " style="height: 100%; overflow: hidden"
		id="mainstatis">
		<div id="topdiv">
			<form:form id="searchForm1" modelAttribute="PlmStatisticsPlan"
				action="${ctx}/statistics/plmStatistics/edit" method="post"
				class="breadcrumb form-search">
				<ul class="ul-form ">
					<li>
					<input name="pages" type="hidden" value="${pages}">
					<label style="color: black;">选择预览方案</label> <form:select
							path="id" class="input-xlarge " onchange="selectsubmit()">
							<form:option value="" label="" />
							<form:options items="${planList}" itemLabel="name" itemValue="id"
								htmlEscape="false" />
						</form:select> <c:if test="${isfangan==1}">
							<label style="margin-left: 50px"><input id=""
								class="btn btn-primary  fan" type="button" value="使用方案"
								onclick="qdfa()" /></label>
							<label style="margin-left: 50px"><input id=""
								class="btn btn-primary  fan" type="button" value="退出预览"
								onclick="qxfa()" /></label>
							<label style="margin-left: 50px"><input id=""
								class="btn btn-primary  bjnr" type="button" value="编辑内容"
								onclick="bjfanr()" /></label>
						</c:if> <c:if test="${isfangan==0}">
							<label style="margin-left: 50px"><input id=""
								class="btn btn-primary  bjnr" type="button" value="编辑内容"
								onclick="bjnr()" /></label>
								 <c:if test="${portletlist.size()>=1}">
								<label style="margin-left: 50px"><input id=""
								class="btn btn-primary  bjnr" type="button" value="删除本页"
								onclick="delnr()" /></label>
								</c:if>
						</c:if>
					   <c:if test="${isfangan==0}">
						
						
						<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="背景切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictList('statisticBackgroundImage')}" var="dict"><li><a href='${ctx}/statistics/plmStatistics/backgroundImage?backgroundImage=${dict.value}' >${dict.label}</a></li></c:forEach>			
						</ul>
						
					</li> 
						</c:if>
						</li>
				</ul>
			</form:form>
		</div>
		<div class="row-fluid header">
			<div class="span12">
				<h5 class="pageTitle">${pageTitle}</h5>
			</div>
		</div>
		
		
		<div id="bodydiv" style="position: relative; width: 100%;">
		
		 <!-- 箭头切换  --> 
		  <c:if test="${isfangan==0}">
		  <c:if test="${pages>1}">
		 <div  id="jiantouleft" class="jiantou" onclick="jiantou('left')"><i class="icon-angle-left"></i></div>
		 </c:if>
		  <c:if test="${portletlist.size()>=1}">
		  <div id="jiantouright" class="jiantou" onclick="jiantou('right')"><i class="icon-angle-right"></i></div>
		  </c:if>
		</c:if>
		 <!--/ 箭头切换  -->
		
			<c:if var="e" test="${portletlist.size()>=1}">
				<script type="text/javascript">
					var bjckid = "-1";//选中窗口id
					
				</script>
				<!-- 门户首页展示 -->

				<c:forEach var="portlet" items="${portletlist}">
					<div id="${portlet.id}" class="portletcolumn shadow "
						longItude="${portlet.longItude}" latItude="${portlet.latItude}"
						style="height:${(fn:length(portlet.longItude)*33)}%;width:${fn:length(portlet.latItude)*93/portlet.type+(fn:length(portlet.latItude)-1)*1}%; top:${(fn:substring(portlet.longItude, 0, 1)-1)*33}%; left:${(fn:substring(portlet.latItude, 0, 1)-1)*(93/portlet.type+1)+(8-portlet.type)/2}%;z-index:${portlet.longItude*portlet.latItude};">
						<c:if test="${portlet.title!=null&&not empty  portlet.title}">
							<div id="" class="portlettitle   ">
								<span id="" class="titlespan top-header">
									${portlet.title}</span>
							</div>
						</c:if>

						<div id="portletcontent${portlet.id}" class="portletcontent"
							style="">
							<div>正在加载...</div>
						</div>
					</div>
					<script type="text/javascript">
						//窗口选中
						$("#${portlet.id}").click(
								function() {
									if (!$(this).hasClass("portletcolumn2")) {
										$(".portletcolumn").removeClass(
												"portletcolumn2");
										$(this).addClass("portletcolumn2");
										bjckid = $(this).attr("id");
									} else {
										$(this).removeClass("portletcolumn2");
										bjckid = "-1";
									}
								});

						//加载窗口内容信息
						$(function() {
							var porwidth = $("#${portlet.id}").width() - 10;
							var porheigh = $("#${portlet.id}").height() - 10;
							$.ajax({
								url : '${ctx}${portlet.content}',
								type : 'post',
								data : {
									"height" : porheigh,
									"width" : porwidth,
									"content" : "${portlet.content}",
									"sid" : "${portlet.id}"
								},
								dataType : 'html',
								error : function() {
									$("#portletcontent${portlet.id}").html(
											"加载错误")
								},
								success : function(data) {
									$("#portletcontent${portlet.id}")
											.html(data);
								}
							});
						});
					</script>
				</c:forEach>

			</c:if>
			<c:if test="${!e}">当前没有数据！！ </c:if>
		</div>

	</div>

	<%-- <script src="${ctxStatic}/common/statistics/js/statisticsedit.js" type="text/javascript"></script> --%>
	<script type="text/javascript">
	//预编辑方案内容（不改数据库）
	function jiantou(type) {
		
		if(type=="left"){
			window.location.href = '${ctx}/statistics/plmStatistics/jianTou?pages=${pages-1}&list=0';
		}else {
			window.location.href = '${ctx}/statistics/plmStatistics/jianTou?pages=${pages+1}&list=0';
		}
		
	}	    	
		$(".pageTitle").dblclick(function() {

			jBox($("#importBox").html(), {
				title : "修改标题名称",
				buttons : {
					"关闭" : true
				},
				bottomText : ""
			});

		});
          
		
		
		//预编辑方案内容（不改数据库）
		function bjfanr() {
			if (bjckid == "-1") {
				bjalert("请先选中编辑窗口!")
			} else {
				jBox('iframe:${ctx}/statistics/plmStatisticsDetail/form?id='
						+ bjckid, {
					title : "预编辑使用方案内容",
					width : 600,
					height : 350,
					buttons : {}, //为了不出现底部的按钮这里特别要这样填写
					closed : function() { //关闭时发生，为了刷新父级页面
						search();
					},
					loaded : function(h) { //隐藏滚动条
						$(".jbox-content").css("overflow", "inherit");
					}
				});
			}
		}

		//编辑内容
		function bjnr() {
			if (bjckid == "-1") {
				bjalert("请先选中编辑窗口!")
			} else {
				jBox(
						'iframe:${ctx}/statistics/plmStatistics/form?id='
								+ bjckid, {
							title : "编辑门户内容",
							width : 600,
							height : 350,
							buttons : {}, //为了不出现底部的按钮这里特别要这样填写
							closed : function() { //关闭时发生，为了刷新父级页面
								search();
							},
							loaded : function(h) { //隐藏滚动条
								$(".jbox-content").css("overflow", "inherit");
							}
						});
			}
		}
		//删除本页
		function delnr() {
			top.$.jBox
					.confirm(
							"您确定要删除此页么",
							"系统提示",
							function(v, h, f) {
								if (v == "ok") {
									
									$.ajax({
												url : '${ctx}/statistics/plmStatistics/delPage',
												data : {
													"pages" : "${pages}"
												},
												type : 'post',
												dataType : 'text',
												error : function() {
													bjalert("删除失败!")
												},
												success : function(data) {

													if (data == "200") {
														window.location.href = '${ctx}/statistics/plmStatistics/';
													}
												}
											});
								}
							}, {
								buttonsFocus : 1
							});
		}
		//确定方案
		function qdfa() {
			top.$.jBox
					.confirm(
							"方案会覆盖您的门户信息，您确定要导入该方案",
							"系统提示",
							function(v, h, f) {
								if (v == "ok") {
									//loading('正在导入模板，请稍等...',1);
									var pid = $("#id").val();
									// alert(pid)
									$
											.ajax({
												url : '${ctx}/statistics/plmStatistics/importPlan',
												data : {
													"pid" : pid
												},
												type : 'post',
												dataType : 'text',
												error : function() {
													bjalert("导入失败!")
												},
												success : function(data) {

													if (data == "200") {
														window.location.href = '${ctx}/statistics/plmStatistics/';
													}
												}
											});
								}
							}, {
								buttonsFocus : 1
							});
		}
		//方案  :取消
		function qxfa() {
			window.location.href = '${ctx}/statistics/plmStatistics/';
		}
		//下拉type 提交事件
		function selectsubmit() {
			$(".fan").show();
			$(".bjnr").hide();
			$("#searchForm1").submit();
		}

		//alert弹窗提示
		function bjalert(text) {
			top.$.jBox.alert(text, "系统提示");
		}

		//1去边距 ；2调整bodydiv 高度
		$(function() {
			$("#right", parent.document).css("padding", "0px")

		})
		function pageTitleBtn() {

			$.ajax({
				url : '${ctx}/statistics/plmStatistics/pageTitle',
				data : {
					"pageTitle" : $(".jbox-content #pageTitleInput").val(),
					"pages" : $(".jbox-content #pagesInput").val()
				},
				type : 'post',
				dataType : 'text',
				error : function() {
					bjalert("修改失败!")
				},
				success : function(data) {

					$(".pageTitle").html(data)
					jBox.close(true);
				}
			});

		}
	</script>
	<div id="importBox" class="hide">
		<form class="form-search"
			style="padding-left: 20px; text-align: center;">
			<br /> <input id="pageTitleInput" name="pageTitle" type="text"
				value="${pageTitle}" style="height: 30px; line-height: 30px;" />
			<c:if test="${isfangan==0}">
				<input id="pagesInput" name="pages" type="hidden" value="${pages}" />
			</c:if>
			<input id="btnImportSubmit" class="btn btn-primary" type="button"
				onclick="pageTitleBtn()" value=" 确定" />

		</form>

	</div>

</body>
</html>