$(document).ready(
		function() {
	var no1Type = $(".ccmccmPatrolPlanID").attr("attrType1");
	
	$("#planIds").load(ctx + "/patrol/ccmPatrolPlan/planNames", {
		"type" : no1Type 
	}, function() {
		// 选择类别
		$("#planIds").val(no1Type).select2();
		// checkPoint 加快 选项加载速度
		$(".checkPoint").load(ctx + "/patrol/ccmPatrolResult/mapcheck");
	});
	
	
	// $("#name").focus();
	$("#inputForm").validate(
			{
		submitHandler : function(form) {
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