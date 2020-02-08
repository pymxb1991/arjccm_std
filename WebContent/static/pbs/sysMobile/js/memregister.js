$(function() {

	// 表单对象
	var $copy = $("#memregister");

	// 点击进行提交
	$copy.find("#registerButton").click(function() {
		// 错误提示消失
		$("#messageError").hide();

		// 显示 form 表单信息
		var sIdtype = $.trim($copy.find("#sIdtype").val());
		var sIdnum = $.trim($copy.find("#sIdnum").val());
		 if(sIdnum == "" || sIdnum == undefined){
		    	mui.toast('证件号码不能为空', {
		            duration : 'short',
		            type : 'div'
		         });
		    	return;
		    }
		var sName = $.trim($copy.find("#sName").val());
		if(sName == "" || sName == undefined){
	    	mui.toast('姓名不能为空', {
	            duration : 'short',
	            type : 'div'
	         });
	    	return;
	    }
		var iSex = $.trim($copy.find("#iSex").val());
		var iNation = $.trim($copy.find("#iNation").val());
		var dtBirth = $.trim($copy.find("#dtBirth").val());
		var sEducation = $.trim($copy.find("#sEducation").val());
		var iType = $.trim($copy.find("#iType").val());
		var sPartybranch = $.trim($copy.find("#sPartybranch").val());
		var dtAdmission = $.trim($copy.find("#dtAdmission").val());
		var dtCorrection = $.trim($copy.find("#dtCorrection").val());
		var sPost = $.trim($copy.find("#sPost").val());
		var sMobilephone = $.trim($copy.find("#sMobilephone").val());
		var sFixedphone = $.trim($copy.find("#sFixedphone").val());
		var sHomeaddr = $.trim($copy.find("#sHomeaddr").val());
		var iStat = $.trim($copy.find("#iStat").val());
		var iOutcontact = $.trim($copy.find("#iOutcontact").val());
		var dtOutcontact = $.trim($copy.find("#dtOutcontact").val());
		var iFloat = $.trim($copy.find("#iFloat").val());
		var sFloattopro = $.trim($copy.find("#sFloattopro").val());
		var sFloattocity = $.trim($copy.find("#sFloattocity").val());
		var sFloattocounty = $.trim($copy.find("#sFloattocounty").val());
		// post请求
		$.post(ctx + "/sys/Mobile/register", {
			"sIdtype" : sIdtype,
			"sIdnum" : sIdnum,
			"sName" : sName,
			"iSex" : iSex,
			"iNation" : iNation,
			"dtBirth" : dtBirth,
			"sEducation" : sEducation,
			"iType" : iType,
			"sPartybranch" : sPartybranch,
			"dtAdmission" : dtAdmission,
			"dtCorrection" : dtCorrection,
			"sPost" : sPost,
			"sMobilephone" : sMobilephone,
			"sFixedphone" : sFixedphone,
			"sHomeaddr" : sHomeaddr,
			"iStat" : iStat,
			"iOutcontact" : iOutcontact,
			"dtOutcontact" : dtOutcontact,
			"iFloat" : iFloat,
			"sFloattopro" : sFloattopro,
			"sFloattocity" : sFloattocity,
			"sFloattocounty" : sFloattocounty,
			"sRegstat":0,
		}, function(data) {
			// 成功
			if (data == "sucess") {
				window.location = ctx + "/sys/Mobile/personInfo";
			} else {
				// 错误展示
				$("#messageError").show();
				$("#messageError").html(data)
			}
		})

	});
});