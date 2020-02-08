/**
 * V 1.0 2018-4-20
 */
$(function() {
	// 切换
	$("#pid").change(function() {
		// 或所选的值
		var type = $(this).val();
		if (type == "ALL") {
			$(".flowList .typeAll").show();
			return ;
		}
		$(".flowList .typeAll").hide();
		$(".flowList ." + type).show();
	});

	// 判断为空
	function isEmpty(obj) {
		if (typeof obj == "undefined" || obj == null || obj == "") {
			return true;
		} else {
			return false;
		}
	}
});