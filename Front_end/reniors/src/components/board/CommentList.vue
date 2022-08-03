<template>
  <div class="comment-list container">
    <comment-form></comment-form>
    <br>
    <br>
    <div class="row">
      <comment-item class="col-8 offset-2" 
        v-for="comment in comments" 
        :comment="comment" 
        :key="comment.pk">
      </comment-item>        

    </div>

  </div>
</template>
<script>


import CommentForm from './CommentForm.vue';
import CommentItem from './CommentItem.vue';
export default{ 
    name:'CommentList',
    components:{ CommentForm, CommentItem },
    props: { comments: Array },
    data(){
        return{
            sampleData:''
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>