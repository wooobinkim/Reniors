<template>
  <div class="jobopening-list">
    <div v-if="isJobopenings">
      <JobopeningItem v-for="jobopening in jobopenings" :key="jobopening.job_opening_id" :jobopening="jobopening" />
    </div>
    <div v-else>아직 채용공고가 없어요!</div>
  </div>
</template>

<script>
import JobopeningItem from './JobopeningItem.vue'

export default {
  name: 'JobopeningList',
  components: {
    JobopeningItem,
  },
  setup() {

    const jobopenings = [
      {
        job_opening_id: 1,
        company_name: '어쩌고 회사이름',
        title: '(공고제목)',
        contents: '공고 내용 주절주절주절주절',
        created_at: '2022-06-20',
        started_at: '2022-07-20',
        finished_at: '2022-08-05',
      },
      {
        job_opening_id: 2,
        company_name: '2줄이상의 회사이름 예시',
        title: '엄청나게 긴 공고제목ㅈㅁㄴㅇㅎㄴㅇㄴㅎㅇㅎㅇㅎㄴ',
        contents: '엄청나게 긴 공고내용ㅁㅎㅎㅇㄴㅎ몽롱로모ㅗ롬ㅇㅁ로롱로ㅗㄹ올오옹로오올로ㅗ',
        created_at: '2022-06-20',
        started_at: '2022-07-20',
        finished_at: '2022-08-05',
      },
      {
        job_opening_id: 3,
        company_name: '어쩌고 회사이름',
        title: '(공고제목)',
        contents: '공고 내용 3번째',
        created_at: '2022-06-20',
        started_at: '2022-07-20',
        finished_at: '2022-08-05',
      },
      {
        job_opening_id: 4,
        company_name: '어쩌고 회사이름',
        title: '(공고제목)',
        contents: '공고 내용 4번째',
        created_at: '2022-06-20',
        started_at: '2022-07-20',
        finished_at: '2022-08-05',
      },
      {
        job_opening_id: 5,
        company_name: '어쩌고 회사이름',
        title: '(공고제목)',
        contents: '공고 내용 5번째',
        created_at: '2022-06-20',
        started_at: '2022-07-20',
        finished_at: '2022-08-05',
      },
    ]


    return {
      jobopenings
    }
  }
}
</script>

<style>
.jobopening-list > div {
  display: grid;
  grid-template-columns: 170px 170px;
  margin-top: 10px;
}
</style>