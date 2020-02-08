//实时警情
var stateCacle = "",
map;
var beginAlarmDate, endAlarmDate;
var pageSize = 11; // 每页条数
var currentPage = 1; // 当前页数
var pageCount = 1; // 总页数
var CacleLocationAlarmId = [];
$(document).ready(function() {
    if ($.cookie('querySon') == undefined) {
        $.cookie('querySon', false);
    }
    initMap();
    // 初始化案事件类型
    getAlarmType();
    // 获取警情列表
    var state = "";
    getAlarm(state);
    refreshPoliceTree();
    $('.VivaTimeline').vivaTimeline();
    plotDrawInit(); // 标绘初始化
    init();
});
function getAlarm(state) {
    if (state === undefined) {
        state = '';
    }
    // 缓存获取当前警情状态
    stateCacle = state;
    var beginAlarmTime = today() + " 00:00:00";
    beginAlarmDate = beginAlarmTime;
    var endAlarmTime = today() + " 23:59:59";
    endAlarmDate = endAlarmTime;
    pageCountInit(state, beginAlarmTime, endAlarmTime);
}
// 当前警情列表总条数
function pageCountInit(state, beginAlarmTime, endAlarmTime) {
    // 清空警情列表
    /*
	 * $('#all-message').html(''); $('#not-handle').html('');
	 * $('#has-signIn').html(''); $('#handling').html('');
	 * $('#has-handle').html('');
	 */
    // 解决点击切换流加载内容，需要多次flow.load，page混乱
    $('#all-message').remove();
    $('#not-handle').remove('');
    $('#has-signIn').remove('');
    $('#handling').remove('');
    $('#has-handle').remove('');
    var elemH = "all-message";
    if (state == '') {
        elemId = "all-message";
    } else if (state == '0') {
        elemId = "not-handle";
    } else if (state == '1') {
        elemId = "has-signIn";
    } else if (state == '2') {
        elemId = "handling";
    } else if (state == '3') {
        elemId = "has-handle";
    }
    $('#slides').append('<ul id="' + elemId + '" class="slide active"></ul>');
    // 清空警情定位
    /*
    * var len=CacleLocationAlarmId.length; if(len>0){ for(i in
    * CacleLocationAlarmId){
    * Map.removeLayer('alarm_'+CacleLocationAlarmId[i]);//清除警情定位 } }
    */
    Map.removeLayer('alarms');
    locationPFeatures = [];
    CacleLocationAlarmId = [];
    // 是否查询下级
    var incSubsetFlag = $.cookie('querySon');
    var param = {};
    param.state = state;
    param.beginAlarmTime = beginAlarmTime;
    param.endAlarmTime = endAlarmTime;
    param.incSubset = incSubsetFlag;
    $.post(ctx + '/alarm/bphAlarmInfo/findCount', param, function(data) {
        var data = JSON.parse(data);
        var Num = Number(data);
        pageCount = Math.ceil(Num / pageSize);
        FlowData(state, beginAlarmTime, endAlarmTime);
    });
}
// 流体加载警情列表数据
var locationPFeatures = [];
function FlowData(state, beginAlarmTime, endAlarmTime) {
    var elemId = "#all-message";
    if (state == '') {
        elemId = "#all-message";
    } else if (state == '0') {
        elemId = "#not-handle";
    } else if (state == '1') {
        elemId = "#has-signIn";
    } else if (state == '2') {
        elemId = "#handling";
    } else if (state == '3') {
        elemId = "#has-handle";
    }
    locationPFeatures = [];
    layui.use('flow',
    function() {
        var flow = layui.flow;
        flow.load({
            elem: elemId // 流加载容器
            ,
            scrollElem: elemId // 滚动条所在元素，一般不用填，此处只是演示需要。
            ,
            done: function(page, next) { // 执行下一页的回调
                // 模拟数据插入
                setTimeout(function() {
                    stateCacle = state; // 当前状态
                    var incSubsetFlag = $.cookie('querySon'); // 是否查询下级
                    var param = {};
                    param.state = state;
                    param.beginAlarmTime = beginAlarmTime;
                    param.endAlarmTime = endAlarmTime;
                    param.pageSize = pageSize;
                    param.currentPage = page;
                    param.incSubset = incSubsetFlag;
                    $.post(ctx + "/alarm/bphAlarmInfo/queryHisAlarmSituation", param, function(datas) {
                        Map.removeLayer('alarms');
                        var data = JSON.parse(datas);
                        var lis = [];
                        var newAlarmDateIdArr = sessionStorage.newAlarmDateId.split(',');
                        var len = data.length;
                        if (len > 0) {
                            for (var i = 0; i < len; i++) {
                                var html = '';
                                var newHtml = '';
                                // 新消息
                                if ($.inArray(data[i].id, newAlarmDateIdArr) != -1) {
                                    newHtml += '<b class="label label-danger" style="float:left;background: #f13f40; padding: 1px 5px; border-radius: 4px; color: #fff;margin-top: 4px;">新</b>'
                                };
                                var displayFlag = 'none';
                                var alarmType = '1';
                                var alarmTypeText = '重大警情';
                                if (data[i].isImportant == '1') { // 是否为重大警情
                                    displayFlag = 'inline';
                                    alarmType = '0';
                                    alarmTypeText = '普通警情';
                                } else {
                                    displayFlag = 'none';
                                    alarmType = '1';
                                    alarmTypeText = '重大警情';
                                }
                                html += '<li class="location_' + data[i].id + '" data-id="' + data[i].id + '" xNum="' + data[i].x + '" yNum="' + data[i].y + '" data-data="' + JSON.stringify(data[i]).replace(/"/g, '&quot;') + '" >';
                                html += '<div class="header">';
                                html += '<div class="clearfix">';
                                html += '<h6 class="place" title="' + data[i].place + '" style="float:left">' + data[i].place + '</h6><b class="" style="float:left;background: #538eda; padding: 1px 2px; border-radius: 4px; color: #fff;margin-top: 4px;">' + ArjAlarmType[data[i].typeCode] + '</b>'+ newHtml;
                                html += '</div>';
                                html += '<p class="clearfix">';
                                html += '<span class="cont" title="' + data[i].content + '">' + data[i].content + '</span><b class="major" style="background: #f13f40; padding: 3px; border-radius: 4px; color: #fff;display:' + displayFlag + '">重大</b>';
                                html += '</p>';
                                html += '<div class="date-time clearfix">';
                                html += '<p>';
                                html += '<b>报警人：</b> <i>' + data[i].manName + '</i>';
                                html += '</p>';
                                html += '<p>' + getDay(data[i].alarmTime.time) + '</p>';
                                html += '</div>';
                                html += '<span class="status state-' + data[i].state + '"></span> ';
                                html += '<span class="location-click" data-id="' + data[i].id + '"  onclick="locationP(this)"></span>';
                                html += '<span class="arrow-cut" onclick="_toggleDetails(this)"></span>';
                                html += '</div>';
                                html += '<div class="details" data-id="' + data[i].id + '">';
                                html += '<span class="defend" type="alarm-details" data-id="' + data[i].id + '" onclick="getAlarmDetails(this)">警情详情</span> ';
                                html += '<span class="secondary-location" type="alarm-location" data-id="' + data[i].id + '" onclick="locationP_er(this)">二次定位</span>';
                                html += '<span class="nearby-search" type="alarm-search" data-id="' + data[i].id + '" onclick="nearSearch(this)">周边查询</span>';
                                html += '<span class="common-alarm" type="alarm-type" data-id="' + data[i].id + '" alarm-type="' + alarmType + '" onclick="alarmTypeChange(this)">' + alarmTypeText + '</span> ';
                                html += '<span class="relation" type="alarm-relation" data-id="' + data[i].id + '" data-isImportant="' + data[i].isImportant + '" alarm-type="' + data[i].typeCode + '" onclick="planAssociated(this)"> 预案关联 </span>';
                                // html += '<span class="intelligent-dispatch"
                                // type="alarm-dispatch" data-id="'+data[i].id+'"
                                // onclick="controlDialog(this)">智能布控</span>';
                                html += '<span class="analysis" data-id="' + data[i].id + '" onclick="findHandleLog(this)" type="alarm-analysis">处置分析</span> ';
                               /* html += '<span class="file" data-id="' + data[i].id + '" data-place = "' + data[i].place + '" data-type="alarm-file" onclick="alarmkArchive(this)">事后归档</span>';*/
                                html += '</div>';
                                html += '</li>';
                                lis.push(html);
                                // 警情定位
                                CacleLocationAlarmId.push(data[i].id);

                                locationPFeatures.push({
                                    "type": "Feature",
                                    "id": data[i].id,
                                    "properties": {
                                        "name": data[i].place,
                                        "icon": 'alarm_' + data[i].state + '.png',
                                        'type': 'alarm',
                                        // 自定义type类型,判断是警情，添加周边查询
                                        "info": {
                                            '接警单编号': data[i].orderNum,
                                            '案发地点': data[i].place,
                                            '报警时间': getDay(data[i].alarmTime.time),
                                            '接警员姓名': data[i].policeName,
                                            '警情类型': ArjAlarmType[data[i].typeCode],
                                            '警情状态': stateChange(data[i].state),
                                            '报警人姓名': data[i].manName,
                                            '报警人性别': sexChange(data[i].manSex),
                                            '报警人电话': data[i].manTel,
                                            '报警方式': alarmForm(data[i].alarmFrom),
                                            '是否为重大警情': isImportantChange(data[i].isImportant),
                                           /* '接警录音': '<audio src="' + data[i].alarmRecord + '" preload="auto" controls></audio>',*/
                                            '警情内容': '<span style="display:block;width:220px;height:60px;cursor:pointer;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;white-space: normal;" title="' + data[i].content + '">' + data[i].content + '</span>',
                                        }
                                    },
                                    "geometry": {
                                        "type": "Point",
                                        "coordinates": [data[i].x, data[i].y]
                                    }
                                })
                            }
                            locationPInfo(locationPFeatures);
                        }
                        next(lis.join(''), page < pageCount); // 假设总页数为 10
                    });
                },
                500);
            }
        });
    });
}

// 智能布控弹框
function setControl(html, title, width, height, offsetWidth, offsetHeight, affirm, cancel, id) {
    layer.open({
        type: 1,
        title: title,
        area: [width, height],
        offset: [offsetWidth, offsetHeight],
        maxmin: false,
        btn: [affirm, cancel],
        // /可以无限个按钮
        content: html,
        cancel: function() {},
        end: function() {},
        yes: function(index, layero) {
            var escapeTime = $('#escapeTime').val();
            var runWayVal = $('#runWay').val();
            var x = $('.location_' + id).attr('xNum');
            var y = $('.location_' + id).attr('yNum');
            if (runWayVal == '') {
                layer.msg('请选择逃跑方式');
                return false;
            }
            var radius = Number(escapeTime) * Number(runWayVal);
            var center = [Number(x), Number(y)];
            Map.removeLayer('riceDrawVector'); // 清除范围图层
            Map.showCircleFromPointDefult(center, radius, false); // 添加范围图层
            layer.close(index); // 如果设定了yes回调，需进行手工关闭
        }
    });
}

//预案关联
function planAssociated(_this) {
    var isImportant = $(_this).attr('data-isImportant');
    var typeCode = $(_this).attr('alarm-type');
    var alarmId = $(_this).attr('data-id'); // 警情id
    $.post(ctx + '/planinfo/bphPlanInfo/planAssociated', {'isImportant': isImportant, 'typeCode': typeCode}, function(data) {
        var planData = JSON.parse(data);
        if (planData === undefined || planData == null || planData == "") {
            layer.msg('没有此案情关联预案');
            return;
        }
        if (planData.length == 1) {
            var planId = planData[0].id;
            TiaoZhuan(alarmId, planId);
        } else if (planData.length > 1) {
            planAssociatedData(alarmId, planData);
        }
    })
}

// 跳转页面
function TiaoZhuan(alarmId, planId) {
    if (typeof(Storage) !== "undefined") {
        sessionStorage.alarmId = alarmId;
        sessionStorage.planId = planId;
    }
    parent.$('#menu li').each(function() {
        if ($(this).children('a').attr('data-id') == '5c05053e82004e6786ddfe758a18013b') {
            $(this).children('a').children('span').click();
        }
    })
}

function planAssociatedData(alarmId, planData) {
    var html = "";
    html += '<div>';
    html += '<table class="table table-striped table-bordered table-condensed">';
    html += '<thead>';
    html += '<tr>';
    html += '<th>选择</th>';
    html += '<th>预案名称</th>';
    html += '<th>警情级别</th>';
    html += '<th>警情类型</th>';
    html += '</tr>';
    html += '</thead>';
    html += '<tbody>';
    for (var i = 0; i < planData.length; i++) {
        // html+='<span class="intelligent-dispatch" style="margin-left: 60px;"
        // type="alarm-dispatch" data-id="'+planData[i].id+'"
        // onclick="TiaoZhuan()">'+planData[i].name+'</span>';
        html += '<tr onclick="labelClick(\'' + planData[i].id + '\')" style="cursor: pointer;">';
        html += '<td style="text-align: center;"><div class="radio radio-success" style="display: inline-block;"><input type="radio" id="' + planData[i].id + '" value="' + planData[i].id + '" name="plantype" ><label for="' + planData[i].id + '"></lable></div></td>';
        html += '<td>' + planData[i].name + '</td>';
        html += '<td>' + isImportantChangeVal(planData[i].isImportant) + '</td>';
        html += '<td>' + ArjAlarmType[planData[i].typeCode] + '</td>';
        html += '</tr>';
    }
    html += '</tbody>';
    html += '</table>';
    html += '</div>';
    layer.open({
        type: 1,
        title: "预案关联",
        area: ["400px", "250px"],
        offset: ["0px", "304px"],
        btn: ["确定", "取消"],
        //
        maxmin: false,
        content: html,
        cancel: function() {},
        end: function() {},
        yes: function(index, layero) {
            var planId = $('input[name="plantype"]:checked').val();
            if (planId == '' || planId == undefined) {
                layer.msg('请选择预案');
                return false
            }
            TiaoZhuan(alarmId, planId);
            layer.close(index);
        }
    });
}
function labelClick(id) {
    $('#' + id).prop('checked', true);
}
// 智能布控
function controlDialog(_this) {
    var id = $(_this).attr('data-id');
    var data = JSON.parse($('.location_' + id).attr('data-data'));
    var alarmTime = data.alarmTime.time;
    var now = new Date($.ajax({
        async: false
    }).getResponseHeader("Date"));
    var escapeTime = parseInt(now - alarmTime) / 1000 / 60;
    $("#alarmTime").text(getDay(alarmTime));
    $('#escapeTime').val(Math.round(escapeTime));
    var html = $('#setControlDialog');
    var title = "智能布控";
    var width = "310px";
    var height = "235px";
    var offsetWidth = "10px";
    var offsetHeight = "310px";
    var affirm = "生成";
    var cancel = "取消";
    $.getJSON(ctx + '/sys/dict/listData?type=escape_way',
    function(data) {
        var laneSelectOptions = $("#runWay")[0].options;
        for (var i in laneSelectOptions) {
            laneSelectOptions.remove(1);
        }
        for (var i in data) {
            var optionItem = new Option(data[i].label, data[i].value);
            laneSelectOptions.add(optionItem);
        }
    });
    setControl(html, title, width, height, offsetWidth, offsetHeight, affirm, cancel, id);
}

function initMap() {
    // 地图默认数据设置
    var defaultPrams = {
        id: 'map',
        centerCoordinate: centerCoordinate,
        zoom: zoomIndex,
        maxZoom: maxZoom,
        minZoom: minZoom,
        baseUrl: baseUrlT,
        zoomShowOrHide: false,
        // 缩小放大
        overviewMap: false,
        selectPointerFlag: true
        // 鹰眼图
    }
    Map = new ArjMap.Map(defaultPrams);
    // 加载地图
    Map.init();
    map = Map.map;
}

function seaDialog() {
    //var html = '<div style="margin-left:15px;">开始时间：<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="" style="width:160px;height:30px;margin-top:10px;margin-left:10px;margin-right:10px;" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',isShowClear:false});"></div><div  style="margin-left:15px;">结束时间：<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="" style="width:160px;height:30px;margin-top:10px;margin-left:10px;margin-right:10px;" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',isShowClear:false});"></div>'; // 捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	var html = '<div class="layui-inline"><label class="layui-form-label" style="width:100px;">开始时间：</label><div class="layui-input-inline"><input name="beginCreateDate" id="beginCreateDate" type="text" class="layui-input" style="margin-top: 5px;"></div></div><div class="layui-inline"><label class="layui-form-label" style="width:100px;">结束时间：</label><div class="layui-input-inline"><input name="endCreateDate" id="endCreateDate" type="text" class="layui-input" style="margin-top: 5px;"></div></div>';
    html += '<div class="checkbox checkbox-success radio-div tab-content-bg" style="margin-left: 15px;margin-top: 10px;">';
    html += '<input type="checkbox" name="query" value="1" class="type-input" id="querySon">';
    html += '<label for="querySon" style="padding:0;width:100px;">是否查询下级</label>';
    html += '</div>';
    var title = "历史警情";
    var width = "305px";
    var height = "230px";
    var offsetWidth = "10px";
    var offsetHeight = "310px";
    var affirm = "查询";
    var cancel = "取消";
    layerData(html, title, width, height, offsetWidth, offsetHeight, affirm, cancel);
    // 给时间框赋默认值
    $("#beginCreateDate").val(today() + " 00:00:00");
    $("#endCreateDate").val(today() + " 23:59:59");

    var isQuerySon = $.cookie('querySon');
    if (isQuerySon == 'true') {
        $('#querySon').attr('checked', true);
    } else {
        $('#querySon').attr('checked', false);
    }

    $('#querySon').change(function() {
        if ($(this).is(':checked')) {
            $.cookie('querySon', true);
        } else {
            $.cookie('querySon', false);
        }
    })
    layui.use('laydate', function(){
  	    var laydate = layui.laydate;
  	    //日期时间选择器
  	    laydate.render({
  	    	elem: '#beginCreateDate'
  	    	,type: 'datetime'
  	    });
  	    laydate.render({
  		    elem: '#endCreateDate'
  			,type: 'datetime'
  	    });
  	});
}
// 警情详情
function getAlarmDetails(_this) {
    var id = $(_this).attr('data-id');
    $.post(ctx + "/alarm/bphAlarmInfo/alarmDetail", {
        'id': id
    },
    function(datas) {
        var data = datas.alarmInfo;
        var alarmHandleList = datas.alarmHandleList;
        var alarmDetail = '';
        if (data != '') {
            alarmDetail += '<table class="table table-striped table-bordered table-condensed">';
            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">接警单编号：</td><td>' + data.orderNum + '</td>';
            alarmDetail += '</tr>';
            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">案发地点：</td><td><span style="display:block;width:220px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;" title="' + data.place + '">' + data.place + '</span></td>';
            alarmDetail += '</tr>';
            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">报警时间：</td><td>' + getDay(data.alarmTime) + '</td>';
            alarmDetail += '</tr>';
            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">警情状态：</td><td id="alarmStateTd"></td>';
            alarmDetail += '</tr>';
            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">所属区域：</td><td><input id="areaId" areaIdValue="' + (data.area ? data.area.id: "") + '" type="text" style="width:220px;" value="' + (data.area ? data.area.name: "") + '"/><a id="areaButton" href="javascript:" class="btn" style="">&nbsp;<i class="icon-search"></i>&nbsp;</a></td>';
            alarmDetail += '</tr>';
            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">报警电话：</td><td><input id="manTel" type="text" style="width:220px;" value="' + data.manTel + '" onmouseleave="normalImg(this)" /><p id="titleNull"  style="display:none;"><span class="help-inline"><font color="red">*请输入正确的电话号码</font> </span></p></td>';
            alarmDetail += '</tr>';
/*            alarmDetail += '<tr>';
            alarmDetail += '<td style="text-align: right;">接警录音：</td><td><audio src="' + data.alarmRecord + '" preload="auto" controls></audio></td>';
            alarmDetail += '</tr>';*/
            alarmDetail += '<tr>';
            // alarmDetail += '<td>接警录音号：</td><td><input id="alarmRecord"
            // type="text" style="width:220px;height:25px;"
            // value="'+data.alarmRecord+'"/></td>';
            alarmDetail += '<td style="height: 60px;text-align: right;">警情内容：</td><td><span style="display:block;width:370px;height:60px;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;" title="' + data.content + '">' + data.content + '</span></td>';
            alarmDetail += '</tr>';
            alarmDetail += '</table>';
            alarmDetail += '<span style="padding-left: 20px;">处警详情</span>';
            alarmDetail += '<table class="table table-striped table-bordered table-condensed" style="margin-top: 10px;">';
            alarmDetail += '<tr>';
            alarmDetail += '<td>处警人姓名</td>';
            // alarmDetail += '<td>任务描述</td>';
            // alarmDetail += '<td>反馈信息</td>';
            alarmDetail += '<td>处置状态</td>';
            alarmDetail += '<td>执行预案</td>';
            alarmDetail += '<td>执行过程</td>';
            alarmDetail += '<td>执行动作</td>';
            alarmDetail += '</tr>';
            if (alarmHandleList !== undefined && alarmHandleList.length > 0) {
                for (var i = 0; i < alarmHandleList.length; i++) {
                    alarmDetail += '<tr>';
                    alarmDetail += '<td>' + alarmHandleList[i].user.name + '</td>';
                    // alarmDetail += '<td>'+alarmHandleList[i].task+'</td>';
                    // alarmDetail +=
                    // '<td>'+alarmHandleList[i].handleResult+'</td>';
                    if (alarmHandleList[i].status == '0') {
                        alarmDetail += '<td><span class="major" style="background: #ec4c6c; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">未处理</span></td>';
                    } else if (alarmHandleList[i].status == '1') {
                        alarmDetail += '<td><span class="major" style="background: #f9d04b; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">已签收</span></td>';
                    } else if (alarmHandleList[i].status == '2') {
                        alarmDetail += '<td><span class="major" style="background: #5cb1ea; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">已到达</span></td>';
                    } else if (alarmHandleList[i].status == '3') {
                        alarmDetail += '<td><span class="major" style="background: #258233; padding:1px 3px; border-radius: 4px;margin-left:5px; color: #fff;display:inline">已反馈</span></td>';
                    } else {
                        alarmDetail += '<td><span></span></td>';
                    }
                    if (alarmHandleList[i].plan !== undefined && alarmHandleList[i].plan != null && alarmHandleList[i].plan != "") {
                        alarmDetail += '<td>' + alarmHandleList[i].plan.name + '</td>';
                    } else {
                        alarmDetail += '<td></td>';
                    }
                    if (alarmHandleList[i].step !== undefined && alarmHandleList[i].step != null && alarmHandleList[i].step != "") {
                        alarmDetail += '<td>' + alarmHandleList[i].step.name + '</td>';
                    } else {
                        alarmDetail += '<td></td>';
                    }
                    if (alarmHandleList[i].action !== undefined && alarmHandleList[i].action != null && alarmHandleList[i].action != "") {
                        alarmDetail += '<td>' + alarmHandleList[i].action.name + '</td>';
                    } else {
                        alarmDetail += '<td></td>';
                    }
                    alarmDetail += '</tr>';
                }
            }
            alarmDetail += '</table>';
        } else {
            layer.msg('暂无警情详情');
            return false;
        }

        layer.open({
            skin: 'layui-layer-lan',
            type: 1,
            title: "警情详情",
            area: ["500px", "540px"],
            offset: ["100px", "740px"],
            maxmin: false,
            btn: ["保存", "取消"],
            //
            content: alarmDetail,
            cancel: function() {},
            end: function() {},
            yes: function(index, layero) {
                var manName = $('#manName').val(); // 报警人姓名
                var manSex = $('input[name="manSex"]:checked').val(); // 报警人性别
                var manTel = $('#manTel').val(); // 报警人联系电话
                var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
                if (!myreg.test(manTel)) {
                    document.getElementById("titleNull").style.display = "";
                    return false;
                } else {
                    document.getElementById("titleNull").style.display = "none";
                }
                var z = $("#z").val(); // 高度
                var typeCode = $('#alarmType').val(); // 警情类型
                var areaId = $("#areaId").attr("areaIdValue"); // 所属区域
                var alarmFrom = $("#alarmFrom").val(); // 报警方式
                var content = $('#cont').val(); // 警情内容
                var place = $('#place').val(); // 案发地点
                var json = {
                    'id': id,
                    'manName': manName,
                    'manSex': manSex,
                    'manTel': manTel,
                    'z': z,
                    'typeCode': typeCode,
                    'area.id': areaId,
                    'alarmFrom': alarmFrom,
                    'content': content,
                    'place': place,
                    'optionDesc': '修改警情信息'
                };
                // var param = encodeURI(JSON.stringify(json)); //转换成json格式
                updateAlarmDetails(json);
                layer.close(index); // 如果设定了yes回调，需进行手工关闭
            }
        });
        $(".layui-layer").css("border-radius", "10px");
        $(".layui-layer-title").css("border-radius", "10px 10px 0 0");
        // 初始化audio
        audioInit();
        $('input[name="manSex"]').each(function() {
            if ($(this).val() == data.manSex) {
                $(this).attr('checked', true)
            }
        })

        // 警情类型下拉框
        var alarmTypeSelectNode = $("<select id='alarmType'></select>");
        // 警情类别下拉框
        var alarmClassSelectNode = $("<select id='alarmClass'></select>");
        $("#alarmTypeTd").append(alarmTypeSelectNode);
        $.getJSON(ctx + '/sys/dict/listData?type=bph_alarm_info_typecode',
        function(datas) {
            for (var i = 0; i < datas.length; i++) {
                if (data.typeCode == datas[i].value) {
                    alarmTypeSelectNode.append("<option data-id='" + datas[i].id + "' value='" + datas[i].value + "' selected='selected'>" + datas[i].label + "</option>");
                } else {
                    alarmTypeSelectNode.append("<option data-id='" + datas[i].id + "' value='" + datas[i].value + "'>" + datas[i].label + "</option>");
                }
            }
            var parentId = $("#alarmType").children('option:selected').attr("data-id");
            $.getJSON(ctx + '/sys/sysDict/getDictListByParentId?type=bph_alarm_info_class&parentId=' + parentId,
            function(datas) {
                for (var i = 0; i < datas.length; i++) {
                    if (data.genreCode == datas[i].value) {
                        alarmClassSelectNode.append("<option value='" + datas[i].value + "' selected='selected'>" + datas[i].label + "</option>");
                    } else {
                        alarmClassSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
                    }
                }
            });
            $("#alarmClassTd").append(alarmClassSelectNode);
        })

        $("#alarmType").change(function() {
            var alarmClassSelectOptions = $("#alarmClass")[0].options;
            for (var i in alarmClassSelectOptions) {
                alarmClassSelectOptions.remove(0);
            }
            var alarmTypeId = $(this).children('option:selected').attr("data-id");
            $.getJSON(ctx + '/sys/sysDict/getDictListByParentId?type=bph_alarm_info_class&parentId=' + alarmTypeId,
            function(datas) {
                for (var i = 0; i < datas.length; i++) {
                    alarmClassSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
                }
            });
        });

        // 所属区域树形
        $("#areaButton").on("click",
        function() {
            $("#areaSelectButton").trigger('click');
        });
        // 报警方式下拉框
        var alarmFromSelectNode = $("<select id='alarmFrom'></select>");
        $.getJSON(ctx + '/sys/dict/listData?type=bph_alarm_from',
        function(datas) {
            for (var i = 0; i < datas.length; i++) {
                if (data.alarmFrom == datas[i].value) {
                    alarmFromSelectNode.append("<option value='" + datas[i].value + "' selected='selected'>" + datas[i].label + "</option>");
                } else {
                    alarmFromSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
                }
            }
        });
        $("#alarmFromTd").append(alarmFromSelectNode);
        var alarmStateSelectNode = $("<select id='alarmState'></select>");
        $.getJSON(ctx + '/sys/dict/listData?type=bph_alarm_info_state',
        function(datas) {
            for (var i = 0; i < datas.length; i++) {
                if (data.state == datas[i].value) {
                    alarmStateSelectNode.append("<option value='" + datas[i].value + "' selected='selected'>" + datas[i].label + "</option>");
                } else {
                    alarmStateSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
                }
            }
        });
        $("#alarmStateTd").append(alarmStateSelectNode);
    });
}
// 验证鼠标移开事件
function normalImg(_this) {
    var manTel = $('#manTel').val(); // 报警人联系电话
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(manTel)) {
        document.getElementById("titleNull").style.display = "";
        return false;
    } else {
        document.getElementById("titleNull").style.display = "none";
    }
}

// 所属区域树形回调方法
function areaSelectTreeselectCallBack(v, h, f) {
    if (v == "ok") {
        var tree = h.find("iframe")[0].contentWindow.tree;
        var ids = [],
        names = [],
        nodes = [];
        nodes = tree.getSelectedNodes();
        for (var i = 0; i < nodes.length; i++) {
            $("#areaId").val(nodes[i].name);
            $("#areaId").attr("areaIdValue", nodes[i].id);
        }
    }
}
// 更新警情详情
function updateAlarmDetails(param) {
    $.post(ctx + "/alarm/bphAlarmInfo/updateAlarmInfo", param, function(data) {
        var result = JSON.parse(data);
        if (result.ret == 0) {
            layer.msg("修改警情详情成功!");
        } else {
            layer.msg("修改警情详情失败!");
        }
    });
}
// 周边查询
function nearSearch(_this) {
    // 隐藏信息窗口
    $('#popup-closer').click();
    var id = $(_this).attr('data-id');
    var x = $('.location_' + id).attr('xNum');
    var y = $('.location_' + id).attr('yNum');
    var html = $('#nearSearchDialog');
    var center = [Number(x), Number(y)];
    layer.open({
        type: 1,
        title: "周边查询",
        area: ["410px", "193px"],
        offset: ["1px", "300px"],
        maxmin: false,
        btn: ["查询", "取消"],
        content: html,
        cancel: function() {},
        end: function() {},
        yes: function(index, layero) {
            var type = "";
            $('input[name="layer"][type="checkbox"]').each(function() {
                type += $(this).val() + ',';
            });
            type = type.substring(0, type.length - 1);
            if (type == '0,0,0') {
                layer.msg('请选择图层');
                return false;
            }
            var radius = $('input[name="distance"][type="radio"]:checked').val();
            if (radius == "custom") {
                radius = $('#customKM').val();
                if (radius == '') {
                    layer.msg('请填写范围');
                    return false;
                }
            }
            Map.removeLayer('riceDrawVector'); // 清除范围图层
            Map.showCircleFromPointDefult(center, radius, true, id, type); // 添加范围图层
            var param = {
                'id': id,
                'x': x,
                'y': y,
                'radius': radius,
                'type': type
            };
            nearSearchQuery(param);
            layer.close(index); // 如果设定了yes回调，需进行手工关闭
            // 关闭实时警力 实时警车 视频
//            if ($("#videoDMS.toggle").hasClass("toggle--on")) {
//                $("#videoDMS.toggle").click();
//            }
//            if ($("#policeDMS.toggle").hasClass("toggle--on")) {
//                $("#policeDMS.toggle").click();
//            }
//            if ($("#carDMS.toggle").hasClass("toggle--on")) {
//                $("#carDMS.toggle").click();
//            }
        }
    });
}
// 周边查询结果
function nearSearchQuery(param) {
    $(".police .result").click();
    $.post(ctx + '/flat/realtimeSituation/findCircumData', {
        'param': JSON.stringify(param)
    },
    function(data) {
        var data = JSON.parse(data);
        var features = [],
        carFeatures = [],
        peopleFeatures = [];
        if (data[0].carData) {
            var carData = JSON.parse(data[0].carData);
            var carDataLen = carData.length;
            // 警车结果树形
            var carNodes = []
            if (carDataLen > 0) {
                for (var i = 0; i < carDataLen; i++) {
                    carFeatures.push({
                        "type": "Feature",
                        "id": carData[i].devId,
                        "properties": {
                            "name": carData[i].devName,
                            "icon": 'c1.png',
                            'type': 'vlc',
                            // 自定义type类型,判断是有视频流
                            "info": {
                                '名称': carData[i].devName,
                                '经度': carData[i].x,
                                '维度': carData[i].y,
                            }
                        },
                        "geometry": {
                            "type": "Point",
                            "coordinates": [carData[i].x, carData[i].y]
                        }
                    })

                    carNodes.push({
                        "id": carData[i].devId,
                        "name": carData[i].devName,
                        "pId": "-1",
                        'x': carData[i].x,
                        'y': carData[i].y,
                        'code': carData[i].code,
                        'devType': carData[i].devType,
                    })

                }
            }
            $.fn.zTree.init($('#carResult'), settingResultCarDev, carNodes);
        }
        var carPoint = {
            "type": "FeatureCollection",
            "features": carFeatures
        }
        Map.removeLayer('jingche');
        Map.addJSON1([{
            'type': 'DanDian',
            'id': 'jingche',
            'data': carPoint,
            'isShow': true
        }]);

        var peopleData = data[0].peopleDataList;
        var peopleDataLen = peopleData.length;
        // 警员结果树形
        var peopleNodes = [];
        if (peopleDataLen > 0) {
            for (var i = 0; i < peopleDataLen; i++) {
                peopleFeatures.push({
                    "type": "Feature",
                    "id": peopleData[i].userId,
                    "properties": {
                        "name": peopleData[i].name,
                        "icon": 'p1.png',
                        "type": "people",
                        // 警力类型有自定义type类型,判断是有视频流people,包含vlc
                        "info": {
                            '姓名': peopleData[i].name,
                            '所在部门': peopleData[i].officeName,
                            '手机': peopleData[i].phone,
                            '电话': peopleData[i].mobile,
                            '关联设备': peopleData[i].policePhoneCode + '<br/>' + peopleData[i].interPhoneCode + '<br/>' + peopleData[i].actionRecoderCode,
                            '经度': peopleData[i].x,
                            '维度': peopleData[i].y,
                        }
                    },
                    "geometry": {
                        "type": "Point",
                        "coordinates": [peopleData[i].x, peopleData[i].y]
                    }
                });
                peopleNodes.push({
                    "id": peopleData[i].userId,
                    "name": peopleData[i].name,
                    "pId": "-1",
                    'x': peopleData[i].x,
                    'y': peopleData[i].y,
                    'code': peopleData[i].code,
                    'devType': peopleData[i].devType,
                })
            }
            $.fn.zTree.init($('#policeResult'), settingResultPople, peopleNodes);
        }
        var peoplePoint = {
            "type": "FeatureCollection",
            "features": peopleFeatures
        }
        Map.removeLayer('jingyuan');
        Map.addJSON1([{
            'type': 'PopLocation',
            'id': 'jingyuan',
            'data': peoplePoint,
            'isShow': true
        }]);
        var videoDelList = data[0].videoDelList;
        var videoDelListLen = videoDelList.length;
        if (videoDelListLen > 0) {
            // 视频结果树形
            var videoNodes = [];
            for (var i = 0; i < videoDelListLen; i++) {
                features.push({
                    "type": "Feature",
                    "id": videoDelList[i].id,
                    "properties": {
                        "name": videoDelList[i].name,
                        "icon": 'video.png',
                        'type': 'video',
                        // 自定义type类型,判断是视频监控
                        'video': {
                            "id": videoDelList[i].id,
                            'ip': videoDelList[i].ip,
                        },
                        "info": {
                            '设备名称': videoDelList[i].name,
                            '设备编号': videoDelList[i].code,
                            'IP地址': videoDelList[i].ip,
                            '设备地址': videoDelList[i].address,
                            '经度': videoDelList[i].x,
                            '维度': videoDelList[i].y,
                        }
                    },
                    "geometry": {
                        "type": "Point",
                        "coordinates": [videoDelList[i].x, videoDelList[i].y]
                    }
                });
                videoNodes.push({
                    "id": videoDelList[i].id,
                    "name": videoDelList[i].name,
                    "pId": "-1",
                    'x': videoDelList[i].x,
                    'y': videoDelList[i].y,
                    'code': videoDelList[i].code,
                    'typeId': videoDelList[i].typeId,
                    'companyId': videoDelList[i].companyId,
                    'coordinate': videoDelList[i].coordinate,
                    'typeVidicon': videoDelList[i].typeVidicon,
                    'account': videoDelList[i].account,
                    'gateway': videoDelList[i].gateway,
                    'imagePath': videoDelList[i].imagePath,
                    'ip': videoDelList[i].ip,
                    'mask': videoDelList[i].mask,
                    'password': videoDelList[i].password,
                    'port': videoDelList[i].port,
                    'protocol': videoDelList[i].protocol,
                    'proxy': videoDelList[i].proxy,
                    'remarks': videoDelList[i].remarks,
                    'sdkPort': videoDelList[i].sdkPort,
                    'status': videoDelList[i].status,
                    'channelNum': videoDelList[i].channelNum,
                    'address': videoDelList[i].address,
                })
            }
            $.fn.zTree.init($('#videoResult'), settingResultVideo, videoNodes);
        }
        var point = {
            "type": "FeatureCollection",
            "features": features
        }
        Map.removeLayer('videos'); // 清除范围图层
        Map.addJSON1([{
            'type': 'videos',
            'data': point,
            'isShow': true
        }])
    })
}
// 重大警情-普通警情
function alarmTypeChange(_this) {
    var id = $(_this).attr('data-id');
    var alarmType = $(_this).attr('alarm-type');
    var _details = $(_this).parent();
    var _li = _details.parent();
    var optionDesc = "";
    if (alarmType == '1') {
        optionDesc = "变更为重大警情";
    } else {
        optionDesc = "变更为普通警情";
    }
    $.get(ctx + '/alarm/bphAlarmInfo/updateAlarmInfo', {
        'id': id,
        'isImportant': alarmType,
        'optionDesc': optionDesc
    },
    function(data) {
        if (alarmType == '1') {
            $(_this).text('普通警情');
            $(_this).attr('alarm-type', '0');
            _li.find('.major').show();
            layer.msg('已标记为重大警情');
        } else {
            $(_this).text('重大警情');
            $(_this).attr('alarm-type', '1');
            _li.find('.major').hide();
            layer.msg('已标记为普通警情');
        }
    })
}
// 弹框方法
function layerData(html, title, width, height, offsetWidth, offsetHeight, affirm, cancel) {
    layer.open({
        type: 1,
        title: title,
        area: [width, height],
        offset: [offsetWidth, offsetHeight],
        maxmin: false,
        btn: [affirm, cancel],
        // /可以无限个按钮
        content: html,
        cancel: function() {},
        end: function() {},
        yes: function(index, layero) {
            queryByTime();
            layer.close(index); // 如果设定了yes回调，需进行手工关闭
        }
    });
}
function queryByTime() {
    var beginAlarmTime = $("#beginCreateDate").val();
    var endAlarmTime = $("#endCreateDate").val();
    beginAlarmDate = beginAlarmTime;
    endAlarmDate = endAlarmTime;
    if (beginAlarmTime == null || beginAlarmTime == "" || beginAlarmTime === undefined || endAlarmTime == null || endAlarmTime == "" || endAlarmTime === undefined) {
    	layer.msg("请选择查询时间段");
        return;
    }
    var beginDateNew = Date.parse(new Date(beginAlarmTime.replace(/-/g, "/")));
    var endDateNew = Date.parse(new Date(endAlarmTime.replace(/-/g, "/")));
    var time = endDateNew - beginDateNew;
    if (time < 0) {
        layer.msg("开始时间必须小于结束时间");
        return;
    }
    pageCountInit(stateCacle, beginAlarmTime, endAlarmTime);
}
var _toggleDetails = function(elmt) {
    $('.details').children('span').removeClass('header-cut-1');
    var _elmt = $(elmt);
    var _li = _elmt.parent().parent();
    var _details = _li.children('.details');
    $('.details').children('span').click(function() {
        $('.details').children('span').removeClass('header-cut-1');
        $(this).addClass('header-cut-1');
    });
    _li.siblings().children('.details').hide();
    _li.siblings().children('.header').children('.arrow-cut').removeClass('arrow-cut-up');
    _details.toggle();
    _elmt.toggleClass('arrow-cut-up');
};
// 警情定位
function locationP(_this) {
    // 隐藏信息窗口
    $('#popup-closer').click();
    var id = $(_this).attr('data-id');
    var x = $('.location_' + id).attr('xNum');
    var y = $('.location_' + id).attr('yNum');
    Map.goTo([Number(x), Number(y)]);
    return false;
}
// 警情定位详情
function locationPInfo(featuresData) {
    var isShow = true;
    // 警情开关
    if ($("#alarmDMS.toggle").hasClass("toggle--off")) {
        isShow = false;
    } else {
        isShow = true;
    }

    // var data=JSON.parse($('.location_'+id).attr('data-data'));
    var point = {
        "type": "FeatureCollection",
        "features": featuresData
    }
    Map.addJSON1([{
        "id": 'alarms',
        'type': 'alarm',
        'data': point,
        'isShow': isShow
    }])
}
// 警情二次定位
function locationP_er(_this) {
    locationP(_this);
    var id = $(_this).attr('data-id');
    var x = $('.location_' + id).attr('xNum');
    var y = $('.location_' + id).attr('yNum');
    Map.SecondlocationTwo(id, locationP_erPoint);
    return false;
}
// 二次定位提交更新点
function locationP_erPoint(point, id) {
    var id = id;
    var x = point[0];
    var y = point[1];
    var dataPoint = JSON.parse($('.location_' + id).attr('data-data'));
    $.get(ctx + '/alarm/bphAlarmInfo/updateAlarmInfo', {
        'id': id,
        'x': x,
        'y': y,
        'optionDesc': '二次定位'
    },
    function(data) {
        var dataPoint = JSON.parse(data);
        if (dataPoint.ret == "0") {
            Map['alarms'].getSource().getFeatures().forEach(function(feature) {
                if (feature.get('features')[0].getId() == id) {
                    // 更新坐标
                    feature.get('features')[0].getGeometry().setCoordinates([x, y]);
                    return false;
                }
            });
            Map.goTo([Number(x), Number(y)]);
            // 更新页面坐标
            $('.location_' + id).attr('xNum', x);
            $('.location_' + id).attr('yNum', y);
            layer.msg('定位成功');
        } else {
            layer.msg('定位失败');
        }
    })
}
// 处置分析
function findHandleLog(_this) {
    var id = $(_this).attr('data-id');
    var html = "";
    var title = "处置分析";
    var width = "1000px";
    var height = "618px";
    var offsetWidth = "80px";
    var offsetHeight = "450px";
    var cancel = "关闭";
    $.getJSON(ctx + '/alarmhandlelog/bphAlarmHandleLog/findHandleLog', {
        'alarmId': id
    },
    function(data) {
        var len = data.length;
        if (len >= 0) {
            var html = '<div class="container"><div class="row"><div class="col-md-12"><div class="VivaTimeline"><dl><dt>处置时间轴</dt>';
            for (var i = 0; i < len; i++) {
                if (i % 2 == 0) {
                    html += '<dd class="pos-left clearfix"><div class="circ"></div><div style="width: 170px;" class="time">' + (data[i].operateTime == undefined ? "": data[i].operateTime) + '</div><div class="events"><div class="events-header"><p>' + (data[i].user.name == undefined ? "": data[i].user.name) + ':' + (data[i].operateDesc == undefined ? "": data[i].operateDesc) + '</p></div></div></dd>';
                } else {
                    html += '<dd class="pos-right clearfix"><div class="circ"></div><div style="width: 170px;padding-right: 20px;margin-left: -170px;" class="time">' + (data[i].operateTime == undefined ? "": data[i].operateTime) + '</div><div class="events"><div class="events-header"><p>' + (data[i].user.name == undefined ? "": data[i].user.name) + ':' + (data[i].operateDesc == undefined ? "": data[i].operateDesc) + '</p></div></div></dd>';
                }
            }
            html += '</dl></div></div></div></div>';
            $('#findHandleLog').html(html);
            findHandle(html, title, width, height, offsetWidth, offsetHeight, cancel);
        }
    })
}

// 处置分析弹框
function findHandle(html, title, width, height, offsetWidth, offsetHeight, cancel) {
    layer.open({
        type: 1,
        title: title,
        area: [width, height],
        offset: [offsetWidth, offsetHeight],
        maxmin: false,
        btn: [cancel],
        // /可以无限个按钮
        content: html,
        cancel: function() {},
        end: function() {},
        yes: function(index, layero) {
            // queryByTime();
            layer.close(index); // 如果设定了yes回调，需进行手工关闭
        }
    });
}
// 指定标绘类型，开始绘制。
function plotDrawInit() {
    // 初始化标绘绘制工具，添加绘制结束事件响应
    plotDraw = new P.PlotDraw(map);
    plotDraw.on(P.Event.PlotDrawEvent.DRAW_END, onDrawEnd, false, this);

    // 设置标绘符号显示的默认样式
    var stroke = new ol.style.Stroke({
        color: '#FF0000',
        width: 2
    });
    var fill = new ol.style.Fill({
        color: 'rgba(0,255,0,0.4)'
    });
    var image = new ol.style.Circle({
        fill: fill,
        stroke: stroke,
        radius: 8
    });
    drawStyle = new ol.style.Style({
        image: image,
        fill: fill,
        stroke: stroke
    });

    // 绘制好的标绘符号，添加到FeatureOverlay显示。
    drawOverlay = new ol.layer.Vector({
        source: new ol.source.Vector()
    });
    drawOverlay.setStyle(drawStyle);
    drawOverlay.setMap(map);

    // 测距初始化
    Map.measureMapInit();
    // 框选查询初始化
    Map.selectQueryInit();
}

function activate(type) {
    plotDraw.activate(type);
};
function onDrawEnd(event) {
    var feature = event.feature;
    drawOverlay.getSource().addFeature(feature);
}
function clearAllGraphic() {
    Map.measureMapClear() // 清楚测绘
    drawOverlay.getSource().clear() // 清除标绘
    Map.removeLayer('videos'); // 清除范围图层
    Map.removeLayer('jingyuan');
    Map.removeLayer('jingche');
    Map.drawVector.getSource().clear() // 清除圈选查询
    Map.removeLayer('riceDrawVector'); // 清除范围图层
    // Map.removeLayer('alarms');//清除
    $('#policeResult').html(''); // 清除警力结果
    $('#carResult').html(''); // 清除警车结果
    $('#videoResult').html(''); // 清除视频结果
}
// 圈选查询
function boxSelectionDevice(data) {
    var param = {
        'id': '',
        'x': data.centerX,
        'y': data.centerY,
        'radius': data.radius,
        "type": "1,1,1"
    };
    nearSearchQuery(param)
}
// 事后归档
function alarmkArchive(_this) {
    var id = $(_this).attr('data-id');
    var place = $(_this).attr('data-place');
    window.location.href = ctx + '/export/exportWord/alarmExportWord?id=' + id + '&place=' + place;
}

// 右下角出警
function policeClick() {
    var strId = '';
    $('.userId').each(function() {
        var id = $(this).attr('userId');
        strId += id.substring(2, id.length) + ',';
    });
    if (strId == '') {
        layer.msg('请选择人员');
        return false;
    }
    strId = strId.substring(0, strId.length - 1);
    popUpAlarmBox(strId);
}

function popUpAlarmBox(userIds, pointAlarmId, alarmPopIndex) {
	var alarmId = '';
	if (pointAlarmId != null && pointAlarmId !== undefined && pointAlarmId != '') {
		alarmId = pointAlarmId;
	} else {
		alarmId = $('#slides li.active').attr('data-id');
	}
    if (alarmId == '' || alarmId == undefined) {
        layer.msg('请选择警情');
        return false;
    }
    $("#btncontrolpolice").attr("disabled", "disabled");
    var x = $('.location_' + alarmId).attr('xNum');
    var y = $('.location_' + alarmId).attr('yNum');
    $('#destinLocation').attr('data-id', alarmId);
    $('#destinyX').val(x);
    $('#destinyY').val(y);
    layer.close(alarmPopIndex);
    layer.open({
        type: 1,
        title: "出警信息安排",
        area: ["445px", "230px"],
        offset: ["0px", "1220px"],
        maxmin: false,
        shade: 0,
        anim: 0,
        btn: ["发送", "取消"],
        content: $('#policeData'),
        end: function() {
            $("#btncontrolpolice").removeAttr("disabled");
        },
        yes: function(index, layero) {
            send(userIds);
            layer.close(index); // 如果设定了yes回调，需进行手工关闭
            $("#btncontrolpolice").removeAttr("disabled");
            $('#dispatch').html('');
            var zTreeObj = $.fn.zTree.getZTreeObj("ztreePolice");
            zTreeObj.checkAllNodes(false);
        }
    });
}

function send(strId) {
    var destinyX = $('#destinyX').val();
    var destinyY = $('#destinyY').val();
    var alarmId = $('#slides li.active').attr('data-id');
    var task = $('#taskTextarea').val();
    var optionDesc = "发送出警信息";
    var param = {
        'type': 2,
        'userId': strId,
        'alarmId': alarmId,
        'task': task,
        'destinyX': destinyX,
        'destinyY': destinyY,
        'optionDesc': optionDesc
    }
    $.post(ctx + '/handle/bphAlarmHandle/sendAlarmInfo', param, function(data) {
    	if(data == "true"){
    		layer.msg('发送警情成功');
    	}else{
    		layer.msg('发送警情失败');
    	}
    });
}
// 警员绑定警情详情
function bindAlarmInfo(id) {
    var alarmHtml = '';
    $.ajax({
        type: "post",
        url: ctx + "/alarm/bphAlarmInfo/findByHandlePoliceId",
        async: false,
        data: {
            'handlePoliceId': id
        },
        success: function(data) {
            alarmHtml += '<div>关联警情</div>';
            alarmHtml += '<table id="BindAlarmInfoTable" class="layui-table">';
            alarmHtml += '<thead>';
            alarmHtml += '<tr>';
            alarmHtml += '<th>接警单号</th>';
            alarmHtml += '<th>警情内容</th>';
            alarmHtml += '<th>警情状态</th>';
            alarmHtml += '</tr>';
            alarmHtml += '</thead>';
            alarmHtml += '<tbody>';
            var data = JSON.parse(data);
            var len = data.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    alarmHtml += '<tr>';
                    alarmHtml += '<td title="' + data[i].orderNum + '" style="border: 1px solid rgb(230, 230, 230);height:39px;line-height:39px;">' + data[i].orderNum + '</td>';
                    alarmHtml += '<td title="' + data[i].content + '" style="border: 1px solid rgb(230, 230, 230);height:39px;line-height:39px;" ><div style="width:100px;  overflow:hidden; text-overflow:ellipsis; white-space:nowrap">' + data[i].content + '</div></td>';
                    alarmHtml += '<td style="border: 1px solid rgb(230, 230, 230);height:39px;line-height:39px;">' + handleStatusChange(data[i].handleStatus) + '</td>';
                    alarmHtml += '</tr>';
                }
            }
            alarmHtml += '</tbody>';
            alarmHtml += '</table>';
        }
    });
    return alarmHtml;
}
function handleStatusChange(handleStatus) {
    var html = '';
    if (handleStatus == '0') {
        html = '<span class="alarmstatus_0">未处理</span>';
    } else if (handleStatus == '1') {
        html = '<span class="alarmstatus_1">已签收</span>';
    } else if (handleStatus == '2') {
        html = '<span class="alarmstatus_2">已到达</span>';
    } else if (handleStatus == '3') {
        html = '<span class="alarmstatus_3">已反馈</span>';
    } else {
        html = '<span></span>';
    }
    return html
}
// 新警情报警
var newIdCacle = '',
newDataCacle = [];
function realTimeAlarmAlert(AlarmDate) {
    if ($.cookie('querySon') == undefined) {
        $.cookie('querySon', false);
    };
    // 是否查询下级
    var incSubsetFlag = $.cookie('querySon');
    $.post(ctx + "/alarm/bphAlarmInfo/queryHisAlarmSituation", {
        'state': '',
        'createDate': AlarmDate,
        'incSubset': incSubsetFlag
    },
    function(data) {
        var obj = JSON.parse(data);
        var len = obj.length;
        newDataCacle = obj;
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                var id = obj[i].id;
                var html = '';
                html += '<div id="NewAlert_' + id + '" class="NewAlert active">';
                html += '<h6 id="hOrgName">' + obj[i].place + '</h6>';
                html += '<p id="pAlarmDetail">' + obj[i].content + '</p>';
                html += '<div><p><b>报警人：</b><i id="iAlarmHuman">' + obj[i].manName + '</i></p><p id="pALarmTiming">' + getDay(obj[i].alarmTime.time) + '<span></span></p></div>';
                html += '</div>';
                if(sessionStorage.newAlarmDateId.split(',').indexOf(id)== -1){
                	sessionStorage.newAlarmDateId += id + ','
                }else{
                	return 
                }
            }
            $('.wrapper').append(html);
            setTimeout(function() {
                $('.NewAlert').remove();
                newDataHtml(newDataCacle)
            },
            3000);
            parent.$('#audioAlarm').trigger('play');
        }
    });
}
function newDataHtml(data) {
    var html = '';
    var oneFeatures = [];
    var len = data.length;
    if (stateCacle == '' || stateCacle == '0') {
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                var displayFlag = 'none';
                var alarmType = '1';
                var alarmTypeText = '重大警情';
                if (data[i].isImportant == '1') { // 是否为重大警情
                    displayFlag = 'inline';
                    alarmType = '0';
                    alarmTypeText = '普通警情';
                } else {
                    displayFlag = 'none';
                    alarmType = '1';
                    alarmTypeText = '重大警情';
                }
                html += '<li class="location_' + data[i].id + '" data-id="' + data[i].id + '" xNum="' + data[i].x + '" yNum="' + data[i].y + '" data-data="' + JSON.stringify(data[i]).replace(/"/g, '&quot;') + '" >';
                html += '<div class="header">';
                //html += '<h6 title="' + data[i].place + '">' + data[i].place + '</h6><b class="" style="float:left;background: #538eda; padding: 1px 5px; border-radius: 4px; color: #fff;margin-top: 4px;">' + ArjAlarmType[data[i].typeCode]  + '</b><b class="label label-danger" style="float:left;background: #f13f40; padding: 1px 5px; border-radius: 4px; color: #fff;margin-top: 4px;">新</b>';
                html += '<div class="clearfix">'; 
				html += '<h6 class="place" title="'+data[i].place+'" style="float:left;">' + data[i].place + '</h6><b class="" style="float:left;background: #538eda;padding: 1px 2px;border-radius: 4px;color: #fff;margin-top: 4px;">' + ArjAlarmType[data[i].typeCode] + '</b><b class="label label-danger" style="float:left;background: #f13f40; padding: 1px 5px; border-radius: 4px; color: #fff;margin-top: 4px;">新</b>';
				html += '</div>';
                html += '<p class="clearfix">';
                html += '<span class="cont" title="' + data[i].content + '">' + data[i].content + '</span><b class="major" style="background: #f13f40; padding: 3px; border-radius: 4px; color: #fff;display:' + displayFlag + '">重大</b>';
                html += '</p>';
                html += '<div class="date-time clearfix">';
                html += '<p>';
                html += '<b>报警人：</b> <i>' + data[i].manName + '</i>';
                html += '</p>';
                html += '<p>' + getDay(data[i].alarmTime.time) + '</p>';
                html += '</div>';
                html += '<span class="status state-' + data[i].state + '"></span> ';
                html += '<span class="location-click" data-id="' + data[i].id + '" onclick="locationP(this)"></span>';
                html += '<span class="arrow-cut" onclick="_toggleDetails(this)"></span>';
                html += '</div>';
                html += '<div class="details" data-id="' + data[i].id + '">';
                html += '<span class="defend" type="alarm-details" data-id="' + data[i].id + '" onclick="getAlarmDetails(this)">警情详情</span> ';
                html += '<span class="secondary-location" type="alarm-location" data-id="' + data[i].id + '" onclick="locationP_er(this)">二次定位</span>';
                html += '<span class="nearby-search" type="alarm-search" data-id="' + data[i].id + '" onclick="nearSearch(this)">周边查询</span>';
                html += '<span class="common-alarm" type="alarm-type" data-id="' + data[i].id + '" alarm-type="' + alarmType + '" onclick="alarmTypeChange(this)">' + alarmTypeText + '</span> ';
                html += '<span class="relation" type="alarm-relation" data-id="' + data[i].id + '" data-isImportant="' + data[i].isImportant + '" alarm-type="' + data[i].typeCode + '" onclick="planAssociated(this)"> 预案关联 </span>';
                //html += '<span class="intelligent-dispatch" type="alarm-dispatch" data-id="' + data[i].id + '" onclick="controlDialog(this)">智能布控</span>';
                html += '<span class="analysis" data-id="' + data[i].id + '" onclick="findHandleLog(this)" type="alarm-analysis">处置分析</span> ';
              /*  html += '<span class="file" data-id="' + data[i].id + '" data-place = "' + data[i].place + '" data- type="alarm-file" onclick="alarmkArchive(this)">事后归档</span>';*/
                html += '</div>';
                html += '</li>';

                locationPFeatures.push({
                    "type": "Feature",
                    "id": data[i].id,
                    "properties": {
                        "name": data[i].place,
                        "icon": 'alarm_' + data[i].state + '.png',
                        'type': 'alarm',
                        // 自定义type类型,判断是警情，添加周边查询
                        "info": {
                        	'接警单编号':data[i].orderNum,
		        			'案发地点':data[i].place,
		        			'报警时间':getDay(data[i].alarmTime.time),
		        			'接警员姓名':data[i].policeName,
		        			'警情类型':ArjAlarmType[data[i].typeCode],
		        			'警情状态':stateChange(data[i].state),
		        			'报警人姓名':data[i].manName,
		        			'报警人性别':sexChange(data[i].manSex),
		        			'报警人电话':data[i].manTel,
		        			'报警方式':alarmForm(data[i].alarmFrom),
		        			'是否为重大警情':isImportantChange(data[i].isImportant),
		        		/*	'接警录音':'<audio src="'+data[i].alarmRecord+'" preload="auto" controls></audio>',*/
		        			'警情内容':'<span style="display:block;width:220px;height:60px;cursor:pointer;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;white-space: normal;" title="'+data[i].content+'">'+data[i].content+'</span>',
                        }
                    },
                    "geometry": {
                        "type": "Point",
                        "coordinates": [data[i].x, data[i].y]
                    }
                })

            }
            Map.removeLayer('alarms');
            locationPInfo(locationPFeatures);
        }
        if (stateCacle == '') {
            $('#all-message').prepend(html);
        } else if (stateCacle == '0') {
            $('#not-handle').prepend(html);
        }
    }
}
var settingVideo = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: '0'
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
            var id = treeNode.id;
            if (treeNode.type != 'camera') {
                return;
            }
            videoLocation(id);
        }
    }
};
var settingResultVideo = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: '0'
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
            var id = treeNode.id;
            Map.goTo([Number(treeNode.x), Number(treeNode.y)]);
        }
    }
};
// 全市警力-视频
function refreshVideoTree() {
    $.getJSON(ctx + "/ccmsys/ccmLiveVideo/treeData",
    function(data) {
        $.fn.zTree.init($("#ztreeVideo"), settingVideo, data);
    });
}

var settingCarDev = {
    view: {
        showLine: false // 设置 zTree 是否显示节点之间的连线。默认是true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: '0'
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
            var id = treeNode.id;
            carLocation(id);
        }
    }
};
var settingResultCarDev = {
    view: {
        showLine: false // 设置 zTree 是否显示节点之间的连线。默认是true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: '0'
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
            Map.goTo([Number(treeNode.x), Number(treeNode.y)]);
        }
    }
};
var settingPople = {
    view: {
        showLine: false // 设置 zTree 是否显示节点之间的连线。默认是true
    },
    check: {
        enable: true,
        chkStyle: "checkbox",
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: '0'
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
            var id = treeNode.id == '-1' ? '': treeNode.pId;
            if (treeNode.type != 'camera') {
                return;
            }
        }
    }
};
var settingResultPople = {
    view: {
        showLine: false // 设置 zTree 是否显示节点之间的连线。默认是true
    },
    check: {
        enable: true,
        chkStyle: "checkbox",
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: '0'
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
            Map.goTo([Number(treeNode.x), Number(treeNode.y)]);
        }
    }
};
// 全市警力-警车
var carData = [];
function findCarDevice() {
    $.getJSON(ctx + "/flat/realtimeSituation/findCarDevice",
    function(data) {
        carData.data = data;
        $.fn.zTree.init($('#carAllUl'), settingCarDev, data);
    });
}
// 警车定位
function carLocation(id) {
    for (var i = 0; i < carData.data.length; i++) {
        if (id == carData.data[i].id) {
            var x = carData.data[i].x;
            var y = carData.data[i].y;
            var point = {
                "type": "FeatureCollection",
                "features": [{
                    "type": "Feature",
                    "id": carData.data[i].id,
                    "properties": {
                        "name": carData.data[i].name,
                        "icon": 'c1.png',
                        'type': 'vlc',
                        // 自定义type类型,判断是有视频流
                        'vlc': {
                            "id": carData.data[i].id,
                            'src': carData.data[i].param,
                        },
                        "info": {
                            '设备名称': carData.data[i].name,
                            '设备编号': carData.data[i].code,
                            '最大速度': carData.data[i].SMax,
                            '类型': carData.data[i].typeName,
                            '经度': x,
                            '维度': y,
                        }
                    },
                    "geometry": {
                        "type": "Point",
                        "coordinates": [x, y]
                    }
                }]
            }
            Map.removeLayer('car'); // 清除
            Map.addJSON1([{
                'type': 'car',
                'data': point,
                'isShow': true
            }]);
            Map.goTo([Number(x), Number(y)]);
        }
    }
}
// 视频定位
function videoLocation(id) {
    $.getJSON(ctx + "/ccmsys/ccmLiveVideo/findVideoInfo", {
        'id': id
    },
    function(data) {
        if (data.coordinate == '' || data.coordinate == undefined || data.coordinate == null) {
            layer.msg('暂无当前位置信息');
            return;
        }
        var x = data.coordinate.split(',')[0];
        var y = data.coordinate.split(',')[1];
        var point = {
            "type": "FeatureCollection",
            "features": [{
                "type": "Feature",
                "id": data.id,
                "properties": {
                    "name": data.name,
                    "icon": 'video.png',
                    'type': 'video',
                    // 自定义type类型,判断是视频监控
                    'video': {
                        "id": data.id,
                        'ip': data.ip,
                    },
                    "info": {
                        '设备名称': data.name,
                        '设备编号': data.code,
                        'IP地址': data.ip,
                        '设备地址': data.address,
                        '经度': x,
                        '维度': y,
                    }
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": [x, y]
                }
            }]
        }
        Map.removeLayer('videos'); // 清除
        Map.addJSON1([{
            'type': 'videos',
            'data': point,
            'isShow': true
        }]);
        Map.goTo([Number(x), Number(y)]);
    });
}
var key, lastValue = "",
teamType = "",
nodeList = [],
type = getQueryString("type", "/sys/office/treeData?type=3");

function onmPoliceClick(_this) {
    var id = "u_" + $(_this).attr('data-id');
    var name = $(_this).attr('data-name');
    $('#dispatch').append('<span style="cursor:pointer" onclick="" class="userId" type="hidden" id="sp' + id + '" userId="' + id + '">' + name + '<i onclick="removePeopleId(\'' + id + '\')">x</i></span>');
}
// 取消ztree出警人员
function removeZtreeId(id) {
    var zTreeObj = $.fn.zTree.getZTreeObj("ztreePolice");
    var popNode = zTreeObj.getNodeByParam("id", id, null);
    zTreeObj.checkNode(popNode, false);
    removePeopleId(id);
}
// 删除出警人员
function removePeopleId(id) {
    $("#sp" + id).remove();
}

// 全市警力-警力
var tree, settingPolice;
function refreshPoliceTree() {
    tree,
    settingPolice = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: '0'
            }
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {
                "Y": "s",
                "N": "s"
            }
        },
        async: {
            enable: (type == 3),
            url: ctx + "/org/ccmOrgTeam/treeData?teamType=" + teamType,
            autoParam: ["id=officeId"],
            dataFilter: function(treeId, parentNode, responseData) {
                if (responseData) {
                    for (var i = 0; i < responseData.length; i++) {
                        if (responseData[i].loginState == 'online') {
                            if (responseData[i].userState == 'busy') {
                                responseData[i].name += "[忙碌]";
                            } else {
                                responseData[i].name += "[备勤]";
                            }
                        } else {
                            responseData[i].name += "[离线]";
                        }
                    }
                }
                return responseData;
            }
        },
        callback: {
            onCheck: function(e, treeId, treeNode) {
                var nodes = tree.getCheckedNodes(true);
                $('#dispatch').html('');
                for (var i = 0, l = nodes.length; i < l; i++) {
                    tree.expandNode(nodes[i], true, false, false);
                    var id = nodes[i].id;
                    if (id.indexOf("u_") > -1) { 
                        $('#dispatch').append('<span style="cursor:pointer" onclick="" class="userId" type="hidden" id="sp' + nodes[i].id + '" userId="' + nodes[i].id + '">' + nodes[i].name + '<i onclick="removeZtreeId(\'' + nodes[i].id + '\')">x</i></span>');
                    }
                }
                return false;
            },
            onAsyncSuccess: function(event, treeId, treeNode, msg) {
                var nodes = tree.getNodesByParam("pId", treeNode.id, null);
                for (var i = 0,
                l = nodes.length; i < l; i++) {
                    try {
                        tree.checkNode(nodes[i], treeNode.checked, true);
                    } catch(e) {}
                }
            }
        },

    };

    $.getJSON(ctx + "/sys/office/treeData?type=3&&extId=&isAll=&module=&t=" + new Date().getTime(),
    function(data) {
        tree = $.fn.zTree.init($("#ztreePolice"), settingPolice, data);
        // 默认展开一级节点
        var nodes = tree.getNodesByParam("level", 0);
        for (var i = 0; i < nodes.length; i++) {
            tree.expandNode(nodes[i], true, false, false);
        }
        // 异步加载子节点（加载用户）
        var nodesOne = tree.getNodesByParam("isParent", true);
        for (var j = 0; j < nodesOne.length; j++) {
            tree.reAsyncChildNodes(nodesOne[j], "!refresh", true);
        }
    });
}
// 出警定位
function locationP_er_destiny(_this) {
    var id = $(_this).attr('data-id');
    Map.SecondlocationTwo(id, locationP_eerPointr_destin);
}
function locationP_eerPointr_destin(point, id) {
    // 更新出警警情坐标
    var x = point[0];
    var y = point[1];
    $('#destinyX').val(x);
    $('#destinyY').val(y);
}
function init() {
    // 警情开关
    // var autoRefreshInterval ='';
    $('.toggle').click(function(e) {
        var toggle = this;
        e.preventDefault();
        $(toggle).toggleClass('toggle--on').toggleClass('toggle--off').addClass('toggle--moving');
        setTimeout(function() {
            $(toggle).removeClass('toggle--moving');
        },
        200)
    });
    $("#alarmDMS.toggle").click(function() {
        if ($("#alarmDMS.toggle").hasClass("toggle--off")) {
            // 显示警情定位
            /*
				 * var len=CacleLocationAlarmId.length; if(len>0){ for(i in
				 * CacleLocationAlarmId){
				 * Map.layersIsShow('alarm_'+CacleLocationAlarmId[i], false); } }
				 */
            Map.layersIsShow('alarms', false);
        } else if ($("#alarmDMS.toggle").hasClass("toggle--on")) {
            // 隐藏警情定位
            Map.layersIsShow('alarms', true);
            /*
				 * var len=CacleLocationAlarmId.length; if(len>0){ for(i in
				 * CacleLocationAlarmId){
				 * Map.layersIsShow('alarm_'+CacleLocationAlarmId[i], true); } }
				 */
        }
    })
    // 视频开关
//    $("#videoDMS.toggle").click(function() {
//        if ($("#videoDMS.toggle").hasClass("toggle--off")) {
//            shipinjiankongFun();
//            $('#shipinjiankong').css('border', '1px solid transparent');
//        } else if ($("#videoDMS.toggle").hasClass("toggle--on")) {
//            shipinjiankongFun();
//            $('#shipinjiankong').css('border', '1px solid #0e54a9');
//        }
//    })

//    $('#shipinjiankong').click(function() {
//    	shipinjiankongFun();
//    	$('#shipinjiankong').css('border', '1px solid transparent');
//    	$('#shipinjiankong').css('border', '1px solid #0e54a9');
//        $("#videoDMS.toggle").click()
//    })

    // 警力开关
//    $("#policeDMS.toggle").click(function() {
//        if ($("#policeDMS.toggle").hasClass("toggle--off")) {
//            jingyuanFun();
//            $('#jingyuan').css('border', '1px solid transparent');
//        } else if ($("#policeDMS.toggle").hasClass("toggle--on")) {
//            jingyuanFun();
//            $('#jingyuan').css('border', '1px solid #0e54a9');
//        }
//    });
//    $('#jingyuan').click(function() {
//    	jingyuanFun();
//    	$('#jingyuan').css('border', '1px solid transparent');
//    	$('#jingyuan').css('border', '1px solid #0e54a9');
//        $("#policeDMS.toggle").click();
//    })
    // 警车开关
//    $("#carDMS.toggle").click(function() {
//        if ($("#carDMS.toggle").hasClass("toggle--off")) {
//            jingcheFun();
//            $('#jingche').css('border', '1px solid transparent');
//        } else if ($("#carDMS.toggle").hasClass("toggle--on")) {
//            jingcheFun();
//            $('#jingche').css('border', '1px solid #0e54a9');
//        }
//    });
//    $('#jingche').click(function() {
//    	jingcheFun();
//    	$('#jingche').css('border', '1px solid transparent');
//    	$('#jingche').css('border', '1px solid #0e54a9');
//        $("#carDMS.toggle").click();
//    })
    // 警情列表
    var wrap = $('#slides'),
    slides = wrap.children('ul'),
    active = slides.filter('.active'),
    i = slides.index(active),
    width = wrap.width(),
    slideButtons = $('.status-explain span'),
    span = $('.alarm .status');
    $('#alarmSta').on('click','span',function (e) {
	    var _i = slideButtons.index($(this));
	    $('#alarmSta span').removeClass();
	    $(this).addClass('grey');
	    slides.removeClass('active');
	    slides.eq(_i).addClass('active');
    });
    // 全市警力单击事件
    $(".police .police-force").click(function() {
        var _this = $(this).parents('.police').children();
        $(this).addClass('fff');
        $(this).next('.result').removeClass('fff');
        _this.children('.police-city').show();
        _this.children('.police-result').hide();
    });
    // 查询结果单击事件
    $(".police .result").click(function() {
        var _this = $(this).parents('.police').children();
        $(this).addClass('fff');
        $(this).prev('.police-force').removeClass('fff');
        _this.children('.police-city').hide();
        _this.children('.police-result').show();
        // PageManager.getOrgnization();
    });
    $(".police-man").click(function() {
        var _this = $(this).parent();
        _this.parent().children('ul').hide();
        _this.parent().children('.police-list').show();
        _this.children('span').removeClass('blue');
        $(this).addClass('blue');
        refreshPoliceTree();
    });
    $(".police-cat").click(function() {
        var _this = $(this).parent();
        _this.parent().children('ul').hide();
        _this.parent().children('.cat-list').show();
        _this.children('span').removeClass('blue');
        $(this).addClass('blue');
        findCarDevice();
    });
    $(".police-video").click(function() {
        var _this = $(this).parent();
        _this.parent().children('ul').hide();
        _this.parent().children('.inner-video').show();
        _this.children('span').removeClass('blue');
        $(this).addClass('blue');
        refreshVideoTree();
    });
    $(".police .police-go").click(function() {
        $(this).addClass('fff');
        $(this).next('.interaction').removeClass('fff');
        $(this).parent().children('.chat').hide();
        $(this).parent().children('.swipeleft').show();
        $(this).children('i').removeClass('police-go-cut');
        $(this).next('.interaction').children('i').removeClass('interaction-cut');
    });
    $(".police .interaction").click(function() {
        $(this).addClass('fff');
        $(this).prev('.police-go').removeClass('fff');
        $(this).parent().children('.chat').show();
        $(this).parent().children('.swipeleft').hide();
        $(this).children('i').addClass('interaction-cut');
        $(this).prev('.police-go').children('i').addClass('police-go-cut');
    });
    // 周边查询图层是否选中
    $('input[name="layer"]').change(function() {
        if ($(this).is(':checked')) {
            $(this).val('1');
        } else {
            $(this).val('0');
        }
    })
    // 周边查询自定义米数
    $('input[name="distance"]').change(function() {
        if ($('#customRadio').is(':checked')) {
            $('#customKM').attr('disabled', false);
        } else {
            $('#customKM').attr('disabled', true);
        }
    })
    // 警情列表添加选中效果
    $('#slides').on('click', 'li .header', function() {
        $('#slides').find('li .header').removeClass(' header-cut');
        $(this).addClass(' header-cut');
        $('#slides').find('li').removeClass('active');
        $(this).parent('li').addClass('active');
        // 去除新消息标志
        if ($(this).find('.label-danger').length > 0) {
            $(this).find('.label-danger').remove();
            var id = $(this).parent('li').attr('data-id');
            // 已查看新消息，删除sessionStorage保存新消息id
            var newAlarmDateIdArr = sessionStorage.newAlarmDateId.split(',');
            newAlarmDateIdArr.splice($.inArray(id, newAlarmDateIdArr), 1);
            sessionStorage.newAlarmDateId = newAlarmDateIdArr.join(',');
        }
    })
    // 警情列表功能按钮
    $('#slides').on('click', 'li .details span', function() {
        $('.details').children('span').removeClass('header-cut-1');
        $(this).addClass('header-cut-1');
        if ($(this).attr('type') != 'alarm-location') {
            // 注销二次定位事件
            if (Map.singleclickListener) {
                map.un('click', Map.singleclickListener);
                map.removeOverlay(Map.helpTooltip);
            }
        }
        // 注销周边查询或智能堵控编辑事件
        if (Map.plotEdit) {
            Map.plotEdit.deactivate();
        }
        $('#btn-delete').hide();
    });
    // 浮动工具栏
    var isDrag = false; // 声明拖动的默认状态是：否
    $('#floatingLayer').draggable({
        containment: "parent"
    });
    var floatingLayerFlag = true;
    $('#toolMenu').draggable({
        containment: "parent",
        stop: function(e) {
            isDrag = true;
        }
    });
    $("#toolbar div span").click(function() {
        if (!isDrag) {
            $("#toolbar div").removeClass('conversion-color');
            $(this).parent('div').addClass('conversion-color');
        }
    });
    $("#floatingLayer").click(function() {
        if (!isDrag) {
            var offset = $("#floatingLayer").offset();
            $("#toolMenu").css("top", offset.top - 300);
            $("#toolMenu").css("left", offset.left - 300);
            $(this).parent().children('#toolMenu').toggleClass('active');
            $(this).hide();
        }
        isDrag = false;
    });
    $("#map .return-map span").click(function() {
        if (!isDrag) {
            var _this = $(this).parents('.wrapper');
            _this.children('.alarm').show();
            _this.children('.police').show();
            $(this).parents('.map').removeClass('none-map');
            $(this).parents('.toolbar').children('.all-map').show();
            $(this).parents('.toolbar').children('.all-map').addClass('conversion-color');
            $(this).parent('.return-map').hide();
        }
        isDrag = false;
    });

    $('.box .hd span').click(function() {
        var _this = $(this).parent().parent();
        _this.children('.bd').toggle();
        $(this).toggleClass('toggle-up');
        $(this).toggleClass('toggle');
    });
    
    $("#range span").click(function() {
        if (!isDrag) {
            var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.range-query');
            $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars,.plottingbar').removeClass('active');
            _this.toggleClass("active");
            $(this).toggleClass("triangle-down");
            $(this).toggleClass("triangle-up");
        }
        isDrag = false;
    });
    $("#plotting span").click(function() {
        if (!isDrag) {
            var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.plottingbar');
            $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars,.range-query').removeClass('active');
            _this.toggleClass("active");
            $(this).toggleClass("triangle-down");
            $(this).toggleClass("triangle-up");
        }
        isDrag = false;
    });

    $(".Tools span").click(function() {
        if (!isDrag) {
            var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars');
            $(this).parents('#toolMenu').children('.inner-menu').children('.plottingbar,.range-query').removeClass('active');
            _this.toggleClass("active");
            $(this).toggleClass("triangle-down");
            $(this).toggleClass("triangle-up");
        }
        isDrag = false;
    });
    $("#turn-off").click(function(e) {
        if (!isDrag) {
            $(this).parents('#toolMenu').removeClass('active');
            $(this).parent().children('div').removeClass('conversion-color');
            $(this).parents('#toolMenu').children().children().removeClass('active');
            $("#floatingLayer").show();
        }
        isDrag = false;
    });
    $("#map .all-map span").click(function() {
        if (!isDrag) {
            var _this = $(this).parents('.wrapper');
            _this.children('.alarm').hide();
            _this.children('.police').hide();
            $(this).parents('.map').addClass('none-map');
            $(this).parents('.toolbar').children('.return-map').show();
            $(this).parents('.toolbar').children('.return-map').addClass('conversion-color');
            $(this).parent('.all-map').hide();
        }
        isDrag = false;
    });
    // 警车警力图层
    /* 布局改变 */
    $(".relevance-bg").click(function() {
        $(".relevance").animate({
            "width": "400px",
            "height": "150px"
        },
        300);
        $(".relevance-bg").hide();
        $('.unfold').css('bottom', '3px');
        $('.unfold').css('left', '320px');
        $('.map-2').hide();
        $('.map-4').show();
    });
    $(".re-close").click(function() {
        $(".relevance").animate({
            "width": 0,
            "height": 0
        },
        300);
        $(".relevance-bg").show();
        $('.unfold').css('bottom', '3px');
        $('.unfold').css('left', '320px');
        $('.map-4').hide();
        $('.map-2').show();
    });
    var ham1 = new Hammer($("#map")[0], {
        domEvents: true
    });
    ham1.add(new Hammer.Pinch());
    ham1.get('pinch').set({
        pointers: 5
    });
    ham1.off('pinchstart pinchmove');
    ham1.on("pinchin", function(e) {
        $("#toolMenu").css("top", e.center.y - 100);
        $("#toolMenu").css("left", e.center.x - 100);
        $("#toolMenu").addClass('active');
    });
    ham1.get('swipe').set({
        pointers: 3
    }); // 三指向左清除，向右选警
    ham1.on('swipeleft', function(e) {
        clearAllGraphic();
    });
    teamType;
    // 警力-警种类型
    $('input[name="police"][type="checkbox"]').change(function() {
        teamType = '';
        $('input[name="police"][type="checkbox"]:checked').each(function() {
            teamType += $(this).val() + ','
        });
        teamType = teamType.substring(0, teamType.length - 1);
        refreshPoliceTree();
    });

    // 视频树搜索
//    $('#videoButton').click(function() {
//        var secVideoVal = $('#secVideo').val();
//        if (secVideoVal == "") {
//            refreshVideoTree();
//        } else {
//            filter('ztreeVideo', 'secVideo');
//        }
//    });
}

//派警
function dispatchPolice(_this) {
	var pointAlarmId = $(_this).attr("data-id");
	var widthX = $('#popup').offset().top;
	var heughtY = $('#popup').offset().left+376;
	layer.open({
		type: 1,
		//type:0 也行
		title: "请选择工作人员",
		area: ["300px", "444px"],
		offset: [ widthX , heughtY ],
		shade: 0.1,
		content: '<table class="layui-hide" id="policeOfficer" lay-filter="test"></table>',//data-id是指最高级节点的parentId
		btn: ['确认', '关闭'],
		success: function(layero, index) {
			layui.use('table', function(){
				var table = layui.table;
				table.render({
					elem: '#policeOfficer'
				    ,url:ctx + '/alarm/bphAlarmInfo/queryPoliceByAlarmId'
				    ,where : {
						'alarmId' : pointAlarmId
					}
				    ,title: '工作人员表'
				    ,cols: [[
				    	{type: 'checkbox', fixed: 'left'}
				    	,{field:'id', hide: true}
				    	,{field:'x', hide: true}
				    	,{field:'y', hide: true}
				    	,{minWidth :20,templet: '#sexTpl',width:20}
				    	,{field:'name', width:90}
				    	,{field:'distance', width:40}
				    	,{fixed: 'right', toolbar: '#barDemo', width:80}
				    ]]
				    ,page: false
				});
			});
		},
		yes: function(index, layero) {
//			var params = dtree.getCheckbarNodesParam("openTree1");
//			if(params.length <= 0){
//				layer.msg('请选择警力');
//		        return;
//			}
//			var userIds = '';
//			for(var i = 0; i < params.length; i++) {
//				var basicData = params[i].basicData;
//				if(basicData == '"user"') {
//					userIds += params[i].nodeId + ',';
//				}
//			}
//			userIds = userIds.substring(0, userIds.length - 1);
//			popUpAlarmBox(userIds, pointAlarmId, index);
			layer.close(index);
		},
		btn2: function(index, layero) {
			layer.close(index);
		}
	});
}

//电话拨打
function callPhone(_this){
	
}

//发送信息
function sendNote(_this){
	
}

//定位警员信息
function locationConstable(_this){
	
}