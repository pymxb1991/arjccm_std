/**
 * V 1.0 2018-4-20
 */
$(function() {
	$(document).ready(
			function() {
				// 文本本身验证
				// $("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});

	// 工作流id
	var sFlowid = $(".sFlowid").attr("topid");
	// 选择工作流
	$("#sFlowid").load(ctx + "/flow/pbsFlowdefinition/namelist", function() {
		$("#sFlowid").val(sFlowid).select2();
		// 节点id并选择节点
		var sFlownodeid = $(".sFlownodeid").attr("topid");
		$("#sFlownodeid").load(ctx + "/flow/pbsFlownode/namelist", {
			"sFlowid" : $(this).val()
		}, function() {
			$("#sFlownodeid").val(sFlownodeid).select2();
		});
	});

	// 当前的 工作流修改后 同时修改 节点 所选内容
	$("#sFlowid").change(function() {
		$("#sFlownodeid").load(ctx + "/flow/pbsFlownode/namelist", {
			"sFlowid" : $(this).val()
		}, function() {
			$("#sFlownodeid").val("").select2();
		});
	});

});