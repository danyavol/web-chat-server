<template>
	<div id="app">
		<div class="container-lg vh-100 d-flex flex-column" style="min-width: 600px">
			<div class="row header position-relative">
				<div class="col d-flex align-items-center">
					<span class="logo ml-2">
						<Logo />
					</span>
					<p>Жывеграм</p>
				</div>
				<div class="d-flex align-items-center mr-3">
					<button type="button" class="btn hb" @click="openSettingsBtn">Настройки</button>
					<button type="button" class="btn hb" @click="logout">Выйти</button>
				</div>
			</div>
			<div class="row flex-grow-1 main-container">
				<component class="col-4" :is="secondaryComponent" v-bind="secondaryProps" :chats="chats" :isLoading="isChatsLoading"
					@openMessages="openMessages"
					@openNewChat="openNewChat"
					@openChatList="openChatList"/>

				<component class="col-8" :is="mainComponent" v-bind="mainProps" :chats="chats" :isLoading="isChatsLoading"
					@newMessage="newMessage"
					@delMessage="delMessage"/>
			</div>
		</div>
	</div>
</template>

<script>
	import Loading from '@/components/Loading';
	import ChatList from '@/components/ChatList/ChatList';
	import Messages from '@/components/Messages/Messages';
	import Settings from '@/components/Settings/Settings';
	import NewChat from '@/components/NewChat/NewChat';
	import Logo from '@/components/Logo';

	import Empty from '@/components/Empty';
	import axios from "axios";

	export default {
		name: 'Main',
		data() {
			return {
				mainComponent: 'Empty',
				mainProps: null,
				tempMainProps: null,

				secondaryComponent: 'ChatList',
				secondaryProps: null,

				isChatsLoading: true,
				notifications: [],
				chats: null,
				newChatsTemp: [],
				notificationInterval: null
			}
		},
		beforeMount() {
			this.getAllChats();
			this.checkNotifications();
			this.notificationInterval = setInterval(this.checkNotifications, 500);
		},
		methods: {
			logout() {
				clearInterval(this.notificationInterval);
				localStorage.clear();
				this.$router.push('/auth');
			},
			openMessages(data) {
				this.mainProps = data;
				this.mainComponent = "Messages";
			},
			openSettingsBtn() {
				if (this.mainComponent === "Settings" && this.tempMainProps == null) {
					this.mainComponent = "Empty";
				} else if (this.mainComponent === "Settings") {
					this.mainProps = this.tempMainProps;
					this.tempMainProps = null;
					this.mainComponent = "Messages";
				} else {
					this.tempMainProps = this.mainProps;
					this.mainProps = null;
					this.mainComponent = "Settings";
				}
			},
			openNewChat() {
				this.secondaryComponent = "NewChat";
			},
			openChatList() {
				this.getAllChats();
				this.secondaryComponent = "ChatList";
			},
			newMessage(message) {
				for (let i = 0; i < this.chats.length; i++) {
					if (this.chats[i].chatId === message.chatId) {
						let newChat = JSON.parse(JSON.stringify(this.chats[i]));
						newChat.messages.push(message.message);
						this.$set(this.chats, i, newChat);
					}
				}
			},
			delMessage(message) {
				for (let i = 0; i < this.chats.length; i++) {
					if (this.chats[i].chatId === message.chatId) {
						for (let j = this.chats[i].messages.length-1; j >= 0; j--) {
							if (this.chats[i].messages[j].messageId === message.messageId) {
								let newChat = JSON.parse(JSON.stringify(this.chats[i]));
								newChat.messages.splice(j, 1);
								this.$set(this.chats, i, newChat);
							}
						}
					}
				}
			},
			clearNotifications(chatId) {
				this.axios.get(this.$root.url+'chats/clearNotifications', {params: {uuid: localStorage.getItem('uuid'), chatId: chatId}})
					.then(response => {
						if (response.data.error) {
							location.reload();
						}
					});
			},
			getAllChats() {
				this.axios.get(this.$root.url+'chats/getAll', {params: {uuid: localStorage.getItem('uuid')}})
					.then(response => {
						if (!response.data.error) {
							let friends = [];
							this.chats = [];
							for (let i = 0; i < response.data.length; i++) {
								friends.push(response.data[i].mate.userId);
								this.$set(this.chats, i, response.data[i]);
							}
							localStorage.setItem('friends', JSON.stringify(friends));
						} else {
							// Неверный id, кто-то изменил localStorage
							location.reload();
						}
					});
			},
			checkNotifications() {
				axios.get(this.$root.url+'chats/checkNotifications', {params: {uuid: localStorage.getItem('uuid')}})
				.then(response => {
					if (response.data && response.data.notifications && this.chats) {

						let no = response.data.notifications;
						// Перебор всех уведомлений
						for (let i = 0; i < no.length; i++) {
							for (let j = 0; j < this.chats.length; j++) {
								// Чат найден
								if (no[i].chatId === this.chats[j].chatId) {
									let newChat;

									// Взаимное удаление deletedMsg и newMsg
									if (no[i].newMsg && no[i].deletedMsg) {
										for (let a = 0; a < no[i].newMsg.length; a++) {
											for (let b = 0; b < no[i].deletedMsg.length; b++) {
												if (no[i].newMsg[a].messageId === no[i].deletedMsg[b].messageId) {
													no[i].newMsg.splice(a, 1);
													a--;
													break;
												}
											}
										}
									}

									// Определение даты последнего сообщения
									let lmDate;
									if (this.chats[j].messages && this.chats[j].messages.length !== 0) {
										lmDate = (new Date(this.chats[j].messages[this.chats[j].messages.length - 1].sendTime)).getTime();
									}

									// Добавление новых сообщений
									for (let newMsg of no[i].newMsg) {
										if (!lmDate || (new Date(newMsg.sendTime)).getTime() > lmDate) {
											!newChat ? newChat = JSON.parse(JSON.stringify(this.chats[j])) : null;
											newChat.messages.push(newMsg);
										}
									}

									// Удаление удалённых сообщений
									for (let deletedMsg of no[i].deletedMsg) {
										for (let k = this.chats[j].messages.length-1; k >= 0; k--) {
											if (this.chats[j].messages[k].messageId === deletedMsg.messageId) {
												!newChat ? newChat = JSON.parse(JSON.stringify(this.chats[j])) : null;
												newChat.messages.splice(k, 1);
												break;
											} else if (this.chats[j].messages[k].messageId < deletedMsg.messageId) {
												break;
											}
										}
									}

									// Изменение количества новых сообщений
									let newMessagesCount = no[i].newMsg.length;
									if (this.chats[j].newMessageCount == null || this.chats[j].newMessageCount !== newMessagesCount) {
										!newChat ? newChat = JSON.parse(JSON.stringify(this.chats[j])) : null;
										newChat.newMessageCount = newMessagesCount;
									}

									// Очистка уведомлений, если открыт чат
									if (this.mainProps && this.mainProps.chatId === no[i].chatId) {
										!newChat ? newChat = JSON.parse(JSON.stringify(this.chats[j])) : null;
										newChat.newMessageCount = 0;
										this.clearNotifications(this.mainProps.chatId);
									}

									// Обновление чата, если есть изменения
									if (newChat) {
										this.$set(this.chats, j, newChat);
									}
								}
								// Конец Чат найден
							}
						}
						this.isChatsLoading = false;

					}
					// Появление нового чата в списке чатов
					if (response.data.newChats.length > 0) {
						this.newChatsTemp = response.data.newChats;
						if (this.chats) {
							for (let newChat of this.newChatsTemp) {
								this.$set(this.chats, this.chats.length, newChat);
							}
							this.newChatsTemp = null;
						}
					}

				});
			},
		},
		components: {
			ChatList,
			Messages,
			Settings,
			Logo,
			Loading,
			Empty,
			NewChat
		}
	}
</script>

<style lang="scss">
	.flip {

	}

	.text-sm {
		font-size: 0.85rem !important;
	}

	#app {
		background: var(--bg);
		font-family: cursive;
		font-size: 16px;
		z-index: 15;
	}

	.main-container {
		background-color: var(--container-bg);
	}

	.header {
		background: var(--header-bg); // цвет хедера сверху
		color: var(--header-text);
		height: 40px;

		.logo {
			width: 30px;
			fill: var(--header-text);
		}

		p {
			margin-bottom: 0;
			margin-left: 40px;
			font-size: 1.5em;
			display: inline;
		}
		.hb {
			height: 100%;
			background: transparent;
			border-radius: 0;
			color: var(--header-text);

			&:hover {
				background: rgba(255,255,255,0.3);
				color: black;
			}
			&:focus {
				box-shadow: 0 0 0;
			}
		}
	}
</style>
