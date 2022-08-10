<template>
  <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
  <button @click="logout()">로그아웃</button>
  <div>
    <router-link :to="{ name: 'companyjobopening' }">채용관리</router-link>
  </div>
  <div>
    <router-link :to="{ name: 'companyinterview' }">면접</router-link>
  </div>
  <div>
    <router-link :to="{ name: 'companymypage' }">마이페이지</router-link>
  </div>

  <input type="button" value="말하기" @click="speech()" />
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem("vuex");
      localStorage.removeItem("token");
      this.$router.push({ name: "Login" });
    },
    speech() {
      // let voices = [];
      // voices = window.speechSynthesis.getVoices();

      // let lang = "ko-KR";
      // let utterThis = new SpeechSynthesisUtterance(txt);
      // utterThis.onend = function () {
      //   console.log("end");
      // };
      // utterThis.onerror = function () {
      //   console.log("error");
      // };

      // console.log(voices);

      // let voiceFound = false;
      // for (let i = 0; i < voices.length; i++) {
      //   if (
      //     voices[i].lang.indexOf(lang) >= 0 ||
      //     voices[i].lang.indexOf(lang.replace("-", "_")) >= 0
      //   ) {
      //     utterThis.voice = voices[i];
      //     voiceFound = true;
      //   }
      // }

      // if (!voiceFound) {
      //   alert("voice not found");
      //   return;
      // }

      // utterThis.lang = lang;
      // utterThis.pitch = 1;
      // utterThis.rate = 1; //속도
      let utterThis = new SpeechSynthesisUtterance(
        "나이가 있으신데 이 일 하시기에 무리는 없으신가요?"
      );
      window.speechSynthesis.speak(utterThis);
    },
  },
};
</script>

<style></style>
