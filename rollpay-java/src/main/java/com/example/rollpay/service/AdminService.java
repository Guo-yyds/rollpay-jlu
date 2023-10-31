package com.example.rollpay.service;

import com.example.rollpay.dto.AdminLoginDTO;
import com.example.rollpay.entity.Admin;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/12:09
 * @Description:
 */
public interface AdminService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Admin login(AdminLoginDTO employeeLoginDTO);


}
