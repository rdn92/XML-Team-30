XMLApp.controller('sessionAmendmentController', ['$scope', '$rootScope', '$window', '$state', '$localStorage', 
                                           function($scope, $rootScope, $window, $state, $localStorage) {
	
	if ($rootScope.user == null) {
		$window.location.href = "#/";
	}
	
	$scope.nextSession = function() {
		$localStorage.state = null;
		$rootScope.state = 'inactive';
		$state.go('session', {}, {reload: true});
	};
	
}]);