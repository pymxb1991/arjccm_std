$(function(){
	 $('#primaryPersonName').attr('placeholder','请选择人员')
	  $('.tab-title li').click(function(){
		  Tab(this)
	  })
	  $('#mapView-p').click(function() {
			$('#mapKey').animate({'opacity': 0}, 'fast', function() {
				$(this).hide();
				$('#showMapKey').show().animate({width: 40, height: 32}, 50);
				$('#mapLend').show().animate({left: 14,}, 50);
			});
		});
	  $('#showMapKey').click(function() {
			$('#showMapKey').animate({width: 0, height: 0}, 50, function() {
				$(this).hide();
				$('#mapLend').show().animate({'left': 380}, 'fast');
				$('#mapKey').show().animate({'opacity': 1}, 'fast');
			});
		});
	  /* 工具栏 */
		$('.tag-panl-span').click(function() {
			$('.tag-panl-span').removeClass('active');
			$(this).addClass('active');
		})
		$('.tag-panl-close').click(function() {
			$('.tag-panl').hide();
			$('.tag-panl-span').removeClass('active')
		})
		$('#DrawFlag').click(function() {
			$('.tag-panl').hide();
			$('.tag-panl-span').removeClass('active')
			$('#toolDetailBox').toggle('fast');
			$(this).toggleClass("active");
		})
		
})
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
//框选查询
function boxSelectionDevice(data){
	$.getJSON(ctx+'/sys/map/showSelect?points1='+data.xyList,function(val){
		var html = "";
		html += '<div class="layer-common" style="width: 92.3%;height: 81.3%; position: relative;padding: 14px 0 0 0;">'
		html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
		html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">框选查询</div>'
		html += '</div>'
		html += '<div class="layer-show  layer-common-center" style="padding: 15px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: url('
				+ ctxStatic
				+ '/common/index/images/showbg.png);background-size: 100% 100%;">'
		html += '<table style="width:100%;height:100%">';
		html+='<tr>';
        html+='<td>框选区域总人口数：</td>';
		html+='<td>'+val.sumPeople+'</td>';
		html+='</tr>';
		html+='<tr>';
        html+='<td>重点人员数量：</td>';
		html+='<td>'+val.pNum+'</td>';
		html+='</tr>';
		html+='<tr>';
        html+='<td>学校数量：</td>';
		html+='<td>'+val.sumSchool+'</td>';
		html+='</tr>';
		html+='<tr>';
        html+='<td>警务工作站数量：</td>';
		html+='<td>'+val.sumPoliceRoom+'</td>';
		html+='</tr>';
		html+='<tr>';
        html+='<td>工作人员数量：</td>';
		html+='<td>'+val.sumPolice+'</td>';
		html+='</tr>';
		html += '</table>';
		html += '</div>'
		html += '</div>'
		layer.open({
			type : 1,
			shade : false,
			title : false, // 不显示标题
			area : [ "300", "200px" ],
			move : '.layer-common-header',
			resize : false,
			fixed : false,
			id : "showSelect",
			content : html,
			cancel : function() {
				Map.clearShape();
				// 关闭事件

			}
		});
		
	})
}