$(document).ready(
	function() {
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
		  // 主题id
	    var sPost = $(".sPost").attr("topid");
	    // 选择主题
	    $("#sPost").load(ctx + "/person/pbsPositionlevel/namelist?sType=0",
	    function() {
	        $("#sPost").val(sPost).select2();
	    });
		
	    var sPosttitle = $(".sPosttitle").attr("topid");
	    // 选择主题
	    $("#sPosttitle").load(ctx + "/person/pbsPositionlevel/namelist?sType=1",
	    function() {
	        $("#sPosttitle").val(sPosttitle).select2();
	    });

	});