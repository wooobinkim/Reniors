<template>
  <div>
    <article-form
        :article="article"
        action="create"
        :category_pk="categoryId"
    ></article-form>
  </div>
</template>
<script>


import ArticleForm from '@/components/board/ArticleForm.vue';

export default{ 
    name:'BoardCreate',
    components:{ ArticleForm },
    data(){
        return{
            categoryId:this.$route.params.category_id,
            article:{
                pk: null,
                title: '',
                contents: '',
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