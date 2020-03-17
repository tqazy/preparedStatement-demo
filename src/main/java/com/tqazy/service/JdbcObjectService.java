package com.tqazy.service;

import com.tqazy.entity.User;

/**
 * @author 散场前的温柔
 */
public interface JdbcObjectService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);
}
