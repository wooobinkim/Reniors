<template>
  <div>
    <interview-list-item
      v-for="interviewapply in interviewapplylistasc"
      :key="interviewapply.id"
      :interviewapply="interviewapply"
    ></interview-list-item>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
import InterviewListItem from "./InterviewListItem.vue";
export default {
  components: { InterviewListItem },
  created() {
    this.CLEAR_INTERVIEW_APPLY_LIST_ASC;
    this.getinterviewapplylistasc(this.$route.params.no);
  },
  methods: {
    ...mapActions("company", ["getinterviewapplylistasc"]),
  },
  computed: {
    ...mapGetters("company", ["interviewapplylistasc"]),
    ...mapMutations("company", ["CLEAR_INTERVIEW_APPLY_LIST_ASC"]),
  },
};
</script>

<style></style>
