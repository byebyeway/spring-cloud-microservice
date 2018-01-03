package com.eccom.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    @GetMapping("/swarm")
    public String getSwarm(){
        return "hello swarm !!";
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
