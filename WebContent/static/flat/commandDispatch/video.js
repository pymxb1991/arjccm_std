/*视频播放-摄像机*/
$(function(){
	$("#popup").on("click",".videoPlay",function() {
		var id = $(this).attr('videoId');
		videoDialog(id);
	})
	$("#popup").on("click",".vlcPlay",function() {
		var id = $(this).attr('vlcId');
		vlcDialog(id);
	})
})
function videoDialog(id){
	// 捕获页
	var html = "";
	html += '<div class="layer-common">'
	html += '<div class="layer-show  layer-common-center" style="padding: 5px 0px 5px 0px; width: 99%;height: 100%;  border: 1px solid #10559a;">'
	html += '<iframe name="mainFrame" src="'+ ctx+'/ccmsys/ccmDevice/getDeviceMap?id='+ id+'" style="overflow: visible;" scrolling="yes" frameborder="no" width="570" height="360" allowfullscreen="true" allowtransparency="true"></iframe>'
	html += '</div>'
	html += '</div>'
	layer.open({
		type : 1,
		shade : false,
		title : '视频监控', // 不显示标题
		area : [ "600px", "425px" ],
		id : "videoDialog",
		content : html,
		cancel : function() {
			// 关闭事件
		}
	});
}
/*视频播放-警车警力视频流播放--vlc*/
function vlcDialog(userId){
//	$.post(ctx+'/alarm/bphAlarmInfo/requestAppVideo',{'userId':userId},function(data){
//		if (isBlank(data)) {
//			layer.msg('请求失败');
//			return;
//		}
//		if (isBlank(data.ret)) {
//			layer.msg('请求失败');
//			return;
//		}
//		if (data.ret == 1) {
//			layer.msg('被拒绝');
//			return;
//		}
//		if (data.ret == 2) {
//			layer.msg('未应答');
//			return;
//		}
//		var html = "";
//		html += '<div class="layer-common" style="width: 100%;height: 100%;">'
//		html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;">'
//	    html+='<div id="ckVideo"></div>';
//		html += '</div>'
//		html += '</div>'
		var html = "";
		html += '<object classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" codebase="./axvlc.cab" id="vlc" width="1140" height="640" events="True">'
		html += '<param name="mrl" value="rtsp://192.168.11.204:8554/200692" />'
		html += '<param name="volume" value="50" />'
		html += '<param name="autoplay" value="true" />'
		html += '<param name="toolbar" value="true" />'
		html += '<param name="loop" value="true" />'
		html += '<param name="fullscreen" value="false" />'
		html += '</object>'
		layer.open({
			type : 1,
			shade : false,
			title : '视频监控', // 不显示标题
			area : [ "600px", "425px" ],
			id : "vlcDialog",
			content : html,
			cancel : function() {
				// 关闭事件
			}
		});
		//rtmp测试地址：rtmp://live.hkstv.hk.lxdns.com/live/hks2
		/*视频直播*/
//	    var flashvars = {
//	        f: 'rtsp://192.168.11.238:8554/740900',
//	        c: 0,
//	        b: 1,
//	        i: ctxStatic+'/images/police1.png'
//	    };
//	    var params = { bgcolor: '#FFF', allowFullScreen: true, allowScriptAccess: 'always', wmode: 'transparent' };
//	    CKobject.embedSWF(ctxStatic+'/ckplayer/ckplayer.swf', 'ckVideo', 'ckplayer_a1', '570', '360', flashvars, params);
//	    /*CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
//	             下面是调用html5播放器用到的*/
//	    var video = ['rtsp://192.168.11.238:8554/740900'];
//	    var support = ['iPad', 'iPhone', 'ios', 'android+false', 'msie10+false'];
//	    CKobject.embedHTML5('ckVideo', 'ckplayer_a1', 570, 360, video, flashvars, support);
//	})
}