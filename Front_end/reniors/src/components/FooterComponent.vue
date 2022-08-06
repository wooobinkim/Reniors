<template>
  <div class="footer">
    <router-link class="footroute" :to="{name: 'home'}">
      <i class="bi bi-house"></i>
      <p>home</p>
    </router-link> 
    <router-link class="footroute" :to="{name:'companyjobopening'}">
      <i class="bi bi-file-earmark-text"></i>
      <p>채용공고</p>
    </router-link> 
    <router-link class="footroute" to="/mypage">
      <i class="bi bi-person"></i>
      <p>MyPage</p>
    </router-link> 
    <router-link  v-if="inter.id !== 1" class="footroute" :to="{name: 'boardMain', params:{'category_id' : inter.id}}">
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> 
    <router-link v-if="inter.id === 1" class="footroute" :to="{name: 'boardMain', params:{'category_id' : 1}}">
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> 
    <router-link class="footroute" :to="{name: 'QuestionList'}">
      <i class="bi bi-camera-video"></i>
      <p>면접/연습</p>
    </router-link>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
  name: "FooterComponent",
  data(){
    return{
      inter: {id: 1},
    }
  },
  methods:{
    ...mapActions(['fetchInterest'])
  },
  created(){
    this.fetchInterest()
  },
  computed:{
    ...mapGetters({inter : 'interest'}),
  },
};
</script>

<style scoped>

.footer {
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-around;
  height: 64px;
  width: 100%;
  bottom: 0;
  border: 1px solid var(--color-black-4);
  background: var(--color-black-5);
}
.footer a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}
.footroute p {
  font-size: 12px;
  margin: 0px 0px 4px;
}

.footer a.router-link-exact-active {
  color: #f3620f;
}
</style>