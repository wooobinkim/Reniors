<template>
  <div>  
    <board-head-slider></board-head-slider>
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
export default{ 
    name:'BoardHome',
    components:{
      BoardHeadSlider,
      ArticleItem
    },
    data(){
        return{
            sampleData:''
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>