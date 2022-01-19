package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.OrderMsgProducerJson;
import com.order.dto.xml.FulfillmentOrder;
import com.order.service.MacyProducerService;

@RestController
@RequestMapping("/orderProducer")
public class MacyProducerController {

	@Autowired
	MacyProducerService macyProducerService;

	// #Query to Produce Json msg to rabbit queue
	@PostMapping(value = "/ompjson", consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> produceJsonMessage(@RequestBody OrderMsgProducerJson orderMessageJson) {
		return macyProducerService.produceJsonMsg(orderMessageJson);
	}

	// #Query to Produce XML msg to rabbit queue
	@PostMapping(value = "/ompxml", consumes = { MediaType.APPLICATION_XML_VALUE })
	ResponseEntity<String> produceXmlMessage(@RequestBody FulfillmentOrder fulfillmentOrder) {
		return macyProducerService.produceXmlMsg(fulfillmentOrder);
	}
}