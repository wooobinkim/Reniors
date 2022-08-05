<template>
  <div>
    <router-link :to="{ name: 'BoardDetail', params: {board_id: article_pk}}">
        <h3>
            {{ article.user }} | {{ article.title }}
        </h3>
    </router-link>
  </div>
</template>
<script>

export default{ 
    name:'ArticleItem',
    props:{
        article: Object,
    },
    data(){
        return{
            article_pk : this.$route.params.article_pk,
            
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>