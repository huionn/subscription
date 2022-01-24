package com.test.subscription.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.subscription.model.SubscriptionReq;
import com.test.subscription.model.SubscriptionResp;
import com.test.subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {
	
	private final SubscriptionService subscriptionService;

	@PostMapping(value = "/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
	public SubscriptionResp subscribe(@RequestBody SubscriptionReq req) {
		log.info("request: {} {} {}", req.getAmount(), req.getDuration(), req.getSubscriptionType());
		
		SubscriptionResp resp = subscriptionService.register(req);
		log.info("response: {}", resp.getInvoiceDates());
		
		return resp;
	}
}
