<template>
	<div class="chat_item card shadow-sm">
		<div class="card-body p-2">
			<div class="row">
				<div class="col-2 align-self-center">
					<span class="avatar">{{this.user.name[0]}}</span>
				</div>
				<div class="col">
					<div class="d-flex h-100 align-items-center">
						<p class="card-title mb-0 mate"><span>@{{this.user.login}}</span><strong class="pl-2">{{this.user.name}}</strong></p>
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
	}
}
</script>

<style lang="scss">

</style>