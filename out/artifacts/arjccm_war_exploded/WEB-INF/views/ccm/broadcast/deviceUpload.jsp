<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>下发广播</title>
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBone.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBone.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
<link rel="stylesheet" href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
	  rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function upload(){
		var code = $("#deviceCode").val();
		var fileUrl = $("#fileUrl").val();
		if(code == null || code == "" || code === undefined){
			parent.layer.alert('设备编号为空，请添加!', {
				icon: 3,
    			title: "警告"});
			return;
		}
		if(fileUrl == null || fileUrl == "" || fileUrl === undefined){
			parent.layer.alert('请选择下发文件!', {
				icon: 3,
    			title: "警告"});
			return;
		}
		$.ajax({
	        type: "POST",
	        url: ctx + "/broadcast/ccmDeviceBroadcast/upload",
	        data: {
	        	code:code,
	        	fileUrl:fileUrl
	        },
	        dataType: "json",
	        cache: false,
	        async: true,
	        success: function (data) {
	        	if(data == "4"){
	        		parent.layer.alert('下发失败!请检查设备编号和文件是否为空!', {
						icon: 2,
	        			title: "提示"});
	        	}else if(data == "3"){
	        		parent.layer.alert('下发失败!当前仅支持MP3和TXT类型文件!', {
						icon: 2,
	        			title: "提示"});
	        	}else if(data == "1"){
	        		parent.layer.alert("下发成功!", {
						icon: 1,
	        			title: "提示"});
	        	}else{
	        		parent.layer.alert("下发失败!", {
						icon: 2,
	        			title: "提示"});
	        	}
	        }, error: function (e,data) {
	        	console.error("e",e);
	        }
	    });
	}
</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="deviceBroadcast" action="${ctx}/broadcast/ccmDeviceBroadcast/upload" method="post" class="form-horizontal">
			<form:hidden id="deviceId" path="id"/>
			<form:hidden id="deviceCode" path="code"/>
			<sys:message content="${message}"/>
			<div>
				<form:hidden id="fileUrl" path="fileUrl" htmlEscape="false" maxlength="24" class="input-xlarge"/>
				<sys:ckfinder input="fileUrl" type="files" uploadPath="/deviceBroadcast" selectMultiple="false"/>
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="upload()" value="确定下发"/>
			</div>
	</form:form>
</body>
</html>