<template>
	<div id="app">
		<div class="container-lg vh-100 d-flex flex-column">
			<div class="row header position-relative">
				<div class="col d-flex align-items-center">
					<span class="logo ml-2">
						<Logo />
					</span>
					<p>Мессенджер</p>
				</div>
				<div class="d-flex align-items-center mr-3">
					<button type="button" class="btn hb" @click="openSettingsBtn">Настройки</button>
					<button type="button" class="btn hb" @click="logout">Выйти</button>
				</div>
			</div>
			<div class="row flex-grow-1 bg-light">
				<component class="col-4" v-bind:is="secondaryComponent" v-bind="secondaryProps"
					@openMessages="openMessages"
					@openNewChat="openNewChat"
					@openChatList="openChatList"/>

				<component class="col-8" v-bind:is="mainComponent" v-bind="mainProps" @newMessage="newMessage"/>
			</div>
		</div>
	</div>
</template>

<script>
	import Loading from '@/components/Loading';
	import ChatList from '@/components/ChatList/ChatList';
	import Messages from '@/components/Messages/Messages';
	import Settings from '@/components/Settings/Settings';
	import NewChat from '@/components/NewChat/NewChat';
	import Logo from '@/components/Logo';

	import Empty from '@/components/Empty';

	export default {
		name: 'Main',
		data() {
			return {
				mainComponent: 'Empty',
				mainProps: null,
				secondaryComponent: 'ChatList',
				secondaryProps: null
			}
		},
		methods: {
			logout() {
        localStorage.removeItem('uuid');
				localStorage.removeItem('userId');
				localStorage.removeItem('login');
				localStorage.removeItem('name');
        localStorage.removeItem('colorScheme');
				this.$router.push('/auth');
			},
			openMessages(data) {
				this.mainProps = data;
				this.mainComponent = "Messages";
			},
			openSettingsBtn() {
				if (this.mainComponent === "Settings" && this.mainProps == null) {
					this.mainComponent = "Empty";
				} else if (this.mainComponent === "Settings") {
					this.mainComponent = "Messages";
				}else {
					this.mainComponent = "Settings";
				}
			},
			openNewChat() {
				this.secondaryComponent = "NewChat";
			},
			openChatList() {
				this.secondaryComponent = "ChatList";
			},
			newMessage(message) {
				this.secondaryProps = {newMessage: message};
			}
		},
		components: {
			ChatList,
			Messages,
			Settings,
			Logo,
			Loading,
			Empty,
			NewChat
		}
	}
</script>

<style lang="scss">
	.flip {

	}

	.text-sm {
		font-size: 0.85rem !important;
	}

	#app {
		background: #e9e9eb;
		font-family: cursive;
		font-size: 16px;
		z-index: 15;
	}

	.header {
		background: #ef4b4c; // цвет хедера сверху
		height: 40px;

		.logo {
			width: 30px;
		}

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
			&:focus {
				box-shadow: 0 0 0;
			}
		}
	}
</style>
