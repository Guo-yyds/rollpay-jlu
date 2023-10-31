package com.example.rollpay.service.impl;


import com.example.rollpay.dto.AdminLoginDTO;
import com.example.rollpay.entity.Admin;
import com.example.rollpay.exception.AccountNotFoundException;
import com.example.rollpay.exception.PasswordErrorException;
import com.example.rollpay.mapper.AdminMapper;
import com.example.rollpay.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     *
     * @param adminLoginDTO
     * @return
     */
    public Admin login(AdminLoginDTO adminLoginDTO) {
        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Admin admin = adminMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (admin == null) {
            //账号不存在
            throw new AccountNotFoundException("用户不存在");
        }

        //密码比对
        log.info(password);
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new PasswordErrorException("密码错误");
        }
        

        //3、返回实体对象
        return admin;
    }

    
}
