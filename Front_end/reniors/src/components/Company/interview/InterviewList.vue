<template>
  <div>
    <interview-list-item
      v-for="interviewapply in interviewapplylist"
      :key="interviewapply.id"
      :interviewapply="interviewapply"
    ></interview-list-item>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import InterviewListItem from "./InterviewListItem.vue";
export default {
  components: { InterviewListItem },
  created() {
    this.CLEAR_INTERVIEW_APPLY_LIST;
    this.getinterviewapplylist(this.$route.params.no);
  },
  methods: {
    ...mapActions("company", ["getinterviewapplylist"]),
  },
  computed: {
    ...mapState("company", ["interviewapplylist"]),
    ...mapMutations("company", ["CLEAR_INTERVIEW_APPLY_LIST"]),
  },
};
</script>

<style></style>
