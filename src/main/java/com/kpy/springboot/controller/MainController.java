package com.kpy.springboot.controller;

import com.kpy.springboot.dao.OrderDao;
import com.kpy.springboot.dao.OrderRepository;
import com.kpy.springboot.model.Order;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.controller
 * @data: 2019-8-23 22:00
 * @discription:
 **/

@Controller
// @Controller：用于标注是控制层组件，需要返回页面时请用@Controller而不是@RestController，@Controller只是实例调用，并不携带数据
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);


    // @GetMapping、@PostMapping等:相当于@RequestMapping（value=”/”，method=RequestMethod.Get\Post\Put\Delete等）,是个组合注解；
    @GetMapping("/Main")
    public String index(){
        return "Main";
    }

    @GetMapping("/jquery")
    public String jquery(){
        return "jquery";
    }

    @GetMapping("/angularJS")
    public String angularJS() {
        return "angularJS";
    }

    // @GetMapping、@PostMapping等:相当于@RequestMapping（value=”/”，method=RequestMethod.Get\Post\Put\Delete等）,是个组合注解；
    @PostMapping("/postData")
    public @ResponseBody Map<String,Object> postData(String no,String quantity,String date){
        logger.info("No:{}", no);
        System.out.println("no:" + no);
        System.out.println("quantity:" + quantity);
        System.out.println("date:" + date);
        Map<String,Object> map=new HashMap<>();
        map.put("message","ok");
        map.put("no", no);
        map.put("quantity", quantity);
        map.put("date", date);
        return map;
    }

    @PostMapping("/postJson")
    public @ResponseBody Map<String, Object> postJson(@RequestBody Order order) {
        System.out.println("order no:" + order.getNo());
        System.out.println("order quantity:" + order.getQuantity());
        System.out.println("order date:" + order.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Map<String, Object> map = new HashMap<>();
        map.put("message", "ok");
        map.put("value", order);
        return map;
    }

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/angularJS-JDBC")
    public String angularJSJDBC(){
        return "angularJS-JDBC";
    }

    @PostMapping("/InsertOrUpdate")
    public @ResponseBody Map<String, Object> Insert(@RequestBody @Valid  Order order){
        logger.debug("Order:{}", order.getNo()+","+order.getDate()+","+order.getQuantity());
        Map<String, Object> result=new HashMap<>();
        String id=null;
        if(StringUtils.isNullOrEmpty(order.getId())){
            id=orderDao.Insert(order);
            logger.debug("Id:{}",id);
            order.setId(id);
            order.setDate(new Date(order.getDate().getTime()));
        }
        else {
            orderDao.Update(order);
        }
        result.put("order", order);
        logger.debug("order_id:{}",order.getId());
        return result;
    }

    @RequestMapping("/SelectOne")
    public @ResponseBody Object SelectOne(String id){
        return orderDao.SelectOne(id);
    }

    @PostMapping("/SelectAll")
    public @ResponseBody Object SelectAll() {
        return orderDao.SelectAll();
    }

    @RequestMapping("/Delete")
    public @ResponseBody Map<String, Object> Delete(String id){
        logger.debug("id{}", id);
        orderDao.Delete(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("message", "ok");
        return map;
    }

    @Autowired
    private OrderRepository orderRepository;

    /**
     * like查询
     * @return
     */
    @PostMapping("/findAllByNoLike")
    public @ResponseBody Object findAllByNoLike(@RequestParam String no) {
        logger.debug("No:{}", no);
        List<Order> orderList=orderRepository.findAllByNoLike("%"+no+"%");
        return orderList;
    }

    /**
     *between...and查询
     */
    @RequestMapping("/findAllByDateBetween")
    public @ResponseBody Object findAllByDateBetween(@RequestParam String startDate, @RequestParam String endDate){
        logger.debug("Date:{}", new Date(Long.parseLong(startDate)));
        logger.debug("Date:{}", new Date(Long.parseLong(endDate)));
        return orderRepository.findAllByDateBetween(new Date(Long.parseLong(startDate)), new Date(Long.parseLong(endDate)));
    }

    /**
     *less than查询
     */
    @PostMapping("/findAllByQuantityLessThan")
    public @ResponseBody Object findAllByQuantityLessThan(int quantity) {
        logger.debug("Quantity:{}", quantity);
        return orderRepository.findAllByQuantityLessThan(quantity);
    }
}
