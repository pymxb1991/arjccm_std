$(document).ready(function() {
	// 表单对象
	var $form = $("#commentForm");
	// 点击进行提交
	$form.find("#fromsave").click(function() {
		// 显示 form 表单信息
		var sContent = $form.find("#sContent").val();
		var courseid = $form.attr("courseid");
		$("#sContent").val("");
		// post请求
		$.post(ctx + "/course/pbsCourseoperate/commentSave", {
			"sContent" : sContent,
			"sParentid" : courseid,
			"sType" : "1",
			"sOpflag" : "1",
			"sOptype":"3",
			"sOpresult":"1"
				
		}, function(data) {
			// 成功
			if (data == "sucess") {
				mui.toast('发布成功', {
					duration : 'short',
					type : 'div'
				});
			} else {
				mui.toast('请填写评价内容', {
					duration : 'short',
					type : 'div'
				});
			}
		})

	});

});
