(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('DisputeProcessing', DisputeProcessing);

    DisputeProcessing.$inject = ['$resource', 'DateUtils'];

    function DisputeProcessing($resource, DateUtils) {
        var resourceUrl =  'api/disputes/processing/:id';

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
