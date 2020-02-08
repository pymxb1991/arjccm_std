/**
 * Created by oHa on 2018/9/17.
 */
$.fn.extend({
    jsplit: function(j) {
        return this.each(function() {
            j = j || {};
            j.Btn = j.Btn || {};
            j.Btn.oBg = j.Btn.oBg || {};
            j.Btn.cBg = j.Btn.cBg || {};
            var jun = { MaxW: "400px"
                , MinW: "60px"
                , FloatD: "left"
                , IsClose: false
                , Bg: "#fff"
                , Btn: { btn: true
                    , oBg: { Out: "#333", Hover: "orange" }
                    , cBg: { Out: "#333", Hover: "orange" }
                }
                , Fn: function() { }
            }
            j.MaxW = parseInt(j.MaxW) || parseInt(jun.MaxW);
            j.MinW = parseInt(j.MinW) || parseInt(jun.MinW);
            j.FloatD = j.FloatD || jun.FloatD;
            j.IsClose = j.IsClose != undefined ? j.IsClose : jun.IsClose;
            j.Bg = j.Bg || jun.Bg;
            j.Btn.btn = j.Btn.btn != undefined ? j.Btn.btn : jun.Btn.btn;
            j.Btn.oBg.Out = j.Btn.oBg.Out || jun.Btn.oBg.Out;
            j.Btn.oBg.Hover = j.Btn.oBg.Hover || jun.Btn.oBg.Hover;
            j.Btn.cBg.Out = j.Btn.cBg.Out || jun.Btn.cBg.Out;
            j.Btn.cBg.Hover = j.Btn.cBg.Hover || jun.Btn.cBg.Hover;
            j.Fn = j.Fn || jun.Fn;
            var antiD = j.FloatD == "left" ? "right" : "left";
            if (j.MinW > j.MaxW) {
                var amax = j.MaxW;
                j.MaxW = j.MinW;
                j.MinW = amax;
            };
            var _self = this;
            var Close = false;
            $(_self).css({ position: "relative", float: j.FloatD, overflow: "hidden", padding: "0px" });
            $('#openClose').wrapInner("<div class='jsplit-c' style='top:0px;z-index:9999;zoom:1;width:100%;overflow:hidden;position:relative;height:100%'></div>");
            $('#openClose').children(".jsplit-c").append("<div class='jsplit-e' unselectable='on' style='background:#fff;height:100%;width:6px;top:0px;-moz-user-select:none;" + antiD + ":0px;position:absolute;cursor:e-resize;overflow:hidden;z-index:10000;'><div class='jsplit-e-handle'  unselectable='on'  style='height:40px;width:100%;top:50%;margin-top:-20px;left:0;position:absolute;cursor:pointer;-moz-user-select:none;'></div></div>");
            var dw = $(_self).width();
            var jsplitc = $('#openClose').children(".jsplit-c");
            var jsplite = jsplitc.children(".jsplit-e");
            var jsplith = jsplite.children(".jsplit-e-handle");
            if (j.Btn.btn == false) { jsplith.css({ display: "none" }) };

            if (dw > j.MaxW) { $(_self).css({ width: j.MaxW }); }
            if (dw < j.MinW) { $(_self).css({ width: j.MinW }); }

            $(_self).hover(function() { if (Close == false) jsplite.stop().animate({ opacity: 0.85 }, 200) }, function() { if (Close == false) jsplite.stop().animate({ opacity: 0 }, 2000) })
            jsplite.mousedown(function(e) {
            if ($('#openClose').hasClass("open")) {
            		return false;
            	};
                j['Fn'] && j['Fn'].call(_self);
                var screenX = e.screenX, w = $(_self).width();
                $(document).add($("iframe").contents()).mousemove(function(e2) {
                    curW = j.FloatD == "left" ? w + (e2.screenX - screenX) : w - (e2.screenX - screenX);
                    if (curW >= j.MaxW) { curW = j.MaxW; };
                    if (curW <= j.MinW) { curW = j.MinW; };
                    $(_self).css({ width: curW });
                    dw = curW;
                });
                $(document).add($("iframe").contents()).mouseup(function() {
                	wSize();
                    $(document).add($("iframe").contents()).unbind();
                });
                if (Close == true) {
                	console.log(3)
                    $(this).css({ cursor: "e-resize", opacity: 0.8 });
                    $(_self).animate({ width: dw }, 200);
                    Close = false;
                };
                return false;
            });
        });
    }
});