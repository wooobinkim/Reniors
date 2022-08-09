<template>
  <div class="total">
    <span v-if="!isEditing" class="content">
        <div>
            <p  class="title2">{{comment.contents}}</p>
            <div class="bottom">
                <img class="person" src="https://i7b307.p.ssafy.io/images/user/1" alt="person">
                <p  class="userName">{{comment.userName}} |</p>
                <p class="time">{{comment.updatedAt.slice(0,4)}}.{{comment.updatedAt.slice(5,7)}}.{{comment.updatedAt.slice(8,10)}}. {{(Number(comment.updatedAt.slice(11,13))+ 8)%24}}:{{comment.updatedAt.slice(14,16)}}</p>

            </div>
        </div>
    </span>

    <span v-if="isEditing">
        <input type="text" v-model="contents">
        <button @click="onUpdate" class="btn-open">수정</button>
        <button @click="switchIsEditing" class="btn-close"></button>
    </span>

    <span v-if="currentUser.id === comment.userId && !isEditing">
        <button @click="switchIsEditing" class="btn-open">수정</button>
        <button @click="onDelete()">삭제</button>
    </span>
    
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'CommentItem',
    components:{},
    props: {
        'comment': Object,
    },
    data(){
        return{
            isEditing: false,
            contents: this.comment.contents,
            categoryId: this.$route.params.category_id,
            boardId: this.$route.params.board_id,

        };
    },
    computed: {
        ...mapGetters(['currentUser', 'isAuthor']),     
    },
    watch:{
        comment: function(){
            this.fetchComments(this.boardId)
        }
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['updateComment', 'deleteComment', 'fetchComments']),
        switchIsEditing(){
            this.isEditing = !this.isEditing
        },
        onUpdate(){
            this.updateComment({
                categoryId: this.categoryId,
                boardId: this.boardId,
                commentId: this.comment.commentId,
                contents: this.contents
            })
            this.isEditing = false
        },
        onDelete(){
            this.deleteComment({
                categoryId: this.categoryId,
                boardId: this.boardId,
                commentId: this.comment.commentId,
                contents: this.contents
            })

        }


    }
}
</script>

<style scoped>
.total{
    border-bottom: solid 1px #FFD39B;
}
.content{
    height: 64px;
    display: flex;
    align-items: center;
    justify-content: left;
    margin: 0;
}
.title2{
    margin: 0 8px;
    font-size: 16px;
    display: flex;
    justify-content: start;


}
.bottom{
    display: flex;
    margin: 0 4px;
}
.person{
    width: 24px;
    height: 24px;
    border-radius: 50%;
    
}
.userName{
    margin: 0 8px;
    font-size: 14px;
    color: #6D6D6D;
    display: flex;
    justify-content: start;
    align-items: center;

}
.time{
    font-size: 12px;
    color: #6D6D6D;
    margin: 0;
    display: flex;
    justify-content: start;
    align-items: center;

}
</style>