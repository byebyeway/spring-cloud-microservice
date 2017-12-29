package com.eccom.cloud.service;

import com.eccom.cloud.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

//@DefaultProperties(commandProperties = {
//        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
//})
@FeignClient(name="consul-user",configuration = FeignConfig.class, fallback =UserService.UserHystrix.class)
public interface UserService {

    @RequestMapping("/user/{id}")
    User getUser(@RequestParam("id") int id);

//  @PostMapping("/user/add")
//  @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    void addUser(@RequestBody User user);

    @HystrixCommand
    @RequestMapping("/hytrixTest/{name}/{id}")
    void hytrixRemote(@RequestParam("name") String arg1, @RequestParam("id") String arg2);

    @Component
    static class UserHystrix implements UserService{

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public User getUser(int id) {
            return new User();
        }

        @Override
        public void addUser(User user) {

        }

        @Override
        public void hytrixRemote(String arg1, String arg2) {
                logger.info("we got problem on feign invoke");
        }
    }

//    @Component
//    static class UserHystrixFallbackFactory implements FallbackFactory<UserService> {
//        @Override
//        public UserService create(Throwable cause) {
//            return new UserService() {
//
//                @Override
//                public User getUser(int id) {
//                    return null;
//                }
//
//                @Override
//                public void addUser(User user) {
//
//                }
//
//                @Override
//                public void hytrixRemote(String arg1, String arg2) {
//
//                }
//            };
//        }
//    }



}
