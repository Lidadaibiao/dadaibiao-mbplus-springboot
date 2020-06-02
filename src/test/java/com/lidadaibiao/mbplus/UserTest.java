package com.lidadaibiao.mbplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lidadaibiao.mbplus.pojo.User;
import com.lidadaibiao.mbplus.mapper.UserMapper;


import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lidadaibiao
 * @date 2020/6/1 - 23:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {


    @Autowired
    UserMapper userMapper;


    /**
     * 测试Mybatis-Plus 查找方法
     */
    @Test
    public void testSelect(){
        List<User> list =  userMapper.selectList(null);
        for (User user : list) {
            System.out.println(user);
        }

    }

    /**
     * 测试传统 映射文件形式。
     */
    @Test
    public void findById(){
        User user =  userMapper.findById(1L);

        System.out.println(user);

    }

    /**
     * 测试 MybatisPlus通用CRUD
     *
     * 新增
     */
    @Test
    public void InsUser(){
        User user = new User();

        user.setPassword("123");
        user.setName("大代表2");
        user.setEmail("123@qq.com");
        user.setUserName("dadaibiao");
        user.setAge("22");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2020-01-01 00:00:00", dateTimeFormatter);
        user.setBirthday(localDateTime);
        int result =  userMapper.insert(user);
        System.out.println(result);

    }

    /**
     * 更新操作
     */
    //-----------------------------------

    /**
     *  根据id更新
     *  说明：只能将对象中不为NULL的属性更新到表中。
     */
    @Test
    public void updUser(){
        User user = new User();
        user.setId(2);//设置更新的主键id
        user.setAge("11");//需要将age更新为11
        user.setName("bingzhang");//需要将name更新为bingzhang
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
    /**
     * 根据条件更新
     * 说明：只能将对象中不为NULL的属性更新到表中。
     * 可实现批量更新
     */
    @Test
    public void updUser1(){
        User user = new User();
        //更新的字段
        user.setName("兵长");

        //更新的条件
        QueryWrapper<User> wra = new QueryWrapper<>();
        wra.eq("age","11");

        //执行更新操作
        int result =  userMapper.update(user,wra);
        System.out.println(result);
    }

    /**
     * 通过UpdateWrapper进行更新
     *
     */
    @Test
    public void updUser2(){
        //创建更新条件 和待更新数据
        UpdateWrapper<User> udw = new UpdateWrapper<>();
        udw.eq("age","22").set("name","兵长").set("email",null);
        //执行操作
        int result = userMapper.update(null,udw);
        System.out.println(result);
    }

    //-------------
    /**
     * 删除操作
     * 根据ID删除
     * 根据条件删除
     * 批量删除
     */

    /**
     * 根据id删除
     */
    @Test
    public void delUser(){
        int result = userMapper.deleteById(7L);
        System.out.println(result);
    }

    /**
     * 根据条件删除
     */
    @Test
    public void delUser1(){
       QueryWrapper<User> qw = new QueryWrapper<>();
       //DELETE FROM tb_user WHERE age = ? AND name = ?
        qw.eq("age","11").eq("name","兵长");
      /*  User user = new User();
        user.setName("兵长");
        user.setAge("11");
        QueryWrapper<User> qw = new QueryWrapper<>(user);*/
        int result =  userMapper.delete(qw);
        System.out.println(result);
    }

    /**
     * 批量删除
     */
    @Test
    public void delUser2(){
        //DELETE FROM tb_user WHERE id IN ( ? , ? , ? )
        int result = userMapper.deleteBatchIds(Arrays.asList(4L,5L,13L));
        System.out.println(result);
    }
    //-----------
    /**
     * 查询
     * 根据id查询
     * 批量查询
     * 查询单条数据
     * 查询列表
     * 分页查询
     */
    /**
     * 根据Id查询
     */
    @Test
    public void selUser(){
        User user = userMapper.selectById(12L);
        System.out.println(user);
    }
    /**
     * 批量查询
     */
    @Test
    public void selUser1(){
        //根据id集合批量查询
        //SELECT id,user_name,password,name,age,email,birthday FROM tb_user WHERE id IN ( ? , ? , ? , ? )
        List<User> list = userMapper.selectBatchIds(Arrays.asList(1L,8L,9L,12L));
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 查询单条数据
     * 根据条件查询一条数据，如果结果超过一条会报错
     */
    @Test
    public void selUser2(){
        //设置QueryWrapper对象，设置查询条件，可以设置多个条件
        //SELECT id,user_name,password,name,age,email,birthday FROM tb_user WHERE name = ?
        QueryWrapper<User> qwr = new QueryWrapper<>();
        qwr.eq("name","张三");
        //执行查询
        User user =   userMapper.selectOne(qwr);
        System.out.println(user);
    }
    /**
     * 查询列表
     * 根据 QueryWrapper 条件，查询总记录数
     */
    @Test
    public void selUser3(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        //SELECT COUNT( 1 ) FROM tb_user WHERE name = ? AND age > ?
        qw.eq("name","大代表2").gt("age","25");
        int count =  userMapper.selectCount(qw);
        System.out.println(count);
    }
    /**
     * 查询全部记录
     * queryWrapper 实体对象封装操作类（可以为 null）
     */
    @Test
    public void selUser4(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.gt("age","26");
        //SELECT id,user_name,password,name,age,email,birthday FROM tb_user WHERE age > ?
        List<User> list = userMapper.selectList(qw);
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 分页查询
     * 需要配置分页插件
     */
    @Test
    public void selUser5(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.gt("age","20");//设置选择条件 年龄大于20

        /**
         * 设置分页规则
         *Page(long current, long size)
         * current：当前页码
         * size: 每页的记录数
         *
         */

        Page<User> page = new Page<>(2,2);
        //根据条件执行查询
        IPage<User> iPage =  userMapper.selectPage(page,qw);
        System.out.println("总条数为：---》"+iPage.getTotal());
        System.out.println("总页数为：---》"+iPage.getPages());

        //取出分页记录
        List<User> list = iPage.getRecords();
        for (User user : list) {
            System.out.println(user);
        }
    }


    /**
     * 模糊查询
     */
    @Test
    public void mohuSelUser(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        //Parameters: %代%(String)
        //qw.like("name","代");
        //Parameters: %大(String)
        //qw.likeLeft("name","大");
        //Parameters: 大%(String)
        //qw.likeRight("name","大");
        //SELECT id,user_name,password,name,age,email,birthday FROM tb_user WHERE name NOT LIKE %大%(String)
        //qw.notLike("name","大");
        List<User> list =  userMapper.selectList(qw);
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 逻辑查询
     */
    @Test
    public void luojiSelUser(){

        QueryWrapper<User> qw = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email,birthday FROM tb_user WHERE name = ? OR name = ?
        qw.eq("name","大代表2").or().eq("name","张三");
        List<User> list = userMapper.selectList(qw);

        for (User user : list) {
            System.out.println(user);
        }
    }
}
