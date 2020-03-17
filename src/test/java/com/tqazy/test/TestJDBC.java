package com.tqazy.test;

import com.tqazy.entity.User;
import com.tqazy.jdbc.JDBCUtils;
import com.tqazy.service.JdbcObjectService;
import com.tqazy.service.impl.JdbcObjectServiceImpl;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBC {

    @Test
    public void update() {
        User user = new User("苏熙", "admin123", 19, "这是一个可爱的美少女");
        JdbcObjectService service = new JdbcObjectServiceImpl();
        boolean flag = service.addUser(user);
        System.out.println("添加结果：" + flag);
    }
}
