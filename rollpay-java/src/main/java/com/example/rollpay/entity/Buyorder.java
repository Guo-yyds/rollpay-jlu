package com.example.rollpay.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/20:07
 * @Description:
 */
@Data
public class Buyorder {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Double amount;
    private String pointcontact;
    private String billingaddress;
    private Long employeeid;
}
