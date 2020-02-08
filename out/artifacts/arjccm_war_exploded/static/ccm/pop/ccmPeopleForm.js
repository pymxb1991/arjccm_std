/**
 * 表单验证-实有人口
 */

		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
				
			});
			
			//社区选择网格
			var areaComId = $("#areaComIdId").val();
			var id = $("#id").val();
			if(id==""){
				id = "001";
			}
			var areaGridId = $("#areaGridIdId").val();
			//alert(areaComId+"333"+id+"333"+areaGridId);
			$("#newNet").load(ctx + "/pop/ccmPeople/getNetAreaForm?usedname="+areaComId+"&id="+id+"&name="+areaGridId, {});
			
			
			//社区选择房屋
			var areaGridId = $("#areaGridIdId").val();
			var id = $("#id").val();
			if(id==""){
				id = "001";
			}
			var roomId = $("#roomIdId").val();
			//alert(areaGridId+"333"+id+"333"+roomId);
			$("#newHouse").load(ctx + "/pop/ccmPeople/getHouseAreaForm?usedname="+areaGridId+"&id="+id+"&name="+roomId, {});
			
			
			
			var s = $("#sel").val();
			if(s=="10"){
				$("#person").show();
				$("#float").hide();
				$("#oversea").hide();
				$("#unsettle").hide();
			}else if(s=="20"){
				$("#person").hide();
				$("#float").show();
				$("#oversea").hide();
				$("#unsettle").hide();
			}else if(s=="30"){
				$("#person").hide();
				$("#float").hide();
				$("#oversea").show();
				$("#unsettle").hide();
			}else if(s=="40"){
				$("#person").hide();
				$("#float").hide();
				$("#oversea").hide();
				$("#unsettle").show();
			}else{
				$("#person").hide();
				$("#float").hide();
				$("#oversea").hide();
				$("#unsettle").hide();
			}
			
			
		});
		//隐藏显示
		function sels(){
			var s = $("#sel").val();
			if(s=="10"){
				$("#person").show();
				$("#float").hide();
				$("#oversea").hide();
				$("#unsettle").hide();
			}else if(s=="20"){
				$("#person").hide();
				$("#float").show();
				$("#oversea").hide();
				$("#unsettle").hide();
			}else if(s=="30"){
				$("#person").hide();
				$("#float").hide();
				$("#oversea").show();
				$("#unsettle").hide();
			}else if(s=="40"){
				$("#person").hide();
				$("#float").hide();
				$("#oversea").hide();
				$("#unsettle").show();
			}else{
				$("#person").hide();
				$("#float").hide();
				$("#oversea").hide();
				$("#unsettle").hide();
			}
		}
		
		
		









