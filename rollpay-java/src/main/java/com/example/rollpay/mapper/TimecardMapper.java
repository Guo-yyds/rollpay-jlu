package com.example.rollpay.mapper;

import com.example.rollpay.dto.TimecardInsertDTO;
import com.example.rollpay.entity.Timecard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/14:10
 * @Description:
 */
@Mapper
public interface TimecardMapper {

    @Insert("insert into timecard (date, time, projectid, employeeid) values (#{date}, #{time}, #{projectid}, #{employeeid})")
    public void insert(Timecard timecard);

    @Update("update timecard set isSubmit = 1 where id = #{id}")
    void setStatue(Long id);

    @Select("select * from timecard where id = #{id}")
    Timecard getById(Long id);

    void update(TimecardInsertDTO timecard);

    @Select("select * from timecard where employeeid = #{id} and date between #{before} and #{now}")
    List<Timecard> getTimecard(Long id, Date before, Date now);

    @Select("select * from timecard where employeeid = #{employeeid}")
    List<Timecard> list(Long employeeid);
}
