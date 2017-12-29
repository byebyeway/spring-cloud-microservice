package com.eccom.cloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SendMessageCenter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Ware ware;

    public void sendMessage(String message) {
        logger.info("send message "+ message);
        ware.sendWare().send(MessageBuilder.withPayload(message).build());
    }
}
