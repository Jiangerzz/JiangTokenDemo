package com.jiang.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {
    @TableId
    private Long id;
    private String userName;
    private String NickName;
    private String password;
    private String status;
    private String email;
    private String phonenumber;
    private String sex;
    private String userType; //管理员0，用户1
    private Long createBy; //创建人
    private Date createTime;
    private Long updateBy; //更新人
    private Date updateTime;
    @TableLogic
    private Integer delFlag; //逻辑删
}
