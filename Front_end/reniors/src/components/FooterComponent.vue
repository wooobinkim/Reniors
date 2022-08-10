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
    <div v-if="isLogginedIn" class="footer-profile" @click="dropdown">
      <img :src="this.currentUser.baseURL + this.currentUser.userProfile" alt="">
      <div class="footer-profile-dropdown" id="footerDropdown">
        <router-link to="/mypage">MyPage</router-link> <br>
        <router-link to="/logout">Logout</router-link> 
      </div>
    </div> 
    <router-link v-else class="footroute" to="/login">
      <i class="bi bi-person"></i>
      <p>Login</p>
    </router-link> 
    <router-link  v-if="inter.id !== 1" class="footroute" :to="{name: 'boardMain', params:{'category_id' : inter.id}}">
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> 
    <router-link v-if="inter.id === 1" class="footroute" :to="{name: 'boardMain', params:{'category_id' : 1}}">
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> 
    <router-link class="footroute" :to="{name: 'VideoMain'}">
      <i class="bi bi-camera-video"></i>
      <p>면접/연습</p>
    </router-link>
    
  </div>
</template>

<script>
import { mapActions,  mapGetters,  } from 'vuex';
export default {
  name: "FooterComponent",
  data(){
    return{
      inter: {id: 1},
      check : false
    }
  },
  methods:{
    ...mapActions(['fetchInterest']),
    dropdown () {
      document.querySelector('#footerDropdown').classList.toggle('active')

    }
  },
  created(){
    this.fetchInterest()
  },
  computed:{
    ...mapGetters(['interest', 'currentUser', 'isLogginedIn']),
  },
};
</script>

<style scoped>

.footer {
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-around;
  height: 50px;
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

.footer a.router-link-active {
  color: #f3620f;
}

.footer-profile {
  position: relative;
  height: 50px;
  width: 50px;
  padding: 5px;
}

.footer-profile img {
  border-radius: 5rem;
  height: 100%;
}

.footer-profile-dropdown {
  position: absolute;
  display: none;
  left: -25px;
  bottom: 60px;
  width: 100px;
  background-color: white;
  border: 1px solid black;
  border-radius: 0.5rem;
}

.footer-profile-dropdown > a {
  font-size: 14px;
}

.footer-profile-dropdown:before {
  content: '';
	position: absolute;
	bottom: 0;
	left: 50%;
	width: 0;
	height: 0;
	border: 7px solid black;
	border-top-color: #ffffff;
	border-bottom: 0;
	margin-left: -7px;
	margin-bottom: -7px;
}

.footer-profile-dropdown:after {
  content: '';
  position: absolute;
  bottom: -1px;
  left: 50%;
  width: 0;
  height: 0;
  border: 7px solid white;
  border-top-color: transparent;
  border-bottom: 0;
  margin-left: -7px;
  margin-bottom: -7px;
}

.footer-profile-dropdown.active {
  display: block;
}
</style>