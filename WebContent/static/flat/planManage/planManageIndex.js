$(document).ready(function () {
    //右侧切换
    $('.lists-nav div').click(function () {
        var div = $('.right-side').children('div');
        var _i = $('.lists-nav div').index($(this));
        if(_i == '1'){
        	refreshPoliceTree();
        }
        var active = div.filter('.active');
        var i = div.index(active);
        $('.lists-nav div').children().removeClass('active');
        $(this).children().addClass('active');
        div.removeClass('active');
        div.eq(_i+1).addClass('active');
        i = _i
    });
    $('.lists-nav div').eq(0).click();
    //左侧滑块
    $('h5 i').click(function () {
        $(this).toggleClass('active');
        $(this).parent().parent().children('div').slideToggle();
    });
    
    $('.phone-one > input').click(function () {
        if ($(this).is(':checked')) {
            $(".phone-list input").attr("checked", true).parent().addClass('active');
        } else {
            $(".phone-list input").attr("checked", false).parent().removeClass('active');
        }
    });
    $(".phone-list input").click(function () {
        $(this).parent().toggleClass('active');
    });
    $('.complete').click(function () {
        if ($(this).is(':checked')) {
        	$('.resource dl').attr('type-val',1);
            $('.resource dl').addClass('active');
        } else {
            $('.resource dl').removeClass('active');
       	    $('.resource dl').attr('type-val',0);
        }
    });
    $('.delete-key').click(function () {
        $(this).prev('input').val('');
    });
    $('.processing li').click(function () {
        var div = $('.processing').children('div');
        var _i = $('.processing li').index($(this));
        var active = div.filter('.active');
        var i = div.index(active);
        $('.processing li').removeClass('selected');
        $(this).addClass('selected');
        div.removeClass('active');
        div.eq(_i).addClass('active');                        
        i = _i;
    });
    $('.activity div').click(function () {
        var div = $('.correlation').children('div');
        var _i = $('.activity div').index($(this));
        var active = div.filter('.active');
        var i = div.index(active);
        $('.activity div').removeClass('active');
        $(this).addClass('active');
        div.removeClass('active');
        div.eq(_i).addClass('active');
        i = _i;
    });
    $('.processing > div').on('click','p',function () {
        $('.processing > div p').removeClass('active');
        $(this).addClass('active');
       // $('.activity div:first-of-type').html($(this).children('b').html());
    });
    $('.resource dl').click(function () {
    	if($(this).hasClass('active')){
    		$(this).attr('type-val',0);
    		$(this).removeClass('active');
    	}else{
    		$(this).addClass('active');
    		$(this).attr('type-val',1);
    	}    
    	var len=$('.resource dl').length;
    	var cont=0;
    	$('.resource dl').each(function(){
        	if($(this).hasClass('active')){
        		cont++;
        	}
    	})
    	if(cont==len){
    		$('.complete').attr('checked',true);
    	}else{
    		$('.complete').attr('checked',false);
    	}
    });
    $('.detailed span').click(function () {
        $('.detailed span').removeClass('active');
        $(this).addClass('active');
        $('.detailed span').children('i').removeClass('active');
        $(this).children('i').addClass('active');
    });
    $('.dial').click(function () {
        $(this).toggleClass('active');
        $('.phone-number input').toggleClass('active');
    });
    //获取当前时间
    function show() {
        var str, mydate, month, getDate;
        mydate = new Date();
        month = (mydate.getMonth() + 1);
        getDate = mydate.getDate();
        if (month < 10) {
            month = '0' + month;
        }
        if (getDate < 10) {
            getDate = '0' + getDate;
        }
        str = mydate.getFullYear() + "/" + month + "/" + getDate;
        return str;
    }

    function showTime() {
        var str, time, getHours, getMinutes, getSeconds;
        time = new Date();
        getHours = time.getHours();
        getMinutes = time.getMinutes();
        getSeconds = time.getSeconds();
        if (getHours < 10) {
            getHours = '0' + getHours;
        }
        if (getMinutes < 10) {
            getMinutes = '0' + getMinutes;
        }
        if (getSeconds < 10) {
            getSeconds = '0' + getSeconds;
        }
        str = getHours + ":" + getMinutes + ":" + getSeconds;
        return str;
    }
    $("#Day").html(show());
    //$(".time-1").html(showTime());
    //弹框
    $('#steps').on('click', 'li', function () {
        var _this = $(this);
        var divHtml = _this.children('div').html();
        var offset = _this.offset();
        $('#steps li').removeClass('active');
        _this.addClass('active');
        $('#dialog').css('left', offset.left+230);
        $('#dialog').css('top', offset.top);
        $('#dialog div').html(divHtml);
        $('#dialog').show();       
    });
    $('#dialog i').click(function () {
        $(this).parents('#dialog').hide();
    });
    /*汽车弹框*/
    $("#map").on('click', '#graphicsLayer2_layer', function () {
        $(".car-main").css('top', $(this).offset().top - 80);
        $(".car-main").css('left', $(this).offset().left + 60);
        $(".car-main").show();
    })

    $(".car-close").click(function () {
        $(".car-main").hide();
    })
    //执行
    $('.execution').click(function () {
        var time = showTime();
        $('.open').show();
        var x = $('.processing > div p.active b').html();
        var html = '<li>';           
        html+='<div class="unit">'+x+'</div>';
        html+='<i></i>';
        html+='<div class="command-center">';
        html += '<div class="time-1">' + time + '</div>';
        html+='</div>';
        html += '</li>';
        $('#steps').append(html);
    });
    $('.indicating > div span').click(function () {
        $('.indicating > div span').removeClass('active');
        $(this).addClass('active');
    });
    /*录音*/
    $('.pause').click(function() {
        var fileseconds = 0;
        var currentseconds = 0;
        var audio = $('.details').children('.audio1')[0];
        if (audio && audio.paused) {
            audio.play();
            $('.pause').addClass('active');
            fileseconds = parseInt(audio.duration);
            $('.currenttime').html(fileseconds);
            window.setInterval(
                function () {
                    currentseconds = parseInt(audio.currentTime);
                   /* $('.currenttime').html(currentseconds);*/
                    if (currentseconds == fileseconds) {
                        $('.pause').removeClass('active');
                        currentseconds = 0;
                    }
                }, 200);
        } else if (audio && audio.played) {
            audio.pause();
            $('.pause').removeClass('active');
        }
    });
    //反馈
    feedback = function(){
       var x = $('.indicating > div input').val();
       var time = showTime();
       var day = show();
       var html = '<div class="receiver">';
       html+='<div class="message-content">'+x+'</div>';
       html += '<span>' + day + ' ' + time + '</span>';
       html += '</div>';
       $('.message-list').append(html);
    }
    $('#sending').click(function () {
        var x = $('.live div input').val();
        var time = showTime();
        var day = show();
        var html = '<div class="sender">';
        html += '<p>张警官</p>';
        html += '<div class="message-content">' + x + '</div>';
        html += '<span>' + day + ' ' + time + '</span>';
        html += '</div>';
        $('.message-list').append(html);
        $('.live div input').val('');
    });

    /*执行滑动*/
    $(".zhi").on("click",function(){
        if($(this).width()<=15){
            $(this).stop(false, true).animate({"width":"0.28rem"},500);
            $(this).children().animate({"width":"0.28rem"},500);
        }else{
            $(this).stop(false, true).animate({"width":"0.1rem"},500);
            $(this).children().animate({"width":0},500);
            $(this).css("background-color","green");
            var time = showTime();
            $('.open').show();
            var x = $('.processing > div p.active b').html();
            var html = '<li>';
            html+='<div class="unit">'+x+'</div>';
            html+='<i></i>';
            html+='<div class="command-center">';
            html += '<div class="time-1">' + time + '</div>';
            html+='</div>';
            html += '</li>';
            $('#steps').append(html);
        }
    })
    //添加切换
    $(".police-man").click(function () {
        var _this = $(this).parent();
        _this.parent().children('ul').hide();
        _this.parent().children('.police-list').show();
        _this.children('span').removeClass('blue');
        $(this).addClass('blue');
        refreshPoliceTree();
    });
    $(".police-cat").click(function () {
        var _this = $(this).parent();
        _this.parent().children('ul').hide();
        _this.parent().children('.cat-list').show();
        _this.children('span').removeClass('blue');
        $(this).addClass('blue');
        findCarDevice();
    });
    $(".police-video").click(function () {
        var _this = $(this).parent();
        _this.parent().children('ul').hide();
        _this.parent().children('.inner-video').show();
        _this.children('span').removeClass('blue');
        $(this).addClass('blue');
        refreshVideoTree();
    });
    
    /*手势操作*/
    var ham = new Hammer($(".container")[0], {
        domEvents: true
    });
    ham.get('swipe').set({ pointers: 1});
    ham.get('swipe').set({ direction: Hammer.DIRECTION_ALL });
    $(".processing > div p").on("swiperight",function(){
        $(this).children(".zhi").stop(false, true).animate({"width":"0.28rem"},500);
        $(this).children().children().animate({"width":"0.28rem"},500);
        console.log();
    });
    $(".processing > div p").on("swipeleft",function(){
        $(this).children(".zhi").stop(false, true).animate({"width":"0.1rem"},500);
        $(this).children().children().animate({"width":"0rem"},500);
        console.log();
    });
    
    //周边查询自定义米数
	$('input[name="distance"]').change(function(){
		if($('#customRadio').is(':checked')){
			$('#customKM').attr('disabled',false);
		}else{
			$('#customKM').attr('disabled',true);
		}
	})
	$('input[name="distance-a"]').change(function(){
		if($('#customRadio_A').is(':checked')){
			$('#customKM_A').attr('disabled',false);
		}else{
			$('#customKM_A').attr('disabled',true);
		}
	})
	// 周边查询图层是否选中
	$('input[name="layer"]').change(function(){
		if($(this).is(':checked')){
			$(this).val('1');
		}else{
			$(this).val('0');
		}
	})
		
	//警车警力图层
    /*布局改变*/
    $(".relevance-bg").click(function(){
        $(".relevance").animate({"width":"400px","height":"150px"},300);
        $(".relevance-bg").hide();
        $('.unfold').css('bottom','43px');
        $('.unfold').css('left','270px');
        $('.map-2').hide();
        $('.map-4').show();
    })
    $(".re-close").click(function(){
        $(".relevance").animate({"width":0,"height":0},300);
        $(".relevance-bg").show();
        $('.unfold').css('bottom','43px');
        $('.unfold').css('left','270px');
        $('.map-4').hide();
        $('.map-2').show();
    })
	//警情开关
    // var autoRefreshInterval ='';
    $('.toggle').click(function (e) {
        var toggle = this;
        e.preventDefault();
        $(toggle).toggleClass('toggle--on').toggleClass('toggle--off').addClass('toggle--moving');
        setTimeout(function () {
            $(toggle).removeClass('toggle--moving');
        }, 200)
    });
    $("#alarmDMS.toggle").click(function(){
        if($("#alarmDMS.toggle").hasClass("toggle--off")){
        	//显示警情定位
        	 Map.layersIsShow('alarm_'+alarmId, false);
        }else if($("#alarmDMS.toggle").hasClass("toggle--on")){
        	//隐藏警情定位
        	Map.layersIsShow('alarm_'+alarmId, true);
		}
    })
    //视频开关
    $("#videoDMS.toggle").click(function(){
        if($("#videoDMS.toggle").hasClass("toggle--off")){
        	shipinjiankongFun();
        	$('#shipinjiankong').css('border', '1px solid transparent');
        }else if($("#videoDMS.toggle").hasClass("toggle--on")){
        	shipinjiankongFun();
        	$('#shipinjiankong').css('border', '1px solid #0e54a9');
		}
    })
    
    $('#shipinjiankong').click(function(){
    	$("#videoDMS.toggle").click();
    })
    
    //警力开关
    $("#policeDMS.toggle").click(function(){
        if($("#policeDMS.toggle").hasClass("toggle--off")){
        	jingyuanFun();
        	$('#jingyuan').css('border', '1px solid transparent');
        }else if($("#policeDMS.toggle").hasClass("toggle--on")){
        	jingyuanFun();
          	$('#jingyuan').css('border', '1px solid #0e54a9');
		}
    })
    $('#jingyuan').click(function(){
    	$("#policeDMS.toggle").click();
    })
    //警车开关
    $("#carDMS.toggle").click(function(){
        if($("#carDMS.toggle").hasClass("toggle--off")){
        	jingcheFun();
        	$('#jingche').css('border', '1px solid transparent');
        }else if($("#carDMS.toggle").hasClass("toggle--on")){
        	jingcheFun();
        	$('#jingche').css('border', '1px solid #0e54a9');
		}
    })
    $('#jingche').click(function(){
    	$("#carDMS.toggle").click();
    })
	//警力-警种类型
    $('input[name="police"][type="checkbox"]').change(function(){
    	teamType='';
    	$('input[name="police"][type="checkbox"]:checked').each(function(){
    		teamType+=$(this).val()+',';
    	})
    	teamType=teamType.substring(0,teamType.length-1);
    	refreshPoliceTree();
    })
	// 视频树搜索
    $('#videoButton').click(function(){
    	var secVideoVal=$('#secVideo').val();
    	if(secVideoVal==""){
    		refreshVideoTree();
    	}else{
    		filter('ztreeVideo','secVideo');
    	}
    })
    //案件流程
    $('.bottom-side h5 i').click(function () {
        $(this).parent().parent().parent().children('div.bottom-side-center').slideToggle();
    });
});
