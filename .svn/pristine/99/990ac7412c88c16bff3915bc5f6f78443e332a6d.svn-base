$(function () {
	
    // 楼栋弹框
    $("#popup").on("click", ".bulidclick",
        function (e) {
            var id = $(this).attr('featureId') || "";
            var pilesNum = $(this).attr('pilesNum') || 0; // 层数
            var elemNum = $(this).attr('elemNum') || 0; // 单元数
            var buildName = $(this).attr('buildName') || ""; // 楼栋名称
            var houseId = null;
            var doorNum = null;
         	var html="";
            html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
            $('#modal-footer').html(html);
			$("#house-details,#pop-details").empty();
			 $("#build-details").show();
            // 加载信息
            $('#buildBtn').trigger("click");
            $("#build-details").load(ctx + "/house/ccmHouseBuildmanage/Maplist", {
                "id": id,
                "pilesNum": pilesNum,
                "buildName": buildName,
                "elemNum": elemNum,
                "type":127
            }, function() {
            	
       
    			// $("#build-details").show();
    				
    				//上下滚动
    		/*	var mainContainer = $('.house-center'), scrollToContainer = mainContainer
    						.find('.ElemNum:last').height();//
    				if (mainContainer.scrollTop() + mainContainer.height() == mainContainer[0].scrollHeight
    						|| mainContainer[0].scrollHeight == 0) {
    					$('.page-down').addClass('active')
    				} else {
    					$('.page-down').removeClass('active');
    				}
    				if (mainContainer.scrollTop() == 0) {
    					$('.page-top').addClass('active')
    				} else {
    					$('.page-top').removeClass('active')
    				}*/

    		});
        });
    // 房屋事件
    $("#myModal ").on("click", ".houseclick",
        function (e) {
    	
            // 取消原本事件
            e.preventDefault();
            var houseId = $(this).attr('houseId') || "";
            var buildName = $(this).attr('buildName'); // 楼栋名称
            var elemNum = $(this).attr('elemNum'); // 单元数
            var pilesNum = $(this).attr('pilesNum'); // 层数
            var doorNum = $(this).attr('doorNum'); // 门牌号
            var popId = null;
            // 判断是否为空 ，为空则不进行出发
            if (houseId == "" || houseId == undefined || houseId == null) {
                top.$.jBox.tip('暂无数据');
                return false;
            } else {
            	
                // 页面加载
                $("#house-details").load(ctx + "/pop/ccmPopTenant/getHouseMapPopList", {
                        "id": houseId,
                        "pilesNum": pilesNum,
                        "buildName": buildName,
                        "elemNum": elemNum,
                    },
                    function (data) {
                    	
                        // 隐藏 原本数据
                        $("#build-details").hide();
                        $("#house-details").show();
                        var html = '';
                        html += '<button class="jbox-button closeHouse " style="padding: 0px 10px 0px 10px;">返回</button>';
                        html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
                        $('#modal-footer').html(html);
        
                        
                    });
            }
        });

    // 人员信息弹框
    $("#myModal").on("click", ".popclick",
        function (e) {
            e.preventDefault();
            var popId = $(this).attr('popId');
            var buildName = $(this).attr('buildName'); // 楼栋名称
            var elemNum = $(this).attr('elemNum'); // 单元数
            var pilesNum = $(this).attr('pilesNum'); // 层数
            var doorNum = $(this).attr('doorNum'); // 门牌号
            $("#pop-details").load(ctx + "/pop/ccmPeople/getMapPopForm", {
                    "id": popId,
                    "pilesNum": pilesNum,
                    "buildName": buildName,
                    "elemNum": elemNum,
                },
                function () {
                	
                    $("#house-details").hide();
                });
            var html = '';
            html += '<button class="jbox-button closePop" style="padding: 0px 10px 0px 10px;">返回</button>';
            html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
            $('#modal-footer').html(html)
        });

    // 定义房屋返回事件
    $("#myModal").on("click", ".closeHouse",
        function (e) {
    	
            e.preventDefault();
            // 点击返回事件
            var html = '';
            html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
            $('#modal-footer').html(html);
            $("#build-details").show();
            $("#house-details").click();
            $("#house-details").empty();
        });

    // 定义人员返回事件
    $("#myModal").on("click", ".closePop",
        function (e) {
    	
            e.preventDefault();
            // 点击返回事件
            var html = '';
            html += '<button class="jbox-button closeHouse " style="padding: 0px 10px 0px 10px;">返回</button>';
            html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
            $('#modal-footer').html(html); 
            $("#house-details").show();
            $("#pop-details").click();
            $("#pop-details").empty();
        });

    // 定义页面向左跳转事件
    $("#myModal").on("click", ".page-left",
        function (e) {
    	
            //  当前的 页码数 
            var Num = $(".houseView .house-center.NumView").attr("Num");
            // 当前 数据为 0
            if (Num <= 0) {
            	top.$.jBox.tip('已无数据');
                return;
            }

            $(".houseView .house-center .Elem" + Number(Num - 4)).removeClass("hide");
            $(".houseView .house-center .Elem" + Num).addClass("hide");
            $(".houseView .house-center.NumView").attr("Num", Number(Num) - 4);
        });

    // 定义页面向右跳转事件
    $("#myModal").on("click", ".page-right",
        function (e) {
    	
            // 当前的 页码数 
            var Num = $(".houseView .house-center.NumView").attr("Num");
            var MaxNum = $(".houseView .house-center.NumView").attr("maxnum");
            // 当前 数据为 0
            if (Num >= MaxNum) {
                //alert("已无数据");
                top.$.jBox.tip('已无数据');
                return;
            }

            $(".houseView .house-center .Elem" + Number(Num + 4)).removeClass("hide");
            $(".houseView .house-center .Elem" + Num).addClass("hide");
            $(".houseView .house-center.NumView").attr("Num", Number(Num) + 4);
        });

})