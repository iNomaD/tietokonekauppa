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
        nga.field('type').label('HDD|SSD'),
        nga.field('capacity'),
        nga.field('capacityUnits'),
        nga.field('rpm')
    ]);
    disks.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true, pattern: 'EUR|USD'}).label('EUR|USR'),
        nga.field('amount_available'),
        nga.field('type').label('HDD|SSD'),
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
        nga.field('price_units').validation({ required: true, pattern: 'EUR|USD'}),
        nga.field('amount_available'),
        nga.field('weight'),
        nga.field('dimensions'),
        nga.field('color')
    ]);
    cases.editionView().fields(cases.creationView().fields());
    admin.addEntity(cases);

    var gpu = nga.entity('gpus');
    gpu.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('ramSize'),
        nga.field('ramType'),
        nga.field('coprocessor'),
        nga.field('busWidth'),
        nga.field('clockSpeed')
    ]);
    gpu.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true }),//TODO add pattern: '[EUR|USD]'
        nga.field('ramSize'),
        nga.field('ramType'),
        nga.field('coprocessor'),
        nga.field('busWidth'),
        nga.field('clockSpeed')
    ]);
    gpu.editionView().fields(gpu.creationView().fields());
    admin.addEntity(gpu);

    var motherboard = nga.entity('motherboards');
    motherboard.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('cpuModelSocket'),
        nga.field('ramMemoryTechnology'),
        nga.field('formFactor'),
        nga.field('memoryMaximumSize')
    ]);
    motherboard.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true }),//TODO add pattern: '[EUR|USD]'
        nga.field('cpuModelSocket'),
        nga.field('ramMemoryTechnology'),
        nga.field('formFactor'),
        nga.field('memoryMaximumSize')
    ]);
    motherboard.editionView().fields(motherboard.creationView().fields());
    admin.addEntity(motherboard);

    var processor = nga.entity('processors');
    processor.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('processorCount'),
        nga.field('speed'),
        nga.field('ramType'),
        nga.field('socket'),
        nga.field('manufacturer')
    ]);
    processor.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true }),//TODO add pattern: '[EUR|USD]'
        nga.field('processorCount'),
        nga.field('speed'),
        nga.field('ramType'),
        nga.field('socket'),
        nga.field('manufacturer')
    ]);
    processor.editionView().fields(processor.creationView().fields());
    admin.addEntity(processor);

    var psu = nga.entity('psus');
    psu.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('size'),
        nga.field('weight'),
        nga.field('dimensions')
    ]);
    psu.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true }),//TODO add pattern: '[EUR|USD]'
        nga.field('size'),
        nga.field('weight'),
        nga.field('dimensions')
    ]);
    psu.editionView().fields(psu.creationView().fields());
    admin.addEntity(psu);

    var ram = nga.entity('rams');
    ram.listView().fields([
        nga.field('id'),
        nga.field('name').isDetailLink(true),
        nga.field('vendor'),
        nga.field('price'),
        nga.field('price_units'),
        nga.field('amount_available'),
        nga.field('memorySize'),
        nga.field('ramType'),
        nga.field('formFactor'),
        nga.field('speed'),
        nga.field('ramMemoryTechnology')
    ]);
    ram.creationView().fields([
        nga.field('name').validation({ required: true }),
        nga.field('vendor'),
        nga.field('price').validation({ required: true }),
        nga.field('price_units').validation({ required: true }),//TODO add pattern: '[EUR|USD]'
        nga.field('memorySize'),
        nga.field('ramType'),
        nga.field('formFactor'),
        nga.field('speed'),
        nga.field('ramMemoryTechnology')
    ]);
    ram.editionView().fields(ram.creationView().fields());
    admin.addEntity(ram);

    var comments = nga.entity('comments');
    comments.listView().fields([
        nga.field('id'),
        nga.field('item.id'),
        nga.field('item.@type'),
        nga.field('contents').isDetailLink(true),
        nga.field('user_name'),
        nga.field('date')
    ]);
    comments.creationView().fields([
        nga.field('item.id').validation({ required: true }),
        nga.field('item.@type').validation({ required: true, pattern: 'Case|Disk|GPU|Motherboard|Processor|PSU|RAM'}),
        nga.field('contents').validation({ required: true}),
        nga.field('user_name'),
        nga.field('date')
    ]);
    comments.editionView().fields([
        nga.field('contents').validation({ required: true }),
        nga.field('user_name'),
        nga.field('date')
    ]);
    admin.addEntity(comments);

    var links = nga.entity('links');
    links.listView().fields([
        nga.field('id'),
        nga.field('link'),
        nga.field('rel')
    ]);
    //links.creationView().fields(links.listView().fields());
    //links.editionView().fields(links.listView().fields());
    admin.addEntity(links);

    var order = nga.entity('orders');
    order.listView().fields([
        nga.field('id'),
        nga.field('components', 'embedded_list') // Define a 1-N relationship with the (embedded) comment entity
            .targetFields([ // which comment fields to display in the datagrid / form
                nga.field('id'),
                nga.field('@type'),
                nga.field('price')
            ]),
        nga.field('user_name'),
        nga.field('user_email'),
        nga.field('date')
    ]);
    order.creationView().fields([
        nga.field('components', 'embedded_list') // Define a 1-N relationship with the (embedded) comment entity)
            .targetFields([ // which comment fields to display in the datagrid / form
                nga.field('id'),
                nga.field('@type')
            ]).validation({ required: true }),
        // nga.field('components').validation({ required: true }),
        nga.field('user_name').validation({ required: true }),
        nga.field('user_email').validation({ required: true }),
        nga.field('date')
    ]);
    order.editionView().fields(order.creationView().fields());
    admin.addEntity(order);

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