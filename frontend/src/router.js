
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import AssignCarAssignmentManager from "./components/listers/AssignCarAssignmentCards"
import AssignCarAssignmentDetail from "./components/listers/AssignCarAssignmentDetail"

import DrivingVehiclePerformanceManager from "./components/listers/DrivingVehiclePerformanceCards"
import DrivingVehiclePerformanceDetail from "./components/listers/DrivingVehiclePerformanceDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/assigns/carAssignments',
                name: 'AssignCarAssignmentManager',
                component: AssignCarAssignmentManager
            },
            {
                path: '/assigns/carAssignments/:id',
                name: 'AssignCarAssignmentDetail',
                component: AssignCarAssignmentDetail
            },

            {
                path: '/drivings/vehiclePerformances',
                name: 'DrivingVehiclePerformanceManager',
                component: DrivingVehiclePerformanceManager
            },
            {
                path: '/drivings/vehiclePerformances/:id',
                name: 'DrivingVehiclePerformanceDetail',
                component: DrivingVehiclePerformanceDetail
            },



    ]
})
