(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('FriendlyFraudNotificationChallengeDialogController', FriendlyFraudNotificationChallengeDialogController);

    FriendlyFraudNotificationChallengeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'FriendlyFraudNotification', 'Dispute', 'DisputeFriendlyFraud'];

    function FriendlyFraudNotificationChallengeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, FriendlyFraudNotification, Dispute, DisputeFriendlyFraud) {
        var vm = this;

        vm.dispute = entity;
        vm.friendlyFraudNotification = {};
        vm.friendlyFraudNotification.dispute = vm.dispute;
        
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.friendlyFraudNotification.id !== null) {
                FriendlyFraudNotification.update(vm.friendlyFraudNotification);
            } else {
                FriendlyFraudNotification.save(vm.friendlyFraudNotification);
            }
            DisputeFriendlyFraud.friendlyfraud({'id': vm.friendlyFraudNotification.dispute.id}, onSaveSuccess, onSaveError);
        }

        function onSaveSuccess (result) {
            $scope.$emit('challengeApp:friendlyFraudNotificationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
