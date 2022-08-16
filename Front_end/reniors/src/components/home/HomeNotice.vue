<template>
  <div class="home-notice">
    <div v-if="login">
      <p>
        🔔 오늘 확인하셔야 할 알림이
        <span class="home-notice-count" v-b-modal.noticeModal
          >{{ noticeCount }}개</span
        >
        있어요!
      </p>
      <HomeCalendarList />
    </div>
    <p v-else>
      지금 <router-link to="/login" class="now-login-btn">로그인</router-link>을
      하고<br />더 정확한 추천공고와 관리를 받아보세요!
    </p>
    <b-modal id="noticeModal" v-model="show" title="🔔 알림" hide-footer>
      <div class="notice-list d-block">
        <a
          v-for="(notice, index) in notices"
          :key="index"
          :href="
            'https://i7b307.p.ssafy.io/jobopening/' +
            notice.applyResponse.jobOpeningId
          "
        >
          <div
            v-if="notice.isRead === 'READ'"
            class="notice-item-read"
            @click="readNotification(notice.id)"
          >
            <p class="notice-item-company">
              {{ notice.jobOpeningResponse.companyName }}
            </p>
            <p class="notice-item-title">
              {{ notice.jobOpeningResponse.title }}
            </p>
            <p class="notice-item-result">
              {{ notice.applyResponse.jobOpeningProcess }}
            </p>
          </div>
          <div
            v-else
            class="notice-item-not-read"
            @click="readNotification(notice.id)"
          >
            <p class="notice-item-company">
              {{ notice.jobOpeningResponse.companyName }}
            </p>
            <p class="notice-item-title">
              {{ notice.jobOpeningResponse.title }}
            </p>
            <p class="notice-item-result">
              {{ notice.applyResponse.jobOpeningProcess }}
            </p>
          </div>

          <!-- { "id": 2, "jobOpeningProcess": "서류불합격", "isRead": "NOT_READ", "createdAt": "2022-08-15T08:46:41", 
          "applyResponse": { "id": 20, "jobOpeningProcess": "서류불합격", "interviewDate": null, "jobOpeningId": 3, 
          "jobOpeningTitle": "공고3", "jobChildCategoryName": "내방객응대", "sessionId": null, "userId": 17, "name": "아니요", 
          "gender": "M", "birth": "2022-08-12T00:00:00.000+00:00", "phone": "01010101010" },
          "jobOpeningResponse": { "id": 3, "createdDate": "2022-08-12T00:00:00.000+00:00", "finishedDate": "2022-08-25T00:00:00.000+00:00", 
          "title": "공고3", "isFinish": "F", "companyName": "company" } } } -->
        </a>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { computed } from "vue";
import { mapActions, useStore } from "vuex";
import HomeCalendarList from "./HomeCalendarList.vue";

export default {
  name: "HomeNotice",
  components: {
    HomeCalendarList,
  },
  props: {
    login: Boolean,
  },
  setup() {
    const store = useStore();

    const fetchNotices = () => store.dispatch("home/fetchNotices");
    fetchNotices();
    const noticeCount = computed(() => store.getters["home/noticeNotReaded"]);
    const notices = computed(() => store.getters["home/notices"]);

    return {
      noticeCount,
      notices,
    };
  },
  methods: {
    ...mapActions("home", ["readNotice"]),
    readNotification(notificationId) {
      this.readNotice(notificationId);
    },
  },
};
</script>

<style scoped>
.home-notice {
  padding: 0;
}

.home-notice p {
  text-align: left;
  margin-left: 10px;
  font-weight: bold;
}

.home-notice > div > p {
  font-weight: bold;
}

.home-notice > div > p > a {
  color: var(--color-green-1);
}

.home-notice-count {
  color: var(--color-green-1);
  text-decoration: none;
  font-size: 20px;
}

.home-notice-count:hover {
  cursor: pointer;
}

.notice-list > a {
  text-decoration: none;
  color: black;
}

.notice-list > a:hover {
  color: black;
}

.notice-item-read {
  background-color: var(--color-black-4);
  border-radius: 0.5rem;
  padding: 5px;
  margin-bottom: 10px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.notice-item-not-read {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 5px;
  margin-bottom: 10px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.notice-item p {
  margin: 0;
}
.now-login-btn {
  color: var(--color-green-1);
  font-weight: bold;
  font-size: 18px;
}
.now-login-btn:hover {
  color: var(--color-green-1);
}
</style>
