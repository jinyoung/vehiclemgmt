
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CarAssignmentManager from "./components/ui/CarAssignmentGrid"

import VehiclePerformanceManager from "./components/ui/VehiclePerformanceGrid"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/carAssignments',
                name: 'CarAssignmentManager',
                component: CarAssignmentManager
            },

            {
                path: '/vehiclePerformances',
                name: 'VehiclePerformanceManager',
                component: VehiclePerformanceManager
            },



    ]
})
