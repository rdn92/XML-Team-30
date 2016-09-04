XMLApp.service('actService', function($http) {
	
	var root = "/XMLProject";
	
	this.upload = function(xml, username) {
		return $http.put(root + "/act/upload/" + username, xml, {
			headers : {
				'Content-Type' : "text/xml"
			}
		});
	}
	
	this.getInProcedureActs = function() {
		return $http.get(root + "/act/getInProcedureActs");
	}
	
	this.getInProcedureActsU = function(user) {
		return $http.get(root + "/act/getInProcedureActsU/" + user);
	}
	
	this.setAccepted = function(akt) {
		return $http.get(root + "/act/setAccepted/" + akt);
	}
	
	this.setRefused = function(akt) {
		return $http.get(root + "/act/setRefused/" + akt);
	}
	
	this.getAllActs = function() {
		return $http.get(root + "/act/getAllActs");
	}
	
	this.search = function(s) {
		return $http.get(root + "/act/search/" + s);
	}
});