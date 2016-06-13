XMLApp.controller('loginController', ['$scope', '$rootScope', '$window', 'userService', 
                                      function($scope, $rootScope, $window, userService) {
	
	if ($rootScope.user != null) {
		$window.location.href = "#/";
	} 
	
	$scope.roles = [ {
				value : 'alderman',
				label : 'Одборник'
			}, {
				value : 'president',
				label : 'Председник скупштине'
			} ];   

	$scope.login = function() {
		$scope.loginuser.role = $scope.loginuser.role.value;
		
		userService.login(angular.toJson($scope.loginuser)).then(
			function(response) {
				if (response.data.startsWith("Login")) {
					console.log("Login success!");
					$rootScope.user = $scope.loginuser;
					$window.location.href = "#/";
				} else {
					console.warn(response.data);
				}
			},
			function(response) {
				console.warn("Unable to log in.");
			}
		);
	};
}]);