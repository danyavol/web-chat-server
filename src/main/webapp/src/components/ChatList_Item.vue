<template>
    <li class="chat_item card">
        <div class="card-body p-2">
            <div class="d-flex flex-row justify-content-between">
                <p class="card-title"><strong>{{data.title}}</strong></p>
                <p>{{data.time}}</p>
            </div>
            <p class="card-text text-truncate text-muted">{{data.msg}}</p>
        </div>
    </li>
</template>

<script>
export default {
    props: {
        data: Object
    }
}
</script>

<style lang="scss">
    .chat_item {
        margin-bottom: 10px;

        &:last-child {
            margin-bottom: 0;
        }
    }

    /*.chat_item {*/
    /*    list-style: none;*/
    /*    padding: 5px;*/
    /*    margin: 0;*/
    /*    background: #c8d0e0; // цвет чата*/
    
    /*    .chat_item__header {*/
    /*        display: flex;*/
    /*        justify-content: space-between;*/
    /*        padding-bottom: 5px;*/

    /*        h4 {*/
    /*            font-weight: bold;*/
    /*            // background: whitesmoke;*/
    /*            padding: 0;*/
    /*            margin: 0;*/
    /*        }*/
    /*        p {*/
    /*            // background: wheat;*/
    /*            padding: 0;*/
    /*            margin: 0;*/
    /*        }*/
    /*    }*/

    /*    .chat_item__footer {*/
    /*        p {*/
    /*            // background: yellowgreen;*/
    /*            margin: 0;*/
    /*        }*/
    /*    }*/
    /*}*/
</style>