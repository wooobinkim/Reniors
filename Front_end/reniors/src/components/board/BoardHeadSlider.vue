<template>
  <div>
    <router-link :to="{ name:'managementSupport' }">경영지원</router-link>
    <router-link :to="{ name:'salesPlanning' }">영업기획</router-link>
    <router-link :to="{ name:'overseasBusiness' }">해외업무</router-link>
    <router-link :to="{ name:'marketing' }">마케팅</router-link>
    <router-link :to="{ name:'it' }">IT</router-link>
    <router-link :to="{ name:'engineering' }">엔지니어링</router-link>
    <router-link :to="{ name:'productionManufacturing' }">생상제조</router-link>
    <router-link :to="{ name:'specialty' }">전문/특수</router-link>
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