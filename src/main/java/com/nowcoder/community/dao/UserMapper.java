package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //根据id查询用户
    User selectById(int id);
    //根据用户名查用户
    User selectByName(String username);
    //根据邮箱查用户
    User selectByEmail(String email);
    //插入一个用户，返回改变的行数
    int insertUser(User user);
    //根据id和状态修改一个用户，返回改变的行数
    int updateStatus(int id,int status);
    //根据id和头像路径修改头像,返回改变的行数
    int updateHeader(int id,String headerUrl);
    //根据id修改密码，返回改变的行数
    int updatePassword(int id,String password);
}
