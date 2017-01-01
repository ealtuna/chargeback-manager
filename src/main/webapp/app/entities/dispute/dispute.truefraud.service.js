(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('DisputeTrueFraud', DisputeTrueFraud);

    DisputeTrueFraud.$inject = ['$resource', 'DateUtils'];

    function DisputeTrueFraud($resource, DateUtils) {
        var resourceUrl =  'api/disputes/truefraud/:id';

        return $resource(resourceUrl, {'id': '@id'}, {
        	'truefraud': {
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
