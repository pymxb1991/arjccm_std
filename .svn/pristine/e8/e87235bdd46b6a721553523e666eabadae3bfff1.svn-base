/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
    $(document).ready(function() {
        // 文本本身验证
        // $("#name").focus();
        $("#inputForm").validate({
            submitHandler: function(form) {
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
    });
    
    // 任务id
    var  sReportid = $(".sReportid").attr("topid");
    // 选择主题
    $("#sReportid").load(ctx + "/thinkingreport/pbsThinkingreport/namelist",function(){
    	$("#sReportid").val(sReportid).select2();
    });
    
});