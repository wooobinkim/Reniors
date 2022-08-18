<template>
  <div class="total">
    <div class="content">
        <div class="toptitle">
            <p>다시 시작하는 나의 <span>일</span>생</p>
            <img src="@/assets/logo_cut.png" alt="logo">
        </div>
        <div class="personaImg">
            <img src="@/assets/persona.png" alt="personaimg">
        </div>
        <div class="bottom">
            <div class="bottomsub">
                <p><img src="@/assets/logo_korean.png" alt="">에서</p>
            </div>
            <p><span>당신의 제 2막</span>을 시작해보세요!</p>
        </div>

    </div>
    
  </div>
</template>
<script>

export default{ 
    name:'IntroView',
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
.total{
    background-color: #FFF5F0;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1;
}
.toptitle p{
    font-size: 20px;
    font-weight: bold;
    color: #FF843E;
    margin: 4px;
}
.toptitle span{
    color: #37BF99;
}
.toptitle img{
    width: 80%;
    height: auto;
    margin: auto;
}
.personaImg img{
    width: 300px;
    height: auto;
    margin: 16px 8px;
}
.goBtn{
    width: 80%;
    height: 8%;
    border: none;
    border-radius: 10px;
    background-color: #FF843E;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}
.goBtn p{
    font-weight: bold;
    font-size: 24px;
    color: white;
    margin: 4px;
}
.goBtn span{
    color: #37BF99;
}
.bottomsub{
    display: flex;
    justify-content: center;
    align-items: center;
}
.bottomsub p{
    font-size: 20px;
    color: #6d6d6d;
    margin: 4px;
}
.bottomsub img{
    width: 100px;
    height: auto;
    margin: 4px;
}
.bottom p{
    font-size: 20px;
    color: #6d6d6d;
    margin: 4px;
}
.bottom span{
    text-decoration:underline #37BF99 2px;

}


</style>