$(document).ready(
    function() {
      // 当前用户的id
      var curUserId = $("#userId").attr("userid");
      var curUserName = $("#userId").attr("username");
      // 指定websocket路径
      var websocket;
      if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + ctxpath + "/ws?uid=" + curUserId);
      } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + ctxpath + "/ws" + curUserId);
      } else {
        websocket = new SockJS("http://" + ctxpath + "/ws/sockjs" + curUserId);
      }
      // var websocket = new
      // WebSocket('ws://localhost:8080/Spring-websocket/ws');
      websocket.onmessage = function(event) {
        var data = JSON.parse(event.data);
        console.log(data)
        if ((data.from != "0L" && data.from != "-2L") || data.from == "-1L") {// 用户或者群消息
          // 接收服务端的实时消息并添加到HTML页面中
          var textContent = data.text;
          textContent = textContent.replace(/&lt;br&gt;/g, '<br>');
          console.log("text:" + textContent);
          $("#log-container").append(
              "<div class='bg-info'><label class='text-danger'>"
                  + data.fromName + "&nbsp;"
                  + new Date().format('yyyy-MM-dd  hh:mm:ss')
                  + "</label><div class='text-success'>" + textContent
                  + "</div></div><br>");
          // 滚动条滚动到最低部
          scrollToBottom();
        } else if (data.from == "0L") {// 上线消息
          if (data.fromName != curUserName) {
            // 自己不能被点击
            if (data.fromid == curUserId) {
              $("#users").append(
                  '<a attrid=' + data["fromid"] + ' class="list-group-item">'
                      + data.fromName + '</a>');
            } else {
              // 可以给其他人发送
              $("#users").append(
                  '<a href="#" attrid=' + data["fromid"]
                      + ' onclick="talk(this)" class="list-group-item">'
                      + data.fromName + '</a>');
            }
            // 显示 上线信息
            $("#log-container").append(
                "<div class='bg-info'><label class='text-danger'>"
                    + data.fromName + "&nbsp;"
                    + new Date().format('yyyy-MM-dd  hh:mm:ss')
                    + "</label><div class='text-success'>" + data.fromName
                    + "上线了" + "</div></div><br>");
            // console.log(data.fromName + "上线了");
          }
        } else if (data.from == "-2L") {// 下线消息
          if (data.fromName != curUserName) {
            console.log("下线消息----");
            console.log(data.fromName);
            $("#users > a").remove(":contains('" + data.fromName + "')");
            $("#log-container").append(
                "<div class='bg-info'><label class='text-danger'>"
                    + data.fromName + "&nbsp;"
                    + new Date().format('yyyy-MM-dd  hh:mm:ss')
                    + "</label><div class='text-success'>" + data.fromName
                    + "下线了" + "</div></div><br>");
            console.log(data.fromName + "下线了");
          }
        }
      };

      // 在线
      $.post("onlineusers", function(data) {
        for (var i = 0; i < data.length; i++)
          if (data[i].from == "0L") {
            // 当前不是自己
            if (data[i].fromid != curUserId) {
              $("#users").append(
                  '<a href="#" onclick="talk(this)"  sendtype="0L"  attrid='
                      + data[i]["fromid"] + ' class="list-group-item">'
                      + data[i]["fromName"] + '</a>');
            }
          } else {
            var roomname = data[i]["fromName"];
            // 已经进入的
            if (roomname.indexOf("进入") != -1) {
              $("#rooms").append(
                  '<a href="#" onclick="talk(this)"  sendtype="RL"   attrid='
                      + data[i]["fromid"] + ' attrname =' + data[i]["fromName"]
                      + ' class="list-group-item">' + data[i]["fromName"]
                      + '</a>');
            } else {
              // 显示用
              $("#rooms").append(
                  '<a   sendtype="RL"   attrid=' + data[i]["fromid"]
                      + ' attrname =' + data[i]["fromName"]
                      + ' class="list-group-item">' + data[i]["fromName"]
                      + '</a>');
            }
          }
      });

      $("#send").click(
          function() {
            var v = $("#myinfo").val();
            if(isEmpty(v)){
              alert("您的内容不能为空，谢谢！");
              return;
            }
            // 发送的类别
            var sendtype = $(this).attr("sendtype");
            console.log(sendtype);
            var fromName = $("#talktitle").attr("attrname");
            var toUser = $("#talktitle").attr("attrid");
            if (toUser == null || toUser == "") {
              alert("请选择一个人进行发送.");
              return;
            }
            if (sendtype == "RL") {
              // 广播群发
              $.post("broadcast", {
                "fromName" : curUserName + "(群发)",
                "roomid" : toUser,
                "text" : $("#myinfo").val()
              });
            } else {
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
              }
            }
            //
            var broadmessage = (sendtype == "RL") ? "（群发）" : ""
            $("#log-container").append(
                "<div class='bg-success'><label class='text-info'>我"
                    + broadmessage + "&nbsp;"
                    + new Date().format('yyyy-MM-dd  hh:mm:ss')
                    + "</label><div class='text-info'>" + v
                    + "</div></div><br>");
            scrollToBottom();
            $("#myinfo").val("");
          });

    });

function talk(a) {
  // console.log(a.getAttribute("attrid"));
  $("#talktitle").text("与" + a.innerHTML + "的聊天");
  // 发送类别
  $("#send").attr("sendtype", a.getAttribute("sendtype"));

  $("body").data("to", a.innerHTML);
  $("#talktitle").attr("attrid", a.getAttribute("attrid"));
  $("#talktitle").attr("attrname", a.getAttribute("attrname"))

}
function scrollToBottom() {
  var div = document.getElementById('log-container');
  div.scrollTop = div.scrollHeight;
}

Date.prototype.format = function(format) {
  var date = {
    "M+" : this.getMonth() + 1,
    "d+" : this.getDate(),
    "h+" : this.getHours(),
    "m+" : this.getMinutes(),
    "s+" : this.getSeconds(),
    "q+" : Math.floor((this.getMonth() + 3) / 3),
    "S+" : this.getMilliseconds()
  };
  if (/(y+)/i.test(format)) {
    format = format.replace(RegExp.$1, (this.getFullYear() + '')
        .substr(4 - RegExp.$1.length));
  }
  for ( var k in date) {
    if (new RegExp("(" + k + ")").test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k]
          : ("00" + date[k]).substr(("" + date[k]).length));
    }
  }
  return format;
}

function isEmpty(obj) {
  if (typeof obj == "undefined" || obj == null || obj == "") {
      return true;
  } else {
      return false;
  }
}