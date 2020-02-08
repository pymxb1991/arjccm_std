$(document).ready(function () {
    $("#btnSubmit").on("click", function () {
        var begin = new Date($("[name='beginHappenDate']").val());
        var end = new Date($("[name='endHappenDate']").val());
        if (begin.getTime() > end.getTime()) {
            messageAlert("开始时间大于结束时间！", "error");
            return false;
        }
        $("#searchForm").submit();
    })
});

function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
}

//全选、取消
function allcheck() {
    var nn = $("#allboxs").is(":checked"); //判断th中的checkbox是否被选中，如果被选中则nn为true，反之为false
    if (nn == true) {
        var namebox = $("input[name^='boxs']");  //获取name值为boxs的所有input
        for (i = 0; i < namebox.length; i++) {
            namebox[i].checked = true;    //js操作选中checkbox
        }
    }
    if (nn == false) {
        var namebox = $("input[name^='boxs']");
        for (i = 0; i < namebox.length; i++) {
            namebox[i].checked = false;
        }
    }
}

//弹框
function openLogTail(ids, tailCase, ccmEventIncident) {
    var html = '';
    html += '<div id="logTail"><table class="table table-striped table-bordered table-condensed"><tr>';
    html += '<td><label><span class="help-inline"><font color="red">*</font></span>处理人姓名：</label>';
    html += '<input id="tailPerson" style="width:170px;border: 1px solid #ccc;border-radius: 4px;padding: 4px 6px;" htmlEscape="false"  class="input-xlarge required" /></td>';
    html += '<td><label><span class="help-inline"><font color="red">*</font></span>联系方式：</label>';
    html += '<input id="more1" style="width:170px;border: 1px solid #ccc;border-radius: 4px;padding: 4px 6px;" htmlEscape="false"  class="input-xlarge telephone0 phone required" />';
    html += '</td></tr><tr>';
    html += '<td colspan="2"><label><span class="help-inline"><font color="red">*</font></span>反馈信息：</label>';
    html += '<input id="tailContent" style="height: 100px;border: 1px solid #ccc;border-radius: 4px;padding: 4px 6px;" htmlEscape="false"  class="input-xxlarge" />';
    html += '</td></tr></table></div>';
    layer.open({
        type: 1
        , id: 'openLogTail' //防止重复弹出
        , content: html
        , btn: ['确定', '关闭']
        , area: ['625px;', '300px']
        , shade: 0 //不显示遮罩
        , title: tailCase
        , yes: function (index) {
            var ccmLogTail = {};
            ccmLogTail.relevanceTable = "ccm_event_incident";//关联表
            if ($('#tailPerson').val() == null || $('#tailPerson').val() == "") {
                layer.msg('请填写处理人姓名');
                $('#tailPerson').focus();
                return false;
            }
            var phone = $('#more1').val();
            if (phone == null || phone == "") {
                layer.msg('请填写联系方式');
                $('#more1').focus();
                return false;
            } else {
                var express = /(^0[1-9]{1}\d{9,10}$)|(^1[3,4,5,6,7,8,9]\d{9}$)/g;
                var result = express.test(phone);
                if (result == false) {
                    layer.msg('固话为:区号(3-4位)号码(7-9位),手机为:13,14,15,16,17,18,19号段');
                    $("#more1").focus();
                    return false;
                }
            }
            if (!isPhoneNum($('#more1').val())) {
                layer.msg('联系方式格式不正确');
                $('#more1').focus();
                return false;
            }
            if ($('#tailContent').val() == null || $('#tailContent').val() == "") {
                layer.msg('请填写反馈信息');
                $('#tailContent').focus();
                return false;
            }
            ccmLogTail.tailPerson = $('#tailPerson').val();
            ccmLogTail.more1 = $('#more1').val();
            ccmLogTail.tailContent = $('#tailContent').val();
            ccmLogTail.tailCase = tailCase;
            insertLogTail(ccmLogTail, ids);
            updateIncident(ccmEventIncident);
            layer.close(index);
        }
        , btn2: function (index) {
            layer.close(index);
        }
    });
}

function isPhoneNum(str) {
    return /^[0-9]\d*$/.test(str)
}

//加急   领导批示    置顶    历史遗留公共功能
function incident(_this) {
    var ccmEventIncident = {};
    var ids = '';
    var tailCase = "";//跟踪事项
    //遍历复选框选中id
    $('input[name="boxs"]').each(function () {
        if ($(this).is(':checked')) {
            ids += $(this).val() + ','
        }
    })
    ids = ids.substring(0, ids.length - 1);


    $.ajax({
        type: "get",
        url: ctx + "/event/ccmEventIncident/getById/" + ids,
        async: true,
        success: function (data) {
            //判断复选框是否有选中
            if (ids !== undefined && ids != null && ids != '') {
                ccmEventIncident.ids = ids;
                if (_this == "ratify") {//领导批示
                    if ("0" == data.ratify || null == data.ratify) {
                        ccmEventIncident.ratify = '1';
                        tailCase = "领导批示";
                        openLogTail(ids, tailCase, ccmEventIncident);
                    } else {
                        layer.msg('确定要将此条案（事）件记录再次批示吗？', {
                            time: 0 //不自动关闭
                            , btn: ['确定', '取消']
                            , yes: function (index) {
                                ccmEventIncident.ratify = '1';
                                tailCase = "领导批示";
                                openLogTail(ids, tailCase, ccmEventIncident);
                                layer.close(index);
                            },
                            btn2: function (index) {
                                layer.close(index);
                            }
                        });

                    }


                } else if (_this == "stick") {//置顶
                    if ("0" == data.stick || null == data.stick) {
                        ccmEventIncident.stick = '1';
                        updateIncident(ccmEventIncident);
                    } else {
                        layer.msg('确定要将此条案（事）件记录取消置顶吗？', {
                            time: 0 //不自动关闭
                            , btn: ['确定', '取消']
                            , yes: function (index) {
                                ccmEventIncident.stick = '0';
                                updateIncident(ccmEventIncident);
                                layer.close(index);
                            },
                            btn2: function (index) {
                                layer.close(index);
                            }
                        });
                    }

                } else if (_this == "urgent") {//加急
                    if ("0" == data.urgent || null == data.urgent) {
                        ccmEventIncident.urgent = '1';
                        tailCase = "加急";
                        openLogTail(ids, tailCase, ccmEventIncident);
                    } else {
                        layer.msg("此案（事）件已加急！");
                    }


                } else if (_this == "historyLegacy") {//历史遗留

                    if ("0" == data.historyLegacy || null == data.historyLegacy) {
                        ccmEventIncident.historyLegacy = '1';
                        updateIncident(ccmEventIncident);
                    } else {

                        layer.msg('确定要将此条案（事）件记录取消遗留吗？', {
                            time: 0 //不自动关闭
                            , btn: ['确定', '取消']
                            , yes: function (index) {
                                ccmEventIncident.historyLegacy = '0';
                                updateIncident(ccmEventIncident);
                                layer.close(index);
                            },
                            btn2: function (index) {
                                layer.close(index);
                            }
                        });
                    }

                } else {
                    return false;
                }
            } else {
                layer.msg('请选择案（事）件！');
                return false;
            }

        }

    })


}

//ccm_event_incident数据修改
function updateIncident(ccmEventIncident) {
    $.post(ctx + '/event/ccmEventIncident/incident', ccmEventIncident, function (data) {
        layer.msg('设置成功！');
        // window.location.reload();
        if (!window.location.hash){
            window.location.href = window.location.href;
        } else {
            window.location.reload();
        }
})
}

function insertLogTail(ccmLogTail, ids) {
    $.post(ctx + '/log/ccmLogTail/saveIncident?ids=' + ids, ccmLogTail, function (data) {
        console.info(data);
    })
}

function checkIncident() {
    var ids = '';
    var deleteType = '1';
    var placeId = $("#placeId").val();
    //遍历复选框选中id
    $('input[name="boxs"]').each(function () {
        if ($(this).is(':checked')) {
            ids += $(this).val() + ','
        }
    })
    if(ids.indexOf(",") == -1){
    	messageAlert("请勾选事件", "error");
    	return;
    }
    ids = ids.substring(0, ids.length - 1);
    var r = confirm("保存新事件关联前需要清除之前的事件关联吗？");
    if (r == true){
    	deleteType = '1';
    }else{
    	deleteType = '2';
    }
    $.ajax({
        type: "post",
        url: ctx + "/event/ccmEventIncident/saveIncidentPlace",
        data:{
        	placeId : placeId,
        	deleteType : deleteType,
        	ids : ids
        },
        async: true,
        success: function (data) {
        	parent.layer.close(parent.layerIndex);
        }
    })
}