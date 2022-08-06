<template>
  <div>
    <router-link :to="{ name: 'boardDetail', params: {'board_id': article.boardId, 'category_id': category_id}}">
        <h3>
            {{ article.userName }} | {{ article.title }}
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
            'category_id' : this.$route.params.category_id,
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{},
}
</script>