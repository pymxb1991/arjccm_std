$(function(){
	  $('#mapView-p').click(function() {
			$('#mapKey').animate({'opacity': 0}, 'fast', function() {
				$(this).hide();
				$('#showMapKey').show().animate({width: 40, height: 32}, 50);
			});
		});
	  $('#showMapKey').click(function() {
			$('#showMapKey').animate({width: 0, height: 0}, 50, function() {
				$(this).hide();
				$('#mapKey').show().animate({'opacity': 1}, 'fast');
			});
		});
		
})
