package com.example.rollpay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/19:12
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReportVO {
    private Short kind;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Long projectid;
    private Double answer;
}
