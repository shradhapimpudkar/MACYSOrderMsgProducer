package com.order.service;

import org.springframework.http.ResponseEntity;

import com.order.dto.OrderMsgProducerJson;
import com.order.dto.xml.FulfillmentOrder;

public interface MacyProducerService {

	ResponseEntity<String> produceXmlMsgToRabbitQ(FulfillmentOrder xmlOrder);

	ResponseEntity<String> produceJsonMsgToRabbitQ(OrderMsgProducerJson JsonOrder);

}
