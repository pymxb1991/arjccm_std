$(function() {

	// 表单对象
	var $form = $("#register");

	// 点击进行提交
	$form.find("#registerButton").click(function() {
		// 错误提示消失
		$("#messageError").hide();

		// 显示 form 表单信息
		var name = $form.find("#name").val();
		var password = $form.find("#password").val();
		var loginname = $form.find("#loginname").val();
		var email = $form.find("#email").val();
		var mobile = $form.find("#mobile").val();
		// post请求
		$.post(ctxFront + "/sys/Mobile/save", {
			"name" : name,
			"newPassword" : password,
			"loginName" : loginname,
			"email" : email,
			"mobile" : mobile,
			// 是否可以登录 后台 默认：不可以
			"loginFlag" : "1",
			// 
			"userType" : "3",
			// 默认 普通用户
			"roleIdList" : "6"
		}, function(data) {
			// 成功
			if (data == "sucess") {
				window.location = ctx+"/sys/Mobile/personInfo";
			} else {
				// 错误展示
				$("#messageError").show();
				$("#messageError").html(data)
			}
		})

	});
});