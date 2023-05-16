import './assets/main.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.js'
import 'bootstrap-icons/font/bootstrap-icons.css'
import router from './router'
import { createApp } from 'vue'
import App from './App.vue'
createApp(App).use(router).mount('#app')

// console.log('VITE_APP_API_URL: ', import.meta.env.VITE_APP_API_URL)
// console.log('base url: ', import.meta.env.BASE_URL)
