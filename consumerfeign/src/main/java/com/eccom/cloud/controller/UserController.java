package com.eccom.cloud.controller;

import com.eccom.cloud.entity.User;
import com.eccom.cloud.feignclient.UserFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserFeign userFeign;

    @NewSpan("sleuth--test--service")
    @RequestMapping(value="/feign/user/{id}", method = RequestMethod.GET)
    public User getUserId(@PathVariable int id ){
            log.info("Feign user search");
            User user = userFeign.getUserId(id);
            return user;
    }

}
