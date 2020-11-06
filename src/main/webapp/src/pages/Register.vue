<template>
    <div class="bg-light">
        <div class="container vh-100 d-flex justify-content-center align-items-center">
            <div class="card w-100" style="max-width: 450px">
                <div class="card-title">
                    <h3 class="text-center mb-2 mt-3">Регистрация</h3>
                </div>
                <div class="card-body">
                    <form>
                        <div class="form-group">
                            <label for="name">Имя</label>
                            <input type="text" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                            <label for="login">Логин</label>
                            <input type="text" class="form-control" id="login">
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" class="form-control" id="password">
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary px-5">Создать аккаунт</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>