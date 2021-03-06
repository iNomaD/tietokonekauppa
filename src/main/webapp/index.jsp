<html lang="en">
<head>
    <title>Tietokonekauppa</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
    <script src="https://code.angularjs.org/1.6.4/angular-cookies.js"></script>
    <script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <scipt src="angular-init.js"></scipt>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/simple_table.css">
    <link rel="stylesheet" href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">
</head>
<script>

//    $window.sessionStorage.id = "-1";
//    sessionStorage.login = "Vasya";
//    sessionStorage.email = "Gr@yd";
//    sessionStorage.first_name = "V";
//    sessionStorage.last_name = "P";
//    sessionStorage.role = "?";
//    sessionStorage.token = "42";

    var app = angular.module('myApp', ['ngRoute', 'ngTable','ngCookies']);
    app.config(function($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl : "templates/main.html"
            })
            .when("/index.jsp", {
                templateUrl : "templates/main.html"
            })
            .when("/disks/:diskID",{
            templateUrl : "templates/DiskInformation.html"

            })
            .when("/cpus/:cpuID",{
                templateUrl : "templates/CPUInformation.html"

            })
            .when("/mbs/:mbID",{
                templateUrl : "templates/MBInformation.html"

            })
            .when("/cases/:caseID",{
                templateUrl : "templates/CaseInformation.html"
            })
            .when("/rams/:ramID",{
                templateUrl : "templates/RAMInformation.html"
            })
            .when("/gpus/:gpuID",{
                templateUrl : "templates/GPUInformation.html"
            })
            .when("/psus/:psuID",{
                templateUrl : "templates/PSUInformation.html"
            })
            .when("/orders/",{
                templateUrl : "/templates/Orders.html"
            })
            .when("/signup/",{
                templateUrl : "/users/singup/index.html"
            })
            .when("/signin/",{
                templateUrl : "/users/singin/index.html"
            })
            .when("/logout/",{
                templateUrl : "/users/logout/index.html"
            });
    });
    app.service('sharedProperties', function () {
        var currency = 0;
        var curUrl = "/#!";
        var orders;
        this.currency = currency;
        this.curUrl =  curUrl;
        this.orders = orders;

    });
    app.controller('HdCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if($scope.currency == 0)
        $http({
            method : "GET",
            url : "/api/disks?price_units=USD"
        }).then(function mySuccess(response) {
            $scope.hds = response.data;
        }, function myError(response) {
            $scope.hds = response.statusText;
        });
        else
            $http({
                method : "GET",
                url : "/api/disks?price_units=EUR"
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
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/disks/"+x);
        };
    });
    app.controller('ramCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/rams?price_units=USD"
            }).then(function mySuccess(response) {
                $scope.rams = response.data;
            }, function myError(response) {
                $scope.rams = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/rams?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.rams = response.data;
            }, function myError(response) {
                $scope.rams = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.rams, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.rams.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/rams/"+x);
        };
    });
    app.controller('vcCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/gpus?price_units=USD"
            }).then(function mySuccess(response) {
                $scope.gpus = response.data;
            }, function myError(response) {
                $scope.gpus = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/gpus?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.gpus = response.data;
            }, function myError(response) {
                $scope.gpus = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.gpus, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.gpus.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/gpus/"+x);
        };
    });
    app.controller('caseCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if ($scope.currency == 0)
            $http({
                method: "GET",
                url : "/api/cases?price_units=USD"
            }).then(function mySuccess(response) {
                $scope.cases = response.data;
            }, function myError(response) {
                $scope.cases = response.statusText;
            });
        else
            $http({
                method: "GET",
                url: "/api/cases?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.cases = response.data;
            }, function myError(response) {
                $scope.cases = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.cases, // length of data
            getData: function ($defer, params) {
                $defer.resolve($scope.cases.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/cases/"+x);
        }
    });
    app.controller('cpuCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/processors?price_units=USD"
            }).then(function mySuccess(response) {
                $scope.cpus = response.data;
            }, function myError(response) {
                $scope.cpus = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/processors?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.cpus = response.data;
            }, function myError(response) {
                $scope.cpus = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.cpus, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.cpus.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/cpus/"+x);
        };
    });
    app.controller('mbCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/motherboards?price_units=USD"
            }).then(function mySuccess(response) {
                $scope.mbs = response.data;
            }, function myError(response) {
                $scope.mbs = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/motherboards?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.mbs = response.data;
            }, function myError(response) {
                $scope.mbs = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.mbs, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.mbs.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/mbs/"+x);
        };
    });
    app.controller('psuCtrl', function($scope, $http, NgTableParams, sharedProperties,$location) {
        $scope.currency = sharedProperties.currency;
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/psus?price_units=USD"
            }).then(function mySuccess(response) {
                $scope.psus = response.data;
            }, function myError(response) {
                $scope.psus = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/psus?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.psus = response.data;
            }, function myError(response) {
                $scope.psus = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.psus, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.psus.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
        $scope.redirect = function(x){
            sharedProperties.cUrl = $location.path("/psus/"+x);
        };
    });
    app.controller('resetc',function ($scope) {
        $scope.reset = function(){
            for(var i=0;i<document.getElementById("mb").getElementsByTagName('input').length;i++){
                document.getElementById("mb").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=0;i<document.getElementById("cpu").getElementsByTagName('input').length;i++){
                document.getElementById("cpu").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=0;i<document.getElementById("ram").getElementsByTagName('input').length;i++){
                document.getElementById("ram").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=0;i<document.getElementById("hd").getElementsByTagName('input').length;i++){
                document.getElementById("hd").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=0;i<document.getElementById("vc").getElementsByTagName('input').length;i++){
                document.getElementById("vc").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=0;i<document.getElementById("psu").getElementsByTagName('input').length;i++){
                document.getElementById("psu").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=0;i<document.getElementById("case").getElementsByTagName('input').length;i++){
                   document.getElementById("case").getElementsByTagName('input')[i].value = 0;
            }
        }
    });
    app.controller('Order', function($scope, $http,sharedProperties,$location,$window) {
        $scope.orderreq = function orderreq(notes) {
            if($window.sessionStorage.token == undefined){
                console.log($window.sessionStorage.token);
                document.getElementById('error').innerHTML="Register please";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                $location.path("/signup");
                return;
            }

            $scope.order = "[";
            for (var i = 0; i < document.getElementById("mb").getElementsByTagName('input').length; i++) {
                if (document.getElementById("mb").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("mb").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('mb').getElementsByTagName('td')[7 * i + 6].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"Motherboard\"},';
                    }
                }
            }
            for (var i = 0; i < document.getElementById("cpu").getElementsByTagName('input').length; i++) {
                if (document.getElementById("cpu").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("cpu").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('cpu').getElementsByTagName('td')[5 * i + 4].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"CPU\"},';
                    }
                }
            }
            for (var i = 0; i < document.getElementById("ram").getElementsByTagName('input').length; i++) {
                if (document.getElementById("ram").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("ram").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('ram').getElementsByTagName('td')[7 * i + 6].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"RAM\"},';
                    }
                }
            }
            for (var i = 0; i < document.getElementById("hd").getElementsByTagName('input').length; i++) {
                if (document.getElementById("hd").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("hd").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('hd').getElementsByTagName('td')[6 * i + 5].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"Disk\"},';
                    }
                }
            }
            for (var i = 0; i < document.getElementById("vc").getElementsByTagName('input').length; i++) {
                if (document.getElementById("vc").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("vc").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('vc').getElementsByTagName('td')[6 * i + 5].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"GPU\"},';
                    }
                }
            }
            for (var i = 0; i < document.getElementById("psu").getElementsByTagName('input').length; i++) {
                if (document.getElementById("psu").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("psu").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('psu').getElementsByTagName('td')[5 * i + 4].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"PSU\"},';
                    }
                }
            }
            for (var i = 0; i < document.getElementById("case").getElementsByTagName('input').length; i++) {
                if (document.getElementById("case").getElementsByTagName('input')[i].value > 0) {
                    for (var j = 0; j < document.getElementById("case").getElementsByTagName('input')[i].value; j++) {
                        $scope.order += '{\"id\": ' + document.getElementById('case').getElementsByTagName('td')[5 * i + 4].getElementsByTagName('div')[1].innerHTML + ', \"@type\": \"Case\"},';
                    }
                }
            }
            if(notes == undefined)
                notes ="";
            if ($scope.order != "[") {
                if ($scope.order[$scope.order.length - 1] == ',')
                    $scope.order = $scope.order.substr(0, $scope.order.length - 1) + ']';
                else
                    $scope.order += ']';

                $http({
                    method: "POST",
                    headers: {
                        'Authorization': 'Bearer '+$window.sessionStorage.token,
                        'Content-Type': 'application/json'
                    },
                    url: "/api/orders?notes=" + notes,
                    data: $scope.order
                })
                $location.path("/orders");
            }
            else{
                document.getElementById('error').innerHTML="Please choose parts";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
        };
    });
    app.controller('DiskInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.diskid = $routeParams.diskID;
        $http({
                method : "GET",
                url : "/api/disks/"+$scope.diskid
            }).then(function mySuccess(response) {
                $scope.disk = response.data;
            }, function myError(response) {
                $scope.disk = response.statusText;
        });
        $http({
            method : "GET",
            url : "/api/disks/"+$scope.diskid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/disks/"+$scope.diskid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/disks/"+$scope.diskid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }};
    });
    app.controller('MBInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.mbid = $routeParams.mbID;
        $http({
                method : "GET",
                url : "/api/motherboards/"+$scope.mbid
            }).then(function mySuccess(response) {
                $scope.mb = response.data;
            }, function myError(response) {
                $scope.mb = response.statusText;
            });
        $http({
            method : "GET",
            url : "/api/mbs/"+$scope.mbid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/mbs/"+$scope.mbid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/mbs/"+$scope.mbid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }}
    });
    app.controller('CPInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.cpuid = $routeParams.cpuID;
        $http({
            method : "GET",
            url : "/api/processors/"+$scope.cpuid
        }).then(function mySuccess(response) {
            $scope.cpu = response.data;
        }, function myError(response) {
            $scope.cpu = response.statusText;
        });
        $http({
            method : "GET",
            url : "/api/processors/"+$scope.cpuid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/processors/"+$scope.cpuid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/processors/"+$scope.cpuid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }}
    });
    app.controller('RAMInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.ramid = $routeParams.ramID;
        $http({
            method : "GET",
            url : "/api/rams/"+$scope.ramid
        }).then(function mySuccess(response) {
            $scope.ram = response.data;
        }, function myError(response) {
            $scope.ram = response.statusText;
        });
        $http({
            method : "GET",
            url : "/api/rams/"+$scope.ramid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/rams/"+$scope.ramid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/rams/"+$scope.ramid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }}
    });
    app.controller('GPUInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.gpuid = $routeParams.gpuID;
        $http({
            method : "GET",
            url : "/api/gpus/"+$scope.gpuid
        }).then(function mySuccess(response) {
            $scope.gpu = response.data;
        }, function myError(response) {
            $scope.gpu = response.statusText;
        });
        $http({
            method : "GET",
            url : "/api/gpus/"+$scope.gpuid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/gpus/"+$scope.gpuid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/gpus/"+$scope.gpuid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }}
    });
    app.controller('PSUInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.psuid = $routeParams.psuID;
        $http({
            method : "GET",
            url : "/api/psus/"+$scope.psuid
        }).then(function mySuccess(response) {
            $scope.psu = response.data;
        }, function myError(response) {
            $scope.psu = response.statusText;
        });
        $http({
            method : "GET",
            url : "/api/psus/"+$scope.psuid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/psus/"+$scope.psuid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/psus/"+$scope.psuid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }}
    });
    app.controller('CaseInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams,$window) {
        $scope.caseid = $routeParams.caseID;
        $http({
            method : "GET",
            url : "/api/cases/"+$scope.caseid
        }).then(function mySuccess(response) {
            $scope.case = response.data;
        }, function myError(response) {
            $scope.case = response.statusText;
        });
        $http({
            method : "GET",
            url : "/api/cases/"+$scope.caseid + "/comments/"
        }).then(function mySuccess(response) {
            $scope.comments = response.data;
        }, function myError(response) {
            $scope.comments = response.statusText;
        });
        $scope.back = function(){
            sharedProperties.cUrl = $location.path("/");
        }
        $scope.delete = function(x){
            $http({
                method : "DELETE",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/cases/"+$scope.caseid + "/comments/"+x
            }).then(function mySuccess(response) {
                $window.location.reload();
            }, function myError(response) {
                document.getElementById('error').innerHTML="You don't have rights";
                $(function () {
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            });
        }
        $scope.Add = function(x){
            if($window.sessionStorage.token == undefined){
                $location.path("/signin/");
                document.getElementById('error').innerHTML="Please Log In or Sing Up";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
                return;
            }
            if(document.getElementById('comment').value == ''){
                document.getElementById('error').innerHTML="No type comment";
                $(function () {
                    // wait till load event fires so all resources are available
                    $scope.$slider = $("#moderr").modal();
                });
            }
            else{
            $http({
                method : "POST",
                headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
                url : "/api/cases/"+$scope.caseid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
            $window.location.reload();
        }}
    });
    app.controller('SingUp', function ($location,$scope,$http) {
        $scope.passwordGood = "red";
        $scope.sendNewMember = function(x){
            if ($scope.passwordGood != "green") {
                $scope.itOk = "Check your password!"
            } else {
                $scope.itOk = "Sended!";
                $http({
                    method: "POST",
                    url: "/api/users/signup/",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: $.param({
                        login: x.login,
                        password: x.password,
                        first_name: x.first_name,
                        last_name: x.last_name,
                        email: x.email
                    })
                }).then(function mySuccess(data, status) {
                    //$scope.itOk = data.xhrStatus;
                    $scope.itOk = data.data.status;
                    if ($scope.itOk !== "ok") $scope.itOk = data.data.errors[0];
                    else $location.path("/signin");
                });
            }
        };
        $scope.passwordFerif = function (p,pc) {
            if(p != pc) $scope.passwordGood = "red";
            else $scope.passwordGood = "green";
        }
        //$http($scope.req).then($scope.itOk = response.data);
    });
    app.controller('SingIn', function ($location,$scope,$http,$window) {
        $scope.sendMember = function(x){
            $scope.itOk = "Sended!";
            $http({
                method: "POST",
                url: "/api/users/signin/",
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data :  $.param({login: x.login,password: x.password})
            }).then(function mySuccess(data, status) {
                //$scope.itOk = data.xhrStatus;
                $scope.itOk = data.data.id;
                if ($scope.itOk === "") $scope.itOk = data.data.errors[0];
                else {
                    $window.sessionStorage.id = data.data.id;
                    $window.sessionStorage.login = data.data.login;
                    $window.sessionStorage.email = data.data.email;
                    $window.sessionStorage.first_name = data.data.first_name;
                    $window.sessionStorage.last_name = data.data.last_name;
                    $window.sessionStorage.role = data.data.role;
                    $window.sessionStorage.token = data.data.token;
                    $location.path("/");
                    $window.location.reload();
                }

            });
        };
        $scope.passwordFerif = function (p,pc) {
            if(p != pc) $scope.passwordGood = "red";
            else $scope.passwordGood = "green";
        }
        //$http($scope.req).then($scope.itOk = response.data);
    });
    app.controller('LogOut', function ($location,$scope,$http,$window) {
        $scope.login = $window.sessionStorage.login;
        $scope.first_name = $window.sessionStorage.first_name;
        $scope.last_name = $window.sessionStorage.last_name;
        $scope.email = $window.sessionStorage.email;
        $scope.LogOut = function (x) {
            $window.sessionStorage.clear();
            $scope.itOk = "Success Logout!";
            $location.path("/");
            $window.location.reload();
        }
    });
    app.controller("Currency",function(sharedProperties,$scope,$route,$cookies) {
        if ($cookies.currency != undefined) {
            sharedProperties.currency = $cookies.currency;
            if (sharedProperties.currency == 1) {
                document.getElementById('eur').className = "active";
                document.getElementById('usd').className = "";
            }
            else {
                document.getElementById('usd').className = "active";
                document.getElementById('eur').className = "";
            }
        }
        else {
            $cookies.currency = sharedProperties.currency;
        }
        $scope.currentUrl = sharedProperties.cUrl;
        $scope.currency = function (x) {
                if(x != sharedProperties.currency){
                    if(x == 1){
                        document.getElementById('eur').className = "active";
                        document.getElementById('usd').className = "";
                    }
                    else{
                        document.getElementById('usd').className = "active";
                        document.getElementById('eur').className = "";
                    }
                    sharedProperties.currency = x;
                    $cookies.put('currency',sharedProperties);
                    $route.reload();
                }
        }
    });
    app.controller("OrderInfo",function($scope,$http,$window){
        $http({
            method : "GET",
            //headers: {'Authorization': 'Basic ' + btoa("admin" + ":" + "admin")},
            transformResponse: "",
            headers: {'Authorization': 'Bearer '+$window.sessionStorage.token},
            url : "/api/orders"
        }).then(function mySuccess(response) {
            $scope.orders = JSON.parse(response.data);
            //alert(JSON.parse(response.data)[0].id);
        });
    });
    app.controller('HatCtrl', function($scope, $window) {
        angular.element(document).ready(function () {
            $("#admin-button").hide();
            $("#orders-button").hide();
            $("#signup-button").hide();
            $("#signin-button").hide();
            $("#logout-button").hide();
            $scope.logoutText = "Log Out";

            if(($window.sessionStorage.token == undefined)){
                $("#signup-button").show();
                $("#signin-button").show();
            }
            else{
                if($window.sessionStorage.role == "admin"){
                    $("#admin-button").show();
                }
                else{
                    $("#orders-button").show();
                }
                $("#logout-button").show();
                $scope.logoutText = "Log Out ("+$window.sessionStorage.login+")";
            }
        });
    });
</script>
<body ng-app="myApp">
<nav class="navbar navbar-inverse" style="margin-bottom: 0;">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#!/">Tietokonekauppa</a>
        </div>
        <ul class="nav navbar-nav navbar-right" ng-controller="HatCtrl">
            <li class="dropdown" ng-controller="Currency">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Currency <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li id="usd" class="active" ng-click="currency(0)"><a href="{{currentUrl}}">USD</a></li>
                    <li id="eur"><a href="{{currentUrl}}" ng-click="currency(1)">EUR</a></li>
                </ul>
            </li>
            <li id="admin-button"><a href="/admin"><span class="glyphicon glyphicon-user"></span> Admin</a></li>
            <li id="orders-button"><a href="#!orders"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
            <li id="signup-button"><a href="#!signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li id="signin-button"><a href="#!signin"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
            <li id="logout-button"><a href="#!logout"><span class="glyphicon glyphicon-log-in"></span> {{logoutText}}</a></li>
        </ul>
    </div>
</nav>
<div class="modal fade" id="moderr" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Error</h4>
            </div>
            <div class="modal-body">
                <p id="error"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<div ng-view>
</div>
<footer>
    <div class="container">
        <div class="row">
            <div class="text-center">
                <p class="copyright text-muted">Copyright &copy; Tietokonekauppa 2017</p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>