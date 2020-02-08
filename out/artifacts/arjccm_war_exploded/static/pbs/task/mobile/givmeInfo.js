/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
	
	// 点击 接收 按钮
	$('#acceptSubmit').click(function() {
		// 提交申请
		$.post(ctx + "/task/pbsTaskrec/receiveTask", {
			"id": $(this).attr("taskid")
		}, function(data) {
			if (data == "success") {
				mui.toast('接收成功', {
					duration : 'short',
					type : 'div'
				});
				// console.log(ctx + "/flow/deal/dealtList");
				 window.location.href = ctx + "/sys/Mobile/pageTurn?pageTo=/Nav-personal/arrange/index";
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