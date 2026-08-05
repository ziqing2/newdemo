package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 Mapper 接口
 * 支持 XML 方式和注解方式混合使用
 */
@Mapper
public interface UserMapper {

    /** 根据 ID 查询用户 (XML 方式) */
    User findById(@Param("id") Long id);

    /** 查询所有用户 (XML 方式) */
    List<User> findAll();

    /** 插入用户 (XML 方式，返回自增 ID) */
    int insert(User user);

    /** 更新用户 (XML 方式) */
    int update(User user);

    /** 根据 ID 删除用户 (XML 方式) */
    int deleteById(@Param("id") Long id);
}
