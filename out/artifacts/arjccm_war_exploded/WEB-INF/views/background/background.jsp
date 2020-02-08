<%--
  Created by IntelliJ IDEA.
  User: lgh
  Date: 2019/9/6
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <title>自定义背景图片上传</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css" media="all">
    <style>
        .bg_div {
            /*            border: #0c0c0c 1px solid;
                        border-radius:5px;*/
            margin-top: 10px;
            margin-bottom: 5px;
        }

        .bg_img {
            width: 90%;
            height: 35%;
        }
    </style>
</head>
<body>

<div class="layui-row">
    <div class="layui-col-md4">
        <div class="bg_div">
            <img class="bg_img" src="/file/login_bg1.png"/>
        </div>
        <button type="button" class="layui-btn test" value="1" id="test1">
            <i class="layui-icon">&#xe67c;</i>上传图片1
        </button>
        <%--<button type="button" class="layui-btn" id="testListAction">开始上传</button>--%>
    </div>
    <div class="layui-col-md4">
        <div class="bg_div">
            <img class="bg_img" src="/file/login_bg2.png"/>
        </div>
        <button type="button" class="layui-btn test" value="2" id="test2">
            <i class="layui-icon">&#xe67c;</i>上传图片2
        </button>
    </div>
    <div class="layui-col-md4">
        <div class="bg_div">
            <img class="bg_img" src="/file/login_bg3.png"/>
        </div>
        <button type="button" class="layui-btn test" value="3" id="test3">
            <i class="layui-icon">&#xe67c;</i>上传图片3
        </button>
    </div>
</div>

<div class="layui-row">
    <div class="layui-col-md4">
        <div class="bg_div">
            <img class="bg_img" src="/file/login_bg4.png"/>
        </div>
        <button type="button" class="layui-btn test" value="4" id="test4">
            <i class="layui-icon">&#xe67c;</i>上传图片4
        </button>
    </div>
    <div class="layui-col-md4">
        <div class="bg_div">
            <img class="bg_img" src="/file/login_bg5.png"/>
        </div>
        <button type="button" class="layui-btn test" value="5" id="test5">
            <i class="layui-icon">&#xe67c;</i>上传图片5
        </button>
    </div>
    <div class="layui-col-md4">
        <div class="bg_div">
            <img class="bg_img" src="/file/login_bg6.png"/>
        </div>
        <button type="button" class="layui-btn test" value="6" id="test6">
            <i class="layui-icon">&#xe67c;</i>上传图片6
        </button>
    </div>
</div>


<script src="../../static/layui/layui.js"></script>
<script src="../../static/jquery/jquery-2.2.4.min.js"></script>
<script>
    layui.use('upload', function () {
        var upload = layui.upload;


        $(".test").each(function () {

            //普通图片上传
            var uploadInst = upload.render({
                elem: $(this)
                , method: "post"
                , cache: false  //清除缓存
                , url: '${ctx}/background/upload'
                , data: {
                    "num": $(this).attr("value")
                }
                , multiple: false
                , accept: 'images' //普通文件
                , acceptMime: 'image/*'
                , before: function () {
                    //预读本地文件示例，不支持ie8
                    layer.load(); //上传loading
                }
                , done: function (res) {
                    debugger
                    layer.closeAll('loading'); //关闭loading
                    if (res.code == "200") {
                        //do something （比如将res返回的图片链接保存到表单的隐藏域）

                        layer.msg('上传成功');
                        location.reload();  //重新加载界面
                    }
                    else {

                        layer.msg("上传失败  " + res.msg);
                        location.reload();  //重新加载界面
                    }
                }
                , error: function () {
                    layer.closeAll('loading'); //关闭loading
                    layer.msg('上传失败');
                }
            });


        });


    });
</script>
</body>
</html>
