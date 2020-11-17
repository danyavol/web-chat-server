<template>
	<div class="chat_item card shadow-sm" style="max-width: 100%" @click="openMessages">
		<div class="card-body p-2">
			<div class="row">
				<div class="col-2 align-self-center">
					<span class="avatar" :class="'color'+data.mate.colorScheme">{{data.mate.name[0]}}</span>
				</div>
				<div class="col-10">
					<div class="d-flex justify-content-between">
						<p class="card-title mb-1 mate text-truncate"><span>@{{data.mate.login}}</span><strong class="pl-2">{{data.mate.name}}</strong></p>
						<p class="mb-1 text-secondary text-sm">{{data.messages[0] ? this.$root.getDate(data.messages[0].sendTime) : ''}}</p>
					</div>
					<p class="card-text text-truncate text-muted"><span class="text-primary mr-1" v-if="isMe">Вы:</span>{{data.messages[0] ? data.messages[0].messageText : 'Нет сообщений'}}</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		data: Object
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
				chatId: this.data.chatId,
				mateName: this.data.mate.name,
				mateLogin: this.data.mate.login,
				mateColor: this.data.mate.colorScheme
			});
		},
		checkIfMe() {
			this.isMe = this.data.messages[0] && this.data.messages[0].sender.userId === localStorage.getItem('userId');
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