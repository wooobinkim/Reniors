<template>
  <div class="comment-list container">
    <comment-form></comment-form>

    <br>
    <div class="row">
      <p>댓글목록</p>
      <comment-item  
        v-for="(comment, idx) in comments" 
        :comment="comment" 
        :key="idx">
      </comment-item>        

    </div>

  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';


import CommentForm from './CommentForm.vue';
import CommentItem from './CommentItem.vue';
export default{ 
    name:'CommentList',
    components:{ CommentForm, CommentItem },
    data(){
        return{
            articleId: this.$route.params.board_id
        };
    },
    setup(){},
    
    mounted(){},
    unmounted(){},
    methods:{
      ...mapActions(['fetchComments'])
    },
    created(){
      this.fetchComments(this.articleId)
    },
    computed: {
      ...mapGetters(['comments'])
    }
}
</script>