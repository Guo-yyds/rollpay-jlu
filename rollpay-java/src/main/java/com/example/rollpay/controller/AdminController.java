package com.example.rollpay.controller;

import com.example.rollpay.dto.AdminLoginDTO;
import com.example.rollpay.dto.AdminReportDTO;
import com.example.rollpay.dto.EmployeePageQueryDTO;
import com.example.rollpay.entity.Admin;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.result.PageResult;
import com.example.rollpay.result.Result;
import com.example.rollpay.service.AdminService;
import com.example.rollpay.service.EmployeeService;
import com.example.rollpay.utils.JwtUtil;
import com.example.rollpay.vo.AdminLoginVO;
import com.example.rollpay.vo.AdminReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/11:14
 * @Description:
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;
    /**
     * 管理员登录
     *
     * @param adminLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.info("管理员登录：{}", adminLoginDTO);

        Admin admin = adminService.login(adminLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", admin.getId());
        String token = JwtUtil.createJWT("guohn2120", 7200000, claims);

        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .token(token)
                .build();

        return Result.success(adminLoginVO);
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Employee employee){
        log.info("新增员工：{}",employee);
        Long id = employeeService.save(employee);
        return Result.success(id);
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询，参数为：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 编辑员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Employee employee){
        log.info("编辑员工信息：{}", employee);
        Employee e = employeeService.getById(employee.getId());
        if (e == null){
            return Result.error("员工不存在");
        }
        employeeService.update(employee);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee == null){
            return Result.error("员工不存在");
        }
        log.info("删除员工：{}", id);
        employeeService.delete(id);
        return Result.success();
    }

    /**
     * 管理员报表查询
     * @param adminReportDTO
     * @return
     */
    @PostMapping("/report")
    public Result report(@RequestBody AdminReportDTO adminReportDTO){
        log.info("管理员报表查询：{}", adminReportDTO);
        AdminReportVO adminReportVO = employeeService.adminreport(adminReportDTO);
        return Result.success(adminReportVO);
    }
}

