<template>
  <div>
    <user-eval-list-item
      v-for="usereval in userevallist"
      :key="usereval"
      :usereval="usereval"
    ></user-eval-list-item>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import UserEvalListItem from "./UserEvalListItem.vue";
export default {
  components: {
    UserEvalListItem,
  },
  computed: {
    ...mapGetters("company", ["userevallist", "jobopening"]),
  },
  created() {
    let data = {
      jobOpeningId: this.jobopening.id,
      userId: this.$route.params.no,
    };
    this.getUserEvalList(data);
  },
  methods: {
    ...mapActions("company", ["getUserEvalList"]),
  },
};
</script>

<style></style>
