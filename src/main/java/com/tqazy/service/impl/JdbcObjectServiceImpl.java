package com.tqazy.service.impl;

import com.tqazy.entity.User;
import com.tqazy.jdbc.JDBCUtils;
import com.tqazy.service.JdbcObjectService;
import org.springframework.stereotype.Service;

/**
 * @author 散场前的温柔
 */
@Service
public class JdbcObjectServiceImpl implements JdbcObjectService {
    public boolean addUser(User user) {
        if (user == null) {
            return false;
        }
        String sql = "INSERT INTO user (name, password, age, remark) VALUES (?, ?, ?, ?)";
        int num = JDBCUtils.update("database.properties", sql, user.getName(), user.getPassword(), user.getAge(), user.getRemark());
        if(num > 0){
            return true;
        }
        return false;
    }
}
