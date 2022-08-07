<template>
  <div>
    <div class="box">
      <button @click="deleteCareer(this.info.id)" style="margin-right: 4px"><img class="DELETE" src="@/assets/DELETE.svg" alt="DELETE"></button>
      <button @click="Undate()"><img class="EDIT" src="@/assets/EDIT.svg" alt="EDIT"></button>
      <p class="title">입사날짜</p>
      <p class="contents">{{ this.start }}</p>
      <p class="title">퇴사날짜</p>
      <p class="contents">{{ this.finish }}</p>      
      <p class="title">기업</p>
      <p class="contents">{{ this.info.companyName }}</p>
      <hr>
      <p class="contents">{{ this.info.jobContents }}</p>
    
    </div>
    
  </div>
</template>
<script>
import { mapActions } from 'vuex'

export default {
  name: 'ResumeCareerDetail',
  props: {
    info: Object
  },

  data() {
    var start = this.info.startedAt.substring(0,10)
    var finish = this.info.finishedAt.substring(0,10)
    return {
      start, finish
    }
  },
  methods: {
    ...mapActions(['deleteCareer', 'updateCareer']),
    Update(){

    }

  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
}
</script>

<style scoped>
p {
  margin-bottom: 0px;
  text-align: left;
}

.box {
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);

  margin-bottom: 20px;
  padding: 15px
}

.title {
  font-size: 12px;
  margin-bottom: 1px;
  margin-left: 1px;
  color: #6D6D6D;
}

.contents {
  margin-bottom: 2px;
  font-weight: 500;
  color: black;
  
}

button {
  float: right;
  margin:2px;
  border: none;
  background-color: transparent;
}
/* .during{
  font-size: 13px;
}

.company {
  font-weight: 700;
  margin-bottom: 5px;
} */

</style>