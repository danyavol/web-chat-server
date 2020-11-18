<template>
	<div class="p-0 position-relative">
		<div class="settings-top bg-white p-2 border-bottom d-flex align-items-center" style="height: 60px">
			<div class="d-flex align-items-center">
				<span class="avatar ml-3" :class="'color'+colorScheme">{{name[0]}}</span>
				<span class="ml-3">@{{ login }}</span>
				<strong class="ml-2">{{ name }}</strong>
			</div>
		</div>
		<div class="p-3">

			<div class="card card-body">
				<form>
					<div class="row">
						<div class="col">
							<h5 class="mb-0">Изменить профиль</h5>
						</div>
						<div class="col text-right">
							<div id="settingsAlert" :class="alertClass">{{alertText}}</div>
						</div>
					</div>


					<hr>

					<div class="row mb-2 align-items-center">
						<div class="col-3 col-lg-2">Аватар</div>
						<div class="col">
							<div class="d-flex avatar-select-container flex-wrap">
							<span v-for="n in 8" :key="n"
								:class="'avatar-select color'+n + (n == colorScheme ? ' selected' : '')"
								:data-n="n">
								{{name[0]}}
							</span>
							</div>
						</div>
					</div>

					<div class="row mb-2 align-items-center">
						<div class="col-3 col-lg-2">Имя</div>
						<div class="col">
							<input type="text" class="form-control" id="settingsName" placeholder="Имя" :value="name">
							<div class="invalid-feedback">
								С большой буквы, не менее 3-х символов.
							</div>
						</div>
					</div>

					<div class="row mb-2 align-items-center">
						<div class="col-3 col-lg-2">Логин</div>
						<div class="col">
							<input type="text" class="form-control" id="settingsLogin" placeholder="Логин" :value="login">
							<div class="invalid-feedback">
								Маленькие латинские буквы или цифры, не менее 4-х символов, первый символ не цифра.
							</div>
						</div>
					</div>

					<div class="row align-items-center">
						<div class="col-3 col-lg-2">Пароль</div>
						<div class="col">
							<input type="password" class="form-control" id="settingsPassword" placeholder="Новый пароль">
							<div class="invalid-feedback">
								Не менее 4-х латинский букв или цифр.
							</div>
						</div>
					</div>

					<div class="d-flex justify-content-center mt-3 position-relative">
						<Loading v-if="isLoading" class="position-absolute" style="right: 10px; bottom: 0"/>
						<input type="button" id="settingsSubmit" class="btn btn-outline-primary" :disabled="isLoading" value="Сохранить изменения">
					</div>


				</form>
			</div>
		</div>
		<p class="text-secondary text-center position-absolute" style="bottom: 0; width: 300px; left: Calc(50% - 150px); font-size: 0.875rem; letter-spacing: 1px;">
			Разработал<font-awesome-icon :icon="['fas', 'code']" class="text-secondary mx-2"/>Волосюк Даниил
		</p>
	</div>
</template>

<script>
import Loading from '@/components/Loading';
export default {
	data() {
		return {
			login: null,
			name: null,
			colorScheme: null,
			isLoading: false,

			alertText: '',
			alertClass: ''
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

		// Валидация
		const form = {
			n: {
				elem: document.getElementById('settingsName'),
				regExp: /(^[А-Я][а-я]{2,14}$|^[A-Z][a-z]{2,14}$)/
			},
			l: {
				elem: document.getElementById('settingsLogin'),
				regExp: /^[a-z][0-9a-z]{3,9}$/
			},
			p: {
				elem: document.getElementById('settingsPassword'),
				regExp: /((^[0-9a-zA-Z]{4,20}$)|(^$))/
			}
		};
		let firstTime = true;
		let submit = document.getElementById('settingsSubmit');

		submit.addEventListener('click', () => {
			let flag = true;

			for (let f in form) {
				if (form[f].elem.value.match(form[f].regExp)) {
					// Валидация пройдена
					form[f].elem.classList.remove('is-invalid');
					form[f].elem.classList.add('is-valid');
				} else {
					// Валидация не пройдена
					form[f].elem.classList.remove('is-valid');
					form[f].elem.classList.add('is-invalid');
					flag = false;
				}
				if (firstTime) {
					form[f].elem.addEventListener('input', () => validateField(f));
				}
			}
			if (firstTime) firstTime = false;

			// Вся валидация пройдена, отправляем запрос на сервер
			if (flag) {
				this.saveProfile();
			}
		});

		function validateField (f) {
			if (form[f].elem.value.match(form[f].regExp)) {
				// Валидация пройдена
				form[f].elem.classList.remove('is-invalid');
				form[f].elem.classList.add('is-valid');
			} else {
				// Валидация не пройдена
				form[f].elem.classList.remove('is-valid');
				form[f].elem.classList.add('is-invalid');
			}
		}
	},
	methods: {
		saveProfile() {
			this.isLoading = true;

			let params = {uuid: localStorage.getItem('uuid')};
			params.name = document.querySelector('#settingsName').value;
			params.login = document.querySelector('#settingsLogin').value;
			params.pass = document.querySelector('#settingsPassword').value;
			params.colorScheme = +document.querySelector('.avatar-select-container .selected').dataset.n;


			this.axios.put(this.$root.url+'users/edit', {}, {params: params})
				.then(response => {
					this.isLoading = false;
					if (!response.data.error) {
						this.name = response.data.name;
						this.login = response.data.login;
						this.colorScheme = response.data.colorScheme;
						localStorage.setItem('name', response.data.name);
						localStorage.setItem('login', response.data.login);
						localStorage.setItem('colorScheme', response.data.colorScheme);
						this.alertText = 'Успешно сохранено';
						this.alertClass = 'success';
						setTimeout(() => {this.alertClass = ''}, 2000);
					} else {
						// Ошибка при сохранении
						this.alertText = response.data.error;
						this.alertClass = 'error';
						setTimeout(() => {this.alertClass = ''}, 2000);
						// location.reload();
					}
				});
		}
	},
	components: {
		Loading
	}
}
</script>

<style lang="scss">
	.settings-top {
		box-shadow: 0px 10px 10px -10px rgba(34, 60, 80, 0.1);
	}

	#settingsAlert  {
		transition-duration: 0.3s;
		opacity: 0;
		&.success {
			color: #28a745;
			transition-duration: 0.3s;
			opacity: 1;
		}
		&.error {
			color: #dc3545;
			transition-duration: 0.3s;
			opacity: 1;
		}
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