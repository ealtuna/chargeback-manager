(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('DisputeDesitionPending', DisputeDesitionPending);

    DisputeDesitionPending.$inject = ['$resource', 'DateUtils'];

    function DisputeDesitionPending($resource, DateUtils) {
        var resourceUrl =  'api/disputes/desitionpending/:id';

        return $resource(resourceUrl, {'id': '@id'}, {
        	'query': { method: 'GET', isArray: true},
        	'processing': {
                method: 'PUT',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.filedDay = DateUtils.convertLocalDateFromServer(data.filedDay);
                    }
                    return data;
                }
            }
        });
    }
})();
