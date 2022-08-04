<template>
  <div>
    <article-form
        :article="article"
        action="create"
    ></article-form>
  </div>
</template>
<script>


import ArticleForm from '@/components/board/ArticleForm.vue';

export default{ 
    name:'BoardCreate',
    components:{ArticleForm},
    data(){
        return{
            article:{
                pk: null,
                title: '',
                content: '',
            }
        }
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>