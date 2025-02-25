import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//import keycloak from './keycloak'
//import axios from './axios'

import VueSweetalert2 from 'vue-sweetalert2';
import PrimeVue from "primevue/config"
import Aura from '@primevue/themes/aura'
import 'sweetalert2/dist/sweetalert2.min.css';

//import userdetails from './userdetails'
//import UserNotInAnyRoleComponent from './components/UserNotInAnyRoleComponent.vue'

//keycloak.init({
//    onLoad: 'login-required'
//}).then(() => {
//    axios.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token
//    userdetails.username   = keycloak.tokenParsed.preferred_username
//    userdetails.first_name = keycloak.tokenParsed.given_name
//    userdetails.last_name  = keycloak.tokenParsed.family_name
//    userdetails.full_name  = keycloak.tokenParsed.name
//    if (!keycloak.tokenParsed.resource_access["sysgarantia-api-quarkus"]) {
//        createApp(UserNotInAnyRoleComponent).mount('#app')
//        return
//    }
//    userdetails.roles = keycloak.tokenParsed.resource_access["sysgarantia-api-quarkus"].roles
    createApp(App).use(PrimeVue, {
        theme: {
            preset: Aura,
            options: {
                prefix: 'p',
                darkModeSelector: false, //'system'
                cssLayer: false
            }}
    }).use(store).use(router).use(VueSweetalert2).mount('#app')
//})
