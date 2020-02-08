var ParentAlarmsetInterval;
var CacleAlarmDate='';//查询条件
var nowAlarmDate='';//当前时间
$(function(){
	//存储新警情Id
	if (typeof (Storage) !== "undefined") {
		if (!sessionStorage.newAlarmDateId) {
			sessionStorage.newAlarmDateId = '';
		}
	}
	//当前时间
	nowAlarmDate=todayHMS();
	//ParentAlarmsetInterval=setInterval('AlarmAlert()',autoRefreshTime);
})
function AlarmAlert(){
	CacleAlarmDate = nowAlarmDate;
	nowAlarmDate=todayHMS();
	if($.cookie('querySon')===undefined){
	    $.cookie('querySon',false);
	}
	//是否查询下级
	var incSubsetFlag=$.cookie('querySon');
/*	 if(typeof $("#mainFrame")[0].contentWindow.realTimeAlarmAlert === "function") {
		    $("#mainFrame")[0].contentWindow.realTimeAlarmAlert(CacleAlarmDate);
		}*/
    if(typeof realTimeAlarmAlert === "function") {
	    realTimeAlarmAlert(CacleAlarmDate);
	}else{
		$.post(ctx + "/alarm/bphAlarmInfo/queryHisAlarmSituation",{'state' : '','createDate' : CacleAlarmDate,'incSubset':incSubsetFlag},function(data) {
			var obj = JSON.parse(data);
	     	var len=obj.length;
	        if(len>0){
	        	for(var i=0;i<len;i++){
	     			var id=obj[i].id;
	     			
	     		    if(sessionStorage.newAlarmDateId.split(',').indexOf(id)== -1){
	                	sessionStorage.newAlarmDateId += id + ','
	                }else{
	                	return 
	                }
	         	    //sessionStorage.newAlarmDateId+=id+','
	     		}
	        	var html='';
	 			html+='<div class="alert alert-warning">';
	 			html+='<a href="#" class="close" data-dismiss="alert">';
	 			html+='&times;';	
	 			html+='</a>';
	 			html+='<strong>新警情！</strong>共'+len+'条！';
	 			html+='</div>';
	 		    $('#alarmAlert').html(html); 
	 		    $('#audioAlarm').trigger('play');
	        }
		});
	}
}