<template>
  <div>
    게시판!!
    <board-head-slider></board-head-slider>
  </div>
</template>
<script>

import BoardHeadSlider from '@/components/board/BoardHeadSlider.vue';

export default{ 
    name:'BoardHome',
    components:{
      BoardHeadSlider,
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