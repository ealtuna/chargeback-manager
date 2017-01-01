(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('ShippingChallengeDialogController', ShippingChallengeDialogController);

    ShippingChallengeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Shipping'];

    function ShippingChallengeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Shipping) {
        var vm = this;

        vm.shipping = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.shipping.id !== null) {
                Shipping.update(vm.shipping, onSaveSuccess, onSaveError);
            } else {
                Shipping.save(vm.shipping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('challengeApp:shippingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.orderDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
