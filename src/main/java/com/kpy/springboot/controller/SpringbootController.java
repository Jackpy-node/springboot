package com.kpy.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.controller
 * @data: 2019-8-22 21:42
 * @discription: Spring Boot页面访问控制代码实现
 **/

@Controller
public class SpringbootController {

    // @RestController注解，相当于@Controller+@ResponseBody两个注解的结合，
    // 返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，
    // 就不能返回jsp,html页面，视图解析器无法解析jsp,html页面

    @RequestMapping("/hello")
    public static String hello(){
        return "Hello Spring Boot";
    }

    @GetMapping("/index")
    public String Index(Model model){
        model.addAttribute("name", "kpy");
        return "index";
    }
}
