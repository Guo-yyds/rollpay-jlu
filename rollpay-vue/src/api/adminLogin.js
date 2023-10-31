// 此处用于存放所有登录相关的接口请求
import request from '@/utils/request'

// 管理员登录接口
export const adminLogin = (username, password) => {
  return request.post('/admin/login', {
    username,
    password
  })
}
