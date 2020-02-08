/**
 * V 1.0 2018-4-20
 */
$(function() {

	// 加载 部门 列表信息
	$("#sPartment").load(ctx + "/sys/pbsDepartmentetc/namelist?type=2");
	
	$('#applySubmit').click(function() {
		var $form = $("#Applyform");
		var SResume = $form.find("#SResume").val();
		var SContent = $form.find("#SContent").val();
		var ActionType = $form.find("#ActionType").val();
		var sPartment = $form.find("#sPartment").val();
		// 如果为空
		if (isEmpty(SResume) || isEmpty(SContent)) {
			mui.toast('填写不完整', {
				duration : 'short',
				type : 'div'
			});
			return;
		}
		// 提交申请
		$.post(ctx + "/apply/pbsApplyrec/apply", {
			"SResume" : SResume,
			"SContent" : SContent,
			"sPartment" : sPartment,
			"flowtype" : ActionType
		}, function(data) {
			if (data == "success") {
				mui.toast('提交成功', {
					duration : 'short',
					type : 'div'
				});
				window.location.reload();
			} else {
				mui.toast(data, {
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