
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import AssignCarAssignmentManager from "./components/listers/AssignCarAssignmentCards"
import AssignCarAssignmentDetail from "./components/listers/AssignCarAssignmentDetail"



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




    ]
})
