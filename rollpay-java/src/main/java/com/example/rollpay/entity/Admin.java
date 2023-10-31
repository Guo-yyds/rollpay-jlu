package com.example.rollpay.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: guo haonan
 * @Date: 2023/09/28/11:59
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private Long id;

    private String username;

    private String password;

}
