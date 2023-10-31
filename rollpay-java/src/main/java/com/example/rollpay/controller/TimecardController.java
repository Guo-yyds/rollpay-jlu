package com.example.rollpay.controller;

import com.example.rollpay.dto.TimecardInsertDTO;
import com.example.rollpay.entity.Timecard;
import com.example.rollpay.result.Result;
import com.example.rollpay.service.TimecardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/14:07
 * @Description:
 */
@RestController
@RequestMapping("/employee/timecard")
@Slf4j
@CrossOrigin
public class TimecardController {

    @Autowired
    private TimecardService timecardService;

    @PostMapping
    public Result insert (@RequestBody Timecard timecard) {
        log.info("员工打卡：{}", timecard);
        timecardService.insert(timecard);
        return Result.success();
    }

    @PostMapping("/list/{employeeid}")
    public Result list(@PathVariable Long employeeid){
        log.info("员工打卡记录查询：{}", employeeid);
        List<Timecard> list = timecardService.list(employeeid);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result submit(@PathVariable Long id){
        log.info("员工提交打卡：{}", id);
        timecardService.submit(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody TimecardInsertDTO timecard){
        log.info("员工修改打卡：{}", timecard);
        timecardService.update(timecard);
        return Result.success();
    }
}
