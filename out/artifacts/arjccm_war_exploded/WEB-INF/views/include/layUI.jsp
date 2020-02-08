<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.table.layer thead td {
	font-weight: bold;
	text-decoration: none;
	background: #fff;
	padding: 5px 5px 4px 5px;
	white-space: nowrap;
}
.tBody td a{
 width:250px;
 overflow: hidden;
 white-space: nowrap;
 text-overflow:ellipsis;
 display: block;
}
.layui-tab-title li{
    min-width: 25px !important;
    padding:0px 7px !important;
}
.layui-tab-bar{
    display: none;
}
.circle {
    width: 20px;
    height: 20px;
    background: red;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
    border-radius: 10px;
    float:right;
    line-height: 20px;
    position: relative;
    left: -5px;
    bottom: -5px;
}
.circleEmpty {
    width: 8px;
    height: 20px;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
    border-radius: 10px;
    float:right;
    line-height: 20px;
    position: relative;
    left: -5px;
    bottom: -5px;
}
.circleEmptyLeft {
    width: 8px;
    height: 20px;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
    border-radius: 10px;
    float:left;
    line-height: 20px;
    position: relative;
    left: -5px;
    bottom: -5px;
}
</style>
<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
      rel="stylesheet" />
<script src="${ctxStatic}/web-speech/webSpeech.js"></script>
<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<link href="${ctxStatic}/layim/layui/css/layui.css" type="text/css"
      rel="stylesheet">
<script src="${ctxStatic}/layim/layui/layui.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element; //Tab的切换功能，
        // element.on('tab(allTab)', function(data){
        //     ClearData(data);
        // });
    });
    // function ClearData(data){
    //
    // }
	layer.ready(function() {
				var layerIndex1;
				var table = '';
				var contentAll = '';
				var content1 = '';
				var content2 = '';
				var content3 = '';
				var content4 = '';
				var countAll = 0,count1 = 0,count2 = 0,count3 = 0
				var noCache = Date();
				// $.getJSON(ctx+"/event/ccmEventIncident/getListToday",{ "noCache": noCache },
                $.getJSON(ctx+"/message/ccmMessage/getListTodayAndUnread",{ "noCache": noCache },
						function(data) {
                    console.info("data",data);
                            contentAll += '<table class="table layer"><tbody id="allTbody" class="tBody">';
                            content1 += '<table class="table layer"><tbody id="eventTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>';
                            content2 += '<table class="table layer"><tbody id="caseDealTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>';
                            content3 += '<table class="table layer"><tbody id="notifyTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>';
                            content4 += '<table class="table layer"><tbody id="workNotifyTbody" class="tBody">';
                            var noRead = false;
                            if(data.length>0){
                                for(var i=0;i<data.length;i++){
                                    // var year,month,day,hh,mm,ss;
                                    // var Date=data[i]["happenDate"].split(' ');
                                    // var Date1=Date[0].split('-');
                                    // var Date2=Date[1].split(':')
                                    // year=Date1[0];
                                    // month=Date1[1];
                                    // day=Date1[2];
                                    // hh=Date2[0];
                                    // mm=Date2[1];
                                    // ss=Date2[2];
                                    // var clock=year+'年'+month+'月'+day+'日'+hh+'时'+mm+'分'+ss+'秒';
                                    // console.log(clock)
                                    // content1 += '<tr><td><a title="'+clock+data[i]["caseName"]+'" onclick="re(\'' + data[i]["id"]
                                    //     + '\')" class="ccmEventLayer' + data[i]["id"] + ' masked"  attrid="'
                                    //     + data[i]["id"] + '" attpoint="' + data[i]["areaPoint"] + '" attname="'
                                    //     + data[i]["caseName"] + '" > '
                                    //     + decodeURIComponent(data[i]["happenDate"]) + '：'
                                    //     + decodeURIComponent(data[i]["caseName"]) + '</a></td></tr>';
                                    if (data[i]["type"]=="01"){
                                        if(content1 == '<table class="table layer"><tbody id="eventTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>'){
                                            content1 = '<table class="table layer"><tbody id="eventTbody" class="tBody">';
                                        }
                                        var name=decodeURIComponent(data[i]["caseName"]);
                                        var content=decodeURIComponent(data[i]["content"]);
                                        var readFlag = "";
                                        if(data[i]["readFlag"]=="0"){
                                            count1 += 1;
                                            noRead = true;
                                            readFlag = "masked";
                                        }
                                        // 生成新的文字插入对话框中
                                        var html = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickEvent(\'' + data[i]["objId"]
                                            + '\')" class="ccmEventLayer' + data[i]["objId"] + ' '+readFlag +' active"  attrid="'
                                            + data[i]["objId"] + '" attname="'
                                            + data[i]["caseName"] + '" > ' + content + '</a></td></tr>';
                                        content1 += html;
                                        contentAll += html;
                                    }else if(data[i]["type"]=="02"||data[i]["type"]=="04"){
                                        if(content2 == '<table class="table layer"><tbody id="caseDealTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>'){
                                            content2 = '<table class="table layer"><tbody id="caseDealTbody" class="tBody">';
                                        }
                                        var content=decodeURIComponent(data[i]["content"]);
                                        var readFlag = "";
                                        if(data[i]["readFlag"]=="0"){
                                            count2 += 1;
                                            noRead = true;
                                            readFlag = "masked";
                                        }
                                        var html = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickEventCaseDeal(\'' + data[i]["objId"]
                                            + '\')" class="ccmEventLayer' + data[i]["objId"] + ' '+readFlag +' active" > ' + content + '</a></td></tr>';
                                        content2 += html;
                                        contentAll += html;
                                    }else if(data[i]["type"]=="03"){
                                        if(content3 == '<table class="table layer"><tbody id="notifyTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>'){
                                            content3 = '<table class="table layer"><tbody id="notifyTbody" class="tBody">';
                                        }
                                        var content=decodeURIComponent(data[i]["content"]);
                                        var readFlag = "";
                                        if(data[i]["readFlag"]=="0"){
                                            count3 += 1;
                                            noRead = true;
                                            readFlag = "masked";
                                        }
                                        var html = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickNotify(\'' + data[i]["objId"]
                                            + '\')" class="ccmEventLayer' + data[i]["objId"] + ' '+readFlag +' active" > ' + content + '</a></td></tr>';
                                        content3 += html;
                                        contentAll += html;
                                    }else if(data[i]["type"]=="13"){
                                        if(content3 == '<table class="table layer"><tbody id="notifyTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>'){
                                            content3 = '<table class="table layer"><tbody id="notifyTbody" class="tBody">';
                                        }
                                        var content=decodeURIComponent(data[i]["content"]);
                                        var readFlag = "";
                                        if(data[i]["readFlag"]=="0"){
                                            count3 += 1;
                                            noRead = true;
                                            readFlag = "masked";
                                        }
                                        var html = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickLeaveRequest(\'' + data[i]["objId"]
                                            + '\')" class="ccmEventLayer' + data[i]["objId"] + ' '+readFlag +' active" > ' + content + '</a></td></tr>';
                                        content3 += html;
                                        contentAll += html;
                                    }else if(data[i]["type"]=="23"){
                                        if(content3 == '<table class="table layer"><tbody id="notifyTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>'){
                                            content3 = '<table class="table layer"><tbody id="notifyTbody" class="tBody">';
                                        }
                                        var content=decodeURIComponent(data[i]["content"]);
                                        var readFlag = "";
                                        if(data[i]["readFlag"]=="0"){
                                            count3 += 1;
                                            noRead = true;
                                            readFlag = "masked";
                                        }
                                        var html = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickVisit(\'' + data[i]["objId"]
                                            + '\')" href="javascript:void(0);" class="ccmEventLayer' + data[i]["objId"] + ' '+readFlag +' active" > ' + content + '</a></td></tr>';
                                        content3 += html;
                                        contentAll += html;
                                    }else if(data[i]["type"]=="33"){
                                        if(content3 == '<table class="table layer"><tbody id="notifyTbody" class="tBody"><tr class="def"> <td>暂无新的消息</td></tr>'){
                                            content3 = '<table class="table layer"><tbody id="notifyTbody" class="tBody">';
                                        }
                                        var content=decodeURIComponent(data[i]["content"]);
                                        var readFlag = "";
                                        if(data[i]["readFlag"]=="0"){
                                            count3 += 1;
                                            noRead = true;
                                            readFlag = "masked";
                                        }
                                        var html = '<tr><td style="text-align: left;"><a title="'+content+'" onclick="clickreport(\'' + data[i]["objId"]
                                            + '\')" href="javascript:void(0);" class="ccmEventLayer' + data[i]["objId"] + ' '+readFlag +' active" > ' + content + '</a></td></tr>';
                                        content3 += html;
                                        contentAll += html;
                                    }
                                }
                                countAll = count1 + count2 + count3;
                            } else{
                                contentAll += '<tr class="def"> <td>暂无新的消息</td></tr>';
                                // content1 += '<tr class="def"> <td>暂无新的消息</td></tr>';
                                // content2 += '<tr class="def"> <td>暂无新的消息</td></tr>';
                                // content3 += '<tr class="def"> <td>暂无新的消息</td></tr>';
                            }
                            contentAll += '</tbody></table>';
                            content1 += '</tbody></table>';
                            content2 += '</tbody></table>';
                            content3 += '</tbody></table>';
                            content4 += '</tbody></table>';
							// 内容
                            table += '<div class="layui-tab">';
                            table += '<ul class="layui-tab-title">';
                            if(countAll!='0'){
                            	table += '<li class="layui-this">全部 <span class="circle" id="countAll">'+countAll+'</span></li>';
                            }else{
                            	table += '<li class="layui-this"><span class="circleEmptyLeft"></span>全部 <span class="circleEmpty"></span></li>';
                            }
                            
                            if(count1!='0'){
                            	table += '<li>事件 <span class="circle" id="count1">'+count1+'</span></li>';
                            }else{
                            	table += '<li><span class="circleEmptyLeft"></span>事件 <span class="circleEmpty"></span></li>';
                            }
                            
                            if(count2!='0'){
                            	table += '<li>指派 <span class="circle" id="count2">'+count2+'</span></li>';
                            }else{
                            	table += '<li><span class="circleEmptyLeft"></span>指派 <span class="circleEmpty"></span></li>';
                            }
                            
                            if(count3!='0'){
                            	table += '<li>消息 <span class="circle" id="count3">'+count3+'</span></li>';
                            	table += '<li>公告 </li>';
                            }else{
                            	table += '<li><span class="circleEmptyLeft"></span>消息 <span class="circleEmpty"></span></li>';
                            	table += '<li><span class="circleEmptyLeft"></span>公告 <span class="circleEmpty"></span></li>';
                            }
                            table += '</ul>';
                            table += '<div class="layui-tab-content">';
                            // 全部
                            table += '<div class="layui-tab-item layui-show">'+contentAll+'</div>';
                            // 事件
                            table += '<div class="layui-tab-item">'+content1+'</div>';
                            // 指派
                            table += '<div class="layui-tab-item">'+content2+'</div>';
                            // 通知
                            table += '<div class="layui-tab-item">'+content3+'</div>';
                            // 公告
                            table += '<div class="layui-tab-item">'+content4+'</div>';
                            table += '</div>';
                            table += '</div>';

							layerIndex1 = layer.open({
								type : 1,
								id:'TodayEvent',
								title : '今日消息',
								maxmin : true,
								shade : 0,
								closeBtn : 0,
								// offset : 'lb',
                                offset : 'rb',
								area : [ '300px', '500px' ],
								content : table,
								//最小化按钮的回调
								min : function(layero) {
									layero.css({
										left : 'auto',
										bottom : '0',
										right : '0',
										top : 'auto'
									})
								},
								restore : function(layero) {
									layero.css({
										left : 'auto',
										bottom : '0',
										right : '0',
										top : 'auto'
									})
								}
							});
							if(noRead){
                                layer.restore(layerIndex1);
                            }else{
                                layer.min(layerIndex1);
                            }
                            $.getJSON(ctx+"/work/ccmWorkNotice/messageList",{ "noCache": noCache,"pageSize":10 },
                                function(data) {
                                    if(data.list.length>0){
                                        var workNotice = "";
                                        for(var i=0;i<data.list.length;i++){
                                            workNotice += '<tr><td style="text-align: left;"><a title="公告：'+data.list[i]["title"]+'" onclick="clickWorkNotify(\'' + data.list[i]["id"]
                                                + '\')" class="ccmEventLayer' + data.list[i]["id"] +' active" > ' +"公告：" +data.list[i]["title"] + '</a></td></tr>';
                                        }
                                        $("#workNotifyTbody").html(workNotice);
                                        // if($("#allTbody").html() !=  '<tr class="def"> <td>暂无新的消息</td></tr>'){
                                        //     $("#allTbody").after(workNotice);
                                        // }else{
                                        //     $("#allTbody").html(workNotice);
                                        // }
                                    }else{
                                        $("#workNotifyTbody").html("<tr class=\"def\"> <td>暂无新的消息</td></tr>");
                                    }

                                });
						}
					);
				
			});

	function re(id) {
		// 左侧栏隐藏
		$('#left,#openClose').hide();
		// 右侧撑开
		$("#right").width($("#content").width() - 5);
		// 导航栏去除所有的选中标识
		$("#menu li").removeClass("active");
		// 第一个添加 选中显示
		$("ul#menu li:first").addClass("active");
		// 获取对象
		var a1 = $(".ccmEventLayer" + id);
		var aId = a1.attr("attrid");
		var aPoint = a1.attr("attpoint");
		var aName = a1.attr("attname");
		var aTitle=a1.attr('title');
		a1.removeClass('active')//去掉闪光字体
		// 添加显示
		$('#mainFrame').attr(
				'src',
				'${ctx}/sys/layer/layMap?id=' + aId + '&areaPoint=' + aPoint
						+ '&caseName=' + encodeURI(aName));
		 // speak(aTitle)
		  
		$("iframe[id='mainFrame']").on("load",function(){
			  
			  $(window.parent.document).contents().find("#mainFrame")[0].contentWindow.checkVideoNode(); 
	        }); 
	}
    function clickEvent(id) {
        $.ajax({
            type: "get",
            url: ctx + "/event/ccmEventIncident/getById",
            data:{
                id:id
            },
            success: function (data) {
                // 左侧栏隐藏
                $('#left,#openClose').hide();
                // 右侧撑开
                $("#right").width($("#content").width() - 5);
                // 导航栏去除所有的选中标识
                $("#menu li").removeClass("active");
                // 第一个添加 选中显示
                $("ul#menu li:first").addClass("active");
                // 获取对象
                var a1 = $(".ccmEventLayer" + id);
                // var aId = a1.attr("attrid");
                // var aPoint = a1.attr("attpoint");
                // var aName = a1.attr("attname");
                // var aTitle=a1.attr('title');
                if(a1.hasClass('masked')){
                    a1.removeClass('active')//去掉闪光字体
                    a1.removeClass('masked')//去掉闪光字体
                    var countAll = $("#countAll").text();
                    $("#countAll").text(parseInt(countAll)-1);
                    var count1 = $("#count1").text();
                    $("#count1").text(parseInt(count1)-1);
                }
                // 添加显示
                $('#mainFrame').attr(
                    'src',
                    '${ctx}/sys/layer/layMap?id=' + data.id + '&areaPoint=' + data.areaPoint
                    + '&caseName=' + encodeURI(data.caseName));
                // speak(aTitle)

                $("iframe[id='mainFrame']").on("load",function(){

                    $(window.parent.document).contents().find("#mainFrame")[0].contentWindow.checkVideoNode();
                });
            }
        });
    }
    function clickEventCaseDeal(id) {
        // 左侧栏隐藏
        //$('#left,#openClose').hide();
        // 右侧撑开
        //$("#right").width($("#content").width() - 5);
        // 导航栏去除所有的选中标识
        //$("#menu li").removeClass("active");
        // 第一个添加 选中显示
        //$("ul#menu li:first").addClass("active");
        // 获取对象
        var a1 = $(".ccmEventLayer" + id);
        if(a1.hasClass('masked')) {
            a1.removeClass('active')//去掉闪光字体
            a1.removeClass('masked')//去掉闪光字体
            var countAll = $("#countAll").text();
            $("#countAll").text(parseInt(countAll) - 1);
            var count2 = $("#count2").text();
            $("#count2").text(parseInt(count2) - 1);
        }
        layer.open({
			type: 2, //类型，解析url
			closeBtn: 1, //关闭按钮是否显示 1显示0不显示
			title: '指派', //页面标题
			shadeClose: true, //点击遮罩区域是否关闭页面
			shade: 0.8,  //遮罩透明度
			area: ['1000px', '600px'],  //弹出层页面比例
			content: '${ctx}/event/ccmEventCasedeal/form?id='+id  //弹出层的url
		});
    }
    function clickNotify(id) {
        // 左侧栏隐藏
        //$('#left,#openClose').hide();
        // 右侧撑开
        //$("#right").width($("#content").width() - 5);
        // 导航栏去除所有的选中标识
        //$("#menu li").removeClass("active");
        // 第一个添加 选中显示
        //$("ul#menu li:first").addClass("active");
        // 获取对象
        var a1 = $(".ccmEventLayer" + id);
        if(a1.hasClass('masked')) {
            a1.removeClass('active')//去掉闪光字体
            a1.removeClass('masked')//去掉闪光字体
            var countAll = $("#countAll").text();
            $("#countAll").text(parseInt(countAll) - 1);
            var count3 = $("#count3").text();
            $("#count3").text(parseInt(count3) - 1);
        }
        layer.open({
			type: 2, //类型，解析url
			closeBtn: 1, //关闭按钮是否显示 1显示0不显示
			title: '消息', //页面标题
			shadeClose: true, //点击遮罩区域是否关闭页面
			shade: 0.8,  //遮罩透明度
			area: ['1000px', '600px'],  //弹出层页面比例
			content: '${ctx}/oa/oaNotify/viewRead?id='+id  //弹出层的url
		}); 
    }
    function clickLeaveRequest(id) {
        // 左侧栏隐藏
        //$('#left,#openClose').hide();
        // 右侧撑开
        //$("#right").width($("#content").width() - 5);
        // 导航栏去除所有的选中标识
        //$("#menu li").removeClass("active");
        // 第一个添加 选中显示
        //$("ul#menu li:first").addClass("active");
        // 获取对象
        var a1 = $(".ccmEventLayer" + id);
        if(a1.hasClass('masked')) {
            a1.removeClass('active')//去掉闪光字体
            a1.removeClass('masked')//去掉闪光字体
            var countAll = $("#countAll").text();
            $("#countAll").text(parseInt(countAll) - 1);
            var count3 = $("#count3").text();
            $("#count3").text(parseInt(count3) - 1);
        }
        layer.open({
			type: 2, //类型，解析url
			closeBtn: 1, //关闭按钮是否显示 1显示0不显示
			title: '消息', //页面标题
			shadeClose: true, //点击遮罩区域是否关闭页面
			shade: 0.8,  //遮罩透明度
			area: ['1000px', '600px'],  //弹出层页面比例
			content: '${ctx}/attendanceapply/ccmWorkerAttendanceApply/form?type=2&id='+id  //弹出层的url
		}); 
    }
    function clickreport(id) {
        // 左侧栏隐藏
        //$('#left,#openClose').hide();
        // 右侧撑开
        //$("#right").width($("#content").width() - 5);
        // 导航栏去除所有的选中标识
        $("#menu li").removeClass("active");
        // 第一个添加 选中显示
        $("ul#menu li:first").addClass("active");
        // 获取对象
        var a1 = $(".ccmEventLayer" + id);
        if(a1.hasClass('masked')) {
            a1.removeClass('active')//去掉闪光字体
            a1.removeClass('masked')//去掉闪光字体
            var countAll = $("#countAll").text();
            $("#countAll").text(parseInt(countAll) - 1);
            var count3 = $("#count3").text();
            $("#count3").text(parseInt(count3) - 1);
        }
        layer.open({
			type: 2, //类型，解析url
			closeBtn: 1, //关闭按钮是否显示 1显示0不显示
			title: '消息', //页面标题
			shadeClose: true, //点击遮罩区域是否关闭页面
			shade: 0.8,  //遮罩透明度
			area: ['1000px', '600px'],  //弹出层页面比例
			content: '${ctx}/sys/ccmWorkReport/view?id='+id  //弹出层的url
		}); 
    }
    function clickVisit(id) {
        // 获取对象
        var a1 = $(".ccmEventLayer" + id);
        if(a1.hasClass('masked')) {
            a1.removeClass('active')//去掉闪光字体
            a1.removeClass('masked')//去掉闪光字体
            var countAll = $("#countAll").text();
            $("#countAll").text(parseInt(countAll) - 1);
            var count3 = $("#count3").text();
            $("#count3").text(parseInt(count3) - 1);
        }
    }

    function clickWorkNotify(id) {
        // 左侧栏隐藏
        //$('#left,#openClose').hide();
        // 右侧撑开
        //$("#right").width($("#content").width() - 5);
        // 导航栏去除所有的选中标识
        //$("#menu li").removeClass("active");
        // 第一个添加 选中显示
        //$("ul#menu li:first").addClass("active");
        // 获取对象
        var a1 = $(".ccmEventLayer" + id);
        a1.removeClass('active')//去掉闪光字体
        layer.open({
			type: 2, //类型，解析url
			closeBtn: 1, //关闭按钮是否显示 1显示0不显示
			title: '公告', //页面标题
			shadeClose: true, //点击遮罩区域是否关闭页面
			shade: 0.8,  //遮罩透明度
			area: ['1000px', '600px'],  //弹出层页面比例
			content: '${ctx}/work/ccmWorkNotice/form?id='+id  //弹出层的url
		}); 
    }
</script>