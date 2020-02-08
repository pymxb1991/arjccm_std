		$(document).ready(function() {
			
			//获取空闲车辆
			$.getJSON(ctx + "/car/plmCar/selectList",{ vehicleStatus:"01" }, function(
					data) {
				$("#carIds").select2({
					placeholder: "请选择车辆",
				    allowClear: true,
				    data: data
				 });

			});
			//获取所有车辆
			$.getJSON(ctx + "/car/plmCar/selectList", function(
					data) {
				$("#carIdsAll").select2({
					placeholder: "请选择车辆",
				    allowClear: true,
				    data: data
				 });

			});
			//当选择车辆变化，自动更新联系人及电话
			$("#carIdsAll").change(function(){
				var id = $("#carIdsAll").val();
				$.getJSON(ctx + "/car/plmCar/getById",{ id:id },function(data){
					$("#carBrand").val(data.brand);
					$("#carVmodel").val(data.vmodel);
				})
			});			
			//获取在岗司机
			$.getJSON(ctx + "/car/plmCarDriver/selectList",{ status:"01" }, function(
					data) {
				$("#driverIds").select2({
					placeholder: "请选择司机（不选择默认自驾）",
				    allowClear: true,
				    data: data
				 });

			});
			//获取所有司机
			$.getJSON(ctx + "/car/plmCarDriver/selectList", function(
					data) {
				$("#driverIdsAll").select2({
					placeholder: "请选择司机",
				    allowClear: true,
				    data: data
				 });

			});	
			//获取所有维保单位
			$.getJSON(ctx + "/car/plmCarRepair/selectList", function(
					data) {
				$("#repairComIds").select2({
					placeholder: "请选择维保单位",
				    allowClear: true,
				    data: data
				 });
			});			
			//当选择维保单位变化，自动更新联系人及电话
			$("#repairComIds").change(function(){
				var id = $("#repairComIds").val();
				$.getJSON(ctx + "/car/plmCarRepair/getById",{ id:id },function(data){
					$("#repairLeader").val(data.leader);
					$("#repairPhone").val(data.phone);
				})
			});
			
			//计算总费用
			$("#totaFee").focus(function(){
				var oilFee = $("#oilFee").val();
				var tollFee = $("#tollFee").val();
				var parkingFee = $("#parkingFee").val();
				var repairFee = $("#repairFee").val();
				var otherFee = $("#otherFee").val();
				var totaFee = 0;
				if(oilFee!=null && oilFee!=""){
					totaFee += parseFloat(oilFee);
				}
				if(tollFee!=null && tollFee!=""){
					totaFee += parseFloat(tollFee);
				}
				if(parkingFee!=null && parkingFee!=""){
					totaFee += parseFloat(parkingFee);
				}
				if(repairFee!=null && repairFee!=""){
					totaFee += parseFloat(repairFee);
				}
				if(otherFee!=null && otherFee!=""){
					totaFee += parseFloat(otherFee);
				}
				$(this).val(totaFee);
			});
			
			//根据流程类型查到已完成流程
			var type = $("#type").val();
			if(type!=null && type!=""){
				applyList(type);
			}
			$("#type").change(function(){
				var type = $(this).val();
				applyList(type);
			});
			function applyList(type){
				$.getJSON(ctx + "/car/plmCarUse/applyList", {
					type:type
				},function(data) {
					$("#proBfore").hide();
					$("#process").select2({
						placeholder: "请选择关联流程",
					    allowClear: true,
					    data: data
					 });
				});
			}
			
		});