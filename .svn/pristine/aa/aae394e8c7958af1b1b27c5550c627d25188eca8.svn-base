/**
 * V 1.0 2018-1-27 11:13:45 楼栋信息
 */
$(function() {
	$("#btnSubmit").on("click" ,function(){
		$("#searchForm").submit();
	})
	$("#btnExport").click(
			function() {
				
				top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action",
								ctx + "/org/ccmOrgSocialorg/export");
						$("#searchForm").submit();
						// 还原查询action 
						$("#searchForm").attr("action",
								ctx + "/org/ccmOrgSocialorg/");
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
	
	$("#btnImport").click(function() {
		$.jBox($("#importBox").html(), {
			title : "导入数据",
			buttons : {
				"关闭" : true
			},
			bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
		});
	});

});

function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}