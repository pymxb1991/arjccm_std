/**
 * 获取当前日期
 */

var year;//年
var month;//月
var day;//日
var hh;//时
var mm;//分
var ss;//秒
var clock;
(function DateFun() {
	var now = new Date();

	year = now.getFullYear(); //年  
	month = now.getMonth() + 1; //月  
	day = now.getDate(); //日  

	hh = now.getHours(); //时  
	mm = now.getMinutes(); //分  
	ss = now.getSeconds(); //秒  
	
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
})()