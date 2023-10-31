package com.example.rollpay.service;

import com.example.rollpay.dto.BuyorderGetByIdDTO;
import com.example.rollpay.entity.Buyorder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/11:45
 * @Description:
 */
public interface BuyorderService {
    Buyorder getById(BuyorderGetByIdDTO id);

    Long insert(Buyorder buyorder);

    void update(Buyorder buyorder);

    void delete(Long id);

    List<Buyorder> getByEmployeeId(Long id);
}
