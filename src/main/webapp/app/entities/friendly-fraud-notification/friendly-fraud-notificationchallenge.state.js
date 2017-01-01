(function() {
    'use strict';

    angular
        .module('challengeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('friendly-fraud-notificationchallenge', {
            parent: 'entity',
            url: '/friendly-fraud-notificationchallenge?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FriendlyFraudNotifications'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/friendly-fraud-notification/friendly-fraud-notificationschallenge.html',
                    controller: 'FriendlyFraudNotificationChallengeController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('friendly-fraud-notificationchallenge-detail', {
            parent: 'entity',
            url: '/friendly-fraud-notificationchallenge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FriendlyFraudNotification'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/friendly-fraud-notification/friendly-fraud-notificationchallenge-detail.html',
                    controller: 'FriendlyFraudNotificationChallengeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'FriendlyFraudNotification', function($stateParams, FriendlyFraudNotification) {
                    return FriendlyFraudNotification.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'friendly-fraud-notificationchallenge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('friendly-fraud-notificationchallenge.new', {
            parent: 'friendly-fraud-notificationchallenge',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/friendly-fraud-notification/friendly-fraud-notificationchallenge-dialog.html',
                    controller: 'FriendlyFraudNotificationChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                email: null,
                                attachedLetter: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('friendly-fraud-notificationchallenge', null, { reload: 'friendly-fraud-notificationchallenge' });
                }, function() {
                    $state.go('friendly-fraud-notificationchallenge');
                });
            }]
        })
        .state('friendly-fraud-notificationchallenge-edit', {
            parent: 'disputechallengeprocessinglist',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
            	$uibModal.open({
                    templateUrl: 'app/entities/friendly-fraud-notification/friendly-fraud-notificationchallenge-dialog.html',
                    controller: 'FriendlyFraudNotificationChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                    	entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallengeprocessinglist', null, { reload: 'disputechallengeprocessinglist' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('friendly-fraud-notificationchallenge.delete', {
            parent: 'friendly-fraud-notificationchallenge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/friendly-fraud-notification/friendly-fraud-notificationchallenge-delete-dialog.html',
                    controller: 'FriendlyFraudNotificationChallengeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FriendlyFraudNotification', function(FriendlyFraudNotification) {
                            return FriendlyFraudNotification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('friendly-fraud-notificationchallenge', null, { reload: 'friendly-fraud-notificationchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
