		$(function() {
			isSupInit();
			$("input[name='plmAct.isSup']").on("click",function (){
				isSupInit();
			});	
			function isSupInit(){
				var isSup = $("input:radio[name='plmAct.isSup']:checked").val();
				if(isSup=='1'){
					$(".isSup").show();
					$("#isSubTd").attr("colspan","2");
				}else{
					$(".isSup").hide();
					$("#isSubTd").attr("colspan","6");
				}
			};
		});
