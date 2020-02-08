/**
 * Echarts Reuse components, expand yourself, but don't change the original
 * method. Thx. -v1.0.0 2018年1月22日 14:45:58
 */
$(function() {
  /**
   * @see 多表视图数据填充
   * @param datatitle
   *            控制显示选项
   * @param dataX
   *            x轴数据
   * @param dataY
   *            y轴数据
   * @param model
   *            模型对象
   * @param mainContent
   *            图表标题
   * @param rotate
   *            角度
   */
	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme');
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
  $.myChartPeople = function(DATATITLE, DATAX, DATAY, MODEL, MAINCONTENT,
      ROTATE) {
    // console.log(dataX)
    // 指定图表的配置项和数据
    var option = {
    		backgroundColor:backgroundColor,
      grid : {
        right : "3%",
        left : "5%"
      },
      title : {
        text : MAINCONTENT,
		textStyle : {
			color : FontColor,
		}
      },
      tooltip : {},
      legend : {
        data : DATATITLE,
		textStyle : {
			color : FontColor,
		}
      },
      toolbox : {
        show : true,
        feature : {
          dataZoom : {
            yAxisIndex : 'none'
          },
          dataView : {
            readOnly : false
          },
          magicType : {
            type : [ 'line', 'bar' ]
          },
          restore : {},
          saveAsImage : {}
        }
      },
      xAxis : {
        axisLabel : {
          interval : 0,
          rotate : ROTATE,
			textStyle : {
				color : FontColor,
			}
        },
        data : DATAX
      },
      yAxis : {
    	  axisLabel : {
				textStyle : {
					color : FontColor,
				}
			},
      },
      series : DATAY
    };
    MODEL.setOption(option);
  }

  // 补充Table数据-单表
  $.TableListPeople = function(echList, data, Ylength) {
    // 拼接 table内容
    var tableContent = "";
    // 获取 按月类型值
    for (var i = 0; i < Ylength.length; i++) {
      // 获取每一个值
      tableContent += "<tr><td>" + IsNull(Ylength[i]) + "</td><td>"
          + IsNull(data[0][i]) + "</td>" /*
                                           * + IsNull(data[1][i]) + "</td></tr>"
                                           */+ "</tr>";
    }
    // 添加内容 到页面
    $("#" + echList + " .body").html(tableContent);

    // 添加BootstrapTable应用
    // $('.table.table-striped').bootstrapTable({
    // height : 250
    // });
  }

  // 判断是否为空
  function IsNull(variable1) {
    if (variable1 !== null && variable1 != undefined && variable1 !== '') {
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
    // console.log("---------");
    // console.log(seriesDate1);
    return seriesDate1;
  }

  /**
   * @Param data
   *            返回数据
   * @Param titleS
   * @Param XYtype
   *            type_value
   * 
   */
  $.getyAxisDate = function(data, titleS, XYtype) {
    // 表1数据-本月户籍新增人数 group area
    var ajaxDataYALL1 = new Array();
    for (var number = 0; number < titleS.length; number++) {
      var dataA = data[titleS[number]];
      var ajaxDataY1 = new Array();
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
   * @Param titleS  标题
   */
  $.getxAxisDate = function(data, titleS) {
    // 表1数据-本月户籍新增人数 group area
    var ajaxDataY1 = new Array();
    var dataA = data[titleS[0]];
    for ( var one in dataA) {
      var item = dataA[one];
      ajaxDataY1.push(item["name"]);
    }
    return ajaxDataY1;
  }

  /**
   * @see  
   * @param myChart1  html 对象 
   * @param title1 标题
   * @param ajaxDataX1 x轴数据 
   * @param ajaxDataYALL1 y轴数据 
   * @param seriesType  图表类型 折线 or 柱状
   */
  $.myChartSigle = function(myChart1, title1, ajaxDataX1, ajaxDataYALL1,seriesType) {
    option = {
    		backgroundColor:backgroundColor,
      title : {
        text : title1,
        subtext : ' '
      },
      tooltip : {
        trigger : 'axis'
      },
      legend : {
        data : title1
      },
      toolbox : {
        show : true,
        feature : {
          mark : {
            show : true
          },
          dataView : {
            show : true,
            readOnly : false
          },
          magicType : {
            show : true,
            type : [ 'line', 'bar', ]
          },
          restore : {
            show : true
          },
          saveAsImage : {
            show : true
          }
        }
      },
      calculable : true,
      xAxis : [ {
        type : 'category',
        data : ajaxDataX1
      } ],
      yAxis : [ {
        type : 'value'
      } ],
      series : [ {
        name : title1,
        type : isEmpty(seriesType)?'bar':seriesType,
        data : ajaxDataYALL1[0],
        barWidth: '40%',
        label: {
				normal: {
					show: true,
					formatter: '{c}',
					position: 'insideTop',
					color: '#fff'
				}
			},
        markPoint : {
          data : [ {
            type : 'max',
            name : '最大值'
          }, {
            type : 'min',
            name : '最小值'
          } ]
        },
        markLine : {
          data : [ {
            type : 'average',
            name : '平均值'
          } ]
        }
      } ]
    };
    
    myChart1.setOption(option);
  }
   
  
  // 饼状图(在线考试信息的详情)
  $.myChartSiglePie2 = function(myChart1, title1, ajaxDataX1, data) {
    
    option = {
    		backgroundColor:backgroundColor,
     color : ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d'],
      title : {
        text : title1,
        // subtext: '纯属虚构',
        x : 'center',
        y:'top'	
      },
      // tooltip: {
      // trigger: 'item',
      // formatter: "{a} <br/>{b}: {c} ({d}%)"
      // },
      legend : {
        orient : 'vertical',
        x : 'right',
        data : ajaxDataX1
      },
      series : [ {
        name : title1,
        type : 'pie',
        radius : [ '48%', '65%' ],
        label : {
          normal : {
            formatter : '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}',
            backgroundColor : '#eee',
            borderColor : '#aaa',
            borderWidth : 1,
            borderRadius : 4,
            rich : {
              a : {
                color : '#999',
                lineHeight : 18,
                align : 'center'
              },
              hr : {
                borderColor : '#aaa',
                width : '100%',
                borderWidth : 0.5,
                height : 0
              },
              b : {
                fontSize :12,
                lineHeight : 38
              },
              per : {
                color : '#eee',
                backgroundColor : '#334455',
                padding : [ 2, 4 ],
                borderRadius : 2
              }
            }
      
          },
          labelLine: {
				normal: {
				show: true,
                  smooth: 0.2,
                  length: 20,
                  length2:15
				}
			},
          emphasis : {
            show : true,
            textStyle : {
              fontSize : '20',
              fontWeight : 'bold'
            }
          }
        },
        data : data[title1]
      } ]
    };
    myChart1.setOption(option);
  }

  
  
  
  // 饼状图
  $.myChartSiglePie = function(myChart1, title1, ajaxDataX1, data) {
    console.log(data);
    option = {
    		backgroundColor:backgroundColor,
      title : {
        text : title1,
        // subtext: '纯属虚构',
        x : 'center'
      },
      // tooltip: {
      // trigger: 'item',
      // formatter: "{a} <br/>{b}: {c} ({d}%)"
      // },
      legend : {
        orient : 'vertical',
        x : 'right',
        data : ajaxDataX1
      },
      series : [ {
        name : title1,
        type : 'pie',
        radius : [ '40%', '55%' ],
        label : {
          normal : {
            formatter : '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
            backgroundColor : '#eee',
            borderColor : '#aaa',
            borderWidth : 1,
            borderRadius : 4,
            rich : {
              a : {
                color : '#999',
                lineHeight : 22,
                align : 'center'
              },
              hr : {
                borderColor : '#aaa',
                width : '100%',
                borderWidth : 0.5,
                height : 0
              },
              b : {
                fontSize : 16,
                lineHeight : 33
              },
              per : {
                color : '#eee',
                backgroundColor : '1',
                padding : [ 2, 4 ],
                borderRadius : 2
              }
            }
          },
          emphasis : {
            show : true,
            textStyle : {
              fontSize : '30',
              fontWeight : 'bold'
            }
          }
        },
        data : data[title1]
      } ]
    };
    myChart1.setOption(option);
  }
  
  
//饼状图(部门积分榜列表)
  $.myChartSiglePie3 = function(myChart1, title1, ajaxDataX1, data) {
	  console.log(data);
	   console.log(ajaxDataX1);
    
    option = {
    		backgroundColor:backgroundColor,
     color : ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d'],
      title : {
        text : title1,
        // subtext: '纯属虚构',
        x : 'center',
        y:'top'	
      },
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend : {
        orient : 'vertical',
        x : 'right',
        data : ajaxDataX1
      },
      series : [ {
        name : title1,
        type : 'pie',
        radius : [ '15%', '55%' ],
        roseType : 'area',
        label : {
        	 normal : {
                 formatter : '{b}：{c}',
                             
               },
          labelLine: {
  				normal: {
  				show: true,
                  smooth: 0.2,
                  length: 20,
                  length2:15
  				}
  			},
          emphasis : {
            show : true,
            textStyle : {
              fontSize : '20',
              fontWeight : 'bold'
            }
          }
        },
        data : data
      } ]
    };
    myChart1.setOption(option);
  }
  
  
//横向主状图(个人积分榜列表)
  $.myChartSigle2 = function(myChart1, title1, dataName, dataValue) {
  	 console.log(dataName);
  	 console.log(dataValue);
  	option = {
  			backgroundColor:backgroundColor,
  			color : ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d'],
  		title: {
  			text: title1,
  			textStyle: {
  				fontSize: 15
  			},
  		}, 		
  		grid: {
  			top: '12%',
  			left: '3%',
  			right: '6%',
  			bottom: '3%',
  			containLabel: true
  		},
  		yAxis: [{
  			'type': 'category',
  			'axisLabel': {
  				'interval': 0
  			},
  			'data': dataName,
  			axisPointer: {
  				type: 'shadow'
  			},
  			splitLine: {
  				show: false
  			}
  		}],
  		xAxis: [{
  			type: 'value',
  			min: 0,
  			
  			axisLabel: {
  				formatter: '{value}'
  			},
  			splitLine: {
  				show: false
  			}
  		}],
  		series: [{
  			name: '个人分数',
  			type: 'bar',
  			data: dataValue,
  			barWidth: '40%',
  			label: {
  				normal: {
  					show: true,
  					formatter: '{c}分',
  					position: 'insideRight',
  					color: '#fff'
  				}
  			},
  		}]
  	};
  	
  	myChart1.setOption(option);
  }
  
});



// 判断为空
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}