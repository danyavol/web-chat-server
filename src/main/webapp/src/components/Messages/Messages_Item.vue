<template>
		<div class="msg_item card w-75" v-bind:class="{'ml-auto': this.isMe}">
			<div class="card-body p-2">
				<div class="msg_header d-flex justify-content-between">
					<p class="text-secondary mb-2">{{mateName}}</p>
					<p class="mb-2 text-secondary">{{this.$root.getDate(data.sendTime)}}</p>
				</div>
				<div class="msg_content">
					<pre class="mb-0">{{data.messageText}}</pre>
				</div>
			</div>
		</div>
</template>

<script>
	export default {
		props: {
			data: Object,
			mateName: String
		},
		data () {
			return {
				isMe: null
			}
		},
		beforeMount() {
			this.isMe = localStorage.getItem('login') === this.data.senderLogin;
		}
	}
</script>

<style lang="scss">
	.msg_item {
		box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
		margin-bottom: 10px;

		.msg_content pre{
			font-size: 1rem;
			font-family: cursive;
			white-space: pre-wrap;
		}

		&:last-child {
			margin-bottom: 20px;
		}
		&:first-child {
			margin-top: 20px;
		}
	}
</style>