var livePlayer = null;

function flashChecker() {
    var hasFlash = 0;　　　　 //是否安装了flash
    var flashVersion = 0;　　 //flash版本
    if (document.all) {
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
        if (swf) {
            hasFlash = 1;
            VSwf = swf.GetVariable("$version");
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
        }
    }
    else {
        if (navigator.plugins && navigator.plugins.length > 0) {
            var swf = navigator.plugins["Shockwave Flash"];
            if (swf) {
                hasFlash = 1;
                var words = swf.description.split(" ");
                for (var i = 0; i < words.length; ++i) {
                    if (isNaN(parseInt(words[i]))) continue;
                    flashVersion = parseInt(words[i]);
                }
            }
        }
    }
    return {f: hasFlash, v: flashVersion};
}

function installFlash() {
    var fls = flashChecker();
    var s = "";
    if (!fls.f) {
        dlgRv = confirm('您的浏览器未安装Flash插件，现在安装？');
        if (dlgRv === true) {
            window.open("http://get.adobe.com/cn/flashplayer/");
        }
    }
}

function flvLiveError() {
    installFlash();
}

$(document).ready(function () {
   // videojs.options.flash.swf = "/arjccm/static/ccm/liveVideo/js/video-js.swf";
    livePlayer = videojs('videoElement', {
        bigPlayButton: false,
        textTrackDisplay: false,
        errorDisplay: true,
        controlBar: true,
        preload: 'auto'
    }, PlayerControl);

    installFlash();
});

PlayerControl = function () {
    this.on('loadedmetadata', function () {
        console.log('loadedmetadata');
        //加载到元数据后开始播放视频
    })
    this.on('error', function () {
        console.log('error');
        // flvLiveError();
    })

    this.on('ended', function () {
        //结束处理
        console.log('ended')
        // $("#playAndPause").removeClass("pause");
        // if (LiveUtil.isLive) {
        //     LiveUtil.flvLiveError();
        // }
    })
    this.on('firstplay', function () {
        console.log('firstplay')
    })
    this.on('loadstart', function () {
        //开始加载

    })
    this.on('loadeddata', function () {
        console.log('loadeddata')
        // if (!LiveUtil.flvPlayer) {
        //     LiveUtil.flvPlayer = this;
        //     LiveUtil.setVideoVolume();
        // }
    })
    this.on('seeking', function () {
        //正在去拿视频流的路上
        console.log('seeking')
    })
    this.on('seeked', function () {
        //已经拿到视频流,可以播放
        console.log('seeked')
    })
    this.on('waiting', function () {
        console.log('waiting')
    })
    this.on('pause', function () {
        //$("#playAndPause").removeClass("pause");
        console.log('pause')
    })
    this.on('play', function () {
        // $("#playAndPause").addClass("pause");
        console.log('play')
        // if (!LiveUtil.flvPlayer) {
        //     LiveUtil.flvPlayer = this;
        //     LiveUtil.setVideoVolume();
        // }
    })
};

function showStatus(str) {
    $('#divStatusBar').text("");
    $('#divStatusBar').append(str);
}

function videoPlay(type, url) {

    livePlayer.src({type: type, src: url});
    livePlayer.play();
    showStatus("正在播放：" + url);
}

