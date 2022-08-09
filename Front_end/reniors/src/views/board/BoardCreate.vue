<template>
  <div  style="width:360px">
    <board-head-slider></board-head-slider>
    <div class="title">
        <router-link :to="{name: 'boardMain', params:{category_id: categoryId}}">
            <i class="bi bi-arrow-left-circle-fill" style="color:#FFD39B; font-size: 24px; margin:0 16px"></i>
        </router-link>
        <p>{{parents[categoryId-1].name}}</p>
    </div>
    <article-form
        :article="article"
        action="create"
        :category_pk="categoryId"
    ></article-form>
  </div>
</template>
<script>

import BoardHeadSlider from '@/components/board/BoardHeadSlider.vue'
import ArticleForm from '@/components/board/ArticleForm.vue';
import { mapGetters } from 'vuex';

export default{ 
    name:'BoardCreate',
    components:{ ArticleForm, BoardHeadSlider },
    data(){
        return{
            categoryId:this.$route.params.category_id,
            article:{
                pk: null,
                title: '',
                contents: '',
            }
        }
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{},
    computed:{
        ...mapGetters(['parents'])
    }
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