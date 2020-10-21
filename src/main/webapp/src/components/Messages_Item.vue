<template>
        <div class="msg_item card w-75" v-bind:class="{'ml-auto': data.isMe}">
<!--            -->
            <div class="card-body p-2">
                <div class="msg_header d-flex justify-content-between">
                    <p>{{data.sender}}</p>
                    <p>{{data.time}}</p>
                </div>
                <div class="msg_content">
                    <p class="text">{{data.msg}}</p>
                </div>
            </div>
        </div>
</template>

<script>
    export default {
        props: {
            data: Object
        }
    }
</script>

<style lang="scss">
    .msg_item {
        margin-bottom: 10px;
        &:last-child {
            margin-bottom: 0;
        }
    }
    /*.msg_item {*/
    /*    background: #c8d0e0; // цвет сообщений*/
    /*    padding: 5px;*/

    /*    .msg_header {*/
    /*        display: flex;*/
    /*        justify-content: space-between;*/

    /*        h4 {*/
    /*            margin: 0;*/
    /*        }*/

    /*        p {*/
    /*            margin: 0;*/
    /*        }*/
    /*    }*/

    /*    .msg_content {*/
    /*        margin-top: 15px;*/

    /*        .text {*/
    /*            margin: 0;*/
    /*        }*/
    /*    }*/
    /*}*/
</style>