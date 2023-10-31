// 此处用于存放所有登录相关的接口请求
import request from '@/utils/request'

// 管理员分页查询接口
export const adminList = (page, pageSize) => {
  return request.get('/admin/page', {
    params: {
      page,
      pageSize
    }
  })
}

// 管理员添加接口
export const adminAdd = (username, password, name, email, SSN, tax, detection,
  telephone, kind, hoursal, sal, com, hourlimit, way) => {
  return request.post('/admin', {
    username,
    password,
    name,
    email,
    SSN,
    tax,
    detection,
    telephone,
    kind,
    hoursal,
    sal,
    com,
    hourlimit,
    way
  })
}

// 管理员更新接口
export const adminUpdate = (id, username, password, name, email, SSN, tax, detection,
  telephone, kind, hoursal, sal, com, hourlimit, way, address, bankname, banknumber) => {
  return request.put('/admin', {
    id,
    username,
    password,
    name,
    email,
    SSN,
    tax,
    detection,
    telephone,
    kind,
    hoursal,
    sal,
    com,
    hourlimit,
    way,
    address,
    bankname,
    banknumber
  })
}

// 管理员删除接口
export const adminDelete = (id) => {
  return request.delete(`/admin/${id}`)
}

// 管理员id查询接口
export const adminId = (id) => {
  return request.get(`/admin/${id}`)
}

// 管理员报告接口
export const adminReport = (kind, name, startTime, endTime) => {
  return request.post('/admin/report', {
    kind, name, startTime, endTime
  })
}
