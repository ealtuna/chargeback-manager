(function() {
    'use strict';

    angular
        .module('challengeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('create-dispute', {
            parent: 'app',
            url: '/createdispute',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/disputes/create-dispute.html',
                    controller: 'CreateDisputeController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
