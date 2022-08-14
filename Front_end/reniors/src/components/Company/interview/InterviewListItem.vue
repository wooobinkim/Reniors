<template>
  <div>
    <div>{{ interviewapply.name }}</div>
    <div>{{ new Date(interviewapply.interviewDate).getFullYear() }}
    -{{new Date(interviewapply.interviewDate).getMonth()+1 }}
    -{{new Date(interviewapply.interviewDate).getDate()}} 
    {{new Date(interviewapply.interviewDate).getHours()}}시 
    {{new Date(interviewapply.interviewDate).getMinutes()==0?null:new Date(interviewapply.interviewDate).getMinutes()+"분"}}
      </div>
    <template v-if="interviewapply.jobOpeningProcess == '면접심사중'">
      <router-link
        :to="{ name: 'usereval', params: { no: this.interviewapply.userId } }"
      >
        <button>면접평가보기</button></router-link
      >
    </template>
    <template v-else>
      <router-link :to="{ name: 'companyopenvidu',params:{no : this.interviewapply.id, jobOpeningId:this.interviewapply.jobOpeningId} }">
        <button @click="regist()">면접보기</button>
      </router-link>
    </template>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  props: {
    interviewapply: Object,
  },
  methods: {
    ...mapActions("company",["setInterviewer"]),
    // regist() {
    //   this.setInterviewer(this.interviewapply.userId);
    // },
  },
};
</script>

<style></style>
