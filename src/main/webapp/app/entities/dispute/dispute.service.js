(function() {
    'use strict';
    angular
        .module('challengeApp')
        .factory('Dispute', Dispute);

    Dispute.$inject = ['$resource', 'DateUtils'];

    function Dispute ($resource, DateUtils) {
        var resourceUrl =  'api/disputes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.filedDay = DateUtils.convertLocalDateFromServer(data.filedDay);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.filedDay = DateUtils.convertLocalDateToServer(copy.filedDay);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.filedDay = DateUtils.convertLocalDateToServer(copy.filedDay);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
