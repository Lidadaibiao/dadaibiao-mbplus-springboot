package com.lidadaibiao.mbplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lidadaibiao
 * @date 2020/6/1 - 23:01
 */
@SpringBootApplication
@MapperScan("com.lidadaibiao.mbplus.mapper") //设置扫描mapper接口的扫描包
public class MpTestApp {

    public static void main(String[] args) {
        SpringApplication.run(MpTestApp.class,args);
    }
}
