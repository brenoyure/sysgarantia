import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import keycloak from './keycloak'
import axios from './axios'

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

keycloak.init({
    onLoad: 'login-required'
}).then(() => {
    console.log(keycloak)
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token
    createApp(App).use(store).use(router).use(VueSweetalert2).mount('#app')
})