<template>
  <div>
    <h1>{{article.title}}</h1>
    <h3>{{article.contents}}</h3>

    <div v-if="isAuthor">
        <router-link :to="{ name: 'boardUpdate', parmas:{'category_id': categoryId, 'board_id': articleId}}">
            <button>수정하기</button>
        </router-link>

        <button @click="deleteArticle({article_pk: articleId, categoryId: categoryId})">삭제</button>
    </div>
    <comment-list></comment-list>
  </div>
</template>
<script>

import CommentList from '@/components/board/CommentList.vue';
import { mapActions, mapGetters } from 'vuex';
export default{ 
    name:'BoardDetail',
    components:{ CommentList },
    data(){
        return{
            'categoryId': this.$route.params.category_id,
            'articleId': this.$route.params.board_id
        }
    },
    computed: {
        ...mapGetters(['article', 'isAuthor', 'currentUser'])
    },
    setup(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchArticle', 'deleteArticle'])
    },
    created(){
        this.fetchArticle(this.articleId)
    },
}
</script>