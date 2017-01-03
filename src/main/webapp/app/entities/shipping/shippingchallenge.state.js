(function() {
    'use strict';

    angular
        .module('challengeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('shippingchallenge', {
            parent: 'entity',
            url: '/shippingchallenge?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Shippings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/shipping/shippingschallenge.html',
                    controller: 'ShippingChallengeController',
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
        .state('shippingchallenge-detail', {
            parent: 'entity',
            url: '/shippingchallenge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Shipping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/shipping/shippingchallenge-detail.html',
                    controller: 'ShippingChallengeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Shipping', function($stateParams, Shipping) {
                    return Shipping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'shippingchallenge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('shippingchallenge-detail.edit', {
            parent: 'shippingchallenge-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/shipping/shippingchallenge-dialog.html',
                    controller: 'ShippingChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Shipping', function(Shipping) {
                            return Shipping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('shippingchallenge-new', {
            parent: 'disputechallengenew',
            url: '/newshipping',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/shipping/shippingchallenge-dialog.html',
                    controller: 'ShippingChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                customerId: null,
                                ipAddress: null,
                                productId: null,
                                email: null,
                                orderId: null,
                                productInCampaign: null,
                                orderAmount: null,
                                orderDate: null,
                                orderStatus: null,
                                shippingService: null,
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
        .state('shippingchallenge.edit', {
            parent: 'shippingchallenge',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/shipping/shippingchallenge-dialog.html',
                    controller: 'ShippingChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Shipping', function(Shipping) {
                            return Shipping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('shippingchallenge', null, { reload: 'shippingchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('shippingchallenge.delete', {
            parent: 'shippingchallenge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/shipping/shippingchallenge-delete-dialog.html',
                    controller: 'ShippingChallengeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Shipping', function(Shipping) {
                            return Shipping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('shippingchallenge', null, { reload: 'shippingchallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
