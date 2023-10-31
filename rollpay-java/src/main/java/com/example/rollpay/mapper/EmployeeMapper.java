package com.example.rollpay.mapper;


import com.example.rollpay.dto.EmployeePageQueryDTO;
import com.example.rollpay.dto.EmployeePaymentDTO;
import com.example.rollpay.entity.Employee;
import com.example.rollpay.entity.Timecard;
import com.example.rollpay.entity.Buyorder;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface EmployeeMapper {


    /**
     * 插入员工数据
     *
     * @param employee
     */
    @Insert("insert into employee (username, password, name, email, SSN, tax, detection, telephone, kind, hoursal, sal, com, hourlimit, way,address,bankname,banknumber) " +
            "values " +
            "(#{username},#{password},#{name},#{email},#{SSN},#{tax},#{detection},#{telephone},#{kind},#{hoursal},#{sal},#{com},#{hourlimit},#{way},#{address},#{bankname},#{banknumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employee employee);

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @Select("select * from employee")
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据主键动态修改属性
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Select("select * from timecard where employeeid=#{id} and date between #{start} and #{end}")
    List<Timecard> getTimecard(Long id, Date start, Date end);

    @Select("select * from timecard where employeeid=#{id} and projectid=#{projectid} and date between #{startTime} and #{endTime}")
    List<Timecard> getTimecardByProjectid(Long id, Long projectid, Date startTime, Date endTime);

    @Select("select * from buyorder where id=#{id} and date between #{start} and #{end}")
    List<Buyorder> getBuyorder(Long id, Date start, Date end);

    @Select("select distinct projectid from timecard where employeeid=#{id} and date between #{start} and #{end}")
    List<Long> getList(Long id, Date start, Date end);

    @Update("update employee set isDelete=1 where id=#{id}")
    void delete(Long id);

    @Select("select * from employee where kind=#{i}")
    List<Employee> getByKind(int i);

    @Delete("delete from employee where id=#{id}")
    void realDelete(Long id);


    void payment(EmployeePaymentDTO employeePaymentDTO);
}

