package com.example.rollpay.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/30/15:03
 * @Description:
 */
@Data
public class EmployeePaymentDTO {
    private Long id;
    private Short way;
    private String address;
    private String bankname;
    private String banknumber;
}
