import Vue from 'vue'
import VueRouter from "vue-router"
import App from './App.vue'

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

const routes = [
	{
		path: "/",
		component: Main,
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
	render: h => h(App),
}).$mount('#app')
