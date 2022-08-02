<template>
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:1} }">경영지원 |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:2} }">영업기획 |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:3} }">해외업무 |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:4} }">IT |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:5} }">마케팅 |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:6} }">엔지니어링 |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:7} }">생산제조 |</router-link></div>
      <div class="swiper-slider"><router-link :to="{ name:'board', params:{job_parent_category_id:8} }">전문/특수 |</router-link></div>
      
    </div>
  </div>
</template>

<script> 

export default{ 
    name:'BoardHeadSlider',
    components:{},
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

<style>

</style>