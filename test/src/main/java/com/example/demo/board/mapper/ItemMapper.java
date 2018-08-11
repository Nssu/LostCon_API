package com.example.demo.board.mapper;


import com.example.demo.board.model.ItemVO;
import com.example.demo.board.model.UserVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.example.demo.board.mapper.ItemMapper")
@Mapper
public interface ItemMapper {

    //나의 모든 비콘 조회
    @Select("select * from item_list WHERE user_key = #{user_key}")
    List<ItemVO> myItems(int user_key);

    //    INSERT INTO item_list(user_key,item_name,item_uuid,item_range,item_img) VALUES(12,"yoon",12,"70","dahyun.png")
//#{item_name},#{item_uuid},#{item_range},#{item_img}
    @Insert("INSERT INTO item_list(user_key,item_name,item_uuid,item_range,item_img) VALUES(#{user_key},#{item_name},#{item_uuid},#{item_range},#{item_img})")
    void uploadItem(ItemVO item);

    @Select("select item_key from item_list WHERE user_key = #{user_key} and item_name = #{item_name}")
    int getItemKey(ItemVO item);
}

