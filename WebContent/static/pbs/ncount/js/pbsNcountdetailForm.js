$(document).ready(
    function() {
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

      // 主题id
      var sParentid = $(".sParentid").attr("topid");
      // 选择主题
      $("#sParentid").load(ctx + "/ncount/pbsNcount/namelist",
          function() {
            $("#sParentid").val(sParentid).select2();
          });

    });