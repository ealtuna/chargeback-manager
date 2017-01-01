(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('RefundChallengeDeleteController',RefundChallengeDeleteController);

    RefundChallengeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Refund'];

    function RefundChallengeDeleteController($uibModalInstance, entity, Refund) {
        var vm = this;

        vm.refund = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Refund.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
