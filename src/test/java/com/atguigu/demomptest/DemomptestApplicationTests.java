package com.atguigu.demomptest;

import com.atguigu.demomptest.entity.User;
import com.atguigu.demomptest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    //此处报错为java中userMapper文件中只有接口，接口内容是自动生成，可添加@Repository来解决报错。
    private UserMapper userMapper;


    @Test
    public void testOptimisticLocker(){
        //根据ID查询
        User user = userMapper.selectById(1370414961297297410L);

        //修改
        user.setName("张三");
        userMapper.updateById(user);
    }

    @Test
    public void testAdd(){
        User user = new User();
        user.setName("李四");
        user.setAge(20);
        user.setEmail("1234@qq.com");

        int insert = userMapper.insert(user);
        System.out.println(insert);

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1370396227648147457L);
        user.setName("lucymaryupup");
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
