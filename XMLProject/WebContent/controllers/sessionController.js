XMLApp.controller('sessionController', ['$scope', '$rootScope', '$window', '$state', '$localStorage', 
                                        function($scope, $rootScope, $window, $state, $localStorage) {
	
	if ($rootScope.user == null) {
		$window.location.href = "#/";
	}
	
//	$localStorage.state = null;
	if ($localStorage.state == null) {
		$rootScope.state = 'inactive';
		$state.go('session.start');
	} else if ($localStorage.state == 'act') {
		$rootScope.state = 'act';
		$state.go('session.act');
	} else if ($localStorage.state == 'amendment') {
		$rootScope.state = 'amendment';
		$state.go('session.amendment');
	}

}]);