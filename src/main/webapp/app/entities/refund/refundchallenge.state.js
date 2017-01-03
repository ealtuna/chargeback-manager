(function() {
    'use strict';

    angular
        .module('challengeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('refundchallenge', {
            parent: 'entity',
            url: '/refundchallenge?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Refunds'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/refund/refundschallenge.html',
                    controller: 'RefundChallengeController',
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
        .state('refundchallenge-detail', {
            parent: 'entity',
            url: '/refundchallenge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Refund'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/refund/refundchallenge-detail.html',
                    controller: 'RefundChallengeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Refund', function($stateParams, Refund) {
                    return Refund.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'refundchallenge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('refundchallenge-detail.edit', {
            parent: 'refundchallenge-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund/refundchallenge-dialog.html',
                    controller: 'RefundChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Refund', function(Refund) {
                            return Refund.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('refundchallengenew', {
            parent: 'disputechallenge',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund/refundchallenge-dialog.html',
                    controller: 'RefundChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                date: null,
                                amount: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('disputechallenge');
                });
            }]
        })
        .state('refundchallenge.edit', {
            parent: 'refundchallenge',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund/refundchallenge-dialog.html',
                    controller: 'RefundChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Refund', function(Refund) {
                            return Refund.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('refundchallenge', null, { reload: 'refundchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('refundchallenge.delete', {
            parent: 'refundchallenge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund/refundchallenge-delete-dialog.html',
                    controller: 'RefundChallengeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Refund', function(Refund) {
                            return Refund.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('refundchallenge', null, { reload: 'refundchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
