<template>
	<div id="app">
		<div class="container-lg vh-100 d-flex flex-column">
			<div class="row header">
				<div class="col">
					<p class="align-self-center">Телеконтакте</p>
				</div>
			</div>
			<div class="row flex-grow-1">
				<ChatList class="col-4" v-bind:chatLi="chat" />
				<Messages class="col-8" v-bind:messages="messages" />
			</div>
		</div>
	</div>
</template>

<script>
	import ChatList from '@/components/ChatList';
	import Messages from '@/components/Messages';

	export default {
		name: 'App',
		data() {
			return {
				chat: [
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
		components: {
			ChatList,
			Messages
		}
	}


</script>

<style lang="scss">
	/* @import './assets/styles/scrollbar.css'; */

	body {
		background:#e9e9eb;
		font-family: cursive;
		font-size: 16px;
	}

	.header {
		background: #ef4b4c; // цвет хедера сверху
		height: 40px;
		p {
			margin-left: 40px;
			font-size: 1.7em;
			display: inline;
			color: white;
		}
	}



	/*main {*/
	/*	max-width: 1024px;*/
	/*	margin: auto;*/
	/*	.header {*/
	/*		p {*/
	/*			margin: 0;*/
	/*			margin-left: 40px;*/
	/*			font-size: 1.7em;*/
	/*			display: inline;*/
	/*			color: white;*/
	/*		}*/
	/*	}*/

	/*	.container {*/
	/*		display: flex;*/
	/*		height: 92vh;*/
	/*	}*/
	/*}*/
</style>
