$(document).ready(function() {
	// checkPoint
	$(".checkPoint").load(ctx + "/patrol/ccmPatrolPlan/mapcheck");
    // 当前类型修改 后 变更 具体的 规则内容为空
	$("#timeType").change(function() {
		$("#timeRuleId,#timeRuleName").val("");
	});
		
	
	// $("#name").focus();
	$("#inputForm").validate({
		submitHandler : function(form) {
			alert()
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer : "#messageBox",
		errorPlacement : function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox") || element.is(":radio")
			|| element.parent().is(".input-append")) {
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
	
});