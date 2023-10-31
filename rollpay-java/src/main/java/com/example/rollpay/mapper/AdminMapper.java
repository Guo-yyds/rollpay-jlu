package com.example.rollpay.mapper;

import com.example.rollpay.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/12:15
 * @Description:
 */
@Mapper
public interface AdminMapper {
    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Admin getByUsername(String username);
}
