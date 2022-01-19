package com.order.components;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.order.dto.OrderMsgProducerJson;

@Component
public class ProducerQueueJSON {

    @Autowired
    @Qualifier("JsonAmqpTemplate")
    AmqpTemplate jsonTemplate;
    
    @Qualifier(value="JSON_Q")
    @Autowired
    private Queue jsonQueue;

    public void send(OrderMsgProducerJson orderMsgJson) throws AmqpException {
        jsonTemplate.convertAndSend(jsonQueue.getName(), orderMsgJson);
    }
}