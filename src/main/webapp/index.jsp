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
            .when("/cpus/:mbId",{
                templateUrl : "templates/MBInformation.html"

            })
            .when("/cases/:caseId",{
                templateUrl : "templates/CaseInformation.html"
            })
            .when("/singup/",{
                templateUrl : "users/singup/index.html"
            })
        ;
    });
    app.service('sharedProperties', function () {
        var currency = 0;
        var diskID,cpuID,caseID,mbID,gpuID,ramID,vcID,psuID;
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
            },
            getCPUID: function () {
                return cpuID;
            },
            setCPUID: function(value) {
                cpuID = value;
            },
            getCaseID: function () {
                return caseID;
            },
            setCaseID: function(value) {
                caseID = value;
            },
            getMBID: function () {
                return mbID;
            },
            setMBID: function(value) {
                mbID = value;
            },
            getGPUID: function () {
                return gpuID;
            },
            setGPUID: function(value) {
                gpuID = value;
            },
            getRAMID: function () {
                return ramID;
            },
            setRAMID: function(value) {
                ramID = value;
            },
            getPSUID: function () {
                return psuID;
            },
            setPSUID: function(value) {
                psuID = value;
            },
            getVCID: function () {
                return vcID;
            },
            setVCID: function(value) {
                vcID = value;
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
    app.controller('CPUInformation', function ($location,$scope,sharedProperties) {
        $scope.redirect = function(x){
            sharedProperties.setCPUID(x);
            $location.path("/cpus/"+x);
        }
    });
    app.controller('PSUInformation', function ($location,$scope,sharedProperties) {
        $scope.redirect = function(x){
            sharedProperties.setPSUID(x);
            $location.path("/psus/"+x);
        }
    });
    app.controller('RAMInformation', function ($location,$scope,sharedProperties) {
        $scope.redirect = function(x){
            sharedProperties.setRAMID(x);
            $location.path("/rams/"+x);
        }
    });
    app.controller('VCInformation', function ($location,$scope,sharedProperties) {
        $scope.redirect = function(x){
            sharedProperties.setVCID(x);
            $location.path("/gpus/"+x);
        }
    });
    app.controller('MBInformation', function ($location,$scope,sharedProperties) {
    });
    app.controller('CaseInformation', function ($location,$scope,sharedProperties) {
        $scope.redirect = function(x){
            sharedProperties.setCaseID(x);
            $location.path("/cases/"+x);
        }
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
        $scope.order = function(){
            $scope.order = "[";
            for(var i=6;i<document.getElementById("mb").getElementsByTagName('input').length;i++){
                if (document.getElementById("mb").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("mb").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('mb').getElementsByTagName('td')[7*(i-6)+6].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"Motherboard\"},';
                    }
                }
            }
            for(var i=4;i<document.getElementById("cpu").getElementsByTagName('input').length;i++){
                if (document.getElementById("cpu").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("cpu").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('cpu').getElementsByTagName('td')[5*(i-4)+4].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"CPU\"},';
                    }
                }
            }
            for(var i=6;i<document.getElementById("ram").getElementsByTagName('input').length;i++){
                if (document.getElementById("ram").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("ram").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('ram').getElementsByTagName('td')[7*(i-6)+6].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"RAM\"},';
                    }
                }
            }
            for(var i=6;i<document.getElementById("hd").getElementsByTagName('input').length;i++){
                if (document.getElementById("hd").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("hd").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('hd').getElementsByTagName('td')[7*(i-6)+6].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"Disk\"},';
                    }
                }
            }
            for(var i=5;i<document.getElementById("vc").getElementsByTagName('input').length;i++){
                if (document.getElementById("vc").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("vc").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('vc').getElementsByTagName('td')[6*(i-5)+5].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"GPU\"},';
                    }
                }
            }
            for(var i=4;i<document.getElementById("psu").getElementsByTagName('input').length;i++){
                if (document.getElementById("psu").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("psu").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('psu').getElementsByTagName('td')[5*(i-4)+4].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"PSU\"},';
                    }
                }
            }
            for(var i=4;i<document.getElementById("case").getElementsByTagName('input').length;i++){
                if (document.getElementById("case").getElementsByTagName('input')[i].value > 0){
                    for(var j=0;j<document.getElementById("case").getElementsByTagName('input')[i].value;j++){
                        $scope.order += '{\"id\": '+ document.getElementById('case').getElementsByTagName('td')[5*(i-4)+4].getElementsByTagName('div')[1].innerHTML+', \"@type\": \"Case\"},';
                    }
                }
            }
            if($scope.order[$scope.order.length-1] == ',')
                $scope.order = $scope.order.substr(0,$scope.order.length-2) + ']';
            else
                $scope.order += ']';
            $http({
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                url: "/api/orders/",
                data: $scope.order
            })

        }
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
</script>
<body ng-app="myApp">
<nav class="navbar navbar-inverse" style="margin-bottom: 0;">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Tietokonekauppa</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
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