<html lang="en">
<head>
    <title>Tietokonekauppa</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
    <script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <scipt src="angular-init.js"></scipt>
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href="css/clean-blog.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">
</head>
<script>
    var app = angular.module('myApp', ['ngRoute', 'ngTable']);
    app.config(function($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl : "templates/main.html"
            })
            .when("/index.jsp", {
                templateUrl : "templates/main.html"
            })
            .when("/disks",{
            templateUrl : "templates/main.html"
        });
    });
    app.service('sharedProperties', function () {
        var currency = 1;
        return {
            getCurrencyy: function () {
                return currency;
            },
            setCurencyy: function(value) {
                currency = value;
            }
        };
    });
    app.controller('HdCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
        $http({
            method : "GET",
            url : "/api/disks"
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
    });
    app.controller('ramCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/rams"
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
    });
    app.controller('vcCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/gpus"
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
    });
    app.controller('caseCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/cases"
            }).then(function mySuccess(response) {
                $scope.cases = response.data;
            }, function myError(response) {
                $scope.cases = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/cases?price_units=EUR"
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
            getData: function($defer, params) {
                $defer.resolve($scope.cases.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
    });
    app.controller('cpuCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/processors"
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
    });
    app.controller('mbCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/motherboards"
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
    });
    app.controller('psuCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrencyy();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/psus"
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
    });
    app.controller('DiskInformation', function ($location,$scope) {
        $scope.redirectDI = function(x){
            $location.path("/disks/"+x);
        }
    })
    /*app.controller('resetc',function ($scope) {
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
    });*/
</script>
<body ng-app="myApp">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="index.html">Tietokonekauppa</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item dropdown ">
                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">Currency</a>
                    <ul class="dropdown-menu" role="menu" style="margin-top:5px;">
                        <li><a href="#">USD</a></li>
                        <li><a href="#">EUR</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="about.html">Log in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="post.html">Sign up</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div ng-view>
</div>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <p class="copyright text-muted">Copyright &copy; Tietokonekauppa 2017</p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>