/**
 * Created by oHa on 2018/1/25.
 */

var ArjMap = window.ArjMap = {};
ArjMap.Map = function (params) {
    this.id = params.id;// 容器ID
    this.baseUrl = params.baseUrl || {
        'name': ' ',
        'url': '',
        'isShow': true
    };// 地图--底图
    this.urlArr = params.urlArr || '';// 地图---数组
    this.center = params.centerCoordinate || '';// 地图中心点[经度,纬度]
    this.zoom = params.zoom || ''; // 地图加载级别
    this.maxZoom = params.maxZoom || ''; // 最大放大级别
    this.minZoom = params.minZoom || ''; // 最小缩放级别
    this.zoomShowOrHide = params.zoomShowOrHide || false; // 右上角地图+-控制
    this.showOLpage = params.showOLpage||false;
    this.map = null;
    //this.view = null;
    this.content = null;
    this.overlayDialog = null;
    this.overlayGISDialog = null;
    // 事件
    this.postcomposeOlName = null;
    this.postcomposeOlCoordinate = null;
    this.postcomposeOlId = null;
    this.postcomposeOlObj = null
    this.happenDate = null;

    // 标注信息
    this.markInfoId = null;
    this.markInfoType = null;
    this.selectedNodes = null;

    // 标注容器
    this.geoStrDraw = null; // 当前绘制图形的坐标串
    this.areaPointDraw = '';
    this.areaMapDraw = '';
    this.currentFeatureDraw = null; // 当前绘制的几何要素
    // 巡逻点位计数和标序
    this.planFlagLine = null;
    this.planFlagInfoCoordinates = null;
    this.selectPlanFlagCont = 0;// 顺序计数
    this.selectPlanFlagIdStr = "";// 获取id
    this.selectPlanFlagObj = [];// 存储顺序及对应id
    this.selectPlanFlag = null;
    this.removePlanFlag = null;
    // 全屏标志
    this.fullScreenFlag = true;
    // 详情
    this.selectPointerFlag = params.selectPointerFlag || false;
    // 鹰眼图是否显示;
    this.overviewmap = params.overviewMap || false;
    //比例尺控件
    this.scaleLinemapShow = params.scaleLinemapShow || false;
    this.units = params.units || "metric";//度量单位
    this.scaleLineClass = params.scaleLineClass || "ol-scale-line";
    /*this.colorArr = [ {
		"color" : "#aa4644",
		"rgba" : "rgba(170,70,68,0.8)"
	}, {
		"color" : "#4573a7",
		"rgba" : "rgba(69,115,167,0.8)"
	}, {
		"color" : "#89a54e",
		"rgba" : "rgba(137,165,78,0.8)"
	}, {
		"color" : "#71588f",
		"rgba" : "rgba(113,88,143,0.8)"
	}, {
		"color" : "#4298af",
		"rgba" : "rgba(66,152,175,0.8)"
	}, {
		"color" : "#db843d",
		"rgba" : "rgba(219,132,61,0.8)"
	}, {
		"color" : "#93a9d0",
		"rgba" : "rgba(147,169,206,0.8)"
	}, {
		"color" : "#d09392",
		"rgba" : "rgba(208,147,146,0.8)"
	}, {
		"color" : "#b9ce96",
		"rgba" : "rgba(185,206,150,0.8)"
	}, {
		"color" : "#a99bbc",
		"rgba" : "rgba(169,155,188,0.8)"
	}, {
		"color" : "#92c3d4",
		"rgba" : "rgba(146,195,212,0.8)"
	}, {
		"color" : "#ffdf5f",
		"rgba" : "rgba(255,223,95,0.8)"
	}]*/


    /*this.colorArr = [ {
		"color" : "#c985fe",
		"rgba" : "rgba(201,133,254,0.8)"
	}, {
		"color" : "#7ae7b2",
		"rgba" : "rgba(122,231,178,0.8)"
	}, {
		"color" : "#5db0fd",
		"rgba" : "rgba(93,176,253,0.8)"
	}, {
		"color" : "#e1e8b2",
		"rgba" : "rgba(225,232,178,0.8)"
	}, {
		"color" : "#82a6f5",
		"rgba" : "rgba(130,166,245,0.8)"
	}, {
		"color" : "#eacf025",
		"rgba" : "rgba(234,207,2,0.8)"
	}, {
		"color" : "#ff534d",
		"rgba" : "rgba(255,83,77,0.8)"
	}, {
		"color" : "#37c6c0",
		"rgba" : "rgba(55,198,192,0.8)"
	}, {
		"color" : "#bf00ff",
		"rgba" : "rgba(191,0,255,0.5)"
	}, {
		"color" : "#ff80ff",
		"rgba" : "rgba(255,128,255,0.8)"
	}]
*/

    this.colorArr = [{
        "color": "#c985fe",
        "rgba": "rgba(201,133,254,0.8)"
    }, {
        "color": "#7ae7b2",
        "rgba": "rgba(122,231,178,0.8)"
    }, {
        "color": "#5db0fd",
        "rgba": "rgba(93,176,253,0.8)"
    }, {
        "color": "#e1e8b2",
        "rgba": "rgba(225,232,178,0.8)"
    }, {
        "color": "#82a6f5",
        "rgba": "rgba(130,166,245,0.8)"
    }, {
        "color": "#eacf025",
        "rgba": "rgba(234,207,2,0.8)"
    }, {
        "color": "#ff82ab",
        "rgba": "rgba(255,130,171,0.8)"
    }, {
        "color": "#ee7ae9",
        "rgba": "rgba(238,122,233,0.8)"
    }, {
        "color": "#fc9d9a",
        "rgba": "rgba(252,157,154,0.8)"
    }, {
        "color": "#66cccc",
        "rgba": "rgba(102,204,204,0.8)"
    }, {
        "color": "#66cccc",
        "rgba": "rgba(102,204,204,0.8)"
    }, {
        "color": "#ffcc00",
        "rgba": "rgba(255,204,0,0.8)"
    }, {
        "color": "#99cc99",
        "rgba": "rgba(153,204,153,0.8)"
    }, {
        "color": "#99ccff",
        "rgba": "rgba(153,204,255,0.8)"
    }, {
        "color": "#ccff99",
        "rgba": "rgba(204,255,153,0.8)"
    }]

    /*	this.colorArr = [ {
		"color" : "rgba(201,133,254,0.8)",//紫
		"rgba" : "rgba(201,133,254,0.8)"
	}, {
		"color" : "rgba(122,231,178,0.8)",//绿
		"rgba" : "rgba(122,231,178,0.8)"
	}, {
		"color" : "rgba(93,176,253,0.8)",//蓝
		"rgba" : "rgba(93,176,253,0.8)"
	}, {
		"color" : "rgba(225,232,178,0.8)",//黄
		"rgba" : "rgba(225,232,178,0.8)"
	}, {
		"color" : "rgba(254,198,103,0.8)",//wo
		"rgba" : "rgba(254,198,103,0.8)"
	}]*/
}

/**
 * 加载地图
 */

ArjMap.Map.prototype = {
    //初始化
    init: function () {
        var _this = this;
        //初始化题图地图
        this.map = this.drawMap();

        //鼠标悬上选中图层样式
        this.addInteractionHover();

        //鹰眼图
        if (this.overviewmap) {
            _this.overviewMap();
        }
        ;
        //比例尺控件
        if (this.scaleLinemapShow) {
            _this.scaleLineMap();
        }
        ;
        //自定义放大缩小方向控件
        this.addPanZoomBar();
    },
    //地图底图数据图层
    baseUrlTypeLayers: function () {
        var baseUrl = this.baseUrl;
        var layers = [];
        var len = baseUrl.length;
        if (len > 0) {
            for (var i = 0; i < len; i++) {

                //地图瓦片类型判断
                var id = baseUrl[i].id;
                var type = baseUrl[i].type;
                if(typeof (type)!="undefined"){
                    switch (type) {
                        //杭州道现场地图
                        case '1':
                            this[id + 'Tile'] = new ol.layer.Tile({
                                title: "地图",
                                opacity: 1,//透明度
                                name: baseUrl[i].name,//名称
                                visible: baseUrl[i].isShow,//显示隐藏
                                source: new ol.source.XYZ({
                                    tileGrid: new ol.tilegrid.TileGrid({
                                        tileSize: baseUrl[i].tileSize,//瓦片大小
                                        origin: baseUrl[i].origin, // 原点
                                        resolutions: baseUrl[i].resolutions//分辨率

                                    }),
                                    url: baseUrl[i].url,//地址
                                    projection: 'EPSG:4326'
                                })
                            });
                            break;
                        //天地地图
                        case '2':
                            this[id + 'Tile'] = new ol.layer.Tile({
                                title: "地图",
                                name: baseUrl[i].name,
                                visible: baseUrl[i].isShow,
                                source: new ol.source.XYZ({
                                    url: baseUrl[i].url,
                                })
                            });
                            break;
                        //arcgis
                        case '3':

                            this[id + 'Tile'] = new ol.layer.Tile({
                                title: "地图",
                                name: baseUrl[i].name,
                                visible: baseUrl[i].isShow,
                                source: new ol.source.TileArcGISRest({
                                    url: baseUrl[i].url,
                                })
                            });
                            break;
                        default:
                    }

                    layers.push(this[id + 'Tile'])
                }

            }
        }
        return layers

    },
    //创建视图
    view: function () {
        var view = new ol.View({
            center: this.center,//中心位置
            zoom: this.zoom,//当前层级
            minZoom: this.minZoom,//最小层级
            maxZoom: this.maxZoom,//最大层级
            projection: 'EPSG:4326'
        });
        return view;
    },
    //创建地图
    drawMap: function () {
        var map = new ol.Map({
            layers: this.baseUrlTypeLayers(),
            view: this.view(),
            target: this.id,//地图容器id
            controls: ol.control.defaults({
                zoom: this.zoomShowOrHide,//图层放大缩小控件是否显示
                attribution: this.showOLpage
            })
        });
        return map;
    },
    //加载GeoJSON数据
    vectorGeoJSON: function (data) {
        var vector = new ol.source.Vector({
            format: new ol.format.GeoJSON(),
            features: (new ol.format.GeoJSON()).readFeatures(data)
        });
        return vector;
    },
    //点聚合
    cluster: function (source) {
        var cluster = new ol.source.Cluster({
            distance: 20,
            source: source,
            geometryFunction: function (feature) {
                if (feature.getGeometry().getType() == "Point") {
                    return feature.getGeometry();
                } else {
                    return null;
                }
            }
        });
        return cluster

    },
    //图层样式
    featureStyles: function (variable, feature) {
        var map = this.map;
        var colorArr = this.colorArr;
        var index = Math.floor((Math.random() * colorArr.length));
        var fillColor = colorArr[index].rgba;
        var color = colorArr[index].color;
        var styles = {
            //楼栋样式-fill透明、stroke透明、含有文字
            'builds': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(255, 255, 255, 0)',
                    width: 3
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    font: 'normal 14px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })

            }),
            //楼栋样式-fill透明、stroke透明、不含文字
            'publicPlace': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(255, 255, 255, 0)',
                    width: 3
                }),
//							text: new ol.style.Text({
//	                                textAlign: 'center', // 位置
//	                                textBaseline: 'middle', // 基准线
//	                                exceedLength:'true',
//	                                font: 'normal 10px 微软雅黑',  // 文字样式
//	                                text: feature.get('name'),  // 文本内容
//	                                fill: new ol.style.Fill({ color: '#aa3300' }), // 文本填充样式（即文字颜色）
//	                                stroke: new ol.style.Stroke({ color: '#ffcc33', width: 2 })
//                            })

            }),
            //重点人员楼栋样式
            'keyPerson': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(248, 61, 61, 1)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(248, 61, 61, 1)',
                    width: 3
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    font: 'normal 10px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })

            }),
            //快捷键搜索Shortcut
            'Shortcut': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(254,198,103,0.8)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#0099ff',
                    width: 1
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    font: 'normal 10px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })

            }),
            //出租屋楼栋样式
            'rentingPerson': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(238, 168, 7, 1)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(238, 168, 7, 1)',
                    width: 3
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    font: 'normal 10px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })
            }),
            //巡逻计划
            'PatrolPlan': new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 3
                }),
                image: new ol.style.Circle({
                    radius: 10,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'top', // 基准线
                    offsetY: '10',
                    exceedLength: 'true',
                    font: 'normal 10px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })

            }),
            //巡逻结果
            'resultCheck': new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 10,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                }),
                fill: new ol.style.Fill({
                    color: variable,
                }),
                stroke: new ol.style.Stroke({
                    color: variable,
                    width: 5
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'top', // 基准线
                    offsetY: '10',
                    font: 'normal 10px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })

            }),
            //案事件小区
            'alarmcommunitys': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 3
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    font: 'normal 12px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#fff'}), // 文本填充样式（即文字颜色）
                    //stroke: new ol.style.Stroke({ color: '#fff', width: 1 })
                })

            }),
            'GIS': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'top', // 基准线
                    offsetY: '-14',
                    exceedLength: 'true',
                    font: 'normal 10px 微软雅黑',  // 文字样式
                    text: feature.get('nameNum'),  // 文本内容
                    fill: new ol.style.Fill({color: '#fff'}), // 文本填充样式（即文字颜色）
                })

            }),
            //默认
            'default': new ol.style.Style({
                image: new ol.style.Icon({
                    src: ctxStatic + '/modules/map/images/' + variable + '',
                    scale: map.getView().getZoom() / 15
                }),
                fill: new ol.style.Fill({
                    color: fillColor ? fillColor : 'rgba(255, 255, 255, 0.6)'
                }),
                stroke: new ol.style.Stroke({
                    color: color ? color : 'blue',
                    width: 3
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    font: 'normal 12px 微软雅黑',  // 文字样式
                    text: feature.get('name'),  // 文本内容
                    fill: new ol.style.Fill({color: '#fff'}), // 文本填充样式（即文字颜色）
                    //stroke: new ol.style.Stroke({ color: '#fff', width: 1 })
                })

            })
        };
        return styles;
    },
    //鼠标悬上图层显示样式
    addInteractionHover: function () {
        var map = this.map;
        var _this = this;
        var HoverSelect = new ol.interaction.Select({
            condition: ol.events.condition.pointerMove, // 设置鼠标移到feature上就选取
            filter: function (feature, layer) {
                return layer != _this['riceDrawVector']//禁止选中默认1千米范围圆圈
            },
            style: new ol.style.Style({//样式
                image: new ol.style.Circle({
                    radius: 10,
                    fill: new ol.style.Fill({
                        color: '#0099ff'
                    })
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.6)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#0099ff',
                    width: 2
                })
            })
        });
        map.addInteraction(HoverSelect);
    },
    //比例尺
    scaleLineMap: function () {
        var map = this.map;
        var scaleLineControl = new ol.control.ScaleLine({
            className: this.scaleLineClass,
            //设置度量单位为米
            units: this.units
        });
        map.addControl(scaleLineControl);
    },
    //鹰眼图
    overviewMap: function () {
        var map = this.map;
        var overviewMapControl = new ol.control.OverviewMap({
            className: "ol-overviewmap ol-custom-overviewmap",
            view: new ol.View({
                projection: 'EPSG:4326'
            }),
            collapsed: false// 初始为展开显示方式
        });
        map.addControl(overviewMapControl)
    },
    //图层显示隐藏
    layersIsShow: function (type, isShow) {
        var map = this.map;
        var layer = this[type];
        if (layer) {
            layer.setVisible(isShow);
        }
    },
    //自定义放大缩小方向控件
    addPanZoomBar: function () {
        var map = this.map;
        var _this = this;
        var view = map.getView();
        var high_top1, high_top2;
        if ($("#PanZoomBar").length > 0) {
            $("#PanZoomBar").draggable({
                axis: "y",
                containment: "#ControlPanZoomBar",
                start: function (e1) {
                    high_top1 = parseInt(document.getElementById("PanZoomBar").style.top);
                },
                stop: function (e2) {
                    high_top2 = parseInt(document.getElementById("PanZoomBar").style.top);
                    // 此处的_this.maxZoom为地图最大的放大级数，250代替滑块150的为滑块轴的高度，16位滑块的高度单位为px,对计算出来的小数向上取整
                    var realzoom = Number(_this.maxZoom) - Number(_this.maxZoom) * high_top2 / (303 - 16);
                    // 设置view zoom值
                    view.setZoom(realzoom);
                }
            });
            // view上绑定zoom改变事件
            view.on('change:resolution', function (e) {
                var zommleve = view.getZoom();
                // 此处的_this.maxZoom为地图最大的放大级数，用210代替110为滑块轴的高度，16位滑块的高度
                var high = (Number(_this.maxZoom) - zommleve) * (303 - 16) / Number(_this.maxZoom);
                document.getElementById("PanZoomBar").style.top = high + "px"
            });
        }
    },
    //定位到某一坐标位置
    goTo: function (point) {
        var map = this.map;
        var view = map.getView();
        //坐标
        var centpoint = point || this.center;
        //动画300ms定位将坐标设为中心点
        view.animate({
            center: centpoint,
            duration: 300
        });
    },
    //地图切换-影像图和普通地图相互切换
    switchMap: function () {
        var map = this.map;
        var baseUrl = this.baseUrl;
        var len = baseUrl.length;
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                var id = baseUrl[i].id;
                //设置底图图层显示隐藏
                this[id + 'Tile'].setVisible(!this[id + 'Tile'].getVisible())

            }
        }
    },
    //缩放控件--放大、缩小
    zoomInOut: function (type) {
        var map = this.map;
        var view = map.getView();
        var zoom = view.getZoom();
        if (type == 'in') {
            zoom = zoom + 0.5;
        } else if (type == 'out') {
            zoom = zoom - 0.5;
        }
        view.animate({
            zoom: zoom,
            duration: 300
        });
    },
    //地图平移
    panDirection: function (direction) {
        var map = this.map;
        var view = map.getView();
        var lonlat = 0.06;// 平移系数
        var mapCenter = ol.proj.fromLonLat(view.getCenter());// 转化为投影坐标
        switch (direction) {
            case "north":
                mapCenter[1] += lonlat * Math.pow(2, 30 - view.getZoom());
                break;
            case "south":
                mapCenter[1] -= lonlat * Math.pow(2, 30 - view.getZoom());
                break;
            case "west":
                mapCenter[0] -= lonlat * Math.pow(2, 30 - view.getZoom());
                break;
            case "east":
                mapCenter[0] += lonlat * Math.pow(2, 30 - view.getZoom());
                break;
        }

        // 更改center 实现地图平移--动画
        view.animate({
            center: ol.proj.toLonLat(mapCenter),
            duration: 300
        });
        // 转化为经纬度坐标
        // view.setCenter(ol.proj.toLonLat(mapCenter));
        // 地图渲染
        map.render();
    },
    //事件动画
    postcomposeOl: function (name, coordinate, id, obj, happenDate) {
        this.postcomposeOlName = name;
        this.postcomposeOlCoordinate = coordinate;
        this.postcomposeOlId = id;
        this.postcomposeOlObj = obj;
        this.happenDate = (happenDate == null ? '' : happenDate)
        var map = this.map;
        this.addEventOverlayLabel(name, coordinate, id);
        //创建矢量图层
        var layer = new ol.layer.Vector({
            source: new ol.source.Vector(),
            zIndex: 9
        })
        map.addLayer(layer);
        map.getView().setZoom(19);
        this.goTo(coordinate);//定位到中心

        var circle = new ol.Feature({
            geometry: new ol.geom.Point(coordinate),
            name: 'EventFeature',
            id: id
        });
        circle.setStyle(new ol.style.Style({
            image: new ol.style.Circle({
                radius: 0,
                stroke: new ol.style.Stroke({
                    color: 'red',
                    size: 1
                }),
                fill: new ol.style.Fill({
                    color: 'red'
                }),
            })

        }));
        layer.getSource().addFeature(circle);

        // 关键的地方在此：监听postcompose事件，在里面重新设置circle的样式
        var radius = 0;
        map.on('postcompose', function () {
            // 增大半径，最大20
            radius++;
            radius = radius % 60;
            // 设置样式
            circle.setStyle(new ol.style.Style({
                image: new ol.style.Circle({
                    radius: radius / 2,
                    snapToPixel: false,
                    fill: new ol.style.Fill({
                        color: 'red'
                    }),
                    stroke: new ol.style.Stroke({
                        color: 'red',
                        size: 1,
                        width: 3
                    })
                })
            }));
        })
    },
    // 图层叠加及管理
    layersControl: function (id) {
        var map = this.map;
        var layer = []; // map中的图层数组
        var layerName = []; // 图层名称数组
        var layerVisibility = []; // 图层可见属性数组
        var treeContent = document.getElementById(id); // 图层目录容器
        var layers = map.getLayers(); // 获取地图中所有图层
        for (var i = 0; i < layers.getLength(); i++) {
            // 获取每个图层的名称、是否可见属性
            layer[i] = layers.item(i);
            layerName[i] = layer[i].get('name');
            layerVisibility[i] = layer[i].getVisible();
            // 新增li元素，用来承载图层项
            var elementLi = document.createElement('li');
            treeContent.appendChild(elementLi); // 添加子节点
            // 创建复选框元素
            var elementInput = document.createElement('input');
            elementInput.type = "checkbox";
            elementInput.name = "layers";
            elementLi.appendChild(elementInput);
            // 创建label元素
            var elementLable = document.createElement('label');
            elementLable.className = "layer";
            // 设置图层名称
            if (typeof elementLable.textContent == "string") {
                elementLable.textContent = layerName[i];
            } else {
                elementLable.innerText = layerName[i];
            }
            elementLi.appendChild(elementLable);
            // 设置图层默认显示状态
            if (layerVisibility[i]) {
                elementInput.checked = true;
            }
            // 为checkbox添加变更事件
            addChangeEvent(elementInput, layer[i]);

            function addChangeEvent(element, layer) {
                element.onclick = function () {
                    if (element.checked) {
                        // 显示图层
                        layer.setVisible(true);
                    } else {
                        // 不显示图层
                        layer.setVisible(false);
                    }

                };
            }
        }
    },
    // 删除图层
    removeLayer: function (type) {
        var map = this.map;
        var layer = this[type]
        if (layer) {
            // 获取地图中所有图层
            var layers = map.getLayers();
            var len = layers.getLength();

//				var selectFeatures = layer.getSource().getFeatures();
//				for ( var i in selectFeatures) {
//					map.removeOverlay(this[(selectFeatures[i].get('name') + 'Overlay')])
//				}
            map.removeLayer(layer)
        }
    },
    //清空图层
    clearOverlays: function () {
        var _this = this;
        var map = this.map;
        map.getOverlays().clear();
        if (this.overlayDialog) {
            map.addOverlay(this.overlayDialog)
        }

        if (_this.overlayGISDialog) {
            map.addOverlay(_this.overlayGISDialog)
        }
        if (this.postcomposeOlId != null && this.postcomposeOlId != '') {
            this.addOverlayLabel(this.postcomposeOlName, this.postcomposeOlCoordinate)
        }
    },
    //地图全屏
    fullScreen: function () {
        var flag = this.fullScreenFlag;
        if (this.fullScreenFlag) {
            var element = document.getElementById("FullBody");
            requestFullScreen(element)
            $('#fullScreen span').html('退出');
            $('#fullScreen i').addClass('tool-small');
            this.fullScreenFlag = false;
        } else {
            // 判断各种浏览器，找到正确的方法
            var exitMethod = document.exitFullscreen || // W3C
                document.mozCancelFullScreen ||    // Chrome等
                document.webkitExitFullscreen || // FireFox
                document.webkitExitFullscreen; // IE11
            if (exitMethod) {
                exitMethod.call(document);
            }
            else if (typeof window.ActiveXObject !== "undefined") {// for Internet Explorer
                var wscript = new ActiveXObject("WScript.Shell");
                if (wscript !== null) {
                    wscript.SendKeys("{F11}");
                }
            }
            $('#fullScreen span').html('全屏');
            $('#fullScreen i').removeClass('tool-small');
            this.fullScreenFlag = true;
        }

        function requestFullScreen(element) {
            // 判断各种浏览器，找到正确的方法
            var requestMethod = element.requestFullScreen || // W3C
                element.webkitRequestFullScreen ||    // Chrome等
                element.mozRequestFullScreen || element.webkitallowfullscreen || element.mozallowfullscreen// FireFox
            element.msRequestFullScreen; // IE11
            if (requestMethod) {
                requestMethod.call(element);
            }
            else if (typeof window.ActiveXObject !== "undefined") {// for Internet Explorer
                var wscript = new ActiveXObject("WScript.Shell");
                if (wscript !== null) {
                    wscript.SendKeys("{F11}");
                }
            }
        }
    },
    //添加公共机构标注
    addOverlayLabelpublicPlace: function (name, coordinate, overlayId) {

        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('map'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker-publicPlace";
        elementDiv.title = name;

        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);

        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = "";
        elementSpan.appendChild(elementImg);

        var elementTd2 = document.createElement('td');
        elementTr.appendChild(elementTd2);
        // 新增a元素
        var elementA = document.createElement("a");
        elementA.className = "address";
        elementA.href = "#";
        setInnerText(elementA, elementDiv.title); // 设置文本
        elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
        // 新建div元素
        var elementDiv1 = document.createElement('div');
        elementDiv1.className = "marker-publicPlace-line";
        elementDiv.appendChild(elementDiv1);
        // 新建div元素
        var elementDiv2 = document.createElement('div');
        elementDiv2.className = "marker-publicPlace-circle";
        elementDiv.appendChild(elementDiv2);
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-15, -30],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //添加公共机构标注图标
    addOverlayLabelpublicPlaceIcon: function (name, coordinate, overlayId) {

        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('map'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker-publicPlace";
        elementDiv.title = name;

        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);

        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = "";
        elementSpan.appendChild(elementImg);

        //	var elementTd2 = document.createElement('td');
        //	elementTr.appendChild(elementTd2);
        // 新增a元素
        //var elementA = document.createElement("a");
        //elementA.className = "address";
        //	elementA.href = "#";
        //	setInnerText(elementA, elementDiv.title); // 设置文本
        //	elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
        // 新建div元素
        var elementDiv1 = document.createElement('div');
        elementDiv1.className = "marker-publicPlace-line";
        elementDiv.appendChild(elementDiv1);
        // 新建div元素
        var elementDiv2 = document.createElement('div');
        elementDiv2.className = "marker-publicPlace-circle";
        elementDiv.appendChild(elementDiv2);
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-15, -30],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //添加图文标注
    addOverlayLabel: function (name, coordinate, overlayId) {

        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('map'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker";
        elementDiv.title = name;

        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);

        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = "";
        elementSpan.appendChild(elementImg);

        var elementTd2 = document.createElement('td');
        elementTr.appendChild(elementTd2);
        // 新增a元素
        var elementA = document.createElement("a");
        elementA.className = "address";
        elementA.href = "#";
        setInnerText(elementA, elementDiv.title); // 设置文本
        elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
        // 新建div元素
        var elementDiv1 = document.createElement('div');
        elementDiv1.className = "marker-line";
        elementDiv.appendChild(elementDiv1);
        // 新建div元素
        var elementDiv2 = document.createElement('div');
        elementDiv2.className = "marker-circle";
        elementDiv.appendChild(elementDiv2);
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-offset, -105],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //添加楼栋标注
    addOverlayLabelBulid: function (name, coordinate) {
        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        elementDiv.className = "bulid-marker";
        elementDiv.title = name;
        var overlay = document.getElementById('map'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        // 新增a元素
        var elementA = document.createElement("a");
        elementA.className = "address";
        elementA.style.color = "#fff";
        elementA.style.textDecoration = 'none';
        elementA.href = "#";
        setInnerText(elementA, elementDiv.title); // 设置文本
        elementDiv.appendChild(elementA); // 新建的div元素添加a子节点
        var offset = 0;
        if (elementDiv) {
            offset = elementA.offsetWidth
        }
        this[name + 'Overlay'] = new ol.Overlay({
            position: coordinate,
            element: elementDiv,
            offset: [-((offset) / 2), -10],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[name + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //添加事件图文标注-地图事件
    addEventOverlayLabel: function (name, coordinate, overlayId) {
        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('map'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker-event";
        elementDiv.title = name;
        elementDiv.onclick = function () {
            //parent.LayerDialog('event/ccmEventIncident/detail?id='+overlayId, '案事件详情', '1000px', '700px')
            TodyAlarm()
        }
        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);

        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = ctxStatic + "/modules/map/images/alarm.png";
        elementSpan.appendChild(elementImg);

        var elementTd2 = document.createElement('td');
        elementTr.appendChild(elementTd2);
        // 新增a元素
        var elementA = document.createElement("a");
        elementA.className = "address-event";
        elementA.href = "#";
        setInnerText(elementA, elementDiv.title); // 设置文本
        elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
        // 新建div元素
        var elementDiv1 = document.createElement('div');
        elementDiv1.className = "marker-event-line";
        elementDiv.appendChild(elementDiv1);
        // 新建div元素
        var elementDiv2 = document.createElement('div');
        elementDiv2.className = "marker-event-circle";
        elementDiv.appendChild(elementDiv2);
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var leftVal = offset / 2
        elementDiv1.style.left = (leftVal - 1) + 'px';
        elementDiv2.style.left = (leftVal - 4) + 'px';
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-leftVal, -105],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    }, //添加图文标注
    addOverlayLabel3: function (name, coordinate, overlayId, icon) {

        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('pubMap'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker";
        elementDiv.title = name;

        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);
        elementDiv.onclick = function () {
            XiangQingFun(name)
        }
        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = ctxStatic + "/modules/map/images/pub/" + icon;
        elementSpan.appendChild(elementImg);

        var elementTd2 = document.createElement('td');
        elementTr.appendChild(elementTd2);
        // 新增a元素
        var elementA = document.createElement("a");
        elementA.className = "address";
        elementA.href = "#";
        setInnerText(elementA, elementDiv.title); // 设置文本
        elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
        // 新建div元素
        var elementDiv1 = document.createElement('div');
        elementDiv1.className = "marker-line";
        elementDiv.appendChild(elementDiv1);
        // 新建div元素
        var elementDiv2 = document.createElement('div');
        elementDiv2.className = "marker-circle";
        elementDiv.appendChild(elementDiv2);
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-offset, -105],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //添加图文标注
    addOverlayLabel4: function (name, coordinate, overlayId, icon, src) {

        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('pubMap'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker-icon";
        elementDiv.title = name;

        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);
        elementDiv.onclick = function () {
            shipinjiankongXiangqingFun(src, name)
        }
        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = ctxStatic + "/modules/map/images/pub/" + icon;
        elementSpan.appendChild(elementImg);

        /*	var elementTd2 = document.createElement('td');
			elementTr.appendChild(elementTd2);
			// 新增a元素
			var elementA = document.createElement("a");
			elementA.className = "address";
			elementA.href = "#";
			setInnerText(elementA, elementDiv.title); // 设置文本
			elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
*/			// 新建div元素
        /*		var elementDiv1 = document.createElement('div');
			elementDiv1.className = "marker-icon-line";
			elementDiv.appendChild(elementDiv1);*/
        // 新建div元素
        /*	var elementDiv2 = document.createElement('div');
			elementDiv2.className = "marker-icon-circle";
			elementDiv.appendChild(elementDiv2);*/
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-16.5, -33],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    // 热力图--人口密度
    heatMap1: function (heatData) {
        var _this = this;
        var radius = 15;
        var blur = 30;
        var map = this.map;
        var name = 'heatMap';
        var len = heatData.features.length;
        for (var j = 0; j < len; j++) {
            if (heatData.features[j].geometry.coordinates) {
                heatData.features[j].geometry.coordinates[0] = Number(heatData.features[j].geometry.coordinates[0]);
                heatData.features[j].geometry.coordinates[1] = Number(heatData.features[j].geometry.coordinates[1]);
            } else {
                heatData.features[j].geometry['coordinates'] = [];
                heatData.features[j].geometry['coordinates'][0] = '';
                heatData.features[j].geometry['coordinates'][1] = '';
            }

        }
        _this[name] = new ol.layer.Heatmap({
            source: new ol.source.Vector({
                format: new ol.format.GeoJSON(),
                features: (new ol.format.GeoJSON()).readFeatures(heatData),
                zIndex: 9,
            }),
            blur: blur,
            radius: radius,
            /*weight:function(feature){
					var value=parseInt(feature.get('info').count);
					if(value>0&&value<=100){
						return 0.5;
					}else if(value>101&&value<=200){
						return 0.7;
					}else if(value>201&&value<=300){
						return 0.8;
					}else if(value>301){
						return 1;
					}else{
						return 0.6
					}
				}*/
        });
        map.addLayer(_this[name]);
        map.getView().animate({
            zoom: 15,
            duration: 250
        })

    },
    // 热力图--扁平化门户
    heatMap2: function (heatData, heatName) {
        var _this = this;
        var radius = 15;
        var blur = 30;
        var map = this.map;
        var name = 'heatMap';
        if (heatName) {
            name = heatName
        }
        _this[name] = new ol.layer.Heatmap({
            source: new ol.source.Vector({
                format: new ol.format.GeoJSON(),
                features: (new ol.format.GeoJSON()).readFeatures(heatData),
                zIndex: 9,
            }),
            blur: blur,
            radius: radius,
        });
        map.addLayer(_this[name]);
    },
    //聚合图
    ClusterMap: function (data) {
        var _this = this;
        var map = this.map;
        var len = data.features.length;
        for (var j = 0; j < len; j++) {
            if (data.features[j].geometry.coordinates) {
                data.features[j].geometry.coordinates[0] = Number(data.features[j].geometry.coordinates[0]);
                data.features[j].geometry.coordinates[1] = Number(data.features[j].geometry.coordinates[1]);
            } else {
                data.features[j].geometry['coordinates'] = [];
                data.features[j].geometry['coordinates'][0] = '';
                data.features[j].geometry['coordinates'][1] = '';
            }

        }
        var clusterSource = new ol.source.Cluster({
            distance: 40,
            source: _this.vectorGeoJSON(data),
            geometryFunction: function (feature) {
                if (feature.getGeometry().getType() == "Point") {
                    return feature.getGeometry();
                } else {
                    return null;
                }
            },
            wrapX: false
        });
        _this['clusterLayer'] = new ol.layer.Vector({
            visible: true,
            source: clusterSource,
            zIndex: 99,
            style: function (feature) {
                var styleCache = {};
                var size = feature.get('features').length;
                var style = styleCache[size];
                if (!style) {
                    var color = size > 25 ? "192,0,0" : size > 8 ? "255,128,0" : "0,128,0";
                    var radius = Math.max(8, Math.min(size * 0.75, 20));
                    var dash = 2 * Math.PI * radius / 6;
                    dash = [0, dash, dash, dash, dash, dash, dash];
                    style = styleCache[size] = [new ol.style.Style({
                        image: new ol.style.Circle({
                            radius: radius,
                            stroke: new ol.style.Stroke({
                                color: "rgba(" + color + ",0.5)",
                                width: 15,
                                lineDash: dash,
                                lineCap: "butt"
                            }),
                            fill: new ol.style.Fill({
                                color: "rgba(" + color + ",1)"
                            })
                        }),
                        text: new ol.style.Text({
                            text: size.toString(),
                            fill: new ol.style.Fill({
                                color: '#fff'
                            })
                        })
                    })
                    ];
                }
                return style;
            }
        });
        map.addLayer(_this['clusterLayer']);
        map.getView().animate({
            zoom: 15,
            duration: 250
        })
    },
    //四色图
    FourColorMap: function (data, colorLevel) {
        var map = this.map;
        var _this = this;
        var greenMax = colorLevel.greenMax;
        var yellowMin = colorLevel.yellowMin;
        var yellowMax = colorLevel.yellowMax;
        var orangeMin = colorLevel.orangeMin;
        var orangeMax = colorLevel.orangeMax;
        var redMin = colorLevel.redMin;
        // 加载数据
        this.center = data.centpoint;
        var Data = data.features;
        var DataLen = Data.length;
        var vectorSource = null;
        if (DataLen > 0) {
            //字符串转化为number数据  ['137','47']=>[137,47]
            for (var j = 0; j < DataLen; j++) {
                if (Data[j].geometry.type == "Polygon") {
                    var PolygonLen = Data[j].geometry.coordinates.length;
                    if (PolygonLen > 0) {
                        for (var k = 0; k < PolygonLen; k++) {
                            var coordinates = Data[j].geometry.coordinates[k];
                            var coordinatesLen = coordinates.length;
                            if (coordinatesLen > 0) {
                                for (var m = 0; m < coordinatesLen; m++) {
                                    data.features[j].geometry.coordinates[k][m][0] = Number(Data[j].geometry.coordinates[k][m][0])
                                    data.features[j].geometry.coordinates[k][m][1] = Number(Data[j].geometry.coordinates[k][m][1])
                                }
                            }
                        }
                    }
                }
            }
        }

        //数据
        var styleCache = {};
        _this['fourColorLayer'] = new ol.layer.Vector({
            source: _this.vectorGeoJSON(data),
            zIndex: 1,
            // 设置样式
            style: function (feature) {
                var fillColor = '';
                var getZoom = map.getView().getZoom();
                var colorNum = feature.get('info')["事件数量"];
                if (colorNum <= greenMax) {
                    fillColor = 'rgba(33, 201, 9,0.8)';
                } else if (colorNum >= yellowMin && colorNum <= yellowMax) {
                    fillColor = 'rgba(255, 236, 5,0.8)';
                } else if (colorNum >= orangeMin && colorNum <= orangeMax) {
                    fillColor = 'rgba(255, 164, 8,0.8)';
                } else if (colorNum >= redMin) {
                    fillColor = 'rgba(255, 57, 13,0.8)';
                }
                //返回样式
                var size = feature.getId();
                var style = styleCache[size];
                if (!style) {
                    style = styleCache[size] = [
                        new ol.style.Style({
                            fill: new ol.style.Fill({
                                color: fillColor ? fillColor : 'rgba(33, 201, 9,0.8)'
                            }),
                            stroke: new ol.style.Stroke({
                                color: 'rgba(1, 2, 247,1)',
                                width: 1
                            }),
                            text: new ol.style.Text({
                                textAlign: 'center', // 位置
                                textBaseline: 'middle', // 基准线
                                exceedLength: 'true',
                                font: 'normal 14px 微软雅黑',  // 文字样式
                                text: feature.get('name'),  // 文本内容
                                fill: new ol.style.Fill({color: '#000'}), // 文本填充样式（即文字颜色）
                            })
                        }),
                    ]
                }
                return style
            }
        });
        map.addLayer(_this['fourColorLayer']);
        map.getView().animate({
            zoom: 15,
            duration: 250
        })
    },
    //数据采集分析对比
    DataAnalysisMap: function (data, colorLevel) {
        var map = this.map;
        var _this = this;
        var greenMax = colorLevel.greenMax;
        var yellowMin = colorLevel.yellowMin;
        var yellowMax = colorLevel.yellowMax;
        var orangeMin = colorLevel.orangeMin;
        var orangeMax = colorLevel.orangeMax;
        var redMin = colorLevel.redMin;
        // 加载数据
        this.center = data.centpoint;
        var Data = data.features;
        var DataLen = Data.length;
        var vectorSource = null;
        if (DataLen > 0) {
            //字符串转化为number数据  ['137','47']=>[137,47]
            for (var j = 0; j < DataLen; j++) {
                if (Data[j].geometry.type == "Polygon") {
                    var PolygonLen = Data[j].geometry.coordinates.length;
                    if (PolygonLen > 0) {
                        for (var k = 0; k < PolygonLen; k++) {
                            var coordinates = Data[j].geometry.coordinates[k];
                            var coordinatesLen = coordinates.length;
                            if (coordinatesLen > 0) {
                                for (var m = 0; m < coordinatesLen; m++) {
                                    data.features[j].geometry.coordinates[k][m][0] = Number(Data[j].geometry.coordinates[k][m][0])
                                    data.features[j].geometry.coordinates[k][m][1] = Number(Data[j].geometry.coordinates[k][m][1])
                                }
                            }
                        }
                    }
                }
            }
        }

        //数据
        var styleCache = {};
        _this['DataAnalysisLayer'] = new ol.layer.Vector({
            source: _this.vectorGeoJSON(data),
            zIndex: 1,
            // 设置样式
            style: function (feature) {
                var fillColor = '';
                var getZoom = map.getView().getZoom();
                var colorNum = feature.get('info')["数量"];
                if (colorNum <= greenMax) {
                    fillColor = 'rgba(255, 227, 170,0.8)';
                } else if (colorNum >= yellowMin && colorNum <= yellowMax) {
                    fillColor = 'rgba(255, 221, 149,0.8)';
                } else if (colorNum >= orangeMin && colorNum <= orangeMax) {
                    fillColor = 'rgba(255, 200, 88,0.8)';
                } else if (colorNum >= redMin) {
                    fillColor = 'rgba(255, 193, 67,0.8)';
                }
                //返回样式
                var size = feature.getId();
                var style = styleCache[size];
                if (!style) {
                    style = styleCache[size] = [
                        new ol.style.Style({
                            fill: new ol.style.Fill({
                                color: fillColor ? fillColor : 'rgba(33, 201, 9,0.8)'
                            }),
                            stroke: new ol.style.Stroke({
                                color: 'rgba(1, 2, 247,1)',
                                width: 1
                            }),
                            text: new ol.style.Text({
                                textAlign: 'center', // 位置
                                textBaseline: 'middle', // 基准线
                                exceedLength: 'true',
                                font: 'normal 14px 微软雅黑',  // 文字样式
                                text: feature.get('name'),  // 文本内容
                                fill: new ol.style.Fill({color: '#000'}), // 文本填充样式（即文字颜色）
                            })
                        }),
                    ]
                }
                return style
            }
        });
        map.addLayer(_this['DataAnalysisLayer']);
        map.getView().animate({
            zoom: 10,
            duration: 250
        })
        _this.DataAnalysisClick = new ol.interaction.Select({
            condition: ol.events.condition.click,
            filter: function (feature, layer) {
                return layer == _this['DataAnalysisLayer']
            }

        });


        _this.DataAnalysisClick.on('select', function (e) {
            if (e.selected.length > 0) {
                selectedDataAnalysisFeatures(_this.DataAnalysisClick)
            }
        });
        map.addInteraction(_this.DataAnalysisClick);
        selectedDataAnalysisFeatures(_this.DataAnalysisClick)

        function selectedDataAnalysisFeatures(selectType) {
            var selectedFeatures = selectType.getFeatures();
            selectedFeatures.on(['add', 'remove'], function (event) {
                var names = selectedFeatures.getArray().map(function (feature) {
                    var id = feature.getId();
                    parentId = id;
                    if (typeof QueryCollect === "function") {
                        QueryCollect();
                        _this.DataAnalysisClick.getFeatures().clear()
                    }
                });
            });
        }
    },
    //添加电子围栏
    addElectronicFence: function (id, type, ElectronicFenceData) {
        var map = this.map;
        var _this = this;
        // 绘制的几何图形要素
        var Feature;
        var coordinate = [];
        // 点
        if (type == "Point") {
            coordinate = ElectronicFenceData.split(';')[0].split(',');
            Feature = new ol.Feature(new ol.geom.Point(coordinate));
        } else if (type == "LineString") {
            var coordinateArr = ElectronicFenceData.split(';');
            if (coordinateArr.length != 0) {
                for (var i = 0; i < coordinateArr.length; i++) {
                    coordinate.push(coordinateArr[i].split(','))
                }
            }
            Feature = new ol.Feature(new ol.geom.LineString(coordinate));
        } else if (type == "Polygon") {
            var coordinateArr = ElectronicFenceData.split(';');
            if (coordinateArr.length != 0) {
                for (var i = 0; i < coordinateArr.length; i++) {
                    coordinate.push(coordinateArr[i].split(','))
                }
            }
            Feature = new ol.Feature(new ol.geom.Polygon([coordinate]));
        }

        // 实例化一个矢量图层Vector作为绘制层
        var source = new ol.source.Vector({
            features: [Feature]
        });

        this['ElectronicFence'] = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'red',
                    width: 2
                }),
                text: new ol.style.Text({
                    text: '电子围栏区域',
                    font: 'normal 15px 微软雅黑',
                    exceedLength: 'true',
                    fill: new ol.style.Fill({
                        color: 'red'
                    }),
                    stroke: new ol.style.Stroke({
                        color: 'red',
                        width: 1
                    }),
                })
            })
        });
        // 将绘制层添加到地图容器中
        map.addLayer(this['ElectronicFence']);
//			var areaPointCenter=_this.selectedNodes[0].areaPoint.split(';')[0].split(',');
//			if(areaPointCenter.length==2){
//				this.goTo(areaPointCenter);
//			}
    },
    //轨迹回放
    trackReplayLu: function (startBtnId, speed, routeCoords, id, colorLine) {
        var map = this.map;
        var center = this.center;
        var route = new ol.geom.LineString(routeCoords);
        var routeLength = routeCoords.length;
        var routeFeature = new ol.Feature({
            type: 'route',
            geometry: route
        });
        var geoMarker = new ol.Feature({
            type: 'geoMarker',
            geometry: new ol.geom.Point(routeCoords[0])
        });
        var startMarker = new ol.Feature({
            type: 'startIcon',
            geometry: new ol.geom.Point(routeCoords[0])
        });
        var endMarker = new ol.Feature({
            type: 'endIcon',
            geometry: new ol.geom.Point(routeCoords[routeLength - 1])
        });

        var styles = {
            'route': new ol.style.Style({
                stroke: new ol.style.Stroke({
                    width: 4, color: colorLine
                })
            }),
            'startIcon': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: ctxStatic + '/modules/map/images/start.png'
                })
            }),
            'endIcon': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: ctxStatic + '/modules/map/images/end.png'
                })
            }),
            'geoMarker': new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 10,
                    snapToPixel: false,
                    fill: new ol.style.Fill({color: "rgba(0,0,0,0)"}),
                    stroke: new ol.style.Stroke({
                        color: 'rgba(0,0,0,0)', width: 2
                    })
                })
            })
        };

        var animating = false;
        var now;
        // var speedInput = document.getElementById('speed');
        var startButton = document.getElementById(startBtnId);
        this['' + id + ''] = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: [routeFeature, geoMarker, startMarker, endMarker]
            }),
            style: function (feature) {
                // hide geoMarker if animation is active
                if (animating && feature.get('type') === 'geoMarker') {
                    return null;
                }
                return styles[feature.get('type')];
            }
        });

        map.addLayer(this['' + id + '']);
        var moveFeature = function (event) {
            var vectorContext = event.vectorContext;
            var frameState = event.frameState;
            if (animating) {
                var elapsedTime = frameState.time - now;
                // here the trick to increase speed is to jump some indexes
                // on lineString coordinates
                var index = Math.round(speed * elapsedTime / 1000);
                if (index >= routeLength) {
                    // stopAnimation(true);
                    return;
                }
                var currentPoint = new ol.geom.Point(routeCoords[index]);
                var feature = new ol.Feature(currentPoint);
                vectorContext.drawFeature(feature, styles.geoMarker);
            }
            // tell OpenLayers to continue the postcompose animation
            map.render();
        };

        function startAnimation() {
            if (animating) {
                stopAnimation(false);
            } else {
                animating = true;
                now = new Date().getTime();
                // speed = speedInput.value;
                startButton.textContent = '取消';
                // hide geoMarker
                geoMarker.setStyle(null);
                // just in case you pan somewhere else
                map.on('postcompose', moveFeature);
                map.render();
            }
        }

        function stopAnimation(ended) {
            animating = false;
            startButton.textContent = '开始';
            // if animation cancelled set the marker at the beginning
            var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
            /** @type {ol.geom.Point} */ (geoMarker.getGeometry())
                .setCoordinates(coord);
            // remove listener
            map.un('postcompose', moveFeature);
        }

        //  startButton.addEventListener('click', startAnimation, false);
    },
    //轨迹回放
    trackReplay: function (startBtnId, speed, routeCoords) {
        var map = this.map;
        var center = this.center;
        var route = new ol.geom.LineString(routeCoords);
        var routeLength = routeCoords.length;
        var routeFeature = new ol.Feature({
            type: 'route',
            geometry: route
        });
        var geoMarker = new ol.Feature({
            type: 'geoMarker',
            geometry: new ol.geom.Point(routeCoords[0])
        });
        var startMarker = new ol.Feature({
            type: 'startIcon',
            geometry: new ol.geom.Point(routeCoords[0])
        });
        var endMarker = new ol.Feature({
            type: 'endIcon',
            geometry: new ol.geom.Point(routeCoords[routeLength - 1])
        });

        var styles = {
            'route': new ol.style.Style({
                stroke: new ol.style.Stroke({
                    width: 6, color: [237, 212, 0, 0.8]
                })
            }),
            'startIcon': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: ctxStatic + '/modules/map/images/start.png'
                })
            }),
            'endIcon': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: ctxStatic + '/modules/map/images/end.png'
                })
            }),
            'geoMarker': new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 10,
                    snapToPixel: false,
                    fill: new ol.style.Fill({color: 'black'}),
                    stroke: new ol.style.Stroke({
                        color: 'white', width: 2
                    })
                })
            })
        };

        var animating = false;
        var now;
        // var speedInput = document.getElementById('speed');
        var startButton = document.getElementById(startBtnId);
        this['trackReplay'] = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: [routeFeature, geoMarker, startMarker, endMarker]
            }),
            style: function (feature) {
                // hide geoMarker if animation is active
                if (animating && feature.get('type') === 'geoMarker') {
                    return null;
                }
                return styles[feature.get('type')];
            }
        });

        map.addLayer(this['trackReplay']);
        var moveFeature = function (event) {
            var vectorContext = event.vectorContext;
            var frameState = event.frameState;
            if (animating) {
                var elapsedTime = frameState.time - now;
                // here the trick to increase speed is to jump some indexes
                // on lineString coordinates
                var index = Math.round(speed * elapsedTime / 1000);
                if (index >= routeLength) {
                    stopAnimation(true);
                    return;
                }
                var currentPoint = new ol.geom.Point(routeCoords[index]);
                var feature = new ol.Feature(currentPoint);
                vectorContext.drawFeature(feature, styles.geoMarker);
            }
            // tell OpenLayers to continue the postcompose animation
            map.render();
        };

        function startAnimation() {
            if (animating) {
                stopAnimation(false);
            } else {
                animating = true;
                now = new Date().getTime();
                // speed = speedInput.value;
                startButton.textContent = '取消';
                // hide geoMarker
                geoMarker.setStyle(null);
                // just in case you pan somewhere else
                map.on('postcompose', moveFeature);
                map.render();
            }
        }

        function stopAnimation(ended) {
            animating = false;
            startButton.textContent = '开始';
            // if animation cancelled set the marker at the beginning
            var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
            /** @type {ol.geom.Point} */ (geoMarker.getGeometry())
                .setCoordinates(coord);
            // remove listener
            map.un('postcompose', moveFeature);
        }

        // startButton.addEventListener('click', startAnimation, false);
    },
    //轨迹回放
    trackReplaInit: function (startBtnId, speedId, routeCoords) {

        var map = this.map;
        var _this = this;
        var center = this.center;
        this.routeCoords = routeCoords
        var route = new ol.geom.LineString(this.routeCoords);
        this.routeLength = this.routeCoords.length;

        this.routeFeature = new ol.Feature({
            type: 'route',
            geometry: route
        });
        this.geoMarker = new ol.Feature({
            type: 'geoMarker',
            geometry: new ol.geom.Point(_this.routeCoords[0])
        });
        this.startMarker = new ol.Feature({
            type: 'startIcon',
            geometry: new ol.geom.Point(_this.routeCoords[0])
        });
        this.endMarker = new ol.Feature({
            type: 'endIcon',
            geometry: new ol.geom.Point(_this.routeCoords[_this.routeLength - 1])
        });

        var styles = {
            'route': new ol.style.Style({
                stroke: new ol.style.Stroke({
                    width: 3,
                    color: [237, 212, 0, 0.8]
                })
            }),
            'startIcon': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: ctxStatic + '/modules/map/images/start.png'
                })
            }),
            'endIcon': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: ctxStatic + '/modules/map/images/end.png'
                })
            }),
            'geoMarker': new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 6,
                    snapToPixel: false,
                    fill: new ol.style.Fill({color: 'black'}),
                    stroke: new ol.style.Stroke({
                        color: 'white', width: 2
                    })
                })
            })
        };

        this.animating = false;
        this.speedInput = document.getElementById(speedId);
        this.startButton = document.getElementById(startBtnId);
        this['trackReplay'] = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: [_this.routeFeature, _this.geoMarker, _this.startMarker, _this.endMarker]
            }),
            style: function (feature) {

                // hide geoMarker if animation is active
                if (_this.animating && feature.get('type') === 'geoMarker') {
                    return null;
                }
                return styles[feature.get('type')];
            }
        });

        map.addLayer(this['trackReplay']);
        map.getView().setZoom(17);
        _this.goTo(_this.routeCoords[0]);//定位到中心
        this.moveFeature = function (event) {

            var vectorContext = event.vectorContext;
            var frameState = event.frameState;
            if (_this.animating) {
                var elapsedTime = frameState.time - _this.now;
                _this.speed = _this.speedInput.value;
                // here the trick to increase speed is to jump some indexes
                // on lineString coordinates
                var index = Math.round(_this.speed * elapsedTime / 1000);
                if (index >= _this.routeLength) {
                    _this.stopAnimation(true);
                    return;
                }
                var currentPoint = new ol.geom.Point(_this.routeCoords[index]);
                var feature = new ol.Feature(currentPoint);
                vectorContext.drawFeature(feature, styles.geoMarker);
            }
            // tell OpenLayers to continue the postcompose animation
            map.render();

        };

        // this.startButton.addEventListener('click', _this.startAnimation1, false);
    },
    startAnimation: function () {
        var map = this.map;
        var _this = this;
        if (_this.animating) {
            _this.stopAnimation(false);
        } else {
            _this.animating = true;
            _this.now = new Date().getTime();
            _this.speed = _this.speedInput.value;
            //startButton.textContent = '取消';
            // hide geoMarker

            _this.geoMarker.setStyle(null);
            // just in case you pan somewhere else
            map.on('postcompose', _this.moveFeature);
            map.render();
        }
    },
    stopAnimation: function (ended) {
        var _this = this;
        this.animating = false;
        //startButton.textContent = '开始';
        // if animation cancelled set the marker at the beginning
        var coord = ended ? _this.routeCoords[_this.routeLength - 1] : _this.routeCoords[0];
        /** @type {ol.geom.Point} */ (_this.geoMarker.getGeometry())
            .setCoordinates(coord);
        // remove listener
        map.un('postcompose', this.moveFeature);
    },
    // 轨迹回放--补充点位一条直线
    trackReplayLine: function (points) {
        var map = this.map;
        var center = this.center;
        var _this = this;
        _this.oldPoints = points;
        _this.newPoints = [];
        _this.toLonLatPoints = [];
        _this.index = 0;
        _this.date;
        _this.tempDate = null;
        _this.calcIndex = 0;
        _this.inter = null;
        _this.ispuse = 0;
        _this.startFlag = false;
        _this.calcIspuse = 1;
        _this.calcPoint = [];
        //动画
        _this.speed = null;
        //点标记
        _this.startMarker = null;
        _this.geoMarker = null;
        _this.endMarker = null;

        $.each(points, function (i, item) {

            var currLng = item[0];
            var currLat = item[1];
            _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));

            if ((i + 1) == points.length) {
                return false;
            }

            var nextLng = parseFloat(points[i + 1][0]);
            var nextLat = parseFloat(points[i + 1][1]);

            var diffLng = nextLng - currLng;
            var diffLat = nextLat - currLat;

            var currParamsLng = 0
            var currParamsLat = 0
            //控制速度  标准 0.0001
            // var base = 0.000005;
            var base = 0.0001;
            currParamsLng = Math.sqrt(base * base / (1 + (diffLat / diffLng) * (diffLat / diffLng)));
            currParamsLat = (diffLat / diffLng) * currParamsLng

            currParamsLng = Math.abs(currParamsLng);
            currParamsLat = Math.abs(currParamsLat);

            if (diffLng == 0) {
                currParamsLng = 0;
                currParamsLat = base;
            }

            if (diffLat == 0) {
                currParamsLng = base;
                currParamsLat = 0;
            }

            //当经度差维度差都等于0 ：则视为同一个点 不处理
            if (diffLng == 0 && diffLat == 0) {

            }
            //当精度等于0维度不等于0：判断
            else if (diffLng == 0) {
                if (diffLat < 0) {
                    currLat = currLat - currParamsLat;
                    while (currLat > nextLat) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLat = currLat - currParamsLat;
                    }
                }
                else {
                    currLat = currLat + currParamsLat;
                    while (currLat < nextLat) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLat = currLat + currParamsLat;
                    }
                }
            }
            else if (diffLat == 0) {
                if (diffLng < 0) {
                    currLng = currLng - currParamsLng;
                    while (currLng > nextLng) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLng = currLng - currParamsLng;
                    }
                }
                else {
                    currLng = currLng + currParamsLng;
                    while (currLng < nextLng) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLng = currLng + currParamsLng;
                    }
                }
            }
            else {
                if (diffLng > 0 && diffLat > 0) {
                    currLng = currLng + currParamsLng;
                    currLat = currLat + currParamsLat;
                    while (currLng < nextLng) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLng = currLng + currParamsLng;
                        currLat = currLat + currParamsLat;
                    }
                }
                else if (diffLng > 0 && diffLat < 0) {
                    currLng = currLng + currParamsLng;
                    currLat = currLat - currParamsLat;
                    while (currLng < nextLng) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLng = currLng + currParamsLng;
                        currLat = currLat - currParamsLat;
                    }
                }
                else if (diffLng < 0 && diffLat < 0) {
                    currLng = currLng - currParamsLng;
                    currLat = currLat - currParamsLat;
                    while (currLng > nextLng) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLng = currLng - currParamsLng;
                        currLat = currLat - currParamsLat;
                    }
                }
                else if (diffLng < 0 && diffLat > 0) {
                    currLng = currLng - currParamsLng;
                    currLat = currLat + currParamsLat;
                    while (currLng > nextLng) {

                        _this.newPoints.push(ol.proj.fromLonLat([currLng, currLat]));
                        currLng = currLng - currParamsLng;
                        currLat = currLat + currParamsLat;
                    }
                }
            }
        })
        $.each(_this.newPoints, function (i, item) {
            var currLng = item[0];
            var currLat = item[1];
            _this.toLonLatPoints.push(ol.proj.toLonLat([currLng, currLat]));
        });
        _this.AddPolygon();
        _this.moveFeature = function (event) {
            _this['vectorContext'] = event.vectorContext;
            var frameState = event.frameState;
            var elapsedTime = frameState.time - _this.date;

            if (_this.ispuse == 0) {
                _this.index = Math.round(_this.speed * elapsedTime / 1000) + _this.calcIndex;
            }

            if (_this.index >= _this.newPoints.length) {
                _this.stopAnimationLine();
                clearInterval(_this.inter);
                return;
            }
            /*   var currentPoint = new ol.geom.Point( _this.toLonLatPoints[_this.index]);
    	        var feature = new ol.Feature(currentPoint);
    	        _this['vectorContext'].drawFeature(feature,  _this.style);*/
            (_this.geoMarker.getGeometry()).setCoordinates(_this.toLonLatPoints[_this.index]);
            if (_this.ispuse == 0) {
                map.render();
            }

        },
            $("#tempstop").click(function () {
                if (!_this.startFlag) {
                    return;
                }
                map.render();
                clearInterval(_this.inter);
                _this.tempDate = (new Date()).getTime();
                _this.ispuse = 1;
                _this.calcIspuse = _this.ispuse;
            })
        $("#start").click(function () {
            if (_this.date == undefined || _this.date == 0) {
                _this.date = (new Date()).getTime();
                _this.calcIndex = 0;
            }
            if (_this.tempDate != undefined && _this.tempDate != 0) {
                _this.date = _this.date + ((new Date()).getTime() - _this.tempDate);
            }
            _this.ispuse = 0;
            _this.calcIspuse = _this.ispuse;
            _this.startAnimationLine();
            _this.startMove();
            _this.startFlag = true;//开启暂停功能
        })


        $("#stop").click(function () {
            _this.stopAnimationLine();
            clearInterval(_this.inter);
            _this.calcIndex = 0;
            _this.startFlag = false;//关闭暂停功能
        })

        $("#next").click(function () {
            var tempIndex = _this.index;
            var ispoint = true;
            while (ispoint) {
                var p = _this.newPoints[tempIndex];
                for (i = 0; i < points.length; i++) {
                    var p1 = ol.proj.fromLonLat(points[i]);
                    if (p[0] == p1[0] && p[1] == p1[1]) {
                        ispoint = false;
                        break;
                    }
                }
                tempIndex = tempIndex + 1;
                if (tempIndex >= _this.newPoints.length) {
                    ispoint = false;
                }
            }
            _this.calcIndex = tempIndex - _this.index + _this.calcIndex;
            _this.index = tempIndex;
            map.render();
        })

        $("#prev").click(function () {
            var tempIndex = _this.index;
            var ispoint = true;
            while (ispoint) {
                var p = _this.newPoints[tempIndex];
                for (i = 0; i < points.length; i++) {
                    var p1 = ol.proj.fromLonLat(points[i]);

                    if (p[0] == p1[0] && p[1] == p1[1]) {
                        ispoint = false;
                        break;
                    }
                }
                tempIndex = tempIndex - 1;
                if (tempIndex == 0) {
                    ispoint = false;
                }
            }
            _this.calcIndex = tempIndex - _this.index + _this.calcIndex;
            _this.index = tempIndex;
            map.render();
        })
    },
    AddPolygon: function () {
        var _this = this;
        var map = this.map;
        _this.removeLayer(_this['tralayer1']);
        //轨迹
        _this['tralayer1'] = new ol.layer.Vector({
            source: new ol.source.Vector(),
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(128, 142, 162, 0.5)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(82, 152, 254, 1)',
                    width: 3
                })
            })
        });
        map.addLayer(_this['tralayer1'])
        _this['tralayer1'].getSource().clear();
        //var line = new ol.geom.LineString(points);
        var line = new ol.geom.LineString(_this.toLonLatPoints);
        var feature = new ol.Feature({
            geometry: line
        });
        _this['tralayer1'].getSource().addFeature(feature);
        _this.SetMoveMark();
    },
    SetMoveMark: function () {
        var _this = this;
        var map = this.map;
        //样式
        _this.style = new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [0.5, 0.75],
                src: ctxStatic + '/modules/map/images/location48.png',
                scale: 1.2
            })
        });
        _this.geoMarkerIcon = new ol.style.Style({
            image: new ol.style.Circle({
                radius: 0,
                snapToPixel: false,
                fill: new ol.style.Fill({color: "rgba(0,0,0,0)"}),
                stroke: new ol.style.Stroke({
                    color: 'rgba(0,0,0,0)', width: 0
                })
            })
        });
        _this.startIcon = new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [0.5, 1],
                src: ctxStatic + '/modules/map/images/start2.png',
                scale: 1.2
            })
        });
        _this.endIcon = new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [0.4, 1],
                src: ctxStatic + '/modules/map/images/end1.png',
                scale: 1.2
            })
        });
        _this['tralayer2'] = new ol.layer.Vector({
            source: new ol.source.Vector()
        });
        map.addLayer(_this['tralayer2']);
        _this.startMarker = new ol.Feature({
            geometry: new ol.geom.Point(_this.toLonLatPoints[0])
        });
        _this.startMarker.setStyle(_this.startIcon)

        _this.endMarker = new ol.Feature({
            geometry: new ol.geom.Point(_this.toLonLatPoints[_this.toLonLatPoints.length - 1])
        });
        _this.endMarker.setStyle(_this.endIcon);
        _this.geoMarker = new ol.Feature({
            geometry: new ol.geom.Point(_this.toLonLatPoints[0]),
        });
        _this.geoMarker.setStyle(_this.style);
        _this['tralayer2'].getSource().addFeatures([_this.startMarker, _this.endMarker, _this.geoMarker]);
        this.goTo(_this.toLonLatPoints[0])
    },

    startAnimationLine: function () {
        var _this = this;
        var map = this.map;
        _this.speed = $("#speed").val();
        //_this.geoMarker.setStyle(_this.style);
        map.on('postcompose', _this.moveFeature);
        map.render();
    },
    stopAnimationLine: function () {
        var _this = this;
        var map = _this.map;
        _this.index = 0;
        _this.date = 0;
        _this.tempDate = 0;
        _this.ispuse = 1;
        _this.calcIspuse = _this.ispuse;
        (_this.geoMarker.getGeometry()).setCoordinates(_this.toLonLatPoints[0]);
        map.un('postcompose', _this.moveFeature);
    },
    startMove: function () {
        var _this = this;
        var map = _this.map;
        _this.inter = setInterval(function () {
            map.render();
        }, _this.speed);
    },
    startLocation: function (point) {
        var _this = this;
        var map = _this.map;
        var tempIndex = _this.index;
        var count = 0;
        var currentI = 0;
        var locationJ = 0;
        var ispoint = true;

        //获取定位位置
        var m = ol.proj.fromLonLat(point);
        for (j = 0; j < _this.newPoints.length; j++) {
            var m1 = _this.newPoints[j];
            if (m[0] == m1[0] && m[1] == m1[1]) {
                ispoint = false;
                _this.index = j;
                break;
            }
        }

        _this.calcIndex = _this.index;
        _this.date = (new Date()).getTime();
        _this.tempDate = (new Date()).getTime();
        _this.ispuse = _this.calcIspuse;
        _this.startAnimationLine();
    },
    //首页事件动画
    postcomposeOlIndex: function (name, coordinate, id, obj, happenDate) {

        this.postcomposeOlName = name;
        this.postcomposeOlCoordinate = coordinate;
        this.postcomposeOlId = id;
        this.happenDate = (happenDate == null ? '' : happenDate)
        var map = this.map;
        this.addOverlayLabelIndex(name, coordinate, obj)

        if (obj != '1') {
            var layer = new ol.layer.Vector({
                source: new ol.source.Vector(),
                zIndex: 9
            })
            map.addLayer(layer);
            var circle = new ol.Feature({
                geometry: new ol.geom.Point(coordinate),
                name: 'EventFeature',
                id: id,
            });
            circle.setStyle(new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 0,
                    stroke: new ol.style.Stroke({
                        color: 'red',
                        size: 1
                    }),

                })

            }));
            layer.getSource().addFeature(circle);
            // 关键的地方在此：监听postcompose事件，在里面重新设置circle的样式
            var radius = 0;
            map.on('postcompose', function () {
                //增大半径，最大30
                radius++;
                radius = radius % 40;
                // 设置样式
                circle.setStyle(new ol.style.Style({
                    image: new ol.style.Circle({
                        radius: radius / 2,
                        stroke: new ol.style.Stroke({
                            color: 'red',
                            size: 1
                        })
                    })
                }));
            })
        }


    },
    //添加首页图文标注
    addOverlayLabelIndex: function (name, coordinate, obj) {
        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        // 获取id为label的元素
        var overlay = document.getElementById('map');
        // 为ID为label的div层添加div子节点
        overlay.appendChild(elementDiv);
        elementDiv.className = "marker-index" + obj;
        elementDiv.title = name;
        this[name + 'Overlay'] = new ol.Overlay({
            id: name,
            position: coordinate,
            element: elementDiv,
            offset: [-8, -10],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[name + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //添加图文标注
    addOverlayLabel4: function (name, coordinate, overlayId, icon, src) {

        var map = this.map;
        // 新增div元素
        var elementDiv = document.createElement('div');
        var overlay = document.getElementById('pubMap'); // 获取id为label的元素
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "marker-icon";
        elementDiv.title = name;

        var elementTable = document.createElement('table');
        elementDiv.appendChild(elementTable);
        elementDiv.onclick = function () {
            shipinjiankongXiangqingFun(src, name)
        }
        var elementTr = document.createElement('tr');
        elementTable.appendChild(elementTr);

        var elementTd1 = document.createElement('td');
        elementTr.appendChild(elementTd1);

        // 新建span标签
        var elementSpan = document.createElement("span");
        elementTd1.appendChild(elementSpan);
        // 新建img图标标签
        var elementImg = document.createElement("img");
        elementImg.src = ctxStatic + "/modules/map/images/" + icon;
        elementSpan.appendChild(elementImg);

        /*	var elementTd2 = document.createElement('td');
			elementTr.appendChild(elementTd2);
			// 新增a元素
			var elementA = document.createElement("a");
			elementA.className = "address";
			elementA.href = "#";
			setInnerText(elementA, elementDiv.title); // 设置文本
			elementTd2.appendChild(elementA); // 新建的div元素添加a子节点
*/			// 新建div元素
        /*		var elementDiv1 = document.createElement('div');
			elementDiv1.className = "marker-icon-line";
			elementDiv.appendChild(elementDiv1);*/
        // 新建div元素
        /*	var elementDiv2 = document.createElement('div');
			elementDiv2.className = "marker-icon-circle";
			elementDiv.appendChild(elementDiv2);*/
        var offset = 0;
        if (elementDiv) {
            offset = elementDiv.offsetWidth
        }
        var OverlayName = name;
        if (overlayId) {
            OverlayName = overlayId;
        }
        this[OverlayName + 'Overlay'] = new ol.Overlay({
            id: OverlayName,
            position: coordinate,
            element: elementDiv,
            offset: [-16.5, -33],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[OverlayName + 'Overlay']);

        //动态设置元素文本内容（兼容）
        function setInnerText(element, text) {
            if (typeof element.textContent == "string") {
                element.textContent = text;
            } else {
                element.innerText = text;
            }
        }
    },
    //标注
    drawMark: function (drawType) {
        var map = this.map;
        var _this = this;
        // 绘制类型对象
        var typeSelect = drawType;
        if(_this.markInfoType == "elecFence"){//电子围栏
            $("#gravity").val(drawType);
        }
        // 删除图层
			this.removeLayer('vectorMark');
        // 删除图层
			this.removeLayer('drawMarkVector');
        map.removeInteraction(_this['drawDraw']);
        // 关闭编辑功能
        this.editGraphical(false);
        // 实例化一个矢量图层Vector作为绘制层
        _this['drawMarkVector'] = new ol.layer.Vector({
            source: new ol.source.Vector(),
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.7)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#0099ff',
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 10,
                    fill: new ol.style.Fill({
                        color: '#0099ff'
                    })
                })
            })
        });
        // 将绘制层添加到地图容器中
        map.addLayer(_this['drawMarkVector']);

        //根据绘制类型进行交互绘制图形处理
        function addInteraction() {
            //绘制时不能点击编辑
            $('#editTool').css('pointer-events', 'none');
            // 绘制类型
            var value = drawType;
            // 实例化交互绘制类对象并添加到地图容器中
            _this['drawDraw'] = new ol.interaction.Draw({
                source: _this['drawMarkVector'].getSource(), // 绘制层数据源
                type: value// 几何图形类型

            });
            map.addInteraction(_this['drawDraw']);
            // 添加绘制结束事件监听，在绘制结束后保存信息到数据库
            _this['drawDraw'].on('drawend', drawEndCallBack, this);
        }

        //用户更改绘制类型触发的事件
        // 添加交互绘制功能控件
        addInteraction();

        //绘制结束事件的回调函数，
        function drawEndCallBack(evt) {
            //绘制结束可以点击编辑
            $('#editTool').css("pointer-events","auto");
            // 绘制图形类型
            var geoType = drawType;
            // 当前绘制的要素
            _this.currentFeatureDraw = evt.feature;
            // 获取要素的几何信息
            var geo = _this.currentFeatureDraw.getGeometry();
            // 获取几何坐标
            var coordinates = geo.getCoordinates();
            map.removeInteraction(_this['drawDraw']);
            // 将几何坐标拼接为字符串
            if (geoType == "Polygon") {
                var len = coordinates[0].length;
                var xSum = null, ySum = null, x = null, y = null;
                for (var i = 0; i < len; i++) {
                    xSum += Number(coordinates[0][i][0]);
                    ySum += Number(coordinates[0][i][1]);
                }
                x = xSum / len;
                y = ySum / len;
                _this.areaPointDraw = x + ',' + y;
                _this.geoStrDraw = coordinates[0].join(";");
            } else if (geoType == "LineString") {
                var len = coordinates.length;
                var xSum = null, ySum = null, x = null, y = null;
                for (var i = 0; i < len; i++) {
                    xSum += Number(coordinates[i][0]);
                    ySum += Number(coordinates[i][1]);
                }
                x = xSum / len;
                y = ySum / len;
                _this.areaPointDraw = x + ',' + y;
                _this.geoStrDraw = coordinates.join(";");
            } else {
                _this.geoStrDraw = coordinates.join(",");
                _this.areaPointDraw = _this.geoStrDraw;
            }
            $('.tag-panl-span').removeClass('active');
            if(_this.markInfoType !== "elecFence"){//电子围栏例外
                if ($('#areaPoint').length > 0) {
                    $('#areaPoint').val(_this.geoStrDraw)
                }
            }
            console.log(_this.geoStrDraw)
        }
    },
    //地图标注信息
    markInfo: function (id, type, selectedNodes) {
        this.markInfoId = id;
        this.markInfoType = type;
        this.selectedNodes = selectedNodes;
    },
    //标注加载默认数据
    addGraphical: function (type) {
        var map = this.map;
        var _this = this;
        // 删除图层vectorMark
        //this.removeLayer('vectorMark');// 删除图层
        //this.removeLayer('drawMarkVector');// 删除图层
        // 关闭编辑功能
        this.editGraphical(false);
        // 绘制的几何图形要素
        var Feature;
        var coordinate = [];
        // 点
        // if (type == "Point") {
        if (_this.selectedNodes[0].areaMap==null||_this.selectedNodes[0].areaMap==""||(_this.selectedNodes[0].areaMap == _this.selectedNodes[0].areaPoint&&_this.selectedNodes[0].areaMap!=null)) {
            coordinate = _this.selectedNodes[0].areaPoint.split(';')[0].split(',');
            Feature = new ol.Feature(new ol.geom.Point(coordinate));
        } else if (type == "LineString") {
            var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
            if (coordinateArr.length != 0) {
                for (var i = 0; i < coordinateArr.length; i++) {
                    coordinate.push(coordinateArr[i].split(','))
                }
            }
            Feature = new ol.Feature(new ol.geom.LineString(coordinate));
        } else if (type == "Polygon") {
            var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
            if (coordinateArr.length != 0) {
                for (var i = 0; i < coordinateArr.length; i++) {
                    coordinate.push(coordinateArr[i].split(','))
                }
            }
            Feature = new ol.Feature(new ol.geom.Polygon([coordinate]));
        }

        // 实例化一个矢量图层Vector作为绘制层
        var source = new ol.source.Vector({
            features: [Feature]
        });

        // this['vectorMark'] = new ol.layer.Vector({
        //     source: source,
        //     style: new ol.style.Style({
        //         fill: new ol.style.Fill({
        //             color: 'rgba(255, 255, 255, 0.2)'
        //         }),
        //         stroke: new ol.style.Stroke({
        //             color: 'blue',
        //             width: 2
        //         }),
        //         image: new ol.style.Circle({
        //             radius: 10,
        //             fill: new ol.style.Fill({
        //                 color: 'blue'
        //             })
        //         })
        //     })
        // });
        this['vectorMark'] = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 225, 255, 0.6)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    offsetY:-15,//向上移（像素）
                    font: 'normal 14px 微软雅黑',  // 文字样式
                    text:_this.selectedNodes[0].name,  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })
            })
        });
        // 将绘制层添加到地图容器中
        map.addLayer(this['vectorMark']);
        var areaPointCenter = _this.selectedNodes[0].areaPoint.split(';')[0].split(',');
        if (areaPointCenter.length == 2) {
            this.goTo(areaPointCenter);
        }
    },
    
    

    //标注加载默认数据,生成的图层用id标识
    addGraphicalWithId: function (type) {
        var map = this.map;
        var _this = this;
        // 删除图层vectorMark
        //this.removeLayer('vectorMark');// 删除图层
        //this.removeLayer('drawMarkVector');// 删除图层
        // 关闭编辑功能
        this.editGraphical(false);
        // 绘制的几何图形要素
        var Feature;
        var coordinate = [];
        // 点
        // if (type == "Point") {
        if (_this.selectedNodes[0].areaMap==null||_this.selectedNodes[0].areaMap==""||(_this.selectedNodes[0].areaMap == _this.selectedNodes[0].areaPoint&&_this.selectedNodes[0].areaMap!=null)) {
            coordinate = _this.selectedNodes[0].areaPoint.split(';')[0].split(',');
            Feature = new ol.Feature(new ol.geom.Point(coordinate));
        } else if (type == "LineString") {
            var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
            if (coordinateArr.length != 0) {
                for (var i = 0; i < coordinateArr.length; i++) {
                    coordinate.push(coordinateArr[i].split(','))
                }
            }
            Feature = new ol.Feature(new ol.geom.LineString(coordinate));
        } else if (type == "Polygon") {
            var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
            if (coordinateArr.length != 0) {
                for (var i = 0; i < coordinateArr.length; i++) {
                    coordinate.push(coordinateArr[i].split(','))
                }
            }
            Feature = new ol.Feature(new ol.geom.Polygon([coordinate]));
        }

        // 实例化一个矢量图层Vector作为绘制层
        var source = new ol.source.Vector({
            features: [Feature]
        });
        
        var areaColor = _this.selectedNodes[0].color;
        
        this[_this.markInfoId] = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: areaColor ? areaColor : 'rgba(255, 225, 255, 0.3)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 1
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    offsetY:-15,//向上移（像素）
                    font: 'normal 14px 微软雅黑',  // 文字样式
                    text:_this.selectedNodes[0].name,  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })
            })
        });
        // 将绘制层添加到地图容器中
        map.addLayer(this[_this.markInfoId]);
        var areaPointCenter = _this.selectedNodes[0].areaPoint.split(';')[0].split(',');
        if (areaPointCenter.length == 2) {
            this.goTo(areaPointCenter);
        }
    },
    

    //工具栏--着色-预览
    previewArea: function (color, opacity) {
    	var map = this.map;
        var _this = this;
        // 删除原叠加的图层  TODO
        _this.removeLayer(_this.markInfoId);// 删除图层，当前不起作用，待处理 TODO
        // 关闭编辑功能
        this.editGraphical(false);
        // 绘制的几何图形要素
        var Feature;
        var coordinate = [];
        // 只支持面的着色
        var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
        if (coordinateArr.length != 0) {
            for (var i = 0; i < coordinateArr.length; i++) {
                coordinate.push(coordinateArr[i].split(','))
            }
        }
        Feature = new ol.Feature(new ol.geom.Polygon([coordinate]));

        // 实例化一个矢量图层Vector作为绘制层
        var source = new ol.source.Vector({
            features: [Feature]
        });
        
        var areaColor = this.color2Rgba(color, opacity);
        
        this[_this.markInfoId] = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: areaColor
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 1
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                }),
                text: new ol.style.Text({
                    textAlign: 'center', // 位置
                    textBaseline: 'middle', // 基准线
                    exceedLength: 'true',
                    offsetY:-15,//向上移（像素）
                    font: 'normal 14px 微软雅黑',  // 文字样式
                    text:_this.selectedNodes[0].name,  // 文本内容
                    fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                    stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                })
            })
        });
        // 将绘制层添加到地图容器中
        map.addLayer(this[_this.markInfoId]);
    },
    
    /*16进制颜色转为RGB格式，返回格式：rgba(30,144,255,0.5)*/
    color2Rgba: function(color, opacity){
	    var sColor = color.toLowerCase();
	    if(sColor){
		    //处理六位的颜色值
		    var sColorChange = [];
		    for(var i=0; i<6; i+=2){
		    	sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));
		    }
		    return "rgba(" + sColorChange.join(",") + "," + opacity + ")";
	    } else {
	    	return sColor;
	    }
    },

    //工具栏--着色-保存配置
    saveAreaColor: function (color, opacity) {
    	var map = this.map;
        var _this = this;
    	if (!_this.markInfoId) {
    		return;
    	}
    	// 关闭编辑功能
        this.editGraphical(false);
        var areaColor = this.color2Rgba(color, opacity);
        top.$.jBox.confirm("是否保存信息吗？", "系统提示", function (v, h, f) {
            if (v == "ok") {
                submitData(areaColor); // 提交几何与属性信息到后台处理
            } 
        }, {
            buttonsFocus: 1
        });
        //将绘制的几何数据与对话框设置的属性数据提交到后台处理
        function submitData(areaColor) {
            // 通过ajax请求将数据传到后台文件进行保存处理
            $.post('' + ctx + '/sys/map/updateMap', {
                type: _this.markInfoType,
                id: _this.markInfoId,
                areaPoint: _this.selectedNodes[0].areaPoint,
                areaMap: _this.selectedNodes[0].areaMap,
                color: areaColor
            }, function (data) {
                top.$.jBox.tip(data);
                _this.selectedNodes[0].color = areaColor;
            })
        }
    },
    
    //标注加载默认数据
    addGraphicalFirstPage: function (type) {
        var map = this.map;
        var _this = this;
        // 删除图层vectorMark
        //this.removeLayer('vectorMark');// 删除图层
        //this.removeLayer('drawMarkVector');// 删除图层
        // 关闭编辑功能
        this.editGraphical(false);
        // 绘制的几何图形要素
        // var Feature;
        // var coordinate = [];
        // 点
        // if (type == "Point") {
        // if (_this.selectedNodes[0].areaMap==null||_this.selectedNodes[0].areaMap==""||(_this.selectedNodes[0].areaMap == _this.selectedNodes[0].areaPoint&&_this.selectedNodes[0].areaMap!=null)) {
            // coordinate = _this.selectedNodes[0].areaPoint.split(';')[0].split(',');
            // Feature = new ol.Feature(new ol.geom.Point(coordinate));
            // Feature.setStyle(
            //     new ol.style.Style({
            //             fill: new ol.style.Fill({
            //                 color: 'rgba(255, 225, 255, 0.6)'
            //             }),
            //             stroke: new ol.style.Stroke({
            //                 color: 'blue',
            //                 width: 2
            //             }),
            //             image: new ol.style.Circle({
            //                 radius: 7,
            //                 fill: new ol.style.Fill({
            //                     color: 'blue'
            //                 })
            //             }),
            //             text: new ol.style.Text({
            //                 textAlign: 'center', // 位置
            //                 textBaseline: 'middle', // 基准线
            //                 exceedLength: 'true',
            //                 offsetY:-15,//向上移（像素）
            //                 font: 'normal 14px 微软雅黑',  // 文字样式
            //                 text:_this.selectedNodes[0].name,  // 文本内容
            //                 fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
            //                 stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
            //             })
            //
            //         })
            // )
            // // 实例化一个矢量图层Vector作为绘制层
            // var source = new ol.source.Vector({
            //     features: [Feature]
            // });
            // this['vectorMark'] = new ol.layer.Vector({
            //     source: source,
            //     style: new ol.style.Style({
            //         fill: new ol.style.Fill({
            //             color: 'rgba(255, 225, 255, 0.6)'
            //         }),
            //         stroke: new ol.style.Stroke({
            //             color: 'blue',
            //             width: 2
            //         }),
            //         image: new ol.style.Circle({
            //             radius: 7,
            //             fill: new ol.style.Fill({
            //                 color: 'blue'
            //             })
            //         }),
            //         text: new ol.style.Text({
            //             textAlign: 'center', // 位置
            //             textBaseline: 'middle', // 基准线
            //             exceedLength: 'true',
            //             offsetY:-15,//向上移（像素）
            //             font: 'normal 14px 微软雅黑',  // 文字样式
            //             text:_this.selectedNodes[0].name,  // 文本内容
            //             fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
            //             stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
            //         })
            //
            //     })
            // });
            // // 将绘制层添加到地图容器中
            //
            // map.addLayer(this['vectorMark']);
        //
        // } else if (type == "LineString") {
            // var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
            // if (coordinateArr.length != 0) {
            //     for (var i = 0; i < coordinateArr.length; i++) {
            //         coordinate.push(coordinateArr[i].split(','))
            //     }
            // }
            // Feature = new ol.Feature(new ol.geom.LineString(coordinate));
        // } else if (type == "Polygon") {
            // var coordinateArr = _this.selectedNodes[0].areaMap.split(';');
            // if (coordinateArr.length != 0) {
            //     for (var i = 0; i < coordinateArr.length; i++) {
            //         coordinate.push(coordinateArr[i].split(','))
            //     }
            // }
            // Feature = new ol.Feature(new ol.geom.Polygon([coordinate]));
        // }

        var areaPointCenter = _this.selectedNodes[0].areaPoint.split(';')[0].split(',');
        if (areaPointCenter.length == 2) {
            this.goTo(areaPointCenter);
        }
    },
    //图形编辑
    editGraphical: function (flag) {
        var map = this.map;
        var _this = this;
        if (flag) {
            if(_this.selectGraphical){
                map.removeInteraction(_this.selectGraphical);
            }
            // 初始化一个交互选择控件,并添加到地图容器中
            _this.selectGraphical = new ol.interaction.Select();
            map.addInteraction(_this.selectGraphical);
            // 初始化一个交互编辑控件，并添加到地图容器中
            _this.modifyGraphical = new ol.interaction.Modify({
                // 选中的要素
                features: _this.selectGraphical.getFeatures()
            });
            map.addInteraction(_this.modifyGraphical);

            // 选中的要素
            var selectedFeaturesGraphical = _this.selectGraphical.getFeatures();
            // 添加选中要素变更事件
            _this.selectGraphical.on('change:active', function () {
                selectedFeaturesGraphical.forEach(selectedFeaturesGraphical.remove,
                    selectedFeaturesGraphical);
            });
            // 图层修改结束事件，获取坐标
            _this.modifyGraphical.on('modifyend', function () {
                var geo = selectedFeaturesGraphical.getArray()[0].getGeometry(); // 获取要素的几何信息
                var coordinates = geo.getCoordinates(); // 获取几何坐标
                var geoType = geo.getType();
                // 将几何坐标拼接为字符串
                if (geoType == "Polygon") {
                    var len = coordinates[0].length;
                    var xSum = null, ySum = null, x = null, y = null;
                    for (var i = 0; i < len; i++) {
                        xSum += Number(coordinates[0][i][0]);
                        ySum += Number(coordinates[0][i][1]);
                    }
                    x = xSum / len;
                    y = ySum / len;
                    _this.areaPointDraw = x + ',' + y;
                    _this.geoStrDraw = coordinates[0].join(";");
                } else if (geoType == "LineString") {
                    var len = coordinates.length;
                    var xSum = null, ySum = null, x = null, y = null;
                    for (var i = 0; i < len; i++) {
                        xSum += Number(coordinates[i][0]);
                        ySum += Number(coordinates[i][1]);
                    }
                    x = xSum / len;
                    y = ySum / len;
                    _this.areaPointDraw = x + ',' + y;
                    _this.geoStrDraw = coordinates.join(";");
                } else {
                    _this.geoStrDraw = coordinates.join(",");
                    _this.areaPointDraw = _this.geoStrDraw;
                }
                if(_this.markInfoType !== "elecFence") {
                    if ($('#areaPoint').length > 0) {
                        $('#areaPoint').val(_this.geoStrDraw)
                    }
                }
            });

        }
        if (_this.selectGraphical) {
            // 激活--注销选择要素控件
            _this.selectGraphical.setActive(flag);
            // 激活--注销修改要素控件
            _this.modifyGraphical.setActive(flag);
        }
    },
    //图形删除
    removeGraphical: function () {
        // 关闭编辑功能
        this.editGraphical(false);
        // this.geoStrDraw = '';
        // this.areaPointDraw = '';
        // 删除
        var map = this.map;
        var _this = this;
        if(_this.markInfoType !== "elecFence"&&_this.markInfoType !== "drawForm") {//电子围栏-添加,或业务标点，不需要判断
            if (_this.markInfoId == null || _this.markInfoId == '') {
                top.$.jBox.tip('请选择资源进行操作');
                return;
            }
        }
        top.$.jBox.confirm("是否删除信息吗？", "系统提示", function (v, h, f) {
            if (v == "ok"){
                _this.geoStrDraw = '';
                _this.areaPointDraw = '';
                // 删除默认数据加载的图层
                _this.removeLayer('vectorMark');
                // 删除话的图层
                _this.removeLayer('drawMarkVector');
                if(_this.markInfoType != "drawForm"){
                    submitData(); // 提交几何与属性信息到后台处理
                }
                
                _this.removeLayer(_this.markInfoId); //用Id删除对应图层
                
                
            } else {
                map.removeInteraction(_this['drawDraw']);
                // 删除默认数据加载的图层
                // _this.removeLayer('vectorMark');
                // 删除话的图层
                // _this.removeLayer('drawMarkVector');
            }
        }, {
            buttonsFocus: 1
        });

        //将绘制的几何数据与对话框设置的属性数据提交到后台处理
        function submitData() {
            if (_this.geoStrDraw != null) {
                // 将数据提交到后台处理（保存到数据库中）
                if(_this.markInfoType == "elecFence"){//电子围栏
                    $('#areaPoint').val(_this.areaPointDraw);
                    $('#areaMap').val(_this.geoStrDraw);
                    // 移除绘制图形
                    map.removeInteraction(_this['drawDraw']);
                    top.$.jBox.tip("删除成功");
                    // 删除当前绘制图形
                    if(_this['drawMarkVector']){
                        _this['drawMarkVector'].getSource().removeFeature(_this.currentFeatureDraw);
                    }
                }else{
                    saveData(_this.areaPointDraw, _this.geoStrDraw);
                }
                // 置空当前绘制的几何要素
                _this.currentFeatureDraw = null;
                // 置空当前绘制图形的geoStr
                _this.geoStrDraw = null;
                this.areaPointDraw = '';
            } else {
                top.$.jBox.tip('图形未发生变化');
            }
        }

        //提交数据到后台保存
        function saveData(areaPoint, areaMap) {
            // 通过ajax请求将数据传到后台文件进行保存处理
            $.post('' + ctx + '/sys/map/updateMap', {
                type: _this.markInfoType,
                id: _this.markInfoId,
                areaPoint: areaPoint,
                areaMap: areaMap
            }, function (data) {
                // 移除绘制图形
                map.removeInteraction(_this['drawDraw']);
                if (areaPoint == "") {
                    //删除，树形颜色变色
                    $('#' + _this.selectedNodes[0].tId + '_span').css('color', '#aeaebb')
                } else {
                    //添加，树形颜色变色
                    $('#' + _this.selectedNodes[0].tId + '_span').css('color', 'black')
                }
                top.$.jBox.tip(data);
                _this.selectedNodes[0].areaPoint = areaPoint;
                _this.selectedNodes[0].areaMap = areaMap;
                // 删除当前绘制图形
                if(_this.currentFeatureDraw){
                    _this['drawMarkVector'].getSource().removeFeature(_this.currentFeatureDraw);
                }
            })
        }
    },
    //图形保存
    saveGraphical: function () {
        // 关闭编辑功能
        this.editGraphical(false);
        var map = this.map;
        var _this = this;
        if(_this.markInfoType !== "elecFence"&&_this.markInfoType !== "drawForm") {//电子围栏-添加不需要判断
            if (_this.markInfoId == null || _this.markInfoId == '') {
                top.$.jBox.tip('请选择资源进行操作');
                return;
            }
        }
        if (_this.geoStrDraw == null) {
            top.$.jBox.tip('图形未发生变化');
            return;
        }
        top.$.jBox.confirm("是否保存信息吗？", "系统提示", function (v, h, f) {
            if (v == "ok") {
                if(_this.markInfoType == "drawForm"){
                    parent.areaPoint=_this.areaPointDraw;
                    parent.areaMap=_this.geoStrDraw;
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.close(index);
                }else{
                    submitData(); // 提交几何与属性信息到后台处理
                }
            } else {
                map.removeInteraction(_this['drawDraw']);
                // 删除默认数据加载的图层
                // _this.removeLayer('vectorMark');
                // 删除话的图层
                // _this.removeLayer('drawMarkVector');
            }
        }, {
            buttonsFocus: 1
        });
        //将绘制的几何数据与对话框设置的属性数据提交到后台处理
        function submitData() {
            if (_this.geoStrDraw != null) {
                // 将数据提交到后台处理（保存到数据库中）
                if(_this.markInfoType == "elecFence"){//电子围栏
                    $('#areaPoint').val(_this.areaPointDraw);
                    $('#areaMap').val(_this.geoStrDraw);
                    // 移除绘制图形
                    map.removeInteraction(_this['drawDraw']);
                    top.$.jBox.tip("编辑成功");
                    // 删除当前绘制图形
                    if(_this['drawMarkVector']){
                        _this['drawMarkVector'].getSource().removeFeature(_this.currentFeatureDraw);
                    }
                }else{
                    saveData(_this.areaPointDraw, _this.geoStrDraw);
                }
                // 置空当前绘制的几何要素
                _this.currentFeatureDraw = null;
                // 置空当前绘制图形的geoStr
                _this.geoStrDraw = null;
                this.areaPointDraw = '';
            } else {
                top.$.jBox.tip('图形未发生变化');
            }
        }

        //提交数据到后台保存
        function saveData(areaPoint, areaMap) {
            // 通过ajax请求将数据传到后台文件进行保存处理
            $.post('' + ctx + '/sys/map/updateMap', {
                type: _this.markInfoType,
                id: _this.markInfoId,
                areaPoint: areaPoint,
                areaMap: areaMap
            }, function (data) {
                // 移除绘制图形
                map.removeInteraction(_this['drawDraw']);
                if (areaPoint == "") {
                    //删除，树形颜色变色
                    $('#' + _this.selectedNodes[0].tId + '_span').css('color', '#aeaebb')
                } else {
                    //添加，树形颜色变色
                    $('#' + _this.selectedNodes[0].tId + '_span').css('color', 'black')
                }
                top.$.jBox.tip(data);
                _this.selectedNodes[0].areaPoint = areaPoint;
                _this.selectedNodes[0].areaMap = areaMap;
                // 删除当前绘制图形
                if(_this.currentFeatureDraw) {
                    _this['drawMarkVector'].getSource().removeFeature(_this.currentFeatureDraw);
                }
            })
        }
    },
    //地图巡逻计划-信息
    planFlagInfo: function (selectPlanFlagObj) {
        this.selectPlanFlagObj = selectPlanFlagObj;
    },
    //地图巡逻计划-标选
    addPlanFlag: function () {
        var map = this.map;
        var _this = this;
        map.removeInteraction(this.removePlanFlag);
        this.selectPlanFlag = new ol.interaction.Select();
        map.addInteraction(this.selectPlanFlag);
        this.selectPlanFlag.on('select', function (e) {
            if (e.selected.length > 0) {
                var selectedFeatures = _this.selectPlanFlag.getFeatures();
                selectedFeatures.getArray().map(function (feature) {
                    var id = feature.c// 获取id;
                    var geo = feature.getGeometry(); // 获取要素的几何信息
                    var coordinates = geo.getCoordinates();
                    if (map.getOverlayById(id) != null) {
                        return false;
                    }
                    _this.selectPlanFlagObj.push({
                        id: id,
                        coordinates: coordinates
                    })
                    // 巡逻点位 数组放到字符串中
                    var len = _this.selectPlanFlagObj.length;
                    var PlanFlagId = [];
                    var PlanFlagCoordinates = [];
                    _this.addOverlayLabel(len, coordinates, id)
                    if (len > 0) {
                        for (var j = 0; j < len; j++) {
                            PlanFlagId.push(_this.selectPlanFlagObj[j].id)// 存储id数组
                            PlanFlagCoordinates.push([Number(_this.selectPlanFlagObj[j].coordinates[0]), Number(_this.selectPlanFlagObj[j].coordinates[1])])// 存储坐标
                        }
                    }
                    _this.selectPlanFlagIdStr = PlanFlagId.join(';')// 存储id字符串
                    // 画路线     // 实例化一个矢量图层Vector作为绘制层
                    _this.drakLine(PlanFlagCoordinates);
                    if ($('#pointSort').length > 0) {
                        $('#pointSort').val(_this.selectPlanFlagIdStr)
                    }
                })
            } else if (e.deselected.length > 0) {

            }
        });
    },
    //地图巡逻计划-删除旗帜标志
    
    rePlanFlag: function () {
        // 删除数组中保存的元素
        function compareId(arr, id) {
            var len = arr.length;
            for (var i = 0; i < len; i++) {
                if (arr[i].id == id) {
                    arr.splice(i, 1);
                    break;
                }
            }
            return arr
        }

        var map = this.map;
        var _this = this;
        map.removeInteraction(this.selectPlanFlag);
        this.removePlanFlag = new ol.interaction.Select();
        map.addInteraction(this.removePlanFlag);
        this.removePlanFlag.on('select', function (e) {
            if (e.selected.length > 0) {
                var selectedFeatures = _this.removePlanFlag.getFeatures();
                selectedFeatures.getArray().map(function (feature) {
                    var id = feature.c// 获取id;
                    var geo = feature.getGeometry(); // 获取要素的几何信息
                    var coordinates = geo.getCoordinates();
                    map.removeOverlay(_this[id + 'Overlay']);
                    _this.selectPlanFlagObj = compareId(_this.selectPlanFlagObj, id)
                    var len = _this.selectPlanFlagObj.length;
                    var PlanFlagId = [];
                    var PlanFlagCoordinates = [];
                    //字符串转number
                    if (len > 0) {
                        for (var j = 0; j < len; j++) {
                            PlanFlagCoordinates.push([Number(_this.selectPlanFlagObj[j].coordinates[0]), Number(_this.selectPlanFlagObj[j].coordinates[1])])// 存储坐标
                            PlanFlagId.push(_this.selectPlanFlagObj[j].id)// 存储id数组
                            map.removeOverlay(_this[_this.selectPlanFlagObj[j].id + 'Overlay']);
                            _this.addOverlayLabel((j + 1), _this.selectPlanFlagObj[j].coordinates, _this.selectPlanFlagObj[j].id);
                        }
                    }
                    _this.selectPlanFlagIdStr = PlanFlagId.join(';')// 存储id字符串
                    // 实例化一个矢量图层Vector作为绘制层
                    _this.drakLine(PlanFlagCoordinates);
                    if ($('#pointSort').length > 0) {
                        $('#pointSort').val(_this.selectPlanFlagIdStr)
                    }
                })
            }
        });
    },
    // 地图巡逻计划---标注加载默认数据
    addGraphicalPlan: function (type, coordinate) {
        var map = this.map;
        var _this = this;
        // 删除图层vectorMark
        this.removeLayer('vectorMark');// 删除图层
        // 关闭编辑功能
        this.editGraphical(false);
        // 绘制的几何图形要素
        var Feature;

        // 点
        if (type == "Point") {
            Feature = new ol.Feature(new ol.geom.Point(coordinate));
            // 线
        } else if (type == "LineString") {
            Feature = new ol.Feature(new ol.geom.LineString(coordinate));
            // 面
        } else if (type == "Polygon") {
            Feature = new ol.Feature(new ol.geom.Polygon([coordinate]));
        }
        // 实例化一个矢量图层Vector作为绘制层
        var source = new ol.source.Vector({
            features: [Feature]
        });
        this['vectorMark'] = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 10,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                })
            })
        });
        map.addLayer(this['vectorMark']); // 将绘制层添加到地图容器中
        this.goTo(coordinate);
    },

    //巡逻结果设置为显示隐藏
    patrolResultIsShow: function (id, resultCheckColor, flag) {
        var map = this.map;
        var featture = this['resultCheck'].getSource().getFeatureById(id);
        if (flag) {
            //显示
            featture.setStyle(new ol.style.Style({
                fill: new ol.style.Fill({
                    color: resultCheckColor,
                }),
                stroke: new ol.style.Stroke({
                    color: resultCheckColor,
                    width: 5
                }),

            }))
        } else {
            //隐藏
            featture.setStyle(new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(255, 255, 255, 0)',
                    width: 0
                }),

            }))
        }
    },
    //巡逻计划点位删除
    removeGraphicalPlan: function () {
        // 关闭编辑功能
        this.editGraphical(false);
        this.geoStrDraw = '';
        this.areaPointDraw = '';
        // 删除默认数据加载的图层
        this.removeLayer('vectorMark');
        // 删除话的图层
        this.removeLayer('drawMarkVector');
        if ($('#areaPoint').length > 0) {
            $('#areaPoint').val('')
        }
    },
    // 画线
    drakLine: function (PlanFlagId) {
        var map = this.map;
        var _this = this;
        var flag = null;
        if (this['planFlagLine']) {
            flag = _this['planFlagLine'].getVisible();
        }
        this.removeLayer('planFlagLine')
        var Feature = new ol.Feature(new ol.geom.LineString(PlanFlagId));
        var source = new ol.source.Vector({
            features: [Feature]
        });
        _this['planFlagLine'] = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 10,
                    fill: new ol.style.Fill({
                        color: 'blue'
                    })
                })
            })
        });
        map.addLayer(_this['planFlagLine']); // 将绘制层添加到地图容器中
        _this.layersIsShow('planFlagLine', flag);//设置图层显示隐藏
    },
    //添加文字
    drawText: function (flag) {
        var map = this.map;
        var _this = this;
        var mousePosition = null;
        map.on('click', function (event) {
            if (event.dragging) {
                return;
            }
            var pixel = map.getEventPixel(event.originalEvent);
            var hit = map.hasFeatureAtPixel(pixel);
            // 添加手势
            map.getTargetElement().style.cursor = hit ? 'pointer' : '';
            mousePosition = event['coordinate'];
            if (flag) {
                _this.drawTexTaddOverlay(mousePosition);
                $('.tag-panl-span').removeClass('active');
            }
            flag = false;
        });
    },
    //添加文字输入框
    drawTexTaddOverlay: function (coordinate) {
        //创建div
        var elementDiv = document.createElement('div');
        //创建文本输入框
        var textarea = document.createElement('textarea');
        textarea.className = "textarea-overlay";
        textarea.id = "Id" + coordinate[0];
        elementDiv.appendChild(textarea);
        var newMarker = new ol.Overlay({
            position: coordinate,
            positioning: 'center-center',
            element: elementDiv,
            stopEvent: false
        });
        map.addOverlay(newMarker);
        textarea.focus();
        textarea.onclick = function () {
            activeDelBtn(this)
        }
        $("#Id" + coordinate[0]).focus(function () {
            activeDelBtn(this);//显示删除按钮
        })
        $('.textarea-overlay').focus(function () {
            $(this).addClass('active')
        })
    },
    // 悬上图层
    selectPointer: function () {
        var map = this.map;
        var _this = this;
        // 鼠标位置
        var mousePosition = null;
        // 弹框
        $('#popup').show();
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');
        this.overlayDialog = new ol.Overlay({
            element: container,
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        if (closer) {
            closer.onclick = function () {
                _this.overlayDialog.setPosition(undefined);
                closer.blur();
                $('#detailsDialog').html('');
                $('#detailsDialog').hide();
                return false;
            };
        }

        map.on('click', function (event) {
            if (closer) {
                // 关闭弹框
                closer.click();
            }

            // 鼠标位置
            // mousePosition = map.getEventCoordinate(event);
            mousePosition = event['coordinate']
        });
        map.on('pointermove', function (event) {
            if (event.dragging) {
                return;
            }
            var pixel = map.getEventPixel(event.originalEvent);
            var hit = map.hasFeatureAtPixel(pixel);
            // 添加手势
            map.getTargetElement().style.cursor = hit ? 'pointer' : '';
            mousePosition = event['coordinate']
        });
        map.addOverlay(this.overlayDialog)

        var select = null; // ref to currently selected interaction

        // select interaction working on "singleclick"

        // select interaction working on "click"
        _this.selectClick = new ol.interaction.Select({
            condition: ol.events.condition.click,
            filter: function (feature, layer) {
                return layer == _this['binguan'] || _this['yule'] || _this['shangchang'] || _this['jiayouzhan'] || _this['yiyuan'] || _this['grids'] || layer == _this['builds'] || layer == _this['events'] || layer == _this['parts'] || layer == _this['lands'] || layer == _this['videos']|| layer == _this['policeroom']||  layer == _this['workstation']|| layer == _this['broadcast'] || layer == _this['schoolPlace'] || layer == _this['keyPlace'] || _this['xuexiao'] || layer == _this['keyPerson'] || layer == _this['rentingPerson'] || layer == _this['publicPlace'] || layer == _this['PopLocation'] || layer == _this['SetTopBoxFlag'] || layer == _this['zongjiaotype']

            }

        });
        var selectSingleClick = new ol.interaction.Select({
            filter: function (feature, layer) {
                return layer == _this['communitys'] || layer == _this['streets'];
            }
        });
        var selectPointerMove = new ol.interaction.Select({
            condition: ol.events.condition.pointerMove,
        });
        map.addInteraction(_this.selectClick);
        _this.selectClick.on('select', function (e) {
            if (e.selected.length > 0) {
                selectedFeatures(_this.selectClick)
            } else if (e.deselected.length > 0) {

            }
        });

        selectedFeatures(_this.selectClick)
        // 点击添加饼形图--勿删
        map.addInteraction(selectSingleClick);
        selectSingleClick.on('select', function (e) {
            $('#detailsDialog').html('');
            $('#detailsDialog').show()
            if (e.selected.length > 0) {
                var selectedFeatures = selectSingleClick.getFeatures();
                var id = selectedFeatures.getArray().map(function (feature) {
                    return feature.get('info')['id1'];// 社区网格id
                })
                var type = selectedFeatures.getArray().map(function (feature) {
                    return feature.get('info')['所属层级'];// 社区网格id
                })
                //var id = $('#detailsDialog').attr('infoId');
//					if(type=='4'){
//						$("#detailsDialog").load(
//								ctx + "/pop/ccmPeople/getMapAreaForm?id=" + id, {});
//					}else{
//						$("#detailsDialog").load(
//								ctx + "/pop/ccmPeople/getMapAreaForm?id=" + id, {});
//					}
                $("#detailsDialog").load(ctx + "/pop/ccmPeople/getMapAreaForm?id=" + id, {});


            } else if (e.deselected.length > 0) {
                $('#detailsDialog').hide()
            }
        });

        function selectedFeatures(selectType) {
            var selectedFeatures = selectType.getFeatures();
            selectedFeatures
                .on(
                    ['add', 'remove'],
                    function (event) {
                        var info = null;
                        var video = null;
                        var videoList = null;
                        var type = null;
                        var videoIp = null;
                        var videoId = null;
                        var videoPort = null;
                        var videoUsername = null;
                        var videoPassword = null;
                        var cameraType = null;
                        var featureId = null;
                        var featureId1 = null;
                        var videoUrl = null;
                        var popInfo = null;
                        var featureName = null;
                        var PopLocationId = null;
                        var htmls ='<div class="items-top">'
                        var feature = selectedFeatures.getArray().map(function (feature) {
                                    if (!feature.get('features')) {

                                        info = feature.get('info');
                                        video = feature.get('video');
                                        videoList = feature.get('videoList');
                                        featureName = feature.get('name');
                                        if (info) {
                                            featureId = feature.get('info')['id'];// 楼栋id
                                            featureId1 = feature.get('info')['id1'];// 社区网格id
                                            popInfo = feature.get('info')['重点人员']
                                            pilesNum = feature.get('info')['层数'];// 层数
                                            elemNum = feature.get('info')['单元数'];// 单元数
                                            buildName = feature.get('info')['楼栋名称'];// 楼栋名称
                                            cameraType = info['监控类型'];
                                        }
                                        if (video) {
                                            type = video['protocol'];
											videoUrl = video['param']
                                            videoId = feature.getId()
                                            videoIp = video['ip'];
                                            videoPort = video['port'];
                                            videoUsername = video['username'];
                                            videoPassword = video['password'];
                                        }
                                    } else {
                                        if (feature.get('features').length == 1) {
                                            //点聚合后的信息与其他不同
                                            info = feature.get('features')[0].get('info');
                                            video = feature.get('features')[0].get('video');
                                            videoList = feature.get('features')[0].get('videoList');
                                            featureName = feature.get('features')[0].get('name');
                                            PopLocationId = feature.get('features')[0].getId();
                                            if (info) {
                                                featureId = feature.get('features')[0].get('info')['id'];// 楼栋id
                                                featureId1 = feature.get('features')[0].get('info')['id1'];// 社区网格id
                                                popInfo = feature.get('features')[0].get('info')['重点人员'];
                                                pilesNum = feature.get('features')[0].get('info')['层数'];// 层数
                                                elemNum = feature.get('features')[0].get('info')['单元数'];// 单元数
                                                buildName = feature.get('features')[0] .get('info')['楼栋名称'];// 楼栋名称
                                                cameraType = feature.get('features')[0] .get('info')['监控类型'];// 监控类型
                                            }

                                            if (video) {
                                                type = video['protocol'];
												videoUrl = video['param']
                                                videoId = feature.get('features')[0].getId()
                                                videoIp = video['ip'];
                                                videoPort = video['port'];
                                                videoUsername = video['username'];
                                                videoPassword = video['password'];
                                            }
                                            // if (videoList) {
                                            //     for(var video in videoList){
                                            //         type = video['protocol'];
                                            //         videoUrl = video['param']
                                            //         videoId = feature.get('features')[0].getId()
                                            //         videoIp = video['ip'];
                                            //         videoPort = video['port'];
                                            //         videoUsername = video['username'];
                                            //         videoPassword = video['password'];
                                            //     }
                                            //
                                            // }
                                        } else {
                                            info = feature.get('features')[0].get('info');
                                            video = feature.get('features')[0].get('video');
                                            videoList = feature.get('features')[0].get('videoList');
                                            featureName = feature.get('features')[0].get('name');
                                            PopLocationId = feature.get('features')[0].getId();
                                            var data=feature.get('features');
                                            var len=data.length;
                                            var displayFlag='';
                                            if(len==1){
                                                displayFlag='none';
                                            }
                                            if (info) {
                                                // var html = '';
                                                // var html ='<div class="items-top">'
                                                for (var i = 0; i < len; i++) {
                                                        htmls += '<div class="top">';
                                                        htmls += '<span style="color: #03f;line-height: 28px; height: 28px;">详情</span>';
                                                        htmls += '<span class="prev-next" style="display:' + displayFlag + '">';
                                                        htmls += '<span style="font-weight: bold;  margin: 0 10px;">（' + (i + 1) + '/' + len + '）</span>';
                                                        htmls += '<i class="icon-caret-left" title="上一页" style="margin: 0 5px;"></i><i title="下一页" class="icon-caret-right"></i>';
                                                        htmls += '</span>';
                                                        htmls += '<div class="inner" >';
                                                        htmls += '<table>';
                                                        var info = data[i].get('info');
                                                        videoId = data[i].getId();
                                                        cameraType = info['监控类型'];
                                                        htmls += '<tr>';
                                                        htmls += '<td><strong>名称：</strong></td>';
                                                        htmls += '<td  style="color:#eea807">' +data[i].get('name') + '</td>';
                                                        htmls += '</tr>'
                                                        htmls += '<tr>';
                                                        for (var j in info) {

                                                            if (j != 'id' && j != '重点人员' && j != 'id1'&& j != '监控类型') {

                                                                if (j == "性别" && info[j] != null) {
                                                                    htmls += '<td><strong>' + j + '：</strong></td>';
                                                                    htmls += '<td style="color:#eea807">' + ((info[j] == 1 || info[j] == "1") ? '女' : '男') + '</td>';
                                                                } else if (j == "设备状态" && info[j] != null) {
                                                                    htmls += '<td><strong>' + j + '：</strong></td>';
                                                                    if (info[j] == 1 || info[j] == "1") {
                                                                        htmls += '<td style="color:#eea807">在线</td>';

                                                                    } else if (info[j] == 2 || info[j] == "2") {
                                                                        htmls += '<td style="color:#eea807">掉线</td>';

                                                                    } else if (info[j] == 3 || info[j] == "3") {
                                                                    	htmls += '<td style="color:#eea807">故障</td>';

                                                                    } else {
                                                                    	htmls += '<td style="color:#eea807">未知</td>';

                                                                    }

                                                                } else if (j == "所属层级") {
                                                                    continue

                                                                } else if (j == "电子围栏区域") {
                                                                    continue

                                                                } else if (j == "设备名称") {
                                                                    continue

                                                                } else if (j == "广播站名称") {
                                                                    continue

                                                                } else if (j == "是否在线") {
                                                                    htmls += '<td><strong>' + j + '：</strong></td>';
                                                                    if (info[j] == "1") {
                                                                        htmls += '<td style="color:#eea807">是</td>';
                                                                    } else {
                                                                        htmls += '<td style="color:#eea807">否</td>';
                                                                    }

                                                                } else if (j == "是否越界") {
                                                                    htmls += '<td><strong>' + j + '：</strong></td>';
                                                                    if (info[j] == "1") {
                                                                        htmls += '<td style="color:#eea807">是</td>';
                                                                    } else {
                                                                        htmls += '<td style="color:#eea807">否</td>';
                                                                    }
                                                                    htmls += '</tr>'
                                                                    htmls += '<tr>'
                                                                    htmls += '<td><strong>历史轨迹：</strong></td>';
                                                                    htmls += '<td><a id="' + PopLocationId + '" ElectronicFence="' + info["电子围栏区域"] + '"  deviceId="' + info["设备唯一标识码"] + '"  class="HisTrack btn btn-success"  href="###" onclick="HisTrackFun(this)">查看</a></td>';
                                                                } else if (j == '公共机构人员') {

                                                                    htmls += '<td><strong>人员信息：</strong></td>';
                                                                    htmls += '<td><a id="' + featureId + '"  popinfo="' + JSON.stringify(info[j]).replace(/"/g, '&quot;') + '" class=" btn btn-success"  href="###" onclick="PopInfoFun(this)">查看</a></td>';
                                                                } else if (j == '下发广播') {
                                                                    htmls += '<td><strong>' + j + '：</strong></td>';
                                                                    htmls += '<td style="vertical-align:middle;">';
                                                                    htmls += '<iframe id="deviceUpload" src="' + ctx + '/sys/map/todevice?code=' + ((info[j] == null || info[j] == "null") ? '' : info[j]) + '" width="100%" height="50%" frameborder="0"></iframe>';
                                                                    htmls += '</td>';

                                                                } else {
                                                                    htmls += '<td><strong>' + j + '：</strong></td>';
                                                                    htmls += '<td style="color:#eea807">' + ((info[j] == null || info[j] == "null") ? '' : info[j]) + '</td>';
                                                                }


                                                            }

                                                            htmls += '</tr>'
                                                        }
                                                        if (video) {
                                                            htmls += '<tr>';
                                                            htmls += '<td><strong>视频监控：</strong></td>';
                                                            htmls += '<td>' +
                                                                '<a class="click btn btn-success"  href="###" videoIp="' + videoIp + '"  videoId="' + videoId + '"  cameraType="' + cameraType + '" >播放</a></td>';
                                                            htmls += '</tr>'
                                                        }


                                                        if (videoList) {
                                                            htmls += '<tr>';
                                                            htmls += '<td><strong>监控列表：</strong></td>';
                                                            htmls += '<td>';
                                                            htmls += '<select id="videoSelect" onchange=" document.getElementById("videoSelect").nextElementSibling.setAttribute("videoId",this.value)">';
                                                            for (var j = 0; j < videoList.length; j++) {
                                                                htmls += '<option value=' + videoList[j].id + ' >' + videoList[j].name + '</option>'
                                                            }
                                                            htmls += '</select>';
                                                            htmls += '<a class="click btn btn-success"  href="###" videoIp="' + videoIp + '"  videoId="' + videoId + '" cameraType="' + cameraType + '"  >播放</a>';
                                                            htmls += '</td>';
                                                            htmls += '</tr>'
                                                        }
                                                        if (j != '公共机构人员') {
                                                            if (featureId && !videoList) {
                                                                htmls += '<tr>';
                                                                htmls += '<td><strong>楼栋住户信息：</strong></td>';
                                                                htmls += '<td><a class="bulidclick btn btn-success" onclick="$(\'#popup-closer\').click();" href="###" featureId="'
                                                                    + featureId
                                                                    + '"  elemNum="'
                                                                    + elemNum
                                                                    + '" pilesNum="'
                                                                    + pilesNum
                                                                    + '" buildName="'
                                                                    + buildName + '">详情</a></td>';
                                                                htmls += '</tr>'
                                                            }
                                                        }

                                                        if (popInfo) {
                                                            htmls += '<tr>';
                                                            htmls += '<td>信息：</td>';
                                                            htmls += '<td><a class="popclick btn btn-success" href="###" type="'
                                                                + type
                                                                + '" popInfo="'
                                                                + JSON.stringify(popInfo).replace(
                                                                    /"/g, '&quot;')
                                                                + '">详情</a></td>';
                                                            htmls += '</tr>'
                                                        }
                                                    htmls += '</table>'
                                                    htmls += '</div>'
                                                    htmls += '</div>'
                                            }
                                                return
                                            }
                                        }

                                    }

                                    if (featureId1) {
                                        $('#detailsDialog').attr('infoId', featureId1);
                                    }

                                    if (feature) {
                                        return feature
                                    } else {
                                        return;
                                    }

                                });

                        htmls += '</div>'
                        if(!feature[0]){
                            content.innerHTML = htmls;
                            var top = $(".top");
                            if(top.length==0){
                                return;
                            }
                            top.hide();
                            $(".items-top .top").eq(0).show();
                            //下一 页
                            $(".icon-caret-right").click(function () {
                                var num = $(this).parent().parent().index() + 1;
                                top.hide();
                                $(".items-top .top").eq(num).show();
                                if (num == top.length) {
                                    $(".items-top .top").eq(0).show();
                                }
                            });
                            //上一页
                            $(".icon-caret-left").click(function () {
                                var num = $(this).parent().parent().index() - 1;
                                top.hide();
                                $(".items-top .top").eq(num).show();
                            });
                            _this.overlayDialog.setPosition(mousePosition);// 弹框位置---鼠标位置
                            return;
                        }
                        if(!feature[0].get('name')&&!(feature[0].get('features')&&feature[0].get('features')[0])){
                            return;
                        }
                        var names = "";
                        var info = "";
                        var video = "";
                        var videoList = "";
                        var featureName = "";
                        var PopLocationId = "";
                        if(feature[0].get('name')){
                            names = feature[0].get('name')
                        }else{
                            names =  feature[0].get('features')[0].get('name');
                            info = feature[0].get('features')[0].get('info');
                            video = feature[0].get('features')[0].get('video');
                            videoList = feature[0].get('features')[0].get('videoList');
                            featureName = feature[0].get('features')[0].get('name');
                            PopLocationId = feature[0].get('features')[0].getId();
                        }

                        // name不等于事件的Featurename
                        if (names.length > 0 && names != "EventFeature"
                            && names[0] != undefined) {
                            var html = '<table>';
                            html += '<tr>';
                            html += '<td><strong>名称：</strong></td>';
                            html += '<td  style="color:#eea807">' + names+ '</td>';
                            html += '</tr>'
                            for (var i in info) {
                                cameraType = info['监控类型'];
                                html += '<tr>';
                                if (i != 'id' && i != '重点人员' && i != 'id1' && i != '监控类型') {

                                    if (i == "性别" && info[i] != null) {
                                        html += '<td><strong>' + i + '：</strong></td>';
                                        html += '<td style="color:#eea807">' + ((info[i] == 1 || info[i] == "1") ? '女' : '男') + '</td>';
                                    } else if (i == "设备状态" && info[i] != null) {
                                        html += '<td><strong>' + i + '：</strong></td>';
                                        if (info[i] == 1 || info[i] == "1") {
                                            html += '<td style="color:#eea807">在线</td>';

                                        } else if (info[i] == 2 || info[i] == "2") {
                                            html += '<td style="color:#eea807">掉线</td>';

                                        } else if (info[i] == 3 || info[i] == "3") {
                                            html += '<td style="color:#eea807">故障</td>';

                                        } else {
                                            html += '<td style="color:#eea807">未知</td>';

                                        }

                                    } else if (i == "所属层级") {
                                        continue

                                    } else if (i == "电子围栏区域") {
                                        continue

                                    } else if (i == "设备名称") {
                                        continue

                                    } else if (i == "广播站名称") {
                                        continue

                                    } else if (i == "是否在线") {
                                        html += '<td><strong>' + i + '：</strong></td>';
                                        if (info[i] == "1") {
                                            html += '<td style="color:#eea807">是</td>';
                                        } else {
                                            html += '<td style="color:#eea807">否</td>';
                                        }

                                    } else if (i == "是否越界") {
                                        html += '<td><strong>' + i + '：</strong></td>';
                                        if (info[i] == "1") {
                                            html += '<td style="color:#eea807">是</td>';
                                        } else {
                                            html += '<td style="color:#eea807">否</td>';
                                        }
                                        html += '</tr>'
                                        html += '<tr>'
                                        html += '<td><strong>历史轨迹：</strong></td>';
                                        html += '<td><a id="' + PopLocationId + '" ElectronicFence="' + info["电子围栏区域"] + '"  deviceId="' + info["设备唯一标识码"] + '"  class="HisTrack btn btn-success"  href="###" onclick="HisTrackFun(this)">查看</a></td>';
                                    } else if (i == '公共机构人员') {

                                        html += '<td><strong>人员信息：</strong></td>';
                                        html += '<td><a id="' + featureId + '"  popinfo="' + JSON.stringify(info[i]).replace(/"/g, '&quot;') + '" class=" btn btn-success"  href="###" onclick="PopInfoFun(this)">查看</a></td>';
                                    } else if(i == '下发广播'){
                                    	html += '<td><strong>' + i + '：</strong></td>';
                                    	html += '<td style="vertical-align:middle;">';
                            			html += '<iframe id="deviceUpload" src="'+ctx+'/sys/map/todevice?code=' + ((info[i] == null || info[i] == "null") ? '' : info[i]) + '" width="100%" height="50%" frameborder="0"></iframe>';
                            			html += '</td>';
                            			
                                    }else{
                                    	html += '<td><strong>' + i + '：</strong></td>';
                                        html += '<td style="color:#eea807">' + ((info[i] == null || info[i] == "null") ? '' : info[i]) + '</td>';
                                    }


                                }

                                html += '</tr>'
                            }
                            if (video) {
                                html += '<tr>';
                                html += '<td><strong>视频监控：</strong></td>';
                                html += '<td><a class="click btn btn-success"  href="###" videoIp="' + videoIp + '"  videoId="' + videoId + '"  cameraType="' + cameraType + '"  >播放</a></td>';
                                html += '</tr>'
                            }


                            if (videoList) {
                                html += '<tr>';
                                html += '<td><strong>监控列表：</strong></td>';
                                html += '<td>';
                                html += '<select id="videoSelect" onchange=" document.getElementById("videoSelect").nextElementSibling.setAttribute("videoId",this.value)">';
                                for (var i = 0; i < videoList.length; i++) {
                                    html += '<option value=' + videoList[i].id + ' >' + videoList[i].name + '</option>'
                                }
                                html += '</select>';
                                html += '<a class="click btn btn-success"  href="###" videoIp="' + videoIp + '"  videoId="' + videoId + '" cameraType="' + cameraType + '"  >播放</a>';
                                html += '</td>';
                                html += '</tr>'
                            }
                            if (i != '公共机构人员') {
                                if (featureId && !videoList) {
                                    html += '<tr>';
                                    html += '<td><strong>楼栋住户信息：</strong></td>';
                                    html += '<td><a class="bulidclick btn btn-success" onclick="$(\'#popup-closer\').click();" href="###" featureId="'
                                        + featureId
                                        + '"  elemNum="'
                                        + elemNum
                                        + '" pilesNum="'
                                        + pilesNum
                                        + '" buildName="'
                                        + buildName + '">详情</a></td>';
                                    html += '</tr>'
                                }
                            }

                            if (popInfo) {
                                html += '<tr>';
                                html += '<td>信息：</td>';
                                html += '<td><a class="popclick btn btn-success" href="###" type="'
                                    + type
                                    + '" popInfo="'
                                    + JSON.stringify(popInfo).replace(
                                        /"/g, '&quot;')
                                    + '">详情</a></td>';
                                html += '</tr>'
                            }

                            html += '</table>'
                            if (content) {
                                content.innerHTML = html;
                            }

                            // overlay.setZIndex(99999)
                            _this.overlayDialog.setPosition(mousePosition);// 弹框位置---鼠标位置

                        } else if (names.length > 0 && names == "EventFeature") {
                            // 事件弹框强请
                            var id = _this.postcomposeOlId;
                            var obj = eval('(' + _this.postcomposeOlObj
                                + ')');
                            var happenDate = _this.happenDate;
                            var html = '<div id="eventsDialog">';
                            html += '<table>';
                            html += '<tr>';
                            html += '<td><strong>名称：</strong></td>';
                            html += '<td>' + obj.caseName + '</td>';
                            html += '</tr>';
                            html += '<tr>';
                            html += '<td><strong>案事件模块分类：</strong></td>';
                            html += '<td>' + obj.eventKind + '</td>';
                            html += '</tr>';
                            html += '<tr>';
                            html += '<td><strong>案发地详细地址：</strong></td>';
                            html += '<td>' + obj.happenPlace + '</td>';
                            html += '</tr>';
                            html += '<tr>';
                            html += '<td><strong>案事件情况：</strong></td>';
                            html += '<td>' + obj.caseCondition + '</td>';
                            html += '</tr>';
                            html += '<tr>';
                            html += '<td><strong>主犯名称：</strong></td>';
                            html += '<td>' + obj.culName + '</td>';
                            html += '</tr>';
                            html += '<tr>';
                            html += '<td><strong>案发日期：</strong></td>';
                            html += '<td>' + happenDate + '</td>';
                            html += '</tr>';
                            html += '</table>';
                            html += '<tr>';
                            html += '<td><strong>预案：</strong></td>';
                            html += '<td><a  class="popclick btn btn-success"  target="_blank" href="'
                                + ctx
                                + '/event/ccmEmergencyPlan/solveList?ccmEmergencyPlan=">详情</a></td>';
                            html += '</tr>';
                            html += '</div>';
                            if (content) {
                                content.innerHTML = html;
                            }
                            _this.overlayDialog.setPosition(mousePosition);
                        } else {

                        }
                    });
        }

    },

    //记载数据--涉及点聚合-style中feature为整个数组
    addJSON1: function (params) {
        var map = this.map;
        var _this = this;
        // 加载数据
        var vectorArr = params;
        var len = vectorArr.length;
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                if(vectorArr[i].data!=null && vectorArr[i].data!=undefined){
                    this.center = vectorArr[i].data.centpoint;
                    var vectorArrType = vectorArr[i].type;
                    //图层id

                    var vectorArrId = vectorArr[i].id || vectorArr[i].type;
                    // 添加到矢量数据源
                    var Data = vectorArr[i].data.features;
                    var DataLen = Data.length;
                    var vectorSource = null;
                    var clusterSource = null;
                    var layerVectortype = vectorSource;
                    if (DataLen > 0) {

                        //字符串转化为number数据  ['137','47']=>[137,47]
                        for (var j = 0; j < DataLen; j++) {
                            if (Data[j].geometry.type == "Point") {
                                vectorArr[i].data.features[j].geometry.coordinates[0] = Number(vectorArr[i].data.features[j].geometry.coordinates[0])
                                vectorArr[i].data.features[j].geometry.coordinates[1] = Number(vectorArr[i].data.features[j].geometry.coordinates[1])
                            } else if (Data[j].geometry.type == "Polygon") {
                                var PolygonLen = vectorArr[i].data.features[j].geometry.coordinates.length;
                                if (PolygonLen > 0) {
                                    for (var k = 0; k < PolygonLen; k++) {
                                        var coordinates = vectorArr[i].data.features[j].geometry.coordinates[k];
                                        var coordinatesLen = coordinates.length;
                                        if (coordinatesLen > 0) {
                                            for (var m = 0; m < coordinatesLen; m++) {
                                                vectorArr[i].data.features[j].geometry.coordinates[k][m][0] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][m][0])
                                                vectorArr[i].data.features[j].geometry.coordinates[k][m][1] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][m][1])

                                            }
                                        }
                                    }
                                }
                            } else if (Data[j].geometry.type == "LineString") {
                                var LineStringLen = vectorArr[i].data.features[j].geometry.coordinates.length;
                                if (LineStringLen > 0) {
                                    for (var k = 0; k < LineStringLen; k++) {
                                        var coordinates = vectorArr[i].data.features[j].geometry.coordinates[k];
                                        var coordinatesLen = coordinates.length;
                                        if (coordinatesLen > 0) {
                                            vectorArr[i].data.features[j].geometry.coordinates[k][0] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][0])
                                            vectorArr[i].data.features[j].geometry.coordinates[k][1] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][1])
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //数据
                    vectorSource = this.vectorGeoJSON(vectorArr[i].data)
                    //点聚合
                    clusterSource = this.cluster(vectorSource);
                    if (DataLen > 0) {
                        for (var j = 0; j < DataLen; j++) {
                            if (Data[j].geometry.type == "Point") {
                                layerVectortype = clusterSource;
                            } else if (Data[j].geometry.type == "Polygon") {
                                layerVectortype = vectorSource;
                            }
                        }
                    }

                    var styleCache = {};
                    var cont1 = 0;
                    this[vectorArrId] = new ol.layer.Vector({
                        visible: vectorArr[i].isShow,
                        source: layerVectortype,
                        zIndex: 9,
                        // 设置样式
                        style: function (feature) {
                            //图标地址
                            var iconSrc = feature.get('icon');
                            var colorArr = _this.colorArr;
                            var index = Math.floor((Math.random() * colorArr.length));
                            var areaColor = feature.get('color');
                            var fillColor = areaColor ? areaColor : colorArr[index].rgba;
                            var color = colorArr[index].color;
                            var getZoom = map.getView().getZoom()
                            if (vectorArrType == "resultCheck") {
                                iconSrc = feature.get('info').color;//巡逻结果颜色
                            }

                            //中心
                            var coordinate = feature.get('coordinateCentre');
                            //公共机构
                            if (vectorArrType == "publicPlace") {
                                map.removeOverlay(_this[feature.getId() + 'Overlay'])
                                if (getZoom >= 16) {
                                    _this.addOverlayLabelpublicPlace(feature.get('name'), coordinate, feature.getId())

                                } else {
                                    _this.addOverlayLabelpublicPlaceIcon(feature.get('name'), coordinate, feature.getId())
                                }
                            }


                            //返回样式
                            if (_this.featureStyles(iconSrc, feature)[vectorArrType]) {
                                return _this.featureStyles(iconSrc, feature)[vectorArrType];
                            } else {

                                //点聚合  点feature为数组
                                var GeometryType = feature.getGeometry().getType();
                                if (GeometryType == "Point") {
                                    var selectedFeatures = feature.get('features');
                                    var idIndex = "";
                                    selectedFeatures.map(function (feature) {
                                        idIndex += feature.getId()
                                    });
                                    var size = selectedFeatures.length;
                                    var style = styleCache[idIndex];
                                    if (!style) {
                                        var color = size > 25 ? "192,0,0" : size > 8 ? "255,128,0" : "0,128,0";
                                        var radius = Math.max(8, Math.min(size * 0.75, 20));
                                        var dash = 2 * Math.PI * radius / 6;
                                        dash = [0, dash, dash, dash, dash, dash, dash];
                                        if (size == 1) {
                                            var Src = feature.get('features')[0].get('icon');
                                            if (vectorArrType == "PopLocation") {
                                                if (feature.get('features')[0].get('info')['是否在线'] == '1') {
                                                    if (feature.get('features')[0].get('info')['是否越界'] == '1') {
                                                        Src = 'p3.png'
                                                    } else {
                                                        Src = 'p1.png'
                                                    }

                                                } else {
                                                    if (feature.get('features')[0].get('info')['是否越界'] == '1') {
                                                        Src = 'p3.png'
                                                    } else {
                                                        Src = 'p2.png'
                                                    }
                                                }
                                            }
                                            style = styleCache[idIndex] = [
                                                new ol.style.Style({
                                                    image: new ol.style.Icon({
                                                        src: ctxStatic + '/modules/map/images/' + Src + '',
                                                        scale: map.getView().getZoom() / 15
                                                    }),
                                                    text: new ol.style.Text({
                                                        textAlign: 'center', // 位置
                                                        textBaseline: 'top', // 基准线
                                                        offsetY: '10',
                                                        exceedLength: 'true',
                                                        font: 'normal 10px 微软雅黑',  // 文字样式
                                                        text: feature.get('features')[0].get('name'),  // 文本内容
                                                        fill: new ol.style.Fill({color: '#aa3300'}), // 文本填充样式（即文字颜色）
                                                        stroke: new ol.style.Stroke({color: '#ffcc33', width: 2})
                                                    })

                                                })
                                            ];
                                        }
                                        else {
                                            style = styleCache[idIndex] = [new ol.style.Style({
                                                image: new ol.style.Circle({
                                                    radius: radius,
                                                    stroke: new ol.style.Stroke({
                                                        color: "rgba(" + color + ",0.5)",
                                                        width: 15,
                                                        lineDash: dash,
                                                        lineCap: "butt"
                                                    }),
                                                    fill: new ol.style.Fill({
                                                        color: "rgba(" + color + ",1)"
                                                    })
                                                }),
                                                text: new ol.style.Text({
                                                    text: size.toString(),
                                                    fill: new ol.style.Fill({
                                                        color: '#fff'
                                                    })
                                                })
                                            })
                                            ];
                                        }
                                    }
                                    _this[vectorArrId].setZIndex(9999);
                                    return style;
                                } else {
                                    var size = feature.getId();
                                    var style = styleCache[size];
                                    if (!style) {
                                        style = styleCache[size] = [new ol.style.Style({
                                            //图层内容颜色
                                            fill: new ol.style.Fill({
                                                color: fillColor ? fillColor : 'rgba(255, 255, 255, 0.6)'
                                            }),
                                            //图层边框颜色
                                            stroke: new ol.style.Stroke({
                                                color: color ? '#0099FF' : 'blue',
                                                width: 1
                                            }),
                                            //图层文字颜色
                                            text: new ol.style.Text({
                                                textAlign: 'center', // 位置
                                                textBaseline: 'middle', // 基准线
                                                exceedLength: 'true',
                                                font: 'normal 14px 微软雅黑',  // 文字样式
                                                text: feature.get('name'),  // 文本内容
                                                fill: new ol.style.Fill({color: '#fff'}), // 文本填充样式（即文字颜色）
                                            })
                                        }),]
                                    }
                                    return style
                                }
                            }

                        }
                    });
                    map.addLayer(this[vectorArrId]);
                }
            }
        }
        // 图层显示隐藏
        // this[type].setVisible(flag);
        if (this.selectPointerFlag) {
            _this.selectPointer();
        }
    },
    //记载数据--涉及点聚合-style中feature为整个数组
    addGIS: function (params) {
        var map = this.map;
        var _this = this;
        // 加载数据
        var vectorArr = params;
        var len = vectorArr.length;
        if (_this.overlayGISDialog) {
            _this.overlayGISDialog.setPosition(undefined);
        }

        if (len > 0) {
            for (var i = 0; i < len; i++) {
                this.center = vectorArr[i].data.centpoint;
                var vectorArrType = vectorArr[i].type;
                //图层id

                var vectorArrId = vectorArr[i].id || vectorArr[i].type;
                // 添加到矢量数据源
                var Data = vectorArr[i].data.features;
                var DataLen = Data.length;
                var vectorSource = null;
                var clusterSource = null;
                var layerVectortype = vectorSource;
                if (DataLen > 0) {
                    //字符串转化为number数据  ['137','47']=>[137,47]
                    for (var j = 0; j < DataLen; j++) {
                        if (Data[j].geometry.type == "Point") {
                            var nameNum = vectorArr[i].data.features[j].properties.nameNum;
                            var id = vectorArr[i].data.features[j].id;
                            var properties = vectorArr[i].data.features[j].properties;
                            var x = vectorArr[i].data.features[j].geometry.coordinates[0];
                            var y = vectorArr[i].data.features[j].geometry.coordinates[1];
                            if (x != '' && y != '') {
                                var coordinates = [Number(x), Number(y)];
                                _this.addGISOverlay(nameNum, coordinates, id, properties)
                            }

                        }
                    }
                }

            }
        }
    },
    //添加图文标注
    addGISOverlay: function (name, coordinate, overlayId, info) {
        var map = this.map;
        var overlay = document.getElementById('FullBody'); // 获取id为label的元素
        // 新增div元素
        var elementDiv = document.createElement('div');
        elementDiv.id = 'overlay_' + overlayId;
        var x = coordinate[0];
        var y = coordinate[1];
        overlay.appendChild(elementDiv); // 为ID为label的div层添加div子节点
        elementDiv.className = "gis-marker";
        elementDiv.onclick = function () {
            goToDetail(x, y, overlayId, info)
        };
        elementDiv.title = name;


        // 新建span标签
        var elementSpan = document.createElement("span");
        elementDiv.appendChild(elementSpan);
        elementSpan.innerHTML = name;

        this[overlayId + 'Overlay'] = new ol.Overlay({
            id: overlayId,
            position: coordinate,
            element: elementDiv,
            //offset : [ -offset, -105 ],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this[overlayId + 'Overlay']);
    },
    // 悬上图层
    selectGISPointer: function (id, info, coordinate) {
        var getid = id;
        var map = this.map;
        var _this = this;
        // 弹框

        $('#popup').show();
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');
        _this.overlayGISDialog = new ol.Overlay({
            element: container,
            offset: [10, 0],
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        if (closer) {
            closer.onclick = function () {
                _this.overlayGISDialog.setPosition(undefined);
                closer.blur();
                return false;
            };
        }

        map.on('click', function (event) {
            if (closer) {
                // 关闭弹框
                closer.click();
            }
        });
        map.addOverlay(_this.overlayGISDialog);
        var html = '<table>';
        /*			html += '<tr>';
			html += '<td><strong>名称：</strong></td>';
			html += '<td  style="color:#eea807">' + (info.name||info.info['楼栋名称']) + '</td>';
			html += '</tr>';*/
        var infoData = info.info;
        var isplace = 0;
        var popflag = 0;
        var placeflag = 0;
        for (var i in infoData) {
            if (i == '住所楼栋区域') {
                continue
            } else if (i == '住所楼栋id') {
                continue
            } else if (i == '住所楼栋id') {
                continue
            } else if (i == '场所图片') {
                continue
            }
            if (i == '场所类型') {
                html += '<tr>';
                html += '<td><strong>' + i + '：</strong></td>';
                html += '<td  style="color:#eea807">' + placeTypeObj[infoData[i]] || '' + '</td>';
                html += '</tr>';
                isplace = 1;
            } else if (i == '重点属性') {
                html += '<tr>';
                html += '<td><strong>' + i + '：</strong></td>';
                if (infoData[i].concat(",")) {
                    var s = "";
                    var iarray = infoData[i].split(",");
                    for (var n = 0; n < iarray.length; n++) {
                        if (iarray[n] != "" && iarray[n] != undefined) {
                            s += placeImportantObj[iarray[n]] + ",";
                        }
                    }
                    html += '<td  style="color:#eea807">' + s || '' + '</td>';
                } else {
                    html += '<td  style="color:#eea807">' + placeImportantObj[infoData[i]] || '' + '</td>';
                }
                html += '</tr>';
                placeflag = 1;
            } else if (i == '人口类型') {
                html += '<tr>';
                html += '<td><strong>' + i + '：</strong></td>';
                html += '<td  style="color:#eea807">' + pTypeObj[infoData[i]] || '' + '</td>';
                html += '</tr>';
                popflag = 1;
            } else if (i == '楼栋长电话') {
                html += '<tr>';
                html += '<td><strong>' + i + '：</strong></td>';
                html += '<td  style="color:#eea807">' + infoData[i] || '' + '</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td><strong>楼栋住户信息：</strong></td>';
                html += '<td><a class="bulidclick btn btn-success" onclick="$(\'#popup-closer\').click();" href="###" featureId="'
                    + id
                    + '"  elemNum="'
                    + info.info['单元数']
                    + '" pilesNum="'
                    + info.info['层数']
                    + '" buildName="'
                    + info.info['楼栋名称'] + '">详情</a></td>';
                html += '</tr>'
            } else if (i == '事件ID'){
            	html += '<tr>';
                html += '<td><strong>事件详情：</strong></td>';
                html += '<td>' + '<button type="button" onclick="parent.LayerDialog(ctx+\'/event/ccmEventIncident/form/?id=' + infoData[i] + '\', \'详情\', \'1200px\', \'800px\')" class="btn btn-mini btn-success">详细</button>' +'</td>';
                html += '</tr>';
            }else {
                html += '<tr>';
                if(i != '单元数' && i != '层数'){
                    html += '<td><strong>' + i + '：</strong></td>';
                }
                if(i == '住所楼栋名称'){
                    html += '<td ><a class="bulidclick" style="color:eea807" onclick="$(\'#popup-closer\').click();" href="###" featureId="'
                                + info.info['住所楼栋id']
                                + '"  elemNum="'
                                + info.info['单元数']
                                + '" pilesNum="'
                                + info.info['层数']
                                + '" buildName="'
                                + info.info['楼栋名称'] + '">' + infoData[i] + '</a></td>';
                } else {
                    if(i != '单元数' && i != '层数'){
                        html += '<td  style="color:#eea807">' + infoData[i] + '</td>';
                    }
                }
                html += '</tr>';
            }

        }
        if (isplace == 1) {
            html += '<tr>';
            html += '<td><strong>事件信息：</strong></td>';
            html += '<td  style="color:#eea807"><button type="button" onclick="getpaceinfo(\'' + getid + '\',' + coordinate + ' )" class="btn btn-mini btn-success">列表</button></td>';
            html += '</tr>';
        }
        html += '</table>';
        if (content) {
            content.innerHTML = html;
        }
        _this.overlayGISDialog.setPosition(coordinate);

    },
    //记载数据--不涉及点聚合-style中feature为数组中某一个值
    addJSON2: function (params) {
        var map = this.map;
        var colorArr = this.colorArr;
        var _this = this;
        // 加载数据
        var vectorArr = params;
        var len = vectorArr.length;
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                this.center = vectorArr[i].data.centpoint;
                var vectorArrType = vectorArr[i].type;
                // 添加到矢量数据源
                var Data = vectorArr[i].data.features;
                var DataLen = Data.length;
                var vectorSource = null;
                var clusterSource = null;
                var layerVectortype = vectorSource;


                vectorSource = this.vectorGeoJSON(vectorArr[i].data)
                var styleCache = {};
                var cont1 = 0;
                this[vectorArrType] = new ol.layer.Vector(
                    {
                        visible: vectorArr[i].isShow,
                        source: vectorSource,
                        renderOrder: function (feature) {
                        },
                        // 设置样式
                        style: function (feature) {
                            var index = Math.floor((Math.random() * colorArr.length));
                            var fillColor = colorArr[index].rgba;
                            var color = colorArr[index].color;
                            var iconSrc = feature.get('icon');
                            //console.log(iconSrc)
                            var coordinate = feature.get('coordinateCentre');
                            //返回样式
                            if (vectorArrType == "resultCheck") {
                                iconSrc = feature.get('info').color;//巡逻结果颜色
                            }
                            if (_this.featureStyles(iconSrc, feature)[vectorArrType]) {
                                return _this.featureStyles(iconSrc, feature)[vectorArrType];
                            } else {
                                var size = feature.getId();
                                var style = styleCache[size];
                                if (!style) {
                                    style = styleCache[size] = [_this.featureStyles(iconSrc, feature)['default']]
                                }
                                return style
                            }
                        }
                    });
                map.addLayer(this[vectorArr[i].type]);
            }
        }

        // 图层显示隐藏
        // this[type].setVisible(flag);
        if (this.selectPointerFlag) {
            _this.selectPointer();
        }

    },
    //记载数据--涉及点聚合-style中feature为整个数组
    addJSON3: function (params) {

        var map = this.map;
        var _this = this;
        // 加载数据
        var vectorArr = params;
        var len = vectorArr.length;
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                this.center = vectorArr[i].data.centpoint;
                var vectorArrType = vectorArr[i].type;
                var vectorArrId = vectorArr[i].id;
                // 添加到矢量数据源
                var Data = vectorArr[i].data.features;
                var DataLen = Data.length;
                var vectorSource = null;
                var clusterSource = null;
                var layerVectortype = vectorSource;
                if (DataLen > 0) {
                    //字符串转化为number数据  ['137','47']=>[137,47]
                    for (var j = 0; j < DataLen; j++) {
                        if (Data[j].geometry.type == "Point") {
                            vectorArr[i].data.features[j].geometry.coordinates[0] = Number(vectorArr[i].data.features[j].geometry.coordinates[0])
                            vectorArr[i].data.features[j].geometry.coordinates[1] = Number(vectorArr[i].data.features[j].geometry.coordinates[1])
                        } else if (Data[j].geometry.type == "Polygon") {
                            var PolygonLen = vectorArr[i].data.features[j].geometry.coordinates.length;
                            if (PolygonLen > 0) {
                                for (var k = 0; k < PolygonLen; k++) {
                                    var coordinates = vectorArr[i].data.features[j].geometry.coordinates[k];
                                    var coordinatesLen = coordinates.length;
                                    if (coordinatesLen > 0) {
                                        for (var m = 0; m < coordinatesLen; m++) {
                                            vectorArr[i].data.features[j].geometry.coordinates[k][m][0] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][m][0])
                                            vectorArr[i].data.features[j].geometry.coordinates[k][m][1] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][m][1])

                                        }
                                    }
                                }
                            }
                        } else if (Data[j].geometry.type == "LineString") {
                            var LineStringLen = vectorArr[i].data.features[j].geometry.coordinates.length;
                            if (LineStringLen > 0) {
                                for (var k = 0; k < LineStringLen; k++) {
                                    var coordinates = vectorArr[i].data.features[j].geometry.coordinates[k];
                                    var coordinatesLen = coordinates.length;
                                    if (coordinatesLen > 0) {
                                        vectorArr[i].data.features[j].geometry.coordinates[k][0] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][0])
                                        vectorArr[i].data.features[j].geometry.coordinates[k][1] = Number(vectorArr[i].data.features[j].geometry.coordinates[k][1])
                                    }
                                }
                            }
                        }
                    }
                }
                //数据
                vectorSource = this.vectorGeoJSON(vectorArr[i].data)
                //点聚合
                clusterSource = this.cluster(vectorSource);
                if (DataLen > 0) {
                    for (var j = 0; j < DataLen; j++) {
                        if (Data[j].geometry.type == "Point") {
                            layerVectortype = clusterSource;
                        } else if (Data[j].geometry.type == "Polygon") {
                            layerVectortype = vectorSource;
                        }
                    }
                }

                var styleCache = {};
                var cont1 = 0;
                this[vectorArrId] = new ol.layer.Vector({
                    visible: vectorArr[i].isShow,
                    source: layerVectortype,
                    // 设置样式
                    style: function (feature) {
                        //图标地址

                        var iconSrc = feature.get('icon');
                        var colorArr = _this.colorArr;
                        var index = Math.floor((Math.random() * colorArr.length));
                        var fillColor = colorArr[index].rgba;
                        var color = colorArr[index].color;
                        var coordinate = feature.get('coordinateCentre');

                        if (vectorArrType == "resultCheck") {
                            iconSrc = feature.get('info').color;//巡逻结果颜色
                        }

                        if (vectorArrType == "Shortcut") {

                            coordinate = feature.get('coordinateCentre');
                            var getID = feature.getId();
                            map.removeOverlay(_this[getID + 'Overlay']);
                            _this.addOverlayLabel3(feature.get('name'), coordinate, getID, iconSrc);
//											//_this.addOverlayLabel(len,coordinates,id)

                            /*	if(jingwushiFlag){
												map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000041Overlay'])
											}*/
                        } else if (vectorArrType == "DanDian") {

                            coordinate = feature.get('coordinateCentre');
                            var getID = feature.getId()
                            map.removeOverlay(_this[getID + 'Overlay'])
                            var src = feature.get('src');
                            _this.addOverlayLabel4(feature.get('name'), coordinate, getID, iconSrc, src)
                        }


                        //返回样式
                        if (_this.featureStyles(iconSrc, feature)[vectorArrType]) {
                            var size = feature.getId();
                            var style = styleCache[size];
                            if (!style) {
                                style = styleCache[size] = _this.featureStyles(iconSrc, feature)[vectorArrType];
                            }
                            return style
                        } else {
                            //点聚合  点feature为数组
                            var GeometryType = feature.getGeometry().getType();
                            if (GeometryType == "Point") {
                                var selectedFeatures = feature.get('features');
                                var idIndex = "";
                                selectedFeatures.map(function (feature) {
                                    idIndex += feature.getId()
                                });
                                var size = selectedFeatures.length;
                                var style = styleCache[idIndex];
                                if (!style) {
                                    var color = size > 25 ? "192,0,0" : size > 8 ? "255,128,0" : "0,128,0";
                                    var radius = Math.max(8, Math.min(size * 0.75, 20));
                                    var dash = 2 * Math.PI * radius / 6;
                                    dash = [0, dash, dash, dash, dash, dash, dash];
                                    if (size == 1) {

                                        var Src = feature.get('features')[0].get('icon');
                                        if (vectorArrType == "PopLocation") {
                                            if (feature.get('features')[0].get('info')['是否在线'] == '1') {
                                                if (feature.get('features')[0].get('info')['是否越界'] == '1') {
                                                    Src = 'p3.png'
                                                } else {
                                                    Src = 'p1.png'
                                                }

                                            } else {
                                                if (feature.get('features')[0].get('info')['是否越界'] == '1') {
                                                    Src = 'p3.png'
                                                } else {
                                                    Src = 'p2.png'
                                                }
                                            }


                                        }
                                        style = styleCache[idIndex] = [
                                            new ol.style.Style({
                                                image: new ol.style.Icon({
                                                    src: ctxStatic + '/modules/map/images/' + Src + '',
                                                    scale: map.getView().getZoom() / 15
                                                }),
//																	text: new ol.style.Text({
//												                                textAlign: 'center', // 位置
//												                                textBaseline: 'top', // 基准线
//												                                offsetY:'10',
//												                                exceedLength:'true',
//												                                font: 'normal 10px 微软雅黑',  // 文字样式
//												                                text: feature.get('features')[0].get('name'),  // 文本内容
//												                                fill: new ol.style.Fill({ color: '#aa3300' }), // 文本填充样式（即文字颜色）
//												                                stroke: new ol.style.Stroke({ color: '#ffcc33', width: 2 })
//												                            })

                                            })
                                        ];

                                    }
                                    else {
                                        style = styleCache[idIndex] = [new ol.style.Style({
                                            image: new ol.style.Circle({
                                                radius: radius,
                                                stroke: new ol.style.Stroke({
                                                    color: "rgba(" + color + ",0.5)",
                                                    width: 15,
                                                    lineDash: dash,
                                                    lineCap: "butt"
                                                }),
                                                fill: new ol.style.Fill({
                                                    color: "rgba(" + color + ",1)"
                                                })
                                            }),
                                            text: new ol.style.Text({
                                                text: size.toString(),
                                                fill: new ol.style.Fill({
                                                    color: '#fff'
                                                })
                                            })
                                        })
                                        ];
                                    }
                                }
                                return style;
                            } else {
                                var size = feature.getId();
                                var style = styleCache[size];
                                if (!style) {
                                    style = styleCache[size] = [
                                        new ol.style.Style({
                                            fill: new ol.style.Fill({
                                                color: fillColor ? fillColor : 'rgba(255, 255, 255, 0.6)'
                                            }),
                                            stroke: new ol.style.Stroke({
                                                color: color ? color : 'blue',
                                                width: 3
                                            }),
                                            text: new ol.style.Text({
                                                textAlign: 'center', // 位置
                                                textBaseline: 'middle', // 基准线
                                                exceedLength: 'true',
                                                font: 'normal 12px 微软雅黑',  // 文字样式
                                                text: feature.get('name'),  // 文本内容
                                                fill: new ol.style.Fill({color: '#fff'}), // 文本填充样式（即文字颜色）
                                            })

                                        }),
                                    ]
                                }
                                return style
                            }
                        }

                    }
                });
                map.addLayer(this[vectorArr[i].id]);
            }
        }
        // 图层显示隐藏
        // this[type].setVisible(flag);

        if (this.selectPointerFlag) {
            _this.selectPointer();
        }
    },
    //框选查询
    selectQueryInit: function () {
        var map = this.map;
        var _this = this;
        var stroke = new ol.style.Stroke({color: 'rgba(255,0,0,0.8)', width: 2});
        var fill = new ol.style.Fill({color: 'rgba(255,255,255,0.3)'});
        var image = new ol.style.Icon({src: 'Images/position.png'});
        var drawImage = new ol.style.Circle({fill: fill, stroke: stroke, radius: 3});//
        var shapeStyle = new ol.style.Style({image: image, fill: fill, stroke: stroke});//定义样式
        _this.drawStyle = new ol.style.Style({image: drawImage, fill: fill, stroke: stroke});//定义样式

        _this.draw = null;
        _this.drawSource = new ol.source.Vector({wrapX: false});
        _this.drawVector = new ol.layer.Vector({              //在地图上绘制图形的图层
            source: _this.drawSource,
            style: shapeStyle
        });
        map.addLayer(_this.drawVector);
        _this.circleCenter = null;
    },
    selectQuery: function (type) {
        var map = this.map;
        var _this = this;
        if (!type) {
            return;
        }
        //selectedFeatures.clear();
        if (_this.draw) {
            map.removeInteraction(_this.draw);
        }
        _this.drawVector.getSource().clear();
        if (type == "Box") {
            _this.draw = new ol.interaction.Draw({
                source: _this.drawSource,
                type: "Circle",
                style: _this.drawStyle,
                geometryFunction: ol.interaction.Draw.createBox()
            });
        } else {
            _this.draw = new ol.interaction.Draw({
                source: _this.drawSource,
                type: type,
                style: _this.drawStyle
            });
        }
        map.addInteraction(_this.draw);
        _this.draw.on('drawend', function (evt) {
            // $(".selectFeature").removeClass("active");
            if (type == 'Point') {
                var circleCenter = evt.feature.getGeometry().getCoordinates();
                layer.confirm('<input id="RangeMeter" type="number" min="1" value="1000" style="margin: 0; width: 168px; margin-right: 5px;"/>米', {
                    btn: ['确定', '取消'] //按钮
                }, function (index) {
                    var val = $('#RangeMeter').val();
                    _this.showCircleFromPoint(circleCenter, val)
                    layer.close(index);
                    $('.pointSelect').removeClass('active')
                }, function () {
                    $('.pointSelect').removeClass('active')

                });
            } else if (type == 'Circle') {

                var polygon = evt.feature.getGeometry();
                var center = polygon.getCenter(), radius = polygon.getRadius(), extent = polygon.getExtent();
                $('.circleSelect').removeClass('active')
                var centerX = center[0], centerY = center[1];
                var val = radius / 360 * (2 * Math.PI * 6378137.0)//将纬度转换为米
                var data = { 'radius': val, 'centerX': centerX, 'centerY': centerY}//实时监控
                if (typeof boxSelectionDevice === "function") {
                    boxSelectionDevice(data,"circle")
                }

                /* setTimeout(function(){
	                    _this.getCircleSelect(polygon);
	                },300)  */            //如果不设置延迟，范围内要素选中后自动取消选中，具体原因不知道
            } else if (type == 'Polygon' || type == 'Box') {
                var polygon = evt.feature.getGeometry();
                var coordinates = polygon.getCoordinates();
                var len = coordinates[0].length;
                var xSum = null, ySum = null, x = null, y = null;
                var xList = '', yList = '', xyList = '';
                xyList = coordinates[0].join(';')
                for (var i = 0; i < len; i++) {
                    xList += coordinates[0][i][0] + ',';
                    yList += coordinates[0][i][1] + ',';

                    xSum += Number(coordinates[0][i][0]);
                    ySum += Number(coordinates[0][i][1]);

                }
                xList = xList.substring(0, xList.length - 1);
                yList = yList.substring(0, yList.length - 1);
                x = xSum / len;
                y = ySum / len;
                var areaPointDraw = x + ',' + y;
                console.log(areaPointDraw)
                var geoStrDraw = coordinates[0].join(";");
                console.log(geoStrDraw)
                $('.polygonSelect').removeClass('active')
                var data = {'type': 2, 'xList': xList, 'yList': yList, 'xyList': xyList};//实时监控
                if (typeof boxSelectionDevice === "function") {
                    boxSelectionDevice(data)
                }

                /*   setTimeout(function(){
	                    _this.getPolygonSelect(polygon);
	                },300)*/
            }
            map.removeInteraction(_this.draw);
        })

    },
    getCircleSelect: function (polygon) {
        var center = polygon.getCenter(), radius = polygon.getRadius(), extent = polygon.getExtent();
        var features = vectorLayer.getSource().getFeaturesInExtent(extent);
        var str = "";
        for (var i = 0; i < features.length; i++) {
            var newCoords = features[i].getGeometry().getCoordinates();//coords.push(features[0].getGeometry().getCoordinates());
            if (pointInsideCircle(newCoords, center, radius)) {
                selectedFeatures.push(features[i]);
                str += "<div class=\"selectedItem\" onclick='showDeviceOnMap(\"" + features[i].getId() + "\");'>" + features[i].get("name") + "</div>";
            }
        }
        if (str == "") {
            str += "<div class='noSearchResult'>无结果</div>"
        }
        $("#selectedInfoContent").html(str);
        if ($("#selectedInfo").css("display") == 'none') {
            $("#selectedInfo").css("display", 'block');
        }
    },
    getPolygonSelect: function (polygon) {
        var coords = polygon.getCoordinates()[0], extent = polygon.getExtent();
        ;
        var features = vectorLayer.getSource().getFeaturesInExtent(extent);
        var str = "";
        for (var i = 0; i < features.length; i++) {
            var newCoords = features[i].getGeometry().getCoordinates();//coords.push(features[0].getGeometry().getCoordinates());
            if (insidePolygon(coords, newCoords)) {
                selectedFeatures.push(features[i]);
                str += "<div class=\"selectedItem\" onclick='showDeviceOnMap(\"" + features[i].getId() + "\");'>" + features[i].get("name") + "</div>";
            }
        }
        if (str == "") {
            str += "<div class='noSearchResult'>无结果</div>"
        }
        $("#selectedInfoContent").html(str);
        if ($("#selectedInfo").css("display") == 'none') {
            $("#selectedInfo").css("display", 'block');
        }
    },
    showCircleFromPoint: function (circleCenter, val) {
        var map = this.map;
        var _this = this;
        var radius = Number(val / (2 * Math.PI * 6378137.0) * 360);//将米转换为纬度
        var feature = new ol.Feature({
            geometry: new ol.geom.Circle(circleCenter, radius)
        });
        _this.drawVector.getSource().addFeature(feature);

        var circle = feature.getGeometry();
        var center = circle.getCenter(), radius = circle.getRadius(), extent = circle.getExtent();
        var centerX = center[0], centerY = center[1];
        var data = {'type': 1, 'radius': val, 'centerX': centerX, 'centerY': centerY}//实时监控
        if (typeof boxSelectionDevice === "function") {
            boxSelectionDevice(data)
        }

        /* var features = vectorLayer.getSource().getFeaturesInExtent(extent);
	        var str = "";
	        for(var i=0;i<features.length;i++){
	            var newCoords = features[i].getGeometry().getCoordinates();//coords.push(features[0].getGeometry().getCoordinates());
	            if(pointInsideCircle(newCoords,center,radius)){
	                selectedFeatures.push(features[i]);
	                str += "<div class=\"selectedItem\" onclick='showDeviceOnMap(\""+features[i].getId()+"\");'>"+features[i].get("name")+"</div>";
	            }
	        }
	        if(str==""){
	            str +="<div class='noSearchResult'>无结果</div>"
	        }
	        $("#selectedInfoContent").html(str);

	        if($("#selectedInfo").css("display")=='none'){
	            $("#selectedInfo").css("display",'block');
	        }*/
    },
    //默认周围1千米画圈
    showCircleFromPointDefult: function (circleCenter, val) {
        var map = this.map;
        var _this = this;

        var radius = Number(val / (2 * Math.PI * 6378137.0) * 360);//将米转换为纬度
        var feature = new ol.Feature({
            geometry: new ol.geom.Circle(circleCenter, radius)
        });
        var stroke = new ol.style.Stroke({color: 'rgba(255,0,0,0.8)', width: 2});
        var fill = new ol.style.Fill({color: 'rgba(255,255,255,0.3)'});
        var image = new ol.style.Icon({src: 'Images/position.png'});
        var drawImage = new ol.style.Circle({fill: fill, stroke: stroke, radius: 3});//
        var shapeStyle = new ol.style.Style({image: image, fill: fill, stroke: stroke});//定义样式
        var drawStyle = new ol.style.Style({image: drawImage, fill: fill, stroke: stroke});//定义样式

        var drawSource = new ol.source.Vector({wrapX: false});

        _this['riceDrawVector'] = new ol.layer.Vector({              //在地图上绘制图形的图层
            source: drawSource,
            style: shapeStyle
        });
        map.addLayer(_this['riceDrawVector']);
        _this['riceDrawVector'].getSource().addFeature(feature);
    },
    clearShape: function () {

        $('.shapeClear').removeClass('active');
        var map = this.map;
        var _this = this;
        _this.drawVector.getSource().clear();
        if (_this.draw) {
            map.removeInteraction(_this.draw);
        }
    },
    showDeviceOnMap: function (id) {
        var feature = vectorLayer.getSource().getFeatureById(id);
        if (feature) {
            var coords = feature.getGeometry().getCoordinates();
            map.getView().animate({
                center: coords,
                duration: 250
            })
        }
    },
    //默认周围1千米画圈--该功能需要引用p-ol3.debug.js
    showCircleFromPointDefult: function (circleCenter, val, queryFlag, id, type) {
        var map = this.map;
        var _this = this;
        //将米转换为纬度
        var radius = Number(val / (2 * Math.PI * 6378137.0) * 360);
        var feature = new ol.Feature({
            geometry: new ol.geom.Circle(circleCenter, radius)
        });
        //获取中心点
        var FirstCoordinate = feature.getGeometry().getFirstCoordinate();
        //获取顶点坐标
        var LastCoordinate = feature.getGeometry().getLastCoordinate();
        //画圆
        var P_feature = new ol.Feature({
            geometry: new P.Plot.Circle([FirstCoordinate, LastCoordinate])
        });
        //定义样式
        var stroke = new ol.style.Stroke({lineDash: [1, 2, 3, 4, 5, 6], color: 'rgba(255,0,0,1)', width: 1.5});
        var fill = new ol.style.Fill({color: 'rgba(255,255,0,0.25)'});
        var image = new ol.style.Icon({src: 'Images/position.png'});
        var drawImage = new ol.style.Circle({fill: fill, stroke: stroke, radius: 3});//
        var shapeStyle = new ol.style.Style({image: image, fill: fill, stroke: stroke});//定义样式
        var drawSource = new ol.source.Vector({wrapX: false});
        //在地图上绘制图形的图层
        _this['riceDrawVector'] = new ol.layer.Vector({
            source: drawSource,
            style: shapeStyle
        });
        map.addLayer(_this['riceDrawVector']);
        _this['riceDrawVector'].getSource().addFeature(P_feature);
        //定位到中心
        _this.goTo(circleCenter);
        //放到到15层级
        map.getView().animate({
            zoom: 15,
            duration: 250
        });

        function get(domId) {
            return document.getElementById(domId);
        }

        function activeDelBtn(This) {
            get('btn-delete').style.display = 'inline-block';
            if (This) {
                $('#btn-delete').click(function () {
                    $(This).remove();
                    deactiveDelBtn();
                })
            } else {
                This = false;
            }
        }

        function deactiveDelBtn() {
            get('btn-delete').style.display = 'none';
        }

        get('btn-delete').onclick = function () {
            _this.plotEdit.deactivate();
            deactiveDelBtn();
            $('#btn-delete').hide();
            if (queryFlag) {
                var Points = P_feature.getGeometry().getPoints();
                var center = Points[0];
                var radius = P.PlotUtils.distance(center, Points[1]);
                var x = center[0], y = center[1];
                var val = radius / 360 * (2 * Math.PI * 6378137.0)// 将纬度转换为米
                var param = {
                    'id': id,
                    'x': x,
                    'y': y,
                    'radius': val,
                    'type': type
                };
                nearSearchQuery(param)
            }
        };
        // 初始化标绘编辑工具
        _this.plotEdit = new P.PlotEdit(map);
        map.on('click', function (e) {
            var cont = 0;
            var feature = map.forEachFeatureAtPixel(e.pixel, function (feature, layer) {
                cont++;
                if (layer == _this['riceDrawVector']) {//当为周边查询图层时，继续
                    return feature;
                }
            });
            if (feature && cont == 1) {
                if (feature.get('name')) {
                    return
                }
                // 开始编辑
                _this.plotEdit.activate(feature);
                activeDelBtn();
            } else {
                // 结束编辑
                _this.plotEdit.deactivate();
                deactiveDelBtn();
            }
        });


    },
    //二次定位-方法二，点击地图选择
    SecondlocationTwo: function (id, fun) {
        var _this = this;
        var map = _this.map;
        var CacheId = '';
        //创建鼠标提示
        var helpMsg = '点击地图选取位置重新定位';
        var helpTooltipElement = document.getElementById('helpTooltipElement');
        if (helpTooltipElement) {
            helpTooltipElement.parentNode.removeChild(helpTooltipElement);
        }
        helpTooltipElement = document.createElement('div');
        helpTooltipElement.innerHTML = helpMsg;
        helpTooltipElement.id = 'helpTooltipElement'
        helpTooltipElement.className = 'helptooltip hidden';
        _this.helpTooltip = new ol.Overlay({
            element: helpTooltipElement,
            offset: [15, 0],
            positioning: 'center-left'
        });
        map.addOverlay(_this.helpTooltip);
        var pointerMoveHandler = function (evt) {
            if (evt.dragging) {
                return;
            }
            //设置帮助提示框的位置
            _this.helpTooltip.setPosition(evt.coordinate);
            //移除帮助提示框的隐藏样式进行显示
            $(helpTooltipElement).removeClass('hidden');
        };
        //地图容器绑定鼠标移动事件，动态显示帮助提示框内容
        map.on('pointermove', pointerMoveHandler);
        //地图绑定鼠标移出事件，鼠标移出时为帮助提示框设置隐藏样式
        $(map.getViewport()).on('mouseout', function () {
            $(helpTooltipElement).addClass('hidden');
        });

        _this.singleclickListener = function (event) {
            if (event.dragging) {
                return;
            }
            var point = event['coordinate'];
            var html = '';
            html += '<div style="margin:5px 10px 5px 20px;" class="secondlocationTwo">';
            html += '<div style="line-height:24px;">经度：' + point[0] + '</div>';
            html += '<div style="line-height:24px;">纬度：' + point[1] + '</div>';
            html += '<div style="line-height:24px;">确定重新定位到该位置？</div>';
            html += '</div>';
            layer.open({
                type: 1,
                title: "二次定位",
                area: ["260px", "180px"],
                maxmin: false,
                btn: ["确定", "取消"], //
                id: 'point',
                content: html,
                end: function () {
                },
                yes: function (index, layero) {
                    if (typeof fun === "function") {
                        fun(point, id);
                    }
                    layer.close(index); // 如果设定了yes回调，需进行手工关闭
                }
            })
            map.removeOverlay(_this.helpTooltip);
            map.un('click', _this.singleclickListener);
        }
        map.on('click', _this.singleclickListener);
    },
    //指挥调度--添加目标
    commandAddTarget: function (fun) {
        var _this = this;
        var map = _this.map;
        var CacheId = '';
        //创建鼠标提示
        var helpMsg = '点击地图选取目标';
        var helpTooltipElement = document.getElementById('helpTooltipElement');
        if (helpTooltipElement) {
            helpTooltipElement.parentNode.removeChild(helpTooltipElement);
        }
        helpTooltipElement = document.createElement('div');
        helpTooltipElement.innerHTML = helpMsg;
        helpTooltipElement.id = 'helpTooltipElement'
        helpTooltipElement.className = 'helptooltip hidden';
        _this.commandHelpTooltip = new ol.Overlay({
            element: helpTooltipElement,
            offset: [15, 0],
            positioning: 'center-left'
        });
        map.addOverlay(_this.commandHelpTooltip);
        var pointerMoveHandler = function (evt) {
            if (evt.dragging) {
                return;
            }
            //设置帮助提示框的位置
            _this.commandHelpTooltip.setPosition(evt.coordinate);
            //移除帮助提示框的隐藏样式进行显示
            $(helpTooltipElement).removeClass('hidden');
        };
        //地图容器绑定鼠标移动事件，动态显示帮助提示框内容
        map.on('pointermove', pointerMoveHandler);
        //地图绑定鼠标移出事件，鼠标移出时为帮助提示框设置隐藏样式
        $(map.getViewport()).on('mouseout', function () {
            $(helpTooltipElement).addClass('hidden');
        });

        _this.commandSingleclickListener = function (event) {
            if (event.dragging) {
                return;
            }
            var point = event['coordinate'];
            if (typeof fun === "function") {
                fun(point);
            }
            map.removeOverlay(_this.commandHelpTooltip);
            map.un('click', _this.commandSingleclickListener);
        }
        map.on('click', _this.commandSingleclickListener);
    },
    //测绘--测距
    measureMapInit: function () {
        var _this = this;
        var map = _this.map;
        var source = new ol.source.Vector(); //图层数据源
        _this.measureVector = new ol.layer.Vector({
            source: source,
            style: new ol.style.Style({ //图层样式
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)' //填充颜色
                }),
                stroke: new ol.style.Stroke({
                    color: '#ffcc33',  //边框颜色
                    width: 2   // 边框宽度
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: '#ffcc33'
                    })
                })
            })
        });
        map.addLayer(_this.measureVector);
    },
    measureMap: function (typeVal) {
        var _this = this;
        var map = _this.map;
        //加载测量的绘制矢量层
        var length;
        var wgs84Sphere = new ol.Sphere(6378137); //定义一个球对象
        //当前绘制的要素
        var sketch;
        //帮助提示框对象
        var helpTooltipElement;
        //帮助提示框显示的信息
        var helpTooltip;
        //测量工具提示框对象
        var measureTooltipElement;
        //测量工具中显示的测量值
        var measureTooltip;
        //当用户正在绘制多边形时的提示信息文本
        var continuePolygonMsg = '双击结束绘制面';
        //当用户正在绘制线时的提示信息文本
        var continueLineMsg = '双击击结束绘制线';

        //鼠标移动事件处理函数
        var pointerMoveHandler = function (evt) {
            if (evt.dragging) {
                return;
            }
            //当前默认提示信息
            var helpMsg = '单击开始绘制';
            //判断绘制几何类型设置相应的帮助提示信息
            if (sketch) {
                var geom = (sketch.getGeometry());
                if (geom instanceof ol.geom.Polygon) {
                    //绘制多边形时提示相应内容
                    helpMsg = continuePolygonMsg;
                } else if (geom instanceof ol.geom.LineString) {
                    //绘制线时提示相应内容
                    helpMsg = continueLineMsg;
                }
            }
            //将提示信息设置到对话框中显示
            helpTooltipElement.innerHTML = helpMsg;
            //设置帮助提示框的位置
            helpTooltip.setPosition(evt.coordinate);
            //移除帮助提示框的隐藏样式进行显示
            $(helpTooltipElement).removeClass('hidden');
        };
        //地图容器绑定鼠标移动事件，动态显示帮助提示框内容
        map.on('pointermove', pointerMoveHandler);
        //地图绑定鼠标移出事件，鼠标移出时为帮助提示框设置隐藏样式
        $(map.getViewport()).on('mouseout', function () {
            $(helpTooltipElement).addClass('hidden');
        });
        //测地学方式对象
        var geodesicCheckbox = true;

        //测量长度
        var formatLength = function (line) {
            var length;
            if (geodesicCheckbox) { //若使用测地学方法测量
                var coordinates = line.getCoordinates();//解析线的坐标
                length = 0;
                var sourceProj = map.getView().getProjection(); //地图数据源投影坐标系
                //通过遍历坐标计算两点之前距离，进而得到整条线的长度
                for (var i = 0, ii = coordinates.length - 1; i < ii; ++i) {
                    var c1 = ol.proj.transform(coordinates[i], sourceProj, 'EPSG:4326');
                    var c2 = ol.proj.transform(coordinates[i + 1], sourceProj, 'EPSG:4326');
                    length += wgs84Sphere.haversineDistance(c1, c2);
                }
            } else {
                length = Math.round(line.getLength() * 100) / 100; //直接得到线的长度
            }
            var output;
            if (length > 100) {
                output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'km'; //换算成KM单位
            } else {
                output = (Math.round(length * 100) / 100) + ' ' + 'm'; //m为单位
            }
            return output;//返回线的长度
        };

        //测量面积
        var formatArea = function (polygon) {
            var area;
            if (geodesicCheckbox) {//若使用测地学方法测量
                var sourceProj = map.getView().getProjection();//地图数据源投影坐标系
                var geom = /** @type {ol.geom.Polygon} */(polygon.clone().transform(sourceProj, 'EPSG:4326')); //将多边形要素坐标系投影为EPSG:4326
                var coordinates = geom.getLinearRing(0).getCoordinates();//解析多边形的坐标值
                area = Math.abs(wgs84Sphere.geodesicArea(coordinates)); //获取面积
            } else {
                area = polygon.getArea();//直接获取多边形的面积
            }
            var output;
            if (area > 10000) {
                output = (Math.round(area / 1000000 * 100) / 100) + ' ' + 'km<sup>2</sup>'; //换算成KM单位
            } else {
                output = (Math.round(area * 100) / 100) + ' ' + 'm<sup>2</sup>';//m为单位
            }
            return output; //返回多边形的面积
        };
        var draw;
        var type = (typeVal == 'area' ? 'Polygon' : 'LineString');
        draw = new ol.interaction.Draw({
            //测量绘制层数据源
            source: _this.measureVector.getSource(),
            //几何图形类型
            type: type,
            style: new ol.style.Style({//绘制几何图形的样式
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(0, 0, 0, 0.5)',
                    lineDash: [10, 10],
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 5,
                    stroke: new ol.style.Stroke({
                        color: 'rgba(0, 0, 0, 0.7)'
                    }),
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 255, 255, 0.2)'
                    })
                })
            })
        });
        map.addInteraction(draw);
        createMeasureTooltip(); //创建测量工具提示框
        createHelpTooltip(); //创建帮助提示框
        var listener;
        //绑定交互绘制工具开始绘制的事件
        draw.on('drawstart',
            function (evt) {
                //绘制的要素
                sketch = evt.feature;
                var tooltipCoord = evt.coordinate;// 绘制的坐标
                //绑定change事件，根据绘制几何类型得到测量长度值或面积值，并将其设置到测量工具提示框中显示
                listener = sketch.getGeometry().on('change', function (evt) {
                    var geom = evt.target;//绘制几何要素
                    var output;
                    if (geom instanceof ol.geom.Polygon) {
                        output = formatArea(geom);//面积值
                        tooltipCoord = geom.getInteriorPoint().getCoordinates();//坐标
                    } else if (geom instanceof ol.geom.LineString) {
                        output = formatLength(geom);//长度值
                        tooltipCoord = geom.getLastCoordinate();//坐标
                    }
                    measureTooltipElement.innerHTML = output;//将测量值设置到测量工具提示框中显示
                    measureTooltip.setPosition(tooltipCoord);//设置测量工具提示框的显示位置
                });
            }, this);
        //绑定交互绘制工具结束绘制的事件
        draw.on('drawend',
            function (evt) {
                measureTooltipElement.className = 'tooltip tooltip-static'; //设置测量提示框的样式
                measureTooltip.setOffset([0, -7]);
                //置空当前绘制的要素对象
                sketch = null;
                measureTooltipElement = null; //置空测量工具提示框对象
                //createMeasureTooltip();//重新创建一个测试工具提示框显示结果
                map.removeInteraction(draw);
                map.removeOverlay(helpTooltip);
                ol.Observable.unByKey(listener);
            }, this);

        //创建一个新的帮助提示框
        function createHelpTooltip() {
            if (helpTooltipElement) {
                helpTooltipElement.parentNode.removeChild(helpTooltipElement);
            }
            helpTooltipElement = document.createElement('div');
            helpTooltipElement.className = 'tooltip hidden';
            helpTooltip = new ol.Overlay({
                element: helpTooltipElement,
                offset: [15, 0],
                positioning: 'center-left'
            });
            map.addOverlay(helpTooltip);
        }

        //创建一个新的测量工具提示框
        function createMeasureTooltip() {
            if (measureTooltipElement) {
                measureTooltipElement.parentNode.removeChild(measureTooltipElement);
            }
            measureTooltipElement = document.createElement('div');
            measureTooltipElement.className = 'tooltip tooltip-measure';
            measureTooltip = new ol.Overlay({
                element: measureTooltipElement,
                offset: [0, -15],
                positioning: 'bottom-center'
            });
            map.addOverlay(measureTooltip);
        }
    },
    //测绘工具清除
    measureMapClear: function () {
        var map = this.map;
        var _this = this;
        map.getOverlays().getArray().map(function (i, val) {
            if (i.getId() != 'info') {
                i.setPosition(undefined);
            }
        })
        _this.measureVector.getSource().clear();
    },
    /**
     *  判断一个点是否在多边形内部
     *  @param points 多边形坐标集合
     *  @param testPoint 测试点坐标
     *  返回true为真，false为假
     *  */
    insidePolygon: function (points, testPoint) {
        var x = testPoint[0], y = testPoint[1];
        var inside = false;
        for (var i = 0, j = points.length - 1; i < points.length; j = i++) {
            var xi = points[i][0], yi = points[i][1];
            var xj = points[j][0], yj = points[j][1];

            var intersect = ((yi > y) != (yj > y))
                && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            if (intersect) inside = !inside;
        }
        return inside;
    },
    /**
     *  判断一个点是否在圆的内部
     *  @param point  测试点坐标
     *  @param circle 圆心坐标
     *  @param r 圆半径
     *  返回true为真，false为假
     *  */
    pointInsideCircle: function (point, circle, r) {
        if (r === 0) return false
        var dx = circle[0] - point[0]
        var dy = circle[1] - point[1]
        return dx * dx + dy * dy <= r * r
    },
}


/**
 * 概括加载地图，地图联动，使用同一个view
 */
//新密
/*var viewSituation
(function viewSituationFun(){
	var zoom=16.7,centerCoordinate= [ 113.3555613562, 34.5413170808 ];
	windowsHeight = $(window).width();
	if (windowsHeight>=1680&&windowsHeight<1920) {
		zoom=14.7;
		centerCoordinate= [ 113.3595613562, 34.5373170808  ]
	} else if(windowsHeight>=1600&&windowsHeight<1680){
		zoom=14.4;
		centerCoordinate= [ 113.3595613562, 34.5373170808  ]
	}else if(windowsHeight>=1440&&windowsHeight<1660){
		zoom=14.4;
		centerCoordinate= [ 113.3595613562, 34.5373170808  ]
	}else if(windowsHeight<1440) {
		zoom=16.2;
		centerCoordinate= [ 113.3555613562, 34.5413170808  ]
	}

	viewSituation = new ol.View({
			center : centerCoordinate,
			zoom : zoom,
			maxZoom : 19,
			minZoom : 9.8,
			projection : 'EPSG:4326'
		});
})()*/
//杭州道
/*var viewSituation
(function viewSituationFun(){
	var zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ];
	windowsHeight = $(window).width();
	if (windowsHeight>=1680&&windowsHeight<1920) {
		zoom=14.7;
		centerCoordinate= [ 117.662920, 39.035650 ]
	} else if(windowsHeight>=1600&&windowsHeight<1680){
		zoom=14.4;
		centerCoordinate= [ 117.662920, 39.035650 ]
	}else if(windowsHeight>=1440&&windowsHeight<1660){
		zoom=14.4;
		centerCoordinate= [ 117.662920, 39.035650 ]
	}else if(windowsHeight<1440) {
		zoom=14.1;
		centerCoordinate= [ 117.662920, 39.035650 ]
	}

	viewSituation = new ol.View({
			center : centerCoordinate,
			zoom : zoom,
			maxZoom : 18,
			minZoom : 9.8,
			projection : 'EPSG:4326'
		});
})()*/
//津南
/*(function viewSituationFun(){
	var zoom=14,centerCoordinate= [ 117.29900836944581,39.03772830963135];
	windowsHeight = $(window).width();

	viewSituation = new ol.View({
			center : centerCoordinate,
			zoom : zoom,
			maxZoom : 18,
			minZoom : 9.8,
			projection : 'EPSG:4326'
		});
})()*/
/*ArjMap.Map.prototype.viewSituation=function(){
	return  new ol.View({
		center : this.center,//中心位置
		zoom : this.zoom,//当前层级
		minZoom : this.minZoom,//最小层级
		maxZoom : this.maxZoom,//最大层级
		projection : 'EPSG:4326'
	});
}*/
//杭州道
var viewSituation

function viewSituationFun(centerCoordinate, zoom) {
    viewSituation = new ol.View({
        center: centerCoordinate,
        zoom: zoom,
        maxZoom: maxZoom,    //18
        minZoom: minZoom,    //9.8
        projection: 'EPSG:4326'
    });
}

ArjMap.Map.prototype.drawMapSituation = function () {
    var baseUrl = this.baseUrl;
    var urlArr = this.urlArr;
    var layers = [];
    var len = baseUrl.length;
    if (len > 0) {
        for (var i = 0; i < len; i++) {
            if (baseUrl[i].type == '1') {
                this[baseUrl[i].id + 'Tile'] = new ol.layer.Tile({
                    title: "地图",
                    opacity: 1,
                    name: baseUrl[i].name,
                    visible: baseUrl[i].isShow,
                    source: new ol.source.XYZ({
                        tileGrid: new ol.tilegrid.TileGrid({
                            tileSize: baseUrl[i].tileSize,
                            // 原点
                            origin: baseUrl[i].origin,
                            resolutions: baseUrl[i].resolutions

                        }),
                        url: baseUrl[i].url,
                        projection: 'EPSG:4326'
                        // 天地图
                    })
                });
            } else if (baseUrl[i].type == '2') {
                this[baseUrl[i].id + 'Tile'] = new ol.layer.Tile({
                    title: "地图",
                    name: baseUrl[i].name,
                    visible: baseUrl[i].isShow,
                    source: new ol.source.XYZ({
                        url: baseUrl[i].url,
                        // 天地图
                    })
                });
            } else if (baseUrl[i].type == '3') {
                this[baseUrl[i].id + 'Tile'] = new ol.layer.Tile({
                    title: "地图",
                    name: baseUrl[i].name,
                    visible: baseUrl[i].isShow,
                    source: new ol.source.TileArcGISRest({
                        url: baseUrl[i].url,
                        // 天地图
                    })
                });

            }
            layers.push(this[baseUrl[i].id + 'Tile'])
        }
    }
    this.map = new ol.Map({
        layers: layers,
        view: viewSituation,
        target: this.id,
        controls: ol.control.defaults({
            zoom: this.zoomShowOrHide,
            attribution: this.showOLpage
        })
    });

    this.map.addInteraction(new ol.interaction.Select({
        condition: ol.events.condition.pointerMove, // 唯一的不同之处，设置鼠标移到feature上就选取
        style: new ol.style.Style({
            image: new ol.style.Circle({
                radius: 10,
                fill: new ol.style.Fill({
                    color: '#0099ff'
                })
            }),
            fill: new ol.style.Fill({
                color: 'rgba(255, 255, 255, 0.7)'
            }),
            stroke: new ol.style.Stroke({
                color: '#0099ff',
                width: 2
            }),
        })
    }));
}
ArjMap.Map.prototype.drawMapSituationKeShiHua = function () {
    var layers = [];
    var baseUrl = this.baseUrl;
    this['KeShiHuaTile'] = new ol.layer.Tile({
        title: "地图",
        name: "可视化地图",
        visible: true,
        source: new ol.source.TileArcGISRest({
             // url: baseUrl[i].url
             // url:"http://192.168.1.106:6080/arcgis/rest/services/quliang/MapServer",//曲梁
             // url:"http://192.168.1.106:6080/arcgis/rest/services/xinmiblack/MapServer"//新密
             // url:"http://192.168.1.250:6080/arcgis/rest/services/sanya6/MapServer"//三亚
                url: baseUrl[2].url
        }),
    });
    layers.push(this['KeShiHuaTile'])
    this.map = new ol.Map({
        layers: layers,
        view: viewSituation,
        target: this.id,
        controls: ol.control.defaults({
            zoom: this.zoomShowOrHide,
            attribution: this.showOLpage
        }),
        interactions: ol.interaction.defaults({
            doubleClickZoom: false
        }),
    });

    // var mapvLayer = new mapv.OpenlayersLayer(options);

    this.map.addInteraction(new ol.interaction.Select({
        condition: ol.events.condition.pointerMove, // 唯一的不同之处，设置鼠标移到feature上就选取
        style: new ol.style.Style({
            image: new ol.style.Circle({
                radius: 10,
                fill: new ol.style.Fill({
                    color: '#0099ff'
                })
            }),
            fill: new ol.style.Fill({
                color: 'rgba(255, 255, 255, 0.7)'
            }),
            stroke: new ol.style.Stroke({
                color: '#0099ff',
                width: 2
            }),
        })
    }));

    //鼠标悬停显示数据
    this.map.on('pointermove', function(evt) {
        if (evt.dragging) {
            return;
        }
        displayFeatureInfo(evt);
    });

    //鼠标单击事件
    this.map.on("singleclick", function (evt) {
        if (evt.dragging) {
            return;
        }
        displaysingleclickInfo(evt);
    });


}

//鼠标单击事件显示区域内容
var displaysingleclickInfo = function (evt) {

    var pixel = this.Map.map.getEventPixel(evt.originalEvent);
    var feature = this.Map.map.forEachFeatureAtPixel(pixel, function (feature) {
        return feature;
    });
    if(feature){
        // Map.clearOverlays();
        var restype = feature.get('type');
        var jsontype = null;
        debugger;
        if(restype == "4"){
            type = 7;
            jsontype = 'communitys';
            Map.layersIsShow('streets', false);
            Map.layersIsShow('grids', false);
            // Map.removeLayer('streets');
            // Map.removeLayer('grids');
        } else if(restype == "5"){
            type = 8;
            jsontype = 'grids';
            Map.layersIsShow('communitys', false);
            Map.layersIsShow('streets', false);
            // Map.removeLayer('communitys');
            // Map.removeLayer('streets');
        } else if(restype == "6"){
            type = 9;
            jsontype = 'grids';
            // Map.removeLayer('grids');
            Map.layersIsShow('communitys', false);
            Map.layersIsShow('streets', false);
            Map.layersIsShow('grids', false);
        }
        var info = feature.get('info');
        if(typeof (info['id1'])!='undefined' && info['id1']!="null"){
            var id = info['id1'];
            $.getJSON('' + ctx + '/sys/map/orgAreaMap?type='+ type +'&ids='+ id , function(data) {
                Map.addJSON1([{
                    'type' : jsontype,
                    'data' : data,
                    'isShow' : true
                }])
               // 转换数据。。。。。
               /* var vectorArr = data;
                var len = vectorArr.features.length;
                var resarr = [];
                var n = 0;
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        var arrcoo = vectorArr.features[i].geometry.coordinates;
                        if (arrcoo.length > 0) {
                            for (var k = 0; k < arrcoo.length; k++) {
                                var coordinates = vectorArr.features[i].geometry.coordinates[k];
                                var coordinatesLen = coordinates.length;
                                if (coordinatesLen > 0) {
                                    for (var m = 0; m < coordinatesLen; m++) {
                                        vectorArr.features[i].geometry.coordinates[k][m][0] = Number(vectorArr.features[i].geometry.coordinates[k][m][0])
                                        vectorArr.features[i].geometry.coordinates[k][m][1] = Number(vectorArr.features[i].geometry.coordinates[k][m][1])

                                        resarr[n++] = Number(vectorArr.features[i].geometry.coordinates[k][m][0]);
                                        resarr[n++] = Number(vectorArr.features[i].geometry.coordinates[k][m][1]);

                                    }
                                }
                            }
                        }
                    }
                }
                resttfeatures = (new ol.format.GeoJSON()).readFeatures(vectorArr);*/

                var polygon = feature.getGeometry();
                viewSituation.fit(polygon, { padding: [150, 150, 30, 150],constrainResolution: false});

            });
        }
    }


}



//鼠标悬停显示网格社区街道数据
var displayFeatureInfo = function (evt) {
    var pixel = this.Map.map.getEventPixel(evt.originalEvent);
    var feature = this.Map.map.forEachFeatureAtPixel(pixel, function (feature) { return feature; });//查询方式有很多
    if (feature) {
        $('#popup').show();
        var mousePosition = feature.get("coordinateCentre");
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');
        var overlayDialog  = new ol.Overlay({
            element: container,
            autoPan: true,
            autoPanAnimation: {
                duration: 250
            },
        });
        if (closer) {
            closer.onclick = function () {
                overlayDialog.setPosition(undefined);
                closer.blur();
                $('#detailsDialog').html('');
                $('#detailsDialog').hide();
                return false;
            };
        }
        var names = "";
        if(feature.get('name')){
            names = feature.get('name')
        }
        var info = feature.get('info');
        if (names.length > 0 && names[0] != undefined) {
            var html = '<table>';
            html += '<tr>';
            html += '<td><strong>名称：</strong></td>';
            html += '<td>' + names+ '</td>';
            html += '</tr>'
            html += '<tr>';
            html += '<td><strong>管理员姓名：</strong></td>';
            html += '<td>' + (typeof (info['管理员姓名'])!='undefined' && info['管理员姓名']!="null" ? info['管理员姓名'] : "暂无数据") + '</td>';
            html += '</tr>'
            html += '<tr>';
            html += '<td><strong>手机号码：</strong></td>';
            html += '<td>' + (typeof (info['手机号码'])!='undefined' && info['手机号码']!="null" ? info['手机号码'] : "暂无数据") + '</td>';
            html += '</tr>'
            html += '<tr>';
            html += '<td><strong >户数：</strong></td>';
            html += '<td>' + (typeof (info['户数'])!='undefined' && info['户数']!="null" ? info['户数'] : "暂无数据") + '</td>';
            html += '</tr>'
            html += '<tr>';
            html += '<td><strong>人口：</strong></td>';
            html += '<td>' + (typeof (info['人口'])!='undefined' && info['人口']!="null" ? info['人口'] : "暂无数据") + '</td>';
            html += '</tr>'
            html += '<tr>';
            html += '<td><strong>重点人员：</strong></td>';
            html += '<td>' + (typeof (info['重点人员'])!='undefined' && info['重点人员']!="null" ? info['重点人员'] : "暂无数据") + '</td>';
            html += '</tr>'
            html += '<tr>';
            html += '<td><strong>工作人员数量：</strong></td>';
            html += '<td>' + (typeof (info['工作人员数量'])!='undefined' && info['工作人员数量']!="null" ? info['工作人员数量'] : "暂无数据") + '</td>';
            html += '</tr>'
            html += '</table>'
            if (content) {
                content.innerHTML = html;
            }
            overlayDialog.setPosition(mousePosition);// 弹框位置---鼠标位置
            this.Map.map.addOverlay(overlayDialog);
        }
    } else {
        $('#popup').hide();
    }
};



//楼栋
var viewSituationloudong
(function viewSituationFun() {
    var zoom = 18.1, centerCoordinate = [113.3543613562, 34.5421370808];
    windowsHeight = $(window).width();
    if (windowsHeight >= 1680 && windowsHeight < 1920) {
        zoom = 14.7;
        centerCoordinate = [113.3595613562, 34.5373170808]
    } else if (windowsHeight >= 1600 && windowsHeight < 1680) {
        zoom = 14.4;
        centerCoordinate = [113.3595613562, 34.5373170808]
    } else if (windowsHeight >= 1440 && windowsHeight < 1660) {
        zoom = 14.4;
        centerCoordinate = [113.3595613562, 34.5373170808]
    } else if (windowsHeight < 1440) {
        zoom = 16.2;
        centerCoordinate = [113.3555613562, 34.5413170808]
    }

    viewSituationloudong = new ol.View({
        center: centerCoordinate,
        zoom: zoom,
        maxZoom: 19,
        minZoom: 9.8,
        projection: 'EPSG:4326'
    });
})()

ArjMap.Map.prototype.drawMapSituationLoudong = function () {

    var baseUrl = this.baseUrl;
    var urlArr = this.urlArr;

    var layers = [];
    var len = baseUrl.length;
    if (len > 0) {
        for (var i = 0; i < len; i++) {
            if (baseUrl[i].type == '1') {
                this[baseUrl[i].id + 'Tile'] = new ol.layer.Tile({
                    title: "地图",
                    opacity: 1,
                    name: baseUrl[i].name,
                    visible: baseUrl[i].isShow,
                    source: new ol.source.XYZ({
                        tileGrid: new ol.tilegrid.TileGrid({
                            tileSize: baseUrl[i].tileSize,
                            // 原点
                            origin: baseUrl[i].origin,
                            resolutions: baseUrl[i].resolutions

                        }),
                        url: baseUrl[i].url,
                        projection: 'EPSG:4326'
                        // 天地图
                    })
                });
            } else if (baseUrl[i].type == '2') {
                this[baseUrl[i].id + 'Tile'] = new ol.layer.Tile({
                    title: "地图",
                    name: baseUrl[i].name,
                    visible: baseUrl[i].isShow,
                    source: new ol.source.XYZ({
                        url: baseUrl[i].url,
                        // 天地图
                    })
                });

            } else if (baseUrl[i].type == '3') {
                this[baseUrl[i].id + 'Tile'] = new ol.layer.Tile({
                    title: "地图",
                    name: baseUrl[i].name,
                    visible: baseUrl[i].isShow,
                    source: new ol.source.TileArcGISRest({
                        url: baseUrl[i].url,
                        // 天地图
                    })
                });

            }

            layers.push(this[baseUrl[i].id + 'Tile'])
        }
    }
    this.map = new ol.Map({
        layers: layers,
        view: viewSituation,
        target: this.id,
        controls: ol.control.defaults({
            zoom: this.zoomShowOrHide,
            attribution: this.showOLpage
        }),
        interactions: ol.interaction.defaults({
            doubleClickZoom: false
        }),
    });
    if (urlArr && urlArr.length > 0) {
        var len = urlArr.length;
        for (var i = 0; i < len; i++) {
            this.map.addLayer(new ol.layer.Tile({
                name: urlArr[i].name,
                visible: urlArr[i].isShow,
                source: new ol.source.TileArcGISRest({
                    url: urlArr[i].url,
                })
            }))
        }
    }

    this.map.addInteraction(new ol.interaction.Select({
        condition: ol.events.condition.pointerMove, // 唯一的不同之处，设置鼠标移到feature上就选取
        style: new ol.style.Style({
            image: new ol.style.Circle({
                radius: 10,
                fill: new ol.style.Fill({
                    color: '#0099ff'
                })
            }),
            fill: new ol.style.Fill({
                color: 'rgba(255, 255, 255, 0.7)'
            }),
            stroke: new ol.style.Stroke({
                color: '#0099ff',
                width: 2
            }),
        })
    }));

    var map = this.map;
    var _this = this;
    var container = document.getElementById('popupDialog');

    map.getOverlays().clear();
    var overlayDialog1 = new ol.Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlayDialog1)

    var highlightStyle = new ol.style.Style({
        stroke: new ol.style.Stroke({
            color: '#f00',
            width: 1
        }),
        fill: new ol.style.Fill({
            color: 'rgba(255,0,0,0.1)'
        }),
        text: new ol.style.Text({
            font: '12px Calibri,sans-serif',

            fill: new ol.style.Fill({
                color: 'red'
            }),
            stroke: new ol.style.Stroke({
                color: 'red',
                width: 1
            })
        })
    });

    var featureOverlay = new ol.layer.Vector({
        source: new ol.source.Vector(),
        map: map,
        style: function (feature) {
            highlightStyle.getText().setText(feature.get('name'));
            return highlightStyle;
        }
    });

    var highlight;
    var displayFeatureInfo = function (pixel) {

        var feature = map.forEachFeatureAtPixel(pixel, function (feature) {
            return feature;
        });

        var info = document.getElementById('popupDialogName');
        if (feature) {
            info.innerHTML = feature.getId() + ': ' + feature.get('name');
        } else {
            info.innerHTML = '&nbsp;';
        }

        if (feature !== highlight) {
            if (highlight) {
                featureOverlay.getSource().removeFeature(highlight);
            }
            if (feature) {
                featureOverlay.getSource().addFeature(feature);
            }
            highlight = feature;
        }

    };

    map.on('pointermove', function (evt) {
        if (evt.dragging) {
            return;
        }
        var pixel = map.getEventPixel(evt.originalEvent);
        displayFeatureInfo(pixel);
    });

    map.on('click', function (evt) {
        displayFeatureInfo(evt.pixel);
    });


    var sshiyoub = 8136;
    var schsngzhub = 8014;
    var schliudongb = 122;
    var schzhongdianb = 34;
    var sloudongb = 36;
    var sdanyuanb = 69;
    var sfangwub = 1524;
    var sfujingb = 4;

    var selectPointerMove = new ol.interaction.Select({
        condition: ol.events.condition.pointerMove,
    });
    map.addInteraction(selectPointerMove);
    selectPointerMove.on('select', function (e) {
        if (e.selected.length > 0) {
            selectedFeatures(selectPointerMove)
        } else if (e.deselected.length > 0) {
            $('.dialog5').hide()
        }
    });

    function selectedFeatures(selectType) {
        var selectedFeatures = selectType.getFeatures();
        selectedFeatures
            .on(
                ['add', 'remove'],
                function (event) {
                    var info = null;
                    var video = null;
                    var type = null;
                    var featureId = null;
                    var featureId1 = null;
                    var videoUrl = null;
                    var popInfo = null;
                    var featureName = null;
                    var PopLocationId = null;
                    var mousePosition1 = null;

                    var names = selectedFeatures.getArray().map(function (feature) {
                        mousePosition1 = feature.get('coordinateCentre')
                        return feature.get('name')
                    });
                    // name不等于事件的Featurename
                    if (names.length > 0) {
                        var loudong = Math.floor(Math.random() * 10 + 2)
                        var zhongdian = Math.floor(Math.random() * 10)
                        var renkou = Math.floor((Math.random() + 1) * 200 + 1)


                        sshiyoub = renkou;
                        schsngzhub = renkou - zhongdian;
                        schliudongb = zhongdian;
                        schzhongdianb = zhongdian;
                        sloudongb = 1;
                        sdanyuanb = 2;
                        sfangwub = parseInt(renkou / 4);
                        ;
                        sfujingb = 1;


//								var html='';
//								html+=' <div class="xinmichengqu" style="width:150px">';
//								html+=' <div class="paichusuo">';
//								html+='<table style="width:100%;height:100%">';
//								html+='<tbody><tr>';
//								html+='<td align="left" style="color:red;font-size:16px;font-weight: bold;">'+names+'</td>';
//								html+=' </tr>';
//								html+='<tr>';
//								html+=' <td align="left">人口数量：</td>';
//								html+='<td align="left"><span style="color:red">'+renkou+'</span></td>';
//								html+=' </tr>';
//								html+=' <tr>';
//								html+=' <td align="left">重点人员：</td>';
//								html+='<td align="left"><span style="color:red">'+zhongdian+'</span></td>';
//								html+=' </tr>';
//								html+=' </tbody></table>';
//								html+='</div>';
//								html+='</div>';

                        var html = '';

                        html += ' <div  class="dialog5" style="background:#fff">';
                        html += ' <div class="dialog4-header">';

                        html += '<span class=""><img src="/arjccm/static/common/index/images/pop/paichusuo.png" alt=""></span>';
                        html += '<b class="dialog4-header-name" style="padding:3px 5px">' + names + '</b>';
                        html += '</div>';
                        html += ' <div class="dialog4-center" style="height:60px">';
                        html += '<table>';
                        html += '<tr class="dialog4-center-tr"> ';
                        html += '<td  align="left" class="dialog4-center-name">人口数量：</td>';
                        html += '<td  align="left"><span >' + renkou + '</span></td>';
                        html += ' </tr>';
                        html += '<tr class="dialog4-center-tr"> ';
                        html += '<td  align="left" class="dialog4-center-name">重点人员：</td>';
                        html += '<td  align="left"><span >' + zhongdian + '</span></td>';
                        html += ' </tr>';
                        html += '</table>';
                        html += '</div>';
                        html += '<div class="dialog4-bottom"></div>';
                        html += '</div>';

                        if (container) {
                            container.innerHTML = html;
                        }

                        // overlay.setZIndex(99999)
                        overlayDialog1.setPosition(mousePosition1);// 弹框位置---鼠标位置
                        $('.dialog5').show()
                    }
                });

    }


    map.on('click', function (event) {


        $('#eachAll13').html(sshiyoub);
        $('#eachAll23').html(schsngzhub);
        $('#eachAll33').html(schliudongb);
        $('#eachAll43').html(schzhongdianb);
        $('#eachAll53').html(sloudongb);
        $('#eachAll63').html(sdanyuanb);
        $('#eachAll73').html(sfangwub);
        $('#eachAll83').html(sfujingb);
    })


}


$(function () {

    $("#myHouse")
        .on(
            "click",
            ".popshowclick",
            function (e) {
                e.preventDefault();
                var popshow = JSON.parse($(this).attr('popshow'));// 重点人员
                var type = $(this).attr('type');// 重点人员类型
                // var popshow = JSON.parse(pop2);
                var da = popshow["birthday"];
                da = new Date(da);
                var year = da.getFullYear() + '年';
                var month = da.getMonth() + 1 + '月';
                var date = da.getDate() + '日';
                var uploadPath = popshow["images"];
                // var uploadPath =
                // "/arjccm/userfiles/1/images/photo/2018/02/%E5%AE%89%E9%98%B2.png";
                // console.log(popshow);
                var html2 = '<table class="table table-striped table-bordered table-condensed">';
                html2 += '名称：' + type;
                html2 += '<tr>';
                html2 += '<td>姓名</td>';
                html2 += '<td colspan="3">' + popshow["name"] + '</td>';
                // html2 += '<td rowspan="4">图片</td>';
                html2 += '<td rowspan="4" style="width:20%"><img  style="width:100%" src="'
                    + uploadPath + ' "/></td>';
                html2 += '</tr>';
                html2 += '<tr>';
                html2 += '<td style="width:15%">性别</td>';
                html2 += '<td style="width:25%">' + popshow["sex"]
                    + '</td>';
                html2 += '<td style="width:15%">民族</td>';
                html2 += '<td style="width:25%">' + popshow["nation"]
                    + '</td>';
                html2 += '</tr>';
                html2 += '<tr>';
                html2 += '<td>出生</td>';
                html2 += '<td colspan="3">' + year + month + date
                    + '</td>';
                html2 += '</tr>';
                html2 += '<tr>';
                html2 += '<td>住址</td>';
                html2 += '<td colspan="3">'
                    + popshow["residencedetail"] + '</td>';
                html2 += '</tr>';
                html2 += '<tr>';
                html2 += '<td colspan="2">公民身份证号码</td>';
                html2 += '<td colspan="3">' + popshow["ident"]
                    + '</td>';
                html2 += '</tr>';
                html2 += '</table>';

                $("#pop-details").html(html2);
                $('#popBtn').click();

            })
})
function doSelect(videoList){
    var videoSelectNode = $("<select id='videoSelect'></select>");
        for (var i = 0; i < videoList.length; i++) {
            videoSelectNode.append("<option value='" + videoList[i].id + "' >" + videoList[i].name + "</option>");
        }

    $("#videoSelectTd").append(videoSelectNode);
}
// 根据id查询  ccm_base_place   ad         	在查询事件
function getpaceinfo(id, coordinate) {
    $.post(ctx + '/event/ccmEventIncident/geteventList', {"placeId": id}, function (data) {
        if (data == null || data == "" || data == undefined) {
            $.jBox.tip('暂无数据！');
            return;
        }
        var html = '';
        html += '<table id="contentTable" class="table table-striped table-bordered table-condensed">';
        html += '<thead>';
        html += '<tr>';
        html += '<th>事件名称</th>';
        html += '<th>发生日期</th>';
        html += '<th>事件分级</th>';
        html += '<th>事件类型</th>';
        html += '<th>详情</th>'
        html += '</tr>';
        html += '</thead>';
        html += '<tbody>';

        var eventScale,eventType;
        for (var i in data) {
            var id = data[i].id;
            html += '<tr>';

            eventScale = data[i].eventScale;
            eventType = data[i].eventType;

            if(eventScale == '01') {
                eventScale = '重特大'
            } else if(eventScale == '02') {
                eventScale = '重大'
            } else if(eventScale == '03') {
                eventScale = '较大'
            } else if(eventScale == '04'){
                eventScale = '一般'
            } else {
                eventScale = '未知'
            }

            if(eventType == '01') {
                eventType = '安全事故'
            } else if(eventType == '02') {
                eventType = '群体性事故'
            } else if(eventType == '03') {
                eventType = '食品安全事故'
            } else if(eventType == '04') {
                eventType = '有关刑事案件'
            } else if(eventType == '05') {
                eventType = '其他'
            } else {
                eventType = '未知'
            }

            html += '<td>' + data[i].caseName + '</td>';
            html += '<td>' + data[i].happenDate + '</td>';
            html += '<td>' + eventScale + '</td>';
            html += '<td>' + eventType + '</td>';
            html += '<td>' + '<button type="button" onclick="parent.LayerDialog(ctx+\'/event/ccmEventIncident/form/?id='+id+'\', \'详情\', \'1200px\', \'800px\')" class="btn btn-mini btn-success">详细</button>' +'</td>';
            html += '</tr>';
        }
        html += '</tbody>';
        html += '</table>';

        layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["600px", "400px"],
            offset: ['260px', '810px'],
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            id: 'contentTable11',
            content: html,
            cancel: function () {
                // 关闭事件
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
                // icon:6});
            }
        });

    })
}
var mapvLayer = [];
var mapvSource = [];
function lightLine() {
    $.get(ctxStatic+'/modules/map/data/quliang-street', function (rs) {
        var data = [];
        var timeData = [];
        // var rs = "113.588333129883,34.515043759993397,113.641891479492,34.522964309203999,113.641891479492,34.522964309203999,113.60481262207,34.486749937679299,113.60481262207,34.486749937679299,113.615112304688,34.542762387234802,113.615112304688,34.542762387234802,113.629531860352,34.490711650881003,113.629531860352,34.490711650881003,113.591766357422,34.5144779776628";
        var data = [];
        var timeData = [];

        rs = rs.split("\n");
        var maxLength = 0;
        for (var i = 0; i < rs.length; i++) {
            var item = rs[i].split(',');
            var coordinates = [];
            if (item.length > maxLength) {
                maxLength = item.length;
            }
            for (j = 0; j < item.length; j += 2) {
                coordinates.push([item[j], item[j + 1]]);
            }
            data.push({
                geometry: {
                    type: 'LineString',
                    coordinates: coordinates
                }
            });

        }


        var dataSet = new mapv.DataSet(data);

        var mapvOptions = {
            strokeStyle: 'rgba(53,57,255,0.5)',
            // coordType: 'bd09mc',
            globalCompositeOperation: 'lighter',
            shadowColor: 'rgba(53,57,255,0.2)',
            shadowBlur: 3,
            lineWidth: 3.0,
            draw: 'simple'
        }

        // var mapvOptions = {
        //     strokeStyle: 'rgba(50, 50, 255, 0.5)',
        //     lineWidth: 20,
        //     globalCompositeOperation: 'lighter',
        //     draw: 'simple'
        // }

        var options = {
            map: self.Map.map,
            dataSet: dataSet,
            mapvOptions: mapvOptions
        };

        self.Map.map.addLayer(new ol.layer.Image({
            source: new ol.source.Mapv(options)
        }));
    });

}
var mapvOptionsBlue =  {
    fillStyle: 'rgba(50, 50, 255, 0.6)',
    shadowColor: 'rgba(50, 50, 255, 1)',
    shadowBlur: 30,
    globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsRed =  {
    fillStyle: 'rgba(255, 50, 50, 0.6)',
    shadowColor: 'rgba(255, 50, 50, 1)',
    shadowBlur: 30,
    globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsBlueGreen = {
    fillStyle: 'rgba(0,245,222, 0.6)',
    shadowColor: 'rgba(0,245,222, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsYellow = {
    fillStyle: 'rgba(255, 255, 0, 0.6)',
    shadowColor: 'rgba(255, 255, 0, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsPink = {
    fillStyle: 'rgba(255, 146, 149, 0.6)',
    shadowColor: 'rgba(255, 146, 149, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsFlesh = {
    fillStyle: 'rgba(255, 241, 193, 0.6)',
    shadowColor: 'rgba(255, 241, 193, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsDarkBlue = {
    fillStyle: 'rgba(110, 176, 253, 0.6)',
    shadowColor: 'rgba(110, 176, 253, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsPurple = {
    fillStyle: 'rgba(127, 52, 252, 0.6)',
    shadowColor: 'rgba(127, 52, 252, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
// other: 'rgba(255, 255, 0, 0.8)',
//     1: 'rgba(253, 98, 104, 0.8)',
//     2: 'rgba(255, 146, 149, 0.8)',
//     3: 'rgba(255, 241, 193, 0.8)',
//     4: 'rgba(110, 176, 253, 0.8)',
//     5: 'rgba(52, 139, 251, 0.8)',
//     6: 'rgba(17, 102, 252, 0.8)'
function simplePointMoreColor(data,mapvOptions) {

    var dataSet = new mapv.DataSet(data);

    // var mapvOptions = mapvOptionsTest;
    var options = {
        map: this.Map.map,
        dataSet: dataSet,
        mapvOptions: mapvOptions
    };
    var source = new ol.source.Mapv(options);
    var layer = new ol.layer.Image({
        source: source
    })
    this.Map.map.addLayer(layer);
    mapvLayer.push(layer);
    mapvSource.push(source);

}
function simplePoint(data,methods) {
    // var randomCount = 300;
    // var data = [];
    // var abc = [113.5646400000,34.5108900000];
    // 构造数据
    // while (randomCount--) {
    //     data.push({
    //         geometry: {
    //             type: 'Point',
    //             // coordinates: [cityCenter.lng - 2 + Math.random() * 4, cityCenter.lat - 2 + Math.random() * 4]
    //             coordinates:[abc[0] + Math.random() * 0.04, abc[1]+ Math.random() * 0.04]
    //         }
    //         // ,
    //         // count: 30 * Math.random()
    //     });
    // }
    var dataSet = new mapv.DataSet(data);

    var mapvOptions = {
        fillStyle: 'rgba(0,245,222, 0.6)',
        shadowColor: 'rgba(0,245,222, 1)',
        shadowBlur: 30,
        // globalCompositeOperation: 'lighter',
        methods: methods,
        size: 5,
        draw: 'simple'
    }

    var options = {
        map: this.Map.map,
        dataSet: dataSet,
        mapvOptions: mapvOptions
    };
    var source = new ol.source.Mapv(options)
    var layer = new ol.layer.Image({
        source: source
    })
    this.Map.map.addLayer(layer);
    mapvSource.push(source);
    mapvLayer.push(layer);
}
function removeMapvList() {
    for(var i=0;i<mapvSource.length;i++){
        mapvSource[i].clearData();
    }
    for(var i=0;i<mapvLayer.length;i++){
        this.Map.map.removeLayer(mapvLayer[i]);
    }


}
//按颜色进行分类
function categoryPoint(data) {
    // var randomCount = 300;
    // var data = [];
    // var abc = [113.5646400000,34.5108900000];
    // 构造数据
    // while (randomCount--) {
    //     data.push({
    //         geometry: {
    //             type: 'Point',
    //             // coordinates: [cityCenter.lng - 2 + Math.random() * 4, cityCenter.lat - 2 + Math.random() * 4]
    //             coordinates:[abc[0] + Math.random() * 0.04, abc[1]+ Math.random() * 0.04]
    //         }
    //         // ,
    //         // count: 30 * Math.random()
    //     });
    // }

    var dataSet = new mapv.DataSet(data);

    var mapvOptions = {
        // fillStyle: 'rgba(175,245,222, 0.6)',
        // shadowColor: 'rgba(175,245,222, 1)',
        // shadowBlur: 30,
        // globalCompositeOperation: 'lighter',
        methods: {
            click: function (item) {
                console.log(item);
            }
        },
        size: 5,
        draw: 'category',
        splitList: {
            other: 'rgba(255, 255, 0, 0.8)',
            1: 'rgba(253, 98, 104, 0.8)',
            2: 'rgba(255, 146, 149, 0.8)',
            3: 'rgba(255, 241, 193, 0.8)',
            4: 'rgba(110, 176, 253, 0.8)',
            5: 'rgba(52, 139, 251, 0.8)',
            6: 'rgba(17, 102, 252)'
        }
    }
    var options = {
        map: this.Map.map,
        dataSet: dataSet,
        mapvOptions: mapvOptions
    };
    this.Map.map.addLayer(new ol.layer.Image({
        source: new ol.source.Mapv(options)
    }));

}
function fengchao(){
    var data = [
        // 点数据
        {
            geometry: {
                type: 'Point',
                coordinates: [113.5946400000,34.5148900000]
            },
            fillStyle: 'red',
            size: 30
        },
        {
            geometry: {
                type: 'Point',
                coordinates: [113.5946400000,34.5148900000]
            },
            fillStyle: 'rgba(255, 255, 50, 0.5)',
            size: 90
        },
        // 线数据
        {
            geometry: {
                type: 'LineString',
                coordinates: [
                    [113.5946400000,34.5148900000],
                    [113.8946400000,34.8148900000]
                ]
            },
            count: 30
        },
        // 面数据
        {
            geometry: {
                type: 'Polygon',
                coordinates: [
                    [
                        [113.5946400000,34.5148900000],
                        [113.5946400000,34.5148900000],
                        [113.5946400000,34.5148900000]
                    ]
                ]
            },
            count: 30 * Math.random()
        }
    ];

    var dataSet = new mapv.DataSet(data);
    var mapvOptions = {
        fillStyle: 'rgba(55, 50, 250, 0.8)',
        shadowColor: 'rgba(255, 250, 50, 1)',
        shadowBlur: 20,
        max: 100,
        size: 50,
        label: {
            show: true,
            fillStyle: 'white'
        },
        globalAlpha: 0.5,
        gradient: { 0.25: "rgb(0,0,255)", 0.55: "rgb(0,255,0)", 0.85: "yellow", 1.0: "rgb(255,0,0)" },
        draw: 'honeycomb'
    };
    var options = {
        map: this.Map.map,
        dataSet: dataSet,
        mapvOptions: mapvOptions
    };
    this.Map.map.addLayer(new ol.layer.Image({
        source: new ol.source.Mapv(options)
    }));
}


/*$.getJSON(ctx+'/sys/map/queryDeviceMap?name='+videoName+',function(data){
	if(data==null||data==""||data==undefined){
    	 $.jBox.tip('暂无数据！');
         return ;
     }
	var html='';
	for(var i in data){
		var id=featuresData[i].id;
		var x='',y='';
		if(featuresData[i].geometry.coordinates){
			 x=featuresData[i].geometry.coordinates[0];
			 y=featuresData[i].geometry.coordinates[1];
		}
		var properties=featuresData[i].properties;
		html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
		html+='<div class="map_list_data" data-id="'+id+'">';
		html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
		html+='<div class="col-right"><img src="'+ctxStatic+'/images/gisVideo.png" style="width:auto;height:58px;"></div>';
		html+='<div class="col-center" style="margin-right: 50px;">';
		html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.name+'</span></div>';
		if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
			html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔坐标错误〕</span></div>';
		}
		html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['IP地址']+'</div>';
		html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['安装位置']+'</div>';
		html+='</div>';
		html+='</div>';
		html+='</li>';
	}
	$('#datalist').html(html);
	

})
*/


















