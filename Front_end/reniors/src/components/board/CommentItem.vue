<template>
  <div class="comment-list-item d-flex justify-content-between align-items-center">
        
    <span v-if="!isEditing">{{ payload.content }}</span>

    <span v-if="isEditing">
      <input type="text" v-model="payload.content">
      <button @click="onUpdate" class="btn btn-open" >Update</button> |
      <button @click="switchIsEditing" class=" btn btn-close" ></button>
    </span>

    <span v-if="currentUser.pk === comment.user.pk && !isEditing">
      <button @click="switchIsEditing" class="btn btn-open">Edit </button>
      <button @click="onDelete(payload)" class=" btn btn-open">Delete</button>
    </span>
    <hr>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'CommentItem',
    components:{},
    props: {
        comment: Object,
    },
    data(){
        return{
            isEditing: false,
            payload: {
                article_pk: this.comment.article,
                comment_pk: this.comment.pk,
                content: this.comment.content
            }
        };
    },
    computed: {
        ...mapGetters(['currentUser', 'isAuthor']),     
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['updateComment', 'deleteComment']),
        switchIsEditing() {
            this.isEditing = !this.isEditing
        },
        onUpdate() {
            this.updateComment(this.payload)
            this.isEditing = false
        },
        onDelete(payload){
            this.deleteComment(payload)
            this.$router.go()
        }

    }
}
</script>