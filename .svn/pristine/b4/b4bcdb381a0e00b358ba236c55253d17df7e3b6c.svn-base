/**
 * V 1.0 2018-4-20
 */
$(function() {
  $('#applySubmit').click(function() {
    var id = $(this).attr("worknodeid");
    var $form = $("#Applyform");
    var handletype = $form.find("#handletype").val();
    var sactionremark = $form.find("#sactionremark").val();
    // 如果为空
    if (isEmpty(handletype) || isEmpty(sactionremark)) {
      mui.toast('请填写审核意见', {
        duration : 'short',
        type : 'div'
      });
      return;
    }
    // 提交申请
    $.post(ctx + "/flow/deal/applyAction", {
      "id" : id,
      "handletype" : handletype,
      "sactionremark" : sactionremark
    }, function(data) {
      if (data == "success") {
        mui.toast('提交成功', {
          duration : 'short',
          type : 'div'
        });
        // console.log(ctx + "/flow/deal/dealtList");
        window.location.href = ctx + "/flow/deal/dealtList";
      } else {
        mui.toast('提交失败', {
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