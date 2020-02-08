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

  var topid = $(".sPartment").attr("topid");
  // 赋值
  $("#sPartment").load(ctx + "/sys/pbsDepartmentetc/namelist?type=2",
      function() {
        $("#sPartment").val(topid).select2();
      });

});