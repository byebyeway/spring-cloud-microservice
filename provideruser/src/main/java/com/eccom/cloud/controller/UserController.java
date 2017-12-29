package com.eccom.cloud.controller;

import com.eccom.cloud.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) throws Exception {
        System.out.println(id);
        log.info("receive service request"+id);
        if(id == 2){
            throw new Exception();
        }
        User u = new User();
        u.setId(1);
        u.setMobileno("111");
        u.setName("eccom");
        return u;
    }

    @RequestMapping("/")
    public String home() {
        return "Hello User World";
    }



}
