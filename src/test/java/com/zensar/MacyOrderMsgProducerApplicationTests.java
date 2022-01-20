package com.zensar;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.order.controller.MacyProducerController;
import com.order.dto.OrderMsgProducerJson;
import com.order.dto.xml.FulfillmentOrder;
import com.order.service.MacyProducerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MacyOrderMsgProducerApplicationTests {

	 @Autowired
	    private MockMvc mvc;

	    @MockBean
	    MacyProducerService service;

	    @Autowired
	    MacyProducerController controller;

	    @Test
	    @DisplayName("Test Produced Xml Msg Order in rabbitmq")
	    void testProducedXmlMsgOrder()throws Exception{
	        FulfillmentOrder prodOrderXml = new FulfillmentOrder();
	        
	        given(service.produceXmlMsgToRabbitQ(prodOrderXml)).willReturn(new ResponseEntity<>("status of xml test case",
	        		HttpStatus.OK));

	        XmlMapper xmlMapper = new XmlMapper();
	        String xmlStr = null;
			try {
				xmlStr = xmlMapper.writeValueAsString(prodOrderXml);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}

	        try {
				mvc.perform(post("/orderProducer/ompxml")
				                .content(xmlStr)
				                .contentType(MediaType.APPLICATION_XML_VALUE))
				        .andExpect(status().isOk())
				        .andDo(print());
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	   
	    @Test
	    @DisplayName("Test Produced Json Msg Order in rabbitmq")
	    void testProducedJsonMsgOrder() throws Exception/*JsonProcessingException*/{
	        OrderMsgProducerJson prodOrderJson = new OrderMsgProducerJson();
	        given(service.produceJsonMsgToRabbitQ(prodOrderJson)).willReturn(new ResponseEntity<>("status of json test case",
	        		HttpStatus.OK));

	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonStr = null;
			try {
				jsonStr = objectMapper.writeValueAsString(prodOrderJson);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

	        mvc.perform(post("/orderProducer/ompjson")
	                        .content(jsonStr)
	                        .contentType(MediaType.APPLICATION_JSON_VALUE))
	                .andExpect(status().isOk())
	                .andDo(print());
	    }

	}