$(document).ready(function() {
	
	
	
    $('#collapseOne').collapse({
        toggle: false
    });
    
    // $("#name").focus();
    $('#btnSubmit').click(function(){
    	//$('#inputForm').submit();
    });
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
    
    //初始化抄送密送

	cmInit();
	
	function cmInit(){
		var isC = $("#isC").val();
		var isM = $("#isM").val();
		if(isC == "true"){
			$("#addC").hide();
			$("#subC").show();
			$("#divC").show();
		}else{
			$("#addC").show();
			$("#subC").hide();
			$("#divC").hide();
		};
		if(isM == "true"){
			$("#addM").hide();
			$("#subM").show();
			$("#divM").show();
		}else{
			$("#addM").show();
			$("#subM").hide();
			$("#divM").hide();
		};		
	};
	

	//添加抄送
	$("#addC").on("click",function(){
		$("#isC").val("true");
		cmInit();
	});
	//删除抄送
	$("#subC").on("click",function(){
		$("#isC").val("false");
		cmInit();
	});    
	//添加密送
	$("#addM").on("click",function(){
		$("#isM").val("true");
		cmInit();
	});
	//删除密送
	$("#subM").on("click",function(){
		$("#isM").val("false");
		cmInit();
	});   
	
	
	
});


//收件人的非空验证
function saveForm(){
	var readId = $("#plmWorkEmailSReadId").val();
	var html1 = '<label for="" class="error">必填信息 <label>';
	if(readId.length==0){
		$("#show1").html(html1);
		$("#plmWorkEmailSReadName").focus();
	}
	if(readId.length!=0){
		$("#btnApply").submit();
	}
}