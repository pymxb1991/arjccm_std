/**
 * Created by oHa on 2018/4/11.
 */
var zTree;
var rMenu;
$(function(){
	// ztree
	var setting = {
		check : {
			enable : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			onRightClick: OnRightClick,
			onClick : getCheckedNodes
		}
	};
	 zTree = $.fn.zTree.init($("#assetTree"), setting, treeArr);
	 zTree.expandAll(true);
	 rMenu=$('#rMenu');
	
	//右键
	function OnRightClick(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			getCheckedNodes();
			if(treeNode.level=="0"){
				showRMenu("node_parent", event.clientX, event.clientY);
			}else if(treeNode.level=="1"){
				showRMenu("node_child", event.clientX, event.clientY);
			}
			
			
		}
	}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			//$("#m_del").hide();
		} else if(type=="node_parent") {
			$("#m_add").show();
			//$("#m_del").show();
		}else if(type=="node_child") {
			$("#m_add").hide();
			//$("#m_del").show();
		}

        y += document.body.scrollTop;
        x += document.body.scrollLeft;
        rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

		$("body").bind("mousedown", onBodyMouseDown);
	}
	
	
	
	//选择节点
	function getCheckedNodes() {
		
		var selectedNodes = zTree.getSelectedNodes();
		var level=selectedNodes[0].level;
		var id=selectedNodes[0].id;
		if(level=="0"){
			//$("#index-details").load(ctx + "/scheme/kpiScheme/formDap?id=" + id, {});
			$('#indexDetailsIframe').attr('src',ctx + "/scheme/kpiScheme/formDap?id=" + id)
		}else if(level=="1"){
			//$("#index-details").load(ctx + "/scheme/kpiSchemeKpi/formDap?id=" + id, {});
			$('#indexDetailsIframe').attr('src',ctx + "/scheme/kpiSchemeKpi/formDap?id=" + id)
		}
	
	}

})
function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
 //增加
	function  addTreeNode(){
	   hideRMenu();
		var selectedNodes = zTree.getSelectedNodes();
		var len=selectedNodes.length;
		if(len==0){
			//$("#index-details").load(ctx + "/scheme/kpiScheme/formDap" , {});
			$('#indexDetailsIframe').attr('src',ctx + "/scheme/kpiScheme/formDap")
		}else if(len>0){
			var id=selectedNodes[0].id;
		//	$("#index-details").load(ctx + "/scheme/kpiSchemeKpi/formDap?sI=" + id, {});
			$('#indexDetailsIframe').attr('src',ctx + "/scheme/kpiSchemeKpi/formDap?sI=" + id)
		}
	}
//删除
function removeTreeNode(){
	hideRMenu();
	var selectedNodes = zTree.getSelectedNodes();
	var level=selectedNodes[0].level;
	var id=selectedNodes[0].id;
	if(level=="0"){
		if (selectedNodes[0].children && selectedNodes[0].children.length > 0) {
	
			top.$.jBox.confirm("如果删除父级将连同子级一起删掉，确认删除？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$.post(ctx + '/scheme/kpiScheme/delete?id='+id,function(){
						zTree.removeNode(selectedNodes[0]);
						top.$.jBox.tip('删除成功');
						$('#indexDetailsIframe').attr('src','')
					})

				} else {
					 
				}
			}, {
				buttonsFocus : 1
			});
			
		} else {
			top.$.jBox.confirm("确认删除？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$.post(ctx + '/scheme/kpiScheme/delete?id='+id, function(){
						zTree.removeNode(selectedNodes[0]);
						top.$.jBox.tip('删除成功');
						$('#indexDetailsIframe').attr('src','')
					})

				} else {
					 
				}
			}, {
				buttonsFocus : 1
			});
			
		}
		
	}else if(level=="1"){
		if (selectedNodes[0].children && selectedNodes[0].children.length > 0) {
			top.$.jBox.confirm("如果删除父级将连同子级一起删掉，确认删除？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$.post(ctx + '/scheme/kpiSchemeKpi/delete?id='+id, function(){
						zTree.removeNode(selectedNodes[0]);
						top.$.jBox.tip('删除成功');
						$('#indexDetailsIframe').attr('src','')
					})

				} else {
					 
				}
			}, {
				buttonsFocus : 1
			});
		
		} else {
			top.$.jBox.confirm("确认删除？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$.post(ctx + '/scheme/kpiSchemeKpi/delete?id='+id, function(){
						zTree.removeNode(selectedNodes[0]);
						top.$.jBox.tip('删除成功');
						$('#indexDetailsIframe').attr('src','')
					})

				} else {
					 
				}
			}, {
				buttonsFocus : 1
			});
			
		}
	}
}
