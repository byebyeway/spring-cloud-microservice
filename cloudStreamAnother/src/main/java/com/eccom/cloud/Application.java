package com.eccom.cloud;

import com.eccom.cloud.service.Customer;
import com.eccom.cloud.service.Demand;
import com.eccom.cloud.service.Processor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
