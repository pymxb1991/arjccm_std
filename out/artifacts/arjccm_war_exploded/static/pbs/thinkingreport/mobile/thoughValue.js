/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
 
  var $form = $("#valueform");
  // 转发
  $('#acceptSubmit').click(function() {
    // 提交申请
    $.post(ctx + "/thinkingreport/pbsThinkingreport/thinkRepAction", {
      "sReportid" : $form.find("#acceptSubmit").attr("attrid"),
      "sType":"0",
      "sLevel": $form.find("#sLevel").val(),
      "sContent": $form.find("#sContent").val(),
    }, function(data) {
      if (data == "success") {
        mui.toast('接收成功', {
          duration : 'short',
          type : 'div'
        });
        window.location.href = ctx + "/thinkingreport/pbsThinkingreport/thinkRepList";
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