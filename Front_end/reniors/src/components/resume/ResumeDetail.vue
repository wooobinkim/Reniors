<template>
  <div>
    <header style="width: 360px">
      <div style="margin-top: 16px; padding: 10px; background-color: #f9f9f9">
        <span class="title">나의 이력서</span>
      </div>
    </header>
    <div style="width: 312px; margin: auto">
      <br />
      <div>
        <img
          class="person"
          :src="this.currentUser.baseURL + this.currentUser.userProfile"
          alt="person"
        />
      </div>
      <br />
      <br />
      <div class="basic">
        <p style="font-weight: 700">{{ currentUser.name }}</p>
        <p style="font-size: 13px; color: #636363">
          {{ currentUser.gender == "M" ? "남자" : "여자" }} |
          <span v-if="currentUser.birth">{{
            currentUser.birth.slice(0, 10)
          }}</span>
        </p>
        <p style="font-size: 13px; color: #636363">
          {{ currentUser.lastEdu.replace("_", " ") }}
        </p>
      </div>
      <div class="sub">
        <p>
          <span style="color: #8a8a8a"><i class="bi bi-telephone"></i></span
          >&emsp;{{ currentUser.phone }}
        </p>
        <p v-if="currentUser.kakaoId === null">
          <span style="color: #8a8a8a"><i class="bi bi-envelope"></i></span
          >&emsp;{{ currentUser.userAppId }}
        </p>
        <p v-else>
          <span style="color: #8a8a8a"><i class="bi bi-envelope"></i></span
          >&emsp;{{ currentUser.kakaoId }}
        </p>
        <p>
          <span style="color: #8a8a8a"><i class="bi bi-house-door"></i></span
          >&emsp;{{ currentUser.address }}
        </p>
      </div>

      <div v-if="careers">
        <hr />
        <p class="subtitle mb-3">경력 사항</p>
        <p class="box pt-3 pb-3" v-for="career in careers" :key="career">
          <span style="color: #8a8a8a">기업명</span
          ><span>&emsp;{{ career.companyName }}<br /></span>
          <span style="color: #8a8a8a">입사일</span
          ><span>&emsp;{{ career.startedAt.slice(0, 10) }}<br /></span>
          <span style="color: #8a8a8a">퇴사일</span
          ><span>&emsp;{{ career.finishedAt.slice(0, 10) }}<br /></span>
          <span style="color: #8a8a8a"></span><br /><span
            >{{ career.jobContents }}<br
          /></span>
        </p>
      </div>

      <div v-if="licenses">
        <hr />
        <p class="subtitle mb-3">자격증</p>
        <p
          class="box pt-3 pb-3"
          v-for="(license, index) in licenses"
          :key="index"
        >
          <span style="color: #8a8a8a">자격증</span
          ><span>&emsp;{{ license.name }}<br /></span>
          <span style="color: #8a8a8a">취득일</span
          ><span>&emsp;{{ license.passedAt.slice(0, 10) }}<br /></span>
          <span style="color: #8a8a8a">등급</span
          ><span>&emsp;{{ license.grade }}<br /></span>
        </p>
      </div>

      <div v-if="awards">
        <hr />
        <p class="subtitle mb-3">수상 내역</p>
        <p v-for="award in awards" :key="award">
          <span style="color: #8a8a8a">수상명</span
          ><span>&emsp;{{ award.name }}<br /></span>
          <span style="color: #8a8a8a">수상일</span
          ><span>&emsp;{{ award.awardedAt.slice(0, 10) }}<br /></span>
        </p>
      </div>

      <button>
        <router-link
          style="text-decoration: none; color: white"
          :to="{ name: 'ResumeStepOne' }"
          >수정</router-link
        >
      </button>
    </div>
  </div>
</template>
<script>
// import ResumeCareerDetail from '@/components/resume/ResumeCareerDetail.vue'
import { mapGetters, mapActions } from "vuex";

export default {
  name: "ResumeDetail",
  components: {},
  computed: {
    ...mapGetters(["currentUser", "careers", "licenses", "awards"]),
  },
  data() {
    return {};
  },

  setup() {},
  created() {
    this.fetchCareer();
    this.fetchLicense();
    this.fetchAward();
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["fetchCareer", "fetchLicense", "fetchAward"]),
  },
};
</script>

<style scoped>
p {
  margin-bottom: 3px;
  text-align: left;
}

span {
  margin-bottom: 3px;
  font-size: 13px;
}
.basic > p {
  margin-left: 110px;
}

.title {
  font-size: 22px;
  margin-top: 4px;
  color: #ffb400;
  font-weight: 900;
}

img {
  width: 90px;
  height: 120px;
  float: left;
}

.sub {
  margin-top: 20px;
  color: #636363;
}

.sub > p {
  margin-left: 0px;
  font-size: 13px;
}

.subtitle {
  text-align: left;
  color: #37bf99;
  font-weight: 700;
  font-size: 18px;
}

.box {
  border-style: none none dashed none;
  border-width: 0.5px;
  border-color: #eaeaea;
}

button {
  float: right;
  height: 40px;
  width: 88px;
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.05);
  border-radius: 10px;
  border-style: none;
  background-color: #ffb400;
  font-weight: 700;
  color: white;
}
</style>
