package com.wlf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Admin
 * Date  2019/12/17 15:25
 * Author 王龙飞
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")

public class Admin {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String nickname;
}
