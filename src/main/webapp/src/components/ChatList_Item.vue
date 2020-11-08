<template>
	<li class="chat_item card" @click="openChat">
		<div class="card-body p-2">
			<div class="row">
				<div class="col-2 align-self-center">
					<span class="avatar">{{data.mate.name[0]}}</span>
				</div>
				<div class="col">
					<div class="d-flex justify-content-between">
						<p class="card-title mb-1 mate"><span>@{{data.mate.login}}</span><strong class="pl-2">{{data.mate.name}}</strong></p>
						<p class="mb-1">{{data.messages[0] ? convertDate(data.messages[0].sendTime) : ''}}</p>
					</div>
					<p class="card-text text-truncate text-muted">{{data.messages[0] ? data.messages[0].messageText : 'Нет сообщений'}}</p>
				</div>
			</div>
		</div>
	</li>
</template>

<script>
export default {
	props: {
		data: Object
	},
	methods: {
		convertDate(dateStr) {
			let date = new Date(dateStr);
			return date.getHours()+':'+date.getMinutes();
		},
		openChat() {
			this.$emit('openChat', {
				chatId: this.data.chatId,
				mateName: this.data.mate.name,
				mateLogin: this.data.mate.login
			});
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
		text-align: center;
		padding-top: 7px;
		display: block;
		border-radius: 50%;
		width: 40px;
		height: 40px;
		background: #8fd19e;
		color: white;
	}

	/*.chat_item {*/
	/*    list-style: none;*/
	/*    padding: 5px;*/
	/*    margin: 0;*/
	/*    background: #c8d0e0; // цвет чата*/

	/*    .chat_item__header {*/
	/*        display: flex;*/
	/*        justify-content: space-between;*/
	/*        padding-bottom: 5px;*/

	/*        h4 {*/
	/*            font-weight: bold;*/
	/*            // background: whitesmoke;*/
	/*            padding: 0;*/
	/*            margin: 0;*/
	/*        }*/
	/*        p {*/
	/*            // background: wheat;*/
	/*            padding: 0;*/
	/*            margin: 0;*/
	/*        }*/
	/*    }*/

	/*    .chat_item__footer {*/
	/*        p {*/
	/*            // background: yellowgreen;*/
	/*            margin: 0;*/
	/*        }*/
	/*    }*/
	/*}*/
</style>