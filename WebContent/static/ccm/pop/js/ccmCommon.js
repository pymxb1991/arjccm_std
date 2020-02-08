$(function(){

})
function LocationOpen (id){
	var context = $(".context").attr("content");
	$.get(context+"/sys/map/getBuildKeyManMap?peopleId="+id,function(data){
		if(data==""){
			top.$.jBox.tip("暂无位置信息")
		}else{
			windowOpen(context+"/sys/map/buildKeyManMap?peopleId="+id,"位置信息",1000,700)
		}
	})
}