<template>
  <div>
    <h1>article edit</h1>
    <article-form
        v-if="isArticle"
        :article="article"
        :category_pk="category_pk"
        action="update"
    ></article-form>
  </div>
</template>
<script>

import ArticleForm from '@/components/board/ArticleForm.vue';
import { mapActions, mapGetters } from 'vuex';
export default{ 
    name:'BoardUpdate',
    components:{ArticleForm},
    data(){
        return{
            category_pk: this.$route.params.jobParentCategoryId,
            article_pk: this.$route.params.jobBoardId
        };
    },
    computed: {
        ...mapGetters(['article', 'isArticle'])
    },
    setup(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchArticle'])
    },
    created(){
        this.fetchArticle({category_pk:this.category_pk, article_pk: this.article_pk})
    },
}
</script>