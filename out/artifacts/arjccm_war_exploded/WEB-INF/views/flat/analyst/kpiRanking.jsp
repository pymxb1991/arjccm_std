<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>KPI排行榜</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${ctxStatic}/layui/css/layui.css" rel="stylesheet"/>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style type="text/css">
        #test {
            width:100%;
        }
        .one{
            width: 32px;
            height: 32px;
            color: #fff;
            text-align: center;
            background-image: url("${ctxStatic}/images/one@2x.png");
            background-size: 32px 32px;
        }
        .two{
            width: 32px;
            height: 32px;
            color: #fff;
            text-align: center;
            background-image: url("${ctxStatic}/images/two@2x.png") ;
            background-size: 32px 32px;


        }
        .three{
            width: 32px;
            height: 32px;
            color: #fff;
            text-align: center;
            background-image: url("${ctxStatic}/images/three@2x.png");
            background-size: 32px 32px;

        }
        .current{
            width: 32px;
            height: 32px;
            color: #fff;
            text-align: center;
            background-image: url("${ctxStatic}/images/多边形 4020@2x(3).png");
            background-size: 32px 32px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span4 offset4" style="margin-left: 10px;margin-top: 20px">
       <b>绩效统计情况</b>
    </div>
</div>
<div class="row-fluid">
    <div class="span11" style="padding: 20px;margin-right: 20px">
        <table class="layui-hide" id="test"></table>
    </div>
</div>


<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    var i=1;
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#test',
            url: '${ctx}/kpi/count/getRank',
            cols: [[{
                field: "",
                title: '排名',
                align:"center",
                sort: true,
                templet:function() {

                    switch (i) {
                        case 1 :
                            return "<div class='one'>"+(i++)+"</div>"
                            break;
                        case 2 :
                            return "<div class='two'>"+(i++)+"</div>"
                            break;
                        case 3 :
                            return "<div class='three'>"+(i++)+"</div>"
                            break;
                        default:
                            return "<div class='current'>"+(i++)+"</div>"
                            break;
                    }

                }

            }, {
                field: 'userId',
                title: '民警姓名',
                align:"center",
            }, {
                field: 'counts',
                title: '总计次数',
                align:"center",
                sort: true
            }/* , {
					field : 'city',
					title : '城市'
				}, {
					field : 'sign',
					title : '签名',
					minWidth : 150
				}, {
					field : 'experience',
					title : '积分',
					sort : true
				}, {
					field : 'score',
					title : '评分',
					sort : true
				}, {
					field : 'classify',
					title : '职业'
				}, {
					field : 'wealth',
					width : 135,
					title : '财富',
					sort : true
				}  */]],
            // page : true
        });
    });
</script>

</body>
</html>