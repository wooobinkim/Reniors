<template>
  <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
  <div class="company-main-btn">

    <div class="company-main-btn-top">

      <div @click="jobopening()" class="company-main-btn-top-item">
       <div>
        <i class="bi bi-file-earmark-text"></i>
       </div>
       <div>채용관리</div>
      </div>

      <div @click="interview()" class="company-main-btn-top-item">
      <div>
        <i class="bi bi-camera-video"></i>
        </div>
        <div>면접</div>
      </div>
      
    </div>

    <div class="company-main-btn-bottom">
      <div @click="mypage()" class="company-main-btn-bottom-item">
      <div>
      <i class="bi bi-person"></i>
      </div>
      <div>
      마이페이지
      </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  methods: {
    ...mapActions("company",["setheader","getCompany"]),
    jobopening(){
      this.setheader('채용관리')
      this.$router.push({name:"companyjobopening"});
    },
    interview(){
      this.setheader('면접')
      this.$router.push({name:"companyinterview"});
    },
    mypage(){
      this.setheader('마이페이지')
      this.$router.push({name:"companymypage"});
    }
  },
  created(){
    this.getCompany();
  },
};
</script>

<style>
/* .company-main-btn{
  display: flex;
} */
.company-main-btn-top{
 display: flex;
 justify-content: center;
}
.company-main-btn-top-item{
   /* position: relative; */
  width: 150px;
  height: 200px;
  margin: 12px;
  border: 1px solid black;
  background-color: #8cd6c1;
}
.company-main-btn-top-item > div:first-child{
   margin-top: 50px;
  
}
.company-main-btn-top-item > div > i{
   font-size: 36px;
   
}
.company-main-btn-top-item > div > div{
   font-size: 20px;
}
.company-main-btn-bottom{
  display: flex;
  justify-content: center;
}
.company-main-btn-bottom-item{
  width: 150px;
  height: 200px;
  border: 1px solid black;
  background-color: #eaeaea;
}
.company-main-btn-bottom-item > div:first-child{
   margin-top: 50px;
  
}
.company-main-btn-bottom-item > div > i{
   font-size: 36px;
   
}
.company-main-btn-bottom-item > div > div{
   font-size: 20px;
}
</style>
