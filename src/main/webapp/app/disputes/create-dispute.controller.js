(function() {
    'use strict';

    angular
        .module('challengeApp')
        .controller('CreateDisputeController', CreateDisputeController);

    CreateDisputeController.$inject = ['$scope', '$state', 'Dispute', 'ParseLinks', 'AlertService', 'CreateDisputeSrv'];

    function CreateDisputeController ($scope, $state, Dispute, ParseLinks, AlertService, CreateDisputeSrv) {
        var vm = this;
        vm.CreateDisputeSrv = CreateDisputeSrv; 
    }
   
})();
