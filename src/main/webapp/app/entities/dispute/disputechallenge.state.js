(function() {
    'use strict';

    angular
        .module('challengeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('disputechallenge', {
            parent: 'entity',
            url: '/disputechallenge?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Disputes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dispute/disputeschallenge.html',
                    controller: 'DisputeChallengeController',
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
        .state('disputechallengeprocessinglist', {
            parent: 'entity',
            url: '/disputechallengeprocessinglist?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Disputes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dispute/disputeschallengeprocessinglist.html',
                    controller: 'DisputeChallengeProcessingListController',
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
        .state('disputedesitionpendinglist', {
            parent: 'entity',
            url: '/disputedesitionpendinglist?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Disputes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dispute/disputes-desitionpending-list.html',
                    controller: 'DisputeDesitionPendingListController',
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
        .state('disputecompletedlist', {
            parent: 'entity',
            url: '/disputecompletedlist?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Disputes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dispute/disputes-completed-list.html',
                    controller: 'DisputeCompletedController',
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
        .state('disputechallenge-detail', {
            parent: 'entity',
            url: '/disputechallenge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Dispute'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dispute/disputechallenge-detail.html',
                    controller: 'DisputeChallengeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Dispute', function($stateParams, Dispute) {
                    return Dispute.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'disputechallenge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('disputechallenge-detail.edit', {
            parent: 'disputechallenge-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-dialog.html',
                    controller: 'DisputeChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disputechallengenew', {
            parent: 'entity',
            url: '/newdispute',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-dialog.html',
                    controller: 'DisputeChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                status: null,
                                disputeType: null,
                                caseNumber: null,
                                filedDay: null,
                                amount: null,
                                cardType: null,
                                reasonCode: null,
                                notes: null,
                                attachedLetter: null,
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
        .state('disputechallenge.edit', {
            parent: 'disputechallenge',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-dialog.html',
                    controller: 'DisputeChallengeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disputechallenge.processing', {
            parent: 'disputechallenge',
            url: '/{id}/processing',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-processing-dialog.html',
                    controller: 'DisputeChallengeProcessingController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dispute-setdeside', {
            parent: 'disputechallenge',
            url: '/{id}/setdeside',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputes-setdecide-dialog.html',
                    controller: 'DisputeChallengeSetdecideController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disputechallenge-unabletodecide', {
            parent: 'disputechallenge',
            url: '/{id}/unabletodecide',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-unabletodecide-dialog.html',
                    controller: 'DisputeChallengeUndecideController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disputechallenge.truefraud', {
            parent: 'disputechallenge',
            url: '/{id}/truefraud',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-truefraud-dialog.html',
                    controller: 'DisputeTrueFraudController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disputechallenge.delete', {
            parent: 'disputechallenge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dispute/disputechallenge-delete-dialog.html',
                    controller: 'DisputeChallengeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Dispute', function(Dispute) {
                            return Dispute.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disputechallenge', null, { reload: 'disputechallenge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
