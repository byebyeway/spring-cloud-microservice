package com.eccom.cloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Demand.class)
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Demand demand;

//    @StreamListener("order")
//    public void getOrder(String message){
//        logger.info("customer get order "+ message);
//        String reponse = "accept";
//        if(message.equals("cold")){
//            reponse = "denied";
//        }
//        demand.sendResponse().send(MessageBuilder.withPayload(reponse).build());
//    }

    @StreamListener(Demand.ORDER)
    @SendTo(Demand.RESPONSE)
    public String processOrder(String message){
        logger.info("processor customer get order "+ message);
        String reponse = "accept";
        if(message.equals("cold")){
            reponse = "denied";
        }
        return reponse;
    }

}
