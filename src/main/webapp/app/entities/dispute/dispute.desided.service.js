(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('DisputeDesided', DisputeDesided);

    DisputeDesided.$inject = ['$resource', 'DateUtils'];

    function DisputeDesided($resource, DateUtils) {
        var resourceUrl =  'api/disputes/desided/:id';

        return $resource(resourceUrl, {'id': '@id'}, {
        	'query': { method: 'GET', isArray: true},
        	'setdecide': {
                method: 'POST',
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
