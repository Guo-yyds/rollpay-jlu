package com.example.rollpay.service;


import com.example.rollpay.dto.*;
import com.example.rollpay.entity.Buyorder;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.entity.Timecard;
import com.example.rollpay.result.PageResult;
import com.example.rollpay.vo.AdminReportVO;
import com.example.rollpay.vo.EmployeeReportVO;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    Long save(Employee employee);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 编辑员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 管理员报表查询
     * @param adminReportDTO
     */
    AdminReportVO adminreport(AdminReportDTO adminReportDTO);

    EmployeeReportVO employeereport(EmployeeReportDTO employeeReportDTO);

    /**
     * 根据id查询员工参与的项目列表
     * @param
     * @return
     */
    List<Long> getList(EmployeeListDTO employeeListDTO);

    void delete(Long id);

    List<Employee> getByKind(int i);

    List<Timecard> getTimecard(Long id, Date before, Date now);

    List<Buyorder> getBuyorder(Long id, Date before, Date now);

    void realDelete(Long id);

    void payment(EmployeePaymentDTO employeePaymentDTO);
}
