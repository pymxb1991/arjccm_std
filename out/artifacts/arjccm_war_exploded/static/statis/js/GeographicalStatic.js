var searchService, map, markers, datas = [], li;
// 去除LOGO
$(function() {
  $("#container").bind("DOMNodeInserted", function(e) {
    var tempCount = 0;
    $("#container .smnoprint").siblings().each(function() {
      tempCount++;
      if (tempCount == 2 || tempCount == 3) {
        $(this).remove();
      }
    });
  });
});

// 静态路径
var ctxStatic = $("#ctxStatic").attr("ctx");
var ctx = $("#ctx").attr("ctx");

var height = document.documentElement.clientHeight
    || document.body.clientHeight;
height = height - 223;
var container = document.getElementById('mapMask');
// container.style.cssText = "height: " + height + "px";
// 直接加载地图
var Map = qq.maps.Map;
var Marker = qq.maps.Marker;
var LatLng = qq.maps.LatLng;
var Event = qq.maps.event;

var MarkerImage = qq.maps.MarkerImage;
var MarkerShape = qq.maps.MarkerShape;
var MarkerAnimation = qq.maps.MarkerAnimation;
var Point = qq.maps.Point;
var Size = qq.maps.Size;
var ALIGN = qq.maps.ALIGN;

var MVCArray = qq.maps.MVCArray;
var MarkerCluster = qq.maps.MarkerCluster;
var Cluster = qq.maps.Cluster;
var MarkerDecoration = qq.maps.MarkerDecoration;
var latlngBounds = new qq.maps.LatLngBounds();
var path2 = [ new qq.maps.LatLng(39.04998064041138, 117.64484167098999),
    new qq.maps.LatLng(39.04543161392212, 117.64106512069702),
    new qq.maps.LatLng(39.02714967727661, 117.63432741165161),
    new qq.maps.LatLng(39.02127027511597, 117.66084909439087),
    new qq.maps.LatLng(39.02161359786987, 117.66217947006226),
    new qq.maps.LatLng(39.02195692062378, 117.6631236076355),
    new qq.maps.LatLng(39.022557735443115, 117.66419649124146),
    new qq.maps.LatLng(39.02324438095093, 117.66475439071655),
    new qq.maps.LatLng(39.024059772491455, 117.66526937484741),
    new qq.maps.LatLng(39.0250039100647, 117.66556978225708),
    // new qq.maps.LatLng(39.02813673019409,117.66900300979614),
    // new qq.maps.LatLng(39.02684926986694,117.66565561294556),
    new qq.maps.LatLng(39.02624845504761, 117.66835927963257),
    new qq.maps.LatLng(39.02624845504761, 117.66835927963257),
    new qq.maps.LatLng(39.03264284133911, 117.6746678352356),
    new qq.maps.LatLng(39.04135465621948, 117.67767190933228),
    new qq.maps.LatLng(39.04998064041138, 117.64484167098999), ];
// var path1=[new qq.maps.LatLng([json1])];


var sw = new qq.maps.LatLng(39.12513313586905, 117.8049373626709);// 东北角坐标
var ne = new qq.maps.LatLng(38.85314396371424, 117.41475105285645);// 西南角坐标
// 图像范围
latlngBounds = new qq.maps.LatLngBounds(ne, sw);

// 新建一个ImageMapType，实现ImageMapTypeOptions规范
var earthlayer = new qq.maps.ImageMapType({
  name : '智慧党建',
  alt : '智慧党建',
  tileSize : new qq.maps.Size(256, 256),
  minZoom : 13,
  maxZoom : 16,
  getTileUrl : function(tile, zoom) {
    var normalizedCoord = getNormalizedCoord(tile, zoom);
    if (!normalizedCoord) {
      return null;
    }
    var z = zoom, x = tile.x, y = tile.y;

    // return 'http://3gimg.qq.com/tencentMapTouch/lbs/demoimg/tile_' + z + '_'
    // + normalizedCoord.x + '-' + normalizedCoord.y + '.png'
    return ctxStatic+'/statis/tiles/' + z + '/' + normalizedCoord.x + '/' + normalizedCoord.y
        + '.jpg'
  }
});

// 自定义图标
var anchor = new qq.maps.Point(6, 6), size = new qq.maps.Size(24, 34), origin = new qq.maps.Point(
    0, 0), icon = new qq.maps.MarkerImage(ctxStatic + '/wechat/img/dw.png',
    size, origin, anchor);
// icon = new qq.maps.MarkerImage('dw.png', size, origin, anchor);

// 定义map变量 调用 qq.maps.Map() 构造函数 获取地图显示容器
var map = new qq.maps.Map(document.getElementById("mapMask"), {
  center : new qq.maps.LatLng(39.034450, 117.648920), // 地图的中心地理坐标。
  zoom : 14, // 地图的中心地理坐标。
  boundary : latlngBounds,
  zoomControl : true,
  zoomControlOptions : {
    // 设置缩放控件的位置为相对左方中间位置对齐.
    position : qq.maps.ControlPosition.LEFT_TOP,
    // 设置缩放控件样式为仅包含放大缩小两个按钮
    style : qq.maps.ZoomControlStyle.SMALL
  }
});

// 使用地图信息加载
map.mapTypes.set('earth', earthlayer);
map.setMapTypeId('earth');

// 地图画区域
var polygon = new qq.maps.Polygon({
  path : path2,
  strokeColor : new qq.maps.Color(253, 168, 61, 0.8),
  strokeWeight : 3,
  fillColor : qq.maps.Color.fromHex("#e9493b", 0.2),
  map : map
});

// 自定义撒点
var markers = new MVCArray();
var markerCluster;
/*
 * li = $('#mui-table-view li'); for (i = 0; i < li.length; i++) { var data =
 * li.eq(i).attr('data-degree'); datas.push(data) }
 */

function createCluster(datas) {
  var infoWin = new qq.maps.InfoWindow({
    map : map
  });
  // console.log(datas)
  // 循环标点
  // console.log(datas);
  for (var i = 0; i < datas.length; i++) {
    var latLng = new LatLng(datas[i]["slatitude"], datas[i]["slongitude"]);
    var decoration = new MarkerDecoration(i, new Point(0, -5));
    // 自定义文字
    var text = datas[i]["officeName"];
    var officeid = datas[i]["sprimarykey01"];
    // 去除空格
    text = text.replace(/\s/g, "");
    var content = text.split('：');
    var marker = new qq.maps.Marker({
      icon : icon,
      position : latLng,
      map : map,
      text : text,
      officeid : officeid,
      sdepartname : datas[i]["sdepartname"],
      peoplesum : datas[i]["peoplesum"]
    });

    var label = new qq.maps.Label({
      position : latLng,
      map : map,
      content : content[0],
      Style : {
        marginLeft : "15px"
      }
    });
    markers.push(marker);
    // 腾讯地图 标点
  }

  markerClusterer = new MarkerCluster({
    map : map,
    minimumClusterSize : 2, // 默认2
    markers : markers,
    zoomOnClick : true, // 默认为true
    gridSize : 30, // 默认60
    averageCenter : true, // 默认false
    maxZoom : 18, // 默认18
  });

  // 添加到提示窗
  var info = new qq.maps.InfoWindow({
    map : map,
    width : 60
  });

  info.setOptions({
    map : map,
    zIndex : 100
  });

  Event.addListener(markerClusterer, 'clusterclick', function(evt) {
    // writeLog("mouse event", eventType);
    var ss = evt;
    // alert('点击了聚合点');
  });

  // 标注点击事件
  // console.log(markers)
  for (var i = 0; i < datas.length; i++) {
    Event
        .addListener(
            markers.elems[i],
            'click',
            function(evt) {
              var thatPosition = this.position;
              $.post(
                      ctx + "/map/pbsGeographical/toMapOfficeBody?id="
                          + evt["target"]["officeid"],
                      function(data) {
                        info.open();
//                        console.log(data);
                        var pbsDepartmentetcs = data["pbsDepartmentetcs"];
                        var mems = data["memList"];
                        var html = "";
                        html += '<img src="' + ctxStatic
                            + '/wechat/images/index1.png">';
                        // html += "<div
                        // style='height:100%;text-align:left;background:#FF8A65;'>";
                        html += '<table id="contentTable" style="max-width: 500px;" class="table table-striped table-bordered table-condensed">'
                        /** 插入部门信息 */
                        html += '<tr><td class="tdtitle">部门信息：</td><td>'
                            + evt["target"]["text"] + '</td></tr>'

                        html += '<tr><td class="tdtitle">部门信息：</td><td>'
                            + evt["target"]["sdepartname"] + '</td></tr>'

                        html += '<tr><td class="tdtitle">部门人数：</td><td>'
                            + evt["target"]["peoplesum"] + '</td></tr>'

                        html += '<tr><td class="tdtitle">部门负责人：</td><td>'
                            + pbsDepartmentetcs["mastername"] + '</td></tr>'

                        for (var i = 0; i < mems.length; i++) {
                          if (mems[i]["id"] != pbsDepartmentetcs["smasterid"]) {
                            html += '<tr><td class="tdtitle">部门学员：</td><td>'
                                + mems[i]["sname"] + '</td></tr>'
                          }
                        }
                        html += '</table>';
                        html += "</div>";
                        info.setContent(html);
                        info.setPosition(thatPosition);
                      });

            });
  }

};

$.post(ctx + "/map/pbsGeographical/toMapBody", function(data) {
  createCluster(data);
})

// / / 调用Poi检索类 s
searchService = new qq.maps.SearchService({
  complete : function(results) {
    var pois = results.detail.pois;
    for (var i = 0, l = pois.length; i < l; i++) {
      var poi = pois[i];
      latlngBounds.extend(poi.latLng);
      var marker = new qq.maps.Marker({
        icon : icon,
        icon : icon,
        map : map,
        zoom : 14,
        position : poi.latLng
      });
      marker.setTitle(i + 1);
      markers.push(marker);
    }
    map.fitBounds(latlngBounds);
  }
});
// / / 实时路况图层 /
// / var layer = new qq.maps.TrafficLayer(); /
// / layer.setMap(map);

// / / 调用初始化函数地图 /
// / 按关键字进行查询 f
function searchKeyword() {
  var keyword = document.getElementById("keyword").value;
  var region = document.getElementById("region").value;
  clearOverlay(markers);
  searchService.setLocation(region);
  searchService.search(keyword);
}
// / / 清除地图上的marker /
/*
 * * function clearOverlays(overlays) { var overlay; while(overlay =
 * overlays.pop()) { overlay.setMap(null); } } /
 */
// / / 清除覆盖层 f
function clearOverlay() {
  if (markers.elems) {
    for (i in markers.elems) {
      markers.elems[i].setMap(null);
    }
  }
}

function getNormalizedCoord(coord, zoom) {
  var y = coord.y;
  var x = coord.x;
  var tileRange = 1 << zoom;
  if (y < 0 || y >= tileRange) {
      return null;
  }
  if (x < 0 || x >= tileRange) {
      x = (x % tileRange + tileRange) % tileRange;
  }
  return {
      x: x,
      y: y
  };
}

function degree(obj) {
  // / / clearOverlay();
  // / clearOverlays(markers);
  // / / var index = li.index(obj);
  // / map.zoomTo(map.getZoom(12))
  // / / latlngBounds.extend(markers.elems[index].position);
  // / / markers.elems[index].setMap(map);
  // / / map.fitBounds(latlngBounds);
  window.location.href = "maps-z.html"
}