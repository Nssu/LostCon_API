package com.example.demo.board.model;

public class LostItemVO {
    private int item_key;
    private int user_key;
    private String item_uuid;
    private int money;
    public int getItem_key() {
        return item_key;
    }

    public void setItem_key(int item_key) {
        this.item_key = item_key;
    }

    public int getUser_key() {
        return user_key;
    }

    public void setUser_key(int user_key) {
        this.user_key = user_key;
    }

    public String getItem_uuid() {
        return item_uuid;
    }

    public void setItem_uuid(String item_uuid) {
        this.item_uuid = item_uuid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
