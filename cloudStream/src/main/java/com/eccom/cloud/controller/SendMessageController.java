package com.eccom.cloud.controller;

import com.eccom.cloud.model.User;
import com.eccom.cloud.service.SendMessageCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendMessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SendMessageCenter sendMessageCenter;

    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable("message") String message){
        sendMessageCenter.sendMessage(message);
    }

    @GetMapping("/api1")
    public String getMessage(){ return "hello api1";}

    @GetMapping("/")
    public String getIndexMessage(){ return "hello api-doc";}

    @GetMapping("/api2")
    public User getUser(){
        User user = new User();
        user.setName("tester");
        user.setId(2);
        user.setMobileNo("13712345678");
        return user;
    }

    @GetMapping("/api3/{name}")
    public User getUserByName(@PathVariable String name){
        User user = new User();
        user.setName(name);
        user.setId(3);
        user.setMobileNo("13712345678");
        return user;
    }

    @GetMapping("/api4")
    public User getUserByParameterName(@RequestParam String mobileNo){
        User user = new User();
        user.setName("requestparam");
        user.setId(4);
        user.setMobileNo(mobileNo);
        return user;
    }

    @PostMapping("/api5")
    public User insertUser(@RequestBody User user){
        logger.info("we got user " + user.getName() + " mobileNo is "+ user.getMobileNo());
        user.setId(5);
        return user;
    }

    @PostMapping("/api6")
    public String insertUserByString(@RequestBody String user){
        logger.info("we got user " + user);
        return user;
    }

}
