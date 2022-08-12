<template>
  <div>
    <!-- <form action=""> -->
    <div class="mb-3 mt-3">
      <label for="title" class="form-label">제목</label>
      <input
        type="text"
        class="form-control"
        id="title"
        placeholder="채용 공고 제목을 입력해주세요."
        name="title"
        v-model="jobopeningdetail.title"
      />
    </div>

    <div class="mb-3">
      <datepicker v-model="jobopeningdetail.createdDate" format="YYYY-MM-DD" />
    </div>

    <div class="mb-3">
      <datepicker v-model="jobopeningdetail.finishedDate" format="YYYY-MM-DD" />
    </div>

    <div class="mb-3 mt-3">
      <label for="numberPeople" class="form-label">채용 인원</label>
      <input
        type="text"
        class="form-control"
        id="numberPeople"
        placeholder="채용 인원을 입력해주세요."
        name="numberPeople"
        v-model="jobopeningdetail.numberPeople"
      />
    </div>

    <div class="mb-3 mt-3">
      시도
      <select v-model="sido">
        <option v-for="sido in sidos" :value="sido.value" :key="sido">
          {{ sido.text }}
        </option>
      </select>

      구군
      <select v-model="jobopeningdetail.gugunId">
        <option v-for="gugun in guguns" :value="gugun.value" :key="gugun">
          {{ gugun.text }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      직무대분류
      <select v-model="jobparent">
        <option
          v-for="jobparent in jobparents"
          :value="jobparent.value"
          :key="jobparent"
        >
          {{ jobparent.text }}
        </option>
      </select>

      직무소분류
      <select v-model="jobopeningdetail.jobChildCategoryId">
        <option
          v-for="jobchild in jobchilds"
          :value="jobchild.value"
          :key="jobchild"
        >
          {{ jobchild.text }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      학력
      <select v-model="jobopeningdetail.lastEdu">
        <option
          v-for="lastedu in lastedus"
          :value="lastedu.value"
          :key="lastedu"
        >
          {{ lastedu.text }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      근무형태
      <select v-model="jobopeningdetail.typeEmployment">
        <option
          v-for="typeemployment in typeemployments"
          :value="typeemployment.value"
          :key="typeemployment"
        >
          {{ typeemployment.text }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      <label for="contents">상세 내용</label>
      <textarea
        class="form-control"
        rows="5"
        id="contents"
        name="text"
        v-model="jobopeningdetail.contents"
      ></textarea>
    </div>

    <div class="mb-3 mt-3">
      <label for="jobPosition" class="form-label">직책</label>
      <input
        type="text"
        class="form-control"
        id="jobPosition"
        placeholder="직책을 입력해주세요."
        name="jobPosition"
        v-model="jobopeningdetail.jobPosition"
      />
    </div>

    <div class="mb-3 mt-3">
      <label for="minSalary" class="form-label">급여</label>
      <input
        type="text"
        class="form-control"
        id="minSalary"
        placeholder="급여를 입력해주세요."
        name="minSalary"
        v-model="jobopeningdetail.minSalary"
      />
    </div>

    <div class="mb-3 mt-3">
      <label for="workingDay" class="form-label">근무횟수</label>
      <input
        type="text"
        class="form-control"
        id="workingDay"
        placeholder="급무횟수를 입력해주세요."
        name="workingDay"
        v-model="jobopeningdetail.workingDay"
      />
    </div>

    <div class="mb-3 mt-3">
      <label for="minCareer" class="form-label">경력</label>
      <input
        type="text"
        class="form-control"
        id="minCareer"
        placeholder="학력을 입력해주세요."
        name="minCareer"
        v-model="jobopeningdetail.minCareer"
      />
    </div>

    <button color="blue" class="ml-3" @click="update">수정</button>
    <!-- </form> -->
  </div>
</template>

<script setup>
import Datepicker from "vue3-datepicker";
// import { ref } from "vue";
import { mapActions, mapGetters } from "vuex";
// import moment from "moment";
// const picked = ref(new Date())
</script>

<script>
export default {
  components: {
    Datepicker,
  },
  data() {
    return {
      sido: null,
      jobparent: null,
      jobopeningdetail: {
        contents: null,
        contentsImgName: null,
        contentsImgPath: null,
        createdDate: new Date(),
        finishedDate: new Date(),
        gugunId: 0,
        jobChildCategoryId: 0,
        jobPosition: null,
        lastEdu: null,
        minCareer: 0,
        minSalary: 0,
        numberPeople: 0,
        title: null,
        typeEmployment: null,
        workingDay: 0,
      },
    };
  },
  computed: {
    ...mapGetters("category", [
      "sidos",
      "guguns",
      "jobparents",
      "jobchilds",
      "lastedus",
      "typeemployments",
    ]),
    ...mapGetters("company", ["jobopening"]),
  },
  watch: {
    jobopening: function (data) {
      data.createdDate = new Date(data.createdDate);
      data.finishedDate = new Date(data.finishedDate);
      this.jobopeningdetail = data;
      this.sido = data.sidoId;
      this.jobparent = data.jobParentCategory;
    },
    sido: function (data) {
      this.getGugun(data);
    },
    jobparent: function (data) {
      this.getJobChild(data);
    },
  },
  created() {
    this.getSido();
    this.getJobParent();
    this.getJobOpening(this.$route.params.no);
  },
  methods: {
    ...mapActions("category", [
      "getSido",
      "getGugun",
      "getJobParent",
      "getJobChild",
    ]),
    ...mapActions("company", ["getJobOpening", "updateJobOpening"]),
    update() {
      let data = {
        no: this.$route.params.no,
        jobopening: this.jobopeningdetail,
      };
      this.updateJobOpening(data);
      this.$router.push({
        name: "companyjobopeningdetail",
        params: { no: this.$route.params.no },
      });
    },
  },
};
</script>

<style></style>
