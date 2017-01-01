(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('RefundChallengeDialogController', RefundChallengeDialogController);

    RefundChallengeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Refund', 'Dispute'];

    function RefundChallengeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Refund, Dispute) {
        var vm = this;

        vm.refund = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.disputes = Dispute.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.refund.id !== null) {
                Refund.update(vm.refund, onSaveSuccess, onSaveError);
            } else {
                Refund.save(vm.refund, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('challengeApp:refundUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
