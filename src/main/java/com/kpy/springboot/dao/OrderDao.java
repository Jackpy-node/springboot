package com.kpy.springboot.dao;

import com.kpy.springboot.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.dao
 * @data: 2019-8-26 17:40
 * @discription: 数据持久层类OrderDao.java
 **/

@Repository
public class OrderDao {

    private static Logger logger = LoggerFactory.getLogger(OrderDao.class);

    private JdbcTemplate jdbcTemplate;  //数据库操作实例

    //新增
    public String Insert(Order order) {
        if (StringUtils.isEmpty(order.getNo())) {
            return null;
        }
        if (StringUtils.isEmpty(order.getDate())) {
            order.setDate(new Date(new java.util.Date().getTime()));
        }
        String id = UUID.randomUUID().toString();
        logger.debug("UUID:{}", id);
        String sql = "insert into t_order(order_id,order_no,order_date,order_quantity) values (?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{id, order.getNo(), order.getDate(), order.getQuantity()});
        return id;
    }

    //删除
    public String Delete(String id) {
        String sql = "delete from t_order where order_id=?";
        jdbcTemplate.update(sql, new Object[]{id});
        return id;
    }

    //修改
    public String Update(Order order) {
        String sql = " update t_order set order_no = ? , order_date = ? , order_quantity = ? where order_id = ? ";
        jdbcTemplate.update(sql,
                new Object[]{order.getNo(), order.getDate(), order.getQuantity(), order.getId()});
        return order.getId();
    }

    //查询所有
    public List<Order> SelectAll() {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from t_order";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, new Object[]{});
        while (rowSet.next()) {
            Order order = new Order();
            order.setId(rowSet.getString("order_id"));
            order.setNo(rowSet.getString("order_no"));
            order.setDate(rowSet.getDate("order_date"));
            order.setQuantity(rowSet.getInt("order_quantity"));
            orderList.add(order);
        }
        return orderList;
    }

    //查询单条
    public Order SelectOne(String id) {
        Order order = new Order();
        String sql = "select * from t_order where order_id=?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, new Object[]{id});
        while (rowSet.next()) {
            order.setId(rowSet.getString("order_id"));
            order.setNo(rowSet.getString("order_no"));
            order.setDate(rowSet.getDate("order_date"));
            order.setQuantity(rowSet.getInt("order_quantity"));
        }
        return order;
    }
}
