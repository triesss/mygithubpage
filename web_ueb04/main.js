$(document).ready(function(){

	$('#search').change(function(){
		if($(this).val().length !== 0){
			var data = {
						'action': 'search',
						'search':	$(this).val()
					};
			var url = 'logic.php';
			$.ajax({
				async:		true,
				type:		'POST',
				url:		url,
				data:		data,
				dataType:  "json",
				success:	function(data, textStatus, jqXHR) {
								if(data.status == 'success') {
									var output = '';
									$.each(data.data, function(index, val) {
										output = '<li>';
										output += '<a href="article.php?id='+val[0]+'" ><strong>'+val[1]+'</strong></a>';
										output += '</li>';
									});
									$('.search_container').html(output);
								} else if(data.status == 'empty'){
									
								}
								
						}
				}); // ajax
		} // if
	}); // change

});