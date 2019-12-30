package com.wlf.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class User
 * Date  2019/12/20 14:17
 * Author 王龙飞
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "用户名")
    private String username;
    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "法号")
    private String dharma;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "头像",type = 2,width = 40,height = 20)
    private String photo;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "电话")
    private String phone;
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name="注册时间",format = "yyyy年MM月dd日")
    private Date createDate;
    private String name;

}
