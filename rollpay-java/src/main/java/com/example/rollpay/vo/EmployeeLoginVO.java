package com.example.rollpay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLoginVO {
    private Long id;
    private String username;
    private String token;
    private Short way;
    private Short kind;
}
