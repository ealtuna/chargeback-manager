(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('DisputeUndecided', DisputeUndecided);

    DisputeUndecided.$inject = ['$resource', 'DateUtils'];

    function DisputeUndecided($resource, DateUtils) {
        var resourceUrl =  'api/disputes/undecide/:id';

        return $resource(resourceUrl, {'id': '@id'}, {
        	'undecide': {
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
