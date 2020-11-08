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
					<button type="button" class="btn hb" id="logout">Выйти</button>
				</div>
			</div>
			<div class="row flex-grow-1 bg-light">
				<component class="col-4" v-bind:is="secondaryComponent" @openChat="openChat" />
<!--				<ChatList class="col-4" v-bind:chatLi="chats" />-->
<!--				<Messages class="col-8" v-bind:messages="messages" />-->
				<component class="col-8" v-bind:is="mainComponent" v-bind="mainProps"/>
			</div>
		</div>
	</div>
</template>

<script>
	import Loading from '@/components/Loading';
	import ChatList from '@/components/ChatList';
	import Messages from '@/components/Messages';
	import Hamburger from '@/components/Hamburger';

	export default {
		name: 'Main',
		data() {
			return {
				secondaryComponent: 'ChatList',
				mainComponent: '',
				mainProps: {},
				currentPage: 'Main',
				chats: [
					{id:1, title: 'Заводник Владислав', time: '16:45', msg: 'Последнее очень длинное сообщение которе никак не влезет'},
					{id:2, title: 'Арсений Говша', time: '13:30', msg: 'Последнее очень длинное сообщение которе никак не влезет'},
					{id:3, title: 'Китурко Роман', time: '12:25', msg: 'Последнее очень длинное сообщение которе никак не влезет'},
					{id:4, title: 'Голомбевский Андрей', time: '18:15', msg: 'Последнее очень длинное сообщение которе никак не влезет'}
				],
				messages: [
					{id: 1, isMe: true, sender: 'Даниил Волосюк', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 2, isMe: true, sender: 'Даниил Волосюк', time: '16:11', msg: 'Сейчас кое-что расскажу'},
					{id: 3, isMe: false, sender: 'Арсений Говша', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 4, isMe: false, sender: 'Арсений Говша', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 5, isMe: true, sender: 'Даниил Волосюк', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 6, isMe: false, sender: 'Арсений Говша', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 7, isMe: true, sender: 'Даниил Волосюк', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 8, isMe: true, sender: 'Даниил Волосюк', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 9, isMe: false, sender: 'Арсений Говша', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
					{id: 10, isMe: true, sender: 'Даниил Волосюк', time: '16:13', msg: 'Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.Мой большой длинный рассказ о том, как я провел лето.'},
				]
			}
		},
		mounted() {
			let logout = document.getElementById('logout');
			logout.addEventListener('click', () => {
				localStorage.removeItem('userId');
				localStorage.removeItem('login');
				localStorage.removeItem('name');
				this.$router.push('/auth');
			})
		},
		methods: {
			dropDown () {
				document.getElementById('hamb').classList.toggle('active');
			},
			openChat (data) {
				this.mainProps = data;
				this.mainComponent = "Messages";
			}
		},
		components: {
			ChatList,
			Messages,
			Hamburger,
			Loading
		}
	}
</script>

<style lang="scss">
	#app {
		background: #e9e9eb;
		font-family: cursive;
		font-size: 16px;
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
				background: white;
				color: black;
			}
		}
	}
</style>
