<template>
    <ul class="chat bg-light p-3 m-0 border-right">
		<Loading v-if="chatsList == null"></Loading>
		<p class="text-center" v-if="chatsList && chatsList.length === 0">У вас нет диалогов</p>
        <ChatListItem v-bind:key="item.chatId" v-for="item in chatsList" v-bind:data="item" @openChat="openChat"/>
    </ul>
</template>

<script>
import ChatListItem from '@/components/ChatList_Item';
import Loading from '@/components/Loading';

export default {
	data() {
		return {
			chatsList: null
		}
	},
	beforeMount() {
		this.axios.get(this.$root.url+'chats/getAll', {params: {userId: localStorage.getItem('userId')}})
			.then(response => {
				if (!response.data.error) {
					this.chatsList = response.data;
				} else {
					// Неверный id, кто-то изменил localStorage
					location.reload();
				}
			});
	},
	methods: {
		openChat(data) {
			this.$emit('openChat', data);
		}
	},
	components: {
        ChatListItem,
		Loading
    }
}
</script>

<style lang="scss">
    /*.chat {*/
    /*    padding: 12px;*/
    /*    margin: 0;*/
    /*    //background: #43506c; // бэкграунд чатов*/
    /*    width: 30%;*/
    /*    min-width: 190px;*/
    /*    overflow-y: auto;*/
    
    /*    .chat_item {*/
    /*        margin-bottom: 8px;*/
    /*        */
    /*        &:last-child {*/
    /*            margin-bottom: 0;*/
    /*        }*/
    /*    }*/
    /*}*/
</style>