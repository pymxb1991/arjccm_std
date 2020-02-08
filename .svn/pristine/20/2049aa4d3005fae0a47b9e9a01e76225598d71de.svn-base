/**
 * V 1.0 2018-1-27 11:13:45 楼栋信息
 */
$(function() {
	$("#btnSubmit").on("click" ,function(){
		$("#searchForm").submit();
	})
	$("#btnExport").click(
		function() {
			top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					// 借用导出action
					$("#searchForm").attr("action",ctx + "/house/ccmHouseBuildmanage/export");
					$("#searchForm").submit();
					// 还原查询action 
					$("#searchForm").attr("action",ctx + "/house/ccmHouseBuildmanage/");
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
	
	$("#btnImport").click(function() {
		$.jBox($("#importBox").html(), {
			title : "导入数据",
			buttons : {
				"关闭" : true
			},
			bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
		});
	});
});
function sortNumber(a,b){
	  return a - b;
}
function descNumber(a,b){
	  return b - a;
}
buildingIdIdCancle='';
function house(buildingId,areaId){
	buildingIdIdCancle=buildingId;
    $.getJSON(ctx+'/house/ccmHouseBuildmanageUnit/findListByBuildmanageId?buildmanageId='+buildingId+'',function(data){
    	var residentialUnitArr=[];
   	 	var html='';
    	if(data.length>0){
    	 for(var i in data){
    		 var a=data[i].residentialUnit;
    		 if( $.inArray(a, residentialUnitArr)==-1&&a!='bottom'){
    			 residentialUnitArr.push(a)
    		 }
    	 }
    	 residentialUnitArr=residentialUnitArr.sort(sortNumber);
	     var residentialUnitLen=residentialUnitArr.length;
	     var widthU=300;
	     var widthB=widthU*residentialUnitLen;
    
		 html+='<div id="home">';
		 html+='<div class="homeLeft" style="width: 172px; height: 700px;float: left;overflow-y:auto ">';
		 html+='<ul id="roomTree" class="ztree"></ul>';
		 html+='</div>';
		 html+='<div class="homeRight" style="width: 1250px;  height: 700px;float: left; overflow-x:auto;">';
		 html+='<div style="margin:10px;margin-left: 70px;">';
		 html+='<div><span style="display:inline-block;width:76px;height:20px;;vertical-align: middle;">居住情况：</span><span style="display:inline-block;width:40px;height:20px;background:#fff7ae;vertical-align: middle;"></span><span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">自住</span>&nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#d6f175;vertical-align: middle;"></span> <span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">出租</span> &nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#ffc934;vertical-align: middle;"></span><span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">空置</span>&nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#27d5f7;vertical-align: middle;"></span> <span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">其他</span>&nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#f5f3f0;vertical-align: middle;"></span><span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">默认</span></div>';
		 html+='</div>';
		 html+='<div class="addFloor" onclick="addFloorTop()">';
		 html+='<i style="color: #555" class="icon iconfont icon-iconjia"></i>';
		 html+='</div>';
		 html+='<div class="build" style="width:'+widthB+'px">';
		 html+='<div class="build-top clearfix" style="width:100%;position:relative;">';	
		 html+='<div  style="width:20px;position:absolute;background:#00a3e4;color:#fff;font-weight: bold;top:0;left:-20px;text-align: center; padding: 5px 0;">住宅</div>';	
    	 for(var j in residentialUnitArr){
    		//几个单元
    		 html+='<div class="unit unit-'+residentialUnitArr[j]+'">';
 			 html+='<div class="unit-top" unitNum="'+residentialUnitArr[j]+'">';
 			 var houseArr=[];
    		 for(var x in data){
    			//找到同一单元有多少楼层
    			 if(residentialUnitArr[j]==data[x].residentialUnit){
    			    var y=data[x].y;
    				if( $.inArray(y, houseArr)==-1){
    					 houseArr.push(y)
    	    		} 
    			 }
    		 }
    		 houseArr=houseArr.sort(descNumber);
    		 //找到同一单元同一楼层多少房屋
    		 for (var m in houseArr){
    		 	    if(m == houseArr.length-1){
						html+='<div class="floor i1">';
					} else {
						html+='<div class="floor">';
					}
					html+='<table>';
					html+='<tr>';
    			for(var n in data){
    				if(residentialUnitArr[j]==data[n].residentialUnit&&houseArr[m]==data[n].y){
    				    var houseType=data[n].houseType;
    					var houseColor="#f5f3f0";
    					if(houseType=='01'){
    						//房屋状态自住
    						houseColor="#fff7ae";
    					}else if(houseType=='02'){
    						//房屋状态出租
    						houseColor="#fff7ae";
    					}else if(houseType=='03'){
    						//房屋状态空置
    						houseColor="#ffc934";
    					}else if(houseType=='99'){
    						//房屋状态其他
    						houseColor="#27d5f7";
    					}else{
    						//默认
    						houseColor="#f5f3f0";
    					}
    					html+='<td>';
    					html+='<div class="floor-center" style="background:'+houseColor+'" x="'+data[n].x+'" y="'+data[n].y+'" roomId="'+data[n].houseNum+'" roomName="'+data[n].houseBuild+'" roomUnit="'+residentialUnitArr[j]+'">';
    					if(data[n].houseBuild!=undefined && data[n].houseBuild!='' && data[n].houseBuild!=null){
							html+='<span style="color: #555">'+(data[n].houseBuild+' ('+ data[n].floorNum +'-'+ data[n].doorNum +')')+'</span>';
						} else {
							html+='<span style="color: #555">'+('')+'</span>';
						}
    					html+='<i  class="icon iconfont icon-guanbi remove-floor" style="color: #555"  onclick="removeFloor(this)"></i>';
    					html+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
    					html+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
    					html+='</div>';
    					html+='</td>';		
    				}
    			}
    			html+='</tr>';			
				html+='</table>';		
				html+='</div>';	
    		 }
    		html+='</div>';
    		html+='<div class="unit-num">';	
			html+='<table>';
			//单元大于1，添加删除单元
			if(residentialUnitArr[j]>1){
				html+='<td><span style="color: #555">'+residentialUnitArr[j]+'单元<a class="btnList" title="删除该单元" onclick="removeUnit('+residentialUnitArr[j]+')"><i class="icon-remove-sign"></i></a></span></td>';
			}else{
				html+='<td><span style="color: #555">'+residentialUnitArr[j]+'单元</span></td>';
			}
					
			html+='</table>';		
			html+='<div class="addFloor addUnit" onclick="addUnit()">';		
			html+='<i style="color: #555" class="icon iconfont icon-iconjia"></i>';
			html+='</div>';		
			html+='</div>';	
			html+='</div>';
    	 }
    	 var houseBottomArr=[];
    	 //地基
    	 for(var b in data){
    		 var z=data[b].residentialUnit;
    		 if( z=='bottom'){
    			 var y=data[b].y;
 				if( $.inArray(y, houseBottomArr)==-1){
 					houseBottomArr.push(y)
 	    		} 
    		 }
    	 }
    	 // houseBottomArr=houseBottomArr.sort(sortNumber);
    	//地基
    	 html+='</div>';
    	 html+='<div class="unit-botom unit-top" unitNum="bottom">';
		 html+='<div  style="width:60px;position:absolute;background:#00a3e4;color:#fff;font-weight: bold;top:0;left:-60px;text-align: center; padding: 15px 0;">地下室</div>';	

    	 if(houseBottomArr.length>0){
    		//地基
		 for (var h in houseBottomArr){
				html+='<div class="floor i1">';
				html+='<table>';				
				html+='<tr>';
			for(var d in data){
				if(data[d].residentialUnit=='bottom'&&houseBottomArr[h]==data[d].y){
					 var houseType=data[n].houseType;
					var houseColor="#f5f3f0";
					if(houseType=='01'){
						//房屋状态自住
						houseColor="#fff7ae";
					}else if(houseType=='02'){
						//房屋状态出租
						houseColor="#fff7ae";
					}else if(houseType=='03'){
						//房屋状态空置
						houseColor="#ffc934";
					}else if(houseType=='99'){
						//房屋状态其他
						houseColor="#27d5f7";
					}else{
						//默认
						houseColor="#f5f3f0";
					}
					
					html+='<td>';
					html+='<div class="floor-center"  style="background:'+houseColor+'"  x="'+data[d].x+'" y="'+data[d].y+'" roomId="'+data[d].houseNum+'" roomName="'+data[d].houseBuild+'" roomUnit="bottom">';
					html+='<span style="color: #555">'+(data[d].houseBuild||'')+'</span>';
					html+='<i class="icon iconfont icon-guanbi remove-floor"  style="color: #555" onclick="removeFloor(this)"></i>';
					html+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
					html+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
					html+='</div>';
					html+='</td>';		
				}
			}
			html+='</tr>';			
			html+='</table>';		
			html+='</div>';	
		 }
    	 }else{
    		 //没有地基数据时初始化
    		html+='<div class="floor i1">';
     		html+='<table>';		
     		html+='<tr>';			
     		html+='<td>';				
     		html+='<div class="floor-add" style="color: #555" onclick="floorAddup(this)">';
     		html+='<i style="color:#555;" class="icon iconfont icon-add"></i>';
     		html+='</div>';				
     		html+='</td>';				
     		html+='</tr>';			
     		html+='</table>';		
     		html+='</div>';	
    	 }
    			
    		html+='</div>';	
    		html+='<div class="unit-num unit-botom-num">';	
    		
    		html+='<table>';		
    		html+='<td><span style="color: #555">地基</span></td>';
    		html+='</table>';		
    		html+='<div class="addFloor addFloorBottom" onclick="addFloorBottom()">';		
    		html+='<i style="color: #555" class="icon iconfont icon-iconjia"></i>';
    		html+='</div>';		
    		html+='</div>';	
    		html+='</div>';
    		html+='</div>';
    		html+='</div>';
    	}else{
    	 //没有数据时初始化
		 html+='<div id="home">';
		 html+='<div class="homeLeft" style="width: 150px; height: 700px;float: left;overflow-y:auto ">';
		 html+='<ul id="roomTree" class="ztree"></ul>';
		 html+='</div>';
		 html+='<div class="homeRight" style="width: 1250px; height: 700px;float: left;overflow-x:auto">';
		 html+='<div style="margin:10px;margin-left: 70px;">';
		 html+='<div><span style="display:inline-block;width:76px;height:20px;vertical-align: middle;">居住情况：</span><span style="display:inline-block;width:40px;height:20px;background:#fff7ae;vertical-align: middle;"></span><span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">自住</span>&nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#d6f175;vertical-align: middle;"></span> <span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">出租</span> &nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#ffc934;vertical-align: middle;"></span><span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">空置</span>&nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#27d5f7;vertical-align: middle;"></span> <span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">其他</span>&nbsp;&nbsp;<span style="display:inline-block;width:40px;height:20px;background:#f5f3f0;vertical-align: middle;"></span><span style="display:inline-block;width:40px;height:20px;;vertical-align: middle;">默认</span></div>';
		 html+='</div>';
		 html+='<div class="addFloor" onclick="addFloorTop()">';
		 html+='<i style="color: #555" class="icon iconfont icon-iconjia"></i>';
		 html+='</div>';
		 html+='<div class="build">';
		 html+='<div class="build-top clearfix" style="width:100%;position:relative;">';	
		 html+='<div  style="width:20px;position:absolute;background:#00a3e4;color:#fff;font-weight: bold;top:0;left:-20px;text-align: center; padding: 5px 0;">住宅</div>';	
    	 html+='<div class="unit unit-1">';
    	html+='<div class="unit-top" unitNum="1">';	
    	html+='<div class="floor i1">';
    	html+='<table>';				
    	html+='<tr>';				
    	html+='<td>';					
    	html+='<div class="floor-add" onclick="floorAdd(this)">';						
    	html+='<i style="color: #555" class="icon iconfont icon-add"></i>';
    	html+='</div>';					
    	html+='</td>';				
    	html+='</tr>';			
    	html+='</table>';		
    	html+='</div>';		
        html+='</div>';	
    	html+='<div class="unit-num">';	
    	html+='<table>';		
    	html+='<td><span style="color: #555">1单元</span></td>';
    	html+='</table>';		
    	html+='<div class="addFloor addUnit" onclick="addUnit(this)">';
    	html+='<i style="color: #555" class="icon iconfont icon-iconjia"></i>';
    	html+='</div>';		
    	html+='</div>';	
    	html+='</div>';
    	html+='</div>';
        html+='<div class="unit-botom unit-top" unitNum="bottom">';
		html+='<div  style="width:60px;position:absolute;background:#00a3e4;color:#fff;font-weight: bold;top:0;left:-60px;text-align: center; padding: 15px 0;">地下室</div>';	
    	html+='<div class="floor i1">';
    	html+='<table>';		
    	html+='<tr>';			
    	html+='<td>';				
    	html+='<div class="floor-add" onclick="floorAddup(this)">';
    	html+='<i style="color:#555;" class="icon iconfont icon-add"></i>';
    	html+='</div>';				
    	html+='</td>';				
    	html+='</tr>';			
    	html+='</table>';		
    	html+='</div>';		
    	html+='</div>';	
    	html+='<div class="unit-num unit-botom-num">';	
    	html+='<table>';		
    	html+='<td><span style="color:#555;">地基</span></td>';
    	html+='</table>';		
    	html+='<div class="addFloor addFloorBottom" onclick="addFloorBottom()">';		
    	html+='<i style="color:#555;" class="icon iconfont icon-iconjia"></i>';
    	html+='</div>';		
    	html+='</div>';	
    	html+='</div>';
    	html+='</div>';
    	html+='</div>';
    	}

    		layer.open({
    	        type: 1,
    	        title: "房间",
    	        area: ["1500px", "800px"],
    	        btn: ["确定", "取消"],
    	        maxmin: false,
    	        content: html,
    	        cancel: function() {},
    	        end: function() {},
    	        yes: function(index, layero) {
    	        	layer.close(index);
    	            sove();
    	        },
    	        
    	    });
    		var maxH = 0;
    		 //遍历最大高度赋给maxH
    		 $(".unit").each(function() {
    			 var maxHeight=$(this).height();
    		  if (maxHeight > maxH) {
    			  maxH = maxHeight;
    		  }
    		 });
    		 $(".unit").each(function() {
    			 var maxHeight=$(this).height();
    		     var _thisHeight=$(this).height()-maxH;
    		     $(this).css('bottom',_thisHeight)
    		 });
    		findListBuildData(buildingId,areaId,data);
	})
	
}

function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}
function findListBuildData(buildingIdId,areaIdId,houseData){
	var ccmPopTenant={};
	ccmPopTenant['buildingId.id'] = buildingIdId;
	ccmPopTenant['area.id'] = areaIdId;
	var MoveTest = {
			errorMsg: "放错了...请选择正确的类别！",
			curTarget: null,
			curTmpTarget: null,
			noSel: function() {
				try {
					window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
				} catch(e){}
			},
			dragTree2Dom: function(treeId, treeNodes) {
				if(treeNodes[0].drag == false){
					return false
				}
				return !treeNodes[0].isParent;
			},
			prevTree: function(treeId, treeNodes, targetNode) {
				return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
			},
			nextTree: function(treeId, treeNodes, targetNode) {
				return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
			},
			innerTree: function(treeId, treeNodes, targetNode) {
				return targetNode!=null && targetNode.isParent && targetNode.tId == treeNodes[0].parentTId;
			},
			dragMove: function(e, treeId, treeNodes) {
				var p = null, pId = 'dom_' + treeNodes[0].pId;
				if (e.target.id == pId) {
					p = $(e.target);
				} else {
					p = $(e.target).parent('#' + pId);
					if (!p.get(0)) {
						p = null;
					}
				}

				$('.domBtnDiv .active').removeClass('active');
				if (p) {
					p.addClass('active');
				}
			},
			dropTree2Dom: function(e, treeId, treeNodes, targetNode, moveType) {
				var id = treeNodes[0].id;
				var name=treeNodes[0].name;
				
				var zTree = $.fn.zTree.getZTreeObj("roomTree");
				if (moveType == null &&$(e.target).attr('roomId')!=undefined&&  $(e.target).attr('roomId')=='') {
					$(e.target).children('span').html(name);
					$(e.target).attr('roomId',id);
			  	    $(e.target).attr('roomName',name);
			  	  var houseType=treeNodes[0].houseType;
					var houseColor="#f5f3f0";
					if(houseType=='01'){
						//房屋状态自住
						houseColor="#fff7ae";
					}else if(houseType=='02'){
						//房屋状态出租
						houseColor="#fff7ae";
					}else if(houseType=='03'){
						//房屋状态空置
						houseColor="#ffc934";
					}else if(houseType=='99'){
						//房屋状态其他
						houseColor="#27d5f7";
					}else{
						//默认
						houseColor="#f5f3f0";
					}
					 $(e.target).css('background',houseColor);
					treeNodes[0].drag = false;
					treeNodes[0].font={'color':'#000'}
					zTree.updateNode(treeNodes[0])
				} else if ( moveType == null && $(e.target).attr('roomId')!=undefined&&  $(e.target).attr('roomId')!='') {
					layer.msg('已绑定房屋');
				}else{
					//layer.alert('请先新建房屋');
				}
			},
			dom2Tree: function(e, treeId, treeNode) {
				var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
				if (!target) return;
				var zTree = $.fn.zTree.getZTreeObj("roomTree"), parentNode;
				if (treeNode != null && treeNode.isParent && "dom_" + treeNode.id == target.parent().attr("id")) {
					parentNode = treeNode;
				} else if (treeNode != null && !treeNode.isParent && "dom_" + treeNode.getParentNode().id == target.parent().attr("id")) {
					parentNode = treeNode.getParentNode();
				}

				if (tmpTarget) tmpTarget.remove();
				if (!!parentNode) {
					var nodes = zTree.addNodes(parentNode, {id:target.attr("domId"), name: target.text()});
					zTree.selectNode(nodes[0]);
				} else {
					target.removeClass("domBtn_Disabled");
					target.addClass("domBtn");
					/*alert(MoveTest.errorMsg);*/
				}
				MoveTest.updateType();
				MoveTest.curTarget = null;
				MoveTest.curTmpTarget = null;
			},
			
			updateType: function() {
				var zTree = $.fn.zTree.getZTreeObj("roomTree"),
				nodes = zTree.getNodes();
				for (var i=0, l=nodes.length; i<l; i++) {
					var num = nodes[i].children ? nodes[i].children.length : 0;
					nodes[i].name = nodes[i].name.replace(/ \(.*\)/gi, "") + " (" + num + ")";
					zTree.updateNode(nodes[i]);
				}
			},
			bindDom: function() {
				$(".domBtnDiv").bind("mousedown", MoveTest.bindMouseDown);
			},
			bindMouseDown: function(e) {
				var target = e.target;
				if (target!=null && target.className=="domBtn") {
					var doc = $(document), target = $(target),
					docScrollTop = doc.scrollTop(),
					docScrollLeft = doc.scrollLeft();
					target.addClass("domBtn_Disabled");
					target.removeClass("domBtn");
					curDom = $("<span class='dom_tmp domBtn'>" + target.text() + "111</span>");
					curDom.appendTo("body");

					curDom.css({
						"top": (e.clientY + docScrollTop + 3) + "px",
						"left": (e.clientX + docScrollLeft + 3) + "px"
					});
					MoveTest.curTarget = target;
					MoveTest.curTmpTarget = curDom;

					doc.bind("mousemove", MoveTest.bindMouseMove);
					doc.bind("mouseup", MoveTest.bindMouseUp);
					doc.bind("selectstart", MoveTest.docSelect);
				}
				if(e.preventDefault) {
					e.preventDefault();
				}
			},
			bindMouseMove: function(e) {
				MoveTest.noSel();
				var doc = $(document), 
				docScrollTop = doc.scrollTop(),
				docScrollLeft = doc.scrollLeft(),
				tmpTarget = MoveTest.curTmpTarget;
				if (tmpTarget) {
					tmpTarget.css({
						"top": (e.clientY + docScrollTop + 3) + "px",
						"left": (e.clientX + docScrollLeft + 3) + "px"
					});
				}
				return false;
			},
			bindMouseUp: function(e) {
				var doc = $(document);
				doc.unbind("mousemove", MoveTest.bindMouseMove);
				doc.unbind("mouseup", MoveTest.bindMouseUp);
				doc.unbind("selectstart", MoveTest.docSelect);

				var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
				if (tmpTarget) tmpTarget.remove();

				if ($(e.target).parents("#roomTree").length == 0) {
					if (target) {
						target.removeClass("domBtn_Disabled");
						target.addClass("domBtn");
					}
					MoveTest.curTarget = null;
					MoveTest.curTmpTarget = null;
				}
			},
			bindSelect: function() {
				return false;
			}
		};
	// ztree
	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
	var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
					prev: MoveTest.prevTree,
					next: MoveTest.nextTree,
					inner: MoveTest.innerTree
				}
			},
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrag: MoveTest.dragTree2Dom,
				onDrop: MoveTest.dropTree2Dom,
				onDragMove: MoveTest.dragMove,
				onMouseUp: MoveTest.dom2Tree
			},
			view: {
				selectedMulti: false,
				fontCss: getFont,
				nameIsHTML: true
			}
	};
	$.getJSON(ctx + '/pop/ccmPopTenant/findListBuildData',ccmPopTenant,function(data){
		var roomNodes = [];
		var roomData=data.data;
		roomNodes.push({
			id:'1', 
			pId:0,
			name:'房间',
			open:true,
			isParent: true
		})
		for (var x in roomData) {
				var resname = "";
				if(roomData[x].floorNum!=undefined && roomData[x].doorNum!=undefined ){
					resname = roomData[x].houseBuild+' ('+ roomData[x].floorNum +'-'+ roomData[x].doorNum +')'
				} else {
					resname = roomData[x].houseBuild
				}
				roomNodes.push({
						id:roomData[x].id, 
						pId:1,
						houseType:roomData[x].houseType,//房屋状态自住、出租、空置、其他
						drag:true,
						font:{'color':'#aeaebb !important' },
						name: resname
				})
			}
		var houseDataLen=houseData.length;
		if(houseDataLen>0){
			for (var x in roomNodes) {
				 for(var q in houseData){
					 if(roomNodes[x].id==houseData[q].houseNum){
						 roomNodes[x].drag=false;
						 roomNodes[x].font={'color':'#000'};
					 }
				 }
				
			}
		}
		
		var treeCollect = $.fn.zTree.init($("#roomTree"), setting, roomNodes);
		MoveTest.updateType();
		MoveTest.bindDom();
	})
	
}

var floorHtml='';
floorHtml+='<div class="floor-center" roomId="" roomName="" roomUnit="">';
floorHtml+='<span style="color: #555"></span>';
floorHtml+='<i class="icon iconfont icon-guanbi remove-floor"  style="color: #555" onclick="removeFloor(this)"></i>';
floorHtml+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
floorHtml+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
floorHtml+='</div>';
var floorAddHtml='';
floorAddHtml+='<div class="floor-add"   onclick="floorAdd(this)">';
floorAddHtml+='<i style="color:#555;" class="icon iconfont icon-add"></i>';
floorAddHtml+='</div>';
var floorAddupHtml='';
floorAddupHtml+='<div class="floor-add"   onclick="floorAddup(this)">';
floorAddupHtml+='<i style="color:#555;" class="icon iconfont icon-add"></i>';
floorAddupHtml+='</div>';
$(function () {
    $('body').on('mouseenter', '.build .floor-center', function() {
        $(this).children('i').show();
    });
    $('body').on('mouseleave', '.build .floor-center', function() {
        $(this).children('i').hide();
    });
})
function floorAdd(_this) {
    if($(_this).parents('.floor').next().find('.floor-center').length==0
        && $(_this).parents('.floor.i1').length==0){
		layer.tips('请从相邻房屋开始添加', _this, {
			tips : [ 1, '#20c694' ],
			time : 2000,
		});
        return;
    }
	var roomUnit=$(_this).parents('.unit-top').attr('unitNum');
    function getytemp () {
        if($(_this).parents('.floor').next().find('.floor-center').attr('y')!=undefined ){
            return $(_this).parents('.floor').next().find('.floor-center').attr('y')
        } else {
            if($(_this).parents('.floor').siblings().last().find('.floor-center').attr('y') !=undefined ){
                return $(_this).parents('.floor').siblings().last().find('.floor-center').attr('y')
            } else {
                return 0;
            }
        }
    }
    var ytemp = getytemp();
	var y= Number(ytemp)+1;
    var xtemp = $(_this).parents('.floor').siblings().find('.floor-center').attr('x')!=undefined ? $(_this).parents('.floor').siblings().find('.floor-center').attr('x') : roomUnit;
    var x= Number(xtemp);
	var floorHtmlAdd='';
	floorHtmlAdd+='<div class="floor-center" x="'+ x +'" y="'+ y +'" roomId="" roomName="" roomUnit="'+roomUnit+'">';
	floorHtmlAdd+='<span style="color: #555"></span>';
	floorHtmlAdd+='<i class="icon iconfont icon-guanbi remove-floor" style="color: #555" onclick="removeFloor(this)"></i>';
	floorHtmlAdd+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
	floorHtmlAdd+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
	floorHtmlAdd+='</div>';
    $(_this).parent('td').html(floorHtmlAdd);
    // $this.each(function(index){
	// 	var index=Number(index);
	// 	$(this).children().children().children().children().children().attr('x',index);
	// })
}
function removeFloor(_this,type) {
    var length=$(_this).parent().parent().parent('tr').children('td').length;
    if(length>1){
        $(_this).parent().parent('td').remove();
    }else if(type == 1){
        $(_this).parent().parent('td').html(floorAddupHtml)
    }else {
        $(_this).parent().parent('td').html(floorAddHtml)
    }
    // $this.each(function(index){
	// 	var index=Number(index)+1;
	// 	$(this).children().children().children().children().children().attr('x',index);
	// });
    
	// $thisY.children().each(function(index){
    // 	// 	var index=Number(index)+1;
    // 	// 	$(this).children().attr('y',index);
    // 	// });
	var roomId=$(_this).parent().attr('roomId');
	var zTree = $.fn.zTree.getZTreeObj("roomTree");
	var node = zTree.getNodeByParam("id", roomId, null);
	if(node!=null){
		node.drag = true;
		node.font={'color':'#aeaebb'};
		zTree.updateNode(node);
	}
}
function floorAddLeft(_this) {
	var roomUnit=$(_this).parents('.unit-top').attr('unitNum');
    var y = Number($(_this).parent('.floor-center').attr('y'));
    var x = Number($(_this).parent('.floor-center').attr('x'));
	var floorHtmlLeft='';
	floorHtmlLeft+='<td>';
	floorHtmlLeft+='<div class="floor-center" x="" y="'+ y +'" roomId="" roomName="" roomUnit="'+roomUnit+'">';
	floorHtmlLeft+='<span style="color: #555"></span>';
	floorHtmlLeft+='<i class="icon iconfont icon-guanbi remove-floor" style="color:#555;" onclick="removeFloor(this)"></i>';
	floorHtmlLeft+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
	floorHtmlLeft+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
	floorHtmlLeft+='</div>';
	floorHtmlLeft+='</td>';
    $(_this).parent().parent('td').before(floorHtmlLeft);
    $(_this).parent().parent().parent().children().each(function(index){
		var index=Number(x++);
		$(this).children().attr('x',index);
	})
/*	$(_this).parents('.unit-top').children('.floor').each(function(index){
		var index=Number(index)+1;
		$(this).children().children().children().children().children().attr('y',index);
	})*/
}
function floorAddRight(_this) {
	// var x=$(_this).parents('.floor').children('.floor').index();
    var x = Number($(_this).parent('.floor-center').attr('x'));
    var y = Number($(_this).parent('.floor-center').attr('y'));
	var roomUnit=$(_this).parents('.unit-top').attr('unitNum');
	var floorHtmlRight='';
	floorHtmlRight+='<td>';
	
	floorHtmlRight+='<div class="floor-center" x="" y="'+ y +'" roomId="" roomName="" roomUnit="'+roomUnit+'">';
	floorHtmlRight+='<span style="color: #555"></span>';
	floorHtmlRight+='<i class="icon iconfont icon-guanbi remove-floor" style="color: #555" onclick="removeFloor(this)"></i>';
	floorHtmlRight+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
	floorHtmlRight+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
	floorHtmlRight+='</div>';
	floorHtmlRight+='</td>';
    $(_this).parent().parent('td').after(floorHtmlRight);
	$(_this).parent().parent().parent().children().each(function(index){
		// var index=Number(index)+1;
        var index=Number(x++);
        $(this).children().attr('x',index);
	})
/*	$(_this).parents('.unit-top').children('.floor').each(function(index){
		var index=Number(index)+1;
		$(this).children().children().children().children().children().attr('y',index);
   })*/
}
function addFloorTop() {
    var html='';
    html+='<div class="floor">' ;
    html+='<table>';
    html+='<tr>';
    html+=' <td>';
    html+=floorAddHtml;
    html+='</td>';
    html+='</tr>';
    html+=' </div>';
    $('.build-top .unit-top').prepend(html)
}
function addFloorBottom() {
    var html='';
    html+='<div class="floor">' ;
    html+='<table>';
    html+='<tr>';
    html+=' <td>';
    html+=floorAddupHtml;
    html+='</td>';
    html+='</tr>';
    html+='</table>';
    html+=' </div>';
    $('.unit-botom').append(html)
}
function addUnit(_this) {
    var len=$('.unit').length;
    var widthU=$('.unit').width();
    var widthN=Number(len)+1;
    var widthB=widthN*widthU
    $('.build').css('width',widthB);
     var html='';
    html+='<div class="unit unit-'+widthN+'">';
    html+='<div class="unit-top" unitNum="'+widthN+'">';
   // var floorLen=$('.unit-1 .floor').length;
	var floorLen = 0;
	$(".unit").each(function() {
		var maxfloorlen=$(this).find('.floor').length;
		if (maxfloorlen > floorLen) {
			floorLen = maxfloorlen;
		}
	});

   for(var i=0;i<floorLen;i++){
       if(i == floorLen-1){
           html+='<div class="floor i1">' ;
       } else {
           html+='<div class="floor">' ;
       }
       html+='<table>';
       html+='<tr>';
       html+=' <td>';
       html+=floorAddHtml;
       html+='</td>';
       html+='</tr>';
       html+='</table>';
       html+=' </div>';
   }
    html+=' </div>';
    html+=' <div class="unit-num">';
    html+=' <table>';
    html+='<td>';
    html+=' <span style="color:#555;">'+widthN+'单元<a class="btnList" title="删除该单元" onclick="removeUnit('+widthN+')"><i  class="icon-trash"></i></a></span></td>';
    html+='</table>';
    html+='<div class="addFloor addUnit" onclick="addUnit(this)"><i style="color: #555" class="icon iconfont icon-iconjia"></i></div>';
    html+='</div>';
    html+=' </div>';
   $('.build-top').append(html)
}
function removeUnit(UnitNum) {

	$('.unit-'+UnitNum+'').remove()
	 var len=$('.unit').length;
    var widthU=$('.unit').width();
    var widthN=Number(len);
    var widthB=widthN*widthU
    $('.build').css('width',widthB);
}
function sove(){
	var ccmHouseBuildmanageUnitList=[];
	var userId=parent.$('#userid').val();
	$('.floor-center').each(function(idnex){
		var x=$(this).attr('x');
		var y=$(this).attr('y');
		var roomId=$(this).attr('roomId');
		var roomUnit=$(this).attr('roomUnit');
		var CcmHouseBuildmanageUnit = {};
		CcmHouseBuildmanageUnit.x = x;
		CcmHouseBuildmanageUnit.y = y;
		CcmHouseBuildmanageUnit.houseNum = roomId;
		CcmHouseBuildmanageUnit.residentialUnit = roomUnit;
		CcmHouseBuildmanageUnit.buildmanageId = buildingIdIdCancle;
		CcmHouseBuildmanageUnit.userId = userId;
		ccmHouseBuildmanageUnitList.push(CcmHouseBuildmanageUnit);
	})
	$.ajax({
		type: 'post',
		url: ctx+'/house/ccmHouseBuildmanageUnit/saveData',
		dataType: "json", //数据格式
		contentType: "application/json",
		async: true,
		data: JSON.stringify(ccmHouseBuildmanageUnitList),
		success: function(res) {
			
		}
	});
}

function floorAddup(_this) {
    if($(_this).parents('.floor').prev().find('.floor-center').length==0
        && $(_this).parents('.floor.i1').length==0){
		layer.tips('请从相邻房屋开始添加', _this, {
			tips : [ 1, '#20c694' ],
			time : 2000,
		});
        return;
    }
    var roomUnit=$(_this).parents('.unit-top').attr('unitNum');
    function getytemp () {
        if($(_this).parents('.floor').prev().find('.floor-center').attr('y')!=undefined ){
            return $(_this).parents('.floor').prev().find('.floor-center').attr('y')
        } else {
            if($(_this).parents('.floor').siblings().first().find('.floor-center').attr('y') !=undefined ){
                return $(_this).parents('.floor').siblings().first().find('.floor-center').attr('y')
            } else {
                return 0;
            }
        }
    }
    var ytemp = getytemp();
    var y= Number(ytemp)-1;
    var xtemp = $(_this).parents('.floor').prev().find('.floor-center').attr('x')!=undefined ? $(_this).parents('.floor').prev().find('.floor-center').attr('x') : 1;

    var x= Number(xtemp);
    var floorHtmlAdd='';
    floorHtmlAdd+='<div class="floor-center" x="'+ x +'" y="'+ y +'" roomId="" roomName="" roomUnit="'+roomUnit+'">';
    floorHtmlAdd+='<span style="color: #555"></span>';
    floorHtmlAdd+='<i style="color:#555;" class="icon iconfont icon-guanbi remove-floor" onclick="removeFloor(this,1)"></i>';
    floorHtmlAdd+='<i class="icon iconfont icon-speed-right-11 floor-add-left"  onclick="floorAddLeft(this)"></i>';
    floorHtmlAdd+='<i class="icon iconfont icon-speed-right-1 floor-add-right"  onclick="floorAddRight(this)"></i>';
    floorHtmlAdd+='</div>';
    $(_this).parent('td').html(floorHtmlAdd);
}

