XMLApp.service('amendmentService', function($http) {
	
	var root = "/XMLProject";
	
	this.upload = function(xml, username, filename) {
		return $http.put(root + "/amendment/upload/" + username + "/" + filename, xml, {
			headers : {
				'Content-Type' : "text/xml"
			}
		});
	}
	
	this.getInProcedureAmend = function() {
		return $http.get(root + "/amendment/getInProcedureAmend");
	}
	
	this.setAccepted = function(am) {
		return $http.get(root + "/amendment/setAccepted/" + am);
	}
	
	this.setRefused = function(am) {
		return $http.get(root + "/amendment/setRefused/" + am);
	}
	
});