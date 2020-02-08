//所列图 列表切换js
$(document).ready(function() {
	
	
	
	
		  //获取当前页面的路径名称  用来做该页面的Cookie的唯一值
		var pagename=window.location.pathname;
	
		if(pagename.substring(pagename.length-5, pagename.length)=="/list"){
			pagename=pagename.substring(0,pagename.length-5)
		}else if(pagename.substring(pagename.length-1, pagename.length)=="/"){
			pagename=pagename.substring(0,pagename.length-1)
		}
			
		//alert(pagename)
		//读取cookie信息   如果没有默认为列表页面
		 if(getCookie(pagename)==null||getCookie(pagename)=="list"){
			
			  $("#switchbtn .listbtn").css({"color":"#2fa4e7","background-color":"#f2f2f2"});
			 $("#prodInfo_List").show();
			   $("#prodInfo_small").hide();
			   setCookie(pagename,"list")
			  
		 }else {
			  $("#switchbtn .thumbnailbtn").css({"color":"#2fa4e7","background-color":"#f2f2f2"});
			 $("#prodInfo_List").hide();
			    $("#prodInfo_small").show();
			    	    
			  
		}
		
		$("#switchbtn .thumbnailbtn").click(function(){
			
		   $(this).css({"color":"#2fa4e7","background-color":"#f2f2f2"});
		    $("#switchbtn .listbtn").css({"color":"inherit","background-color":"inherit"});
		    $("#prodInfo_List").hide();
		    $("#prodInfo_small").show();		   		   
		    setCookie(pagename,"small")
		    
		    /*var pagez=  $(".pagination .controls a input").eq(1).val();	
			   var pagey=   $(this).attr("page")
			    if(pagez!=pagey){ page(1,pagey,'');}*/
		});
		$("#switchbtn .listbtn").click(function(){
		   $(this).css({"color":"#2fa4e7","background-color":"#f2f2f2"});
		    $("#switchbtn .thumbnailbtn").css({"color":"inherit","background-color":"inherit"});
		    $("#prodInfo_List").show();
		   $("#prodInfo_small").hide();
		   setCookie(pagename,"list")
		 
		/* var pagez=  $(".pagination .controls a input").eq(1).val();	
		   var pagey=   $(this).attr("page")
		    if(pagez!=pagey){ page(1,pagey,'');}*/
		   
		});		
	});
	//设置	setCookie  
	function setCookie(name,value)
	{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	//读取	sCookie  
	function getCookie(name)
	{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
	}