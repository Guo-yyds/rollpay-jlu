package com.example.rollpay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/12:48
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String SSN;
    private double tax;
    private double detection;
    private String telephone;

    private short kind;
    private double hoursal;
    private double sal;
    private double com;
    private double hourlimit;
    private short way;
    private String address;
    private String bankname;
    private String banknumber;

    private Short isDelete;
}
