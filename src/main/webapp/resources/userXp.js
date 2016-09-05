$("document").ready (
	function () {
		console.log("docReady" );
	
	var dynamicElement = $("#search");

		dynamicElement.change(function () {
			console.log("inChange" );
			var data = dynamicElement.val();
			var toMod = $("#ajaxUsername");
			
				$.ajax({
				url : "/goingmobile/generic/getUsernames",
				dataType : "text",
				type : "get",
				data : {
					partialName : data
				},
				success : function(data) {
					var names = data.split("===");
					for (var i = 0;i<names.length;i++) {
						var newElem = $("<option></option>").val(names[i]).html(names[i]);
						toMod.append(newElem);
						
					
						}
					console.log("End Loop" );

					},
					error : function(){
						console.log("End with error" );
						},
					
				
				})
		});


});
		
		
			
			
		
	