function stateChange(val){
	if(val=='0'){
		return '未处理';
	}else if(val=='1'){
		return '已派发';
	}else if(val=='2'){
		return '处理中';
	}else if(val=='3'){
		return '已完成';
	}else{
		return '';
	}
}
function sexChange(val){
	if(val=='0'){
		return '男';
	}else{
		return '女';
	}
}function isImportantChange(val) {
	if (val == '1') {
		return '是';
	} else {
		return '否';
	}
}
function isImportantChangeVal(val) {
	if (val == '1') {
		return '重大';
	} else {
		return '普通';
	}
}
function alarmForm(val){
	if(val=='01'){
		return '110';
	}else if(val=='02'){
		return '119';
	}else if(val=='03'){
		return '120';
	}else{
		return '';
	}
}
//初始化audio
function audioInit(){
	$('audio').audioPlayer();
}
//获取案件类型
var ArjAlarmType={};
function getAlarmType(){
	$.ajax({
		type : "post",
		url : ctx+'/sys/dict/listData?type=bph_alarm_info_typecode',
		async : false,
		success : function(data){
			var len=data.length;
			if(len>0){
				for(var i=0;i<len;i++){
					var key=data[i].value;
	                var val=data[i].label;
	                ArjAlarmType[key]=val;
				}
				ArjAlarmType['']='';
			}
		}
	});
}