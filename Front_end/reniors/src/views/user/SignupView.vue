<template>
  <div class="container">
    <header>
      <div>
        <img style="width: 40%; margin: 10px" src="@/assets/logo.png" alt="logo" >
        <br>
        <div style="float: right">
          <img class="order" v-if="page===1" src="@/assets/one_active.svg" alt="order">
          <img class="order" v-else src="@/assets/one.svg" alt="order">
          <img class="order" v-if="page===2" src="@/assets/two_active.svg" alt="order">
          <img class="order" v-else src="@/assets/two.svg" alt="order">
          <img class="order" v-if="page===3" src="@/assets/three_active.svg" alt="order">
          <img class="order" v-else src="@/assets/three.svg" alt="order">
        </div>

      </div>
    </header> 

    <div>
      <br>
      <!-- <form @submit.prevent="signup(user)" class="signupform"> -->
      <div class="signupform">
        <div v-show="page===1">
          <p style="font-size: 14px">간단한 회원가입을 진행하려고 해요.</p>
          <p>먼저, 로그인 시 사용하실 <span>이메일</span>과 <span>비밀번호</span>를 입력해주세요!</p>
          <br>
          <p class="forminfo">이메일</p>
          <b-form-input class="mb-3" v-model="user.userAppId" type="text" placeholder="사용하실 이메일을 입력해주세요." ></b-form-input>
          <p class="forminfo">비밀번호</p>
          <b-form-input class="mb-3" v-model="user.userAppPwd" type="password" placeholder="비밀번호를 입력해주세요." ></b-form-input>
          <p class="forminfo">비밀번호 확인</p>
          <b-form-input class="mb-3" v-model="password" type="password" placeholder="비밀번호를 한번 더 입력해주세요." ></b-form-input>
        </div>

        <div v-show="page===2">
          <p>회원님의 <span>이름</span>과 <span>전화번호</span>, <span>주소</span>를 알려주실 수 있나요?</p>
          <br>
          <p class="forminfo">이름</p>
          <b-form-input class="mb-3" v-model="user.name" type="text" placeholder="이름을 입력해주세요." ></b-form-input>
          <p class="forminfo">휴대전화</p>
          <b-form-input class="mb-3" v-model="user.phone" type="text" placeholder="예시) 01012345678" ></b-form-input>
          <p class="forminfo">주소</p>
          <div class="address">
            <b-form-input class="mb-3" style="width:90%;" v-model="user.address" type="text" placeholder="주소 검색" ></b-form-input>
            <button class="search" style="margin-bottom:16px;" @click="execDaumPostcode()" type="button" value="우편번호 찾기" ><img src="@/assets/searching.png" alt="search"></button>
          </div>
          <b-form-input class="mb-3" v-model="extraAddress" type="text" placeholder="상세주소를 입력해주세요" ></b-form-input>
          <!-- <b-form-input class="mb-3" v-model="credentials.address" type="text" placeholder="" ></b-form-input> -->
        </div>

        <div v-show="page===3">
          <p style="font-size: 14px">마지막 단계입니다!</p>
          <p><span>최종학력</span>, <span>생년월일</span>, 성별을 입력해주세요!</p>
          <br>
          <p class="forminfo">최종학력</p>
          <b-form-select class="mb-3" v-model="user.lastEdu" :options="lastEdu" ></b-form-select>
          <p class="forminfo">생년월일</p>
          <b-form-input class="mb-3" v-model="user.birth" type="date" placeholder="생년-월-일" ></b-form-input>
          <p class="forminfo">성별</p>
          <b-form-select class="mb-3" v-model="user.gender" :options="gender" ></b-form-select>
        </div>

        
        <footer style="width: 312px;">
          <button style="background-color: #FFC0A3;" type="button" v-show="page === 1"><router-link style="text-decoration:none; color: white;" :to="{ name: 'Login' }">이전</router-link></button>
          <button style="background-color: #FFC0A3;" type="button" v-show="page !== 1" @click="decreasePage">이전</button>
          <button type="button" v-show="page !== 3" @click="increasePage">다음</button>
          <button @click="signup()" v-show="page === 3">완료!</button>
        </footer>
      </div>



    </div>

  </div>
</template>
<script>
// import { mapActions } from 'vuex'
// import { register } from "@/api/user.js"
import axios from 'axios'

export default {
  name: 'SignupView',
  components: {},
  data() {
    return {
      user: {
        // 변수 이름 설정
        userAppId: '',
        userAppPwd: '',
        name: '',
        phone: '',
        address: '',
        // extraAddress: '',
        lastEdu: '',
        birth: '',
        gender: '',
        isOpen: 'CLOSE',
        kakaoId: '',
        portfolioName: '',
        portfolioPath: '',
        profileImgName: '',
        profileImgPath: '',
        totalCareer: 0,        
        // 이력서 공개 여부
      },
      lastEdu: [
        // value 수정
      { value: null, text: '최종학력을 선택해주세요.' },          
      { value: '고교졸업이하', text: '고교졸업 이하' },
      { value: 'b', text: 'Selected Option' },
      ],
      gender: [
      { value: null, text: '성별을 선택해주세요.' },          
      { value: 'F', text: '여자' },
      { value: 'M', text: '남자' },
      ],
      page: 1,
      password: '',
      extraAddress: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    // ...mapActions(['signup']),
    // confirm(){
    //   register(this.credentials, (response) => {
    //     if (response.data.data === "회원 가입 성공"){
    //       this.$router.push({ name: "login" })
    //     }}
    //   )
    // },
    signup(){
      axios({
        // url 수정
        url: 'https://i7b307.p.ssafy.io/api/users/regist',
        method: 'post',
        data: this.user
      })
      // .then(res) < 수정
      .then(() => {
        console.log('성공!')
        this.$router.push({ name: 'Login' })
      })
      .catch((res) => {
        console.log(res)
      })
      // error 부분 추가
    },
    increasePage(){
      this.page += 1
    },
    decreasePage(){
      this.page -= 1
    },
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.user.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.user.address = data.jibunAddress;
          }
 
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
        },
      }).open();
    },
  },
}

</script>

<style scoped>

  header{
    height: 120px;
    border-style: none  none solid none;
    border-width: 0.5px;
    border-color: #EAEAEA;
  }

  span{
    color: var(--color-green-1);
    font-size: 17px;
  }
  
  p{
    color: #0F0F0F;
    font-weight: 800;
    font-size: 16px;
    text-align:left;
  }

  .order{
    margin: 2px;
  }

  .forminfo {
    color: #8A8A8A;
    font-weight: 400;
    font-size: 13px;
    margin-bottom: 5px;
  }

  .signupform {
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    align-items: center;
    height: 600px;
    /* min-height: 100vh; */
  }

  .address{
    display: flex;
    justify-content: space-between;    
  }

  .search{
    border: 0px;
    background-color:transparent;
  }

  footer > button {    
    background-color: var(--color-red-2);
    width: 45%;
    height: 80%;
    /* height: 40px; */
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 18px;
    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }

  footer {
    position: fixed;
    bottom: 30px;
    width: 100%;
    height: 50px;
    display: flex;
    justify-content: space-between;
  }
</style>