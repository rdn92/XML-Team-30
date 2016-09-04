XMLApp.controller('profileController', ['$scope', '$rootScope', '$window', '$state', 'actService', 'amendmentService',
                                        function($scope, $rootScope, $window, $state, actService, amendmentService) {
	
	if ($rootScope.user == null && $rootScope.user.role != 'alderman') {
		$window.location.href = "#/";
	}
	
	actService.getInProcedureActsU($rootScope.user.username).then(
			function(response) {
				$scope.inProcedureActs = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	amendmentService.getInProcedureAmendU($rootScope.user.username).then(
			function(response) {
				$scope.inProcedureAmend = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	$scope.potvrdi = function() {
		if ($scope.selectedf != "") {
			actService.setRefused($scope.selectedf.replace("/act/", "").replace(".xml", "")).then(
					function(response) {
						console.log("Success withdrawing the act!");
						$state.reload();
					},
					function(response) {
						console.warn(response.data);
					}
				);
		} else if ($scope.selectedf2 != "") {
			amendmentService.setRefused($scope.selectedf2.replace("/amendment/", "").replace(".xml", "")).then(
					function(response) {
						console.log("Success withdrawing the amendment!");
						$state.reload();
					},
					function(response) {
						console.warn(response.data);
					}
				);
		}
	};
	
}]);