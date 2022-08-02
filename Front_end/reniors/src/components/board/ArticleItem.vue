<template>
  <div>

  </div>
</template>
<script>

export default{ 
    name:'ArticleItem',
    props:{},
    components:{},
    data(){
        return{
            article_pk : this.$route.params.article_pk,
            
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>