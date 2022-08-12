<template>
  <div class="list">
    <div class="one">
        <router-link :to="{ name:'openVidu' }" >
            <button class="btn1">
                <div>
                    <i class="bi bi-chat-square-quote-fill " style="font-size:48px; color:#FF843E;"></i>
                    <p style="color:#FF843E; font-size: 20px; font-weight: bold; margin: 4px auto;">면접 보러 가기</p>
                    
                </div>
            </button>
        </router-link>

    </div>
    <div class="two">
        <router-link :to="{ name:'QuestionList' }" >
            <button class="btn2">
                <div class="prac">
                    <i class="bi bi-camera-video" style="color:#37BF99; font-size:24px; font-weight:bold"></i>
                    <p style="margin: 4px 4px; font-size: 18px; color: #37BF99; font-weight:bold">면접 연습</p>
                </div>
            </button>
        </router-link>
        <router-link :to="{ name: 'PracticeBox'}">
            <button  class="btn3">
                <div>
                    <i style="font-size:20px; color:white; font-weight:bold; margin: auto;" class="bi bi-folder-check"></i>
                    <p style="font-size:14px; color:white; font-weight:bold; margin: auto;">내 연습함</p>

                </div>
            </button>
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
  background-color: #FFF5F0;

}
.one{
   width: 100%;
   justify-content: center;
   align-items: center;
    margin: 8px 0px;
}
.one a{
    text-decoration: none;
}
.two{
    width: 100%;
   
   justify-content: center;
   align-items: center;
   margin: 8px 0px;
}
.two a{
    text-decoration: none;
}
.btn1 {
    width: 82%;
    height: 160px;
    border: solid 1px;
    border-color: #FF843E;
    background-color: #FFF5F0;
    border-radius: 10px;
    background-color: white;
    box-shadow: 1px 1px 1px gray;
    
}
.btn2 {
    width: 56%;
    height: 80px;
    border: solid 1px;
    border-color: #37BF99;
    background-color: white;
    border-radius: 10px;
    margin: 0 2% 0 0;
    box-shadow: 0.5px 0.5px 0.5px gray;

}
.prac{
    display: flex;
    align-items: center;
    justify-content: center;
}
.btn3 {
    width: 22%;
    height: 80px;
    border: none;
    background-color: #37BF99;
    border-radius: 10px;
    margin: 0 0 0 2%;
    padding: 0;
    box-shadow: 0.5px 0.5px 0.5px gray;

}
</style>