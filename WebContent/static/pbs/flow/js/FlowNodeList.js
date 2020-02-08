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
	});

	// 上一个节点id
	var sPrevnodeid = $(".sPrevnodeid").attr("topid");
	// 选择上一个节点
	$("#sPrevnodeid").load(ctx + "/flow/pbsFlownode/namelist",{"sFlowid":sFlowid} ,function() {
		$("#sPrevnodeid").val(sPrevnodeid).select2();
	});

	// 下一个节点id
	var sNextnodeid = $(".sNextnodeid").attr("topid");
	// 选择下一个节点
	$("#sNextnodeid").load(ctx + "/flow/pbsFlownode/namelist", {"sFlowid":sFlowid} ,function() {
		$("#sNextnodeid").val(sNextnodeid).select2();
	});

});