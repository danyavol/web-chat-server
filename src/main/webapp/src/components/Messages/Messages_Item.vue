<template>
		<div class="msg_item card w-75 shadow-sm" v-bind:class="{'ml-auto my-msg': this.isMe}">
			<div class="card-body p-2">
				<div class="msg_header mb-2 d-flex justify-content-between">
					<p class="secondary-text mb-0">{{this.isMe ? myName : mateName}}</p>
					<div class="d-flex align-items-center">
						<span class="secondary-text text-sm" v-if="!isLoading">{{this.$root.getDate(obj.sendTime)}}</span>
						<div class="spinner-border spinner-border-sm messageLoading secondary-text" v-if="isLoading" role="status">
							<span class="sr-only">Loading...</span>
						</div>
						<span v-on:click="confirmDelete" class="position-relative" v-if="isMe && !isLoading">
							<font-awesome-icon :icon="['far', 'trash-alt']" class="secondary-text ml-3 deleteMsg"/>
							<div class="confirmDelete position-absolute text-sm d-none" v-on:click="deleteMessage">Удалить сообщение?</div>
						</span>
					</div>
				</div>
				<div class="msg_content">
					<pre class="mb-0">{{obj.messageText}}</pre>
				</div>
			</div>
		</div>
</template>

<script>
	export default {
		props: {
			msg: Object,
			localMsg: Object,
			mateName: String
		},
		data () {
			return {
				obj: {},
				isMe: true,
				isLoading: false,
				myName: '',
				timeoutShow: setTimeout(()=>{}, 0),
				timeoutDisplay: setTimeout(()=>{}, 0)
			}
		},
		beforeMount() {
			this.myName = localStorage.getItem('name');
			if (this.localMsg == null) {
				this.obj = this.msg;
				this.isMe = localStorage.getItem('userId') === this.obj.sender.userId;
			} else {
				this.obj = this.localMsg;
				this.isLoading = true;
			}
		},
		methods: {
			confirmDelete(e) {
				let n = 0, span;
				while (e.path[n].tagName !== "SPAN") n++;
				span = e.path[n];

				let confirmation = span.querySelector('.confirmDelete');

				// Удаление старого timeout

					clearTimeout(this.timeoutShow);
					clearTimeout(this.timeoutDisplay);


				confirmation.classList.toggle('d-none');
				setTimeout(()=>{confirmation.classList.add('show')}, 0);
				this.timeoutShow = setTimeout(() => {
					confirmation.classList.remove('show')
					this.timeoutDisplay = setTimeout(()=>{confirmation.classList.toggle('d-none')}, 800);
				}, 3000);

				// console.log(confirmation);
			},
			deleteMessage() {
				this.isLoading = true;
				this.$emit('delMsg', this.msg.messageId);
			}
		}
	}
</script>

<style lang="scss">
	.my-msg {
		background-color: var(--my-msg-bg) !important;
	}
	.msg_item {
		//box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
		margin-bottom: 10px;
		background-color: var(--mate-msg-bg);

		.msg_content pre {
			color: var(--primary-text);
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

		.messageLoading {
			margin-top: 3px;
			margin-right: 15px;
			display: inline-block !important;
			line-height: 1rem;
			> div {
				margin: 0 !important;
				width: 1rem;
				height: 1rem;
			}
		}

		.deleteMsg {
			transition-duration: 0.2s;
			user-select: none;
			&:hover {
				transition-duration: 0.2s;
				color: #ef4b4c !important;
			}
		}

		.confirmDelete {
			opacity: 0;
			transition-duration: 0.8s;
			padding: 5px;
			text-align: center;
			line-height: 1.2em;
			border-radius: 10px 0 10px 10px;
			border: 1px solid #ef4b4c;
			color: #ef4b4c;
			background: #fbdddd;
			right: 0;
			user-select: none;
			&.show {
				cursor: pointer;
				opacity: 1;
				transition-duration: 0.2s;
			}
			&.show:hover {
				box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
			}
		}
	}
</style>