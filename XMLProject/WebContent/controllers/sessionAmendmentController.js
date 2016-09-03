XMLApp.directive('onReadFile', function ($parse) {
    return {
        restrict: 'A',
        scope: false,
        link: function(scope, element, attrs) {
            element.bind('change', function(e) {
                var onFileReadFn = $parse(attrs.onReadFile);
                var reader = new FileReader();
                reader.onload = function() {
                    var fileContents = reader.result;
                    scope.$apply(function() {
                        onFileReadFn(scope, {
                            'contents' : fileContents,
                            'filename' : element[0].files[0].name
                        });
                    });
                };
                reader.readAsText(element[0].files[0]);
            });
        }
    };
});

XMLApp.controller('sessionAmendmentController', ['$scope', '$rootScope', '$window', '$state', '$localStorage', 'actService', 'amendmentService',
                                           function($scope, $rootScope, $window, $state, $localStorage, actService, amendmentService) {
	
	if ($rootScope.user == null) {
		$window.location.href = "#/";
	}
		
	$scope.nextSession = function() {
		$localStorage.state = 'end';
		$rootScope.state = 'end';
		$state.go('session.end');
	};
	
	actService.getInProcedureActs().then(
			function(response) {
				$scope.inProcedureActs = response.data;
			},
			function(response) {
				console.warn(response.data);
			}
		);
	
	$scope.setFile = function(contents, filename) {
        $scope.xml = contents;
        $scope.filename = filename;
    };
    
    $scope.selectedf = "";
    
	$scope.uploadFile = function() {
		amendmentService.upload($scope.xml, $rootScope.user.username, $scope.selectedf.replace("/act/", "").replace(".xml", "")).then(
				function(response) {
					if (response.data.startsWith("Upload of an XML file succeed.")) {
						console.log(response.data);
						$state.reload();
					} else {
						console.warn(response.data);
					}
				},
				function(response) {
					console.warn(response.data);
				}
			);
    };
	
	
}]);