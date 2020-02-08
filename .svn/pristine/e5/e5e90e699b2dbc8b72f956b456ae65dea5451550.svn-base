// ztree
var teamType = "";

var setting = {
	check : {
		enable : true
	},
	async : {
		enable : true,
		url : ctx + "/org/ccmOrgTeam/treeData?teamType=" + teamType,
		autoParam : [ "id=officeId" ],
		dataFilter : function(treeId, parentNode, responseData) {
			if (responseData) {
				for (var i = 0; i < responseData.length; i++) {
					responseData[i].type = "user";
					
				}
			}
			return responseData;
		}
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	view : {
		fontCss : getFont,
		nameIsHTML : true
	/*
	 * , addDiyDom : collectDiyDom
	 */
	},

	callback : {
		onClick : getClickedNodes,
		onCheck : getCheckedNodes
	}
};




$(function() {
	start()
	$("#search").on('click', function() {

		filter('userTree', 'username')// treeId, 文本框的id
	})
	// 保存
	$("#btnSubmit").on('click', function() {
//		var id = $("#id").val()
		var groupname = $("#groupname").val()
		var userlistObject = $("#userlist  .userli")
		var userList = '';
		var avatar = 'WebContent/static/layimExtend/group/img/qunzutupian.jpg';
		var ccmUserGroup = {};
		for(var i = 0; i < userlistObject.length; i++){
			userList+=$(userlistObject[i]).attr("id").replace(/u_/g,"")+','
		}
//		ccmUserGroup.id = id;
		ccmUserGroup.groupname = groupname;
		ccmUserGroup.userList = userList;
		ccmUserGroup.avatar = avatar;
		$.getJSON(ctx + "/im/userGroup", ccmUserGroup, function(data) {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});

	})
	// 关闭
	$("#btnCancel").on('click', function() {
		
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	})

})

// 设置字体颜色
function getFont(treeId, node) {
	return node.point ? {
		'color' : 'black'
	} : {
		'color' : '#333'
	};
}

function showLog(str, start) {
	top.$.jBox.tip(str, start, {
		persistent : true,
		opacity : 0
	});
}
// 点击文字
function getClickedNodes(e, treeId, treeNode) {
	var checked = "";
	var zTree = $.fn.zTree.getZTreeObj("userTree");
	zTree.checkNode(treeNode, !treeNode.checked, true);
	getCheckedNodes()

}
// 选中多选框

function getCheckedNodes() {
	var usernumber = 0;
	$("#userlist").html("")
	var checkedNodes = zTreeObj.getCheckedNodes(true);
	for (var i = 0; i < checkedNodes.length; i++) {
		var nodes = checkedNodes[i];
		if (nodes.type != null && nodes.type == "user") {
			$("#userlist").append(
					' <li class="userli" id="' + nodes.id + '" ><span>'
							+ nodes.name + '</span> <i  class="icon-remove-circle icon-white iconclose"  ></i></li>');
			usernumber += 1;
		}
	}
	if (usernumber == 0) {
		$("#xinxi").html("请勾选需要添加的人")
	} else {
		$("#xinxi").html("已选择" + usernumber + "个人")
	}
	
	$(".userli i").on("click" ,function(){
		var order=$(this).parent().remove()
		var userlistObject = $("#userlist  .userli")
		var userList = [];
		 for(var i = 0; i < userlistObject.length; i++){
			userList.push($(userlistObject[i]).attr("id"))
		}
		treeHxIdFun(userList) 
	})

}

//回显选择的checkbox函数
function treeHxIdFun(obj) {
	 zTreeObj.checkAllNodes(false);
   zTreeObj.getCheckedNodes(false);
  
    for(var i = 0; i < obj.length; i++){
    	zTreeObj.checkNode(zTreeObj.getNodeByParam("id", obj[i]));
       zTreeObj.selectNode(zTreeObj.getNodeByParam("id", obj[i]));
    };
};

// 查询所有树
var zTreeObj = null;
function start() {

	$.getJSON(ctx + "/sys/office/treeData?type=3&&extId=&isAll=&module=&t="
			+ new Date().getTime(), function(data) {

		var tree = $.fn.zTree.init($("#userTree"), setting, data);
		zTreeObj = $.fn.zTree.getZTreeObj("userTree");
		// 默认展开一级节点
		var nodes = tree.getNodesByParam("level", 0);
		for (var i = 0; i < nodes.length; i++) {
			tree.expandNode(nodes[i], true, false, false);
		}

		// 异步加载子节点（加载用户）
		var nodesOne = tree.getNodesByParam("isParent", true);
		for (var j = 0; j < nodesOne.length; j++) {
			tree.reAsyncChildNodes(nodesOne[j], "!refresh", true);
		}

	});

}

/**
 * 搜索树，显示并展示
 * 
 * @param treeId
 * @param searchConditionId
 *            文本框的id
 */
function filter(treeId, searchConditionId) {
	searchByFlag_ztree(treeId, searchConditionId, '');
}
// 刷新
function refreshTree() {
	start();
}

/**
 * 搜索树，显示并展示
 * 
 * @param treeId
 * @param searchConditionId
 *            搜索条件Id
 * @param flag
 *            需要高亮显示的节点标识
 */
function searchByFlag_ztree(treeId, searchConditionId, flag) {
	// <1>.搜索条件

	var searchCondition = $('#' + searchConditionId).val();
	if (searchCondition == "") {
		refreshTree();
	} else {
		// <2>.得到模糊匹配搜索条件的节点数组集合
		var highlightNodes = new Array();
		if (searchCondition != "") {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			highlightNodes = treeObj.getNodesByParamFuzzy("name",
					searchCondition, null);
		}
		// <3>.显示并展示【指定节点s】
		highlightAndExpand_ztree(treeId, highlightNodes, flag);
	}
}

/**
 * 显示并展示
 * 
 * @param treeId
 * @param highlightNodes
 *            需要高亮显示的节点数组
 * @param flag
 *            需要高亮显示的节点标识
 */
function highlightAndExpand_ztree(treeId, highlightNodes, flag) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	// <1>. 先把全部节点更新为普通样式
	var treeNodes = treeObj.transformToArray(treeObj.getNodes());
	for (var i = 0; i < treeNodes.length; i++) {
		treeNodes[i].highlight = false;
		treeObj.updateNode(treeNodes[i]);
	}
	// <2>.把指定节点的样式更新为高亮显示，并展开
	if (highlightNodes != null) {
		var nodesTwo = [];
		for (var i = 0; i < highlightNodes.length; i++) {
			if (flag != null && flag != "") {
				if (highlightNodes[i].flag == flag) {
					// 显示节点，并展开
					highlightNodes[i].highlight = true;
					treeObj.updateNode(highlightNodes[i]);
					// 显示节点的父节点的父节点....直到根节点，并展示
					var parentNode = highlightNodes[i].getParentNode();
					var parentNodes = getParentNodes_ztree(treeId, parentNode);
					treeObj.expandNode(parentNodes, true, false, true);
					treeObj.expandNode(parentNode, true, false, true);
					if (parentNode != null && parentNode != "") {

						nodesTwo.push(parentNode);
					}
					if (parentNodes != null && parentNodes != "") {

						for (var j = 0; j < parentNodes.length; j++) {

							nodesTwo.push(parentNodes[j]);

						}
					}
				}
			} else {

				// 高亮显示节点，并展开
				highlightNodes[i].highlight = true;
				treeObj.updateNode(highlightNodes[i]);
				// 高亮显示节点的父节点的父节点....直到根节点，并展示
				var parentNode = highlightNodes[i].getParentNode();
				var parentNodes = getParentNodes_ztree(treeId, parentNode);
				treeObj.expandNode(parentNodes, true, false, true);
				treeObj.expandNode(parentNode, true, false, true);
				if (parentNode != null && parentNode != "") {

					nodesTwo.push(parentNode);
				}
				if (parentNodes != null && parentNodes != "") {

					for (var k = 0; k < parentNodes.length; k++) {

						nodesTwo.push(parentNodes[k]);

					}
				}
			}
		}

		// 隐藏所有节点
		treeObj.hideNodes(treeNodes);
		// 展示搜索到的节点
		treeObj.showNodes(nodesTwo);
		treeObj.showNodes(highlightNodes);
	}

	/**
	 * 递归得到指定节点的父节点的父节点....直到根节点
	 */
	function getParentNodes_ztree(treeId, node) {
		var ParentNodes = [];
		if (node != null) {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var parentNode = node.getParentNode();
			if (parentNode != null && parentNode != "") {

				ParentNodes.push(parentNode);
			}
			getParentNodes_ztree(treeId, parentNode);
			return ParentNodes;
		} else {
			return ParentNodes;
		}
	}
}