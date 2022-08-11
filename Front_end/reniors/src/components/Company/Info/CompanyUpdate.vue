<template>
  <div>
    <div v-if="pagenum == 1">
      <b>1 </b>2 3
      <div class="mb-3 mt-3">
        <label for="name" class="form-label">기업명</label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="기업명을 입력해주세요."
          name="name"
          v-model="company.name"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyAppId" class="form-label">이메일</label>
        <input
          type="text"
          class="form-control"
          id="companyAppId"
          placeholder="이메일을 입력해주세요."
          name="companyAppId"
          v-model="company.companyAppId"
          readonly
        />
      </div>

      <div class="mb-3 mt-3">
        <label for="companyNum" class="form-label">사업자번호</label>
        <input
          type="text"
          class="form-control"
          id="companyNum"
          placeholder="사업자번호를 입력해주세요."
          name="companyNum"
          v-model="company.companyNum"
          readonly
        />
      </div>

      <button @click="firstnext()">다음</button>

    </div>
    <div v-if="pagenum == 2">
      1 <b>2 </b>3
      <div class="mb-3 mt-3">
        <label for="representativePhone" class="form-label">담당자번호</label>
        <input
          type="text"
          class="form-control"
          id="representativePhone"
          placeholder="담당자번호를 입력해주세요."
          name="representativePhone"
          v-model="company.representativePhone"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyPhone" class="form-label">대표번호</label>
        <input
          type="text"
          class="form-control"
          id="companyPhone"
          placeholder="대표번호를 입력해주세요."
          name="companyPhone"
          v-model="company.companyPhone"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyUrl" class="form-label">회사 홈페이지</label>
        <input
          type="text"
          class="form-control"
          id="companyUrl"
          placeholder="회사 홈페이지를 입력해주세요."
          name="companyUrl"
          v-model="company.companyUrl"
        />
      </div>
      <div class="mb-3 mt-3">
        기업형태
        <select v-model="company.typeCompany">
          <option
            v-for="typeCompany in typecompanies"
            :value="typeCompany.value"
            :key="typeCompany"
          >
            {{ typeCompany.text }}
          </option>
        </select>
      </div>
      <button @click="secondprev()">이전</button>
      <button @click="secondnext()">다음</button>
    </div>
    <div v-if="pagenum == 3">
      1 2 <b>3</b>
      <div class="mb-3 mt-3">
        <label for="establishedAt" class="form-label">설립연도</label>
        <input
          type="text"
          class="form-control"
          id="establishedAt"
          placeholder="설립연도를 입력해주세요."
          name="establishedAt"
          v-model="company.establishedAt"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="address" class="form-label">회사주소</label>
        <input
          type="text"
          class="form-control"
          id="address"
          placeholder="회사주소를 입력해주세요."
          name="address"
          v-model="company.address"
        />
      </div>
      <div class="mb-3 mt-3">
        <label class="form-label">회사이미지</label>
        <input
          type="file"
          class="form-control"
          placeholder="이미지를 선택해주세요"
          ref="img"
          @change="changeImg()"
        />
      </div>
      <button @click="thirdprev()">이전</button>
      <button @click="updatecompany()">완료</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      pagenum: 1,
      comfirmcompanyAppPwd: "",
      company: {
        name: "",
        companyAppId: "",
        companyAppPwd: "",
        establishedAt: "",
        companyUrl: "",
        address: "",
        companyNum: "",
        companyPhone: "",
        representativePhone: "",
        typeCompany: "",
      },
      companyImg:null,
    };
  },
  watch: {
    companyinfo: function (data) {
      this.company = data;
    },
  },
  computed: {
    ...mapState("category", ["typecompanies"]),
    ...mapState("company", ["companyinfo"]),
  },
  created() {
    this.getCompany();
  },
  methods: {
    ...mapActions("company", ["updateCompany", "getCompany"]),
    firstnext() {
      this.pagenum = 2;
    },
    secondprev() {
      this.pagenum = 1;
    },
    secondnext() {
      this.pagenum = 3;
    },
    thirdprev() {
      this.pagenum = 2;
    },
    updatecompany() {
      const formData = new FormData();
        formData.append("img", this.companyImg[0]);
        formData.append(
          "data",
          new Blob([JSON.stringify(this.company)], { type: "application/json" })
        );

      this.updateCompany(formData);
      this.$router.push({ name: "companymypage" });
    },
    changeImg() {
      this.jobOpeningImg = this.$refs.img.files;
    },
  },
};
</script>

<style></style>
