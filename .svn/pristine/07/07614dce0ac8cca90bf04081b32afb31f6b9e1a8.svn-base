//是否有涉密资格
function HasSecret(_this){
	var val=$('#hasPermission').val();
	var href=$(_this).attr('data-href');
	if(val=="0"){
		layer.confirm('请输入涉密密码:<input autofocus="autofocus" id="Secret" type="password"  value="" style="margin: 0; width: 168px; margin-right: 5px;"/>', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				var val=$('#Secret').val();
				if(val==''){
					top.$.jBox.tip("密码不能为空");
					return;
				}
				window.location.href=href+'?permissionKey='+val;
				layer.close(layer.index);
			}, function(){
				//alert('取消');
			}
		);
	}else{
		window.location.href=href+'?permissionKey=';
	}
 	
}