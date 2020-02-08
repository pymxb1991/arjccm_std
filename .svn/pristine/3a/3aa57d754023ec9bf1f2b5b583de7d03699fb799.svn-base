/**
 * V 1.0 2018-4-20
 */
$(function() {

	// $("#name").focus();
	$("#inputForm").validate(
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
					if (element.is(":checkbox") || element.is(":radio")
							|| element.parent().is(".input-append")) {
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

	// 工作模板 节点 所属任务id
	var sFlowid = $(".sFlowid").attr("sFlowid");
	var sFlowworkid = $(".sFlowworkid").attr("sFlowworkid");
	var sNodeid =  $(".sNodeid").attr("sNodeid");
	
	// 选择工作模板
	$("#sFlowid").load(ctx + "/flow/pbsFlowdefinition/namelist", function() {
		$("#sFlowid").val(sFlowid).select2();
		// 节点id
		var sFlownodeid = $(".sFlownodeid").attr("topid");
		// 选择节点
		$("#sNodeid").load(ctx + "/flow/pbsFlownode/namelist", {
			"sFlowid" : $(this).val()
		}, function() {
			$("#sNodeid").val(sNodeid).select2();
		});
	});

	// 当前的 工作流修改后 同时修改 节点 所选内容
	$("#sFlowid").change(function() {
		$("#sNodeid").load(ctx + "/flow/pbsFlownode/namelist", {
			"sFlowid" : $(this).val()
		}, function() {
			$("#sNodeid").val("").select2();
		});
	});
	
	//  所属工作流id
	$("#sFlowworkid").load(ctx + "/flow/pbsFlowwork/namelist" , function() {
		$("#sFlowworkid").val(sFlowworkid).select2();
	});
	

});