(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeChallengeUndecideController',DisputeChallengeUndecideController);

    DisputeChallengeUndecideController.$inject = ['$uibModalInstance', 'entity', 'DisputeUndecided'];

    function DisputeChallengeUndecideController($uibModalInstance, entity, DisputeUndecided) {
        var vm = this;

        vm.dispute = entity;
        vm.clear = clear;
        vm.confirmDesition = confirmDesition;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDesition () {
        	DisputeUndecided.undecide(vm.dispute,
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
