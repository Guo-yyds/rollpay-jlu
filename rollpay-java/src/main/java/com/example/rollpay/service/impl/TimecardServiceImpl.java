package com.example.rollpay.service.impl;

import com.example.rollpay.dto.TimecardInsertDTO;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.entity.Timecard;
import com.example.rollpay.mapper.EmployeeMapper;
import com.example.rollpay.mapper.TimecardMapper;
import com.example.rollpay.service.TimecardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/14:09
 * @Description:
 */
@Service
public class TimecardServiceImpl implements TimecardService {

    @Autowired
    private TimecardMapper timecardMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void insert(Timecard timecard) {
        Employee e = employeeMapper.getById(timecard.getEmployeeid());
        if (e == null) {
            throw new RuntimeException("员工不存在");
        }
        timecardMapper.insert(timecard);
    }

    @Override
    public void submit(Long id) {
        timecardMapper.setStatue(id);
    }

    @Override
    public void update(TimecardInsertDTO timecard) {
        Timecard t = timecardMapper.getById(timecard.getId());
        if (t == null) {
            throw new RuntimeException("打卡记录不存在");
        }
        if (t.getIssubmit() == 1) {
            throw new RuntimeException("打卡记录已提交");
        }
        timecardMapper.update(timecard);
    }

    @Override
    public List<Timecard> list(Long employeeid) {
        return timecardMapper.list(employeeid);
    }
}
