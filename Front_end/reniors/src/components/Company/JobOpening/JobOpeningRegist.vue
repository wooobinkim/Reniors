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
        v-model="jobopening.title"
      />
    </div>

    <div class="mb-3">
      <datepicker v-model="jobopening.createdDate" format="YYYY-MM-DD" />
    </div>

    <div class="mb-3">
      <datepicker v-model="jobopening.finishedDate" format="YYYY-MM-DD" />
    </div>

    <div class="mb-3 mt-3">
      <label for="numberPeople" class="form-label">채용 인원</label>
      <input
        type="text"
        class="form-control"
        id="numberPeople"
        placeholder="채용 인원을 입력해주세요."
        name="numberPeople"
        v-model="jobopening.numberPeople"
      />
    </div>

    <div class="mb-3 mt-3">
      시도
      <select v-model="sido">
        <option v-for="sido in sidos" :value="sido.id" :key="sido">
          {{ sido.name }}
        </option>
      </select>

      구군
      <select v-model="jobopening.gugunId">
        <option v-for="gugun in guguns" :value="gugun.id" :key="gugun">
          {{ gugun.name }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      직무대분류
      <select v-model="jobparent">
        <option
          v-for="jobparent in jobparents"
          :value="jobparent.id"
          :key="jobparent"
        >
          {{ jobparent.name }}
        </option>
      </select>

      직무소분류
      <select v-model="jobopening.jobChildCategoryId">
        <option
          v-for="jobchild in jobchilds"
          :value="jobchild.id"
          :key="jobchild"
        >
          {{ jobchild.name }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      학력
      <select v-model="jobopening.lastEdu">
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
      <select v-model="jobopening.typeEmployment">
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
        v-model="jobopening.contents"
      ></textarea>
    </div>

    <div class="mb-3 mt-3">
        <label class="form-label">채용공고 이미지 </label>
        <input
          type="file"
          class="form-control"
          placeholder="이미지를 선택해주세요"
          ref="img"
          @change="changeImg()"
        />
      </div>

    <div class="mb-3 mt-3">
      <label for="jobPosition" class="form-label">직책</label>
      <input
        type="text"
        class="form-control"
        id="jobPosition"
        placeholder="직책을 입력해주세요."
        name="jobPosition"
        v-model="jobopening.jobPosition"
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
        v-model="jobopening.minSalary"
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
        v-model="jobopening.workingDay"
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
        v-model="jobopening.minCareer"
      />
    </div>

    <button color="blue" class="ml-3" @click="regist">Submit</button>
    <!-- </form> -->
  </div>
</template>

<script setup>
import Datepicker from "vue3-datepicker";
// import { ref } from "vue";
import { mapActions, mapState } from "vuex";
import moment from "moment";
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
      jobopening: {
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
      jobOpeningImg: "",
    };
  },
  watch: {
    sido: function (data) {
      this.getGugun(data);
    },
    jobparent: function (data) {
      this.getJobChild(data);
    },
  },
  computed: {
    ...mapState("category", [
      "sidos",
      "guguns",
      "jobparents",
      "jobchilds",
      "lastedus",
      "typeemployments",
    ]),
  },
  created() {
    this.getSido();
    this.getJobParent();
  },
  methods: {
    ...mapActions("category", [
      "getSido",
      "getGugun",
      "getJobParent",
      "getJobChild",
    ]),
    ...mapActions("company", ["registJobOpening"]),
    regist() {
      this.jobopening.createdDate = new Date(
        moment(this.jobopening.createdDate).format("YYYY-MM-DD")
      );
      this.jobopening.finishedDate = new Date(
        moment(this.jobopening.finishedDate).format("YYYY-MM-DD")
      );

      const formData = new FormData();
        formData.append("img", this.jobOpeningImg[0]);
        formData.append(
          "data",
          new Blob([JSON.stringify(this.jobopening)], { type: "application/json" })
        );

      this.registJobOpening(formData);
      this.$router.push({ name: "companyjobopeninglist" });
    },
    changeImg() {
      this.jobOpeningImg = this.$refs.img.files;
    },
  },
};
</script>

<style></style>
