import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'

import employeeLogin from '@/views/employeeLogin.vue'
import employeeHome from '@/views/employeeHome.vue'
import employeePayment from '@/views/employeePayment.vue'
import employeeShow from '@/views/employeeShow.vue'
import employeeInsert from '@/views/employeeInsert.vue'
import employeeUpdate from '@/views/employeeUpdate.vue'
import employeeDelete from '@/views/employeeDelete.vue'

import employeeReport from '@/views/employeeReport.vue'

import timecardInsert from '@/views/timecardInsert.vue'
import timecardShow from '@/views/timecardShow.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/employeePayment',
    component: employeeHome,
    children: [
      { path: '/employeePayment', component: employeePayment },
      { path: '/employeeInsert', component: employeeInsert },
      { path: '/employeeShow', component: employeeShow },
      { path: '/employeeUpdate', component: employeeUpdate },
      { path: '/employeeDelete', component: employeeDelete },
      { path: '/timecardShow', component: timecardShow },
      { path: '/timecardInsert', component: timecardInsert },
      { path: '/employeeReport', component: employeeReport }
    ]
  },
  { path: '/employeeLogin', component: employeeLogin }
]

const router = new VueRouter({
  routes
})

// 定义一个数组，专门用户存放所有需要权限访问的页面
const authUrls = ['/employeeLogin']

router.beforeEach((to, from, next) => {
  // console.log(to, from, next)
  // 看 to.path 是否在 authUrls 中出现过
  if (authUrls.includes(to.path)) {
    // 非权限页面，直接放行
    next()
    return
  }

  // 是权限页面，需要判断token
  const token = store.getters.token
  if (token) {
    next()
  } else {
    next('/employeeLogin')
  }
})

export default router
