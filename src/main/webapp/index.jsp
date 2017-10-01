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
    <link rel="stylesheet" href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">
</head>
<script>
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
            .when("/orders/:orderID",{
                templateUrl : "/templates/Orders.html"
            })
            .when("/signup",{
                templateUrl : "/users/singup/index.html"
            });
    });
    app.service('sharedProperties', function () {
        var currency = 0;
        var curUrl = "/#!";
        this.currency = currency;
        this.curUrl =  curUrl;

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
            for(var i=6;i<document.getElementById("mb").getElementsByTagName('input').length;i++){
                document.getElementById("mb").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=4;i<document.getElementById("cpu").getElementsByTagName('input').length;i++){
                document.getElementById("cpu").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=6;i<document.getElementById("ram").getElementsByTagName('input').length;i++){
                document.getElementById("ram").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=6;i<document.getElementById("hd").getElementsByTagName('input').length;i++){
                document.getElementById("hd").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=5;i<document.getElementById("vc").getElementsByTagName('input').length;i++){
                document.getElementById("vc").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=4;i<document.getElementById("psu").getElementsByTagName('input').length;i++){
                document.getElementById("psu").getElementsByTagName('input')[i].value = 0;
            }
            for(var i=4;i<document.getElementById("case").getElementsByTagName('input').length;i++){
                   document.getElementById("case").getElementsByTagName('input')[i].value = 0;
            }
        }
    });
    app.controller('Order', function($scope, $http) {
        $scope.order = function(notes) {
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
            if ($scope.order != "[") {
                if ($scope.order[$scope.order.length - 1] == ',')
                    $scope.order = $scope.order.substr(0, $scope.order.length - 1) + ']';
                else
                    $scope.order += ']';

                $http({
                    method: "POST",
                    headers: {
                        'Authorization': 'Basic ' + btoa("admin" + ":" + "admin"),
                        'Content-Type': 'application/json'
                    },
                    url: "/api/orders?notes=" + notes,
                    data: $scope.order
                })
                //$location.path("")
            }
        }
    });
    app.controller('DiskInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/disks/"+$scope.diskid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('MBInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/mbs/"+$scope.mbid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('CPInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/processors/"+$scope.cpuid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('RAMInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/rams/"+$scope.ramid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('GPUInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/gpus/"+$scope.gpuid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('PSUInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/psus/"+$scope.psuid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('CaseInfoCtrl', function($scope, $http, sharedProperties, $location, $routeParams) {
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
        $scope.Add = function(x){
            $http({
                method : "POST",
                headers:{ 'Authorization':  'Basic ' + btoa("admin" + ":" + "admin")},
                url : "/api/cases/"+$scope.caseid + "/comments?contents="+x
            }).then(function mySuccess(response) {
                $scope.status = response.data;
            }, function myError(response) {
                $scope.status = response.statusText;
            });
        }
    });
    app.controller('SingUp', function ($location,$scope,$http) {
        $scope.passwordGood = "red";
        $scope.sendNewMember = function(x){
            if ($scope.passwordGood != "green") $scope.itOk = "Check your password!"
            else {
                $scope.itOk = "Sended!";
                $http({
                    method: "POST",
                    url: "/api/users/signup/",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
//                    transformRequest: function(x){
//                        var str = [];
//                        for(var p in x)
//                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(x[p]));
//                        return str.join("&");
//                    }
                    data :  $.param({login: x.login,password: x.password,first_name: x.first_name,last_name: x.last_name,email: x.email})
                }).then(function ServAnsw(response) {
                    if (response.status != "ok")
                        $scope.itOk = "Something wrong!";
                    else $scope.itOk = "Registed!";
                });
            }
        };
        $scope.passwordFerif = function (p,pc) {
            if(p != pc) $scope.passwordGood = "red";
            else $scope.passwordGood = "green";
        }
        //$http($scope.req).then($scope.itOk = response.data);
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
    app.controller("OrderInfo",function(){

    })
</script>
<body ng-app="myApp">
<nav class="navbar navbar-inverse" style="margin-bottom: 0;">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#!/">Tietokonekauppa</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown" ng-controller="Currency">
                <a href="{{currentUrl}}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Currency <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li id="usd" class="active" ng-click="currency(0)"><a href="{{currentUrl}}">USD</a></li>
                    <li id="eur"><a href="{{currentUrl}}" ng-click="currency(1)">EUR</a></li>
                </ul>
            <li><a href="#!signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#!login"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
        </ul>
    </div>
</nav>
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