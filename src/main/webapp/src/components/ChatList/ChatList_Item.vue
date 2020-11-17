<template>
	<div class="chat_item card shadow-sm" style="max-width: 100%" @click="openMessages">
		<div class="card-body p-2">
			<div class="row">
				<div class="col-2 align-self-center">
					<span class="avatar" :class="'color'+chat.mate.colorScheme">{{chat.mate.name[0]}}</span>
				</div>
				<div class="col-10">
					<div class="d-flex justify-content-between">
						<p class="card-title mb-1 mate text-truncate"><span>@{{chat.mate.login}}</span><strong class="pl-2">{{chat.mate.name}}</strong></p>
						<p class="mb-1 text-secondary text-sm">{{msg ? this.$root.getDate(msg.sendTime) : ''}}</p>
					</div>
					<div class="d-flex justify-content-between align-items-center">
						<p class="card-text mb-0 text-truncate text-muted"><span class="text-primary mr-1" v-if="isMe">Вы:</span>{{msg ? msg.messageText : 'Нет сообщений'}}</p>
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
			isMe: false,
			msg: null
		}
	},
	beforeMount() {
		this.checkIfMe();
		this.updateMessage();
	},
	beforeUpdate() {
		this.checkIfMe();
		this.updateMessage();
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
		updateMessage() {
			// if (this.chat && this.chat.messages) {
				if (this.chat.messages[1]) {
					this.msg = this.chat.messages[1];
				} else if (this.chat.messages[0]) {
					this.msg = this.chat.messages[0];
				} else {
					this.msg = null;
				}
			// }
		},
		checkIfMe() {
			if (this.chat.messages[1]) {
				this.isMe = this.chat.messages[1].senderId !== this.chat.mate.userId;
			} else if (this.chat.messages[0]) {
				this.isMe = this.chat.messages[0].sender.userId !== this.chat.mate.userId;
			}
		}
	}
}
</script>

<style lang="scss">
	.chat_item {
		margin-bottom: 10px;
		cursor: pointer;

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