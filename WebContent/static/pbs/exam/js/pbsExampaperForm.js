/**
 * V 1.0 2018-4-20  
 */
$(function() {
    $(document).ready(function() {
        // 文本本身验证
        // $("#name").focus();
        $("#inputForm").validate({
            submitHandler: function(form) {
            	var startTime = $("#startTime").val();
        		var endTime = $("#endTime").val();
        		var currentTime = new Date();
        		var startTimeEx = Date.parse(startTime);
        		var endTimeEx = Date.parse(endTime);
        		var subTime = currentTime.getTime()-startTimeEx;
        		if(startTimeEx < currentTime.getTime()){
        			alertx("开始时间不能小于当前系统时间");
        			return false;
        		}
        		if(startTimeEx > endTimeEx){
        			alertx("开始时间不能大于结束时间");
        			return false;
        		}
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
    // 主题id
    var sProject = $(".sProject").attr("topid");
    // 选择主题
    $("#sProject").load(ctx + "/question/pbsQuestionProject/listPage",
    function() {
        $("#sProject").val(sProject).select2();
    });
    
    
    // 主题id
    var sLevel = $(".sLevel").attr("topid");
    // 选择主题
    $("#sLevel").load(ctx + "/question/pbsQuestionLevel/namelist",
    function() {
        $("#sLevel").val(sLevel).select2();
    });

});