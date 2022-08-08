<template>
  <div>  
    <board-head-slider></board-head-slider>
    <div class="title">
      <p>{{parents[category_id - 1].name}}</p>
      <div v-if="isLogginedIn">
        <router-link :to="{name: 'boardCreate', params:{category_id: category_id}}">
          <i class="bi bi-plus-circle-fill" style="color:#FFD39B; font-size: 24px; margin:0 16px"></i>
        </router-link>
      </div>
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
    watch:{
      $route: function(from){
        this.fetchArticles(from.params.category_id)
      }
    },
    computed:{
      ...mapGetters(['articles', 'isLogginedIn', 'parents'])
    },
    methods:{
      ...mapActions(['fetchArticles'])
    },
    created(){
      console.log("----------------------------------");
      console.log(this.category_id);
      this.fetchArticles(this.category_id)
    },
    mounted(){},
    unmounted(){},
    
    
}
</script>

<style scoped>
.title{
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #FF843E;
}
.title p{
  font-size: 20px;
  font-weight: bold;
  color: #FFB400;
  margin: 0 16px;
}


</style>