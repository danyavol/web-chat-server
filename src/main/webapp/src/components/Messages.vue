<template>
    <div class="container bg-light p-0 position-relative d-flex flex-column">

        <div class="card mb-3 p-3">
            <p class="card-text">Арсений Говша</p>
        </div>

        <div id="msg-container" class="overflow-auto position-relative flex-grow-1 mx-3" >
            <div class="position-absolute d-flex flex-column">
                <Message v-bind:key="item.id" v-for="item in messages" v-bind:data="item"/>
            </div>
        </div>

        <div class="card mt-3 p-5">

        </div>


    </div>
</template>

<script>
    import Message from '@/components/Messages_Item'

    export default {
        props: {
            messages: Array
        },
        components: {
            Message
        },
        mounted: () => {
            let msg = document.getElementById('msg-container');
            msg.scrollTo(0, msg.scrollHeight);
        }
    }
</script>

<style lang="scss">
    /*::-webkit-scrollbar:vertical {*/
    /*    display: none;*/
    /*}*/
    
    /*#msg {*/
    /*    width: 70%;*/
    /*    min-width: 220px;*/
    /*    background:#43506c; // бэкграунд раздела сообщений*/
    /*    overflow-y: scroll;*/
    /*    position: relative;*/

    /*    .msg_content {*/
    /*        margin: 10px;*/
    /*        display: flex;*/
    /*        flex-direction: column;*/
    /*        */
    /*        > div {*/
    /*            margin-bottom: 10px;*/
    /*            */
    /*            &:last-child {*/
    /*                margin-bottom: 0;*/
    /*        }*/
    /*    }*/
    /*    }*/
    /*}*/
</style>