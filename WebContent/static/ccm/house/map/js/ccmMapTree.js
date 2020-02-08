$(function() {
	// tab 转换页 js
	$('.left-tab>ul>li').click(
		function() {
			var num = $(".left-tab ul li").index(this);
			$(this).addClass('active').siblings().removeClass('active')
			$(".left-tab-center>ul>li").eq(num).addClass('active')
			.siblings().removeClass('active');
		});
	// ztree
	var setting = {
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {"Y":"","N":""} 
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		view: {
			fontCss: getFont,
			nameIsHTML: true
		},
		callback : {
			onClick : getSelectedNodes, //点击节点触发事件
			onCheck : getCheckedNodes   //点击checkbox触发事件
		}
	};
	// 设置字体颜色
	function getFont(treeId, node) {
		return node.point ?{'color':'black'} : {'color':'#aeaebb !important'};
	}

	// 点击节点触发事件
	function getSelectedNodes() {
		var checked = "";
		var zTree = $.fn.zTree.getZTreeObj("assetTree");
		var selectedNodes = zTree.getSelectedNodes();
		var type=selectedNodes[0].pointType;
		var areaPoint=selectedNodes[0].areaPoint;
		var areaMap=selectedNodes[0].areaMap;
		var id=selectedNodes[0].id;
		var treeTypeData=selectedNodes[0].type;
		Map.markInfo(id,treeTypeData,selectedNodes);
		if(areaPoint==""&&areaMap==""){
			top.$.jBox.tip('请标注添加坐标信息');
//			Map.removeLayer('vectorMark'); //pengjianqiang
		}else{
			var areaPointCenter = areaPoint.split(';')[0].split(',');
	        if (areaPointCenter.length == 2) {
	        	Map.goTo(areaPointCenter);
	        }
		}
	}
	
	// 点击checkbox触发事件
	function getCheckedNodes() {
		var checked = "";
		var zTree = $.fn.zTree.getZTreeObj("assetTree");
		var selectedNodes = zTree.getChangeCheckedNodes();
		
		//zTree的getChangeCheckedNodes()方法用于获取输入框勾选状态被改变的节点集合。
		//如果需要获取每次操作后全部被改变勾选状态的节点数据，请在每次勾选操作后，遍历所有被改变勾选状态的节点数据，让其 checkedOld = checked 就可以了。
		for (var i=0, l=selectedNodes.length; i<l; i++) {
			selectedNodes[i].checkedOld = selectedNodes[i].checked;
		}
		
		var type=selectedNodes[0].pointType;
		var areaPoint=selectedNodes[0].areaPoint;
		var areaMap=selectedNodes[0].areaMap;
		var id=selectedNodes[0].id;
		var treeTypeData=selectedNodes[0].type;
		
		if (selectedNodes[0].checked) {//该节点未选中时，点击完变为选中状态
			Map.markInfo(id,treeTypeData,selectedNodes);
			if(areaPoint==""&&areaMap==""){
				top.$.jBox.tip('请标注添加坐标信息');
//				Map.removeLayer('vectorMark'); //pengjianqiang
			}else{
				if(type=='0'){
					type='Point';
					
				}else if(type=='1'){
					type='Polygon';
				}else if(type=='2'){
					type='LineString';
				}
				Map.addGraphicalWithId(type);
				Map.removeLayer('drawMarkVector');// 删除刚才标绘的图层
			}
		} else {//取消选中状态
			Map.removeLayer(id);
		}
		
	}
	
	// 当前的选中发生变化时
	var p1='';
	$('#treeType').change(function(){  
		　　　 p1=$(this).children('option:selected').val(); 
		start1(p1)
	});	
	
	
	// 启动 默认 全部数据
	start1();
	
	// 查询所有树
	function start1(treeType) {
		if("appEfence"==treeType){
			$.getJSON(ctx + '/tree/ccmTree/treeDataNewApp', function(data) {
				// 对于icon 添加照片
				for(x in data){
					data[x].icon = typeTree(data[x]["type"]);
				}
				var tree = $.fn.zTree.init($("#assetTree"), setting, data);
				tree.expandAll(true);//全展开
			});
		}else{
			$.getJSON(ctx + '/tree/ccmTree/treeDataNew', {
				type : treeType||""
			}, function(data) {
				// 对于icon 添加照片
				for(x in data){
					data[x].icon = typeTree(data[x]["type"]);
				}
				var tree = $.fn.zTree.init($("#assetTree"), setting, data);
				 // 展开3级树
				 closeTree(tree);
				});
		}
	}
	// 展开树
	function expandTree(leftTree) {
		var tree = leftTree;
		tree.expandAll(tree);
	}
// 收起树：只展开根节点下的一级节点
function closeTree(leftTree ) {
	var tree =leftTree;
        // 获取 zTree 的全部节点数据将节点数据转换为简单 Array 格式
        var nodes = tree.transformToArray(tree.getNodes());
        for(var i=0;i<nodes.length;i++){
        	if(nodes[i].level == 3){
        		// console.log(nodes[i].name);
                // 根节点展开
                tree.expandNode(nodes[i],true,true,false)
            }else{
            	tree.expandNode(nodes[i],false,true,false)
            }
        }
    }
    
    // 搜索
    $('#areaButton').click(function(){
    	filter('assetTree','secuPlace')
    })
    // 刷新
    function refreshTree(){
    	start1(p1);
    }

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
        if(searchCondition == ""){
        	refreshTree();
        } else {
            // <2>.得到模糊匹配搜索条件的节点数组集合
            var highlightNodes = new Array();
            if (searchCondition != "") {
            	var treeObj = $.fn.zTree.getZTreeObj(treeId);
            	highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
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
	 
	 function typeTree(type){
// if(type=="communityGrid")
// {
// return ctxStatic+"";
// }
//if(type=="area1"){
//	return ctxStatic+"/modules/map/images/tree_shequ.png";
//}
if(type=="area2"){
	return ctxStatic+"/modules/map/images/tree_shequ.png";
}
// if(type=="area3"){
// return ctxStatic+"/modules/map/images/tree_grid.png";
// }
if(type=="area5"){
	return ctxStatic+"/modules/map/images/tree_shequ.png";
}

if(type=="area6"){
	return ctxStatic+"/modules/map/images/tree_diqu.png";
}

if(type=="area7"){
	return ctxStatic+"/modules/map/images/tree_shequ.png";
}

if(type=="commonality"){
	return ctxStatic+"/modules/map/images/tree_shequ.png";
}
if(type=="grid"){
	return ctxStatic+"/modules/map/images/tree_grid.png";
}
if(type=="build"){
	return ctxStatic+"/modules/map/images/tree_shequ.png";
}
if(type=="npse"){
	return ctxStatic+"/modules/map/images/tree_shequ.png";
}
if(type=="school"){
	return ctxStatic+"/modules/map/images/tree_schools.png";
}
if(type=="vccmorg"){
	return ctxStatic+"/modules/map/images/tree_zhian.png";
}
// citycomponents
if(type=="citycomponents"){
	return ctxStatic+"/modules/map/images/tree_tushuguan.png";
}
// land
if(type=="land"){
	return ctxStatic+"/modules/map/images/tree_grid.png";
}
// camera
if(type=="camera"){
	return ctxStatic+"/modules/map/images/tree_xiangji.png";
}
//appEfence
if(type=="appEfence"){
	return ctxStatic+"/modules/map/images/tree_app.png";
}


}

})
