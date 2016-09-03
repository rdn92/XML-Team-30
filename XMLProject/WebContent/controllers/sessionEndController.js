XMLApp.controller('sessionEndController', ['$scope', '$rootScope', '$window', '$state', '$localStorage', 'actService', 'amendmentService',
                                             function($scope, $rootScope, $window, $state, $localStorage, actService, amendmentService) {
	
	if ($rootScope.user == null) {
		$window.location.href = "#/";
	}
		
	$scope.nextSession = function() {
		if ($scope.inProcedureAmend.length == 0 && $scope.inProcedureActs.length == 0) {
			$localStorage.state = null;
			$rootScope.state = 'inactive';
			$state.go('session', {}, {reload: true});
		}
	};
	
	actService.getInProcedureActs().then(
			function(response) {
				$scope.inProcedureActs = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	amendmentService.getInProcedureAmend().then(
			function(response) {
				$scope.inProcedureAmend = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	$scope.selectedf = "";
	$scope.selectedf2 = "";
	
	$scope.potvrdi = function() {
		if ($scope.yes > $scope.no) {
			if ($scope.selectedf != "") {
				actService.setAccepted($scope.selectedf.replace("/act/", "").replace(".xml", "")).then(
						function(response) {
							console.log("Success accepting the act!");
							$state.reload();
						},
						function(response) {
							console.warn(response.data);
						}
					);
			} else if ($scope.selectedf2 != "") {
				amendmentService.setAccepted($scope.selectedf2.replace("/amendment/", "").replace(".xml", "")).then(
						function(response) {
							console.log("Success accepting the amendment!");
							$state.reload();
						},
						function(response) {
							console.warn(response.data);
						}
					);
			}
		} else {
			if ($scope.selectedf != "") {
				actService.setRefused($scope.selectedf.replace("/act/", "").replace(".xml", "")).then(
						function(response) {
							console.log("Success refusing the act!");
							$state.reload();
						},
						function(response) {
							console.warn(response.data);
						}
					);
			} else if ($scope.selectedf2 != "") {
				amendmentService.setRefused($scope.selectedf2.replace("/amendment/", "").replace(".xml", "")).then(
						function(response) {
							console.log("Success refusing the amendment!");
							$state.reload();
						},
						function(response) {
							console.warn(response.data);
						}
					);
			}
		}
	};
	
}]);
