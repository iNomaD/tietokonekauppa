<html lang="en">
<head>
    <title>Tietokonekauppa</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body ng-app="myApp">
<script>
    var app = angular.module('myApp', ["ngRoute"]);
    app.config(function($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl : "main.html"
            })
            .when("/index.jsp", {
                templateUrl : "main.html"
            });
    });
    app.controller('hdCtrl', function($scope, $http) {
        $http({
            method : "GET",
            url : "/api/disks"
        }).then(function mySuccess(response) {
            $scope.hds = response.data;
        }, function myError(response) {
            $scope.hds = response.statusText;
        });
    });
    app.controller('ramCtrl', function($scope, $http) {
        $http({
            method : "GET",
            url : "/api/rams"
        }).then(function mySuccess(response) {
            $scope.rams = response.data;
        }, function myError(response) {
            $scope.rams = response.statusText;
        });
    });
    app.controller('vcCtrl', function($scope, $http) {
        $http({
            method : "GET",
            url : "/api/gpus"
        }).then(function mySuccess(response) {
            $scope.gpus = response.data;
        }, function myError(response) {
            $scope.gpus = response.statusText;
        });
    });
    app.controller('psuCtrl', function($scope, $http) {
        $http({
            method : "GET",
            url : "/api/psus"
        }).then(function mySuccess(response) {
            $scope.psus = response.data;
        }, function myError(response) {
            $scope.psus = response.statusText;
        });
    });
    app.controller('resetc',function ($scope) {
        $scope.reset = function(){
            for(var i=0;i<document.getElementsByName("input").length;i++){
                document.getElementsByTagName("input")[i].value = 0;
            }
        }
    });
    app.controller('Order', function($scope, $http) {
        $http({
            method : "POST",
            url : "/api/orders"
        }).then(function mySuccess(response) {
            $scope.myWelcome = response.data;
        }, function myError(response) {
            $scope.myWelcome = response.statusText;
        });
    });
</script>
<div ng-view>
</div>
</body>
</html>