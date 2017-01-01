(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('FriendlyFraudNotificationChallengeDeleteController',FriendlyFraudNotificationChallengeDeleteController);

    FriendlyFraudNotificationChallengeDeleteController.$inject = ['$uibModalInstance', 'entity', 'FriendlyFraudNotification'];

    function FriendlyFraudNotificationChallengeDeleteController($uibModalInstance, entity, FriendlyFraudNotification) {
        var vm = this;

        vm.friendlyFraudNotification = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FriendlyFraudNotification.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
