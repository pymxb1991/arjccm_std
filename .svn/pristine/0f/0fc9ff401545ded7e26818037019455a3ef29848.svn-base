$(function(){
	//手势操作
    var ham = new Hammer($("#content")[0], {
        domEvents: true
    });
    ham.get('press').set({ pointers: 2 });
    ham.on("press", function (event) {
        parent.window.location.href = ctx;
    });
})