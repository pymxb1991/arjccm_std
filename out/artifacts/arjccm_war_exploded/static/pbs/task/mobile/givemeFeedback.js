/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {

	var $taskform =$("#taskform");
	// 点击 接收 按钮
	$('#applySubmit').click(function() {
		
		var sContent =$taskform.find("#sContent").val();
		var sOptstatus =$taskform.find("#sOptstatus").val();
		// 提交申请
		$.post(ctx + "/task/pbsTaskrec/applyFinish", {
			"sTaskid" : $(this).attr("taskid"),
			"sContent":sContent,
			"sOptstatus":sOptstatus
		}, function(data) {
			if (data == "success") {
				mui.toast('接收成功', {
					duration : 'short',
					type : 'div'
				});
				window.location.href = ctx + "/task/pbsTaskrec/givemeList";
			} else {
				mui.toast('接收失败', {
					duration : 'short',
					type : 'div'
				});
			}
		});
	});

	// 判断为空
	function isEmpty(obj) {
		if (typeof obj == "undefined" || obj == null || obj == "") {
			return true;
		} else {
			return false;
		}
	}

});