package com.eccom.cloud.controller;

import com.eccom.cloud.model.User;
import com.eccom.cloud.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "hytrix")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/user/consul/get")
    public User getUser(){
        logger.info("consul invoke");
        return userService.getUser(1);
    }

    @GetMapping("/")
    public String home(){
        return "consul service-consumer";
    }

    @GetMapping("/user/consul/add")
    public void addUser(){
        logger.info("consul add user");
        User user = new User();
        user.setMobileNo("123456789");
        user.setId(666);
        user.setName("userTester");
        try{
            userService.addUser(user);
        }catch (Exception e){
            logger.info("invoke fail");
            logger.info(e.getMessage());
        }finally {
            logger.info("client invoke fail");
        }
    }

    @GetMapping("/hytrixRemote")
    public String hystrixRemote(){
        String a = "name1";
        String b = "id1";
        userService.hytrixRemote(a, b);

        return "end hystrix";
    }


//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
//    })
    @HystrixCommand
    @GetMapping("/hytrix")
    public String hystrix(){
        String message = "hystrix";
        return message;
    }

    @HystrixCommand
    @GetMapping("/hytrixTimeout")
    public String hystrixTimeout() throws InterruptedException {
        String message = "hystrix";
        Thread thread = new Thread();
        thread.sleep(8000);
        return message;
    }

    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5")
    })
    @GetMapping("/hytrixProperty")
    public String hystrixProperty() throws InterruptedException {
        String message = "hystrixProperty";
        logger.info("get Request");
        Thread thread = new Thread();
        thread.sleep(8000);
        return message;
    }

    @HystrixCommand(threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "100"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
    })
    @GetMapping("/hytrixThread")
    public String hystrixThread() throws InterruptedException {
        String message = "hystrixProperty";
        logger.info("get Thread Request");
        return message;
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
            @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="2")
    })
    @GetMapping("/hytrixSema")
    public String hystrixSema() throws InterruptedException {
        String message = "hystrixProperty";
        logger.info("get Sema Request");
        return message;
    }


    public String hytrix(){
        logger.info("get fallback");
        return "hytrix Method";
    }


}
