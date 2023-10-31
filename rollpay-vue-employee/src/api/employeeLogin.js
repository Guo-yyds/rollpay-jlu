// 此处用于存放所有登录相关的接口请求
import request from '@/utils/request'

// 员工登录接口
export const employeeLogin = (username, password) => {
  return request.post('/employee/login', {
    username,
    password
  })
}
