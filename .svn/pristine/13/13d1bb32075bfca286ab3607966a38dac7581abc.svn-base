/**
 * 表单验证
 * 测试测试测试
 */


		function submits(){
			var ident0 = $(".ident0").val();//身份证号码格式
			var telephone0 = $(".telephone0").val();//联系方式格式
			var ident1 = $(".ident1").val();//身份号码1
			var telephone1 = $(".telephone1").val();//联系方式1
			var ident2 = $(".ident2").val();//身份号码2
			var telephone2 = $(".telephone2").val();//联系方式2
			var ident3 = $(".ident3").val();//身份号码3
			var telephone3 = $(".telephone3").val();//联系方式3

			//
			var a =0;
			var b =0;
			var c =0;
			var d =0;
			var e =0;
			var f =0;
			var g =0;
			var h =0;
			
			var express1 = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
			var express2 = /^((\d{11})|(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
			
			var htmlIdent = '<label for="" class="error">身份证号码格式错误！<label>';
			var htmlTelephone = '<label for="" class="error">联系方式格式格式错误！<label>';
			var htmlNull = '';
			
			if(typeof(ident0)!="undefined"){
				if(ident0.length>0){
					var result1 = express1.test(ident0);
					if (result1==false) {
						a = 1;
						$("#ident0").html(htmlIdent);
						$(".ident0").focus();
					}else{
						$("#ident0").html(htmlNull);
					}
				}
			}
			
			if(typeof(telephone0)!="undefined"){
				if(telephone0.length>0){
					var result2 = express2.test(telephone0);
					if (result2==false) {
						b = 1;
						$("#telephone0").html(htmlTelephone);
						$(".telephone0").focus();
					}else{
						$("#telephone0").html(htmlNull);
					}
				}
			}
			
			if(typeof(ident1)!="undefined"){
				if(ident1.length>0){
					var result3 = express1.test(ident1);
					if (result3==false) {
						c = 1;
						$("#ident1").html(htmlIdent);
						$(".ident1").focus();
					}else{
						$("#ident1").html(htmlNull);
					}
				}
			}
			
			if(typeof(telephone1)!="undefined"){
				if(telephone1.length>0){
					var result4 = express2.test(telephone1);
					if (result4==false) {
						d = 1;
						//$(".telephone1").css("color","red");
						//alert("联系方式格式格式错误，请重新输入！");
						$("#telephone1").html(htmlTelephone);
						$(".telephone1").focus();
					}else{
						$("#telephone1").html(htmlNull);
					}
				}
			}
			
			if(typeof(ident2)!="undefined"){
				if(ident2.length>0){
					var result5 = express1.test(ident2);
					if (result5==false) {
						e = 1;
						$("#ident2").html(htmlIdent);
						$(".ident2").focus();
					}else{
						$("#ident2").html(htmlNull);
					}
				}
			}
			
			if(typeof(telephone2)!="undefined"){
				if(telephone2.length>0){
					var result6 = express2.test(telephone2);
					if (result6==false) {
						f = 1;
						$("#telephone2").html(htmlTelephone);
						$(".telephone2").focus();
					}else{
						$("#telephone2").html(htmlNull);
					}
				}
			}
			
			if(typeof(ident3)!="undefined"){
				if(ident3.length>0){
					var result7 = express1.test(ident3);
					if (result7==false) {
						g = 1;
						$("#ident3").html(htmlIdent);
						$(".ident3").focus();
					}else{
						$("#ident3").html(htmlNull);
					}
				}
			}
			
			if(typeof(telephone3)!="undefined"){
				if(telephone3.length>0){
					var result8 = express2.test(telephone3);
					if (result8==false) {
						h = 1;
						$("#telephone3").html(htmlTelephone);
						$(".telephone3").focus();
					}else{
						$("#telephone3").html(htmlNull);
					}
				}
			}
			
			var name = $("#name").val();
			var html1 = '<label for="" class="error">必填信息 *<label>';
			if(name.length!=0){
				$("#show1").html("*");
			}else{
				$("#show1").html(html1);
				$("#name").focus();
				
			}

			if(a==0 && b==0 && c==0 && d==0 && e==0 && f==0 && g==0 && h==0 &&name.length!=0){
				$("#inputForm").submit();
			}


		
		}		
		function onclickNet(){
			//社区选择网格
			var areaComId = $("#areaComIdId").val();
			var id = $("#id").val();
			if(id==""){
				id = "001";
			}
			var areaGridId = $("#areaGridIdId").val();
			//alert(areaComId+"333"+id+"333"+areaGridId);
			$("#newNet").load(ctx + "/pop/ccmPeople/getNetAreaForm?usedname="+areaComId+"&id="+id+"&name="+areaGridId, {});

			saveForm();
			
		}
		function onclickHouse(){
			//社区选择房屋
			var areaGridId = $("#areaGridIdId").val();
			var id = $("#id").val();
			if(id==""){
				id = "001";
			}
			var roomId = $("#roomIdId").val();
			//alert(areaGridId+"333"+id+"333"+roomId);
			$("#newHouse").load(ctx + "/pop/ccmPeople/getHouseAreaForm?usedname="+areaGridId+"&id="+id+"&name="+roomId, {});
			
		}
	
		
		
		
		