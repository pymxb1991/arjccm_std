$(document).ready(function() {
	initAlarmState();// 警情状态
	changeTime();// 今日 本周
	initTable();// 初始化表格
	AlarmDistribute();//统计数据
	$('#btnSubmit').click(function() {
		initTable();// 初始化表格
		AlarmDistribute();//统计数据
	})
});
var activeId = null;
// 初始化表格
function initTable() {
	var beginAlarmTime = $('#beginAlarmTime').val();
	var endAlarmTime = $('#endAlarmTime').val();
	var officeId = $('#officeIdId').val();
	var alarmState = $('#alarmState').val();
	layui.use('table', function() {
		var table = layui.table;
		table.render({
			elem : '#contentTable',
			url : ctx + '/alarmdistribute/bphAlarmDistribute/list',
			where : {
				'beginAlarmTime' : beginAlarmTime,
				'endAlarmTime' : endAlarmTime,
				'officeId' : officeId,
				'state' : alarmState
			},
			request : {
				pageName : 'currentPage',// 页码的参数名称，默认：page
				limitName : 'pageSize' // 每页数据量的参数名，默认：limit
			},
			limit:15,
			limits:[15,30,45,60],
			height : tableHeight,
			cols : [ [ {
				field : 'content',
				width : '25%',
				title : '警情内容',
			}, {
				field : 'alarmTime',
				width : '20%',
				title : '日期',
				sort : true
			}, {
				field : 'place',
				width : '19%',
				title : '地点',
			}, {
				field : 'typeName',
				width : '10%',
				title : '警情类型'
			}, {
				field : 'office',
				title : '处理单位',
				width : '15%',
				templet: function(val){
					if(val.office&&val.office.id){
						return val.office.name;
					}else{
						return '';
					}
				}
			}, {
				field : 'state',
				width : '10%',
				title : '警情状态',
				sort : true,
				templet: function(val){
					var html='';
					if(val.state=='0'){
						html='<span class="alarmstatus_0">未处理</span>';
					}else if(val.state=='1'){
						html='<span class="alarmstatus_1">已处理</span>';
					}else if(val.state=='2'){
						html='<span class="alarmstatus_2">已到达</span>';
					}else if(val.state=='3'){
						html='<span class="alarmstatus_3">已反馈</span>';
					}else{
						html='<span></span>';
					}
			        return html;
				}
			}] ],
			page : true,
			done: function(res, curr, count){
			    $('#content').find('.layui-table-body').find("table").find("tbody").children("tr").on('click',function(){
			    	if(activeId != null){
			    		$('#content').find('.layui-table-body').find("table" ).find("tbody").find("tr").eq(activeId).removeClass("layUiTableActive");
			    	}
			        var id = JSON.stringify($('#content').find('.layui-table-body').find("table" ).find("tbody").find(".layui-table-hover").data('index'));
			        activeId = id;
			        $('#content').find('.layui-table-body').find("table" ).find("tbody").find("tr").eq(activeId).addClass("layUiTableActive");
			        var objId = res.data[id].id;
			        getAlarmInfo(objId);
			    })
			}
		});
	});
}

function changeTime() {
	var time = today();
	$('#beginAlarmTime').val(time + ' 00:00:00');
	$('#endAlarmTime').val(time + ' 23:59:59');
	$('input[type="radio"][name="time"]').change(function() {
		if ($(this).val() == 0) {// 今日
			var time = today();
			$('#beginAlarmTime').val(time + ' 00:00:00');
			$('#endAlarmTime').val(time + ' 23:59:59');
		} else if ($(this).val() == 1) {// 本周
			var beginTime = getWeekStartDate();
			var endTime = today();
			$('#beginAlarmTime').val(beginTime + ' 00:00:00');
			$('#endAlarmTime').val(endTime + ' 23:59:59');
		}
	})
}

function AlarmDistribute() {
	var beginAlarmTime = $('#beginAlarmTime').val();//开始时间
	var endAlarmTime = $('#endAlarmTime').val();//结束时间
	var beginDateNew = Date.parse(new Date(beginAlarmTime.replace(/-/g, "/")));
	var endDateNew = Date.parse(new Date(endAlarmTime.replace(/-/g, "/")));
	var time = endDateNew - beginDateNew;
	if (time < 0) {
		layer.msg("开始时间必须小于结束时间", "警告");
		return;
	}
	var area = $('#area').text();//处理单位
	var state = $('#state').val();//警情状态
	//查询全部数量
	$.post(ctx + '/alarmdistribute/bphAlarmDistribute/Count',{'beginAlarmTime':beginAlarmTime,'endAlarmTime':endAlarmTime,'area':area,'state':state}, function(data) {
		var data = JSON.parse(data);
		$('#count_0').html(Number(data.count0));
		$('#count_1').html(Number(data.count1));
		$('#count_2').html(Number(data.count2));
		$('#count_3').html(Number(data.count3));
		$('#countSum').html(Number(data.sum));
	})
	//按部门查询数量
	$.post(ctx + '/alarmdistribute/bphAlarmDistribute/byOfficeCount',{'beginAlarmTime':beginAlarmTime,'endAlarmTime':endAlarmTime,'area':area,'state':state},function(data) {
		var data = JSON.parse(data);
		var len = data.length;
		if (len > 0) {
			var html = '';
			for (var i = 0; i < len; i++) {
				var count = Number(data[i].numState0) + Number(data[i].numState1) + Number(data[i].numState2) + Number(data[i].numState3);
				html += '<div class="clearfix">';
				html += '<div style="float: left; line-height: 27px; padding: 5px;overflow:hidden;cursor: pointer;text-overflow:ellipsis;-o-text-overflow:ellipsis;white-space:nowrap;width:75px;display: block;">';
				html += '<span title="'+data[i].name+'">'+ data[i].name + '</span>';
				html += '</div>';
				html += '<div class="status-unit clearfix" style="float: left">';
				html += '<span><i class="all-message">'+ count + '</i></span>';
				html += '<span><i class="not-handle">'+ Number(data[i].numState0)+ '</i></span>';
				html += '<span><i class="has-signIn">'+ Number(data[i].numState1)+ '</i></span>';
				html += '<span><i class="handling">'+ Number(data[i].numState2)+ '</i></span>';
				html += '<span><i class="has-handle">'+ Number(data[i].numState3)+ '</i></span>';
				html += '</div>';
				html += '</div>';
			}
			$('#byOfficeCount').html(html);
		}
	})
}

// 警情状态下拉框
function initAlarmState() {
	var alarmTypeSelectNode = $("<select id='alarmState'></select>");
	alarmTypeSelectNode.append("<option value='' >全部</option>");
	$.getJSON(ctx + '/sys/dict/listData?type=bph_alarm_info_state', function(datas) {
		for (var i = 0; i < datas.length; i++) {
			alarmTypeSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
		}
	})
	$("#alarmStateTd").append(alarmTypeSelectNode);
}

function initTableAlarmState(state) {
	var alarmTypeSelectNode = $("<select id='stateTd'></select>");
	$.getJSON(ctx + '/sys/dict/listData?type=bph_alarm_info_state', function(datas) {
		for (var i = 0; i < datas.length; i++) {
			if (state == datas[i].value) {
				alarmTypeSelectNode.append("<option value='" + datas[i].value + "' selected>" + datas[i].label + "</option>");
			} else {
				alarmTypeSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
			}
		}
	})
	$("#alarmStateFromTd").html(alarmTypeSelectNode);
}

//查询结果-行点击事件
function getAlarmInfo(id) {
	$.get(ctx + '/alarmdistribute/bphAlarmDistribute/alarm?id=' + id, function(data) {
		var data = JSON.parse(data);
		var officeId = '', officeName = '';
		if (data.office !== undefined && data.office != null && data.office != '') {
			officeId = data.office.id;
			officeName = data.office.name;
		}
		$("#orderNum").html(data.orderNum);
		$("#alarmId").val(id);
		$("#policeNum").html(data.policeNum);
		$("#policeName").html(data.policeName);
		$("#manName").val(data.manName);
		$("#manTel").val(data.manTel);
		$("#xy").html(data.x + ',' + data.y);
		$("#z").html(data.z);
		$("#alarmTime").html(getDay(data.alarmTime.time));
		$("#alarmRecord").attr("src", data.alarmRecord);
		$("#isImportant").html(isImportantChange(data.isImportant));
		$("#cont").html(data.content);
		$("#place").html(data.place);
		$("#tableOfficeIdId").val(officeId);
		$("#tableOfficeIdName").val(officeName);
		$('#alarmInfo').show();
		$('.form-horizontal').height($('.common-height').height()-100);
		$('input[name="manSex"]').each(function() {
			if ($(this).val() == data.manSex) {
				$(this).attr('checked', true);
			}
		});
		audioInit();//初始化audio
		initTableAlarmState(data.state);//初始化警情状态下拉框
		var alarmTypeSelectNode = $("<select id='alarmType'></select>");//警情类型下拉框
		var alarmClassSelectNode = $("<select id='alarmClass'></select>");//警情类别下拉框
		$("#alarmTypeTd").html(alarmTypeSelectNode);
		$.getJSON(ctx+'/sys/dict/listData?type=bph_alarm_info_typecode',function(datas){
			for(var i=0;i<datas.length;i++){
				if(data.typeCode == datas[i].value){
					alarmTypeSelectNode.append("<option data-id='"+datas[i].id+"' value='"+datas[i].value+"' selected='selected'>"+datas[i].label+"</option>");
				}else{
					alarmTypeSelectNode.append("<option data-id='"+datas[i].id+"' value='"+datas[i].value+"'>"+datas[i].label+"</option>");
				}
			}
			var parentId = $("#alarmType").children('option:selected').attr("data-id");
			var alarmClassUrl = '/sys/sysDict/getDictListByParentId?type=bph_alarm_info_class&parentId=' + parentId;
			alarmClassUrl = '/sys/dict/listData?type=bph_alarm_info_class';
			$.getJSON(ctx + alarmClassUrl,function(datas){
				for(var i=0;i<datas.length;i++){
					if(data.genreCode == datas[i].value){
						alarmClassSelectNode.append("<option value='"+datas[i].value+"' selected='selected'>"+datas[i].label+"</option>");
					}else{
						alarmClassSelectNode.append("<option value='"+datas[i].value+"'>"+datas[i].label+"</option>");
					}
				}
			});
			$("#alarmClassTd").html(alarmClassSelectNode);
		});
		
		$("#alarmType").change(function(){
			var alarmClassSelectOptions = $("#alarmClass")[0].options;
			for(var i in alarmClassSelectOptions){
				alarmClassSelectOptions.remove(0);
			}
			var alarmTypeId = $(this).children('option:selected').attr("data-id");
			$.getJSON(ctx+'/sys/sysDict/getDictListByParentId?type=bph_alarm_info_class&parentId='+alarmTypeId,function(datas){
				for(var i=0;i<datas.length;i++){
					alarmClassSelectNode.append("<option value='"+datas[i].value+"'>"+datas[i].label+"</option>");
				}
			});
		});
		// 报警方式下拉框
		var alarmFromSelectNode = $("<select id='alarmFrom'></select>");
		$.getJSON(ctx+ '/sys/dict/listData?type=bph_alarm_from',function(datas) {
			for (var i = 0; i < datas.length; i++) {
				if (data.alarmFrom == datas[i].value) {
					alarmFromSelectNode.append("<option value='" + datas[i].value + "' selected='selected'>" + datas[i].label + "</option>");
				} else {
					alarmFromSelectNode.append("<option value='" + datas[i].value + "'>" + datas[i].label + "</option>");
				}
			}
		});
		$("#alarmFromTd").html(alarmFromSelectNode);
	})
}
function deleteAlarm(id){
	layer.open({
		skin: 'layui-layer-lan',
		type : 1,
		title : "提示信息",
		area : [ "200px" , "140px" ],
		maxmin : false,
		btn : [ "确定" , "取消" ], // /可以无限个按钮
		content :'<div><span style="font-size:14px;color:red;"><b>确定将该警情改成无效警情？</b></span></div>',
		cancel : function() {
		},
		end : function() {
		},
		yes : function(index, layero) {
			var alarmId = $('#alarmId').val();
			var bphAlarmInfo = {};
			bphAlarmInfo.id = alarmId;
			$.getJSON(ctx+ '/alarm/bphAlarmInfo/delete',bphAlarmInfo,function(data) {
				if(data == '1'){
					$('#alarmTable').remove();
					//$('#orderSubmit').remove();
					//$('#deleteAlarm').remove();
					layer.msg('操作成功！');
					initTable();// 初始化表格
				}else{
					layer.msg('操作失败！');
				}
			})
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
	$(".layui-layer").css("border-radius", "10px");
	$(".layui-layer-title").css("border-radius", "10px 10px 0 0");
}
//警情分发
function orderFun() {
	var alarmId = $('#alarmId').val();
	var manName = $('#manName').val();
	var manSex = $('input[name="manSex"]:checked').val();
	var manTel = $('#manTel').val();
	var content = $('#cont').val();
	var place = $('#place').val();
	var officeId = $('#tableOfficeIdId').val();
	var typeCode = $('#alarmType').val();
	var state = $('#stateTd').val();
	var alarmFrom = $('#alarmFrom').val();
	var optionDesc = "分发警情给"+$('#belongAreaId').attr('value');
	var bphAlarmInfo = {};
	bphAlarmInfo.id = alarmId;
	bphAlarmInfo.manName = manName;
	bphAlarmInfo.manSex = manSex;
	bphAlarmInfo.manTel = manTel;
	bphAlarmInfo.content = content;
	bphAlarmInfo.place = place;
	bphAlarmInfo.officeId = officeId;
	bphAlarmInfo.state = state;
	bphAlarmInfo.alarmFrom = alarmFrom;
	bphAlarmInfo.typeCode = typeCode;
	bphAlarmInfo.optionDesc = optionDesc;
	$.post(ctx + '/alarmdistribute/bphAlarmDistribute/updateBphAlarmDistribute', bphAlarmInfo, function(data) {
		var data = JSON.parse(data);
		if (data.ret == '0') {
			layer.msg('分发成功');
			$('#alarmTable').remove();
			//$('#orderSubmit').remove();
			//$('#deleteAlarm').remove();
			initTable();// 初始化表格
		} else {
			layer.msg('分发失败');
		}
	})
}
