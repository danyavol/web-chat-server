<template>
		<div class="container main-component p-0 position-relative d-flex flex-column" >

			<div class="msg-container-header p-2 d-flex align-items-center flex-row">
				<span class="avatar ml-3" :class="'color'+mateColor">{{this.mateName[0]}}</span>
				<span class="ml-3">@{{ this.mateLogin }}</span>
				<strong class="ml-2">{{ this.mateName }}</strong>
			</div>

			<div id="msg-container" class="overflow-auto position-relative flex-grow-1 mx-0" >
				<Loading v-if="messages == null" />
				<div class="position-absolute d-flex flex-column msg-wrapper px-3" v-if="messages != null">
					<p class="text-center secondary-text" v-if="messages.length === 0">Нет сообщений</p>
					<Message v-bind:key="item.messageId" v-for="item in messages" v-bind:msg="item" v-bind:mateName="mateName" @delMsg="deleteMessage"/>
					<Message v-bind:key="index" v-for="(item, index) in localMessages" v-bind:localMsg="item" v-bind:mateName="mateName"/>
				</div>
			</div>

			<div class="msg-container-bottom py-3">
				<div class="d-flex ">
					<div style="width: calc(100% - 145px)" class="pl-3">
						<div contenteditable="true" id="message-input" class="secondary-text" @keypress="keyPress" @focusin="inputFocusIn" @focusout="inputFocusOut" placeholder="Напишите сообщение...">Напишите сообщение...</div>
					</div>
					<div style="min-width: 145px" class="px-3">
						<input type="button" class="btn btn-outline-primary w-100" value="Отправить" @click="sendMessage">
					</div>
				</div>
			</div>

		</div>
</template>

<script>
	import Message from '@/components/Messages/Messages_Item'
	import Loading from '@/components/Loading';

	export default {
		props: {
			chats: Array,

			chatId: String,
			mateLogin: String,
			mateName: String,
			mateColor: Number
		},
		data () {
			return {
				messages: [],
				localMessages: {},
				localMessageCounter: 0,
				mate: null
			}
		},
		beforeMount() {
			this.updateMessages();
		},
		watch: {
			chatId() {
				this.updateMessages();
			},
			chats() {
				this.updateMessages();
			},
			messages() {
				if (this.messages != null) {
					setTimeout(() => {
						let msg = document.querySelector('#msg-container');
						msg.scrollTo(0, msg.scrollHeight);
					},0);
				}
			},
			localMessages() {
				setTimeout(() => {
					let msg = document.querySelector('#msg-container');
					msg.scrollTo(0, msg.scrollHeight);
				},0);
			}
		},
		methods: {
			updateMessages() {
				for (let chat of this.chats) {
					if (chat.chatId === this.chatId) {
						this.messages = chat.messages;
					}
				}
			},
			sendMessage() {
				let input = document.getElementById('message-input');
				if (input.innerText !== input.getAttribute('placeholder') && !input.innerText.match(/^\s*$/)) {
					let localMsg = {messageText: input.innerText, sender: {name: localStorage.getItem('name')}};

					let localMsgId = this.localMessageCounter++;
					this.$set(this.localMessages, 'i'+localMsgId, localMsg);

					input.innerText = '';

					this.axios.post(this.$root.url+'msg/new', {}, {params: {uuid: localStorage.getItem('uuid'), chatId: this.chatId, message: localMsg.messageText}})
						.then(response => {
							if (!response.data.error) {
								delete this.localMessages['i'+localMsgId];

								this.$emit('newMessage', {chatId: this.chatId, message: response.data});
							} else {
								location.reload();
							}
						});
				}
			},
			deleteMessage(msgId) {
				this.axios.delete(this.$root.url+'msg/delete', {params: {uuid: localStorage.getItem('uuid'), chatId: this.chatId, messageId: msgId}})
					.then(response => {
						if (!response.data.error) {
							this.$emit('delMessage', {chatId: this.chatId, messageId: msgId});
						} else {
							location.reload();
						}
					});
			},
			keyPress(e) {
				if (!e.shiftKey && e.key === 'Enter') {
					e.preventDefault();
					this.sendMessage();
				}
			},
			inputFocusIn(e) {
				if(e.target.innerText === e.target.getAttribute('placeholder')) {
					e.target.innerText = '';
					e.target.classList.remove('secondary-text');
				}
			},
			inputFocusOut(e) {
				if(e.target.innerText === '') {
					e.target.innerText = e.target.getAttribute('placeholder');
					e.target.classList.add('secondary-text');
				}

			}
		},
		components: {
			Message,
			Loading
		}
	}
</script>

<style lang="scss">

	.main-component {
		background-color: var(--container-bg);
	}

	#message-input {
		border: 1px solid var(--border);
		border-radius: 5px;
		padding: 5px 20px;
		width: 100%;
		max-height: 300px;
		overflow: auto;
		color: var(--secondary-text);

		&:focus {
			border: 1px solid var(--secondary-text);
			outline: 0;
		}
	}

	.msg-container-header {
		border-bottom: 1px solid var(--border);
		background: var(--card-bg);
		box-shadow: 0px 10px 10px -10px rgba(34, 60, 80, 0.1);
		z-index: 1;
		height: 60px;
	}
	.msg-container-bottom {
		border-top: 1px solid var(--border);
		background: var(--card-bg);
		box-shadow: 0px -10px 10px -10px rgba(34, 60, 80, 0.1);
		z-index: 1;
	}
	.msg-wrapper {
		min-width: 100%;
		min-height: 100%;
		justify-content: flex-end;
	}
</style>