$(document).ready(function() {
	$("#btnSubmit").on("click" ,function(){
		var begin = new Date($("[name='beginHandleDeadline']").val());
		var end = new Date($("[name='endHandleDeadline']").val());
		if(begin.getTime() > end.getTime()){
			messageAlert("开始时间大于结束时间！", "error");
			return false;
		}
		$("#searchForm").submit();
	})
});
function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
};
function messageAlert(content, type) {
	$("#messageBox").removeClass();
	$("#messageBox").addClass("alert alert-" + type + " hide");
	top.$.jBox.tip(content, type, {
		persistent : true,
		opacity : 0
	});
	$("#messageBox").show();
}
