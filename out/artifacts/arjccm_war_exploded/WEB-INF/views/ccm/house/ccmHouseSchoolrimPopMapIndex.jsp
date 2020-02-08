<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安全可视化呈现</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {
			overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;
		}
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
		    	<a class="accordion-toggle">校园区域<i class="icon-refresh pull-right" onclick="refreshTree();"></i></a>
		    </div>
			<div id="ztree" class="ztree"></div>
		</div>
		<!--  -->
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="ccmHouseSchoolrim" src="${ctx}/house/ccmHouseSchoolrim/popMap?id=" style="width:100%;height:100%" frameborder="0"></iframe>
		</div>
	</div>
	<script src="${ctxStatic}/common/jsplit.js" type="text/javascript"></script>
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var type = treeNode.type;
					var id = treeNode.id == '0' ? '' :treeNode.pId;
					console.log("id:"+id+"// treeNode:"+treeNode.name)
					//id="+treeNode.id+"&parentIds=,"+treeNode.id+","
					if(type=="1"){
						$('#ccmHouseSchoolrim').attr("src","${ctx}/house/ccmHouseSchoolrim/popMap?id="+treeNode.id);
					}
				}
			}
		};
		function refreshTree(){
			$.getJSON("${ctx}/house/ccmHouseSchoolrim/treeData",function(data){
				// 对于icon 添加照片
				for(x in data){
					data[x].icon = typeTree(data[x]["type"]);
				}
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		var leftWidth = 230; // 左侧窗口大小
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
		function typeTree(type){
			if(type=="1"){//学校
				return ctxStatic+"/modules/map/images/tree_schools.png";
			}
			if(type=="0"){//学校分类
				return ctxStatic+"/modules/map/images/tree_house.png";
			}
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