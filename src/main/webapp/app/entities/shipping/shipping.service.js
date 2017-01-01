(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('Shipping', Shipping);

    Shipping.$inject = ['$resource', 'DateUtils'];

    function Shipping ($resource, DateUtils) {
        var resourceUrl =  'api/shippings/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.orderDate = DateUtils.convertLocalDateFromServer(data.orderDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.orderDate = DateUtils.convertLocalDateToServer(copy.orderDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.orderDate = DateUtils.convertLocalDateToServer(copy.orderDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
