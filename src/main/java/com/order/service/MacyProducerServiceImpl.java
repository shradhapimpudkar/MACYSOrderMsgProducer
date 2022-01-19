package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.components.ProducerQueueJSON;
import com.order.components.ProducerQueueXML;
import com.order.dto.OrderMsgProducerJson;
import com.order.dto.xml.FulfillmentOrder;

@Service
public class MacyProducerServiceImpl implements MacyProducerService {
	@Autowired
	ProducerQueueJSON producerJsonQueue;

	@Autowired
	ProducerQueueXML producerXmlQueue;

	// #Query to Produce Json msg to rabbit queue
	@Override
	public ResponseEntity<String> produceJsonMsg(OrderMsgProducerJson orderMessageJson) {
		try {
			producerJsonQueue.send(orderMessageJson);
			return new ResponseEntity<>("Producer Produced successfull", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Producer Produced failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// #Query to Produce Xml msg to rabbit queue
	public ResponseEntity<String> produceXmlMsg(FulfillmentOrder fulfillmentOrder) {
		try {
			producerXmlQueue.send(fulfillmentOrder);
			return new ResponseEntity<>("XML produced Successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("XML producing Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
