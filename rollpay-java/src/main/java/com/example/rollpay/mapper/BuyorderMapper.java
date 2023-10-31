package com.example.rollpay.mapper;

import com.example.rollpay.entity.Buyorder;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/11:46
 * @Description:
 */
@Mapper
public interface BuyorderMapper {
    @Insert("insert into buyorder (date, amount, pointcontact, billingaddress, employeeid) " +
            "values " +
            "(#{date},#{amount},#{pointcontact},#{billingaddress},#{employeeid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(com.example.rollpay.entity.Buyorder buyorder);

    @Select("select * from buyorder where id = #{id}")
    Buyorder getById(Long id);

    void update(Buyorder buyorder);

    @Delete("delete from buyorder where id = #{id}")
    void delete(Long buyorder);

    @Select("select * from buyorder where employeeid = #{id} and date between #{before} and #{now}")
    List<Buyorder> getBuyorder(Long id, Date before, Date now);

    @Select("select * from buyorder where employeeid = #{id}")
    List<Buyorder> getByEmployeeId(Long id);
}
