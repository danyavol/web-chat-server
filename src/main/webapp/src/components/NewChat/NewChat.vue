<template>
	<div class="border-right p-0 d-flex flex-column">
		<div class="newChat-top d-flex align-items-center pl-4 justify-content-between">
			<span class="h5 mb-0">Новый диалог</span>
			<span class="mr-3 p-2 addChat" @click="openChatList">
				<font-awesome-icon icon="share" class="fa-lg"/>
			</span>
		</div>
		<div class="searchUser">
			<input type="text" class="text-secondary" placeholder="Введите имя.." @input="search">
		</div>
		<div class="overflow-auto position-relative flex-grow-1 mx-0">
			<Loading v-if="users == null"/>
			<div class="chatList-wrapper position-absolute d-flex flex-column px-3 py-3" v-if="users != null">
				<NewChat_Item v-bind:key="user.login" v-for="user in users" v-bind:user="user"/>
			</div>
		</div>
	</div>
</template>

<script>
import Loading from '@/components/Loading';
import NewChat_Item from "@/components/NewChat/NewChat_Item";

export default {
	beforeMount() {
		this.findUsers();
	},
	data() {
		return {
			users: null
		}
	},
	methods: {
		openChatList() {
			this.$emit('openChatList');
		},
		findUsers(search='') {
			this.axios.get(this.$root.url+'users/find', {params: {text: search}})
				.then(response => {
					if (!response.data.error) {
						this.users = response.data.filter(item => {
							// Фильтрация самого себя
							if (item.login === localStorage.getItem('login')) {
								return false
							}

							// Фильтрация уже добавленных пользователей
							let friends = localStorage.getItem('friends') ? JSON.parse( localStorage.getItem('friends') ) : [];
							for (let user of friends) {
								if (user === item.login) return false;
							}

							return true;

						})
					}
				});
		},
		search(e) {
			console.log(e.target.value);
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
			border-color: #dee2e6;
			border-right: 0;
			border-left: 0;

			padding: 1px 25px;

			&:focus {
				outline: 0;
			}
		}
	}

	.newChat-top {
		background: white;
		height: 59px;
		position: relative;
		z-index: 15;
	}
</style>