package com.eccom.cloud.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface Ware {

    String ORDER = "order";

    @Output(Ware.ORDER)
    MessageChannel sendWare();

}
