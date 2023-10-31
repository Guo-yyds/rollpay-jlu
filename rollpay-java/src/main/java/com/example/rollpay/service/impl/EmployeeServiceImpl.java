package com.example.rollpay.service.impl;


import com.example.rollpay.dto.*;
import com.example.rollpay.entity.Buyorder;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.entity.Timecard;
import com.example.rollpay.exception.AccountNotFoundException;
import com.example.rollpay.exception.PasswordErrorException;
import com.example.rollpay.mapper.BuyorderMapper;
import com.example.rollpay.mapper.EmployeeMapper;
import com.example.rollpay.mapper.TimecardMapper;
import com.example.rollpay.result.PageResult;
import com.example.rollpay.service.EmployeeService;

import com.example.rollpay.vo.AdminReportVO;
import com.example.rollpay.vo.EmployeeReportVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private TimecardMapper timecardMapper;

    @Autowired
    private BuyorderMapper buyorderMapper;
    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException("用户不存在");
        }

        //密码比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException("密码错误");
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    public Long save(Employee employee) {
        employeeMapper.insert(employee);
        return employee.getId();
    }

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // select * from employee limit 0,10
        //开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult(total, records);
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        if (employee == null) {
            throw new AccountNotFoundException("用户不存在");
        }
        return employee;
    }

    /**
     * 编辑员工信息
     *
     * @param employee
     */
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    /**
     * 管理员报表查询
     *
     * @param adminReportDTO
     */
    @Override
    public AdminReportVO adminreport(AdminReportDTO adminReportDTO) {
        Employee employee = employeeMapper.getByUsername(adminReportDTO.getName());
        if (employee == null) {
            throw new AccountNotFoundException("用户不存在");
        }
        double sum = 0;
        if (adminReportDTO.getKind() == 0) {
            //查询总工作时间
            List<Timecard> list = employeeMapper.getTimecard(
                    employee.getId(),
                    adminReportDTO.getStartTime(),
                    adminReportDTO.getEndTime());
            for (Timecard timecard : list) {
                sum += timecard.getTime();
            }
        } else if (adminReportDTO.getKind() == 1) {
            //工资
            if (employee.getKind() == 0) {
                //小时工
                List<Timecard> list = employeeMapper.getTimecard(
                        employee.getId(),
                        adminReportDTO.getStartTime(),
                        adminReportDTO.getEndTime());
                for (Timecard timecard : list) {
                    if (timecard.getTime() < 8)
                        sum += timecard.getTime() * employee.getHoursal();
                    else {
                        sum += 8 * employee.getHoursal() + (timecard.getTime() - 8) * employee.getHoursal() * 1.5;
                    }
                }
            } else if (employee.getKind() == 1) {
                //工资工
                //计算从开始时间到结束时间经过的月份
                int month = (adminReportDTO.getEndTime().getYear() - adminReportDTO.getStartTime().getYear()) * 12 +
                        (adminReportDTO.getEndTime().getMonth() - adminReportDTO.getStartTime().getMonth());
                sum = month * employee.getSal();
            } else if (employee.getKind() == 2) {
                //销售工
                int month = (adminReportDTO.getEndTime().getYear() - adminReportDTO.getStartTime().getYear()) * 12 +
                        (adminReportDTO.getEndTime().getMonth() - adminReportDTO.getStartTime().getMonth());
                sum = month * employee.getSal();

                List<Buyorder> list = employeeMapper.getBuyorder(
                        employee.getId(),
                        adminReportDTO.getStartTime(),
                        adminReportDTO.getEndTime());
                for (Buyorder buyorder : list) {
                    sum += buyorder.getAmount() * employee.getCom();
                }
            }
        }
        AdminReportVO adminReportVO = new AdminReportVO().builder()
                .name(adminReportDTO.getName())
                .kind(adminReportDTO.getKind())
                .startTime(adminReportDTO.getStartTime())
                .endTime(adminReportDTO.getEndTime())
                .answer(sum)
                .build();
        return adminReportVO;
    }

    // 判断指定日期是否是周末
    public static boolean isWeekend(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    @Override
    public EmployeeReportVO employeereport(EmployeeReportDTO employeeReportDTO) {
        Employee employee = employeeMapper.getByUsername(employeeReportDTO.getName());
        if (employee == null) {
            throw new AccountNotFoundException("用户不存在");
        }
        double sum = 0;
        if (employeeReportDTO.getKind() == 0) {
            //查询总工作时间
            List<Timecard> list = employeeMapper.getTimecard(
                    employee.getId(),
                    employeeReportDTO.getStartTime(),
                    employeeReportDTO.getEndTime());
            for (Timecard timecard : list) {
                sum += timecard.getTime();
            }
        } else if (employeeReportDTO.getKind() == 1) {
            //查询一个项目的总工作时间
            List<Timecard> list = employeeMapper.getTimecardByProjectid(
                    employee.getId(),
                    employeeReportDTO.getProjectid(),
                    employeeReportDTO.getStartTime(),
                    employeeReportDTO.getEndTime());
            for (Timecard timecard : list) {
                sum += timecard.getTime();
            }
        } else if (employeeReportDTO.getKind() == 2) {
            //查询剩余假期
            Date start = employeeReportDTO.getStartTime();
            Date end = employeeReportDTO.getEndTime();
            Date now = new Date();
            //如果开始时间在当前时间之前，那么开始时间就是当前时间
            start = start.before(now) ? now : start;
            //计算从开始时间到结束时间的周末数
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            while (!calendar.getTime().after(end)) {
                if (isWeekend(calendar)) {
                    sum++;
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } else if (employeeReportDTO.getKind() == 3) {
            //工资
            if (employee.getKind() == 0) {
                //小时工
                List<Timecard> list = employeeMapper.getTimecard(
                        employee.getId(),
                        employeeReportDTO.getStartTime(),
                        employeeReportDTO.getEndTime());
                for (Timecard timecard : list) {
                    if (timecard.getTime() < 8)
                        sum += timecard.getTime() * employee.getHoursal();
                    else {
                        sum += 8 * employee.getHoursal() + (timecard.getTime() - 8) * employee.getHoursal() * 1.5;
                    }
                }
            } else if (employee.getKind() == 1) {
                //工资工
                //计算从开始时间到结束时间经过的月份
                int month = (employeeReportDTO.getEndTime().getYear() - employeeReportDTO.getStartTime().getYear()) * 12 +
                        (employeeReportDTO.getEndTime().getMonth() - employeeReportDTO.getStartTime().getMonth());
                sum = month * employee.getSal();
            } else if (employee.getKind() == 2) {
                //销售工
                int month = (employeeReportDTO.getEndTime().getYear() - employeeReportDTO.getStartTime().getYear()) * 12 +
                        (employeeReportDTO.getEndTime().getMonth() - employeeReportDTO.getStartTime().getMonth());
                sum = month * employee.getSal();

                List<Buyorder> list = employeeMapper.getBuyorder(
                        employee.getId(),
                        employeeReportDTO.getStartTime(),
                        employeeReportDTO.getEndTime());
                for (Buyorder buyorder : list) {
                    sum += buyorder.getAmount() * employee.getCom();
                }
            }
        }
        EmployeeReportVO employeeReportVO = new EmployeeReportVO().builder()
                .name(employeeReportDTO.getName())
                .kind(employeeReportDTO.getKind())
                .startTime(employeeReportDTO.getStartTime())
                .endTime(employeeReportDTO.getEndTime())
                .projectid(employeeReportDTO.getProjectid())
                .answer(sum)
                .build();
        return employeeReportVO;
    }

    /**
     * 根据id查询员工参与的项目列表
     *
     * @param
     * @return
     */

    @Override
    public List<Long> getList(EmployeeListDTO employeeListDTO) {
        Employee e = employeeMapper.getByUsername(employeeListDTO.getName());
        List<Long> list = employeeMapper.getList(e.getId(), employeeListDTO.getStartTime(), employeeListDTO.getEndTime());
        return list;
    }

    @Override
    public void delete(Long id) {
        employeeMapper.delete(id);
    }

    @Override
    public List<Employee> getByKind(int i) {

        return employeeMapper.getByKind(i);
    }

    @Override
    public List<Timecard> getTimecard(Long id, Date before, Date now) {
        return timecardMapper.getTimecard(id, before, now);
    }

    @Override
    public List<Buyorder> getBuyorder(Long id, Date before, Date now) {
        return buyorderMapper.getBuyorder(id, before, now);
    }

    @Override
    public void realDelete(Long id) {
        employeeMapper.realDelete(id);
    }

    @Override
    public void payment(EmployeePaymentDTO employeePaymentDTO) {
        //判断输入的合法性
        if(employeePaymentDTO.getWay() == 1) {
            //支票
            if (employeePaymentDTO.getAddress() == null || employeePaymentDTO.getAddress().equals("")) {
                throw new RuntimeException("地址不能为空");
            }
        }
        else if(employeePaymentDTO.getWay()==2){
            //银行
            if (employeePaymentDTO.getBankname() == null || employeePaymentDTO.getBankname().equals("")) {
                throw new RuntimeException("银行名不能为空");
            }
            if (employeePaymentDTO.getBanknumber() == null || employeePaymentDTO.getBanknumber().equals("")) {
                throw new RuntimeException("银行账号不能为空");
            }
        }
        Employee e = employeeMapper.getById(employeePaymentDTO.getId());
        if (e == null) {
            throw new AccountNotFoundException("用户不存在");
        }
        employeeMapper.payment(employeePaymentDTO);
    }

}
