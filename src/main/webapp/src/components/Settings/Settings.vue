<template>
	<div class="p-0">
		<div class="settings-top bg-white border-bottom d-flex align-items-center" style="height: 60px">
			<p class="ml-4 mb-0 h5">Настройки</p>
		</div>
		<div class="p-3">
			<div class="d-flex align-items-center ml-4 mb-4">
				<span class="avatar" :class="'color'+colorScheme">{{name[0]}}</span>
				<span class="px-2">@{{ login }}</span>
				<strong>{{ name }}</strong>
			</div>
			<form>

				<div class="row mb-2 align-items-center">
					<div class="col-2">Аватар</div>
					<div class="col-10">
						<div class="d-flex avatar-select-container flex-wrap">
							<span class="avatar-select color1">{{name[0]}}</span>
							<span class="avatar-select color2">{{name[0]}}</span>
							<span class="avatar-select color3">{{name[0]}}</span>
							<span class="avatar-select color4">{{name[0]}}</span>
							<span class="avatar-select color5">{{name[0]}}</span>
							<span class="avatar-select color6">{{name[0]}}</span>
							<span class="avatar-select color7">{{name[0]}}</span>
							<span class="avatar-select color8">{{name[0]}}</span>
						</div>
					</div>
				</div>

				<div class="row mb-2 align-items-center">
					<div class="col-2">Имя</div>
					<div class="col-10">
						<input type="text" class="form-control" placeholder="Имя">
					</div>
				</div>

				<div class="row mb-2 align-items-center">
					<div class="col-2">Логин</div>
					<div class="col-10">
						<input type="text" class="form-control" placeholder="Логин">
					</div>
				</div>

				<div class="row align-items-center">
					<div class="col-2">Пароль</div>
					<div class="col-10">
						<input type="text" class="form-control" placeholder="Пароль">
					</div>
				</div>



			</form>



		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			login: null,
			name: null,
			colorScheme: null
		}
	},
	beforeMount() {
		this.login = localStorage.getItem('login');
		this.name = localStorage.getItem('name');
		this.colorScheme = localStorage.getItem('colorScheme');
	},
	mounted() {
		document.querySelectorAll('.avatar-select').forEach(avatar => {
			avatar.addEventListener('click', (e) => {
				document.querySelectorAll('.avatar-select').forEach(a => {
					a.classList.remove('selected');
				})
				e.target.classList.add('selected');
			});
		})
	}
}
</script>

<style lang="scss">
	.settings-top {
		box-shadow: 0px 10px 10px -10px rgba(34, 60, 80, 0.1);
	}

	.avatar-select-container {
		.avatar-select {
			display: flex;
			justify-content: center;
			align-items: center;
			width: 34px;
			height: 34px;
			border-radius: 50%;
			user-select: none;
			cursor: pointer;
			border: 1px solid transparent;
			transition-duration: 0.2s;

			margin: 4px;
		}

		.avatar-select:hover {
			transition-duration: 0.2s;
			border: 2px solid #6c757d;
		}

		.selected {
			transition-duration: 0.2s;
			border: 2px solid #007bff;
			transform: scale(1.3);
		}

		.selected:hover {
			border: 2px solid #007bff;
		}
	}


	.avatar-select {

	}
</style>