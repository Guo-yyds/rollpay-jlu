import Vue from 'vue'
import VueRouter from 'vue-router'

import store from '@/store'
import adminLogin from '@/views/adminLogin'
import adminHome from '@/views/adminHome'
import adminShow from '@/views/adminShow'
import adminInsert from '@/views/adminInsert'
import adminUpdate from '@/views/adminUpdate'
import adminDelete from '@/views/adminDelete'
import adminReport from '@/views/adminReport'
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/',
      component: adminHome,
      redirect: '/adminShow',
      children: [
        { path: '/adminShow', component: adminShow },
        { path: '/adminInsert', component: adminInsert },
        { path: '/adminUpdate', component: adminUpdate },
        { path: '/adminDelete', component: adminDelete },
        { path: '/adminReport', component: adminReport }

      ]
    },
    { path: '/adminLogin', component: adminLogin }

  ]
})

// 定义一个数组，专门用户存放所有需要权限访问的页面
const authUrls = ['/adminLogin']

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
    next('/adminLogin')
  }
})

export default router
