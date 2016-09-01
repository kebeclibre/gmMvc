function loadGPXFileIntoGoogleMap(map, filename,id) {
								$.ajax({
									url : filename,
									dataType : "xml",
									type : "get",
									data : {
										trackid : id
									},
									success : function(data) {
										var parser = new GPXParser(data, map);
										parser.setTrackColour("#ff0000"); // Set the track line colour
										parser.setTrackWidth(5); // Set the track line width
										parser.setMinTrackPointDelta(0.001); // Set the minimum distance between track points
										parser.centerAndZoom(data);
										parser.addTrackpointsToMap(); // Add the trackpoints
										parser.addWaypointsToMap(); // Add the waypoints
									},
									error : function(request, status, error) {
										console.log(request.responseText);
										//alert(request.responseText);
									}
								});
							};







$(document).ready(			
							
						function() {
							var mapOptions = {
									zoom : 15,
									mapTypeId : google.maps.MapTypeId.ROADMAP
								};
							
							var controllerUrl = "/goingmobile/generic/getTrack";
							
							var allMaps=$(".googleMap");
							
							var index;
							for (index = 0; index < allMaps.length; ++index) {
							    var idTotal = allMaps[index].id;
							    var idNum = idTotal.slice(3);
							    console.log("ID :=====>"+idNum);
							    var map = new google.maps.Map($('#map'+idNum)[0], mapOptions);

							    loadGPXFileIntoGoogleMap(map,controllerUrl,idNum);
							};

						});				