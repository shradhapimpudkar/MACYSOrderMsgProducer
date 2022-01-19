package com.order.service;

import org.springframework.http.ResponseEntity;

import com.order.dto.OrderMsgProducerJson;
import com.order.dto.xml.FulfillmentOrder;

public interface MacyProducerService {

	ResponseEntity<String> produceXmlMsg(FulfillmentOrder fulfillmentOrder);

	ResponseEntity<String> produceJsonMsg(OrderMsgProducerJson orderMessageJson);

}
