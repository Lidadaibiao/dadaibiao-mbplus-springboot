package com.lidadaibiao.mbplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lidadaibiao.mbplus.pojo.User;

/**
 * @author Lidadaibiao
 * @date 2020/6/1 - 23:00
 */
public interface UserMapper extends BaseMapper<User> {


    //测试一下mapperUser.xml
    User findById(long id);
}
