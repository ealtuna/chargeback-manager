'use strict';

describe('Controller Tests', function() {

    describe('Dispute Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDispute, MockTransaction, MockShipping, MockRefund;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDispute = jasmine.createSpy('MockDispute');
            MockTransaction = jasmine.createSpy('MockTransaction');
            MockShipping = jasmine.createSpy('MockShipping');
            MockRefund = jasmine.createSpy('MockRefund');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Dispute': MockDispute,
                'Transaction': MockTransaction,
                'Shipping': MockShipping,
                'Refund': MockRefund
            };
            createController = function() {
                $injector.get('$controller')("DisputeChallengeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'challengeApp:disputeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
