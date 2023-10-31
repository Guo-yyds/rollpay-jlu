package com.example.rollpay.controller;

import com.example.rollpay.dto.EmployeeListDTO;
import com.example.rollpay.dto.EmployeePaymentDTO;
import com.example.rollpay.dto.EmployeeReportDTO;
import com.example.rollpay.dto.EmployeeLoginDTO;
import com.example.rollpay.entity.Buyorder;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.result.Result;
import com.example.rollpay.service.EmployeeService;
import com.example.rollpay.utils.JwtUtil;
import com.example.rollpay.vo.EmployeeReportVO;
import com.example.rollpay.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/13:13
 * @Description:
 */
@RestController
@RequestMapping("/employee")
@Slf4j
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", employee.getId());
        String token = JwtUtil.createJWT("guohn2120", 7200000, claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .token(token)
                .way(employee.getWay())
                .kind(employee.getKind())
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 员工报表查询
     * @param employeeReportDTO
     * @return
     */
    @PostMapping("/report")
    public Result report(@RequestBody EmployeeReportDTO employeeReportDTO){
        log.info("员工报表查询：{}", employeeReportDTO);
        EmployeeReportVO employeeReportVO = employeeService.employeereport(employeeReportDTO);
        return Result.success(employeeReportVO);
    }

    /**
     * 查询员工参与的项目列表
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody EmployeeListDTO employeeListDTO){
        log.info("查询员工参与的项目列表：{}", employeeListDTO);
        List<Long> list = employeeService.getList(employeeListDTO);
        return Result.success(list);
    }


    @PostMapping("/payment")
    public Result payment(@RequestBody EmployeePaymentDTO employeePaymentDTO){
        log.info("员工改变支付方式：{}", employeePaymentDTO);
        employeeService.payment(employeePaymentDTO);
        return Result.success();
    }
}
