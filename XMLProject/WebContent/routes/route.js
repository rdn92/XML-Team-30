var XMLApp = angular.module('XMLApp', ['ngRoute']);

XMLApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		controller : 'loginController',
		templateUrl : 'views/login.html'
	}).otherwise({
		redirectTo : '/'
	});
} ]);
