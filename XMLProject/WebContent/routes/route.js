var XMLApp = angular.module('XMLApp', ['ui.router', 'ngStorage']);

XMLApp.config(function($stateProvider, $urlRouterProvider) {
	
    $stateProvider
	    .state('home', {
	        url: '/',
	        templateUrl : 'views/home.html'
	    })
        .state('login', {
            url: '/login',
            templateUrl : 'views/login.html',
            controller: 'loginController'
        })
        .state('session', {
            url: '/session',
            templateUrl: 'views/session.html',
            controller: 'sessionController'
        })
        .state('session.start', {
            url: '/sessionstart',
            templateUrl: 'views/sessionStart.html',
            controller: 'sessionStartController'
        })
        .state('session.act', {
            url: '/sessionact',
            templateUrl: 'views/sessionAct.html',
            controller: 'sessionActController'
        })
        .state('session.amendment', {
            url: '/sessionamendment',
            templateUrl: 'views/sessionAmendment.html',
            controller: 'sessionAmendmentController'
        })
        .state('session.end', {
            url: '/sessionend',
            templateUrl: 'views/sessionEnd.html',
//            controller: 'sessionActController'
        });
    
    $urlRouterProvider.otherwise('/');
});