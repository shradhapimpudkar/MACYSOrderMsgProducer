package com.order.components;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.order.dto.xml.FulfillmentOrder;

@Component
public class ProducerQueueXML {

	@Autowired
    @Qualifier("XMLAmqpTemplate")
	AmqpTemplate xmlTemplate;

	@Autowired
	@Qualifier(value="XML_Q")
	private Queue xmlQueue;

	public void send(FulfillmentOrder fullFulfillmentOrder) throws AmqpException {
		xmlTemplate.convertAndSend(xmlQueue.getName(), fullFulfillmentOrder);
	}
}