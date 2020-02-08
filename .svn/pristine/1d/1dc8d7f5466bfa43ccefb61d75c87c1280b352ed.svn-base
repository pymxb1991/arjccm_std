//预案管理
var Map,map;
var CacleLocationAlarmId=[];
$(document).ready(function () {
	//初始化地图
	initMap();
	//初始化案事件类型
	getAlarmType();
	//初始化警情
	initAlarm();
	//案件流程记录--同过程记录
	findHanleLogFlow();
	//加载当前警情执行的动作
	queryStatus();
	//根据警情类型-加载周边资源  by pengjq 2019-03-27
	loadingPerResource();
	planProcessData();
	if(alarmId === undefined || alarmId == null || alarmId == ''){
		$.jBox.tip('请选择警情！！');
		for(var i = 1; i < 5;i++){
			$(".ic"+i).click();
		}
	}
	plotDrawInit();//标绘初始化
	liveStreamingData();
});
function loadingPerResource(){
	
	//所有类型都显示的资源
	shipinjiankongFun(this);
	jingyuanFun(this);
	
	if (typeCode == 01) {
		$('#v_gasStation').hide();
		$('#v_entertainment').hide();
		$('#v_hotel').hide();
		$('#v_danger').hide();
		var v_width = $('#v_videoMon').width();
		var div_width = $('#onparentdiv').width();
		var r_width = div_width - v_width*4;
		$('#onparentdiv').width(r_width);
		xuexiaoFun(this);
		yiyuanFun(this);
		jingcheFun(this);
		shangchangFun(this);
		jingwushiFun(this);
		gongzuozhanFun(this);
	}
	
	if (typeCode == 04){
		xuexiaoFun(this);
		yiyuanFun(this);
		jingcheFun(this);
		jiayouzhanFun(this);
		shangchangFun(this);
		yuleFun(this);
		binguanFun(this);
		sheweishebaoFun(this);
		jingwushiFun(this);
		gongzuozhanFun(this);
	}
	if (typeCode == 05){
		$('#v_scoole').hide();
		$('#v_gasStation').hide();
		$('#v_entertainment').hide();
		$('#v_store').hide();
		$('#v_hotel').hide();
		$('#v_danger').hide();
		var v_width = $('#v_videoMon').width();
		var div_width = $('#onparentdiv').width();
		var r_width = div_width - v_width*6;
		$('#onparentdiv').width(r_width);
		yiyuanFun(this);
		jingcheFun(this);
		jingwushiFun(this);
		gongzuozhanFun(this);
	}
}
function initMap() {
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomIndex,
		maxZoom : 20,
		minZoom : 9.8,
		baseUrl : baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag : true
	// 鹰眼图
	}
	Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();
	map=Map.map;
	var langxiStyles = new ol.style.Style({
        fill: new ol.style.Fill({
            color: 'rgba(245, 0, 255, 0)'
        }),
        stroke: new ol.style.Stroke({
            color: '#0099ff',
            width: 1
        })
    }); 
	var langxi = new ol.source.Vector({
        url: ctxStatic+'/flat/mapdata/langxi.kml',
        format: new ol.format.KML({
            extractStyles: false //至关重要
        }),
        projection: 'EPSG:4326'
    });
    Map['langxi']= new ol.layer.Vector({
        source: langxi,
        style: langxiStyles
    });
   map.addLayer( Map['langxi']);
}  
var alarmId='',planId='',typeCode='',stepId='',actionId='',x='',y='',alarmData;
function initAlarm(){
	alarmId=sessionStorage.alarmId;
	planId=sessionStorage.planId;
	typeCode=sessionStorage.typeCode;
	if(alarmId!=''&&alarmId!=undefined){
		//$('#alarmInfoDiv').height($('.left-side').height()-$('#planInfoDiv').height()-$('#contactInfoDiv').height()-5);
		$('#alarmInfoDivDetails').height($('.left-side').height()-$('#planInfoDiv').height()-$('#contactInfoDiv').height()-$('#alarmInfoDiv h5').height()-10);
		$.getJSON(ctx+'/flat/planManage/alarmDetail',{'id':alarmId},function(data){
			alarmData=data;
			x = data.x;
			y = data.y;
			var html='';
			var displayFlag='none';
			var alarmType='1';
			var alarmTypeText='重大警情';
			if(data.isImportant=='1'){//是否为重大警情
				displayFlag='inline';
				alarmType='0';
				alarmTypeText='普通警情';
			}else{
				displayFlag='none';
				alarmType='1';
				alarmTypeText='重大警情';
			}
			html+='<table class="location_'+data.id+'" xNum="'+x+'" yNum="'+y+'">';
			var orderNum = data.orderNum;
		    if(orderNum == "" || orderNum == null || orderNum == undefined){
		    	orderNum = "";
		    }
			html+='<tr><td>接警单编号：</td><td>'+orderNum+'</td></tr>';
			html+='<tr><td>报警时间：</td><td id="dataAlarmTime">'+data.alarmTime+'</td></tr>';
			html+='<tr><td>警情类型：</td><td>'+ArjAlarmType[data.typeCode]+'<b class="major" style="background: #f13f40; margin-left: 6px; padding: 3px; border-radius: 4px; color: #fff;display:'+displayFlag+'">重大</b></td></tr>';
			html+='<tr><td>案发地点：</td><td>'+data.place+'</td></tr>';
			var officeName = data.officeName === undefined ? '' : data.officeName;
			html+='<tr><td>处理单位：</td><td>'+officeName+'</td></tr>';
			html+='<tr><td>警情状态：</td><td>'+stateChange(data.state)+'</td></tr>';
			html+='<tr><td>报警人姓名：</td><td>'+data.manName+'</td></tr>';
			html+='<tr><td>报警人性别：</td><td>'+sexChange(data.manSex)+'</td></tr>';
			html+='<tr><td>报警人电话：</td><td>'+data.manTel+'</td></tr>';
			var policeName = data.policeName;
		    if(policeName == "" || policeName == null || policeName == undefined){
		    	policeName = "";
		    }
			html+='<tr><td>接警员姓名：</td><td>'+policeName+'</td></tr>';
		/*	html+='<tr><td>接警录音：</td><td class="clearfix"><audio  src="'+data.alarmRecord+'" preload="auto" controls></audio></td></tr>';*/
			html+='<tr><td>警情内容：</td><td><span style="display:block;width:160px;height:52px;display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:3;overflow:hidden;cursor:pointer;" title="'+data.content+'">'+data.content+'</span></td></tr>';
			html+='</table>';
			$('.details').html(html);
			//赋值周边查询坐标
			$('#btnSubmit').attr('xNum',data.x);
		    $('#btnSubmit').attr('yNum',data.y);
		    //初始化audio
			audioInit();
		    //警情定位
		    locationPInfo(data);
			//默认周边查询
			yesNearSearch();
		})
	}
}

//过程记录
function findHanleLog(){
	var html="";
	var	title = "过程记录";
	var	width = "1000px";
	var	height = "618px";
	var	offsetWidth = "80px";
	var	offsetHeight = "450px";
	var	cancel = "关闭";
	$.getJSON(ctx+'/flat/planManage/findHandleLog',{'alarmId':alarmId},function(data){
		var len=data.length;
		if(len>=0){
			var html='<div class="container"><div class="row"><div class="col-md-12"><div class="VivaTimeline"><dl><dt>过程记录</dt>';
			for(var i=0;i<len;i++){
				if(data[i].user){
				if(i%2==0){
					html+='<dd class="pos-left clearfix"><div class="circ"></div><div style="width: 170px;" class="time">'+(data[i].operateTime==undefined?"":data[i].operateTime)+'</div><div class="events"><div class="events-header"><p>'+(data[i].user.name==undefined?"":data[i].user.name)+':'+(data[i].operateDesc==undefined?"":data[i].operateDesc)+'</p></div></div></dd>';
				}else{
					html+='<dd class="pos-right clearfix"><div class="circ"></div><div style="width: 170px;padding-right: 20px;margin-left: -170px;" class="time">'+(data[i].operateTime==undefined?"":data[i].operateTime)+'</div><div class="events"><div class="events-header"><p>'+(data[i].user.name==undefined?"":data[i].user.name)+':'+(data[i].operateDesc==undefined?"":data[i].operateDesc)+'</p></div></div></dd>';
					}
				}
			}
			html+='</dl></div></div></div></div>';
			findHandle(html,title,width,height,offsetWidth,offsetHeight,cancel);
		}
	});
}

function liveStreamingData(){
	$.getJSON(ctx+'/flat/planManage/findHandleLog',{'alarmId':alarmId},function(data){
		var len=data.length;
		var html='';
		if(len>=0){
			for(var i=0;i<len;i++){
				if(data[i].user){
				html+='<li><div class="thumb">';
				html+='<img src="'+(data[i].user.photo==undefined?"":data[i].user.photo)+'"/></div>';
				html+='<div class="content"><b></b>';
                html+='<h3>'+(data[i].user.name==undefined?"":data[i].user.name)+'</h3>';
				html+='<p>'+data[i].operateDesc+'</p>';
				html+='<span>'+(data[i].operateTime==undefined?"":data[i].operateTime)+'</span></div></li>';
				}
			}
			$('#timeline').html(html);
		}
	});
}

var FlowData=[],layuiTable='';
function findHanleLogFlow(){
	$.getJSON(ctx+'/flat/planManage/findHandleLog',{'alarmId':alarmId},function(data){
		var len=data.length;
	    FlowData=[];
		if(len>0){
			for(var i=0;i<len;i++){
				if(data[i].user){
				FlowData.push({
	                'operateD':'调派内容:',
					'operateDesc':data[i].operateDesc,
					'userN':'操作人:',
					'userName':data[i].user.name,
					'operateT':'调派时间:',
					'operateTime':data[i].operateTime,
				})
				}
			}
		}	
		layui.use('table', function(){
			layuiTable = layui.table;
			  //展示已知数据
			layuiTable.render({
			    elem: '#flowTable'
			    ,height : '195px'
			    ,cols: [[ //标题栏
			    {field: 'operateD', title: '', width: '10.6%'},
			    {field: 'operateDesc', title: '', width: '22.6%'}
			    ,{field: 'userN', title: '', width: '10.6%'}
			    ,{field: 'userName', title: '', width: '22.6%'}
			    ,{field: 'operateT', title: '',width: '10.6%'}
			    ,{field: 'operateTime', title: '',width: '22.6%' }
			    ]]
			    ,data: FlowData
			    //,skin: 'line' //表格风格
			    ,even: true
			    ,done:function(res, curr, count){
			    	 $('.bottom-table').find('.layui-table-header').css("display","none");
			    }
			});
		});
	})
}
//过程
function planProcessData(){
	$.post(ctx+'/flat/planManage/planProcessData',{'planId':planId},function(data){
		var data = JSON.parse(data);
		console.log(data)
		//动作关联
		var html = '';
		//案件流程预案
		var flowHtml='';
		flowHtml+='<div class="step-hap">事件发生</div>';
		flowHtml+='<div class="step-hap-arrow"><i class="iconfont icon-314"></i></div>'; 
		flowHtml+='<div class="step-name" style="">警情核实</div>';
		flowHtml+='<div class="step-arrow"><i class="iconfont icon-jiantou2"></i></div>'; 
		for(var i = 0;i < data.length;i++){
			//动作关联
			 html+='<li id="'+(data[i].id || "")+'" data-stepId="'+(data[i].id || "")+'" class="select" onclick="planActionData(this)" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;max-width: 70px;display: block;" title="'+(data[i].name || "")+'">'+(data[i].name || "")+'</li>';
			    //案件流程预案
			 flowHtml+='<div class="step-name-cus">'+(data[i].name || "")+'</div>'; 
			 flowHtml+='<div class="step-arrow"><i class="iconfont  icon-jiantou2"></i></div>';
		}
		flowHtml+='<div class="step-name-sus">警情归档</div>';
		flowHtml+='<div class="step-arrow"><i class="iconfont  icon-jiantou2"></i></div>'; 
		flowHtml+='<div class="step-name-sus">指挥结束</div>'; 
		$('#planInfoLi').html(html);
		$("#planInfoLi li:first-child").click();
		$('#bottom-flow').html(flowHtml);
	})
}
//步驟动作
function planActionData(_this){
	$('#planInfoLi li').removeClass('selected');
	$(_this).addClass('selected');
	stepId=$(_this).attr('id');
	$.getJSON(ctx+'/flat/planManage/planActionData',{'stepId':stepId},function(data){
		var html = '';
		html+='<div>操作步骤</div>';
		for(var i = 0;i < data.length;i++){
			var ActionBackground='#fa575b';
			var CacleActiontStatus='';
			var actiontflag=1;
			var statusHtml='';
			var id=data[i].bphActionInfo.id;
			var alarmType=data[i].bphActionInfo.type;
			for(var j = 0;j < stepActionArr.length;j++){
				if(stepActionArr[j].stepId == stepId){
					if(stepActionArr[j].actionId == id){
						ActionBackground='green';
						CacleActiontStatus = stepActionArr[j].actionStatus;
						actiontflag=0;
						switch(CacleActiontStatus) {
							case '未处理':
								statusHtml='<span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
								break;
							case '已签收':
								statusHtml='<span class="major" style="background: #f9d04b; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
								break;
							case '已到达':
								statusHtml='<span class="major" style="background: #5cb1ea; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
								break;
							case '已反馈':
								statusHtml='<span class="major" style="background: #258233; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
								break;
							case '未阅读':
								statusHtml='<span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
								break;
							case '已阅读':
								statusHtml='<span class="major" style="background: #258233; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
								break;


						}
						/*if(CacleActiontStatus == '未处理'){
							statusHtml='<span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
						}else if(CacleActiontStatus == '已签收'){
							statusHtml='<span class="major" style="background: #f9d04b; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
						}else if(CacleActiontStatus == '已到达'){
							statusHtml='<span class="major" style="background: #5cb1ea; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
						}else if(CacleActiontStatus == '已反馈'){
							statusHtml='<span class="major" style="background: #258233; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+data[i].bphActionInfo.type+'" data-id="'+id+'" onclick="planManagerDetails(this)">'+CacleActiontStatus+'</span>';
						}*/
					}
				}
			}
			html+='<p><span  actiontflag="'+actiontflag+'" type="'+data[i].bphActionInfo.type+'" data-name="'+data[i].bphActionInfo.name+'" ' +	'onclick="startAction(this)"  data-id="'+id+'" data-data="'+JSON.stringify(data[i].bphActionInfo).replace(/"/g, '&quot;')+'">' +
					   '<span class="zhi" style="background-color:'+ActionBackground+'">' +'<span>执行</span>' +'</span>' +
				       '<b style="width:86px;" title="'+data[i].bphActionInfo.name+'">'+data[i].bphActionInfo.name+'</b>' +
				       '</span>'+statusHtml+'' +
				   '</p>';
		}
		$('#stepInfoDiv').html(html);
	})
}
//弹框方法
function layerData(html,title,width,height,offsetWidth,offsetHeight,affirm,cancel){
	layer.open({
		type : 1,
		title : title,
		area : [ width, height ],
		offset : [ offsetWidth, offsetHeight ],
		maxmin : false,
		btn : [ affirm, cancel ], // /可以无限个按钮
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}
//警情详情
function getAlarmDetails(_this){
	//var id=$(_this).attr('data-id');
	var id=alarmId;
	$.post(ctx+"/alarm/bphAlarmInfo/alarmDetail",{'id':id},function(datas){
		var data = datas.alarmInfo;
		var alarmHandleList = datas.alarmHandleList;
		var alarmDetail = '';
		if(data!=''){
			alarmDetail += '<table class="table table-striped table-bordered table-condensed">'; 
			alarmDetail += '<tr>';
			alarmDetail += '<td>接警单编号：</td><td>'+data.orderNum+'</td>';
			alarmDetail += '<td>报警人姓名：</td><td>'+data.manName+'</td>';
			alarmDetail += '</tr>';
			alarmDetail += '<tr>';
			alarmDetail += '<td>接警员姓名：</td><td>'+data.policeName+'</td>';
			alarmDetail += '<td>报警人性别：</td><td><input name="manSex" type="radio" value="0"/>男<input name="manSex" type="radio" value="1"/>女</td>';
			alarmDetail += '</tr>';
			alarmDetail += '<tr>';
			alarmDetail += '<td>接警员警号：</td><td>'+data.policeNum+'</td>';
			alarmDetail += '<td>报警人联系电话：</td><td><input id="manTel" type="text" style="width:220px;height:25px;" value="'+data.manTel+'" onmouseleave="normalImg(this)" /><p id="titleNull"  style="display:none;"><span class="help-inline"><font color="red">*请输入正确的电话号码</font> </span></p></td>'; 
			alarmDetail += '</tr>'; 
			alarmDetail += '<tr>'; 
			alarmDetail += '<td>案发地点：</td><td><span style="display:block;width:220px;height:20px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;" title="'+data.place+'">'+data.place+'</span></td>';
			alarmDetail += '<td>报警方式：</td><td id="alarmFromTd"></td>';
			alarmDetail += '</tr>'; 
			alarmDetail += '<tr>';
			alarmDetail += '<td>警情类型：</td><td id="alarmTypeTd"></td>';
			alarmDetail += '<td>报警时间：</td><td>'+getDay(data.alarmTime)+'</td>';
			alarmDetail += '</tr>';
			alarmDetail += '<tr>';
			alarmDetail += '<td>警情类别：</td><td id="alarmClassTd"></td>';
			alarmDetail += '<td>警情状态：</td><td>'+stateChange(data.state)+'</td>';
			alarmDetail += '</tr>';
			alarmDetail += '<tr>';
			alarmDetail += '<td>警情内容：</td><td><span style="display:block;width:220px;height:20px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;" title="'+data.content+'">'+data.content+'</span></td>';
			alarmDetail += '<td>所属区域：</td><td><input id="areaId" areaIdValue="'+(data.area?data.area.id:"")+'" type="text" style="width:220px;height:25px;" value="'+(data.area?data.area.name:"")+'"/><a id="areaButton" href="javascript:" class="btn" style="">&nbsp;<i class="icon-search"></i>&nbsp;</a></td>';
			alarmDetail += '</tr>';
			alarmDetail += '<tr>';
			alarmDetail += '<td>是否为重大警情：</td><td>'+isImportantChange(data.isImportant)+'</td>';
			alarmDetail += '<td>案发经纬度：</td><td>'+(data.x?data.x:"0")+','+(data.y?data.y:"0")+'</td>';
			alarmDetail += '</tr>';
/*			alarmDetail += '<tr>';
			alarmDetail += '<td>接警录音：</td><td colspan="3"> <audio style="display:none"  preload="auto" id="" src="'+ctxStatic+'/images/jq110.mp3" controls="controls"></audio></td>';
			alarmDetail += '</tr>';*/
			alarmDetail += '</table>';
			alarmDetail += '<span>处警详情</span>';
			alarmDetail += '<table class="table table-striped table-bordered table-condensed">'; 
			alarmDetail += '<tr>';
			alarmDetail += '<td>处警人姓名</td>';
			alarmDetail += '<td>任务描述</td>';
			alarmDetail += '<td>反馈信息</td>';
			alarmDetail += '<td>处置状态</td>';
			alarmDetail += '<td>执行预案</td>';
			alarmDetail += '<td>执行过程</td>';
			alarmDetail += '<td>执行动作</td>';
			alarmDetail += '</tr>';
			if(alarmHandleList !== undefined && alarmHandleList.length > 0){
				for (var i = 0; i < alarmHandleList.length; i++) {
					alarmDetail += '<tr>';
					alarmDetail += '<td>'+alarmHandleList[i].user.name+'</td>';
					alarmDetail += '<td>'+alarmHandleList[i].task+'</td>';
					alarmDetail += '<td>'+alarmHandleList[i].handleResult+'</td>';
					if(alarmHandleList[i].status=='0'){
						alarmDetail +='<td><span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">未处理</span></td>';
					}else if(alarmHandleList[i].status=='1'){
						alarmDetail += '<td><span class="major" style="background: #f9d04b; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">已签收</span></td>';
					}else if(alarmHandleList[i].status=='2'){
						alarmDetail += '<td><span class="major" style="background: #5cb1ea; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">已到达</span></td>';
					}else if(alarmHandleList[i].status=='3'){
						alarmDetail += '<td><span class="major" style="background: #258233; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">已反馈</span></td>';
					}else{
						alarmDetail += '<td><span></span></td>';
					}
					if(alarmHandleList[i].plan !== undefined && alarmHandleList[i].plan != null && alarmHandleList[i].plan != ""){
						alarmDetail += '<td>'+alarmHandleList[i].plan.name+'</td>';
					}else{
						alarmDetail += '<td></td>';
					}
					if(alarmHandleList[i].step !== undefined && alarmHandleList[i].step != null && alarmHandleList[i].step != ""){
						alarmDetail += '<td>'+alarmHandleList[i].step.name+'</td>';
					}else{
						alarmDetail += '<td></td>';
					}
					if(alarmHandleList[i].action !== undefined && alarmHandleList[i].action != null && alarmHandleList[i].action != ""){
						alarmDetail += '<td>'+alarmHandleList[i].action.name+'</td>';
					}else{
						alarmDetail += '<td></td>';
					}
					alarmDetail += '</tr>';
				}
			}
			alarmDetail += '</table>';
		}else{
			$.jBox.tip('暂无警情详情！！');
			return false;
		}
		
		layer.open({
			type : 1,
			title : "警情详情",
			area : [ "900px", "540px" ],
			offset : [ "100px", "500px" ],
			maxmin : false,
			btn : [ "保存", "取消" ], //
			content :alarmDetail,
			cancel : function() {
			},
			end : function() {
			},
			yes : function(index, layero) {
				var manName=$('#manName').val();//报警人姓名
				var manSex=$('input[name="manSex"]:checked').val();//报警人性别
				var manTel=$('#manTel').val();//报警人联系电话
				var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
				if (!myreg.test(manTel)) {
					document.getElementById("titleNull").style.display = ""; 
					return false;
				} else {
					document.getElementById("titleNull").style.display = "none"; 
				}
				var z = $("#z").val();//高度
				var typeCode=$('#alarmType').val();//警情类型
				var areaId=$("#areaId").attr("areaIdValue");//所属区域
				var alarmFrom = $("#alarmFrom").val();//报警方式
				/*var alarmRecord = $("#alarmRecord").val();//接警录音号*/
				var content=$('#cont').val();//警情内容
				var place=$('#place').val();//案发地点
				var json={
					'id':id,
					'manName':manName,
					'manSex':manSex,
					'manTel':manTel,
					'z':z,
					'typeCode':typeCode,
					'area.id':areaId,
					'alarmFrom':alarmFrom,
					/*'alarmRecord':alarmRecord,*/
					'content':content,
					'place':place,
					'optionDesc':'修改警情信息'
				};
				updateAlarmDetails(json);
				layer.close(index); // 如果设定了yes回调，需进行手工关闭
			}
		});
		$('input[name="manSex"]').each(function(){
			if($(this).val()==data.manSex){
				$(this).attr('checked',true);
			}
		})
		
		//警情类型下拉框
		var alarmTypeSelectNode = $("<select id='alarmType'></select>");
		//警情类别下拉框
		var alarmClassSelectNode = $("<select id='alarmClass'></select>");
		$("#alarmTypeTd").append(alarmTypeSelectNode);
		$.getJSON(ctx+'/sys/dict/listData?type=bph_alarm_info_typecode',function(datas){
			for(var i=0;i<datas.length;i++){
				if(data.typeCode == datas[i].value){
					alarmTypeSelectNode.append("<option data-id='"+datas[i].id+"' value='"+datas[i].value+"' selected='selected'>"+datas[i].label+"</option>");
				}else{
					alarmTypeSelectNode.append("<option data-id='"+datas[i].id+"' value='"+datas[i].value+"'>"+datas[i].label+"</option>");
				}
			}
			var parentId = $("#alarmType").children('option:selected').attr("data-id");
			$.getJSON(ctx+'/sys/sysDict/getDictListByParentId?type=bph_alarm_info_class&parentId='+parentId,function(datas){
				for(var i=0;i<datas.length;i++){
					if(data.genreCode == datas[i].value){
						alarmClassSelectNode.append("<option value='"+datas[i].value+"' selected='selected'>"+datas[i].label+"</option>");
					}else{
						alarmClassSelectNode.append("<option value='"+datas[i].value+"'>"+datas[i].label+"</option>");
					}
				}
			});
			$("#alarmClassTd").append(alarmClassSelectNode);
		})
		
		$("#alarmType").change(function(){
			var alarmClassSelectOptions = $("#alarmClass")[0].options;
			for(var i in alarmClassSelectOptions){
				alarmClassSelectOptions.remove(0);
			}
			var alarmTypeId = $(this).children('option:selected').attr("data-id");
			$.getJSON(ctx+'/sys/sysDict/getDictListByParentId?type=bph_alarm_info_class&parentId='+alarmTypeId,function(datas){
				for(var i=0;i<datas.length;i++){
					alarmClassSelectNode.append("<option value='"+datas[i].value+"'>"+datas[i].label+"</option>");
				}
			});
		});
		
		//所属区域树形
		$("#areaButton").on("click",function(){
			$("#areaSelectButton").trigger('click');
		});
		//报警方式下拉框
		var alarmFromSelectNode = $("<select id='alarmFrom'></select>");
		$.getJSON(ctx+'/sys/dict/listData?type=bph_alarm_from',function(datas){
			for(var i=0;i<datas.length;i++){
				if(data.alarmFrom == datas[i].value){
					alarmFromSelectNode.append("<option value='"+datas[i].value+"' selected='selected'>"+datas[i].label+"</option>");
				}else{
					alarmFromSelectNode.append("<option value='"+datas[i].value+"'>"+datas[i].label+"</option>");
				}
			}
		})
		$("#alarmFromTd").append(alarmFromSelectNode);
		//初始化audio
		audioInit();
	});
}
//验证鼠标移开事件
function normalImg(_this){
    var manTel=$('#manTel').val();//报警人联系电话
	var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
	if (!myreg.test(manTel)) {
		document.getElementById("titleNull").style.display = ""; 
		return false;
	} else {
		document.getElementById("titleNull").style.display = "none"; 
	}
}
//更新警情详情
function updateAlarmDetails(param){
	$.post(ctx+"/alarm/bphAlarmInfo/updateAlarmInfo",param,function(data){
		var result = JSON.parse(data);
		if(result.ret == 0){
			$.jBox.tip("修改警情详情成功!");
		}else{
			$.jBox.tip("修改警情详情失败!");
		}
	});
}
//警情二次定位
function locationP_er(_this){
	//locationP(_this);
	//var id = $(_this).attr('data-id');
	var id=alarmId;
	var x =$('.location_'+id).attr('xNum');
	var y =$('.location_'+id).attr('yNum');
	Map.SecondlocationTwo(id,locationP_erPoint);
	return false;
}
//二次定位提交更新点
function locationP_erPoint(point,id){
	var id=id;
	var x=point[0];
	var y=point[1];
	$.get(ctx+'/alarm/bphAlarmInfo/updateAlarmInfo',{'id':id,'x':x,'y':y,'optionDesc':'二次定位'},function(data){
		var dataPoint=JSON.parse(data);
		if(dataPoint.ret=="0"){
			 var point = {
	    		"type":"FeatureCollection",
		        "features":[{
	            	"type":"Feature",
	            	"id":dataPoint.content.id,
	            	"properties":{
	            		"name": dataPoint.content.place,
	            		"icon": 'alarm_'+dataPoint.content.state+'.png',
	            		'type' : 'alarm',//自定义type类型,判断是警情，添加周边查询
	            		"info":{
	            			'接警单编号':dataPoint.content.orderNum,
	            			'案发地点':dataPoint.content.place,
	            			'报警时间':dataPoint.content.alarmTime,
	            			'接警员姓名':dataPoint.content.policeName,
	            			'警情类型':ArjAlarmType[dataPoint.content.typeCode],
	            			'警情状态':stateChange(dataPoint.content.state),
	            			'报警人姓名':dataPoint.content.manName,
	            			'报警人性别':sexChange(dataPoint.content.manSex),
	            			'报警人电话':dataPoint.content.manTel,
	            			'报警方式':alarmForm(dataPoint.content.alarmFrom),
	            			'是否为重大警情':isImportantChange(dataPoint.content.isImportant),
	            		/*	'接警录音':'<audio src="'+dataPoint.content.alarmRecord+'" preload="auto" controls></audio>',*/
	            			'警情内容':'<span style="display:block;width:220px;height:51px;cursor:pointer;display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:3;overflow:hidden;white-space:normal;" title="'+dataPoint.content.content+'">'+dataPoint.content.content+'</span>',
	            		}
	            	},
	            	"geometry":{
	            		"type":"Point",
	            		"coordinates":[x,y]
	            	 }
	            	}]
			    }
			    Map.removeLayer('alarm_'+dataPoint.content.id);//清除
				Map.addJSON1([ {
					'id':'alarm_'+dataPoint.content.id,
					'type' : 'alarm',
					'data' : point,
					'isShow' : true
				} ])
				Map.goTo([Number(x),Number(y)]);
				//更新页面坐标
				$('.location_'+id).attr('xNum',x);
				$('.location_'+id).attr('yNum',y);
				$.jBox.tip('定位成功');
		}else{
			$.jBox.tip('定位失败');
		}
	})
}
//周边查询
function nearSearch(_this){
	//隐藏信息窗口
	$('#popup-closer').click();
	var html=$('#nearSearchDialog');
	layer.open({
		type : 1,
		title : "周边查询",
		area : [ "400px", "180px" ],
		offset : [ "10px", "265px" ],
		maxmin : false,
		btn : [ "查询", "取消" ], //
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			yesNearSearch(_this);
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
			//关闭实时警力 实时警车 视频
			if($("#videoDMS.toggle").hasClass("toggle--on")){
				$("#videoDMS.toggle").click();
			}
			if($("#policeDMS.toggle").hasClass("toggle--on")){
				$("#policeDMS.toggle").click();
			}
			if($("#carDMS.toggle").hasClass("toggle--on")){
				$("#carDMS.toggle").click();
			}
		}
	});
}
function yesNearSearch(_this){
	var id = $(_this).attr('data-id')||alarmId;
	var x =$('.location_'+id).attr('xNum');
	var y =$('.location_'+id).attr('yNum');
	if(x==''||x==undefined||x=="undefined"||x=='0'||x=='0.0'){
		$.jBox.tip('暂无警情定位信息');
		return;
	}
	var center=[Number(x),Number(y)];
	var type="";
	$('input[name="layer"][type="checkbox"]').each(function(){
		type+=$(this).val()+',';
	})
	type=type.substring(0,type.length-1);
	if(type=='0,0,0'){
		$.jBox.tip('请选择图层');
		return false;
	}
	var radius=$('input[name="distance"][type="radio"]:checked').val();
	if(radius=="custom"){
		radius=$('#customKM').val();
		if(radius==''){
			$.jBox.tip('请填写范围');
			return false;
		}
	}
	Map.removeLayer('riceDrawVector');//清除范围图层
    Map.showCircleFromPointDefult(center,radius,true,id,type);//添加范围图层
	var dataList = {};
    dataList.id = id;
    dataList.x = x;
    dataList.y = y;
    dataList.radius = radius;
    dataList.type = type;
	nearSearchQuery(dataList);
}
//周边查询
function nearSearchQuery(dataList){
	$('#result').click();
	$.post(ctx+'/flat/realtimeSituation/findCircumData',dataList,function(data){
		var data=JSON.parse(data);
		var features=[],carFeatures=[];
		if(data.carData){
			var carData=JSON.parse(data[0].carData);
			var carDataLen=carData.length;
			//警车结果树形
			var carNodes = [];
			if(carDataLen>0){
				for(var i=0;i<carDataLen;i++){
					carFeatures.push( {
						"type":"Feature",
						"id":carData[i].devId,
						"properties":{
							"name": carData[i].devName,
							"icon": 'c1.png',
							"info":{
								'名称':carData[i].devName,
								'经度':carData[i].x,
								'维度':carData[i].y,
							}
						},
						"geometry":{
							"type":"Point",
							"coordinates":[carData[i].x,carData[i].y]
						}
					})
					carNodes.push({
						"id":carData[i].devId,
						"name": carData[i].devName,
						"pId": "-1",
						'x':carData[i].x,
						'y':carData[i].y,
						'code':carData[i].code,
						'devType':carData[i].devType,
					})
				}
			}
			$.fn.zTree.init($('#carResult'),settingResultCarDev,carNodes);
		}
		var carPoint = {
			"type":"FeatureCollection",
	        "features":carFeatures
		};
		Map.removeLayer('jingche');
		Map.addJSON1([ {
			'type' : 'DanDian',
			'id' : 'jingche',
			'data' : carPoint,
			'isShow' : true
		} ]);
		var videoDelList=data.videoDelList;
		var videoDelListLen=videoDelList.length;
		if(videoDelListLen>0){
			//警车结果树形
			var videoNodes = [];
			for(var i=0;i<videoDelListLen;i++){
				features.push( {
	            	"type":"Feature",
	            	"id":videoDelList[i].id,
	            	"properties":{
	            		"name": videoDelList[i].name,
	            		"icon": 'video.png',
	             		'type' : 'video',//自定义type类型,判断是视频监控
	             		'video':{
	             			"id":videoDelList[i].id,
	             			'ip':videoDelList[i].ip,
	             		},
	            		"info":{
	            			'设备名称':videoDelList[i].name,
	            			'设备编号':videoDelList[i].code,
	            			'IP地址':videoDelList[i].ip,
	            			'设备地址':videoDelList[i].address,
	            			'经度':videoDelList[i].x,
	            			'维度':videoDelList[i].y,
	            		}
	            	},
	            	"geometry":{
	            		"type":"Point",
	            		"coordinates":[videoDelList[i].x,videoDelList[i].y]
	            	}
	            })
	            videoNodes.push({
					"id":videoDelList[i].id,
					"name": videoDelList[i].name,
					"pId": "-1",
					'x':videoDelList[i].x,
					'y':videoDelList[i].y,
					'code':videoDelList[i].code,
					'typeId':videoDelList[i].typeId,
					'companyId':videoDelList[i].companyId,
					'coordinate':videoDelList[i].coordinate,
					'typeVidicon':videoDelList[i].typeVidicon,
					'account':videoDelList[i].account,
					'gateway':videoDelList[i].gateway,
					'imagePath':videoDelList[i].imagePath,
					'ip':videoDelList[i].ip,
					'mask':videoDelList[i].mask,
					'password':videoDelList[i].password,
					'port':videoDelList[i].port,
					'protocol':videoDelList[i].protocol,
					'proxy':videoDelList[i].proxy,
					'remarks':videoDelList[i].remarks,
					'sdkPort':videoDelList[i].sdkPort,
					'status':videoDelList[i].status,
					'channelNum':videoDelList[i].channelNum,
					'address':videoDelList[i].address,
				})
			}
			$.fn.zTree.init($('#videoResult'),settingResultVideo,videoNodes);
		}
	    var point = {
    		"type":"FeatureCollection",
	        "features":features
		};
	    Map.removeLayer('videos');//清除范围图层
		Map.addJSON1([ {
			'type' : 'videos',
			'data' : point,
			'isShow' : true
		} ])
	
		layui.use('table', function(){
			table = layui.table;
			table.render({
				elem: '#queryPolice'
			    ,url:ctx + '/flat/realtimeSituation/queryPolice'
			    ,where:dataList
			    ,title: '工作人员表'
			    ,cols: [[
			    	{type: 'checkbox', fixed: 'left'}
			    	,{field:'id', hide: true}
			    	,{field:'x', hide: true}
			    	,{field:'y', hide: true}
			    	,{minWidth :20,templet: '#sexTpl',width:20}
			    	,{field:'name', width:70}
			    	,{field:'distance', width:70}
			    	,{fixed: 'right', toolbar: '#barPeo', width:80}
			    ]]
			    ,page: false
			});
			table.on('checkbox(test)',function(obj){
				if(obj.checked == true){
					var checkData = obj.data;
					$('#dispatch').append('<span style="cursor:pointer" class="userId" type="hidden" id="sp' + checkData.id + '" userId="' + checkData.id + '">' + checkData.name + '<i onclick="removePeopleId(\'' + checkData.id + '\')">x</i></span>');
				}
			})
		});
		
	})
}
//重大警情-普通警情
function alarmTypeChange(_this){
	//var id=$(_this).attr('data-id');
	var id=alarmId;
	var alarmType=$(_this).attr('alarm-type');
	var _details = $(_this).parent();
    var _li = _details.parent();
    var optionDesc = "";
    if(alarmType=='1'){
    	optionDesc = "变更为重大警情";
    }else{
    	optionDesc = "变更为普通警情";
    }
	$.get(ctx+'/alarm/bphAlarmInfo/updateAlarmInfo',{'id':id,'isImportant':alarmType,'optionDesc':optionDesc},function(data){
		if(alarmType=='1'){
			$(_this).children('b').text('普通警情');
			$(_this).attr('alarm-type','0');
			_li.find('.major').show();
			$.jBox.tip('已标记为重大警情');
		}else{
			$(_this).children('b').text('重大警情');
			$(_this).attr('alarm-type','1');
			_li.find('.major').hide();
			$.jBox.tip('已标记为普通警情');
		}
	})
}
//周边资源
function nearSearch_A(_this){
	var id ='';
	var x =$(_this).attr('xNum');
	var y =$(_this).attr('yNum');
	if(x==''||x==undefined||x=="undefined"||x=='0'||x=='0.0'){
		$.jBox.tip('暂无警情定位信息');
		return;
	}
	var center=[Number(x),Number(y)];
	var type="";
	$('.resource dl').each(function(){
		type+=$(this).attr('type-val')+',';
	})
	type=type.substring(0,type.length-1);
	if(type=='0,0,0'){
		$.jBox.tip('请选择查询资源');
		return false;
	}
	var radius=$('input[name="distance-a"][type="radio"]:checked').val();
	if(radius=="custom"){
		radius=$('#customKM_A').val();
		if(radius==''){
			$.jBox.tip('请填写范围');
			return false;
		}
	}
	Map.removeLayer('riceDrawVector');//清除范围图层
    Map.showCircleFromPointDefult(center,radius,true,id,type);//添加范围图层
    var dataList = {};
    dataList.id = '';
    dataList.x = x;
    dataList.y = y;
    dataList.radius = radius;
    dataList.type = type;
	nearSearchQuery(dataList);
}
//智能布控
function controlDialog(_this) {
	//var id=$(_this).attr('data-id');
	var id=alarmId;
	var alarmTime = new Date($('#dataAlarmTime').html());
	var now = new Date($.ajax({async: false}).getResponseHeader("Date"));
	var escapeTime=parseInt(now - alarmTime) / 1000 / 60;
	if(escapeTime<0){
		escapeTime = 0;
	}
	$("#alarmTime").text(getDay(alarmTime));
	$('#escapeTime').val(Math.round(escapeTime));
	var html = $('#setControlDialog');
	var	title = "智能布控";	
	var	width = "310px";
	var	height = "235px";
	var	offsetWidth = "10px";
	var	offsetHeight = "265px";
	var	affirm = "生成";
	var	cancel = "取消";
	$.getJSON(ctx+'/sys/dict/listData?type=escape_way',function(data){
		var laneSelectOptions = $("#runWay")[0].options;
		for(var i in laneSelectOptions){
			laneSelectOptions.remove(1);
		}
		for(var i in data){
			var optionItem = new Option(data[i].label,data[i].value);
			laneSelectOptions.add(optionItem);
		}
	})
	setControl(html,title,width,height,offsetWidth,offsetHeight,affirm,cancel,id);
	//给时间框赋默认值
	//$("#escapeTime").val(Math.round(escapeTime));
	//$("#startTime").val(getDay(data[i].alarmTime.time));
}
//智能布控弹框
function setControl(html,title,width,height,offsetWidth,offsetHeight,affirm,cancel,id){
	layer.open({
		type : 1,
		title : title,
		area : [ width, height ],
		offset : [ offsetWidth, offsetHeight ],
		maxmin : false,
		btn : [ affirm, cancel ], // /可以无限个按钮
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
            var escapeTime=$('#escapeTime').val();
            var runWayVal=$('#runWay').val();
        	var x =$('.location_'+id).attr('xNum');
        	var y =$('.location_'+id).attr('yNum');
        	if(runWayVal==''){
             	$.jBox.tip('请选择逃跑方式');
             	return false;
            }
        	var radius=Number(escapeTime)*Number(runWayVal);
        	var center=[Number(x),Number(y)];
            Map.removeLayer('riceDrawVector');//清除范围图层
        	Map.showCircleFromPointDefult(center,radius,false);//添加范围图层
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}
function planAssociated(_this){
	var isImportant = $(_this).attr('data-isImportant');
	var typeCode = $(_this).attr('alarm-type');
	var alarmId=$(_this).attr('data-id');//警情id
	$.post(ctx+'/planinfo/bphPlanInfo/planAssociated',{'isImportant':isImportant,'typeCode':typeCode},function(data){
		var planData = JSON.parse(data);
		if(planData === undefined || planData == null || planData == ""){
			$.jBox.tip('没有此案情关联预案');
			return;
		}
		if(planData.length == 1){
			var planId=planData[0].id;
			TiaoZhuan(alarmId,planId);
		}else if(planData.length > 1){
			planAssociatedData(alarmId,planData);
		}
		
	})
}

function planAssociatedData(alarmId,planData){
	var html = "";
    html+='<div>';
    html+='<table class="table table-striped table-bordered table-condensed">';
    html+='<thead>';
    html+='<tr>';
    html+='<th>选择</th>';
    html+='<th>预案名称</th>';
    html+='<th>警情级别</th>';
    html+='<th>警情类型</th>';
    html+='</tr>';
    html+='</thead>';
    html+='<tbody>';
	for(var i = 0; i < planData.length;i++){
	    html+='<tr>';
	    html+='<td style="text-align: center;"><div class="radio radio-success" style="display: inline-block;"><input type="radio" id="'+planData[i].id+'" value="'+planData[i].id+'" name="plantype" ><label for="'+planData[i].id+'"></lable></div></td>';
	    html+='<td>'+planData[i].name+'</td>';
	    html+='<td>'+isImportantChangeVal(planData[i].isImportant)+'</td>';
	    html+='<td>'+alarmType(planData[i].typeCode)+'</td>';
	    html+='</tr>';
	}	   
    html+='</tbody>';
	html+='</table>';
    html+='</div>';
	layer.open({
		type : 1,
		title : "预案关联",
		area : [ "400px", "250px" ],
		offset : [ "10px", "265px" ],
		btn : [ "确定", "取消" ], //
		maxmin : false,
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			var planId=$('input[name="plantype"]:checked').val();
			if(planId==''||planId==undefined){
				$.jBox.tip('请选择预案');
				return false;
			}
			TiaoZhuan(alarmId,planId);
			layer.close(index);
		}
    });
}
//处置分析
function findHandleLog(_this){
	var id=alarmId;
	var html="";
	var	title = "处置分析";
	var	width = "1000px";
	var	height = "618px";
	var	offsetWidth = "80px";
	var	offsetHeight = "450px";
	var	cancel = "关闭"; 
	$.getJSON(ctx+'/alarmhandlelog/bphAlarmHandleLog/findHandleLog',{'alarmId':id},function(data){
		var len=data.length;
		if(len>=0){
			var html='<div class="container"><div class="row"><div class="col-md-12"><div class="VivaTimeline"><dl><dt>处置时间轴</dt>';
			for(var i=0;i<len;i++){
				if(data[i].user){
				if(i%2==0){
					html+='<dd class="pos-left clearfix"><div class="circ"></div><div style="width: 170px;" class="time">'+(data[i].operateTime==undefined?"":data[i].operateTime)+'</div><div class="events"><div class="events-header"><p>'+(data[i].user.name==undefined?"":data[i].user.name)+':'+(data[i].operateDesc==undefined?"":data[i].operateDesc)+'</p></div></div></dd>';
				}else{
					html+='<dd class="pos-right clearfix"><div class="circ"></div><div style="width: 170px;padding-right: 20px;margin-left: -170px;" class="time">'+(data[i].operateTime==undefined?"":data[i].operateTime)+'</div><div class="events"><div class="events-header"><p>'+(data[i].user.name==undefined?"":data[i].user.name)+':'+(data[i].operateDesc==undefined?"":data[i].operateDesc)+'</p></div></div></dd>';
					}
				}
			}
			html+='</dl></div></div></div></div>';
			$('#findHandleLog').html(html);
			findHandle(html,title,width,height,offsetWidth,offsetHeight,cancel);
		}
	})
}

//处置分析--过程记录弹框
function findHandle(html,title,width,height,offsetWidth,offsetHeight,cancel){
	layer.open({
		type : 1,
		title : title,
		area : [ width, height ],
		offset : [ offsetWidth, offsetHeight ],
		maxmin : false,
		btn : [cancel], // /可以无限个按钮
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}
//执行
function startAction(_this){
	var actiontFlag=$(_this).attr('actiontflag');
	var ActionType=$(_this).attr('type');
	var actionName=$(_this).attr('data-name');
	if(actiontFlag=='1'){
		if($('.zhi',_this).width()<=15){
	        $('.zhi',_this).stop(false, true).animate({"width":"0.28rem"},500)
	        $('.zhi',_this).children().animate({"width":"0.28rem"},500)
	    }
		actionId=$(_this).attr('data-id');
		var data=JSON.parse($(_this).attr('data-data'));
		var html='';
		var title='';
		if(ActionType=='1'){
			title='通知';
			//通知类
			html+='<div id="policeData" class="policeSenData">';
			html+='<div class="control-group" style="margin-top: 10px;">';
			html+='<label class="control-label" style="margin-left: 15px;">通知标题：</label>';
			html+='<div class="controls" style="display: inline-block;">';
			html+='<input id="title" name="title" style="width:322px;height:30px;margin-left: 8px;" type="text" value="" maxlength="80">';	
			html+='</div>';
			html+='</div>';
			html+='<div class="control-group" style="margin-top: 10px;">';
			html+='<label class="control-label" style="margin-left: 15px;">通知内容：</label>';
			html+='<div class="controls" style="display: inline-block;">';
			html+='<textarea id="noticeContent" name="content" maxlength="500"  style="margin-left: 8px;width:324px;"></textarea>';	
			html+='</div>';
			html+='</div>';
			html+='<div class="control-group" style="display:inline-block;width: 520px;height: 200px;">';
			html+='<label class="control-label" style="margin-left:15px;float:left">接收人</label> ';
			html+='<div id="treeDiv" style="float:left; width: 300px; height: 180px; overflow: auto;  margin-left: 13px;">';
			html+='<div id="userZtree" class="ztree"></div>';
			html+='</div>';
			html+='<div id="treeNmae" style="overflow:auto;float:left;width: 100px;height: 180px;border:1px solid rgb(204, 204, 204);">';
			html+='</div>';
			html+='</div>';
			html+='</div>';
		}else if(ActionType=='2'){
			title='任务';
			//任务类
			html+='<div id="policeData" class="policeSenData">';
			html+='<div class="control-group" style="margin-top: 10px;">';
			html+='<label class="control-label" style="margin-left: 15px;">目的地</label>';	
			html+='<div class="controls" style="display: inline-block;">';
			html+='<input class="input-medium" id="destinyX" type="text" readonly="readonly" value="" style="width:160px;height:30px;margin-left: 20px;" onmouseover="this.title=this.value"/>';
			html+='<input class="input-medium" id="destinyY" type="text" readonly="readonly" value="" style="width:160px;height:30px;"/>';
			html+='<i class="icon-map-marker" id="destinLocation" onclick="locationP_er_destiny(this)" style=" font-size: 16px;  margin-left: 10px;cursor: pointer;"></i>';		
			html+='</div>';	
			html+='</div>';
			html+='<div class="control-group" style="margin-top: 10px;">';
			html+='<label class="control-label" style="padding-top: 20px;margin-left: 15px;">出警安排</label>';	
			html+='<div class="controls"  style="display: inline-block;">';	
			html+='<textarea id="taskTextarea" style="margin-left: 8px;width:324px;" name="eduhistory" align="center"></textarea>';	 	
			html+='</div>';	
			html+='</div>';
			html+='<div class="control-group" style="display:inline-block;width: 520px;height: 200px;">';
			html+='<label class="control-label" style="margin-left:15px;float:left">接收人</label> ';
			html+='<div id="treeDiv" style="float:left; width: 300px; height: 180px; overflow: auto;  margin-left: 13px;">';
			html+='<div id="userZtree" class="ztree"></div>';
			html+='</div>';
			html+='<div id="treeNmae" style="float:left;width: 100px;height: 180px;border:1px solid rgb(204, 204, 204);">';
			html+='</div>';
			html+='</div>';
			html+='</div>';
		}
		layer.open({
			type : 1,
			title : title,
			area : [ "520px", "450px" ],
			btn : [ "发送", "取消" ], //
			maxmin : false,
			shade: 0,
			anim: 0,
			id:'send',
			content :html,
			cancel : function() {
			},
			end : function() {
				$("#btncontrolpolice").removeAttr("disabled");
				$('.zhi').each(function(){
					if($(this).width()>=28){
						$(this).stop(false, true).animate({"width":"0.1rem"},500);
				        $(this).children().animate({"width":0},500);
					}
				})
			},
			yes : function(index, layero) {
				sendAction(ActionType,actionName);
				if(yesOrNo == 'yes'){
					layer.close(index);
				}
			}
	    });
		if(ActionType=='1'){
			$('#title').val(data.title);
			$('#noticeContent').val(data.content);
		}else if(ActionType=='2'){
			$("#btncontrolpolice").attr("disabled","disabled");
			$('#destinLocation').attr('data-id',alarmId);
			$('#destinyX').val(x);
			$('#destinyY').val(y);
		}
		//加载默认发送人树形
		userTreeInit();
		var _stepId=data.id;
		//默认发送人
		userSendInit(_stepId);
	}else{
		$.jBox.tip('该动作已执行');
		return;
	}
}
//发送提交数据
var yesOrNo='';
function sendAction(ActionType,actionName){
	var strId='',param='',actionUserName='';
	$('input[name="userId"]').each(function(){
	    var id=$(this).val();
		strId+=id.substring(2,id.length)+',';
		actionUserName+=$(this).attr('data-name')+',';
	})
	if(strId==''){
		$.jBox.tip('请选择接收人');
		yesOrNo='no';
		return false;
	}
 	yesOrNo='yes';
	strId=strId.substring(0,strId.length-1);
	actionUserName=actionUserName.substring(0,actionUserName.length-1);
	var _alarmId=alarmId;
	var _planId=planId;
    var _stepId=stepId;
    var _actionId=actionId;
    var optionDesc = ""+actionUserName+"执行："+actionName+"";
    if(ActionType=='1'){
    	var title=$('#title').val();
    	var content=$('#noticeContent').val();
		param={'type':ActionType,'userId':strId,'alarmId':_alarmId,'title':title,'content':content,'planId':_planId,'stepId':_stepId,'actionId':_actionId,'optionDesc':optionDesc}
	}else if(ActionType=='2'){
		var destinyX=$('#destinyX').val();
		var destinyY=$('#destinyY').val();
		var task=$('#taskTextarea').val();
		param={'type':ActionType,'userId':strId,'alarmId':_alarmId,'task':task,'destinyX':destinyX,'destinyY':destinyY,'planId':_planId,'stepId':_stepId,'actionId':_actionId,'optionDesc':optionDesc}
	}
	$.getJSON(ctx+'/handle/bphAlarmHandle/saveHandle',param,function(data){
		if (data) {
			var stepAction = {};
			stepAction.stepId = _stepId;
			stepAction.actionId = _actionId;
			if(ActionType=='1'){
				stepAction.actionStatus = '未阅读';
			}else if(ActionType=='2'){
				stepAction.actionStatus = '未处理';
			}

			stepActionArr.push(stepAction);
			var username=parent.$('#usersname').val();
			//成功
			var html='';
			var time=todayHMS();
			html+='<li><div class="unit"><div>'+username+':发送出警信息</div></div><i></i><div style="width: 70px;" class="command-center"><div style="width:76px;padding-left:9px;" id="time">'+time+'</div></div></li>'
            $('#steps').append(html).width($('#steps').width()+240);
			flowTableSCroll(optionDesc);
			console.log($('.zhi'))
			$('.zhi').each(function(){
				if($(this).width()>=28){
					$(this).stop(false, true).animate({"width":"0.1rem"},500);
					$(this).children().animate({"width":0},500);
					$(this).css("background-color","green");
					$(this).parent().attr('actiontflag',0);
					if(ActionType=='1'){
						$(this).siblings('b').after('<span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+ActionType+'" data-id="'+actionId+'" onclick="planManagerDetails(this)">未阅读</span>');
					}else if(ActionType=='2'){
						$(this).siblings('b').after('<span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline" type="'+ActionType+'" data-id="'+actionId+'" onclick="planManagerDetails(this)">未处理</span>');
					}

				}
			})
		} /*else {
			$.jBox.tip('发送失败，该警员可能没有上线！');
		}*/
	})
}
//默认发送人树形
function userTreeInit(){
	var key, lastValue = "", nodeList = [], type = getQueryString("type", "/sys/office/treeData?type=3");
	var tree,  setting = {
		data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType :{ "Y" : "s", "N" : "s" }
		},
		async:{enable:(type==3),url:ctx+"/org/ccmOrgTeam/treeData?teamType="+teamType,autoParam:["id=officeId"],
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
		      }
		      return responseData;
			}
		},
		callback:{
			onCheck: function(e, treeId, treeNode){
				var nodes = tree.getCheckedNodes(true);
				$('#treeNmae').html('')
				for (var i=0, l=nodes.length; i<l; i++) {
					var id=nodes[i].id;
					if(id.indexOf("u_")>-1){
						$('#treeNmae').append('<input name="userId" data-name="'+nodes[i].name+'" type="hidden" value="'+id+'"/>"'+nodes[i].name+'"<br/>');
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
			},
			onExpand:function(e,treeId,treeNode){ 
			var userSendId=[];
			$('input[name="userId"]').each(function(){
				userSendId.push($(this).val());
			})
			var userSendIdLen=userSendId.length;
			var zTreeObj = $.fn.zTree.getZTreeObj(treeId);  
			var ChildNode=treeNode.children;
			if(ChildNode!= null && ChildNode != "undefined"&&ChildNode !=''){  
				if(userSendIdLen>0){
			    	var len=ChildNode.length;
				    	for(var i=0;i<len;i++){
				    		for(var j=0;j<userSendIdLen;j++){
				    			if(ChildNode[i].id==userSendId[j]){
						    		var CheckNode = zTreeObj.getNodeByParam("id", ChildNode[i].id, null);
						    		zTreeObj.checkNode(CheckNode, true);
					    		}
				    		}
				    	}
			    	}
			    }
			}
		}
	};
	$.getJSON(ctx+"/sys/office/treeData?type=3&&extId=&isAll=&module=&t="+ new Date().getTime(),function(data){
		tree =$.fn.zTree.init($("#userZtree"), setting, data);
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

//默认发送人
function userSendInit(_stepId){
	$.getJSON(ctx+"/flat/planManage/planActionUsers",{'actionId':_stepId},function(data){
		if(data!=''&&data!=null&&data!=undefined){
			for(i in data){
				$('#treeNmae').append('<input name="userId" data-name="'+data[i].uName+'" type="hidden" value="u_'+data[i].userId+'"/>"'+data[i].uName+'"<br/>');
			}
		}
	})
}
//加载默认执行的动作-id和状态 fingListByAlarmId /handle/bphAlarmHandle
var stepActionArr=[];
function queryStatus(){
	$.getJSON(ctx+"/handle/bphAlarmHandle/fingListByAlarmId",{'alarmId':alarmId},function(data){
		for(var i = 0;i<data.length;i++){
			var stepAction = {};
			stepAction.stepId = data[i].stepId;
			stepAction.actionId = data[i].actionId;
			var status=data[i].status;
			if(status==0){
				stepAction.actionStatus = '未处理';
			}else if(status==1){
				stepAction.actionStatus = '已签收';
			}else if(status==2){
				stepAction.actionStatus = '已到达';
			}else if(status==3){
				stepAction.actionStatus = '已反馈';
			}else if(status==4){
				stepAction.actionStatus = '未阅读';
			}else if(status==5){
				stepAction.actionStatus = '已阅读';
			}
			stepActionArr.push(stepAction);
		}
	})
}

//联系人树形
function contactTreeInit(){
	var key, lastValue = "", nodeList = [], type = getQueryString("type", "/sys/office/treeData?type=3");
	var tree,  setting = {
		data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType :{ "Y" : "s", "N" : "s" }
		},
		async:{enable:(type==3),url:ctx+"/org/ccmOrgTeam/treeData?teamType="+teamType,autoParam:["id=officeId"],
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
			     }
			     return responseData;
			 }
		},
		callback:{
			onCheck: function(e, treeId, treeNode){
				var nodes = tree.getCheckedNodes(true);
				$('#contactId').html('')
				for (var i = 0, l = nodes.length; i < l; i++) {
					var id=nodes[i].id;
					if(id.indexOf("u_")>-1){
						$('#contactId').append('<span style="cursor:pointer" class="contactName"  id="sp'+nodes[i].id+'" userId="'+nodes[i].id+'">'+nodes[i].name+'<i onclick="removePeopleId(\''+nodes[i].id+'\')">x</i></span>');
					}
				}
				return false;
			 } ,
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
	$.getJSON(ctx+"/sys/office/treeData?type=3&&extId=&isAll=&module=&t=" + new Date().getTime(),function(data){
		tree = $.fn.zTree.init($("#userZtree"), setting, data);
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
//联系人弹框
function contact(){
	var html = "<div id='userZtree' class='ztree' style='width:320px;height:300px;padding-left:20px;'></div>";
	contactTreeInit();
	layer.open({
		type : 1,
		title : "联系人",
		area : [ "330px", "400px" ],
		offset : [ "180px", "770px" ],
		maxmin : false,
		btn : [ "确认", "关闭" ], //可以无限个按钮
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}
//删除联系人
function removePeopleId(id){
	$("#sp" + id).remove();
}
//消息推送
function sendContactAndMessage(){
	if(alarmId === undefined || alarmId == null || alarmId == ''){
		$.jBox.tip('预案管理后可发送');
	}
	var contactId = '';
	$('.contactName').each(function(){
		var id = $(this).attr('userId');
		contactId += id.substring(2,id.length)+',';
	});
	contactId = contactId.substring(0,contactId.length-1);
	if(contactId == '' || contactId == undefined){
		$.jBox.tip('请选择联系人');
		return false;
	}
	var contactMessageId = $('#contactMessageId').val();
	if(contactMessageId === undefined || contactMessageId == ''|| contactMessageId == null){
		$.jBox.tip('不能发送空白信息');
		return false;
	}
	var operateDesc = contactMessageId;
	$.getJSON(ctx+"/alarmhandlelog/bphAlarmHandleLog/sendMessage",{'contactId':contactId,'contactMessageId':contactMessageId,'alarmId':alarmId,'operateDesc':operateDesc},function(data){
		if(data == true){
			$.jBox.tip('消息发送成功');
			//清空联系人框
			$('#contactId').html('');
			//清空对话框
			$('#contactMessageId').val('');
			flowTableSCroll(optionDesc);
		}else{
			$.jBox.tip('消息发送失败');
		}
	});
}

//跳转页面
function TiaoZhuan(alarmId,planId){
	if (typeof (Storage) !== "undefined") {
		sessionStorage.alarmId = alarmId;
		sessionStorage.planId = planId;
	}
	parent.$('#menu li').each(function(){
		if($(this).children('a').attr('data-id')=='5c05053e82004e6786ddfe758a18013b'){
			$(this).children('a').children('span').click();
		}
	})
}

function planManagerDetails(_this){
	var ActionType = $(_this).attr('type');
	var actId = $(_this).attr('data-id');
	$.getJSON(ctx+"/handle/bphAlarmHandle/planManagerDetails",{'actionId':actId,'alarmId':alarmId},function(data){
		var users = [];
		var usersCode = [];
		var height = '';
		var title = '';
		if(data === undefined || data == null || data == ''){
			$.jBox.tip('错误操作');
			return;
		}
		for(var i = 0;i < data.length;i++){
			users.push(data[i].name);
			usersCode.push(data[i].handleCode);
		}
		var html = '';
		html += '<table class="table table-striped table-bordered table-condensed" style="margin-bottom:0">';
		html += '<tr><td>处警员编号：</td><td><span style="display:block;width:180px;height:20px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;" title="'+usersCode+'">'+usersCode+'</span></td></tr>';
		html += '<tr><td>处警员姓名：</td><td><span style="display:block;width:180px;height:20px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;" title="'+users+'">'+users+'</span></td></tr>';
		html += '<tr><td>任务描述：</td><td><span style="display:block;width:180px;height:20px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;" title="'+data[0].task+'">'+data[0].task+'</span></td></tr>';
		//通知类
		if(ActionType == '1'){
			height = '188px';
			title = '通知';
		}
		//任务类
		if(ActionType == '2'){
			height = '260px';
			title = '任务';
			html += '<tr><td>目的地经度：</td><td>'+data[0].destinyX+'</td></tr>';
			html += '<tr><td>目的地纬度：</td><td>'+data[0].destinyY+'</td></tr>';
		}
		html += '<tr><td>派单时间：</td><td>'+getDay(data[0].dispatchTime.time)+'</td></tr>';
		html += '</table>';
		layer.open({
			type : 1,
			title : title,
			area : [ "400px", height ],
			maxmin : false,
			offset : [ "250px", "770px" ],
			content : html,
			cancel : function() {
			},
			end : function() {
			},
			yes : function(index, layero) {
				layer.close(index); // 如果设定了yes回调，需进行手工关闭
			}
		});
	})
}
//事后归档
function alarmkArchive(_this){
	//var id=$(_this).attr('data-id');
	var id=alarmId;
	var place=$(_this).attr('data-place');
	window.location.href = ctx+'/export/exportWord/alarmExportWord?id='+id+'&place='+place;
}
//警情定位详情
function locationPInfo(data){
	var isShow=true;
	//警情开关
	if($("#alarmDMS.toggle").hasClass("toggle--off")){
		isShow = false;
	}else{
		isShow=true;
	}
	//var data=JSON.parse($('.location_'+id).attr('data-data'));
    var point = {
		"type":"FeatureCollection",
        "features":[{
        	"type":"Feature",
        	"id":data.id,
        	"properties":{
        		"name": data.place,
        		"icon": 'alarm_'+data.state+'.png',
        		'type' : 'alarm',//自定义type类型,判断是警情，添加周边查询
        		"info":{
        			'接警单编号':data.orderNum,
        			'案发地点':data.place,
        			'报警时间':data.alarmTime,
        			'接警员姓名':data.policeName,
        			'警情类型':ArjAlarmType[data.typeCode],
        			'警情状态':stateChange(data.state),
        			'报警人姓名':data.manName,
        			'报警人性别':sexChange(data.manSex),
        			'报警人电话':data.manTel,
        			'报警方式':alarmForm(data.alarmFrom),
        			'是否为重大警情':isImportantChange(data.isImportant),
        			/*'接警录音':'<audio src="'+data.alarmRecord+'" preload="auto" controls></audio>',*/
        			'警情内容':'<span style="display:block;width:220px;height:51px;cursor:pointer;display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:3;overflow:hidden;white-space:normal;" title="'+data.content+'">'+data.content+'</span>',
        		}
        	},
        	"geometry":{
        		"type":"Point",
        		"coordinates":[data.x,data.y]
        	 }
        	}]
    }
	Map.addJSON1([ {
		"id":'alarm_'+data.id,
		'type' : 'alarm',
		'data' : point,
		'isShow' : isShow
	} ])
	if(data.x&&data.y){
	Map.goTo([Number(data.x),Number(data.y)]);
	}
}
//指示功能

//联系人弹框
function Instructions(){
	var title='批示';
	//批示
	var html='';
	html+='<div id="policeData" class="policeSenData">';
	html+='<div class="control-group" style="margin-top: 10px;">';
	html+='<label class="control-label" style="padding-top: 20px;margin-left: 15px;">批示内容</label>';	
	html+='<div class="controls"  style="display: inline-block;">';	
	html+='<textarea id="infoInstructions" style="margin-left: 8px;width:324px;height:50px;" name="eduhistory" align="center"></textarea>';	 	
	html+='</div>';	
	html+='</div>';
	html+='<div class="control-group" style="display:inline-block;width: 520px;height: 200px;">';
	html+='<label class="control-label" style="margin-left:15px;float:left">接收人</label> ';
	html+='<div id="treeDiv" style="float:left; width: 300px; height: 180px; overflow: auto;  margin-left: 13px;">';
	html+='<div id="userZtree" class="ztree"></div>';
	html+='</div>';
	html+='<div id="treeNmae" style="float:left;width: 100px;height: 180px;border:1px solid rgb(204, 204, 204);overflow-x: hidden;overflow-y: scroll;">';
	html+='</div>';
	html+='</div>';
	html+='</div>';
	layer.open({
		type : 1,
		title : title,
		area : [ "520px", "450px" ],
		btn : [ "发送", "取消" ], //
		maxmin : false,
		shade: 0,
		anim: 0,
		id:'send',
		content :html,
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			var contactId = '';
			$('input[name="userId"]').each(function(){
				var id = $(this).val();
				contactId += id.substring(2,id.length)+',';
			});
			contactId = contactId.substring(0,contactId.length-1);
			if(contactId == '' || contactId == undefined){
				$.jBox.tip('请选择联系人');
				return false;
			}
			var contactMessageId = $('#infoInstructions').val();
			if(contactMessageId === undefined || contactMessageId == ''|| contactMessageId == null){
				$.jBox.tip('不能发送空白信息');
				return false;
			}
			var operateDesc = contactMessageId;
			$.getJSON(ctx+"/alarmhandlelog/bphAlarmHandleLog/sendMessage",{'contactId':contactId,'contactMessageId':contactMessageId,'alarmId':alarmId,'operateDesc':operateDesc},function(data){
				if(data == true){
					$.jBox.tip('消息发送成功');
					//默认添加记录
					flowTableSCroll(optionDesc);
				}else{
					$.jBox.tip('消息发送失败');
				}
			});
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
	//加载默认发送人树形
	userTreeInit();
}
//默认加载到案事件流程table
function flowTableSCroll(optionDesc){
	var Time=todayHMS();
	var userName=$('#usersname').val();
	FlowData.push({
        'operateD':'调派内容:',
		'operateDesc':optionDesc,
		'userN':'操作人:',
		'userName':userName,
		'operateT':'调派时间:',
		'operateTime':Time,
	})
	layuiTable.reload('flowTable',{
		data:FlowData
	})
	$(".bottom-table .layui-table-body").scrollTop($(".bottom-table .layui-table-body")[0].scrollHeight);
}
//警员绑定警情详情
function bindAlarmInfo(id){
	var alarmHtml='';
	$.ajax({
		type : "post",
		url : ctx+"/alarm/bphAlarmInfo/findByHandlePoliceId",
		async : false,
		data:{'handlePoliceId':id},
		success : function(data){
			alarmHtml+='<div>关联警情</div>';
			alarmHtml+='<table id="BindAlarmInfoTable" class="layui-table">';
			alarmHtml+='<thead>';
			alarmHtml+='<tr>';
			alarmHtml+='<th>接警单号</th>';
			alarmHtml+='<th>警情内容</th>';
			alarmHtml+='<th>警情状态</th>';
			alarmHtml+='</tr>';
			alarmHtml+='</thead>';
			alarmHtml+='<tbody>';
			var data=JSON.parse(data);
			var len=data.length;
			if(len>0){
				for(var i=0;i<len;i++){
					alarmHtml+='<tr>';
					alarmHtml+='<td title="'+data[i].orderNum+'" style="border: 1px solid rgb(230, 230, 230);height:39px;line-height:39px;">'+data[i].orderNum+'</td>';
					alarmHtml+='<td title="'+data[i].content+'" style="border: 1px solid rgb(230, 230, 230);height:39px;line-height:39px;" ><div style="width:100px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap">'+data[i].content+'</div></td>';
					alarmHtml+='<td style="border: 1px solid rgb(230, 230, 230);height:39px;line-height:39px;">'+handleStatusChange(data[i].handleStatus)+'</td>';
					alarmHtml+='</tr>';
				}
			}	
			alarmHtml+='</tbody>';
			alarmHtml+='</table>';
		}
	});
	return alarmHtml;
}
function handleStatusChange(handleStatus){
	var html='';
	if(handleStatus=='0'){
		html='<span class="alarmstatus_0">未处理</span>';
	}else if(handleStatus=='1'){
		html='<span class="alarmstatus_1">已签收</span>';
	}else if(handleStatus=='2'){
		html='<span class="alarmstatus_2">已到达</span>';
	}else if(handleStatus=='3'){
		html='<span class="alarmstatus_3">已反馈</span>';
	}else{
		html='<span></span>';
	}
	return html;
}
var alarmSendId = '';
//点位周边查询
function nearPTSearch(_this) {
	// 隐藏信息窗口
	$('#popup-closer').click();
	var id = $(_this).attr('data-id');
	alarmSendId = id;
	var x = $('.location_' + id).attr('xNum');
	var y = $('.location_' + id).attr('yNum');
	if(x==''||x==undefined||x=="undefined"||x=='0'||x=='0.0'){
		$.jBox.tip('暂无警情定位信息');
		return;
	}
	var html = $('#nearSearchDialog');
	var center = [Number(x), Number(y)];
	layer.open({
		type: 1,
		title: "周边查询",
		area: ["410px", "193px"],
		maxmin: false,
		btn: ["查询", "取消"],
		content: html,
		cancel: function() {},
		end: function() {},
		yes: function(index, layero) {
			var type = "";
			$('input[name="layer"][type="checkbox"]').each(function() {
				type += $(this).val() + ',';
			});
			type = type.substring(0, type.length - 1);
			if (type == '0,0,0') {
				layer.msg('请选择图层');
				return false;
			}
			var radius = $('input[name="distance"][type="radio"]:checked').val();
			if (radius == "custom") {
				radius = $('#customKM').val();
				if (radius == '') {
					layer.msg('请填写范围');
					return false;
				}
			}
			Map.removeLayer('riceDrawVector'); // 清除范围图层
			Map.showCircleFromPointDefult(center, radius, true, id, type); // 添加范围图层
			var dataList = {};
			dataList.id = id;
			dataList.x = x;
			dataList.y = y;
			dataList.radius = radius;
			dataList.type = type;
			nearSearchQuery(dataList);
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}
//派警
function addDiyDom(treeId, treeNode){
	var id = treeNode.id;
    if (id.indexOf("u_") > -1) { 
    	var aObj = $("#" + treeNode.tId + '_a');
    	var policeId=id.split('u_')[1];
    	var html=''
    		//html+='<i class="icon-phone" data-id="'+policeId+'" onclick="callPhoneNew(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="拨打电话"></i>';
    		html+='<i class="icon-comment" data-id="'+policeId+'" onclick="sendNoteNew(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="发送信息"></i>';
    		html+='<i class="icon-map-marker" data-id="'+policeId+'" data-x="" data-y="" onclick="locationConstableNew(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="定位"></i>';
    		aObj.append(html);  
    }
}
var sendPoliceSetting = {
		view: {
			addDiyDom: addDiyDom
		},
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: '0'
            }
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {
                "Y": "s",
                "N": "s"
            }
        },
        async: {
            enable: (type == 3),
            url: ctx + "/org/ccmOrgTeam/treeData?teamType=" + teamType,
            autoParam: ["id=officeId"],
            dataFilter: function(treeId, parentNode, responseData) {
                if (responseData) {
                    for (var i = 0; i < responseData.length; i++) {
                        if (responseData[i].loginState == 'online') {
                            if (responseData[i].userState == 'busy') {
                                responseData[i].name += "[忙碌]";
                            } else {
                                responseData[i].name += "[备勤]";
                            }
                        } else {
                            responseData[i].name += "[离线]";
                        }
                    }
                }
                return responseData;
            }
        },
        callback: {
            onCheck: function(e, treeId, treeNode) {
            },
            onAsyncSuccess: function(event, treeId, treeNode, msg) {
            }
        },
    };
function sendPolice(_this) {
	var widthX = $('#popup').offset().top;
	var heughtY = $('#popup').offset().left+376;
	var x = $(_this).attr("x");
	var y = $(_this).attr("y");
	var officeId=$(_this).attr('data-officeId');
	var pointAlarmId = $(_this).attr("data-id");
	var  sendPoliceTree;
	alarmSendId = pointAlarmId;
	var html='';
	    html+='<div class="content_wrap">'
		html+=	'<div class="zTreeDemoBackground left">'
		html+=		'<ul id="sendPoliceTree" class="ztree"></ul>'
		html+=	'</div>'
		html+='</div>';
	layer.open({
		  type: 1,
		  offset: [ widthX , heughtY ],
		  skin: 'layui-layer-demo', //样式类名
		  title: "请选择警员",
		  area: ["300px", "444px"],
		  closeBtn: 0, //不显示关闭按钮
		  anim: 2,
		  shade: 0,
		  shadeClose: false, //开启遮罩关闭
		  btn: ['确认', '关闭'],
		  content: html,//data-id是指最高级节点的parentId
		  success: function(layero, index) {
			  $.getJSON(ctx + "/sys/office/treeData?type=3&&extId=&isAll=&module=&t=" + new Date().getTime(),
					    function(data) {
					         sendPoliceTree = $.fn.zTree.init($("#sendPoliceTree"), sendPoliceSetting, data);
					        // 默认展开一级节点
					        var nodes = sendPoliceTree.getNodesByParam("level", 0);
					        for (var i = 0; i < nodes.length; i++) {
					        	sendPoliceTree.expandNode(nodes[i], true, false, false);
					        }
					        // 异步加载子节点（加载用户）
					        var nodesOne = sendPoliceTree.getNodesByParam("isParent", true);
					        for (var j = 0; j < nodesOne.length; j++) {
					        	sendPoliceTree.reAsyncChildNodes(nodesOne[j], "!refresh", true);
					        }
					        if(officeId!=''&&officeId!="undefined"&&officeId!=undefined){
					        	var openOfficeId = sendPoliceTree.getNodeByParam("id",officeId);
					        	sendPoliceTree.selectNode(openOfficeId,true);//指定选中ID的节点
					        	sendPoliceTree.checkNode(openOfficeId,true);//指定选中ID的节点
								sendPoliceTree.expandNode(openOfficeId, true, false);//指定选中ID节点展开
					        }
					    });
		  },
		  yes: function(index, layero) {
			  var checkedNodes = sendPoliceTree.getCheckedNodes();
			  var strId = '';
			  for(var i in checkedNodes){
				  var id = checkedNodes[i].id;
				   if (id.indexOf("u_") > -1) { 
					   id=id.split('u_')[1];
					   strId += id + ',';
		            }
			  }
			 // strId=strId.substring(0,strId.length-1);
			  if(strId==''){
				  top.$.jBox.tip("请选择人员");
				  return ;
			  }
			  var html='';
			  html+='<div id="policeData" class="policeSenData">';
			  html+='<div class="control-group" style="margin-top: 10px;">';
			  html+='<label class="control-label" style="margin-left: 15px;float: left;margin-top: 8px;">目的地</label>';	
			  html+='<div class="controls" style="display: inline-block;">';	
			  html+='<input class="input-medium" id="destinyX" type="text" readonly="readonly" style="width: 160px; height: 30px; margin-left: 20px;" onmouseover="this.title=this.value" />';		
			  html+='<input class="input-medium" id="destinyY" type="text" readonly="readonly" style="width: 160px; height: 30px;" onmouseover="this.title=this.value" /> ';		
			  html+='<i class="icon-map-marker" id="destinLocation" onclick="locationP_er_destiny(this)" style="font-size: 16px; margin-left: 10px; cursor: pointer;"></i>';		
			  html+='</div>';		
			  html+='</div>';	
			  html+='<div class="control-group" style="margin-top: 10px;">';	
			  html+='<label class="control-label" style="padding-top: 10px; margin-left: 15px;float: left; margin-top: 8px;">出警安排</label>';		
			  html+='<div class="controls" style="display: inline-block;">';		
			  html+='<textarea id="taskTextarea" style="margin-left: 8px; width: 324px;" name="eduhistory" align="center"></textarea>';			
			  html+='</div>';		
			  html+='</div>';	
			  html+='</div>';
				layer.open({
					type: 1,
				    title: "消息内容安排",
			        area: ["445px", "230px"],
			        shade: 0,
			        maxmin: false,
			        btn: ["发送", "取消"],
			        content: html,
			        cancel: function() {},
			        end: function() {},
			        yes: function(index, layero) {
			        	var destinyX = $('#destinyX').val();
			            var destinyY = $('#destinyY').val();
			            var alarmId = pointAlarmId;
			            var task = $('#taskTextarea').val();
			            var optionDesc = "发送出警信息";
			            var param = {
			                'type': 2,
			                'userId': strId,
			                'alarmId': alarmId,
			                'task': task,
			                'destinyX': destinyX,
			                'destinyY': destinyY,
			                'optionDesc': optionDesc
			            }
			        	$.getJSON(ctx+'/handle/bphAlarmHandle/sendAlarmInfo',param,function(data){
			        		if(data == true){
			        			layer.msg('消息发送成功');
			        		}else{
			        			layer.msg('消息发送失败');
			        		}
			        	});
			        	layer.close(index);
			        }
			    });
			 	$('#destinyX').val(x);
			 	$('#destinyY').val(y);
				layer.close(index);
			},
			btn2: function(index, layero) {
				layer.close(index);
			}
		});
}
//派警
function dispatchPolice(_this) {
	var pointAlarmId = $(_this).attr("data-id");
	var destinyX = $(_this).attr("data-x");
	var destinyY = $(_this).attr("data-y");
	$('#destinyX').val(x);
    $('#destinyY').val(y);
	alarmSendId = pointAlarmId;
	var widthX = $('#popup').offset().top;
	var heughtY = $('#popup').offset().left+376;
	layer.open({
		id:'dispatchPolice',
		type: 1,
		//type:0 也行
		title: "请选择工作人员",
		area: ["300px", "444px"],
		offset: [ widthX , heughtY ],
		shade: 0,
		content: '<table class="layui-hide" id="policeOfficer" lay-filter="test"></table>',//data-id是指最高级节点的parentId
		btn: ['确认', '关闭'],
		success: function(layero, index) {
			layui.use('table', function(){
			    table = layui.table;
				table.render({
					elem: '#policeOfficer'
				    ,url:ctx + '/alarm/bphAlarmInfo/queryPoliceByAlarmId'
				    ,where : {
						'alarmId' : pointAlarmId
					}
				    ,title: '工作人员表'
				    ,cols: [[
				    	{type: 'checkbox', fixed: 'left'}
				    	,{field:'id', hide: true}
				    	,{field:'x', hide: true}
				    	,{field:'y', hide: true}
				    	,{minWidth :20,templet: '#sexTpl',width:20}
				    	,{field:'name', width:90}
				    	,{field:'distance', width:40}
				    	,{fixed: 'right', toolbar: '#barDemo', width:80}
				    ]]
				    ,page: false
				});
			});
		},
		yes: function(index, layero) {
			var strId = '';
			var checkStatus = table.checkStatus('policeOfficer'),
			data = checkStatus.data;
			for(var i = 0;i<data.length;i++){
				strId += data[i].id + ',';
			}
			 var html='';
			  html+='<div id="policeData" class="policeSenData">';
			  html+='<div class="control-group" style="margin-top: 10px;">';
			  html+='<label class="control-label" style="margin-left: 15px;float: left;margin-top: 8px;">目的地</label>';	
			  html+='<div class="controls" style="display: inline-block;">';	
			  html+='<input class="input-medium" id="destinyX" type="text" readonly="readonly" style="width: 160px; height: 30px; margin-left: 20px;" onmouseover="this.title=this.value" />';		
			  html+='<input class="input-medium" id="destinyY" type="text" readonly="readonly" style="width: 160px; height: 30px;" onmouseover="this.title=this.value" /> ';		
			  html+='<i class="icon-map-marker" id="destinLocation" onclick="locationP_er_destiny(this)" style="font-size: 16px; margin-left: 10px; cursor: pointer;"></i>';		
			  html+='</div>';		
			  html+='</div>';	
			  html+='<div class="control-group" style="margin-top: 10px;">';	
			  html+='<label class="control-label" style="padding-top: 10px; margin-left: 15px;float: left; margin-top: 8px;">出警安排</label>';		
			  html+='<div class="controls" style="display: inline-block;">';		
			  html+='<textarea id="taskTextarea" style="margin-left: 8px; width: 324px;" name="eduhistory" align="center"></textarea>';			
			  html+='</div>';		
			  html+='</div>';	
			  html+='</div>';
			layer.open({
				type: 1,
				title: "消息内容安排",
				area: ["445px", "230px"],
				shade: 0,
				maxmin: false,
				btn: ["发送", "取消"],
				content:html,
				cancel: function() {},
				end: function() {},
				yes: function(index, layero) {
					var destinyX = $('#destinyX').val();
				    var destinyY = $('#destinyY').val();
				    var alarmId = pointAlarmId;
				    var task = $('#taskTextarea').val();
				    var optionDesc = "发送出警信息";
				    var param = {
			    		'type': 2,
		                'userId': strId,
		                'alarmId': alarmId,
		                'task': task,
		                'destinyX': destinyX,
		                'destinyY': destinyY,
		                'optionDesc': optionDesc
		            }
		        	$.getJSON(ctx+'/handle/bphAlarmHandle/sendAlarmInfo',param,function(data){
		        		if(data == true){
		        			layer.msg('消息发送成功');
		        		}else{
		        			layer.msg('消息发送失败');
		        		}
		        	});
		        	layer.close(index);
		        }
		    });
			layer.close(index);
		},
		btn2: function(index, layero) {
			layer.close(index);
		}
	});
}
//电话拨打
function callPhone(_this){
	
}

//发送信息
function sendNote(_this){
	var contactId = '';
	$(_this).parent().parent().parent().children().each(function(){
		if($(this).attr('data-field')=='id'){
			contactId=$(this).children().html();
		}
	});
	var html = '<textarea id="contactMessage" class="contactMessage" style="margin: 0px 0px 10px;height: 85px;width: 399px;"></textarea>'
	 layer.open({
	        type: 1,
	        title: "消息内容安排",
	        area: ["410px", "193px"],
	        maxmin: false,
	        btn: ["发送", "取消"],
	        content: html,
	        cancel: function() {},
	        end: function() {},
	        yes: function(index, layero) {
	        	var contactMessage = $('#contactMessage').val();
	        	var operateDesc = contactMessage;
	        	$.getJSON(ctx+"/alarmhandlelog/bphAlarmHandleLog/sendMessage",{'contactId':contactId,'contactMessageId':contactMessage,'alarmId':alarmSendId,'operateDesc':operateDesc},function(data){
	        		if(data == true){
	        			layer.msg('消息发送成功');
	        		}else{
	        			layer.msg('消息发送失败');
	        		}
	        	});
	        	layer.close(index);
	        }
	    });
}

//定位警员信息
function locationConstable(_this){
	var x = '';
	var y = '';
	$(_this).parent().parent().parent().children().each(function(){
		if($(this).attr('data-field')=='x'){
			x=$(this).children().html();
		}
		if($(this).attr('data-field')=='y'){
			y=$(this).children().html();
		}
	})
	if(x !== undefined && x != null && x != '' && y !== undefined && y != null && y != ''){
		Map.goTo([Number(x), Number(y)]);
		layer.msg('定位成功');
	}else{
		layer.msg('定位失败');
	}
}