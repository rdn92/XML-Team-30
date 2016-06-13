XMLApp.controller('sessionStartController', ['$scope', '$rootScope', '$window', '$state', '$localStorage', 
                                             function($scope, $rootScope, $window, $state, $localStorage) {
	
	if ($rootScope.user == null) {
		$window.location.href = "#/";
	}
		
	$scope.nextSession = function() {
		$localStorage.state = 'act';
		$rootScope.state = 'act';
		$state.go('session.act');
	};
	
}]);