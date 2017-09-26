<html lang="en">
<head>
    <title>Tietokonekauppa</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
    <script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <scipt src="angular-init.js"></scipt>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">
</head>
<script>
    var app = angular.module('myApp', ['ngRoute', 'ngTable']);
    app.config(function($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl : "main.html"
            })
            .when("/index.jsp", {
                templateUrl : "main.html"
            });
    });
    app.controller('HdCtrl', function($scope, $http, NgTableParams) {
        $http({
            method : "GET",
            url : "/api/disks"
        }).then(function mySuccess(response) {
            $scope.hds = response.data;
        }, function myError(response) {
            $scope.hds = response.statusText;
        });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.hds, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.hds.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
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
<body ng-app="myApp">
<div ng-view>
</div>
</body>
</html>