XMLApp.controller('searchController', ['$scope', '$rootScope', '$window', '$state', 'actService', 'amendmentService',
                                        function($scope, $rootScope, $window, $state, actService, amendmentService) {
		
	actService.getAllActs().then(
			function(response) {
				$scope.acts = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	amendmentService.getAllAmend().then(
			function(response) {
				$scope.amendments = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	$scope.term = "";
	
	$scope.search = function() {
		if ($scope.term == "") {
			actService.getAllActs().then(
					function(response) {
						$scope.acts = response.data;
					},
					function(response) {
						console.warn(response.data);
					}
				);
			
			amendmentService.getAllAmend().then(
					function(response) {
						$scope.amendments = response.data;
					},
					function(response) {
						console.warn(response.data);
					}
				);
		} else {
			actService.search($scope.term).then(
					function(response) {
						$scope.acts = response.data;
					},
					function(response) {
						console.warn(response.data);
					}
				);
			
			amendmentService.search($scope.term).then(
					function(response) {
						$scope.amendments = response.data;
					},
					function(response) {
						console.warn(response.data);
					}
				);
		}
	};
	
//	$scope.potvrdi = function() {
//		if ($scope.selectedf != "") {
//			actService.setRefused($scope.selectedf.replace("/act/", "").replace(".xml", "")).then(
//					function(response) {
//						console.log("Success withdrawing the act!");
//						$state.reload();
//					},
//					function(response) {
//						console.warn(response.data);
//					}
//				);
//		} else if ($scope.selectedf2 != "") {
//			amendmentService.setRefused($scope.selectedf2.replace("/amendment/", "").replace(".xml", "")).then(
//					function(response) {
//						console.log("Success withdrawing the amendment!");
//						$state.reload();
//					},
//					function(response) {
//						console.warn(response.data);
//					}
//				);
//		}
//	};
	
}]);