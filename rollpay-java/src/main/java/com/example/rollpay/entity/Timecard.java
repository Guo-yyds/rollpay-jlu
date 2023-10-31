package com.example.rollpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/19:26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timecard {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Double time;
    private Long projectid;
    private Long employeeid;
    private Short issubmit;
}
