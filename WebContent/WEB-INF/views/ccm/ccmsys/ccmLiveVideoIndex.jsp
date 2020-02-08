<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
		#left{
			background:none
		}
		.accordion-heading{
			background-image: none
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group">
			<div class="accordion-heading">
		    	<a class="accordion-toggle"style="width: 172px">网格<i class="icon-refresh pull-right" onclick="refreshTree();"></i></a>
		    </div>
			<div id="ztree" class="ztree"></div>
		</div>
		<!--  -->
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="ccmLiveVideo" src="${ctx}/ccmsys/ccmLiveVideo/vedioPlay?id=&parentIds=" width="100%" height="91%" frameborder="0" ></iframe>
		</div>
	</div>
	<script src="${ctxStatic}/common/jsplit.js" type="text/javascript"></script>
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id == '0' ? '' :treeNode.pId;
					console.log("id:"+id+"// treeNode:"+treeNode.name);
					if(treeNode.type != 'camera') {
						return;
					}
					//id="+treeNode.id+"&parentIds=,"+treeNode.id+","
					$('#ccmLiveVideo').attr("src","${ctx}/ccmsys/ccmLiveVideo/vedioPlay?parentIds="+treeNode.id+"&id="+treeNode.id);
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ctx}/ccmsys/ccmLiveVideo/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		 
		var leftWidth = 240; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
		//左侧导航栏拖动
		 window.onload = function() {
	            $("#left").jsplit({ MaxW: "600px"
	                , MinW: "50px"
	                , FloatD: "left"
	                , IsClose: false
	            });
	            wSize();
	        }
	</script>
		<!-- 左侧导航栏双击隐藏显示 -->
<script src="${ctxStatic}/common/wsizeDb.js" type="text/javascript"></script>
</body>
</html>