/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {

  var $taskform = $("#taskform");

  $taskform.find("#sStatus").change(function() {
    // 如果未能完成 评价则变为差（D）
    if ($(this).val() == "1") {
      $taskform.find("#sValue").val("D");
    }
  });

  // 点击 接收 按钮
  $('#applySubmit').click(function() {
    // 获取相关的 值
    var taskid = $(this).attr("pbsTaskrecid");
    var sValue = $taskform.find("#sValue").val();
    var sContent = $taskform.find("#sContent").val();
    var sStatus = $taskform.find("#sStatus").val();
    // 提交申请
    $.post(ctx + "/task/pbsTaskrec/applyValue", {
      "sTaskid" : taskid,
      "sContent" : sContent,
      "sValue" : sValue,
      "sStatus" : sStatus,
    }, function(data) {
      if (data == "success") {
        mui.toast('接收成功', {
          duration : 'short',
          type : 'div'
        });
        window.location.href = ctx + "/task/pbsTaskrec/distributedList";
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