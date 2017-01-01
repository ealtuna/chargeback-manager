(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('TransactionChallengeDetailController', TransactionChallengeDetailController);

    TransactionChallengeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Transaction'];

    function TransactionChallengeDetailController($scope, $rootScope, $stateParams, previousState, entity, Transaction) {
        var vm = this;

        vm.transaction = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('challengeApp:transactionUpdate', function(event, result) {
            vm.transaction = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
