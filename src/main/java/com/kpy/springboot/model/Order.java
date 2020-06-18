package com.kpy.springboot.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.model
 * @data: 2019-8-23 21:56
 * @discription: 实体类
 **/

@Entity
@Table(name="t_order")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_id", length = 36)
    private String id;

    @Column(name = "order_no", length = 50)
    @NotNull
    private String no;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    @NotNull
    private Date date;

    @Column(name = "order_quantity")
    @NotNull
    @Min(100)
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
