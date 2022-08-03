import { createRouter, createWebHistory } from 'vue-router'

import LoginView from '../views/user/LoginView.vue'
import SignupView from '../views/user/SignupView.vue'
import SignupCompleteView from '../views/user/SignupCompleteView.vue'
import FindPasswordView from '../views/user/FindPasswordView.vue'
import FindUsernameView from '../views/user/FindUsernameView.vue'

import ResumeEditView from '../views/user/ResumeEditView.vue'
import HomeView from '../views/home/HomeView.vue'

import BoardMain from "../views/board/BoardMain.vue"
import BoardDetail from "../views/board/BoardDetail.vue"
import BoardCreate from "../views/board/BoardCreate.vue"
import BoardUpdate from "../views/board/BoardUpdate.vue"

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
    path: '/resume/edit',
    name: 'ResumeEdit',
    component: ResumeEditView
  },

  // jobopening
  {
    path: '/jobopening',
    name: 'Jobopening',
    component: () => import('../views/jobopening/JobopeningView.vue')
  },

  //boards
  {
    path: '/boards',
    component: BoardMain,
    name: 'board',
  },
  {
    path: '/boards/:board_id',
    component:BoardDetail,
    name: 'boardDetail'
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


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
