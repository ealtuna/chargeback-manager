(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeChallengeSetdecideController',DisputeChallengeSetdecideController);

    DisputeChallengeSetdecideController.$inject = ['$uibModalInstance', 'entity', 'DisputeDesided'];

    function DisputeChallengeSetdecideController($uibModalInstance, entity, DisputeDesided) {
        var vm = this;

        vm.dispute = entity;
        vm.clear = clear;
        vm.confirmDesition = confirmDesition;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDesition () {
        	DisputeDesided.setdecide(vm.dispute,
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
