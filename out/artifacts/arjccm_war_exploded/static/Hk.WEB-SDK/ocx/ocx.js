$(function () {

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
        }, 500);//这里设置延迟(数值请根据实际情况来)是防止快速刷新页面导致进程残留  具体清楚进程方式请参考<关闭进程 云台控制>demo中的代码
    });

    //初始化
    function init() {
        var OCXobj = document.getElementById("PlayViewOCX");
        var txtInit = $("#config").val();
        OCXobj.ContainOCX_Init(txtInit);
    }

    //提交按钮
    $('.submit').on('click', function () {
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

    //exe弹出
    $('.exe').on('click', function () {
        var PalyType = $('.PalyType').val();
        var SvrIp = $('.SvrIp').val();
        var SvrPort = $('.SvrPort').val();
        var appkey = $('.appkey').val();
        var appSecret = $('.appSecret').val();
        var time = $('.time').val();
        var timeSecret = $('.timeSecret').val();
        var httpsflag = $('.httpsflag').val();
        var CamList = $('.CamList').val();
        //主要是添加了'hikvideoclient://' 和 'VersionTag:artemis'2段字符串 
        var param = 'hikvideoclient://ReqType:' + PalyType + ';' + 'VersionTag:artemis' + ';' + 'SvrIp:' + SvrIp + ';' + 'SvrPort:' + SvrPort + ';' + 'Appkey:' + appkey + ';' + 'AppSecret:' + appSecret + ';' + 'time:' + time + ';' + 'timesecret:' + timeSecret + ';' + 'httpsflag:' + httpsflag + ';' + 'CamList:' + CamList + ';';

        document.getElementById("url").src = param;

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
});