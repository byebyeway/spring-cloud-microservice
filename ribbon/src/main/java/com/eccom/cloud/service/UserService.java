package com.eccom.cloud.service;

import com.eccom.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public User getUser(int id){
        return restTemplate.getForObject("http://provider-user/user/" + id, User.class);
    }
}
