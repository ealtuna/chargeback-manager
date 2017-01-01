'use strict';

describe('Controller Tests', function() {

    describe('FriendlyFraudNotification Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFriendlyFraudNotification, MockDispute;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFriendlyFraudNotification = jasmine.createSpy('MockFriendlyFraudNotification');
            MockDispute = jasmine.createSpy('MockDispute');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'FriendlyFraudNotification': MockFriendlyFraudNotification,
                'Dispute': MockDispute
            };
            createController = function() {
                $injector.get('$controller')("FriendlyFraudNotificationChallengeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'challengeApp:friendlyFraudNotificationUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
