<template>
	<div class="sec-component p-0 d-flex flex-column">
		<div class="newChat-top d-flex align-items-center pl-4 justify-content-between">
			<span class="h5 mb-0">Новый диалог</span>
			<span class="mr-3 p-2 addChat" @click="openChatList">
				<font-awesome-icon icon="share" class="fa-lg"/>
			</span>
		</div>
		<div class="searchUser">
			<input type="text" class="" placeholder="Введите имя.." @input="search">
		</div>
		<div class="overflow-auto position-relative flex-grow-1 mx-0">
			<Loading v-if="users == null"/>
			<div class="chatList-wrapper position-absolute d-flex flex-column px-3 py-3" v-if="users != null">
				<p class="text-center secondary-text" v-if="isDone">Вы уже добавили всех пользователей</p>
				<p class="text-center secondary-text" v-if="users.length === 0 && !isDone">Пользователь не найден</p>
				<NewChat_Item v-bind:key="user.login" v-for="user in users" v-bind:user="user" @removeFromSearch="removeFromSearch"/>
			</div>
		</div>
	</div>
</template>

<script>
import Loading from '@/components/Loading';
import NewChat_Item from "@/components/NewChat/NewChat_Item";

export default {
	async beforeMount() {
		let users = await this.findUsers();
		if (users.length === 0) {
			this.isDone = true;
		}
		this.users = users;
	},
	data() {
		return {
			users: null,
			isDone: false
		}
	},
	methods: {
		openChatList() {
			this.$emit('openChatList');
		},
		findUsers(search='') {
			return this.axios.get(this.$root.url + 'users/find', {params: {text: search}})
				.then(response => {
					if (!response.data.error) {
						let friends = localStorage.getItem('friends') ? JSON.parse(localStorage.getItem('friends')) : [];

						return response.data.filter(item => {
							// Фильтрация самого себя
							if (item.userId === localStorage.getItem('userId')) {
								return false
							}

							// Фильтрация уже добавленных пользователей
							for (let user of friends) {
								if (user === item.userId) return false;
							}

							return true;

						});
					}
				});
		},
		async search(e) {
			if (!this.isDone) {
				let result = await this.findUsers(e.target.value);
				if (e.target.value.length === 0 && result.length === 0) {
					this.isDone = true;
				}
				this.users = result;
			}
		},
		removeFromSearch(userId) {
			this.users = this.users.filter(item => {
				return item.userId !== userId;

			});
			if (this.users.length === 0) this.isDone = true;
		}
	},
	components: {
		Loading,
		NewChat_Item
	}
}
</script>

<style  lang="scss">
	.chatList-wrapper {
		min-width: 100%;
		min-height: 100%;
	}
	.searchUser {
		box-shadow: 0px 10px 10px -10px rgba(34, 60, 80, 0.1);

		input {
			width: 100%;
			border-radius: 0;
			border-style: solid;
			border-width: 1px;
			border-right: 0;
			border-left: 0;

			padding: 1px 25px;

			color: var(--primary-text);
			border-color: var(--border);
			background-color: var(--card-bg);

			&::placeholder {
				color: var(--secondary-text);
			}

			&:focus {
				outline: 0;
			}
		}
	}

	.newChat-top {
		background: var(--card-bg);
		color: var(--primary-text);
		height: 59px;
		position: relative;
		z-index: 15;
	}
</style>