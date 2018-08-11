package com.example.demo.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class UserVO {

    private String user_name="";
    private String user_phone="";
    private float user_latitude;
    private float user_longitude;
    private int user_key;
    private String user_token;
    /*
    user_img 파라미터는 file.getOriginalFileName 하면 됨
     */
    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public float getUser_latitude() {
        return user_latitude;
    }

    public void setUser_latitude(float user_latitude) {
        this.user_latitude = user_latitude;
    }

    public float getUser_longitude() {
        return user_longitude;
    }

    public void setUser_longitude(float user_longitude) {
        this.user_longitude = user_longitude;
    }

    public int getUser_key() {
        return user_key;
    }

    public void setUser_key(int user_key) {
        this.user_key = user_key;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
