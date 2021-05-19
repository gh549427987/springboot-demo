package com.jhua;

import com.jhua.Service.UserServiceImpl;
import com.jhua.dao.UserMapper;
import com.jhua.pojo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test_redis() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();

        redisTemplate.opsForValue().set("mykey", "jhua");
        System.out.println(redisTemplate.opsForValue().get("mykey"));

    }

    @Test
    void test_mybatis() {
        User root = userService.queryUserByName("root");
        System.out.println(root);
    }

    @Test
    void contextLoads_jdbc() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        connection.close();
    }

}
