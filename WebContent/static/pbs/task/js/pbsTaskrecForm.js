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
    var  sSuperiorid = $(".sSuperiorid").attr("topid");
    // 选择主题
    $("#sSuperiorid").load(ctx + "/task/pbsTaskrec/namelist",function(){
    	$("#sSuperiorid").val(sSuperiorid).select2();
    });
    
    // 类型id
    var  sType = $(".sType").attr("topid");
    // 选择主题
    $("#sType").load(ctx + "/task/pbsTasktype/namelist",function(){
    	$("#sType").val(sSuperiorid).select2();
    });

    
});