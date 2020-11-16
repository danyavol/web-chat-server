<template>
	<div class="newChat_item card shadow-sm">
		<div class="card-body p-2">
			<div class="row">
				<div class="col-2 align-self-center">
					<span class="avatar color1">{{this.user.name[0]}}</span>
				</div>
				<div class="col">
					<div class="d-flex h-100 align-items-center justify-content-between">
						<p class="card-title mb-0 mate"><span>@{{this.user.login}}</span><strong class="pl-2">{{this.user.name}}</strong></p>
						<span class="mr-2 addNewChat p-2" @click="addNewChat">
							<font-awesome-icon icon="plus" class="fa-lg" v-if="!loading"/>
							<div class="spinner-border text-success" role="status" v-if="loading">
								<span class="sr-only">Loading...</span>
							</div>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		user: Object
	},
	data() {
		return {
			loading: false
		}
	},
	methods: {
		addNewChat() {
			this.loading = true;
			this.axios.post(this.$root.url+'chats/new', {}, {params: {myUuid: localStorage.getItem('uuid'), mateId: this.user.userId}})
				.then(response => {
					if (!response.data.error) {
						let friends = localStorage.getItem('friends')
						friends ? friends = JSON.parse(friends) : friends = [];
						friends.push(response.data.mate.userId);
						localStorage.setItem('friends', JSON.stringify(friends));
						this.$emit('removeFromSearch', response.data.mate.userId);
					} else {
						// Неверный id, кто-то изменил localStorage
						location.reload();
					}
				});
		}
	}
}
</script>

<style lang="scss">
	.newChat_item {
		margin-bottom: 10px;

		&:last-child {
			margin-bottom: 0;
		}
	}

	.addNewChat {
		cursor: pointer;
		transition-duration: 0.2s;
		color: #8fd19e;

		&:hover {
			transition-duration: 0.2s;
			color: #28a745;
		}

		.spinner-border {
			width: 1.3rem;
			height: 1.3rem;
			border-width: 0.2em;
		}
	}
</style>