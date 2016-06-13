XMLApp.controller('logoutController', ['$scope', '$rootScope', function($scope, $rootScope) {
	$scope.logout = function() {
		$rootScope.user = null;
	};
}]);