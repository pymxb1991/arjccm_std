$(document).ready(function() {
// 当前的页面离开的事件
	window.onbeforeunload = function(e) {
		// var e = window.event||e;
		// e.returnValue=("确定离开当前页面吗？");
		var iTime = Math.floor(videoMedia.currentTime)
		// 显示 form 表单信息
		var courseid = $("#commentForm").attr("courseid");
		// post请求
		$.post(ctx + "/course/pbsCourseoperate/updateOperate", {
			"iTime" : iTime,
			"sParentid" : courseid,
			"sType" : "1",
			"sOpflag" : "1",
			"sOptype" : "1",
			"sOpresult":"1"
		}, function(data) {
		})
	}
});
