<template>
	<div class="p-0">
		<div class="settings-top bg-white border-bottom d-flex align-items-center" style="height: 60px">
			<p class="ml-4 mb-0 h5">Настройки</p>
		</div>
		<div class="p-3">

			<p>@{{ login }}<strong class="pl-2">{{ name }}</strong></p>
			<p>
				<button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#changePassword">
					Изменить пароль
				</button>
			</p>
			<div class="collapse" id="changePassword">
				<div class="card card-body">
					Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
				</div>
			</div>


		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			login: null,
			name: null
		}
	},
	beforeMount() {
		this.login = localStorage.getItem('login');
		this.name = localStorage.getItem('name');
	}
}
</script>

<style lang="scss">
	.settings-top {
		box-shadow: 0px 10px 10px -10px rgba(34, 60, 80, 0.1);
	}
</style>