<template>
	<div class="border-right p-0 d-flex flex-column">
		<div class="chatList-top d-flex align-items-center pl-4 justify-content-between">
			<span class="h5 mb-0">Сообщения</span>
			<span class="mr-3 p-2 addChat" @click="openNewChat">
				<font-awesome-icon icon="user-plus" class="fa-lg"/>
			</span>
		</div>

		<div class="overflow-auto position-relative flex-grow-1 mx-0">
			<Loading v-if="chatsList == null"/>
			<div class="chatList-wrapper position-absolute d-flex flex-column px-3 py-3" v-if="chatsList != null">
				<p class="text-center" v-if="chatsList && chatsList.length === 0">У вас нет диалогов</p>
				<ChatListItem v-bind:key="item.chatId" v-for="item in chatsList" v-bind:data="item" @openMessages="openMessages"/>
			</div>
		</div>

	</div>
</template>

<script>
import ChatListItem from '@/components/ChatList/ChatList_Item';
import Loading from '@/components/Loading';

export default {
	data() {
		return {
			chatsList: null
		}
	},
	beforeMount() {
		this.axios.get(this.$root.url+'chats/getAll', {params: {userId: localStorage.getItem('userId')}})
			.then(response => {
				if (!response.data.error) {
					let friends = [];
					for (let chat of response.data) {
						friends.push(chat.mate.login);
					}
					localStorage.setItem('friends', JSON.stringify(friends));
					this.chatsList = response.data;
				} else {
					// Неверный id, кто-то изменил localStorage
					location.reload();
				}
			});
	},
	methods: {
		openMessages(data) {
			this.$emit('openMessages', data);
		},
		openNewChat() {
			this.$emit('openNewChat');
		}
	},
	components: {
        ChatListItem,
		Loading
    }
}
</script>

<style lang="scss">
	.chatList-top {
		background: white;
		border-bottom: 1px solid #dee2e6;
		height: 60px;
		position: relative;
		z-index: 15;
		box-shadow: 0px 10px 10px -10px rgba(34, 60, 80, 0.1);
	}

	.addChat {
		cursor: pointer;
		color: #6f757b;
		transition-duration: 0.2s;
		&:hover {
			transition-duration: 0.2s;
			color: #343a40;
		}
	}
</style>