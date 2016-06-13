XMLApp.service('actService', function($http) {
	
	var root = "/XMLProject";
	
	this.upload = function(xml, username) {
		return $http.put(root + "/act/upload/" + username, xml, {
			headers : {
				'Content-Type' : "text/xml"
			}
		});
	}
});