package com.example.rollpay.controller;

import com.example.rollpay.entity.Buyorder;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.entity.Timecard;
import com.example.rollpay.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/14:28
 * @Description:
 */
@Component
@Slf4j
public class RunTaskController {
    @Autowired
    private EmployeeService employeeService;
//    @Scheduled(cron = "0/10 * * * * ?") // 每10秒执行一次
//    public void runTask() {
//        // 在这里编写定时任务的逻辑
//        System.out.println("定时任务执行了！");
//    }
//    @Scheduled(cron = "0/5 * * * * ?") // 每10秒执行一次
//    public void runTask1() {
//        // 在这里编写定时任务的逻辑
//        System.out.println("定时任务执行了111！");
//    }


    @Scheduled(cron = "0 0 0 ? * FRI")//每周五执行一次
//    @Scheduled(cron = "0/10 * * * * ?") // 每10秒执行一次
    public void runTask2() {
        List<Employee> list = employeeService.getByKind(0);
        for (Employee employee : list) {
            double sum = 0;
            //当前时间
            Date now = new Date();
            //当前时间的前一周
            Date before = new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000);
            List<Timecard> l = employeeService.getTimecard(employee.getId(), before, now);
            for (Timecard timecard : l) {
                sum += timecard.getTime() * employee.getHoursal();
            }
            log.info("员工" + employee.getName() + "的工资为" + sum);
            if (employee.getIsDelete() == 1) {
                employeeService.realDelete(employee.getId());
            }
        }
    }

    //每月最后一个工作日执行一次
    @Scheduled(cron = "0 0 0 LW * ? ")
    public void runTask3() {
        //月薪员工
        List<Employee> list = employeeService.getByKind(1);
        for (Employee employee : list) {
            log.info("员工" + employee.getName() + "的工资为" + employee.getSal());
            if (employee.getIsDelete() == 1) {
                employeeService.realDelete(employee.getId());
            }
        }
        //销售员工
        List<Employee> list1 = employeeService.getByKind(2);
        for (Employee employee : list1) {
            double sum = employee.getSal();
            //当前时间
            Date now = new Date();
            //当前时间的前一月
            Date before = new Date(now.getTime() - 30 * 24 * 60 * 60 * 1000);
            List<Buyorder> l = employeeService.getBuyorder(employee.getId(), before, now);
            for (Buyorder buyorder : l) {
                sum += buyorder.getAmount() * employee.getCom();
            }
            log.info("员工" + employee.getName() + "的工资为" + sum);
            if (employee.getIsDelete() == 1) {
                employeeService.realDelete(employee.getId());
            }
        }
    }
}
