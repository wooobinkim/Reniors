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

import CompanyJobOpeningList from "@/components/Company/JobOpening/JobOpeningList.vue";
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
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
