var myApp = angular.module('myApp', ['ng-admin']);
myApp.config(['NgAdminConfigurationProvider', function (nga) {
    // create an admin application
    var admin = nga.application('My First Admin')
      .baseApiUrl('http://localhost:8080/api/admin/'); // main API endpoint
    // create a user entity
    // the API endpoint for this entity will be 'http://jsonplaceholder.typicode.com/users/:id
    var disks = nga.entity('disks');
    // set the fields of the user entity list view
    disks.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('type'),
        nga.field('capacity'),
        nga.field('capacityUnits'),
        nga.field('rpm')
    ]);
    disks.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true}), //TODO add pattern: '[EUR|USD]'
        nga.field('amount_available'),
        nga.field('type'),
        nga.field('capacity'),
        nga.field('capacityUnits'),
        nga.field('rpm')
    ]);
    disks.editionView().fields(disks.creationView().fields());

    // add the user entity to the admin application
    admin.addEntity(disks);

    var cases = nga.entity('cases');
    cases.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('weight'),
        nga.field('dimensions'),
        nga.field('color')
    ]);
    cases.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true }),//TODO add pattern: '[EUR|USD]'
        nga.field('amount_available'),
        nga.field('weight'),
        nga.field('dimensions'),
        nga.field('color')
    ]);
    cases.editionView().fields(cases.creationView().fields());
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
    //admin.addEntity(gpu);

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
    //admin.addEntity(motherboard);

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
    //admin.addEntity(processor);

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
    //admin.addEntity(psu);

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
    //admin.addEntity(ram);

    var comments = nga.entity('comments');
    comments.listView().fields([
        nga.field('id'),
        nga.field('item'),
        nga.field('item_type'),
        nga.field('contents'),
        nga.field('user_name'),
        nga.field('date')
    ]);
    comments.creationView().fields([
        nga.field('item').validation({ required: true }).label('id of item'),
        nga.field('item_type').label('type of item'),
        nga.field('contents').validation({ required: true }),
        nga.field('user_name'),
        nga.field('date')
    ]);
    comments.editionView().fields(comments.creationView().fields());
    admin.addEntity(comments);

    var links = nga.entity('links');
    links.listView().fields([
        nga.field('id'),
        nga.field('link'),
        nga.field('rel'),
        nga.field('component_id')
    ]);
    //links.creationView().fields(links.listView().fields());
    //links.editionView().fields(links.listView().fields());
    admin.addEntity(links);

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