import Vue from 'vue'
import VueRouter from "vue-router"
import App from './App.vue'

import axios from 'axios'
import VueAxios from 'vue-axios'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserPlus, faShare, faPlus } from '@fortawesome/free-solid-svg-icons'
import { } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faUserPlus, faShare, faPlus)

Vue.component('font-awesome-icon', FontAwesomeIcon)

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
			let uuid = localStorage.getItem('uuid');
			if ( uuid ) {
				axios.post(url+'users/checkAuth', {},{params: {uuid: uuid}})
					.then(response => {
						if (response.data) {
							localStorage.setItem('uuid', response.data.uuid);
							localStorage.setItem('userId', response.data.userId);
							localStorage.setItem('name', response.data.name);
							localStorage.setItem('login', response.data.login);
							localStorage.setItem('colorScheme', response.data.colorScheme);
							next();
						}
						else next('/auth');
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
		url: url,
		getDate
	},
	render: h => h(App),
}).$mount('#app')


// Global functions
function getDate(dateStr) {
	let output = '';
	let msgDate = new Date(dateStr);
	let now = new Date();

	// Подсчет часов и минут
	let hours = msgDate.getHours();
	let minutes = msgDate.getMinutes();
	if (hours < 10) hours = '0' + hours;
	if (minutes < 10) minutes = '0' + minutes;

	if (msgDate.getDate() === now.getDate()) {
		// Сегодня
		output = hours + ':' + minutes;
	} else if (msgDate.getDate() === now.getDate()-1) {
		// Вчера
		output = 'Вчера ' + hours + ':' + minutes;
	} else if (msgDate.getFullYear() === now.getFullYear() ) {
		// Дата
		let date = msgDate.getDate();
		if (date < 10) date = '0' + date;
		let month = msgDate.getMonth()+1;
		switch (month) {
			case '1': month = 'янв'; break;
			case '2': month = 'фев'; break;
			case '3': month = 'мар'; break;
			case '4': month = 'апр'; break;
			case '5': month = 'май'; break;
			case '6': month = 'июн'; break;
			case '7': month = 'июл'; break;
			case '8': month = 'авг'; break;
			case '9': month = 'сен'; break;
			case '10': month = 'окт'; break;
			case '11': month = 'ноя'; break;
			case '12': month = 'дек'; break;
		}
		output = date + ' ' + month + ' ' + hours + ':' + minutes;
	} else {
		// Дата + год
		let date = msgDate.getDate();
		if (date < 10) date = '0' + date;
		let month = msgDate.getMonth()+1;
		if (month < 10) month = '0' + month;
		let year = msgDate.getFullYear();
		year = year.toString()[2] + year.toString()[3];

		output = date + '.' + month + '.' + year + ' ' + hours + ':' + minutes;
	}

	return output;
}
