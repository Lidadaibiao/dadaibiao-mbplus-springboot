package com.lidadaibiao.mbplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Lidadaibiao
 * @date 2020/6/1 - 22:54
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO) //指定主键字段名
    private long id;

   // @TableField("user_name")    //  注释为了测试驼峰规则   指定对应字段名
    private String userName;

    @TableField("password") //指定对应字段名
    private String password;

    @TableField("name") //指定对应字段名
    private String name;

    @TableField("age") //指定对应字段名
    private String age;

    @TableField("email") //指定对应字段名
    private String email;

    @TableField("birthday")
    private LocalDateTime birthday;


}
