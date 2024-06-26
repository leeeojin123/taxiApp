
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CallCallManager from "./components/listers/CallCallCards"
import CallCallDetail from "./components/listers/CallCallDetail"

import DriveDriveManager from "./components/listers/DriveDriveCards"
import DriveDriveDetail from "./components/listers/DriveDriveDetail"

import PaymentPaymentManager from "./components/listers/PaymentPaymentCards"
import PaymentPaymentDetail from "./components/listers/PaymentPaymentDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/calls/calls',
                name: 'CallCallManager',
                component: CallCallManager
            },
            {
                path: '/calls/calls/:id',
                name: 'CallCallDetail',
                component: CallCallDetail
            },

            {
                path: '/drives/drives',
                name: 'DriveDriveManager',
                component: DriveDriveManager
            },
            {
                path: '/drives/drives/:id',
                name: 'DriveDriveDetail',
                component: DriveDriveDetail
            },

            {
                path: '/payments/payments',
                name: 'PaymentPaymentManager',
                component: PaymentPaymentManager
            },
            {
                path: '/payments/payments/:id',
                name: 'PaymentPaymentDetail',
                component: PaymentPaymentDetail
            },




    ]
})
