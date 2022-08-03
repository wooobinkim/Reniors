import { createRouter, createWebHistory } from 'vue-router'
import ResumeStepOne from '../components/resume/ResumeStepOne.vue'
import ResumeStepTwo from '../components/resume/ResumeStepTwo.vue'
import LoginView from '../views/user/LoginView.vue'
import SignupView from '../views/user/SignupView.vue'
import SignupCompleteView from '../views/user/SignupCompleteView.vue'
import FindPasswordView from '../views/user/FindPasswordView.vue'
import FindUsernameView from '../views/user/FindUsernameView.vue'

import HomeView from '../views/home/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

  //user
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'Signup',
    component: SignupView
  },
  {
    path: '/signup/complete',
    name: 'SignupComplete',
    component: SignupCompleteView
  },
  {
    path: '/find/password',
    name: 'FindPassword',
    component: FindPasswordView
  },
  {
    path: '/find/username',
    name: 'FindUsername',
    component: FindUsernameView
  },


  // resume
  {
    path: '/resume/edit/1',
    component: ResumeStepOne,
    name: 'ResumeStepOne'
  },
  {
    path: '/resume/edit/2',
    component: ResumeStepTwo,
    name: 'ResumeStepTwo' 
  },


  // jobopening
  {
    path: '/jobopening',
    name: 'Jobopening',
    component: () => import('../views/jobopening/JobopeningView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
