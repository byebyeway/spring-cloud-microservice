package com.eccom.cloud.feignclient;

import com.eccom.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "provider-user", fallback =UserFeign.UserHystrix.class)
public interface UserFeign {

    @RequestMapping("/user/{id}")
    User getUserId(@RequestParam("id") int id);

    @Component
    static class UserHystrix implements UserFeign{

        @Override
        public User getUserId(int id) {
            User user = new User();
            user.setMobileno("00000");
            user.setName("__default name");
            user.setId(0);
            return user;
        }
    }
}
