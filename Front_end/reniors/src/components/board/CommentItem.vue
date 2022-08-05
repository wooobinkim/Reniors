<template>
  <div>
    <span v-if="!isEditing">
        {{ comment.contents }} | {{comment.userName}}
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