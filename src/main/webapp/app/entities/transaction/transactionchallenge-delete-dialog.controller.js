(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('TransactionChallengeDeleteController',TransactionChallengeDeleteController);

    TransactionChallengeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Transaction'];

    function TransactionChallengeDeleteController($uibModalInstance, entity, Transaction) {
        var vm = this;

        vm.transaction = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Transaction.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
