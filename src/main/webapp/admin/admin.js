var myApp = angular.module('myApp', ['ng-admin']);
myApp.config(['NgAdminConfigurationProvider', function (nga) {
    // create an admin application
    var admin = nga.application('My First Admin')
      .baseApiUrl('http://localhost:8080/api/admin/'); // main API endpoint
    // create a user entity
    // the API endpoint for this entity will be 'http://jsonplaceholder.typicode.com/users/:id
    var user = nga.entity('disks');
    // set the fields of the user entity list view
    user.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('type'),
        nga.field('capacity'),
        nga.field('capacityUnits'),
        nga.field('rpm')
    ]);
    user.creationView().fields(user.listView().fields());
    user.editionView().fields(user.listView().fields());
    // add the user entity to the admin application
    admin.addEntity(user);

    var cases = nga.entity('cases');
    cases.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('weight'),
        nga.field('dimensions'),
        nga.field('color')
    ]);
    cases.creationView().fields(cases.listView().fields());
    cases.editionView().fields(cases.listView().fields());
    admin.addEntity(cases);

    var gpu = nga.entity('gpus');
    gpu.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('ramSize'),
        nga.field('ramType'),
        nga.field('coprocessor'),
        nga.field('busWidth'),
        nga.field('clockSpeed')
    ]);
    gpu.creationView().fields(gpu.listView().fields());
    gpu.editionView().fields(gpu.listView().fields());
    admin.addEntity(gpu);

    var motherboard = nga.entity('motherboards');
    motherboard.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('cpuModelSocket'),
        nga.field('ramMemoryTechnology'),
        nga.field('formFactor'),
        nga.field('memoryMaximumSize')
    ]);
    motherboard.creationView().fields(motherboard.listView().fields());
    motherboard.editionView().fields(motherboard.listView().fields());
    admin.addEntity(motherboard);

    var processor = nga.entity('processors');
    processor.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('processorCount'),
        nga.field('speed'),
        nga.field('ramType'),
        nga.field('socket'),
        nga.field('manufacturer')
    ]);
    processor.creationView().fields(processor.listView().fields());
    processor.editionView().fields(processor.listView().fields());
    admin.addEntity(processor);

    var psu = nga.entity('psus');
    psu.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('size'),
        nga.field('weight'),
        nga.field('dimensions')
    ]);
    psu.creationView().fields(psu.listView().fields());
    psu.editionView().fields(psu.listView().fields());
    admin.addEntity(psu);

    var ram = nga.entity('rams');
    ram.listView().fields([
        nga.field('name'),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('links'),
        nga.field('memorySize'),
        nga.field('ramType'),
        nga.field('formFactor'),
        nga.field('speed'),
        nga.field('ramMemoryTechnology')
    ]);
    ram.creationView().fields(ram.listView().fields());
    ram.editionView().fields(ram.listView().fields());
    admin.addEntity(ram);

    // attach the admin application to the DOM and execute it
    nga.configure(admin);
}]);

myApp.config(['RestangularProvider', function (RestangularProvider) {
    RestangularProvider.addFullRequestInterceptor(function(element, operation, what, url, headers, params) {
        if (operation == "getList") {
            // custom pagination params
            if (params._page) {
                params._start = (params._page - 1) * params._perPage;
                params._end = params._page * params._perPage;
            }
            delete params._page;
            delete params._perPage;
            delete params._start;
            delete params._end;
            // custom sort params
            if (params._sortField) {
                params._sort = params._sortField;
                params._order = params._sortDir;
                delete params._sortField;
                delete params._sortDir;
                delete params._sort;
                delete params._order;
            }
            // custom filters
            if (params._filters) {
                for (var filter in params._filters) {
                    params[filter] = params._filters[filter];
                }
                delete params._filters;
                delete params;
            }
        }
        return { params: params };
    });
}]);