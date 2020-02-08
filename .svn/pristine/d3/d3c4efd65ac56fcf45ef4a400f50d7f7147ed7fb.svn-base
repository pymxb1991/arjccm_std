<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>个人门户编辑</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/home/css/homeedit.css">
</head>
<body>
	<div id="topdiv">
		<form:form id="searchForm1" modelAttribute="plmPortalPlan" action="${ctx}/home/plmHome/edit" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<li>
					<label class="formlabel">选择方案</label>
					<form:select path="id" class="input-xlarge" onchange="selectsubmit()">
						<form:option value="" label="" />
						<form:options items="${planList}" itemLabel="name" itemValue="id" htmlEscape="false" />
					</form:select>
				</li>
				<li class="clearfix">
					<c:if test="${isfangan==1}">
						<label>
							<input id="" class="btn btn-primary  fan" type="button" value="使用方案" onclick="qdfa()" />
						</label>
						<label>
							<input id="" class="btn btn-primary  fan" type="button" value="返   回" onclick="qxfa()" />
						</label>
<!-- 						<label> -->
<!-- 							<input id="" class="btn btn-primary  bjnr" type="button" value="编辑内容" onclick="bjfanr()" /> -->
<!-- 						</label> -->
					</c:if>
					<c:if test="${isfangan==0}">
						<label>
							<input id="" class="btn btn-primary  bjnr" type="button" value="编辑内容" onclick="bjnr()" />
						</label>
					</c:if>
				</li>
			</ul>
		</form:form>
	</div>
	<div id="bodydiv">
		<c:if var="e" test="${portletlist.size()>=1}">
			<script type="text/javascript">
				var bjckid = "-1";//选中窗口id
				/* var type="${portletlist.get(0).type}"
				 //通过高度 和 行数调节#bodydiv 的高度  以免出现背景色覆盖不全问题  
				 var highttype=3;// 行数（默认为三行）
				 var bdhight="${(portletlist.get(0).hight+30)}"//高度 */
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
								<a id="" class="titlemore  " href="${ctx}${portlet.connect}">
									<c:if
										test="${portlet.connect!='null'&&not empty  portlet.connect}">更多<i
											class="icon-double-angle-right tubiao"></i>
									</c:if>
								</a>
							</div>
						</c:if>
						<div id="portletcontent${portlet.id}" class="portletcontent"
							style="">
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
							} else {
								$(this).removeClass("portletcolumn2");
								bjckid = "-1";
							}
						});
						/* //通过高度 和 行数调节#bodydiv 的高度  以免出现背景色覆盖不全问题  
						 var h="${fn:substring(portlet.longItude, -1,1)}";   
						 if(highttype<h){
						 highttype=h
						 } */
						//加载窗口内容信息
						$(function() {
							var porheigh = ($(window).height() - 60) * 0.48 - 10;
							var porwidth = $("#${portlet.id}").width() - 10;
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
	<script type="text/javascript">
		//预编辑方案内容（不改数据库）
// 		function bjfanr() {
// 			if (bjckid == "-1") {
// 				bjalert("请先选中编辑窗口!")
// 			} else {
// 				jBox('iframe:${ctx}/home/plmPortalDetail/form?id=' + bjckid, {
// 					title : "预编辑使用方案内容",
// 					width : 600,
// 					height : 380,
// 					buttons : {}, //为了不出现底部的按钮这里特别要这样填写
// 					closed : function() { //关闭时发生，为了刷新父级页面
// 						search();
// 					},
// 					loaded : function(h) { //隐藏滚动条
// 						$(".jbox-content").css("overflow", "inherit");
// 					}
// 				});
// 			}
// 		}

		//编辑内容
		function bjnr() {
			if (bjckid == "-1") {
				bjalert("请先选中编辑窗口!")
			} else {
				jBox('iframe:${ctx}/home/plmHome/form?id=' + bjckid, {
					title : "编辑门户内容",
					width : 600,
					height : 380,
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

		//确定方案
		function qdfa() {
			top.$.jBox.confirm("方案会覆盖您的门户信息，您确定要导入该方案", "系统提示", function(v, h,
					f) {
				if (v == "ok") {
					//loading('正在导入模板，请稍等...',1);
					var pid = $("#id").val();
					// alert(pid)
					$.ajax({
						url : '${ctx}/home/plmHome/importPlan',
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
								window.location.href = '${ctx}/home/plmHome/';
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
			window.location.href = '${ctx}/home/plmHome/';
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
			$("#bodydiv").height($(window).height() - 60);
		})
	</script>
</body>
</html>