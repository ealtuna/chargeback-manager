(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('FriendlyFraudNotificationChallengeDetailController', FriendlyFraudNotificationChallengeDetailController);

    FriendlyFraudNotificationChallengeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FriendlyFraudNotification', 'Dispute'];

    function FriendlyFraudNotificationChallengeDetailController($scope, $rootScope, $stateParams, previousState, entity, FriendlyFraudNotification, Dispute) {
        var vm = this;

        vm.friendlyFraudNotification = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('challengeApp:friendlyFraudNotificationUpdate', function(event, result) {
            vm.friendlyFraudNotification = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
