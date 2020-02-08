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
		view : {
			fontCss : getFont,
			nameIsHTML : true,
			addDiyDom : collectDiyDom
			
		},
		
		callback : {
			onClick : getClickedNodes,
			onCheck : getCheckedNodes
			
			

		}
	};

	// 设置字体颜色'color' : '#aeaebb'
	function getFont(treeId, node) {
		return node.point ? {
			'color' : 'black'
		} : {
			'color' : 'black'
		};
	}

	
	function showLog(str,start) {
		top.$.jBox.tip(str, start, {
            persistent : true,
            opacity : 0
       });
	}

	

	function collectDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId);
		
		if ($("#diyBtn_" + treeNode.tId).length > 0)
			return;
		if (treeNode.level == 0) {
			var editStr = "&nbsp;<span class='diyBtn1' id='addBtn' title='新增' onfocus='this.blur();'><i class='icon-plus'></i></span>&nbsp;";					
			$("#collectZtree").prepend(editStr);
			
			$("#addBtn").on("click",function() {
				
				 top.$.jBox('<br><form  class="form-search" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="addInput" class="input-medium"   type="text" style="width:229px"/>&nbsp;&nbsp;&nbsp;　'						
						 +'</form>', 
								 {title:"创建收藏夹",
						          buttons:{"保存":'ok',"关闭":true}, 
						          buttonsFocus: 0, 
						          submit: function (v, h, f) { 
						        	  if(v=="ok"){
						        		  
						        		 var val= h[0].children[1][0].value;
						        		  if(val != null && val != ""){
						        			
						        		  $.post('' + ctx + '/tree/ccmMapCollectZtree/saveCollect',{						  										
						  					"name":val								
						  					}, function(data) {
						  						// alert(data)
						  						if(data != null && data != ""){
						  					    	   showLog(data,"success")	
						  					    	 refreshTreecollect();
						  					       }else{
						  					    	   showLog(data,"error")	
						  					       }
						  				   });			  				
						        		  }else{
						        			   showLog("收藏夹名称不能为空","error")
						        		  }
						        	  }
						        	  return true;
						         },
		                       });
				});
			
		}else if (treeNode.level == 1){
			var editStr = "&nbsp;<span class='diyBtn1' id='editBtn_"
				+ treeNode.tId
				+ "' title='编辑' onfocus='this.blur();'><i class='icon-pencil'></i></span>&nbsp;&nbsp;"
				+"<span class='diyBtn1' id='delBtn_"
				+ treeNode.tId
				+ "' title='删除' onfocus='this.blur();'><i class='icon-trash'></i></span>";					
		     aObj.append(editStr);		
		}else{
			var editStr = "&nbsp;<span class='diyBtn1' id='delBtn_"
				+ treeNode.tId
				+ "' title='删除' onfocus='this.blur();'><i class='icon-trash'></i></span>";					
		     aObj.append(editStr);
		}
		
		var editbtn = $("#editBtn_" + treeNode.tId);
		var delbtn = $("#delBtn_" + treeNode.tId);		
			  // addbtn.hide();
			   editbtn.hide();
			   delbtn.hide();
		
		
		  aObj.hover(function(){
			  //addbtn.show();
			   editbtn.show();
			   delbtn.show();		  
		  },function(){
			  //addbtn.hide();
			   editbtn.hide();
			   delbtn.hide();			  
		  })
				
		  //编辑事件
		  editbtn.on("click",function() {
				
			  top.$.jBox('<br><form  class="form-search" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="addInput" class="input-medium" value="'+treeNode.name+'"  type="text" style="width:229px"/>&nbsp;&nbsp;&nbsp;　'						
				 +'</form>', 
						 {title:"编辑收藏夹",
				          buttons:{"保存":'ok',"关闭":true}, 
				          buttonsFocus: 0, 
				          submit: function (v, h, f) { 
				        	  if(v=="ok"){
				        		  var val= h[0].children[1][0].value;
				        		  if(val != null && val != ""){
				        		  $.post('' + ctx + '/tree/ccmMapCollectZtree/saveCollect',{
				  					"id":treeNode.id,					
				  					"name":val								
				  					}, function(data) {
				  						// alert(data)
				  						if(data != null && data != ""){
				  					    	   showLog(data,"success")	
				  					    	  refreshTreecollect();
				  					       }else{
				  					    	   showLog(data,"error")	
				  					       }
				  				   });	
				  				    return true;			  				
				        		  }else{
				        			   showLog("收藏夹名称不能为空","error")
				        		  }
				        	  }
				        	 
				         },
                       });
			});
		  
		  //删除事件
		  delbtn.on("click",function() {
			  if(!treeNode.isParent ){
			  top.$.jBox.confirm('确认删除该节点吗？', "确认删除", function(v, h, f) {
					if (v == "ok") {
						$.get('' + ctx + '/tree/ccmMapCollectZtree/del?id='+treeNode.id, function(data) {
							       if(data!=null&&data!=""){
							    	   showLog(data,"success")	 
							       }else{
							    	   showLog(data,"error")	
							       }
									 refreshTreecollect();
						});
					}
				}, {
					buttonsFocus : 0
				});
			  }else{
				  top.$.jBox.tip("该节点下有子节点，不可删除！", "error", {
	                  persistent : true,
	                  opacity : 0
                 }); 
			  }
		   });

		
		
		
		var spantxt = $("#" + treeNode.tId + "_span").html();
		if (spantxt.length > 8) {
			spantxt = spantxt.substring(0, 8) + "...";
			$("#" + treeNode.tId + "_span").html(spantxt);
		}

	}
	// 获取选中节点

	function getClickedNodes(e,treeId, treeNode) {
      
		var checked = "";
		var zTree = $.fn.zTree.getZTreeObj("collectZtree");
		var selectedNodes = zTree.getSelectedNodes();
		var type = selectedNodes[0].pointType;
		var areaPoint = selectedNodes[0].areaPoint;
		var areaMap = selectedNodes[0].areaMap;
		var id = selectedNodes[0].id;
		var treeTypeData = selectedNodes[0].type;
		var level = selectedNodes[0].level;
		if(!treeNode.isParent && !treeNode.checked && treeNode.type.indexOf("area") < 0 ){
			zTree.checkNode(treeNode, true, true);
			getCheckedNodes()
			}
		if(level>1){
		Map.markInfo(id, treeTypeData, selectedNodes);
		if (areaPoint == "" && areaMap == "") {
			top.$.jBox.tip('请标注添加坐标信息');
			Map.removeLayer('vectorMark');
		} else {

			Map.map.getView().setZoom(18);

			type = 'Point';
			Map.addGraphicalFirstPage(type)
		}
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
		Map.clearOverlays()
		// alert('公共机构清除')
		var landsids = "";
		var partsids = "";
		var videosids = "";
		var publicPlaceids = "";
		var schoolids = "";
		var npseids = "";

		var checkedNodes = zTreeObjCollect.getCheckedNodes(true);
		for (var i = 0; i < checkedNodes.length; i++) {
			var nodes = checkedNodes[i];
			if (nodes.level >= 2 && nodes.type != 'area7') {
				if (nodes.type == 'land') {
					landsids += "\'" + nodes.zId + "\',";
				} else if (nodes.type == 'citycomponents') {
					partsids += "\'" + nodes.zId + "\',";
				} else if (nodes.type == 'camera') {
					videosids += "\'" + nodes.zId + "\',";
				} else if (nodes.type == 'commonality') {
					publicPlaceids += "\'" + nodes.zId + "\',";
				} else if (nodes.type == 'school') {
					schoolids += "\'" + nodes.zId + "\',";
				} else if (nodes.type == 'npse') {
					npseids += "\'" + nodes.zId + "\',";
				}
			}
		}

		if (landsids != "") {
			// alert('土地加载')
			Map.removeLayer('lands');
			$.getJSON('' + ctx + '/sys/map/landMap?ids=' + landsids + "11",
					function(data) {
						Map.addJSON1([ {
							'type' : 'lands',
							'data' : data,
							'isShow' : true
						} ])
					});
		}
		if (partsids != "") {
			// alert('城市部件加载')
			Map.removeLayer('parts');
			$.getJSON('' + ctx + '/sys/map/cityComponentsMap?type=' + ''
					+ '&ids=' + partsids + "11", function(data) {
				Map.addJSON1([ {
					'type' : 'parts',
					'data' : data,
					'isShow' : true
				} ])
			})
		}
		if (videosids != "") {
			// alert('视频监控加载')
			Map.removeLayer('videos');
			$.getJSON('' + ctx + '/sys/map/deviceiveMap?ids=' + videosids
					+ "11", function(data) {
				Map.addJSON1([ {
					'type' : 'videos',
					'data' : data,
					'isShow' : true
				} ])
			})
		}
		if (publicPlaceids != "") {
			// alert('公共机构加载')
			Map.removeLayer('publicPlace');
			Map.clearOverlays()
			$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type='
					+ '12345678' + '&ids=' + publicPlaceids + "11", function(
					data) {
				Map.addJSON1([ {
					'type' : 'publicPlace',
					'data' : data,
					'isShow' : true
				} ])
			})
		}
		if (schoolids != "") {
			// alert('重点场所加载:学校')
			Map.removeLayer('schoolPlace');

			$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type=' + '32'
					+ '&ids=' + schoolids + "11", function(data) {
				Map.addJSON1([ {
					'type' : 'schoolPlace',
					'data' : data,
					'isShow' : true
				} ])

			})
		}
		if (npseids != "") {
			// alert('重点及风险单位')
			Map.removeLayer('keyPlace');
			$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type=' + '31'
					+ '&ids=' + npseids + "11", function(data) {
				Map.addJSON1([ {
					'type' : 'keyPlace',
					'data' : data,
					'isShow' : true
				} ])

			})
		}
	}
    
	//tab页切换
	$(".nav-tabsLeft li").on("click",function(){
		$(".nav-tabsLeft li").removeClass("active")
		$(this).addClass("active")
		//alert($(this).attr('id'))
		if($(this).attr('id') == "resource"){
		   $("#resourceDiv").show();
		   $("#businessDiv").hide();
		   $("#collectDiv").hide();
		}else if($(this).attr('id') == "business"){
		   $("#resourceDiv").hide();
		   $("#businessDiv").show();
		   $("#collectDiv").hide();			
		}else if($(this).attr('id') == "collect"){
		   $("#resourceDiv").hide();
		   $("#businessDiv").hide();
		   $("#collectDiv").show();	
		   refreshTreecollect();
		   
		}
	})
		
	
	collectZTree()
	//刷新树
  function refreshTreecollect(){
		
		collectZTree()
    }
	var zTreeObjCollect
  function collectZTree(){
	// 查询所有树
	$.ajaxSetup({ cache: false }); 
	$.getJSON(ctx + '/tree/ccmMapCollectZtree/treeDataNew', function(data) {
		// 对于icon 添加照片
		for (x in data) {
			data[x].icon = typeTree(data[x]["type"]);
		}
		data.push({
			id : "collect",
			name : "收藏",
			open : true,
			level : 1,
		// checked : true
		});
	
		var treeCollect = $.fn.zTree.init($("#collectZtree"), setting, data);
		zTreeObjCollect = $.fn.zTree.getZTreeObj("collectZtree");
		treeCollect.expandAll(treeCollect)
		// 获取根节点
		node = treeCollect.getNodes(),

		// 遍历所有节点
		nodes = treeCollect.transformToArray(node);
		if (nodes.length > 0) {

			for (var i = 0; i < nodes.length; i++) {
				// 删除没有坐标信息的数据
				if (nodes[i].level > 2) {
					var areaPoint = nodes[i].areaPoint;
					var areaMap = nodes[i].areaMap;
					if (areaPoint == "" && areaMap == "") {
						treeCollect.removeNode(nodes[i]);
					}
				}

			}

		}
		$.ajaxSetup({ cache: false }); 
	});
  }
	// 收起树：只展开根节点下的一级节点
	function closeTree(leftTree) {
		var tree = leftTree;
		// 获取 zTree 的全部节点数据将节点数据转换为简单 Array 格式
		var nodes = tree.transformToArray(tree.getNodes());
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].level == 2) {
				console.log(nodes[i].name);
				// 根节点展开
				tree.expandNode(nodes[i], true, true, false)
			} else {
				tree.expandNode(nodes[i], false, true, false)
			}
		}
	}

	function typeTree(type) {
		// if(type=="communityGrid")
		// {
		// return ctxStatic+"";
		// }
		// if(type=="area1"){
		// return ctxStatic+"/modules/map/images/tree_shequ.png";
		// }
		if (type == "area2") {
			return ctxStatic + "/modules/map/images/tree_shequ.png";
		}
		// if(type=="area3"){
		// return ctxStatic+"/modules/map/images/tree_grid.png";
		// }
		if (type == "area5") {
			return ctxStatic + "/modules/map/images/tree_shequ.png";
		}

		if (type == "area6") {
			return ctxStatic + "/modules/map/images/tree_diqu.png";
		}

		if (type == "area7") {
			return ctxStatic + "/modules/map/images/tree_shequ.png";
		}

		if (type == "commonality") {
			return ctxStatic + "/modules/map/images/tree_shequ.png";
		}
		if (type == "grid") {
			return ctxStatic + "/modules/map/images/tree_grid.png";
		}
		if (type == "build") {
			return ctxStatic + "/modules/map/images/tree_shequ.png";
		}
		if (type == "npse") {
			return ctxStatic + "/modules/map/images/tree_shequ.png";
		}
		if (type == "school") {
			return ctxStatic + "/modules/map/images/tree_schools.png";
		}
		// citycomponents
		if (type == "citycomponents") {
			return ctxStatic + "/modules/map/images/tree_tushuguan.png";
		}
		// land
		if (type == "land") {
			return ctxStatic + "/modules/map/images/tree_grid.png";
		}
		// camera
		if (type == "camera") {
			return ctxStatic + "/modules/map/images/tree_xiangji.png";
		}
		// appEfence
		if (type == "appEfence") {
			return ctxStatic + "/modules/map/images/tree_app.png";
		}
	}

});
