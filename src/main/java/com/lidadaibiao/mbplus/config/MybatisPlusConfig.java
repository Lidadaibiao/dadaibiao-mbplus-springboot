package com.lidadaibiao.mbplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * MybatisPlus相关插件配置
 * @author Lidadaibiao
 * @date 2020/6/2 - 14:59
 */
@Configuration
@MapperScan("com.lidadaibiao.mbplus.mapper")//设置mapper接口的扫描包
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
