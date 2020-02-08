/**
 * 搜索树，显示并展示
 * 
 * @param treeId
 * @param searchConditionId
 *            文本框的id
 */
 function filter(treeId, searchConditionId){
 	searchByFlag_ztree(treeId, searchConditionId, '');
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
 function searchByFlag_ztree(treeId, searchConditionId, flag){
    // <1>.搜索条件
    var searchCondition = $('#' + searchConditionId).val();
    // <2>.得到模糊匹配搜索条件的节点数组集合
    var highlightNodes = new Array();
    if (searchCondition != "") {
    	var treeObj = $.fn.zTree.getZTreeObj(treeId);
    	highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
    }
    // <3>.显示并展示【指定节点s】
    highlightAndExpand_ztree(treeId, highlightNodes, flag);
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
 function highlightAndExpand_ztree(treeId, highlightNodes, flag){
 	var treeObj = $.fn.zTree.getZTreeObj(treeId);
    // <1>. 先把全部节点更新为普通样式
    var treeNodes = treeObj.transformToArray(treeObj.getNodes());
    for (var i = 0; i < treeNodes.length; i++) {
    	treeNodes[i].highlight = false;
    	treeObj.updateNode(treeNodes[i]);
    }
    // <2>.把指定节点的样式更新为高亮显示，并展开
    if (highlightNodes != null) {
    	var nodesTwo =[];
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
                    if(parentNode != null && parentNode != ""){

                    	nodesTwo.push(parentNode);
                    }
                    if(parentNodes != null && parentNodes != ""){

                    	for( var j = 0;j < parentNodes.length; j++){

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
                if(parentNode != null && parentNode != ""){

                	nodesTwo.push(parentNode);
                }
                if(parentNodes != null && parentNodes != ""){

                	for( var k = 0;k < parentNodes.length; k++){

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
}

var nodes =[];
/**
 * 递归得到指定节点的父节点的父节点....直到根节点
 */
 function getParentNodes_ztree(treeId, node){
 	if (node != null) {
 		var treeObj = $.fn.zTree.getZTreeObj(treeId);
 		var parentNode = node.getParentNode();
 		if(parentNode != null && parentNode !=""){

 			nodes.push(parentNode);
 		}
 		getParentNodes_ztree(treeId, parentNode);
 		return nodes;
 	} else {
 		return nodes;
 	}
 } 