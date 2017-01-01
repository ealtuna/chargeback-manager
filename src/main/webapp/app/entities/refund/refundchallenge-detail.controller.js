(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('RefundChallengeDetailController', RefundChallengeDetailController);

    RefundChallengeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Refund', 'Dispute'];

    function RefundChallengeDetailController($scope, $rootScope, $stateParams, previousState, entity, Refund, Dispute) {
        var vm = this;

        vm.refund = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('challengeApp:refundUpdate', function(event, result) {
            vm.refund = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
