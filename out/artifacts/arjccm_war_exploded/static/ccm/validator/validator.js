/**
 * 表单验证
 */



		//身份证号码格式
		function ids(myid){
			//alert("123");
			//alert(myid);
			var ids = $("#"+myid).val();
			//alert(ids);
			if (ids.length>0) {
				var express = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
				result = express.test(ids);
				if (result) {
					$("#"+myid).css("color","#555555");
				}else{
					alert("身份证号码格式错误，请重新输入！");
					$("#"+myid).css("color","red");
					$("#"+myid).focus();
				}
			}
		}
		/*
		$("#ident").live("change",function(){
			console.log($(this).val());
			var ids = $(this).val();
			if (ids.length>0) {
				var express = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
				result = express.test(ids);
				if (result) {
					$(this).css("color","#555555");
				}else{
					alert("身份证号码格式错误，请重新输入！");
					$(this).css("color","red");
					$(this).focus();
				}
			}
			
		});
		*/
		
		//联系方式格式
		function telephones(mytel){
			var tels = $("#"+mytel).val();
			if (tels.length>0) {
				var express = /^((\d{11})|(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
				result = express.test(tels);
				if (result) {
					$("#"+mytel).css("color","#555555");
				}else{
					alert("联系方式格式错误，请重新输入！");
					$("#"+mytel).css("color","red");
					$("#"+mytel).focus();
				}
			}
		}
		
		//邮箱格式
		function emails(myemail){
			var emails = $("#"+myemail).val();
			if (emails.length>0) {
				var express = /^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,5}$/;
				result = express.test(emails);
				if (result) {
					$("#"+myemail).css("color","#555555");
				}else{
					alert("邮箱格式错误，请重新输入！");
					$("#"+myemail).css("color","red");
					$("#"+myemail).focus();
				}
			}
		}
		
		
		// 输入正整数1000000
		function nums(mynum){
			var nums = $("#"+mynum).val();
			if (nums.length>0) {
				var express = /^(?:\d{1,6}|1000000)$/;
				result = express.test(nums);
				if (result) {
					$("#"+mynum).css("color","#555555");
				}else{
					alert("输入格式错误，请重新输入！");
					$("#"+mynum).css("color","red");
					$("#"+mynum).focus();
				}
			}
		}
		
		// 数字
		function numbers(mynumber){
			var numbers = $("#"+mynumber).val();
			if (numbers.length>0) {
				var express = /^(-|\+)?\d+(\.\d+)?$/;
				result = express.test(numbers);
				if (result) {
					$("#"+mynumber).css("color","#555555");
				}else{
					alert("输入格式为数字，请重新输入！");
					$("#"+mynumber).css("color","red");
					$("#"+mynumber).focus();
				}
			}
		}
		
		
		// 金额格式
		function moneys(mymoney){
			var moneys = $("#"+mymoney).val();
			if (moneys.length>0) {
				var express = /^(-|\+)?\d+(\.?\d{0,2})$/;
				result = express.test(moneys);
				if (result) {
					$("#"+mymoney).css("color","#555555");
				}else{
					alert("输入格式为货币，请重新输入！");
					$("#"+mymoney).css("color","red");
					$("#"+mymoney).focus();
				}
			}
		}
		
		
		
		
		
		
		
		
		