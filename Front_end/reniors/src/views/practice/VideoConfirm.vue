<template>
  <div>
    <p>나와라;;{{id}}</p>
  </div>
</template>
<script>

export default{ 
    name:'VideoConfirm',
    components:{},
    data(){
        return{
            id : this.$route.params.videoID
        };
    },
    props:{

    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>