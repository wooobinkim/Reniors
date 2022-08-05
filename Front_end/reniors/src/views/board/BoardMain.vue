<template>
  <div>  
    <board-head-slider></board-head-slider>
    <p></p>
    <article-item
      v-for="article in articles"
      :key="article.pk"
      :article="article"
    ></article-item>
  </div>
</template>
<script>

import BoardHeadSlider from '@/components/board/BoardHeadSlider.vue'
import ArticleItem from '@/components/board/ArticleItem.vue'
import { mapActions, mapGetters } from 'vuex';
export default{ 
    name:'BoardHome',
    components:{
      BoardHeadSlider,
      ArticleItem
    },
    data(){
        return{
        }
    },
    computed:{
      ...mapGetters([''])
    },
    methods:{
      ...mapActions(['fetchArticles'])
    },
    created(){
      this.fetchArticles()
    },
    mounted(){},
    unmounted(){},
    
}
</script>