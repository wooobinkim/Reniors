<template>
  <div>  
    <board-head-slider></board-head-slider>
    <p></p>
    <div v-if="isLogginedIn">
      <router-link :to="{name: 'boardCreate', params:{category_id: category_id}}">
        <button>글쓰기</button>
      </router-link>

    </div>
    <article-item
      v-for="article in articles"
      :key="article.boardId"
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
          category_id : this.$route.params.category_id
        }
    },
    computed:{
      ...mapGetters(['articles', 'isLogginedIn'])
    },
    methods:{
      ...mapActions(['fetchArticles'])
    },
    created(){
      this.fetchArticles(this.category_id)
    },
    mounted(){},
    unmounted(){},
    
    
}
</script>