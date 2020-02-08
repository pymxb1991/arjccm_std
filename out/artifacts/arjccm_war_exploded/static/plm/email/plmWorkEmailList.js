$(document).ready(function() {
	
	//星标邮件动态修改
	$(".star").on("click",function(){
		var id = $(this).attr("emailId");
		var readStatus = $(this).attr("readStatus");
		var isStar = $("#list"+id+readStatus).attr("isStar");
		if(readStatus==""){
			$.getJSON(ctx + "/email/plmWorkEmail/star",{ id:id , isStar:isStar }, function(
					data) {
				changeStar(data,id,readStatus);
			});			
		}else{
			$.getJSON(ctx + "/email/plmWorkEmail/self/star",{ reportId:id , isStar:isStar }, function(
					data) {
				changeStar(data,id,readStatus);
			});	
		}

	});
	function changeStar(data,id,readStatus){
		if(data.isStar==1){
			$("#list"+id+readStatus).attr("title","取消星标");
			$("#list"+id+readStatus).attr("src",ctxStatic+"/plm/email/images/star_yes.png");
			$("#list"+id+readStatus).attr("isStar","0");
		}else{
			$("#list"+id+readStatus).attr("title","添加星标");
			$("#list"+id+readStatus).attr("src",ctxStatic+"/plm/email/images/star_no.png");
			$("#list"+id+readStatus).attr("isStar","1");
		}
		
	}
	
});