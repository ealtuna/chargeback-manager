(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('FriendlyFraudNotification', FriendlyFraudNotification);

    FriendlyFraudNotification.$inject = ['$resource'];

    function FriendlyFraudNotification ($resource) {
        var resourceUrl =  'api/friendly-fraud-notifications/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
