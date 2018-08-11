package com.example.demo.board.mapper;


import com.example.demo.board.model.UserVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.example.demo.board.mapper.UserMapper")
@Mapper
public interface UserMapper {

    @Select("select * from user_list")
    List<UserVO> findAll();

    @Select("select * from user_list WHERE user_key = #{user_key}")
    List<UserVO> findUser(int user_key);

    @Select("select * from user_list WHERE user_key != #{user_key}")
    List<UserVO> findUserNotMe(int user_key);

    List<UserVO> getUserList();


}

