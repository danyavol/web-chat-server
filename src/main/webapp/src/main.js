import Vue from 'vue'
import VueRouter from "vue-router"
import App from './App.vue'

import axios from 'axios'
import VueAxios from 'vue-axios'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Main from './pages/Main';
import Auth from './pages/Auth';
import Register from './pages/Register';

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
// Router
Vue.use(VueRouter);
// Axios
Vue.use(VueAxios, axios);

// url сервера
const url = "http://localhost:8080/";

const routes = [
	{
		path: "/",
		component: Main,
		beforeEnter: (to, from, next) => {
			// Проверка авторизован ли пользователь
			let userId = localStorage.getItem('userId');
			if ( userId ) {
				axios.post(url+'users/checkAuth', {},{params: {userId: userId}})
					.then(response => {
						if (!response.data) next('/auth');
						else next();
					});
			} else {
				next('/auth')
			}
		}
	},
	{
		path: "/auth",
		component: Auth,
	},
	{
		path: "/register",
		component: Register,
	}
];

const router = new VueRouter({
	routes,
	mode: "history",
});

Vue.config.productionTip = false

new Vue({
	router,
	data: {
		url: url
	},
	render: h => h(App),
}).$mount('#app')
