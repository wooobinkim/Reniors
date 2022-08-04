import { createRouter, createWebHistory } from "vue-router";

import LoginView from "../views/user/LoginView.vue";
import SignupView from "../views/user/SignupView.vue";
import SignupCompleteView from "../views/user/SignupCompleteView.vue";
import FindPasswordView from "../views/user/FindPasswordView.vue";
import FindUsernameView from "../views/user/FindUsernameView.vue";
import ResumeEditView from "../views/user/ResumeEditView.vue";
import HomeView from "../views/home/HomeView.vue";
import CompanyHomeView from "@/views/home/CompanyHomeView.vue";
// import CompanyInterviewView from "@/views/CompanyInterviewView.vue";
import CompanyJobOpeningView from "@/views/CompanyJobOpeningView.vue";
// import CompanyMyPageView from "@/views/CompanyMyPageView.vue";

import BoardMain from "../views/board/BoardMain.vue"
import BoardDetail from "../views/board/BoardDetail.vue"
import BoardCreate from "../views/board/BoardCreate.vue"
import BoardUpdate from "../views/board/BoardUpdate.vue"

import VideoMain from "../views/video/VideoMain.vue"

import QuestionAnswer from "../views/practice/QuestionAnswer.vue"
import QuestionList from "../views/practice/QuestionList.vue"
import VideoPractice from "../views/practice/VideoPractice.vue"
import VideoPracticeList from "../views/practice/VideoPracticeList.vue"
import CompanyJobOpeningList from "@/components/Company/JobOpening/JobOpeningList.vue";
import CompanyJobOpeningRegist from "@/components/Company/JobOpening/JobOpeningRegist.vue";
// import CompanyInterview from "@/components/Company/Interview/CompanyInterview.vue";
// import CompanyMyPage from "@/components/Company/MyPage/CompanyMyPage.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },

  //user
  {
    path: "/login",
    name: "Login",
    component: LoginView,
  },
  {
    path: "/signup",
    name: "Signup",
    component: SignupView,
  },
  {
    path: "/signup/complete",
    name: "SignupComplete",
    component: SignupCompleteView,
  },
  {
    path: "/find/password",
    name: "FindPassword",
    component: FindPasswordView,
  },
  {
    path: "/find/username",
    name: "FindUsername",
    component: FindUsernameView,
  },

  // resume
  {
    path: "/resume/edit",
    name: "ResumeEdit",
    component: ResumeEditView,
  },

  // jobopening
  {
    path: "/jobopening",
    name: "Jobopening",
    component: () => import("../views/jobopening/JobopeningView.vue"),
  },
  {
    path: "/company",
    name: "company",
    component: CompanyHomeView,
  },
  {
    path: "/jobopening",
    name: "companyjobopening",
    component: CompanyJobOpeningView,
    redirect: "/jobopening/list",
    children: [
      {
        path: "list",
        name: "companyjobopeninglist",
        component: CompanyJobOpeningList,
      },
      {
        path: "regist",
        name: "companyjobopeningregist",
        component: CompanyJobOpeningRegist,
      },
    ],
  },
  {
    path: '/boards/create',
    component: BoardCreate,
    name: 'boardCreate'
  },
  {
    path: '/boards/:board_id/update',
    component: BoardUpdate,
    name: 'boardUpdate'
  },

  //video
  {
    path: '/video',
    component: VideoMain,
    name: 'VideoMain'
  },

  //video practice
  {
    path: '/practice/questions',
    name:'QuestionList',
    component: QuestionList,
  },
  {
    path: '/practice/question/:question_id/answer',
    component: QuestionAnswer,
    name: 'QuestionAnswer'
  },
  {
    path: '/practice/videopractices',
    component: VideoPracticeList,
    name: 'VideoPracticeList',
  },
  {
    path: '/practice/videopractice',
    component: VideoPractice,
    name: 'VideoPractice'
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
