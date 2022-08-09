<template>
  <div class="comment-list">
  
    <div class="row">
      <comment-item  
        v-for="(comment, idx) in comments" 
        :comment="comment" 
        :key="idx">
      </comment-item>        

    </div>
    <comment-form></comment-form>

  </div>
</template>
<script>


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
    props:{
      comments: Array,
    },
    setup(){},
    
    mounted(){},
    unmounted(){},
 
}
</script>

<style scoped>

</style>