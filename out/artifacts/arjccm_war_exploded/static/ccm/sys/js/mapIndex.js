var Map, Pubmap;
var map_level;  // 地图层级
var zTreeObjLeft
var streetFlag, vccmorgFlag, communityFlag, gridFlag, buildFlag, eventFlag, partsFlag, landsFlag, videoFlag,
    broadcastFlag, policeroomFlag, workstationFlag, schoolPlaceFlag, keyPlaceFlag, keyPersonFlag, rentingPersonFlag,
    publicPlaceFlag, popLocationFlag, SetTopBoxFlag;
var checkVideoNode, checkPopNode, checkPopNodeNot, TodyAlarm, reCloseLayerFun, reCloseLayer, reOpenLayerFun;
var TodayVideo, TodayCommunity, TodayHandle, TodayHandleDialog, TodayHandleDialogLayer, TodayHandleDialogLayerClose,
    popLocationData, publicPlaceData, keyPersonHandleLayer, keyPersonHandleFun, keyPersonHandleLayerClose;
eventFlag = true;
streetFlag = true;
communityFlag = false;
gridFlag = false;
openflag = false;


$(function () {

    // 滨海新区 centerCoordinate : [ 117.655020, 39.035450 ],
    // 津南区centerCoordinate : [117.29900836944581,39.03772830963135],
    // http://localhost:6080/arcgis/rest/services/hzd1/ImageServer
    var userId = parent.$('#userid').val();
    if (userId != '1' && userId != undefined) {
        $.ajax({
            type: "get",
            url: ctx + "/org/ccmOrgArea/location?userId=" + userId,
            async: false,
            success: function (data) {
                if (data.areaPoint != '') {
                    var areaPointArr = data.areaPoint.split(',');
                    centerCoordinate = [Number(areaPointArr[0]), Number(areaPointArr[1])];

                    var AreaType = data.type;
                    if (AreaType == '5') {
                        streetFlag = true;
                        communityFlag = false;
                        gridFlag = false;
                        zoomIndex = 13;
                    } else if (AreaType == '6') {
                        streetFlag = false;
                        communityFlag = true;
                        gridFlag = false;
                        zoomIndex = 14;
                    } else if (AreaType == '7') {
                        streetFlag = false;
                        communityFlag = false;
                        gridFlag = true;
                        zoomIndex = 15.5;
                    }
                }
            },
            error: function (err) {
            }
        });
    }


    // 地图默认数据设置
    var defaultPrams = {
        id: 'map',
        centerCoordinate: centerCoordinate,
        zoom: zoomIndex,
        maxZoom: maxZoom,
        minZoom: minZoom,
        baseUrl: baseUrlT,
        zoomShowOrHide: false,// 缩小放大
        // 鹰眼图
        overviewMap: true,
        //比例尺
        scaleLinemapShow: true,
        scaleLineClass: "ol-scale-line ol-custom-scale-line",
        selectPointerFlag: true
    }
    Map = new ArjMap.Map(defaultPrams);
    // 加载地图
    Map.init();

    Pubmap = Map.map;
    // 框选查询初始化
    Map.selectQueryInit();
    window.LayUIMap = Map;// 案事件调用LayUIMap


    // 加载数据
    var PopData = {
        "type": "FeatureCollection",
        "centpoint": ["117.64277015018463", "39.03320166873932"],
        "features": [
            {
                "type": "Feature",
                "id": "4",
                "properties": {
                    "name": "pjq13",
                    "icon": "location48.png",
                    "info": {
                        "设备唯一标识码": "rrrr312351532",
                        "是否授权使用": "20",
                        "设备编号": "4",
                        "登录用户": "pjq13"
                    },
                    "coordinateCentre": ["117.63877015018463",
                        "39.03220166873932"]
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": ["117.63877015018463",
                        "39.03220166873932"]
                }
            }, {
                "type": "Feature",
                "id": "8",
                "properties": {
                    "name": "系统管理员",
                    "icon": "location48.png",
                    "info": {
                        "设备唯一标识码": "aaaa312351532",
                        "是否授权使用": "30",
                        "设备编号": "8",
                        "登录用户": "系统管理员"
                    },
                    "coordinateCentre": ["117.654960", "39.029190"]
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": ["117.654960", "39.029190"]
                }
            }]
    }

    // 视频监控弹框---liveVideo
    /*
	 * $("#popup").on("click", ".click", function() { var type =
	 * $(this).attr('videoType'); var url = $(this).attr('videoUrl');
	 * videoDialog(type, url);
	 *
	 *  }) function videoDialog(type, url) { $('#videoBtn').click();
	 * videoPlay(type, url) }
	 */
    //快捷切换查询
    $('.pubMapDialog-close').click(function () {
        $('.pubMapDialog').hide()
    })

    /*布局改变*/
    $(".relevance-bg").click(function () {
        $(".relevance").animate({"width": "400px", "height": "168px"}, 300)
        $(".relevance-bg").hide();
        $('.unfold').css('bottom', '3px');
        $('.unfold').css('left', '3px')
        $('.map-2').hide();
        $('.map-4').show();
    })
    $(".re-close").click(function () {
        $(".relevance").animate({"width": 0, "height": 0}, 300)
        $(".relevance-bg").show()
        $('.unfold').css('bottom', '3px');
        $('.unfold').css('left', '3px')
        $('.map-4').hide();
        $('.map-2').show();
    })

    // 海康视频
    $("#popup")
        .on(
            "click",
            ".click",
            function () {
                var cameraType = $(this).attr('cameraType');
                /*console.log('...............................',cameraType);
                console.log('...............................',id);*/
                var id = $(this).attr('videoId');

                // 捕获页
                var html = "";

                html += '<div class="layer-common"  style="width: 632px;height: auto; position: relative;padding: 14px 0 0 0; float: left">'

                html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
                html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">视频监控</div>'
                html += '</div>'
                html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; border: 1px solid #10559a;margin-left: 10px;background: url('
                    + ctxStatic
                    + '/common/index/images/showbg.png);background-size: 100% 100%;">'
                html += '<iframe  name="mainFrame" src="'
                    + ctx
                    + '/ccmsys/ccmDevice/getDeviceMap?id='
                    + id
                    + '" style="overflow: visible;" scrolling="yes" frameborder="no" width="870" height="360" allowfullscreen="true" allowtransparency="true"></iframe>'

                html += '</div>'

                html += '</div>'


                layer.open({
                    type: 1,
                    shade: false,
                    title: false, // 不显示标题
                    area: ["850px", "408px"],
                    move: '.layer-common-header',
                    resize: false,
                    fixed: false,
                    id: "videoLayer",
                    content: html,
                    cancel: function () {
                        // 关闭事件
                        // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构',
                        // {time: 5000, icon:6});
                    }
                });
                $('#videoLayer').attr("style", "overflow:hidden");
            })

    function videoDialog(ip, port, username, password) {
        // $('#videoBtn').click();
        // 检查插件是否已经安装过
    }


    // ztree
//	var setting = {
//		check : {
//			enable : true
//		},
//		data : {
//			simpleData : {
//				enable : true
//			}
//		},
//		callback : {
//			onCheck : getCheckedNodes
//		}
//	};

    // ztreeLeft
    var settingLeft = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: getCheckedNodesLeft
        }
    };

//	var zNodes = [ {
//		id : "communityGrid",
//		pId : 0,
//		name : "区域",
//		open : true,
//		checked : true
//	}, {
//		id : "street",
//		pId : "communityGrid",
//		name : "街道",
//		type : 4,
//		icon : ctxStatic + '/modules/map/images/tree_jiedao.png',
//		checked : true
//	}, {
//		id : "community",
//		pId : "communityGrid",
//		name : "社区",
//		type : 1,
//		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
//		checked : true
//	}, {
//		id : "grid",
//		pId : "communityGrid",
//		name : "网格",
//		type : 2,
//		icon : ctxStatic + '/modules/map/images/tree_grid.png',
//		checked : true
//	}, {
//		id : "bulid",
//		pId : "communityGrid",
//		name : "楼栋",
//		type : 0,
//		typeVal : 1,
//		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
//		checked : true
//	}, {
//		id : "keyPerson",
//		pId : 0,
//		name : "重点人员楼栋",
//		open : false,
//		checked : false,
//		type : 1,
//	}, {
//		id : "xingman",
//		pId : "keyPerson",
//		name : "安置帮教人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_shifang.png',
//		flag : "5",
//	}, {
//		id : "shequ",
//		pId : "keyPerson",
//		name : "社区矫正人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_jiaozheng.png',
//		flag : "6",
//	}, {
//		id : "jingshen",
//		pId : "keyPerson",
//		name : "肇事肇祸等严重精神障碍患者",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_psy.png',
//		flag : "2",
//	}, {
//		id : "xidu",
//		pId : "keyPerson",
//		name : "吸毒人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_drug.png',
//		flag : "4",
//	}, {
//		id : "aizi",
//		pId : "keyPerson",
//		name : "艾滋病危险人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_aids.png',
//		flag : "1",
//	}, {
//		id : "shangfang",
//		pId : "keyPerson",
//		name : "重点上访人员",
//		icon : ctxStatic + '/modules/map/images/tree_diqu.png',
//		open : false,
//		checked : false,
//		flag : "8",
//	}, {
//		id : "shejiao",
//		pId : "keyPerson",
//		name : "涉教人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_anquan.png',
//		flag : "9",
//	}, {
//		id : "weixian",
//		pId : "keyPerson",
//		name : "危险品工作人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_drug.png',
//		flag : "10",
//	}, {
//		id : "liushou",
//		pId : "keyPerson",
//		name : "留守人员",
//		icon : ctxStatic + '/modules/map/images/tree_liushou.png',
//		open : false,
//		checked : false,
//		flag : "7",
//	}, {
//		id : "qingshaonian",
//		pId : "keyPerson",
//		name : "重点青少年",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_boy.png',
//		flag : "3",
//	}, {
//		id : "rentingPerson",
//		pId : 0,
//		name : "出租屋楼栋",
//		open : false,
//		checked : false,
//		type : 1,
//	}, {
//		id : "event",
//		pId : 0,
//		name : "案事件",
//		open : false,
//		icon : ctxStatic + '/modules/map/images/tree_event.png',
//		type : 1,
//		checked : false
//	}, {
//		id : "NumUrban",
//		pId : 0,
//		name : "数字城管",
//		type : 0,
//		open : false,
//		checked : false
//	}, {
//		id : "parts",
//		pId : 'NumUrban',
//		name : "城市部件",
//		type : 1,
//		open : false,
//		checked : false
//	}, {
//		id : "lands",
//		pId : 'NumUrban',
//		name : "土地",
//		type : 1,
//		open : false,
//		checked : false
//	}, {
//		id : "NumUrbanA",
//		pId : "parts",
//		name : "井盖",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'a',
//		checked : false
//	}, {
//		id : "NumUrbanB",
//		pId : "parts",
//		name : "交通信号灯",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'b',
//		checked : false
//	}, {
//		id : "NumUrbanC",
//		pId : "parts",
//		name : "交通标志牌",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'c',
//		checked : false
//	}, {
//		id : "NumUrbanD",
//		pId : "parts",
//		name : "停车场",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'd',
//		checked : false
//	}, {
//		id : "NumUrbanE",
//		pId : "parts",
//		name : "健身设施",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'e',
//		checked : false
//	}, {
//		id : "NumUrbanF",
//		pId : "parts",
//		name : "公交站亭",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'f',
//		checked : false
//	}, {
//		id : "NumUrbanG",
//		pId : "parts",
//		name : "垃圾箱",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'g',
//		checked : false
//	}, {
//		id : "NumUrbanH",
//		pId : "parts",
//		name : "存车支架",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'h',
//		checked : false
//	}, {
//		id : "NumUrbanI",
//		pId : "parts",
//		name : "报刊亭",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'i',
//		checked : false
//	}, {
//		id : "NumUrbanJ",
//		pId : "parts",
//		name : "景观灯",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'j',
//		checked : false
//	}, {
//		id : "NumUrbanK",
//		pId : "parts",
//		name : "电力设施",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'k',
//		checked : false
//	}, {
//		id : "NumUrbanL",
//		pId : "parts",
//		name : "监控电子眼",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'l',
//		checked : false
//	}, {
//		id : "NumUrbanM",
//		pId : "parts",
//		name : "行道树",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'm',
//		checked : false
//	}, {
//		id : "NumUrbanN",
//		pId : "parts",
//		name : "路灯",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'n',
//		checked : false
//	}, {
//		id : "NumUrbanO",
//		pId : "parts",
//		name : "邮筒",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'o',
//		checked : false
//	}, {
//		id : "NumUrbanP",
//		pId : "parts",
//		name : "消防栓",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'p',
//		checked : false
//	}, {
//		id : "NumUrbanQ",
//		pId : "parts",
//		name : "消防水源",
//		icon : ctxStatic + '/modules/map/images/tree_security.png',
//		type : 'q',
//		checked : false
//	}, {
//		id : "NumUrbanR",
//		pId : "parts",
//		name : "急避难场所",
//		icon : ctxStatic + '/modules/map/images/tree_fire.png',
//		type : 'r',
//		checked : false
//	}, {
//		id : "video",
//		pId : 'NumUrban',
//		name : "视频监控",
//		type : 1,
//		icon : ctxStatic + '/modules/map/images/tree_xiangji.png',
//		checked : false
//	}, {
//		id : "keyPlace",
//		pId : 0,
//		name : "重点场所",
//		open : false,
//		checked : false
//	}, {
//		id : "school",
//		pId : "keyPlace",
//		name : "学校",
//		type : 32,
//		icon : ctxStatic + '/modules/map/images/tree_schools.png',
//		checked : false
//	}, {
//		id : "safetyProduction",
//		pId : "keyPlace",
//		name : "安全生产重点场所",
//		type : 2,
//		icon : ctxStatic + '/modules/map/images/tree_anquan.png',
//		checked : false
//	}, {
//		id : "fireSafety",
//		pId : "keyPlace",
//		name : "消防安全重点场所",
//		type : 4,
//		icon : ctxStatic + '/modules/map/images/tree_xiaofang.png',
//		checked : false
//	}, {
//		id : "securityFocus",
//		pId : "keyPlace",
//		name : "治安重点场所",
//		type : 8,
//		icon : ctxStatic + '/modules/map/images/tree_zhian.png',
//		checked : false
//	}, {
//		id : "logisticsSafety",
//		pId : "keyPlace",
//		name : "物流安全重点场所",
//		icon : ctxStatic + '/modules/map/images/tree_wuliu.png',
//		type : 1,
//		checked : false
//	}, {
//		id : "others",
//		pId : "keyPlace",
//		name : "其他重点场所",
//		type : 16,
//		icon : ctxStatic + '/modules/map/images/tree_qita.png',
//		checked : false
//	}, {
//		id : "publicPlace",
//		pId : 0,
//		name : "公共机构",
//		open : false,
//		checked : false
//	}, {
//		id : "PublicSecurity",
//		pId : "publicPlace",
//		name : "公安/派出所/警务站",
//		type : '1',
//		icon : ctxStatic + '/modules/map/images/tree_gongan.png',
//		checked : false
//	}, {
//		id : "Hospital",
//		pId : "publicPlace",
//		name : "医院",
//		type : '2',
//		icon : ctxStatic + '/modules/map/images/tree_yiyuan.png',
//		checked : false
//	}, {
//		id : "FireControlPlace",
//		pId : "publicPlace",
//		name : "消防",
//		type : '3',
//		icon : ctxStatic + '/modules/map/images/tree_xiaofang.png',
//		checked : false
//	}, {
//		id : "Library",
//		pId : "publicPlace",
//		name : "图书馆",
//		type : '4',
//		icon : ctxStatic + '/modules/map/images/tree_tushuguan.png',
//		checked : false
//	}, {
//		id : "Stadium",
//		pId : "publicPlace",
//		name : "体育场",
//		icon : ctxStatic + '/modules/map/images/tree_tiyuchang.png',
//		type : '5',
//		checked : false
//	}, {
//		id : "Museum",
//		pId : "publicPlace",
//		name : "博物馆",
//		type : '6',
//		icon : ctxStatic + '/modules/map/images/tree_bowuguan.png',
//		checked : false
//	}, {
//		id : "WateSupply",
//		pId : "publicPlace",
//		name : "供水",
//		icon : ctxStatic + '/modules/map/images/tree_gongshui.png',
//		type : '7',
//		checked : false
//	}, {
//		id : "PowerSupply",
//		pId : "publicPlace",
//		name : "供电",
//		type : '8',
//		icon : ctxStatic + '/modules/map/images/tree_gongdian.png',
//		checked : false
//	}, {
//		id : "PopulationDensity",
//		pId : 0,
//		name : "人口密度",
//		open : false,
//		type : 1,
//		checked : false
//	}, {
//		id : "PopLocation",
//		pId : 0,
//		name : "实时定位",
//		open : false,
//		type : 1,
//		checked : false
//	}, {
//		id : "keyPersonCountry",
//		pId : 0,
//		name : "重点人员分布",
//		open : false,
//		checked : false,
//		type : 1,
//	}, {
//		id : "aiziCountry",
//		pId : "keyPersonCountry",
//		name : "艾滋病危险人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_aids.png',
//		flag : "1",
//	}, {
//		id : "jingshenCountry",
//		pId : "keyPersonCountry",
//		name : "肇事肇祸等严重精神障碍患者",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_psy.png',
//		flag : "2",
//	}, {
//		id : "qingshaonianCountry",
//		pId : "keyPersonCountry",
//		name : "重点青少年",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_boy.png',
//		flag : "3",
//	}, {
//		id : "xiduCountry",
//		pId : "keyPersonCountry",
//		name : "吸毒人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_drug.png',
//		flag : "4",
//	}, {
//		id : "xingmanCountry",
//		pId : "keyPersonCountry",
//		name : "安置帮教人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_shifang.png',
//		flag : "5",
//	}, {
//		id : "shequCountry",
//		pId : "keyPersonCountry",
//		name : "社区矫正人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_jiaozheng.png',
//		flag : "6",
//	}, {
//		id : "liushouCountry",
//		pId : "keyPersonCountry",
//		name : "留守人员",
//		icon : ctxStatic + '/modules/map/images/tree_liushou.png',
//		open : false,
//		checked : false,
//		flag : "7",
//	}, {
//		id : "shangfangCountry",
//		pId : "keyPersonCountry",
//		name : "重点上访人员",
//		icon : ctxStatic + '/modules/map/images/tree_diqu.png',
//		open : false,
//		checked : false,
//		flag : "8",
//	}, {
//		id : "shejiaoCountry",
//		pId : "keyPersonCountry",
//		name : "涉教人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_anquan.png',
//		flag : "9",
//	}, {
//		id : "weixianCountry",
//		pId : "keyPersonCountry",
//		name : "危险品工作人员",
//		open : false,
//		checked : false,
//		icon : ctxStatic + '/modules/map/images/tree_drug.png',
//		flag : "10",
//	}, {
//		id : "keyPersonHandle",
//		pId : 0,
//		name : "重点人员管控",
//		open : false,
//		checked : false,
//		type : 1,
//	} ];


    /*--------------------------------------------------区域------------------------------------------------*/
    var zNodesleft = [{
        id: "communityGrid",
        pId: 0,
        name: "区域",
        open: true,
        checked: true
    }, {
        id: "street",
        pId: "communityGrid",
        name: "街道",
        type: 4,
        icon: ctxStatic + '/modules/map/images/tree_jiedao.png',
        checked: true
    }, {
        id: "community",
        pId: "communityGrid",
        name: "社区",
        type: 1,
        icon: ctxStatic + '/modules/map/images/tree_shequ.png',
        checked: true
    }, {
        id: "grid",
        pId: "communityGrid",
        name: "网格",
        type: 2,
        icon: ctxStatic + '/modules/map/images/tree_grid.png',
        checked: true
    }
        /*	, {
		id : "build",
		pId : "communityGrid",
		name : "楼栋",
		type : 0,
		typeVal : 1,
		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
		checked : true
	}*/
    ];


    var communityGridVal = 0;
    var streetVal = 0;
    var communityVal = 0;
    var gridVal = 0;
    var buildVal = 0;
    var eventVal = 0;
    var partsVal = '';// 城市部件
    var landsVal = 0;// 土地
    var videoVal = 0;
    var PopulationDensityVal = 0;
    var schoolPlaceVal = 0;
    var keyPlaceVal = 0;
    var keyPersonVal = "";
    var keyPersonValCon = "";
    var rentingPersonVal = 0;
    var publicPlaceVal = "";
    var popLocationVal = 0
    var keyPersonHandleVal = 0;

    eventFlag = true;
    // streetFlag = true;
    // communityFlag = false;
    // gridFlag = false;
    buildFlag = false;
    vccmorgFlag = false;
    partsFlag = true;
    landsFlag = true;// 土地
    videoFlag = true;
    PopulationDensityFlag = true;
    schoolPlaceFlag = true;
    keyPlaceFlag = true;
    keyPersonFlag = true;
    keyPersonFlagCon = true;
    rentingPersonFlag = true;
    publicPlaceFlag = true;
    popLocationFlag = true;

    // 数值对比
    function isEqual(a, b) {
        if (a == b) {
            return false
        } else {
            return a = b;
        }
    }

    var PopLocationTime;


    $.fn.zTree.init($("#treeLeft"), settingLeft, zNodesleft);
    zTreeObjLeft = $.fn.zTree.getZTreeObj("treeLeft");

//	$.fn.zTree.init($("#tree"), setting, zNodes);
//	var zTreeObj = $.fn.zTree.getZTreeObj("tree");


    //点击今日事件勾选视频监控
    checkVideoNode = function () {

//		var videoNode = zTreeObj.getNodeByParam("id", "video", null);
//		zTreeObj.checkNode(videoNode, true);
//		videoData = 1
//		getCheckedNodes();
        /*
		 * Map.removeLayer('videos'); $.getJSON('' + ctx +
		 * '/sys/map/deviceiveMap', function(data) { videoVal = videoData=1;
		 * Map.addJSON1([ { 'type' : 'videos', 'data' : data, 'isShow' :
		 * videoFlag } ]) })
		 */
        $('#VideoFlag').click();
        shipinjiankongFlag = false;
    }

    checkPopNode = function () {
        var popNode = zTreeObj.getNodeByParam("id", "PopLocation", null);
        zTreeObj.checkNode(popNode, true);
        popLocationData = 1

        var PublicSecurityNode = zTreeObj.getNodeByParam("id",
            "PublicSecurity", null);
        zTreeObj.checkNode(PublicSecurityNode, true);
        publicPlaceData = "1";
        // getCheckedNodes();
    }
    checkPopNodeNot = function () {
        var popNode = zTreeObj.getNodeByParam("id", "PopLocation", null);
        zTreeObj.checkNode(popNode, false);
        popLocationData = 0
        var PublicSecurityNode = zTreeObj.getNodeByParam("id",
            "PublicSecurity", null);
        zTreeObj.checkNode(PublicSecurityNode, false);
        publicPlaceData = "";
        // getCheckedNodes();
    }


    //getCheckedNodes();
    /* 特殊人群定位 */
    PopLocation()

    /* 特殊人群定位 */
    function getCheckedNodes() {

        var checked = "";
        var checkedNodes = zTreeObj.getCheckedNodes(true);
        if (checkedNodes.length != 0) {
            var communityGridData = 0;
            var streetData = 0;
            var communityData = 0;
            var gridData = 0;
            var buildData = 0;
            var eventData = 0;
            var partsData = '';
            var landsData = 0;// 土地
            var videoData = 0;
            var PopulationDensityData = 0;
            var schoolPlaceData = 0;
            var keyPlaceData = 0;
            var keyPersonData = 0;
            var keyPersonDataCon = 0;
            var keyPersonStr = "";
            var keyPersonStrCon = "";
            var rentingPersonData = 0;
            publicPlaceData = "";
            popLocationData = 0
            var keyPersonHandleData = 0;

            for (var i = 0; i < checkedNodes.length; i++) {
                if (!checkedNodes[i].isParent) {
                    checked += checkedNodes[i].id + ",";
                    if (checkedNodes[i].pId == 'communityGrid') { // 街道社区网格
                        communityGridData += parseInt(checkedNodes[i].type);
                        if (checkedNodes[i].id == 'bulid') { // 楼栋
                            buildData += parseInt(checkedNodes[i].typeVal)
                        }
                    } else if (checkedNodes[i].id == 'event') { // 案事件
                        eventData += parseInt(checkedNodes[i].type)
                    } else if (checkedNodes[i].pId == 'parts') { // 城市部件
                        partsData += checkedNodes[i].type;

                    } else if (checkedNodes[i].id == 'lands') { // 土地
                        landsData += parseInt(checkedNodes[i].type);

                    } else if (checkedNodes[i].id == 'video') { // 视频监控
                        videoData += parseInt(checkedNodes[i].type);

                    } else if (checkedNodes[i].pId == 'schoolPlace') { // 学校
                        schoolPlaceData += parseInt(checkedNodes[i].type);
                    } else if (checkedNodes[i].pId == 'keyPlace') { // 重点场所
                        keyPlaceData += parseInt(checkedNodes[i].type);
                    } else if (checkedNodes[i].pId == 'keyPerson') { // 重点人员楼栋

                        // keyPersonData += parseInt(checkedNodes[i].type);
                        keyPersonStr += checkedNodes[i].flag + ","
                    } else if (checkedNodes[i].pId == 'keyPersonCountry') { // 重点人员分布

                        // keyPersonData += parseInt(checkedNodes[i].type);
                        keyPersonStrCon += checkedNodes[i].flag + ","
                    } else if (checkedNodes[i].id == 'rentingPerson') { // 出租屋楼栋
                        rentingPersonData += parseInt(checkedNodes[i].type);
                    } else if (checkedNodes[i].pId == 'publicPlace') { // 公共机构
                        publicPlaceData += checkedNodes[i].type;
                    } else if (checkedNodes[i].id == 'PopulationDensity') {
                        PopulationDensityData += parseInt(checkedNodes[i].type);
                    } else if (checkedNodes[i].id == 'keyPersonHandle') {

                        keyPersonHandleData += parseInt(checkedNodes[i].type);
                    } else if (checkedNodes[i].id == 'PopLocation') {

                        popLocationData += parseInt(checkedNodes[i].type);
                    }

                    /*
					 * if(checkedNodes[i].id == 'PopLocation'){
					 *
					 * popLocationFlag=true
					 * Map.layersIsShow('PopLocation',true); }else{
					 *
					 * popLocationFlag=false
					 * Map.layersIsShow('PopLocation',false); }
					 */

                }
            }
            if (communityGridVal != communityGridData && communityGridData != 0) {
                communityGridVal = communityGridData;
                // alert('社区网格图层加载' + communityGridVal)
                Map.removeLayer('streets');
                Map.removeLayer('communitys');
                Map.removeLayer('grids');
                if (communityGridVal == '1') {

                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
                        + communityGridVal + '', function (data) {
                        Map.addJSON1([{
                            'type': 'communitys',
                            'data': data,
                            'isShow': communityFlag
                        }])

                    })
                    console.log()
                } else if (communityGridVal == '2') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
                        + communityGridVal + '', function (data) {
                        Map.addJSON1([{
                            'type': 'grids',
                            'data': data,
                            'isShow': gridFlag
                        }])

                    })
                } else if (communityGridVal == '4') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
                        + communityGridVal + '', function (data) {
                        console.log(data)
                        Map.addJSON1([{
                            'type': 'streets',
                            'data': data,
                            'isShow': streetFlag
                        }])

                    })
                } else if (communityGridVal == '3') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'communitys',
                                'data': data,
                                'isShow': communityFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'grids',
                                'data': data,
                                'isShow': gridFlag
                            }])

                        })
                } else if (communityGridVal == '5') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'communitys',
                                'data': data,
                                'isShow': communityFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'streets',
                                'data': data,
                                'isShow': streetFlag
                            }])

                        })

                } else if (communityGridVal == '6') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'grids',
                                'data': data,
                                'isShow': gridFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'streets',
                                'data': data,
                                'isShow': streetFlag
                            }])

                        })

                } else if (communityGridVal == '7') {

                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1 ',
                        function (data) {

                            Map.addJSON1([{
                                'type': 'communitys',
                                'data': data,
                                'isShow': communityFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'grids',
                                'data': data,
                                'isShow': gridFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'streets',
                                'data': data,
                                'isShow': streetFlag
                            }])

                        })

                }

            } else if (communityGridVal != communityGridData
                && communityGridData == 0) {
                communityGridVal = communityGridData;
                // alert('社区网格图层清除')
                Map.removeLayer('streets');
                Map.removeLayer('communitys');
                Map.removeLayer('grids');
            } else {
                // alert('社区网格图层不变')
            }
            if (buildVal != buildData && buildData != 0) {
                buildVal = buildData;
                // alert('案事件图层加载')
                Map.removeLayer('builds');

                $.getJSON('' + ctx + '/sys/map/buildmanageMap', function (data) {
                    Map.addJSON1([{
                        'type': 'builds',
                        'data': data,
                        'isShow': buildFlag,
                    }])
                })

            } else if (buildVal != buildData && buildData == 0) {
                buildVal = buildData;
                // alert('案事件图层清除')
                Map.removeLayer('builds');
            } else {
                // alert('案事件图层不变')
            }

            if (eventVal != eventData && eventData != 0) {
                eventVal = eventData;
                // alert('案事件图层加载')
                Map.removeLayer('events');

                $.getJSON('' + ctx + '/sys/map/eventIncidentMap?type='
                    + eventVal + '', function (data) {
                    Map.addJSON1([{
                        'type': 'events',
                        'data': data,
                        'isShow': eventFlag,
                    }])
                })

            } else if (eventVal != eventData && eventData == 0) {
                eventVal = eventData;
                // alert('案事件图层清除')
                Map.removeLayer('events');
            } else {
                // alert('案事件图层不变')
            }
            if (partsVal != partsData && partsData != '') {
                partsVal = partsData;
                // alert('城市部件加载')
                Map.removeLayer('parts');
                $.getJSON('' + ctx + '/sys/map/cityComponentsMap?type='
                    + partsVal + '', function (data) {
                    Map.addJSON1([{
                        'type': 'parts',
                        'data': data,
                        'isShow': partsFlag
                    }])
                })

            } else if (partsVal != partsData && partsData == '') {
                partsVal = partsData;
                Map.removeLayer('parts');
                // alert('城市部件清除')
            } else {
                // alert('城市部件不变')
            }

            if (landsVal != landsData && landsData != 0) {
                landsVal = landsData;
                // alert('土地加载')
                Map.removeLayer('lands');
                $.getJSON('' + ctx + '/sys/map/landMap', function (data) {
                    Map.addJSON1([{
                        'type': 'lands',
                        'data': data,
                        'isShow': landsFlag
                    }])
                })

            } else if (landsVal != landsData && landsData == 0) {
                landsVal = landsData;
                Map.removeLayer('lands');
                // alert('土地清除')
            } else {
                // alert('土地不变')
            }

            if (videoVal != videoData && videoData != 0) {

                videoVal = videoData;
                // alert('视频监控加载')
                Map.removeLayer('videos');
                $.getJSON('' + ctx + '/sys/map/deviceiveMap', function (data) {
                    Map.addJSON1([{
                        'type': 'videos',
                        'data': data,
                        'isShow': videoFlag
                    }])
                })

            } else if (videoVal != videoData && videoData == 0) {

                videoVal = videoData;
                Map.removeLayer('videos');
                // alert('视频监控清除')
            } else {

                // alert('视频监控不变')
            }
            if (PopulationDensityVal != PopulationDensityData
                && PopulationDensityData != 0) {
                PopulationDensityVal = PopulationDensityData;
                // alert('人口密度')

                /* 热力图 */
                $.getJSON('' + ctx + '/sys/map/buildHeatMap', function (
                    heatmapData) {
                    Map.removeLayer('heatMap')
                    Map.heatMap1(heatmapData);
                    Map.layersIsShow('heatMap', true);
                    // //Map.layersIsShow('heatMap',true);

                })

            } else if (PopulationDensityVal != PopulationDensityData
                && PopulationDensityData == 0) {
                PopulationDensityVal = PopulationDensityData;
                Map.removeLayer('heatMap')
                // alert('人口密度')
            } else {
                // alert('人口密度')
            }

            function GetPopLocation() {
                Map.removeLayer('PopLocation');
                $.getJSON('' + ctx + '/sys/map/deviceMobileMap',
                    function (data) {
                        Map.addJSON1([{
                            'type': 'PopLocation',
                            'data': data,
                            'isShow': popLocationFlag
                        }]);
                    })

                Map.layersIsShow('PopLocation', true);
            }

            // 重点人员管控

            if (keyPersonHandleVal != keyPersonHandleData
                && keyPersonHandleData != 0) {

                keyPersonHandleVal = keyPersonHandleData;
                // alert('重点人员管控')
                checkPopNode()
                $.getJSON(
                    '' + ctx + '/sys/map/ccmPatrolPointPlanMap',
                    function (data) {
                        var lenData = data.length;
                        var color = ["#62c000", "#8e7513",
                            "#3268ff", "#e73400", "#72fffd",
                            "#c4c501", "#ffff80", "#fd6d01",
                            "#951167", "#e822e7", "#804040"]
                        if (lenData > 0) {

                            for (var i = 0; i < lenData; i++) {
                                var str = data[i].value;
                                var routeCoords = str.split(';')
                                var len = routeCoords.length;
                                var id = data[i].type
                                var colorLine = color[i]
                                Map.removeLayer('' + id + '');
                                var pushArr = []
                                for (var j = 0; j < len; j++) {
                                    var value = [];
                                    value[0] = Number(routeCoords[j]
                                        .split(',')[0]);
                                    value[1] = Number(routeCoords[j]
                                        .split(',')[1]);
                                    pushArr.push(value);
                                }
                                Map.trackReplayLu(' ', 5, pushArr,
                                    id, colorLine);
                            }
                        }

                    })
                keyPersonHandleLayerFun()

            } else if (keyPersonHandleVal != keyPersonHandleData
                && keyPersonHandleData == 0) {

                keyPersonHandleVal = keyPersonHandleData;
                checkPopNodeNot()
                $.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap',
                    function (data) {
                        var len = data.length;
                        if (len > 0) {
                            for (var i = 0; i < len; i++) {
                                var id = data[i].type
                                Map.removeLayer('' + id + '');
                            }
                        }
                    })
                keyPersonHandleLayerClose()
                // alert('重点人员管控')
            } else {
                // alert('重点人员管控')
            }

            if (popLocationVal != popLocationData && popLocationData != 0) {
                popLocationVal = popLocationData;
                // alert('实时定位')
                // 实时定位
                GetPopLocation()
                PopLocationTime = setInterval(function () // 开启循环：每秒出现一次提示框
                {
                    GetPopLocation()
                }, 15000);

            } else if (popLocationVal != popLocationData
                && popLocationData == 0) {
                popLocationVal = popLocationData;
                Map.removeLayer('ElectronicFence');
                Map.removeLayer('trackReplay');
                Map.removeLayer('PopLocation');

                clearInterval(PopLocationTime);
                // alert('实时定位')
            } else {
                // alert('实时定位')
            }

            if (schoolPlaceVal != schoolPlaceData && schoolPlaceData != 0) {
                schoolPlaceVal = schoolPlaceData;
                // alert('重点场所加载')
                Map.removeLayer('schoolPlace');
                $.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type='
                    + schoolPlaceVal + '', function (data) {
                    Map.addJSON1([{
                        'type': 'schoolPlace',
                        'data': data,
                        'isShow': schoolPlaceFlag
                    }])

                })
            } else if (schoolPlaceVal != schoolPlaceData && schoolPlaceData == 0) {
                schoolPlaceVal = schoolPlaceData;
                Map.removeLayer('schoolPlace');
                // alert('重点场所清除')
            } else {
                // alert('重点场所不变')
            }


            if (keyPlaceVal != keyPlaceData && keyPlaceData != 0) {
                keyPlaceVal = keyPlaceData;
                // alert('重点场所加载')
                Map.removeLayer('keyPlace');
                $.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type='
                    + keyPlaceVal + '', function (data) {
                    Map.addJSON1([{
                        'type': 'keyPlace',
                        'data': data,
                        'isShow': keyPlaceFlag
                    }])

                })
            } else if (keyPlaceVal != keyPlaceData && keyPlaceData == 0) {
                keyPlaceVal = keyPlaceData;
                Map.removeLayer('keyPlace');
                // alert('重点场所清除')
            } else {
                // alert('重点场所不变')
            }

            keyPersonStr = keyPersonStr.substring(0, keyPersonStr.length - 1)
            if (keyPersonStr != "" && keyPersonVal != keyPersonStr) {
                // alert('重点人员加载')

                keyPersonVal = keyPersonStr;
                $.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=1&flag='
                    + keyPersonStr, function (data) {
                    Map.removeLayer('keyPerson');
                    Map.addJSON1([{
                        'type': 'keyPerson',
                        'data': data,
                        'isShow': keyPersonFlag
                    }])
                })
            } else if (keyPersonStr == "" && keyPersonVal != keyPersonStr) {
                // alert('重点人员清除')
                keyPersonVal = keyPersonStr;
                Map.removeLayer('keyPerson');
            } else {

                // alert('重点人员不变')
            }

            // 重点人员分布
            keyPersonStrCon = keyPersonStrCon.substring(0,
                keyPersonStrCon.length - 1)
            if (keyPersonStrCon != "" && keyPersonValCon != keyPersonStrCon) {
                // alert('重点人员分布')

                keyPersonValCon = keyPersonStrCon;
                $.getJSON('' + ctx + '/sys/map/buildHeatMap?type='
                    + keyPersonStrCon, function (data) {
                    Map.removeLayer('heatMap')
                    /* 热力图 */
                    Map.heatMap1(data);
                    Map.layersIsShow('heatMap', true);
                })
            } else if (keyPersonStrCon == ""
                && keyPersonValCon != keyPersonStrCon) {
                // alert('重点人员分布清除')
                keyPersonValCon = keyPersonStrCon;
                Map.removeLayer('heatMap')
            } else {
                // alert('重点人员分布不变')
            }

            if (rentingPersonVal != rentingPersonData && rentingPersonData != 0) {
                rentingPersonVal = rentingPersonData;
                // alert('出租屋楼栋加载')
                Map.removeLayer('rentingPerson');

                $.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=2',
                    function (data) {
                        Map.addJSON1([{
                            'type': 'rentingPerson',
                            'data': data,
                            'isShow': rentingPersonFlag
                        }])
                    })
            } else if (rentingPersonVal != rentingPersonData
                && rentingPersonData == 0) {
                rentingPersonVal = rentingPersonData;
                Map.removeLayer('rentingPerson');
                // alert('出租屋楼栋清除')
            } else {
                // alert('出租屋楼栋不变')
            }

            if (publicPlaceVal != publicPlaceData && publicPlaceData != "") {
                publicPlaceVal = publicPlaceData;
                // alert('公共机构加载')
                Map.removeLayer('publicPlace');
                $.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type='
                    + publicPlaceVal + '', function (data) {
                    Map.addJSON1([{
                        'type': 'publicPlace',
                        'data': data,
                        'isShow': publicPlaceFlag
                    }])
                })
            } else if (publicPlaceVal != publicPlaceData
                && publicPlaceData == "") {

                publicPlaceVal = publicPlaceData;
                Map.removeLayer('publicPlace');
                Map.clearOverlays()
                // alert('公共机构清除')
            } else {

                // alert('公共机构不变')
            }

        } else {

            Map.clearOverlays();
            // alert('清空图层')
            /*Map.removeLayer('communitys');
			Map.removeLayer('streets');
			Map.removeLayer('grids');
			Map.removeLayer('builds');*/
            Map.removeLayer('events');
            Map.removeLayer('parts');
            Map.removeLayer('lands');// 土地
            Map.removeLayer('videos');
            Map.removeLayer('schoolPlace');
            Map.removeLayer('keyPlace');
            Map.removeLayer('keyPerson');
            Map.removeLayer('rentingPerson');
            Map.removeLayer('publicPlace');
            Map.layersIsShow('heatMap', false);
            Map.layersIsShow('PopLocation', false);
            Map.removeLayer('ElectronicFence');
            Map.removeLayer('trackReplay');
            Map.removeLayer('PopLocation');
            Map.removeLayer('heatMap');

            $.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap', function (
                data) {
                var len = data.length;
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        var id = data[i].type
                        Map.removeLayer('' + id + '');
                    }
                }
            })
            clearInterval(PopLocationTime);
            PopulationDensityVal = 0
            communityGridVal = 0;
            eventVal = 0;
            buildtVal = 0;
            partsVal = '';
            landsVal = 0;
            videoVal = 0;
            PopulationDensityVal = 0;
            schoolPlaceVal = 0;
            keyPlaceVal = 0;
            keyPersonVal = "";
            keyPersonHandleVal = 0
            rentingPersonVal = 0;
            publicPlaceVal = "";
            popLocationVal = 0;
        }

    }


    getCheckedNodesLeft();

    function getCheckedNodesLeft() {
        var checked = "";
        var checkedNodes = zTreeObjLeft.getCheckedNodes(true);
        if (checkedNodes.length != 0) {
            var communityGridData = 0;
            var streetData = 0;
            var communityData = 0;
            var gridData = 0;
            var buildData = 0;


            for (var i = 0; i < checkedNodes.length; i++) {
                if (!checkedNodes[i].isParent) {
                    checked += checkedNodes[i].id + ",";
                    if (checkedNodes[i].pId == 'communityGrid') { // 街道社区网格
                        communityGridData += parseInt(checkedNodes[i].type);
                        if (checkedNodes[i].id == 'build') { // 楼栋
                            buildData += parseInt(checkedNodes[i].typeVal)
                        }
                    }


                }
            }
            if (communityGridVal != communityGridData && communityGridData != 0) {
                communityGridVal = communityGridData;
                // alert('社区网格图层加载' + communityGridVal)
                Map.removeLayer('streets');
                Map.removeLayer('communitys');
                Map.removeLayer('grids');
                if (communityGridVal == '1') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=' + communityGridVal + '', function (data) {
                        Map.addJSON1([{
                            'type': 'communitys',
                            'data': data,
                            'isShow': communityFlag
                        }])

                    })
                    console.log()
                } else if (communityGridVal == '2') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
                        + communityGridVal + '', function (data) {
                        Map.addJSON1([{
                            'type': 'grids',
                            'data': data,
                            'isShow': gridFlag
                        }])

                    })
                } else if (communityGridVal == '4') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
                        + communityGridVal + '', function (data) {
                        console.log(data)
                        Map.addJSON1([{
                            'type': 'streets',
                            'data': data,
                            'isShow': streetFlag
                        }])

                    })
                } else if (communityGridVal == '3') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'communitys',
                                'data': data,
                                'isShow': communityFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'grids',
                                'data': data,
                                'isShow': gridFlag
                            }])

                        })
                } else if (communityGridVal == '5') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'communitys',
                                'data': data,
                                'isShow': communityFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'streets',
                                'data': data,
                                'isShow': streetFlag
                            }])

                        })

                } else if (communityGridVal == '6') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'grids',
                                'data': data,
                                'isShow': gridFlag
                            }])

                        })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
                        function (data) {
                            Map.addJSON1([{
                                'type': 'streets',
                                'data': data,
                                'isShow': streetFlag
                            }])

                        })

                } else if (communityGridVal == '7') {
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1', function (data) {
                        Map.addJSON1([{
                            'type': 'communitys',
                            'data': data,
                            'isShow': communityFlag
                        }])
                    })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2', function (data) {
                        Map.addJSON1([{
                            'type': 'grids',
                            'data': data,
                            'isShow': gridFlag
                        }])
                    })
                    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4', function (data) {
                        Map.addJSON1([{
                            'type': 'streets',
                            'data': data,
                            'isShow': streetFlag
                        }])
                    })
                }

            } else if (communityGridVal != communityGridData
                && communityGridData == 0) {
                communityGridVal = communityGridData;
                // alert('社区网格图层清除')
                Map.removeLayer('streets');
                Map.removeLayer('communitys');
                Map.removeLayer('grids');
            } else {
                // alert('社区网格图层不变')
            }
            if (buildVal != buildData && buildData != 0) {
                buildVal = buildData;
                // alert('案事件图层加载')
                Map.removeLayer('builds');

                $.getJSON('' + ctx + '/sys/map/buildmanageMap', function (data) {
                    Map.addJSON1([{
                        'type': 'builds',
                        'data': data,
                        'isShow': buildFlag,
                    }])
                })

            } else if (buildVal != buildData && buildData == 0) {
                buildVal = buildData;
                // alert('案事件图层清除')
                Map.removeLayer('builds');
            } else {
                // alert('案事件图层不变')
            }


        } else {

            Map.clearOverlays();
            // alert('清空图层')
            Map.removeLayer('communitys');
            Map.removeLayer('streets');
            Map.removeLayer('grids');
            Map.removeLayer('builds');
            $.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap', function (
                data) {
                var len = data.length;
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        var id = data[i].type
                        Map.removeLayer('' + id + '');
                    }
                }
            })
            clearInterval(PopLocationTime);
            communityGridVal = 0;
            buildtVal = 0;
        }

    }


    // 监听地图层级变化
    $.getJSON('' + ctx + '/sys/sysConfig/getMapLevelData', function (
        data) {
        map_level = data;
        // checkZoom(map_level);
        var map = Map.map;// change:resolution
        map.getView().on('change:resolution', checkZoom);// checkZoom为调用的函数
        // 案事件change:resolution
        function checkZoom() {
            // console.log(map.getView().getZoom());
            var zoom = map.getView().getZoom();
            if (map.getView().getZoom() <= Number(map_level.quXian)) {
                Map.layersIsShow('communitys', false);
                Map.layersIsShow('streets', true);
                Map.layersIsShow('grids', false);
                Map.layersIsShow('builds', false);
                Map.layersIsShow('schoolPlace', false);
                Map.layersIsShow('keyPlace', false);
                Map.layersIsShow('events', false);
                Map.layersIsShow('keyPerson', false);
                Map.layersIsShow('rentingPerson', false);
                Map.layersIsShow('lands', false);
                //Map.layersIsShow('videos', false);
                // Map.layersIsShow('heatMap', true);
                // Map.layersIsShow('PopLocation',false);
                Map.layersIsShow('parts', false);
                Map.layersIsShow('publicPlace', false);
                Map.layersIsShow('jingwushi', false);
                Map.layersIsShow('gongzuozhan', false);
                communityFlag = false;
                streetFlag = true;
                gridFlag = false;
                buildFlag = false;
                vccmorgFlag = false;
                eventFlag = false;
                partsFlag = false;
                landsFlag = false;
                videoFlag = true;
                PopulationDensityFlg = false;
                schoolPlaceFlag = false;
                keyPlaceFlag = false;
                keyPersonFlag = false;
                rentingPersonFlag = false;
                publicPlaceFlag = false;

                // Map.clearOverlays();

            } else if (map.getView().getZoom() <= Number(map_level.jieDaoMax)
                && map.getView().getZoom() > Number(map_level.jieDaoMin)) {
                Map.layersIsShow('communitys', true);
                Map.layersIsShow('streets', false);
                Map.layersIsShow('grids', false);
                Map.layersIsShow('builds', false);
                Map.layersIsShow('schoolPlace', false);
                Map.layersIsShow('keyPlace', false);
                Map.layersIsShow('events', true);
                Map.layersIsShow('keyPerson', false);
                Map.layersIsShow('rentingPerson', false);
                Map.layersIsShow('lands', false);
                //Map.layersIsShow('videos', false);
                // Map.layersIsShow('heatMap', true);
                // Map.layersIsShow('PopLocation',true);
                Map.layersIsShow('parts', false);
                Map.layersIsShow('publicPlace', false);
                Map.layersIsShow('jingwushi', false);
                Map.layersIsShow('gongzuozhan', false);
                $.each(idArrjingwushi2, function (index, val) {
                    Pubmap.removeOverlay(Map['' + val + 'Overlay'])
                });
                communityFlag = true;
                streetFlag = false;
                gridFlag = false;
                buildFlag = false;
                vccmorgFlag = false;
                eventFlag = true;
                partsFlag = false;
                landsFlag = false;
                videoFlag = true;
                PopulationDensityFlg = true;
                schoolPlaceFlag = false;
                keyPlaceFlag = false;
                keyPersonFlag = false;
                rentingPersonFlag = false;
                publicPlaceFlag = false;

                // Map.clearOverlays();

            } else if (map.getView().getZoom() > Number(map_level.sheQuMin)
                && map.getView().getZoom() <= Number(map_level.sheQuMax)) {
                Map.layersIsShow('communitys', false);
                Map.layersIsShow('streets', false);
                Map.layersIsShow('grids', true);
                Map.layersIsShow('builds', false);
                Map.layersIsShow('schoolPlace', false);
                Map.layersIsShow('keyPlace', false);
                Map.layersIsShow('events', true);
                Map.layersIsShow('keyPerson', true);
                Map.layersIsShow('rentingPerson', true);
                Map.layersIsShow('lands', true);
                //Map.layersIsShow('videos', true);
                // Map.layersIsShow('heatMap', true);
                // Map.layersIsShow('PopLocation',true);
                Map.layersIsShow('parts', false);
                Map.layersIsShow('publicPlace', false);
                Map.layersIsShow('jingwushi', true);
                Map.layersIsShow('gongzuozhan', true);
                communityFlag = false;
                streetFlag = false;
                gridFlag = true;
                buildFlag = false;
                vccmorgFlag = true;
                eventFlag = true;
                partsFlag = false;
                landsFlag = true;
                videoFlag = true;
                PopulationDensityFlg = true;
                schoolPlaceFlag = true;
                keyPlaceFlag = true;
                keyPersonFlag = false;
                rentingPersonFlag = true;
                publicPlaceFlag = false;
                // Map.clearOverlays();

            } else if (map.getView().getZoom() > Number(map_level.wangGe)) {
                Map.layersIsShow('communitys', false);
                Map.layersIsShow('streets', false);
                Map.layersIsShow('grids', false);
                Map.layersIsShow('builds', true);
                Map.layersIsShow('schoolPlace', true);
                Map.layersIsShow('keyPlace', true);
                Map.layersIsShow('events', true);
                Map.layersIsShow('keyPerson', true);
                Map.layersIsShow('rentingPerson', true);
                Map.layersIsShow('lands', true);
                Map.layersIsShow('videos', true);
                Map.layersIsShow('parts', true);
                Map.layersIsShow('publicPlace', true);
                Map.layersIsShow('PopLocation', true);
                Map.layersIsShow('jingwushi', true);
                Map.layersIsShow('gongzuozhan', true);
                communityFlag = false;
                streetFlag = false;
                gridFlag = false;
                buildFlag = true;
                vccmorgFlag = true;
                eventFlag = true;
                partsFlag = true;
                landsFlag = true;
                videoFlag = true;
                schoolPlaceFlag = true;
                keyPlaceFlag = true;
                keyPersonFlag = true;
                rentingPersonFlag = true;
                publicPlaceFlag = true;
                // Map.clearOverlays();

            }
        }

    })


    keyPersonHandleLayerFun = function () {

        var html = "";
        html += '<div class="layer-common" style=" width: 97.75%;;height: 95.3%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">重点人员管控</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%; overflow: hidden; border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '	<iframe  name="mainFrame" src="'
            + ctx
            + '/event/ccmEventIncident/findEventMapJump" style="overflow: hidden;" scrolling="yes" frameborder="no" width="980" height="630" allowfullscreen="true" allowtransparency="true"></iframe>'
        html += '</div>'
        html += '</div>'
        keyPersonHandleLayer = layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["1000px", "650px"],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            content: html,
            id: 'keyPersonHandleLayer',
            cancel: function () {
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });
    }
    keyPersonHandleLayerClose = function () {
        layer.close(keyPersonHandleLayer)
    }

    /*	function TodayVideo(id) {
		var ccmDeviceIncidentId = $('#ccmDeviceIncidentId').val()
		var html = "";
		html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
		html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
		html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">视频监控</div>'
		html += '</div>'
		html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
				+ ctxStatic
				+ '/common/index/images/showbg.png);background-size: 100% 100%;">'
		html += '	<iframe  name="mainFrame" src="'
				+ ctx
				+ '/ccmsys/ccmDevice/getDeviceMap?id='
				+ ccmDeviceIncidentId
				+ '" style="overflow: visible;" scrolling="yes" frameborder="no" width="570" height="360" allowfullscreen="true" allowtransparency="true"></iframe>'
		html += '</div>'
		html += '</div>'
		layer.open({
			type : 1,
			shade : false,
			title : false, // 不显示标题
			area : [ "600px", "400px" ],
			offset : [ '70px', '1300px' ],
			move : '.layer-common-header',
			resize : false,
			fixed : false,
			content : html,
			id : 'TodayVideoLayer',
			cancel : function() {
				// 关闭事件
				// layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
				// icon:6});
			}
		});
	}*/

    TodayCommunity = function () {
        var areaIncidentName = $('#areaIncidentName').val()
        var ccmOrgAreaIncidentNetManName = $('#ccmOrgAreaIncidentNetManName')
            .val()
        var ccmOrgAreaIncidentNetManTelephone = $(
            '#ccmOrgAreaIncidentNetManTelephone').val()
        var ccmOrgAreaIncidentNetManNetNum = $(
            '#ccmOrgAreaIncidentNetManNetNum').val()
        var ccmOrgAreaIncidentNetManMannum = $(
            '#ccmOrgAreaIncidentNetManMannum').val()
        var ccmOrgAreaIncidentNetManManIcon = $(
            '#ccmOrgAreaIncidentNetManManIcon').val()
        var videoSafetyNum = $('#videoSafetyNum').val()
        var definitionNum = $('#definitionNum').val()

        var netPeoName = $('#netPeoName').val()
        var netPeoNum = $('#netPeoNum').val()

        var html = "";
        html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">网格信息</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '<div style="width:80%;height:332px; padding: 10px;margin:auto;background: url('
            + ctxStatic
            + '/common/index/images/pop-bg1.png);background-size: 100% 100%;">'
        html += '<table style="width:100%;    margin-left: 35px; margin-top: 9px;">'
        html += '<tr>'
        html += '<td colspan="2" style=" text-align: left;"><img src="'
            + ccmOrgAreaIncidentNetManManIcon
            + '" style="height:150px;margin:auto"/>'
        html += '</td>'
        html += '<tr>'
        html += '<td style="    style="    text-align: left;"">名称：</td>'
        html += '<td style="    text-align: left;">' + areaIncidentName
            + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td     style="    text-align: left;">责任人：</td>'
        html += '<td  style="    text-align: left;">'
            + ccmOrgAreaIncidentNetManName + '</td>'
        html += '</tr>'
        html += '<tr  style="    text-align: left;">'
        html += '<td >电话：</td>'
        html += '<td style="    text-align: left;">'
            + ccmOrgAreaIncidentNetManTelephone + '</td>'
        html += '</tr>'
        html += '<tr >'
        html += '<td   style="    text-align: left;">工作人员姓名：</td>'
        html += '<td style="    text-align: left;">' + netPeoName + '</td>'
        html += '</tr>'
        html += '<tr >'
        html += '<td    style="    text-align: left;">工作人员数量：</td>'
        html += '<td style="    text-align: left;">' + netPeoNum + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td    style="    text-align: left;">户数：</td>'
        html += '<td style="    text-align: left;">'
            + ccmOrgAreaIncidentNetManNetNum + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td   style="    text-align: left;">人口：</td>'
        html += '<td style="    text-align: left;">'
            + ccmOrgAreaIncidentNetManMannum + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td    style="    text-align: left;"">公共安全视频监控：</td>'
        html += '<td style="    text-align: left;">' + videoSafetyNum + '</td>'
        html += '</tr>'
        html += '<tr >'
        html += '<td   style="    text-align: left;">高清摄像机：</td>'
        html += '<td style="    text-align: left;">' + definitionNum + '</td>'
        html += '</tr>'
        html += '</table>'
        html += '</div>'

        html += '</div>'
        html += '</div>'
        layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["600px", "400px"],
            offset: ['500px', '10px'],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            id: 'TodayCommunityLayer',
            content: html,
            cancel: function () {
                Map.removeLayer('alarmcommunitys');
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });

        var netMapIncident = $('#netMapIncident').val()
        netMapcoordinates = netMapIncident.split(';')
        var len = netMapcoordinates.length;
        var netMapcoordinatesArr = []
        if (len > 0) {
            for (var i = 0; i < len; i++) {

                netMapcoordinatesArr.push(netMapcoordinates[i].split(','))
            }
        }

        // 画出小区区域
        var XiaoQuData = {
            "type": "FeatureCollection",
            "centpoint": netMapcoordinatesArr[0],
            "features": [{
                "type": "Feature",
                "id": "alarmcommunitys123",
                "properties": {
                    "name": areaIncidentName,
                    "icon": "",
                    "info": {},
                    "coordinateCentre": netMapcoordinatesArr[0]
                },
                "geometry": {
                    "type": "Polygon",
                    "coordinates": [

                        netMapcoordinatesArr

                    ]
                }
            },]
        }

        Map.addJSON1([{
            'type': 'alarmcommunitys',
            'data': XiaoQuData,
            'isShow': true
        }])

    }

    TodayHandle = function () {

        var nameArr = []
        $('input[name="vLIsname"]').each(function () {
            if ($(this).val() != "" && $(this).val() != null) {
                nameArr.push({
                    "name": $(this).val(),
                    "tel": $(this).attr('attrtel'),
                    'photo': $(this).attr('attrphoto'),
                    "id": $(this).attr('attrid')
                })
            }
        })

        var html = "";
        html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">处置信息</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;overflow-y: auto;  border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '<div style="width:100%;height:302px;padding-top:10px">'
        html += '<div class="row-fluid tool-container">'
        html += '<h5>当前网格</h5>'
        var len = nameArr.length
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                html += '<div class="span3" style="height:150px">'
                html += '<div class="" style="width:100%;height:100%;background: url('
                    + ctxStatic
                    + '/common/index/images/pop-bg.png);background-size: 100% 100%;">'
                html += '<table style="width: 100%;">'
                html += '<tr>'
                html += '<td style="height:60px;padding:5px;text-align: center;" colspan="2"><img src="'
                    + nameArr[i].photo
                    + '" style="height:60px;margin:auto"/>'
                html += '</tr>'
                html += '<tr>'
                html += '<td style="text-align: center;">' + nameArr[i].name
                    + '</td>'
                html += '</tr>'
                html += '<tr>'
                html += '<td style="text-align: center;">' + nameArr[i].tel
                    + '</td>'
                html += '</tr>'
                html += '<tr>'
                html += '<td>'
                html += '<span id="TodayLiveDialog" style="margin-left: 30px;cursor: pointer;background: url('
                    + ctxStatic
                    + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 83px; height: 28px; text-align: center;line-height: 28px;" onclick="TodayHandleDialog(\''
                    + nameArr[i].id + '\')">处置</span>'
                html += '</td>'
                html += '</tr>'
                html += '</table>'
                html += '</div>'
                html += '</div>'
            }

        }
        html += '</div>'

        var suoshunameArr = []
        $('input[name="suoshuvLIs"]').each(function () {

            suoshunameArr.push({
                "name": $(this).val(),
                "tel": $(this).attr('attrtel'),
                'photo': $(this).attr('attrphoto'),
                "id": $(this).attr('attrid')
            })
        })

        html += '<div class="row-fluid tool-container">'
        html += '<h5>所属网格</h5>'
        var len = suoshunameArr.length
        if (len > 0) {
            for (var i = 0; i < len; i++) {

                html += '<div class="span3" style="height:150px">'
                html += '<div class="" style="width:100%;height:100%;background: url('
                    + ctxStatic
                    + '/common/index/images/pop-bg.png);background-size: 100% 100%;">'
                html += '<table style="width: 100%;">'
                html += '<tr>'
                html += '<td style="height:60px;padding:5px;text-align: center;" colspan="2"><img src="'
                    + suoshunameArr[i].photo
                    + '" style="height:60px;margin:auto"/>'
                html += '</tr>'
                html += '<tr>'
                html += '<td style="text-align: center;">'
                    + suoshunameArr[i].name + '</td>'
                html += '</tr>'
                html += '<tr>'
                html += '<td style="text-align: center;">'
                    + suoshunameArr[i].tel + '</td>'
                html += '</tr>'
                html += '<tr>'
                html += '<td>'
                html += '<span id="TodayLive" style="margin-left: 30px;cursor: pointer;background: url('
                    + ctxStatic
                    + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 83px; height: 28px; text-align: center;line-height: 28px;" onclick="TodayHandleDialog(\''
                    + suoshunameArr[i].id + '\')">处置</span>'
                html += '</td>'
                html += '</tr>'
                html += '</table>'
                html += '</div>'
                html += '</div>'
            }

        }
        html += '</div>'
        html += '</div>'
        html += '</div>'
        html += '</div>'
        layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["600px", "400px"],
            offset: ['500px', '1300px'],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            id: 'TodayHandleLayer',
            content: html,
            cancel: function () {
                clearInterval(TodyAlarmTime);
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });
    }

    TodayHandleDialog = function (userId) {

        var eventIncidentId = $('#eventIncidentId').val()
        var html = "";
        html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">处置信息</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;  overflow-y: auto;  border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '	<iframe  name="mainFrame" src="'
            + ctx
            + '/event/ccmEventCasedeal/dealformCommonMap?objType=ccm_event_incident&objId='
            + eventIncidentId
            + '&handleUser.id='
            + userId
            + '" style="overflow: visible;" scrolling="yes" frameborder="no" width="770" height="450" allowfullscreen="true" allowtransparency="true"></iframe>'
        html += '</div>'
        html += '</div>'
        TodayHandleDialogLayer = layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["800px", "480px"],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            content: html,
            id: 'TodayHandleDialogLayer',
            cancel: function () {
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });
    }

    TodayHandleDialogLayerClose = function () {
        layer.close(TodayHandleDialogLayer);
    }

    // 事件实时获取
    function setIntervalToday() {
        TodyAlarmTime = setInterval(function () // 开启循环：每秒出现一次提示框
        {
            var date = new Date();
            var id = $('#eventIncidentId').val();
            $.get(ctx + '/sys/map/getEventCasedealMap?id=' + id + '&date='
                + date, function (data) {
                if (data != "[null]") {
                    setIntervalLayer(data);
                }

            })
        }, 5000);
    }

    var setIntervalLayerId = "";

    function setIntervalLayer(data) {

        var data = JSON.parse(data);
        var len = data.length;
        var id = data[0].id;
        var date = data[0].updateDate.time;
        if (date == setIntervalLayerId) {
            return false;
        }

        setIntervalLayerId = data[0].updateDate.time;
        var handleStep = data[0].handleStep;
        var caseName = data[0].caseName;

        var clock = "";
        var eventTime = new Date(date);
        year = eventTime.getFullYear(); // 年
        month = eventTime.getMonth() + 1; // 月
        day = eventTime.getDate(); // 日
        hh = eventTime.getHours(); // 时
        mm = eventTime.getMinutes(); // 分
        ss = eventTime.getSeconds(); // 秒

        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hh < 10) {
            hh = "0" + hh;
        }
        if (mm < 10)
            mm = '0' + mm;

        if (ss < 10) {
            ss = '0' + ss;
        }
        clock = year + "年" + month + "月" + day + "日 ";
        var html = "";
        html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">';
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">';
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">处置结果</div>';
        html += '</div>';
        html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 98%;height: 97%;  border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">';
        html += '<div style="width:90%;height:250px; padding: 10px;">';
        html += '<table style="width:100%">';
        html += '<tr>';
        html += '<td>姓名：</td>';
        html += '<td>' + caseName + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>时间：</td>';
        html += '<td>' + clock + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>反馈信息：</td>';
        html += '<td>' + handleStep + '</td>';
        html += '</tr>';
        html += '</table>';
        html += '<span onclick="reCloseLayerFun()" class="QueClose" style="margin-left: 90px; margin-top: 30px;cursor: pointer;background: url('
            + ctxStatic
            + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 77px; height: 28px; text-align: center;line-height: 28px;">确认关闭</span>'
        html += '<span class="QueClose" onclick="reOpenLayerFun()" style="margin-left: 50px;margin-top: 30px;cursor: pointer;background: url('
            + ctxStatic
            + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 77px; height: 28px; text-align: center;line-height: 28px;">重新处理</span>'
        html += '</div>';

        html += '</div>';
        html += '</div>';
        reCloseLayer = layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["400px", "300px"],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            content: html,
            id: 'setIntervalLayer',
            cancel: function () {
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });
    }

    reCloseLayerFun = function () {
        layer.close(reCloseLayer)
    }
    reOpenLayerFun = function () {

        TodayHandleDialog('')

    }
    TodyAlarm = function () {

        // 捕获页
        var TodayCulName = $('#TodayCulName').val()
        var TodayCaseName = $('#TodayCaseName').val();// 案事件名称
        var TodayHappenDate = $('#TodayHappenDate').val().replace(/GMT\+08:00/g, 'GMT+0800');// 发生日期

        var da = new Date(TodayHappenDate);
        var year = da.getFullYear() + '年';
        var month = da.getMonth() + 1 + '月';
        var date = da.getDate() + '日';
        var HappenDate = year + month + date;
        var TodayCasePlace = $('#TodayCasePlace').val()// 案发地
        var TodayHappenPlace = $('#TodayHappenPlace').val()// 详细地址
        var TodayCaseCondition = $('#TodayCaseCondition').val()// 案件情况
        var TodatFile = $('#TodatFile').val()// 案事件图片
        var TodatStatus = $('#TodatStatus').val()// 处理状态
        var TodatEventScale = $('#TodatEventScale').val()// 案事件级别
        var TodatStatusSpan = "未处理"
        var TodatEventScaleSpan = "一般"
        if (TodatStatus == "01") {
            TodatStatusSpan = "未处理"
        } else if (TodatStatus == "02") {
            TodatStatusSpan = "处理中"
        } else if (TodatStatus == "03") {
            TodatStatusSpan = "已完成"
        }

        if (TodatEventScale == "01") {
            TodatEventScaleSpan = "重特大"
        } else if (TodatEventScale == "02") {
            TodatEventScaleSpan = "重大"
        } else if (TodatEventScale == "03") {
            TodatEventScaleSpan = "较大"
        } else if (TodatEventScale == "04") {
            TodatEventScaleSpan = "一般"
        }

        var html = "";
        html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">事件信息</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px;    overflow-y: auto; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '<div style="width:49%;height:302px; padding: 10px;float:left">'
        html += '<table style="width:100%">'
        html += '<tr>'
        html += '<td colspan="2"><img src="' + TodatFile
            + '" style="height:100px;margin:auto"/>'
        html += '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>事件名称：</td>'
        html += '<td width="75%">' + TodayCaseName + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>发生时间：</td>'
        html += '<td>' + HappenDate + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>案发地：</td>'
        html += '<td>' + TodayCasePlace + '</td>'
        html += '</tr>'
        /*
		 * html += '<tr>' html += '<td>详细地址：</td>' html += '<td>' +
		 * TodayHappenPlace + '</td>' html += '</tr>'
		 */
        html += '<tr>'
        html += '<td>事件级别：</td>'
        html += '<td><span style="margin-left:0" class="eventScaleCss eventScaleCss-'
            + TodatEventScale + '">' + TodatEventScaleSpan + '</span></td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>事件情况：</td>'
        html += '<td>' + TodayCaseCondition + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>处理状态：</td>'
        html += '<td><span style="margin-left:0" class="eventScaleCss statusCss-'
            + TodatStatus + '">' + TodatStatusSpan + '</span></td>'
        html += '</tr>'
        html += '</table>'
        html += '</div>'

        // 人员
        var ccmPeopleIncidentName = $('#ccmPeopleIncidentName').val()
        var ccmPeopleIncidentSex = $('#ccmPeopleIncidentSex').val()
        var ccmPeopleIncidentBirthday = $('#ccmPeopleIncidentBirthday').val()
        var ccmPeopleIncidentImages = $('#ccmPeopleIncidentImages').val()
        var areaLiveIncident = $('#areaLiveIncident').val()
        var ccmOrgAreaLiveIncidentnetManName = $(
            '#ccmOrgAreaLiveIncidentnetManName').val()
        var ccmOrgAreaLiveIncidenttelephone = $(
            '#ccmOrgAreaLiveIncidenttelephone').val()

        if (ccmPeopleIncidentSex == "1") {
            ccmPeopleIncidentSex = "女"
        } else if (ccmPeopleIncidentSex == "0") {
            ccmPeopleIncidentSex = "男"
        } else {
            ccmPeopleIncidentSex = ""
        }
        var PopBirthday;
        if (ccmPeopleIncidentBirthday != "") {
            var Popda = new Date(ccmPeopleIncidentBirthday);

            var Popyear = Popda.getFullYear() + '年';
            var Popmonth = Popda.getMonth() + 1 + '月';
            var Popdate = Popda.getDate() + '日';
            PopBirthday = Popyear + Popmonth + Popdate;
        } else {
            PopBirthday = "";
        }

        html += '<div style="width:44%;height:302px; padding: 10px;float:left">'
        html += '<table style="width:100%">'
        html += '<tr>'
        html += '<td colspan="2"><img src="' + ccmPeopleIncidentImages
            + '" style="height:100px;margin:auto"/>'
        html += '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>姓名：</td>'
        html += '<td>' + ccmPeopleIncidentName + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>性别：</td>'
        html += '<td>' + ccmPeopleIncidentSex + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>生日：</td>'
        html += '<td>' + PopBirthday + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>所属网格：</td>'
        html += '<td>' + areaLiveIncident + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>责任人：</td>'
        html += '<td>' + ccmOrgAreaLiveIncidentnetManName + '</td>'
        html += '</tr>'
        html += '<tr>'
        html += '<td>电话：</td>'
        html += '<td>' + ccmOrgAreaLiveIncidenttelephone + '</td>'
        html += '</tr>'
        html += '</table>'
        html += '</div>'
        html += '<div style="width:100%;">'
        html += '<span id="TodayVideo"  style="margin-left: 90px;cursor: pointer;background: url('
            + ctxStatic
            + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 77px; height: 28px; text-align: center;line-height: 28px;">视频</span>'
        html += '<span id="TodayCommunity" style="margin-left: 50px;cursor: pointer;background: url('
            + ctxStatic
            + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 77px; height: 28px; text-align: center;line-height: 28px;">网格</span>'
        html += '<span id="TodayHandle"  style="margin-left: 50px;cursor: pointer;background: url('
            + ctxStatic
            + '/common/index/images/header-right.png) no-repeat center; background-size: 100% 100%; float: left;display:block;  width: 77px; height: 28px; text-align: center;line-height: 28px;">处置</span>'
        // html+='<span id="TodayLive" style="margin-left: 30px;cursor:
        // pointer;background:
        // url('+ctxStatic+'/common/index/images/header-right.png) no-repeat
        // center; background-size: 100% 100%; float: left;display:block; width:
        // 77px; height: 28px; text-align: center;line-height: 28px;">同住</span>'
        html += '</div>'
        html += '</div>'
        html += '</div>'
        layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["600px", "400px"],
            offset: ['70px', '10px'],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            id: 'TodyAlarmLayer',
            content: html,
            cancel: function () {
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });

        $('#TodayVideo').click(function () {
            var ccmDeviceIncidentArr = []
            $('input[name="resccmDeviceIncidentname"]').each(function () {
                ccmDeviceIncidentArr.push({
                    "id": $(this).attr('data-id'),
                    "name": $(this).attr('data-name')
                })
            })

            var html = "";
            html += '<div style="width:80%;height:260px; padding: 10px;float:left">'
            html += '<table style="width:100%" class="table table-striped table-bordered table-condensed table-gradient tree_table">'
            html += '<thead><tr><th>名称</th><th>操作</th></thead>'
            var len = ccmDeviceIncidentArr.length
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    html += '<tr>'
                    html += '<td>' + ccmDeviceIncidentArr[i].name + '</td>'
                    html += '<td><input type="button" class="btn btn-primary" value="播放" onclick="playTodayVideo(\'' + ccmDeviceIncidentArr[i].id + '\')"></td>'
                    html += '</tr>'
                }
            }
            html += '</table>'
            html += '</div>'
            layer.open({
                type: 1,
                shade: false,
                title: false, // 不显示标题
                area: ["400px", "350px"],
                // offset : [ '570px', '101px' ],
                move: '.layer-common-header',
                resize: false,
                fixed: false,
                id: 'playvideoLayerid',
                content: html,
                cancel: function () {

                }
            });


            // var listccmDeviceIncident = $('#listccmDeviceIncidentId').val();
            // var dataarray = JSONArray.parseArray(schelistccmDeviceIncidentdules);
            // var data = JSON.parse(listccmDeviceIncident);
            //
            // if(listccmDeviceIncident!=null){
            // 	// var arrayccmDevi = JSON.parse(listccmDeviceIncident);
            // 	// var jsonArray = JSONArray.fromObject(listccmDeviceIncident);
            // 	var data = JSON.parse(listccmDeviceIncident);
            // 	var data = JSON.parse($('#listccmDeviceIncidentId').val());
            // 	var arrayccmDevi = listccmDeviceIncident.parseJSON();
            // 	for(var i= 0 ; i<arrayccmDevi.length;i++) {
            // 		console.log(arrayccmDevi[i].id);
            // 		console.log(arrayccmDevi[i].name);
            // 	};
            // }

            // TodayVideo();
        })


        $('#TodayCommunity').click(function () {
            TodayCommunity();
        })
        $('#TodayHandle').click(
            function () {
                TodayHandle();
                setIntervalToday();

                var id = $('#eventIncidentId').val();
                $.get(ctx + '/sys/map/getEventCasedealMap?id=' + id,
                    function (data) {

                        if (data != "[null]") {
                            setIntervalLayer(data);
                        }

                    })
            })
        $('#TodayLive').click(function () {
            TodayLive();
        })

    }

    // 今日案事件页面跳转
    if ($('#layEventIncident').attr('attrid') != ''
        && $('#layEventIncident').attr('attrid') != null) {
        var id = $('#layEventIncident').attr('attrid');
        var name = $('#layEventIncident').attr('attrName');
        var happenDate = $('#layEventIncident').attr('happenDate');
        var coordinate = $('#layEventIncident').attr('attrCoordinates').split(
            ',');
        var obj = $('#layEventIncident').attr('attrccmeventincident');
        Map.postcomposeOl(name, coordinate, id, obj, happenDate)
        TodyAlarm()
    }

    // 迁徙图
    var data = [{
        from: {
            city: '1',
            lnglat: [117.66084909439087, 39.03940200805664]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '2',
            lnglat: [117.64616560935977, 39.03373718261719]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '3',
            lnglat: [117.6412904262543, 39.039586544036865]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '4',
            lnglat: [117.637060, 39.031640]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '5',
            lnglat: [117.65420947756085, 39.04100290366581]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '6',
            lnglat: [117.653700, 39.036050]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '7',
            lnglat: [117.63514280319214, 39.02385439191546]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '8',
            lnglat: [117.63092851638794, 39.04314422607422]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '9',
            lnglat: [117.62861967086792, 39.03797721862793]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '10',
            lnglat: [117.65724205970764, 39.03594732284546]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '11',
            lnglat: [117.642120, 39.030520]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '12',
            lnglat: [117.66181928770882, 39.0350215775626]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '13',
            lnglat: [117.63459348678589, 39.041144371032715]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '14',
            lnglat: [117.65623998641968, 39.019463539123535]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }, {
        from: {
            city: '15',
            lnglat: [117.64711725711823, 39.03388953208923]
        },
        to: {
            city: '社区',
            lnglat: [117.648920, 39.034450]
        }
    }]
    // var moveline = new MoveLine(map, {
    // //marker点半径
    // markerRadius : 2,
    // //marker点颜色,为空或null则默认取线条颜色
    // markerColor : null,
    // //线条类型 solid、dashed、dotted
    // lineType : 'solid',
    // //线条宽度
    // lineWidth : 2,
    // //线条颜色
    // colors : [ '#F9815C', '#F8AB60', '#EDCC72', '#E2F194',
    // '#94E08A','#4ECDA5', '#F9815C', '#F8AB60', '#EDCC72', '#E2F194',
    // '#94E08A','#4ECDA5', '#F9815C', '#F8AB60', '#EDCC72', '#E2F194',
    // '#94E08A','#4ECDA5' ],
    // //移动点半径
    // moveRadius : 3,
    // //移动点颜色
    // fillColor : '#fff',
    // //移动点阴影颜色
    // shadowColor : '#fff',
    // //移动点阴影大小
    // shadowBlur : 6,
    // data : data
    // });

    /* 工具栏 */
    $('.tag-panl-span').click(function () {
        $('.tag-panl-span').removeClass('active');
        $(this).addClass('active');
    })
    $('.tag-panl-close').click(function () {
        $('.tag-panl').hide();
        $('.tag-panl-span').removeClass('active')
    })
    $('#DrawFlag').click(function () {
        $('.tag-panl').hide();
        $('.tag-panl-span').removeClass('active')
        $('#toolDetailBox').toggle('fast');
        $(this).toggleClass("active");
    })
    plotDrawInit(); // 标绘初始化
    /* 工具栏 */
    // 浮动工具栏
    var isDrag = false; // 声明拖动的默认状态是：否
    $('#floatingLayer').draggable({
        containment: "parent"
    });
    var floatingLayerFlag = true;
    $('#toolMenu').draggable({
        containment: "parent",
        stop: function (e) {
            isDrag = true;
        }
    });
    $("#toolbar div span").click(function () {
        if (!isDrag) {
            $("#toolbar div").removeClass('conversion-color');
            $(this).parent('div').addClass('conversion-color');
        }
    });
    $("#floatingLayer").click(function () {
        if (!isDrag) {
            var offset = $("#floatingLayer").offset();
            $("#toolMenu").css("top", offset.top - 300);
            $("#toolMenu").css("left", offset.left - 300);
            $(this).parent().children('#toolMenu').toggleClass('active');
            $(this).hide();
        }
        isDrag = false;
    });
    $("#map .return-map span").click(function () {
        if (!isDrag) {
            // var _this = $(this).parents('.wrapper');
            // _this.children('.left').show();
            // // _this.children('.police').show();
            // $(this).parents('.map').removeClass('none-map');
            Map.fullScreen();
            $(this).parents('.toolbar').children('.all-map').show();
            $(this).parents('.toolbar').children('.all-map').addClass('conversion-color');
            $(this).parent('.return-map').hide();
        }
        isDrag = false;
    });

    $('.box .hd span').click(function () {
        var _this = $(this).parent().parent();
        _this.children('.bd').toggle();
        $(this).toggleClass('toggle-up');
        $(this).toggleClass('toggle');
    });

    $("#range span").click(function () {
        if (!isDrag) {
            var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.range-query');
            $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars,.plottingbar').removeClass('active');
            _this.toggleClass("active");
            $(this).toggleClass("triangle-down");
            $(this).toggleClass("triangle-up");
        }
        isDrag = false;
    });
    $("#plotting span").click(function () {
        if (!isDrag) {
            var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.plottingbar');
            $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars,.range-query').removeClass('active');
            _this.toggleClass("active");
            $(this).toggleClass("triangle-down");
            $(this).toggleClass("triangle-up");
        }
        isDrag = false;
    });

    $(".Tools span").click(function () {
        if (!isDrag) {
            var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars');
            $(this).parents('#toolMenu').children('.inner-menu').children('.plottingbar,.range-query').removeClass('active');
            _this.toggleClass("active");
            $(this).toggleClass("triangle-down");
            $(this).toggleClass("triangle-up");
        }
        isDrag = false;
    });
    $("#turn-off").click(function (e) {
        if (!isDrag) {
            $(this).parents('#toolMenu').removeClass('active');
            $(this).parent().children('div').removeClass('conversion-color');
            $(this).parents('#toolMenu').children().children().removeClass('active');
            $("#floatingLayer").show();
        }
        isDrag = false;
    });
    $("#map .all-map span").click(function () {
        if (!isDrag) {
            // Map.fullScreen();
            // var _this = $(this).parents('.wrapper');
            // _this.children('.left').hide();
            // // _this.children('.police').hide();
            // $(this).parents('.map').addClass('none-map');
            Map.fullScreen();
            $(this).parents('.toolbar').children('.return-map').show();
            $(this).parents('.toolbar').children('.return-map').addClass('conversion-color');
            $(this).parent('.all-map').hide();
        }
        isDrag = false;
    });

})

// 指定标绘类型，开始绘制。
function plotDrawInit() {
    // 初始化标绘绘制工具，添加绘制结束事件响应
    plotDraw = new P.PlotDraw(Map.map);
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
    // // 框选查询初始化
    // Map.selectQueryInit();
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
}

function PointBox() {
    $('#PointBox').show();
    $('#toolDetailBox').hide()
    $('#DrawFlag').removeClass('active');
}

function LineBox() {
    $('#LineBox').show();
    $('#toolDetailBox').hide()
    $('#DrawFlag').removeClass('active');
}

function PolygonBox() {
    $('#PolygonBox').show();
    $('#toolDetailBox').hide()
    $('#DrawFlag').removeClass('active');
}

function ArrowBox() {
    $('#ArrowBox').show();
    $('#toolDetailBox').hide()
    $('#DrawFlag').removeClass('active');
}

function TextBox() {
    $('#TextBox').show();
    $('#toolDetailBox').hide()
    $('#DrawFlag').removeClass('active');
}

function LayersBox() {
    $('#LayersBox').show();
    $('#toolDetailBox').hide()
    $('#DrawFlag').removeClass('active');
}

// 实时定位
function HisTrackFun(_this) {
    var id = $(_this).attr('id')
    var _deviceId = $(_this).attr('deviceId')

    $.getJSON('/arjccm/a/sys/map/deviceMobileTrace?deviceId=' + _deviceId + '',
        function (data) {
            if (data.returnFlag) {
                var DataTrack = data.result;
                var len = DataTrack.length;
                var DataTrackArr = [];
                // 轨迹
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        var areaPoint = DataTrack[i].areaPoint;
                        var value = [];
                        value[0] = Number(areaPoint.split(',')[0]);
                        value[1] = Number(areaPoint.split(',')[1]);
                        DataTrackArr.push(value)

                    }
                    // console.log(DataTrackArr);
                    Map.trackReplay('start-animation', 40, DataTrackArr);
                    $('#start-animation').click()
                }

                // 电子围栏
                var ElectronicFence = $(_this).attr('ElectronicFence')
                // console.log(ElectronicFence)
                if (ElectronicFence != '') {
                    Map.addElectronicFence(id, 'Polygon', ElectronicFence)
                }
                $('#popup-closer').click();
            } else {
                top.$.jBox.tip('暂无轨迹信息');
            }

        })

}

function PopInfoFun(_this) {
    var popinfo = $(_this).attr('popinfo')
    var popinfoData = JSON.parse(JSON.parse(popinfo))
    // 捕获页
    var len = popinfoData.length;
    var minjing = '';
    var fujing = '';
    var html = "";
    html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
    html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
    html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">人员信息</div>'
    html += '</div>'
    html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; overflow-x:hidden;overflow-y:auto;width: 100%;height: 100%;  border: 1px solid #10559a;background: url(' + ctxStatic + '/common/index/images/showbg.png);background-size: 100% 100%;">'
    html += '<div class="well">'
    minjing += '<ul style="border-bottom:1px dashed  #fff;margin-bottom:5px;overflow-y: auto;overflow-x: hidden;width:100%;height:auto;margin:0">民警:';
    minjing += '<li class="clearfix" style="overflow-y: auto;overflow-x: hidden;width:100%;height:auto">';
    fujing += '<ul style="overflow-y: auto;overflow-x: hidden;width:100%;height:auto;margin:0">辅警:';
    fujing += '<li  class="clearfix" style="overflow-y: auto;overflow-x: hidden;width:100%;height:auto">';
    for (var i = 0; i < len; i++) {
        var src = popinfoData[i]['images'] || popinfoData[i]['photo'];
        var telephone = popinfoData[i]['telephone'] || popinfoData[i]['phone'];

        if (popinfoData[i]['no']) {//新密市警务室、工作站
            var name = popinfoData[i]['name'];
            var nameType = name.split('（');
            if (nameType[1]) {
                var nameType1 = name.split('（')[1].split('）')[0];

                if (nameType1 == "民警") {

                    minjing += '<div class="well-item">'
                    minjing += '<div  class="jingcha-name correct">';
                    minjing += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="'
                        + src + '" /></div>';
                    minjing += ' <p class="test2name">' + name + '</p>';
                    // html+='<p>'+popinfoData[i]['警号']+'</p>';
                    minjing += ' </div>';
                    minjing += '<div class="opposite"><div>';
                    minjing += '<table>';
                    minjing += '<tr>';
                    minjing += '<td  align="right" >姓名：</td><td align="left">'
                        + popinfoData[i]['name'] + '</td>';
                    minjing += '</tr>';
                    minjing += '<tr>';
                    minjing += '<td align="right" >性别：</td><td align="left">'
                        + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
                    minjing += '</tr>';
                    minjing += '<tr>';
                    if (popinfoData[i]['service']) {
                        minjing += '<td align="right" >职位：</td><td align="left">'
                            + popinfoData[i]['service'] + '</td>';
                    }
                    if (popinfoData[i]['no']) {
                        minjing += '<td align="right" >警号：</td><td align="left">'
                            + popinfoData[i]['no'] + '</td>';
                    }

                    minjing += '</tr>';
                    minjing += '<tr>';
                    minjing += '<td align="right" >电话：</td><td align="left">'
                        + telephone + '</td>';
                    minjing += '</tr>';
                    minjing += '</table>';
                    minjing += '</div></div>';
                    minjing += '</div>'


                } else {
                    fujing += '<div class="well-item">'
                    fujing += '<div  class="jingcha-name correct">';
                    fujing += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="'
                        + src + '" /></div>';
                    fujing += ' <p class="test2name">' + name + '</p>';
                    // html+='<p>'+popinfoData[i]['警号']+'</p>';
                    fujing += ' </div>';
                    fujing += '<div class="opposite"><div>';
                    fujing += '<table>';
                    fujing += '<tr>';
                    fujing += '<td  align="right" >姓名：</td><td align="left">'
                        + popinfoData[i]['name'] + '</td>';
                    fujing += '</tr>';
                    fujing += '<tr>';
                    fujing += '<td align="right" >性别：</td><td align="left">'
                        + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
                    fujing += '</tr>';
                    fujing += '<tr>';
                    if (popinfoData[i]['service']) {
                        fujing += '<td align="right" >职位：</td><td align="left">'
                            + popinfoData[i]['service'] + '</td>';
                    }
                    if (popinfoData[i]['no']) {
                        fujing += '<td align="right" >警号：</td><td align="left">'
                            + popinfoData[i]['no'] + '</td>';
                    }

                    fujing += '</tr>';
                    fujing += '<tr>';
                    fujing += '<td align="right" >电话：</td><td align="left">'
                        + telephone + '</td>';
                    fujing += '</tr>';
                    fujing += '</table>';
                    fujing += '</div></div>';
                    fujing += '</div>'

                }
            } else {

                fujing += '<div class="well-item">'
                fujing += '<div  class="jingcha-name correct">';
                fujing += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="'
                    + src + '" /></div>';
                fujing += ' <p class="test2name">' + name + '</p>';
                // html+='<p>'+popinfoData[i]['警号']+'</p>';
                fujing += ' </div>';
                fujing += '<div class="opposite"><div>';
                fujing += '<table>';
                fujing += '<tr>';
                fujing += '<td  align="right" >姓名：</td><td align="left">'
                    + popinfoData[i]['name'] + '</td>';
                fujing += '</tr>';
                fujing += '<tr>';
                fujing += '<td align="right" >性别：</td><td align="left">'
                    + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
                fujing += '</tr>';
                fujing += '<tr>';
                if (popinfoData[i]['service']) {
                    fujing += '<td align="right" >职位：</td><td align="left">'
                        + popinfoData[i]['service'] + '</td>';
                }
                if (popinfoData[i]['no']) {
                    fujing += '<td align="right" >警号：</td><td align="left">'
                        + popinfoData[i]['no'] + '</td>';
                }

                fujing += '</tr>';
                fujing += '<tr>';
                fujing += '<td align="right" >电话：</td><td align="left">'
                    + telephone + '</td>';
                fujing += '</tr>';
                fujing += '</table>';
                fujing += '</div></div>';
                fujing += '</div>'
            }

        } else {//公共机构
            html += '<div class="well-item">'
            html += '<div  class="jingcha-name correct">';
            html += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="'
                + src + '" /></div>';
            html += ' <p class="test2name">' + popinfoData[i]['name'] + '</p>';
            // html+='<p>'+popinfoData[i]['警号']+'</p>';
            html += ' </div>';
            html += '<div class="opposite"><div>';
            html += '<table>';
            html += '<tr>';
            html += '<td  align="right" >姓名：</td><td align="left">'
                + popinfoData[i]['name'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >性别：</td><td align="left">'
                + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
            html += '</tr>';
            html += '<tr>';
            if (popinfoData[i]['service']) {
                html += '<td align="right" >职位：</td><td align="left">'
                    + popinfoData[i]['service'] + '</td>';
            }
            if (popinfoData[i]['no']) {
                html += '<td align="right" >警号：</td><td align="left">'
                    + popinfoData[i]['no'] + '</td>';
            }

            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >电话：</td><td align="left">'
                + telephone + '</td>';
            html += '</tr>';
            html += '</table>';
            html += '</div></div>';
            html += '</div>'

        }
    }

    minjing += '</li>';
    fujing += '</li>';
    minjing += '</ul>';
    fujing += '</ul>';
    if (popinfoData[0]['no']) {//新密市警务室、工作站
        html += minjing;
        html += fujing;
    }
    html += '</div>'
    html += '</div>'
    html += '</div>'


    layer.open({
        type: 1,
        shade: false,
        title: false, // 不显示标题
        content: html, // 捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        area: ['800px', '610px'],
        id: "PopInfoLayer",
        cancel: function () {
            // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000, icon:6});
        }
    });

    $(".well-item").hover(function () {


        $(this).find(".correct").children().removeClass('test');
        $(this).find(".correct").children().removeClass('test2');
        $(this).find(".opposite").children().removeClass('test');
        $(this).find(".opposite").children().removeClass('test2');
        $(this).find(".correct").children().addClass("test");
        $(this).find(".opposite").children().addClass('test2');
    }, function () {

        $(this).find(".correct").children().removeClass('test');
        $(this).find(".opposite").children().removeClass('test');
        $(this).find(".correct").children().removeClass("test2");
        $(this).find(".opposite").children().removeClass("test2");
        $(this).find(".correct").children().addClass("test2");
        $(this).find(".opposite").children().addClass('test');
    });

}

function PopLocation() {
    // 人员定位
    var areaPoint = $('#areaPoint').val()
    var ccmPeopleName = $('#ccmPeopleName').val()
    if (areaPoint != "" && ccmPeopleName != "") {
        var ccmPeopleSex = $('#ccmPeopleSex').val()
        if (ccmPeopleSex == "1") {
            ccmPeopleSex = "女"
        } else if (ccmPeopleSex == "0") {
            ccmPeopleSex = "男"
        } else {
            ccmPeopleSex = ""
        }
        var ccmPeopleBirthday = $('#ccmPeopleBirthday').val()
        if (ccmPeopleBirthday != "") {
            var da = new Date(ccmPeopleBirthday);
            var year = da.getFullYear() + '年';
            var month = da.getMonth() + 1 + '月';
            var date = da.getDate() + '日';

            var Birthday = year + month + date;
        }


        var ccmPeopleareaComId = $('#ccmPeopleareaComId').val()
        var ccmPeopleareaGridId = $('#ccmPeopleareaGridId').val()
        var ccmPeopleIdent = $('#ccmPeopleIdent').val()
        var residencedetail = $('#ccmPeopleresidencedetail').val()
        areaPoint = areaPoint.split(',')
        var PopDat = {
            "type": "FeatureCollection",
            "centpoint": areaPoint,
            "features": [{
                "type": "Feature",
                "id": "f3284f6b226b4035a8438f87694aae5b",
                "properties": {
                    "name": ccmPeopleName,
                    "icon": "zhongdian.png",
                    "info": {
                        "性别": ccmPeopleSex,
                        "生日": Birthday,
                        "社区": ccmPeopleareaComId,
                        "网格": ccmPeopleareaGridId,
                        "住址": residencedetail
                    },

                    "coordinateCentre": areaPoint
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": areaPoint
                }
            },]
        }
        Map.addJSON1([{
            'type': 'videos',
            'data': PopDat,
            'isShow': true
        }])
        var map = Map.map;// change:resolution
        map.getView().setZoom(19);
        Map.goTo(areaPoint)
    }

    // 事件定位
    var AlarmAreaPoint = $('#AlarmAreaPoint').val()
    if (AlarmAreaPoint != "") {
        var ccmEventIncidentCaseName = $('#ccmEventIncidentCaseName').val()
        var ccmEventIncidentCulName = $('#ccmEventIncidentCulName').val()
        var ccmEventIncidentCasePlace = $('#ccmEventIncidentCasePlace').val()
        var ccmEventIncidentHappenDate = $('#ccmEventIncidentHappenDate').val()
        var da = new Date(ccmEventIncidentHappenDate);
        var year = da.getFullYear() + '年';
        var month = da.getMonth() + 1 + '月';
        var date = da.getDate() + '日';
        var HappenDate = year + month + date;
        var ccmEventIncidentHappenPlace = $('#ccmEventIncidentHappenPlace')
            .val()
        var ccmEventIncidentCaseCondition = $('#ccmEventIncidentCaseCondition')
            .val()

        AlarmAreaPoint = AlarmAreaPoint.split(',')


        var geoJSONIncidentBuildmanage = $('#geoJSONIncidentBuildmanage').val();
        var geoJSONIncidentCcmDevice = $('#geoJSONIncidentCcmDevice').val();
        var geoJSONIncidentBuildmanageData = JSON.parse(geoJSONIncidentBuildmanage);
        var geoJSONIncidentCcmDeviceData = JSON.parse(geoJSONIncidentCcmDevice);
        //周围一千米重点人员楼栋
        var geoJSONIncidentBuildmanageDataLen = geoJSONIncidentBuildmanageData.length;
        if (geoJSONIncidentBuildmanageDataLen > 0) {
            var features1 = [];
            for (var i = 0; i < geoJSONIncidentBuildmanageDataLen; i++) {
                var coordinates = geoJSONIncidentBuildmanageData[i].areaMap.split(';');
                var coordinatesLen = coordinates.length;
                var coordinatesArr = [];
                if (coordinatesLen > 0) {
                    for (var k = 0; k < coordinatesLen; k++) {
                        coordinatesArr.push(coordinates[k].split(','))
                    }
                }

                features1.push({
                    "type": "Feature",
                    "id": geoJSONIncidentBuildmanageData[i].id,
                    "properties": {
                        "name": geoJSONIncidentBuildmanageData[i].name,
                        "icon": "",
                        "info": {
                            "单元数": geoJSONIncidentBuildmanageData[i].elemNum,
                            "层数": geoJSONIncidentBuildmanageData[i].pilesNum,
                            "楼栋名称": geoJSONIncidentBuildmanageData[i].buildname,
                            "电话": geoJSONIncidentBuildmanageData[i].tel,
                            "id": geoJSONIncidentBuildmanageData[i].id
                        },
                        "coordinateCentre": geoJSONIncidentBuildmanageData[i].areaPoint.split(',')
                    },
                    "geometry": {
                        "type": "Polygon",
                        "coordinates": [
                            coordinatesArr
                        ]
                    }
                });
            }
        }
        var geoJSONIncidentCcmDeviceFeatureCollection = {
            "type": "FeatureCollection",
            "centpoint": [
                "117.66011939942837",
                "39.03207153081894"
            ],
            "features": features1
        }
        console.log(geoJSONIncidentCcmDeviceFeatureCollection)
        Map.addJSON1([{
            'type': 'keyPerson',
            'data': geoJSONIncidentCcmDeviceFeatureCollection,
            'isShow': true
        }])


        //周围一千米摄像机
        var geoJSONIncidentCcmDeviceDataLen = geoJSONIncidentCcmDeviceData.length;
        if (geoJSONIncidentCcmDeviceDataLen > 0) {
            var features2 = [];
            for (var i = 0; i < geoJSONIncidentCcmDeviceDataLen; i++) {
                features2.push({
                    "type": "Feature",
                    "id": geoJSONIncidentCcmDeviceData[i].id,
                    "properties": {
                        "name": geoJSONIncidentCcmDeviceData[i].name,
                        "icon": geoJSONIncidentCcmDeviceData[i].imagePath,
                        "info": {
                            "设备状态": geoJSONIncidentCcmDeviceData[i].status,
                            "IP地址": geoJSONIncidentCcmDeviceData[i].ip,
                            "安装位置": geoJSONIncidentCcmDeviceData[i].address,
                            "登陆账号": geoJSONIncidentCcmDeviceData[i].account
                        },
                        "video": {
                            "protocol": geoJSONIncidentCcmDeviceData[i].protocol,
                            "password": geoJSONIncidentCcmDeviceData[i].password,
                            "param": geoJSONIncidentCcmDeviceData[i].param,
                            "port": geoJSONIncidentCcmDeviceData[i].port,
                            "ip": geoJSONIncidentCcmDeviceData[i].ip,
                            "username": geoJSONIncidentCcmDeviceData[i].account
                        },
                        "coordinateCentre": geoJSONIncidentCcmDeviceData[i].coordinate.split(',')
                    },
                    "geometry": {
                        "type": "Point",
                        "coordinates": geoJSONIncidentCcmDeviceData[i].coordinate.split(',')
                    }
                });
            }
        }
        var geoJSONIncidentCcmDeviceDataFeatureCollection = {
            "type": "FeatureCollection",
            "centpoint": [
                "117.64662265777588",
                "39.03797507286072"
            ],
            "features": features2
        }
        Map.addJSON1([{
            'type': 'videos',
            'data': geoJSONIncidentCcmDeviceDataFeatureCollection,
            'isShow': true
        }])


        //案事件定位
        var AlarmDat = {
            "type": "FeatureCollection",
            "centpoint": AlarmAreaPoint,
            "features": [{
                "type": "Feature",
                "id": "",
                "properties": {
                    "name": ccmEventIncidentCaseName,
                    "icon": "security.png",
                    "info": {
                        "主犯（嫌疑人）姓名": ccmEventIncidentCulName,
                        "发生日期": HappenDate,
                        "案发地": ccmEventIncidentCasePlace,
                        "详细地址": ccmEventIncidentHappenPlace,
                        "案件情况": ccmEventIncidentCaseCondition,
                    },
                    "coordinateCentre": AlarmAreaPoint
                },
                "geometry": {
                    "type": "Point",
                    "coordinates": AlarmAreaPoint
                }
            },]
        }

        Map.addJSON1([{
            'type': 'videos',
            'data': AlarmDat,
            'isShow': true
        }])


        var map = Map.map;// change:resolution
        Map.showCircleFromPointDefult(AlarmAreaPoint, 1000)//默认1000米范围
        map.getView().setZoom(18);
        Map.goTo(AlarmAreaPoint);

        window.onload = function () {
            Map.removeLayer('communitys');
            Map.removeLayer('streets');
            Map.removeLayer('grids');
            Map.removeLayer('builds');
        }

    }

}

//
//
//
//
// ------------------------------------------------------以下为新加的代码-----------------------------------------------------------------------------------
//
//
//
//

var map;
var xuexiaoFlag = true;
var idArrxuexiao = [];

function xuexiaoFun(_this) {

    var centpoint = [];
    if (xuexiaoFlag) {
        $.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type=32', function (data) {
            centpoint = data.centpoint;
            var features = data.features;
            var len = features.length;
            idArrxuexiao = [];
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    idArrxuexiao.push(features[i].id);
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'data': data,
                'id': 'xuexiao',
                'isShow': true
            }])

        })
        $(_this).css('border', '1px solid #0e54a9')
        //Map.goTo(centpoint)
    } else {
        $(_this).css('border', '1px solid transparent');
        $.each(idArrxuexiao, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        Map.removeLayer('xuexiao');
    }
    xuexiaoFlag = !xuexiaoFlag;
}

//医院
var yiyuanFlag = true;
var publicPlaceFlag = true;
var idArryiyuan = [];

function yiyuanFun(_this) {
    if (yiyuanFlag) {
        $.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function (
            data) {
            var features = data.features;
            var len = features.length;
            idArryiyuan = [];
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    idArryiyuan.push(features[i].id);
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'data': data,
                'id': 'yiyuan',
                'isShow': true
            }])

        })
        $(_this).css('border', '1px solid #0e54a9')
        /*	Map.goTo([ "113.36105768169675", "34.54275331326893" ])*/
    } else {
        $.each(idArryiyuan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('yiyuan');
    }
    yiyuanFlag = !yiyuanFlag;
}

var jingcheFlag = true;

function jingcheFun(_this) {
    if (jingcheFlag) {
        $.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function (
            data) {
            Map.addJSON1([{
                'type': 'DanDian',
                'data': data,
                'id': 'jingche',
                'isShow': true
            }])
        })

        $(_this).css('border', '1px solid #0e54a9')
    } else {
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('jingche');
    }
    jingcheFlag = !jingcheFlag;
}

//警员
var jingyuanFlag = true;

function jingyuanFun(_this) {
    if (jingyuanFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceMobileMap', function (data) {
            Map.addJSON1([{
                'type': 'PopLocation',
                'id': 'jingyuan',
                'data': data,
                'isShow': true
            }]);
        })
        Map.layersIsShow('PopLocation', true);
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('jingyuan');
    }
    jingyuanFlag = !jingyuanFlag;
}

var jiayouzhanFlag = true;
var idArrjiayouzhan = [];

function jiayouzhanFun(_this) {
    if (jiayouzhanFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=1', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArrjiayouzhan = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrjiayouzhan.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'jiayouzhan',
                'data': data,
                'isShow': true
            }])
        })

        $(_this).css('border', '1px solid #0e54a9');
        //Map.goTo([ "113.39035820960999", "34.528061628341675" ])
    } else {
        $.each(idArrjiayouzhan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('jiayouzhan');
    }
    jiayouzhanFlag = !jiayouzhanFlag;
}

//商场超市
var shangchangFlag = true;
var idArrshangchang = [];

function shangchangFun(_this) {
    if (shangchangFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=2', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArrshangchang = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrshangchang.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'shangchang',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrshangchang, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('shangchang');
    }
    shangchangFlag = !shangchangFlag;
}

//娱乐场所
var yuleFlag = true;
var idArryule = [];

function yuleFun(_this) {
    if (yuleFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=3', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArryule = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArryule.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'yule',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArryule, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('yule');
    }
    yuleFlag = !yuleFlag;
}

//酒店宾馆
var binguanFlag = true;
var idArrbinguan = [];

function binguanFun(_this) {
    var id = 0;
    if (binguanFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=4', function (data) {
            if (data != null) {
                id = data.id;
                var features = data.features;
                var len = features.length;
                idArrbinguan = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrbinguan.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'binguan',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrbinguan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('binguan');
    }

    binguanFlag = !binguanFlag;
}

//涉危涉爆
var sheweishebaoFlag = true;
var idArrsheweishebao = [];

function sheweishebaoFun(_this) {
    if (sheweishebaoFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=5', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArrsheweishebao = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrsheweishebao.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'sheweishebao',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrsheweishebao, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('sheweishebao');
    }
    sheweishebaoFlag = !sheweishebaoFlag;
}

//警务室
var jingwushiFlag = true;
var idArrjingwushi = [];

function jingwushiFun(_this) {
    if (jingwushiFlag) {
        Map.removeLayer('policeroom');
        $.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=10', function (data) {
            // var features=data.features;
            // var len=features.length;
            // idArrjingwushi=[];
            // if(len>0){
            // 	for(var i=0;i<len;i++){
            // 		idArrjingwushi.push(features[i].id);
            // 	}
            // }
            Map.addJSON1([{
                'type': 'policeroom',
                'id': 'jingwushi',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        // $.each(idArrjingwushi,function(index,val){
        // 	Pubmap.removeOverlay(Map[''+val+'Overlay'])
        // });
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('jingwushi');
    }
    jingwushiFlag = !jingwushiFlag;
}

//工作站
var gongzuozhanFlag = true;
var idArrgongzuozhan = [];

function gongzuozhanFun(_this) {
    if (gongzuozhanFlag) {
        Map.removeLayer('workstation');
        $.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=11', function (data) {
            var features = data.features;
            var len = features.length;
            idArrgongzuozhan = [];
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    idArrgongzuozhan.push(features[i].id);
                }
            }
            Map.addJSON1([{
                'type': 'workstation',
                'id': 'gongzuozhan',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrgongzuozhan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('gongzuozhan');
    }

    gongzuozhanFlag = !gongzuozhanFlag;

}

//视频监控
var shipinjiankongFlag = true;

function shipinjiankongFun(_this) {
    if (shipinjiankongFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceiveMap', function (data) {
            Map.addJSON1([{
                'type': 'videos',
                'id': 'shipinjiankong',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
        $(_this).attr('VideoFlagAttr', true);
    } else {
        $(_this).css('border', '1px solid transparent');
        $(_this).attr('VideoFlagAttr', false);
        Map.removeLayer('shipinjiankong');
    }
    shipinjiankongFlag = !shipinjiankongFlag;
}

//广播站
var broadcastFlag = true;

function guangbozhanFun(_this) {
    if (broadcastFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceBroadcastMap', function (data) {
            Map.addJSON1([{
                'type': 'broadcast',
                'id': 'guangbozhan',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('guangbozhan');
    }
    broadcastFlag = !broadcastFlag;
}

//机顶盒
var SetTopBoxFlag = true;

function SetTopBoxFun(_this) {
    if (SetTopBoxFlag) {
        $.getJSON('' + ctx + '/sys/map/buildBox', function (data) {
            Map.addJSON1([{
                'type': 'topBox',
                'id': 'SetTopBoxFlag',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('SetTopBoxFlag');
    }
    SetTopBoxFlag = !SetTopBoxFlag;
}

function XiangQingFun(type) {
    $('.pubMapDialog').show()
    var XiangQingData = {
        "长乐路警务室": {
            "民警": [{
                '姓名': '慕观卿',
                '警号': '103802',
                '性别': '男',
                '电话': '17603711723',
                "src": 'muguanqing.JPG'
            }, {
                '姓名': '许小五',
                '警号': '103803',
                '性别': '男',
                '电话': '17603711728',
                "src": 'xuxiaowu.jpg'
            }, {
                '姓名': '郑首守',
                '警号': '103804',
                '性别': '男',
                '电话': '17503711759',
                "src": 'zhebgshoushou.jpg'
            }],
            "辅警": [{
                '姓名': '蒋尚涵',
                '警号': '103805',
                '性别': '男',
                '电话': '13674919298',
                "src": 'jiangshanghan.jpg'
            }, {
                '姓名': '李格',
                '警号': '103806',
                '性别': '女',
                '电话': '13509856056',
                "src": 'lige.JPG'
            }, {
                '姓名': '许伟强',
                '警号': '103807',
                '性别': '男',
                '电话': '13603986786',
                "src": 'xuweiqiang.jpg'
            }]
        },
        "于家岗警务工作站": {
            "民警": [{
                '姓名': '郭亚凯',
                '警号': '103808',
                '性别': '男',
                '电话': '15515991957',
                "src": 'guoyakai.jpg'
            }, {
                '姓名': '慕观卿',
                '警号': '103802',
                '性别': '男',
                '电话': '17603711723',
                "src": 'muguanqing.JPG'
            }, {
                '姓名': '吕一帆',
                '警号': '103809',
                '性别': '男',
                '电话': '13673992608',
                'src': 'lvyifan.jpg'
            }],
            "辅警": [{
                '姓名': '梁炎龙',
                '警号': '103810',
                '性别': '男',
                '电话': '18539436116',
                "src": 'lianyanlong.jpg'
            }, {
                '姓名': '楚褤铭',
                '警号': '103811',
                '性别': '男',
                '电话': '15803817833',
                "src": 'chuming.jpg'
            }, {
                '姓名': '申奥',
                '警号': '103812',
                '性别': '男',
                '电话': '15890009001',
                "src": 'shenao.jpg'
            }]
        },
        "嵩阳路警务工作站": {
            "民警": [{
                '姓名': '蒋尚涵',
                '警号': '103805',
                '性别': '男',
                '电话': '13674919298',
                "src": 'jiangshanghan.jpg'
            }, {
                '姓名': '马双科',
                '警号': '103813',
                '性别': '男',
                '电话': '15093337726',
                "src": 'mashuangke.JPG'
            }, {
                '姓名': '侯鹏',
                '警号': '103814',
                '性别': '男',
                '电话': '18437188688',
                "src": 'houpeng.JPG'
            }],
            "辅警": [{
                '姓名': '高志阳',
                '警号': '103815',
                '性别': '男',
                '电话': '15136205957',
                "src": 'gaozhiyang.jpg'
            }, {
                '姓名': '楚褤铭',
                '警号': '103811',
                '性别': '男',
                '电话': '15803817833',
                "src": 'chuming.jpg'
            }, {
                '姓名': '侯林山',
                '警号': '103817',
                '性别': '男',
                '电话': '15290869958',
                'src': 'houlinshan.jpg'
            }]
        },
        "广场警务工作站": {
            "民警": [{
                '姓名': '吕一帆',
                '警号': '103809',
                '性别': '男',
                '电话': '13673992608',
                'src': 'lvyifan.jpg'
            }, {
                '姓名': '慕观卿',
                '警号': '103802',
                '性别': '男',
                '电话': '17603711723',
                "src": 'muguanqing.JPG'
            }, {
                '姓名': '郭亚凯',
                '警号': '103808',
                '性别': '男',
                '电话': '15515991957',
                "src": 'guoyakai.jpg'
            }],
            "辅警": [{
                '姓名': '梁炎龙',
                '警号': '103810',
                '性别': '男',
                '电话': '18539436116',
                "src": 'lianyanlong.jpg'
            }, {
                '姓名': '高志阳',
                '警号': '103815',
                '性别': '男',
                '电话': '15136205957',
                "src": 'gaozhiyang.jpg'
            }, {
                '姓名': '侯林山',
                '警号': '103817',
                '性别': '男',
                '电话': '15290869958',
                'src': 'houlinshan.jpg'
            }]
        }
    }
    if (type == '长乐路警务室' || type == '于家岗警务工作站' || type == '嵩阳路警务工作站'
        || type == '广场警务工作站') {
        var data = XiangQingData[type];
        console.log(data)
        var data1 = data['民警'];
        var data2 = data['辅警'];
        var len1 = data1.length;
        var len2 = data2.length;
        var html = "";
        html += '<div class="pubMapDialog-center1"><div> 民警： </div>';
        for (var i = 0; i < len1; i++) {
            html += '<div class="well-item">'
            html += '<div  class="jingcha-name correct">';
            html += '<div  class="jiachatouxiang" style="width:100%;height:100%;"><img src="/arjccm/static/modules/map/images/pub/'
                + data1[i]['src'] + '" /></div>';
            html += ' <p>' + data1[i]['姓名'] + '</p>';
            html += '<p>' + data1[i]['警号'] + '</p>';
            html += ' </div>'
            html += '<div class="opposite"><div>';
            html += '<table>';
            html += '<tr>';
            html += '<td align="right" >姓名：</td><td align="left" >'
                + data1[i]['姓名'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >性别：</td><td align="left">'
                + data1[i]['性别'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >警号：</td><td align="left">'
                + data1[i]['警号'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >电话：</td><td align="left">'
                + data1[i]['电话'] + '</td>';
            html += '</tr>';
            html += '</table>';
            html += '</div></div>';
            html += '</div>'
        }
        html += ' </div>'
        html += '<div  class="pubMapDialog-center2"><div>辅警： </div>';
        for (var i = 0; i < len2; i++) {
            html += '<div class="well-item">'
            html += '<div  class="jingcha-name correct">';
            html += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="/arjccm/static/modules/map/images/pub/'
                + data2[i]['src'] + '" /></div>';
            html += ' <p>' + data2[i]['姓名'] + '</p>';
            html += '<p>' + data2[i]['警号'] + '</p>';
            html += ' </div>';
            html += '<div class="opposite"><div>';
            html += '<table>';
            html += '<tr>';
            html += '<td  align="right" >姓名：</td><td align="left">'
                + data2[i]['姓名'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >性别：</td><td align="left">'
                + data2[i]['性别'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >警号：</td><td align="left">'
                + data2[i]['警号'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >电话：</td><td align="left">'
                + data2[i]['电话'] + '</td>';
            html += '</tr>';
            html += '</table>';
            html += '</div></div>';
            html += '</div>'
        }
        html += ' </div>'

        $('.pubMapDialog-center-center').html(html)

        $(".well-item").hover(function () {
            $(this).find(".correct").children().removeClass('test');
            $(this).find(".correct").children().removeClass('test2');
            $(this).find(".opposite").children().removeClass('test');
            $(this).find(".opposite").children().removeClass('test2');
            $(this).find(".correct").children().addClass("test");
            $(this).find(".opposite").children().addClass('test2');
        }, function () {
            $(this).find(".correct").children().removeClass('test');
            $(this).find(".opposite").children().removeClass('test');
            $(this).find(".correct").children().removeClass("test2");
            $(this).find(".opposite").children().removeClass("test2");
            $(this).find(".correct").children().addClass("test2");
            $(this).find(".opposite").children().addClass('test');
        });

    } else if (type == '骨科医院' || type == "眼科医院") {
        var data = {
            "骨科医院": {
                "级别": "一级医院",
                "经营性质": "县市区直属",
                "重点部位": "化验生化室、放射科",
                "基本情况": "新密市骨科医院是经郑州市卫生局批准执业的骨科专业机构，是以骨科、显微外科、普外为特色优势，集创伤急救、医疗、保健、功能康复为一体的专科医院。",
                'src': 'guke.jpg',
            },
            "眼科医院": {
                "级别": "一级医院",
                "经营性质": "县市区直属",
                "重点部位": "病房、治疗室",
                "基本情况": "新密市眼科医院是全省最大的一所县（市）级眼科专科医院。该医院位于新密市嵩山大道123号总建筑面积6000余平方米，医务人员100多人，其中副主任医师4人，中级职称人员30余人。 ",
                'src': 'yanke.jpg',
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">级别：</td>';
        html += '<td class="textcenterleft">' + data[type]["级别"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">经营性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["经营性质"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';

        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '金凤路小学' || type == "金凤路初中") {
        var data = {
            "金凤路小学": {
                "性质": "消防重点单位",
                "学校地址": "西大街与金凤路交叉口北150米",
                "重点部位": "教室、办公室",
                "基本情况": "新密市金凤路小学是隶属于新密市教体局的一所市直学校,占地面积近24亩,建筑面积10398平方米。学校环境优美,教学设施齐全,设有梦想教室、美术教室等",
                'src': 'xiaoxue.jpg'
            },
            "金凤路初中": {
                "性质": "消防重点单位",
                "学校地址": "金凤路与青屏大街交叉口西南150米",
                "重点部位": "教室、办公室",
                "基本情况": "金凤路初中学校建有教学综合楼、科技实验楼、综合办公楼、体育馆、运动场占地四十余亩，是一所教育教学设施一流的现代化的城市初级中学 ",
                'src': 'xuexiao2.png'
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">学校地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["学校地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '郑交运集团加油站(新密分公司)' || type == "亚立石化加油站(开阳路)") {
        var data = {
            "郑交运集团加油站(新密分公司)": {
                "性质": "消防重点单位",
                "重点部位": "油库、加油作业区",
                "单位地址": "郑交运集团加油站(新密分公司)",
                "基本情况": "成立于1996年7月。主要经营汽油、柴油、润滑油销售等。",
                'src': 'zhengjiao.jpg'
            },
            "亚立石化加油站(开阳路)": {
                "性质": "消防重点单位",
                "重点部位": "油库、加油作业区",
                "单位地址": "河南省郑州市新密市嵩山大道669",
                "基本情况": "河南亚立石油化工有限公司，成立于2001年9月。主要经营车用乙醇汽油、柴油、煤油零售与批发，预包装食品零售，化工产品经销，仓储，燃料油、润滑油、日用百货的销售，经营进出口贸易等业务。 ",
                'src': 'yali.jpg'
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + ' " /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '承誉德大酒店' || type == "鑫海湾假日酒店" || type == "福海商务宾馆") {
        var data = {
            "承誉德大酒店": {
                "性质": "消防重点单位",
                "单位地址": "郑州市新密市西大街88号",
                "重点部位": "客房、餐厅",
                "基本情况": "郑州承誉德大酒店是一家集餐饮、客房、娱乐、休闲洗浴及商务会议活动为一体的多功能大型商务涉外酒店。",
                'src': 'chengyude.png',
            },
            "鑫海湾假日酒店": {
                "性质": "消防重点单位",
                "单位地址": "郑州市新密市平安路225号",
                "重点部位": "客房、餐厅",
                "基本情况": "新密鑫海湾假日酒店融顶尖豪华酒店设计理念、艺术构筑于一体，采用新型绿色环保材料、安全健康科技的人文智能控制系统，拥有各式舒适客房，并设有茶室、棋牌室、足浴等配套服务设施。",
                'src': 'xinhaiwan.jpg',
            },
            "福海商务宾馆": {
                "性质": "消防重点单位",
                "单位地址": "郑州市新密市鑫苑花园东楼",
                "重点部位": "客房、餐厅",
                "基本情况": "福海商务宾馆，于2008年正式营业，是一家住宿、休闲、餐饮为一体的经营式现代企业。",
                'src': 'fuhai.jpg',
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '金博大购物中心') {
        var data = {
            "金博大购物中心": {
                "性质": "消防重点单位",
                "商场地址": "郑州市新密市西大街276号",
                "重点部位": "仓库、逃生通道",
                "基本情况": "新密金博大购物中心有限公司于2007年06月14日在新密市工商行政管理局登记成立。法定代表人楚文杰，公司经营范围包括百货、家用电器、服装、鞋帽、五金交电、皮革制品等。",
                'src': 'jingboda.jpg'
            }
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img style="width:48px;height:48px" src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">商场地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["商场地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '麦克疯量贩KTV' || type == '糖果娱乐空间') {
        var data = {
            "麦克疯量贩KTV": {
                "性质": "治安消防单位",
                "单位地址": "新密市长乐路515号",
                "重点部位": "客房、配电室",
                "基本情况": "麦克疯量贩KTV新密店以人性化的布局，奢华的装饰装潢，让消费者感受贴心的服务和自由的欢唱；麦克疯量贩KTV新密店本着独特的消费方式及完善的服务理念，致力于为您打造价格低、音响好、歌曲全、服务优的健康纯K歌场所。",
                'src': 'maikefeng.jpg'
            },
            "糖果娱乐空间": {
                "性质": "治安消防单位",
                "单位地址": "新密市西大街368号",
                "重点部位": "客房、配电室",
                "基本情况": "糖果娱乐空间内部装饰时尚、典雅、舒适，消费价格低廉。是好友聚会、家人娱乐、生日庆典、商务休闲的一个极佳的文化娱乐场所。 ",
                'src': 'tanguo.jpg'
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';

        $('.pubMapDialog-center-center').html(html)
    } else if (type == '烟花爆竹专营店(新华路三店)') {
        var data = {
            "烟花爆竹专营店(新华路三店)": {
                "性质": "涉危涉爆重点单位",
                "重点部位": "仓库",
                "单位地址": "郑州市新密市南密新路168号",
                "基本情况": "烟花爆竹专营店已取得取得《烟花爆竹经营（批发）许可证》和《烟花爆竹经营（零售）许可证》的规范专营店",
                'src': 'baozhu.jpg'
            }
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    }

}

function shipinjiankongXiangqingFun(src, name) {
    $('.pubMapDialog').show()
    if (name == '视频监控1' || name == '视频监控2' || name == '视频监控3'
        || name == '视频监控4') {
        var html = '';
        html += '<video width="440" height="250"  controls="controls" autoplay="autoplay" loop="loop" class="videoBtn">';
        html += '<source src="/arjccm/static/modules/map/images/pub/video/'
            + src + '" type="video/mp4">';
        html += '</video>';
        $('.pubMapDialog-center-center').html(html)
    } else if (name == "豫A0S81Q" || name == "豫A0519H") {
        var data = {
            "豫A0S81Q": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "广场警务工作站",
                "目前位置": "新密市西大街西段1号"
            },
            "豫A0519H": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "长乐路警务工作站",
                "目前位置": "新密市西大街西段1号"
            }
        }
        var html = '';
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/jingche2.png" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + name + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">任务：</td>';
        html += '<td class="textcenterleft">' + data[name]["任务"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点路线：</td>';
        html += '<td class="textcenterleft">' + data[name]["重点路线"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">所属单位：</td>';
        html += '<td class="textcenterleft">' + data[name]["所属单位"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">目前位置：</td>';
        html += '<td class="textcenterleft">' + data[name]["目前位置"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)

    } else if (name == "吕一凡" || name == "王庆豪") {
        var data = {
            "吕一凡": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "广场警务工作站",
                "目前位置": "新密市大鸿路159号"
            },
            "王庆豪": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "长乐路警务工作站",
                "目前位置": "新密市大鸿路159号"
            }
        }
        var html = '';
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/jingcha2.png" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + name + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">任务：</td>';
        html += '<td class="textcenterleft">' + data[name]["任务"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点路线：</td>';
        html += '<td class="textcenterleft">' + data[name]["重点路线"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">所属单位：</td>';
        html += '<td class="textcenterleft">' + data[name]["所属单位"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">目前位置：</td>';
        html += '<td class="textcenterleft">' + data[name]["目前位置"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    }

}

//框选查询
function boxSelectionDevice(data, type) {
    var url = "";
    if (type == "circle") {
        url = ctx + '/sys/map/showSelect?x=' + data.centerX + '&y=' + data.centerY + '&radius=' + data.radius;
    } else {
        url = ctx + '/sys/map/showSelect?points1=' + data.xyList;
    }
    $.getJSON(url, function (val) {
        var html = "";
        html += '<div class="layer-common" style="width: 92.3%;height: 81.3%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">框选查询</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 15px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '<table style="width:100%;height:100%">';
        html += '<tr>';
        html += '<td>框选区域总人口数：</td>';
        html += '<td>' + val.sumPeople + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>重点人员数量：</td>';
        html += '<td>' + val.pNum + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>学校数量：</td>';
        html += '<td>' + val.sumSchool + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>警务工作站数量：</td>';
        html += '<td>' + val.sumPoliceRoom + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>工作人员数量：</td>';
        html += '<td>' + val.sumPolice + '</td>';
        html += '</tr>';
        html += '</table>';
        html += '</div>'
        html += '</div>'
        layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["300", "200px"],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            id: "showSelect",
            content: html,
            cancel: function () {
                Map.clearShape();
                // 关闭事件

            }
        });

    })
}

var palyVideIdLayer;

function playTodayVideo(id) {
    // 	debugger
    // 	// if(openflag){
    // 	// 	layer.close(layer.index);
    // 	// 	openflag = false;
    // 	// 	// palyVideIdLayer ++;
    // 	// } else {
    // 	// 	openflag = true;
    // 	// }
    // layer.close(palyVideIdLayer);
    palyVideoBegin(id);
}

function palyVideoBegin(id) {
    var ccmDeviceIncidentId = id;
    var html = "";
    html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
    html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
    html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">视频监控</div>'
    html += '</div>'
    html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
        + ctxStatic
        + '/common/index/images/showbg.png);background-size: 100% 100%;">'
    html += '	<iframe  name="mainFrame" src="'
        + ctx
        + '/ccmsys/ccmDevice/getDeviceMap?id='
        + ccmDeviceIncidentId
        + '" style="overflow: visible;" scrolling="yes" frameborder="no" width="570" height="360" allowfullscreen="true" allowtransparency="true"></iframe>'
    html += '</div>'
    html += '</div>'
    palyVideIdLayer = layer.open({
        type: 1,
        shade: false,
        title: false, // 不显示标题
        area: ["600px", "400px"],
        offset: ['70px', '1300px'],
        move: '.layer-common-header',
        resize: false,
        fixed: false,
        content: html,
        id: 'TodayVideoLayer',
        cancel: function () {
            // 关闭事件
            // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
            // icon:6});
        }
    });
}