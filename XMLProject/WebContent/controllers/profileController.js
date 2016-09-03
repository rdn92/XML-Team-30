XMLApp.controller('profileController', ['$scope', '$rootScope', '$window', '$state', 
                                        function($scope, $rootScope, $window, $state) {
	
	if ($rootScope.user == null && $rootScope.user.role != 'alderman') {
		$window.location.href = "#/";
	}
	
	
	
}]);