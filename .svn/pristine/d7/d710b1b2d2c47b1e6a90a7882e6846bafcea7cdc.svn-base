var uuid = "";
var timer;
var mqFlag = true;
function initRabbitMQ(){
	alarmVerify();
	RaMq();
}
function initActiveMQ() {
	var amq = new org.activemq.AmqClass();

	var amqPath = window.location.href.substring(0, window.location.href
			.indexOf("arjccm"))
			+ "arjccm/amq/";
	amq.init({
		uri : amqPath,
		timeout : 5,
		clientId : "topic://ccm-4232342-activemq-topic-id",
		connectStatusHandler : connectStatusHandler
	});
	// amq.addListener('chat', "topic://ccm-4232342-activemq-topic-id", message,
	// 'text');
	amq.addListener('chat', "topic://ccm-4232342-activemq-topic-id", message,
			"amq-msg-type=>'text'");
}

function connectStatusHandler(connected) {
	if (!connected) {
		if (!mqFlag) {
			return;
		}
		mqFlag = false;
		setTimeout(function() {
			// initActiveMQ();
			window.onload();
			mqFlag = true;
		}, 70000);
	}
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
		url : webServicePath + "rest/event/subscribeAmq",
		success : function(data) {
			if (data) {
				uuid = data.result;// 获取到uuid
			}
		}
	});
}

// 中间件返回消息
/*var message = function(msg) {
	// 解析对象字符串
	var msgObj = (msg["data"] + "").replace(/"(\w+)"(\s*:\s*)/g, "$1$2");
	var obj = eval('(' + msgObj + ')');
	//
	// layer.restore(layerIndex1);
	top.$.jBox.tip('有新事件上报，请您查收！');
	if($('#layui-layer1 .layui-layer-ico').hasClass("layui-layer-maxmin")){
		$('#layui-layer1 .layui-layer-ico').click()
	}
	$(".def").hide();
	var clock="";
	var clock1="";
	var year,month,day,hh,mm,ss;
	var eventTime = new Date(obj["happenDate"].time);
	year = eventTime.getFullYear(); //年  
	month = eventTime.getMonth() + 1; //月  
	day = eventTime.getDate(); //日  
	hh = eventTime.getHours(); //时  
	mm = eventTime.getMinutes(); //分  
	ss = eventTime.getSeconds(); //秒  
	
	if (month < 10){
		month = "0"+month;
	}
	if (day < 10){
		day="0"+day;
	}
	if (hh < 10){
		hh = "0"+hh;
	}
	if (mm < 10)
		mm = '0'+mm;

	if (ss < 10){
		ss = '0'+ss;
	}
	clock = year + "-"+month+"-"+day+" "+hh+":"+mm+":"+ss;
	clock1=year + "年"+month+"月"+day+"日 "+hh+"时"+mm+"分"+ss+"秒";
	var name=decodeURIComponent(obj["caseName"]);
	// 生成新的文字插入对话框中
	var htmlM = '<tr><td><a title="'+clock1+name+'" onclick="re(\'' + obj["id"]
			+ '\')" class="ccmEventLayer' + obj["id"] + ' masked active"  attrid="'
			+ obj["id"] + '" attpoint="' + obj["areaPoint"] + '" attname="'
			+ obj["caseName"] + '" > ' + clock+ '：' + name + '</a></td></tr>';
	// 插入
	$("#eventTbody tr:first-child").before(htmlM);
	
	var  textNAme=clock1+name
	speak(textNAme)
}
*/
function RaMq(){
	// 初始化 ws 对象
	var ws = new WebSocket('ws://'+window.location.hostname +':15674/ws');
	// 获得Stomp client对象
	var client = Stomp.over(ws);
	client.heartbeat.outgoing = 10000;//mq心跳
	client.heartbeat.incoming = 0;
	// 定义连接成功回调函数
	var on_connect = function(x) {
		orderRabbitMQInfo();
	    client.subscribe("/queue/"+uuid+"", function(msg) {
	    	// 解析对象字符串
	    	var msgObj = (msg.body).replace(/"(\w+)"(\s*:\s*)/g, "$1$2");
	    	var obj = eval('(' + msgObj + ')');
	    	//
	    	// layer.restore(layerIndex1);
	    	// top.$.jBox.tip('有新事件上报，请您查收！');
			top.$.jBox.tip('有新消息上报，请您查收！');
	    	if($('#layui-layer1 .layui-layer-ico').hasClass("layui-layer-maxmin")){
	    		$('#layui-layer1 .layui-layer-ico').click()
	    	}
	    	$(".def").hide();
	    	if (obj["type"]=="01"){
				var name=decodeURIComponent(obj["caseName"]);
				var content=decodeURIComponent(obj["content"]);
				// 生成新的文字插入对话框中
				var htmlM = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickEvent(\'' + obj["objId"]
						+ '\')" class="ccmEventLayer' + obj["objId"] + ' masked active"  attrid="'
						+ obj["objId"] + '" attpoint="' + "areaPoint" + '" attname="'
						+ obj["caseName"] + '" > ' + content + '</a></td></tr>';
				var countAll = $("#countAll").text();
				$("#countAll").text(parseInt(countAll)+1);
				var count1 = $("#count1").text();
				$("#count1").text(parseInt(count1)+1);
				// 插入
				$("#eventTbody tr:first-child").before(htmlM);
			}else if(obj["type"]=="02"){
				var content=decodeURIComponent(obj["content"]);
				var htmlM = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickEventCaseDeal(\'' + obj["objId"]
					+ '\')" class="ccmEventLayer' + obj["objId"] + ' masked active" > ' + content + '</a></td></tr>';
				var countAll = $("#countAll").text();
				$("#countAll").text(parseInt(countAll)+1);
				var count2 = $("#count2").text();
				$("#count2").text(parseInt(count2)+1);
				// 插入
				$("#caseDealTbody tr:first-child").before(htmlM);
			}else if(obj["type"]=="03"){
				var content=decodeURIComponent(obj["content"]);
				var htmlM = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickNotify(\'' + obj["objId"]
					+ '\')" class="ccmEventLayer' + obj["objId"] + ' masked active" > ' + content + '</a></td></tr>';
				var countAll = $("#countAll").text();
				$("#countAll").text(parseInt(countAll)+1);
				var count3 = $("#count3").text();
				$("#count3").text(parseInt(count3)+1);
				// 插入
				$("#notifyTbody tr:first-child").before(htmlM);
			}else if(obj["type"]=="13"){
				var content=decodeURIComponent(obj["content"]);
				var htmlM = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickLeaveRequest(\'' + obj["objId"]
					+ '\')" class="ccmEventLayer' + obj["objId"] + ' masked active" > ' + content + '</a></td></tr>';
				var countAll = $("#countAll").text();
				$("#countAll").text(parseInt(countAll)+1);
				var count3 = $("#count3").text();
				$("#count3").text(parseInt(count3)+1);
				// 插入
				$("#notifyTbody tr:first-child").before(htmlM);
			}else if(obj["type"]=="23"){
				var content=decodeURIComponent(obj["content"]);
				var htmlM = '<tr><td style="text-align: left;"><a title="'+content+'" href="javascript:void(0);" class="ccmEventLayer' + obj["objId"] + ' masked active" > ' + content + '</a></td></tr>';
				var countAll = $("#countAll").text();
				$("#countAll").text(parseInt(countAll)+1);
				var count3 = $("#count3").text();
				$("#count3").text(parseInt(count3)+1);
				// 插入
				$("#notifyTbody tr:first-child").before(htmlM);
			}
			$.ajax({
				type: "get",
				url: ctx + "/message/ccmMessage/read",
				data: {
					id: obj["id"]
				},
				success: function (data) {

				}
			})
	    	// var clock="";
	    	// var clock1="";
	    	// var year,month,day,hh,mm,ss;
	    	// var eventTime = new Date(obj["createDate"].time);
	    	// year = eventTime.getFullYear(); //年
	    	// month = eventTime.getMonth() + 1; //月
	    	// day = eventTime.getDate(); //日
	    	// hh = eventTime.getHours(); //时
	    	// mm = eventTime.getMinutes(); //分
	    	// ss = eventTime.getSeconds(); //秒
	    	//
	    	// if (month < 10){
	    	// 	month = "0"+month;
	    	// }
	    	// if (day < 10){
	    	// 	day="0"+day;
	    	// }
	    	// if (hh < 10){
	    	// 	hh = "0"+hh;
	    	// }
	    	// if (mm < 10)
	    	// 	mm = '0'+mm;
			//
	    	// if (ss < 10){
	    	// 	ss = '0'+ss;
	    	// }
	    	// clock = year + "-"+month+"-"+day+" "+hh+":"+mm+":"+ss;
	    	// clock1=year + "年"+month+"月"+day+"日 "+hh+"时"+mm+"分"+ss+"秒";
	    	// var name=decodeURIComponent(obj["caseName"]);
	    	// // 生成新的文字插入对话框中
	    	// var htmlM = '<tr><td><a title="'+clock1+name+'" onclick="re(\'' + obj["id"]
	    	// 		+ '\')" class="ccmEventLayer' + obj["id"] + ' masked active"  attrid="'
	    	// 		+ obj["id"] + '" attpoint="' + obj["areaPoint"] + '" attname="'
	    	// 		+ obj["caseName"] + '" > ' + clock+ '：' + name + '</a></td></tr>';
	    	// 插入
	    	$("#allTbody tr:first-child").before(htmlM);
	    	//
	    	// var  textNAme=clock1+name
	    	// speak(textNAme)
			speak(content);
	    });
	};
	// 定义错误时回调函数
	var on_error =  function() {
	    if (!mqFlag) {
			return;
		}
		mqFlag = false;
		setTimeout(function() {
			initRabbitMQ();
			mqFlag = true;
		}, 70000);
	};
	// 连接RabbitMQ
	client.connect('arj', '123456', on_connect, on_error, '/');
}
//订阅设备
function orderRabbitMQInfo(){
	var userId=$('#userid').val();
	var json={'clientId':uuid,'userId':userId};
	var param = encodeURI(JSON.stringify(json));
	$.post(webServicePath+'rest/event/orderRabbitMQInfo',{'json':param},function(data){
		var data=JSON.parse(data);
		if(data.ret=='0'){
			// 开启心跳
			timer=window.setInterval('sendHeartBeat()',120000)
		}
	})
}
//心跳
function sendHeartBeat(){
	var userId=$('#userid').val();
	var json={'clientId':uuid,'userId':userId};
	var param = encodeURI(JSON.stringify(json));
	var dateTime=new Date()
	$.post(webServicePath+'rest/event/sendFenceHeartBeat?date='+dateTime+'',{'json':param},function(data){
		var data=JSON.parse(data);
		if(data.ret=='0'){
		}
	})
}
//取消订阅设备
function cancelOrderRabbitMQInfo(){
	$.post(webServicePath+'rest/event/cancelOrderRabbitMQInfo',{'clientId':uuid},function(data){
		var data=JSON.parse(data);
		if(data.ret=='0'){
		}
	})
}