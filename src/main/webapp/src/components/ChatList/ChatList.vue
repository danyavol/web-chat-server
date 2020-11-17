<template>
	<div class="border-right p-0 d-flex flex-column">
		<div class="chatList-top d-flex align-items-center pl-4 justify-content-between">
			<span class="h5 mb-0">Сообщения</span>
			<span class="mr-3 p-2 addChat" @click="openNewChat">
				<font-awesome-icon icon="user-plus" class="fa-lg"/>
			</span>
		</div>

		<div class="overflow-auto position-relative flex-grow-1 mx-0" >
			<Loading v-if="chatsList == null"/>
			<div class="chatList-wrapper position-absolute d-flex flex-column px-3 py-3" v-if="chatsList != null" style="max-width: 100%">
				<p class="text-center" v-if="chatsList && chatsList.length === 0">У вас нет диалогов</p>
				<ChatListItem v-bind:key="chat.chatId" v-for="chat in chatsList" v-bind:chat="chat" @openMessages="openMessages"/>
			</div>
		</div>

	</div>
</template>

<script>
import ChatListItem from '@/components/ChatList/ChatList_Item';
import Loading from '@/components/Loading';

export default {
	props: {
		newMessage: Object,
		notifications: Array
	},
	data() {
		return {
			chatsList: null
		}
	},
	beforeMount() {
		this.axios.get(this.$root.url+'chats/getAll', {params: {uuid: localStorage.getItem('uuid')}})
			.then(response => {
				if (!response.data.error) {
					let friends = [];
					for (let chat of response.data) {
						friends.push(chat.mate.userId);
					}
					localStorage.setItem('friends', JSON.stringify(friends));
					this.chatsList = response.data;
				} else {
					// Неверный id, кто-то изменил localStorage
					location.reload();
				}
			});
	},
	beforeUpdate() {
		if (this.chatsList != null) {
			this.chatsList = this.chatsList.sort((a, b) => {
				if (a.messages.length !== 0 && b.messages.length !== 0){
					return (new Date(b.messages[0].sendTime)).getTime() - (new Date(a.messages[0].sendTime)).getTime();
				}
				if (a.messages.length === 0 && b.messages.length === 0) return 0;
				if (a.messages.length !== 0 && b.messages.length === 0) return -1;
				else return 1;
			})
			this.handleNotifications();
		}
	},
	methods: {
		openMessages(data) {
			this.$emit('openMessages', data);
		},
		openNewChat() {
			this.$emit('openNewChat');
		},
		handleNotifications() {
			if (this.chatsList && this.notifications) {
				// console.log(this.chatsList, this.notifications);
				this.notifications.forEach(chat_notification => {
					for (let i = 0; i < this.chatsList.length; i++) {
						if (this.chatsList[i].chatId === chat_notification.chatId) {
							let newChats;

							// Изменение количества новых сообщений
							if (this.chatsList[i].newMessageCount !== chat_notification.messages.length) {
								newChats = JSON.parse(JSON.stringify(this.chatsList));
								newChats[i].newMessageCount = chat_notification.messages.length;
							}

							// Изменение последнего сообщения в чате
							if (chat_notification.messages.length > 0) {
								let lastMsg = chat_notification.messages[chat_notification.messages.length - 1];
								// ЕСЛИ совпало [0] и [1] пустое ИЛИ совпало [1] ИЛИ [0] и [1] нету
								if ( (this.chatsList[i].messages[0] && this.chatsList[i].messages[0].messageId !== lastMsg.messageId && !this.chatsList[i].messages[1]) ||
									(this.chatsList[i].messages[1] && this.chatsList[i].messages[1].messageId !== lastMsg.messageId) ) {
									!newChats ? newChats = JSON.parse(JSON.stringify(this.chatsList)) : null;
									newChats[i].messages[1] = lastMsg;
								}
							} else if (this.chatsList[i].messages[1]) {
								!newChats ? newChats = JSON.parse(JSON.stringify(this.chatsList)) : null;
								delete newChats[i].messages[1];
							}

							// Применение изменений, если они есть
							if (newChats) {
								console.log(newChats);
								this.chatsList = newChats;
							}

						}
					}
				});
			}
		}
	},
	watch: {
		newMessage(newMsg) {
			let chats = JSON.parse(JSON.stringify(this.chatsList));
			for (let i = 0; i < chats.length; i++) {
				if (chats[i].chatId === newMsg.chatId) {
					if (newMsg.message.length === 0) {
						chats[i].messages = [];
					} else {
						chats[i].messages[0] = newMsg.message;
					}
					this.chatsList = chats;
					break;
				}
			}
		},
		notifications() {
			this.handleNotifications();
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