package com.example.rollpay.service;

import com.example.rollpay.dto.TimecardInsertDTO;
import com.example.rollpay.entity.Timecard;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/11:45
 * @Description:
 */
public interface TimecardService {

    void insert(Timecard timecard);

    void submit(Long id);

    void update(TimecardInsertDTO timecard);


    List<Timecard> list(Long employeeid);
}
