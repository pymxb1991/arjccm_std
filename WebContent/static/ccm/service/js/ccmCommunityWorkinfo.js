$(document).ready(function() {
	// 第一类选项
	var no1Type = $(".ccmCommunityWorkType").attr("attrType1");
	// 第二类选项
	var no2Type = $(".ccmCommunityWorkType").attr("attrType2");
	// 表单选项
	var purposeType = $(".ccmCommunityWorkType").attr("attrPurpose");
	
	// 小类别
	$("#type2").load(ctx + "/service/ccmCommunityWork/selectType", {
		"type" : no1Type,
		"type2" : no2Type,
		"purposeType":purposeType
	}, function() {
		// 选择类别
		$("#type2").val(no2Type).select2();
	});

	// 变动事件
	$("#type1").off().on("change", function() {
		var data = $(this).val();
		if(data==""){//判断一级分类为全部时二级分类也显示全部
			$("#type2").load(ctx + "/service/ccmCommunityWork/selectType", {
				"type" : no1Type,
				"type2" : no2Type,
				"purposeType":purposeType
			}, function() {
				$("#type2").val(no2Type).select2();
			});
		}else{
			$("#type2").load(ctx + "/service/ccmCommunityWork/selectType", {
				"type" : data,
				"type2" : no2Type
			}, function() {
				$("#type2").val("").select2();
			});
		}
		
	});
	
	/*// 二级分类变动事件
	$("#type2").off().on("change", function() {
		var index = document.getElementById("type2").selectedIndex;//获取当前选择项的索引.
		var data = document.getElementById("type2").options[index].text;//获取当前选择项的值.
		console.info(data);
	});*/
});