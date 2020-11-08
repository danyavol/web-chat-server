<template>
	<div id="app">
		<div class="container-lg vh-100 d-flex flex-column">
			<div class="row header position-relative">
				<div class="col d-flex align-items-center">
					<span v-on:click="dropDown" >
						<Hamburger id="hamb"/>
					</span>
					<p>Мессенджер</p>
				</div>
				<div class="d-flex align-items-center mr-3">
					<button type="button" class="btn hb" id="settings">Настройки</button>
					<button type="button" class="btn hb" @click="logout">Выйти</button>
				</div>
			</div>
			<div class="row flex-grow-1 bg-light">
				<component class="col-4" v-bind:is="secondaryComponent"
					@openMessages="openMessages"
					@openNewChat="openNewChat"
					@openChatList="openChatList"/>

				<component class="col-8" v-bind:is="mainComponent" v-bind="mainProps"/>
			</div>
		</div>
	</div>
</template>

<script>
	import Loading from '@/components/Loading';
	import ChatList from '@/components/ChatList/ChatList';
	import Messages from '@/components/Messages/Messages';
	import NewChat from '@/components/NewChat/NewChat';
	import Hamburger from '@/components/Hamburger';

	export default {
		name: 'Main',
		data() {
			return {
				secondaryComponent: 'ChatList',
				mainComponent: '',
				mainProps: {}
			}
		},
		methods: {
			logout() {
				localStorage.removeItem('userId');
				localStorage.removeItem('login');
				localStorage.removeItem('name');
				this.$router.push('/auth');
			},
			dropDown() {
				document.getElementById('hamb').classList.toggle('active');
			},
			openMessages(data) {
				this.mainProps = data;
				this.mainComponent = "Messages";
			},
			openNewChat() {
				this.secondaryComponent = "NewChat";
			},
			openChatList() {
				this.secondaryComponent = "ChatList";
			}
		},
		components: {
			ChatList,
			Messages,
			Hamburger,
			Loading,
			NewChat
		}
	}
</script>

<style lang="scss">
	#app {
		background: #e9e9eb;
		font-family: cursive;
		font-size: 16px;
		z-index: 15;
	}

	.header {
		background: #ef4b4c; // цвет хедера сверху
		height: 40px;
		p {
			margin-bottom: 0;
			margin-left: 40px;
			font-size: 1.5em;
			display: inline;
			color: white;
		}
		.hb {
			height: 100%;
			background: transparent;
			color: white;
			border-radius: 0;
			&:hover {
				background: rgba(255,255,255,0.3);
				color: black;
			}
		}
	}
</style>
