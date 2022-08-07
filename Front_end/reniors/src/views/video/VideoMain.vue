<template>
  <div class="list">
    <div class="one">
        <router-link to="/" >
            <button class="btn1">면접</button>
        </router-link>

    </div>
    <div class="two">
        <router-link :to="{ name:'QuestionList' }" >
            <button class="btn2">면접연습</button>
        </router-link>
        <router-link to="/">
            <button  class="btn3">연습함</button>
        </router-link>

    </div>
  </div>
</template>
<script>

export default{ 
    name:'VideoMain',
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

<style scoped>
.list{
  display: flex;
  flex-direction: column;

  justify-content: center;
  min-height: 100vh;

}
.one{
   width: 100%;
   justify-content: center;
   align-items: center;
    margin: 8px 0px;
}
.two{
    width: 100%;
   justify-content: center;
   align-items: center;
   margin: 8px 0px;
}
.btn1 {
    width: 80%;
    height: 160px;
    border: solid;
    border-color: #FF843E;
    background-color: #FFF5F0;
    border-radius: 10px;
    
}
.btn2 {
    width: 56%;
    height: 80px;
    border: solid;
    border-color: #37BF99;
    background-color: #ECFFFA;
    border-radius: 10px;
    margin: 0 2% 0 0;

}
.btn3 {
    width: 20%;
    height: 80px;
    border: solid;
    border-color: #37BF99;
    background-color: #37BF99;
    border-radius: 10px;
    margin: 0 0 0 2%;

}
</style>