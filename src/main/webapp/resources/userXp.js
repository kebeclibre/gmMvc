$("document").ready (
	function () {
		console.log("docReady" );
	
	var dynamicElement = $("#ajaxUsername");

		dynamicElement.autocomplete({
			source : function(request,response) {
				var data = dynamicElement.val();
				$.ajax({
				url : contextRoot+"generic/getUsernames",
				dataType : "text",
				type : "get",
				data : {
					partialName : data
				},
				success : function(data) {
					response( data.split("==="));
					

					},
					error : function(){
						console.log("End with error" );
						},
					
				
				})


			}
		
		}
		)
});
		
		
			
			
		
	