package com.test.subscription.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.subscription.model.SubscriptionReq;
import com.test.subscription.model.SubscriptionResp;
import com.test.subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimpleSubscriptionService implements SubscriptionService {
	
	private final Scheduler scheduler;

	@Override
	public SubscriptionResp register(SubscriptionReq req) {
		if (!req.getDuration().isValid()) {
			throw new IllegalArgumentException("duration is invalid");
		}
		
		SubscriptionResp resp = new SubscriptionResp();
		
		resp.setAmount(req.getAmount());
		resp.setSubscriptionType(req.getSubscriptionType());
		
		int firstDay;
		switch (req.getSubscriptionType()) {
		case WEEKLY:
			if (req.getDayOfWeek() == null) {
				throw new IllegalArgumentException("dayOfWeek is required for WEEKLY subscription");
			}
			firstDay = req.getDayOfWeek().getValue();
			break;
		case MONTHLY:
			if (req.getDayOfMonth() == 0) {
				throw new IllegalArgumentException("dayOfMonth is required for MONTHLY subscription");
			}
			firstDay = req.getDayOfMonth();
			break;
		default:
			firstDay = 0;
			break;
		}
		List<LocalDate> dates = scheduler.generateDates(req.getDuration(), firstDay, req.getSubscriptionType());
		resp.setInvoiceDates(dates);
		
		return resp;
	}
}
