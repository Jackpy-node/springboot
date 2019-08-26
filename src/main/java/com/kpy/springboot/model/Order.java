package com.kpy.springboot.model;

import java.util.Date;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.model
 * @data: 2019-8-23 21:56
 * @discription: 实体类
 **/
public class Order {
    private String no;
    private Date date;
    private int quantity;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
