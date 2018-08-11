package com.example.demo.board.model;

public class ItemVO {

    private int user_key;
    private int item_key; // item_key는 여기서 주는 것이 아니라 mysql에서 자동으로 조정할 수 있도
    private String item_name;
    private int item_range;
    private String item_img;
    private String item_uuid;
    private int item_compensation;



    public int getUser_key() {
        return user_key;
    }

    public void setUser_key(int user_key) {
        this.user_key = user_key;
    }

    public int getItem_key() {
        return item_key;
    }

    public void setItem_key(int item_key) {
        this.item_key = item_key;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


    public int getItem_range() {
        return item_range;
    }

    public void setItem_range(int item_range) {
        this.item_range = item_range;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }


    public String getItem_uuid() {
        return item_uuid;
    }

    public void setItem_uuid(String item_uuid) {
        this.item_uuid = item_uuid;
    }

    public int getItem_compensation() {
        return item_compensation;
    }

    public void setItem_compensation(int item_compensation) {
        this.item_compensation = item_compensation;
    }
}
