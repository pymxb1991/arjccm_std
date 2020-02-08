$(document).ready(
		function() {
	// 结果Id 与 点位ID
	
	var resultId = $("#ccmPatrolPlanID").attr("resultId");
	var pointId = $("#ccmPatrolPlanID").attr("pointId");
	
	// ccmPatrolResult
	$("#resultId").load(ctx + "/patrol/ccmPatrolResult/resultNames", {
		"type" : resultId
	}, function() {
		// 选择类别
		$("#resultId").val(resultId).select2();
	});

	// ccmPatrolPoint
	$("#pointId").load(ctx + "/patrol/ccmPatrolPoint/pointNames", {
		"type" : pointId
	}, function() {
		// 选择类别
		$("#pointId").val(pointId).select2();
		// checkPoint 加快 选项加载速度
	});

	// 变动事件
	$("#resultId").off().on("change", function() {
		var data = $(this).val();
		$("#pointId").load(ctx + "/patrol/ccmPatrolPoint/pointNames", {
			"id" : data,
		}, function() {
			$("#pointId").val(pointId).select2();
		});
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