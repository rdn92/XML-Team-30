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

XMLApp.controller('sessionActController', ['$scope', '$rootScope', '$window', '$state', '$localStorage', 'actService',
                                           function($scope, $rootScope, $window, $state, $localStorage, actService) {
	
	if ($rootScope.user == null) {
		$window.location.href = "#/";
	}
	
	$scope.nextSession = function() {
		$localStorage.state = 'amendment';
		$rootScope.state = 'amendment';
		$state.go('session.amendment');
	};
	
	$scope.setFile = function(contents, filename) {
        $scope.xml = contents;
        $scope.filename = filename;
    };
    
	$scope.uploadFile = function() {
		actService.upload($scope.xml, $rootScope.user.username);
    };
		
}]);