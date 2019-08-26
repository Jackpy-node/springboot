package com.kpy.springboot.controller;

import com.kpy.springboot.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZoneId;
import java.util.HashMap;
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
        return "angularjs";
    }

    @PostMapping("/postData")
    public @ResponseBody Map<String,Object> postData(String no,String quantity,String date){
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
}
