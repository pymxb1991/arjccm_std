/**
 * Echarts Reuse components, expand yourself, but don't change the original
 * method. Thx. -v1.0.0 2018年1月22日 14:45:58
 */
$(function() {
	/**
	 * @see 多表视图数据填充
	 * @param datatitle 控制显示选项
	 * @param dataX x轴数据
	 * @param dataY y轴数据
	 * @param model 模型对象
	 * @param mainContent 图表标题
	 * @param rotate 角度
	 */
	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme');
    var lineStyleColor="#f1f1f1"
	var lineStyleColor1="#c1c1c1"
	var FontColor1="#62c9cb"
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
		lineStyleColor = '#397ba1';
		lineStyleColor1="#397ba1"
	}
	$.myChartPeople = function(datatitle, dataX, dataY, model, mainContent,
			rotate,optionToContent) {
		// 指定图表的配置项和数据
		var option = {
		    backgroundColor:backgroundColor,
			grid : {
				right : "3%",
				left : "5%"
			},
			title : {
				text : mainContent,
				textStyle : {
					color : FontColor,
				}
			},
			tooltip : {
				
			},
			legend : {
				data : datatitle,
				textStyle : {
					color : FontColor,
				}
			},
			toolbox: {
		        show: true,
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            },
		            dataView: {readOnly: false},
		            magicType: {type: ['line', 'bar']},
		            restore: {},
		            saveAsImage: {},
		        },
		        iconStyle:{
                      color:FontColor1,//设置颜色
                    }
		    },
			xAxis : {

				axisLabel : {
					interval : 0,
					rotate : rotate,
					textStyle : {
						color : FontColor,
					}
				},
				splitLine:{
					lineStyle:{
						color: lineStyleColor
					}
				},
				axisLine:{
					lineStyle:{
						color: lineStyleColor1
					}
				},
				data : dataX
			},
			yAxis : {
				axisLabel : {
					textStyle : {
						color : FontColor,
					}
				},
				splitLine:{
					lineStyle:{
						color: lineStyleColor
					}
				},
				axisLine:{
					lineStyle:{
						color: lineStyleColor1
					}
				},
			},
			series : dataY,
			optionToContent:optionToContent
		};
		model.setOption(option);
	}

	// 补充Table数据-单表
	$.TableListPeople = function(echList, data, Ylength) {
		// 拼接 table内容
		var tableContent = "";
		// 获取 按月类型值
		for (var i = 0; i < Ylength.length; i++) {
			// 获取每一个值
			tableContent += "<tr><td>" + IsNull(Ylength[i]) + "</td><td>"
					+ IsNull(data[0][i]) + "</td><td>" + IsNull(data[1][i])
					+ "</td></tr>";
		}
		// 添加内容 到页面
		$("#" + echList + " .body").html(tableContent);

		// 添加BootstrapTable应用
//		$('.table.table-striped').bootstrapTable({
//			height : 250
//		});
	}

	// 判断是否为空
	function IsNull(variable1) {
		if (variable1 !== null && variable1 != undefined && variable1 != '') {
			return variable1;
		} else {
			return "0";
		}
	}

	/**
	 * @Param titleS
	 * @Param ajaxDataYALLS
	 */
	$.getSeriesDate = function(titleS, ajaxDataYALLS) {
		// 返回数据
		var seriesDate1 = new Array();
		// for 循环生成数据
		for (var titleNumer = 0; titleNumer < titleS.length; titleNumer++) {
			seriesDate1.push({
				"name" : titleS[titleNumer],
				"type" : 'bar',
				"data" : ajaxDataYALLS[titleNumer]
			})
		}
		// 返回数据
		return seriesDate1;
	}

	/**
	 * @Param data 返回数据
	 * @Param titleS 本月户籍新增人数
	 * @Param XYtype type_value
	 * 
	 */
	$.getyAxisDate = function(data,titleS,XYtype) {
		// 表1数据-本月户籍新增人数 group area
		var ajaxDataYALL1 = new Array();
		for(var number=0;number<titleS.length;number++){
			var dataA = data[titleS[number]];
			var  ajaxDataY1 = new Array();
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataY1.push(item["value"]);
			}
			ajaxDataYALL1.push(ajaxDataY1);
		}
		return ajaxDataYALL1;
	}
	  
	/**
	 * @Param data 数据
	 * @Param titleS 标题
	 */
	$.getxAxisDate = function(data,titleS) {
		// 表1数据-本月户籍新增人数 group area
			var  ajaxDataY1 = new Array();
			var dataA = data[titleS[0]];
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataY1.push(item["type"]);
			}
		return ajaxDataY1;
	}

});