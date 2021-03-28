package com.atguigu.demomptest;

import com.atguigu.demomptest.entity.User;
import com.atguigu.demomptest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    //此处报错为java中userMapper文件中只有接口，接口内容是自动生成，可添加@Repository来解决报错。
    private UserMapper userMapper;


    //MP实现复杂查询
    @Test
    public void testSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //ge,gt,le,lt
        //queryWrapper.gt("age",21);

        //eq,ne
        //queryWrapper.eq("name","Tom");

        //between,notBetween
        //queryWrapper.between("age",24,28);

        //like,notLike,likeLeft,likeRight
        //queryWrapper.like("name","张");

        //orderBy,orderByDesc,orderByAsc
        //Desc降序,Asc升序
        queryWrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(queryWrapper);

        System.out.println(users);

    }



    //批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }



    //根据id删除
    @Test
    public void testDeleteById() {
        int rows = userMapper.deleteById(1376194204086063105L);
        System.out.println(rows);

    }


    //分页查询
    @Test
    public void testSelectPage() {
        Page<User> page = new Page(1,3);//当前第一页，每页显示三条记录。
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到分页所有数据
        long pages = userPage.getPages();//页数
        long current = userPage.getCurrent();//当前页
        List<User> records = userPage.getRecords();//当前查询数据集合
        long total = userPage.getTotal();//总记录数
        boolean hasNext = userPage.hasNext();//是否有下一页
        boolean hasPrevious = userPage.hasPrevious();//是否有上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
    }

    //简单条件查询（很少用，了解就好）
    @Test
    public void testSelect2() {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("name","Jack");
        columnMap.put("age",20);

        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }


    //多个id批量查询
    @Test
    public void testSelect1() {

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    //测试乐观锁
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
        user.setName("王五");
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
