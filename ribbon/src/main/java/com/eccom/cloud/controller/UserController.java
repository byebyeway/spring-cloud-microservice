package com.eccom.cloud.controller;

import com.eccom.cloud.model.User;
import com.eccom.cloud.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/user/ribbon/{id}")
    public User getUser(@PathVariable int id){
        log.info("Robbin user search");
        return userService.getUser(id);
    }
}
