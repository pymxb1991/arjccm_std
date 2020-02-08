//var center = [117.0599086744,39.0843548271];
var center = [126.639241,45.753481];
//实时警情列表自动刷新时间-ms
var autoRefreshTime=10000;
//var timeoutAlarmTime = 300000;//超时警情定时任务间隔时间ms
var timeoutAlarmTime = 20000;//超时警情定时任务间隔时间ms
//实时警情-警力实时刷新
var policeTime=30000;
var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var ctx="http://"+window.location.host+projectName+"/a";
var ctxStatic="http://"+window.location.host+projectName+"/static";
var filePath = "http://"+window.location.host;
var layer='';
var layui;
if(layui){
	layui.use('layer', function(){
		layer = layui.layer;
	});
}
//详情弹框--刷新父页面
function LayerDialogWithReload(src, title, height, width){
	parent.layer.open({
	    type: 2,
	    title: title,
	    area: [height, width],
	    fixed: true, //固定
	    maxmin: true,
	    content: src,
	    end: function () {
		    // window.location.reload();
			window.location.href = window.location.href;
        }
	});
}


//案事件定位
function LocationOpen(id){
	var context = $(".context").attr("content");
	$.get(context+"/sys/map/getIncidentMap?id="+id,function(data){
		if(data==""){
			top.$.jBox.tip("暂无位置信息");
		}else{
			windowOpen(context+"/sys/map/incidentMap?id="+id,"位置信息",1000,700);
		}
	})
}
//获取当前时间返回年月日
function today(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    var hh=today.getHours();
    var mm=today.getMinutes();
    var ss=today.getSeconds();
    m= m<10?"0"+m:m;
    d= d<10?"0"+d:d;
    hh = hh < 10 ? "0" + hh:hh;
    mm = mm < 10 ? "0" +  mm:mm;
    ss = ss < 10 ? "0" + ss:ss;
    return h+"-"+m+"-"+d;
}
//获取当前时间返回年月日时分秒
function todayHMS(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    var hh=today.getHours();
    var mm=today.getMinutes();
    var ss=today.getSeconds();
    m= m<10?"0"+m:m;
    d= d<10?"0"+d:d;
    hh = hh < 10 ? "0" + hh:hh;
    mm = mm < 10 ? "0" +  mm:mm;
    ss = ss < 10 ? "0" + ss:ss;
    return h+"-"+m+"-"+d+" "+hh+':'+mm+':'+ss;
}
//时间转化
function getDay(time){
    var time=new Date(time);
    var h=time.getFullYear();
    var m=time.getMonth()+1;
    var d=time.getDate();
    var hh=time.getHours();
    var mm=time.getMinutes();
    var ss=time.getSeconds();
    m= m<10?"0"+m:m;
    d= d<10?"0"+d:d;
    hh = hh < 10 ? "0" + hh:hh;
    mm = mm < 10 ? "0" +  mm:mm;
    ss = ss < 10 ? "0" + ss:ss;
    return h+"-"+m+"-"+d+" "+hh+":"+mm+":"+ss;
}

//获得本周周一 
function getWeekStartDate() {
	var now = new Date(); 
	var nowTime = now.getTime(); 
	var day = now.getDay();
	var oneDayTime = 24*60*60*1000; 
	var MondayTime = nowTime - (day-1)*oneDayTime; 
	return formatDate(MondayTime); 
} 
//获得本月01号 
function  GetThisMonthStartDate(){
	var now = new Date();
	//获取当前年
	var year=now.getFullYear();
	//获取当前月
	var month=now.getMonth()+1;
	if(month < 10){ 
		month = "0" + month; 
	} 
	return (year+"-"+month + "-" + "01"); 
};
//获得本年01-01
function getYear(){
    var year=new Date();
    var y=year.getFullYear();
    return y+"-01-01";
}
//格式化日期：yyyy-MM-dd 
function formatDate(date) { 
	var date =new Date(date); 	
	var myyear = date.getFullYear(); 
	var mymonth = date.getMonth()+1; 
	var myweekday = date.getDate(); 

	if(mymonth < 10){ 
		mymonth = "0" + mymonth; 
	} 
	if(myweekday < 10){ 
		myweekday = "0" + myweekday; 
	} 
	return (myyear+"-"+mymonth + "-" + myweekday); 
} 

//获取当前时间往前推param天返回年月日
//例:往前推30天param=30
function timeBackstepping(param){
	var date1 = new Date();
	var date2 = new Date(date1);
	date2.setDate(date1.getDate() - param);
    var h=date2.getFullYear();
    var m=date2.getMonth()+1;
    var d=date2.getDate();
    m= m<10?"0"+m:m;
    d= d<10?"0"+d:d;
    return h+"-"+m+"-"+d+" ";
}

//计算两个日期天数差的函数
function DateDiffFun(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式
	var time1 = Date.parse(new Date(sDate1));
    var time2 = Date.parse(new Date(sDate2));
    var nDays = Math.abs(parseInt((time2 - time1) / 1000 / 3600 / 24));
    return nDays;
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//点击事件弹出部门和用户的dtree复选树
function dispatchPolice(_this) {
	var pointAlarmId = $(_this).attr("data-id");
	// dtree实际开发中将路径替换为自己项目中的路径
	layui.extend({dtree: ctxStatic+'/layui_ext/dtree/dtree'}).use(['layer', 'dtree'], function() {
		var dtree = layui.dtree,
		layer = layui.layer,
		$ = layui.$;
		layer.open({
			type: 1,
			//type:0 也行
			title: "请选择工作人员",
			area: ["320px", "65%"],
			shade: 0.01,
			content: '<ul id="openTree1" class="dtree" data-id="0"></ul>',//data-id是指最高级节点的parentId
			btn: ['确认', '关闭'],
			success: function(layero, index) {
				var DTree = dtree.render({
					obj: $(layero).find("#openTree1"), //如果直接用elem加载不出来，则可以使用这个方式加载jquery的DOM
					url: ctx + '/tree/officeAndUserTreeData',
					checkbar: true,
					checkbarType: "no-all",
					method: "get",
					initLevel: 2,
					menubar: true,
					dataStyle: "layuiStyle", //使用layui风格的数据格式
					dataFormat: "list", //配置data的风格为list
					response: {
						message: "msg",
						statusCode: 0
					}, //修改response中返回数据的定义
				});
			},
			yes: function(index, layero) {
				var params = dtree.getCheckbarNodesParam("openTree1");
				if(params.length <= 0){
					layer.msg('请选择人员');
			        return;
				}
				var userIds = '';
				for(var i = 0; i < params.length; i++) {
					var basicData = params[i].basicData;
					if(basicData == '"user"') {
						userIds += params[i].nodeId + ',';
					}
				}
				userIds = userIds.substring(0, userIds.length - 1);
				popUpAlarmBox(userIds, pointAlarmId, index);
			},
			btn2: function(index, layero) {
				layer.close(index);
			}
		});
	});
}

function isBlank(str){
	if (str == null || str === undefined || str == '') {
		return true;
	}
	return false;
}

function isNotBlank(str){
	if (str != null && str !== undefined && str != '') {
		return true;
	}
	return false;
}