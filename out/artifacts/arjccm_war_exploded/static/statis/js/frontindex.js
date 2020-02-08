// JavaScript Document
(function ($) {
    $(document).ready(function () {        
        //Slideshow
        var length, 
            currentIndex = 0, 
            interval, 
            hasStarted = false,
            t = 3000;
        length = $('.slider-panel').length;        
        $('.slider-panel:not(:first)').hide(); 
        $('.slider-item:first').addClass('slider-item-selected'); 
        $('.slider-panel').hover(function() { 
            stop(); 
        }, function() { 
            start(); 
        }); 
        $('.slider-item').hover(function(e) { 
            stop(); 
            var preIndex = $(".slider-item").filter(".slider-item-selected").index(); 
            currentIndex = $(this).index(); 
            play(preIndex, currentIndex); 
        }, function() { 
            start(); 
        }); 

        function pre() { 
            var preIndex = currentIndex; 
            currentIndex = (--currentIndex + length) % length; 
            play(preIndex, currentIndex); 
        } 

        function next() { 
            var preIndex = currentIndex; 
            currentIndex = ++currentIndex % length; 
            play(preIndex, currentIndex); 
        } 

        function play(preIndex, currentIndex) { 
            $('.slider-panel').eq(preIndex).fadeOut(500) 
             .parent().children().eq(currentIndex).fadeIn(1000); 
            $('.slider-item').removeClass('slider-item-selected'); 
            $('.slider-item').eq(currentIndex).addClass('slider-item-selected'); 
        } 

        function start() { 
            if(!hasStarted) { 
                hasStarted = true; 
                interval = setInterval(next, t); 
            } 
        } 
        function stop() { 
            clearInterval(interval); 
            hasStarted = false; 
        } 
        start(); 
    }); 
})(jQuery);