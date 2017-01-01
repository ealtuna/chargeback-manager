(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('DisputeChallengeDialogController', DisputeChallengeDialogController);

    DisputeChallengeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Dispute', 'Transaction', 'Shipping', 'Refund'];

    function DisputeChallengeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Dispute, Transaction, Shipping, Refund) {
        var vm = this;

        vm.dispute = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.transactions = Transaction.query({filter: 'dispute-is-null'});
        $q.all([vm.dispute.$promise, vm.transactions.$promise]).then(function() {
            if (!vm.dispute.transaction || !vm.dispute.transaction.id) {
                return $q.reject();
            }
            return Transaction.get({id : vm.dispute.transaction.id}).$promise;
        }).then(function(transaction) {
            vm.transactions.push(transaction);
        });
        vm.shippings = Shipping.query({filter: 'dispute-is-null'});
        $q.all([vm.dispute.$promise, vm.shippings.$promise]).then(function() {
            if (!vm.dispute.shipping || !vm.dispute.shipping.id) {
                return $q.reject();
            }
            return Shipping.get({id : vm.dispute.shipping.id}).$promise;
        }).then(function(shipping) {
            vm.shippings.push(shipping);
        });
        vm.refunds = Refund.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dispute.id !== null) {
                Dispute.update(vm.dispute, onSaveSuccess, onSaveError);
            } else {
                Dispute.save(vm.dispute, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('challengeApp:disputeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.filedDay = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
