(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('CreateDisputeSrv', CreateDisputeSrv);

    CreateDisputeSrv.$inject = ['$resource', 'DateUtils'];

    function CreateDisputeSrv ($resource, DateUtils) {
        var service = {
        		
        		newDispute:{}
        		
        };
        return service;
    }
})();
