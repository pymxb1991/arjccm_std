/**
 * V 1.0 2018-4-20
 */
$(function() {
  $(document).ready(
      function() {
        // 文本本身验证
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
      });
  var topid = $(".sType").attr("topid");
  $("#sType").load(ctx + "/flow/pbsFlowdefinition/namelist", function() {
    $("#sType").val(topid).select2();
  });

  // 保存按钮
  $("#btnSubmit").click(function() {
    var id = $(".worknodeid").attr("worknodeid");
    var handletype = $("#handletype").val();
    var sactionremark = $("#sactionremark").val();
    // 如果选项为空
    if (isEmpty(sactionremark)) {
      alertx("请您填写意见！");
      return;
    }
    console.log(id + "||" + handletype + "||" + sactionremark)
    // window.location = ctx + "/flow/dealPc/dealtList";
    // 提交投票信息
    $.post(ctx + "/flow/dealPc/applyAction", {
      "id" : id,
      "handletype" : handletype,
      "sactionremark" : sactionremark
    }, function(data) {
      if (data == "success") {
        window.location = ctx + "/flow/dealPc/dealtList";
      } else {
        alertx("请您刷新后重试！");
      }
    });
    // savevote
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