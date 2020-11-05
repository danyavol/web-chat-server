<template>
	<div id="app">
		<router-view />
	</div>
</template>

<script>
	export default {
		name: 'App',
		beforeCreate() {
			// Проверка авторизован ли пользователь
			if ( this.$router.currentRoute.fullPath === '/') {
				if ( localStorage.getItem('isAuth') ) {
					this.$router.push('/auth');
				}
			}
		},
		components: {

		}
	}


</script>