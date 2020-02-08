/**
 * Created by oHa on 2017/12/27.
 */

/*       
 * @returns
 */

$(function() {
      
    // 基础颜色表
    var color = [ '#79F0C9','#9CDCDE','#F5D883','#ED817C', '#B0DAB4',  '#2ec7c9','#3398DB','#b6a2de','#5ab1ef','#ffb980','#d87a80',
        '#8d98b3','#97b552','#95706d','#dc69aa',
        '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
        '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'];
    // 基础参数
    var windowsHeight, _fontSize = 14,

    _fontSize1 = 26,
    _fontSizeText=12,
    breakData = 8;
    legendTop = '30%',
    radiusData = [90, 65],
    lengthECharts = 30;
    
//	$.get("/arjccm/a/statistics/plmStatistics/backgroundImageName", function(
//			data) {
//	/*alert(data)*/
//		  $('.container-fluid').css("background-image","url('/arjccm/static/common/index/images/"+data+"')")
//		  
//		
//	});
    var color1;
    if(getCookie("theme")=="black"||!getCookie("theme")){
	$('.container-fluid').css("background-color",getCookie("theme"));
	color1="#fff"
    }else if(getCookie("theme")=="gradient"){
    	    	$('.container-fluid').css("background-color",getCookie("theme"));
    	    	color1="#000"}
    
    
/*    if(getCookie("theme")=="cerulean" || !getCookie("theme")){
    	$('.container-fluid').css("background-color","white");
    }else{
    	$('.container-fluid').css("background-color",getCookie("theme"));
    }*/
    function getCookie(cname)
    {
      var name = cname + "=";
      var ca = document.cookie.split(';');
      for(var i=0; i<ca.length; i++) 
      {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
      }
      return "";
    }
	
       
    // 
    $('#mainstatis').height($(window).height());

    // 
    $('.height100').height($('#mainstatis').height() -90);
    $('.bodydiv').height($('#mainstatis').height() -90);
   /*  
    //
    $('#main').height($(window).height());
    $('.height100').height($('#main').height() - 75);
    */

    
    
    
    windowsHeight = $(window).width();

    if (windowsHeight > 1600) {
        _fontSize = 10;
        legendTop = '15%';
        radiusData = [90, 65];
        lengthECharts = 20;
        _fontSize1 = 26;
        breakData = 8;
        legendRight="8%"
    } else {
        _fontSize = 9;
        legendTop = '15%';
        radiusData = [60, 45];
        lengthECharts = 5;
        _fontSize1 = 14;
        breakData = 6;
        legendRight="5%"
    }

    
   /**
    * 仓库、物资、物资类型总数量统计
    */
    $.CountStorage = function(model, data) {
    	 
       
        var content = '仓库数量：<span id="countStorageSpan1">'+data[0]+
        '</span>&nbsp;物资总数量：<span id="countStorageSpan1">'+data[1]
        
        
       
        // 添加内容 到页面
        $("#" + model).html(content);
    }
    /**
     * 车辆数量 
     */
     $.CountCar = function(model, data) {    	
         var content = '车辆总数量：<span id="countCarSpan">'+data[0]+
         '</span>';               
         // 添加内容 到页面
         $("#" + model).html(content);
     }
    
    
     
     /**
      * 各倉庫物资统计2
      */
     
     $.CountEquipmentByStorage = function(model, data) {
    	
    		      
    		      
    		    option = {
    		   title:{
    		        text:''
    		    },
    		    tooltip: {
    		    },
    		    series: []
    		};
    		  
    		    
    		    
    		     var radius1=40
    		    var radius2=44  
    		     var center1=22
    		    var center2=50
    		   
    		  var length=data.length;
    		     var  _fontSize2=16;
    		for (var i = 0; i <length; i++) {
    		    if( length>6){
    		        radius1=22
    		         radius2=24
    		         
    		          center1=3
    		         center2=3
    		       
    		   }    else if(length>4){
    		          radius1=30
    		         radius2=32 
    		         
    		          center1=25
    		         center2=20
    		       
    		   }
    		   
    		   switch (length) {
    		       case 1:
    		    	   _fontSize2=20;
    		            radius1=74
    		            radius2=80  
    		            center1=50
    		            center2=50
    		           break;
    		       case 2:
    		            radius1=50
    		            radius2=55 
    		            center1=26+i*48
    		            center2=50
    		           break;           
    		       case 3:
    		            radius1=44
    		            radius2=48
    		         if(i<1){
    		            center1=50
    		            center2=28
    		         }else {
    		            center1=29+(i-1)*44
    		            center2=74
    		         }
    		           break;
    		       case 4:
    		    	   _fontSize2=14;
    		            radius1=40
    		            radius2=44 
    		         if(i<2){
    		            center1=30+i*42
    		            center2=27
    		         }else {
    		            center1=30+(i-2)*42
    		            center2=76
    		         }
    		           break;           
    		       case 5:
    		    	   _fontSize2=13;
    		           radius1=33
    		           radius2=36
    		         if(i<2){
    		            center1=35+i*34
    		            center2=30
    		         }else {
    		            center1=20+(i-2)*32
    		            center2=70
    		         }
    		           break;           
    		       case 6:
    		    	   _fontSize2=12;
    		          radius1=33
    		           radius2=36
    		         if(i<1){
    		            center1=50
    		            center2=20
    		         }else if(i<3){
    		            center1=20+(i-1)*60
    		            center2=31
    		         }else if(i==5){
    		            center1=50
    		            center2=80
    		         }else {
    		            center1=20+(i-3)*60
    		            center2=69
    		         }
    		           break;           
    		       case 7:
    		    	   _fontSize2=11;
    		           radius1=32.5
    		           radius2=35
    		         if(i<2){
    		            center1=34+i*32
    		            center2=20
    		         }else if(i>4){
    		            center1=34+(i-5)*32
    		            center2=80
    		         }else {
    		            center1=18+(i-2)*32
    		            center2=50
    		         }
    		           break;           
    		               
    		       default:
    		    	   _fontSize2=12;
    		          radius1=32.5
    		           radius2=35
    		         if(i<2){
    		            center1=34+i*32
    		            center2=20
    		         }else if(i>6){   		             
    		            radius1=0
    		            radius2=0
    		            center1=0
    		            center2=50
    		            data[i].name="";
    		            data[i].value1="";
    		            
    		         }else if(i>4){
    		            center1=34+(i-5)*32
    		            center2=80
    		         }else {
    		            center1=18+(i-2)*32
    		            center2=50
    		         }
    		   }
    		  
    		   
    		    option.series.push(
    		        {
    		        name: data[i].name,
    		        type: 'pie',
    		        radius: [radius1+'%', radius2+'%'],
    		        center:[ center1+'%', center2+'%'],
    		     
    		        label: {
    		            normal: {
    		                position: 'center'
    		            }
    		        },
    		        data: [{
    		            value: data[i].value1,
    		            name: data[i].name,
    		              itemStyle: {
    		                normal: {
    		                     color:color[i],
    		                    shadowColor: color[i],
    		                    shadowBlur: 10
    		                }
    		            },
    		            label: {
    		                normal: {
    		                    formatter: '{c}',
    		                    textStyle: {
    		                    	 fontSize:_fontSize2
    		     		           
    		                    }
    		                }
    		            },
    		            tooltip:{
    		                 trigger: 'item',
    		                 formatter: "{a} <br/>仓库物资总数："+data[i].valueAll+"<br/> 仓库物资闲置数 : {c}"
    		            }
    		            
    		        }, {
    		            value: data[i].value2,
    		            name: '仓库物资其他状态数',
    		            label: {
    		                normal: {
    		                    formatter: '\n'+data[i].name,
    		                    textStyle: {
    		                    	 fontSize:_fontSize2,
    		     		            color1
    		                    }
    		                }
    		                
    		            },
    		            itemStyle: {
    		                normal: {
    		                    color: '#aaa'
    		                },
    		                emphasis: {
    		                    color: '#aaa'
    		                }
    		            },
    		        }]
    		    });}
    	// 实例化 对象
         var Barchart = echarts.init(document.getElementById(model));
         // 填充数据
         Barchart.setOption(option);
     }
     
     
     
     
     
     
     
     
    /**
	 * 物资类别(饼图)
	 */

    $.RatioEquipmentByType = function(model, dataValue1,dataValue2,dataLegend) {
    	option = { color: color,
    			title : {text: '',
                    
    	            x: 'center',
    	            bottom:10,
    	            textStyle: {
    	                color1,
    	                fontSize: _fontSizeText
    	            },
              },
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: 'left',
    		        icon: 'roundRect',
    		        textStyle:{
    		            fontSize:_fontSize,
    		            color1
    		        },
    		        data:dataLegend
    		    },
    		    series: [
    		        {
    		            name:'物资类型',
    		            type:'pie',
    		            selectedMode: 'single',
    		            radius: [0, '35%'],
    		            center: ['62%', '50%'],
    		            label: {
    		                normal: {
    		                    position: 'inner'
    		                }
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:dataValue1
    		        },
    		        {
    		            name:'物资子类型',
    		            type:'pie',
    		            radius: ['45%', '70%'],
    		            center: ['62%', '50%'],
    		            label: {
    		            	 normal: {
    		                     
    		                     
    		                 
    		                }
    		            },
    		            data:dataValue2
    		        }
    		    ]
    		};
        // 实例化 对象
        var Barchart = echarts.init(document.getElementById(model));
        // 填充数据
        Barchart.setOption(option);
    }
   
    
    
     
    /**
	 * 物资使用情况
	 */
    $.CountEquipmentByStats = function(model, originalData) {
    	option = {
    			/* title : {text: '装备使用统计',
	                     
	    	            x: 'center',
	    	            bottom:5,
	    	            textStyle: {
	    	                color: '#fff',
	    	                fontSize: _fontSizeText
	    	            },
	              },*/
    		    color:color,
    		    tooltip: {
    		        trigger: 'axis',
    		        axisPointer: {
    		            type: 'shadow'
    		        }
    		    },
    		    legend: {
    		        data: [/*'占用',*/'闲置','维修保养','使用中','报废'],
    		        textStyle:{
    		        	 fontSize:_fontSize,
    		            color1
    		        }
    		    },
    		    grid: {
    		    	
    		        left: '0%',
    		        right: '25',
    		        bottom: '20',
    		        containLabel: true
    		    },
    		    xAxis: {
    		    	  type: 'category',
      		        data: originalData[4],
      		      axisLine: {
                      lineStyle: {
                          color1
                      }
                  },
      		        axisLabel: {
      	                show: true,
      	              interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
                      rotate:-30,
      	                textStyle: { fontSize:_fontSize,
      	                    color1
      	                },
		    		    nameTextStyle: {
				        	width:'10',
				        	color1
						}
      	            }
    		      
    		    },
    		    yAxis: {
    		    	  type: 'value',
      		        splitLine:{show: false},//去除网格线
      		      axisLine: {
                      lineStyle: {
                          color1
                      }
                  },
      		        boundaryGap: [0, 0.01],
      		        axisLabel: {
      	                show: true,
      	                textStyle: {
      	                    color1
      	                }
      	            }
    		    	
    		    	
    		      
    		    },
    		    series: [/*{
    		            name: '占用',
    		            type: 'bar',
    		            stack: '总量',
    		            data: originalData[0],
    		            label: {
    		                normal: {
    		                    show: true,
    		                    position: 'insideRight'
    		                }
    		            },
    		            itemStyle:{
    	                    normal:{
    	                        color:''
    	                    }
    	                }
    		            
    		        },*/
    		        {
    		            name: '闲置',
    		            type: 'bar',
    		            stack: '总量',
    		            label: {
    		                normal: {
    		                   /* show: true,
    		                    position: 'insideRight'*/
    		                }
    		            },
    		            itemStyle:{
    	                    normal:{
    	                        color:''
    	                    }
    	                },
    		            data:originalData[0],
    		        },{
    		            name: '维修保养',
    		            type: 'bar',
    		            stack: '总量',
    		            label: {
    		                normal: {
    		                    /*show: true,
    		                    position: 'insideRight'*/
    		                }
    		            },
    		            
    		            data:originalData[1],
    		        },{
    		            name: '使用中',
    		            type: 'bar',
    		            stack: '总量',
    		            label: {
    		                normal: {
    		                    /*show: true,
    		                    position: 'insideRight'*/
    		                }
    		            },
    		            
    		            data:originalData[2],
    		        },{
    		            name: '报废',
    		            type: 'bar',
    		            stack: '总量',
    		            label: {
    		                normal: {
    		                   
    		                }
    		            }, itemStyle:{
    	                    normal:{
    	                        color:'#e5323e'
    	                    }
    	                },
    		            
    		            
    		            data:originalData[3],
    		        }
    		       
    		    ]
    		};

    	;
        // 初始化参数
        var myChart = echarts.init(document.getElementById(model));
        myChart.setOption(option, true);      
    }
    
    
    
   
    
    
    //到使用期限物资
    $.WarningEquipment=function(model, originalData){
    	
    	
    	   
    	option = {
    			 color: color,
    		    title : {text: '已到使用期限物资',
    		           top: 4,
    		    	            left: '20%',
    		    	            textStyle: {
    		    	                color1,
    		    	                fontSize: _fontSizeText
    		    	            },
    		    },
    		    tooltip: {
    		        trigger: 'axis',
    		        axisPointer: {
    		            type: 'shadow'
    		        }
    		    },
    		    
    		    grid: {
    		        left: '3%',
    		        right: '4%',
    		        bottom: '3%',
    		        containLabel: true
    		    },
    		    xAxis: {
    		        type: 'value',
    		        splitLine:{show: false},//去除网格线
    		        boundaryGap: [0, 0.01],   		       
    		       axisLabel: {
    			                formatter: '{value} ',
    			                textStyle: {
    				                color1
    				            }
    			            }
    		    },
    		    yAxis: {
    		        type: 'category',
    		        data: originalData[0],   		      
    		        axisLabel: {
    		        	 show: true,
    		        	
    						        textStyle: {
    						        	 fontSize:_fontSize,
    						        	 
    						        	color1
    						        
    						        		
    						        }
    					        },
    					        nameTextStyle: {
    					        	width:'2%',
    					        	color1
    							}
    		    },
    		    series: [
    		        {
    		            name: '已到使用期限物资',
    		            type: 'bar',
    		            itemStyle:{
    		                    normal:{
    		                        color:'#7091C4'
    		                    }
    		                },
    		            data: originalData[1]
    		        }
    		    ]
    		};
    	options = {
    			 color: color,
    		    title : {text: '即到使用期限物资',
    		           top: 4,
    		    	            left: '20%',
    		    	            textStyle: {
    		    	                color1,
    		    	                fontSize: _fontSizeText
    		    	            },
    		    },
    		    tooltip: {
    		        trigger: 'axis',
    		        axisPointer: {
    		            type: 'shadow'
    		        }
    		    },
    		    
    		    grid: {
    		        left: '3%',
    		        right: '4%',
    		        bottom: '3%',
    		        containLabel: true
    		    },
    		    xAxis: {
    		    	 type: 'value',
    		    	 splitLine:{show: false},//去除网格线
    		    	 axisLine: {
    	                    lineStyle: {
    	                        color1
    	                    }
    	                },
     		        boundaryGap: [0, 0.01],   		       
     		       axisLabel: {
     			                formatter: '{value} ',
     			                textStyle: {
     				                color1
     				            }
     			            }
     		    },
    		    
    		    yAxis: {
    		        type: 'category',
    		        data: originalData[2],
    		        axisLine: {
                        lineStyle: {
                            color1
                        }
                    },
    		        axisLabel: {
				        textStyle: {
				        	 fontSize:_fontSize,
				        	color1
				        }
			        },
			        nameTextStyle: {
			        	color1
					}
                    },
    		    series: [
    		       
    		        {   
    		            name: '即到使用期限物资',
    		            type: 'bar',
    		            itemStyle:{
    		                    normal:{
    		                        color:'#7091C4'
    		                    }
    		                },
    		            data:originalData[3]
    		        }
    		    ]
    		};
        // 初始化参数
        var myChart = echarts.init(document.getElementById(model));
        var myCharts = echarts.init(document.getElementById(model+2));
        myChart.setOption(option, true);
        myCharts.setOption(options, true);
    	
    }
    
    //到使用期限物资(tabelist)
    $.WarningEquipmentTable=function(model, dData,jData ,cData){
    	
    	 
    	
    	  // 已到期使用期限物资
    	
        // 拼接 table内容
        var dtableContent = "";
        // 获取 按月类型值
        var j=0;
        for (var i in dData) {
        	
        	
        	j=Number(i)+1;
            // 获取每一个值
            dtableContent += "<tr><td>"+j+"</td><td>"+dData[i]["name"]+"</td><td>"+dData[i]["count"]+"</td> <td>"+Math.abs(dData[i]["durDay"])+"</td></tr>"
        }
        // 添加内容 到页面
        $ ("#d" +model).html(dtableContent);
        
    	//即到到使用期限物资
        // 拼接 table内容
        var jtableContent = "";
        // 获取 按月类型值
        for (var i in jData) {
        	
        	j=Number(i)+1;
            // 获取每一个值
            jtableContent += "<tr><td>"+j+"</td><td>"+jData[i]["name"]+"</td><td>"+jData[i]["count"]+"</td> <td>"+jData[i]["durDay"]+"</td> </tr>"
        }
        // 添加内容 到页面
        $ ("#j" +model).html(jtableContent);
        
        
      //即到到使用期限物资
        // 拼接 table内容
        var ctableContent = "";
        // 获取 按月类型值
        for (var i in cData) {
        	
        	j=Number(i)+1;
            // 获取每一个值
            ctableContent += "<tr><td>"+j+"</td><td>"+cData[i]["name"]+"</td><td>"+cData[i]["count"]+"</td> <td>"+cData[i]["durDay"]+"</td> </tr>"
        }
        // 添加内容 到页面
        $ ("#c" +model).html(ctableContent);
    	
    }
    
    $.AllNumByMonth=function(model,data){
    	
    	option = {
    			/* title : {text: '车辆使用趋势',
  		                     
  		    	            x: 'center',
  		    	            bottom:10,
  		    	            textStyle: {
  		    	                color: '#fff',
  		    	                fontSize: _fontSizeText
  		    	            },
  		              },*/
    			 color:['#F7AF24','#78d6ef'],
    			tooltip : {
    				trigger : 'axis',
    				axisPointer : {
    					type : 'shadow'
    				}
    			},   			
    			calculable : true,
    			  grid: {
    				top:  '10',
      		        left: '3%',
      		        right: '4%',
      		        bottom: '30',
      		        containLabel: true
      		    },
    			xAxis : [ {
    				type : 'category',
    				axisTick : {
    					show : false
    				},
    				data : data[0], 
    				axisLine: {
                        lineStyle: {
                            color1
                        }
                    },
    				axisLabel: {
    	                formatter: '{value} 月',
    	                textStyle: {
    	                	 fontSize:_fontSize,
    		                color1
    		            }
    	            }
    			} ],
    			yAxis : [ 
    				{
    					type : 'value',
    					name :'次数',
    					splitLine:{show: false},//去除网格线
    					axisLine: {
    	                    lineStyle: {
    	                        color1
    	                    }
    	                },
    					axisLabel: {
    				        textStyle: {
    				        	color1
    				        }
    			        },
    			        nameTextStyle: {
    			        	color: ''
    					}
    					
    				}
    				
    			],
    			series : [ {
    				name : '用车次数',
    				type : 'line',
    				
    				data : data[1],
    				 areaStyle: {
    		                normal: {type: 'default',
    		                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    		                        offset: 0,
    		                        color: 'rgba(237,170,46,0.5)'
    		                     }, {
    		                         offset: 1,
    		                         color: 'rgba(247,211,139,0.1)'
    		                    }], false)
    		                }
    		            },
    		            smooth:true,
    		            itemStyle: {
    		                normal: {areaStyle: {type: 'default'}}    
    		            }
    			} ]
    		};
    	
    	
    	 // 初始化参数
        var myChart = echarts.init(document.getElementById(model));  
        myChart.setOption(option, true);       
    }
    
    /**
	 * 车辆类型
	 */

    $.NumByVtype = function(model, dataValue,dataLegend) {
    	option = { color: color,
    			 title : {text: '车辆类型',
                  
  	            x: 'center',
  	            bottom:0,
  	            textStyle: {
  	                color: '#fff',
  	                fontSize: _fontSizeText
  	            },
                 },
    		    tooltip : {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
    		    },
    		    legend: {
    		    	  orient: 'vertical',
      		        x: 'right',
    		        data:dataLegend,
    		        textStyle:{
    		        	 fontSize:_fontSize,
    		            color1
    		        }
    		    },
    		    
    		    calculable : true,
    		    series : [
    		        {
    		            name:'类型',
    		            type:'pie',
    		            radius: ["30%", '85%'],
    		            center : ['40%', '45%'],
    		            roseType : 'radius',
    		            label: {
    		                normal: {
    		                    show: false
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            lableLine: {
    		                normal: {
    		                    show: false
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            data:dataValue
    		        }   
    		        ]
    	};
        // 实例化 对象
        var Barchart = echarts.init(document.getElementById(model));
        // 填充数据
        Barchart.setOption(option);
    }
   
    
    
 
    /**
     * 各部门用车次数
     */
    $.UseNumAllByOfficeAjax=function(model,jsondata){
    	
    	option = {
    			 /*title : {text: '部门用车次数',
	                     
	    	            x: 'center',
	    	            bottom:10,
	    	            textStyle: {
	    	                color: '#fff',
	    	                fontSize: _fontSizeText
	    	            },
	              },*/
	              color: ['#4E80BC'],
	              tooltip : {
	                  trigger: 'axis',
	                  axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	                      type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	                  }
	              },
	              grid: {
	            	  top:"10",
	                  left: '3%',
	                  right: '40',
	                  bottom: '40',
	                  containLabel: true
	              },
	              xAxis : [
	                  {
	                      type : 'category',
	                      data : $.ToConvertLegendA(jsondata),
	                      axisLine: {
	                          lineStyle: {
	                              color1
	                          }
	                      },
	                      axisTick: {
	                          alignWithLabel: true
	                      }, 
	                     
	                      axisLabel: { 
	                    	  show: true,
	                    	  interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
	                          rotate:-35,
	                    	  textStyle: {
	                        	  fontSize:_fontSize,
	                              color1
	                          },
                              /*formatter:function(value)  
                              {  
                                  var ret = "";//拼接加\n返回的类目项  
                                  var maxLength = 2;//每项显示文字个数  
                                  var valLength = value.length;//X轴类目项的文字个数  
                                  var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数  
                                  if (rowN > 1)//如果类目项的文字大于3,  
                                  {  
                                      for (var i = 0; i < rowN; i++) {  
                                          var temp = "";//每次截取的字符串  
                                          var start = i * maxLength;//开始截取的位置  
                                          var end = start + maxLength;//结束截取的位置  
                                          //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧  
                                          temp = value.substring(start, end) + "\n";  
                                          ret += temp; //凭借最终的字符串  
                                      }  
                                      return ret;  
                                	 
                                  }  
                                  else {  
                                      return value;  
                                  }  
                              }  */
                          }  
	                  
	                  }
	              ],
	              yAxis : [
	                  {
	                      type : 'value',
	                      splitLine:{show: false},//去除网格线
	                      axisLine: {
	                          lineStyle: {
	                              color1
	                          }
	                      },
	                      axisLabel: {
	                          show: true,
	                          textStyle: {
	                              color1
	                          }
	                      }
	                  }
	              ],
	              series : [
	                  {
	                      name:'用车次数',
	                      type:'bar',
	                      barWidth: '60%',
	                      data:$.ToConvertSeriesA(jsondata),
	                      itemStyle: {
	                          normal: {
	                              lineStyle: {
	                                  color: '#eb9041'
	                              }
	                          }
	                      },
	                  }
	              ]
	          };
    	
    	
    	// 初始化参数
        var myChart = echarts.init(document.getElementById(model));  
        myChart.setOption(option, true); 
    }
    
    
   
      
    //车辆使用状态3（还饼图）
    $.CountByStatusAjax3=function(model,data){
    	
    	option = {
    			title : {text: '车辆使用状态',
  	              
    	            x: 'center',
    	            bottom:0,
    	            textStyle: {
    	                color1,
    	                fontSize: _fontSizeText
    	            }
                    },
                    color: ["#A2C45F","#DB843D","#93ADD8","#E5CF0D"],
    		    tooltip : {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
    		    },
    		    legend: {
    		        orient : 'vertical',
    		        x : 'left',
    		        data:$.ToConvertLegendA(data),
    		        textStyle:{
   		        	 fontSize:_fontSize,
   		            color1
   		        }
    		    },
    		   
    		    
    		    series : [
    		        {
    		            name:'使用状态',
    		            type:'pie',
    		            radius : ['50%', '75%'],
    		            avoidLabelOverlap: false,
    		            avoidLabelOverlap: false,
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		                emphasis: {
    		                    show: true,
    		                    textStyle: {
    		                        fontSize: '30',
    		                        fontWeight: 'bold'
    		                    }
    		                }
    		            },
    		            data: $.ToConvertA(data)
    		        }
    		    ]
    		};
    		                    
    	
    	
      	   
      	 // 初始化参数
          var myChart = echarts.init(document.getElementById(model));  
          myChart.setOption(option, true);       
      }
    
    //车辆使用状态4
    $.CountByStatusAjax4=function(data){
    	
         for (var one in data) {
            if(data[one]["type"]=='02'){
            	$("#lingyong").text(data[one]["value"])
            } else if(data[one]["type"]=='01'){           	
            	$("#kongxian").text(data[one]["value"])
            }else if(data[one]["type"]=='03'){           	
            	$("#weixiu").text(data[one]["value"])
            }else if(data[one]["type"]=='04'){           	
            	$("#baofei").text(data[one]["value"])
            }
         }
    	
      }
    
    
    
   // 流程申请统计（按状态）
    $.CountActByStatus=function(model,datatype,datevalue){
    	
    			
    	 var content = '<br/><div class="span3"><span class="countBigFont ">'+datevalue[5]+'</span><br><br>'+datatype[5]+'</div>&nbsp'+
    	 '<div class="span3"><span class="countBigFont ">'+datevalue[1]+'</span><br><br>'+datatype[1]+'</div>&nbsp'+
    	         
    	 '<div class="span3"><span class="countBigFont ">'+datevalue[3]+'</span><br><br>'+datatype[3]+'</div>&nbsp'+
    	 '<div class="span3"><span class="countBigFont ">'+datevalue[4]+'</span><br><br>'+datatype[4]+'</div>';
        
         // 添加内容 到页面
         $("#" + model).html(content);
    	
    }
    //流程趋势  以天为时间轴
 $.CountActByDate=function(model,data){ 	
	 option = {
			 title : {text: '',
		                     
		    	            x: 'center',
		    	            bottom:10,
		    	            textStyle: {
		    	                color1,
		    	                fontSize: _fontSizeText
		    	            },
		              },
		    color: color,
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				}
			}, legend: {
		        data: ['审核中','已完成','流程申请总量']  ,
		        textStyle:{
		        	 fontSize:_fontSize,
	 	            color1
	 	        }
		    },  			
			calculable : true,
			  grid: {
				top:  '10',
  		        left: '3%',
  		        right: '4%',
  		        bottom: '30',
  		        containLabel: true
  		    },
			xAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : data[0], 
				splitLine:{show: false},//去除网格线
				axisLine: {
                    lineStyle: {
                        color1
                    }
                },
				axisLabel: {
	                formatter: '{value}',
	                textStyle: {
	                	 fontSize:_fontSize,
		                color1
		            }
	            }
			} ],
			yAxis : [ 
				{
					type : 'value',
					name :'次数',
					splitLine:{show: false},//去除网格线
					axisLine: {
	                    lineStyle: {
	                        color1
	                    }
	                },
					axisLabel: {
				        textStyle: {
				        	color1
				        }
			        },
			        nameTextStyle: {
			        	color: ''
					}
					
				}
				
			],
			series : [ {
				name : '审核中',
				type : 'bar',
				 areaStyle: {
		                normal: {type: 'default',
		                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                        offset: 0,
		                        color: 'rgba(78,201,172,0.5)'
		                     }, {
		                         offset: 1,
		                         color: 'rgba(147,255,219,0.1)'
		                    }], false)
		                }
		            },
		            smooth:true,
		            itemStyle: {
		                normal: {areaStyle: {type: 'default'}}    
		            },
				data : data[2],
			},{
				name : '已完成',
				type : 'bar',
				 areaStyle: {
		                normal: {type: 'default',
		                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                        offset: 0,
		                        color: 'rgba(78,201,172,0.5)'
		                     }, {
		                         offset: 1,
		                         color: 'rgba(147,255,219,0.1)'
		                    }], false)
		                }
		            },
		            smooth:true,
		            itemStyle: {
		                normal: {areaStyle: {type: 'default'}}    
		            },
				data : data[3],
			},{
				name : '流程申请总量',
				type : 'line',
				areaStyle: {
	                normal: {type: 'default',
	                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                        offset: 0,
	                        color: 'rgba(78,201,172,0.5)'
	                     }, {
	                         offset: 1,
	                         color: 'rgba(147,255,219,0.1)'
	                    }], false)
	                }
	            },
		            smooth:true,
		            itemStyle: {
		                normal: {areaStyle: {type: 'default'}}    
		            },
				data : data[1],
			} ]
		};
	
	 // 初始化参数
     var myChart = echarts.init(document.getElementById(model)); 
     
     myChart.setOption(option, true);         
    }
   

// 各流程数量（图表统计）
$.CountActByType=function(model,data){
	 
	  var yubiao=['icon-truck', 'icon-truck', 'icon-truck',  'icon-truck',
		     'icon-cog','icon-cog','icon-cog',		   
	        ' icon-shopping-cart','  icon-shopping-cart','icon-paste'
	        ,'icon-home','icon-home'
	        ,'icon-folder-open','icon-folder-open','icon-plane'
	        ,'icon-file-alt','icon-file-alt','icon-file-alt',
	        'icon-file-alt','icon-file-alt','icon-file-alt','icon-file-alt','icon-file-alt'];
	  
	  var Content = "";
      // 获取 按月类型值
      for (var i in data) {
          // 获取每一个值
          Content +=' <div class="ActByType span3" style="color:'+color[i]+' "><i class="'+yubiao[i]+'"></i>&nbsp;'+data[i]["value"]+'<br><div>'+data[i]["type"]+' </div></div>'
      }
      // 添加内容 到页面
      $ ("#"+model).html(Content);
	
	
 }
    //读取	sCookie  
	function getCookie(name)
	{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
	}
    // EchartType 转换成 Echart所需要的类型
    $.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    }
    
 // EchartType 转换成 Echart所需要的类型
    $.ToConvertB = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["typeO"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    }
 // EchartType 转换成 Echart legend  所需要的类型
    $.ToConvertLegendA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["type"]
            );
        }
        return ajaxData;
    }
    // EchartType 转换成 Echart Series  所需要的类型
    $.ToConvertSeriesA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["value"]
            );
        }
       
        return ajaxData;
    }   
    // EchartType 转换成 Echart Series  所需要的类型
    $.ToConvertSeriesB = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["typeO"]
            );
        }
        return ajaxData;
    }
    // EchartType 转换成 Echart Series  所需要的类型
    $.ToConvertSeriesC = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["value2"]
            );
        }
        return ajaxData;
    }
   
    
    $.updateSearchTable = function(model, originalData) {
        // echartsAlarm-value
        // 拼接 table内容
        var tableContent = "";
        // 获取 按月类型值
        for (var i in originalData) {
            // 获取每一个值
            tableContent += "<tr><td>"+i+"</td><td>"+originalData[i]["value1"]+"</td><td>"+originalData[i]["value2"]+"</td> <td>"+originalData[i]["value3"]+"</td> <td>"+originalData[i]["value4"]+"</td></tr>"
        }
        // 添加内容 到页面
        $ (model).html(tableContent);
    }
   
    
   
    		
    
  
    

   // $('#alarmTable').height($('.height33').height()-50)
    /* //各流程各状态数量
    $.CountActByStatusType=function(model,datax,data){
   	 
   	 option = {
   			    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
   			    tooltip: {
   			        trigger: 'axis',
   			        axisPointer: {
   			            type: 'shadow'
   			        }
   			    },
   			    grid: {
   			    	top: 25,
   		 	        left: '3%',
   		 	        right: '4%',
   		 	        bottom: '30',
   		 	        containLabel: true
   		 	    },
   			    legend: {
   			        data: ['未提交','审核中','已完成','已销毁']  ,
   			        textStyle:{
   			        	 fontSize:_fontSize,
   		 	            color:'#fff'
   		 	        }
   			    },
   			    toolbox: {
   			        show: true,
   			        orient: 'vertical',
   			        left: 'right',
   			        top: 'center'
   			       
   			    },
   			    calculable: true,
   			    xAxis: [
   			        {
   			            type: 'category',
   			            axisTick: {show: false},
   			            axisLabel: {
   	                          show: true,
   	                          interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
   	                          rotate:-25,
   	                          textStyle: {
   	                        	  fontSize:_fontSize,
   	                              color: '#fff'
   	                          }
   	                      },
   			            data: datax
   			        }
   			    ],
   			    yAxis: [
   			        {
   			            type: 'value',
   			            splitLine:{show: false},//去除网格线
   			            axisLabel: {
   	                          show: true,
   	                          textStyle: {
   	                              color: '#fff'
   	                          }
   	                      }
   			        }
   			    ],
   			    series: [
   			        {
   			            name: '未提交',
   			            type: 'bar',
   			            barGap: 0,
   			            stack: '总量',
   			            data: data[0]
   			        },
   			        {
   			            name: '审核中',
   			            type: 'bar',
   			            stack: '总量',
   			            data: data[1]
   			        },{
   			            name: '已完成',
   			            type: 'bar',
   			            stack: '总量',
   			            data: data[3]
   			        }
   			        ,
   			        {
   			            name: '已销毁',
   			            type: 'bar',
   			            stack: '总量',
   			            data: data[2]
   			        }
   			        
   			    ]
   			}; 
   	
   	 // 初始化参数
        var myChart = echarts.init(document.getElementById(model));  
        myChart.setOption(option, true);      
    }*/
});