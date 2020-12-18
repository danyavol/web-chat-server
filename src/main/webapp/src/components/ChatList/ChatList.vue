<template>
	<div class="sec-component p-0 d-flex flex-column">
		<div class="chatList-top d-flex align-items-center pl-4 justify-content-between">
			<span class="h5 mb-0 primary-text">Сообщения</span>
			<span class="mr-3 p-2 addChat" @click="openNewChat">
				<font-awesome-icon icon="user-plus" class="fa-lg"/>
			</span>
		</div>

		<div class="overflow-auto position-relative flex-grow-1 mx-0" >
			<Loading v-if="isLoading"/>
			<div class="chatList-wrapper position-absolute d-flex flex-column px-3 py-3" v-if="!isLoading" style="max-width: 100%">
				<p class="secondary-text text-center" v-if="chats && chats.length === 0">У вас нет диалогов</p>
				<ChatListItem v-bind:key="chat.chatId" v-for="chat in chats" v-bind:chat="chat" @openMessages="openMessages"/>
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
		chats: Array,
		isLoading: Boolean
	},
	data() {
		return {
		}
	},
	beforeMount() {
		this.sortChats();
	},
	beforeUpdate() {
		this.sortChats();
	},
	methods: {
		openMessages(data) {
			this.$emit('openMessages', data);
		},
		openNewChat() {
			this.$emit('openNewChat');
		},
		sortChats() {
			if (this.chats) {
				this.chats = this.chats.sort((a, b) => {
					if (a.messages.length !== 0 && b.messages.length !== 0){
						return (new Date(b.messages[b.messages.length-1].sendTime)).getTime() - (new Date(a.messages[a.messages.length-1].sendTime)).getTime();
					}
					if (a.messages.length === 0 && b.messages.length === 0) return 0;
					if (a.messages.length !== 0 && b.messages.length === 0) return -1;
					else return 1;
				})
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
		}
	},
	components: {
        ChatListItem,
		Loading
    }
}
</script>

<style lang="scss">
	.sec-component {
		border-right: 1px solid var(--border);
	}
	.chatList-top {
		background: var(--card-bg);
		border-bottom: 1px solid var(--border);
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