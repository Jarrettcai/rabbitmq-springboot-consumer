package com.enn.springboot.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private Integer Id;
    private String Name;
    private String Desc;
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getDesc() {
        return Desc;
    }
    public void setDesc(String desc) {
        Desc = desc;
    }
    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Desc='" + Desc + '\'' +
                '}';
    }
}
