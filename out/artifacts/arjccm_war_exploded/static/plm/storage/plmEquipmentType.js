$(document).ready(function() {
	var typeChild=$("#typeChild").val();
	var typeChildName=$("#s2id_typeChild .select2-chosen").html();
	
	//typeChildInit();
	
	$("#typeId").change(function(){
		//$("#typeChild").empty();
		//$("#typeChild").val("").select2();
		//typeChildInit();
	});
	
	
	function typeChildInit(){
		var type = $("#typeId").attr("dictTyep");
		var value = $("#typeId").val();
		if(value==null||value==""){
			$("#typeChild").empty();
			
		}else{
		$.getJSON(ctx + "/sys/dict/typeChildList", {
			"value":value,
			"type":type
		},function(data){
			$("#typeChild").empty();
			$("#typeChild").prev().find(".select2-chosen").text("未选择")
			$("#typeChild").append("<option value selected='selected'>未选择</option>");
			$("#typeChild").append("<option value='99' >其他</option>");
			$.each(data,function(i,item){
				$("#typeChild").append("<option value='"+item.value+"'>"+item.label+"</option>");
			});
			
			if(typeChild!=null&&typeChild!=""){
				$("#s2id_typeChild .select2-chosen").html(typeChildName)
				//alert(typeChild)
				$("#typeChild").val(typeChild);
				
				//alert($("#typeChild").val())
				
			}
			
		})
		}
	}
	
});