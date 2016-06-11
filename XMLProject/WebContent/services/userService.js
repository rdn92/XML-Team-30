XMLApp.service('userService', function($http) {
	
	var root = "/XMLProject";
	this.login = function(user) {
		return $http.post(root + "/users/login", user);
	}
	
	this.register = function(user) {
		return $http.post(root + "/users/register", user);
	}
});