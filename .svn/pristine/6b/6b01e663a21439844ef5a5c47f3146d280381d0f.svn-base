var ajax_option = {},validateForm = null;
$(function(){
		//关闭弹框事件
		$('#btnCancel').click(function() {
			parent.layer.close(parent.drawForm);
		})
		//默认最大化
		parent.layer.full(parent.drawForm);
		//隐藏掉最小化按钮
		parent.layer.style(parent.drawForm, {
			maxmin: false
		});
		init();
		initData();
})
var subInfo = {},newSubInfo = {};
function initData(){
	var areaPoint=getUrlParam('areaPoint');
	var areaMap=getUrlParam('areaMap');
	console.info("areaPoint",areaPoint);
	console.info("areaMap",areaMap);
	subInfo.areaPoint = areaPoint;
	if(areaPoint!=areaMap) {
		subInfo.areaMap = areaMap;
	}
	Map.markInfo("","drawForm",[subInfo]);//编辑地图范围
	console.info("[subInfo]",[subInfo]);
	if(areaPoint==areaMap){
		Map.addGraphical("Point");
	}else{
		Map.addGraphical("Polygon");
	}
	// Map.markInfoType = "drawForm";
};
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r != null) return unescape(r[2]); return null; //返回参数值
}
