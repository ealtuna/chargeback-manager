(function() {
    'use strict';

    angular
        .module('challengeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('transactionchallenge', {
            parent: 'entity',
            url: '/transactionchallenge?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Transactions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/transaction/transactionschallenge.html',
                    controller: 'TransactionChallengeController',
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
        .state('transactionchallenge-detail', {
            parent: 'entity',
            url: '/transactionchallenge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Transaction'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/transaction/transactionchallenge-detail.html',
                    controller: 'TransactionChallengeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Transaction', function($stateParams, Transaction) {
                    return Transaction.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'transactionchallenge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('transactionchallenge-detail.edit', {
            parent: 'transactionchallenge-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/transaction/transactionchallenge-dialog.html',
                    controller: 'TransactionChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Transaction', function(Transaction) {
                            return Transaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('transactionchallengenew', {
            parent: 'disputechallengenew',
            url: '/newtransaction',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/transaction/transactionchallenge-dialog.html',
                    controller: 'TransactionChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                date: null,
                                amount: null,
                                addressVerification: null,
                                cvv: null,
                                authCode: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('disputechallengenew', null, { reload: 'disputechallengenew' });
                }, function() {
                    $state.go('disputechallengenew');
                });
            }]
        })
        .state('transactionchallenge.edit', {
            parent: 'transactionchallenge',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/transaction/transactionchallenge-dialog.html',
                    controller: 'TransactionChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Transaction', function(Transaction) {
                            return Transaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('transactionchallenge', null, { reload: 'transactionchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('transactionchallenge.delete', {
            parent: 'transactionchallenge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/transaction/transactionchallenge-delete-dialog.html',
                    controller: 'TransactionChallengeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Transaction', function(Transaction) {
                            return Transaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('transactionchallenge', null, { reload: 'transactionchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
