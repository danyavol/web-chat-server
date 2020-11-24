<template>
	<div>
		<div class="container vh-100 d-flex justify-content-center align-items-center">
			<div class="card w-100" style="max-width: 450px">
				<div class="card-title mb-0 border-bottom">
					<h3 class="text-center my-3">Регистрация</h3>
				</div>
				<div class="card-body">
					<p class="bg-danger text-white card p-2 mb-2" id="errorAlert" style="display: none"></p>
					<form>
						<div class="form-group">
							<label for="name">Имя</label>
							<input type="text" class="form-control" id="name" autocomplete="off">
							<div class="invalid-feedback">
								С большой буквы, не менее 3-х символов.
							</div>
						</div>
						<div class="form-group">
							<label for="login">Логин</label>
							<input type="text" class="form-control" id="login" autocomplete="off">
							<div class="invalid-feedback">
								Маленькие латинские буквы или цифры, не менее 4-х символов, первый символ не цифра.
							</div>
						</div>
						<div class="form-group">
							<label for="password">Пароль</label>
							<input type="password" class="form-control" id="password" autocomplete="off">
							<div class="invalid-feedback">
								Не менее 4-х латинский букв или цифр.
							</div>
						</div>
						<div class="d-flex justify-content-center">
							<button type="button" id="submit" class="btn btn-primary px-5">Создать аккаунт</button>
						</div>
						<hr>
						<a href="/auth">Уже есть аккаунт?</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	mounted() {
		const form = {
			n: {
				elem: document.getElementById('name'),
				regExp: /(^[А-Я][а-я]{2,14}$|^[A-Z][a-z]{2,14}$)/
			},
			l: {
				elem: document.getElementById('login'),
				regExp: /^[a-z][0-9a-z]{3,9}$/
			},
			p: {
				elem: document.getElementById('password'),
				regExp: /^[0-9a-zA-Z]{4,20}$/
			}
		};
		let firstTime = true;
		let submit = document.getElementById('submit');

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
				let error = document.getElementById('errorAlert');
				error.style.display = 'none';
				this.axios.post(this.$root.url+'/users/new', {}, {params: {
					name: form.n.elem.value,
					login: form.l.elem.value,
					password: form.p.elem.value
				} }).then(response => {
					if (response.data.error) {
						error.innerText = response.data.error;
						error.style.display = 'block';
					} else {
						localStorage.setItem('uuid', response.data.uuid);
						this.$router.push('/');
					}
				})
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
	}
}
</script>