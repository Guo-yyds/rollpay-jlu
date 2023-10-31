package com.example.rollpay.service.impl;

import com.example.rollpay.dto.BuyorderGetByIdDTO;
import com.example.rollpay.entity.Buyorder;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.exception.AccountNotFoundException;
import com.example.rollpay.mapper.BuyorderMapper;
import com.example.rollpay.mapper.EmployeeMapper;
import com.example.rollpay.service.BuyorderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/11:45
 * @Description:
 */
@Slf4j
@Service
public class BuyorderServiceImpl implements BuyorderService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private BuyorderMapper buyorderMapper;
    @Override
    public Buyorder getById(BuyorderGetByIdDTO buyorderGetByIdDTO) {
        log.info("查询采购订单：{}", buyorderGetByIdDTO);
        Buyorder buyorder = buyorderMapper.getById(buyorderGetByIdDTO.getId());
        if (buyorder == null){
            throw new AccountNotFoundException("采购订单不存在");
        }
        if (buyorder.getEmployeeid()!=buyorderGetByIdDTO.getEmployeeid()){
            throw new AccountNotFoundException("采购订单不属于该用户");
        }
        return buyorder;
    }
    @Override
    public Long insert(Buyorder buyorder) {
        Employee employee = employeeMapper.getById(buyorder.getEmployeeid());
        if(employee == null){
            throw new AccountNotFoundException("用户不存在");
        }
        if(employee.getKind() != 2){
            throw new AccountNotFoundException("用户不是销售工");
        }
        buyorderMapper.insert(buyorder);
        return buyorder.getId();
    }

    @Override
    public void update(Buyorder buyorder) {
        Buyorder b = buyorderMapper.getById(buyorder.getId());
        if(b == null){
            throw new AccountNotFoundException("采购订单不存在");
        }
        if (b.getEmployeeid()!=buyorder.getEmployeeid()){
            throw new AccountNotFoundException("采购订单不属于该用户");
        }
        buyorderMapper.update(buyorder);
    }

    @Override
    public void delete(Long buyorder) {
        buyorderMapper.delete(buyorder);
    }

    @Override
    public List<Buyorder> getByEmployeeId(Long id) {
        return buyorderMapper.getByEmployeeId(id);
    }
}
