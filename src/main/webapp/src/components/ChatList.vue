<template>
    <ul class="chat bg-light p-3 m-0 border-right">
        <ChatListItem v-bind:key="item.id" v-for="item in chatLi" v-bind:data="item"/>
    </ul>
</template>

<script>
import ChatListItem from '@/components/ChatList_Item';

export default {
    props: [
        'chatLi'
    ],
    components: {
        ChatListItem
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