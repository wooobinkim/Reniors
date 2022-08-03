<template>
  <div>
    <h1>{{article.title}}</h1>
    <h3>{{article.content}}</h3>

    <div v-if="isAuthor">
        <router-link :to="{ name: 'BoardUpdate'}">
            <button>수정하기</button>
        </router-link>

        <button @click="deleteArticle({article_pk})">삭제</button>
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
            article_pk: this.$route.params.jobBoardId
        };
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
        this.fetchArticle({article_pk: this.article_pk})
    },
}
</script>