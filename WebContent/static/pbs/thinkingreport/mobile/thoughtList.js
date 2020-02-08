/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
  
  $("#sPartment").load(ctx + "/sys/pbsDepartmentetc/namelist?type=2",
      function() {
        var selectString = $(this).val();
  //  查询 学员
        $("#sMember").load(ctx + "/person/pbsPartymem/findPbsListByOffice?officeid="
                +selectString);
      });
  // 修改 部门
  $("#sPartment").change(function() {
      $("#sMember").load(ctx + "/person/pbsPartymem/findPbsListByOffice", {
          "officeid" : $(this).val()
      }, function() {
          $("#sMember").val("");
      });
  });
	
  var $form = $("#tabbar-with-chat");
	// 点击 接收 按钮
	$('#acceptSubmit').click(function() {
		// 提交申请
	  var sMember = $form.find("#sMember").val()
	   // 如果为空
	  if(isEmpty(sMember)||isEmpty($form.find("#sTitle").val()) ){
	    mui.toast('当前信息需填写完整 ', {
          duration : 'short',
          type : 'div'
      });
	    return;
	  }  
	  
		$.post(ctx + "/thinkingreport/pbsThinkingreport/thinkRepSubmit", {
			"sTitle":$form.find("#sTitle").val(),
			"sContent":$form.find("#sContent").val(),
			"sAcceptermem":sMember,
			"sStat":"0",
		}, function(data) {
			if (data == "success") {
				mui.toast('接收成功', {
					duration : 'short',
					type : 'div'
				});
				 window.location.reload();
			} else {
				mui.toast('接收失败', {
					duration : 'short',
					type : 'div'
				});
			}
		});
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