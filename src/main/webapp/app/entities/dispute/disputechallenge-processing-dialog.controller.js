(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeChallengeProcessingController',DisputeChallengeProcessingController);

    DisputeChallengeProcessingController.$inject = ['$uibModalInstance', 'entity', 'DisputeProcessing'];

    function DisputeChallengeProcessingController($uibModalInstance, entity, DisputeProcessing) {
        var vm = this;

        vm.dispute = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DisputeProcessing.processing({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
