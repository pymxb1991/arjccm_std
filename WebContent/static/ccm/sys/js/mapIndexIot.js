var Map, zTreeObjLeft, Pubmap;
var streetFlag = true, communityFlag = false, gridFlag = false, buildFlag = false;
var uuid = "";
$(function() {
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

	// 读取配置信息
	getAlarmSet();

	// 数据接入
	dataAccess();
	// 动态感知
	dynamicPerception();

	// 查询今日预警信息
	getDataByToday();

	// 异常预警
	alarmVerify();
	RaMq();

	// 隐藏区域街道社区房屋楼栋
	treeLeftInit();
	$('#hideOrShowDivBFloat').click(function() {
		$('#hideOrShowDivB').show();
	})
	$('#hideOrShowDivB1Float').click(function() {
		$('#hideOrShowDivB1').show();
	})
	$('#hideOrShowDivRFloat').click(function() {
		$('#hideOrShowDivR').show();
	})
	$('#hideOrShowDivLFloat').click(function() {
		$('#hideOrShowDivL').show();
	})

})

// 隐藏
var ShowHideDivLFlag = false;
function ShowHideDivL(_this) {
	$("#hideOrShowDivL").hide();
}
var ShowHideDivBFlag = true;
function ShowHideDivB(_this) {
	$("#hideOrShowDivB").hide();
}
var ShowHideDivB1Flag = true;
function ShowHideDivB1(_this) {
	$("#hideOrShowDivB1").hide();

}
var ShowHideDivRFlag = true;
function ShowHideDivR(_this) {
	$("#hideOrShowDivR").hide();
}

//点击定位
function goToDetail(x,y,id,info){
	var coordinates=[x,y];
	Map.map.getView().setZoom(18);
	Map.goTo(coordinates);
	Map.selectGISPointer2(id,info,coordinates);
	$('.gis-marker').removeClass('activeMax');
	$('#overlay_'+id).addClass('activeMax');
	$('.gis-marker').parent().removeClass('activeMax');
	$('#overlay_'+id).parent().addClass('activeMax');
}
// 查询今日预警信息
function getDataByToday() {

	$
			.ajax({
				async : false,
				type : "get",
				url : ctx + "/warning/ccmEarlyWarning/getDataByToday",
				success : function(data) {

					for (var i = 0, len = data.length; i < len; i++) {

						var html = '';
						var clock = "";
						var clock1 = "";
						var year, month, day, hh, mm, ss;

						var eventTime = new Date(data[i].alarmDate);
						year = eventTime.getFullYear(); // 年
						month = eventTime.getMonth() + 1; // 月
						day = eventTime.getDate(); // 日
						hh = eventTime.getHours(); // 时
						mm = eventTime.getMinutes(); // 分
						ss = eventTime.getSeconds(); // 秒

						if (month < 10) {
							month = "0" + month;
						}
						if (day < 10) {
							day = "0" + day;
						}
						if (hh < 10) {
							hh = "0" + hh;
						}
						if (mm < 10)
							mm = '0' + mm;

						if (ss < 10) {
							ss = '0' + ss;
						}
						clock = year + "-" + month + "-" + day + " " + hh + ":"
								+ mm + ":" + ss;
						clock1 = year + "年" + month + "月" + day + "日 " + hh
								+ "时" + mm + "分" + ss + "秒";
						var icon = ctxStatic+ '/modules/map/images/video.png';
						if (data[i].alarmType == "1") {// 人脸
							if (!faceSet) {
								return;
							}
                            icon=faceSet.reportAimages;
							html += '<div class="waring">'
							html += '<div class="waring-left"><i title="人员" style="font-size:24px;margin-top: 40px;display: inline-block;" class="icon iconfont icon-xingren"></i></div>'
							html += '<div class="waring-right">'
							html += '<div class="waring-name">' + data[i].name
									+ '<div class="waring-time">' + clock
									+ '</div></div>';
							html += '<div class="waring-details" style="color:red;">'
									+ data[i].remarks + '</div>';
							html += '<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+data[i].x+','+data[i].y+',\''+data[i].id+'\','+JSON.stringify(data[i]).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'
									+ data[i].address + '</a></div>';
							html += '</div></div>';
							var music = faceSet.reportMusic;
							if (music && music.substr(0, 1) == '|') {
								music = music.substr(1);
							}
							html += '<audio  src="' + music
									+ '" preload="auto" autoplay></audio>';
						} else if (data[i].alarmType == "2") {// 车辆
							if (!carSet) {
								return;
							}
                            icon=carSet.reportAimages;
							html += '<div class="waring">'
							html += '<div class="waring-left"><i  title="车辆"  style="font-size:24px;margin-top: 40px;    display: inline-block;"  class="icon iconfont icon-cheliangguanli"></i></div>'
							html += '<div class="waring-right">'
							html += '<div class="waring-name">' + data[i].carid
									+ '<div class="waring-time">' + clock
									+ '</div></div>';
							html += '<div class="waring-details" style="color:#ff8000;">'
									+ data[i].remarks + '</div>';
							html += '<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+data[i].x+','+data[i].y+',\''+data[i].id+'\','+JSON.stringify(data[i]).replace(/"/g, '&quot;')+')" ><i class="icon-map-marker"></i>'
									+ data[i].address + '</a></div>';
							html += '</div></div>';
							var music = carSet.reportMusic;
							if (music && music.substr(0, 1) == '|') {
								music = music.substr(1);
							}
							html += '<audio  src="' + music
									+ '" preload="auto" autoplay></audio>';
						} else if (data[i].alarmType == "3") {// rfid
							if (!rfidSet) {
								return;
							}
                            icon=rfidSet.reportAimages;
							html += '<div class="waring">'
							html += '<div class="waring-left"><i title="rfid"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-shipin"></i></div>'
							html += '<div class="waring-right">'
							html += '<div class="waring-name">' + data[i].rfid
									+ '<div class="waring-time">' + clock
									+ '</div></div>';
							html += '<div class="waring-details" style="color:#c85190;">'
									+ data[i].remarks + '</div>';
							html += '<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+data[i].x+','+data[i].y+',\''+data[i].id+'\','+JSON.stringify(data[i]).replace(/"/g, '&quot;')+')" ><i class="icon-map-marker"></i>'
									+ data[i].address + '</a></div>';
							html += '</div></div>';
							var music = rfidSet.reportMusic;
							if (music && music.substr(0, 1) == '|') {
								music = music.substr(1);
							}
							html += '<audio  src="' + music
									+ '" preload="auto" autoplay></audio>';
						} else if (data[i].alarmType == "4") {// wifi
							if (!wifiSet) {
								return;
							}
                            icon=wifiSet.reportAimages;
							html += '<div class="waring">'
							html += '<div class="waring-left"><i title="wifi"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-wifi"></i></div>'
							html += '<div class="waring-right">'
							html += '<div class="waring-name">'
									+ data[i].macAddress
									+ '<div class="waring-time">' + clock
									+ '</div></div>';
							html += '<div class="waring-details" style="color:#c85190;">'
									+ data[i].remarks + '</div>';
							html += '<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+data[i].x+','+data[i].y+',\''+data[i].id+'\','+JSON.stringify(data[i]).replace(/"/g, '&quot;')+')" ><i class="icon-map-marker"></i>'
									+ data[i].address + '</a></div>';
							html += '</div></div>';
							var music = wifiSet.reportMusic;
							if (music && music.substr(0, 1) == '|') {
								music = music.substr(1);
							}
							html += '<audio  src="' + music
									+ '" preload="auto" autoplay></audio>';
						} else if (data[i].alarmType == "5") {// 手机电子围栏
							if (!phoneSet) {
								return;
							}
                            icon=phoneSet.reportAimages;
							html += '<div class="waring">'
							html += '<div class="waring-left"><i title="手机电子围栏"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-wanggehuadianhua"></i></div>'
							html += '<div class="waring-right">'
							html += '<div class="waring-name">' + data[i].phone
									+ '<div class="waring-time">' + clock
									+ '</div></div>';
							html += '<div class="waring-details" style="color:#d34413;">'
									+ data[i].remarks + '</div>';
							html += '<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+data[i].x+','+data[i].y+',\''+data[i].id+'\','+JSON.stringify(data[i]).replace(/"/g, '&quot;')+')" ><i class="icon-map-marker"></i>'
									+ data[i].address + '</a></div>';
							html += '</div></div>';
							var music = phoneSet.reportMusic;
							if (music && music.substr(0, 1) == '|') {
								music = music.substr(1);
							}
							html += '<audio  src="' + music
									+ '" preload="auto" autoplay></audio>';
						} else if (data[i].alarmType == "6") {// GPS
							if (!gpsSet) {
								return;
							}
                            icon=gpsSet.reportAimages;
							html += '<div class="waring">'
							html += '<div class="waring-left"><i title="GPS"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-bukongyujing"></i></div>'
							html += '<div class="waring-right">'
							html += '<div class="waring-name">' + data[i].imei
									+ '<div class="waring-time">' + clock
									+ '</div></div>';
							html += '<div class="waring-details" style="color:#d34413;">'
									+ data[i].remarks + '</div>';
							html += '<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+data[i].x+','+data[i].y+',\''+data[i].id+'\','+JSON.stringify(data[i]).replace(/"/g, '&quot;')+')" ><i class="icon-map-marker"></i>'
									+ data[i].address + '</a></div>';
							html += '</div></div>';
							var music = gpsSet.reportMusic;
							if (music && music.substr(0, 1) == '|') {
								music = music.substr(1);
							}
							html += '<audio  src="' + music
									+ '" preload="auto" autoplay></audio>';
						} else {
							return;
						}
						
						// 修改异常预警显示数量
						$('#alarmCount')
						.html(parseInt($('#alarmCount').html()) + 1);
						// 将预警内容插入列表最头部
						$('#abnormalWarning').prepend(html);
                        var geo = {
                            features:[{
                                id:data[i].id,
                                geometry:{
                                    type: "Point",
                                    coordinates:[data[i].x,data[i].y]
                                },
                                properties: data[i],
                                icon:icon,
                                type:"Feature",
                            }],
                            count: 1,
                            pageNo: 1,
                            pageSize: 10,
                            type: "FeatureCollection"
                        };
                        // var featuresData = null;
                        // geo.features = featuresData;
                        // featuresData.id = data[i].id;
                        // featuresData.geometry.coordinates[0] = data[i].x
                        // featuresData.geometry.coordinates[1] = data[i].y;
                        // featuresData.properties.info["测试"]="测试信息";
                        Map.addGIS2([ {
                            'type' : 'GIS',
                            'id' : 'GIS',
                            'data' : geo,
                            'isShow' : true
                        } ])
					}


				}
			});
}

var webServicePath = window.location.href.substring(0, window.location.href
		.indexOf("arjccm"))
		+ "arjccm/app/";

// 注册uuid
function alarmVerify() {

	$.ajax({
		async : false,
		type : "get",
		cache : false,
		url : webServicePath + "rest/event/subscribeAmq1",
		success : function(data) {
			if (data) {
				uuid = data.result;// 获取到uuid
			}
		}
	});
}
// 订阅设备
function orderRabbitMQInfo() {
	var userId = $('#userid').val();
	var json = {
		'clientId' : uuid,
		'userId' : userId
	};
	var param = encodeURI(JSON.stringify(json));
	$.post(webServicePath + 'rest/event/orderRabbitMQInfo', {
		'json' : param
	}, function(data) {
		var data = JSON.parse(data);
		if (data.ret == '0') {
			// 开启心跳
			timer = window.setInterval('sendHeartBeat()', 120000)
		}
	})
}
// 心跳
function sendHeartBeat() {
	var userId = $('#userid').val();
	var json = {
		'clientId' : uuid,
		'userId' : userId
	};
	var param = encodeURI(JSON.stringify(json));
	var dateTime = new Date()
	$.post(webServicePath + 'rest/event/sendFenceHeartBeat?date=' + dateTime
			+ '', {
		'json' : param
	}, function(data) {
		var data = JSON.parse(data);
		if (data.ret == '0') {
		}
	})
}
function RaMq() {
	// 初始化 ws 对象
	var ws = new WebSocket('ws://192.168.1.106:15674/ws');
	// 获得Stomp client对象
	var client = Stomp.over(ws);
	client.heartbeat.outgoing = 10000;// mq心跳
	client.heartbeat.incoming = 0;
	// 定义连接成功回调函数
	var on_connect = function(x) {
		orderRabbitMQInfo();
		// client.subscribe("/exchange/"+"amq.fanout", function(msg) {
		client
				.subscribe(
						"/queue/" + uuid + "",
						function(msg) {
							console.log("-------------------------------");
							console.log(msg);
							// 解析对象字符串
							var msgObj = (msg.body).replace(
									/"(\w+)"(\s*:\s*)/g, "$1$2");
							var obj = eval('(' + msgObj + ')');
			console.info("obj",obj);
							var html = '';
							var clock = "";
							var clock1 = "";
							var year, month, day, hh, mm, ss;
							var eventTime = new Date(obj["alarmDate"].time);
							year = eventTime.getFullYear(); // 年
							month = eventTime.getMonth() + 1; // 月
							day = eventTime.getDate(); // 日
							hh = eventTime.getHours(); // 时
							mm = eventTime.getMinutes(); // 分
							ss = eventTime.getSeconds(); // 秒

							if (month < 10) {
								month = "0" + month;
							}
							if (day < 10) {
								day = "0" + day;
							}
							if (hh < 10) {
								hh = "0" + hh;
							}
							if (mm < 10)
								mm = '0' + mm;

			if (ss < 10){
				ss = '0'+ss;
			}
			clock = year + "-"+month+"-"+day+" "+hh+":"+mm+":"+ss;
			clock1=year + "年"+month+"月"+day+"日 "+hh+"时"+mm+"分"+ss+"秒";
			var icon = ctxStatic+ '/modules/map/images/video.png';
			if(obj.alarmType=="1"){//人脸
				if(!faceSet){
					return;
				}
				icon=faceSet.reportAimages;
				html+='<div class="waring">'
				html+='<div class="waring-left"><i title="人员" style="font-size:24px;margin-top: 40px;display: inline-block;" class="icon iconfont icon-xingren"></i></div>'
				html+='<div class="waring-right">'
				html+='<div class="waring-name">'+obj.name+'<div class="waring-time">'+clock+'</div></div>';
				html+='<div class="waring-details" style="color:red;">'+obj.remarks+'</div>';
				html+='<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+obj.x+','+obj.y+',\''+obj.id+'\','+JSON.stringify(obj).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'+obj.address+'</a></div>';
				html+='</div></div>';
				var music = faceSet.reportMusic;
				if (music&&music.substr(0,1)=='|'){
					music=music.substr(1);
				}
				html+='<audio  src="'+music+'" preload="auto" autoplay></audio>';
			}else if(obj.alarmType=="2"){//车辆
				if(!carSet){
					return;
				}
				icon=carSet.reportAimages;
				html+='<div class="waring">'
				html+='<div class="waring-left"><i  title="车辆"  style="font-size:24px;margin-top: 40px;    display: inline-block;"  class="icon iconfont icon-cheliangguanli"></i></div>'
				html+='<div class="waring-right">'
				html+='<div class="waring-name">'+obj.carid+'<div class="waring-time">'+clock+'</div></div>';
				html+='<div class="waring-details" style="color:#ff8000;">'+obj.remarks+'</div>';
				html+='<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+obj.x+','+obj.y+',\''+obj.id+'\','+JSON.stringify(obj).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'+obj.address+'</a></div>';
				html+='</div></div>';
				var music = carSet.reportMusic;
				if (music&&music.substr(0,1)=='|'){
					music=music.substr(1);
				}
				html+='<audio  src="'+music+'" preload="auto" autoplay></audio>';
			}else if(obj.alarmType=="3"){//rfid
				if(!rfidSet){
					return;
				}
				icon=rfidSet.reportAimages;
				html+='<div class="waring">'
				html+='<div class="waring-left"><i title="rfid"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-shipin"></i></div>'
				html+='<div class="waring-right">'
				html+='<div class="waring-name">'+obj.rfid+'<div class="waring-time">'+clock+'</div></div>';
				html+='<div class="waring-details" style="color:#c85190;">'+obj.remarks+'</div>';
				html+='<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+obj.x+','+obj.y+',\''+obj.id+'\','+JSON.stringify(obj).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'+obj.address+'</a></div>';
				html+='</div></div>';
				var music = rfidSet.reportMusic;
				if (music&&music.substr(0,1)=='|'){
					music=music.substr(1);
				}
				html+='<audio  src="'+music+'" preload="auto" autoplay></audio>';
			}else if(obj.alarmType=="4"){//wifi
				if(!wifiSet){
					return;
				}
				icon=wifiSet.reportAimages;
				html+='<div class="waring">'
				html+='<div class="waring-left"><i title="wifi"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-wifi"></i></div>'
				html+='<div class="waring-right">'
				html+='<div class="waring-name">'+obj.macAddress+'<div class="waring-time">'+clock+'</div></div>';
				html+='<div class="waring-details" style="color:#c85190;">'+obj.remarks+'</div>';
				html+='<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+obj.x+','+obj.y+',\''+obj.id+'\','+JSON.stringify(obj).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'+obj.address+'</a></div>';
				html+='</div></div>';
				var music = wifiSet.reportMusic;
				if (music&&music.substr(0,1)=='|'){
					music=music.substr(1);
				}
				html+='<audio  src="'+music+'" preload="auto" autoplay></audio>';
			}else if(obj.alarmType=="5"){//手机电子围栏
				if(!phoneSet){
					return;
				}
				icon=phoneSet.reportAimages;
				html+='<div class="waring">'
				html+='<div class="waring-left"><i title="手机电子围栏"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-wanggehuadianhua"></i></div>'
				html+='<div class="waring-right">'
				html+='<div class="waring-name">'+obj.phone+'<div class="waring-time">'+clock+'</div></div>';
				html+='<div class="waring-details" style="color:#d34413;">'+obj.remarks+'</div>';
				html+='<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+obj.x+','+obj.y+',\''+obj.id+'\','+JSON.stringify(obj).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'+obj.address+'</a></div>';
				html+='</div></div>';
				var music = phoneSet.reportMusic;
				if (music&&music.substr(0,1)=='|'){
					music=music.substr(1);
				}
				html+='<audio  src="'+music+'" preload="auto" autoplay></audio>';
			}else if(obj.alarmType=="6"){//GPS
				if(!gpsSet){
					return;
				}
				icon=gpsSet.reportAimages;
				html+='<div class="waring">'
				html+='<div class="waring-left"><i title="GPS"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-bukongyujing"></i></div>'
				html+='<div class="waring-right">'
				html+='<div class="waring-name">'+obj.imei+'<div class="waring-time">'+clock+'</div></div>';
				html+='<div class="waring-details" style="color:#d34413;">'+obj.remarks+'</div>';
				html+='<div class="waring-address"><a href="javascript:;" style="color:#c1c420" onclick="goToDetail('+obj.x+','+obj.y+',\''+obj.id+'\','+JSON.stringify(obj).replace(/"/g, '&quot;')+')"><i class="icon-map-marker"></i>'+obj.address+'</a></div>';
				html+='</div></div>';
				var music = gpsSet.reportMusic;
				if (music&&music.substr(0,1)=='|'){
					music=music.substr(1);
				}
				html+='<audio  src="'+music+'" preload="auto" autoplay></audio>';
			}else {
				return;
			}
			//修改异常预警显示数量
			$('#alarmCount').html(parseInt($('#alarmCount').html())+1);
			//将预警内容插入列表最头部
			$('#abnormalWarning').prepend(html);

			var geo = {
					features:[{
						id:obj.id,
						geometry:{
							type: "Point",
							coordinates:[obj.x,obj.y]
						},
						properties: obj,
						icon:icon,
						type:"Feature",
					}],
				count: 1,
				pageNo: 1,
				pageSize: 10,
				type: "FeatureCollection"
			};
			// var featuresData = null;
			// geo.features = featuresData;
			// featuresData.id = obj.id;
			// featuresData.geometry.coordinates[0] = obj.x
			// featuresData.geometry.coordinates[1] = obj.y;
			// featuresData.properties.info["测试"]="测试信息";
			Map.addGIS2([ {
				'type' : 'GIS',
				'id' : 'GIS',
				'data' : geo,
				'isShow' : true
			} ])


		});
	};
	// 定义错误时回调函数
	var on_error = function() {
		console.error('error');
	};
	// 连接RabbitMQ
	client.connect('arj', '123456', on_connect, on_error, '/');
}
// 数据接入
function dataAccess() {
	// 标准地址
	$.getJSON(ctx + "/house/ccmHouseBuildmanage/findListNum", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#standardAddress').html("0");
		} else {
			$('#standardAddress').html(dataStr);
		}
	});
	// 实有人口
	$.getJSON(ctx + "/pop/ccmPeople/findCount", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#realPopulation').html("0");
			$('#realPopulation2').html("0");
		} else {
			$('#realPopulation').html(dataStr);
			$('#realPopulation2').html(dataStr);
		}
	});
	// 实有房屋
	$.getJSON(ctx + "/pop/ccmPopTenant/findCount", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#realHouse').html("0");
		} else {
			$('#realHouse').html(dataStr);
		}
	});
	// 实有单位
	$.getJSON(ctx + "/org/ccmOrgNpse/getqiyeNumData", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#realCompany').html("0");
		} else {
			$('#realCompany').html(dataStr);
		}
	});
	// 动态库&静态库
	$.getJSON(ctx + "/list/ccmList/getCount", function(data) {
		var dynamicLibrary = JSON.stringify(data.dynamicLibrary)
		var staticLibrary = JSON.stringify(data.staticLibrary)
		if (dynamicLibrary == null || dynamicLibrary == "") {
			$('#dynamicLibrary').html("0");
		} else {
			$('#dynamicLibrary').html(dynamicLibrary);
		}
		if (staticLibrary == null || staticLibrary == "") {
			$('#staticLibrary').html("0");
		} else {
			$('#staticLibrary').html(staticLibrary);
		}
	});
	// 抓拍机
	$.getJSON(ctx + "/grabber/ccmGrabberManage/getCount", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#faceGrabber').html("0");
		} else {
			$('#faceGrabber').html(dataStr);
		}
	});

	// 卡口
	$.getJSON(ctx + "/bayonet/ccmCarBayonet/getCount", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#bayonet').html("0");
		} else {
			$('#bayonet').html(dataStr);
		}
	});
	// 车辆布控数量
	$.getJSON(ctx + "/car/ccmCarControl/getCount", function(data) {
		var dataStr = JSON.stringify(data)
		if (dataStr == null || dataStr == "") {
			$('#carControl').html("0");
		} else {
			$('#carControl').html(dataStr);
		}
	});

	// rfid、wifi、gps、手机电子围栏布控
	$.getJSON(ctx + "/device/ccmDeviceControl/getCount", function(data) {
		$('#wifiContol').html("0");
		$('#rfidControl').html("0");
		$('#gpsControl').html("0");
		$('#phoneControl').html("0");
		for (var i = 0; i < data.length; i++) {
			switch (data[i].control_by) {
			case "1":
				$('#wifiContol').html(data[i].count);
				break;
			case "2":
				$('#rfidControl').html(data[i].count);
				break;
			case "3":
				$('#gpsControl').html(data[i].count);
				break;
			case "4":
				$('#phoneControl').html(data[i].count);
				break;
			}
		}
	});

}
// 动态感知
function dynamicPerception() {
	// 今天
	$.getJSON(ctx + "/warning/ccmEarlyWarning/getSortCountToday",
			function(data) {
				$('#faceAlarm').html("0");
				$('#carAlarm').html("0");
				$('#rfidAlarm').html("0");
				$('#wifiAlarm').html("0");
				$('#phoneAlarm').html("0");
				$('#gpsAlarm').html("0");
				for (var i = 0; i < data.length; i++) {
					switch (data[i].alarm_type) {
					case "1":
						$('#faceAlarm').html(data[i].count);
						break;
					case "2":
						$('#carAlarm').html(data[i].count);
						break;
					case "3":
						$('#rfidAlarm').html(data[i].count);
						break;
					case "4":
						$('#wifiAlarm').html(data[i].count);
						break;
					case "5":
						$('#phoneAlarm').html(data[i].count);
						break;
					case "6":
						$('#gpsAlarm').html(data[i].count);
						break;
					}
				}
			});
	// 7天
	$.getJSON(ctx + "/warning/ccmEarlyWarning/getSortCountWeek",
			function(data) {
				$('#faceAlarm2').html("0");
				$('#carAlarm2').html("0");
				$('#rfidAlarm2').html("0");
				$('#wifiAlarm2').html("0");
				$('#phoneAlarm2').html("0");
				$('#gpsAlarm2').html("0");
				for (var i = 0; i < data.length; i++) {
					switch (data[i].alarm_type) {
					case "1":
						$('#faceAlarm2').html(data[i].count);
						break;
					case "2":
						$('#carAlarm2').html(data[i].count);
						break;
					case "3":
						$('#rfidAlarm2').html(data[i].count);
						break;
					case "4":
						$('#wifiAlarm2').html(data[i].count);
						break;
					case "5":
						$('#phoneAlarm2').html(data[i].count);
						break;
					case "6":
						$('#gpsAlarm2').html(data[i].count);
						break;
					}
				}
			});
}

var faceSet = false, // 人脸预警
carSet = false, // 车辆预警
rfidSet = false, // rfid
wifiSet = false, // wifi
phoneSet = false, // 手机电子电子围栏
gpsSet = false;// GPS定位预警
//只有在配置中设置过得预警类型才能被查询出来
function getAlarmSet() {
	$.ajax({
		async : false,
		type : "get",
		cache : false,
		url : ctx + "/configure/ccmReportConfigure/getList",
		success : function(data) {
			console.info("data", data);
			for (var i = 0; i < data.length; i++) {
				switch (data[i].reportType) {
				case "01":
					faceSet = data[i];
					break;
				case "02":
					carSet = data[i];
					break;
				case "03":
					rfidSet = data[i];
					break;
				case "04":
					wifiSet = data[i];
					break;
				case "05":
					phoneSet = data[i];
					break;
				case "06":
					gpsSet = data[i];
					break;
				}
			}

		}
	});
}

// 隐藏区域街道社区房屋楼栋-------------------------------------------------区域------------------------------------------------

function treeLeftInit() {

	// ztreeLeft
	var settingLeft = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : getCheckedNodesLeft
		}
	};

	var zNodesleft = [ {
		id : "communityGrid",
		pId : 0,
		name : "区域",
		open : true,
		checked : false
	}, {
		id : "street",
		pId : "communityGrid",
		name : "街道",
		type : 4,
		icon : ctxStatic + '/modules/map/images/tree_jiedao.png',
		checked : false
	}, {
		id : "community",
		pId : "communityGrid",
		name : "社区",
		type : 1,
		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
		checked : false
	}, {
		id : "grid",
		pId : "communityGrid",
		name : "网格",
		type : 2,
		icon : ctxStatic + '/modules/map/images/tree_grid.png',
		checked : false
	}, {
		id : "build",
		pId : "communityGrid",
		name : "楼栋",
		type : 0,
		typeVal : 1,
		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
		checked : true
	} ];

	var communityGridVal = 0;
	var streetVal = 0;
	var communityVal = 0;
	var gridVal = 0;
	var buildVal = 0;
	var eventVal = 0;
	var partsVal = '';// 城市部件
	var landsVal = 0;// 土地
	var videoVal = 0;
	var PopulationDensityVal = 0;
	var schoolPlaceVal = 0;
	var keyPlaceVal = 0;
	var keyPersonVal = "";
	var keyPersonValCon = "";
	var rentingPersonVal = 0;
	var publicPlaceVal = "";
	var popLocationVal = 0
	var keyPersonHandleVal = 0;

	eventFlag = true;
	streetFlag = false;
	communityFlag = true;
	gridFlag = false;
	buildFlag = false;
	vccmorgFlag = false;
	partsFlag = true;
	landsFlag = true;// 土地
	videoFlag = true;
	PopulationDensityFlag = true;
	schoolPlaceFlag = true;
	keyPlaceFlag = true;
	keyPersonFlag = true;
	keyPersonFlagCon = true;
	rentingPersonFlag = true;
	publicPlaceFlag = true;
	popLocationFlag = true;

	// 数值对比
	function isEqual(a, b) {
		if (a == b) {
			return false
		} else {
			return a = b;
		}
	}
	var PopLocationTime;

	$.fn.zTree.init($("#treeLeft"), settingLeft, zNodesleft);
	zTreeObjLeft = $.fn.zTree.getZTreeObj("treeLeft");

	// 点击今日事件勾选视频监控
	checkVideoNode = function() {
		$('#VideoFlag').click();
		shipinjiankongFlag = false;
	}

	checkPopNode = function() {
		var popNode = zTreeObj.getNodeByParam("id", "PopLocation", null);
		zTreeObj.checkNode(popNode, true);
		popLocationData = 1

		var PublicSecurityNode = zTreeObj.getNodeByParam("id",
				"PublicSecurity", null);
		zTreeObj.checkNode(PublicSecurityNode, true);
		publicPlaceData = "1";
		// getCheckedNodes();
	}
	checkPopNodeNot = function() {
		var popNode = zTreeObj.getNodeByParam("id", "PopLocation", null);
		zTreeObj.checkNode(popNode, false);
		popLocationData = 0
		var PublicSecurityNode = zTreeObj.getNodeByParam("id",
				"PublicSecurity", null);
		zTreeObj.checkNode(PublicSecurityNode, false);
		publicPlaceData = "";
		// getCheckedNodes();
	}

	function getCheckedNodes() {

		var checked = "";
		var checkedNodes = zTreeObj.getCheckedNodes(true);
		if (checkedNodes.length != 0) {
			var communityGridData = 0;
			var streetData = 0;
			var communityData = 0;
			var gridData = 0;
			var buildData = 0;
			var eventData = 0;
			var partsData = '';
			var landsData = 0;// 土地
			var videoData = 0;
			var PopulationDensityData = 0;
			var schoolPlaceData = 0;
			var keyPlaceData = 0;
			var keyPersonData = 0;
			var keyPersonDataCon = 0;
			var keyPersonStr = "";
			var keyPersonStrCon = "";
			var rentingPersonData = 0;
			publicPlaceData = "";
			popLocationData = 0
			var keyPersonHandleData = 0;

			for (var i = 0; i < checkedNodes.length; i++) {
				if (!checkedNodes[i].isParent) {
					checked += checkedNodes[i].id + ",";
					if (checkedNodes[i].pId == 'communityGrid') { // 街道社区网格
						communityGridData += parseInt(checkedNodes[i].type);
						if (checkedNodes[i].id == 'bulid') { // 楼栋
							buildData += parseInt(checkedNodes[i].typeVal)
						}
					} else if (checkedNodes[i].id == 'event') { // 案事件
						eventData += parseInt(checkedNodes[i].type)
					} else if (checkedNodes[i].pId == 'parts') { // 城市部件
						partsData += checkedNodes[i].type;

					} else if (checkedNodes[i].id == 'lands') { // 土地
						landsData += parseInt(checkedNodes[i].type);

					} else if (checkedNodes[i].id == 'video') { // 视频监控
						videoData += parseInt(checkedNodes[i].type);

					} else if (checkedNodes[i].pId == 'schoolPlace') { // 学校
						schoolPlaceData += parseInt(checkedNodes[i].type);
					} else if (checkedNodes[i].pId == 'keyPlace') { // 重点场所
						keyPlaceData += parseInt(checkedNodes[i].type);
					} else if (checkedNodes[i].pId == 'keyPerson') { // 重点人员楼栋

						// keyPersonData += parseInt(checkedNodes[i].type);
						keyPersonStr += checkedNodes[i].flag + ","
					} else if (checkedNodes[i].pId == 'keyPersonCountry') { // 重点人员分布

						// keyPersonData += parseInt(checkedNodes[i].type);
						keyPersonStrCon += checkedNodes[i].flag + ","
					} else if (checkedNodes[i].id == 'rentingPerson') { // 出租屋楼栋
						rentingPersonData += parseInt(checkedNodes[i].type);
					} else if (checkedNodes[i].pId == 'publicPlace') { // 公共机构
						publicPlaceData += checkedNodes[i].type;
					} else if (checkedNodes[i].id == 'PopulationDensity') {
						PopulationDensityData += parseInt(checkedNodes[i].type);
					} else if (checkedNodes[i].id == 'keyPersonHandle') {

						keyPersonHandleData += parseInt(checkedNodes[i].type);
					} else if (checkedNodes[i].id == 'PopLocation') {

						popLocationData += parseInt(checkedNodes[i].type);
					}

					/*
					 * if(checkedNodes[i].id == 'PopLocation'){
					 * 
					 * popLocationFlag=true
					 * Map.layersIsShow('PopLocation',true); }else{
					 * 
					 * popLocationFlag=false
					 * Map.layersIsShow('PopLocation',false); }
					 */

				}
			}
			if (communityGridVal != communityGridData && communityGridData != 0) {
				communityGridVal = communityGridData;
				// alert('社区网格图层加载' + communityGridVal)
				Map.removeLayer('streets');
				Map.removeLayer('communitys');
				Map.removeLayer('grids');
				if (communityGridVal == '1') {

					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						Map.addJSON1([ {
							'type' : 'communitys',
							'data' : data,
							'isShow' : communityFlag
						} ])

					})
					console.log()
				} else if (communityGridVal == '2') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						Map.addJSON1([ {
							'type' : 'grids',
							'data' : data,
							'isShow' : gridFlag
						} ])

					})
				} else if (communityGridVal == '4') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						console.log(data)
						Map.addJSON1([ {
							'type' : 'streets',
							'data' : data,
							'isShow' : streetFlag
						} ])

					})
				} else if (communityGridVal == '3') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
							function(data) {
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
				} else if (communityGridVal == '5') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
							function(data) {
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				} else if (communityGridVal == '6') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				} else if (communityGridVal == '7') {

					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1 ',
							function(data) {

								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				}

			} else if (communityGridVal != communityGridData
					&& communityGridData == 0) {
				communityGridVal = communityGridData;
				// alert('社区网格图层清除')
				Map.removeLayer('streets');
				Map.removeLayer('communitys');
				Map.removeLayer('grids');
			} else {
				// alert('社区网格图层不变')
			}
			if (buildVal != buildData && buildData != 0) {
				buildVal = buildData;
				// alert('案事件图层加载')
				Map.removeLayer('builds');

				$.getJSON('' + ctx + '/sys/map/buildmanageMap', function(data) {
					Map.addJSON1([ {
						'type' : 'builds',
						'data' : data,
						'isShow' : buildFlag,
					} ])
				})

			} else if (buildVal != buildData && buildData == 0) {
				buildVal = buildData;
				// alert('案事件图层清除')
				Map.removeLayer('builds');
			} else {
				// alert('案事件图层不变')
			}

			if (eventVal != eventData && eventData != 0) {
				eventVal = eventData;
				// alert('案事件图层加载')
				Map.removeLayer('events');

				$.getJSON('' + ctx + '/sys/map/eventIncidentMap?type='
						+ eventVal + '', function(data) {
					Map.addJSON1([ {
						'type' : 'events',
						'data' : data,
						'isShow' : eventFlag,
					} ])
				})

			} else if (eventVal != eventData && eventData == 0) {
				eventVal = eventData;
				// alert('案事件图层清除')
				Map.removeLayer('events');
			} else {
				// alert('案事件图层不变')
			}
			if (partsVal != partsData && partsData != '') {
				partsVal = partsData;
				// alert('城市部件加载')
				Map.removeLayer('parts');
				$.getJSON('' + ctx + '/sys/map/cityComponentsMap?type='
						+ partsVal + '', function(data) {
					Map.addJSON1([ {
						'type' : 'parts',
						'data' : data,
						'isShow' : partsFlag
					} ])
				})

			} else if (partsVal != partsData && partsData == '') {
				partsVal = partsData;
				Map.removeLayer('parts');
				// alert('城市部件清除')
			} else {
				// alert('城市部件不变')
			}

			if (landsVal != landsData && landsData != 0) {
				landsVal = landsData;
				// alert('土地加载')
				Map.removeLayer('lands');
				$.getJSON('' + ctx + '/sys/map/landMap', function(data) {
					Map.addJSON1([ {
						'type' : 'lands',
						'data' : data,
						'isShow' : landsFlag
					} ])
				})

			} else if (landsVal != landsData && landsData == 0) {
				landsVal = landsData;
				Map.removeLayer('lands');
				// alert('土地清除')
			} else {
				// alert('土地不变')
			}

			if (videoVal != videoData && videoData != 0) {

				videoVal = videoData;
				// alert('视频监控加载')
				Map.removeLayer('videos');
				$.getJSON('' + ctx + '/sys/map/deviceiveMap', function(data) {
					Map.addJSON1([ {
						'type' : 'videos',
						'data' : data,
						'isShow' : videoFlag
					} ])
				})

			} else if (videoVal != videoData && videoData == 0) {

				videoVal = videoData;
				Map.removeLayer('videos');
				// alert('视频监控清除')
			} else {

				// alert('视频监控不变')
			}
			if (PopulationDensityVal != PopulationDensityData
					&& PopulationDensityData != 0) {
				PopulationDensityVal = PopulationDensityData;
				// alert('人口密度')

				/* 热力图 */
				$.getJSON('' + ctx + '/sys/map/buildHeatMap', function(
						heatmapData) {
					Map.removeLayer('heatMap')
					Map.heatMap1(heatmapData);
					Map.layersIsShow('heatMap', true);
					// //Map.layersIsShow('heatMap',true);

				})

			} else if (PopulationDensityVal != PopulationDensityData
					&& PopulationDensityData == 0) {
				PopulationDensityVal = PopulationDensityData;
				Map.removeLayer('heatMap')
				// alert('人口密度')
			} else {
				// alert('人口密度')
			}

			function GetPopLocation() {
				Map.removeLayer('PopLocation');
				$.getJSON('' + ctx + '/sys/map/deviceMobileMap',
						function(data) {
							Map.addJSON1([ {
								'type' : 'PopLocation',
								'data' : data,
								'isShow' : popLocationFlag
							} ]);
						})

				Map.layersIsShow('PopLocation', true);
			}

			// 重点人员管控

			if (keyPersonHandleVal != keyPersonHandleData
					&& keyPersonHandleData != 0) {

				keyPersonHandleVal = keyPersonHandleData;
				// alert('重点人员管控')
				checkPopNode()
				$
						.getJSON(
								'' + ctx + '/sys/map/ccmPatrolPointPlanMap',
								function(data) {
									var lenData = data.length;
									var color = [ "#62c000", "#8e7513",
											"#3268ff", "#e73400", "#72fffd",
											"#c4c501", "#ffff80", "#fd6d01",
											"#951167", "#e822e7", "#804040" ]
									if (lenData > 0) {

										for (var i = 0; i < lenData; i++) {
											var str = data[i].value;
											var routeCoords = str.split(';')
											var len = routeCoords.length;
											var id = data[i].type
											var colorLine = color[i]
											Map.removeLayer('' + id + '');
											var pushArr = []
											for (var j = 0; j < len; j++) {
												var value = [];
												value[0] = Number(routeCoords[j]
														.split(',')[0]);
												value[1] = Number(routeCoords[j]
														.split(',')[1]);
												pushArr.push(value);
											}
											Map.trackReplayLu(' ', 5, pushArr,
													id, colorLine);
										}
									}

								})
				keyPersonHandleLayerFun()

			} else if (keyPersonHandleVal != keyPersonHandleData
					&& keyPersonHandleData == 0) {

				keyPersonHandleVal = keyPersonHandleData;
				checkPopNodeNot()
				$.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap',
						function(data) {
							var len = data.length;
							if (len > 0) {
								for (var i = 0; i < len; i++) {
									var id = data[i].type
									Map.removeLayer('' + id + '');
								}
							}
						})
				keyPersonHandleLayerClose()
				// alert('重点人员管控')
			} else {
				// alert('重点人员管控')
			}

			if (popLocationVal != popLocationData && popLocationData != 0) {
				popLocationVal = popLocationData;
				// alert('实时定位')
				// 实时定位
				GetPopLocation()
				PopLocationTime = setInterval(function() // 开启循环：每秒出现一次提示框
				{
					GetPopLocation()
				}, 15000);

			} else if (popLocationVal != popLocationData
					&& popLocationData == 0) {
				popLocationVal = popLocationData;
				Map.removeLayer('ElectronicFence');
				Map.removeLayer('trackReplay');
				Map.removeLayer('PopLocation');

				clearInterval(PopLocationTime);
				// alert('实时定位')
			} else {
				// alert('实时定位')
			}

			if (schoolPlaceVal != schoolPlaceData && schoolPlaceData != 0) {
				schoolPlaceVal = schoolPlaceData;
				// alert('重点场所加载')
				Map.removeLayer('schoolPlace');
				$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type='
						+ schoolPlaceVal + '', function(data) {
					Map.addJSON1([ {
						'type' : 'schoolPlace',
						'data' : data,
						'isShow' : schoolPlaceFlag
					} ])

				})
			} else if (schoolPlaceVal != schoolPlaceData
					&& schoolPlaceData == 0) {
				schoolPlaceVal = schoolPlaceData;
				Map.removeLayer('schoolPlace');
				// alert('重点场所清除')
			} else {
				// alert('重点场所不变')
			}

			if (keyPlaceVal != keyPlaceData && keyPlaceData != 0) {
				keyPlaceVal = keyPlaceData;
				// alert('重点场所加载')
				Map.removeLayer('keyPlace');
				$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type='
						+ keyPlaceVal + '', function(data) {
					Map.addJSON1([ {
						'type' : 'keyPlace',
						'data' : data,
						'isShow' : keyPlaceFlag
					} ])

				})
			} else if (keyPlaceVal != keyPlaceData && keyPlaceData == 0) {
				keyPlaceVal = keyPlaceData;
				Map.removeLayer('keyPlace');
				// alert('重点场所清除')
			} else {
				// alert('重点场所不变')
			}

			keyPersonStr = keyPersonStr.substring(0, keyPersonStr.length - 1)
			if (keyPersonStr != "" && keyPersonVal != keyPersonStr) {
				// alert('重点人员加载')

				keyPersonVal = keyPersonStr;
				$.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=1&flag='
						+ keyPersonStr, function(data) {
					Map.removeLayer('keyPerson');
					Map.addJSON1([ {
						'type' : 'keyPerson',
						'data' : data,
						'isShow' : keyPersonFlag
					} ])
				})
			} else if (keyPersonStr == "" && keyPersonVal != keyPersonStr) {
				// alert('重点人员清除')
				keyPersonVal = keyPersonStr;
				Map.removeLayer('keyPerson');
			} else {

				// alert('重点人员不变')
			}

			// 重点人员分布
			keyPersonStrCon = keyPersonStrCon.substring(0,
					keyPersonStrCon.length - 1)
			if (keyPersonStrCon != "" && keyPersonValCon != keyPersonStrCon) {
				// alert('重点人员分布')

				keyPersonValCon = keyPersonStrCon;
				$.getJSON('' + ctx + '/sys/map/buildHeatMap?type='
						+ keyPersonStrCon, function(data) {
					Map.removeLayer('heatMap')
					/* 热力图 */
					Map.heatMap1(data);
					Map.layersIsShow('heatMap', true);
				})
			} else if (keyPersonStrCon == ""
					&& keyPersonValCon != keyPersonStrCon) {
				// alert('重点人员分布清除')
				keyPersonValCon = keyPersonStrCon;
				Map.removeLayer('heatMap')
			} else {
				// alert('重点人员分布不变')
			}

			if (rentingPersonVal != rentingPersonData && rentingPersonData != 0) {
				rentingPersonVal = rentingPersonData;
				// alert('出租屋楼栋加载')
				Map.removeLayer('rentingPerson');

				$.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=2',
						function(data) {
							Map.addJSON1([ {
								'type' : 'rentingPerson',
								'data' : data,
								'isShow' : rentingPersonFlag
							} ])
						})
			} else if (rentingPersonVal != rentingPersonData
					&& rentingPersonData == 0) {
				rentingPersonVal = rentingPersonData;
				Map.removeLayer('rentingPerson');
				// alert('出租屋楼栋清除')
			} else {
				// alert('出租屋楼栋不变')
			}

			if (publicPlaceVal != publicPlaceData && publicPlaceData != "") {
				publicPlaceVal = publicPlaceData;
				// alert('公共机构加载')
				Map.removeLayer('publicPlace');
				$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type='
						+ publicPlaceVal + '', function(data) {
					Map.addJSON1([ {
						'type' : 'publicPlace',
						'data' : data,
						'isShow' : publicPlaceFlag
					} ])
				})
			} else if (publicPlaceVal != publicPlaceData
					&& publicPlaceData == "") {

				publicPlaceVal = publicPlaceData;
				Map.removeLayer('publicPlace');
				Map.clearOverlays()
				// alert('公共机构清除')
			} else {

				// alert('公共机构不变')
			}

		} else {

			Map.clearOverlays();
			// alert('清空图层')
			/*
			 * Map.removeLayer('communitys'); Map.removeLayer('streets');
			 * Map.removeLayer('grids'); Map.removeLayer('builds');
			 */
			Map.removeLayer('events');
			Map.removeLayer('parts');
			Map.removeLayer('lands');// 土地
			Map.removeLayer('videos');
			Map.removeLayer('schoolPlace');
			Map.removeLayer('keyPlace');
			Map.removeLayer('keyPerson');
			Map.removeLayer('rentingPerson');
			Map.removeLayer('publicPlace');
			Map.layersIsShow('heatMap', false);
			Map.layersIsShow('PopLocation', false);
			Map.removeLayer('ElectronicFence');
			Map.removeLayer('trackReplay');
			Map.removeLayer('PopLocation');
			Map.removeLayer('heatMap');

			$.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap', function(
					data) {
				var len = data.length;
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						var id = data[i].type
						Map.removeLayer('' + id + '');
					}
				}
			})
			clearInterval(PopLocationTime);
			PopulationDensityVal = 0
			communityGridVal = 0;
			eventVal = 0;
			buildtVal = 0;
			partsVal = '';
			landsVal = 0;
			videoVal = 0;
			PopulationDensityVal = 0;
			schoolPlaceVal = 0;
			keyPlaceVal = 0;
			keyPersonVal = "";
			keyPersonHandleVal = 0
			rentingPersonVal = 0;
			publicPlaceVal = "";
			popLocationVal = 0;
		}

	}

	getCheckedNodesLeft();
	function getCheckedNodesLeft() {
		var checked = "";
		var checkedNodes = zTreeObjLeft.getCheckedNodes(true);
		if (checkedNodes.length != 0) {
			var communityGridData = 0;
			var streetData = 0;
			var communityData = 0;
			var gridData = 0;
			var buildData = 0;

			for (var i = 0; i < checkedNodes.length; i++) {
				if (!checkedNodes[i].isParent) {
					checked += checkedNodes[i].id + ",";
					if (checkedNodes[i].pId == 'communityGrid') { // 街道社区网格
						communityGridData += parseInt(checkedNodes[i].type);
						if (checkedNodes[i].id == 'build') { // 楼栋
							buildData += parseInt(checkedNodes[i].typeVal)
						}
					}

				}
			}
			if (communityGridVal != communityGridData && communityGridData != 0) {
				communityGridVal = communityGridData;
				// alert('社区网格图层加载' + communityGridVal)
				Map.removeLayer('streets');
				Map.removeLayer('communitys');
				Map.removeLayer('grids');
				if (communityGridVal == '1') {

					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						Map.addJSON1([ {
							'type' : 'communitys',
							'data' : data,
							'isShow' : communityFlag
						} ])

					})
					console.log()
				} else if (communityGridVal == '2') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						Map.addJSON1([ {
							'type' : 'grids',
							'data' : data,
							'isShow' : gridFlag
						} ])

					})
				} else if (communityGridVal == '4') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						console.log(data)
						Map.addJSON1([ {
							'type' : 'streets',
							'data' : data,
							'isShow' : streetFlag
						} ])

					})
				} else if (communityGridVal == '3') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
							function(data) {
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
				} else if (communityGridVal == '5') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
							function(data) {
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				} else if (communityGridVal == '6') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				} else if (communityGridVal == '7') {

					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1 ',
							function(data) {

								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				}

			} else if (communityGridVal != communityGridData
					&& communityGridData == 0) {
				communityGridVal = communityGridData;
				// alert('社区网格图层清除')
				Map.removeLayer('streets');
				Map.removeLayer('communitys');
				Map.removeLayer('grids');
			} else {
				// alert('社区网格图层不变')
			}
			if (buildVal != buildData && buildData != 0) {
				buildVal = buildData;
				// alert('案事件图层加载')
				Map.removeLayer('builds');

				$.getJSON('' + ctx + '/sys/map/buildmanageMap', function(data) {
					Map.addJSON1([ {
						'type' : 'builds',
						'data' : data,
						'isShow' : buildFlag,
					} ])
				})

			} else if (buildVal != buildData && buildData == 0) {
				buildVal = buildData;
				// alert('案事件图层清除')
				Map.removeLayer('builds');
			} else {
				// alert('案事件图层不变')
			}
		} else {

			Map.clearOverlays();
			// alert('清空图层')
			Map.removeLayer('communitys');
			Map.removeLayer('streets');
			Map.removeLayer('grids');
			Map.removeLayer('builds');
			$.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap', function(
					data) {
				var len = data.length;
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						var id = data[i].type
						Map.removeLayer('' + id + '');
					}
				}
			})
			clearInterval(PopLocationTime);
			communityGridVal = 0;
			buildtVal = 0;
		}

	}

	// 监听地图层级变化

	var map = Map.map;// change:resolution
	map.getView().on('change:resolution', checkZoom);// checkZoom为调用的函数
	function checkZoom() {
		// console.log(map.getView().getZoom());
		var zoom = map.getView().getZoom();
		if (map.getView().getZoom() <= 14) {
			Map.layersIsShow('communitys', false);
			Map.layersIsShow('streets', true);
			Map.layersIsShow('grids', false);
			Map.layersIsShow('builds', false);
			Map.layersIsShow('schoolPlace', false);
			Map.layersIsShow('keyPlace', false);
			Map.layersIsShow('events', false);
			Map.layersIsShow('keyPerson', false);
			Map.layersIsShow('rentingPerson', false);
			Map.layersIsShow('lands', false);
			Map.layersIsShow('videos', false);
			// Map.layersIsShow('heatMap', true);
			// Map.layersIsShow('PopLocation',false);
			Map.layersIsShow('parts', false);
			Map.layersIsShow('publicPlace', false);
			Map.layersIsShow('jingwushi', false);
			Map.layersIsShow('gongzuozhan', false);
			communityFlag = false;
			streetFlag = true;
			gridFlag = false;
			buildFlag = false;
			vccmorgFlag = false;
			eventFlag = false;
			partsFlag = false;
			landsFlag = false;
			videoFlag = false;
			PopulationDensityFlg = false;
			schoolPlaceFlag = false;
			keyPlaceFlag = false;
			keyPersonFlag = false;
			rentingPersonFlag = false;
			publicPlaceFlag = false;

			// Map.clearOverlays();

		} else if (map.getView().getZoom() <= 16
				&& map.getView().getZoom() > 14) {
			Map.layersIsShow('communitys', true);
			Map.layersIsShow('streets', false);
			Map.layersIsShow('grids', false);
			Map.layersIsShow('builds', false);
			Map.layersIsShow('schoolPlace', false);
			Map.layersIsShow('keyPlace', false);
			Map.layersIsShow('events', true);
			Map.layersIsShow('keyPerson', false);
			Map.layersIsShow('rentingPerson', false);
			Map.layersIsShow('lands', false);
			Map.layersIsShow('videos', false);
			// Map.layersIsShow('heatMap', true);
			// Map.layersIsShow('PopLocation',true);
			Map.layersIsShow('parts', false);
			Map.layersIsShow('publicPlace', false);
			Map.layersIsShow('jingwushi', false);
			Map.layersIsShow('gongzuozhan', false);
			$.each(idArrjingwushi2, function(index, val) {
				Pubmap.removeOverlay(Map['' + val + 'Overlay'])
			});
			communityFlag = true;
			streetFlag = false;
			gridFlag = false;
			buildFlag = false;
			vccmorgFlag = false;
			eventFlag = true;
			partsFlag = false;
			landsFlag = false;
			videoFlag = false;
			PopulationDensityFlg = true;
			schoolPlaceFlag = false;
			keyPlaceFlag = false;
			keyPersonFlag = false;
			rentingPersonFlag = false;
			publicPlaceFlag = false;

			// Map.clearOverlays();

		} else if (map.getView().getZoom() > 16
				&& map.getView().getZoom() <= 17) {
			Map.layersIsShow('communitys', false);
			Map.layersIsShow('streets', false);
			Map.layersIsShow('grids', true);
			Map.layersIsShow('builds', false);
			Map.layersIsShow('schoolPlace', false);
			Map.layersIsShow('keyPlace', false);
			Map.layersIsShow('events', true);
			Map.layersIsShow('keyPerson', true);
			Map.layersIsShow('rentingPerson', true);
			Map.layersIsShow('lands', true);
			Map.layersIsShow('videos', true);
			// Map.layersIsShow('heatMap', true);
			// Map.layersIsShow('PopLocation',true);
			Map.layersIsShow('parts', false);
			Map.layersIsShow('publicPlace', false);
			Map.layersIsShow('jingwushi', true);
			Map.layersIsShow('gongzuozhan', true);
			communityFlag = false;
			streetFlag = false;
			gridFlag = true;
			buildFlag = false;
			vccmorgFlag = true;
			eventFlag = true;
			partsFlag = false;
			landsFlag = true;
			videoFlag = true;
			PopulationDensityFlg = true;
			schoolPlaceFlag = true;
			keyPlaceFlag = true;
			keyPersonFlag = false;
			rentingPersonFlag = true;
			publicPlaceFlag = false;
			// Map.clearOverlays();

		} else if (map.getView().getZoom() > 17) {
			Map.layersIsShow('communitys', false);
			Map.layersIsShow('streets', false);
			Map.layersIsShow('grids', false);
			Map.layersIsShow('builds', true);
			Map.layersIsShow('schoolPlace', true);
			Map.layersIsShow('keyPlace', true);
			Map.layersIsShow('events', true);
			Map.layersIsShow('keyPerson', true);
			Map.layersIsShow('rentingPerson', true);
			Map.layersIsShow('lands', true);
			Map.layersIsShow('videos', true);
			Map.layersIsShow('parts', true);
			Map.layersIsShow('publicPlace', true);
			Map.layersIsShow('PopLocation', true);
			Map.layersIsShow('jingwushi', true);
			Map.layersIsShow('gongzuozhan', true);
			communityFlag = false;
			streetFlag = false;
			gridFlag = false;
			buildFlag = true;
			vccmorgFlag = true;
			eventFlag = true;
			partsFlag = true;
			landsFlag = true;
			videoFlag = true;
			schoolPlaceFlag = true;
			keyPlaceFlag = true;
			keyPersonFlag = true;
			rentingPersonFlag = true;
			publicPlaceFlag = true;
			// Map.clearOverlays();
		}
	}
	// 海康视频
	$("#popup")
			.on(
					"click",
					".click",
					function() {
						var id = $(this).attr('videoId');
						// 捕获页
						var html = "";
						html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
						html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
						html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">视频监控</div>'
						html += '</div>'
						html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
								+ ctxStatic
								+ '/common/index/images/showbg.png);background-size: 100% 100%;">'
						html += '	<iframe  name="mainFrame" src="'
								+ ctx
								+ '/ccmsys/ccmDevice/getDeviceMap?id='
								+ id
								+ '" style="overflow: visible;" scrolling="yes" frameborder="no" width="570" height="360" allowfullscreen="true" allowtransparency="true"></iframe>'
						html += '</div>'
						html += '</div>'
						layer.open({
							type : 1,
							shade : false,
							title : false, // 不显示标题
							area : [ "600px", "400px" ],
							move : '.layer-common-header',
							resize : false,
							fixed : false,
							id : "videoLayer",
							content : html,
							cancel : function() {
								// 关闭事件
								// layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构',
								// {time: 5000, icon:6});
							}
						});

					})
}
// 业务-异常告警
function selectAll(id, name) {
	if ($("#" + id).is(":checked")) {// 判断一个checkbox多选框是否选中

		$("input:checkbox[name=" + name + "]").prop("checked", "checked");

		var allValue = queryCheckedValue(name);
		console.log(allValue);

	} else {

		$("input:checkbox[name=" + name + "]").prop("checked", "");
		var str = noCheckedValue(name);
		console.log(str);

	}

}

// 获取所有选中checkbox的值

function queryCheckedValue(name) {

	var str = "";

	$("input:checkbox[name=" + name + "]:checked").each(function(i) {

		var val = $(this).val();
		str = str + val + "-";

	});

	return str;

}

// 所有的name为‘selected’的checkbox的值
function noCheckedValue(name) {
	var str = "";
	$("input:checkbox[name=" + name + "]").each(function(i) {

		var val = $(this).val();
		str = str + "-" + val;
	});
	return str;
}

// 判断所有的子checkbox全部选中时，总checkbox选中，否则，反之；
function oneToAll(allId, name) {
	var allChecked = 0;// 所有选中checkbox的数量

	var all = 0;// 所有checkbox的数量

	$("input:checkbox[name=" + name + "]").each(function(i) {
		all++;
		if ($(this).is(":checked")) {
			allChecked++;
		}

	});

	if (allChecked == all) {// 相等时，则所有的checkbox都选中了，否则，反之；

		$("#" + allId).prop("checked", true);

	} else {

		$("#" + allId).prop("checked", false);

	}

}
