package com.eccom.cloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;


@Component
public class ReceivMessageCenter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(Barista.TEST)
    public void  receiveTest(Object payload){
        logger.info("we got test "+ payload);
    }

    @StreamListener(Barista.CUSTOMER_RESPONSE)
    public void receiveOrder(Object payload){
        logger.info("we got order "+ payload);
    }


}
