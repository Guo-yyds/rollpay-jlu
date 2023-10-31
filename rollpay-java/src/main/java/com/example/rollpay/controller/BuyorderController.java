package com.example.rollpay.controller;

import com.example.rollpay.dto.BuyorderGetByIdDTO;
import com.example.rollpay.entity.Buyorder;
import com.example.rollpay.result.Result;
import com.example.rollpay.service.BuyorderService;
import com.example.rollpay.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/11:41
 * @Description:
 */
@RestController
@RequestMapping("/employee")
@Slf4j
@CrossOrigin
public class BuyorderController {
    @Autowired
    private BuyorderService buyorderService;
    @PostMapping
    public Result insert(@RequestBody Buyorder buyorder){
        log.info("新增采购订单：{}", buyorder);
        Long id = buyorderService.insert(buyorder);
        return Result.success(id);
    }

    @PostMapping("/getById")
    public Result getById(@RequestBody BuyorderGetByIdDTO buyorderGetByIdDTO){
        log.info("查询采购订单：{}", buyorderGetByIdDTO);
        Buyorder buyorder = buyorderService.getById(buyorderGetByIdDTO);
        return Result.success(buyorder);
    }

    @GetMapping("/id/{id}")
    public Result getByEmployeeId(@PathVariable Long id){
        log.info("查询采购订单：{}", id);
        List<Buyorder> list = buyorderService.getByEmployeeId(id);
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody Buyorder buyorder){
        log.info("更新采购订单：{}", buyorder);
        buyorderService.update(buyorder);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除采购订单：{}", id);
        buyorderService.delete(id);
        return Result.success();
    }
}
