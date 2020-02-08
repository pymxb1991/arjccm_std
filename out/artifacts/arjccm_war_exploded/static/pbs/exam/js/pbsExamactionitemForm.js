/**
 * V 1.0 2018-4-20  
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

    // 试卷id
    var sExampaper = $(".sExampaper").attr("topid");
    // 选择主题
    $("#sExampaper").load(ctx + "/exam/pbsExampaper/namelist",
    function() {
        $("#sExampaper").val(sExampaper).select2();
    });
    
    // 考题id
    var sItem = $(".sItem").attr("topid");
    // 选择考题
    $("#sItem").load(ctx + "/question/pbsQuestionObjective/namelist",
    function() {
        $("#sItem").val(sItem).select2();
    });
    
    // 答题id
    var sActionid = $(".sActionid").attr("topid");
    // 选择考题
    $("#sActionid").load(ctx + "/exam/pbsExamaction/namelist",
    function() {
        $("#sActionid").val(sActionid).select2();
    });


});