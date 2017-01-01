(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('DisputeFriendlyFraud', DisputeFriendlyFraud);

    DisputeFriendlyFraud.$inject = ['$resource', 'DateUtils'];

    function DisputeFriendlyFraud($resource, DateUtils) {
        var resourceUrl =  'api/disputes/:id/friendlyfraud';

        return $resource(resourceUrl, {'id': '@id'}, {
        	'friendlyfraud': {
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
