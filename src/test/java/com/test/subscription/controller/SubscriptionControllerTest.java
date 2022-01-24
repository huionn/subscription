package com.test.subscription.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.test.subscription.model.Duration;
import com.test.subscription.model.SubscriptionReq;
import com.test.subscription.model.SubscriptionResp;
import com.test.subscription.model.SubscriptionType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SubscriptionControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testSubscribeDaily() {
		SubscriptionReq req = new SubscriptionReq();
		req.setAmount(new BigDecimal("10.00"));
		Duration d = new Duration();
		d.setStartDate(LocalDate.of(2022, 1, 1));
		d.setEndDate(LocalDate.of(2022, 1, 3));
		req.setDuration(d);
		req.setSubscriptionType(SubscriptionType.DAILY);
		
		ResponseEntity<SubscriptionResp> resp = restTemplate.postForEntity("http://localhost:" + port + "/subscription", req, SubscriptionResp.class);
		SubscriptionResp sr = resp.getBody();
		
		assertEquals(3, sr.getInvoiceDates().size());
		assertEquals(LocalDate.of(2022, 1, 1), sr.getInvoiceDates().get(0));
		assertEquals(LocalDate.of(2022, 1, 3), sr.getInvoiceDates().get(2));
	}

}
