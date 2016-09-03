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
	
	this.setAccepted = function(akt) {
		return $http.get(root + "/act/setAccepted/" + akt);
	}
	
	this.setRefused = function(akt) {
		return $http.get(root + "/act/setRefused/" + akt);
	}
});