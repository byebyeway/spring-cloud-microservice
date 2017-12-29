package com.eccom.cloud.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Service
public interface Demand {

    String ORDER = "order";

    String RESPONSE = "response";

    @Output(Demand.RESPONSE)
    MessageChannel sendResponse();

    @Input(Demand.ORDER)
    SubscribableChannel receiveOrder();

}
