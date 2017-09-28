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
            .when("/disks/:diskId",{
            templateUrl : "templates/DiskInformation.html"

        })
            .when("/cpus/:cpuId",{
                templateUrl : "templates/CPUInformation.html"

            })
    .when("/cases/:caseId",{
            templateUrl : "templates/CaseInformation.html"

        });
    });
    app.service('sharedProperties', function () {
        var currency = 1;
        var diskID,cpuID,caseID,mbID,gpuID,ramID;
        return {
            getCurrency: function () {
                return currency;
            },
            setCurency: function(value) {
                currency = value;
            },
            getDiskID: function () {
                return diskID;
            },
            setDiskID: function(value) {
                diskID = value;
            }
        };
    });
    app.controller('HdCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.currency = sharedProperties.getCurrency();
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
        $scope.currency = sharedProperties.getCurrency();
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
        $scope.currency = sharedProperties.getCurrency();
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
        $scope.currency = sharedProperties.getCurrency();
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
        $scope.currency = sharedProperties.getCurrency();
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
        $scope.currency = sharedProperties.getCurrency();
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
        $scope.currency = sharedProperties.getCurrency();
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
    app.controller('DiskInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setDiskID(x);
            $location.path("/disks/"+x);
        }
    });
    app.controller('CPUInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setCPUID(x);
            $location.path("/cpus/"+x);
        }
    });
    app.controller('PSUInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setPSUID(x);
            $location.path("/psus/"+x);
        }
    });
    app.controller('RamInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setRAMID(x);
            $location.path("/rams/"+x);
        }
    });
    app.controller('VCInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setVCID(x);
            $location.path("/gpus/"+x);
        }
    });
    app.controller('MBInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setMBID(x);
            $location.path("/mbs/"+x);
        }
    });
    app.controller('CaseInformation', function ($location,$scope,sharedProperties) {
        $scope.redirectDI = function(x){
            sharedProperties.setCaseID(x);
            $location.path("/cases/"+x);
        }
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
    app.controller('DiskInfoCtrl', function($scope, $http, NgTableParams, sharedProperties) {
        $scope.diskid = sharedProperties.getDiskID();
        $scope.currency = sharedProperties.getCurrency();
        if($scope.currency == 0)
            $http({
                method : "GET",
                url : "/api/disks/"+$scope.diskid
            }).then(function mySuccess(response) {
                $scope.disk = response.data;
            }, function myError(response) {
                $scope.disk = response.statusText;
            });
        else
            $http({
                method : "GET",
                url : "/api/disks/"+$scope.diskid+"?price_units=EUR"
            }).then(function mySuccess(response) {
                $scope.disk = response.data;
            }, function myError(response) {
                $scope.disk = response.statusText;
            });
        $scope.tableParams = new NgTableParams({
            page: 1, // show first page
            count: 5 // count per page
        }, {
            total: $scope.disk, // length of data
            getData: function($defer, params) {
                $defer.resolve($scope.disk.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        });
    });
</script>
<body ng-app="myApp">
<nav class="navbar navbar-inverse" style="margin-bottom: 0;">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Tietokonekauppa</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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