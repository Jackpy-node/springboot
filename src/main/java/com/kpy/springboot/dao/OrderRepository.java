package com.kpy.springboot.dao;

import com.kpy.springboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.dao
 * @data: 2019-8-31 11:21
 * @discription: JPA持久层框架
 **/
public interface OrderRepository extends JpaRepository<Order,String>{

    /**
     * like查询
     * @param no
     * @return
     */
    List<Order> findAllByNoLike(String no);

    /**
     * between查询
     * @param startDate
     * @param endDate
     * @return
     */
    List<Order> findAllByDateBetween(Date startDate, Date endDate);

    /**
     * 小于查询
     * @param quantity
     * @return
     */
    List<Order> findAllByQuantityLessThan(int quantity);
}
