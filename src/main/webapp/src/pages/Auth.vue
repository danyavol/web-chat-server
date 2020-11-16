<template>
	<div class="bg-light">
		<div class="container vh-100 d-flex justify-content-center align-items-center">
			<div class="card w-100" style="max-width: 450px">
				<div class="card-title mb-0 border-bottom">
					<h3 class="text-center my-3">Авторизация</h3>
				</div>
				<div class="card-body">
					<p class="bg-danger text-white card p-2" id="errorAlert" style="display: none"></p>
					<form id="loginForm">
						<div class="form-group">
							<label for="login">Логин</label>
							<input type="text" class="form-control" id="login">
						</div>
						<div class="form-group">
							<label for="password">Пароль</label>
							<input type="password" class="form-control" id="password">
						</div>
						<div class="d-flex justify-content-center">
							<button type="button" id="submit" class="btn btn-primary px-5">Войти</button>
						</div>
					</form>
					<hr>
					<a href="/register">Создать аккаунт</a>
				</div>
			</div>
		</div>
	</div>
</template>

<script>


export default {
	mounted () {
		let loginUser = login.bind(this);
		let submit = document.getElementById('submit');
		submit.addEventListener("click", loginUser);
		document.addEventListener('keydown', (e) => e.key === 'Enter' ? loginUser() : null );

		function login () {
			let l = document.getElementById('login').value;
			let p = document.getElementById('password').value;
			let error = document.getElementById('errorAlert');
			error.style.display = 'none';

			this.axios.post(this.$root.url+'users/auth', {}, {params: { login: l, password: p }})
			.then(response => {
				if (response.data.error) {
					// Ошибка входа
					error.style.display = 'block';
					error.innerText = response.data.error;
				} else {
					// Успешный вход
					localStorage.setItem('uuid', response.data.uuid);
					this.$router.push('/')
				}
			})
		}
	}
}
</script>