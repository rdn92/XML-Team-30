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
	
	this.getInProcedureAmendU = function(user) {
		return $http.get(root + "/amendment/getInProcedureAmendU/" + user);
	}
	
	this.setAccepted = function(am) {
		return $http.get(root + "/amendment/setAccepted/" + am);
	}
	
	this.setRefused = function(am) {
		return $http.get(root + "/amendment/setRefused/" + am);
	}
	
	this.getAllAmend = function() {
		return $http.get(root + "/amendment/getAllAmend");
	}
	
	this.search = function(s) {
		return $http.get(root + "/amendment/search/" + s);
	}
	
});