(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('ShippingChallengeDeleteController',ShippingChallengeDeleteController);

    ShippingChallengeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Shipping'];

    function ShippingChallengeDeleteController($uibModalInstance, entity, Shipping) {
        var vm = this;

        vm.shipping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Shipping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
