<template>
		<div class="msg_item card w-75" v-bind:class="{'ml-auto': this.isMe}">
			<div class="card-body p-2">
				<div class="msg_header d-flex justify-content-between">
					<p>{{data.senderName}}</p>
					<p>{{convertDate(data.sendTime)}}</p>
				</div>
				<div class="msg_content">
					<p class="text">{{data.messageText}}</p>
				</div>
			</div>
		</div>
</template>

<script>
	export default {
		props: {
			data: Object
		},
		data () {
			return {
				isMe: null
			}
		},
		beforeMount() {
			this.isMe = localStorage.getItem('login') === this.data.senderLogin;
		},
		methods: {
			convertDate(dateStr) {
				let date = new Date(dateStr);
				return date.getHours()+':'+date.getMinutes();
			}
		}
	}
</script>

<style lang="scss">
	.msg_item {
		margin-bottom: 10px;
		&:last-child {
			margin-bottom: 0;
		}
	}
</style>