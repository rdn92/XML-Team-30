XMLApp.controller('loginController', ['$scope', '$window', 'userService', function($scope, $window, userService) {
	
	if ($scope.user != null) {
		alert($scope.user);
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
					$scope.user = $scope.loginuser;
					alert($scope.user);
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