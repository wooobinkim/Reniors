import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/home/HomeView.vue'

import Board from "../views/board/Board.vue"
import BoardDetail from "../views/board/BoardDetail.vue"

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
  {
    path: '/boards/categories/:job_parent_category_id',
    component: Board,
    name: 'board',
  },
  {
    path: '/boards/:job_parent_category_id/:job_board_id',
    component:BoardDetail,
    name: 'boardDetail'
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
