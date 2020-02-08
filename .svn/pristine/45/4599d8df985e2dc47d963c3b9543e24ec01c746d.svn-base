<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<meta charset="UTF-8">

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
	href="${ctxStatic}/common/index/css/indexCommon.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/index.css">
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/shine.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/indexCommon.js"></script>


<style>
</style>

<script type="text/javascript">
	var bjckid = "";//选中窗口id
</script>
</head>
<body>

	<div class="container-fluid" style="height: 100%; overflow: hidden"
		id="mainstatis">
		<div class="context" content="${ctx}"></div>
		<div class="row-fluid header">
			<div class="span1"></div>
			<div class="span2">

				<div class="Logout">
					<span> <a onclick="bjnr()" class="addck"> 新增窗口</a>
					</span>
				</div>
			</div>
			<div class="span1">
				<div class="Logout">
					<span> <a onclick="del()"> 删除窗口</a>
					</span>
				</div>

			</div>
		</div>

		<c:if var="e" test="${portletlist.size()>=1}">
			<!-- 门户首页展示 -->
			<div id="height100 jbox">
				<c:forEach var="portlet" items="${portletlist}">
					<div id="${portlet.id}" class="portletcolumn shadow "
						longItude="${portlet.longItude}" latItude="${portlet.latItude}"
						style="height:${(fn:length(portlet.longItude)*30)}%;width:${fn:length(portlet.latItude)*93/PlmStatisticsPlan.type+(fn:length(portlet.latItude)-1)*1}%; top:${((fn:substring(portlet.longItude, 0, 1)-1)*30)+10}%; left:${(fn:substring(portlet.latItude, 0, 1)-1)*(93/PlmStatisticsPlan.type+1)+(8-PlmStatisticsPlan.type)/2}%;z-index:${portlet.longItude*portlet.latItude};">
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
										$(".addck").html("编辑窗口");
									} else {
										$(this).removeClass("portletcolumn2");
										bjckid = "";
										$(".addck").html("新增窗口");
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
			</div>
		</c:if>
		<c:if test="${!e}">当前没有数据！！ </c:if>
	</div>


	<script type="text/javascript">
		//编辑内容
		function bjnr() {

			jBox('iframe:${ctx}/statistics/plmStatisticsDetail/formDetail?id='
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

		//删除
		function del() {
			if (bjckid == "") {
				bjalert("请先选中删除窗口!")
			} else {
				jBox.confirm('确认删除该窗口吗', "系统提示", function(v, h, f) {
					if (v == "ok") {

						$.get(
								'${ctx}/statistics/plmStatisticsDetail/delete?id='
										+ bjckid, function(data) {

									window.location.reload();

								});

					}
				}, {
					buttonsFocus : 1
				});

			}
		}

		//alert弹窗提示
		function bjalert(text) {
			top.$.jBox.alert(text, "系统提示");
		}

		//1去边距 ；2调整bodydiv 高度
		$(function() {
			$("#right", parent.document).css("padding", "0px")

		})
	</script>
</body>
</html>