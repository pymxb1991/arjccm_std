$(function() {
	// 当前用户的id
	var curUserId =$("#userId").attr("userid");
	var curUserName =$("#userId").attr("username");  
	var roomid =$("#roomid").attr("roomid");
    // 指定websocket路径
	if(roomid !=null && roomid !=""){
    var websocket;
    if ('WebSocket' in window) {
		websocket = new WebSocket("ws://"+ctxpath+"/ws?uid="+curUserId);
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://"+ctxpath+"/ws"+curUserId);
	} else {
		websocket = new SockJS("http://"+ctxpath+"/ws/sockjs"+curUserId);
	}
    // var websocket = new WebSocket('ws://localhost:8080/Spring-websocket/ws');
    websocket.onmessage = function(event) {
   	 var data=JSON.parse(event.data);
   	 	if((data.from!="0L" &&data.from!="-2L")){
   	 	  // 用户或者群消息
   	    addTalk(data.text,data.fromName ,"left",data["fromid"],"other");
   	    // 控制该信息是否显示
   	  if($("#toMsg").attr("attrid") !=data["fromid"]){
   	   $(".clearfloat."+data["fromid"]).hide();
   	  }
        // 滚动条滚动到最低部
        scrollToBottom();
        }else if(data.from=="0L"){// 上线消息
        	if(data.fromid!=curUserId)
        	{	
        	  console.log("上线消息----");
        		console.log(data.fromName);
        		// 添加用户
        		addUser(data["fromid"], data["fromName"],data["from"],data["goflag"]);
        		addUserListen($(".chat-list-people[attrid='"+data.fromid+"']"));
        		console.log(data.fromName+"上线了");
        	}
        }else if(data.from=="-2L"){// 下线消息
        	if(data.fromid!=curUserId)
        	{	
        		console.log("下线消息----");
        		console.log(data.fromName);
        		$(".chat-list-people[attrid='"+data.fromid+"']").remove();
//        		addTalk(data.fromName+"下线了",data.fromName ,"left");
        		console.log(data.fromName+"下线了");
        	}
        }
     };
	}
	
	// 显示所有人的信息
    $.post("onlineusers",function(data){
		for(var i=0;i<data.length;i++){
			addUser(data[i]["fromid"], data[i]["fromName"],data[i]["from"],data[i]["goflag"]);
		}
		// 给当前的所有人员添加点击事件
		addUserAllListen();
	});
    
  // 发送信息
  $("#chat-fasong").click(
    function() {
      // 短息发送的类型
      var type = $("#toMsg").attr("attrtype");
      // 短信的内容
      var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>');
      // 短信显示
      if (textContent != "") {
        // 添加短信
          addTalk(textContent,"我" ,"right" , $("#toMsg").attr("attrid"),"self");
        // 发送后清空输入框
        $(".div-textarea").html("");
        // 聊天框默认最底部
        $("#chatBox-content-demo").scrollTop(
            $("#chatBox-content-demo")[0].scrollHeight);
        // 开始发送
        if (type == "RL") {
          // 房间内广播
          var roomid = $("#toMsg").attr("attrid");
          $.post(ctx + "/exchange/deemo/broadcast", {
            "fromName" :curUserName+"(群发)",
            "roomid" : roomid,
            "text" : textContent
          });
        } else {
          // 对人员发送
          var v = textContent;
          var toUser = $("#toMsg").attr("attrid");
          if (toUser == null || toUser == "") {
            alert("请选择一个人进行发送.");
            return;
          }
          if (v == "") {
            return;
          } else {
            var data = {};
            data["fromType"] = "-1L";
            data["fromid"] = curUserId;
            data["fromName"] = curUserName;
            data["to"] = toUser;
            data["text"] = v;
            console.log("-------------");
            console.log(data);
            websocket.send(JSON.stringify(data));
            scrollToBottom();
          }
        }
      } else {
    	  mui.toast('发送内容不能为空',{
    		  duration : 'short',
    		  type : 'div'
    	  });
    	  return;
      }
    });
});

function scrollToBottom(){
  $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
}

// 添加用户信息
function addUser(id,username,type,goflag) {
	var typeImg = "head";
	// 判断是不是房间
	if(type =="RL"){
		typeImg = "users";
	}
	// 插入的人员信息
	var insertLabel = '<div class="chat-list-people" goflag='+goflag+'   attrtype='+type+' attrid="' + id
			+ '"> <div>' + '<img src="' + ctxStatic
			+ '/wechat/img/'+typeImg+'.png" alt="头像" /></div>'
			+ '<div class="chat-name"><p>' + username + '</p></div>'
			// 添加消息数
			// +'<div class="message-num"></div>';
			+ '</div>'
	$(".chatBox-list").append(insertLabel);
}

// 添加 用户监听器
function addUserAllListen(){
	$(".chat-list-people").each(function() {
	    $(this).click(function() {
	      var goflag =   $(this).attr("goflag");
	      var roomtype =   $(this).attr("attrtype");
	      // 该单元是房间 且 未进入该房间
	        if(goflag == "false" &&  roomtype =="RL"){
	          // 进行跳转页面
	          location.replace(ctx + "/exchange/deemo/chat?roomid="+$(this).attr("attrid"));
	        }
	        var n = $(this).index();
	        $(".chatBox-head-one").toggle();
	        $(".chatBox-head-two").toggle();
	        $(".chatBox-list").fadeToggle();
	        $(".chatBox-kuang").fadeToggle();
	        // 传名字
	        $("#toMsg").attr("attrid",$(this).attr("attrid"));
	        $("#toMsg").attr("attrtype",$(this).attr("attrtype"));
	        // 控制显示输出 
	        $(".clearfloat").hide();   
	        $(".clearfloat."+$(this).attr("attrid")).show();
	        
	        // 传输
	        $(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());
	        var winWidth = $(window).innerWidth();
	        $(".ChatInfoName").css("width",winWidth-106);
	        /*
           * //传头像 $(".ChatInfoHead>img").attr("src",
           * $(this).children().eq(0).children("img").attr("src"))
           */;
	        // 聊天框默认最底部
	        $(document).ready(function() {
	            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
	        });
	    })
	});
}

//添加 用户监听器
function addUserListen(that){
        that.click(function() {
          var n = $(this).index();
          $(".chatBox-head-one").toggle();
          $(".chatBox-head-two").toggle();
          $(".chatBox-list").fadeToggle();
          $(".chatBox-kuang").fadeToggle();
          // 传名字
          $("#toMsg").attr("attrid",$(this).attr("attrid"));
          $("#toMsg").attr("attrtype",$(this).attr("attrtype"));
          // 控制显示输出
          $(".clearfloat").hide();   
          $(".clearfloat."+$(this).attr("attrid")).show();
          // 传输
          $(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());
          var winWidth = $(window).innerWidth();
          $(".ChatInfoName").css("width",winWidth-106);
          /*
           * //传头像 $(".ChatInfoHead>img").attr("src",
           * $(this).children().eq(0).children("img").attr("src"))
           */;
          // 聊天框默认最底部
          $(document).ready(function() {
              $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
          });
      })
}


// 添加显示谈话消息
/**
 * 
 * @param textContent 内容
 * @param fromName 发送人名称
 * @param float 浮动类型
 * @param id 发送者id
 * @returns
 */
function addTalk(textContent,fromName ,float,id,self){

  if(self !="self"){
    textContent = textContent.replace(/&lt;br&gt;/g, '<br>');
    console.log("text:"+textContent);
  }else{
    textContent = textContent.replace(/[\n\r]/g, '<br>');
  }
	    if (textContent != "") {
	      var keyContent = "";
	      var msgContent  = "<div class=\"chat-message\"> " + textContent + " </div> ";
	      var imgContent  = "<div class=\"chat-avatars\"><img " +" onerror='this.src=\""+ctxStatic+"/wechat/img/head.png\"' src=\"img/icon01.png\" alt=\"头像\" /></div> ";
	      
	      if(float == "left"){
	        keyContent  = imgContent +msgContent;
	      }else{
	        keyContent  = msgContent +imgContent;
	      }
	      
	        $(".chatBox-content-demo").append("<div class=\"clearfloat  "+id+"\">"
	        		+ "<div class=\"author-name\"><small class=\"chat-date\">"+new Date().format('yyyy-MM-dd  hh:mm:ss')+"<small class ='talk"+float+"Tab'>"+ fromName+"</small> </div> " 
	        		+ "<div class=\""+float+"\"> "
	        		+  keyContent
	        		+ "</div> </div>");
	    }
}


// 时间 格式化
Date.prototype.format = function(format) { 
    var date = { 
           "M+": this.getMonth() + 1, 
           "d+": this.getDate(), 
           "h+": this.getHours(), 
           "m+": this.getMinutes(), 
           "s+": this.getSeconds(), 
           "q+": Math.floor((this.getMonth() + 3) / 3), 
           "S+": this.getMilliseconds() 
    }; 
    if (/(y+)/i.test(format)) { 
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length)); 
    } 
    for (var k in date) { 
           if (new RegExp("(" + k + ")").test(format)) { 
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length)); 
           } 
    } 
    return format; 
} 