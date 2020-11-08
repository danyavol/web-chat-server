<template>
		<div class="container bg-light p-0 position-relative d-flex flex-column" >

			<div class="msg-container-header mb-3 p-2 d-flex align-items-center flex-row">
				<span class="avatar ml-2">{{this.mateName[0]}}</span>
				<span class="ml-3">@{{ this.mateLogin }}</span>
				<strong class="ml-2">{{ this.mateName }}</strong>
			</div>

			<div id="msg-container" class="overflow-auto position-relative flex-grow-1 mx-0" >
				<Loading v-if="messages == null" />
				<div class="position-absolute d-flex flex-column msg-wrapper px-3" v-if="messages != null">
					<p class="text-center text-secondary" v-if="messages.length === 0">Нет сообщений</p>
					<Message v-bind:key="item.id" v-for="item in messages" v-bind:data="item" />
				</div>
			</div>

			<div class="msg-container-bottom mt-3 d-flex justify-content-center py-3">
				<textarea type="text" class="w-75" />
				<input type="button" class="btn btn-outline-primary ml-3" value="Отправить">
			</div>

		</div>
</template>

<script>
	import Message from '@/components/Messages_Item'
	import Loading from '@/components/Loading';

	export default {
		props: {
			chatId: String,
			mateLogin: String,
			mateName: String
		},
		data () {
			return {
				messages: null,
			}
		},
		beforeMount() {
			this.updateMessages();
		},
		mounted ()  {
			// let msg = document.getElementById('msg-container');
			// msg.scrollTo(0, msg.scrollHeight);
			// console.log(document.querySelector('#msg-container'));
		},
		watch: {
			chatId() {
				this.messages = null;
				this.updateMessages();
			},
			messages() {
				if (this.messages != null) {
					setTimeout(() => {
						let msg = document.querySelector('#msg-container');
						msg.scrollTo(0, msg.scrollHeight);
					},0);
				}
			}
		},
		methods: {
			updateMessages() {
				this.axios.get(this.$root.url+'chats/getOne', {params: {userId: localStorage.getItem('userId'), chatId: this.chatId}})
					.then(response => {
						if (!response.data.error) {
							this.messages = response.data.messages;
						} else {
							location.reload();
						}
					});
			}
		},
		components: {
			Message,
			Loading
		}
	}
</script>

<style lang="scss">
	.msg-container-header {
		border-bottom: 1px solid #dee2e6;
		background: white;
	}
	.msg-container-bottom {
		border-top: 1px solid #dee2e6;
		background: white;
	}
	.msg-wrapper {
		min-width: 100%;
		min-height: 100%;
		justify-content: flex-end;
	}
</style>