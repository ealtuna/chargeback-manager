(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeChallengeDetailController', DisputeChallengeDetailController);

    DisputeChallengeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Dispute', 'Transaction', 'Shipping', 'Refund'];

    function DisputeChallengeDetailController($scope, $rootScope, $stateParams, previousState, entity, Dispute, Transaction, Shipping, Refund) {
        var vm = this;

        vm.dispute = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('challengeApp:disputeUpdate', function(event, result) {
            vm.dispute = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
