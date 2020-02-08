var MoveFlow = {
	errorMsg: "请选择正确的步骤节点拖动！",
	curTarget: null,
	curTmpTarget: null,
	noSel: function() {
		try {
			window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
		} catch(e){}
	},
	dragTree2Dom: function(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
	},
	beforeDrag: function(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	},
	beforeDrop: function(treeId, treeNodes, targetNode, moveType) {
		if(targetNode==null){
			$.jBox.tip('请选择预案流程节点！');
			    return false;
			}else if(targetNode!=null&&targetNode.level!=0){
			    $.jBox.tip('请选择预案流程节点！');
			    return false;
			}
			var ids=targetNode.children;
			var planId=targetNode.id;
			var stepId=treeNodes[0].id;
			if(ids){
				var idsLen=ids.length;
				if(idsLen>0){
					for(var i=0;i<idsLen;i++){
						if(stepId==ids[i].id){
							$.jBox.tip('该'+treeNodes[0].name+'已经存在！');
							return false;
						}
					}
				}
			}
			var flag=targetNode ? targetNode.drop !== false : true;
			if(flag){
				var json = {'planId':planId,'stepId':stepId};
				var paramBphPlanStep = encodeURI(JSON.stringify(json));
				$.post(ctx+'/fiow/planFlowManage/saveBphPlanStep',{'paramBphPlanStep':paramBphPlanStep},function(){
				})
			}
			return flag;
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
	dom2Tree: function(e, treeId, treeNode) {
		var target = MoveFlow.curTarget, tmpTarget = MoveFlow.curTmpTarget;
		if (!target) return;
		var zTree = $.fn.zTree.getZTreeObj("StepTree"), parentNode;
		var ids=[];
		if (treeNode != null && treeNode.level==0) {
			parentNode = treeNode;
			ids=treeNode.children;
		} else if (treeNode != null && !(treeNode.level==0)) {
			parentNode = treeNode.getParentNode();
			ids=treeNode.getParentNode().children;
		}
		if (tmpTarget) {
			tmpTarget.remove();
		}
		if (!!parentNode) {
			var stepId=parentNode.id;
			var actionId=target.attr("domId");
			if(ids){
				var idsLen=ids.length;
				if(idsLen>0){
					for(var i=0;i<idsLen;i++){
						if(actionId==ids[i].id){
							$.jBox.tip('该'+ids[i].name+'已经存在！');
							return false;
						}
					}
				}
			}
			var nodes = zTree.addNodes(parentNode, {id:actionId, name: target.text(),drag:false, drop:false});
			zTree.selectNode(nodes[0]);
			var jsons = {'stepId':stepId,'actionId':actionId};
			var paramBphStepAction = encodeURI(JSON.stringify(jsons));
			$.post(ctx+'/fiow/planFlowManage/saveBphStepAction',{'paramBphStepAction':paramBphStepAction},function(){
			})
		} else {
			target.removeClass("domBtn_Disabled");
			target.addClass("domBtn");
			$.jBox.tip(MoveFlow.errorMsg);
		}
		MoveFlow.curTarget = null;
		MoveFlow.curTmpTarget = null;
	},
	bindDom: function() {
		$(".domBtnDiv").bind("mousedown", MoveFlow.bindMouseDown);
	},
	bindMouseDown: function(e) {
		var target = e.target;
		if (target!=null && target.className=="domBtn") {
			var doc = $(document), target = $(target),
			docScrollTop = doc.scrollTop(),
			docScrollLeft = doc.scrollLeft();
			curDom = $("<span style='display:block;width:270px;height:20px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;' title='"+target.text()+"' class='dom_tmp domBtn'>" + target.text() + "</span>");
			curDom.appendTo("body");
			curDom.css({
				"top": (e.clientY + docScrollTop + 3) + "px",
				"left": (e.clientX + docScrollLeft + 3) + "px"
			});
			MoveFlow.curTarget = target;
			MoveFlow.curTmpTarget = curDom;
			doc.bind("mousemove", MoveFlow.bindMouseMove);
			doc.bind("mouseup", MoveFlow.bindMouseUp);
			doc.bind("selectstart", MoveFlow.docSelect);
		}
		if(e.preventDefault) {
			e.preventDefault();
		}
	},
	bindMouseMove: function(e) {
		MoveFlow.noSel();
		var doc = $(document), 
		docScrollTop = doc.scrollTop(),
		docScrollLeft = doc.scrollLeft(),
		tmpTarget = MoveFlow.curTmpTarget;
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
		doc.unbind("mousemove", MoveFlow.bindMouseMove);
		doc.unbind("mouseup", MoveFlow.bindMouseUp);
		doc.unbind("selectstart", MoveFlow.docSelect);
		var target = MoveFlow.curTarget, tmpTarget = MoveFlow.curTmpTarget;
		if (tmpTarget) tmpTarget.remove();
		if ($(e.target).parents("#StepTree").length == 0) {
			if (target) {
				target.removeClass("domBtn_Disabled");
				target.addClass("domBtn");
			}
			MoveFlow.curTarget = null;
			MoveFlow.curTmpTarget = null;
		}
	},
	bindSelect: function() {
		return false;
	}
};
$(function(){
	inittree();
	MoveFlow.bindDom();
})
function inittree(){
	var ReservePlanSetting = {
		edit: {
			enable: true,
			showRemoveBtn: PlanshowRemoveBtn,
			showRenameBtn: false,
			  drag: {
				  isCopy:false,
				  isMove:false,
				  inner : true,
				  prev : false,
				  next  : false
			    }
			},
			view : {  
                selectedMulti: false,  
                showIcon: true,  //设置是否显示节点图标  
            },  
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrag: MoveFlow.beforeDrag,
				beforeDrop: MoveFlow.beforeDrop,
			    onExpand:function(e,treeId,treeNode){  
	              var treeObj = $.fn.zTree.getZTreeObj(treeId);  
	              var id=treeNode.id 
	              if(treeNode.children == null || treeNode.children == "undefined"){  
	            	  if(treeNode.level=='0'){
	            		  //加载过程节点
	                	$.getJSON(ctx+'/fiow/planFlowManage/planStepTree',{'id':id},function(data){
			    			if(data!=null && data !=""){  
			    				for (i in data){
		    					/*	data[i].drop=false;*/
			    					data[i].isParent=true;
				    			}
					    		var newNode = treeObj.addNodes(treeNode,data);
			    			 	}
			    			})
	            	   }else if(treeNode.level=='1'){
	            		  //加载动作节点
	            		  $.getJSON(ctx+'/fiow/planFlowManage/planActionTree',{'id':id},function(data){
			    			 if(data!=null && data !=""){  
					    		console.log(data)
					    		var newNode = treeObj.addNodes(treeNode,data);
			    			 }
	            		  })
	            	   }
	                }
			     },
				 onRemove:function(e, treeId, treeNode) {
					var jsonDelAction = {'planId':treeNode.pId,'stepId':treeNode.id};
					var paramDelPlanStep = encodeURI(JSON.stringify(jsonDelAction));
					$.post(ctx+'/fiow/planFlowManage/deleteBphPlanStep',{'paramDelPlanStep':paramDelPlanStep},function(data){
					})
				    //需要对删除做判定或者其它操作，在这里写~~
				}
			}
		};
        //预案树
		var str="";
		/*$.getJSON(ctx+'/fiow/planFlowManage/planStepActionList?typeCode='+str,function(data){
			$.fn.zTree.init($("#ReservePlanTree"), ReservePlanSetting,data);
		})*/
		$.getJSON(ctx+'/fiow/planFlowManage/planTree?typeCode='+str,function(data){
			for(i in data){
				data[i].isParent=true;
			}
			$.fn.zTree.init($("#ReservePlanTree"), ReservePlanSetting,data);
		})
		var StepSetting = {
			edit: {
				enable: true,
				showRemoveBtn: StepshowRemoveBtn,
				showRenameBtn: false,
			    drag: {
				  isCopy:true,
				  isMove:false,
			    }
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrop: MoveFlow.beforeDrop,
				beforeDrag: MoveFlow.dragTree2Dom,
				onDragMove: MoveFlow.dragMove,
				onMouseUp: MoveFlow.dom2Tree,
				onExpand:function(e,treeId,treeNode){  
		            var treeObj = $.fn.zTree.getZTreeObj(treeId);  
		            var id=treeNode.id 
		            if(treeNode.children == null || treeNode.children == "undefined"){  
	            		$.getJSON(ctx+'/fiow/planFlowManage/planActionTree',{'id':id},function(data){	
	            			if(data!=null && data !=""){  
						    	var newNode = treeObj.addNodes(treeNode,data);
				    		}
				    	})
		            }
				},
				onRemove:function(e, treeId, treeNode) {
					var jsonDelAction = {'stepId':treeNode.pId,'actionId':treeNode.id};
					var paramDelStepAction = encodeURI(JSON.stringify(jsonDelAction));
					$.post(ctx+'/fiow/planFlowManage/deleteBphStepAction',{'paramDelStepAction':paramDelStepAction},function(){
					})
				    //需要对删除做判定或者其它操作，在这里写~~
				}
			}
		};
		//过程树
	   /*$.getJSON(ctx+'/fiow/planFlowManage/stepActionList',function(data){
	     	for (i in data){
				if(data[i].typeClass=='step'){
					data[i].drop=false;
				}else{
					data[i].drop=false;
					data[i].drop=false;
				}
			}
			$.fn.zTree.init($("#StepTree"), StepSetting,data);
		})*/
		$.getJSON(ctx+'/fiow/planFlowManage/planStepTree',function(data){
	     	for (i in data){
				if(data[i].typeClass=='step'){
					data[i].drop=false;
					data[i].isParent=true;
				}else{
					data[i].drop=false;
					data[i].drop=false;
					data[i].isParent=true;
				}
			}
			$.fn.zTree.init($("#StepTree"), StepSetting,data);
		})
		$.getJSON(ctx+'/fiow/planFlowManage/actionList',function(data){
			var len=data.length;
			var html='';
			if(len>0){
				for(var i=0;i<len;i++){
					html+='<span class="domBtn" style="display:block;width:120px;height:20px;white-space: nowrap;text-overflow: ellipsis;overflow:hidden;" title="'+data[i].name+'"  domId="'+data[i].id+'">'+data[i].name+'</span>'
				}
			}
           $('#dom_1').html(html);
		})
}
//是否显示删除按钮
function StepshowRemoveBtn(treeId, treeNode){
	//获取节点所配置的noRemoveBtn属性值
	if(treeNode.level=="0"){
		return false;
	}else{
		return true;
	}
}
function PlanshowRemoveBtn(treeId, treeNode){
	//获取节点所配置的noRemoveBtn属性值
	if(treeNode.level=="1"){
		return true;
	}else{
		return false;
	}
}