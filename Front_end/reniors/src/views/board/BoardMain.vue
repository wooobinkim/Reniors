<template>
  <div style="width:360px">  
    <board-head-slider></board-head-slider>
    <div class="title">
      <p>{{jobname}}</p>
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
          category_id : Number(this.$route.params.category_id),
          jobname: ""
        }
    },
    watch:{
      $route: function(from){
        if ((from.path).includes('boards')) {
          this.fetchArticles(from.params.category_id)
          this.jobname = this.parents[Number(from.params.category_id) -1].name
        }
      }
    },
    computed:{
      ...mapGetters(['articles', 'isLogginedIn', 'parents'])
    },
    methods:{
      ...mapActions(['fetchArticles', 'fetchParents'])
    },
    created(){
      console.log(this.$route.params.category_id);
      this.fetchParents()
      console.log( this.parents[this.$route.params.category_id - 1]);
      this.fetchArticles(this.category_id)
      this.jobname = this.parents[this.$route.params.category_id - 1].name

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