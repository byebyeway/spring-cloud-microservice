package com.eccom.cloud.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Service
public interface Barista {

    String TEST = "test";

    String CUSTOMER_RESPONSE = "response";

    @Input(Barista.TEST)
    SubscribableChannel test();

    @Input(Barista.CUSTOMER_RESPONSE)
    SubscribableChannel getresponse();

}
