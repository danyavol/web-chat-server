<template>
	<div class="chat_item card shadow-sm" style="max-width: 100%" @click="openMessages">
		<div class="card-body p-2">
			<div class="row">
				<div class="col-2 align-self-center d-none d-md-block">
					<span class="avatar" :class="'color'+chat.mate.colorScheme">{{chat.mate.name[0]}}</span>
				</div>
				<div class="col-md-10">
					<div class="d-flex justify-content-between">
						<p class="card-title mb-1 mate text-truncate"><span>@{{chat.mate.login}}</span><strong class="pl-2">{{chat.mate.name}}</strong></p>
						<p class="mb-1 secondary-text text-sm">{{chat.messages.length ? this.$root.getDate(chat.messages[chat.messages.length-1].sendTime) : ''}}</p>
					</div>
					<div class="d-flex justify-content-between align-items-center">
						<p class="card-text mb-0 text-truncate secondary-text"><span class="primary-text2 mr-1" v-if="isMe">Вы:</span>{{chat.messages.length ? chat.messages[chat.messages.length-1].messageText : 'Нет сообщений'}}</p>
						<span class="badge badge-pill badge-primary ml-1" v-if="chat.newMessageCount">{{chat.newMessageCount}}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		chat: Object
	},
	data() {
		return {
			isMe: false
		}
	},
	beforeMount() {
		this.checkIfMe();

	},
	beforeUpdate() {
		this.checkIfMe();
	},
	methods: {
		openMessages() {
			this.$emit('openMessages', {
				chatId: this.chat.chatId,
				mateName: this.chat.mate.name,
				mateLogin: this.chat.mate.login,
				mateColor: this.chat.mate.colorScheme
			});
		},
		checkIfMe() {
			let messages = this.chat.messages;
			if (messages.length === 0) return this.isMe = false;
			if (messages[messages.length - 1].senderId) {
				this.isMe = messages[messages.length - 1].senderId !== this.chat.mate.userId;
			} else {
				this.isMe = messages[messages.length - 1].sender.userId !== this.chat.mate.userId;
			}
		}
	}
}
</script>

<style lang="scss">
	.chat_item {
		background-color: var(--card-bg);
		margin-bottom: 10px;
		cursor: pointer;

		.card-title {
			color: var(--primary-text);
		}

		&:last-child {
			margin-bottom: 0;
		}
	}

	.avatar {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 40px;
		height: 40px;
		border-radius: 50%;
	}
</style>