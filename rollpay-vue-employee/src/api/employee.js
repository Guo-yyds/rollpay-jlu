// 此处用于存放所有登录相关的接口请求
import request from '@/utils/request'

// 员工改变支付方式
export const employeePayment = (id, way, address, bankname, banknumber) => {
  return request.post('/employee/payment', {
    id,
    way,
    address,
    bankname,
    banknumber
  })
}

// 员工添加订单
export const employeeAddOrder = (date, amount, pointcontact, billingaddress, employeeid) => {
  return request.post('/employee', {
    date, amount, pointcontact, billingaddress, employeeid
  })
}

// 员工显示订单
export const employeeShow = (id) => {
  // 路径参数
  return request.get(`/employee/id/${id}`)
}

export const employeeGetById = (id, employeeid) => {
  return request.post('/employee/getById', {
    id, employeeid
  })
}

// 员工修改订单
export const employeeUpdateOrder = (id, date, amount, pointcontact, billingaddress, employeeid) => {
  return request.put('/employee/', {
    id,
    date,
    amount,
    pointcontact,
    billingaddress,
    employeeid
  })
}

export const employeeDeleteOrder = (id) => {
  return request.delete(`/employee/${id}`)
}

export const employeeReport = (kind, name, startTime, endTime, projectid) => {
  return request.post('/employee/report', {
    kind,
    name,
    startTime,
    endTime,
    projectid
  })
}

export const employeeList = (name, startTime, endTime) => {
  return request.post('/employee/list', {
    name,
    startTime,
    endTime
  })
}
