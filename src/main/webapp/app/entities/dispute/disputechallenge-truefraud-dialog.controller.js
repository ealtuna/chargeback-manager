(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeTrueFraudController',DisputeTrueFraudController);

    DisputeTrueFraudController.$inject = ['$uibModalInstance', 'entity', 'DisputeTrueFraud'];

    function DisputeTrueFraudController($uibModalInstance, entity, DisputeTrueFraud) {
        var vm = this;

        vm.dispute = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
        	DisputeTrueFraud.truefraud({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
