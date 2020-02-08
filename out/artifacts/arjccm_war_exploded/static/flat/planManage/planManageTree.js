var settingVideo = {
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			var id = treeNode.id;
			if(treeNode.type != 'camera') {
				return;
			}
			videoLocation(id);
		}
	}
};
var settingResultVideo = {
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			var id = treeNode.id;
			Map.goTo([Number(treeNode.x),Number(treeNode.y)]);
		}
	}
};
//全市警力-视频
function refreshVideoTree(){
	$.getJSON(ctx+"/ccmsys/ccmLiveVideo/treeData",function(data){
		$.fn.zTree.init($("#ztreeVideo"), settingVideo, data);
	});
}

var settingCarDev = {
	view: {
        showLine: false//设置 zTree 是否显示节点之间的连线。默认是true
	},
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			var id = treeNode.id;
			carLocation(id);
		}
	}
};
var settingResultCarDev = {
	view: {
        showLine: false//设置 zTree 是否显示节点之间的连线。默认是true
	},
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			Map.goTo([Number(treeNode.x),Number(treeNode.y)]);
		}
	}
};
var settingPople = {
	view: {
        showLine: false//设置 zTree 是否显示节点之间的连线。默认是true
	},
	check : {
		enable : true,
		chkStyle : "checkbox",
	},
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			var id = treeNode.id == '-1' ? '' :treeNode.pId;
			if(treeNode.type != 'camera') {
				return;
			}
		}
	}
};
var settingResultPople = {
	view: {
        showLine: false//设置 zTree 是否显示节点之间的连线。默认是true
	},
	check : {
		enable : true,
		chkStyle : "checkbox",
	},
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			Map.goTo([Number(treeNode.x),Number(treeNode.y)]);
		}
	}
};
//全市警力-警车
var carData = [];
function findCarDevice(){
	$.getJSON(ctx+"/flat/realtimeSituation/findCarDevice",function(data){
		carData.data=data;
		$.fn.zTree.init($('#carAllUl'),settingCarDev,data);
	});
}
//警车定位
function carLocation(id){
	for(var i = 0;i<carData.data.length;i++){
		if(id == carData.data[i].id){
			var x=carData.data[i].x;
			var y=carData.data[i].y;
			var point = {
				"type":"FeatureCollection",
		        "features":[{
		        	"type":"Feature",
		        	"id":carData.data[i].id,
		        	"properties":{
		        		"name": carData.data[i].name,
		        		"icon": 'c1.png',
		        		'type' : 'vlc',//自定义type类型,判断是有视频流
	             		'vlc':{
	             			"id":carData.data[i].id,
	             			'src':carData.data[i].param,
	             		},
	            		"info":{
	            			'设备名称':carData.data[i].name,
	            			'设备编号':carData.data[i].code,
	            			'最大速度':carData.data[i].SMax,
	            			'类型':carData.data[i].typeName,
	            			'经度':x,
	            			'维度':y,
	            		}
		        	},
		        	"geometry":{
		        		"type":"Point",
		        		"coordinates":[x,y]
		        	 }
		        	}]
		        }
		    Map.removeLayer('car');//清除
			Map.addJSON1([ {
				'type' : 'car',
				'data' : point,
				'isShow' : true
			} ])
			Map.goTo([Number(x),Number(y)]);
		}
	}
}
//视频定位
function videoLocation(id){
	$.getJSON(ctx+"/ccmsys/ccmLiveVideo/findVideoInfo",{'id':id},function(data){
		if(data.coordinate == '' || data.coordinate == undefined || data.coordinate == null){
			$.jBox.tip('暂无当前位置信息');
			return;
		}
		var x=data.coordinate.split(',')[0];
		var y=data.coordinate.split(',')[1];
		var point = {
			"type":"FeatureCollection",
	        "features":[{
	        	"type":"Feature",
	        	"id":data.id,
	        	"properties":{
	        		"name": data.name,
	        		"icon": 'video.png',
	        		'type' : 'video',//自定义type类型,判断是视频监控
             		'video':{
             			"id":data.id,
             			'ip':data.ip,
             		},
            		"info":{
            			'设备名称':data.name,
            			'设备编号':data.code,
            			'IP地址':data.ip,
            			'设备地址':data.address,
            			'经度':x,
            			'维度':y,
            		}
	        	},
	        	"geometry":{
	        		"type":"Point",
	        		"coordinates":[x,y]
	        	 }
	        }]
	    }
	    Map.removeLayer('videos');//清除
		Map.addJSON1([ {
			'type' : 'videos',
			'data' : point,
			'isShow' : true
		} ])
		Map.goTo([Number(x),Number(y)]);
	});
}
var key, lastValue = "",  teamType="",nodeList = [], type = getQueryString("type", "/sys/office/treeData?type=3");

function onmPoliceClick(_this){
	var id = "u_"+$(_this).attr('data-id');
	var name = $(_this).attr('data-name');
	$('#dispatch').append('<span style="cursor:pointer" onclick="" class="userId" type="hidden" id="sp'+id+'" userId="'+id+'">'+name+'<i onclick="removePeopleId(\''+id+'\')">x</i></span>');
}
//取消ztree出警人员
function removeZtreeId(id){
	var zTreeObj = $.fn.zTree.getZTreeObj("ztreePolice");
	var popNode = zTreeObj.getNodeByParam("id", id, null);
	zTreeObj.checkNode(popNode, false);
	removePeopleId(id);
}
//删除出警人员
function removePeopleId(id){
	$("#sp" + id).remove();
}
//出警按钮方法
function policeClick(){
	if(alarmId==''||alarmId==undefined){
		$.jBox.tip('请选择警情');
		return false;
	}
	var strId='';
	$('.userId').each(function(){
		var id=$(this).attr('userId');
		strId+=id.substring(2,id.length)+',';
	});
	if(strId==''){
		$.jBox.tip('请选择人员');
		return false;
	}
	strId=strId.substring(0,strId.length-1);
	var html='';
	html+='<div id="policeData" class="policeSenData">';
	html+='<div class="control-group" style="margin-top: 10px;">';
	html+='<label class="control-label" style="margin-left: 15px;">目的地</label>';	
	html+='<div class="controls" style="display: inline-block;">';
	html+='<input class="input-medium" id="destinyX" type="text" readonly="readonly" value="112.4154521" style="width:160px;height:30px;margin-left: 20px;" onmouseover="this.title=this.value"/>';	
	html+='<input class="input-medium" id="destinyY" type="text" readonly="readonly" value="112.4154521" style="width:160px;height:30px;"/>';		
	html+='<i class="icon-map-marker" id="destinLocation" onclick="locationP_er_destiny(this)"   style=" font-size: 16px;  margin-left: 10px;cursor: pointer;"></i>';		
	html+='</div>';	
	html+='</div>';
	html+='<div class="control-group" style="margin-top: 10px;">';
	html+='<label class="control-label" style="padding-top: 20px;margin-left: 15px;">出警安排</label>';	
	html+='<div class="controls"  style="display: inline-block;">';	
	html+='<textarea id="taskTextarea" style="margin-left: 8px;width:324px;" name="eduhistory" align="center"></textarea>';	 	
	html+='</div>';	
	html+='</div>';
	html+='</div>';
	layer.open({
		type : 1,
		title : "出警信息安排",
		area : [ "445px", "230px"],
		offset : [ "0px", "1220px" ],
		maxmin : false,
		shade: 0,
		anim: 0,
		id:'send',
		btn : [ "发送", "取消" ], // /可以无限个按钮
		content :html,
		end : function() {
			$("#btncontrolpolice").removeAttr("disabled");
		},
		yes : function(index, layero) {
			send(strId);
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
			$("#btncontrolpolice").removeAttr("disabled");
			$('#dispatch').html('');
			var zTreeObj = $.fn.zTree.getZTreeObj("ztreePolice");
			zTreeObj.checkAllNodes(false);
		}
	});
	$("#btncontrolpolice").attr("disabled","disabled");
	$('#destinLocation').attr('data-id',alarmId); 
	$('#destinyX').val(x);
	$('#destinyY').val(y);
}

function send(strId){
	var destinyX=$('#destinyX').val();
	var destinyY=$('#destinyY').val();
	var _alarmId=alarmId;
	var task=$('#taskTextarea').val();
	var optionDesc = "发送警情";
	var param={'type':2,'userId':strId,'alarmId':_alarmId,'task':task,'destinyX':destinyX,'destinyY':destinyY,'optionDesc':optionDesc}
	$.post(ctx+'/handle/bphAlarmHandle/sendAlarmInfo',param,function(data){
	})
}
//全市警力-警力
var tree,  settingPolice;
function refreshPoliceTree(){
	tree,  settingPolice = {
		data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType :{ "Y" : "s", "N" : "s" }
		},
		async:{
			enable:(type==3),
			url:ctx+"/org/ccmOrgTeam/treeData?teamType="+teamType
			,autoParam:["id=officeId"],
			dataFilter:function(treeId, parentNode, responseData) {
			    if (responseData) {
			        for(var i =0; i < responseData.length; i++) {
			        	if(responseData[i].loginState=='online'){
			        		if(responseData[i].userState=='busy'){
			        			responseData[i].name += "[忙碌]";
			        		}else{
			        			responseData[i].name += "[备勤]";
			        		}
			        	}else{
			        		responseData[i].name += "[离线]";
			        	}
			        }
			        return responseData;
			    }
			}
		},
		callback:{
			onCheck: function(e, treeId, treeNode){
				var nodes = tree.getCheckedNodes(true);
				$('#dispatch').html('');
				for (var i=0, l=nodes.length; i<l; i++) {
					tree.expandNode(nodes[i], true, false, false);
					var id=nodes[i].id;
					if(id.indexOf("u_")>-1){//'<>' + item.police_id + '</span>'
						$('#dispatch').append('<span style="cursor:pointer" onclick="" class="userId" type="hidden" id="sp'+nodes[i].id+'" userId="'+nodes[i].id+'">'+nodes[i].name+'<i onclick="removeZtreeId(\''+nodes[i].id+'\')">x</i></span>');
					}
				}
				return false;
			},
			onAsyncSuccess: function(event, treeId, treeNode, msg){
				var nodes = tree.getNodesByParam("pId", treeNode.id, null);
				for (var i=0, l=nodes.length; i<l; i++) {
					try{tree.checkNode(nodes[i], treeNode.checked, true);}catch(e){}
					//tree.selectNode(nodes[i], false);
				}
				//selectCheckNode();
			}
		}
	};
	$.getJSON(ctx+"/sys/office/treeData?type=3&&extId=&isAll=&module=&t="+ new Date().getTime(),function(data){
		tree =$.fn.zTree.init($("#ztreePolice"), settingPolice, data);
		// 默认展开一级节点
		var nodes = tree.getNodesByParam("level", 0);
		for(var i=0; i<nodes.length; i++) {
			tree.expandNode(nodes[i], true, false, false);
		}
		//异步加载子节点（加载用户）
		var nodesOne = tree.getNodesByParam("isParent", true);
		for(var j=0; j<nodesOne.length; j++) {
			tree.reAsyncChildNodes(nodesOne[j],"!refresh",true);
		}
	});
}
//出警定位
function locationP_er_destiny(_this){
	var id = $(_this).attr('data-id');
	Map.SecondlocationTwo(id,locationP_eerPointr_destin);
}
function locationP_eerPointr_destin(point,id){
	//更新出警警情坐标
	var x=point[0];
	var y=point[1];
	$('#destinyX').val(x);
	$('#destinyY').val(y);
}