import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import router from './router';
import { createApp } from 'vue';
import App from './App.vue';
import globalDirective from '@/plugins/global-directive';
import dayjs from '@/plugins/dayjs';
import { createPinia } from 'pinia';

const app = createApp(App);
app.use(globalDirective);
app.use(createPinia()); // store
app.use(router);
app.use(dayjs);
app.mount('#app');
