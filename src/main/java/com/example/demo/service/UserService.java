package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务层
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Transactional
    public User create(User user) {
        userMapper.insert(user);
        return user;
    }

    @Transactional
    public boolean update(User user) {
        return userMapper.update(user) > 0;
    }

    @Transactional
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    public List<User> findByAgeGreaterThan(int minAge) {
        return userMapper.findByAgeGreaterThan(minAge);
    }
}
