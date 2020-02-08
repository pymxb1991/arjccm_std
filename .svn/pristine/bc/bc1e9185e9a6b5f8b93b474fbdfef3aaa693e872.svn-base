function checkAll(e, itemName){
        var repairId = document.getElementsByName(itemName);    //获取全选复选框
        for (var i=0; i<repairId.length; i++){
        repairId[i].checked = e.checked;    //改变所有复选框的状态为全选复选框的状态
       }
    }
function selectSingle(){
	var k=0;
	var oInput=document.getElementsByName("repairId");
	for (var i=0;i<oInput.length;i++){
	  if(oInput[i].checked==false){
	k=1;
	break;
	    }
	}
	if(k==0){
	 //document.getElementsByName("selid").checked=true;
	 document.getElementById("checkId").checked=true;

	}
	else{
	document.getElementById("checkId").checked=false;
	//document.getElementsByName("selid").checked=false;
	}
}