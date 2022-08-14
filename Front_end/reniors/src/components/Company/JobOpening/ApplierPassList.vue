<template>
  <div>
    <template v-for="apply in applies" :key="apply.id">
      <template v-if="apply.jobOpeningProcess == '최종합격'">
        <div>
          <applier-pass-list-item :apply="apply"> </applier-pass-list-item>
        </div>
      </template>
    </template>
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapGetters } from "vuex";
</script>
<script>
import ApplierPassListItem from "./ApplierPassListItem.vue";
export default {
  components: {
    ApplierPassListItem,
  },
  props: {
    jobopeningdetail: Object,
  },
  data() {
    return {
      applies: null,
    };
  },
  watch: {
    applylist: function (datas) {
      this.applies = [];
      datas.forEach((data) => {
        data.interviewDate = new Date(data.interviewDate);
        this.applies.push(data);
      });
    },
  },
  async created() {
    await this.getapplylist(this.$route.params.no);
  },
  computed: {
    ...mapGetters("company", ["jobopening", "applylist"]),
  },
  methods: {
    ...mapActions("company", [
      "getapplylist",
      "progressJobOpening",
      "updateApply",
    ]),
  },
};
</script>

<style></style>
