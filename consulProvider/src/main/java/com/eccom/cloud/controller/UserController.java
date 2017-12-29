package com.eccom.cloud.controller;

import com.eccom.cloud.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String hello(){
        return "hello consul !!";
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){
        logger.info("invoked id is "+id);
        User user = new User();
        user.setName("consul user");
        user.setId(555);
        user.setMobileNo("13555555");
        return user;
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
//        logger.info(name);
        logger.info(user.getMobileNo());
        logger.info(user.getName());
        logger.info(String.valueOf(user.getId()));
    }

    @GetMapping("/hytrixTest/{name}/{id}")
    public void hytrixTest(@PathVariable("name") String name,
                           @PathVariable("id") String id) throws InterruptedException {
        logger.info("##### enter provider hytrix #####");
        logger.info("##### "+name+" "+id+" #####");
        Thread thread = new Thread();
        thread.sleep(5000);
        logger.info("##### end provider hytrix   #####");

    }


}
