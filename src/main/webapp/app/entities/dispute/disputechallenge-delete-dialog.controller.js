(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeChallengeDeleteController',DisputeChallengeDeleteController);

    DisputeChallengeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Dispute'];

    function DisputeChallengeDeleteController($uibModalInstance, entity, Dispute) {
        var vm = this;

        vm.dispute = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Dispute.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
