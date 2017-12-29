package com.eccom.cloud;

import com.eccom.cloud.service.Barista;
import com.eccom.cloud.service.Ware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({Barista.class, Ware.class})
public class Application {

    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
    }



}
