<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>视频监控</title>
	<link rel="stylesheet"
	href="${ctxStatic}/ccm/liveVideo/css/livedemo.css">
    <link rel="stylesheet"
	href="${ctxStatic}/ccm/liveVideo/css/video-js.css">
	
</head>
<body>
<input type="hidden" value="${ccmDevice.ip}" id="ccmDeviceIp"/>
<input type="hidden" value="${ccmDevice.port}" id="ccmDevicePort"/>
<input type="hidden" value="${ccmDevice.account}" id="ccmDeviceAccount"/>
<input type="hidden" value="${ccmDevice.password}" id="ccmDevicePassword"/>
<!--摄像机类型  -->
<input type="hidden" value="${ccmDevice.typeVidicon}" id="ccmDeviceTypeVidicon"/>
<!-- 协议 -->
<input type="hidden" value="${ccmDevice.protocol}" id="ccmDeviceProtocol"/>
<!-- 设备参数信息-->
<input type="hidden" value='${ccmDevice.param}' id="ccmDeviceParaml"/>

<!-- 监控设备类型 -->
<input type="hidden" value="${ccmDevice.type}" id="ccmDeviceCameraType">


<div>
	<div id="divPlugin"  style="overflow: hidden;width:100%;height:100%;">
		<c:if test="${ccmDevice.typeVidicon == 2}">
			<OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" id="vlc"     width="560" height="340" id="vlc" events="True">
				<param name='mrl' value='${ccmDevice.param}' />
				<param name='volume' value='50' />
				<param name='autoplay' value='true' />
				<param name="toolbar" value="true" />
				<param name='loop' value='true' />
				<param name='fullscreen' value='false' />
			</object>
		</c:if>
		<c:if test="${ccmDevice.typeVidicon == 1}">
			<div class="form" style="display: none">
		        <label for="PalyType">PalyType:</label>
		        <br />
		        <input type="text" class="PalyType text" value="PlayReal" />
		        <br />
		        <label for="SvrIp">SvrIp:</label>
		        <br />
		        <input type="text" class="SvrIp text" value="" />
		        <br />
		        <label for="SvrPort">SvrPort:</label>
		        <br />
		        <input type="text" class="SvrPort text" value="443" />
		        <br />
		        <label for="appkey">appkey:</label>
		        <br />
		        <input type="text" class="appkey text" value="" />
		        <br />
		        <label for="appSecret">appSecret:</label>
		        <br />
		        <input type="text" class="appSecret text" />
		        <br />
		        <label for="time">time:</label>
		        <br />
		        <input type="text" class="time text" />
		        <br />
		        <label for="timeSecret">timeSecret:</label>
		        <br />
		        <input type="text" class="timeSecret text" />
		        <br />
		        <label for="httpsflag">httpsflag:</label>
		        <br />
		        <input type="text" class="httpsflag text" value="1" />
		        <br />
		        <label for="CamList">CamList:</label>
		        <br />
		        <input type="text" class="CamList text" value="" />
		        <br />
		        <input type="submit" class="playBtn" value="视频预览播放" />
		    </div>
			<div class="video-position" style="width: 99%; height:99%;">
		        <p class="pop" style="display:block">加载中</p>
		        <input type="hidden" name="config" id="config" value="ReqType:PlayReal;wndcount:1" />
		        <!-- 添加预览控件（需要先在windows下注册） -->
		        <object classid="CLSID:7E393848-7238-4CE3-82EE-44AF444B240A" id="PlayViewOCX" wmode="opaque" width="0" height="0" name="PlayViewOCX">
		        </object>
	        </div>
		</c:if>
		<c:if test="${ccmDevice.typeVidicon == 4}">

		<div class="clearfix" style="overflow: hidden">

			<div class="video-position" style="width: 600px;height: auto; position: relative;padding: 14px 0 0 0; float: left">
		        <p class="pop" style="display:block">加载中</p>
		        <input type="hidden" name="config" id="config" value="ReqType:PlayReal;wndcount:1" />
		        <!-- 添加预览控件（需要先在windows下注册） -->
		        <object classid="clsid:02EFF2E9-3D57-461F-BDAC-7A598239E53C" id="PlayViewOCX" wmode="opaque" width="0" height="0" name="PlayViewOCX">
		        </object>
	        </div>


			<div id="vidioDiv" style="float: left; width: 250px;">

				<ul class="clearfix" style="width: 210px; margin: 0px auto;">
					<li onclick="PtzControl(11,1,1,100)" style="float:left ;width: 30px;height: 30px;padding: 10px;margin-left: 45px;margin-top: 5px">
						<img src="${ctxStatic}/common/index/images/white_ZoomOut_hover.png" style="width:30px;height:30px;" title="放大">
					</li>
					<li onclick="PtzControl(12,1,1,100)" style="float:left ;width: 30px;height: 30px;padding: 10px;margin-left: 18px;margin-top: 2px">
						<img src="${ctxStatic}/common/index/images/white_ZoomIn_hover.png" style="width:30px;height:30px;" title="缩小">
					</li>
				</ul>

				<ul class="clearfix" style="width: 210px; margin: 90px auto;">
					<li onmousedown="PtzControl(25,1,1,0)" onmouseup="PtzControl(25,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/left_up.png" style="width:50px;height:50px;" title="左上">
					</li>
					<li onmousedown="PtzControl(21,1,1,0)" onmouseup="PtzControl(21,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/up.png" style="width:50px;height:50px;" title="向上">
					</li>
					<li onmousedown="PtzControl(26,1,1,0)" onmouseup="PtzControl(26,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/right_up.png" style="width:50px;height:50px;" title="右上">
					</li>
					<li onmousedown="PtzControl(23,1,1,0)" onmouseup="PtzControl(23,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/left.png" style="width:50px;height:50px;" title="向左">
					</li>
					<li onmousedown="PtzControl(29,1,1,0)" onmouseup="PtzControl(29,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/auto.png" style="width:50px;height:50px;" title="自动">
					</li>
					<li onmousedown="PtzControl(24,1,1,0)" onmouseup="PtzControl(24,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/right.png" style="width:50px;height:50px;" title="向右">
					</li>
					<li onmousedown="PtzControl(27,1,1,0)" onmouseup="PtzControl(27,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/left_down.png" style="width:50px;height:50px;" title="左下">
					</li>
					<li onmousedown="PtzControl(22,1,1,0)" onmouseup="PtzControl(22,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/down.png" style="width:50px;height:50px;" title="向下">
					</li>
					<li onmousedown="PtzControl(28,1,1,0)" onmouseup="PtzControl(28,0,0,0)" style="float: left;width: 50px;height: 50px;padding: 9px">
						<img src="${ctxStatic}/common/index/images/right_down.png" style="width:50px;height:50px;" title="右下">
					</li>
				</ul>

			</div>


		</div>

		</c:if>
	</div>
</div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"  type="text/javascript"></script>
<script>

	/* ***********************控制摄像头************************/

	//PTZ控制
	function PtzControl(cmd,param, start, power){


		if(cmd == 8 || cmd == 9 || cmd == 39){
			var paramInfo = "{\"cmd\":"+cmd+",\"param\":"+param+",\"start\":"+start+",\"power\":"+power+"}";
			var ret = document.getElementById("PlayViewOCX").PtzControl(paramInfo);
			var showInfo = " 调用PtzControl    返回信息: " + ret;
			showMessage(showInfo);
			if (ret != 0) {
				alert("PTZ控制失败！");
				return;
			}
		}
		else{
			var paramInfo = "{\"cmd\":"+cmd+",\"param\":"+param+",\"start\":"+start+",\"power\":"+power+"}";
			var ret = document.getElementById("PlayViewOCX").PtzControl(paramInfo);
			var showInfo = " 调用PtzControl    返回信息: " + ret;
			showMessage(showInfo);
			if (ret != 0) {
				alert("PTZ控制失败！");
				return;
			}
		}
	}

$(function(){

	/*var layerSize = ['860px','400px']

	super.layerSize = layerSize;*/

	var ccmDeviceTypeVidicon=$('#ccmDeviceTypeVidicon').val()//摄像机类型
	var ccmDeviceIp=$('#ccmDeviceIp').val()//ip
	var ccmDevicePort=$('#ccmDevicePort').val()//端口
	var ccmDeviceAccount=$('#ccmDeviceAccount').val()//用户名
	var ccmDevicePassword=$('#ccmDevicePassword').val()//密码
	var ccmDeviceProtocol=$('#ccmDeviceProtocol').val()//协议
	var ccmDeviceParaml=$('#ccmDeviceParaml').val()//设备参数信息
	var ccmDeviceCameraType=$('#ccmDeviceCameraType').val()//监控设备类型  0枪机  1球机  2半球机




	if(ccmDeviceTypeVidicon==2){
/* 	    var  mainOcxHtml = '	<video id="videoElement" class="video-js vjs-default-skin vjs-big-play-centered" controlspreload="auto" width="570" height="340"> </video>';
	    document.getElementById('divPlugin').innerHTML = mainOcxHtml;
		LivePlayerInit();//初始化
		videoPlay(ccmDeviceProtocol, ccmDeviceParaml) */
	/*var  mainOcxHtml='';
			mainOcxHtml+='<OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" id="vlc"   codebase="./axvlc.cab"    width="1140" height="640" id="vlc" events="True">';
			mainOcxHtml+='<param name="mrl" value="'+ccmDeviceParaml+'" />';
			mainOcxHtml+='<param name="volume" value="50" />'
			mainOcxHtml+='<param name="autoplay" value="true" />'
			mainOcxHtml+='<param name="toolbar" value="true" />'
			mainOcxHtml+='<param name="loop" value="true" />'
			mainOcxHtml+='<param name="fullscreen" value="false" />';
			mainOcxHtml+='</object>';
			document.getElementById('divPlugin').innerHTML = mainOcxHtml;*/
	}else if(ccmDeviceTypeVidicon==1){
		//*****************海康视频OCX播放方式**************//
		//延迟初始化
	    $(document).ready(function () {
	        setTimeout(function () {
				init();
	            
	        }, 50); //这里设置延迟是为了正确加载OCX(取决于电脑性能,具体数值请根据实际情况设定,通常不需要修改 直接调用init()是可行的)
	        setTimeout(function () {
	            $('#PlayViewOCX').css({
	                'width': '100%',
	                'height': '100%'
	            });
	            $('.pop').hide();
	            var noCache = Date();
				$.getJSON('/arjccm/app/rest/video/callApiGetSecurity',{"noCache": noCache},function(data){
			          var appKey=data.result.appKey;
			           var SvrIp=data.result.svrIp;
			           var SvrPort=data.result.svrPort;
			           var appKeyEncrypt=JSON.parse(data.result.appKeyEncrypt);
			           var appSecret=appKeyEncrypt.data.appSecret;
			           var time=appKeyEncrypt.data.time;
			           var timeSecret=appKeyEncrypt.data.timeSecret;
		
			           $('.SvrIp').val(SvrIp);
			           $('.SvrPort').val(SvrPort);
			           $('.appkey').val(appKey);
			           $('.appSecret').val(appSecret);
			           $('.time').val(time);
			           $('.timeSecret').val(timeSecret);
			           $('.CamList').val("${ccmDevice.code}");
			         setTimeout(function () {
				              $('.playBtn').click();
	              }, 1000);
			        

	            })
	        }, 500);//这里设置延迟(数值请根据实际情况来)是防止快速刷新页面导致进程残留  具体清楚进程方式请参考<关闭进程 云台控制>demo中的代码
	    });
	    //初始化
	    function init() {
	        var OCXobj = document.getElementById("PlayViewOCX");
	        var txtInit = $("#config").val();
	        OCXobj.ContainOCX_Init(txtInit);
	    }
	    //提交按钮
	    $('.playBtn').on('click', function () {
	        var PalyType = $('.PalyType').val();
	        var SvrIp = $('.SvrIp').val();
	        var SvrPort = $('.SvrPort').val();
	        var appkey = $('.appkey').val();
	        var appSecret = $('.appSecret').val();
	        var time = $('.time').val();
	        var timeSecret = $('.timeSecret').val();
	        var httpsflag = $('.httpsflag').val();
	        var CamList = $('.CamList').val();
	        var param = 'ReqType:' + PalyType + ';' + 'SvrIp:' + SvrIp + ';'+'WndCount: 1'+';' + 'SvrPort:' + SvrPort + ';' + 'Appkey:' + appkey + ';' + 'AppSecret:' + appSecret + ';' + 'time:' + time + ';' + 'timesecret:' + timeSecret + ';' + 'httpsflag:' + httpsflag + ';' + 'CamList:' + CamList + ';';
	        //如果初始化传了WndCount值 这里也需要传 如demo中设置了WndCount:1  这里也要传WndCount:1  如果使用默认四窗口则可以不传

	        play_ocx_do(param);

	    });
	  


        ////OCX控件视频处理函数
        function play_ocx_do(param) {
            if ("null" == param || "" == param || null == param || "undefined" == typeof param) {
                return;
            } else {
                var OCXobj = document.getElementById("PlayViewOCX");
                OCXobj.ContainOCX_Do(param);
            }
        }
        
      
          // 关闭浏览器
		    $(window).unload(function () {
			
		    	  var param = 'hikvideoclient://VersionTag:artemis;Exit:1;';
		          play_ocx_do(param);
		    });
		//*****************海康视频OCX播放方式**************//
		
		
		
		//*****************海康视频**************//
		/*  var iRet = WebVideoCtrl.I_CheckPluginInstall();
		    if (-1 == iRet) {
		        alert("您还未安装过插件，双击开发包目录里的WebComponentsKit.exe安装！");
		        return;
		    }
            
		    var oPlugin = {
		        iWidth: 560,             // plugin width
		        iHeight: 342             // plugin height
		    };

		    var oLiveView = {
		        iProtocol: 1,            // protocol 1：http, 2:https
		        szIP: ccmDeviceIp,    // protocol ip
		        szPort: ccmDevicePort,            // protocol port
		        szUsername: ccmDeviceAccount,     // device username
		        szPassword: ccmDevicePassword, // device password
		        iStreamType: 1,          // stream 1：main stream  2：sub-stream  3：third stream  4：transcode stream
		        iChannelID: 1,           // channel no
		        bZeroChannel: false      // zero channel
		    };
		        
		    // 初始化插件参数及插入插件
		    WebVideoCtrl.I_InitPlugin(oPlugin.iWidth, oPlugin.iHeight, {
		        bWndFull: true,//是否支持单窗口双击全屏，默认支持 true:支持 false:不支持
		        iWndowType: 1,
		        cbInitPluginComplete: function () {
		            WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
		            // 检查插件是否最新
		            if (-1 == WebVideoCtrl.I_CheckPluginVersion()) {
		                alert("检测到新的插件版本，双击开发包目录里的WebComponentsKit.exe升级！");
		                return;
		            }

		            // 登录设备
		            WebVideoCtrl.I_Login(oLiveView.szIP, oLiveView.iProtocol, oLiveView.szPort, oLiveView.szUsername, oLiveView.szPassword, {
		                success: function (xmlDoc) {
		                    // 开始预览
		                    var szDeviceIdentify = oLiveView.szIP + "_" + oLiveView.szPort;
		                    setTimeout(function () {
		                        WebVideoCtrl.I_StartRealPlay(szDeviceIdentify, {
		                            iStreamType: oLiveView.iStreamType,
		                            iChannelID: oLiveView.iChannelID,
		                            bZeroChannel: oLiveView.bZeroChannel
		                        });
		                    }, 1000);
		                }
		            });
		        }
		    });
		    
			
		    // 关闭浏览器
		    $(window).unload(function () {
		        WebVideoCtrl.I_Stop();
		    }); */
	}else if(ccmDeviceTypeVidicon==3){
		//*****************大华视频**************//
		var Sys = {};
		var ua = navigator.userAgent.toLowerCase();
		var s;
		(s = ua.match(/(msie\s|trident.*rv:)([\d.]+)/)) ? Sys.ie = s[2] :
		    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
		    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
		    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
		    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

		var hasPlugin = checkPlugins();

		var g_ocx; //控件对象，初始化完毕后，可以调用《二次开发使用 WEB32网页调用接口说明.doc》文档中的接口
		var g_PlayTime;
		var g_curSpeed = 4; //默认的正常速度
		var htmlStChn1 = '';
		var recInfosByFile = [];
	
		//加载插件到网页中去。
		loadPageOcx();
		
		//登录设备
		LoginDevice();
		RealPlay();
		
		/**
		 * 检测浏览器是否存在视频插件
		 * @return {Boolean}
		 */
		function checkPlugins() {
		    var PLUGINS_NAME = 'WebActiveEXE.Plugin.1';
		    var result;
		    if (Sys.ie) {
		        try {
		            result = new ActiveXObject(PLUGINS_NAME);
		            delete result;
		        } catch (e) {
		            return false;
		        }
		        return true;
		    } else {
		        navigator.plugins.refresh(false);
		        result = navigator.mimeTypes["application/media-plugin-version-3.1.0.2"];
		        return !!(result && result.enabledPlugin);
		    }
		}
		 function loadPageOcx() {
			    var mainOcxHtml = '';
			    if (Sys.ie) {
			        mainOcxHtml = '<object id="ocx" width="100%" height="100%" classid="CLSID:7F9063B6-E081-49DB-9FEC-D72422F2727F"></object>';
			    } else {
			        mainOcxHtml = '<object id="ocx" width="100%" height="100%" type="application/media-plugin-version-3.1.0.2" VideoWindTextColor="9c9c9c" VideoWindBarColor="414141"></object>';
			    }
			    document.getElementById('divPlugin').innerHTML = mainOcxHtml;
			    g_ocx = document.getElementById('ocx');
			}
		 function LoginDevice() {
			    var bRet = g_ocx.LoginDeviceEx(ccmDeviceIp, ccmDevicePort, ccmDeviceAccount, ccmDevicePassword, 0);
			    //登录后，默认四窗口显示。若需要自定义其他窗口数，可以调用g_ocx.SetWinBindedChannel
			    g_ocx.SetWinBindedChannel(1, 0, 0, 0); //这样调用可以切换为单窗口模式，参数意义详见《二次开发使用 WEB32网页调用接口说明.doc》
			    if (bRet == 0) {
			        //登录成功后
			    }
			}
		//实时监视
		function RealPlay() {
		//首先切换到监视模式
		g_ocx.SetModuleMode(1); //监视模式
		//打开通道视频
		g_ocx.ConnectRealVideo(0, 1);
		}
		//登出
		function LogoutDevice() {
		    g_ocx.LogoutDevice();
		}
		//关闭浏览器
	    $(window).unload(function () {
	    	LogoutDevice();
	    });
	}

	else if(ccmDeviceTypeVidicon==4){
		$(document).ready(function () {
			setTimeout(function () {
				init();
	        }, 50);
			setTimeout(function () {
			    $('#PlayViewOCX').css({
			        'width': '100%',
			        'height': '100%'
			    });
			    $('.pop').hide();
			    startVideo();
			}, 500);
		});
		function init() {
			var info = {};
			info.OcxType = 0;
			info.buttons =  [0, 1, 2, 3, 4];//需要加载的按钮
			info.menus = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];//需要加载的右键菜单
			info.diskplaninfo = {};
			info.otherinfo = {};
			info.authority = 1;
	        var OCXobj = document.getElementById("PlayViewOCX");
	        OCXobj.init(JSON.stringify(info));
	    }
		function startVideo(){
			var ret = document.getElementById("PlayViewOCX").StartVideo(ccmDeviceParaml);
		}





	}


	
})

</script>
</body>
</html>