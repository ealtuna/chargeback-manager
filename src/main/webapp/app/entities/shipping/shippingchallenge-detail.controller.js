(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('ShippingChallengeDetailController', ShippingChallengeDetailController);

    ShippingChallengeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Shipping'];

    function ShippingChallengeDetailController($scope, $rootScope, $stateParams, previousState, entity, Shipping) {
        var vm = this;

        vm.shipping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('challengeApp:shippingUpdate', function(event, result) {
            vm.shipping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
