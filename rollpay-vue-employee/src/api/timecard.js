// 此处用于存放所有登录相关的接口请求
import request from '@/utils/request'

export const timecardAdd = (date, time, projectid, employeeid) => {
  return request.post('/employee/timecard', {
    date, time, projectid, employeeid
  })
}

export const timecardList = (id) => {
  return request.post(`/employee/timecard/list/${id}`)
}

export const timecardSubmit = (id) => {
  return request.get(`/employee/timecard/${id}`)
}

export const timecardUpdate = (id, time) => {
  return request.put('/employee/timecard', {
    id,
    time
  })
}
