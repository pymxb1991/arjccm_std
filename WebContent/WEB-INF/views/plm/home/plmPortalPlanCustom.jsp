<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>门户方案定制</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/home/css/homeedit.css">
<script type="text/javascript">
	var bjckid = "";//选中窗口id
</script>
</head>
<body>
	<form class="breadcrumb form-search" style="margin-top: 8px;">
		<ul class="ul-form ">
			<li class="clearfix">
				<label>
					<input id="" class="btn btn-primary addck" type="button" value="新增窗口" onclick="bjnr()" />
				</label>
				<label>
					<input id="" class="btn btn-primary" type="button" value="删除窗口" onclick="del()" />
				</label>
			</li>
		</ul>
	</form>
	<div id="bodydiv">
		<c:if var="e" test="${portletlist.size()>=1}">
			<script type="text/javascript">
				var type = "${portletlist.get(0).type}"
				//通过高度 和 行数调节#bodydiv 的高度  以免出现背景色覆盖不全问题  
				var highttype = 3;// 行数（默认为三行）
				var bdhight = "${(portletlist.get(0).hight+30)}"//高度
			</script>
			<!-- 门户首页展示 -->
			<div id="portletcontainer jbox">
				<c:forEach var="portlet" items="${portletlist}">
					<div id="${portlet.id}" class="portletcolumn "
						longItude="${portlet.longItude}" latItude="${portlet.latItude}"
						style="height:${(fn:length(portlet.longItude)*49)+(fn:length(portlet.longItude)-1)}%;width:${fn:length(portlet.latItude)*93/portlet.type+(fn:length(portlet.latItude)-1)*1}%; top:${((fn:substring(portlet.longItude, 0, 1)-1)*(49+1))}%; left:${(fn:substring(portlet.latItude, 0, 1)-1)*(93/portlet.type+1)}%;z-index:${portlet.longItude*portlet.latItude};">
						<c:if test="${portlet.title!=null&&not empty  portlet.title}">
							<div id="" class="portlettitle   ">
								<span id="" class="titlespan ">
									<i class="icon-caret-right tubiao"></i>
									${portlet.title}
								</span>
								<a class="titlemore" href="${ctx}${portlet.connect}">
									<c:if test="${portlet.connect!='null'&&not empty  portlet.connect}">
										更多<i class="icon-double-angle-right tubiao"></i>
									</c:if>
								</a>
							</div>
						</c:if>
						<a class="jbox-close" title="关闭"
							onmouseover="$(this).addClass('jbox-close-hover');"
							onmouseout="$(this).removeClass('jbox-close-hover');"
							style="position: absolute; display: block; cursor: pointer; top: 11px; right: 11px; width: 15px; height: 15px;"></a>
						<div id="portletcontent${portlet.id}" class="portletcontent">
							<div>正在加载...</div>
						</div>
					</div>
					<script type="text/javascript">
						//窗口选中
						$("#${portlet.id}").click(function() {
							if (!$(this).hasClass("portletcolumn2")) {
								$(".portletcolumn").removeClass("portletcolumn2");
								$(this).addClass("portletcolumn2");
								bjckid = $(this).attr("id");
								$(".addck").val("编辑窗口");
							} else {
								$(this).removeClass("portletcolumn2");
								bjckid = "";
								$(".addck").val("新增窗口");
							}
						});
						//通过高度 和 行数调节#bodydiv 的高度  以免出现背景色覆盖不全问题  
						var h = "${fn:substring(portlet.longItude, -1,1)}";
						if (highttype < h) {
							highttype = h
						}
						//加载窗口内容信息
						$(function() {
							var porwidth = $("#${portlet.id}").width() - 10;
							var porheigh = ($(window).height() - 60) * 0.48 - 10;
							$.ajax({
								url : '${ctx}${portlet.content}',
								type : 'post',
								data : {
									"height" : porheigh,
									"width" : porwidth,
									"content" : "${portlet.content}"
								},
								dataType : 'html',
								error : function() {
									$("#portletcontent${portlet.id}").html("加载错误")
								},
								success : function(data) {
									$("#portletcontent${portlet.id}").html(data);
								}
							});
						});
					</script>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${!e}">当前没有数据！！ </c:if>
	</div>
	<script src="${ctxStatic}/common/home/js/homeedit.js" type="text/javascript"></script>
	<script type="text/javascript">
		//编辑内容
		function bjnr() {
			jBox('iframe:${ctx}/home/plmPortalDetail/formDetail?id=' + bjckid, {
				title : "编辑门户内容",
				width : 600,
				height : 500,
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
						$.get('${ctx}/home/plmPortalDetail/delete?id=' + bjckid, function(data) {
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
			$("#bodydiv").height($(window).height() - 60);
		})
	</script>
</body>
</html>