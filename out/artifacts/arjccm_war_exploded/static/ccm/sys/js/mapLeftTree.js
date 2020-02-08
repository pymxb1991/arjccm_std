var idArrjingwushi2=[];
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
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		view: {		
			
			addDiyDom: addDiyDom,
			addHoverDom:collectDiyDom,
		},		
		callback : {
			onClick : getClickedNodes,
			onCheck : getCheckedNodes
			
		}
	};
	
	
	
	
	
	
	function addDiyDom(treeId, treeNode) {
			var spantxt=$("#" + treeNode.tId + "_span").html();
			if(spantxt.length>8){
				spantxt=spantxt.substring(0,8)+"...";
				$("#" + treeNode.tId + "_span").html(spantxt);
			}
	}
	function collectDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId );
		if ($("#diyBtn_"+treeNode.id).length>0) return;
		  if(!treeNode.isParent){
			var editStr = "<span class='diyBtn1' id='diyBtn_" + treeNode.id 
				+ "' title='收藏' onfocus='this.blur();'><i class='icon-folder-open'></i></span>" +
						"<div class='detail-box collectType' id='collect_" + treeNode.id+"' style='display:none;'>"+	
						 "<div id='collect_" + treeNode.id+"_div'> </div>"+
						 " <div class='collectTypeBtn'>" +
						 "<input id='collectSubmit" + treeNode.id+"' class='btn btn-xs btn-primary' type='button' value='确定'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						 "<input id='collectClose" + treeNode.id+"' class='btn btn-xs ' type='button' value='取消'>"+
						 "</div>"+
			            "</div>";
			aObj.append(editStr);
			var btn = $("#diyBtn_"+treeNode.id);
			if (btn) btn.bind("click", function(){
				 $("#collect_"+treeNode.id +"_div").html("");
				$(".collectType").hide();
				 var radio="";
				$.getJSON('' + ctx + '/tree/ccmMapCollectZtree/radio', function(data) {
					for ( var one in data) {
						if(one == 0){
							radio+= "<input class='radio' type='radio' value='"+data[one].id+"' name='collectRadio_" + treeNode.id+"' checked='checked' /><span title='"+data[one].name+"' style='color:#08c;'>"+data[one].name+"</span><br>";	
						}else{
							radio+= "<input class='radio' type='radio' value='"+data[one].id+"' name='collectRadio_" + treeNode.id+"'  /><span title='"+data[one].name+"' style='color:#08c;'>"+data[one].name+"</span><br>";	
						}
					}     
					   
						 $("#collect_"+treeNode.id +"_div").append(radio);
					});
				$("#collect_"+treeNode.id).show();
		    });
			$("#collectSubmit" + treeNode.id).on("click",function(){
				treeNode.pId=$("input[name='collectRadio_"+treeNode.id+"']:checked").val();
				$.post('' + ctx + '/tree/ccmMapCollectZtree/save',{
					"id":treeNode.id ,
					"parentId":treeNode.pId ,
					"type":treeNode.type ,
					"name":treeNode.name ,
					"areaPoint":treeNode.areaPoint,
					"areaMap":treeNode.areaMap 					
					}, function(data) {
						// alert(data)
					top.$.jBox.tip(data, "success", {
		                  persistent : true,
		                  opacity : 0
	                 });
					
					});			  				  
				  $("#collect_"+treeNode.id).hide();
			});
			$("#collectClose" + treeNode.id).on("click",function(){
				$("#collect_"+treeNode.id).hide();
			});
		  }
		  $("#diyBtn_" + treeNode.id ).show();
		  aObj.hover(function(){
			  $("#diyBtn_" + treeNode.id ).show();			  
		  },function(){
			  $("#diyBtn_" + treeNode.id ).hide();			  
		  })
		  
		  

	}
	// 获取选中节点

	function getClickedNodes(e,treeId, treeNode) {
		
		var checked = "";
		var zTree = $.fn.zTree.getZTreeObj("assetTree");
		var selectedNodes = zTree.getSelectedNodes();
		var type=selectedNodes[0].pointType;
		var areaPoint=selectedNodes[0].areaPoint;
		var areaMap=selectedNodes[0].areaMap;
		var id=selectedNodes[0].id;
		var treeTypeData=selectedNodes[0].type;
		var level=selectedNodes[0].level;
		var isParent=selectedNodes[0].isParent;
		if(!treeNode.isParent && !treeNode.checked && treeNode.type.indexOf("area") < 0 ){
		zTree.checkNode(treeNode, true, true);
		getCheckedNodes()
		}
		
		Map.markInfo(id,treeTypeData,selectedNodes);
		if(areaPoint==""&&areaMap==""){
			top.$.jBox.tip('请标注添加坐标信息');
			Map.removeLayer('vectorMark');
		}else{
			
			
			if(type=='0'){
				type='Point';
				Map.map.getView().setZoom(18);
			}else if(type=='1'){
				type='Polygon';
				if(treeTypeData=="area7"){
					Map.map.getView().setZoom(17);
					Map.removeLayer('grids');
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2&ids='+"\'"+selectedNodes[0].id+"\'", function(data) {
						Map.addJSON1([ {
							'type' : 'grids',
							'data' : data,
							'isShow' : gridFlag
						} ])
					});
				}
				if(treeTypeData=="area6"){
					Map.map.getView().setZoom(16);	
					Map.removeLayer('communitys');
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1&ids='+"\'"+selectedNodes[0].id+"\'", function(data) {
						Map.addJSON1([ {
							'type' : 'communitys',
							'data' : data,
							'isShow' : communityFlag
						} ])
					});
				}
				if(treeTypeData=="area5"){
					Map.removeLayer('streets');
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4&ids='+"\'"+selectedNodes[0].id+"\'", function(data) {
						Map.addJSON1([ {
							'type' : 'streets',
							'data' : data,
							'isShow' : streetFlag
						} ])
					});
					Map.map.getView().setZoom(14);	
				}
				else if(!isParent){
					Map.map.getView().setZoom(18);		
				}
			}else if(type=='2'){
				type='LineString';
				Map.map.getView().setZoom(17);
			}
			
			Map.addGraphicalFirstPage(type)
		}
	}


	// 获取选中节点
	
	function getCheckedNodes() {
		Map.removeLayer('lands');
		Map.removeLayer('parts');
		Map.removeLayer('videos');
		Map.removeLayer('publicPlace');
		Map.removeLayer('schoolPlace');
		Map.removeLayer('keyPlace');
		Map.removeLayer('builds');
		Map.removeLayer('streets');
		Map.removeLayer('communitys');
		Map.removeLayer('grids');
		Map.removeLayer('broadcast');
		Map.removeLayer('policeroom');
		Map.removeLayer('workstation');
		Map.clearOverlays()
		// alert('公共机构清除')
		var landsids = "";
		var partsids = "";
		var videosids = "";
		var publicPlaceids  = "";
		var schoolids  = "";
		var npseids  = "";
		var buildids  = "";
		var streetsids  = "";
		var communitysids  = "";
		var gridsids  = "";
		var vccmorgids= "";
		var broadcasts= "";
		var policeroom= "";
		var workstation= "";
		var checkedNodes = zTreeObj.getCheckedNodes(true);
		for (var i = 0; i < checkedNodes.length; i++) {
			     var nodes=checkedNodes[i];
			 if((!nodes.isParent && nodes.type.indexOf("area") < 0)||nodes.type=="area5"||nodes.type=="area6"||nodes.type=="area7" ){ 
				 if (nodes.type == 'land') {						
					 landsids+="\'"+nodes.id+"\',";
				   }else if(nodes.type == 'citycomponents'){
					   partsids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'camera'){
					   videosids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'commonality'){
					   publicPlaceids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'school'){
					   schoolids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'npse'){
					   npseids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'build'){
					   buildids +="\'"+nodes.id+"\',";
				   }
				 
				   else if(nodes.type == 'area5'){
					   streetsids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'area6'){
					   communitysids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'area7'){
					   gridsids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'vccmorg'){
					  vccmorgids +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'broadcast'){
					 broadcasts +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'policeroom'){
					 policeroom +="\'"+nodes.id+"\',";
				   }
				   else if(nodes.type == 'workstation'){
					 workstation +="\'"+nodes.id+"\',";
				   }
		      }/*else if(nodes.type=="area5"){		    	   
		    	  $.getJSON('' + ctx + '/sys/map/chooseAreaMap?AreaId='+nodes.id, function(data) {
		    			 if(data=="200"){
		    				 alert(200)
		    			 }
					}); 
		      }	*/
		 }
		var checkedLeftNodes = zTreeObjLeft.getCheckedNodes(true);
		if(streetsids != ""){	
			var isstreets=false;
				for (var i = 0; i < checkedLeftNodes.length; i++) {
					if(checkedLeftNodes[i].id=="street"){
						isstreets=true;
					}
				 }				
			   if(isstreets){
				// alert('街道')
				Map.removeLayer('streets');
				$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4&ids='+streetsids+"11", function(data) {
					Map.addJSON1([ {
						'type' : 'streets',
						'data' : data,
						'isShow' : streetFlag
					} ])
				});
			   }
			 }
		
		
		
		if(communitysids != ""){	
			var iscommunitys=false;
				for (var i = 0; i < checkedLeftNodes.length; i++) {
					if(checkedLeftNodes[i].id=="community"){
						iscommunitys=true;
					}
				 }				
			   if(iscommunitys){
				// alert('街道')
				Map.removeLayer('communitys');
				$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1&ids='+communitysids+"11", function(data) {
					Map.addJSON1([ {
						'type' : 'communitys',
						'data' : data,
						'isShow' : communityFlag
					} ])
				});
			   }
			 }
		
		
		if(gridsids != ""){	
			var isgrids=false;
				for (var i = 0; i < checkedLeftNodes.length; i++) {
					if(checkedLeftNodes[i].id=="grid"){
						isgrids=true;
					}
				 }				
			   if(isgrids){
				// alert('街道')
				Map.removeLayer('grids');
				$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2&ids='+gridsids+"11", function(data) {
					Map.addJSON1([ {
						'type' : 'grids',
						'data' : data,
						'isShow' : gridFlag
					} ])
				});
			   }
			 }
		
		if(buildids != ""){	
		var isbuild=false;
			for (var i = 0; i < checkedLeftNodes.length; i++) {
				if(checkedLeftNodes[i].id=="build"){
					isbuild=true;
				}
			 }				
		   if(isbuild){
			// alert('楼栋')
			Map.removeLayer('builds');
			$.post('' + ctx + '/sys/map/buildmanageMap',{
					"ids":buildids+"11",																		
  					} , function(data) {
				Map.addJSON1([ {
					'type' : 'builds',
					'data' : data,
					'isShow' : buildFlag
				} ])
			});
		   }
		 }
		
		if(landsids != ""){			
			// alert('土地加载')
			Map.removeLayer('lands');
			$.getJSON('' + ctx + '/sys/map/landMap?ids='+landsids+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'lands',
					'data' : data,
					'isShow' : landsFlag
				} ])
			});
		 }
		if (partsids != "") {
			// alert('城市部件加载')
			Map.removeLayer('parts');
			$.getJSON('' + ctx + '/sys/map/cityComponentsMap?type='
					+ '' + '&ids='+partsids+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'parts',
					'data' : data,
					'isShow' : partsFlag 
				} ])
			})
		}
		if (videosids!= "") {
			// alert('视频监控加载')
			Map.removeLayer('videos');
			$.getJSON('' + ctx + '/sys/map/deviceiveMap?ids='+videosids+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'videos',
					'data' : data,
					'isShow' : videoFlag 
				} ])
			})
		}
		if (broadcasts!= "") {
			// alert('广播站加载')
			Map.removeLayer('broadcast');
			$.getJSON('' + ctx + '/sys/map/deviceBroadcastMap?ids='+broadcasts+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'broadcast',
					'data' : data,
					'isShow' : broadcastFlag
				} ])
			})
		}
		if (policeroom!= "") {
			// alert('警务室加载')
			Map.removeLayer('policeroom');
			$.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=10&ids='+policeroom+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'policeroom',
					'data' : data,
					'isShow' : policeroomFlag
				} ])
			})
		}
		if (workstation!= "") {
			// alert('工作站加载')
			Map.removeLayer('workstation');
			$.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=11&ids='+workstation+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'workstation',
					'data' : data,
					'isShow' : workstationFlag
				} ])
			})
		}

		if (publicPlaceids!= "") {
			// alert('公共机构加载')
			Map.removeLayer('publicPlace');
			Map.clearOverlays()
			$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type='
					 + '12345678'+ '&ids='+publicPlaceids+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'publicPlace',
					'data' : data,
					'isShow' : publicPlaceFlag 
				} ])
			})
		}
		
		if (schoolids!= "") {		
			// alert('重点场所加载:学校')
			Map.removeLayer('schoolPlace');
			
			$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type='
					+ '32' + '&ids='+schoolids+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'schoolPlace',
					'data' : data,
					'isShow' : schoolPlaceFlag  
				} ])

			})
		}
		if (npseids!= "") {		
			// alert('重点及风险单位')
			Map.removeLayer('keyPlace');
			$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type='
					+ '31' + '&ids='+npseids+"11", function(data) {
				Map.addJSON1([ {
					'type' : 'keyPlace',
					'data' : data,
					'isShow' : keyPlaceFlag
				} ])

			})
		}
		
		if (vccmorgids!= "") {
			Map.removeLayer('jingwushi');  
				$.getJSON('' + ctx + '/sys/map/findMapVCcmOrgType?type=sql'+ '&ids='+vccmorgids+"11", function(data) {
					var features=data.features;
					var len=features.length;
					idArrjingwushi2=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrjingwushi2.push(features[i].id);
						}
					}
					Map.addJSON3([ {
						'type' : 'Shortcut',
						'id' : 'jingwushi',
						'data' : data,
						'isShow' : vccmorgFlag
					} ])
				})
				//$(_this).css('border', '1px solid #0e54a9');
		
		}else{
			$.each(idArrjingwushi2,function(index,val){
				Pubmap.removeOverlay(Map[''+val+'Overlay'])
			});
			//$(_this).css('border', '1px solid transparent');
			Map.removeLayer('jingwushi'); 
			Map.removeLayer('gongzuozhan');
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
	var zTreeObj
	// 查询所有树
	function start1(treeType) {
		if("appEfence"==treeType){
			$.getJSON(ctx + '/tree/ccmTree/treeDataNewApp', function(data) {
				// 对于icon 添加照片
				for(x in data){
					data[x].icon = typeTree(data[x]["type"]);
				}
				var tree = $.fn.zTree.init($("#assetTree"), setting, data);
				zTreeObj = $.fn.zTree.getZTreeObj("assetTree");
				tree.expandAll(true);// 全展开
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
				zTreeObj = $.fn.zTree.getZTreeObj("assetTree");					
				 closeTree(tree);				      
			
				});
		}
	}
	// 展开树
	function expandTree(leftTree) {
		var tree = leftTree;
		zTreeObj.expandAll(tree);
	}
// 收起树：只展开根节点下的一级节点
	function closeTree(treeObj) {		
		　　　var nodeList = treeObj.getNodes();　　　 //展开第一个根节点
		for(var i = 0; i < nodeList.length; i++) { //设置节点展开第二级节点
			treeObj.expandNode(nodeList[i], true, false, true);
			
			/*var nodespan = nodeList[i].children;
			for(var j = 0; j < nodespan.length; j++) { //设置节点展开第三级节点
				treeObj.expandNode(nodespan[j], true, false, true);
				
				var nodespan2 = nodespan[i].children;
				for(var k = 0; k < nodespan2.length; k++) { //设置节点展开第4级节点
					treeObj.expandNode(nodespan2[j], true, false, true);
					
					var nodespan3 = nodespan2[i].children;
					for(var n = 0; n < nodespan3.length; n++) { //设置节点展开第5级节点
						treeObj.expandNode(nodespan3[j], true, false, true);
					
					}
				}
			}*/
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
// if(type=="area1"){
// return ctxStatic+"/modules/map/images/tree_shequ.png";
// }
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
// appEfence
if(type=="appEfence"){
	return ctxStatic+"/modules/map/images/tree_app.png";
}
if(type=="vccmorg"){
	return ctxStatic+"/modules/map/images/tree_zhian.png";
}
}


});
