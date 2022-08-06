<template>
  <form @submit.prevent="onSubmit" class="comment-list-form">
    <label for="comment"></label>
    <input type="text" id="content" placeholder="내용을 입력해주세요." v-model="content">
    <button type="submit">등록</button>
  </form>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'CommentForm',
    components:{},
    data(){
        return{
            content: '',
        }
    },
    computed: {
        ...mapGetters(['article'])
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['createComment']),
        onSubmit() {
            this.createComment({
                boardId: this.$route.params.board_id,
                categoryId: this.$route.params.category_id,
                content: this.content
                })
            this.content= ''
            this.$router.go()
        }
    }
}
</script>

<style>
.commentInput{
   width: 500px;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}

</style>